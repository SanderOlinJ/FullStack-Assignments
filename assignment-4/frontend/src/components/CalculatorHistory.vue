<template>
    <div id="container">
        <div id="history-container">
            <div class="history-div">
                <h1>Calculator History</h1>
            </div>

            <div class="history-div" v-for="(equation, index) in equationHistory" :key="index">
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
            type: Array
        }
    },

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