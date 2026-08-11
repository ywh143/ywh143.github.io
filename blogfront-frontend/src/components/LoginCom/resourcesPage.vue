<template>
  <div style="padding: 10px">
   商品名称： <el-input v-model="inputspmc" placeholder="inputspmc"></el-input>
    销售记录： <el-input v-model="inputxsjl" placeholder="spmc"></el-input>
    购买价： <el-input v-model="inputgmj" placeholder="inputgmj"></el-input>


    <el-button @click="pay(111)" type="primary" size="small">支付</el-button>
    <el-button @click="notify()" type="primary" size="small">回调</el-button>
  </div>
</template>

<script>
import {get,post} from "@/net";
import {ElMessage} from "element-plus";
import router from "@/router";
import {ref} from "vue";

export default {
  name: 'Orders',
  data() {
    return {
      inputspmc:ref(0),
      inputxsjl:ref(0),
      inputgmj:ref(0),
      tableData: []
    }
  },
  methods: {
    pay(row) {
      window.open("http://localhost:2223/pc_pay?subject=" + 123123 + "&traceNo=" + 11111 + "&totalAmount=" + 0.31)
      ElMessage.success("请求支付宝成功")
    }
  }
}
</script>









