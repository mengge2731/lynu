<style lang="less" scoped>
.header {
    height: 180px;
    .nav-list {
      height: 50px;
      min-height: 50px;
      line-height: 50px;
      float: right;

      ul {
          border: 1px solid #ccc;
          border-radius: 5px;
          margin-top:10px;
          background-color: #fafafa;
          line-height: 30px;
        li{
          float: left;
          text-align: center;
          a{
            display: block;
            height: 30px;
            
            font-size: 16px;
            font-weight: 700;
            padding: 0 20px;
          }

          a:hover{
            color: #fff;
            border-radius: 5px;
            background-color: rgb(243, 152, 152);
          }
        }
      }
    }
    .banner {
      height: 120px;
      width: 100%;
      overflow: hidden;
      img {
        width: 100%;
      }
    }
  }
  // 选中状态
  .active{
    color: #fff;
    border-radius: 5px;
    background-color: red;
  }
</style>
<template>
  <div class="header">
        <div class="nav-list">
          
          <el-radio-group v-model="type" size="medium" @change="check">
            <el-radio-button label="1" >首页</el-radio-button>
            <el-radio-button label="2">数据市场</el-radio-button>
            <el-radio-button label="3">数据共享</el-radio-button>
            <el-radio-button label="4">个人中心</el-radio-button>
          </el-radio-group>

        </div>
        <!-- <div class="banner"> 
          <img src="../../assets/img/banner.png" alt="">
        </div> -->

        <login-box :loginbox="loginbox" ></login-box>
    </div>
</template>

<script>
import loginBox from './loginBox';
import { code } from '../../util/util'
export default {
  name: "nav-header",
  data() {
    return {
      type:1,
      currentPage: 1, // 1首页  2数据市场 3数据共享  4 个人中心  [要变成 全局的变量]
      loginbox: {
        cover: false, // 遮罩层是否开启
        loginOrRegister: true,  // 显示登录框  还是注册框
      },
    };
  },
  components:{
    loginBox,
  },
  created(){
    switch (this.$route.path) {
      case '/' :
        this.type = 1;
        break;
      case  '/market':
        this.type = 2;
        break;
      case '/center':
        this.type = 3;
        break;
      default:
        this.type = 4;
        break;
    }
  },
  mounted(){
    switch (this.$route.path) {
      case '/' :
        this.type = 1;
        break;
      case  '/market':
        this.type = 2;
        break;
      case '/center':
        this.type = 3;
        break;
      default:
        this.type = 4;
        break;
    }
  }, 
  methods: {
    change(index){
      if(index == 1){
        this.currentPage = 1;
        this.$router.push({
          path:'/'
        })
      }else {
         this.$axios.post('/login/isLogin')
        .then( res => {
          if(res.data.code == code.login){
            if(index == 2){
              this.currentPage = 2;
              this.$router.push({
                path:'/market'
              })
            }else if(index == 3){
              this.currentPage = 3;
              this.$router.push({
                path:'/center'
              })
            }else if(index == 4){
              this.currentPage = 4;
              this.$router.push({
                path:'/user/info'
              })
            }
          }else {
            // 弹出登录框
            // 传入登录还是注册
            this.loginbox.cover = true; // 遮罩层是否开启
            this.loginbox.loginOrRegister = true;  // 显示登录框  还是注册框
          }
        })
      }
    },
    check(){
      // 跳转路由
      if(this.type == 1){
        this.currentPage = 1;
        this.$router.push({
          path:'/'
        })
      }else {
         this.$axios.post('/login/isLogin')
        .then( res => {
          if(res.data.code == code.login){
            if(this.type == 2){
              this.currentPage = 2;
              this.$router.push({
                path:'/market'
              })
            }else if(this.type == 3){
              this.currentPage = 3;
              this.$router.push({
                path:'/center'
              })
            }else if(this.type == 4){
              this.currentPage = 4;
              this.$router.push({
                path:'/user/info'
              })
            }
          }else {
            this.type = 1;
            // 弹出登录框
            // 传入登录还是注册
            this.loginbox.cover = true; // 遮罩层是否开启
            this.loginbox.loginOrRegister = true;  // 显示登录框  还是注册框
          }
        })
      }
    }
    
  },
  
};
</script>

