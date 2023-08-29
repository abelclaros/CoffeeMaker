Feature: CoffeeMakerFeature

#In this feature, we are going to test the user stories and use cases for the CoffeeMaker
#Example.  We have provided a CoffeeMakerMain.java file that you can use to examine the
#modal behavior in the coffee maker and issue UI commands to use it, so that we can
#adequately test the user stories.
#
#Hints: to catch the mistakes, you might need to add out of range cases for
#recipes and amounts of ingredients.  Also, be sure to check machine state
#after running the user story:
#  - Are the amounts of ingredients correct?
#  - Is the system in the right mode?
#  - Is the status what you expect?
#  - Is the change produced correct?
#  - etc.

Scenario Outline: Waiting State
      Priority: 1 Story Points: 2
      If the Coffee Maker is not in use it waits for user input. There are six different
      options of user input: 1) add recipe, 2) delete a recipe, 3) edit a recipe,
      4) add inventory, 5) check inventory, and 6) purchase beverage.

      For this scenario, what we will do is try each of the six user inputs and make sure
      that the coffee maker ends up in the appropriate mode.  This would be a good place
      for a scenario outline with a table that described user inputs and expected final states.

      You might also want to try a couple of exceptional values to see what the
      Coffee Maker does.

      Given an empty recipe book
      * user inputs <OPTION>
      * SUT status is <STATUS>
      * SUT mode is <MODE>
      Examples:
            | OPTION    | MODE          | STATUS      |
            |1          |ADD_RECIPE       |OK         |
            |2          |DELETE_RECIPE    |OK         |
            |3          |EDIT_RECIPE      |OK         |
            |4          |ADD_INVENTORY    |OK         |
            |5          |CHECK_INVENTORY  |OK         |
            |6          |PURCHASE_BEVERAGE |OK        |
#            |8          |WAITING         |UNKNOWN_ERROR|
#            |F          |WAITING         |UNKNOWN_ERROR|


      Scenario: A new recipe is added
            Given an empty recipe book
            * user adds a recipe named TEST, Chocolate: 2, Coffee: 1, Milk: 1, Sugar: 1, Price: 1
            * SUT mode is WAITING
            * SUT status is OK

#Scenario: Recipe with negative chocolate
#      Given an empty recipe book
#      * user adds a recipe named badchoco, Chocolate: -1, Coffee: 2, Milk: 3, Sugar: 3, Price: 3
#      * SUT mode is WAITING
#      * SUT status is RECIPE_NOT_ADDED

      Scenario: adding a recipe with the same name
            Given an empty recipe book
            * user adds a recipe named repeat, Chocolate: 1, Coffee: 1, Milk: 1, Sugar: 1, Price: 1
            * SUT mode is WAITING
            * SUT status is OK
            * user adds a recipe named repeat, Chocolate: 10, Coffee: 10, Milk: 10, Sugar: 10, Price: 10
            * SUT mode is WAITING
            * SUT status is RECIPE_NOT_ADDED

      Scenario: adding three recipes
            Given an empty recipe book
            * user adds a recipe named 1, Chocolate: 1, Coffee: 1, Milk: 1, Sugar: 1, Price: 1
            * SUT mode is WAITING
            * SUT status is OK
            * user adds a recipe named 2, Chocolate: 1, Coffee: 1, Milk: 1, Sugar: 1, Price: 1
            * SUT mode is WAITING
            * SUT status is OK
            * user adds a recipe named 3, Chocolate: 1, Coffee: 1, Milk: 1, Sugar: 1, Price: 1
            * SUT mode is WAITING
            * SUT status is OK

      Scenario: adding 4 recipes
            Given an empty recipe book
            * user adds a recipe named 1, Chocolate: 1, Coffee: 1, Milk: 1, Sugar: 1, Price: 1
            * SUT mode is WAITING
            * SUT status is OK
            * user adds a recipe named 2, Chocolate: 1, Coffee: 1, Milk: 1, Sugar: 1, Price: 1
            * SUT mode is WAITING
            * SUT status is OK
            * user adds a recipe named 3, Chocolate: 1, Coffee: 1, Milk: 1, Sugar: 1, Price: 1
            * SUT mode is WAITING
            * SUT status is OK
            * user adds a recipe named 4, Chocolate: 1, Coffee: 1, Milk: 1, Sugar: 1, Price: 1
            * SUT mode is WAITING
            * SUT status is RECIPE_NOT_ADDED
#
#Scenario: adding and empty recipe
#      Given an empty recipe book
#      * user adds an empty recipe
#      * SUT mode is WAITING
#      * SUT status is RECIPE_NOT_ADDED
#
#Scenario: Add inventory with no sugar
#      Given an empty recipe book
#      * user adds coffee 10, milk 10, sugar 0, chocolate 10
#      * SUT mode is WAITING
#      * SUT status is OK
#
      Scenario: add inventory with sugar
            Given an empty recipe book
            * user adds coffee 10, milk 10, sugar 2, chocolate 10
            * SUT mode is WAITING
            * SUT status is OK
