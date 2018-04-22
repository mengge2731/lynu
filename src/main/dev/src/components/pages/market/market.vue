<style lang="less" scoped>
.content {
    width: 100%;
    margin-top: 10px;
    min-height: 500px;
    overflow: auto;
    overflow-y: auto;
    position: relative;

    .info-left {
      padding-right: 200px;
      margin-left: 10px;
      margin-bottom: 60px;

      .info-nav{
        height: 40px;
        line-height: 40px;
        font-size: 16px;

      }

      .info-list {
        padding: 10px 0px;
        .info-list-item {
          padding: 10px 0;

          border-bottom: 1px dashed #ccc;
          .info-content-left {
            .info-img {
              width: 40px;
              float: left;
              img {
                width: 100%;
              }
            }

            .info-text {
              padding: 0 14px;
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
            padding: 0 30px;

            border-left: 1px dashed rgb(161, 161, 161);
            p {
              font-size: 12px;
            }
            p + p {
              margin-top: 8px;
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
</style>

<template>
 
   <div class="content">
     <!-- 这是要做成 路由的区域 -->
      <div class="info-left">
        <div class="info-nav">
          <el-radio-group v-model="type" size="small" @change="check">
            <el-radio-button label="0" >全部</el-radio-button>
            <el-radio-button label="1">旅游相关</el-radio-button>
            <el-radio-button label="2">文化相关</el-radio-button>
            <el-radio-button label="3">意大利相关</el-radio-button>
            <el-radio-button label="4">其他</el-radio-button>
          </el-radio-group>
        </div>

        <ul class="info-list">
          <li class="info-list-item clearfix" v-for="(item,index) in dataList" :key="index">
            <!-- <router-link :to="{ name:'marketApply', params:{fileId: item.fileId} }"> -->
            <a href="javascript:void(0)">
              <div class="info-content" @click="goDetail(item.dataId)">
                <div class="info-content-left">
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
            </a>
            <!-- </router-link> -->
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
         <!-- layout="total, sizes, prev, pager, next, jumper" -->
      </div>
      
      <!-- 右侧组件  遮罩层在右侧组件中-->
      <info-right></info-right>
  </div>
  
</template>

<script>
import infoRight from '../../common/infoRight'
import { code } from '../../../util/util'
export default {
  data(){
    return {
      type: 0,
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
  components:{
    infoRight,
  },
  created(){
    let that = this;
    let data = {
        pageSize:this.size,
        index:this.index, 
        dataType: this.type
      }
      let params = 'body=' + JSON.stringify(data);
    this.$axios.post('/login/isLogin')
    .then( res => {

      if(res.data.code == code.login){
        // 默认请求首页数据
        this.$axios.post('/data/getDataByDataTypeAndPage',params)
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
        this.$message({
            message: '未登录',
            type: 'info'
        });

        this.$router.push({ path: '/'});
      }
    })
  },
  methods:{
    goDetail(id){
          this.$router.push({path:'/apply?dataId=' + id })
    },
     handleSizeChange(val){
      this.size = val;
      let data = {
        pageSize:this.size,
        index:this.index, 
        dataType: this.type
      }
      let params = 'body=' + JSON.stringify(data);
      this.$axios.post('/login/isLogin')
      .then( res => {
        if(res.data.code == code.login){
          this.$axios.post('/data/getDataByDataTypeAndPage',params)
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
        dataType: this.type
      }
      let params = 'body=' + JSON.stringify(data);
      this.$axios.post('/login/isLogin')
      .then( res => {
        if(res.data.code == code.login){
           this.$axios.post('/data/getDataByDataTypeAndPage',params)
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
    check(val){
      let data = {
        pageSize:this.size,
        index:this.index, 
        dataType: this.type
      }
      // 切换分类
      let params = 'body=' + JSON.stringify(data);
      this.$axios.post('/login/isLogin')
      .then( res => {
        if(res.data.code == code.login){
           this.$axios.post('/data/getDataByDataTypeAndPage',params)
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

    }

  }
}
</script>


