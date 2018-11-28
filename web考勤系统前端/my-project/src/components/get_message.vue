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
              <MenuItem name="1-3">员工个人请假/考勤信息</MenuItem>
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
          查看您的请假信息
        </p>
        <Input prefix="ios-contact" v-model="id3" placeholder="输入您的卡号" style="position: absolute; width: 500px; top: 80px; left: 50px;"/>
        <i-button type = "default" style="position: absolute; top: 80px;left: 550px" @click="getl()">
          查看
        </i-button>
        <Table style="position: absolute; top: 140px;left:20px;width: 600px;" height="400"  size="small" :columns="columnsdata" :stripe="true" :data="leave_message" border ></Table>
      </Col>

    </Row>


  </div>

</template>

<script>
    export default {
        name: "get_message",
      data(){
          return{
            leave_message: [
              {
                lid:'默认',
                reason:'无',
                time_start:null,
                time_end:null,
                if_admit:'无信息',
                days_leaves:0,
                days_left:0
              }

            ],
            id3:'',
            columnsdata: [
              {
                title: '标号',
                key: 'lid'
              },
              {
                title: '请假理由',
                key: 'reason'
              },
              {
                title: '开始时间',
                key: 'time_start'
              },
              {
                title:'终止时间',
                key:'time_end'
              },
              {
                title:'审批情况',
                key:'if_admit'
              },
              {
                title:'年假天数',
                keys:'days_leaves'
              },
              {
                title:'剩余年假',
                keys:'days_left'
              },
              {
                title: 'Action',
                key: 'action',
                width: 150,
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
                    }, '修改'),
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
                    }, '取消')
                  ]);
                }
              }
            ],
          }
      },
      methods:{
          getl(){
            this.$axios.post('/api/getmessage', {id:this.id3})
              .then((response)=> {
                //this.$Message.info(response.data );
                this.leave_message = response.data;
                // this.question.name = response.data.name;
                // this.question.description = response.data.description;
                // this.question.cid = response.data.cid;
                // this.question.qid = response.data.qid;
                // this.$set(this.question, 'name',response.data.name);
                // this.$set(this.question, 'description', response.data.description);
                // this.$set(this.question, 'qid', response.data.qid);
                // this.$set(this.question, 'cid', response.data.cid);
              })
              .catch((error)=> {
                // this.$Message.info("搜索失败");
                console.log(error);
              });

          },
          show(index){
            this.$router.push({name:'leave',params:{changemessage: true}});

          }
      }
    }
</script>

<style scoped>

</style>
