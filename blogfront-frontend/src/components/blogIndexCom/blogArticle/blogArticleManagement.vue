<template>
  <header class="header">
  </header>
  <div class="row" style="margin-bottom: -120px;">
    <div class="col"></div>
    <div class="col-10">
      <h3 style="text-align: center;">博客管理</h3>
      <table border="1" class="table table-striped">
        <thead>
        <tr>
          <th>博客id</th>
          <th>博客标题</th>
          <th>博客内容</th>
          <th>博客分类</th>
          <th>博客创建时间</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in articleList" :key="item.id">
          <td>{{item.id}}</td>
          <td>{{item.title}}</td>
          <td>{{item.content}}</td>
          <td>{{item.category}}</td>
          <td>{{item.creat}}</td>
          <td>
            <div class="form-group" style="float:left;display:flex;width:100%">
              <a href="#" @click="gotoArticleUpdate(item)"><input class="form-control"  type="button" value="更新"/></a>
              <a href="#" @click="deleteBtn(item)"><input class="form-control"  type="button" value="删除"/></a>
              <a href="#" @click="gotoArticleDetail(item)"><input class="form-control"  type="button" value="查看"/></a>
            </div>
          </td>
        </tr>
        </tbody>
      </table>

<!--      <nav class="blog-pagination" aria-label="Pagination">-->
<!--        <ul class="pagination">-->
<!--          <a class="btn btn-outline-primary rounded-pill"  @click="subCount()">上一页</a>-->
<!--          <a class="btn btn-outline-primary rounded-pill" @click="addCount()">下一页</a>-->
<!--          <a class="btn btn-outline-primary rounded-pill"  @click="router.push('/article')">返回</a>-->
<!--        </ul>-->
<!--      </nav>-->

      <ul class="pagination">
        <li class="page-item"><a class="page-link" @click="subCount()" style="cursor:pointer">上一页</a></li>
        <li class="page-item"><a class="page-link" @click="numBtn1()" style="cursor:pointer">{{num1}}</a></li>
        <li class="page-item"><a class="page-link" @click="numBtn2()" style="cursor:pointer">{{num2}}</a></li>
        <li class="page-item"><a class="page-link" @click="numBtn3()" style="cursor:pointer">{{num3}}</a></li>
        <li class="page-item"><a class="page-link" href="#">...</a></li>
        <li class="page-item"><a class="page-link" @click="numBtn4()" style="cursor:pointer">尾页</a></li>
        <li class="page-item"><a class="page-link" @click="addCount()" style="cursor:pointer">下一页</a></li>
        <li class="page-item"><a class="page-link" @click="router.push('/article')" style="cursor:pointer">返回</a></li>
      </ul>
    </div>
    <div class="col"></div>
  </div>
</template>

<script setup>
import router from "@/router";
import {useStore} from "@/stores";
import {get} from "@/net";
import {ElMessage} from "element-plus";
import {Plus, Search} from "@element-plus/icons-vue";
import {onBeforeMount, onMounted, reactive, ref} from "vue";
import axios from "axios";

//数据初始化
let count = 1;
let recordNum = 8;
let startNum = 8 * (count-1);
let articleList = ref([])

let category = ""
let selectNum = 0

let keyString = ""
let countNum =""

//分页功能数据初始化
let num1 = 1
let num2 = 2
let num3 = 3

//第二种方式获取用户id
const store = useStore()
let account = ""
account = store.auth.userInfo.userId
// if(store.auth.userInfo != null){
//   account = store.auth.userInfo.userId
// }else{
//   alert("请先登录博客！")
//   router.push("/login")
// }
console.log("这是用户id")
console.log(account)


//博客文章分页功能实现
const getData = () =>{
  axios.request({
    method:'POST',
    url:'http://localhost:2223/api/article/articleManage',
    params:{
      uidNum: account,
      startNum: startNum,
      recordNum: recordNum,
    },
  }).then(response => {
    articleList.value = response.data
    console.log("这是数据")
    console.log(response.data)
  })
  return articleList
}
articleList = getData()


//获取总行数
const getCountNum = () => {
  axios.request({
    method:'POST',
    url:'http://localhost:2223/api/article/articleCount',
  }).then(response => {
    countNum = response.data
    console.log(countNum)
  })
  return countNum
}
countNum = getCountNum()

//向下翻页
const addCount = () =>{
  if(selectNum === 0){
    if((count * 8) < countNum){
      count++;
      startNum = 8 * (count-1);
      articleList = getData(startNum,recordNum)
    }else if((count * 8) >= countNum){
      alert("已经是最后一页了")
    }

  }else if(selectNum === 1){
    if((count * 8) < countNum){
      count++;
      startNum = 8 * (count-1);
      articleList = getCategoryData(startNum,recordNum,category)
    }else if((count * 8) >= countNum){
      alter("已经是最后一页了")
    }

  }else if(selectNum === 2){
    if((count * 8) < countNum){
      count++;
      startNum = 8 * (count-1);
      articleList = getSearchData(startNum,recordNum,keyString)
    }else if((count * 8) >= countNum){
      alter("已经是最后一页了")
    }
  }
}
//指定跳转页面
const numBtn1 = () => {
  count = 1
  startNum = 8 * (count-1)
  articleList = getData(startNum,recordNum)
}
const numBtn2 = () => {
  count = 2
  startNum = 8 * (count-1)
  articleList = getData(startNum,recordNum)
}
const numBtn3 = () => {
  count = 3
  startNum = 8 * (count-1)
  articleList = getData(startNum,recordNum)
}
const numBtn4 = () => {
  count = (countNum / 8)
  startNum = 8 * (count-1)
  articleList = getData(startNum,recordNum)
}


//向上翻页
const subCount = () =>{
  if(selectNum === 0){
    if(count>=2){
      count--;
      startNum = 8 * (count-1);
      articleList = getData(startNum,recordNum)
    }else{
      alert("已经在第一页了")
    }
  }else if(selectNum === 1){
    if(count >= 2){
      count--;
      startNum = 8 * (count-1);
      articleList = getCategoryData(startNum,recordNum,category)
    }
  }else if(selectNum === 2){
    if(count >= 2){
      count--;
      startNum = 8 * (count-1);
      articleList = getSearchData(startNum,recordNum,keyString)
    }
  }
}



//跳转到文章详情页
const gotoArticleDetail = (item) => {
  router.push({
    //指定跳转目标页面
    path:'/articleDetail',
    //传递的参数
    state:{
      data:JSON.stringify(item)
    }
  })
}

//跳转到文章修改页面
const gotoArticleUpdate = (item) => {
  router.push({
    //指定跳转目标页面
    path:'/articleUpdate',
    //传递的参数
    state:{
      data:JSON.stringify(item)
    }
  })
}

//实现博客删除功能
let flag = 0
let aidNum = 0
const getArticleByDeleteData = () =>{
  axios.request({
    method:'POST',
    url:'http://localhost:2223/api/article/articleDelete',
    params:{
      aidNum:aidNum,
    },
  }).then(response =>{
    flag = response.data
    if(flag){
      alert("删除成功")
      articleList = getData(startNum,recordNum)
    }else{
      alert("删除失败")
    }
  })
}
const deleteBtn = (item) => {
  aidNum = item.id
  console.log(item.id)
  getArticleByDeleteData()
}

</script>

<style scoped>
.header{
  margin-top: 100px;
}
.table {
  table-layout:fixed;
  word-break:break-all;
}
.table td:hover{
  overflow: visible;
  white-space: normal;
}
.table td {
  word-wrap: break-word;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
</style>