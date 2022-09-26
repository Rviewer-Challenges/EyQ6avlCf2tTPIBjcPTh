package com.rebirth.mycode.stepdef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class TestStepdef {

    private Integer x;
    private Integer y;
    private Integer result;


    @Given("I have number {int} and number {int}")
    public void iHaveNumberAndNumber(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @When("I sum this two numbers")
    public void whenStep() {
        this.result = x + y;
    }

    @Then("I should get {int}")
    public void iShouldGet(int z) {
        Assertions.assertEquals(this.result, z);
    }


}
