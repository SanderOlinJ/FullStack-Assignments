<template>
    <div id="container">
        <div id="history-container">
            <div class="history-div">
                <h1>Calculator History</h1>
            </div>

            <div class="history-div" v-for="(equation, index) in equationsHistory" :key="index">
                <p>
                    {{ equation }}
                </p>
            </div>
        </div>
        <button id="clear-button" @click="resetHistory">
            Clear History
        </button>
    </div>
</template>

<script setup>
import { onMounted, ref, defineProps, watch } from 'vue';
import { useUserStore } from '@/store';
import axios from 'axios';

const userStore = useUserStore()
const equationsHistory = ref([])

const props = defineProps({
  equation: {
    type: String,
    required: true
  }
}) 

watch(() => props.equation, (newValue) => {
  if (props.equation !== ''){
    equationsHistory.value.push(newValue)
  }
})

onMounted(() => {
  getHistory()
})

async function getHistory() {
  const path = 'http://localhost:8088/api/getEquations/' + userStore.username
  const config = {
    headers: {
      'Content-type': 'application/json',
      'Authorization' : 'Bearer ' + userStore.token
    }
  }
  await axios.get(path,userStore.username, config).then(
    (response) => {
      for (let i = 0; i < response.data.length; i++){
        equationsHistory.value.push(response.data[i].equation + " = " + response.data[i].solution)
      }
    }
  ).catch(() => {
    alert("You need to log in!")
  })
}

async function resetHistory() {
  const path = 'http://localhost:8088/api/clearEquations/' + userStore.username
  const config = {
    headers: {
      'Content-type': 'application/json',
      'Authorization' : 'Bearer ' + userStore.token
    }
  }
  await axios.post(path, userStore.username, config).then(
    () => {
      equationsHistory.value = []
    }
  ).catch((error) => {
    alert(error.response)
  })
}

</script>

<style>

#container{
    text-align: center;
}

#clear-button{
    width: 100px;
    height: 50px;
}
#history-container{
    padding: 10px;
    width: 300px;
    height: 550px;
    background-color: rgb(239, 205, 142);
    text-align: center;
    overflow-x: hidden;
    overflow-y: auto;
}

.history-div{
    border: 2px solid black;
    overflow-x: auto;
}

</style>