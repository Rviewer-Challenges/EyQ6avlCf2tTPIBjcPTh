package com.rebirth.mycode.stepdef;

import com.rebirth.mycode.domain.udt.pojos.ProgrammingLanguageSecure;
import com.rebirth.mycode.domain.udt.pojos.ProgrammingLanguageUpsert;
import com.rebirth.mycode.exceptions.EntityDoesntExistException;
import com.rebirth.mycode.services.ProgrammingLanguageService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;


public class ProgrammingLanguageStepdef {

    private static final Logger LOG = LoggerFactory.getLogger(ProgrammingLanguageStepdef.class);
    @Autowired
    ProgrammingLanguageService programmingLanguageService;


    // Scenario01
    private String programmingLanguageNameInsert;
    private UUID programmingLanguageNameUUID;

    @Given("The follow param {string}")
    public void theFollowParam(String arg0) {
        programmingLanguageNameInsert = arg0.trim();
        LOG.info("Entrada {}", arg0);
    }

    @When("I try to create this language in the database")
    public void iTryToCreateThisLanguageInTheDatabase() {
        ProgrammingLanguageUpsert object = new ProgrammingLanguageUpsert()
                .setProgrammingLanguageName(programmingLanguageNameInsert);
        ProgrammingLanguageSecure result = programmingLanguageService.insert(object);
        programmingLanguageNameUUID = result.getProgrammingLanguageUid();
    }

    @Then("I fetch an object of this language created")
    public void iFetchAnObjectOfThisLanguageCreated() {
        Assertions.assertNotNull(programmingLanguageNameUUID);
    }

    // Scenario02
    private String programmingLanguageNameInPersistence;
    private ProgrammingLanguageSecure programmingLanguageInPersistence;

    @Given("The name of the Programming Language created in the last scenario was {string}")
    public void theIdentifierOfTheProgrammingLanguageCreatedInTheLastScenario(String arg0) {
        LOG.info("Entrada {}", arg0);
        programmingLanguageNameInPersistence = arg0.trim();
    }

    @When("I try to fetch this language in the database by name")
    public void iTryToFetchThisLanguageInTheDatabase() {
        programmingLanguageInPersistence = programmingLanguageService.fetchByName(programmingLanguageNameInPersistence);
    }

    @Then("I compere the name from the fetched with the assigned in the last scenario")
    public void iCompereTheNameFromTheFetchedWithTheAssignedInTheLastScenario() {
        Assertions.assertEquals(programmingLanguageNameInPersistence, programmingLanguageInPersistence.getProgrammingLanguageName());
    }

    // Scenario03
    List<ProgrammingLanguageSecure> programmingLanguageSecureList;

    @Given("I need to query the list of languages")
    public void iNeedToQueryTheListOfLanguages() {
        LOG.info("Init this test");
    }

    @When("I fetch this list")
    public void iFetchThisList() {
        this.programmingLanguageSecureList = this.programmingLanguageService.findAll();
    }

    @Then("I should get a not empty list")
    public void iShouldGetANotEmptyList() {
        Assertions.assertFalse(this.programmingLanguageSecureList.isEmpty());
        Assertions.assertEquals(1, this.programmingLanguageSecureList.size());
    }

    // Scenario04
    private String programmingLanguageNameToUpdateOld;
    private String programmingLanguageNameToUpdateNew;
    private String programmingLanguageFetchedWhenUpdate;

    @Given("The follow name param {string} is to update the current programming language {string}")
    public void theFollowNameParam(String arg0, String arg1) {
        LOG.info("Entrada: {}", arg0);
        LOG.info("Entrada: {}", arg1);

        programmingLanguageNameToUpdateOld = arg1.trim();
        programmingLanguageNameToUpdateNew = arg0.trim();
    }

    @When("I try to update this entity in persistence in the database")
    public void iTryToUpdateThisEntityInPersistenceInTheDatabase() {
        ProgrammingLanguageSecure fetched = this.programmingLanguageService.fetchByName(programmingLanguageNameToUpdateOld);
        ProgrammingLanguageUpsert programmingLanguageUpsert = new ProgrammingLanguageUpsert()
                .setProgrammingLanguageName(programmingLanguageNameToUpdateNew);
        programmingLanguageFetchedWhenUpdate = this.programmingLanguageService.update(programmingLanguageUpsert, fetched.getProgrammingLanguageUid())
                .getProgrammingLanguageName();
    }

    @Then("I should get the same name from the database")
    public void iShouldGetTheSameNameFromTheDatabase() {
        Assertions.assertEquals(programmingLanguageNameToUpdateNew, programmingLanguageFetchedWhenUpdate);
    }

    // Scenario04
    private String programmingLanguageNameToDelete;
    private ProgrammingLanguageSecure programmingLanguageToDelete;

    @Given("The id fetched by the param {string}")
    public void theIdFetchedByTheParam(String arg0) {
        LOG.info("Entrada: {}", arg0);
        programmingLanguageNameToDelete = arg0.trim();
    }

    @When("I try to delete this entity in the database")
    public void iTryToDeleteThisEntityInTheDatabase() {
        programmingLanguageToDelete = this.programmingLanguageService.fetchByName(programmingLanguageNameToDelete);
        this.programmingLanguageService.delete(programmingLanguageToDelete.getProgrammingLanguageUid());
    }

    @Then("The entity don't exist in the database")
    public void theEntityDonTExistInTheDatabase() {
        Assertions.assertThrows(EntityDoesntExistException.class, () -> programmingLanguageService.findById(programmingLanguageToDelete.getProgrammingLanguageUid()));
    }

}
