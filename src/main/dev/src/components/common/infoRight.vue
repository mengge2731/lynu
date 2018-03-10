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

// 登录框样式
  #cover-box{
    background-color: rgba(0,0,0,.5);
    position: absolute;
    left: 0;
    top:0;
    width: 100%;
    height: 100%;

    .login-box{
      width: 380px;
      height: 350px;
      background-color: #fff;
      margin: 0 auto;
      transform: translateY(50%);

      box-shadow: 0px 15px 30px rgba(0,0,0,.1);
    }
  }


  // 登录框样式
  #cover-box{
    
    position: absolute;
    left: 0;
    top:0;
    width: 100%;
    height: 100%;

    .box-cover{
      background-color: rgba(0,0,0,.5);
      width: 100%;
      height: 100%;
      position: absolute;
      
    }

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


.reg-code{

  .code-input{
    width: 84px !important;
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


     <!-- 遮罩层 -->
    <div id="cover-box" v-show="cover" >
     <!-- <div id="cover-box" v-show="$route.path == '/login'" > -->

      <div class="box-cover" @click.stop="closeCover"></div>
      <!-- 登录框 -->
      <div class="login-box" v-if="loginOrRegister">

        <div class="login-title">登录</div>
        <div class="login-phone">
          手机号: <input type="phone" maxlength="11" placeholder="请输入手机号" v-model="phoneNum">
        </div>

        <div class="login-pw">
          登录密码: <input type="password" placeholder="请输入登录密码" v-model="password">
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
      <div class="login-box" v-else>
        <div class="login-title">注册</div>
        <div class="login-phone">
          手机号: <input type="phone" maxlength="11" v-model="codePhone" >
        </div>
        <div class="login-phone reg-code">
          短信验证码:           
          <input type="phone" maxlength="11" class="code-input" v-model="msg">
          <el-button @click="phoneCode">获取验证码</el-button>
        </div>
        <div class="login-pw">
          登录密码: <input type="password" v-model="password">
        </div>
        <div @click="toggleRegister">已有账号，直接登录</div>
        <div class="login-submit" >
          <el-button type="danger" @click="submitRegister">注册</el-button>
        </div>
      </div>
    </div>
    
  </div>
</template>

<script>
export default {
  name:'info-right',
  data() {
    return {
      cover: false, // 遮罩层是否开启
      loginOrRegister: true,  // 显示登录框  还是注册框
      checked: 0,
      codePhone:'',
      msg:'',
      password:'',
      phoneNum:'',
    };
  },
  created(){
    // console.log(localStorage.cover)
    // this.cover = localStorage.cover;
  },
  mounted(){
    
  },
  methods:{
    phoneCode(){
      let data = {phone: this.codePhone};
      let todata = JSON.stringify(data)

      var dataParam = 'body='+ todata;
      this.$axios.post('/login/sendMsg',dataParam)
      .then(res => {
        console.log(res.data);
      })

    },
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

      let that = this;
      let data = {phone: this.phoneNum, password: this.password };
      let todata = JSON.stringify(data)

      var dataParam = 'body='+ todata;
      this.$axios.post('/login/submit',dataParam)
      .then(res => {
        console.log(res.data);

        if(res.data.code == '0000'){
          sessionStorage.isLogin = true;

          that.$message({
            message: '登录成功',
            type: 'success'
          });

          // 关闭弹窗
          that.cover = false;
          that.loginOrRegister = true;
          
        }
        
      })
      
    },
    submitRegister(){
      console.log('提交注册')

      let that =this;

      let data = {phone: this.codePhone,message: this.msg, password: this.password };
      let todata = JSON.stringify(data)

      var dataParam = 'body='+ todata;
      this.$axios.post('/login/register',dataParam)
      .then(res => {
        console.log(res.data);

        if(res.data.code == '0000'){

          that.$message({
            message: '恭喜你，注册成功!',
            type: 'success'
          });

          setTimeout(function(){
            that.loginOrRegister = true;
          },500)
          
        }else if(res.data.code == '0013'){
          that.$message({
            message: '手机号已注册，请直接登录',
            type: 'info'
          });
        }

      })

    },// 关闭遮罩层
    closeCover(){
      // this.$router.push({
      //   name:'index',
      //   path:'/'
      // });
      this.cover = false;
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


