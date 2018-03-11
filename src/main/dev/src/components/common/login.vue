<style lang="less" scoped>
.content {
    width: 100%;
    display: flex;
    flex: 1;
    min-height: 500px;
    overflow: auto;
    overflow-y: auto;
    .info-left {
      flex: 4;
      // background-color: red;

      .info-list {
        padding: 10px 0px;

        .info-list-item {
          padding: 10px 0;

          border-bottom: 1px dashed #ccc;
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
              width: 466px;
              

              h3 {
                font-size: 18px;
                font-weight: 700;
                color: rgb(49, 49, 49);

                width: 460px;
                text-overflow: ellipsis;
              overflow: hidden;
              white-space: nowrap;
              }
              p {
                margin-top: 12px;
                font-size: 14px;
                color: rgb(151, 151, 151);
                // 省略号
                width: 470px;
              text-overflow: ellipsis;
              overflow: hidden;
              white-space: nowrap;
              }
            }
          }
          .info-content-right {
            float: left;
            padding: 10px 30px;

            border-left: 1px dashed rgb(161, 161, 161);
            p {
              font-size: 12px;
            }
            p + p {
              margin-top: 10px;
            }
          }
        }

        .info-list-item:first-child {
          border-top: 1px dashed #ccc;
        }
      }

      .page-component {
        float: right;
        margin-bottom: 20px;
      }
    }
    
  }

// 登录框样式
  #cover-box{
    background-color: rgba(0,0,0,.5);
    position: absolute;
    left: 0;
    top:0;
    width: 100%;
    height: 100%;

    .login-box{
      width: 380px;
      height: 350px;
      background-color: #fff;
      margin: 0 auto;
      transform: translateY(50%);
    }
  }
</style>

<template>
  <div class="content">
     <!-- 这是要做成 路由的区域 -->
      <div class="info-left">
        <ul class="info-list">
          <li class="info-list-item clearfix" v-for="(item,index) in dataList">
            <div class="info-content">
              <div class="info-content-left">
                <div class="info-img" >
                  <img src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517767818040&di=ad8e9ca4c1b4ec96be5b13a9665795ec&imgtype=0&src=http%3A%2F%2Fk2.jsqq.net%2Fuploads%2Fallimg%2F1703%2F7_170331144403_4.jpg" alt="">
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
            
            :total="totalPage">
          </el-pagination>
        </div>
        <!-- layout="total, sizes, prev, pager, next, jumper" -->
      </div>
      <!-- 右侧组件  遮罩层在右侧组件中-->
      <info-right></info-right>

  </div>
    
</template>

<script>
import infoRight from './infoRight'
export default {
  data() {
    return {
      dataList:[], // 列表数据
      // 分页数据
      pageData: {},
      pageSize:[10, 20, 50],
      currentPage: 1,
      totalPage:0, // 总条数 = 总页数 * 每页数据
      
    };
  },
  components:{
    infoRight,
  },
  created(){
    // 默认请求首页数据
    this.$axios.post('/login/getFirstPageData')
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
  },
  methods:{
    handleSizeChange(){

      this.$axios.post('/login/isLogin')
      .then( res => {

        if(res.data.code == "0002"){

        }else {
          this.$router.push({
            path:'/login'
          })
        }
      })



    },
    handleCurrentChange(){
      this.$axios.post('/login/isLogin')
      .then( res => {

        if(res.data.code == "0002"){

        }else {
          this.$router.push({
            path:'/login'
          })
        }
      })
    },
    
  },
  mounted(){
    
  }
};
</script>

