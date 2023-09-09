/*
 * Copyright (c) 2009,  Sarah Heckman, Laurie Williams, Dright Ho
 * All Rights Reserved.
 *
 * Permission has been explicitly granted to the University of Minnesota
 * Software Engineering Center to use and distribute this source for
 * educational purposes, including delivering online education through
 * Coursera or other entities.
 *
 * No warranty is given regarding this software, including warranties as
 * to the correctness or completeness of this software, including
 * fitness for purpose.
 *
 *
 * Modified 20171114 by Ian De Silva -- Updated to adhere to coding standards.
 *
 */
package edu.ncsu.csc326.coffeemaker;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.ncsu.csc326.coffeemaker.CoffeeMaker;
import edu.ncsu.csc326.coffeemaker.UICmd.*;
import gherkin.lexer.Th;
import org.junit.Assert;

/**
 * Contains the step definitions for the cucumber tests.  This parses the
 * Gherkin steps and translates them into meaningful test steps.
 */
public class TestSteps {

	private Recipe recipe1;
	private Recipe recipe2;
	private Recipe recipe3;
	private Recipe recipe4;
	private Recipe recipe5;
	private Recipe recipeDetailed;
	private CoffeeMakerUI coffeeMakerMain;
	private CoffeeMaker coffeeMaker;
	private RecipeBook recipeBook;
	private Inventory inventory;


	private void initialize() {
		recipeBook = new RecipeBook();
		coffeeMaker = new CoffeeMaker(recipeBook, new Inventory());
		coffeeMakerMain = new CoffeeMakerUI(coffeeMaker);
	}

	@Given("^an empty recipe book$")
	public void an_empty_recipe_book() {
		initialize();
	}

	@Given("^recipes with too much ingredients$")
	public void bigIngredientsRecipe() throws Throwable{

		initialize();

		//Set up for r1
		recipe1 = new Recipe();
		recipe1.setName("BigCoffee");
		recipe1.setAmtChocolate("0");
		recipe1.setAmtCoffee("20");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");

		//Set up for r2
		recipe2 = new Recipe();
		recipe2.setName("BigChocolate");
		recipe2.setAmtChocolate("20");
		recipe2.setAmtCoffee("3");
		recipe2.setAmtMilk("1");
		recipe2.setAmtSugar("1");
		recipe2.setPrice("75");

		//Set up for r3
		recipe3 = new Recipe();
		recipe3.setName("BigMilk");
		recipe3.setAmtChocolate("0");
		recipe3.setAmtCoffee("3");
		recipe3.setAmtMilk("20");
		recipe3.setAmtSugar("1");
		recipe3.setPrice("100");

		recipeBook.addRecipe(recipe1);
		recipeBook.addRecipe(recipe2);
		recipeBook.addRecipe(recipe3);

	}


	@Given("a default recipe book")
	public void a_default_recipe_book() throws Throwable {
		initialize();

		//Set up for r1
		recipe1 = new Recipe();
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("0");
		recipe1.setAmtCoffee("3");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");

		//Set up for r2
		recipe2 = new Recipe();
		recipe2.setName("Mocha");
		recipe2.setAmtChocolate("20");
		recipe2.setAmtCoffee("3");
		recipe2.setAmtMilk("1");
		recipe2.setAmtSugar("1");
		recipe2.setPrice("75");

		//Set up for r3
		recipe3 = new Recipe();
		recipe3.setName("Latte");
		recipe3.setAmtChocolate("0");
		recipe3.setAmtCoffee("3");
		recipe3.setAmtMilk("3");
		recipe3.setAmtSugar("1");
		recipe3.setPrice("100");

		//Set up for r4
		recipe4 = new Recipe();
		recipe4.setName("Hot Chocolate");
		recipe4.setAmtChocolate("4");
		recipe4.setAmtCoffee("0");
		recipe4.setAmtMilk("1");
		recipe4.setAmtSugar("1");
		recipe4.setPrice("65");

		//Set up for r5 (added by MWW)
		recipe5 = new Recipe();
		recipe5.setName("Super Hot Chocolate");
		recipe5.setAmtChocolate("6");
		recipe5.setAmtCoffee("0");
		recipe5.setAmtMilk("1");
		recipe5.setAmtSugar("1");
		recipe5.setPrice("100");

		recipeBook.addRecipe(recipe1);
		recipeBook.addRecipe(recipe2);
		recipeBook.addRecipe(recipe3);
//		recipeBook.addRecipe(recipe4);

	}


