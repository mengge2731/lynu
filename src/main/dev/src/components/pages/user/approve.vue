<style lang="less" scoped>
.approve-con{
    width: 530px;
    font-size: 14px;
    padding: 60px 0 0 180px;
    // border: 1px solid red;
  .approve-pub {
      font-size: 16px;
      font-weight: 700;
      margin-bottom: 20px;
  }
  .approve-info{
      .approve-user{
        float: left;
        width: 260px;
        div{
          float:left;
        }
        .uesr-left{
          margin-right: 10px;
          font-weight: 700;
        }
        .uesr-left{

        }
      }
      .pub-phone{
        float: left;
        div{
          float:left;
        }
        .phone-left{
          margin-right: 10px;
          font-weight: 700;
        }
        .phone-left{

        }
      }
  }
  .approve-title{
    margin-top: 10px;

    div{
      float: left;
    }
    .title-left{
      width: 70px;
      font-weight: 700;
    }
    .title-right{
      // 省略号
      width: 450px;
      text-overflow: ellipsis;
      overflow: hidden;
      white-space: nowrap;
    }
  }
  .other-desc{
    margin-top: 10px;
    div{
      float:left;
    }
    .desc-left{
      margin-right: 10px;
      font-weight: 700;
    }
    .desc-right{
      width: 480px;
    }
  }
  .agree-submit{
    margin-top: 20px;
    float: right;
    margin-right: 10px;
  }
}
</style>

<template>
  <div class="approve-con clearfix">
    <div class="approve-pub">
      数据获取申请:
    </div>
    <div class="approve-info clearfix">
      <div class="approve-user clearfix">
        <div class="uesr-left">申请人:</div>
        <div class="uesr-right">{{data.applyUserName}}</div>
      </div>
      <div class="pub-phone">
        <div class="phone-left clearfix">注册电话:</div>
        <div class="phone-right">{{data.applyUserPhone}}</div>
      </div>
    </div>
    <div class="approve-title clearfix">
      <div class="title-left">申请内容:</div>
      <div class="title-right">{{data.dataTitle}}</div>
    </div>
    <div class="other-desc clearfix">
      <div class="desc-left">附言:</div>
      <div class="desc-right">{{data.applyDesc}}</div>
    </div>
    <div class="agree-submit clearfix">
      <el-button type="primary" class="submit-button" @click="agree">同意</el-button>
      <el-button  class="submit-button" @click="noAgree">不同意</el-button>
    </div>
  </div>
</template>

<script>
import { code } from '../../../util/util'

export default {
  data(){
    return {
      applyId:'',
      data:{},
    }
  },
  created(){
    this.applyId = parseInt(this.$route.query.applyId)

    let data = {
      applyId: this.applyId
    }
    let params = 'body=' + JSON.stringify(data);

    this.$axios.post('/login/isLogin')
    .then( res => {

      if(res.data.code == code.login){
        // 默认请求首页数据
        this.$axios.post('/apply/getTheirApplyInfo',params)
        .then( res => {
          // 数据列表
          this.data = res.data.data;
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
  methods:{
    agree(){
      let data = {
        applyId: this.applyId
      }
      let params = 'body=' + JSON.stringify(data);

      this.$axios.post('/login/isLogin')
      .then( res => {

        if(res.data.code == code.login){
          // 默认请求首页数据
          this.$axios.post('/apply/agreeApply',params)
          .then( res => {
            if(res.data.code == code.success){
              // 弹框提示
              this.$message({
                message: '已同意',
                type: 'success'
            });

            this.$router.go(-1);

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
    noAgree(){
      let data = {
        applyId: this.applyId
      }
      let params = 'body=' + JSON.stringify(data);

      this.$axios.post('/login/isLogin')
      .then( res => {

        if(res.data.code == code.login){
          // 默认请求首页数据
          this.$axios.post('/apply/refuseApply',params)
          .then( res => {
           if(res.data.code == code.success){
              // 弹框提示
              this.$message({
                  message: '不同意',
                  type: 'info'
              });

            this.$router.go(-1);
              
              
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

    }
  }
}
</script>


