
<style lang="less" scoped>

  
</style>

<template>
  <div class="data-con">
    
    <div class="form">

      <div>
        登录账号: 13521260626
      </div>

      <div>
        姓名: <el-input v-model="input" placeholder="请输入内容"></el-input>
      </div>

      <div>
        介绍:
        <el-input
          type="textarea"
          :rows="2"
          placeholder="请输入内容"
          v-model="textarea">
        </el-input>
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
  }
}
</script>



