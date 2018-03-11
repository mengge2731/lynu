<style lang="less" scoped>
.info-right {
      flex: 1;
      // background-color: rgb(185, 185, 185);
      padding-top: 10px;
      padding-left: 10px;
      .info-right-top,
      .info-right-bot {
        height: 228px;
        border: 1px solid #ccc;
        text-align: center;

        h3{
          font-size: 16px;
          font-weight: 700;
          height: 50px;
          line-height: 50px;
        }
        ul{
          li{
            font-size: 14px;
            height: 40px;
            line-height: 40px;
          }
        }

      }
      .info-right-top {
        
      }
      .info-right-bot {
        margin-top: 10px;
       
      }
    }


</style>

<template>
  <div class="info-right">
    <div class="info-right-top">
      <h3>数据获取</h3>
      <ul>
        <li @click="register">注册</li>
        <li>申请</li>
        <li>批准</li>
        <li>获取</li>
      </ul>
    </div>
    <div class="info-right-bot">
      <h3>数据共享</h3>
      <ul>
        <li @click="login">登录</li>
        <li>描述</li>
        <li>发布</li>
        <li>等待获取申请</li>
      </ul>
    </div>
    
    <login-box :loginbox="loginbox"></login-box>

  </div>
</template>
<script>
import loginBox from './loginBox';
export default {
  name:'info-right',
  data() {
    return {
      loginbox: {
        cover: false, // 遮罩层是否开启
        loginOrRegister: true,  // 显示登录框  还是注册框
      },
      checked: 0,
    };
  },
  components:{
    loginBox,
  },
  created(){
   
  },
  mounted(){
    
  },
  methods:{
    login(){
      this.loginbox.cover = true;
    },
    register(){
      this.loginbox.cover = true;
      this.loginbox.loginOrRegister = false;
    }
  }
};
</script>


