<style lang="less" scoped>

</style>

<template>
  <div>
    
    <div>申请获取数据：</div>

    <div>
      申请内容：意大利2017年经济情况报告-全部数据
    </div>

    <div>数据量：200GB </div>

    <div>发布时间：2018-1-29</div>

    <div>
      数据介绍：
      <div>
        意大利2017年金融、工业、旅游等主要经济类型在意大利各地的分布情况，其中包括银行、保险、酒店、机票、造车、造船等行业的政府发布数据，以及部分数据分析结论。数据为英文版。此类数据仅公开给高校或研究所背景的单位，有需要的单位可申请。

      </div>
    </div>

    <div>
      数据发布人：韩冷
    </div>

    <div>
      发布人简介：洛阳师范学院意大利研究中心老师
    </div>

    <hr>

    <div>

      申请人：王磊  
    </div>

    <div>注册电话：13512348888</div>

    <div>
      附言：
      <textarea name="附言" id="" cols="30" rows="10" placeholder="至少20个字"></textarea>
    </div>

    <div>
      <el-button type="primary">申请</el-button>
      <el-button>返回</el-button>
      
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
    console.log(this.$route.params.id)
  }
}
</script>


