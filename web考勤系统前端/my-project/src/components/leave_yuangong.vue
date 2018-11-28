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
        <Select v-model="model2" placeholder="选择您的请假目的" style="position: absolute; top: 190px;left: 50px;width: 250px">
          <Option v-for="item in reasons" :value="item.value" :key="item.value">{{ item.label }}</Option>
        </Select>
        <Input prefix="ios-contact" v-model="message_leave" placeholder="详细说明请假理由" style="position: absolute; width: 500px; top: 250px; left: 50px;"/>
        <!--<i-button type = "default" style="position: absolute; top: 320px;left: 500px">-->
          <!--提交-->
        <!--</i-button>-->
        <p style="position: absolute; top: 300px; left: 50px; font-size: 25px">
          选择您的请假时间
        </p>
        <Input  v-model="message_days" placeholder="输入天数" style="position: absolute; width: 500px; top: 350px; left: 50px;"/>
        <DatePicker type="daterange" @on-change="handleChange" format="yyyy-MM-dd" show-week-numbers placement="bottom-end" placeholder="Select date" style="position:absolute;width: 500px;left: 50px;top: 400px"></DatePicker>
        <i-button type = "default" style="position: absolute; top: 500px;left: 600px" @click="leave()">
        提交
        </i-button>
      </Col>
    </Row>


  </div>

</template>

<script>
    export default {
        name: "leave_yuangong",
      data () {
        return {

          id1:'',
          reasons: [
            {
              value: 'reason1',
              label: '因病'
            },
            {
              value: 'reason2',
              label: '因私事'
            },
            {
              value: 'reason3',
              label: '婚假'
            },
            {
              value: 'reason4',
              label: '产假'
            },
            {
              value:'reason5',
              label: '年假',
            },
            {
              value:'reason6',
              label: '公事外出',
            }
          ],
          model2: '',
          message_leave:'',
          message_days:'',
          dataValue: null
        }
      },
      methods:{
        handleChange(daterange) {
          this.dataValue = daterange;
          //console.log(this.datavalue);
        },
        leave(){
          console.log(this.dataValue[1]);
          console.log(this.dataValue[0]);//卡号，原因，原因类别，开始时间，结束时间，天数，
          this.$axios.post('/api/leave', {id: this.id1, reason: this.message_leave, item:this.model2, time_start: this.dataValue[0],time_end: this.dataValue[1],days:this.message_days})
            .then((response) => {

              if (response.data == true) {
                this.$Message.info("提交成功");
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
</script>

<style scoped>

</style>
