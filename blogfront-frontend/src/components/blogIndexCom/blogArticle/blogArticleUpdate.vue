<template>
  <header class="header"></header>
  <div class="container div1" style="margin-top: 36px;">
    <form>
      <div class="form-group">
        <label for="title">博客标题<br></label>
        <input type="text" class="form-control" id="title" placeholder="Enter Title" v-model="title">
      </div>
      <div class="form-group">
        <label for="title" >博客分类</label>
        <input type="text" class="form-control" id="title" placeholder="Enter Category" v-model="category">
      </div>
      <div class="form-group">
        <label for="content">博客内容</label>
        <textarea class="form-control" id="content" rows="10" placeholder="请输入博客内容......" v-model="content"></textarea>
      </div>
      <button type="button" class="btn btn-primary" @click="submitBtn()">更新</button>
      <button type="reset" class="btn btn-primary">清空</button>
      <button type="button" class="btn btn-primary" @click="router.push('articleManagement')">返回</button>
    </form>
  </div>
</template>

<script setup>
import router from "@/router";
import {ref} from "vue";
import axios from "axios";
import {useRoute} from "vue-router";
import {useStore} from "@/stores";
//数据初始化
//定义路由
// const route = useRoute()
let itemList = ref([])
itemList.value = JSON.parse(history.state.data)
console.log("这是从主页传过来的数据")
console.log(itemList.value)
if(!itemList){
  router.push('/articleManagement')
}

let aidNum = itemList.value.id
let title = ref("")
let category = ref("")
let content = ref("")
// let findAccountId
let flag = 0

// const store = useStore()
// let account = ""
// if(store.auth.user.userAccountUserName!=null){
//   account = store.auth.user.userAccountUserName
// }

title = itemList.value.title
category = itemList.value.category
content = itemList.value.content

//通过账户名获取id
// const getUserIdData = () => {
//   axios.request({
//     method:'POST',
//     url:'http://localhost:2223/api/user/userAccount',
//     params:{
//       account:account
//     },
//   }).then(response =>{
//     findAccountId = response.data
//   })
//   return findAccountId
// }
//
// findAccountId = getUserIdData()

//实现博客修改功能
const getArticleByUpdateData = () =>{
  axios.request({
    method:'POST',
    url:'http://localhost:2223/api/article/articleUpdate',
    params:{
      // uid:findAccountId,
      aidNum:aidNum,
      title:title,
      category:category,
      content:content
    },
  }).then(response =>{
    flag = response.data
    if(flag){
      alert("更新成功")
      router.push('/articleUpdate')
      // title.value = ""
      // category.value = ""
      // content.value = ""
    }else{
      alert("更新失败")
    }
  })
}
const submitBtn = () => {
  if(aidNum.value && title.value && category.value && content.value){
    getArticleByUpdateData()
  }else{
    alert('请输入标题、分类和内容')
  }
}
</script>

<style scoped>
.header{
  height:60px;
}
.div1{
  margin-bottom: -110px;
}
</style>