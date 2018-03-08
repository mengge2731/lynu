
<style lang="less" scoped>

.data-con{


  .user-info-form{
    width: 100%;
    font-size: 14px;
    padding-left: 20px;

    .user-account-number{
     margin-top: 10px;
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
        width: 600px;
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
        width: 600px;

        textarea{
          min-height: 200px !important;
        }
        
      }
    }

    .user-info-submit{
      margin-top: 30px;
      width: 664px;

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
        登录账号: 13521260626
      </div>
      <div class="user-name clearfix">
        <div class="name-text">姓名:</div>
        <el-input v-model="input" placeholder="请输入内容"></el-input>
      </div>
      <div class="user-present clearfix">
        <div class="present-text">介绍:</div>
        
        <el-input
          type="textarea"
          :rows="2"
          placeholder="请输入内容"
          v-model="textarea">
        </el-input>
      </div>


      <div class="user-info-submit clearfix">
        <el-button type="primary" class="submit-button" @click="saveInfo">保存</el-button>
      </div>
     


    </div>

     
  </div>
</template>

<script>
export default {
  data(){
    return {
      input:'韩冷',
      textarea:'中国大数据中心数据专家，主要从事旅游大数据模型设计。'
    }
  },
  methods:{
    saveInfo(){
      console.log('提交保存修改')
    }
  }
}
</script>



