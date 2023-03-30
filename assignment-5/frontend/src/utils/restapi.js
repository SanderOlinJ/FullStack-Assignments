import axios from 'axios'

const restAPICalc = axios.create({
    baseURL: "http://localhost:8088/api",
    headers: {
        "Content-Type": "application/json"
    }
})

export const postEquation = (equation) => {
    return restAPICalc.post("/calculate", equation)
}

export const postUser = (user) => {
    return restAPICalc.post("/signup", user)
}

export const postLogin = (user) => {
    return restAPICalc.post("/login", user)
}

