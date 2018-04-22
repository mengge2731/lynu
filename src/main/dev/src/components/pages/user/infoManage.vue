
<style lang="less" scoped>
.data-list {
  padding: 20px 0 20px 10px;
  // width: 100%;
  .data-list-item {
    &:first-child {
      border-top: 1px dashed #ccc;
    }

    padding: 10px 0;
    border-bottom: 1px dashed #ccc;
    .item-container {
      width: 100%;

      .item-left {
        float: left;
        width: 750px;

        .info-content-left {
          .info-img {
            width: 40px;
            float: left;
            img {
              width: 100%;
            }
          }

          .info-text {
            padding: 0 0 0 14px;
            float: left;
            width: 480px;

            h3 {
              font-size: 16px;
              // font-weight: 700;
              color: rgb(49, 49, 49);

              // 省略号
              width: 480px;
              text-overflow: ellipsis;
              overflow: hidden;
              white-space: nowrap;
            }
            p {
              margin-top: 6px;
              font-size: 14px;
              color: rgb(151, 151, 151);
              // 省略号
              width: 480px;
              text-overflow: ellipsis;
              overflow: hidden;
              white-space: nowrap;
            }
          }
        }

        .info-content-right {
          float: left;
          padding: 0 0 0 20px;

          border-left: 1px dashed rgb(161, 161, 161);
          p {
            font-size: 12px;
          }
          p + p {
            margin-top: 8px;
          }
        }
      }
      // 按钮
      .item-right {
        float: right;
        padding-top: 6px;
        padding-right: 20px;
      }
    }
  }
}

.page-component {
  float: right;
  margin-bottom: 20px;
}
</style>

<template>
  <div>
      <ul class="data-list">
        <li class="data-list-item clearfix"  v-for=" (item,index) in dataList" :key="index">
          <div class="item-container">
            <div class="item-left">
                <div class="info-content">
                <div class="info-content-left" >
                  <div class="info-img" >
                    <img :src="item.imgUrl" alt="">
                  </div> 
                  <div class="info-text">
                    <h3>{{item.dataTitle}}</h3>
                    <p>{{item.dataDesc}}</p>
                  </div> 
                </div>
                <div class="info-content-right">
                  <p>数据量: {{item.dataNum}}</p>
                    <p>时间: {{item.createTime}}</p>
                </div>
              </div>
            </div>

            <div class="item-right">
              <el-button type="danger" plain size="small"  @click="del(item.dataId,index)">删除</el-button>
            </div>
          </div>
        </li>
      </ul>

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
  data(){
    return {
      dataList:[], // 列表数据
       // 分页数据
      pageData: {},
      pageSize:[10, 20, 50],
      currentPage: 1,
      totalPage:0, // 总条数 = 总页数 * 每页数据
      size:10,
      index:1,
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
        // 登录后获取  第一页信息
        let data = {
          pageSize:this.size,
          index:this.index, 
        }
        let params = 'body=' + JSON.stringify(data);
        // 默认请求首页数据
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
        .catch( err => console.log(err + '获取首页数据失败'));

      }
    })
  },
  methods:{
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
          this.loginbox.cover = true; // 遮罩层是否开启
          this.loginbox.loginOrRegister = true;  // 显示登录框  还是注册框
        }
      })
    },
    del(dataId,index){

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
              this.$axios.post('/data/delDataInfo',params)
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
    }
     
  }
}
</script>