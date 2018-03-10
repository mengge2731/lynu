
<style lang="less" scoped>
  .manage-con{
    width: 840px;
    padding: 10px 0 0 20px;
  }

 
</style>

<template>
 <div class="manage-con">
  
     <el-table
      :data="userList.data"
      style="width: 100%">
      <el-table-column
        prop="userId"
        label="编号"
        width="100">
      </el-table-column>

      <el-table-column
        prop="phone"
        label="手机号"
        width="140">
      </el-table-column>

      <el-table-column
        prop="userName"
        label="姓名"
        width="100">
      </el-table-column>
      
      <el-table-column
        prop="userDesc"
        label="简介"
        width="280">
      </el-table-column>

      <el-table-column label="角色"  width="100">
        <template slot-scope="scope">
          <el-select v-model="scope.row.userType" size="mini">
            <el-option
              v-for="item in typeOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </template>
      </el-table-column>

      <el-table-column label="状态"  width="100" :model="userList.data">
        <template slot-scope="scope">
          <el-select v-model="scope.row.status" size="mini">
            
            <el-option
              v-for="item in statusOptions"
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
    data() {
      return {
        userList:{
          "data": [
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
                  "status": "1",
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
        },
        typeOptions: [{
          value: 1,
          label: '管理员'
        }, {
          value: 2,
          label: '普通用户'
        }],
        statusOptions:[{
          value: '0',
          label: '正常'
        },{
          value: '1',
          label: '停用'
        }],
        userType:'',
        status:''
        
        
      }
    },
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

    
  }
</script>
