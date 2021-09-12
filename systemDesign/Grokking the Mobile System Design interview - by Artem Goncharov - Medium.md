[medium.com](https://medium.com/@goncharov.artemv/grokking-the-mobile-system-design-interview-6a06fa94491b "Grokking the Mobile System Design interview | by Artem Goncharov")

# Grokking the Mobile System Design interview | by Artem Goncharov

# Grokking the Mobile System Design interview

![Artem Goncharov][1]

1. Purpose
2. How mobile system design interview usually looks like
3. Common recommendations
4. Proposed interview answer plan
* Business idea
* Requirements clarification
* Mathematical model design (if needed)
* High level system design
* API design
* High level client side design
* Detailed design of some modules
* One complex case and detailed discussion

5\. Summary

6\. References

# Purpose

Being the mobile developer you may know that it's a problem to find any preparation materials for mobile system design interview. There are plenty of videos about backend system design interviews and just few of them about mobile one. The same situation is with the articles, one can find a lot of articles and even courses that would help to be prepared to the backend system design interview and there is no articles at all about mobile system design interviews. That's why I thought it would be great to share my experience of the preparation and passing the mobile system design interviews.

Sure thing this article is just one way of grokking the interview, and the outcome will depend not only on information you've got from this article but on your knowledge, skills and on interviews skills and knowledge as well, so don't consider this info as a silver bullet.

# How mobile system design interview usually looks like

First let's explore how mobile system design interview looks like, what are the input, the process and the expectations.

## Process

Usually the interview takes about 45 minutes:

* 5 minutes - acquaintance
* 5–10 minutes - defining the task
* 25–30 minutes - solution
* 5 minutes - your questions to an interviewer.

Commonly your task is to design the mobile application or a part of the application and sometimes to design some part of backend that is relevant to the mobile application as well. An interviewer lets you know all the needed information during the second 5–10 minutes, the input can vary from company to company.

## Input

Let's see what can be provided to you:

1. **The idea of the new feature or of the whole application**. Usually it sounds like — "Let's design the Instagram like application" or "Given you work in Twitter let's add the the feature of combining some of your posts to stories". Nothing more you commonly hear from the interviewer, all additionally info you need to get from them using properly formulated questions — the technique will be described later in the article.
2. **The sketches of the screens **needed to implement the idea from the first item. It's a very important piece of the information and you have to be very attentive in order to absorb all the nuances.
3. **Some requirements and limitations**, like in what extent you need to design the system — mobile only, or mobile and API only, or mobile and backend.

That's it, no more input you most likely have from the interviewer.

## Expectations

High-level expectations:

1. Ability to understand and solve complex problems
2. Communicate clearly and effectively
3. Having relevant experience and knowledge

The way of going deeper from this abstract definitions mainly depends on the interviewer, theirs skillset, experience and position in the company. They can define complex problems very differently, but we will try to formulate the most common concrete signals you should demonstrate.

For the first item, in order to show your ability to understand and solve complex problems, you should demonstrate the following:

1. **Ability to think about the business part of the task** as this is the first step in understanding the problem and answering the question "Why?". You would show the attitude to understand the business purpose of the feature or the whole application and apply this knowledge in the process of clarifying or generating the technical requirements.
2. **Working well with the vague requirements.** The interviewer usually gives you very less information and this info is definitely not enough to even start designing, thus you should be able to ask proper questions in order to clarify the requirements. The requirements clarification process should be driven by definite and clear aims, questions shouldn't be random, but should rather follow some system.
3. **Ability to synthesise the solution for a given problem**. In the first two items you demonstrated ability to understand the problem, next step is to apply all the available knowledge and design a reasonable solution. You would synthesise the design using the "top to bottom" approach in order to provide the context and the main conceptions first, and then, using divide and conquer technics, gradually split the system to smaller and smaller pieces.

The second item — effective and clear communication — could include:

1. **Voice communication.** You should speak for about 30 minutes without any big pauses explaining all your solutions and ideas with enough confidence, knowledge and ability to defend your ideas
2. **Whiteboard communication.** A whiteboard is super helpful to document all your thoughts so you can return back to any step and see what you decided earlier and whether all of your decisions are consistent.

With regards to the relevant experience and knowledge:

1. **Have experience in the development of the huge mobile applications or better the whole client-server systems**. You are expected to show the knowledge of the application to server communications design (one-way, two-ways, API design), pagination, cache and state management, modularisation techniques, different mobile application design patterns and their pros and cons. In addition here you can demonstrate knowledge of the most popular problems in mobile apps and methods of avoiding them (like state mutation, storing of the big images in the app state and overuse of singletons and god objects).
2. **Ability to foresee the problems or difficulties in the solution. **Some solutions can be very hard to implement and you are expected to clearly communicate about it, explain where the possible problem or the trade-off is.
3. **Have experience working with the different quality assurance and risk mitigation techniques.** Here you are expected to mention how you make your design good for testing, blue-green deployments, fail fast, etc.
4. **Ability to design the modules on the class level**, awareness of the main principles and design patterns. Very unlikely you will have time to do this but still there is a chance the interviewer will ask you questions like "How would you implement updating of the unread messages count label?" — you will need to decide will it be observer pattern or delegate or polling or whatever else it could be and draw the simple diagram.

# Common recommendations

Summarising the most popular recommendations companies provide before the interview:

1. You need to gather as many requirements as possible from your interviewer before starting designing. Don't hesitate to ask as many questions as needed.
2. You should show your knowledge so it's possible to start from the part of design you know the best.
3. You shouldn't be silent, speak all the time, communicate clearly, don't wait for hints from the interviewer.
4. Always provide information about the alternatives and defend your choice.

# Proposed interview answer plan

I believe that if in your company you have a good tradition to do a design review before implementation of any big (or cross team) feature you can notice that actually a system design interview looks like a complex design review along with the preliminary work when you gather the requirements, think about the design and draw diagrams.

So you can conduct design review for 3–5 times and it should be enough to understand the idea. But if you don't have such an option let's explore the good plan of your system design interview.

This could be the high level plan:

1. Business idea (what we are selling to user?, usually it's the services and the data) : 3–4 minutes
2. Requirements clarification (5 dimensions to help with limitations) : 1–2 minutes
3. Designing of a mathematical model (if needed) : 0–5 minutes
4. Define how to split that state and functionality between the server and the client sides : 3–5 minutes
5. API design : 7–10 minutes
6. High level client side design (layered structure) : 4–5 minutes
7. Detailed design of the one chosen module : 4–8 minutes
8. One complex/tricky case and detailed discussion : 5–10 minutes

Let's explore each item from the below plan in more detail.

## Business idea

This is mainly to understand the context, to formulate what exactly we are going to sell users (what kind of services and data) and to start thinking about the task from the very top. The outcome of this step is as the following:

1. The list of services we sell to our users
2. The data entities access we sell to our users
3. Understanding the main idea of the product or the feature (if you don't understand something, don't hesitate to ask all the needed questions. The more you know about the product the easier will be making decisions during the the rest of the process)

For instance, if you were asked to design the simplified WhatsApp-like application without registering/login, groups, broadcast lists, E2E encryption and reading the status then the outcome of this step would be something like this:

We sell the following services:
   
   
    - Safely storing and providing read/write access to your address book 
    - Providing the ability to make/receive Audio and Video calls to contacts from your address book 
    - Providing the ability to send/receive text messages and images to contacts from your address book and safely store the history of all the chats

_Services should be as independent from each other as possible so it will help you to design API faster. In our example all services depend on address book service but they are independent from each other._

We sell the access to the following data:
   
   
    Address book item: 
    - First name 
    - Family name 
    - Phone numberChat history message: 
    - User opponent 
    - Inbound or outbound 
    - Date and time of the message 
    - Message text or Image

So the above services list and the data entities (+ UI sketches commonly provided by the interviewer) form the basis for all your future work. The data entities are not the final ones and are not prepared to be stored or transferred by network, it's just a high level description of the data mostly from the end-user perspective.

You can start clarifing requirements right here, for instance you can ask whether we need to add user avatar image but in this case you should be ready to answer this question by your self since the interviewer can ask you back the same question and you should reason your choice.

## Requirements clarification

It's very important to gather all the possible requirements beforehand. We started doing that with the business requirements on the previous step, now we need to clarify the technical requirements, the limitations and the context. Why do we need it? Because your design should depend on the context, development processes and actually the ask from the customer/PO/interviewer.

So the first question should be about the context, whether we have any in our task, whether we have the host application we're adding new functionality to, whether we have backend already done for the web version, how big our team is and what their expertise is. In 99% of the cases I believe the interviewer will answer that there is no context at all and you should start from the scratch but these questions will show that you do care about the context and take it into account during the solution design process.

Concerning the limitations I prefer to use a checklist for evaluation of the design solutions. The following list contains the checklist items and questions that can be asked regarding each of the item related to the mobile system design. You can come up with different questions and use another list, this one just for reminding you of the questions during the interview.

**Maintainability** — are we designing MVP or PoC or full scale system? How big is the team which will implement my design?

**Testability** — we can leave this dimension, we definitely need to think about some type of testing. No limitations here, just not to forget to mention about testing on the latest steps of you design.

**Scalability/Performance** — here we can treat scalability as the scalability of our team — ability to reuse their code in different platforms and though we can ask about cross platform option since it can impact our design considerably. Also you can ask questions about some parameters that could impact the performance of the applications where performance is crucial and where it's better to define limitations beforehand. For instance, if you are designing an application with recognition of many objects in the live video using ML you could mention that we limit our selves to the latest models of Android or iOS devices only. Otherwise we need to use third party libraries and use main processor for making all the calculation that can affect the battery life and the quality of recognition. This will not impact design so seriously but it's worth mentioning since it shows your experience and an ability to foresee the problems.

**Security** — it's very important to discuss the limitations caused by security reasons. Especially if you're asked to store some information on the device, or if you should work with the Personally Identifiable Information (PII) or sensitive PII. So it's better to clarify with the interviewer that persisting and transferring requested information should be safe and if you think it's not safe you should suggest what have to be done to protect it.

**Availability** — here we can ask about the ability to work offline, probably limitations of OS versions, phone/tablet choice, using limited set of languages and screen sizes. Of course all those questions should be asked only if it's relevant to your task.

There is no need to enlist all the dimensions on the whiteboard as they work only as the keys to recall what should you think and ask about. Later on you can use the same dimensions if you will be asked to compare two solutions.

It's better to write all the answers to the above questions on the whiteboard so you can reference them later.

## Mathematical model design

This step is relevant only to a limited subset of the tasks. Those tasks would sound like following:

* You are Facebook developer and you have to add new feature to show the two most popular posts at the top of the feed. In this case you need to clarify/come up with what it means — "most popular" and then describe how you can calculate it: should you use views or comments or likes in order to figure it out, should it be instant popularity or moving average and so on.
* You are developer in a rapidly growing super hotel aggregator and your task is to develop a bot detection feature. Here you should define the input data you need to detect bots and come up with the algorithm and mathematical model of how to use this data in order to distinguish bots from the real users. For instance, you can calculate the requests rate using moving window and compare it to a given limit.

Outcome of this step would be a simple schema of the module with inputs, formula(s) inside and outputs.

## High level system design

Next step is to decide what part of the services and the data should be implemented/stored on the server and what part should be implemented/stored on the mobile device. Here you can use the output of the first step — list of services and data entities.

Let's consider previous example — WhatsApp-like app.

Can we implement address book storage on device only? No, because we need to have one shared store so all the clients can use it in order to be able to contact to the needed correspondent (We don't consider distributed storage schemes but you can think about it and compare with the above schema using dimensions from the second step).

Can we implement Audio calls using client side only? Yes, but you need to let the interviewer know about all the tradeoffs in that case in comparison to the server side service (like E2E encryption, better scalability, worse availability because of NAT traversal and so on).

For instance, you decide to put all the services to the server side, and so all the data will reside on the server as well.

Then since all the data and services reside on the backend we need to choose the client-server communication type for each of them one by one.

For WhatsApp-like application example, address book service can be easily implemented as a simple REST HTTP calls with JSON payload (given that each user can use only one device and so we don't need server to client notifications about changes in the address book contacts), Audio/Video calls service would use its own communication interfaces — bidirectional TCP-based connection for a signal protocol (WebSocket probably) and UDP-based for the streaming of the data (RTP probably). On this step it should be enough to just mention type of communication (bidirectional or unidirectional, streaming or request-response-based, then more details — HTTP REST (simple, long-polling), web-socket, raw-socket, etc…)

For the text/images chats service it is a bit harder to decide what communication channel to use. It could be REST HTTP + push notification or bidirectional web-sockets or custom TCP based channel or something else. All the options have their own pros and cons so you can choose any option, just let the interviewer know about the other alternatives and be ready to defend the chosen one.

The outcome of this step should be something like on the following scheme where you define the place where the services and the data will reside + communication channels between the server and clients.

This is the first very high level structure of our system and most likely it will be the only scheme where you draw any backend details.

## API design

This topic is rather controversial, there are a lot of ways to design the API, so you can use any of them, the only thing is to define all the exact data entities and API calls as an outcome of this step. Side effect of API design is the clarification of some server-side solutions.

From my experience it's good to start from the service and the data entity that other services/data entities depend on and expand it to one or more API calls. For instance, our first service is address book, so there would be 4 API calls:

1. _GET /address-book?page,limit -> [AddressBookItem]. _Read the address book data with paging (it's very important not to forget about paging — better if you consider paging by default to any call and then remove it if it's not needed than to forget about it at all). I intentionally don't mention any user identifier as we need to mention in the interview that all the requests should contain header with authorisation token which was received earlier during login procedure. If the interviewer wants to know more about login you can let him know one or more ways of the register/login implementation.
2. _POST /address-book?user_id,first_name,family_name,phone -> AddressBookItem. _Add new record to the address book.
3. _PUT /address-book?usert_id,first_name,family_name,phone -> AddressBookItem. _Update existed record in the address book.
4. _DELETE /address-book?user_id. _Delete existed record from the address book.

Where _AddressBookItem_ looks like:
   
   
    AddressBookItem: 
    user_id: int64 
    first_name: string 
    family_name: string 
    phone: string

Read chat message API call would look like:

_GET /chat-history?user_id,page,limit -> [ChatMessage, ChatImageMessage]_

Where _ChatImageMessage_ looks like:
   
   
    ChatImageMessage: 
    user_id: int64 
    msg_id: int64 
    dt: timestamp 
    is_inbound: bool 
    img_url_thumb: string 
    img_url_full_size: string

I wrote API calls in a custom form that is easy to show in one line, so question mark doesn't mean we will use request path parameters, it's just delimiter between the call name and parameters list. Moreover word "GET" doesn't imply we will use HTTP requests, it could be WebSocket according to our high-level scheme and this API call can be encoded with Google Protobuf, for instance, but idea will remain the same — it should be an idempotent call with three input parameters that returns array of 2 types of chat messages (simple text message and image message).

You can come up with your own format of writing API calls if you think it would better represent the call name, input and output data.

At this step we define data with more details taking into account server side implementation and trying to foresee future problems. For instance, we added unique identifiers to users and messages and represent image as two direct links — one for a thumbnail and one for a full size image.

Most likely in your task you will have to work with images so it's better to think about the possible problems beforehand, like saving a lot of traffic for users by downloading only thumbnails and not the full size images. It sounds obvious but those small optimisations make your solution more interesting to design and discuss.

Designing an API is an iterative process, you can change API later when you will be designing client-side and it's OK. This step is rather important and hard since you need to think simultaneously about server-side design and client-side design trying to foresee future problems with the connection speed, throughput and usage convenience. The previous steps are intended to prepare you by providing with the services list, data entities and the type of communication channel between the client and the server.

After finishing this step you can spend some time analysing the result — probably there are some obvious optimisations you can apply to the API, for example, you may want to merge some APIs to one, or split some of them, or even decide to change communication channel because, for instance, it turns out that bidirectional communication better suits for one of the cases.

## High level client side design

Finally you can start drawing the client modules structure scheme. Layered design, commonly, the easiest way to organise modules in some meaningful groups in almost all Frontend applications.

It's better to start drawing this scheme from the central and most important part of it — the state and services that doesn't related to sate. These are things you provide to your users, so let's start designing from them. You can name this layer business entities or B**usiness Layer **and then start going downwards in order to meet the server-side.

Next layer could be **Service Layer**, here I use name "service" in a different meaning than before— interval Services Layer helps to isolate concrete implementations of the low level communication and persistent modules (Data Layer) from the Business Layer. So Business Layer shouldn't bother about the place where we get the data from — persistent storage or communication channels, and what kind of SDK we use to implement audio/video calls. Another purpose of the Service Layer is to convert data from multiple formats to the one Business Layer is using. At this step we don't make any assumptions about implementation of the services — will it be the set of middleware in Redux or services distributed among VIPER modules or something else, we can just mention that our purpose is to make services stateless and to try to keep explicit state in the Business Layer only.

**Data Layer** contains SDKs, frameworks, libraries that provide your application with such features like network communication, local DB access, logging, or, complete fully functional features like Audio/Video calls. Of course you can split Data Layer to multiple internal layers if it makes sense, for instance if you develop some low level codecs you would have two layers in the Data Layer — one for high level orchestration and one for low level access to device hardware, but it should be hidden in the Data Layer and exposed only in Dependency Injection phase where we can define what kind of modules and codec should be used in Data Layer.

Going upwards from business layer to meet user we should draw the **Presentation Layer**. The main purpose of this layer is to prepare the data from Business Layer to be displayed to user in UI Layer. Again it worth to mention that we want to keep the Presentation Layer stateless.

Last layer to draw on the high level design structure scheme is **UI Layer**. It's very platform specific layer where the business data, converted by the Presentation Layer, is being displayed on the device screen using OS SDKs. The second purpose of this layer is to interact with the user and pass all those interactions throughout the Presentation Layer to the Business Layer and further if needed.

This layered design can be mapped to any concrete architecture patterns like MVC, MVI, Redux, VIPER, RIBs, etc, but it's very important to remain on the higher level of abstraction at this step and not to go deeper to those concrete patterns. You can mention such thing like "module" here but postpone the concrete definition of the "module" to the next step as module's meaning depends on the architecture pattern you will choose later, for instance, module in VIPER lays horizontally and crosses all the layers except the Data Layer, so each module contain small piece if each layer. In opposite, Redux, usually, doesn't have modules but more like features instead.

## Detailed design of some modules

Here we should finally choose one feature/module and define the architecture pattern that suits it best in your opinion. Let's consider the chat feature in a WhatsApp-like application. As the chat feature crosses all the layers, the chosen pattern will influence the whole application, so we need to think not only about chosen feature but rather about the app in a whole.

Actually, our WhatsApp-like application works with rather limited types of data entities that are related to each other and all changes in the data should be immediately reflected in different part of the application as messages in the chat list and the chat screen, as a notification when user browses the other parts of the app and as a counter of the unread messages. It means we can have the one periodically updated state with the ability to subscribe to those state's changes. Looks like Redux is a good option for the Business Layer + middleware as the Service Layer + MVP or MVVM or MVI patterns for the Presentation and UI layers with the Redux store playing the Model role. VIPER seems a bit excessive for just a few screens with minimum number of routes between them and RIBs is better suits very complex screens with many teams working on the same screen. Of course, you can choose any pattern or mention none of them, but then you would have to to explain where store/state should be placed, how it should work, how we would implement subscription for state changes, how services should be used and how they will update the state.

I intentionally didn't mention any concrete platform as it actually doesn't matter if it's Android or iOS as there are multiple implementations of Redux store and reactive frameworks for implementation of the MVVM/MVI for both platforms whereas MVP doesn't need any.

Let's draw a possible structure scheme for the chat feature:

Here it's important to describe how all the cycle of the data update would work, demonstrate the advantages of the pattern you have chosen and mention some tradeoffs and hard problems you would encounter during the implementation of this solution.

For instance, we can enlist the following advantages of the chat feature's Redux design using the previous dimensions:

* **Maintainability:** We fully decouple data receiving part (Data and Service Layer) and data consumer part (Presentation and UI Layer) using store with updates subscriptions, so it doesn't matter from which source the data comes — from a persistent store or a network communication channel. It helps to change some parts of the app without impacting the others + reuse as much code as possible.
* **Scalability:** The store could have multiple subscribers out of the box, so Presenter of our chat feature will be triggered and will pass the latest changes to View in order to display them + Presenter of the top panel, for instance, will be triggered as well and will update the counter of the unread messages. Adding one more subscriber is easy.
* **Testability: **State is immutable and located in one place (not distributed across the app) which help to avoid many mistakes related to simultaneous changes of the state. Also it helps to test the app as you are sure that state can be changed only in reducers.

Disadvantages:

* **Deployability (and partly Maintainability): **complex project structure and the tricky modularisation concept. In order to change something in the chat feature developers will have to make changes in many places — state, reducers, actions, middleware, Presenter, View. Moreover, all those places are spreader among the project (we can come up with a project structure that minimise this effect by uniting the most of the parts in one place but it could lead with the repeating code and problems with changing the other teams code) which makes modularisation a bit tricky. So every team will be most likely touching the store/state and it means rebuilding of all the modules as all the modules depend on the state.
* **Scalability:** the bigger become the application the bigger become the state. Usually we split it into sub-states so each feature has it's own sub-state but anyway huge state could lead to performance and multithreaded access issues.

It's worth mentioning that your pattern/approach/design is good for testing, for instance, in our example we can mention that we should test middleware, services, presenters and reducers and it's very easy since they are stateless.

Additionally, here you can demonstrate the skill of class diagrams designing. Specific implementation will depend on the platform (because, for instance, table rendering concepts are different in Android and iOS).

## One complex case and detailed discussion

At this step you can show the knowledge of the platform and problem solving skill. It's better to choose one interesting case that you met before and successfully resolved and softly lead the conversation to this case.

For instance, you can say that it's interesting problem of caching the images in the chats. Each image has direct link to it's thumbnail and full size image and once image comes to the visible area of the screen it will be requested from, let's say, an image download service which downloads image using those direct links. Weak point of such a solution is that if user has slow network connection images will appear too late, especially if user scrolls chat rather fast. Obviously we need a cache. The easiest solution is to add to the image download service a memory cache which represents the dictionary with keys as direct links and values as images but what will happen when we restart the app? All the text messages will be restored from the local DB (assume it) but should we save thumbnails and full size images in DB as well? Or should we save only thumbnails in DB and full size images in the file system? If we will save images in the filesystem how can we find them later on? How can we housekeep folder with images if for instance device, in case of iOS, asks to free some space up? There are a lot of questions and answering them could demonstrate your experience, knowledge of the platform and problem solving skills.

So the trick here is to choose one common hard enough problem beforehand and lead the conversation to this problem. The interviewer can reject that and choose the random one, but still you will show that you aware of some problems and you would have a chance to talk a lot about the problem you know the best.

# Summary

Summarizing the above information I can say that it's always better to elaborate a plan of the common system design interview beforehand, should it be like one I suggested in this article or another one. It doesn't matter which plan you will chose but it should clearly demonstrate your ability to tackle a very big ambiguous problem — split it to some meaningful pieces, absorb them one by one, process that information and produce another type of relevant information as an outcome.

Good luck at your interviews and don't hesitate to write comments to this article or share your experience.

# References

1. Layered architecture pattern: <https://towardsdatascience.com/software-architecture-patterns-98043af8028>
2. Architecture/systems quality attributes/dimensions: <https://sites.google.com/site/misresearch000/home/software-architecture-quality-attributes>
3. There are a lot of books about API Design, it does worth to read at least one
4. Mobile Architecture patterns: MVC, MVP, MVVM, MVI, VIPER, REDUX, etc. It would be good to know about them and about the conceptions behind each of them even if you don't want to mention any of them on the interview. It's easy to find a lot of info about each of them.

Update: Big Thanks to [Kirill Cherkashin][2] for noticing and helping to fix some typos and mistakes!

[1]: https://miro.medium.com/fit/c/56/56/2*_qEEg7o9O1bPAH7dQ_HLfQ.jpeg
[2]: https://medium.com/u/650009575703?source=post_page-----6a06fa94491b--------------------------------

