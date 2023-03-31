<template>
    <div class="form-container">
        <label class="error">{{ errorMsg }}</label>
        <div class="username">
            <h3>Username</h3>
            <input v-model="username" />
        </div>
        <div class="password">
            <h3>Password</h3>
            <input v-model="password" />
        </div>
        <button v-if="props.isLogin" @click="login">Login</button>
        <button v-if="!props.isLogin" @click="signup" >Sign up</button>
    </div>
</template>

<script setup>
import { ref, defineProps } from 'vue'
import router from '@/router';
import { useUserStore } from '@/store';
import { postUser, postLogin } from '@/utils/restapi';
import sha256 from 'crypto-js/sha256'

const username = ref('')
const password = ref('')
const errorMsg = ref('')
const userStore = useUserStore()

const props = defineProps({
    isLogin: {
        type: Boolean,
        required: true
    }
})

async function login() {
    const user = {
        username: username.value,
        password: sha256(password.value).toString()
    }
    await postLogin(user).then(
        (response) => {
            if (response.status === 200) {
              userStore.login(response.data)
              router.push('/calculator')
            }
        }
    ).catch((error) => {
      if (error.response.status === 409){
        errorMsg.value = error.response.data.message
      }
    })
}

async function signup() {
  
  const user = {
    username: username.value,
    password: sha256(password.value).toString()
  }

  await postUser(user).then(
    (response) => {
      if (response.status === 200) {
        console.log('User registered')
        router.push('/')
      }
    }
  ).catch((error) => {
      if (error.response.status === 409){
        errorMsg.value = error.response.data
      }
  })
}

</script>

<style scoped>
.form-container {
    background-color: rgb(61, 61, 61);
    display: grid;
    grid-template-columns: 1fr;
    justify-content: center;
    align-items: center;
    text-align: center;
    width: 500px;
    height: 300px;
    border-radius: 20px;
}

.form-container button {
    height: 35px;
    width: 100px;
    border-radius: 8px;
    font-size: 18px;
    color: white;
    background-color: rgb(189, 27, 189);
    justify-self: center;
    cursor: pointer;
}

.form-container button:hover {
    transform: scale(1.2);
}

.login-container button:active {
    transform: scale(0.8);
}

.error {
    font-size: 22px;
    color: yellow;
    height: 25px;
}

.username,
.password {
    display: flex;
    height: 60px;
    width: 400px;
    align-items: center;
    justify-self: center;
    justify-content: space-between;
}

.username h3,
.password h3 {
    margin: 0;
    font-size: 22px;
    color: white;
}

.username input,
.password input {
    height: 25px;
    border-radius: 12px;
    width: 250px;
    font-size: 20px;
    font-weight: bolder;
    color: black;
}</style>
