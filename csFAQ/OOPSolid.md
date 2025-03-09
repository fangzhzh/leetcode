# OOP (Object-Oriented Programming)

## Core Concepts
- **Encapsulation**: Bundling data and methods that operate on that data within a single unit
- **Inheritance**: Creating new classes that are built upon existing classes
- **Polymorphism**: The ability to present the same interface for different underlying forms (data types or classes)
- **Abstraction**: Hiding complex implementation details and showing only necessary features

## SOLID Principles

- **S - Single Responsibility**: A class should have only one reason to change
- **O - Open/Closed**: Software entities should be open for extension but closed for modification
- **L - Liskov Substitution**: Objects of a superclass should be replaceable with objects of its subclasses
- **I - Interface Segregation**: Clients should not be forced to depend on interfaces they do not use
- **D - Dependency Inversion**: High-level modules should not depend on low-level modules; both should depend on abstractions

### S - Single Responsibility
> "A class should do one thing and do it well"
- 📝 Think of a "Swiss Army knife vs. Dedicated Tools"
- Example: Split a UserService into:
  - UserAuthService (handles login/logout)
  - UserProfileService (handles profile updates)
  - UserNotificationService (handles emails/notifications)

### O - Open/Closed
> "Open for extension, closed for modification"
- 🔌 Think of "Plugins" - you add new ones without changing existing code
- Code Example: Payment methods
  ```python
  class Payment:
      def process(self): pass
  
  class CreditCard(Payment):
      def process(self): # handle credit card
  
  class PayPal(Payment):
      def process(self): # handle PayPal
  ```

### L - Liskov Substitution
> "If it looks like a duck and quacks like a duck but needs batteries, you probably have the wrong abstraction"
- 🦆 Think of "Duck Typing"
- Example: Square/Rectangle problem
  - A Square IS-A Rectangle, but if it breaks rectangle's behavior (setting width/height independently), it violates LSP

### I - Interface Segregation
> "Don't force clients to depend on methods they don't use"
- 📱 Think of "TV Remote" - different remotes for different functions
- Code Example: Split large interfaces:
  ```python
  # Bad
  interface Worker:
      work()
      eat()
      sleep()
  
  # Good
  interface Workable:
      work()
  interface Eatable:
      eat()
  ```

### D - Dependency Inversion
> "High-level modules should depend on abstractions, not details"
- 🔌 Think of "Power Socket" - you plug in any compatible device
- Code Example:
  ```python
  # Bad
  class EmailService:
      def send(self, mysql_database)
  
  # Good
  class EmailService:
      def send(self, database_interface)
  ```

### Creational Patterns

#### Singleton Pattern
The Singleton pattern ensures that a class has only one instance and provides a global point of access to it. 
* **Use Case:** When exactly one object is needed to coordinate actions across the system.  
* **Example:** Database connection pool.

**Code Example:**
```python
class Singleton:
    _instance = None

    def __new__(cls):
        if cls._instance is None:
            cls._instance = super(Singleton, cls).__new__(cls)
        return cls._instance

# Usage:
obj1 = Singleton()
obj2 = Singleton()

print(obj1 is obj2)  # Output: True
```

#### Factory Method Pattern
The Factory Method pattern defines an interface for creating an object, but lets subclasses alter the type of objects that will be created. This pattern is useful when a class cannot anticipate the class of objects it must create.
**Example:** GUI framework that creates buttons.
**Code Example:**
 GUI framework that creates buttons.
```python
from abc import ABC, abstractmethod

class Button(ABC):
    @abstractmethod
    def click(self):
        pass

class WindowsButton(Button):
    def click(self):
        print("Windows button clicked")

class MacOSButton(Button):
    def click(self):
        print("MacOS button clicked")

class ButtonFactory:
    def create_button(self, os):
        if os == "Windows":
            return WindowsButton()
        elif os == "MacOS":
            return MacOSButton()
        else:
            raise ValueError("Invalid OS")

# Usage:
factory = ButtonFactory()
button = factory.create_button("Windows")
button.click()  # Output: Windows button clicked
```

