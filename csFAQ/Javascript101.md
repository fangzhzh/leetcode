- [Basic]
	+ [Diagram]
	+ [Variable]
	+ [Object variable]
	+ [Function]
- [L:sort in javascript](x-devonthink-item://58126051-8E54-4855-9779-7CBB0554F1A9)
- [Intermediate]
	+ [Javascript programming paradigms]
	+ [Prototypes]
	+ [Event loop]
	+ [Browser events]
	+ [Event bubbling ]
	+ [Event delegation]
	+ [this]
	+ [call apply]
	+ [Arrow function]
	+ [Binding]
	+ [Currying]
	+ [Closure]
	+ [Create new javascript object]
	+ [Execution context, scope chain]
	+ [IIFE]
- [Advanced]
	+ [Architect]
- [L: notes of dan abramov Javascript mential models]()
- [Miscs]
-------

## Basic

- javascript definition
	+ JavaScript, also known as ECMAScript,[6] is a prototype-based, object-oriented[7] scripting language that is dynamic, weakly typed and has first-class functions. It is also considered a functional programming language[1] like Scheme and OCaml because it has closures and supports higher-order functions.[8]


### Diagram
- Number
- String
- Boolean
- Symbol (new in Edition 6) 
- Object
    + Function
    + Array
    + Date
    + RegExp
- null
- undefined

```
if(true) {
	console.log("hello world!", [1,2,3], new Date())
}
```
### Variable
- let
	+ block level variable
- const 
	+ const, never change
- var
	+ available from (declared in )-> end
- blocks do not have scope; only functions have scope

### Object variable
JavaScript objects can be thought of as simple collections of name-value pairs. As such, they are similar to:
- Hash tables in C and C++. 
- HashMaps in Java. 


- two way to create empty object
- `var obj = new Object();`
- `var obj = {};`

### Function
- Arguments

```js
 function avg() {
  var sum = 0;
  for (var i = 0, j = arguments.length; i < j; i++) {
    sum += arguments[i];
  }
  return sum / arguments.length;
}
avg(2, 3, 4, 5); // 3.5
```
- `using the apply() method of any function object.`
	+ `avg.apply(null, [2, 3, 4, 5]); // 3.5`
### expressions

+ An expression is any valid unit of code that resolves to a value.
+ two type of expressions
    - with side effects: ```x = 7``` assign value seven to x, itself evaluates to seven
    - evaluates and resolves to value
- expression categories:
    - Arithmetic: 3.14159
    - String: "123"
    - Logical: true or false
    - Primary expressions: Basic keywords and general expression in Javascript
    - Left-hand-side expressions: Left Value are the destination of an assignment

- Primary expressions
	+ this
    	- current object
	    + ```this.propertyName```
	    + ```this['propertyName']```

- Group operator \( \)

controls the precedence in expressions


- Left-hand-side expressions

	+ new: create an instance of a user-defined object type or one of the build-in object types

	```JavaScript
	    var objectName = new objectType([param1, param2, ..., paramN]);
	```
	+ super: call functions on an object's parent.

	```JavaScript
	    super([arguments]); // calls the parent constructor.
	    super.functionOnParent([arguments]);
	```

+ Spread operator

```JavaScript

    var parts = ['shoulder', 'knees'];
    var lyrics = ['head', ...parts, 'and', 'toes'];
```

## Intermediate

- [L:A re-introduction to JavaScript ](x-devonthink-item://7B117064-1792-471E-BC5B-35C6171FD645)
	+ from function to class
	+ class

### Javascript programming paradigms

+ imperative programming 
+ declarative 
- What's functional programming 
	+ evaluation of mathematic function
	+ avoid shared state and mutable data
	+ first class and higher order function
	+ pure function / side effects
	+ recursion

### Prototypes

#### prototypes inheritance 
A delegate prototype is an object that serves as a base for another object. When you inherit from a delegate prototype, the new object gets a reference to the prototype. 

```
+---------------+        +---------------+
|   George      |        |    proto      |
|---------------+<-------+---------------|
| name: 'george'|        | hello() {...} |
+---------------+        +---------------+
```

```
const proto = {
	hello () {
	return `Hello, my name is ${ this.name }`;
	}
};

const greeter = (name) => Object.assign(Object.create(proto), {
	name
});

const george = greeter('george');

const msg = george.hello();

console.log(msg);
```
特点 ：

- 使用 Object.create(proto) 创建对象，建立原型链
- 新对象保留对原型的 引用
- 当访问 george.hello() 时，如果 george 对象上没有该方法，会沿着原型链查找
优点 ：

- 内存效率高，所有实例共享方法
- 动态更新：修改原型上的方法会影响所有实例
- 符合 JavaScript 的原生继承机制
缺点 ：

- 原型污染：修改原型会影响所有实例
- 共享可变状态可能导致意外行为

#### concatenative inheritance
- cloning objects by copying properties from a source of boject to a new object(Object.assign())  *without retaining a reference*
- source of clone properties "exemplar prototypes"
- cloning an examplar prototype is known as concatenative inheritance

      	```
      		const proto = {
      		  hello: function hello() {
      		    return `Hello, my name is ${ this.name }`;
      		  }
      		};

      		const george = Object.assign({}, proto, {name: 'George'});

      		const msg = george.hello();

      		console.log(msg); // Hello, my name is George
      	```
特点 ：

- 使用 Object.assign() 复制属性， 不保留引用
- 直接将方法复制到新对象上
- 也称为"混合"(mixin)或"组合"模式

优点 ：

- 对象独立，不受原型修改影响
- 更简单直观，没有原型链查找
- 可以从多个源对象组合属性

缺点 ：

- 内存效率较低，每个实例都有自己的方法副本
- 失去了原型链的动态特性

#### functional inheritance

- Functional inheritance makes use of a factory function, and then tacks on new properties using concatenative inheritance.
- commonly referred as 'functional mixins'

    ```
      import Events from 'eventemitter3';

      const rawMixin = function () {
        const attrs = {};

        return Object.assign(this, {
          set (name, value) {
            attrs[name] = value;

            this.emit('change', {
              prop: name,
              value: value
            });
          },

          get (name) {
            return attrs[name];
          }
        }, Events.prototype);
      };

      const mixinModel = (target) => rawMixin.call(target);

      const george = { name: 'george' };
      const model = mixinModel(george);

      model.on('change', data => console.log(data));

      model.set('name', 'Sam');
      /*
      {
        prop: 'name',
        value: 'Sam'
      }
      */
    ```
特点 ：

- 使用工厂函数创建对象
- 结合了闭包来创建私有状态
- 通常与连接继承结合使用

优点 ：

- 支持私有状态（通过闭包）
- 更好的封装性
- 可以实现更复杂的初始化逻辑

缺点 ：

- 更复杂的实现
- 可能更难调试（闭包中的变量）
- 内存占用可能更高

历史：

* 早期 React (0.13 版本之前) 支持并推荐使用 mixins：
* React 团队在 2016 年正式宣布不再推荐使用 mixins，并在 React 0.13 版本中移除了对 mixins 的支持（对于使用 ES6 类的组件）。
* 原因：
	1. 命名冲突 ：多个 mixins 可能定义相同的方法或状态
	2. 依赖不透明 ：难以追踪组件的行为来源
	3. 级联复杂性 ：mixins 可能依赖其他 mixins，形成复杂的依赖网络
	4. 隐式状态共享 ：难以追踪状态变化
	5. 与 ES6 类不兼容 ：React 转向 ES6 类后，mixins 不再适用
	* Dan Abramov（React 核心团队成员）在一篇著名的博文中详细解释了这些问题：" Mixins Considered Harmful "。
	
### Event Demultiplexer

- epoll in Linux
- kqeueue in BSD system(MacOS)
- event ports in Solaris 
- IOCP(Input Output Completion Port) in Windows

*libuv*
- libuv is cross-platform support library which was originally written for NodeJS. It’s designed around the event-driven asynchronous I/O model.

- Network IO
	+ TCP
	+ UDP
	+ TTY
	+ Pipe
	+ ...
- File I/O
- DNS Ops
- User code
- uv_io_t
- epoll
- kqueue
- event ports
- IOCP
- Thread Pool

### Event queue
four types of queue
- Expired timers and intervals queue — consists of callbacks of expired timers added using setTimeout or interval functions added using setInterval.
- IO Events Queue — Completed IO events
- Immediates Queue — Callbacks added using setImmediate function
- Close Handlers Queue— Any close event handlers.


### Event loop 
+ [L: Javascript EventLoop](https://developer.mozilla.org/en-US/docs/Web/JavaScript/EventLoop)
+ [event loop in a photo](https://photos.app.goo.gl/bBwCB27QIpgJ8WBt1)
+ [task, microtasks, queue and schedules](https://jakearchibald.com/2015/tasks-microtasks-queues-and-schedules/)
+ event loop
	- indexedDB define their own
+ Task
+ Microtasks
	- Promise
	- MutationRecord

### Browser events
+ element.addEventListener(event, handler[, phase]);
	+ event.type
	+ event.currentTarget
	+ event.clientX / event.clientY
- three ways
	+ HTML attribute: onlcick(rare)
	+ DOM property: elem.onclick = function
	+ Methods: elem.addEventListener(event, handler[, phrase]) to add removeEventListener to remove
### Event bubbling 
+ [DONE: event capturing and bubbling](http://javascript.info/tutorial/bubbling-and-capturing)
	- Overview process:
		+ event.target
		+ from root to event.target, calling handler assigned with addEvenetListener(..., true) on the way
		+ then, event move from event.target up to the root, callling handler assigned use on<event> and addEventListener without 3rd argument or with 3rd augument false. 						- properties
		+ event.target
		+ event.currentTarget(=this)
		+ event.eventPhase

	- first, event loop(all events)
	+ When an event happens on an element, it first runs the handlers on it, then on its parent, then all the way up on other ancestors.		- addEventListener to add handler for that event on this element, and still bubbling
	- stop bubbling 
		+ affect current: event.stopPropagation()
		+ affect all handler on an element: event.stopImmediatePropagation()
			- parent and others
### Event delegation
+ if we have a lot elements handled in a similar way, assign handler on their common ancestor
### this
+ a variable with the value of the object that invokes the function where this is used
+ Function.prototype.bind.
+ ECMAScript 5
+ Calling f.bind(obj): creates a new function with same body and scope, as f, but where this occurs in the original function, `this` as obj, regardless of how the function is being used.
+ global context(outside any function), this === winow
	- strict mode => this == undefined
+ [DONE: L:Understand JavaScript’s “this” With Clarity, and Master It](x-devonthink-item://630C7420-F924-457A-A5C5-8B22933F7D9D)
+ *this is not assigned a value until an object invokes the function where this is defined*
+ assgin a method, using `this` inside, to a variable
	- `.bind(user)`
+ function uses `this` is passed as a callback
	- `.bind(user)`
+ `this` is inside a closure, an inner function
	- let `that = this`
+ pass `this` from one context to another, use `call` and `apply`
+ ECMAScript 5 introduced `Function.prototype.bind`
+ [L:bind implementation](x-devonthink-item://920B3E38-DF16-41E5-8B31-C3871BA44AA2)
### call apply
+ [L: appy bind](x-devonthink-item://DC45ADAE-EEC9-41A0-AD33-C62290CA8519) 
+ funciton.call(obj), function.apply(obj)
+ obj as `this`
+ if this obj is not object, number, string, it will be converted to object
+ `bar.call(7);     // [object Number]`
+ `bar.call('foo'); // [object String]`

### Arrow function
+ `this` remains the value of the enclosing lexical context's this
### Binding
+ (passing `this` from one context to another)
+ call, apply
+ bind
+ Arrow functions
+ http://javascriptissexy.com/javascript-apply-call-and-bind-methods-are-essential-for-javascript-professionals/

### Currying
+ http://www.sitepoint.com/currying-in-functional-javascript/
+ currying is a way of constructing functions that allows partial application of a function’s arguments

```
	var greetCurried = function(greeting) {
		return function(name) {
		console.log(greeting + ", " + name);
		};
	};

	var greetHello = greetCurried("Hello");
	greetHello("Heidi"); //"Hello, Heidi"
	greetHello("Eddie"); //"Hello, Eddie"
```
### Closure
+ [B:how do javascript closures work, SO](https://stackoverflow.com/questions/111102/how-do-javascript-closures-work)
	- ALL AWESOME examples
	- This environment consists of any local variables that were in-scope at the time the closure was created(outer function)
	- [first-class function](https://en.wikipedia.org/wiki/First-class_function), *expression* can
		+ reference variables with its scope
		+ be assigned to a variable
		+ passed as an argument of a function
		+ be returned as a function result
	- stack frame: allocated when a function starts its execution and not freed after function returns
		+ as if a 'stack frame' were allocated on the heap rather than the stack! ?? HOW!!
+ [L:closure, javascript.isSexy](x-devonthink-item://D31F3497-14A5-4366-9146-9BA16746F477) 
	- variables scope chain
+ [B:closure @ mozilla](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Closures)
	- inner function that has access to outer function's variable, scope chain
		+ own scope
		+ outer function variables
		+ global variables
		+ own scope
		+ outer function variables
		+ global variables


### Create new javascript object
  + `Object.create()` is an ES5 feature that was championed by Douglas Crockford so that we could attach delegate prototypes without using constructors and the `new` keyword.
  + `Object.assign()` is a new ES6 feature which will copy all of the **_enumerable_** own properties by assignment from the source objects to the destination objects with last in priority.
	- https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Object/assign


    ```
    		const object1 = {
    			a: 1,
    			b: 2,
    			c: 3
    		};

    		const object2 = Object.assign({c: 4, d: 5}, object1);

    		console.log(object2.c, object2.d);
    		// expected output: 3 5

    		const object2 = Object.assign( object1, {c: 4, d: 5});

    		console.log(object2.c, object2.d);
    		// expected output: 4 5
    ```

### [Execution context, scope chain](x-devonthink-item://920B3E38-DF16-41E5-8B31-C3871BA44AA2)
### IIFE
- [IIFE (Immediately Invoked Function Expression)](https://developer.mozilla.org/en-US/docs/Glossary/IIFE)
	+ javascript function that runs as soon as it is defined
	+ Self-Executing Anonymous Function(two major operator)
		- The first is the anonymous function with lexical scope enclosed within the Grouping Operator ()
		- Creating the immediately executing function expression ()
	
	```
	(function() {
    	statements
	})();
	```

## test
- jest
	+ unit test
	+ snapshot
- Enzyme
	+ shadow render
	+ find element to verify
	+ redux-mock-store

## need to cover
- Explain what a single page app is and how to make one SEO-friendly.
- Explain the difference between layout, painting and compositing.
- Do your best to describe the process from the time you type in a website's URL to it finishing loading on your screen.
- What are the differences between Long-Polling, Websockets and Server-Sent Events?
- What are HTTP actions? List all HTTP actions that you know, and explain them.
- [Stream api](https://www.sitepoint.com/basics-node-js-streams/)
- Composition and high order functions.
- Type Coercion using typeof, instanceof and Object.prototype.toString.
- Handling asynchronous calls with callbacks, promises, await and async.
	+ [L:callback](x-devonthink-item://C629FB40-72DB-433E-9F64-264812E4CB30) 
	+ https://www.quora.com/Whats-the-difference-between-a-promise-and-a-callback-in-Javascript
- When to use function declarations and expressions.
- Symbol and meta programming
- Jasmine
- ReactJS
- SASS
- Foundation
- Grunt/Gulp
- SOAP
- NET Webapi



## Advanced
### import, export 

- export function

![export function](./static_files/export_function.jpg)

in utils, functions should be pure.

作为utils，一般的理解是里面的function都是pure的，所以没有必要把几个function做成对象共享一个block再输出，这样反而破坏代码的可读性，除非你有特殊的理由

- export object

![export object](./static_files/export_object.jpg)

- export class static

![export class static](./static_files/class_static.jpg)

- what's module?

- [module history](x-devonthink-item://0BDB5A0B-6DE6-4A17-BA3F-7992AA3B79B1)
- [javascript module summary](x-devonthink-item://1F8DD480-9CDB-4191-8B46-4D6C3BF54637)
- [Node.js, TC-39, and Modules](x-devonthink-item://062773DB-0E2D-49E0-BD49-ABEB2C5A3B2D)
- https://hackernoon.com/node-js-tc-39-and-modules-a1118aecf95e

### Architect


## Miscs
### go like async, await without try catch
```
export default function to(promise) {
   return promise.then(data => {
      return [null, data];
   })
   .catch(err => [err]);
}
async function asyncTask(cb) {
[err, user] = await to(UserModel.findById(1));
     if(!user) return cb('No user found');
}

```

### How to loop a string
- for index
	```
	for (let i = 0; i < str.length; i++) {
	process(str[i]);
	}
	```
- for in/ for ofloop

	```
	for(char in str) {
		console.log(char) // index
	}	


	for (let c of str) {
		console.log(c) // char
	}
	```

	+ or/in - loops through the properties of an object
	+ for/of - loops through the values of an iterable object
	```javascript
		// Print names and values of object properties
		for (attribute in shark) {
			console.log(`${attribute}`.toUpperCase() + `: ${shark[attribute]}`);
		}
		Output
		SPECIES: great white
		COLOR: white
		NUMBEROFTEETH: Infinity
	```
- for each