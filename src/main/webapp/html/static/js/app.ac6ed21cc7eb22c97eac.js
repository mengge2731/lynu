webpackJsonp([1],{"0kjs":function(t,e){},BR8l:function(t,e){},EcBu:function(t,e){},FxWc:function(t,e){},KK8q:function(t,e){},NHnr:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=a("7+uW"),n={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"view-container"}},[a("div",{staticClass:"header"},[a("div",{staticClass:"nav-list"},[a("ul",[a("li",[a("router-link",{attrs:{to:{name:"index",path:"/"}}},[t._v("首页")])],1),t._v(" "),a("li",[a("router-link",{attrs:{to:{name:"market",path:"/market"}}},[t._v("数据市场")])],1),t._v(" "),a("li",[a("router-link",{attrs:{to:{name:"share",path:"/share"}}},[t._v("数据共享")])],1),t._v(" "),a("li",[a("router-link",{attrs:{to:{name:"info",path:"/user/name"}}},[t._v("个人中心")])],1)])]),t._v(" "),t._m(0)]),t._v(" "),a("div",{staticClass:"content-wrap"},[a("router-view")],1),t._v(" "),t._m(1)])},staticRenderFns:[function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"banner"},[e("img",{attrs:{src:a("omhf"),alt:""}})])},function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"footer"},[e("p",[this._v("copyright @2018 洛阳师范学院")])])}]};var s={name:"App",components:{Layout:a("VU/8")({name:"Layout",data:function(){return{}},created:function(){},components:{}},n,!1,function(t){a("EcBu")},"data-v-f8bfad96",null).exports}},r={render:function(){var t=this.$createElement,e=this._self._c||t;return e("div",{attrs:{id:"app"}},[e("Layout")],1)},staticRenderFns:[]};var c=a("VU/8")(s,r,!1,function(t){a("0kjs")},"data-v-28b1e1cd",null).exports,l=a("/ocq"),o={data:function(){return{dataList:[]}},created:function(){this.$axios.post("/lynu/getPageData").then(function(t){console.log(t.data)}).catch(function(t){return console.log(t)})}},v={render:function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"content"},[e("div",{staticClass:"info-left"},[this._m(0),this._v(" "),e("div",{staticClass:"page-component"},[e("el-pagination",{attrs:{"current-page":this.currentPage4,"page-sizes":[100,200,300,400],"page-size":100,layout:"total, sizes, prev, pager, next, jumper",total:400},on:{"size-change":this.handleSizeChange,"current-change":this.handleCurrentChange}})],1)]),this._v(" "),this._m(1)])},staticRenderFns:[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("ul",{staticClass:"info-list"},[a("li",{staticClass:"info-list-item clearfix"},[a("div",{staticClass:"info-content"},[a("div",{staticClass:"info-content-left"},[a("div",{staticClass:"info-img"},[a("img",{attrs:{src:"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517767818040&di=ad8e9ca4c1b4ec96be5b13a9665795ec&imgtype=0&src=http%3A%2F%2Fk2.jsqq.net%2Fuploads%2Fallimg%2F1703%2F7_170331144403_4.jpg",alt:""}})]),t._v(" "),a("div",{staticClass:"info-text"},[a("h3",[t._v("意大利2017年经济情况报告-全部数据")]),t._v(" "),a("p",[t._v("意大利2017年金融、工业、旅游等主要经济类型各地金融工业旅游等主要经济类型各地")])])]),t._v(" "),a("div",{staticClass:"info-content-right"},[a("p",[t._v("数据量: 200GB")]),t._v(" "),a("p",[t._v("时间: 2018年1月29日")])])])])])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"info-right"},[a("div",{staticClass:"info-right-top"},[a("h3",[t._v("数据获取")]),t._v(" "),a("ul",[a("li",[t._v("注册")]),t._v(" "),a("li",[t._v("申请")]),t._v(" "),a("li",[t._v("批准")]),t._v(" "),a("li",[t._v("获取")])])]),t._v(" "),a("div",{staticClass:"info-right-bot"},[a("h3",[t._v("数据共享")]),t._v(" "),a("ul",[a("li",[t._v("登录")]),t._v(" "),a("li",[t._v("描述")]),t._v(" "),a("li",[t._v("发布")]),t._v(" "),a("li",[t._v("等待获取申请")])])])])}]};var _=a("VU/8")(o,v,!1,function(t){a("KK8q")},"data-v-9a57a7ca",null).exports,u={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("el-tabs",{staticStyle:{height:"100%"},attrs:{"tab-position":t.tabPosition}},[a("el-tab-pane",{attrs:{label:"个人信息"}},[t._v("个人信息")]),t._v(" "),a("el-tab-pane",{attrs:{label:"已共享数据"}},[t._v("已共享数据")]),t._v(" "),a("el-tab-pane",{attrs:{label:"已申请数据"}},[t._v("已申请数据")]),t._v(" "),a("el-tab-pane",{attrs:{label:"密码修改"}},[t._v("密码修改")])],1)],1)},staticRenderFns:[]};var f=a("VU/8")({data:function(){return{tabPosition:"left"}}},u,!1,function(t){a("rAEP")},"data-v-3dc53e9c",null).exports,d={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticStyle:{width:"100%"}},[a("div",[t._v("全部 旅游相关 文化相关 意大利相关")]),t._v(" "),a("div",{staticClass:"content"},[a("div",{staticClass:"info-left"},[t._m(0),t._v(" "),a("div",{staticClass:"page-component"},[a("el-pagination",{attrs:{"current-page":t.currentPage4,"page-sizes":[100,200,300,400],"page-size":100,layout:"total, sizes, prev, pager, next, jumper",total:400},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}})],1)]),t._v(" "),t._m(1)])])},staticRenderFns:[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("ul",{staticClass:"info-list"},[a("li",{staticClass:"info-list-item clearfix"},[a("div",{staticClass:"info-content"},[a("div",{staticClass:"info-content-left"},[a("div",{staticClass:"info-img"},[a("img",{attrs:{src:"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517767818040&di=ad8e9ca4c1b4ec96be5b13a9665795ec&imgtype=0&src=http%3A%2F%2Fk2.jsqq.net%2Fuploads%2Fallimg%2F1703%2F7_170331144403_4.jpg",alt:""}})]),t._v(" "),a("div",{staticClass:"info-text"},[a("h3",[t._v("意大利2017年经济情况报告-全部数据")]),t._v(" "),a("p",[t._v("意大利2017年金融、工业、旅游等主要经济类型各地金融工业旅游等主要经济类型各地")])])]),t._v(" "),a("div",{staticClass:"info-content-right"},[a("p",[t._v("数据量: 200GB")]),t._v(" "),a("p",[t._v("时间: 2018年1月29日")])])])])])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"info-right"},[a("div",{staticClass:"info-right-top"},[a("h3",[t._v("数据获取")]),t._v(" "),a("ul",[a("li",[t._v("注册")]),t._v(" "),a("li",[t._v("申请")]),t._v(" "),a("li",[t._v("批准")]),t._v(" "),a("li",[t._v("获取")])])]),t._v(" "),a("div",{staticClass:"info-right-bot"},[a("h3",[t._v("数据共享")]),t._v(" "),a("ul",[a("li",[t._v("登录")]),t._v(" "),a("li",[t._v("描述")]),t._v(" "),a("li",[t._v("发布")]),t._v(" "),a("li",[t._v("等待获取申请")])])])])}]};var p=a("VU/8")({data:function(){return{}}},d,!1,function(t){a("FxWc")},"data-v-2a2a5d0a",null).exports,h={render:function(){var t=this.$createElement;return(this._self._c||t)("div",[this._v("\n  数据共享-板块\n")])},staticRenderFns:[]};var m=a("VU/8")({data:function(){return{}}},h,!1,function(t){a("BR8l")},"data-v-dac95694",null).exports;i.default.use(l.a);var g=new l.a({routes:[{path:"/",name:"index",component:_},{path:"/user/info",name:"info",component:f},{path:"/market",name:"market",component:p},{path:"/share",name:"share",component:m}]}),C=a("zL8q"),b=a.n(C),F=(a("tvR6"),a("mtWM")),x=a.n(F);a("erWL");i.default.use(g),x.a.defaults.baseURL="http://rapapi.org/mockjsdata/31586",i.default.prototype.$axios=x.a,i.default.use(b.a),i.default.config.productionTip=!1,new i.default({el:"#app",router:g,components:{App:c},template:"<App/>"})},erWL:function(t,e){},omhf:function(t,e,a){t.exports=a.p+"static/img/banner.e660727.jpg"},rAEP:function(t,e){},tvR6:function(t,e){}},["NHnr"]);
//# sourceMappingURL=app.ac6ed21cc7eb22c97eac.js.map