package com.example.demo.common;

import com.example.demo.tool.DBManager;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RestController
@CrossOrigin
@ResponseBody
public class GetOtherController {
    @RequestMapping(value = "/getOthers", method = RequestMethod.POST)
    public ArrayList<GetOthersBean> login(@RequestBody Getother js) {
        String userId = js.getId();
        Date time = js.getTime();
        Date date=new Date();
        ArrayList<GetOthersBean> flist=new ArrayList<GetOthersBean>();
        ArrayList<String> idlist=getid(userId);
        for(int i = 0 ; i < idlist.size(); i++){
            GetOthersBean g = isout(idlist.get(i),date);
            if(g.getU_id()!="99999"){
                flist.add(g);
            }
        }

return flist;
    }
    //返回传来id的同部门同事id
    public ArrayList<String> getid(String id){
        ArrayList<String> IdList= new ArrayList<String>();
        try {
            String sql="select id from worker where department=(select department from worker where id ="+id+");";
            DBManager dbManager = new DBManager(sql);
            ResultSet result;
            String dbid = null;
            result = dbManager.preparedStatement.executeQuery();
            while (result.next()) {
                dbid = result.getString("id");
                if(!dbid.equals(id)) {
                    IdList.add(dbid);
                }
            }

            result.close();
            dbManager.close();

        } catch (Exception e) {
            System.out.print(e);
        }
        return IdList;
    }
    //判断某个人是不是在请假/外出，zhijiefanhui
    public GetOthersBean isout(String id,Date date){
        GetOthersBean getOthersBean = new GetOthersBean();
        getOthersBean.setU_id("99999");
        String sql = "select stime,etime from leave_form where wid="+id+" and status = 5 union " +
                     "select stime ,etime from out_form where wid="+id+" and status = 5;";
        try{
            DBManager dbManager = new DBManager(sql);
            ResultSet result;
            result = dbManager.preparedStatement.executeQuery();

            while (result.next()) {
                //如果界面时间在请假/外出的起止时间之间
                if(date.compareTo(result.getDate("etime"))*
                        date.compareTo(result.getDate("stime"))<0){

                    getOthersBean.setTime_start(result.getDate("stime"));
                    getOthersBean.setTime_end(result.getDate("etime"));
                    getOthersBean.setU_id(id);
                    break;
                }
            }

            String sqll="select name,work from worker where id ="+id+";";
            dbManager = new DBManager(sqll);
            result = dbManager.preparedStatement.executeQuery();
            while(result.next()){
                getOthersBean.setU_name(result.getString("name"));
                getOthersBean.setU_work(result.getString("work"));
            }


            result.close();
            dbManager.close();
        }catch (Exception e) {
            System.out.print(e);
        }
        return getOthersBean;
    }
}
class Getother{
    private String id;
    private Date time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date date) {
        this.time =date ;
    }
}
//返回的json数据类
class GetOthersBean{
    private String u_id;
    private String u_name;
    private Date time_start;
    private Date time_end;
    private String u_work;


    public String getU_work() {
        return u_work;
    }

    public void setU_work(String u_work) {
        this.u_work = u_work;
    }

    public void setTime_start(Date time_start) {
        this.time_start = time_start;
    }

    public void setTime_end(Date time_end) {
        this.time_end = time_end;
    }

    public Date getTime_end() {
        return time_end;
    }

    public Date getTime_start() {
        return time_start;
    }

    public String getU_id() {
        return u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }
}