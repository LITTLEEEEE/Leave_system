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
<router-link to="/check2">
        <menu-item name="2">
          <icon type="ios-contact"></icon>
          考勤检查
        </menu-item>
        </router-link>
        <router-link to="/leavedeals2">
        <menu-item name="3">
          <icon type="ios-contact"></icon>
          请假审批
        </menu-item>
        </router-link>
        <router-link to="/outdeals2">
        <menu-item name="4">
          <icon type="ios-contact"></icon>
          外出审批
        </menu-item>
        </router-link>
        <router-link to="/leave3">
        <menu-item name="5">
          <icon type="ios-contact"></icon>
          请假申请
        </menu-item>
        </router-link>
        <router-link to="/out3">
        <menu-item name="6">
          <icon type="ios-contact"></icon>
          外出申请
        </menu-item>
        </router-link>
      </div>
    </i-menu>


  </div>

</template>


<script>
    export default {
        name: "messager"
    }
</script>

<style scoped>

</style>
