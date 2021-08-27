<style type="text/css">
body {
    zoom: 1.2;
    line-height: 1.5;
    padding: 2em 4em;
    font-size: 12px;
    line-height: 1.5;
    max-width: 800px;
    margin: 0 auto;
    word-wrap: break-word;
}

</style>

[timer](http://www.intervaltimer.com/timers/9835337-interview)

# 系统设计最好的模板

## Most important feature when designing a mobile app/feature
- reliable
- scalable 
- maintainable
- power efficiency
- customizable
- cost efficiency
- secure

## Use case and technology alternative
- storage
	+ database, room
	+ sharedpreferences
	+ file
- offline
	+ store and retry
	+ discard
- reminder
	+ notification
- background/schedule tasking
	+ immediate
		+ Kotlin Coroutine
		+ Foreground Service
		+ WorkManager
	- deferred
		+ DownloadManager
	- exact	
		+ AlarmManager
- idle mode
- dependency
	+ dagger
	+ hilt
- location
	- The fused location provider
	- locationListener

	```
	var dir: Double,
    var fix: Int, accuracy
    var hdop: Double,
    var lat: Double,
    var lon: Double,
    var pSpd: Double,
    var rssi: Double?,
    var sat: Int, number of satallat
    var spd: Double, // kmph
    var timestamp: Long, // system timestamp when data created
    var gpsTime: Long // gps time of this data
	```
- end to end encryption
	When you first register on Signal, a private 'identity key' is generated on your phone, as well as a bunch of public 'prekeys' that are uploaded to the server and sent to your contacts whenever they initiate a new conversation with you or you initiate a new conversation with them.

	When you add a new instance of Signal Desktop to your account, the desktop client 1) generates a keypair, 2) encodes the public key as a QR code, 3) you scan it with your phone, 4) the phone encrypts your private 'identity key' to the desktop client’s public key and uploads the encrypted key to the Signal server, 5) the desktop client then downloads and decrypts your 'identity key' and uses it to generate a new set of public 'prekeys' that are uploaded to the server.		

