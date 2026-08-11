<template>
  <div class="header"></div>
  <div class="container articlediv">
    <!-- 文章标题 -->
    <h4 class="post-title">{{itemList.title}}</h4>
    <p class="post-info">
      <!-- 文章发布时间 -->
      <span>发布于：{{itemList.creat}}</span>
      <!-- 文章作者 -->
      <span>作者：{{itemList.user.name}}</span>
      <!-- 文章流量信息 -->
<!--      <span class="glyphicon glyphicon-eye-open">浏览量：</span>-->
<!--      <span class="glyphicon glyphicon-heart">点赞：</span>-->
<!--      <span class="glyphicon glyphicon-comment">评论：</span>-->
    </p>
    <!-- 文章标签 -->
    <p class="post-tag">
<!--      <span class="label label-danger">Python</span>-->
<!--      <span class="label label-warning">OCR</span>-->
<!--      <span class="label label-info">Python模块介绍使用</span>-->
    </p>
    <hr>
    <div class="row">
      <div class="col-md-8">
        <!-- 文章内容 -->
        <div class="content">
          <p>{{itemList.content}}</p>
<!--          <p>安装命令：</p>-->
        </div>
        <hr>
        <!--添加文章评论功能-->
        <!--发布评论-->
        <h4 class="comment-group-title col-md-8">评论：</h4>
        <form>
          <div class="form-group">
            <input type="text" placeholder="请填写评论" class="form-control" v-model="commentContent">
            <input type="hidden" name="question_id" value="">
          </div>
          <div class="form-group" style="text-align: left;">
            <a class="btn btn-outline-primary rounded-pill"  @click="addCommentBtn()">评论</a>
<!--            <button class="btn btn-primary" @click="addCommentBtn()">评论</button>-->
          </div>
        </form>
        <!--所有评论展示-->
<!--        评论表内容-->
        <ul class="list-group" style="">
          <li class="list-group-item" v-for="item in commentList" :key="item.id">
            <div class="user-info">
              <!--<img class="avatar" src="" alt="">-->
              <span class="username">用户：{{item.user.name}}</span>&nbsp;&nbsp;
              <span class="create-time">发布时间：{{item.creat}}</span>
            </div>
            <p class="comment-content">{{item.content}}</p>
          </li>
        </ul>
      </div>

      <div class="col-md-4">
        <h4><b>获取更多文章</b></h4>
        <ul class="list-group" v-for="item in articleList" :key="item.id">
          <!-- 最新文章列表 -->
          <li class="list-group-item">
            <a class="nav-item" @click="otherBtn(item)" style="cursor:pointer">{{item.title}}</a>
          </li>
        </ul>
      </div>

      <nav class="blog-pagination" aria-label="Pagination">
        <a class="btn btn-outline-primary rounded-pill"  @click="gotoHomeOrManage()">返回</a>
      </nav>
    </div>
<!--    <a class="btn btn-outline-primary rounded-pill"  @click="router.push('/article')">返回</a>-->
  </div>
</template>

<script setup>
//引入生命周期函数
import {onMounted, reactive, ref} from "vue";
//引入路由
import {useRoute} from "vue-router";
import axios from "axios";
import router from "@/router";
import {useStore} from "@/stores";

//数据初始化
let itemList = ref([])

itemList.value = JSON.parse(history.state.data)
console.log("这是从主页传过来的数据")
console.log(itemList.value)

let uidNum = itemList.value.uid
let articleList = ref([])
let startNum = 0
let recordNum = 5
let aidNum = itemList.value.id
// 根据作者id查询更多文章
const getArticleByAuthorIdData = () =>{
  axios.request({
    method:'POST',
    url:'http://localhost:2223/api/article/articleAuthorId',
    params:{
      startNum: startNum,
      recordNum: recordNum,
      uidNum: uidNum,
    },
  }).then(response => {
    articleList.value = response.data
    // console.log(articleList.value)
  })
  return articleList
}
articleList = getArticleByAuthorIdData()
console.log('根据作者id查询更多文章')
console.log(articleList)
//根据文章id查询文章信息
const getArticleByTitleData = () =>{
  axios.request({
    method:'POST',
    url:'http://localhost:2223/api/article/articleTitle',
    params:{
      aidNum:aidNum,
    },
  }).then(response => {
    itemList.value = response.data[0]
    console.log("根据文章id查询文章信息")
    console.log(itemList.value)
  })
  return itemList
}

const otherBtn = (item) => {
  aidNum = item.id
  itemList = getArticleByTitleData()
}

const gotoHomeOrManage = () => {
  // 关闭当前页面
  // useTagsViewStore().delView(router.currentRoute.value);
  // 返回上一页面
  router.go(-1);

}


// 查询评论功能
let commentList = ref([])
const getCommentByData = () =>{
  axios.request({
    method:'POST',
    url:'http://localhost:2223/api/comment/commentList',
    params:{
      aidNum:aidNum,
    },
  }).then(response => {
    commentList.value = response.data
  })
  return commentList
}
commentList = getCommentByData()
console.log("这是评论内容")
console.log(commentList.value)


//实现评论的添加和查询功能
//添加评论功能
//评论内容
let commentContent = ref("")
//用户id
const store = useStore()
let uidNumLogin
//文章id aidNum
let addCommentFlag = ref()
//添加评论
const getCommentByAddData = () =>{
  uidNumLogin = store.auth.userInfo.userId
  axios.request({
    method:'POST',
    url:'http://localhost:2223/api/comment/commentInsert',
    params:{
      uidNumLogin: uidNumLogin,
      aidNum:aidNum,
      commentContent:commentContent.value
    },
  }).then(response => {
    addCommentFlag.value = response.data
  })
  return addCommentFlag
}
let addCommentBtn = () => {
  if(store.auth.userInfo === null){
    alert("请先登录博客！")
    router.push("/login")
  }else{
    addCommentFlag = getCommentByAddData()
    commentList = getCommentByData()
    console.log(addCommentFlag)
  }
}




</script>

<style scoped>
/* 博客文章信息为灰色 */
.post-info {
  color: gray;
}
.post-info span {
  margin-right: 15px;
}
.header{
  height:100px;
}
.articlediv{
  margin-bottom: -80px;
}
</style>