<style lang="less" scoped>
.info-right {
  flex: 1;
  padding-left: 10px;
  padding-right: 10px;

  margin-top: 10px;

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

.el-steps--vertical{
  height:180px;
  padding-left:20px;
  text-align: center;
}


</style>

<template>
  <div class="info-right">
    <div class="info-right-top">
      <h3>数据获取流程</h3>
      <!-- <ul>
        <li @click="register">注册</li>
        <li>申请</li>
        <li>批准</li>
        <li>获取</li>
      </ul> -->

      <el-steps direction="vertical" :active="4" :space="50">
        <el-step title="注册"></el-step>
        <el-step title="申请"></el-step>
        <el-step title="批准"></el-step>
        <el-step title="获取"></el-step>
      </el-steps>
    </div>
    <div class="info-right-bot">
      <h3>数据共享流程</h3>
      <!-- <ul>
        <li @click="login">登录</li>
        <li>描述</li>
        <li>发布</li>
        <li>等待获取申请</li>
      </ul> -->

      <el-steps direction="vertical" :active="4" :space="50">
        <el-step title="登录"></el-step>
        <el-step title="描述"></el-step>
        <el-step title="发布"></el-step>
        <el-step title="等待获取申请"></el-step>
      </el-steps>
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


