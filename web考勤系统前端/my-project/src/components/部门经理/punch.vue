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
      <router-link to="/punch2">
        <menu-item name="2">
          <Icon type="ios-time" />
          打卡
        </menu-item>
      </router-link>

      <router-link to="/check">
        <menu-item name="2">
          <icon type="ios-contact"></icon>
          考勤检查
        </menu-item>
      </router-link>
      <router-link to="/leavedeals">
        <menu-item name="3">
          <icon type="ios-contact"></icon>
          请假审批
        </menu-item>
      </router-link>
      <router-link to="/outdeals">
        <menu-item name="4">
          <icon type="ios-contact"></icon>
          外出审批
        </menu-item>
      </router-link>
      <router-link to="/leave2">
        <menu-item name="5">
          <icon type="ios-contact"></icon>
          请假申请
        </menu-item>
      </router-link>
      <router-link to="/out2">
        <menu-item name="6">
          <icon type="ios-contact"></icon>
          外出申请
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
  <p style="position: absolute; top: 75px;left: 30px;font-size: 20px">输入您的账号</p>
  <Input prefix="ios-contact" placeholder="输入卡号" v-model="id" style="width: auto; position: absolute; top: 75px; left: 200px"></Input>
  <Button @click="punch(1)" style="position: absolute; top: 130px;left: 250px;">打卡上班</Button>
  <Button type="primary" @click="punch(0)" style="position: absolute; top: 130px;left: 350px;">打卡下班</Button>
</div>
</template>

<script>
    export default {
        name: "punch",
      data(){
        return{
          id:sessionStorage.getItem("userId"),
          status:sessionStorage.getItem("userStatus"),
          date:new Date()
        }
      },
      methods: {
        punch(index) {
          if(index == 1){
            console.log(this.date.toLocaleDateString());
            console.log(this.id);
            console.log(this.status);
            this.$axios.post('http://localhost:9090/punch', {id:this.id, function: '1',status:this.status,punch_date:this.date.toLocaleDateString()})
              .then((response) => {
                console.log(response);

                if (response.data.result == true) {
                  this.$Message.info("打卡上班成功");
                }
                else {
                  this.$Message.info("未打卡成功请重试");
                }

              })
              .catch(function (error) {
                //this.$Message.info("服务器出错，注册失败" + error.data);
                console.log(error)
              });

          }else{
            this.$axios.post('http://localhost:9090/punch', {id:this.id, function: '2',status:this.status,punch_date:this.date.toLocaleDateString()})
              .then((response) => {

                if (response.data.result == true) {
                  this.$Message.info("打卡下班成功");
                }
                else {
                  this.$Message.info("未打卡成功请重试");
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
