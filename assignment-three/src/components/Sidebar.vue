<template>
    <nav>
        <ul>
            <li>
                <img id="logo" :src="logo" />
            </li>
            <li>
                Calculator
            </li>
            <li>
                Contact
            </li>
        </ul>
    </nav>
</template>

<script>
    export default{
        data(){
            return {
                logo: require('../assets/zav.png')
            }
        }
    }
</script>

<style scoped>
    #logo{
        width: 100px;
        height: 70px;
        margin: 0;
    }
</style>