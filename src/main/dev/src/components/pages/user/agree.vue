<style lang="less" scoped>
  .data-agree-con{
    width: 820px;
    padding: 20px 20px;


    .page-component {
        float: right;
        margin-top: 20px;
        margin-bottom: 20px;
      }
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
        prop="desc"
        label="申请信息"
        width="400">
      </el-table-column>

      <el-table-column label="操作"  width="80">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="check(scope.row.id)">查看</el-button>
          
        </template>
      </el-table-column>
  </el-table>

  <!-- 分页功能 -->
    <div class="page-component">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="pageSize"
        :page-size="pageData.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        
        :total="totalPage">
      </el-pagination>
    </div>


  </div>
</template>

<script>
export default {
  data(){
    return {
      // 分页数据
      pageData: {},
      pageSize:[10, 20, 50],
      currentPage: 1,
      totalPage:0, // 总条数 = 总页数 * 每页数据
      size:10,
      index:1,

      tableData: [{
            id:1,
            date: '2016-05-02',
            name: '王小虎',
            desc: '您好，我是河北师范大学旅游学院的王磊，我们希望获取贵校意大利2017年的经济数据，不知可否共享，我校有加拿大的数据可以交换，如果可以麻烦您联系我的电话13512348888.谢谢。',
            tel:'13512348888',
          }, {
            id:2,
            date: '2016-05-04',
            name: '王小虎王小虎王小虎王小虎王小虎',
            desc: '您好，我是河北师范大学旅游学院的王磊，我们希望获取贵校意大利2017年的经济数据，不知可否共享，我校有加拿大的数据可以交换，如果可以麻烦您联系我的电话13512348888.谢谢。',
            tel:'13512348888',
          }, {
            id:3,
            date: '2016-05-01',
            name: '王小虎',
            desc: '您好，我是河北师范大学旅游学院的王磊，我们希望获取贵校意大利2017年的经济数据，不知可否共享，我校有加拿大的数据可以交换，如果可以麻烦您联系我的电话13512348888.谢谢。',
            tel:'13512348888',
          }, {
            id:4,
            date: '2016-05-03',
            name: '王小虎',
            desc: '您好，我是河北师范大学旅游学院的王磊，我们希望获取贵校意大利2017年的经济数据，不知可否共享，我校有加拿大的数据可以交换，如果可以麻烦您联系我的电话13512348888.谢谢。',
            tel:'13512348888',
          },
          {
            id:1,
            date: '2016-05-02',
            name: '王小虎',
            desc: '您好，我是河北师范大学旅游学院的王磊，我们希望获取贵校意大利2017年的经济数据，不知可否共享，我校有加拿大的数据可以交换，如果可以麻烦您联系我的电话13512348888.谢谢。',
            tel:'13512348888',
          }, {
            id:2,
            date: '2016-05-04',
            name: '王小虎王小虎王小虎王小虎王小虎',
            desc: '您好，我是河北师范大学旅游学院的王磊，我们希望获取贵校意大利2017年的经济数据，不知可否共享，我校有加拿大的数据可以交换，如果可以麻烦您联系我的电话13512348888.谢谢。',
            tel:'13512348888',
          }, {
            id:3,
            date: '2016-05-01',
            name: '王小虎',
            desc: '您好，我是河北师范大学旅游学院的王磊，我们希望获取贵校意大利2017年的经济数据，不知可否共享，我校有加拿大的数据可以交换，如果可以麻烦您联系我的电话13512348888.谢谢。',
            tel:'13512348888',
          }, {
            id:4,
            date: '2016-05-03',
            name: '王小虎',
            desc: '您好，我是河北师范大学旅游学院的王磊，我们希望获取贵校意大利2017年的经济数据，不知可否共享，我校有加拿大的数据可以交换，如果可以麻烦您联系我的电话13512348888.谢谢。',
            tel:'13512348888',
          }]
    }
  },
  created(){
    let that = this;
    // this.$axios.post('/login/isLogin')
    // .then( res => {
    //   if(res.data.code == "0001"){
    //     this.$message({
    //         message: '未登录',
    //         type: 'info'
    //     });

    //     this.$router.push({ path: '/'});
        
    //   }
    // })
    // 获得当前数据id  获取该数据的申请状态信息

  },
  methods:{
    goBack(){
      this.$router.go(-1)
    },
    check(id){
      console.log(id);
      this.$router.push({
        path:'/user/approve?id=' + id 
      })
      // 跳转审核页面
    },
    handleSizeChange(val){
      this.size = val;
      let data = {
        pageSize:this.size,
        index:this.index, 
      }
      let params = 'body=' + JSON.stringify(data);
      this.$axios.post('/login/isLogin')
      .then( res => {
        if(res.data.code == code.login){
           this.$axios.post('/data/getDataByPage',params)
          .then( res => {
              // 整体数据，包括分页数据
              let pageInfo = res.data.data
              this.pageData = pageInfo;
              // 数据总条数  总条数 = 总页数 * 每页数据
              this.totalPage = pageInfo.totalPage * pageInfo.pageSize;
              // 数据列表
              this.dataList = res.data.data.data;
          })
          .catch( err => console.log(err));
        }else if(res.data.code == code.noLogin){

          // 显示登录框
          this.loginbox.cover = true; // 遮罩层是否开启
          this.loginbox.loginOrRegister = true;  // 显示登录框  还是注册框
        }
      })
    },
    handleCurrentChange(val){
      this.index = val;
      let data = {
        pageSize:this.size,
        index:this.index, 
      }
      let params = 'body=' + JSON.stringify(data);
      this.$axios.post('/login/isLogin')
      .then( res => {
        if(res.data.code == code.login){
           this.$axios.post('/data/getDataByPage',params)
          .then( res => {
            if(res.data.code == "0000"){
              // 整体数据，包括分页数据
              let pageInfo = res.data.data
              this.pageData = pageInfo;
              // 数据总条数  总条数 = 总页数 * 每页数据
              this.totalPage = pageInfo.totalPage * pageInfo.pageSize;
              // 数据列表
              this.dataList = res.data.data.data;
            }else {
              //网络异常请重试
            }
              
          })
          .catch( err => console.log(err));

        }else if(res.data.code == code.noLogin){
          // 显示登录框
          this.loginbox.cover = true; // 遮罩层是否开启
          this.loginbox.loginOrRegister = true;  // 显示登录框  还是注册框
        }
      })
    },
  }
}
</script>

