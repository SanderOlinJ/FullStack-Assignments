import { describe, it, expect } from "vitest"
import { mount } from "@vue/test-utils"
import Calculator from '../../Calculator.vue'

describe("Calculator", () => {
    const wrapper = mount(Calculator)
    it("Should render properly", () => {
        expect(wrapper.html()).toBeTruthy()
    })
    it("Should contain zero button", () => {
        expect(wrapper.find("zero-button")).toBeTruthy()
    })
})
