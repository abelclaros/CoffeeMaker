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
import edu.ncsu.csc326.coffeemaker.UICmd.AddInventory;
import edu.ncsu.csc326.coffeemaker.UICmd.ChooseRecipe;
import edu.ncsu.csc326.coffeemaker.UICmd.ChooseService;
import edu.ncsu.csc326.coffeemaker.UICmd.DescribeRecipe;
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
	private Recipe recipeGood;
	private Recipe recipeBad;
	private Recipe recipeEmpty;

	private Recipe recipeDetailed;
	private CoffeeMakerUI coffeeMakerMain;
	private CoffeeMaker coffeeMaker;
	private RecipeBook recipeBook;


	private void initialize() {
		recipeBook = new RecipeBook();
		coffeeMaker = new CoffeeMaker(recipeBook, new Inventory());
		coffeeMakerMain = new CoffeeMakerUI(coffeeMaker);
	}

	@Given("^an empty recipe book$")
	public void an_empty_recipe_book() {
		initialize();
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

	@When("^user inputs (\\d+)$")
	public void userInputsOPTION(int userInput) {
		coffeeMakerMain.UI_Input(new ChooseService(userInput));
	}

	@And("^SUT mode is ([A-Z_]+)$")
	public void theSutModeIs(CoffeeMakerUI.Mode expectedMode) {
		Assert.assertEquals(expectedMode, coffeeMakerMain.getMode());
	}

	@When("^user adds a recipe named (.+), Chocolate: (.+), Coffee: (.+), Milk: (.+), Sugar: (.+), Price: (.+)$")
	public void addGoodRecipe(String name, String chocolate, String coffee, String milk, String sugar, String price) throws Throwable {
		coffeeMakerMain.UI_Input(new ChooseService(1));

		//Set up for r1
		recipe1 = new Recipe();
		recipe1.setName(name);
		recipe1.setAmtChocolate(chocolate);
		recipe1.setAmtCoffee(chocolate);
		recipe1.setAmtMilk(milk);
		recipe1.setAmtSugar(sugar);
		recipe1.setPrice(price);

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
	public void emptyRecipe() throws Throwable{
		coffeeMakerMain.UI_Input(new ChooseService(1));
		recipeDetailed = new Recipe();
		recipeDetailed.setName("0");
		recipeDetailed.setAmtChocolate("0");
		recipeDetailed.setAmtCoffee("0");
		recipeDetailed.setAmtMilk("0");
		recipeDetailed.setAmtSugar("0");
		recipeDetailed.setPrice("0");
		coffeeMakerMain.UI_Input(new DescribeRecipe(recipeDetailed));
	}

	@When ("^user adds coffee (.+), milk (.+), sugar (.+), chocolate (.+)$")
	public void userAddsInventory(String coffee, String milk, String sugar, String chocolate ){
		coffeeMakerMain.UI_Input(new ChooseService(4));
		coffeeMakerMain.UI_Input(new AddInventory(Integer.parseInt(coffee), Integer.parseInt(milk), Integer.parseInt(sugar), Integer.parseInt(chocolate)));
	}

	@When ("^user deletes recipe (.+)$")
	public void userDeletesRecipe(String recipe) {
		coffeeMakerMain.UI_Input(new ChooseService(2));
		coffeeMakerMain.UI_Input(new ChooseRecipe(1));
	}

	@When("^user edits recipe (.+)$")
	public void userEditsRecipe(String recipe) throws Throwable{
		coffeeMakerMain.UI_Input(new ChooseService(3));
		coffeeMakerMain.UI_Input(new ChooseRecipe(Integer.parseInt(recipe)));

		recipe1 = new Recipe();
		recipe1.setName("name");
		recipe1.setAmtChocolate("1");
		recipe1.setAmtCoffee("1");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("1");

		coffeeMakerMain.UI_Input(new DescribeRecipe(recipe1));
	}
}

