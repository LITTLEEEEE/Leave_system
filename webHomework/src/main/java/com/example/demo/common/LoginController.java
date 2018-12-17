package com.example.demo.common;


import com.example.demo.tool.DBManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;

@RestController
@CrossOrigin
@ResponseBody
public class LoginController {
    private String userName;
    private String password;

//    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
//    public LoginBean loginPage() {
//        LoginBean loginBean = new LoginBean();
//        loginBean.setPassword("123456");
//        loginBean.setUserID("liuyuesen");
//        return loginBean;
//    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginBean login(@RequestBody JS js) {
        String userName=js.getId();
        String password=js.getPasswords();
        LoginBean loginBean = new LoginBean();
        String sql = "SELECT password,category FROM worker where id = " + userName + ";";
        try {
            DBManager dbManager = new DBManager(sql);
            ResultSet result;
            String DBpassword = null;
            int category = 4;
            result = dbManager.preparedStatement.executeQuery();
            while (result.next()) {
                DBpassword = result.getString("password");
                category = Integer.parseInt(result.getString("category"));
            }
            if (DBpassword.equals(password)) {
                result.close();
                dbManager.close();
                loginBean.setResult(true);
                loginBean.setStatus(getStatus(category));
                return loginBean;
            }
        } catch (Exception e) {
        }
        loginBean.setResult(false);
        loginBean.setStatus("密码错误");
        return loginBean;
    }

    private String getStatus(int number) {
        switch (number) {
            case 0:
                return "staff";
            case 1:
                return "department_manager";
            case 2:
                return "deputy_general_manager";
            case 3:
                return "general_manager";
            default:
                return "error";
        }
    }
}
class JS{
    private String id;
    private String passwords;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }
}
class LoginBean {
    private boolean result;
    private String status;

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public boolean getResult() {
        return result;
    }

}

