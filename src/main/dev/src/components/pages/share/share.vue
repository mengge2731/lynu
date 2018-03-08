<style lang="less" scoped>
  .data-share-con{
    width: 100%;

    font-size: 14px;
    padding-left: 20px;
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
      width: 144px;
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
      width: 454px;
      .el-button{
        float:right;
      }
    }

    // 修改 element 样式
    .el-input{
        width: 300px;
    }
  }
</style>

<template>
  <div class="data-share-con">
   

    <div class="data-pub">数据发布:</div>
    <div class="">
      数据名称:
      <el-input v-model="dataName" placeholder="请输入内容"></el-input>
    </div>

    <div class="">
      <div class="">
        数据量:
        <el-input v-model="dataSize" placeholder="请输入内容"></el-input>
      </div>

      <div class="">
        数据分类:
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
    
    <div class="user-present clearfix">
      <div class="present-text">数据介绍:</div>
      
      <el-input
        type="textarea"
        :rows="2"
        placeholder="请输入内容"
        v-model="dataDesc">
      </el-input>
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
      this.$router.push({
        path:'/'
      })
      // this.$router.go(-1);
    }
  }
}
</script>


