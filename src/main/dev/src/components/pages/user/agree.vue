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
        prop="createTime"
        label="申请日期"
        width="100">
      </el-table-column>
      <el-table-column
        prop="applyUserName"
        label="申请人"
        width="100">
      </el-table-column>
      <el-table-column
        prop="applyUserPhone"
        label="申请人电话"
        width="140">
      </el-table-column>

      <el-table-column label="操作"  width="80">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="check(scope.row.applyId)">查看</el-button>
        </template>

        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="delRow(scope.row.applyId)">删除</el-button>
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
import { code } from '../../../util/util'
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

      tableData: []
    }
  },
  created(){
    let that = this;
    this.$axios.post('/login/isLogin')
    .then( res => {
      if(res.data.code == code.noLogin){
        this.$message({
            message: '未登录',
            type: 'info'
        });

        this.$router.push({ path: '/'});
        
      }else {
        // 获取共享数据的  id  发送请求
         let data = {
          dataId : parseInt(this.$route.query.dataId),
          pageSize: this.size,
          index: this.index, 
        }
        
        let params = 'body=' + JSON.stringify(data);
        this.$axios.post('/login/isLogin')
        .then( res => {
          if(res.data.code == code.login){
            this.$axios.post('/apply/getThireApplyInfoByPage',params)
            .then( res => {
                // 整体数据，包括分页数据
                let pageInfo = res.data.data
                this.pageData = pageInfo;
                // 数据总条数  总条数 = 总页数 * 每页数据
                this.totalPage = pageInfo.totalPage * pageInfo.pageSize;
                // 数据列表
                this.tableData = res.data.data.data;
            })
            .catch( err => console.log(err));
          }else if(res.data.code == code.noLogin){

            // 显示登录框
            this.loginbox.cover = true; // 遮罩层是否开启
            this.loginbox.loginOrRegister = true;  // 显示登录框  还是注册框
          }
        })
      }
    })
    // 获得当前数据id  获取该数据的申请状态信息

  },
  methods:{
    goBack(){
      this.$router.go(-1)
    },
    delRow(){ //需要测试
      let that = this;
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          // 发送删除请求操作
          let data = {
            dataId: dataId
          }
          let params = 'body=' + JSON.stringify(data);
          this.$axios.post('/login/isLogin')
          .then( res => {
            if(res.data.code == code.login){
              this.$axios.post('/data/',params)
              .then( res => {
                if(res.data.code == code.success){
                     this.$message({
                        type: 'success',
                        message: '删除成功!'
                      });

                    setTimeout( function(){
                      that.dataList.shift(index,1);
                    },200)
                  
                }else {
                  //网络异常请重试


                }
                  
              })
              .catch( err => console.log(err));

            }else if(res.data.code == code.noLogin){
              this.$message({
                message: '未登录',
                type: 'info'
              });

              this.$router.push({ path: '/'});
            }
          })
          
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
    },
    check(id){
      this.$router.push({
        path:'/user/approve?applyId=' + id 
      })
      // 跳转审核页面
    },
    handleSizeChange(val){
      this.size = val;
      let data = {
        dataId : parseInt(this.$route.query.dataId),
        pageSize: this.size,
        index: this.index, 
      }
      
      let params = 'body=' + JSON.stringify(data);
      this.$axios.post('/login/isLogin')
      .then( res => {
        if(res.data.code == code.login){
           this.$axios.post('/apply/getThireApplyInfoByPage',params)
          .then( res => {
              // 整体数据，包括分页数据
              let pageInfo = res.data.data
              this.pageData = pageInfo;
              // 数据总条数  总条数 = 总页数 * 每页数据
              this.totalPage = pageInfo.totalPage * pageInfo.pageSize;
              // 数据列表
              this.tableData = res.data.data.data;
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
        dataId : parseInt(this.$route.query.dataId),
        pageSize: this.size,
        index: this.index, 
      }
      let params = 'body=' + JSON.stringify(data);
      this.$axios.post('/login/isLogin')
      .then( res => {
        if(res.data.code == code.login){
           this.$axios.post('/data/getDataByPage',params)
          .then( res => {
            if(res.data.code == code.success){
              // 整体数据，包括分页数据
              let pageInfo = res.data.data
              this.pageData = pageInfo;
              // 数据总条数  总条数 = 总页数 * 每页数据
              this.totalPage = pageInfo.totalPage * pageInfo.pageSize;
              // 数据列表
              this.tableData = res.data.data.data;
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

