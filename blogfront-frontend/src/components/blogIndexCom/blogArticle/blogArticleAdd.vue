<template>
  <header class="header"></header>
  <div class="container div1" style="margin-top: 36px;">
    <form></form>
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
    <button type="button" class="btn btn-primary" @click="submitBtn()">提交</button>
    <button type="button" class="btn btn-primary">清空</button>
    <button type="button" class="btn btn-primary" @click="router.go(-1)">返回</button>
  </div>
</template>

<script setup>
import router from "@/router";
import {ref} from "vue";
import axios from "axios";
import {useStore} from "@/stores";
let title = ref("")
let category = ref("")
let content = ref("")
let flag = 0

//第二种方式获取用户id
const store = useStore()
let account
if(store.auth.userInfo != null){
  account = store.auth.userInfo.userId
}
console.log("这是用户id")
console.log(store.auth.userInfo.userId)
console.log(account)

// const store = useStore()
// let account = ""
// if(store.auth.user.userAccountUserName!=null){
//   account = store.auth.user.userAccountUserName
// }
// console.log("这是登录信息")
// console.log(account)
//
//
// const getUserIdData = () => {
//   let responseData
//   axios.request({
//     method:'POST',
//     url:'http://localhost:2223/api/user/userAccount',
//     params:{
//       account:account
//     },
//   }).then(response =>{
//     responseData = response.data
//   })
//   return responseData
// }
//
// let findAccountId = getUserIdData()
// console.log("这是博客用户id")
// console.log(findAccountId)

const getArticleByAddData = () =>{
  axios.request({
    method:'POST',
    url:'http://localhost:2223/api/article/articleInsert',
    params:{
      uid:account,
      title:title.value,
      category:category.value,
      content:content.value
    },
  }).then(response =>{
    flag = response.data
    if(flag){
      alert("发布成功")
      router.push('/articleAdd')
      title.value = ""
      category.value = ""
      content.value = ""
    }else{
      alert("发布失败")
    }
  })
}
const submitBtn = () => {
  if(title.value && category.value && content.value){
    getArticleByAddData()
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