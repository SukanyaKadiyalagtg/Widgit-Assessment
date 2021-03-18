Feature: Carousel

 Scenario: Verify Carousel navigates right
 When user clicks on right navigation button
 Then the content shifts once to the right
 
 Scenario: Verify Carousel navigates left
 When user clicks on left navigation button
 Then the content shifts once to the left
 
 Scenario: Verify Carousel rotates through all the content
 When user has navigated through all the content
 Then the carousel goes back and shows the first item again
 