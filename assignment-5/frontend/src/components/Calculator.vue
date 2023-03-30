<template>
    <div id="grid-container">
        <div class="calculator-row" id="outer-calculator-div">
            <div id="calculator-output-div">
                <div id="calculator-output">
                    {{ updatingValue }}
                </div>
            </div>
        </div>
        <div class="calculator-row">
            <button class="calculator-button" v-for="n in calculatorFirstRow" :key="n"
                :class="{'pink-buttons': ['PWR','DEL','AC','÷'].includes(n)}" 
                @click="buttonClick(n)">
                {{ n }}
            </button>
        </div>
        
        <div class="calculator-row">
            <button class="calculator-button" v-for="n in calculatorSecondRow" :key="n"
                :class="{'green-buttons': ['7','8','9'].includes(n), 'pink-buttons': ['x'].includes(n)}"
                @click="buttonClick(n)">
                {{ n }}
            </button>
        </div>

        <div class="calculator-row">
            <button class="calculator-button" v-for="n in calculatorThirdRow" :key="n"
                :class="{'green-buttons': ['4','5','6'].includes(n), 'pink-buttons': ['-'].includes(n)}"
                @click="buttonClick(n)">
                {{ n }}
            </button>
        </div>

        <div class="calculator-row">
            <button class="calculator-button" v-for="n in calculatorFourthRow" :key="n"
                :class="{'green-buttons': ['1','2','3'].includes(n), 'pink-buttons': ['+'].includes(n)}"
                @click="buttonClick(n)">
                {{ n }}
            </button>
        </div>

        <div class="calculator-row">
            <div id="zero-button-div">
                <button class="calculator-button, green-buttons" id="zero-button"
                @click="buttonClick('0')">
                    0
                </button>
            </div>
            <button class="calculator-button" v-for="n in calculatorFifthRow" :key="n"
                :class="{'pink-buttons': ['.','='].includes(n)}"
                @click="buttonClick(n)">
                {{ n }}
            </button>
        </div>
    </div>
</template>

<script setup>
import { ref, defineEmits } from 'vue'
import { postEquation, postEquationWithUser } from '../utils/restapi'
import { useUserStore } from '@/store';

const updatingValue = ref('OFF')
const value = ref(0.0)
const lastOperator = ref('')
const equation = ref('')
const solution = ref('')
const isOff = ref(true)
const beginNewEquation = ref(false)
const calculatorFirstRow = ref(['PWR','DEL', 'AC', '÷'])
const calculatorSecondRow = ref(['7', '8', '9', 'x'])
const calculatorThirdRow = ref(['4', '5', '6', '-'])
const calculatorFourthRow = ref(['1', '2', '3', '+'])
const calculatorFifthRow = ref(['.', '='])
const emits = defineEmits(['addEquationToHistory'])
const userStore = useUserStore()

function buttonClick(buttonValue){
  if (buttonValue == 'PWR') {
    clearOutAllFields()
    if (isOff.value) {
      isOff.value = false
      return
    } else {
      updatingValue.value = 'OFF'
      isOff.value = true
      return
    }
  }
  if (isOff.value){
    return
  }
  if (isNaN(buttonValue) && buttonValue !== '.') {
    symbolHandling(buttonValue)
  } else {
    numberHandling(buttonValue)
  }
}

function numberHandling(buttonValue){
  if (isNaN(updatingValue.value) && updatingValue.value !== '.') {
    updatingValue.value = ''
  }
  if (!beginNewEquation.value) {
    updatingValue.value = updatingValue.value.concat(buttonValue)
  } else {
    updatingValue.value = buttonValue
    beginNewEquation.value = false
  }
}

function symbolHandling(buttonValue) {
  switch (buttonValue){
    case 'AC':
      clearOutAllFields()
      break
    case 'DEL':
      if (!updatingValue.value == ''){
        updatingValue.value = updatingValue.value.slice(0, -1)
      } else {
        clearOutAllFields()
      }
      break
    case '=':
      buildEquation(buttonValue)
      RestAPI()
      break
    default:
      buildEquation(buttonValue)
      break
  }
}

async function RestAPI(){
  await postEquation(equation.value).then(
    (response) => {
      const result = ref(response.data)
      result.value = parseFloat(result.value.solution)
      if (!Number.isInteger(result.value)){
        result.value = parseFloat(result.value.toFixed(4))
      }
      value.value = result.value
      updatingValue.value = value.value.toString()
      solution.value = value.value
      beginNewEquation.value = true
    }
  ).catch((error) => {
    alert(error.response)
  })
  const request = ref({
    username: userStore.username,
    equation: equation.value,
    solution: solution.value
  })
  console.log(equation.value)
  await postEquationWithUser(request.value)
  emits('addEquationToHistory', equation.value + "=" + solution.value)
  clearAllButOutput()
}

function buildEquation(buttonValue){
  if (lastOperator.value !== '' && isNaN(updatingValue.value)){
    equation.value = equation.value.substring(0, equation.value.length-1)
  } else{
    equation.value += updatingValue.value
  }
  if (buttonValue !== '='){
    equation.value += buttonValue
    updatingValue.value = buttonValue
  }
}

function clearOutAllFields(){
  clearAllButOutput()
  updatingValue.value = ''
}

function clearAllButOutput(){
  value.value = 0;
  lastOperator.value = ''
  equation.value = ''
  solution.value = ''
}
</script>


<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Orbitron:wght@600&display=swap');
#grid-container{
    display: grid;
    color: white;
    height: 600px;
    width: 500px;
    grid-template-columns: repeat(auto, 1fr);
    grid-template-rows:  repeat(auto, 1fr);
    background-color: rgb(35, 35, 72);
    border: 10px solid black;
    border-radius: 10px;
}

.calculator-row{
    width: 500px;
    min-height: 88px;
    text-align: left;
    display: flex;
}

.calculator-button{
    min-width: 125px;
    height: 88px;
    font-size: 40px;
    border-radius: 5px;
    cursor: pointer;
}

.calculator-button:active{
    border: 3px solid black;
    font-size: 36px;
}

#calculator-output{
    padding-top: 12px;
    padding-right: 10px;
    height: 100px;
    text-align: right;
    justify-items: center;
    font-size: 70px;
    font-family: 'Orbitron', sans-serif;
    overflow-x: auto;
    overflow-y: hidden;
}

#calculator-output-div{
    width: 460px;
    height: 100px;
    border-radius: 20px;
    margin-left: 20px;
    margin-top: 25px;
    background-color: rgb(6, 6, 50);
}

#outer-calculator-div{
    height: 160px;
}

.pink-buttons{
    background-color: #fe67c2;
    border: 1px solid black;
}

.green-buttons{
    background-color: #62f2b4;
    border: 1px solid black;
}

#zero-button-div{
    display: grid;
    width: 250px;
    min-height: 88px;
}

#zero-button{
    font-size: 40px;
    border-radius: 5px;
    cursor: pointer;
}

#zero-button:active{
    border: 3px solid black;
    font-size: 36px;
}

button:hover{
    opacity: 0.85;
}

</style>