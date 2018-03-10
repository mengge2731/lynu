<style lang="less" scoped>
  .data-share-con{
    width: 600px;

    font-size: 14px;
    padding: 20px 0 0 200px;
    .data-pub,
    .data-name,
    .data-info,
    .new-pw-repeat,
    {
      margin-top: 10px;
    }

    
    // 单独样式
    .data-pub{
      font-size: 16px;
      font-weight: 700;
      margin-bottom: 20px;
    }

    .data-name{
      display: flex;
      .name-title{
        width: 70px;
        height: 40px;
        line-height: 40px;
      }

      .name-input{
        flex:1;
      }
    }
    .data-info{
      display: flex;
      .data-info-num{
        display: flex;
        width: 280px;
        .info-title{
          width: 70px;
          height: 40px;
          line-height: 40px;
        }

        .info-input{
          flex:1;
        }
      }

      .data-info-type{
        display: flex;
        width: 280px;
        margin-left: 80px;
        .type-title{
          width: 70px;
          height: 40px;
          line-height: 40px;
        }

        .type-input{
          flex:1;
        }
      }
      
    }
    .user-present{

    }
    
    .user-info-submit{

    }
  
  }

  .el-select{
    width: 100%;
  }
</style>

<template>
  <div class="data-share-con">
   

    <div class="data-pub">数据发布:</div>
    <div class="data-name">
      <div class="name-title">
        数据名称:
      </div>
      <div class="name-input">
         <el-input v-model="dataName" placeholder="请输入内容"></el-input>
      </div>
    </div>

    <div class="data-info">
      <div class="data-info-num">
        <div class="info-title">
          数据量:
        </div>
        <div class="info-input">
          <el-input v-model="dataSize" placeholder="请输入内容"></el-input>
        </div>
        
      </div>

      <div class="data-info-type">
        <div class="type-title">
          数据分类:
        </div>
        <div class="type-input">
          <el-select v-model="dataType" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </div>
       
      </div>
  </div>
    
    <div class="user-present clearfix">
      <div class="present-text">数据介绍:</div>
      
      <div>
        <el-input
          type="textarea"
          :rows="2"
          placeholder="请输入内容"
          v-model="dataDesc">
        </el-input>
      </div>
      
    </div>

    <div class="user-info-submit clearfix">
        <el-button type="primary" class="submit-button" @click="saveInfo">发布</el-button>

        <el-button  class="submit-button" @click="goBack">返回</el-button>
      </div>


  </div>
</template>

<script>
export default {
  data(){
    return {
      dataName:'',
      dataSize:'',
      dataType:'',
      dataDesc:'',
      label:'',
      options: [{
          value: '1',
          label: '旅游相关'
        },{
          value: '2',
          label: '意大利相关'
        },{
          value: '3',
          label: '文化相关'
        },{
          value: '4',
          label: '其他'
        }],
        value:''
    }
  },
  methods:{
    saveInfo(){
      console.log(this.dataName ,
      this.dataSize ,
      this.dataType ,
      this.dataDesc )

      // 只有当所有信息不为空时，才能提交
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


