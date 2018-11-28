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
      </div>
    </i-menu>
    <Row>
      <Col span="8">
        <i-menu theme="light">
          <Submenu name="1">
            <template slot="title">
              <Icon type="ios-people" />
              员工
            </template>
            <router-link to="/punch">
              <MenuItem name="1-1">员工打卡</MenuItem>
            </router-link>
            <router-link to="/leave">
              <MenuItem name="1-2">员工请假申请</MenuItem>
            </router-link>
            <router-link to="/getmessage">
              <MenuItem name="1-3">员工个人考勤信息</MenuItem>
            </router-link>
            <router-link to="/others">
              <MenuItem name="1-4">同项目员工请假信息</MenuItem>
            </router-link>
            <router-link to="/out">
              <MenuItem name="1-5">员工外出申请</MenuItem>
            </router-link>
          </Submenu>
        </i-menu>
      </Col>
      <Col span = "16">

          <p style="position: absolute; top: 30px; left: 50px; font-size: 25px">
            输入您的卡号
          </p>
        <Input prefix="ios-contact" v-model="id1" placeholder="输入您的卡号" style="position: absolute; width: 500px; top: 80px; left: 50px;"/>
        <i-button type = "default" style="position: absolute; top: 180px;left: 500px" @click="punch1()">
          上班打卡
        </i-button>
        <i-button type = "default" style="position: absolute; top: 180px;left: 600px" @click="punch2()">
          下班打卡
        </i-button>
      </Col>

    </Row>



  </div>

</template>

<script>
  export default {
    name: "user",
    data(){
      return{
        id1: '',
      }
    },
    methods:{
      punch1() {//打卡
        if (this.id1 == '') {
          this.$Message.info("请不要输入空值");
        }
        else {
          this.$axios.post('/api/punch', {id: this.id1,function:'1'})//传编号，state变为1
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
        }
      },
      punch2(){
        if (this.id1 == '') {
          this.$Message.info("请不要输入空值");
        }
        else {
          this.$axios.post('/api/punch', {id: this.id1,function:'2'})//传编号，state变为0
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

