In the program we are dealing with a problem, which is known as The Single Lane Bridge. In this problem, we have Threads that are represented as Red and Blue cars and they are coming from both sides of a bridge which they want to cross it. The main issue is that only one car at a time is able to cross the bridge. In this essay we developt four different scenarios, that have to 
do with the way that the cars (Threads) are passing the bridge. 


Scenario 1: CrashingCars.java
	In this scenario cars (Threads) are comming all together and they are trying to cross the bridge without "locking" the part of the code that they execute. 
	In other words every Thread is avaliable to enter a method that another Thread hasn't finished executing yet. As a result we can see more than one car entering and exiting 
	from bridge. 

	Example:
 
	Left Side			Bridge			Right Side
	Red Car 1 Arrived at Time1
								Blue Car 1 Arrived at Time2
	Red Car 2 Arrived at Time3
								Blue Car 2 Arrived at Time4
	Red Car 3 Arrived at Time5
	Red Car 4 Arrived at Time6

	Red Car 1 Passing at Time7 

								Blue Car 2 Passing at Time9 

	Red Car 2 Passing at Time9 
								Red Car 4 Passed at Time15 
	Blue Car 4 Passed at Time14 

	Red Car 4 Passing at Time13 
								Red Car 4 Passed at Time16 

								Blue Car 2 Passing at Time11 
	Blue Car 4 Passed at Time17 

	Red Car 3 Passing at Time12 
								Red Car 4 Passed at Time18 
								Red Car 2 Passed at Time10 
	
Scenario 2: UnfairBridge.java
	Here we have made the Threads to create a Critical Section by locking and unlocking a specific part of code. With this way, a Thread can't
	join the Critical Section that another Thread is executing, even if the other Thread is sleeping. So only one car is able to cross the bridge and we don't have
	crashes. On the other hand there isn't a line or code to define which turn is it, and the cars are crossing the bridge with an undefined row.

	Example: 

	Left Side			Bridge			Right Side
	Red Car 1 Arrived at Time1
								Blue Car 1 Arrived at Time2
	Red Car 2 Arrived at Time3
								Blue Car 2 Arrived at Time4
	Red Car 3 Arrived at Time5
								Blue Car 3 Arrived at Time6
	Red Car 4 Arrived at Time7
	Red Car 5 Arrived at Time8

	Red Car 1 Passing at Time9 
								Red Car 1 Passed at Time10 

	Red Car 3 Passing at Time11 
								Red Car 3 Passed at Time12 

								Blue Car 3 Passing at Time13 
	Blue Car 3 Passed at Time14 

	Red Car 5 Passing at Time15 
								Red Car 5 Passed at Time16 

	Red Car 4 Passing at Time17 
								Red Car 4 Passed at Time18 

								Blue Car 2 Passing at Time19 
	Blue Car 2 Passed at Time20 

								Blue Car 1 Passing at Time21 
	Blue Car 1 Passed at Time22 

	Red Car 2 Passing at Time23 
								Red Car 2 Passed at Time24 


Scenario 3: FairBridge.java
	In FairBridge class we made the cars crossing the bridge by switching types. In another way, if a Red car passed the bridge then the next one must be blue. Of course, 
	if any of the types is out of cars the other won't stop and it will continue until all of it's cars cross the bridge. Furthermore, in this scenario we used synchronized and wait to prevent
	the Threads from entering the part of code that another Thread was trying to execute, and notify to restart them. Also to define which type will start first, we used the total amount of cars that it's has and we give priority 
	to the one with the biggest number.

	Example: 

	Left Side			Bridge			Right Side
	Red Car 1 Arrived at Time1
								Blue Car 1 Arrived at Time2
	Red Car 2 Arrived at Time3
								Blue Car 2 Arrived at Time4
	Red Car 3 Arrived at Time5
								Blue Car 3 Arrived at Time6
								Blue Car 4 Arrived at Time7
								Blue Car 5 Arrived at Time8

								Blue Car 1 Passing at Time9 
	Blue Car 1 Passed at Time10 

	Red Car 1 Passing at Time11 
								Red Car 1 Passed at Time12 

								Blue Car 2 Passing at Time13 
	Blue Car 2 Passed at Time14 

	Red Car 2 Passing at Time15 
								Red Car 2 Passed at Time16 

								Blue Car 3 Passing at Time17 
	Blue Car 3 Passed at Time18 

	Red Car 3 Passing at Time19 
								Red Car 3 Passed at Time20 

								Blue Car 4 Passing at Time21 
	Blue Car 4 Passed at Time22 

								Blue Car 5 Passing at Time23 
	Blue Car 5 Passed at Time24 

Scenario 4 PerfectBridge.java
	In the last scenario we want to cover the issues from the previous ones that we did on purpose to understand how Threads are working. The main goal here is that the cars in the most
	populated side of the bridge are going to cross it with a higher frequency than the cars from the other side. In this case we check the difference between the total amount of cars from each
	side and if the difference is bigger or equal to two then we let two cars from the most populated side and one from the other to cross the bridge. But when our difference is smaller than two, then we 
	return to previous method and the cars are crossing the bridge by switching types (Scenario 3). Of cource this technique won't mean that the Threads are going to enter all together on bridge but only one at a time
	will be able to execute a specific part of code, this again is happening with the help of synchronized and wait, as for the restart of the other Threads we use notify.

	Example: 
	
	Left Side			Bridge			Right Side
	Red Car 1 Arrived at Time1
								Blue Car 1 Arrived at Time2
	Red Car 2 Arrived at Time3
								Blue Car 2 Arrived at Time4
	Red Car 3 Arrived at Time5
	Red Car 4 Arrived at Time6
	Red Car 5 Arrived at Time7
	Red Car 6 Arrived at Time8
	Red Car 7 Arrived at Time9

	Red Car 1 Passing at Time10 
								Red Car 1 Passed at Time11 

	Red Car 2 Passing at Time12 
								Red Car 2 Passed at Time13 

								Blue Car 1 Passing at Time14 
	Blue Car 1 Passed at Time15 

	Red Car 3 Passing at Time16 
								Red Car 3 Passed at Time17 

	Red Car 4 Passing at Time18 
								Red Car 4 Passed at Time19 

								Blue Car 2 Passing at Time20 
	Blue Car 2 Passed at Time21 

	Red Car 5 Passing at Time22 
								Red Car 5 Passed at Time23 

	Red Car 6 Passing at Time24 
								Red Car 6 Passed at Time25 

	Red Car 7 Passing at Time26 
								Red Car 7 Passed at Time27


There are a few more classes that we haven't explain yet. First of all we have Main.java, where we create a random number of
cars for each type. In this class we let user to choose which scenario wants our programm to run and based on his answer we
create the corresponding bridge. After that, every car (Thread) uses start method to start executing the code which exists inside run.
Moving on we have Red_Car.java and Blue_Car.java classes which we extend to Thread, so we could make our cars represented as Threads.
Also in this classes we can find the method run. In the end, we have SupportClass.java where we just define which type of car 
will go first, based on the total amount of cars for each type.


Other solutions that I used as an inspiration:
1)https://stackoverflow.com/questions/46579280/crossing-single-lane-bridge-with-pairs-of-threads-java-semaphore 
2)https://www.doc.ic.ac.uk/~jnm/book/book_applets/SingleLaneBridge.html 
