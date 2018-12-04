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
  </div>

</template>

<script>
    export default {
        name: "getLeave"
    }
</script>

<style scoped>

</style>
