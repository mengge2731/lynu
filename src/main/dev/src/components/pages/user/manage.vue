
<style lang="less" scoped>
  .manage-con{
    width: 100%;
  }
</style>

<template>
 <div class="manage-con">
   用户管理页面
  
  <el-table
    :data="data"
    style="width: 100%">
    <el-table-column
      prop="userId"
      label="编号"
      width="180">
    </el-table-column>
    <el-table-column
      prop="phone"
      label="手机号"
      width="180">
    </el-table-column>
    <el-table-column
      prop="userName"
      label="姓名"
      width="180">
    </el-table-column>
    <el-table-column
      prop="userDesc"
      label="简介">
    </el-table-column>


    <el-table-column label="角色">
      <template slot-scope="scope">
        <el-select v-model="value" placeholder="请选择" size="small">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </template>
    </el-table-column>

     <el-table-column label="状态">
      <template slot-scope="scope">
        <el-select v-model="value" placeholder="请选择" size="small">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </template>
    </el-table-column>


  </el-table>

 </div>
</template>

<script>
  export default {
    methods: {
      handleEdit(index,row) {
        console.log(index)
      },
      handleDelete(index,row){
        console.log(index)
      },
      submitType(value){
        alert('触发了')
        console.log(value)
      }
    },

    data() {
      return {
        data: [
                  {
                      "createTime": "2018-02-11 16:24:36",
                      "msg": "",
                      "userDesc": "洛阳师范学院旅游学院老师，研究领域为意大利旅游经济情况构成。",
                      "msgExpired": null,
                      "nickName": "",
                      "password": "",
                      "phone": "13199998887",
                      "status": "0",
                      "updateTime": "2018-02-11 16:24:41",
                      "userId": 1,
                      "userName": "李明亮",
                      "userType": 1
                  },
                  {
                      "createTime": "2018-02-11 16:24:36",
                      "msg": "",
                      "userDesc": "洛阳师范学院旅游学院老师，研究领域为意大利旅游经济情况构成。",
                      "msgExpired": null,
                      "nickName": "",
                      "password": "",
                      "phone": "13199998887",
                      "status": "0",
                      "updateTime": "2018-02-11 16:24:41",
                      "userId": 25,
                      "userName": "赵大宝",
                      "userType": 2
                  },
                  {
                      "createTime": "2018-02-11 16:24:36",
                      "msg": "",
                      "userDesc": "洛阳师范学院旅游学院老师，研究领域为意大利旅游经济情况构成。",
                      "msgExpired": null,
                      "nickName": "",
                      "password": "",
                      "phone": "13199998887",
                      "status": "0",
                      "updateTime": "2018-02-11 16:24:41",
                      "userId": 78,
                      "userName": "宋明哲",
                      "userType": 2
                  },
                  {
                      "createTime": "2018-02-11 16:24:36",
                      "msg": "",
                      "userDesc": "洛阳师范学院旅游学院老师，研究领域为意大利旅游经济情况构成。",
                      "msgExpired": null,
                      "nickName": "",
                      "password": "",
                      "phone": "13199998887",
                      "status": "0",
                      "updateTime": "2018-02-11 16:24:41",
                      "userId": 33,
                      "userName": "刘海华",
                      "userType": 2
                  }
              ],
        userList:{
              "data": [
                  {
                      "createTime": "2018-02-11 16:24:36",
                      "msg": "",
                      "msgExpired": null,
                      "nickName": "",
                      "password": "",
                      "phone": "13199998887",
                      "status": "0",
                      "updateTime": "2018-02-11 16:24:41",
                      "userId": 1,
                      "userName": "李明亮",
                      "userType": 1
                  },
                  {
                      "createTime": "2018-02-11 16:24:36",
                      "msg": "",
                      "msgExpired": null,
                      "nickName": "",
                      "password": "",
                      "phone": "13199998887",
                      "status": "0",
                      "updateTime": "2018-02-11 16:24:41",
                      "userId": 25,
                      "userName": "赵大宝",
                      "userType": 2
                  }
              ],
              "endRow": 30,
              "firstPage": true,
              "index": 1,
              "lastPage": true,
              "middlePage": false,
              "nextPage": 25,
              "nextPageAvailable": false,
              "pageSize": 30,
              "previousPage": 1,
              "previousPageAvailable": false,
              "startRow": 0,
              "totalItem": 25,
              "totalPage": 1
          }
      }
    }
  }
</script>
