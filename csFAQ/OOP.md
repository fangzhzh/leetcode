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
- Example: Payment methods
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
- Example: Split large interfaces:
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
- Example:
  ```python
  # Bad
  class EmailService:
      def send(self, mysql_database)
  
  # Good
  class EmailService:
      def send(self, database_interface)
  ```
## Design Patterns

### Creational Patterns
- **Singleton**: Ensures a class has only one instance
    - java singleton implementation [双重检查单例模式](./volatile.md)
- **Factory Method**: Creates objects without specifying the exact class
- **Abstract Factory**: Creates families of related objects
- **Builder**: Constructs complex objects step by step
- **Prototype**: Creates new objects by cloning an existing object

#### 面试问题
* What is the Factory Method pattern? How does it differ from the Abstract Factory pattern?

### Structural Patterns
- **Adapter**: Allows incompatible interfaces to work together
- **Bridge**: Separates an object's interface from its implementation
- **Composite**: Composes objects into tree structures
- **Decorator**: Adds new functionality to existing objects dynamically
- **Facade**: Provides a simplified interface to a complex subsystem
#### 面试问题
* Can you explain the Decorator pattern and how it differs from the Adapter pattern?
* What is the difference between the Composite pattern and the Decorator pattern?
### Behavioral Patterns
- **Observer**: Defines a one-to-many dependency between objects
- **Strategy**: Defines a family of algorithms and makes them interchangeable
- **Command**: Encapsulates a request as an object
- **State**: Allows an object to alter its behavior when its internal state changes
- **Template Method**: Defines the skeleton of an algorithm in a method

#### 面试问题

## Can you explain the difference between the MVC and MVP patterns?