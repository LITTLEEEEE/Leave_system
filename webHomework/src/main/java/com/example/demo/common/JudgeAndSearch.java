package com.example.demo.common;


import com.example.demo.tool.DBManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;

@RestController
@CrossOrigin
@ResponseBody
public class JudgeAndSearch {

    @RequestMapping(value = "/outDeals", method = RequestMethod.POST)
    public ArrayList<OutBean> outDeals(@RequestBody JS1 js/*@RequestParam(value = "id", required = true) String id,
                        @RequestParam(value = "status", required = true) String status*/) {
//        userName = httpServletRequest.getParameter("id");
//        password = httpServletRequest.getParameter("passwords");
        String status = js.getStatus();
        String id = js.getId();
        String error = "no error";
        String status_temp = null;
        if(status.equals("department_manager")){
            status_temp = "1";
        }else if(status.equals("deputy_general_manager")){
            status_temp = "2";
        }else{
            status_temp = "3";
        }
        System.out.println(status_temp );
        String sql = null;
        if(status_temp.equals("1")){
            sql = "SELECT * FROM out_form where status < " + status_temp + " AND wid IN(select id from worker where department in (select department from worker where id="+id+"))";
        }else {
            sql = "SELECT * FROM out_form where status < " + status_temp;
        }
            System.out.println(sql);
            ArrayList<OutBean> array = new ArrayList<OutBean>();
            try {
                DBManager dbManager = new DBManager(sql);
                ResultSet result;
                result = dbManager.preparedStatement.executeQuery();
                while (result.next()) {
                    OutBean out = new OutBean();
                    out.setu_id(result.getString("wid"));
                    out.setd_id(result.getString("id"));
                    out.settime_end(result.getDate("etime"));
                    out.settime_start(result.getDate("stime"));
                    out.setreason(result.getString("reason"));
                    out.setdays();
                    String wid = out.getu_id();
                    String name = select(wid);
                    out.setu_name(name);
                    array.add(out);
                }
                return array;

            } catch (Exception e) {
                error = e.toString();
            }

            return null;


    }

    public String select(String wid){
        String sql = "SELECT * FROM worker where id = " + wid + ";";
        try {
            DBManager dbManager = new DBManager(sql);
            ResultSet result;
            String DB = null;
            result = dbManager.preparedStatement.executeQuery();
            while (result.next()) {
                DB = result.getString("name");
            }
            return DB;
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/leaveDeals", method = RequestMethod.POST)
    public ArrayList<LeaveBean> leaveDeals(@RequestBody JS1 js/*@RequestParam(value = "id", required = true) String id,
                                       @RequestParam(value = "status", required = true) String status*/) {
//        userName = httpServletRequest.getParameter("id");
//        password = httpServletRequest.getParameter("passwords");
        String status = js.getStatus();
        String id = js.getId();
        String[] s = {"年假", "事假", "病假",  "婚假",  "产假"};
        String status_temp = null;
        if(status.equals("department_manager")){
            status_temp = "1";
        }else if(status.equals("deputy_general_manager")){
            status_temp = "2";
        }else{
            status_temp = "3";
        }
        System.out.println(status_temp );
        String sql = null;
        if(status_temp.equals("1")){
            sql = "SELECT * FROM leave_form where status < " + status_temp + " AND wid IN(select id from worker where department in (select department from worker where id="+id+"))";
        }else {
            sql = "SELECT * FROM leave_form where status < " + status_temp;
        }
        System.out.println(sql);
        ArrayList<LeaveBean> array = new ArrayList<LeaveBean>();
        try {
            DBManager dbManager = new DBManager(sql);
            ResultSet result;
            result = dbManager.preparedStatement.executeQuery();
            while (result.next()) {
                LeaveBean out = new LeaveBean();
                out.setU_id(result.getString("wid"));
                out.setD_id(result.getString("id"));
                out.setTime_e(result.getDate("etime"));
                out.setTime_s(result.getDate("stime"));
                out.setReason(result.getString("description"));
                out.setItem(s[result.getInt("type")]);
                out.setDays();
                String wid = out.getU_id();
                String name = select(wid);
                out.setU_name(name);
                int left_days = select_leftdays(wid);
                out.setLeft_days(left_days);
                array.add(out);
                System.out.println(s[result.getInt("type")]);
            }
            return array;

        } catch (Exception e) {
            return null;
        }
    }

    public int select_leftdays(String wid){
        String sql = "SELECT * FROM worker where id = " + wid + ";";
        try {
            DBManager dbManager = new DBManager(sql);
            ResultSet result;
            Integer DB = null;
            result = dbManager.preparedStatement.executeQuery();
            while (result.next()) {
                DB = result.getInt("annual_remaining");
            }
            return DB;
        } catch (Exception e) {
            return -1;
        }
    }
}

class LeaveBean{
    private String u_name;
    private String u_id;
    private String d_id;
    private String reason;
    private String item;
    private Date time_start;
    private Date time_end;
    private int days;
    private int left_days;

    public String getU_name(){
        return u_name;
    }
    public String getU_id(){
        return u_id;
    }
    public String getD_id(){
        return d_id;
    }
    public String getReason(){
        return reason;
    }
    public String getItem(){
        return item;
    }
    public Date getTime_s(){
       return time_start;
    }
    public Date getTime_e(){
        return time_end;
    }
    public int getDays(){
        return days;
    }
    public int getLeft_days(){
        return left_days;
    }

    public void setU_name(String u_name){
        this.u_name = u_name;
    }
    public void setU_id(String u_id){
        this.u_id = u_id;
    }
    public void setD_id(String d_id){
        this.d_id = d_id;
    }
    public void setReason(String reason){
        this.reason = reason;
    }
    public void setItem(String item){
        this.item = item;
    }
    public void setTime_s(Date time_start){
        this.time_start = time_start;
    }
    public void setTime_e(Date time_end){
        this.time_end = time_end;
    }
    public void setDays(){
        this.days = (int)((time_end.getTime()-time_start.getTime())/(24*60*60*1000));
    }
    public void setLeft_days(int left_days){
        this.left_days = left_days;
    }
}

class OutBean {
    private String u_name;
    private String u_id;
    private String d_id;
    private String reason;
    private Date time_start;
    private Date time_end;
    private int days;

    public String getu_name() {
        return u_name;
    }

    public String getu_id() {
        return u_id;
    }

    public Date gettime_start() {
        return time_start;
    }

    public Date gettime_end() {
        return time_end;
    }

    public String getreason(){
        return reason;
    }
    public int getdays(){
        return days;

    }

    public String getd_id(){
        return d_id;
    }

    public void setd_id(String d_id){
        this.d_id = d_id;
    }

    public void setu_name(String u_name) {
        this.u_name= u_name;
    }

    public void setu_id(String userID) {
        this.u_id = userID;
    }

    public void setreason(String reason){
        this.reason = reason;
    }
    public void settime_start(Date time_start){
        this.time_start = time_start;
    }
    public void settime_end(Date time_end){
        this.time_end = time_end;
    }
    public void setdays(){
        this.days = (int)((time_end.getTime()-time_start.getTime())/(24*60*60*1000));
    }
}


class JS1{
    private String id;
    private String status;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}