#### Abstract Factory Pattern
The Abstract Factory pattern provides an interface for creating families of related or dependent objects without specifying their concrete classes. 
* **Use Case:** When the system needs to be independent of how its objects are created.  
* **Example:** UI toolkit that creates different widgets for different operating systems.
**Code Example:**
```python
from abc import ABC, abstractmethod

class GUIFactory(ABC):
    @abstractmethod
    def create_button(self):
        pass

    @abstractmethod
    def create_text_field(self):
        pass

class WindowsFactory(GUIFactory):
    def create_button(self):
        return WindowsButton()

    def create_text_field(self):
        return WindowsTextField()

class MacOSFactory(GUIFactory):
    def create_button(self):
        return MacOSButton()

    def create_text_field(self):
        return MacOSTextField()

# Usage:
factory = WindowsFactory()
button = factory.create_button()
text_field = factory.create_text_field()
```

#### **Builder**  
Separates the construction of a complex object from its representation.  
**Use Case:** When an object needs to be created step by step.  
**Example:** Building a complex meal in a restaurant.

**Code Example:**
```python
class Meal:
    def __init__(self):
        self.items = []
    
    def add_item(self, item):
        self.items.append(item)
    
    def show_items(self):
        return ', '.join(self.items)

class MealBuilder:
    def __init__(self):
        self.meal = Meal()
    
    def add_burger(self):
        self.meal.add_item('Burger')
        return self
    
    def add_drink(self):
        self.meal.add_item('Drink')
        return self
    
    def build(self):
        return self.meal

# Usage
builder = MealBuilder()
meal = builder.add_burger().add_drink().build()
print(meal.show_items())  # Output: Burger, Drink
```

#### **Prototype**  
Creates new objects by copying an existing object, known as the prototype.  
**Use Case:** When the cost of creating a new instance of an object is more expensive than copying an existing instance.  
**Example:** Cloning a configuration object.

**Code Example:**
```python
import copy

class Configuration:
    def __init__(self, settings):
        self.settings = settings
    
    def clone(self):
        return copy.deepcopy(self)

# Usage
config1 = Configuration({'volume': 10, 'brightness': 70})
config2 = config1.clone()
config2.settings['volume'] = 5

print(config1.settings)  # Output: {'volume': 10, 'brightness': 70}
print(config2.settings)  # Output: {'volume': 5, 'brightness': 70}
```

## Design patterns
### Structural Patterns
#### Adapter Pattern
The Adapter pattern allows incompatible interfaces to work together.
**Use Case:** When you want to use an existing class, but its interface does not match the one you need.  
**Example:** Adapting a legacy system to work with a new system.

**Code Example:**
```python
class OldSystem:
    def specific_request(self):
        print("Old system request")

class NewSystem:
    def new_request(self):
        print("New system request")

class Adapter:
    def __init__(self, old_system):
        self.old_system = old_system

    def new_request(self):
        self.old_system.specific_request()

# Usage:
old_system = OldSystem()
adapter = Adapter(old_system)
adapter.new_request()  # Output: Old system request
```

#### Composite Pattern
The Composite pattern composes objects into tree structures to represent part-whole hierarchies. 
**Use Case:** When you want to treat individual objects and compositions of objects uniformly.  
**Example:** File system structure.

**Code Example:**
```python
class Component(ABC):
    @abstractmethod
    def operation(self):
        pass

class Leaf(Component):
    def operation(self):
        print("Leaf operation")

class Composite(Component):
    def __init__(self):
        self.children = []

    def add(self, component):
        self.children.append(component)

    def remove(self, component):
        self.children.remove(component)

    def operation(self):
        for child in self.children:
            child.operation()

# Usage:
composite = Composite()
leaf1 = Leaf()
leaf2 = Leaf()

composite.add(leaf1)
composite.add(leaf2)

composite.operation()  # Output: Leaf operation, Leaf operation
```

