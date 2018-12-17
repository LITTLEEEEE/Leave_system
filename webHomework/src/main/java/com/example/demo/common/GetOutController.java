package com.example.demo.common;

import ch.qos.logback.classic.boolex.GEventEvaluator;
import com.example.demo.tool.DBManager;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.ArrayUtils;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@ResponseBody
public class GetOutController {
    @RequestMapping(value = "/getOut", method = RequestMethod.POST)
    public ArrayList<GetOutBean> getout(@RequestBody Getout js) {
        String userId = js.getId();
        String statu = js.getStatus();
        ArrayList<GetOutBean> leavelist = getleave(userId);
            ArrayList<GetOutBean> outlist = getout(userId);
            for(int i = 0 ; i < outlist.size() ; i++){
                leavelist.add(outlist.get(i));
            }

        return leavelist;
    }
    public ArrayList<GetOutBean> getleave(String id){
        String sql = "select * from leave_form where wid = " + id + ";";
        //GetOutBean[] leavelist=new GetOutBean[50];
        ArrayList<GetOutBean> leavelist = new ArrayList<GetOutBean>();

        try {
            DBManager dbManager = new DBManager(sql);
            ResultSet result;
            result = dbManager.preparedStatement.executeQuery();

            int j = 0;
            while (result.next()) {

                GetOutBean getOutBean=new GetOutBean();
                getOutBean.setId(id);
                getOutBean.setTime_start(result.getDate("stime"));
                getOutBean.setTime_end(result.getDate("etime"));
                getOutBean.setReason(result.getString("description"));
                String item=null;
                int dbitem=5;
                dbitem=result.getInt("type");
                switch (dbitem){
                    case 0:item="年假";break;
                    case 1:item="事假";break;
                    case 2:item="病假";break;
                    case 3:item="婚假";break;
                    case 4:item="产假";break;
                    case 9 :item="cuowu";break;
                }
                getOutBean.setItem(item);
                String state=null;
                int dbstate=9;
                dbstate=result.getInt("status");
                if(dbstate==0||dbstate==1||dbstate==2){
                    state="正在审批";
                }else if(dbstate==5){
                    state="通过";
                }else if(dbstate==4||dbstate==6){
                    state="未通过";
                }else if(dbstate==9){
                    state="cuowu";
                }

                getOutBean.setState(state);
                if(state=="未通过") {
                    String description = "未通过--";
                    if (result.getString("opinion1") != null) {
                        description +="部门经理意见："+ result.getString("opinion1");
                    } else if (result.getString("opinion2") != null) {
                        description +="副总经理意见："+ result.getString("opinion2");
                    } else if (result.getString("opinion3") != null) {
                        description +="总经理意见：" +result.getString("opinion3");
                    }
                    getOutBean.setDescription(description);
                }else if(state=="通过"){
                    getOutBean.setDescription("请假申请合理，予以通过");
                }else if(state=="正在审批"){
                    getOutBean.setDescription("正在审批请稍等");
                }
                leavelist.add(getOutBean);
                j++;
            }
            for(int i = 0 ; i < leavelist.size() ; i++){
                String sqll="select annual_remaining from worker where id ="+leavelist.get(i).getId()+";";
                DBManager dbManager1 = new DBManager(sqll);
                ResultSet result1;
                result1 = dbManager1.preparedStatement.executeQuery();
                while(result1.next()){
                    leavelist.get(i).setLeft_days(result1.getInt("annual_remaining"));
                }
                if(i==leavelist.size()-1){
                    result1.close();
                    dbManager1.close();
                }
            }
            result.close();
            dbManager.close();
          //return  leavelist;
        } catch (Exception e) {
            System.out.print(e);
        }
return leavelist;
    }
    public ArrayList<GetOutBean> getout(String id){
        String sql = "SELECT * FROM out_form where wid = " + id + ";";
        ArrayList<GetOutBean> outlist = new ArrayList<GetOutBean>();
        try {
            DBManager dbManager = new DBManager(sql);
            ResultSet result;
            String DBpassword = null;
            result = dbManager.preparedStatement.executeQuery();
            int j = 0;
            while (result.next()) {
                GetOutBean getOutBean=new GetOutBean();
                getOutBean.setId(id);
                getOutBean.setTime_start(result.getDate("stime"));
                getOutBean.setTime_end(result.getDate("etime"));
                getOutBean.setReason(result.getString("reason"));
                getOutBean.setItem("外出");
                String state=null;
                int dbstate=9;
                dbstate=result.getInt("status");

                if(dbstate==0||dbstate==1||dbstate==2){
                    state="正在审批";
                }else if(dbstate==5){
                    state="通过";
                }else if(dbstate==4||dbstate==6){
                    state="未通过";
                }

                getOutBean.setState(state);
                if(state=="未通过") {
                    String description = "未通过--";
                    if (result.getString("opinion1") != null) {
                        description +="部门经理意见："+ result.getString("opinion1");
                    } else if (result.getString("opinion2") != null) {
                        description +="副总经理意见："+ result.getString("opinion2");
                    } else if (result.getString("opinion3") != null) {
                        description +="总经理意见：" +result.getString("opinion3");
                    }
                    getOutBean.setDescription(description);
                }else if(state=="通过"){
                    getOutBean.setDescription("外出申请合理，予以通过");
                }else if(state=="正在审批"){
                    getOutBean.setDescription("正在审批请稍等");
                }
                outlist.add(getOutBean);
                j++;
            }
            for(int i = 0 ; i < outlist.size() ; i++){
                String sqll="select annual_remaining from worker where id ="+outlist.get(i).getId()+";";
                DBManager dbManager1 = new DBManager(sqll);
                ResultSet result1;
                result1 = dbManager1.preparedStatement.executeQuery();
                while(result1.next()){
                    outlist.get(i).setLeft_days(result1.getInt("annual_remaining"));
                }
                if(i==outlist.size()){
                    result1.close();
                    dbManager1.close();
                }
            }
            result.close();
            dbManager.close();
        } catch (Exception e) {
            System.out.print(e);
        }
        return outlist;
    }
}
class Getout{
    private String id;
    private String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
class GetOutBean{
    private Date time_start;
    private Date time_end;
    private String item;
    private String state;
    private int left_days;
    private String description;
    private String id;
    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTime_start() {
        return time_start;
    }

    public Date getTime_end() {
        return time_end;
    }

    public int getLeft_days() {
        return left_days;
    }

    public String getDescription() {
        return description;
    }

    public String getItem() {
        return item;
    }

    public String getState() {
        return state;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setLeft_days(int left_days) {
        this.left_days = left_days;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setTime_end(Date time_end) {
        this.time_end = time_end;
    }

    public void setTime_start(Date time_start) {
        this.time_start = time_start;
    }

}