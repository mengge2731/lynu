
<style lang="less" scoped>

.data-con{
  // padding: 20px 60px;

  .user-info-form{
    width: 500px;
    font-size: 14px;
    padding: 60px 0 0 200px;

    .user-account-number{
     margin-top: 10px;
      div {
        float:left;
      }
     .account-left{
       width: 64px;
     }
     .account-right{
       margin-left: 10px;
     }
    }
    .user-name{
      width: 100%;
      margin-top: 10px;
      line-height: 40px;

      .name-text{
        width: 64px;
        float: left;
      }
      .el-input{
        float: left;
        width: 400px;
      }
    }

    .user-present{
      width: 100%;
      margin-top: 10px;
      line-height: 40px;

      .present-text{
        width: 64px;
        float: left;
      }
      .el-textarea{
        float: left;
        width: 400px;

        textarea{
          min-height: 200px !important;
        }
        
      }
    }

    .user-info-submit{
      margin-top: 30px;
      width: 464px;

      .submit-button{
        float: right;
      }
    }
  }

  
}

  
</style>

<template>
  <div class="data-con">
    <div class="user-info-form" >
      <div class="user-account-number clearfix">
        <div class="account-left">登录账号:</div>
        <div class="account-right">{{phone}}</div>
         
      </div>
      <div class="user-name clearfix">
        <div class="name-text">姓名:</div>
        <el-input v-model="userName" placeholder=""></el-input>
      </div>
      <div class="user-present clearfix">
        <div class="present-text">介绍:</div>
        
        <el-input
          type="textarea"
          :rows="2"
          placeholder=""
          v-model="desc">
        </el-input>
      </div>

      <div class="user-info-submit clearfix">
        <el-button type="primary" class="submit-button" @click="saveInfo">保存</el-button>
      </div>

    </div>
     
  </div>
</template>

<script>
import { code } from '../../../util/util'
export default {
  data(){
    return {
      phone:'',
      userName:'',
      desc:''
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
        //  请求用户数据
         this.$axios.post('/user/getUserInfo')
        .then( res => {
          if( res.data.code == code.success){
            var userInfo = res.data.data;
            this.phone = userInfo.phone;
            this.userName = userInfo.userName;
            this.desc = userInfo.desc;
          }
        })
      }
    })

    
  },
  methods:{
    saveInfo(){
      console.log('提交保存修改');
       let that = this;
        // 请求登录查询
        // 发送数据提交
      this.$axios.post('/login/isLogin')
      .then( res => {
        // 如果成功就去获取 用户权限 0000
        if(res.data.code == code.login){
          
          var data = {
            userName: this.userName,
            desc: this.desc,
          }
          let params = 'body=' + JSON.stringify(data);

          this.$axios.post('/user/updateUserInfo',params )
          .then( res => {
            if( res.data.code == code.success){

              this.$message({
                message: '修改成功',
                type: 'success'
              });

              // 重新加载页面
              setTimeout(function(){
                location.reload;
              },200)
            }
          })

        }else if(res.data.code == code.noLogin){
          this.$message({
              message: '未登录',
              type: 'info'
          });
          this.$router.push({ path: '/'});
        }

      })


    }
  }
}
</script>



