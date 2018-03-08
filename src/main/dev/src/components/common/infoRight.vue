<style lang="less" scoped>
.info-right {
      flex: 1;
      // background-color: rgb(185, 185, 185);
      padding-top: 10px;
      padding-left: 10px;
      .info-right-top,
      .info-right-bot {
        height: 228px;
        border: 1px solid #ccc;
        text-align: center;

        h3{
          font-size: 16px;
          font-weight: 700;
          height: 50px;
          line-height: 50px;
        }
        ul{
          li{
            font-size: 14px;
            height: 40px;
            line-height: 40px;
          }
        }

      }
      .info-right-top {
        
      }
      .info-right-bot {
        margin-top: 10px;
       
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

      box-shadow: 0px 15px 30px rgba(0,0,0,.1);
    }
  }
</style>

<template>
  <div class="info-right">
    <div class="info-right-top">
      <h3>数据获取</h3>
      <ul>
        <li @click="register">注册</li>
        <li>申请</li>
        <li>批准</li>
        <li>获取</li>
      </ul>
    </div>
    <div class="info-right-bot">
      <h3>数据共享</h3>
      <ul>
        <li @click="login">登录</li>
        <li>描述</li>
        <li>发布</li>
        <li>等待获取申请</li>
      </ul>
    </div>


     <!-- 遮罩层 -->
    <!-- <div id="cover-box" v-show="$route.path != '/'" @click ="closeCover"> -->
      <!-- 登录框 -->
      <!-- <div class="login-box" v-if="loginOrRegister">登录框
        <button @click="submitLogin">登录</button>

        <el-button @click="closeCover">关闭</el-button>
      </div> -->

      <!-- 注册框 -->
      <!-- <div class="login-box" v-else>注册框
        <button @click="submitRegister">注册</button>

        <el-button @click="closeCover">关闭</el-button>
      </div>
    </div> -->
    
  </div>
</template>

<script>
export default {
  name:'info-right',
  data() {
    return {
      cover: false, // 遮罩层是否开启
      loginOrRegister: true,  // 显示登录框  还是注册框
    };
  },
  created(){
    // console.log(localStorage.cover)
    // this.cover = localStorage.cover;
  },
  mounted(){
    
  },
  methods:{
    login(){
      console.log('登录操作')
      this.cover = true;
    },
    register(){
      console.log('注册操作')
      this.cover = true;
      this.loginOrRegister = false;
    }, //提交登录和注册
    submitLogin(){
      console.log('提交登录')
    },
    submitRegister(){
      console.log('提交注册')
    },// 关闭遮罩层
    closeCover(){
      this.$router.push({
        name:'index',
        path:'/'
      });
      // this.cover = false;
      this.loginOrRegister = true;
    }
  }
};
</script>


