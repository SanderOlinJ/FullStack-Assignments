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
import { getEquationByUser, clearEquationsByUser } from '@/utils/restapi';
import { useUserStore } from '@/store';

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

function getHistory() {
  getEquationByUser(userStore.username).then(
    (response) => {
      for (let i = 0; i < response.data.length; i++){
        equationsHistory.value.push(response.data[i].equation + " = " + response.data[i].solution)
      }
    }
  ).catch((error) => {
    alert(error)
  })
}

function resetHistory() {
  clearEquationsByUser(userStore.username).then(
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