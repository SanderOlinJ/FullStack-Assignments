import { describe, it, expect } from "vitest"
import { mount } from "@vue/test-utils"
import CalculatorHistory from '../../CalculatorHistory.vue'

describe("CalculatorHistory", () => {
    const wrapper = mount(CalculatorHistory)
    it("Should render properly", () => {
        expect(wrapper.html()).toBeTruthy()
    })
    it("Should pass array prop", async() => {
        await wrapper.setProps({equationHistory: ["Calculator test"]})
        expect(wrapper.props().equationHistory).toStrictEqual(["Calculator test"]);
    })
})
