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
    <br>
    <Button type="default" size="large" @click="get_leaves()" ><Icon type="ios-download-outline"></Icon> 刷新员工请假表 </Button>
    <Button type="default" size="large" @click="exportData(1)" ><Icon type="ios-download-outline"></Icon> 原始数据下载 </Button>
    <Button type="default" size="large" @click="exportData(2)" ><Icon type="ios-download-outline"></Icon> 处理后数据下载</Button>
    <br>
    <br>
    <Table stripe border :columns="columns8" :data="data7" size="small" ref="table" ></Table>
  </div>

</template>

<script>
    export default {
        name: "getLeave",
      data(){
          return{
            id:sessionStorage.getItem("userId"),
            status:sessionStorage.getItem("userStatus"),
            columns8: [
              {
                "title": "开始时间",
                "key": "time_start",
                "width": 200,
                "sortable":true
              },
              {
                "title": "结束时间",
                "key": "time_end",

                "width": 200,
                "sortable":true
              },
              {
                "title": "请假类型",
                "key": "item",
                "width": 120,
                "sortable": true
              },
              {
                "title": "请假原因",
                "key": "reason",
                "width": 300,
                "sortable": true
              },
              {
                "title": "处理状态",
                "key": "state",
                "width": 200,
                //"sortable": true
              },
              {
                "title": "剩余年假",
                "key": "left_days",
                "width": 100,
              },
              {
                "title": "处理详情",
                "key": "description",
                "width": 300,
              },



            ],
            data7: [
              {
                "time_start":"2018-1-3",
                "time_end":"2018-1-7",
                "item":"病假",
                "reason":"发烧感冒四肢无力腰酸背痛手脚冰凉",
                "state":"审批通过",
                "left_days":3,
                "description":"无"

              }

            ]

          }
      },
      mounted:function () {
        this.getMessage();
      },
      methods: {
        getMessage() {
          this.$axios.post('/api/getOut', {id: this.id,status:this.status})
            .then((response) => {
              this.$Message.info("提交成功");
              this.data7 = response.data;
            })
            .catch(function (error) {
              //this.$Message.info("服务器出错，注册失败" + error.data);
              console.log(error)
            });
        },
        exportData(type) {
          if (type === 1) {
            this.$refs.table.exportCsv({
              filename: '原始数据下载'
            });
          } else if (type === 2) {
            this.$refs.table.exportCsv({
              filename: '排序数据下载',
              original: false
            });
          } else if (type === 3) {
            this.$refs.table.exportCsv({
              filename: '基本数据下载',
              columns: this.columns8.filter((col, index) => index < 3),
              data: this.data7.filter((data, index) => index < 3)
            });
          }
        },
        get_leaves() {
          this.$axios.post('/api/getOut', {id:this.id,status:this.status})
            .then((response) => {
              this.data7 = response.data;
            })
            .catch((error) => {
              // this.$Message.info("搜索失败");
              console.log(error);
            });
        },


      }
    }
</script>

<style scoped>

</style>
