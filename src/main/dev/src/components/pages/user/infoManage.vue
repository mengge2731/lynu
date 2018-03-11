
<style lang="less" scoped>

  .data-list{
    padding: 20px 0 20px 10px;
    width: 100%;
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
          width: 764px;

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
        // 按钮
        .item-right{
          float: right;
          padding-top: 20px;
          padding-right: 20px;
          
        }
      }


    }
  }
  
</style>

<template>
  <div class="data-con">
    
   <ul class="data-list">
      <li class="data-list-item clearfix">
        <div class="item-container">
          <div class="item-left">
              <div class="info-content">
              <div class="info-content-left" >
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
          </div>

          <div class="item-right">
            <el-button type="danger" plain size="small"  @click="del()">删除</el-button>
          </div>
        </div>
      </li>
    </ul>

  </div>

</template>

<script>
export default {
  data(){
    return {
    }
  },
  created(){
    let that = this;
    this.$axios.post('/login/isLogin')
    .then( res => {
      console.log(res.data)
      if(res.data.code == "0001"){
        this.$message({
            message: '未登录',
            type: 'info'
        });

        this.$router.push({ path: '/'});
      }
    })
  },
  methods:{
    del(fileId){
      let that = this;
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          // 发送删除请求操作
          console.log('删除文件操作'+ fileId )

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
    }
     
  }
}
</script>