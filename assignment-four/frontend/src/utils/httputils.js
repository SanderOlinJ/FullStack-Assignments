import axios from 'axios'

const restAPICalc = axios.create({
    baseURL: "http://localhost:8088",
    withCredentials: false,
    headers: {
        "Content-Type": "application/json"
    }
})

export const postEquation = (equation) => {
    return restAPICalc.post("/api/calculate", equation)
}