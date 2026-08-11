<template>
  <div style="position: absolute;">
    <iframe :src="htmlFilePath" style="width:100vw;height:87vh;"></iframe>
  </div>
</template>

<script setup>
import {useStore} from "@/stores";

const store = useStore();
store.auth.index = 3
console.log()
const htmlFilePath= 'src/assets/img/blog-helpDocument/helpDocument.html';

</script>
<style>

</style>