	@And("^SUT status is ([A-Z_]+)$")
	public void theSutStatusIs(CoffeeMakerUI.Status expectedStatus) {
		Assert.assertEquals(expectedStatus, coffeeMakerMain.getStatus());

	}

	@When("^user inputs (.+)$")
	public void userInputsOPTION(String userInput) {
		try {
			int inputNumber = Integer.parseInt(userInput);
			coffeeMakerMain.UI_Input(new ChooseService(inputNumber));
		} catch (NumberFormatException e) {
			// Handle the exception when the user input is not a valid number
			Assert.fail("Invalid user input format: " + userInput);
		} catch (Exception e) {
			// Handle other exceptions that UI_Input might throw
			// For example, you can log the exception and fail the test
			e.printStackTrace();
			Assert.fail("An exception occurred during user input: " + e.getMessage());
		}

//	coffeeMakerMain.UI_Input(new ChooseService(Integer.parseInt(userInput)));

	}

	@And("^SUT mode is ([A-Z_]+)$")
	public void theSutModeIs(CoffeeMakerUI.Mode expectedMode) {
		Assert.assertEquals(expectedMode, coffeeMakerMain.getMode());
	}

	@When("^user adds a recipe named (.+), Chocolate: (.+), Coffee: (.+), Milk: (.+), Sugar: (.+), Price: (.+)$")
	public void addGoodRecipe(String name, String chocolate, String coffee, String milk, String sugar, String price) throws Throwable {
//		coffeeMakerMain.UI_Input(new ChooseService(1));
		try {
			//Set up for r1
			recipe1 = new Recipe();
			recipe1.setName(name);
			recipe1.setAmtChocolate(chocolate);
			recipe1.setAmtCoffee(chocolate);
			recipe1.setAmtMilk(milk);
			recipe1.setAmtSugar(sugar);
			recipe1.setPrice(price);
		}
		catch (Exception e){}

		coffeeMakerMain.UI_Input(new DescribeRecipe(recipe1));
	}

	@Then("recipe was added")
	public void recipeAdded(){
		Assert.assertEquals(coffeeMakerMain.getMode(), CoffeeMakerUI.Mode.WAITING);
		Assert.assertEquals(coffeeMakerMain.getStatus(), CoffeeMakerUI.Status.OK);
	}


	@When("recipe was not added")
	public void recipeNotAdded() {
		Assert.assertEquals(coffeeMakerMain.getMode(), CoffeeMakerUI.Mode.WAITING);
		Assert.assertEquals(coffeeMakerMain.getStatus(), CoffeeMakerUI.Status.RECIPE_NOT_ADDED);
	}


	@When("user adds an empty recipe")
	public void emptyRecipe() {
//		coffeeMakerMain.UI_Input(new ChooseService(1));
		try {
			recipeDetailed = new Recipe();
			recipeDetailed.setName(null);
			recipeDetailed.setAmtChocolate(null);
			recipeDetailed.setAmtCoffee(null);
			recipeDetailed.setAmtMilk(null);
			recipeDetailed.setAmtSugar(null);
			recipeDetailed.setPrice(null);
			coffeeMakerMain.UI_Input(new DescribeRecipe(recipeDetailed));
		}
		catch (Exception e) {}
	}

