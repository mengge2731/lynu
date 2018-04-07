<style lang="less" scoped>
.approve-con{
    width: 544px;
    font-size: 14px;
    padding: 60px 0 0 160px;
    // border: 1px solid red;
  .approve-pub {
      font-size: 16px;
      font-weight: 700;
      margin-bottom: 20px;
  }
  .approve-info{
      .approve-user{
        float: left;
        width: 260px;
        div{
          float:left;
        }
        .uesr-left{
          margin-right: 10px;
          font-weight: 700;
        }
        .uesr-left{

        }
      }
      .pub-phone{
        float: left;
        div{
          float:left;
        }
        .phone-left{
          margin-right: 10px;
          font-weight: 700;
        }
        .phone-left{

        }
      }
  }
  .approve-title{
    margin-top: 10px;

    div{
      float: left;
    }
    .title-left{
      width: 70px;
      font-weight: 700;
    }
    .title-right{
      // 省略号
      width: 450px;
      text-overflow: ellipsis;
      overflow: hidden;
      white-space: nowrap;
    }
  }
  .other-desc{
    margin-top: 10px;
    div{
      float:left;
    }
    .desc-left{
      margin-right: 10px;
      font-weight: 700;
    }
    .desc-right{
      width: 480px;
    }

    .downLoad{
      margin-top: 10px;

    }
  }
  .agree-submit{
    margin-top: 20px;
    float: right;
    margin-right: 10px;
  }
  .approve-dataInfo{
    padding-bottom: 20px;
    border: 1px solid #ccc;
  }
}



</style>

<template>
  <div class="approve-con clearfix">

    <div class="approve-dataInfo">
      <div class="approve-pub">
        数据信息:
      </div>
      <div class="approve-title clearfix">
        <div class="title-left">数据名称:</div>
        <div class="title-right">{{dataInfoVo.dataTitle}}</div>
      </div>
      <div class="approve-title clearfix">
        <div class="title-left">数据内容:</div>
        <div class="title-right">{{dataInfoVo.pubDesc}}</div>
      </div>
      <div class="approve-title clearfix">
        <div class="title-left">数据量:</div>
        <div class="title-right">{{dataInfoVo.dataNum}}</div>
      </div>
      <div class="approve-title clearfix">
        <div class="title-left">数据类型:</div>
        <div class="title-right">{dataInfoVo.dataType == '1'? '旅游相关':  dataInfoVo.dataType == '2'? '文化相关': dataInfoVo.dataType == '3'? '意大利相关': dataInfoVo.dataType == '4'? '其他'}</div>
      </div>
      <div class="approve-info clearfix" >
        <div class="approve-user clearfix">
          <div class="uesr-left">发布人:</div>
          <div class="uesr-right">{{dataInfoVo.pubUser}}</div>
        </div>
        <div class="pub-phone">
          <div class="phone-left clearfix">注册电话:</div>
          <div class="phone-right" v-if="dataInfoVo.status == '1'">{{applyInfoVo.pubUserPhone}}</div>
          <div class="phone-right" v-else>审核通过后显示发布人联系方式</div>
        </div>
      </div>
      
    </div>
    
    <div class="apply-info">
      <div class="approve-pub">
        申请信息:
      </div>
      <div class="approve-info clearfix" >
        <div class="approve-user clearfix">
          <div class="uesr-left">申请人:</div>
          <div class="uesr-right">{{applyInfoVo.applyUserName}}</div>
        </div>
        <div class="pub-phone">
          <div class="phone-left clearfix">注册电话:</div>
          <div class="phone-right">{{applyInfoVo.applyUserPhone}}</div>
        </div>
      </div>
      <div class="approve-title clearfix">
        <div class="title-left">申请描述:</div>
        <div class="title-right">{{applyInfoVo.applyDesc}}</div>
      </div>
      <div class="other-desc clearfix">
        <div class="desc-left">状态:</div>
        <div class="desc-right" v-if="dataInfoVo.status == '0'">已提交，待审核。</div>
        <div class="desc-right" v-else-if="dataInfoVo.status == '1'">{{dataInfoVo.pubUser}}已同意给您数据，请直接与他联系。</div>
        <div class="desc-right" v-else="dataInfoVo.status == '2'">{{dataInfoVo.pubUser}}已拒绝给您数据。</div>
        <!-- <div class="downLoad" v-if="dataInfoVo.status == '1'"> -->
        <div class="downLoad" >
          
          这是数据下载的连接
          <a href="javascript:void(0);">{{dataInfoVo.fileName}}</a>
        </div>
      </div>
      <div class="agree-submit clearfix">
        <el-button  class="submit-button" @click="goBack">返回</el-button>
      </div>
    </div>
    
  </div>
</template>

<script>
import { code } from '../../../util/util'

export default {
  data(){
    return {
      // 由于数据库建表，接口返回的信息有交叉
      dataInfoVo:{},
      applyInfoVo:{},
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
        // 获得当前数据id  获取该数据的申请状态信息
        let data = {
          applyId : parseInt(this.$route.query.applyId),
        }
        let params = 'body=' + JSON.stringify(data);
        this.$axios.post('/login/isLogin')
        .then( res => {
          if(res.data.code == code.login){
            this.$axios.post('/apply/applyResult',params)
            .then( res => {
              // 获取 数据申请状态  信息
              this.dataInfoVo = res.data.data.dataInfoVo;
              this.applyInfoVo = res.data.data.applyInfoVo;
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
  },
  methods:{
    goBack(){
      this.$router.go(-1);
    },
  }
}
</script>





