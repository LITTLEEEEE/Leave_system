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
        <p style="position: absolute; top: 140px; left: 50px; font-size: 25px">
          输入您的请假目的
        </p>

        <Input prefix="ios-contact" v-model="message_out" placeholder="详细说明外出理由" style="position: absolute; width: 500px; top: 250px; left: 50px;"/>
        <!--<i-button type = "default" style="position: absolute; top: 320px;left: 500px">-->
        <!--提交-->
        <!--</i-button>-->
        <p style="position: absolute; top: 300px; left: 50px; font-size: 25px">
          选择您的外出时间
        </p>
        <Input  v-model="out_days" placeholder="输入天数" style="position: absolute; width: 500px; top: 350px; left: 50px;"/>
        <DatePicker type="daterange" @on-change="handleChange" format="yyyy-MM-dd" show-week-numbers placement="bottom-end" placeholder="Select date" style="position:absolute;width: 500px;left: 50px;top: 400px"></DatePicker>
        <!--<i-button type = "default" style="position: absolute; top: 500px;left: 600px" @click="leave()">-->
          <!--提交-->
        <!--</i-button>-->
        <i-button type="default" @click="modal1 = true" style="position: absolute; top: 500px;left: 600px">提交</i-button>
        <Modal
          v-model="modal1"
          title="确认信息"
          @on-ok="ok"
          @on-cancel="cancel">
          <p>请核实您的请假信息，核实无误后选择提交</p>

        </Modal>
      </Col>
    </Row>


  </div>

</template>

<script>
    export default {
        name: "out",
      data () {
        return {
          modal1: false,
          id1:'',
          out_days:0,
          message_out:'',
          dataValue: null

        }
      },
      methods: {
        handleChange(daterange) {
          this.dataValue = daterange;
          //console.log(this.datavalue);
        },
        ok () {
          this.$Message.info('进行提交');
          this.$axios.post('/api/out', {id: this.id1,reason:this.message_out, start_time:this.dataValue[0],end_time:this.dataValue[1],days:this.out_days})//传编号，外出原因，开始时间，终止时间，天数
            .then((response) => {

              if (response.data == true) {
                this.$Message.info("提交成功");
              }
              else {
                this.$Message.info("提交失败，请重试");
              }

            })
            .catch(function (error) {
              //this.$Message.info("服务器出错，注册失败" + error.data);
              console.log(error)
            });
        }
        },
        cancel () {
          this.$Message.info('修改信息');
        }

    }
</script>

<style scoped>

</style>
