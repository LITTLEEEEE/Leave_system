<template>
  <div>
    <i-menu mode="horizontal" theme="dark" active-name="1">
      <div class="layout-logo"></div>
      <div class="layout-nav">
        <menu-item name="1">
          <h1>
            <font color = #f0f8ff>
              公司考勤管理系统
            </font>
          </h1>
        </menu-item>
        <router-link to="/punch">
          <menu-item name="2">
            <icon type="ios-contact"></icon>
            上班打卡
          </menu-item>
        </router-link>
        <router-link to="/leave">
          <menu-item name="3">
            <icon type="ios-contact"></icon>
            申请请假
          </menu-item>
        </router-link>
        <router-link to="/out">
          <menu-item name="4">
            <icon type="ios-contact"></icon>
            申请外出
          </menu-item>
        </router-link>
        <router-link to="/getPunch">
          <menu-item name="5">
            <icon type="ios-contact"></icon>
            考勤信息查询
          </menu-item>
        </router-link>
        <router-link to="/getLeave">
          <menu-item name="6">
            <icon type="ios-contact"></icon>
            请假信息查询
          </menu-item>
        </router-link>
        <router-link to="/others">
          <menu-item name="6">
            <icon type="ios-contact"></icon>
            同部门员工请假信息查询
          </menu-item>
        </router-link>
      </div>
    </i-menu>
    <!--<strong style="position:absolute; left: 50px; top: 100px;font-size: 70px">-->
      <!--Hello-->
    <!--</strong>-->
    <!--<p style="position:absolute; left: 250px; top: 130px;font-size: 40px" >-->
      <!--{{Name}}-->
    <!--</p>-->
    <!--<p style="position:absolute; left: 50px; top: 200px;font-size: 40px">-->
      <!--您的卡号-->
    <!--</p>-->
    <!--<p style="position:absolute; left: 250px; top: 200px;font-size: 40px">-->
      <!--{{this.$route.params.id}}-->
    <!--</p>-->
    <!--<p style="position:absolute; left: 50px; top: 250px;font-size: 40px">-->
      <!--您的部门-->
    <!--</p>-->
    <!--<p style="position:absolute; left: 250px; top: 250px;font-size: 40px">{{Department}}</p>-->

    <!--<strong style="font-size: 60px; position:absolute;left: 100px;top: 500px;">-->
      <!--欢迎上班，开启全新的一天!(记得打卡哦！！！）-->
    <!--</strong>-->




  </div>

</template>

<script>
  //功能：分为：打卡，考勤查询和请假查询，请假申请，外出申请，同部门请假查询
    export default {
        name: "user",

      data(){
          return{
             Name:sessionStorage.getItem("userId"),

            // Department:sessionStorage.getItem("userDepartment"),
          }
      },
      // mounted:function () {
      //   this.getMessage();
      // },
      // methods:{
      //     getMessage(){
      //       this.$axios.post('/api/get', {id: this.$route.params.id})
      //         .then((response) => {
      //
      //           if (response.data == true) {
      //             this.$Message.info("提交成功");
      //             this.Name = response.data.name;
      //
      //           }
      //           else {
      //             this.$Message.info("账号不正确或连接失败，请重试");
      //           }
      //
      //         })
      //         .catch(function (error) {
      //           //this.$Message.info("服务器出错，注册失败" + error.data);
      //           console.log(error)
      //         });
      //
      //     }
      // }
    }
</script>

<style scoped>

</style>
