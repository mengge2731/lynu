<style lang="less" scoped>
#view-container {
  height: 100%;
  display: flex;
  width: 1000px;
  justify-content: center;
  flex-direction: column;
  margin: 0 auto;
  // min-height: 900px;
  
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
    border-bottom: 1px dashed #ccc;
}
</style>
<template>
  <div id="view-container">
    <nav-header></nav-header>
    <div class="content-wrap">
      <router-view></router-view>
    </div>
    <div class="footer">
      <p>copyright @2018 洛阳师范学院</p>
    </div>
  </div>
</template>
<script>
import navHeader from './header.vue'
export default {
  name: "Layout",
  data() {
    return {
    };
  },
  created() {},
  components: {
    navHeader,
  },
  methods:{
  }
};
</script>