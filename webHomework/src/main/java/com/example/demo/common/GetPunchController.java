package com.example.demo.common;

import com.example.demo.tool.DBManager;
import org.springframework.web.bind.annotation.*;


import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@RestController
@CrossOrigin
@ResponseBody
public class GetPunchController {

    @RequestMapping(value = "/getPunch", method = RequestMethod.POST)
    public ArrayList<GetPunchBean> login(@RequestBody Getpunch js) {
//        userName = httpServletRequest.getParameter("id");
//        password = httpServletRequest.getParameter("passwords");
        ArrayList<GetPunchBean> al = new ArrayList<GetPunchBean>();
        String userId = js.getId();
        String statu = js.getStatus();
        GetPunchBean getPunchBean=new GetPunchBean();
        //设置判断是否打卡的Bool变量
        boolean res = false;

        String sql = "SELECT stime FROM attendance where wid = " + userId + ";";
        try {
            DBManager dbManager = new DBManager(sql);
            ResultSet result;
            Date date=new Date();
            SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd");
            dateFormat.format(date);
            result = dbManager.preparedStatement.executeQuery();
            while (result.next()) {
                if (isSameDate(date,result.getDate("stime"))){
                    res=true;
                    getPunchBean.setDescription("已打卡");
                    getPunchBean.setPunch_time(result.getDate("stime"));
                    break;
                }
            }
            if(res==false){
                getPunchBean.setDescription("未打卡");
                getPunchBean.setPunch_time(date);
            }
            al.add(getPunchBean);
            result.close();
            dbManager.close();
        } catch (Exception e) {
             System.out.print(e);
        }

        return al;
    }
    //判断两个日期是否相同的方法
    private static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                .get(Calendar.YEAR);
        boolean isSameMonth = isSameYear
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                .get(Calendar.DAY_OF_MONTH);

        return isSameDate;
    }

}
class Getpunch{
    private String id;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
class GetPunchBean {
    private Date punch_time ;
    private String description;

    public Date getPunch_time() {
        return punch_time;
    }

    public String getDescription() {
        return description;
    }

    public void setPunch_time(Date punch_time) {
        this.punch_time = punch_time;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

