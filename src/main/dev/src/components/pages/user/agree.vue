<style lang="less" scoped>
  .data-agree-con{
    width: 820px;
    padding: 20px 20px;


  }
</style>

<template>
  <div class="data-agree-con">
  
    <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column
        prop="date"
        label="申请日期"
        width="100">
      </el-table-column>
      <el-table-column
        prop="name"
        label="申请人"
        width="100">
      </el-table-column>
      <el-table-column
        prop="tel"
        label="注册电话"
        width="140">
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址"
        width="300">
      </el-table-column>

      <el-table-column label="操作"  width="80">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="check(scope.row.id)">查看</el-button>
          
        </template>
      </el-table-column>
  </el-table>



  </div>
</template>

<script>
export default {
  data(){
    return {
      tableData: [{
        id:1,
            date: '2016-05-02',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄',
            tel:'13512348888',
          }, {
            id:2,
            date: '2016-05-04',
            name: '王小虎王小虎王小虎王小虎王小虎',
            address: '上海市普陀区金沙江路 1517 弄',
            tel:'13512348888',
          }, {
            id:3,
            date: '2016-05-01',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1519 弄',
            tel:'13512348888',
          }, {
            id:4,
            date: '2016-05-03',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1516 弄',
            tel:'13512348888',
          }]
    }
  },
  created(){
    // 获得当前数据id  获取该数据的申请状态信息

  },
  methods:{
    goBack(){
      this.$router.go(-1)
    },
    check(id){
      console.log(id);
      // 跳转审核页面
    }
  }
}
</script>

