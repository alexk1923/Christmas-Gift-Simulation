Object Oriented Programming Course
Homework - Santa Claus is coming to ACS students (Project - First Stage)
KULLMAN ROBERT - ALEXANDRU, 323CA
January 2022

# Info
https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa1

# Input format

The input is provided in a JSON format and it needs to be parsed by the app


# Input classes

	* Database
		* the fields are declared based on the input tests, 
  		* its role is to parse the input fulfilling ObjectMapper requirements

	* DataChange
		* getters and setters also used by the Object Mapper
		* contains a list of children and a list of Santa's gift list
	* AnnualChange
		* getters and setters also used by the Object Mapper
		* contains the changes for a year (santa budget, new gifts, new children,
		  updates for children)
	* ChildUpdate
		* getters and setters also used by the Object Mapper
		* contains the id of the child for whom the nice score or gifts preferences
		  will change


# Simulation related classes:

	* Simulator
		* it will run a simulation for a given number of years using the database
		* the main method is **execute** which is called in Main class, the starting
  		  point for this application
		* **execute** get the number of years to be simulated and store the output
		  data in a variable. After all rounds have been simulated, an object mapper
		  is used to write a json file in the output path.
  		* **calcTotalAverageScore** iterates through an array of children given as
		  a paramater and get the sum of all average scores
		* **updateScores** iterates through an array of children and apply a strategy
		  depending on the current child age and apply nice score bonus, then set its
		  field to the returned value of the average score
		* **firstRound** method is used for a specific case, when nothing has to be
  		  updated
			* all adults are removed from the database
			* all nice scores are added into theirs history
			* call **updateAverageScores** method for the children list
			* get the total average score calling **calcTotalAverageScore&& method
			* calculate the budget unit using the formula provided in the application
			  requirements
			* use a Santa instance and set its initial data using the modified database
			* call **giveGifts** method to distribute the gifts
		* **round** is the main method called when simulating every round
			* verify if is the first round and call the corresponding method
			* get the Santa instance and update using annual changes from the database
			* call **giveGifts** method to distribute the gifts
	* Santa
		* this class is an observable and manage the children and gift lists through
		  the simulation
		* it has a single instance, because a single database/santa is needed
		* it has two double type fields to store the santa budget and the budget unit
		  for the current round
		* the list of children contains observers which will be updated every time a
		  year has passed
		* **notiftyObserver** is calling **update** method for each child
		  (observer)
		* **setData** is used to update all fields of the instance
		* **annualUpdate** is based on the annual change list from the database
			* update Santa's budget
			* add new gifts to the list
			* remove all children who will become adults this year
			* notify all children about the changes made to the observable
			* add new children from the annual change to the Santa's list
			* update average scores for the current round
			* calculate the budget unit
			* check the annual change strategy and order children array list
		* **applyAssignStrategy**
			* call a method based on the strategy given as a parameter
		* **giveGifts** returns a list with modified children in an output format
			* calculate individual budget for a child and call a method to apply
			  the elf bonus
			* iterates through the preferences of each child and calls a method to find
			  the cheapest gift from a category
			* add the gift to the receivedGifts list if the Santa still has money to
			  buy it
			* add the ChildOutput object to the list
		* **getCheapestGiftByCategory**
			* deep copy the gift list
			* sort it by price
			* get the cheapest gift for the category given as a parameter that has the
			  quantity greater than 0
		* **getFirstCheapestGiftByCategory**
			* deep copy the gift list
			* sort it by price
			* get the cheapest gift for the category given as a parameter even it has
			  the quantity equals to 0
	* Child
		* is an observer which implements update method
		* getters and setters also used by the Object Mapper
		* **update** is based on the changes provided in the second paramater
			* increase the child's age
			* for each element in the updates list, verify if it matches the child id
			* if it does:
				* add the nice score from the update to history list if it is not null
				* update the gift preferences if there are any new ones and remove
			  	  duplicates
		* **calcElfTotalBudget** update the child budget based on a negative
		  amplifier for a black elf and on a positive one for a pink elf
			
	* Gift
		* getters and setters also used by the Object Mapper

# Strategy Classes
	* AverageScoreStrategyFactory
		* creates a strategy based on the age given as a parameter
	* BabyAverageScore, KidAverageScore, TeenAverageScore
		* implements an average score strategy which calculates and return a value
		  depending on the category in which the child falls
	* NiceScoreStrategy
		* order the given array list based on average score for each child
	* CityScoreStrategy
		* order children depending on which city they live
		* **getCitiesByScore** method creates a hashmap containing each pair has
		  a city as a key and a list of average scores of the children living in that
		  city
		
	


# Output classes
	* OutputData
		* a list containing ChildOutputList objects
		* * getters and setters also used by the Object Mapper
	* ChildOutput
		* a class with required fields of the output file
		* constructor to create a new o
		* getters and setters also used by the Object Mapper
	* ChildOutputList
		* contains a list with ChildOutput objects
		* getters and setters also used by the Object Mapper
	* GiftOutput
		* a gift type object with only 3 fields (name, price, category)
		* getters and setters also used by the Object Mapper

			
	
		
	
		

