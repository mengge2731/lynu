<template>
  <div id="view-container">
    <div class="header">
        <div class="nav-list">
          <ul>
            <li>
              <router-link :to="{ name:'index',path:'/'}">首页</router-link>
            </li>
            <li>
              <router-link :to="{ name:'market', path:'/market'}">数据市场</router-link>
            </li>
            <li>
              <router-link :to="{ name:'share',path:'/share'}">数据共享</router-link>
            </li>
            <li>
              <router-link :to="{name:'info',path:'/user/name'}">个人中心</router-link>
            </li>
          </ul>
        </div>

        <div class="banner"> 
          <img src="../../assets/img/banner.jpg" alt="">
        </div>
    </div>
    <div class="content-wrap">
      <router-view></router-view>
    </div>
    

    <div class="footer">
      <p>copyright @2018 洛阳师范学院</p>
    </div>
    
  </div>
</template>
<script>
export default {
  name: "Layout",
  data() {
    return {};
  },
  created() {},
  components: {}
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="less" scoped>
#view-container {
  height: 100%;
  display: flex;
  width: 1000px;
  justify-content: center;
  flex-direction: column;
  margin: 0 auto;

  // min-height: 900px;

  .header {
    // background-color: #fff;
    // flex: 4;
    height: 170px;
    .nav-list {
      height: 50px;
      min-height: 50px;
      line-height: 50px;
      float: right;

      ul {
        li{
          float: left;
          text-align: center;
          a{
            display: block;
            height: 50px;
            font-size: 16px;
            font-weight: 700;
            line-height: 50px;
            padding: 0 20px;
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
  .footer {
    // flex: 1;
    height: 50px;
    // background-color: #fff;
    text-align: center;
    p {
      font-size: 14px;
      color: #999;
      line-height: 50px;
    }
  }
  
}

.content-wrap {
    display: flex;
    flex: 1;
    min-height: 500px;
    // background-color: #fff;
    border-bottom: 1px dashed #ccc;
}
</style>
