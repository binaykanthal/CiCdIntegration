Feature: Error validation Ecommerce Website
	@ErrorValidation
	Scenario Outline: Submit order
		Given I landed on ecommerce page
		When  I logged in with username <username> and password <password>
		Then I checked for error message "Incorrect email or password."
		
		
		Examples:
			|username  				   |password   |productName    |
			|binaykanthal@gmail.com	   |passWord1$ |ADIDAS ORIGINAL|