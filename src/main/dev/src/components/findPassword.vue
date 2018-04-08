<style lang="less" scoped>
  .data-share-con{
    width: 430px;
    font-size: 14px;
    padding: 80px 0 0 300px;
    .find-pub,
    .phone-num,
    .send-msg,
    .new-pw-repeat,
    {
      margin-top: 10px;
    }

    
    // 单独样式
    .find-pub{
      font-size: 16px;
      font-weight: 700;
      margin-bottom: 20px;
    }

    .phone-num{
      display: flex;
      .phone-title{
        width: 120px;
        height: 40px;
        line-height: 40px;

        text-align: right;
        padding-right: 10px;
      }

      .phone-input{
        // flex:1;
        width: 300px;
      }
    }
    
    .send-msg{
      display: flex;
      .msg-title{
        width: 120px;
        height: 40px;
        line-height: 40px;

        text-align: right;
        padding-right: 10px;
      }

      .msg-input{
        width: 200px;
        margin-right: 8px;
      }
    }

    .pwd-new{
      display: flex;
      margin-top: 10px;
      .pwd-text{
        width: 120px;
        height: 40px;
        line-height: 40px;

        text-align: right;
        padding-right: 10px;
      }

      .pwd-input{
        width: 300px;
        // margin-right: 8px;
      }
    }

    .pwd-new-two{
      display: flex;
      margin-top: 10px;
      .pwd-text{
        width: 120px;
        height: 40px;
        line-height: 40px;

        text-align: right;
        padding-right: 10px;
      }

      .pwd-input{
        width: 300px;
        // margin-right: 8px;
      }
    }
    
    
    .pwd-submit{
      float: right;
      margin-top: 20px;
    }
  
  }

  .el-select{
    width: 100%;
  }
</style>

<template>
  <div class="data-share-con clearfix">

    <div class="find-pub">找回密码:</div>

    <div class="phone-num">
      <div class="phone-title">
        手机号:
      </div>
      <div class="phone-input">
         <el-input v-model="phone" placeholder=""></el-input>
      </div>
    </div>

    <div class="send-msg">
        <div class="msg-title">
          验证码:
        </div>
        <div class="msg-input">
          <el-input v-model="codePhone" placeholder=""></el-input>
        </div>
        <el-button @click="phoneCode" :disabled="disabled" v-text="sendMsg" size="mini"></el-button>
    </div>
    
    <div class="pwd-new clearfix">
      <div class="pwd-text">新的登录密码:</div>
      <div class="pwd-input">
         <el-input v-model="newPw" placeholder="" type="password"></el-input>
      </div>
    </div>

    <div class="pwd-new-two clearfix">
      <div class="pwd-text">再次输入登录密码:</div>
      <div class="pwd-input">
         <el-input v-model="newPwRe" placeholder="" type="password"></el-input>
      </div>
    </div>

    <div class="pwd-submit clearfix">
      <el-button type="primary" class="submit-button" @click="saveInfo">确认</el-button>
      <el-button  class="submit-button" @click="goBack">返回</el-button>
    </div>

  </div>
</template>

<script>
import { code } from '../util/util'
export default {
  data(){
    return {
      phone:'',
      codePhone:'',
      newPw:'',
      newPwRe:'',
      sendMsg:'获取验证码',
      disabled:false,
    }
  },
  created(){
    // this.$axios.post('/login/isLogin')
    // .then( res => {
    //   if(res.data.code == code.noLogin){
    //     this.$message({
    //         message: '未登录',
    //         type: 'info'
    //     });
    //     this.$router.push({ path: '/'});
    //   }
    // })
  },
  methods:{
    // 获取验证码
    phoneCode(){ 

      if(this.phone == ''){
        this.$message({
          message: '手机号不能为空',
          type: 'info'
        });
        return false;
      }else if(false){
        this.$message({
          message: '请输入正确的手机号',
          type: 'info'
        });
        return false;
      }
      
      this.disabled = true;

      let that =this;
      let sec =60;

      let data = {phone: this.phone};
      let todata = JSON.stringify(data)

      var dataParam = 'body='+ todata;
      this.$axios.post('/login/sendFindPassMsg',dataParam)
      .then(res => {
        if(res.data.code == '0000' || res.data.code == '0006'){

            this.$message({
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
        }else if(res.data.code == '0003'){
          this.$message({
            message: '请输入正确的手机号',
            type: 'info'
          });
          this.disabled = false;
        }else if(res.data.code == '0005'){
          this.$message({
            message: '网络异常，稍后重试',
            type: 'info'
          });
          this.disabled = false;
        }else if(res.data.code == '0020'){
          this.$message({
            message: '验证码已发送',
            type: 'info'
          });
          this.disabled = false;
        }else if(res.data.code == '0021'){
          this.$message({
            message: '手机号异常，请核实',
            type: 'info'
          });
          this.disabled = false;
        }
      })
    },
    saveInfo(){
      if(this.phone != '' && this.codePhone != '' && this.newPw != '' && this.newPwRe != '' ){
        
        if(this.newPw != this.newPwRe){
          this.$message({
            message: '两次密码输入不一致，请重新输入',
            type: 'info'
          });

          return false;
        }
        let data = {
            phone: this.phone,
            message: this.codePhone,
            newPassword: this.newPw
          }

          let params = 'body=' + JSON.stringify(data);
         
          this.$axios.post('/login/changePassword',params)
          .then( res => {
            if(res.data.code == code.success){
  
              that.$message({
                message: '密码修改成功,请登录',
                type: 'success'
              });
              this.$router.push('/');

            }else if(res.data.code == '0009'){
              that.$message({
                message: '验证码超时，请重新获取',
                type: 'info'
              });
            }else if(res.data.code == '0008'){
              that.$message({
                message: '验证码错误，请重新输入',
                type: 'info'
              });
            }else if(res.data.code == '0021'){
              that.$message({
                message: '无权访问',
                type: 'info'
              });
            }else if(res.data.code == '0003'){
              that.$message({
                message: '输入信息有误，请重新输入',
                type: 'info'
              });
            }

          })
          .catch( err => console.log(err ));

      }else {

        // 手机号校验

        // 验证码不为空

        // 密码不为空

        this.$message({
            message: '内容不能为空，请重新输入',
            type: 'info'
          });
      }

    },
    goBack(){
      // 默认返回首页，或者历史记录 返回
      // this.$router.push({
      //   path:'/'
      // })
      this.$router.go(-1);
    }
  }
}
</script>


