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
      数据申请:
    </div>
    <div class="approve-info clearfix">
      <div class="approve-user clearfix">
        <div class="uesr-left">发布人:</div>
        <div class="uesr-right">韩冷</div>
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
      <div class="desc-left">状态:</div>
      <div class="desc-right">韩冷已同意给您数据，请直接与他联系。</div>
    </div>
    <div class="agree-submit clearfix">
      <el-button  class="submit-button" @click="goBack">返回</el-button>
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
    let that = this;
    this.$axios.post('/login/isLogin')
    .then( res => {
      if(res.data.code == code.noLogin){
        this.$message({
            message: '未登录',
            type: 'info'
        });

        this.$router.push({ path: '/'});
        
      }
    })
    // 获得当前数据id  获取该数据的申请状态信息
  },
  methods:{
    goBack(){
      this.$router.go(-1);
    },
    
  }
}
</script>


