<template>
  <div id="app">
    <NavBar />
    <RouterView />
  </div>
</template>

<script setup>
import NavBar from './components/NavBar.vue';

</script>

<style>
html{
    box-sizing: border-box;
    height: 100vh;
}

body{
    align-items: center;
    background: linear-gradient(109.6deg, rgb(78, 62, 255) 11.2%, rgb(164, 69, 255) 91.1%);
    display: grid;
    height: inherit;
    justify-content: center;
}

.spacing{
  width: 50px;
}
</style>
