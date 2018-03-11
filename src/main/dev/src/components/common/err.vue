<style lang="less" scoped>
  .err-con{
    width: 500px;
    padding-top: 40px;
    margin: 0 auto;
    text-align: center;
    img{
      width: 500px;
    }
    .err-text{
      font-size: 16px;
      margin-top: 20px;
    }

    .err-button{
      margin-top: 40px;
    }
  }
</style>

<template>
  <div class="err-con">
    <img src="../../assets/img/err.jpg" alt="">
    <div class="err-text">
      抱歉，找不到此页面~
    </div>
    <div class="err-button">
      <el-button type="primary" @click="goHome" size="small">返回首页</el-button>
    </div>
  </div>
</template>

<script>
export default {
  data(){
    return {

    }
  },
  methods:{
    goHome(){
      this.$router.push({ path:'/'})
    },
    refresh(){
      this.$router.go(-1);
    }
  }
}
</script>


