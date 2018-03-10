<style lang="less" scoped>
.apply-con{
  padding:30px 80px;
  font-size: 14px;

  width: 100%;

  overflow: auto;

  .apply-info{
    h3{
      font-size: 16px;
      font-weight: 700;
    }
  }

  .apply-info-top{
    margin-top: 20px;
    padding-bottom: 20px;
    border-bottom: 1px solid #ccc;

    .apply-title{

      // 省略号
                width: 800px;
              text-overflow: ellipsis;
              overflow: hidden;
              white-space: nowrap;
    }

    .apply-pro{
      margin-top: 10px;
      display: flex;
      div{
        flex:1;
      }
    }

    .apply-data-con{
      margin-top: 10px;
      width: 100%;

      display: flex;

      .apply-data-title{
        width: 80px;
      }

      .apply-data-info{
        flex: 1;
      }
    }

    .apply-name{
      margin-top: 10px;

    }

    .apply-user-pre{
      margin-top: 10px;
      display: flex;

      .pre-title{
        width: 90px;
      }

      .pre-info{
        flex: 1;
      }
      

    }

  }

  .apply-info-bot{
    margin-top: 20px;

    .apply-user{}

    .apply-tel{
      margin-top: 10px;
    }

    .apply-info-other{
      margin-top: 10px;

      display: flex;

      .info-other-title{
        width: 60px;
      }

      .info-other-input{
        flex:1;
      }
    }

    .apply-button{
      margin-top: 20px;

      float: right;

    }

  }


}
</style>

<template>
  <div class="apply-con">
    
    <div class="apply-info">
      <h3>申请获取数据：</h3>
    </div>

    <div class="apply-info-top">
      <div class="apply-title">
        申请内容：意大利2017年经济情况报告-全部数据-(意大利2017年金融、工业、旅游等主要经济类型在意大利各地的分布情况，其中包括银行、保险、酒店、机票、造车、造船等行业的政府发布数据，以及部分数据分析结论。数据为英文版。此类数据仅公开给高校或研究所背景的单位，有需要的单位可申请。)
      </div>
      <div class="apply-pro clearfix"> 
        <div class="apply-num">数据量：200GB </div>

        <div class="apply-time">发布时间：2018-1-29</div>
      </div>
      

      <div class="apply-data-con clearfix">
        <div class="apply-data-title">
          数据介绍：
        </div>
        <div class="apply-data-info">
          意大利2017年金融、工业、旅游等主要经济类型在意大利各地的分布情况，其中包括银行、保险、酒店、机票、造车、造船等行业的政府发布数据，以及部分数据分析结论。数据为英文版。此类数据仅公开给高校或研究所背景的单位，有需要的单位可申请。

        </div>
      </div>

      <div class="apply-name">
        数据发布人：韩冷
      </div>

      <div class="apply-user-pre">
        <div class="pre-title">发布人简介：</div>
        <div class="pre-info">洛阳师范学院意大利研究中心老师.意大利2017年金融、工业、旅游等主要经济类型在意大利各地的分布情况，其中包括银行、保险、酒店、机票、造车、造船等行业的政府发布数据，以及部分数据分析结论。数据为英文版。此类数据仅公开给高校或研究所背景的单位，有需要的单位可申请。</div>
        
      </div>
    </div>

    <div class="apply-info-bot">
      <div class="apply-user">
        申请人：王磊  
      </div>

      <div class="apply-tel">注册电话：13512348888</div>

      <div class="apply-info-other">
        <div  class="info-other-title">
          附言：
        </div>
        <div class="info-other-input">
          <el-input
            type="textarea"
            :rows="2"
            placeholder="请输入至少20个字"
            v-model="otherInfo">
          </el-input>
        </div>
        
       
      </div>

      <div class="apply-button">
        <el-button type="primary" size="medium">申请</el-button>
        <el-button size="medium" @click="goBack">返回</el-button>
        
      </div>
    </div>

  </div>
</template>

<script>
export default {
  data(){
    return {
      otherInfo:"", // 附言
    }
  },
  created(){
    // 页面加载时获取，数据的id，然后查询渲染页面
    // console.log(this.$route.params.id)
  },
  methods:{
    goBack(){
      this.$router.go(-1)
    }
  }
}
</script>


