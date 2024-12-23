# Bean Scopes

- Bean Scopes determine how they are instantiated and managed by the container

# Introduction

- Most common scopes
    - Singleton
    - Prototype
    - Request
    - Session
    - Application
    - WebSocket
    - you can create custom scope depend needs of application
- Choosing the right scope depends on
    - the requirements of the application
    - and the desired lifecycle of the managed object.

> ***Singleton scope in Spring is the DEFAULT scope for beans***
> 

# Singleton Scope

- This mean **Only a single instance of the bean** is created **per Spring application context**
- This **instance** is **stored in memory** and reused each time the bean is requested in the future, rather than creating a new instance for each request

## Example

```java
@Component
@Scope("singleton")
public class SingletonBean {
   
}
```

## Key Features of Singleton Scope in Spring

- **Single Instance:** Only one instance of the bean is created in the entire Spring Application Lifecycle
    - Each time the bean is requested , the same previously created instance is returned, if it already exist.
- **Stored in memory:** The bean instance is stored in memory, which means that it persists for the lifetime of the application and is available for use at any time.
- **Shared:** The same singleton bean is shared between different components or classes that inject it into their Spring application context. This can have implications for concurrency and bean state, so care must be taken when handling shared data in singleton beans.
- **Efficiency:** Using singleton beans can improve the efficiency and performance of the application by avoiding the overhead of creating multiple instances of the same bean for each request.

<aside>
💡

> concurrency & Bean State related to shared data موضوع ال Singleton مشاكل ال
> 
</aside>

# Prototype Scope

- It means that a **new instance of the bean is created each time it is requested** .**(Unlike the singleton scope)**
    
    ```java
    @Component
    @Scope("prototype")
    public class PrototypeBean {
       
    }
    ```
    

## Key Features of Prototype Scope in Spring

- New Instance for each request
- No state sharing
- **Unmanaged lifecycle ( not managed completely by Spring IoC Container )**
    - This means that Spring does not take care of destroying prototype bean instances when they are no longer in use. It is the responsibility of the developer to manage the lifetime of prototype bean instances and release resources appropriately.
- **Greater configuration flexibility ( Create different instances with different configurations from the same bean )**
    
    ```java
    @Bean
    @Scope("prototype")
    public Printer printer(String formatType) {
        return new Printer(formatType);
    }
    
    // Later in your code, you can get different instances:
    Printer pdfPrinter = context.getBean(Printer.class, "PDF");
    Printer wordPrinter = context.getBean(Printer.class, "Word");
    
    ```
    
- **Higher resource consumption ( new instance for each request )**
- **Suitable for objects with changing state**

<aside>
💡

الها عالية Computational Cost انو ال prototypeمساوئ ال

Spring **doesn’t manage the full lifecycle** **(especially destruction)** of prototype-scoped beans.

</aside>

# As a rule,

> **you should use the prototype scope for all stateful beans and the singleton scope for stateless beans**.
> 

# Request Scope

- It means that a **new instance of the bean is created for each HTTP request that arrives at the server.**
- The instance of the bean will be **available for the duration** of the **processing of that specific request**, and will be **destroyed at the end of the request.**

## Key Features of Prototype Scope in Spring

- One instance per request
- In-request state sharing مثل موضوع الليرز
    - *Scenario*: In a web application, multiple components (e.g., controllers, services) within the same request may need to access the same instance of a request-scoped bean. For example, if you have a `ShoppingCart` bean, both a controller and a service can access and modify the same instance of `ShoppingCart` during the request.
- Limited to Request Context
    - *Scenario*: A bean with request scope is only accessible during the lifecycle of a single request. Once the request is complete, the bean instance is destroyed and cannot be reused in other requests.
- It is important to note that the Spring request scope only makes sense in the context of web applications,
    - and will only be available in the context of a web request that is handled by a Spring framework, such as Spring MVC.
    - In addition, **care** should be taken when **handling shared data** in beans with scope request, as it can have **implications on concurrency and state integrity** in applications with **multiple concurrent requests.**

# **Session Scope**

- **a single instance of the bean is created for each HTTP session that is established in the application.**
- This instance of the bean will be available for the duration of the user’s session, and will be destroyed at the end of the session.
    
    ```java
    @Component
    @SessionScope
    public class SessionBean {
        
    }
    ```
    

## Key Features of Session Scope

- **One instance per session**: Each time a user logs into the web application, a new instance of the bean with scope “session” is created for that specific session. This instance of the bean will be available for the duration of the user’s session, and will be destroyed at the end of the session.
- **In-memory storage during the session**: The bean instance with scope “session” is stored in memory for the duration of the user’s session.
    - This allows to have a specific state for each user session and to keep the session-related information persistent during the user’s navigation in the application.
- **If a user logs out or the session expires, the bean instance is automatically destroyed**. This allows for automated management of bean instances based on the lifecycle of the user’s session.
- **Shared within the same session**: The same bean with scope “session” is shared between different components or classes that inject it in the context of the same user session. This allows the same instance of the bean to be accessed in different parts of the application during the same user session.
- **Useful in web applications**: The session”scope is especially useful in web applications, where specific user session information needs to be maintained, such as authentication data, user preferences or session state.
- It is important to note that the session scope in Spring only makes sense in the context of web applications based on user session technologies, **such as HTTP sessions**. In addition, **concurrency management and security** need to be taken into account when handling shared data in beans with session scope, as they may have implications on the integrity and privacy of user session information.

# Application Scope

- **beans whose instances are intended to be shared globally throughout the application**, meaning that they can be accessed from anywhere in the application at any time. **( يعني مشاع )**
    
    ```java
    @ApplicationScope
    @Component
    public class ApplicationBean{
       
    }
    ```
    

## Key Features of Application Scope

- **A bean instance is created at the start of the application** and is maintained for the lifetime of the application. This means that there will only be a single instance of the bean in the entire application, and all parts of the application will access the same instance.
- **The bean instance is stored in a global scope and can be accessed from any part of the application**. This allows sharing global data or functionality in the application, such as configurations, utilities, cache objects, and so on.
- **The Application scope is useful to define beans that need to be shared between multiple components**, services or controllers in a Spring application, and that need to maintain a global state and be accessible from any part of the application.
- **The use of application scoping should be careful** and consider the impact on performance and scalability, as the bean instance is kept in memory for the lifetime of the application, which may consume server resources.

# Singleton Vs. Application Scope

- A **`ServletContext`** is shared between all servlets living on the same servlet container ( e.g. Tomcat ). This is a Java EE class (it belongs to the package `javax.servlet`) .
    - Beans annotated with **`@ApplicationScope`** are bounded to the **ServletContext**
- An ApplicationContext represents a Spring IoC Container, so it’s a Spring-Specific class (it belongs to the  package `org.springframework.context`).
- Si**ngleton scoped beans are bounded to the ApplicationContext.**

> You can have multiple IoC container in the same servlet container, so you can have multiple singleton beans of the same type but **only one application scoped** bean of each type.
> 

![image.png](Bean%20Scopes%20124f1472fabd80d4af65eb85225b5fed/image.png)

# **WebSocket Scope**

- Used in websocket applications, for example for bidirectional message exchange between client and server.
- This type of Scope will live as long as the WebSocket session is alive.
- We can also say that it exhibits singleton behavior, but limited to a *WebSocket* session only. ( بيوخذ نفس سلوك السنجلتون )

![image.png](Bean%20Scopes%20124f1472fabd80d4af65eb85225b5fed/image%201.png)

> **Non-web Application —> Singleton & prototype
Web application —> Request, Session, Application & Websocket**
>