3. **Proxy**  
Provides a surrogate or placeholder for another object to control access to it.  
**Use Case:** When you want to control access to an object, such as **lazy initialization**.
**Example:** Virtual proxy for image loading.
**Code Example:**
```python
class Image:
    def display(self):
        print("Displaying image")

class ProxyImage:
    def __init__(self):
        self.real_image = None

    def display(self):
        if not self.real_image:
            self.real_image = Image()
        self.real_image.display()

# Usage
proxy_image = ProxyImage()
proxy_image.display()  # Output: Displaying image
```
4. **Decorator**  
Attaches additional responsibilities to an object dynamically.  
**Use Case:** When you want to add responsibilities to individual objects, not to the whole class.  
**Example:** Adding scrollbars to a window.

**Code Example:**
```python
class Window:
    def draw(self):
        print("Drawing window")

class WindowDecorator:
    def __init__(self, window):
        self.window = window

    def draw(self):
        self.window.draw()
        self.add_scrollbar()

    def add_scrollbar(self):
        print("Adding scrollbar")

# Usage
window = Window()
decorated_window = WindowDecorator(window)
decorated_window.draw()  # Output: Drawing window
# Output: Adding scrollbar
```
5. **Facade**  
Provides a simplified interface to a complex subsystem.  
**Use Case:** When you want to provide a simple interface to a complex system.  
**Example:** A library that provides a simple API to a complex set of classes.
**Code Example:**
```python
class SubsystemA:
    def operation_a(self):
        return "Subsystem A: Ready!"

class SubsystemB:
    def operation_b(self):
        return "Subsystem B: Ready!"

class Facade:
    def __init__(self):
        self.subsystem_a = SubsystemA()
        self.subsystem_b = SubsystemB()

    def operation(self):
        return f"{self.subsystem_a.operation_a()}\n{self.subsystem_b.operation_b()}"

# Usage
facade = Facade()
print(facade.operation())  # Output: Subsystem A: Ready!\nSubsystem B: Ready!
```
### Behavioral Patterns

#### Observer Pattern
The Observer pattern defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified. This pattern is useful when an object needs to notify other objects without knowing who they are.
**Example:** Producer and Consumer
**Code Example:**
```python
class Producer:
    def __init__(self):
        self.observers = []
        self.data = None

    def attach(self, observer):
        self.observers.append(observer)

    def detach(self, observer):
        self.observers.remove(observer)

    def notify(self):
        for observer in self.observers:
            observer.update(self.data)

    def produce(self, data):
        self.data = data
        print(f"Produced: {data}")
        self.notify()

class Consumer:
    def update(self, data):
        print(f"Consumed: {data}")

# Usage:
producer = Producer()
consumer1 = Consumer()
consumer2 = Consumer()

producer.attach(consumer1)
producer.attach(consumer2)

producer.produce("Item 1")  # Output: Produced: Item 1
# Output: Consumed: Item 1
# Output: Consumed: Item 1

producer.produce("Item 2")  # Output: Produced: Item 2
# Output: Consumed: Item 2
# Output: Consumed: Item 2
```

#### Strategy Pattern
The Strategy pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable. This pattern is useful when you want to define a set of algorithms and make them interchangeable.
**Example:** Payment gateways in an e-commerce platform.

**Code Example:**
```python
from abc import ABC, abstractmethod

class Strategy(ABC):
    @abstractmethod
    def execute(self):
        pass

class ConcreteStrategyA(Strategy):
    def execute(self):
        print("Concrete strategy A")

class ConcreteStrategyB(Strategy):
    def execute(self):
        print("Concrete strategy B")

class Context:
    def __init__(self, strategy):
        self.strategy = strategy

    def set_strategy(self, strategy):
        self.strategy = strategy

    def execute_strategy(self):
        self.strategy.execute()

# Usage:
context = Context(ConcreteStrategyA())
context.execute_strategy()  # Output: Concrete strategy A

context.set_strategy(ConcreteStrategyB())
context.execute_strategy()  # Output: Concrete strategy B
```

