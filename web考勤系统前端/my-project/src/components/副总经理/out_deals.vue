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
    <br>
    <Button type="default" size="large"@click="get_outs()" ><Icon type="ios-download-outline"></Icon> 刷新员工外出表 </Button>
    <Button type="default" size="large" @click="exportData(1)" ><Icon type="ios-download-outline"></Icon> 原始数据下载 </Button>
    <Button type="default" size="large" @click="exportData(2)" ><Icon type="ios-download-outline"></Icon> 处理后数据下载</Button>
    <br>
    <br>
    <Table stripe border :columns="columns8" :data="data7" size="small" ref="table" ></Table>


  </div>
</template>

<script>
    export default {
        name: "out_deals",
      data () {
        return {
          columns8: [
            {
              "title": "编号",
              "key": "o_id",
              "fixed": "left",
              "width": 200
            },
            {
              "title": "员工编号",
              "key": "u_id",

              "width": 100
            },
            {
              "title": "请假原因详情",
              "key": "reason",
              "width": 550,
              //"sortable": true
            },
            {
              "title": "请假天数",
              "key": "days",
              "width": 100,
            },
            {
              title: '审批',
              key: 'action',
              width: 250,
              align: 'center',
              render: (h, params) => {
                return h('div', [
                  h('Button', {
                    props: {
                      type: 'primary',
                      size: 'small'
                    },
                    style: {
                      marginRight: '5px'
                    },
                    on: {
                      click: () => {
                        this.show(params.index)
                      }
                    }
                  }, '审批通过'),
                  h('Button', {
                    props: {
                      type: 'error',
                      size: 'small'
                    },
                    on: {
                      click: () => {
                        this.remove(params.index)
                      }
                    }
                  }, '审批不通过'),


                ]);
              }
            }

          ],
          data7: [
            {
              "o_id":"1",
              "u_id":"1",

              "reason":"出差",
              "days":3,

            }

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
        get_outs(){
          this.$axios.post('/api/leave_deals', {status : "vice_general_manager"})//还未处理过的员工的请假信息
            .then((response)=> {
              this.data7 = response.data;
            })
            .catch((error)=> {
              // this.$Message.info("搜索失败");
              console.log(error);
            });
        },
        show(index){//部门经理通过
          this.$axios.post('/api/admit2', {status : "vice_general_manager",o_id:this.data7[index].o_id, uid:this.data7[index].uid,admission: true})
            .then((response)=> {
              console.log(response);
            })
            .catch((error)=> {
              // this.$Message.info("搜索失败");
              console.log(error);
            });
        },
        remove(index){//部门经理未通过
          this.$axios.post('/api/admit2', {status : "vice_general_manager",o_id:this.data7[index].o_id, uid:this.data7[index].uid,admission: false})
            .then((response)=> {
              console.log(response);
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
