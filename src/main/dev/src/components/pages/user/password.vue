<style lang="less" scoped>
.pw-con{
  font-size: 14px;
  padding: 60px 200px;
  .user-phone,
  .old-pw,
  .new-pw,
  .new-pw-repeat,
  {
    margin-top: 10px;
  }
  .user-phone,
  .old-pw,
  .new-pw,
  .new-pw-repeat,
  {
    height: 40px;
    line-height: 40px;
    
  }
  .user-phone-text,
  .old-pw-text,
  .new-pw-text,
  .new-pw-repeat-text,
  {
    width: 100px;
    text-align: right;
    margin-right: 10px;

    // 两端对齐
    // text-align:justify;  
    // text-align-last:justify;  
    
  }

  .user-phone div,
  .old-pw div,
  .new-pw div,
  .new-pw-repeat div{
      float:left;
  }

  // 单独样式
  
  .goSubmit{
    margin-top: 30px;
    width: 200px;
    
      float:right;
  }

  // 修改 element 样式
  .el-input{
      width: 300px;
    }

}
</style>


<template>
  <div class="pw-con">
    
    <div class="user-phone clearfix">
      <div class="user-phone-text">登录手机号:</div>
      <div>13512349999</div>
    </div>

    <div class="old-pw clearfix">
      <div class="old-pw-text">
        原密码:
      </div>
       <el-input type="password" v-model="oldPw" ></el-input>
    </div>

    <div class="new-pw clearfix">
      <div class="new-pw-text">
        新密码:
      </div>
       <el-input type="password" v-model="newPw" ></el-input>
    </div>

    <div class="new-pw-repeat clearfix">
      <div class="new-pw-repeat-text">
        再次输入: 
      </div>
      <el-input type="password" v-model="newPwRepeat" @blur="check" ></el-input>
    </div>

    <div class="goSubmit clearfix">
      <el-button type="primary"  @click="saveChange" size="medium">保存</el-button>
      <el-button plain  @click="goBack" size="medium">返回</el-button>
    </div>

  </div>
</template>

<script>
import { code } from '../../../util/util'
export default {
  data(){
    return {
      oldPw:'',
      newPw:'',
      newPwRepeat:'',

      newPassword:''
    }
  },
  created(){
    let that = this;
    this.$axios.post('/login/isLogin')
    .then( res => {
      console.log(res.data)
      if(res.data.code == code.noLogin){
        this.$message({
            message: '未登录',
            type: 'info'
        });
        this.$router.push({ path: '/'});
      }else {
        // 请求用户 信息，获取 手机号
      }
    })
  },
  methods:{
    saveChange(){
      

      if(this.oldPw == ''){
        console.log('原密码为空')
        return false;
      }else if(this.newPw == ''){
        console.log('新密码为空')
        return false;
      }else if(this.newPwRepeat == ''){
        console.log('2次输入密码为空')
        return false;
      }

      console.log('提交修改的密码');

      // /function/user/changePassword

      let data = {
        newPassword: this.newPassword,
        oldPassword: this.oldPw, 
      }
      let params = 'body=' + JSON.stringify(data);
      this.$axios.post('/login/isLogin')
      .then( res => {
        if(res.data.code == code.login){
           this.$axios.post('/user/changePassword',params)
          .then( res => {
            if(res.data.code == code.success){
              // 修改完密码后，应该重新登录

              // 清空输入框
              this.oldPw='';
              this.newPw='';
              this.newPwRepeat='';

              // 提示
              this.$message({
                type: 'success',
                message: '密码修改成功!'
              });
              
            }else {
              //网络异常请重试
            }
              
          })
          .catch( err => console.log(err));

        }else if(res.data.code == code.noLogin){
          this.$message({
            message: '未登录',
            type: 'info'
          });

          this.$router.push({ path: '/'});
        }
      })


    },
    check(){
      console.log('校验2次新密码是否相同');

      if(this.newPw != this.newPwRepeat){
        console.log('2次密码输入不同')
      }else{
        console.log('2次密码相同')
        this.newPassword = this.newPw
      }
    },
    goBack(){
      this.$router.go(-1);
    }
  }
}
</script>

