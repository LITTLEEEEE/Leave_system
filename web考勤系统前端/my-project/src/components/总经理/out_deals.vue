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

    <br>
    <Button type="default" size="large"@click="get_outs()" ><Icon type="ios-download-outline"></Icon> 刷新员工外出表 </Button>
    <Button type="default" size="large" @click="exportData(1)" ><Icon type="ios-download-outline"></Icon> 原始数据下载 </Button>
    <Button type="default" size="large" @click="exportData(2)" ><Icon type="ios-download-outline"></Icon> 处理后数据下载</Button>
    <br>
    <br>
    <Input v-model="description" placeholder="输入您的处理原因"></Input>
    <Table stripe border :columns="columns8" :data="data7" size="small" ref="table" ></Table>

  </div>
</template>

<script>
    export default {
        name: "out_deals",
      data () {
        return {
          id:sessionStorage.getItem("userId"),
          status:sessionStorage.getItem("userStatus"),
          description:'',
          columns8: [
            {
              "title": "事务编号",
              "key": "d_id",
              "fixed": "left",
              "width": 120
            },
            {
              "title": "员工编号",
              "key": "u_id",
              "sortable":true,
              "width": 120
            },
            {
              "title": "员工姓名",
              "key": "u_name",
              "sortable":true,
              "width": 120
            },
            {
              "title": "外出原因详情",
              "key": "reason",
              "width": 300,
              //"sortable": true
            },
            {
              "title": "开始时间",
              "key": "time_start",
              "width": 120,
              "sortable": true
            },
            {
              "title": "结束时间",
              "key": "time_end",
              "width": 120,
              "sortable": true
            },
            {
              "title": "外出天数",
              "key": "days",
              "width": 100,
            },
            {
              title: '审批',
              key: 'action',
              width: 200,
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
              "d_id":"1",
              "u_id":"1",
              "u_name":'李狗蛋',
              "reason":"出差",
              "days":3,
              "time_start":'2018-1-4',
              "time_end":'2018-1-7',

            }

          ]
        }
      },
      mounted:function () {
        this.getMessage();
      },
      methods: {
        getMessage(){
          this.$axios.post('/api/outDeals', {status:this.status,id:this.id})//还未处理过的员工的请假信息
            .then((response)=> {
              this.data7 = response.data;
            })
            .catch((error)=> {
              // this.$Message.info("搜索失败");
              console.log(error);
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
        get_outs(){
          this.$axios.post('/api/outDeals', {status:this.status,id:this.id})//还未处理过的员工的请假信息
            .then((response)=> {
              this.data7 = response.data;
            })
            .catch((error)=> {
              // this.$Message.info("搜索失败");
              console.log(error);
            });
        },
        show(index){//部门经理通过
          this.$axios.post('/api/deals', {d_id:this.data7[index].d_id, admission: "1",status:this.status,description:this.description})
            .then((response)=> {
              console.log(response);
              if(response.data.result == true){
                this.$Message.info("处理成功");
              }else{
                this.$Message.info("处理失败重新提交");
              }
            })
            .catch((error)=> {
              // this.$Message.info("搜索失败");
              console.log(error);
            });
        },
        remove(index){//部门经理未通过
          this.$axios.post('/api/deals', {d_id:this.data7[index].d_id, admission: "0",status:this.status,description:this.description})
            .then((response)=> {
              console.log(response);
              if(response.data.result == true){
                this.$Message.info("处理成功");
              }
              else{
                this.$Message.info("处理失败请重新提交");
              }
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
