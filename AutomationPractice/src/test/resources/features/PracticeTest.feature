@Include
Feature: PracticeTest Feature
	
Scenario: User registration in automationpractice.com
	Given practice site is open
	Then register an user
	And add item to cart
	And logout the new user
	Then login back gain with the new user
	Then validate cart
