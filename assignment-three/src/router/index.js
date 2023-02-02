import {createRouter, createWebHistory} from 'vue-router';
import Calculator from "../components/Calculator";
import CalculatorHistory from "../components/CalculatorHistory";


const routes = [
    {
        path: "/Calculator",
        name: "Calculator",
        component: Calculator
    },
    {
        path: "/CalculatorHistory",
        name: "CalculatorHistory",
        component: CalculatorHistory
    }
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
});

export default router;