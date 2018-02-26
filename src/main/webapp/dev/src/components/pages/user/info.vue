<template>
  <div>
    
    <el-tabs :tab-position="tabPosition" style="height: 100%;">
      <el-tab-pane label="个人信息">个人信息</el-tab-pane>
      <el-tab-pane label="已共享数据">已共享数据</el-tab-pane>
      <el-tab-pane label="已申请数据">已申请数据</el-tab-pane>
      <el-tab-pane label="密码修改">密码修改</el-tab-pane>
    </el-tabs>
  </div>

</template>

<script>
export default {
  data(){
    return {
      tabPosition: 'left',  // 左侧tab切换 
    }
  }
}
</script>

<style lang="less" scoped>

</style>


