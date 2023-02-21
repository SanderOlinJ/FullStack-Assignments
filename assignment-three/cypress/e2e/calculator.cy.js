describe("Calculator with CalculatorHistory", () => {
    beforeEach(() => {
      cy.visit("/")
    })

    it("Finds buttons", () => {
        cy.get("#3").should("have.class", "calculator-button")
        cy.get("#7").should("have.class", "green-buttons")
    })
    
    it("Perform addition", () => {
        cy.get("#PWR").click()
        cy.get("#3").click()
        cy.get('button:contains("+")').click();
        cy.get("#7").click()
        cy.get('button:contains("=")').click();
        cy.get("#calculator-output").should("have.text","10")
    })

    it("Perform multiple different calculations in a row", () => {
        cy.get("#PWR").click()
        cy.get("#1").click()
        cy.get("#zero-button").click()
        cy.get("button:contains('-')").click()
        cy.get("#5").click()
        cy.get("button:contains('x')").click()
        cy.get("#2").click()
        cy.get("button:contains('=')").click()
        cy.get("button:contains('x')").click()
        cy.get("#3").click()
        cy.get("#5").click()
        cy.get("#DEL").click()
        cy.get("button:contains('=')").click()
        cy.get("#calculator-output").should("have.text","30")
    })

    it("History should receive calculation", () => {
        cy.get("#PWR").click()
        cy.get("#5").click()
        cy.get("#zero-button").click()
        cy.get("button:contains('÷')").click()
        cy.get("#2").click()
        cy.get("button:contains('=')").click()
        cy.get("#calculator-history").should("have.text", "50÷2=25")
    })
})