## Android System Design Template 1
![](https://miro.medium.com/max/1400/1*qQnrlqfAmSE1GcTFZkQuuw.png)
### Business idea 3–4 minutes

	Ability to think about the business part of the task. WHY

- The list of services we sell to our users
- The data entities access we sell to our users
- Understanding the main idea of the product or the feature (if you don't understand something, don't hesitate to ask all the needed questions. The more you know about the product the easier will be making decisions during the the rest of the process)

### Requirements clarification 1–2 minutes

	The requirements clarification process should be driven by definite and clear aims, questions shouldn't be random, but should rather follow some system.
- Availability 
	+ country
		- Europe/ gdpr
		- China, google play store
		- India, low internet speed and coverage
	+ OS/language
		- Lollipop 21, permission
		- google play store needs new app *API level 28* above
	+ Multiple Lauguage & cultures
		- especially Persian
	+ offline
	+ phone/tablet
- Testability 
	+ CI automation test
	+ do we have QA already?
	+ do they have related experiences?
	+ do need training?

- Scalability/Performance
	+  scalability of our team
		- how many team do we have involving this feature
		- how do we separate feature across teams
		- how do we reuse our code
- Security 
	+ how security requirement for our data
	+ wss, htps, tls



### Mathematical model design (if needed)
### High level system design 3–5 minutes
	Define how to split that state and functionality between the server and the client sides :

- define communication protocol
	+ bidirectional or unidirectional, 
	+ streaming or request-response-based,
	+ then more details — HTTP REST (simple, long-polling), web-socket, raw-socket,

Always provide information about the alternatives and defend your choice

![](https://miro.medium.com/max/1400/1*LpI0PQLJ-WabMA_YUAW6pw.png)
### API design  7–10 minutes
### High level client side design 10

Always provide information about the alternatives and defend your choice

![](https://miro.medium.com/max/1400/1*RJddvC5szQ_mnxROUEupNA.png)
### Detailed design of some modules 10

### One complex case and detailed discussion

## Android System Design Template 2

### Define the scope of the problem.​ 5 minutes
<details><summary>**Why is ti? What is it?**</summary>

What are the basic requirements of the system? How will the app deliver updates to listening friends? What data, battery and privacy concerns will it face? Should it work constantly in the background, or only when active and in the foreground?​
</details>
 
 
### HIGH LEVEL DESIGN (5-10 min)
<details><summary>**how** </summary>


### Explore the solution space.​ 5 minutes
What kind of data will we need to track? What kind of database options do we have? What are their advantages and disadvantages? Which one would you choose?​

#### Ensure the solution is complete.​ 

Does this design leave an entire area of the system undescribed? Does it cover the “send location update” use case end-to-end? How about “receive location update”? 

 

#### Ensure the different components of the system are well-defined.​

Are the responsibilities between components clear? Would different people be able to work on different components independently? Does the architecture help the system scale in the long-term? What pieces are Activities, and what pieces are Services? How do they interact?

 
#### Evaluate trade-offs as you make decisions​

What is this system optimal for? What are its drawbacks? How would you scale this system to new use cases (e.g.: discovering friends in your area?)​

</details>

### DEEP DIVE (15-20 min)
<details><summary>**iterate every component** </summary>
</details>

### Justify your decisions, but be flexible to new information.​ 5 minutes

Would you change your design based on a new requirement? Can you do a rough calculation to justify why you chose one architecture vs the other (e.g.: push vs pull)?



## How would you design Instagram / Instagram Stories
- Instagram/Instragram stories

## How would you design Facebook

## How would you design Facebook Messenger

- peers online
- message sent
- message read

## How would you design Facebook's live update of comments on posts

## How would you design an online collaborative editor (e.g. Google Docs)

## How would you design a typehead feature (e.g. Google search autocomplete)

## How would you design Twitter's trending topics

## How would you design a distributed Botnet

## How would you design a system that can handle millions of card transactions per hour

## How would you design security for Facebook's corporate network from scratch (Security team interview)


## Cloud system design 

### FEATURE EXPECTATIONS (5 min)
<details><summary>FEATURE EXPECTATIONS (5 min)  </summary>
1. Use cases  
2. Scenarios that will be/not be covered  
3.  Who will use  
4. How many will use  
5. Usage patterns  

What are the basic requirements of the system? How will the app deliver updates to listening friends? What data, battery and privacy concerns will it face? Should it work constantly in the background, or only when active and in the foreground?​

some notes:
- Lollipop 21, permission
- google play store needs new app *API level 28* above
 
</details>

### ESTIMATIONS (5 min)
<details><summary> ESTIMATIONS (5 min)  </summary>

1. Throughput (QPS for read and write queries)  

2. Latency expected from the system (for read and write queries)  

3. Read/Write ratio  

4. Traffic estimates  

	- Write (QPS, Volume of data)  
	- Read (QPS, Volume of data)  

5. Storage estimates  

6. Memory estimates  

	- If we are using a cache, what is the kind of data we want to store in cache  

	- How much RAM and how many machines do we need for us to achieve this ?  

	- Amount of data you want to store in disk/ssd  

</details>

### DESIGN GOALS (5 min)
<details><summary> DESIGN GOALS (5 min) </summary>

1. Latency and Throughput requirements  

2. Consistency vs Availability [Weak/strong/eventual => consistency | Failover/replication => availability]  
</details>

### HIGH LEVEL DESIGN (5-10 min)
<details><summary> HIGH LEVEL DESIGN (5 min)  </summary>

1. APIs for Read/Write scenarios for crucial components  

2. Database schema  

3. Basic algorithm  

4. High level design for Read/Write scenario  
</details>

### DEEP DIVE (15-20 min)
<details><summary> 
- Scaling the algorithm
- Scaling individual components
- Component

</summary>

1. Scaling the algorithm  

2. Scaling individual components:  

	- -> Availability, Consistency and Scale story for each component  

	- -> Consistency and availability patterns  

3. Think about the following components, how they would fit in and how it would help  

	- a. DNS  

	- b. CDN [Push vs Pull]  

	- c) Load Balancers [Active-Passive, Active-Active, Layer 4, Layer 7]  

	- d) Reverse Proxy  

	- e) Application layer scaling [Microservices, Service Discovery]  

	- f) DB [RDBMS, NoSQL]  

		+ > RDBMS  
		+ >> Master-slave, Master-master, Federation, Sharding, Denormalization, SQL Tuning  

		+ > NoSQL  
		+ >> Key-Value, Wide-Column, Graph, Document  
		+ Fast-lookups:  
		+ >>> RAM [Bounded size] => Redis, Memcached  
		+ >>> AP [Unbounded size] => Cassandra, RIAK, Voldemort  
		+ >>> CP [Unbounded size] => HBase, MongoDB, Couchbase, DynamoDB  

	- g) Caches  

		+ > Client caching, CDN caching, Webserver caching, Database caching, Application caching, Cache @Query level, Cache @Object level  

		+ > Eviction policies:  
		+ >> Cache aside  
		+ >> Write through  
		+ >> Write behind  
		+ >> Refresh ahead  

	- h) Asynchronism  
		+ > Message queues  
		+ > Task queues  
		+ > Back pressure  

	- i) Communication  

		+ > TCP  

		+ > UDP  

		+ > REST  

		+ > RPC  
</details>

### JUSTIFY [5 min]  
<details><summary> JUSTIFY [5 min]  </summary>

	- (1) Throughput of each layer  

	- (2) Latency caused between each layer  

	- (3) Overall latency justification  
</details>
