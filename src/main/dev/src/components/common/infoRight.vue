<style lang="less" scoped>
.info-right {
  padding-left: 10px;
  padding-right: 10px;

  width: 180px;
  position: absolute;
  top:0;
  right: 0;

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
      padding-left: 40px;
      li{
        padding-left: 34px;
        text-align: left;
        font-size: 14px;
        height: 40px;
        line-height: 40px;
        color: #0c6e3d;
        background: url('../../assets/img/1.png') no-repeat center left;
      }
      
      
      .regiter{
        background-image: url('../../assets/img/8.png');
      }
      .apply{
        background-image: url('../../assets/img/7.png');
      }
      .agree{
        background-image: url('../../assets/img/6.png');
      }
      .get{
        background-image: url('../../assets/img/5.png');
      }
      .login{
        background-image: url('../../assets/img/4.png');
      }
      .desc{
        background-image: url('../../assets/img/3.png');
      }
      .public{
        background-image: url('../../assets/img/2.png');
      }
      
    }

  }
  .info-right-top {
    
  }
  .info-right-bot {
    margin-top: 10px;
    
  }
}
.line{
  // width:1px;
  // height: 200px;
  // border:1px dashed #0c6e3d;
  // position:absolute;
  // top:0;
  // left:60px;
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
      <ul>
        <li class="regiter">注册账号</li>
        <li class="apply">申请数据</li>
        <li class="agree">等待批准</li>
        <li class="get">获取数据</li>
      </ul>
      <div class="line"></div>
    </div>
    <div class="info-right-bot">
      <h3>数据共享流程</h3>
      <ul>
        <li class="login" >登录账号</li>
        <li class="desc">描述数据</li>
        <li class="public">发布数据</li>
        <li class="hold">等待申请</li>
      </ul>
      <div class="line"></div>

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


