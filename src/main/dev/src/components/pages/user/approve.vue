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
        <div class="uesr-right">王磊</div>
      </div>
      <div class="pub-phone">
        <div class="phone-left clearfix">注册电话:</div>
        <div class="phone-right">13512348888</div>
      </div>
    </div>
    <div class="approve-title clearfix">
      <div class="title-left">申请内容:</div>
      <div class="title-right">意大利2017年经济情况报告-全部数据(意大利2017年经济情况报告-全部数据)</div>
    </div>
    <div class="other-desc clearfix">
      <div class="desc-left">附言:</div>
      <div class="desc-right">您好，我是河北师范大学旅游学院的王磊，我们希望获取贵校意大利2017年的经济数据，不知可否共享，我校有加拿大的数据可以交换，如果可以麻烦您联系我的电话13512348888.谢谢。</div>
    </div>
    <div class="agree-submit clearfix">
      <el-button type="primary" class="submit-button" @click="agree">同意</el-button>
      <el-button  class="submit-button" @click="noAgree">不同意</el-button>
    </div>
  </div>
</template>

<script>
export default {
  data(){
    return {
      
    }
  },
  created(){

  },
  methods:{
    agree(){
      console.log('同意操作')

    },
    noAgree(){
      console.log('不同意操作')

    }
  }
}
</script>


