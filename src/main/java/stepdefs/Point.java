package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import point.pages.AboutUsPage;

public class Point {
	
	
	AboutUsPage obj = new AboutUsPage();
	
	@Given("a word has a valid symbol, or valid symbols")
	public void a_word_has_a_valid_symbol_or_valid_symbols() {
	    // Write code here that turns the phrase above into concrete actions
	    obj.getWord();
	}
	
	@When("a user hovers over a word")
	public void a_user_hovers_over_a_word() {
		obj.mouseHoverOnWord();
	}

	@When("after a short delay")
	public void after_a_short_delay() {
	    obj.shortDelay();
	}

	@Then("the word should be highlighted")
	public void the_word_should_be_highlighted() {
		obj.verifyElementIsHighlighted();
	}

	@Then("a symbol, or multiple symbols, should appear for that context")
	public void a_symbol_or_multiple_symbols_should_appear_for_that_context() {
	    obj.verifySymbolsOnTheText();
	}

	

}
