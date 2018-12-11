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

    <p style="position: absolute; top: 65px;left: 30px;font-size: 20px">输入您的账号</p>
    <Input prefix="ios-contact" placeholder="输入卡号" v-model="id" style="width: auto; position: absolute; top: 65px; left: 200px"></Input>
    <Button @click="punch(1)">打卡上班</Button>
    <Button type="primary" @click="punch(0)">打卡下班</Button>
  </div>

</template>

<script>
  export default {
    name: "user",
    data(){
      return{
        id:'',
      }
    },
    methods: {
      punch(index) {
        if(index == 1){
          this.$axios.post('/api/punch', {id:id, function: '1'})
            .then((response) => {

              if (response.data == true) {
                this.$Message.info("打卡上班成功");
              }
              else {
                this.$Message.info("账号不正确或连接失败，请重试");
              }

            })
            .catch(function (error) {
              //this.$Message.info("服务器出错，注册失败" + error.data);
              console.log(error)
            });

        }else{
          this.$axios.post('/api/punch', {id:id, function: '2'})
            .then((response) => {

              if (response.data == true) {
                this.$Message.info("打卡下班成功");
              }
              else {
                this.$Message.info("账号不正确或连接失败，请重试");
              }

            })
            .catch(function (error) {
              //this.$Message.info("服务器出错，注册失败" + error.data);
              console.log(error)
            });

        }

      }
    }

  }
</script>

<style scoped>

</style>

