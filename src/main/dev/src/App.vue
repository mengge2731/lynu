<template>
  <div id="app">
    <Layout></Layout>
  </div>
</template>

<script>
import Layout from "./components/common/layout.vue";
export default {
  name: "App",
  data(){
    return {
    }
  },
  components: {
    Layout
  },
  methods:{
  }
  ,
  mounted(){
    
  }
};
</script>

<style lang="less" scoped>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /* text-align: center; */
  color: #2c3e50;
  min-width: 1000px;
  height: 100%;
  min-height: 720px;
  background: url('//120.27.37.129:8080/lynu/img/bg-1.jpg') no-repeat center top;
}

</style>
