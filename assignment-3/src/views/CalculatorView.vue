<template>
    <div class="calc">    
      <Calculator @addEquationToHistory="addEquationToArray"/>
      <div class="spacing"></div>
      <CalculatorHistory :equationHistory="equationsArray" @resetHistory="resetArray"/>
    </div>
  </template>
  
  <script>
  import Calculator from '../components/Calculator.vue';
  import CalculatorHistory from '../components/CalculatorHistory.vue';
  
  export default {
    name: 'CalculatorView',
    components: {
      Calculator,
      CalculatorHistory
    },
    data() {
      return{
        equationsArray: []
      }
    },
    
    methods:{
      addEquationToArray(equation){
        this.equationsArray.push(equation);
      },
      resetArray(){
        this.equationsArray = [];
      }
    }
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