#
#Scenario: add inventory with negative chocolate
#      Given an empty recipe book
#      * user adds coffee 1, milk 1, sugar 1, chocolate -1
#      * SUT mode is WAITING
#      * SUT status is OUT_OF_RANGE
##
      Scenario: add inventory with positive chocolate
            Given an empty recipe book
            * user adds coffee 10, milk 10, sugar 2, chocolate 2
            * SUT mode is WAITING
            * SUT status is OK
##
      Scenario: add inventory with negative milk
            Given an empty recipe book
            * user adds coffee 1, milk -1, sugar 2, chocolate 10
            * SUT mode is WAITING
            * SUT status is OUT_OF_RANGE
##
      Scenario: add inventory with negative sugar
            Given an empty recipe book
            * user adds coffee 1, milk 1, sugar -2, chocolate 10
            * SUT mode is WAITING
            * SUT status is OUT_OF_RANGE
#
#      Scenario: adding recipe with floating price
#            Given an empty recipe book
#            * user adds a recipe named float, Chocolate: 1, Coffee: 1, Milk: 1, Sugar: 1, Price: 10.5
#            * SUT mode is WAITING
#            * SUT status is RECIPE_NOT_ADDED

      Scenario: delete a recipe
            Given a default recipe book
            * user deletes recipe 1
            * SUT mode is WAITING
            * SUT status is OK

      Scenario: edit a recipe
            Given a default recipe book
            * user edits recipe 1
            * SUT mode is WAITING
            * SUT status is OK

   # add more here!

#Scenario: Add a Recipe
#      Priority: 1 Story Points: 2
#
#      Only three recipes may be added to the CoffeeMaker. A recipe consists of a name,
#      price, units of coffee, units of milk, units of sugar, and units of chocolate.
#      Each recipe name must be unique in the recipe list. Price must be handled as an
#      integer. A status message is printed to specify if the recipe was successfully
#      added or not. Upon completion, the CoffeeMaker is returned to the waiting state.
#
#      For this scenario, you should try to add a recipe to the recipe book, and check to
#      see whether the coffee maker returns to the Waiting state.
#
#      Given an empty recipe book
#      * user adds a good recipe
#      * recipe was added
#
#Scenario: Delete a Recipe
#      Priority: 2 Story Points: 1
#
#      A recipe may be deleted from the CoffeeMaker if it exists in the list of recipes in the
#      CoffeeMaker. The recipes are listed by their name. Upon completion, a status message is
#      printed and the Coffee Maker is returned to the waiting state.
#
#   Given an empty recipe book
#   # add more here!
#
#
#Scenario: Edit a Recipe
#      Priority: 2 Story Points: 1
#
#      A recipe may be edited in the CoffeeMaker if it exists in the list of recipes in the
#      CoffeeMaker. The recipes are listed by their name. After selecting a recipe to edit, the user
#      will then enter the new recipe information. A recipe name may not be changed. Upon
#      completion, a status message is printed and the Coffee Maker is returned to the waiting
#      state.
#
#   Given a default recipe book
#   # add more here!
#
#Scenario: Add Inventory
#      Priority: 1 Story Points: 2
#
#      Inventory may be added to the machine at any time from the main menu, and is added to
#      the current inventory in the CoffeeMaker. The types of inventory in the CoffeeMaker are
#      coffee, milk, sugar, and chocolate. The inventory is measured in integer units. Inventory
#      may only be removed from the CoffeeMaker by purchasing a beverage. Upon completion, a
#      status message is printed and the CoffeeMaker is returned to the waiting state.
#
#   Given an empty recipe book
#   # add more here!
#
#Scenario: Check Inventory
#      Priority: 2 Story Points: 1
#
#      Inventory may be checked at any time from the main menu. The units of each item in the
#      inventory are displayed. Upon completion, the Coffee Maker is returned to the waiting state.
#
#   Given an empty recipe book
#   # add more here!
#
#Scenario: Purchase Beverage
#      Priority: 1 Story Points: 2
#
#      The user selects a beverage and inserts an amount of money. The money must be an
#      integer. If the beverage is in the RecipeBook and the user paid enough money the
#      beverage will be dispensed and any change will be returned. The user will not be able to
#      purchase a beverage if they do not deposit enough money into the CoffeeMaker. A user's
#      money will be returned if there is not enough inventory to make the beverage. Upon
#      completion, the Coffee Maker displays a message about the purchase status and is
#      returned to the main menu.
#
#   Given an empty recipe book
#   # add more here!
#

## Add scenarios from the Use Cases here.  These can be Cucumber versions of the unit
## tests that were required for course 1, or can be more direct expressions of the use
## case tests found in the Requirements-coffeemaker.pdf file.