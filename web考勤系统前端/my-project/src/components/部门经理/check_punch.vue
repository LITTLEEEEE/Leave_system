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
      </div>
    </i-menu>
    <Table :columns="columns8" :data="data7" size="small" ref="table" style="position: absolute; top: 70px; left: 30px"></Table>
    <br>
    <Button type="defult" size="large"@click="get_punch()" style="position:absolute;top: 70px;left: 850px;"><Icon type="ios-download-outline"></Icon> 刷新员工打卡表 </Button>
    <Button type="defult" size="large" @click="exportData(1)" style="position:absolute;top: 110px;left: 850px"><Icon type="ios-download-outline"></Icon> 原始数据下载 </Button>
    <Button type="defult" size="large" @click="exportData(2)" style="position:absolute;top: 150px;left: 850px"><Icon type="ios-download-outline"></Icon> 处理后数据下载</Button>
    <Button type="defult" size="large" @click="exportData(3)" style="position:absolute;top: 190px;left: 850px"><Icon type="ios-download-outline"></Icon> 基础数据下载</Button>

  </div>
</template>

<script>
    export default {
        name: "check_punch",
      data () {
        return {
          columns8: [
            {
              "title": "姓名",
              "key": "name",
              "fixed": "left",
              "width": 200
            },
            {
              "title": "打卡状态",
              "key": "state",
              "width": 150,
              "sortable": true,
              filters: [
                {
                  label: '已打卡上班',
                  value: 1
                },
                {
                  label: '未打卡上班',
                  value: 2
                },
                {
                  label: '请假中',
                  value: 2
                },
              ],
              filterMultiple: false,
              filterMethod (value, row) {
                if (value === 1) {
                  return row.state == 1;
                } else if (value === 2) {
                  return row.state ==0 ;
                }else if(value === 3){
                  return row.state == 2;
                }
              }
            },
            {
              "title": "编号",
              "key": "id",
              "width": 150,
              "sortable": true
            },
            {
              "title": "打卡时间",
              "key": "time",
              "width": 150,
              "sortable": true
            },
            {
              "title": "部门",
              "key": "department",
              "width": 150,
              "sortable": true
            },

          ],
          data7: [
            {
              "name": "Name1",
              "state" : 1,
              "id" : '3016218128',
              "time": '2017-1-1',
              "department":'215'
            },
            {
              "name": "Name2",
              "state" : 0,
              "id" : '3016218129',
              "time": '2017-1-1',
              "department":'215'
            },
            {
              "name": "Name3",
              "state" : 1,
              "id" : '3016218120',
              "time": '2017-1-1',
              "department":'215'
            },
            {
              "name": "Name4",
              "state" : 1,
              "id" : '3016218126',
              "time": '2017-1-2',
              "department":'215'
            },
            {
              "name": "Name5",
              "state" : 0,
              "id" : '3016218190',
              "time": '2017-1-2',
              "department":'215'
            },

          ]
        }
      },
      methods: {
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
        get_punch(){//name,state,id,time,department json数组
          this.$axios.post('/api/check', {status:'department_manager'})
            .then((response)=> {
              this.data7 = response.data;
            })
            .catch((error)=> {
              // this.$Message.info("搜索失败");
              console.log(error);
            });
        }
      }
      }
</script>

<style scoped>

</style>