3. **Command**  
Encapsulates a request as an object, thereby allowing for parameterization of clients with queues, requests, and operations.  
**Use Case:** When you want to queue operations, log operations, or support undoable operations.  
**Example:** Menu commands in a GUI.

**Code Example:**  
```python  
class Command:  
    def execute(self):  
        pass  

class NewFileCommand(Command):  
    def execute(self):  
        print("Creating new file...")  

class OpenFileCommand(Command):  
    def execute(self):  
        print("Opening file...")  

class SaveFileCommand(Command):  
    def execute(self):  
        print("Saving file...")  

class Menu:  
    def __init__(self):  
        self.commands = {}  

    def add_command(self, name, command):  
        self.commands[name] = command  

    def select(self, name):  
        if name in self.commands:  
            self.commands[name].execute()  
        else:  
            print("Command not found!")  

# Usage  
menu = Menu()  
menu.add_command("New", NewFileCommand())  
menu.add_command("Open", OpenFileCommand())  
menu.add_command("Save", SaveFileCommand())  

menu.select("New")  # Output: Creating new file...  
menu.select("Open")  # Output: Opening file...  
menu.select("Save")  # Output: Saving file...  
```

4. **State**  
Allows an object to alter its behavior when its internal state changes.  
**Use Case:** When an object should change its behavior based on its state.  
**Example:** A context-aware application that changes its behavior based on user preferences.  

**Code Example:**  
```python  
class State:  
    def handle(self):  
        pass  

class HappyState(State):  
    def handle(self):  
        print("I'm feeling happy!")  

class SadState(State):  
    def handle(self):  
        print("I'm feeling sad...")  

class Context:  
    def __init__(self):  
        self.state = HappyState()  
    
    def set_state(self, state):  
        self.state = state  
    
    def request(self):  
        self.state.handle()  

# Usage  
context = Context()  
context.request()  # Output: I'm feeling happy!  

context.set_state(SadState())  
context.request()  # Output: I'm feeling sad...  
```  

5. **Iterator**  
Provides a way to access the elements of an aggregate object sequentially without exposing its underlying representation.  
**Use Case:** When you want to provide a way to traverse a collection without exposing its details.  
**Example:** Iterating over a collection of items.  

**Code Example:**  
```python  
class Iterator:  
    def __init__(self, collection):  
        self._collection = collection  
        self._index = 0  
    
    def __iter__(self):  
        return self  
    
    def __next__(self):  
        if self._index < len(self._collection):  
            item = self._collection[self._index]  
            self._index += 1  
            return item  
        raise StopIteration  

# Usage  
items = [1, 2, 3, 4, 5]  
iterator = Iterator(items)  
for item in iterator:  
    print(item)  # Output: 1, 2, 3, 4, 5  

```

### Mediator Pattern  
The Mediator pattern defines an object that encapsulates how a set of objects interact. This pattern promotes loose coupling by preventing objects from referring to each other explicitly. Instead, they communicate through a mediator object.  

**Example:** Chat application where users communicate through a mediator.  

**Code Example:**  
```python  
class Mediator:  
    def send(self, message, colleague):  
        pass  

class ChatMediator(Mediator):  
    def __init__(self):  
        self.colleagues = []  

    def add_colleague(self, colleague):  
        self.colleagues.append(colleague)  

    def send(self, message, colleague):  
        for c in self.colleagues:  
            if c != colleague:  
                c.receive(message)  

class User:  
    def __init__(self, name, mediator):  
        self.name = name  
        self.mediator = mediator  
        self.mediator.add_colleague(self)  

    def send(self, message):  
        print(f"{self.name} sends: {message}")  
        self.mediator.send(message, self)  

    def receive(self, message):  
        print(f"{self.name} received: {message}")  

# Usage  
mediator = ChatMediator()  
user1 = User("Alice", mediator)  
user2 = User("Bob", mediator)  

user1.send("Hello, Bob!")  
user2.send("Hi, Alice!")  
```

### Visitor Pattern  
The Visitor pattern allows you to define new operations on objects without changing their classes. This pattern is useful when you need to perform operations on a set of objects with different types.  

