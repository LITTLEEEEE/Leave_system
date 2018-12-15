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
            <Icon type="ios-time" />
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
            <Icon type="ios-contacts" />
            同部门员工请假信息查询
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
    <br/>


    <Button type="default" @click="getPunch()" style="position: absolute;left: 1100px">刷新</Button>

    <Table border :columns="columns5" :data="data5" style="position: absolute;left: 20px;width: 1000px"></Table>
  </div>
</template>

<script>
    export default {
        name: "getPunch",
      data(){
          return{
            id:sessionStorage.getItem("userId"),
            status:sessionStorage.getItem("userStatus"),
            columns5: [
              {
                title: '日期',
                key: 'date',
                sortable: true
              },

              {
                title: '打卡信息',
                key: 'punch',
                sortable:true
              },

            ],
            data5: [
              {
                date: '2016-10-03',
                punch:'已打卡'
              },
              {

                date: '2016-10-01',
                punch:'未打卡'
              },
              {
                date: '2016-10-02',
                punch:'已打卡'
              },

            ]
          }
      },
      mounted:function () {
        this.getPunch();
      },

      methods: {
        // getMessage() {
        //   this.$axios.post('/api/getPunch', {id: this.id,status:this.status})
        //     .then((response) => {
        //         this.$Message.info("提交成功");
        //         this.data5 = response.data;
        //     })
        //     .catch(function (error) {
        //       //this.$Message.info("服务器出错，注册失败" + error.data);
        //       console.log(error)
        //     });
        // },
        getPunch(){
          this.$axios.post('/api/getPunch', {id: this.id,status:this.status})
            .then((response) => {
              console.log(response.data);
              console.log(response);
                this.$Message.info("提交成功");
                this.data5 = response.data;
            })
            .catch(function (error) {
              //this.$Message.info("服务器出错，注册失败" + error.data);
              console.log(error)
            });
          //     this.$axios.get('../static/a.json', {
          //         params: {
          //            ID: 12345
          //         }
          //    })
          //    .then((response)=> {
          //     console.log(response.data);
          //     this.data5 = response.data.data;
          //    })
          //    .catch(function (error) {
          //     console.log(error);
          //   });
        }

      }
    }
</script>

<style scoped>

</style>
