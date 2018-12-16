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
            <Icon type="ios-time" />
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
            <Icon type="ios-contacts" />
            同部门员工请假信息查询
          </menu-item>
        </router-link>
        <router-link to="/">
          <menu-item name="7">
            <Icon type="md-power" />
            注销
          </menu-item>
        </router-link>
      </div>
    </i-menu>
    <br>
    <Table stripe border :columns="columns8" :data="data7" size="small" ref="table" style="position: absolute; width: 1000px;left: 50px"></Table>
    <Button type="default" size="large" @click="get_others()" style="position: absolute; left: 1100px"><Icon type="ios-download-outline"></Icon> 刷新员工请假表 </Button>
  </div>

</template>

<script>
    export default {
        name: "get_other_message",
      data(){
          return{
            date:new Date(),
            id:sessionStorage.getItem("userId"),
            status:sessionStorage.getItem("userStatus"),
            columns8: [
              {
                "title": "同事id",
                "key": "u_id",
                "width": 150,
                "sortable":true
              },
              {
                "title": "同事姓名",
                "key": "u_name",

                "width": 150,
                "sortable":true
              },
              {
                "title": "工作信息",
                "key": "u_work",
                "width": 300,
              },
              {
                "title": "开始时间",
                "key": "time_start",
                "width": 200,
                "sortable": true
              },
              {
                "title": "结束时间",
                "key": "time_end",
                "width": 200,
                "sortable": true
              },

            ],
            data7: [
              {
                "time_start":"2018-1-3",
                "time_end":"2018-1-7",
                "u_id":"3016218125",
               "u_name":"李狗蛋",
                "u_work":"项目开发"

              }

            ]

          }
      },
      mounted:function () {
        this.getMessage();
      },
      methods:{
        getMessage() {
          this.$axios.post('http://192.168.21.102:9090/getOthers', {id: this.id,time:this.date.toLocaleDateString()})
            .then((response) => {
              this.$Message.info("提交成功");
              this.data7 = response.data;
            })
            .catch(function (error) {
              //this.$Message.info("服务器出错，注册失败" + error.data);
              console.log(error)
            });
        },
          get_others(){
            this.$axios.post('http://192.168.21.102:9090/getOthers', {id:this.id,time:this.date.toLocaleDateString()})
              .then((response) => {
                this.data7 = response.data;
              })
              .catch((error) => {
                // this.$Message.info("搜索失败");
                console.log(error);
              });

          }
      }
    }
</script>

<style scoped>

</style>
