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
public class CheckController {
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ArrayList<checkbeans> login(@RequestBody check js) {
    String id=js.getId();
    String status=js.getStatus();
    Date date=new Date();
    //String da =(1900+date.getYear())+"-"+(1+date.getMonth())+"-"+(16+date.getDay());
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String dateNowStr = sdf.format(date);
    String sql = "select A.wid ,A.stime ,W.work,W.name from attendance A ,worker W where to_days(A.stime)" +
            "=to_days(\""+dateNowStr+"\")and A.wid=W.id;";
    System.out.print(sql);
    ArrayList<checkbeans> arrayList=new ArrayList<checkbeans>();

    try{
        DBManager dbManager = new DBManager(sql);
        ResultSet result;
        result = dbManager.preparedStatement.executeQuery();
        while(result.next()){
            checkbeans cb=new checkbeans();
            cb.setState("1");
            cb.setTime(result.getString("stime"));
            cb.setU_id(result.getString("wid"));
            cb.setU_name(result.getString("name"));
            cb.setU_work(result.getString("work"));
            arrayList.add(cb);
        }
        dbManager.close();
        result.close();
    }catch (Exception e) {
        System.out.print(e);
    }
    return arrayList;
    }
}
class checkbeans{
    private String u_id;
    private String u_name;
    private String state;
    private String time;
    private String u_work;

    public void setU_work(String u_work) {
        this.u_work = u_work;
    }

    public String getU_work() {
        return u_work;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public String getU_id() {
        return u_id;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
class check {
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