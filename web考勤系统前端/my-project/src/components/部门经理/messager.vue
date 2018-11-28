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
              部门经理
            </template>
            <router-link to="/punch">
              <MenuItem name="1-1">请假信息审核</MenuItem>
            </router-link>
            <router-link to="/leave">
              <MenuItem name="1-2">部门经理请假申请</MenuItem>
            </router-link>
            <router-link to="/getmessage">
              <MenuItem name="1-3">查看个人考勤信息</MenuItem>
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
    export default {
        name: "messager"
    }
</script>

<style scoped>

</style>
