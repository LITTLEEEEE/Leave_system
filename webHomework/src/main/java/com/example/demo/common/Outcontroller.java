package com.example.demo.common;

import com.example.demo.tool.DBManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;


@RestController
@CrossOrigin
@ResponseBody
public class Outcontroller {
    private String status;
    private String password;
    private String reason;
    private Date time_start;
    private Date time_end;
    private Integer days;

    @RequestMapping(value = "/out", method = RequestMethod.POST)
    public boolean out(@RequestBody JS_2 js/*@RequestParam(value = "status", required = true) String status,
                        @RequestParam(value = "id", required = true) String id,
                        @RequestParam(value = "reason", required = true) String reason,
                        @RequestParam(value = "time_start", required = true) Date time_start,
                        @RequestParam(value = "time_end", required = true) Date time_end,
                         @RequestParam(value = "days", required = true) Integer days*/) {
//        userName = httpServletRequest.getParameter("id");
//        password = httpServletRequest.getParameter("passwords");
        String id = js.getId();
        String status = js.getStatus();
        Date time_start = js.getTime_start();
        Date time_end = js.getTime_end();
        int days = js.getDays();
        String reason = js.getReason();


        String error = "no error";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String time_s = df.format(time_start);
        String time_e = df.format(time_end);
        String status_temp = null;
        if(status.equals("department_manager")){
            status_temp = "1";
        }else if(status.equals("staff")){
            status_temp = "0";
        }else{
            status_temp = "2";
        }


        String sql = "insert into out_form(wid, stime,etime,reason,otime,status,pre_statu) values ('"+id+"','"+time_s+"','"+time_e+"','"+reason+"','"+date+"',"+status_temp+","+status_temp+")";
        System.out.println(sql);
        try {
            DBManager dbManager = new DBManager(sql);
            dbManager.preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            error = e.toString();
        }
        return false;
    }


    @RequestMapping(value = "/leave", method = RequestMethod.POST)
    public boolean leave(@RequestBody JS_2 js/*@RequestParam(value = "status", required = true) String status,
                         @RequestParam(value = "id", required = true) String id,
                         @RequestParam(value = "item",required = true) String item,
                         @RequestParam(value = "reason", required = true) String reason,
                         @RequestParam(value = "time_start", required = true) Date time_start,
                         @RequestParam(value = "time_end", required = true) Date time_end,
                         @RequestParam(value = "days", required = true) Integer days*/) {
//        userName = httpServletRequest.getParameter("id");
//        password = httpServletRequest.getParameter("passwords");
        String id = js.getId();
        String status = js.getStatus();
        Date time_start = js.getTime_start();
        Date time_end = js.getTime_end();
        int days = js.getDays();
        String reason = js.getReason();
        String item = js.getItem();
        String error = "no error";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String time_s = df.format(time_start);
        String time_e = df.format(time_end);
        String status_temp = null;
        if(status.equals("department_manager")){
            status_temp = "1";
        }else if(status.equals("staff")){
            status_temp = "0";
        }else{
            status_temp = "2";
        }
        Integer type = null;
        if(item.equals("年假")){
            type = 0;
        }else if(item.equals("事假")){
            type = 1;
        }else if(item.equals("病假")){
            type = 2;
        }else if(item.equals("婚假")){
            type = 3;
        }else if(item.equals("产假")){
            type = 4;
        }

        String sql = "insert into leave_form(wid, stime,etime,description,otime,status,type,pre_statu) values ('"+id+"','"+time_s+"','"+time_e+"','"+reason+"','"+date+"',"+status_temp+","+type+","+status_temp+")";
        System.out.println(sql);
        try {
            DBManager dbManager = new DBManager(sql);
            dbManager.preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            error = e.toString();
        }
        return false;
    }

}

class JS_2{
    private String id;
    private String status;
    private Date time_start;
    private Date time_end;
    private int days;
    private String reason;
    private String item;


    public void setId(String id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTime_start(Date time_start) {
        this.time_start = time_start;
    }

    public void setTime_end(Date time_end){
        this.time_end = time_end;
    }

    public void setItem(String item){
        this.item = item;
    }

    public void setDays(int days){
        this.days = days;
    }

    public void setReason(String reason){
        this.reason = reason;
    }

    public Date getTime_end() {
        return time_end;
    }

    public Date getTime_start() {
        return time_start;
    }

    public String getId() {
        return id;
    }

    public int getDays() {
        return days;
    }

    public String getReason() {
        return reason;
    }

    public String getStatus() {
        return status;
    }

    public String getItem() {
        return item;
    }
}


