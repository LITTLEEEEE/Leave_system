package com.example.demo;

import com.example.demo.tool.DBManager;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@CrossOrigin

public class PunchController {

    @RequestMapping(value = "/punch", method = RequestMethod.POST)
    public PunchBean Punch(@RequestBody get js) {
        String id=js.getId();
        String function=js.getFunction();
        String status=js.getStatus();
        String punch_date=js.getPunch_date();
        String sql=null;
        java.util.Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(date);
        if(function.equals("1")){
            sql = "Insert into attendance(wid,stime) values (" + id + ",'"+dateNowStr+"');";
        }else if(function.equals("2")){
            sql="update attendance set etime = '"+dateNowStr+"' where wid = "+id+" and stime ='"+dateNowStr+"';";
        }
System.out.print(sql);

        PunchBean punchBean = new PunchBean();
        boolean bool = true;
        try {
            DBManager dbManager = new DBManager(sql);
            dbManager.preparedStatement.executeUpdate();
            dbManager.close();
            bool = true;
        } catch (Exception e) {
            bool = false;
        }
        punchBean.setResult(bool);
        return punchBean;
    }
}
class get{
    private String id;
    private String function;
    private String status;
    private String punch_date;

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

    public void setFunction(String function) {
        this.function = function;
    }

    public String getFunction() {
        return function;
    }

    public String getPunch_date() {
        return punch_date;
    }

    public void setPunch_date(String punch_date) {
        this.punch_date = punch_date;
    }
}
class PunchBean {
    private boolean result;

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }
}

