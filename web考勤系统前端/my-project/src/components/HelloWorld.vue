<template>
  <div :style="{backgroundImage: 'url(' + img + ')'}" style=" position: absolute; top: 0px;width: 100%; height: 100%; background-repeat: no-repeat; background-size: cover">
    <div class="first-words">
      <p style="font-size: 70px; position:absolute; color: white; top: 100px; left: 100px">
        考勤-管理系统
      </p>
      <p style="font-size: 40px; position:absolute; color: white; top: 200px; left: 50%; margin-left:-350px;font-style: italic">
        --------designed by 单馃子购买流
      </p>
    </div>
    <div style="position: absolute; top:300px; left: 400px;width: 600px; height: 400px; opacity: 0.7; background-color: black">
      <Input prefix="ios-contact" v-model="id" placeholder="输入您公司卡号" style="position:absolute;top:50px; left: 50px; width: 300px" />
      <i-input  type = "password" v-model="passwords" placeholder="输入您的密码" style="position:absolute;top:100px; left: 50px; width: 300px"/>
      <Select v-model="model1" placeholder="输入您的职位" style="position:absolute;top:150px; left: 50px; width: 300px">
        <Option v-for="item in cityList" :value="item.value" :key="item.value">{{ item.label }}</Option>
      </Select>

      <i-button type = "default" @click="login" style="position:absolute;top:300px; left: 400px; width: 150px" >
        登录
      </i-button>

      <i-button type = "default" @click="again" style="position:absolute;top:300px; left: 200px; width: 150px">
       重置信息
      </i-button>
    </div>


</div>

</template>

<script>
  import Img from '@/assets/working.png'
export default {
  name: 'HelloWorld',
  data () {
    return {
       img:Img,
      id:'',
      passwords:'',
      cityList: [
        {
          value: 'staff',
          label: '普通员工'
        },
        {
          value: 'department_manager',
          label: '部门经理'
        },
        {
          value: 'deputy_general_manager',
          label: '副总经理'
        },
        {
          value: 'general_manager',
          label: '总经理'
        },
      ],
      model1: ''
    }
  },
  methods:{
    login(){
      //this.$Message.info(this.model1);
      // this.$axios.post("url",{id:this.id, passwords:this.passwords})
      //   .then((response) => {
      //
      //     if (response.data != "false") {
      //       this.$Message.info("提交成功");
      //       sessionStorage.setItem("userName", this.id);
      //       //sessionStorage.setItem("userToken", )
      //     }
      //     else {
      //       this.$Message.info("账号不正确或连接失败，请重试");
      //     }
      //
      //   })
      //   .catch(function (error) {
      //     //this.$Message.info("服务器出错，注册失败" + error.data);
      //     console.log(error)
      //   });
      sessionStorage.setItem("userId",this.id);
      sessionStorage.setItem("userToken", this.model1);
      sessionStorage.setItem("userDepartment", this.model1);
      // this.$store.dispatch("setUser",this.id);
      // this.$store.dispatch("setToken", this.model1);
      //console.log(this.$store.state.isLogin);
      console.log(sessionStorage.getItem("userId"));
      console.log(sessionStorage.getItem("userToken"));
      console.log(sessionStorage.getItem("userDepartment"));
      if(this.model1 == 'staff'){
        this.$router.push({name:'user',params:{id:this.id,changemessage: true}});
      }
      else if(this.model1 == 'department_manager'){
        this.$router.push({name:'messager',params:{id:this.id,changemessage: true}});
      }
      else if(this.model1 == 'deputy_general_manager'){
        this.$router.push({name:'messager2',params:{id:this.id,changemessage: true}});
      }
      else{
        this.$router.push({name:'messager3',params:{id:this.id,changemessage: true}});
      }


    },
    again(){
       this.passwords = '';
       this.id = '';
       this.model1 = '';
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
.fade-in-words{ width: 200px; margin: 0 auto; text-align: center;}/*自定义一个透明度从0到1的动画,它的名称是fade-in*/
@keyframes fade-in{ 0%{ opacity: 0;} 100%{opacity:1;}}
@-webkit-keyframes fade-in{ 0%{ opacity: 0;} 100%{opacity:1;}}
@-ms-keyframes fade-in{ 0%{ opacity: 0;} 100%{opacity:1;}}
@-o-keyframes fade-in{ 0%{ opacity: 0;} 100%{opacity:1;}}
@-moz-keyframes fade-in{ 0%{ opacity: 0;} 100%{opacity:1;}}
.first-words{
  opacity: 0; /*实先规定文字的状态是不显示的*/
  animation: fade-in 4s ease 0s 1;
  /*调用名称为fade-in的动画,全程动画显示时间4S,进入方式为ease,延时0S进入,播放次数1次*/
  -webkit-animation: fade-in 4s ease 0s 1;
  -moz-animation: fade-in 4s ease 0s 1;
  -o-animation: fade-in 4s ease 0s 1;
  -ms-animation: fade-in 4s ease 0s 1; /*规定动画的最后状态为结束状态*/
  animation-fill-mode:forwards;
  -webkit-animation-fill-mode: forwards;
  -o-animation-fill-mode: forwards;
  -ms-animation-fill-mode: forwards;
  -moz-animation-fill-mode: forwards;
}
</style>
