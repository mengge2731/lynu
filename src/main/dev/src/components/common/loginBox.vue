<style lang="less" scoped>
// 登录框样式
  #cover-box{
    
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
    
    // position: absolute;
    // left: 0;
    // top:0;

    position: fixed;
    top: 0px;
    left: 0px;
    right:0px;
    bottom:0px;
    width: 100%;
    height: 100%;

    z-index: 999;

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
          cursor:pointer;
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
          cursor: pointer;
        }
        

      }


    }
  }


.reg-code{
  // position: relative;
  .code-input{
    width: 96px !important;
  }
 
}
.tlr{
  text-align: right;
}
.mt40{
  margin-top: 40px !important;
}

.el-button--danger:hover {
    background: #f78989;
    border-color: #f78989;
    color: #fff;
}

</style>

<template>
    <!-- 遮罩层 -->
    <div id="cover-box" v-if="loginbox.cover">
      <div class="box-cover" @click.stop="closeCover"></div>
      <!-- 登录框 -->
      <div class="login-box" v-if="loginbox.loginOrRegister">
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
        <div class="login-submit mt40" >
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
          <el-button @click="phoneCode" :disabled="disabled" v-text="sendMsg" size="mini"></el-button>
        </div>
        <div class="login-pw">
          登录密码: <input type="password" v-model="password">
        </div>
        <div class="register-tip tlr">已有账号，直接<span  @click="toggleRegister">登录</span></div>
        <div class="login-submit mt40" >
          <el-button type="danger" @click="submitRegister">注册</el-button>
        </div>
      </div>
    </div>
</template>

<script>
import { code } from '../../util/util'
export default {
  name:'login-box',
  data(){
    return {
      checked: 0, //记住密码
      codePhone:'',
      msg:'',
      password:'',
      phoneNum:'',
      disabled: false,
      sendMsg:'获取验证码'
    }
  },
  props: {
    loginbox: Object
  },
  created(){
  },
  methods:{
    // 校验手机号
    isPoneAvailable(phone) {  
      var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;  
      if (!myreg.test(phone)) {  
        this.$message({
          message: '请输入正确的手机号',
          type: 'success'
        });
        return false;  
      } else {  
        return true;  
      }  
    },
    clearInput(){
      this.codePhone ='';
      this.msg ='';
      this.password ='';
      this.phoneNum ='';
    },
    phoneCode(){
      this.disabled = true;

      let that =this;
      let sec =60;

      let data = {phone: this.codePhone};
      let todata = JSON.stringify(data)

      var dataParam = 'body='+ todata;
      this.$axios.post('/login/sendMsg',dataParam)
      .then(res => {
        if(res.data.code == '0000' || res.data.code == '0006'){
            that.$message({
              message: '验证码发送成功',
              type: 'success'
            });

            for(let  i=0; i<=60; i++){
              setTimeout(function(){
                  if (sec != 0) {
                    that.sendMsg =   sec + "秒后重发" ;
                    sec--;
                } else {
                    sec = 60;//如果倒计时结束就让  获取验证码显示出来
                    that.sendMsg = '获取验证码';
                    that.disabled = false;
                }
              }, i * 1000)
            }
        }else if(res.data.code == '0013'){
          that.$message({
            message: '手机号已注册，请直接登录',
            type: 'info'
          });
        }
      })
    },
    submitLogin(){
      let that = this;
      let data = {phone: this.phoneNum, password: this.password };
      let todata = JSON.stringify(data)
      var dataParam = 'body='+ todata;
      this.$axios.post('/login/submit',dataParam)
      .then(res => {
        if(res.data.code == '0000'){
          that.$message({
            message: '登录成功',
            type: 'success'
          });
          // 关闭弹窗
          that.loginbox.cover = false;
          that.loginbox.loginOrRegister = true;
          if(this.checked == 1){
          }else {
            this.clearInput();
          }
        }else if(res.data.code == '0003' || res.data.code == '0014'){
          // 登录信息错误
          that.$message.error('手机号或密码错误')
        }else if(res.data.code == '0022'){
          // 账号停用显示
          that.$message.error('用户状态异常。请联系系统管理员')
        }
      })
    },
    submitRegister(){
      // 校验用户输入的信息，手机号，不为空
      this.isPoneAvailable(this.codePhone);






      let that =this;
      let data = { phone: this.codePhone,message: this.msg, password: this.password };
      let todata = JSON.stringify(data)
      var dataParam = 'body='+ todata;

      this.$axios.post('/login/register',dataParam)
      .then(res => {
        if(res.data.code == '0000'){
          that.$message({
            message: '恭喜你，注册成功!',
            type: 'success'
          });

          this.clearInput();
          setTimeout(function(){
            that.loginbox.loginOrRegister = true;
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
      this.loginbox.cover = false;
      this.loginbox.loginOrRegister = true;
    },
    findPw(){
      this.loginbox.cover = false;
      this.$router.push({
        path:'/find'
      })
    },
    toggleRegister(){
      if(this.loginbox.loginOrRegister == true){
          this.loginbox.loginOrRegister = false;
      }else {
        this.loginbox.loginOrRegister = true;
      }
      
    }
  },
  
}
</script>


