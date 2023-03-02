<template>
  <div id="app">
    <Calculator @addEquationToHistory="addEquationToArray"/>
    <div class="spacing">

    </div>
    <CalculatorHistory :equationHistory="equationsArray" @resetHistory="resetArray"/>
  </div>
</template>

<script>
  import Calculator from "./components/Calculator.vue";
  import CalculatorHistory from "./components/CalculatorHistory.vue";

  export default{
    name: "App",
    components:{
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
#app{
  display: flex;
  
}

.spacing{
  width: 50px;
}
</style>
