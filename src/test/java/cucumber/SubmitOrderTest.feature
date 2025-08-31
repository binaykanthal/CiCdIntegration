Feature: Purchase and submit order from Ecommerce Website

	Background:
	Given I landed on ecommerce page
	
	@Regression
	Scenario Outline: Submit order
		Given I logged in with username <username> and password <password>
		When I add product <productName> from cart
		And checkout <productName> and sumbit the order
		Then "Thankyou for the order." message is displayed on confirmation page
		
		Examples:
			|username  				   |password   |productName    |
			|binaykanthal@gmail.com	   |passWord1* |ADIDAS ORIGINAL|
			|binaykanthal2995@gmail.com|Binay12345$|ZARA COAT 3    |
			