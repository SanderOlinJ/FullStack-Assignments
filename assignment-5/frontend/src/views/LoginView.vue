<template>
    <div class="title">
        <h1>Log in</h1>
    </div>
    <UserForm :is-login="isLogin"/>
</template>

<script setup>
import UserForm from '@/components/UserForm.vue';
import { ref } from 'vue'

const isLogin = ref(true)



</script>

<style scoped>
.title {
    font-weight: bold;
    font-size: 22px;
    color: white;
    text-align: center;
}
</style>