	@When ("^user adds coffee (.+), milk (.+), sugar (.+), chocolate (.+)$")
	public void userAddsInventory(String coffee, String milk, String sugar, String chocolate ){
//		coffeeMakerMain.UI_Input(new ChooseService(4));
try {
	coffeeMakerMain.UI_Input(new AddInventory(Integer.parseInt(coffee), Integer.parseInt(milk), Integer.parseInt(sugar), Integer.parseInt(chocolate)));
}
catch (Exception e){}
}

	@When ("^user adds null inventory$")
	public void userAddsNullInventory(){
//		coffeeMakerMain.UI_Input(new ChooseService(4));
		try {
			coffeeMakerMain.UI_Input(new AddInventory(Integer.parseInt(null), Integer.parseInt(null), Integer.parseInt(null), Integer.parseInt(null)));
		}
		catch (Exception e){}
	}

	@When ("^user deletes recipe (.+)$")
	public void userDeletesRecipe(String recipe) {
		coffeeMakerMain.UI_Input(new ChooseService(2));
		coffeeMakerMain.UI_Input(new ChooseRecipe(1));
	}

	@When("^user edits recipe (.+)$")
	public void userEditsRecipe(String recipe)  {
//		coffeeMakerMain.UI_Input(new ChooseService(3));
		try {
			coffeeMakerMain.UI_Input(new ChooseRecipe(Integer.parseInt(recipe)));
			recipe1 = new Recipe();
			recipe1.setName("name");
			recipe1.setAmtChocolate("1");
			recipe1.setAmtCoffee("1");
			recipe1.setAmtMilk("1");
			recipe1.setAmtSugar("1");
			recipe1.setPrice("1");
		}
		catch (Exception e){}

		coffeeMakerMain.UI_Input(new DescribeRecipe(recipe1));
	}

	@When("^user verifies the added recipes$")
	public void verifyRecipes() throws Throwable{
//			String s ="1. Coffee\n2. Hot Chocolate\n";
			coffeeMakerMain.displayRecipes();
			assertEquals("FOUR", coffeeMakerMain.getRecipes()[0].getName());
			assertEquals("FIVE", coffeeMakerMain.getRecipes()[1].getName());
	}

	@When("^edit a recipe with wrong values$")
	public void editWrongValues()  {
		try {
			recipe1 = new Recipe();
			recipe1.setName("name");
			recipe1.setAmtChocolate("-1");
			recipe1.setAmtCoffee("-1");
			recipe1.setAmtMilk("-1");
			recipe1.setAmtSugar("-1");
			recipe1.setPrice("-1");
		}
		catch (Throwable t){}
		coffeeMaker.editRecipe(1, recipe1 );
	}

	@When("^user compares inventory to coffee (.+), milk (.+), sugar (.+), chocolate (.+)$")
	public void compareInventory(String coffee, String milk, String sugar, String chocolate){

//		String inventory =  new AddInventory(Integer.parseInt(coffee), Integer.parseInt(milk), Integer.parseInt(sugar), Integer.parseInt(chocolate));
//		String inventory = new Inventory(Integer.parseInt(coffee), Integer.parseInt(milk), Integer.parseInt(sugar), Integer.parseInt(chocolate));

		String inventory = coffeeMaker.checkInventory();
     	System.out.println(inventory);
//#      ciCmd.setInventory(result);
	}

	@When("^user selects recipe number (.+)$")
	public void selectRecipe(String recipe){
		coffeeMakerMain.UI_Input(new ChooseRecipe(Integer.parseInt(recipe)));
	}

	@When ("^user inserts (.+) units of money$")
	public void insertMoney(String money){
		coffeeMakerMain.defaultCommands(new InsertMoney(Integer.parseInt(money)));
//		coffeeMakerMain.UI_Input(new InsertMoney(Integer.parseInt(money)));

	}

	@When("^user takes money from tray$")
	public void takeMoneyFromTray(){
		coffeeMakerMain.UI_Input(new TakeMoneyFromTray());
	}

	@When("^user resets SUT$")
	public void resetSUT(){
		coffeeMakerMain.UI_Input(new Reset());
	}

}

