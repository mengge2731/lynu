<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="less" scoped>
#view-container {
  height: 100%;
  display: flex;
  width: 1000px;
  justify-content: center;
  flex-direction: column;
  margin: 0 auto;

  // min-height: 900px;

  
  .footer {
    // flex: 1;
    height: 50px;
    // background-color: #fff;
    text-align: center;
    p {
      font-size: 14px;
      color: #999;
      line-height: 50px;
    }
  }
  
}

.content-wrap {
    display: flex;
    flex: 1;
    min-height: 500px;
    // background-color: #fff;
    border-bottom: 1px dashed #ccc;
}


// 登录框样式
  #cover-box{
    background-color: rgba(0,0,0,.5);
    position: absolute;
    left: 0;
    top:0;
    width: 100%;
    height: 100%;

    .login-box{
      padding: 30px;
      width: 300px;
      height: 320px;
      background-color: #fff;
      margin: 0 auto;
      transform: translateY(50%);
      box-shadow: 0px 15px 30px rgba(0,0,0,.1);
      
      .login-title{
        text-align: center;
        font-size: 16px;
        font-weight: 700;

      }

      .login-phone, .login-pw,  .login-submit{
        margin-top: 10px;
        border: 1px solid #ccc;
        height: 40px;
        line-height: 40px;
      }

      .login-phone, .login-pw,{
        padding-left: 10px;
        font-size: 14px;

        input{
          // height: 40px;
          // line-height: 40px;
          font-size: 16px;
          margin-left: 10px;
          width: 200px;
        }
      }

      .login-check{
        margin-top: 10px;
        height: 20px;
        line-height: 20px;
        .login-check-left {
          float:left;
        }

        .login-check-right{
          float: right;
          color: red;
        }
      }

      .login-submit{
        border: none;
        .el-button{
          width: 100%;
        }
      }

      .register-tip{
        margin-top: 10px;
        span {
          color:red;
        }
        

      }


    }
  }


</style>

<template>
  <div id="view-container">

    <nav-header></nav-header>

    <div class="content-wrap">
      <router-view></router-view>
    </div>
  
    <div class="footer">
      <p>copyright @2018 洛阳师范学院</p>
    </div>


      <!-- 遮罩层 -->
    <div id="cover-box" v-show="$route.path == '/login'" >
      <!-- 登录框 -->
      <div class="login-box" v-if="loginOrRegister">

        <div class="login-title">登录</div>
        <div class="login-phone">
          手机号: <input type="phone" maxlength="11" placeholder="请输入手机号">
        </div>

        <div class="login-pw">
          登录密码: <input type="password" placeholder="请输入登录密码">
        </div>

        <div class="login-check">
          <div class="login-check-left">
            <input type="checkbox" v-model="checked"> 记住密码
          </div>
          <div class="login-check-right" @click="findPw">忘记密码</div>
        </div>

        <div class="login-submit" >
          <el-button type="danger" @click="submitLogin">登录</el-button>
        </div>

        <div class="register-tip">没有账号, <span  @click="toggleRegister">免费注册</span></div>

      </div>

      <!-- 注册框 -->
      <div class="login-box" v-else>注册框
        <button @click="submitRegister">注册</button>

        <el-button @click="closeCover">关闭</el-button>

        <div @click="toggleRegister">已有账号，直接登录</div>
      </div>
    </div>

    
  </div>
</template>
<script>
import navHeader from './header.vue'
export default {
  name: "Layout",
  data() {
    return {
      cover: false, // 遮罩层是否开启
      loginOrRegister: true,  // 显示登录框  还是注册框
      checked: 0
    };
  },
  created() {},
  methods:{
    
  },
  components: {
    navHeader,
  },
  methods:{
    login(){
      console.log('登录操作')
      this.cover = true;

      
    },
    register(){
      console.log('注册操作')
      this.cover = true;
      this.loginOrRegister = false;
    }, //提交登录和注册
    submitLogin(){
      console.log('提交登录')
      this.$router.push({
        path:'/'
      })
    },
    submitRegister(){
      console.log('提交注册')
    },// 关闭遮罩层
    closeCover(){
      this.$router.push({
        name:'index',
        path:'/'
      });
      // this.cover = false;
      this.loginOrRegister = true;
    },
    findPw(){
      console.log('找回密码');
      this.$router.push({
        path:'/user/password'
      })
    },
    toggleRegister(){
      if(this.loginOrRegister == true){
          this.loginOrRegister = false;
      }else {
        this.loginOrRegister = true;
      }
      
    }
  }
};
</script>