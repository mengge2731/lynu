
<style lang="less" scoped>
.center-con{
  // width: 900px;
  min-height: 500px;
  overflow: scroll;
  .data-list{
    padding: 20px 0 20px 20px;
    // width:880px;
    .data-list-item{

      &:first-child{
          border-top: 1px dashed #ccc;
      }

      padding: 10px 0;
      border-bottom: 1px dashed #ccc;
      .item-container{
        width: 100%;

        .item-left{
          float: left;
          width: 644px;

          .info-content-left {
            .info-img {
              width: 60px;
              float: left;
              img {
                width: 100%;
              }
            }

            .info-text {
              padding: 8px 14px;
              float: left;
              width: 360px;
              

              h3 {
                font-size: 18px;
                font-weight: 700;
                color: rgb(49, 49, 49);
              }
              p {
                margin-top: 12px;
                font-size: 14px;
                color: rgb(151, 151, 151);
                // 省略号
                width: 360px;
                text-overflow: ellipsis;
                overflow: hidden;
                white-space: nowrap;
              }
            }
          }

          .info-content-right {
            float: left;
            padding: 10px 40px;

            border-left: 1px dashed rgb(161, 161, 161);
            p {
              font-size: 12px;
            }
            p + p {
              margin-top: 10px;
            }
          }
        }
        // 按钮
        .item-right{
          float: right;
          padding-top: 20px;
          padding-right: 20px;
          
        }
      }


    }
  }

  .page-component {
    float: right;
    margin-bottom: 20px;
  }
}
  
  
</style>

<template>
  <div class="center-con">
      <ul class="data-list clearfix">
        <li class="data-list-item clearfix" v-for=" (item,index) in dataList" :key="index">
          <div class="item-container">
            <div class="item-left">
                <div class="info-content">
                <div class="info-content-left" >
                  <div class="info-img" >
                    <img src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517767818040&di=ad8e9ca4c1b4ec96be5b13a9665795ec&imgtype=0&src=http%3A%2F%2Fk2.jsqq.net%2Fuploads%2Fallimg%2F1703%2F7_170331144403_4.jpg" alt="">
                  </div> 
                  <div class="info-text">
                    <h3>{{item.title}}</h3>
                    <p>{{item.desc}}</p>
                  </div> 
                </div>
                <div class="info-content-right">
                  <p>数据量: {{item.dataNum}}</p>
                  <p>时间: {{item.dataTime}}</p>
                </div>
              </div>
            </div>

            <div class="item-right">
              <el-button type="primary" plain size="small"  @click="toDetail(item.id)">新申请待审批</el-button>
              <el-button type="danger" plain size="small" @click="del(item.id,index)">删除</el-button>
            </div>
          </div>
        </li>
      </ul>

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
      dataList:[{
        title:'意大利2017年经济情况报告-全部数据',
        desc:'意大利2017年金融、工业、旅游等主要经济类型各地金融工业旅游等主要经济类型各地',
        dataNum:'200GB',
        dataTime:'2018年1月29日',
        dataId: 1,

      },
      {
        title:'意大利2017年经济情况报告-全部数据',
        desc:'意大利2017年金融、工业、旅游等主要经济类型各地金融工业旅游等主要经济类型各地',
        dataNum:'200GB',
        dataTime:'2018年1月29日',
        dataId: 1,

      },
      {
        title:'意大利2017年经济情况报告-全部数据',
        desc:'意大利2017年金融、工业、旅游等主要经济类型各地金融工业旅游等主要经济类型各地',
        dataNum:'200GB',
        dataTime:'2018年1月29日',
        dataId: 1,

      },
      {
        title:'意大利2017年经济情况报告-全部数据',
        desc:'意大利2017年金融、工业、旅游等主要经济类型各地金融工业旅游等主要经济类型各地',
        dataNum:'200GB',
        dataTime:'2018年1月29日',
        dataId: 1,

      },
      {
        title:'意大利2017年经济情况报告-全部数据',
        desc:'意大利2017年金融、工业、旅游等主要经济类型各地金融工业旅游等主要经济类型各地',
        dataNum:'200GB',
        dataTime:'2018年1月29日',
        dataId: 1,

      },
      {
        title:'意大利2017年经济情况报告-全部数据',
        desc:'意大利2017年金融、工业、旅游等主要经济类型各地金融工业旅游等主要经济类型各地',
        dataNum:'200GB',
        dataTime:'2018年1月29日',
        dataId: 1,

      }],
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
    // let that = this;
    // this.$axios.post('/login/isLogin')
    // .then( res => {
    //   console.log(res.data)
    //   if(res.data.code == "0001"){
    //     this.$message({
    //         message: '未登录',
    //         type: 'info'
    //     });

    //     this.$router.push({ path: '/'});
        
    //   }
    // })
  },
  methods:{
    toDetail(id){
      this.$router.push({
        path:'agree?id=' + id
      });
    },
    del(fileId,index){
      let that = this;
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          // 发送删除请求操作
          console.log('删除文件操作'+ fileId )

          this.dataList.shift(index);
          // 发送请求，删除
          

          setTimeout( function(){
            that.$message({
              type: 'success',
              message: '删除成功!'
            });
          },3000)

          
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
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