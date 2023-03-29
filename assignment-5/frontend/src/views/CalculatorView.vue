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
import { ref } from 'vue'
    
const equationsArray =  ref([])

function addEquationToArray(equation) {
    equationsArray.value.push(equation);
}

function resetArray() {
    equationsArray.value = [];
}

</script>

<style scoped>
.calc{
display: flex;

}
.spacing{
width: 30px;
}
</style>