package test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.Base;
import pages.ChangeReleasePage;
import pages.ChangerequestApproval;
import pages.CreateChangeStudyRequest;
import pages.Login;
import pages.Primarydetailspage;

import pages.StudyFindingsPage;
import pages.changes;
import pages.hamburgermenunavigationpage;

public class Tc_006_NewRequestChanges extends Base {

    @BeforeTest
    public void setValues() {
        testName = "Verify creation of new change request";
        testdesc = "Test end-to-end workflow for new change request creation using XML parameters";
        testAuthor = "Samlordson";
        testcategory = "Sanity";
    }

    @Test
    @Parameters({
        "email", "password", "business", "originatingParty", "requestedBy", 
        "projectArea", "otherProjectArea", "changeDescription", "changeReason", "additionalInfo", 
        "responsibility", "pmName", "country", "planIssuanceDate", "tierType", "discipline",
        "dateComplete", "sheetsAffected", "detailsAffected", "specSection", "changeNarrative",
        "specDateComplete", "newSpecRequired", "newSpecification", "costDateComplete", "estimatedCost",
        "costSummary", "qaDateComplete", "qaDueDate", "assignTo", 
        "clientApproves", "clientNotes", "reviewer", "decisionDate", 
        "releaseCountry", "releaseNumber", "finalCountry", "finalReleaseNumber"
    })
    public void createChangeRequest(
        String email, String password, String business, String originatingParty, String requestedBy,
        String projectArea, String otherProjectArea, String changeDescription, String changeReason, String additionalInfo,
        String responsibility, String pmName, String country, String planIssuanceDate, String tierType, String discipline,
        String dateComplete, String sheetsAffected, String detailsAffected, String specSection, String changeNarrative,
        String specDateComplete, String newSpecRequired, String newSpecification, String costDateComplete, String estimatedCost,
        String costSummary, String qaDateComplete, String qaDueDate, String assignTo,
        String clientApproves, String clientNotes, String reviewer, String decisionDate,
        String releaseCountry, String releaseNumber, String finalCountry, String finalReleaseNumber
    ) {
        
        

      //  new SelectRolespages(driver)
       //     .clickroledropdown()
          //  .selectAdmin();

        new hamburgermenunavigationpage(driver)
          //  .clickHamburgermenu()
            .clickchangemanagement();
        
        new changes(driver).clickchanges();
        

        new CreateChangeStudyRequest(driver)
            .clickcreatenewrequest()
            .setDateReceived("10/23/2025")
            .selectBusiness(business)
            .selectOriginatingParty(originatingParty)
            .enterRequestedBy(requestedBy)
            .selectProjectArea(projectArea)
            .enterOtherProjectArea(otherProjectArea)
            .enterChangeDescription(changeDescription)
            .enterChangeReason(changeReason)
            .enterAdditionalInfo(additionalInfo)
            .scrollToSubmit()
            .clickSubmit();

        new Primarydetailspage(driver)
            .waitForDrawer()
            .selectResponsibility(responsibility)
            .selectPM(pmName)
            .selectMG2TeamYes()
            .selectPrioritizationHigh()
            .selectCountry(country)
            .setPlanIssuanceDate(planIssuanceDate)
            .selectTierAndType(tierType)
            .selectDiscipline(discipline)
            .scrollToSubmit()
            .clickSubmit();

        new StudyFindingsPage(driver)
            .waitForPage()
            .setDateComplete(dateComplete)
            .enterSheetsAffected(sheetsAffected)
            .enterDetailsAffected(detailsAffected)
            .enterSpecSectionAffected(specSection)
            .enterChangeNarrative(changeNarrative)
            .setSpecDateComplete(specDateComplete)
            .selectNewSpecRequired(newSpecRequired)
            .enterNewSpecification(newSpecification)
            .setCostDateComplete(costDateComplete)
            .enterEstimatedCost(estimatedCost)
            .enterCostAnalysisSummary(costSummary)
            .setQADateComplete(qaDateComplete)
            .setQADueDate(qaDueDate)
            .selectAssignTo(assignTo)
            .clickNextOrSave();

        new ChangerequestApproval(driver)
            .waitForSection()
            .selectClientApproves(clientApproves)
            .enterClientImplementationNotes(clientNotes)
            .enterChangeReviewer(reviewer)
            .setChangeDecisionDate(decisionDate)
            .selectChangeReleaseCountry(releaseCountry)
            .selectChangeReleaseNumber(releaseNumber)
            .clickAddRelease()
            .clickSave();

        new ChangeReleasePage(driver)
            .waitForChangeReleasePage()
            .selectCountry(finalCountry)
            .selectChangeReleaseNumber(finalReleaseNumber)
            .clickAdd()
            .clickSave();
    }
}
