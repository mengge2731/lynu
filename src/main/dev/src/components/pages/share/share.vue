<style lang="less" scoped>
  .data-share-con{
    width: 600px;

    font-size: 14px;
    padding: 20px 0 0 200px;
    .data-pub,
    .data-name,
    .data-info,
    .new-pw-repeat,
    {
      margin-top: 10px;
    }

    
    // 单独样式
    .data-pub{
      font-size: 16px;
      font-weight: 700;
      margin-bottom: 20px;
    }

    .data-name{
      display: flex;
      .name-title{
        width: 70px;
        height: 40px;
        line-height: 40px;
      }

      .name-input{
        flex:1;
      }
    }
    .data-info{
      display: flex;
      .data-info-num{
        display: flex;
        width: 280px;
        .info-title{
          width: 70px;
          height: 40px;
          line-height: 40px;
        }

        .info-input{
          flex:1;
        }
      }

      .data-info-type{
        display: flex;
        width: 280px;
        margin-left: 80px;
        .type-title{
          width: 70px;
          height: 40px;
          line-height: 40px;
        }

        .type-input{
          flex:1;
        }
      }
      
    }
    .user-present{
      margin-top: 10px;

      div {
        float:left;
      }

      .present-text{
        width:70px;
        height: 40px;
        line-height: 40px;
      }

      .present-input {
        width: 530px;
      }
    }

    .user-info{
      margin-top: 20px;
      border-top: 1px solid #ccc;
      .user-name{
        padding-top: 20px;

        div {
          float: left;
        }
        .name-left{
          width: 80px;
        }
        .name-right{
          width: 520px;
          text-overflow: ellipsis;
          overflow: hidden;
          white-space: nowrap;
        }

      }

      .user-desc {
        margin-top: 10px;
        div{
           float: left;
        }
        .desc-left{
          width: 80px;
        }
        .desc-right{
          width: 520px;
        }
      }
    }
    
    
    .user-info-submit{
      float: right;
      margin-top: 20px;
    }
  
  }

  .el-select{
    width: 100%;
  }
</style>

<template>
  <div class="data-share-con">
   

    <div class="data-pub">数据发布:</div>
    <div class="data-name">
      <div class="name-title">
        数据名称:
      </div>
      <div class="name-input">
         <el-input v-model="dataName" placeholder="请输入内容"></el-input>
      </div>
    </div>

    <div class="data-info">
      <div class="data-info-num">
        <div class="info-title">
          数据量:
        </div>
        <div class="info-input">
          <el-input v-model="dataSize" placeholder="请输入内容"></el-input>
        </div>
        
      </div>

      <div class="data-info-type">
        <div class="type-title">
          数据分类:
        </div>
        <div class="type-input">
          <el-select v-model="dataType" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </div>
       
      </div>
  </div>
    
    <div class="user-present clearfix">
      <div class="present-text">数据介绍:</div>
      
      <div class="present-input">
        <el-input
          type="textarea"
          :rows="2"
          placeholder="请输入内容"
          v-model="dataDesc">
        </el-input>
      </div>
      
    </div>

    <div class=" user-info">
      <div class="user-name clearfix">
        <div class="name-left">
          数据发布人:
        </div>

        <div class="name-right">
          {{userInfo.userName}}
        </div>
        
      </div>
      <div class="user-desc clearfix">
        <div class="desc-left">发布人简介:</div>
        <div class="desc-right">{{userInfo.userDesc}}</div>
      </div>

    </div>
    <div class="user-info-submit clearfix">
        <el-button type="primary" class="submit-button" @click="saveInfo">发布</el-button>
        <el-button  class="submit-button" @click="goBack">返回</el-button>
      </div>

  </div>
</template>

<script>
import { code } from '../../../util/util'
export default {
  data(){
    return {
      dataName:'',
      dataSize:'',
      dataType:'',
      dataDesc:'',
      fileId:0,
      label:'',
      options: [{
          value: '1',
          label: '旅游相关'
        },{
          value: '3',
          label: '意大利相关'
        },{
          value: '2',
          label: '文化相关'
        },
        // {
        //   value: '4',
        //   label: '其他'
        // }
        ],
        value:'',
        userInfo:{}
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
        //已登录  获取用户数据
            this.$axios.post('/user/getUserInfo')
            .then( res => {
              if(res.data.code == code.success){
                this.userInfo = res.data.data;
              }else {
                // 数据获取失败
                this.$message({
                    message: '申请数据失败，请重试',
                    type: 'info'
                });
              }
            })
            .catch( err => console.log(err ));
      }
    })
  },
  methods:{
    saveInfo(){

      if(this.dataName != '' &&
      this.dataSize != '' &&
      this.dataType != '' &&
      this.dataDesc != '' ){

        // 发送数据
        var data ={
          dataDesc: this.dataDesc,
          dataNum: this.dataSize,
          dataTitle: this.dataName,
          dataType: this.dataType,	// 数据类型；数据类型 1-旅游相关 2-文化相关 3-意大利相关	string
          fileId: this.fileId,	
        }

        let params = 'body=' + JSON.stringify(data);

        this.$axios.post('/login/isLogin')
        .then( res => {

          if(res.data.code == code.login){
            this.$axios.post('/data/saveDataInfo',params)
            .then( res => {
              if(res.data.code == code.success){
                
                // 提示上传成功

                this.$message({
                  message: '数据共享成功',
                  type: 'success'
                });
                // 跳转首页
                this.$router.push({ path: '/'});


              }
            })
            .catch( err => console.log(err ));

            

          }else if(res.data.code == code.noLogin){
            this.$message({
                message: '未登录',
                type: 'info'
            });
            this.$router.push({ path: '/'});
          }
        })
        


      }else {
        alert('内容不能为空，请输入')
      }

      // 只有当所有信息不为空时，才能提交
    },
    goBack(){
      // 默认返回首页，或者历史记录 返回
      // this.$router.push({
      //   path:'/'
      // })
      this.$router.go(-1);
    }
  }
}
</script>


