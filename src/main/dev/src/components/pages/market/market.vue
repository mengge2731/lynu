<style lang="less" scoped>
.content {
    width: 100%;
    display: flex;
    flex: 1;
    
    min-height: 500px;

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
              width: 480px;
              

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
                width: 480px;
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

        .info-list-item:first-child {
          border-top: 1px dashed #ccc;
        }
      }

      .page-component {
        float: right;
      }
    }
    
  }
</style>

<template>
 <div style="width:100%">
   <div >全部 旅游相关 文化相关 意大利相关</div>

   <div class="content">
       
     <!-- 这是要做成 路由的区域 -->
      <div class="info-left">

        

        <ul class="info-list">
          <li class="info-list-item clearfix">

            <router-link :to="{name:'marketApply'}">

            <div class="info-content">
              <div class="info-content-left">
                <div class="info-img" >
                  <img src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517767818040&di=ad8e9ca4c1b4ec96be5b13a9665795ec&imgtype=0&src=http%3A%2F%2Fk2.jsqq.net%2Fuploads%2Fallimg%2F1703%2F7_170331144403_4.jpg" alt="">
                </div> 
                <div class="info-text">
                  <h3>意大利2017年经济情况报告-全部数据</h3>
                  <p>意大利2017年金融、工业、旅游等主要经济类型各地金融工业旅游等主要经济类型各地</p>
                </div> 
              </div>
              <div class="info-content-right">
                <p>数据量: 200GB</p>
                <p>时间: 2018年1月29日</p>
              </div>
            </div>

            </router-link>
          </li>
          
        </ul>
        <!-- 分页组件 -->
        <div class="page-component">
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[100, 200, 300, 400]"
            :page-size="100"
           
            :total="400">
          </el-pagination>
        </div>
         <!-- layout="total, sizes, prev, pager, next, jumper" -->
      </div>
      
      <!-- 右侧组件  遮罩层在右侧组件中-->
      <info-right></info-right>
  </div>
 </div>
  
  
</template>

<script>
import infoRight from '../../common/infoRight'
export default {
  data(){
    return {
      currentPage: 1,
    }
  },
  components:{
    infoRight,
  },
  created(){

  },
  methods:{
    handleSizeChange(){

    },
    handleCurrentChange(){

    },

  }
}
</script>


