describe("Contact Form", () => {
    beforeEach(() => {
        cy.visit("/contact")
    })

    it("Check that submit-button is deactivated as long as fields aren't filled out correctly", () => {
        cy.get("#submitButton").should("be.disabled")
        cy.get("#nameInput").type("Random Name")
        cy.get("#submitButton").should("be.disabled")
        cy.get("#emailInput").type("random@email.com")
        cy.get("#submitButton").should("be.disabled")
        cy.get("#messageInput").type("Random Message")
        cy.get("#submitButton").should("be.enabled")
    })

    it("Check that invalid email is handled", () => {
        cy.get("#nameInput").type("Random Name")
        cy.get("#emailInput").type("randomemail.com")
        cy.get("#messageInput").type("Random Message")
        cy.get("#email-update").should("have.text", "Please enter a valid email.")
        cy.get("#submitButton").should("be.disabled")
    })

    it("Check that form is submitted", () => {
        cy.get("#nameInput").type("Random Name")
        cy.get("#emailInput").type("random@email.com")
        cy.get("#messageInput").type("Random Message")
        cy.get("#submitButton").click()
        cy.get("#feedback").should("have.text", "Form was filled out successfully")
    })
})