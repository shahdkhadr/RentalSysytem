# The Difference between DI & IoC

- Both play central role in making the framework
    - flexible
    - modular
    - easier to manage

 

# 1. Inversion of Control (IoC)

- Is a **design principle** that inverts the control flow of an application.
- It means that instead of the programmer controlling
    - **how dependencies are created & managed**
- This control is delegated to a framework ( in this case spring boot )

## Traditional Flow

- In a traditional program, the object that requires a dependency is responsible for creating or fetching it. For example:
    
    ```java
    public class Car {
        private Engine engine;
    
        public Car() {
            this.engine = new Engine(); // Car is responsible for creating the Engine
        }
    }
    ```
    
    - Here, the `Car` class is **tightly coupled** with the `Engine` class. This approach makes testing and maintaining the `Car` class difficult because you cannot easily change the `Engine` it uses without modifying the `Car` class.

## IoC Principle

- With IoC, This responsibility( responsibility of how dependencies are created & managed ), is transferred to the **Spring IoC Container**
- The framework ( Spring ) is in control of managing the dependencies and how objects are created and linked together.
- Spring manages the
    - Lifecycle of objects( beans )
    - creates Objects (beans)
    - Inject their dependencies
- Example
    
    ```java
    @Component
    public class Engine { ... }
    
    @Component
    public class Car {
        private final Engine engine;
    
        @Autowired
        public Car(Engine engine) { // Engine is injected by the Spring container
            this.engine = engine;
        }
    }
    ```
    
    - Here, Spring takes control of creating the `Engine` object and passing it into the `Car` class. This results in a loosely-coupled design where `Car` no longer manages the creation of `Engine`.

# 2. Dependency Injection (DI)

- is a **specific technique used to achieve IoC**
- It refers to the **process of providing an object with its dependencies** rather than the object creating them itself.

## Types of DI

### 1. Constructor Injection

- Dependencies are provided through a class constructor
    
    ```java
    @Component
    public class Car {
        private final Engine engine;
    
        @Autowired
        public Car(Engine engine) {
            this.engine = engine;
        }
    }
    ```
    
    - Benefits
        - **Promotes immutability** because dependencies are set through the constructor and cannot be changed later.
        - Makes it easier to understand what dependencies a class requires.
        - Easier to write unit tests with mock dependencies.

### 2. Setter Injection

- Dependencies are provided through setter methods.
    
    ```java
    @Component
    public class Car {
        private Engine engine;
    
        @Autowired
        public void setEngine(Engine engine) {
            this.engine = engine;
        }
    }
    ```
    
- **Benefits**:
    - Allows changing dependencies after the object is constructed.
    - Useful when a dependency is optional.
- **Drawbacks**:
    - May lead to mutable state.
    - Less clear about which dependencies are mandatory.

### 3. Field Injection

- Dependencies are injected directly into the fields of a class.
    
    ```java
    @Component
    public class Car {
        @Autowired
        private Engine engine;
    }
    ```
    
- **Benefits**:
    - Simpler and less boilerplate**( أبسط )** code.
- **Drawbacks**:
    - Not ideal for unit testing, as it can make mocking difficult.
    - Considered less clean because dependencies are not explicitly visible in constructors.

# Relationship Between IoC and DI

- **IoC** is a broader concept **( مفهوم أوسع )**, describing the overall framework's control over the application flow. It defines the mechanism that allows a framework (like Spring) to manage object creation, wiring dependencies, and managing their lifecycle.
- **DI** is a way to implement IoC. It allows dependencies be injected into an object( like a class ) rather than the object creating its own dependencies.
    - **It is a pattern used by the IoC container to achieve loose coupling between components.**

# How IoC Works Here