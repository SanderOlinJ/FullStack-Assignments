import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useUserStore = defineStore('userStore', () => {
  const username = ref('')
  const loggedIn = ref(false)

  function login(user){
    username.value = user.username
    loggedIn.value = true
  }

  function logOut() {
    username.value = ''
    loggedIn.value = false
  }
  return { username, loggedIn, login, logOut }
},
{
  persist: true
})
