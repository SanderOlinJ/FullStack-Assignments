import axios from 'axios'

const restAPICalc = axios.create({
  baseURL: "http://localhost:8088/api",
  withCredentials: true,
  headers: {
    Accept: 'application/json',
    'Content-Type': 'application/json'
  }
})

export const postUser = (user) => {
  return restAPICalc.post('/signup', user)
}

export const postLogin = (user) => {
    return restAPICalc.post('/login', user)
}

