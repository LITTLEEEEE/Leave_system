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
    </Row>


  </div>

</template>

<script>
  //功能：分为：打卡，考勤查询和请假查询，请假申请，外出申请，同部门请假查询
    export default {
        name: "user"
    }
</script>

<style scoped>

</style>
