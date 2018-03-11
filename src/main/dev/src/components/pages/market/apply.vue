<style lang="less" scoped>
.apply-con{
  padding:30px 80px;
  font-size: 14px;

  width: 100%;

  overflow: auto;

  .apply-info{
    h3{
      font-size: 16px;
      font-weight: 700;
    }
  }

  .apply-info-top{
    margin-top: 20px;
    padding-bottom: 20px;
    border-bottom: 1px solid #ccc;

    .apply-title{

      // 省略号
                width: 800px;
              text-overflow: ellipsis;
              overflow: hidden;
              white-space: nowrap;
    }

    .apply-pro{
      margin-top: 10px;
      display: flex;
      div{
        flex:1;
      }
    }

    .apply-data-con{
      margin-top: 10px;
      width: 100%;

      display: flex;

      .apply-data-title{
        width: 80px;
      }

      .apply-data-info{
        flex: 1;
      }
    }

    .apply-name{
      margin-top: 10px;

    }

    .apply-user-pre{
      margin-top: 10px;
      display: flex;

      .pre-title{
        width: 90px;
      }

      .pre-info{
        flex: 1;
      }
      

    }

  }

  .apply-info-bot{
    margin-top: 20px;

    .apply-user{}

    .apply-tel{
      margin-top: 10px;
    }

    .apply-info-other{
      margin-top: 10px;

      display: flex;

      .info-other-title{
        width: 60px;
      }

      .info-other-input{
        flex:1;
      }
    }

    .apply-button{
      margin-top: 20px;
      float: right;
    }
  }
}
</style>

<template>
  <div class="apply-con">
    <div class="apply-info">
      <h3>申请获取数据：</h3>
    </div>
    <div class="apply-info-top" :key="dataInfo.dataId">
      <div class="apply-title">
        申请内容：{{dataInfo.dataTitle}}
      </div>
      <div class="apply-pro clearfix"> 
        <div class="apply-num">数据量：{{dataInfo.dataNum}} </div>
        <div class="apply-time">发布时间：{{dataInfo.createTime}}</div>
      </div>
      <div class="apply-data-con clearfix">
        <div class="apply-data-title">
          数据介绍：
        </div>
        <div class="apply-data-info">
          {{dataInfo.dataDesc}}

        </div>
      </div>

      <div class="apply-name">
        数据发布人：{{dataInfo.pubUser}}
      </div>

      <div class="apply-user-pre">
        <div class="pre-title">发布人简介：</div>
        <div class="pre-info">{{dataInfo.pubDesc}}</div>
        
      </div>
    </div>

    <div class="apply-info-bot">
      <div class="apply-user">
        申请人：{{userInfo.userName}}  
      </div>
      <div class="apply-tel">注册电话：{{userInfo.phone}}</div>
      <div class="apply-info-other">
        <div  class="info-other-title">
          附言：
        </div>
        <div class="info-other-input">
          <el-input
            type="textarea"
            :rows="2"
            placeholder="请输入至少20个字"
            v-model="otherInfo">
          </el-input>
        </div>
        
       
      </div>

      <div class="apply-button">
        <el-button type="primary" size="medium" @click="toApply">申请</el-button>
        <el-button size="medium" @click="goBack">返回</el-button>
        
      </div>
    </div>

  </div>
</template>

<script>
export default {
  data(){
    return {
      dataInfo:{},  // 数据信息
      userInfo:{}, // 申请人数据
      otherInfo:"", // 附言
    }
  },
  created(){
    // 页面加载时获取，数据的id，然后查询渲染页面
    
    // let dataId = this.$route.query.dataId

    let data = {
      dataId: this.$route.query.dataId,
    }
    let params = 'body=' + JSON.stringify(data);

    this.$axios.post('/login/isLogin')
    .then( res => {

      if(res.data.code == '0002'){
        this.$axios.post('/data/getDataInfo',params)
        .then( res => {
          if(res.data.code == '0000'){
            this.dataInfo = res.data.data;
          }
        })
        .catch( err => console.log(err ));

        // 获取用户数据
        this.$axios.post('/user/getUserInfo')
        .then( res => {
          if(res.data.code == '0000'){
            this.userInfo = res.data.data;
          }else {
            
          }
        })
        .catch( err => console.log(err ));

      }else if(res.data.code == "0001"){
        this.$message({
            message: '未登录',
            type: 'info'
        });
        this.$router.push({ path: '/'});
      }
    })
  },
  methods:{
    goBack(){
      this.$router.go(-1)
    },
    toApply(){
      // 申请这条数据
      let data = {
        dataId: this.$route.query.dataId,
        applyDesc: this.otherInfo, 
      }
      let params = 'body=' + JSON.stringify(data);

      this.$axios.post('/login/isLogin')
      .then( res => {
        if(res.data.code == '0002'){
          this.$axios.post('/apply/saveApplyInfo',params)
          .then( res => {
            if(res.data.code == '0000'){
              this.otherInfo = '';
              // 返回市场
              this.$router.push({path:'/market'});
            }
          })
          .catch( err => console.log(err));

        }else if(res.data.code == "0001"){
          this.$message({
            message: '未登录',
            type: 'info'
          });
          this.$router.push({ path: '/'});
        }
      });
    }
  }
}
</script>