**Example:** A shopping cart where different items can be visited for pricing or discount calculations.  

**Code Example:**  
```python  
class ShoppingCartVisitor:  
    def visit(self, item):  
        pass  

class Item:  
    def accept(self, visitor):  
        visitor.visit(self)  

class Book(Item):  
    def __init__(self, price):  
        self.price = price  

    def accept(self, visitor):  
        visitor.visit(self)  

class Fruit(Item):  
    def __init__(self, price):  
        self.price = price  

    def accept(self, visitor):  
        visitor.visit(self)  

class ConcreteVisitor(ShoppingCartVisitor):  
    def visit(self, item):  
        if isinstance(item, Book):  
            print(f"Book price: {item.price}")  
        elif isinstance(item, Fruit):  
            print(f"Fruit price: {item.price}")  

# Usage  
book = Book(20)  
fruit = Fruit(5)  
visitor = ConcreteVisitor()  
book.accept(visitor)  # Output: Book price: 20  
fruit.accept(visitor)  # Output: Fruit price: 5  
```

### Template Method Pattern  
The Template Method pattern defines the skeleton of an algorithm in a method, deferring some steps to subclasses. This pattern lets subclasses redefine certain steps of an algorithm without changing the algorithm's structure.  

**Example:** A data processing pipeline where the steps are defined in a base class.  

**Code Example:**  
```python  
class DataProcessor:  
    def process(self):  
        self.read_data()  
        self.process_data()  
        self.save_data()  

    def read_data(self):  
        raise NotImplementedError  

    def process_data(self):  
        raise NotImplementedError  

    def save_data(self):  
        print("Data saved.")  

class CSVDataProcessor(DataProcessor):  
    def read_data(self):  
        print("Reading data from CSV...")  

    def process_data(self):  
        print("Processing CSV data...")  

class JSONDataProcessor(DataProcessor):  
    def read_data(self):  
        print("Reading data from JSON...")  

    def process_data(self):  
        print("Processing JSON data...")  

# Usage  
csv_processor = CSVDataProcessor()  
csv_processor.process()  

json_processor = JSONDataProcessor()  
json_processor.process()  
```

## A little story
One day, the kingdom faced a crisis, and King Abstraction called upon his advisors, the SOLID Council , to help:

1. Sir Single Responsibility insisted that each class should focus on one task. For example, the Blacksmith class only forged weapons, while the Tailor class only sewed clothes. This ensured efficiency and clarity.
2. Lady Open/Closed suggested that the kingdom's systems should be open for extension but closed for modification. For instance, they added a new Banker class to handle finances without changing the existing Merchant class.
3. Lord Liskov Substitution ensured that any subclass could replace its parent class without breaking the system. For example, a Knight could always replace a Soldier in battle without causing chaos.
4. Dame Interface Segregation advised against forcing classes to depend on interfaces they didn’t need. She split the Worker interface into FarmerInterface , MerchantInterface , and SoldierInterface , so each class only implemented what it required.
5. Duke Dependency Inversion emphasized that high-level modules (like the KingdomManager ) should not depend on low-level modules (like Farmers or Merchants ). Instead, both should depend on abstractions, like the FoodSupplier interface.

小李拍电影，找了不同的人，每个人不一样的任务，因为**single responsiblity**。其中动画设计公司提出他们自己根据需求做设计，会做得更好，open to extension, close to modification. open-closed原则，负责灯光的小灯生病了，另一个灯光替代他，因为liskov 替代原则，小李认为自己（导演）的功能是：招人/拍戏/发行，最后想到自己应该是导演+招聘+发行方，于是分开，哪一块自己做不了了，可以找相应功能的部门支持，比如可以找专门星探公司找演员，这是interface segragation。小李把排序过程抽象成 cut/action，这样，所有的演员，相关人员，只要实现了这两个接口，理解这个概念，就能配合演戏。而不需要单独对所有的相关人员说停。即使是10年老演员，新演员，只要实现了这两个接口，就可以合作。