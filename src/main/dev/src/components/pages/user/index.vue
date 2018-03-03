
<style lang="less" scoped>
.data-con{
  width: 100%;
  display: flex;
  .nav-left{
    width: 140px;
    border: 2px solid red;
  }

  .right-content{
    flex: 1;
    border: 2px solid red;
  }

}
  
</style>

<template>
  <div class="data-con">
    
    <div class="nav-left">
      <ul>
        <li>
          <router-link :to="{name:'info'}">个人信息</router-link>
        </li>
        <li>
          <router-link :to="{name:'share' }">已共享数据</router-link>
        </li>
        <li>
          <router-link :to="{name:'apply'}">已申请数据</router-link>
        </li>
        <li>
          <router-link :to="{name:'password'}">密码修改</router-link>
        </li>
      </ul>
    </div>

    <div class="right-content">
      右侧子路由区域
      <router-view></router-view>
    </div>


  </div>

</template>

<script>
export default {
  data(){
    return {
    }
  }
}
</script>



