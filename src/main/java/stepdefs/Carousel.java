package stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import point.pages.IndexPage;
import wrappers.GenericWrapper;

public class Carousel extends GenericWrapper{

	IndexPage obj = new IndexPage();

@When("user clicks on right navigation button")
public void user_clicks_on_right_navigation_button() {
    obj.getTheDefaultWidgetList();
	obj.clickOnRightNavigation();
}

@Then("the content shifts once to the right")
public void the_content_shifts_once_to_the_right() {
    obj.validateRightNavigation();
    obj.clickOnLeftNavigation();
}

@When("user clicks on left navigation button")
public void user_clicks_on_left_navigation_button() {
    obj.getTheDefaultWidgetList();
	obj.clickOnLeftNavigation();
}

@Then("the content shifts once to the left")
public void the_content_shifts_once_to_the_left() {
    obj.validateLeftNavigation();
    obj.clickOnRightNavigation();
}

@When("user has navigated through all the content")
public void user_has_navigated_through_all_the_content() {
	obj.getTheDefaultWidgetList();
	obj.navigateAllContent();
}

@Then("the carousel goes back and shows the first item again")
public void the_carousel_goes_back_and_shows_the_first_item_again() {
    // Write code here that turns the phrase above into concrete actions
    obj.validateFirstContent();
}

	
	
}
