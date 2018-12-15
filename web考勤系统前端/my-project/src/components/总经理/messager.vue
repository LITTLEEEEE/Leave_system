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
<router-link to="/check3">
        <menu-item name="2">
          <icon type="ios-contact"></icon>
          考勤检查
        </menu-item>
        </router-link>
        <router-link to="/leavedeals3">
        <menu-item name="3">
          <icon type="ios-contact"></icon>
          请假审批
        </menu-item>
        </router-link>
        <router-link to="/outdeals3">
        <menu-item name="4">
          <icon type="ios-contact"></icon>
          外出审批
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


  </div>

</template>


<script>
    export default {
        name: "messager"
    }
</script>

<style scoped>

</style>
