<template>
    <label>{{ label }}</label>
    <textarea
        v-bind="$attrs"
        :placeholder="label"
        class="field"
        :value="modelValue"
        @input="$emit('update:modelValue', $event.target.value)"
    ></textarea>
</template>

<script>
    export default{
        props: {
            label: {
                type: String,
                default: ''
            },
            modelValue: {
                type: [String],
                default: ''
            }
        }
    }
</script>

<style scoped>
    textarea{
        align-content: center;
        resize: none;
        font-size: 20px; 
    }

    label{
        text-align: left;
        color: white;
        font-weight: bold;
        margin-bottom: 10px;
        font-size: 20px; 
    }
</style>