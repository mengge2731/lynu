<template>
  <div class="body-header">
    头部内容
  </div>
</template>

<script>
export default {
  name: "nav-header",
  data() {
    return {};
  },
  methods: {}
};
</script>

<style lang="less" scoped>
/* 外部大框 */
.body-header {
  height: 46px;
  width: 100%;
  background-color: #fff;
  box-shadow: 14px 0 rgba(33, 41, 50, 0.05);
  border-bottom: 1px solid #edf0f4;
  /*border:1px solid red;*/
}

/* 主体 */
.admin-opt {
  height: 100%;
  float: right;
  font-size: 14px;
  color: #6c7d8e;
  line-height: 46px;
  padding-right: 20px;
  /*border:1px solid blue;*/
}
/* 田字框 */
.Header-not {
  /*border:1px solid yellow;*/
  float: left;
  margin-left: 20px;
}
.iconfont {
  width: 20px;
  height: 20px;
}
.Header-hp {
  color: #6c7d8e;
}
.Header-hp:hover {
  color: #0c9aff;
}
/* 用户 */
.Header-portrait {
  /*width:100%;*/
  /*border:1px solid deeppink;*/
  float: left;
}
.Header-user {
  /*height:298px;*/
  /*border:1px solid green;*/
  float: left;
}
.Header-hr {
  font-size: 13px;
  font-family: PingFangSC-Regular;
  color: #6c7d8e;
  margin-left: 5px;
}
.Header-hr:hover {
  color: #0c9aff;
}
/* 弹出框 */
.Header-detail {
  width: 315px;
}
.Header-detailone {
  width: 90px;
}
.Header-photo {
  font-size: 12px;
  color: #23282a;
  line-height: 17px;
  display: block;
  text-align: center;
}
Header-portrait {
  width: 60px;
  height: 60px;
  margin-top: 34px;
}
.Header-img {
  width: 60px;
  height: 60px;
  padding-top: 30px;
  padding-left: 30px;
}
.Header-usernm {
  float: left;
  width: 200px;
  margin-left: 10px;
  /*border:1px solid green;*/
}
.Header-nm {
  margin-top: 30px;
  font-size: 14px;
  font-family: PingFangSC-Regular;
  color: #34495e;
  line-height: 14px;
  /*border:1px solid red;*/
}
.Header-include {
  width: 100%;
  /*border:1px solid orangered;*/
  float: left;
  margin-top: -6px;
}
.Header-includeone {
  width: 100%;
  /*border:1px solid orangered;*/
  float: left;
  margin-top: -1px;
}
.Header-dm {
  font-size: 12px;
  font-family: PingFangSC-Regular;
  color: #758896;
}
.Header-sf {
  font-size: 12px;
  font-family: PingFangSC-Regular;
  color: #34495e;
}
/* 弹出框 底部按钮 */
.Header-btn {
  width: 280px;
  float: left;
  /*border:1px solid red;*/
  /*margin:0 auto;*/
  margin-top: 20px;
  margin-bottom: 16px;
  margin-left: 18px;
}
.Header-btn-one {
  width: 76px;
  float: left;
  padding-left: 8px;
  padding-right: 8px;
  /*border:1px solid deeppink;*/
  border-radius: 3px;
}
.Header-btn-one:hover {
  background: #0c9aff;
  color: #fff;
}
.Header-btn-two {
  width: 66px;
  float: left;
  /*border:1px solid deeppink;*/
  margin-left: 8px;
  border-radius: 3px;
}
.Header-btn-two:hover {
  background: #0c9aff;
}
.Header-btn-text {
  font-size: 12px;
  font-family: PingFangSC-Regular;
  color: #34495e;
  line-height: 12px;
}
.Header-btn-text:hover {
  color: #fff;
}
.dg-admin-opt {
  i {
    color: #939ca2;
    font-size: 12px;
  }
}
.dg-logout-opt {
  span {
    color: #fff;
  }
  i {
    color: #fff;
    font-size: 14px;
    vertical-align: bottom;
    margin-right: -2px;
  }
}
.ecsc-button-m {
  padding: 0 8px;
  span {
    display: inline-block;
    height: 28px;
    line-height: 28px;
  }
}

/* 2017-11-07 折叠导航 按钮样式 */

.isCollapse-button {
  float: left;
  margin-top: 12px;
  margin-left: 18px;
}
.el-button--primary {
  width: 22px;
  height: 20px;
  padding: 0;
}

/*消息提醒*/
.el-badge__content.is-fixed {
  top: 10px;
  font-size: 6px;
}
</style>
