<template>
    <div id="container">
        <div id="history-container">
            <div class="history-div">
                <h1>Calculator History</h1>
            </div>

            <div class="history-div" v-for="(equation, index) in equationHistory" :key="index" id="calculator-history">
                <p>
                    {{ equation }}
                </p>
            </div>
        </div>
        <button id="clear-button" @click="$emit('resetHistory')">
            Clear History
        </button>
    </div>
</template>

<script>

export default{
    name: "Calculator History",
    props: {
        equationHistory: {
            type: Array,
        }
    },

}

</script>

<style scoped>

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
    background-color: #32c5cf;
    text-align: center;
    overflow-x: hidden;
    overflow-y: auto;
}

.history-div{
    border: 2px solid black;
    overflow-x: auto;
    color: black;
}

p{
    font-size: 20px;
    color: black;
    font-weight: bold;
}

</style>