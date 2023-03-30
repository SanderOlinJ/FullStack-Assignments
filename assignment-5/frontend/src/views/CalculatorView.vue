<template>
    <div class="calc">    
        <Calculator @addEquationToHistory="addEquationToArray"/>
        <div class="spacing"></div>
        <CalculatorHistory :equationHistory="equationsArray" @resetHistory="resetArray"/>
    </div>
</template>
  
<script setup>
import Calculator from '../components/Calculator.vue';
import CalculatorHistory from '../components/CalculatorHistory.vue';
import { ref, onMounted } from 'vue'
import { useUserStore } from '@/store';
    
const userStore = useUserStore()
const equationsArray =  ref([])

function addEquationToArray(equation) {
    equationsArray.value.push(equation);
}

function resetArray() {
    equationsArray.value = [];
}

onMounted(() => {
  console.log(userStore.username)
})

</script>

<style scoped>
.calc{
display: flex;

}
.spacing{
width: 30px;
}
</style>