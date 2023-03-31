import { defineStore } from 'pinia'
import { ref } from 'vue'
import axios from 'axios'

export const useUserStore = defineStore('userStore', () => {
  const username = ref('')
  const token = ref('')
  const loggedIn = ref(false)
  const interval = ref(null)

  function login(responseData){
    username.value = responseData.userRequest.username
    token.value = responseData.userRequest.password
    loggedIn.value = true
    startInterval()
  }

  function logOut() {
    username.value = ''
    token.value = ''
    loggedIn.value = false
  }

  function startInterval() {
    if (interval.value === null) {
      interval.value = setInterval(() => {
        refreshToken()
      }, 1000 * 4 * 60)
    }
  }

  async function refreshToken() {
    const config = {
      params: {
        username: username.value
      },
      headers: {
        'Content-type': 'application/json',
        Authorization: 'Bearer ' + token.value
      }
    }
    const path = 'http://localhost:8088/api/refreshToken'
    await axios.get(path, config).then(response => {
      if (response.data) {
        token.value = response.data
      }
    }).catch(error => console.error(error))
  }

  function stopInterval() {
    clearInterval(interval.value)
    interval.value = null
  }

  return { username, loggedIn, token, login, logOut, startInterval, stopInterval}
},
{
  persist: true
})
