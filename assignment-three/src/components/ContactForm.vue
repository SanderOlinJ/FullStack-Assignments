<template>
    <form id="loginContainer" @submit.prevent="submitForm">
        <div id="loginTitle">
            <label>Fill out form</label>
        </div>
        <p class="update" id="feedback">{{ updates.feedback_update }}</p>
        <BaseInput
            id="nameInput"
            v-model="inputs.name"
            label="Name"
            type="text"
        />
        <p class="update">{{ updates.name_update }}</p>
        <BaseInput
            id="emailInput"
            v-model="inputs.email"
            label="Email"
            type="text"
        />
        <p class="update" id="email-update">{{ updates.email_update }}</p>
        <BaseInput
            id="messageInput"
            v-model="inputs.message"
            label="Message"
            type="text"
        />
        <p class="update">{{ updates.message_update }}</p>
        <div>
            <button id="submitButton" type="submit" :disabled="isDisabled">Submit form</button>
        </div>
    </form>
</template>

<script>
    import BaseInput from './BaseInput.vue'
    import ContactFormServices from '../services/ContactFormServices'

    export default {
    name: "LoginView",
    data() {
        return {
            inputs: {
                name: "",
                email: "",
                message: ""
            },
            updates: {
                name_update: "",
                email_update: "",
                message_update: "",
                feedback_update: ""
            },
            emailRegex: /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        };
    },
    computed: {
        isDisabled() {
            if (this.inputs.name == "" || this.inputs.email == "" || this.inputs.message == ""){
                return true
            }

            if (!this.emailRegex.test(String(this.inputs.email).toLowerCase())){
                this.setEmailUpdateMessage("Please enter a valid email.")
                return true
            } else {this.setEmailUpdateMessage("")}
        
            if (this.inputs.name.length > 40) {
                this.setNameUpdateMessage("Name cannot be longer than 40 characters.")
                return true
            } else {this.setNameUpdateMessage("")}

            if (this.inputs.email.length > 50) {
                this.setEmailUpdateMessage("Email cannot be longer than 50 characters.")
                return true
            } else {this.setEmailUpdateMessage("")}

            if (this.inputs.message.length > 300) {
                this.setMessageUpdateMessage("Message cannot be longer than 300 characters.")
                return true
            } else {this.setMessageUpdateMessage("")}

            return false;
        }
    },
    methods: {
        submitForm() {
            this.$store.commit("SET_NAME", this.inputs.name)
            this.$store.commit("SET_EMAIL", this.inputs.email)
            ContactFormServices.postContactForm(this.inputs)
                .then( () => {
                    this.updates.feedback_update = "Form was filled out successfully"
                    this.inputs.name = this.$store.state.name
                    this.inputs.email = this.$store.state.email
                    this.inputs.message = ""
                })
                .catch( error => {
                    this.updates.feedback_update = error
                })
        },
        setNameUpdateMessage(message){
            this.updates.name_update = message
        },
        setEmailUpdateMessage(message){
            this.updates.email_update = message
        },
        setMessageUpdateMessage(message){
            this.updates.message_update = message
        },
        setFeedbackUpdateMessage(message){
            this.updates.feedback_update = message
        }
    },
    components: { BaseInput },
    
    created(){
        this.inputs.name = this.$store.state.name;
        this.inputs.email = this.$store.state.email;
    }
};
</script>

<style>

    #loginContainer {
        display: grid;
        justify-content: center;
        text-align: center;
        align-items: center;
        align-content: center;
        margin: 40px;
    }

    #loginTitle {
        font-size: x-large;
        font-weight: bold;
        margin-bottom: 50px;
        font-size: 30px;
        color: white;
    }

    #submitButton{
        width: 200px;
        height: 50px;
        margin-top: 50px;
        background-color: rgb(75, 13, 133);
        border-color: rgb(164, 69, 255);
        color: white;
        font-size: 20px;
        font-weight: bold;
        border-radius: 50px;
        cursor: pointer;
        opacity: 1;
    }

    #submitButton:disabled{
        opacity: 0.6;
        cursor: auto;
    }

    #nameInput,
    #emailInput{
        height: 35px;
        resize: none;
    }
    #messageInput{
        font-size: 20px;
        height: 200px;
        resize: none;
    }

    .update{
        color: white;
        height: 10px;
        width: 400px;
        margin-top: 5;
        padding: 0;
    }

    #feedback{
        font-size: 20px;
        color: white;
        text-decoration: underline;
    }
</style>

