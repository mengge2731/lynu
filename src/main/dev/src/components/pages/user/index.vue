
<style lang="less" scoped>
.data-con{
  width: 100%;
  display: flex;
  .nav-left{
    width: 140px;
    // border: 2px solid red;
    ul {
      margin-top: 10px;
      li{
        a{
          display: block;
          width: 100%;
          text-align: center;
          font-size: 14px;
          height: 30px;
          line-height: 30px;
          margin-bottom: 10px;
          border: 1px solid #ccc;
          border-radius: 5px;
        }

         a:hover{
           display:block;
           height: 30px;
           background-color: red;
           color: #fff;
           border: 1px solid red;
         }
      }
    }
  }

  .right-content{
    flex: 1;
    overflow: scroll;
    // border: 2px solid red;
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
          <router-link :to="{name:'userApply'}">已申请数据</router-link>
        </li>
        <li>
          <router-link :to="{name:'password'}">密码修改</router-link>
        </li>

        <li v-show="admin">
          <router-link :to="{name:'infoManage'}">发布信息管理</router-link>
        </li>
        <li v-show="admin">
          <router-link :to="{name:'manage'}">用户管理</router-link>
        </li>
      </ul>
    </div>

    <div class="right-content">
      <router-view></router-view>
    </div>

  </div>

</template>

<script>
import { code } from '../../../util/util'
export default {
  data(){
    return {
      admin: false,
    }
  },
  created(){
    let that = this;
    this.$axios.post('/login/isLogin')
    .then( res => {
      // 如果成功就去获取 用户权限 0000
      if(res.data.code == code.success){

        this.$axios.post('/user/getUserInfo')
        .then( res => {
          if( res.data.code == code.success){
            if(res.data.userType == '2'){
              this.admin = true;
            }
          }
        })

      }else if(res.data.code == code.noLogin){
        this.$message({
            message: '未登录',
            type: 'info'
        });
        this.$router.push({ path: '/'});
      }
    })
  }
}
</script>



