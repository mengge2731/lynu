<style lang="less" scoped>
.header {
    height: 170px;
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

          <ul class="clearfix">
            <li @click="change(1)">
              <router-link :to="{ name:'index',path:'/'}" :class="{active: currentPage == 1 }">首页</router-link>
            </li>
            <li @click="change(2)">
              <router-link :to="{ name:'market', path:'/market'  }" :class="{active: currentPage == 2}">数据市场</router-link>
            </li>
            <li @click="change(3)">
              <router-link :to="{ name:'center',path:'/center' }" :class="{active: currentPage == 3}">数据共享</router-link>
            </li>
            <li @click="change(4)">
              <router-link :to="{name:'info',path:'/user/info' }" :class="{active: currentPage == 4}">个人中心</router-link>
            </li>
          </ul>
        </div>

        <div class="banner"> 
          <img src="../../assets/img/banner.jpg" alt="">
        </div>
    </div>


</template>

<script>
export default {
  name: "nav-header",
  data() {
    return {
      currentPage: 1, // 1首页  2数据市场 3数据共享  4 个人中心  [要变成 全局的变量]
    };
  },
  created(){

  },
  methods: {
    change(index){
      if(index == 1){
        this.currentPage = 1;
      }else if(index == 2){
        this.currentPage = 2;
      }else if(index == 3){
        this.currentPage = 3;
      }else if(index == 4){
        this.currentPage = 4;
      }
    }
  }
};
</script>

