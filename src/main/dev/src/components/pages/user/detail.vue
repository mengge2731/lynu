<style lang="less" scoped>

</style>

<template>
  <div class="data-apply-con">
    
    <div>
      数据申请：
    </div>


    <div>
        <div>
          发布人： 韩冷
        </div>

        <div>
          注册电话：13512349999
        </div>
    </div>
    
    <div>
      申请内容：意大利2017年经济情况报告-全部数据
    </div>

    <div>
      状态：韩冷已同意给您数据，请直接与他联系。
    </div>

    <div class="data-apply-button">
        <el-button size="medium" @click="goBack">返回</el-button>
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
      if(res.data.code == "0001"){
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
      this.$router.go(-1)
    }
  }
}
</script>

