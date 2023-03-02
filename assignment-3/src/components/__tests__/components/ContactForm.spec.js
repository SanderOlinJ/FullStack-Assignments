import { describe, it, expect } from "vitest"
import { mount } from "@vue/test-utils"
import ContactForm from '../../ContactForm.vue'
import { createStore } from 'vuex'

describe("ContactForm", async() => {
    const mockStore = createStore({
        state: {
            name: "Test1",
            email: "test@gmail.com"
        },
        mutations: {
            SET_NAME(state, name){
                state.name = name
            },
            SET_EMAIL(state, email){
              state.email = email
            }
        }
    })
    const wrapper = mount(ContactForm, {
        global: {
            plugins: [mockStore]
        }
    })

    it("Should render properly", () => {
        expect(wrapper.html()).toBeTruthy()
    })

    const button = wrapper.find('#submitButton')
    it("If form is not filled out, button should be disabled", () => {
        expect(button.element.disabled).toBe(true)
    })
})
