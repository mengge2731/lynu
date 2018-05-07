
<style lang="less" scoped>
.data-con{
  width: 100%;
  margin-top:10px;
  display: flex;
  .nav-left{
    width: 120px;
    padding: 10px;

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
           background-color: #dceae3;
           color: #0c6e3d;
           border: 1px solid #0c6e3d;
         }
      }
    }
  }

  .active{
    display:block;
    height: 30px;
    background-color: #0c6e3d;
    color: #fff;
    border: 1px solid #0c6e3d;
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
        <li @click="checkNav(1)">
          <router-link :to="{name:'info'}" :class="{active: selectNav == 1 }" >个人信息</router-link>
        </li>
        <li  @click="checkNav(2)">
          <router-link :to="{name:'share' }" :class="{active: selectNav == 2 }">已共享数据</router-link>
        </li>
        <li @click="checkNav(3)">
          <router-link :to="{name:'userApply'}" :class="{active: selectNav == 3 }" >已申请数据</router-link>
        </li>
        <li  @click="checkNav(4)">
          <router-link :to="{name:'password'}" :class="{active: selectNav == 4 }">密码修改</router-link>
        </li>

        <li v-if="admin" @click="checkNav(5)">
          <router-link :to="{name:'infoManage'}" :class="{active: selectNav == 5 }" >发布信息管理</router-link>
        </li>
        <li v-if="admin" @click="checkNav(6)">
          <router-link :to="{name:'manage'}" :class="{active: selectNav == 6 }" >用户管理</router-link>
        </li>

        <li  @click="loginOut">
          <a href="javascript:void(0);">退出登录</a>
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
      selectNav: 1, // 默认选择个人信息
    }
  },
  methods:{
    checkNav(key){
      console.log(key)
      this.selectNav = key;
    },
    loginOut(){
      this.$confirm('确定要退出当前账号吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
          }).then(() => {
            this.$axios.post('/login/isLogin')
        .then( res => {
          // 如果成功就去获取 用户权限 0000
          if(res.data.code == code.login){
            // 请求退出登陆接口
            this.$axios.post('/login/logout')
            .then( res => {
              if( res.data.code == '0000'){
                this.$message({
                  type: 'success',
                  message: '退出成功!'
                });
                this.$router.push({ path: '/'});
              }else {
                // 如果请求失败，提示
                this.$message({
                    message: '网络错误，请重试',
                    type: 'info'
                });
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


          
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消退出'
          });          
        });
      
    }
  },
  created(){
    // switch (this.$route.path) {
    //   case '/user/info' :
    //     this.selectNav = 1;
    //     break;
    //   case  '/market':
    //     this.selectNav = 2;
    //     break;
    //   case '/center':
    //     this.selectNav = 3;
    //     break;
    //   case '/center':
    //     this.selectNav = 4;
    //     break;
    //   case '/center':
    //     this.selectNav = 5;
    //     break;
    //   default:
    //     this.selectNav = 6;
    //     break;
    // }

    let that = this;
    this.$axios.post('/login/isLogin')
    .then( res => {
      // 如果成功就去获取 用户权限 0000
      if(res.data.code == code.login){

        this.$axios.post('/user/getUserInfo')
        .then( res => {
          if( res.data.code == code.success){
            if(res.data.data.userType == '2'){
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



