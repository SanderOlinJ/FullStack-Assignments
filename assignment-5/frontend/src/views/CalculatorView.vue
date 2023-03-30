<template>
    <div class="calc">    
        <Calculator @addEquationToHistory="addEquationToArray"/>
        <div class="spacing"></div>
        <CalculatorHistory :equation="currentEquation"/>
    </div>
</template>
  
<script setup>
import Calculator from '../components/Calculator.vue';
import CalculatorHistory from '../components/CalculatorHistory.vue';
import { ref } from 'vue'
    
const currentEquation =  ref('')

function addEquationToArray(equation) {
  currentEquation.value = equation
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