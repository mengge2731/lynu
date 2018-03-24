
<style lang="less" scoped>
  .manage-con{
    width: 840px;
    padding: 10px 0 0 20px;


    .page-component {
        float: right;
        margin-bottom: 20px;
        margin-top: 20px;
      }
  }

 
</style>

<template>
 <div class="manage-con">
  
     <el-table
      :data="userList.data"
      style="width: 100%">
      <el-table-column
        prop="userId"
        label="编号"
        width="100">
      </el-table-column>

      <el-table-column
        prop="phone"
        label="手机号"
        width="140">
      </el-table-column>

      <el-table-column
        prop="userName"
        label="姓名"
        width="100">
      </el-table-column>
      
      <el-table-column
        prop="userDesc"
        label="简介"
        width="280">
      </el-table-column>

      <el-table-column label="角色"  width="100">
        <template slot-scope="scope">
          <el-select v-model="scope.row.userType" size="mini" @change="changeType(scope.row.userId,scope.row.userType)">
            <el-option
              v-for="item in typeOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </template>
      </el-table-column>

      <el-table-column label="状态"  width="100" :model="userList.data">
        <template slot-scope="scope">
          <el-select v-model="scope.row.status" size="mini" @change="changeStatus( scope.row.userId ,scope.row.status )">
            
            <el-option
              v-for="item in statusOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </template>
      </el-table-column>

  </el-table>

  <!-- 分页组件 -->
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
    data() {
      return {
        type: 0,
        // 分页数据
        pageData: {},
        pageSize:[10, 20, 50],
        currentPage: 1,
        totalPage:0, // 总条数 = 总页数 * 每页数据
        size:10,
        index:1,


        userList:{},
        typeOptions: [{
          value: '1',
          label: '普通用户'
        }, {
          value:'2',
          label: '管理员'
        }],
        statusOptions:[{
          value: 0,
          label: '正常'
        },{
          value: 2,
          label: '停用'
        }],

        userType:'',
        status:''
        
        
      }
    },
    created(){
      let that = this;
      this.$axios.post('/login/isLogin')
      .then( res => {
        console.log(res.data)
        if(res.data.code == code.noLogin){
          this.$message({
              message: '未登录',
              type: 'info'
          });

          this.$router.push({ path: '/'});
        }else if(res.data.code == code.login){
          // 获取 用户列表
           let data = {
            pageSize:this.size,
            index:this.index, 
          }
          let params = 'body=' + JSON.stringify(data);
          this.$axios.post('/login/isLogin')
          .then( res => {
            if(res.data.code == code.login){
              this.$axios.post('/user/getUserByPage',params)
              .then( res => {
                  if(res.data.code == code.success){
                  // 整体数据，包括分页数据
                  let pageInfo = res.data.data
                  this.pageData = pageInfo;
                  // 数据总条数  总条数 = 总页数 * 每页数据
                  this.totalPage = pageInfo.totalPage * pageInfo.pageSize;
                  // 数据列表
                  this.userList = res.data.data;
                }else {

                  this.$message({
                  message: '用户信息获取失败，请刷新',
                  type: 'info'
                });

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
        }
      })
    },
    methods: {
      // handleEdit(index,row) {
      //   console.log(index)
      // },
      // handleDelete(index,row){
      //   console.log(index)
      // },
      // submitType(value){
      //   alert('触发了')
      //   console.log(value)
      // },
      changeType(id, userType){

        let data = {
          userId: id,
          userType: userType, 
        }
        let params = 'body=' + JSON.stringify(data);
        this.$axios.post('/login/isLogin')
        .then( res => {
          if(res.data.code == code.login){
            this.$axios.post('/user/updateUserType',params)
            .then( res => {
                // 修改成功 提示
                this.$message({
                  type: 'success',
                  message: '修改成功!'
                });
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

      },
      changeStatus(id,status){
        alert(id)
        alert(status)

         let data = {
          userId: id,
          status: status, 
        }
        let params = 'body=' + JSON.stringify(data);
        this.$axios.post('/login/isLogin')
        .then( res => {
          if(res.data.code == code.login){
            this.$axios.post('/user/updateUserStatus',params)
            .then( res => {
                // 修改成功 提示
                this.$message({
                  type: 'success',
                  message: '修改成功!'
                });
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
      },

      // /function/user/getUserByPage
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
            this.$axios.post('/user/getUserByPage',params)
            .then( res => {
                if(res.data.code == code.success){
                  // 整体数据，包括分页数据
                  let pageInfo = res.data.data
                  this.pageData = pageInfo;
                  // 数据总条数  总条数 = 总页数 * 每页数据
                  this.totalPage = pageInfo.totalPage * pageInfo.pageSize;
                  // 数据列表
                  this.userList = res.data.data;
                }else {
                  this.$message({
                    message: '用户信息获取失败，请刷新',
                    type: 'info'
                })

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
            this.$axios.post('/user/getUserByPage',params)
            .then( res => {
              if(res.data.code == code.success){
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
            // this.loginbox.cover = true; // 遮罩层是否开启
            // this.loginbox.loginOrRegister = true;  // 显示登录框  还是注册框
          }
        })
      },
       
    },

    
  }
</script>
