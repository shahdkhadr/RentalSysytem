# Spring Boot - Bean Customization

- Customizing Beans allows you to adjust configuration, behavior or dependencies of components that Spring manages within the application context.
- Beans Customization is essential for
    - achieving specific configurations based on requirements,
    - including setting different profiles,
    - injecting custom properties
    - defining the lifecycle behaviors of a bean

# Various ways to customize beans in Spring Boot

## **1. Using `@Bean` and `@Configuration`**

- The **most common** way to customize beans is by defining them in a @Configuration class.
- Here you can define beans and set up any required configuration, like
    1. setting default values, 
    2. dependencies, 
    3. or modifiying properties 

```java
@Configuration
public class AppConfig {
    @Bean
    public MyService myService() {
        MyService service = new MyService();
        service.setSomeProperty("Customized Value");
        return service;
    }
}
```

## 2. Customizing Bean Properties via **`@Value`** and Application Properties

- Injecting values from **`application.properties`** (or **`application.yml`**) using the **`@Value`** annotation allows for flexible configurations based on the environment.

```java
# application.properties
my.custom.value=Hello, Spring Boot!
```

```java
@Component
public class MyService {
    @Value("${my.custom.value}")
    private String customValue;

    // Constructor or methods that use customValue
}
```

## 3. Using Conditional Beans with **`@Conditional`** and Profiles

- عشان ننفذ أشياء بناء على حدوث شرط معيّن ممكن يكون  @Conditional بنستخدم ال
الشرط على مستوى السيستيم أو ع مستوى الكود تبعك , او ع وجود مكتبة أو عدمه
بتعمل لود للكود بناء على الاينفايرونمينت اللي هو موجود فيها @Profileأما بالنسبة لل
dev, prod, testمثل ال
- Spring allows conditional bean creation using **`@Conditional`** annotations or **`@Profile`**.
    - *This can help create **beans that only load under certain conditions**, like based on environment profiles ( **`@Profile(”dev”)`** )*
    
    ```java
    @Configuration
    public class AppConfig {
        @Bean
        @Profile("dev")
        public MyService devService() {
            return new MyService("Development Service");
        }
    
        @Bean
        @Profile("prod")
        public MyService prodService() {
            return new MyService("Production Service");
        }
    }
    ```
    
- **`@Profile`** Vs. **`@Conditional`**
    - https://chatgpt.com/share/672712ee-6d14-8006-875b-2d39391f3554

## Register Beans Vs. Execute Beans

- Registering a bean means that Spring is **making the bean available in the application context** so it can be used or injected into other parts of your application when needed.
- **Registration vs. Execution**:
    - **Registration**: When a bean is registered, Spring creates an instance of it and stores it in the application context. It does not necessarily execute any methods within the bean at this stage. This is more about *making it available* for the application's lifecycle.
    - **Execution**: Beans are typically "executed" when other parts of the application call methods on them. For example, a `Service` bean might have a method like `processData()`, which is only executed when the method is explicitly called by another component.

## 4. Using **`BeanPostProcessor`** for Advanced Customization

- A **`BeanPostProcessor`** allows you to intercept beans ( تمسكها أو توصل الها ) before and after they are initialized, which is useful for adding custom logic during the bean lifecycle.

```java
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (bean instanceof MyService) {
            // Customize or modify the MyService bean
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (bean instanceof MyService) {
            // Further customization
        }
        return bean;
    }
}
```

## 5. Customizing Bean Scope **`@Scope`**

- By default, Spring beans are singleton-scoped. However, using **`@Scope`**, you can change the scope to **`prototype`**, **`request`**, **`session`**, etc., which changes how and when the bean is instantiated.

```java
@Bean
@Scope("prototype")
public MyPrototypeService myPrototypeService() {
    return new MyPrototypeService();
}
```

## **6. Custom Initializers and Destruction Callbacks (`@PostConstruct` and `@PreDestroy`)**

You can use **`@PostConstruct`** for any initialization after dependencies are injected, and **`@PreDestroy`** for cleanup before a bean is destroyed.

**`@PostConstruct` —> Bean لل Initializationتتنفذ بعد ال methodبدنا ال**

**`@PreDestroy` —> Bean لل destroy تتنفذ قبل ما ينعمل**  methodبدنا ال

```java
@Component
public class MyService {
    @PostConstruct
    public void init() {
        System.out.println("Bean is initialized with @PostConstruct.");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("Bean is about to be destroyed.");
    }
}
```

## 7. Using **`@ConfigurationProperties`** for Complex Configuration Mapping

- If you have a complex configuration, you can map properties to a Java class using **`@ConfigurationProperties`**. This allows grouping related configurations and injecting them as a single object.
    
    ```java
    app.datasource.url=jdbc:mysql://localhost:3306/mydb
    app.datasource.username=root
    app.datasource.password=secret
    ```
    
    ```java
    @ConfigurationProperties(prefix = "app.datasource")
    public class DataSourceConfig {
        private String url;
        private String username;
        private String password;
        // Getters and setters
    }
    ```
    
    > ***Inject all app.datasource properties into DataSourceConfig class***
    > 
    
    - Then, register the configuration class as a bean:
        
        ```
        @Configuration
        @EnableConfigurationProperties(DataSourceConfig.class)
        public class AppConfig {
            // Further customization if needed
        }
        ```
        
        ### Explanation this step
        
        - In Spring Boot, `@EnableConfigurationProperties` is essential for enabling and registering configuration properties classes like `DataSourceConfig` so they can be injected as Spring Beans. Let’s delve into the specifics of why this works and how it simplifies configuration management.
        
        ### Why Register the Configuration Class as a Spring Bean?
        
        - By default, Spring does not automatically manage any class with `@ConfigurationProperties`. To have Spring instantiate and configure these properties automatically, we need to tell it to manage `DataSourceConfig` as a bean. This is achieved by using `@EnableConfigurationProperties`, which instructs Spring to bind external configuration properties (from `application.properties` or `application.yml`) to this specific class.
        
        ### Step-by-Step Breakdown of the Code
        
        ### 1. `@Configuration`
        
        The `@Configuration` annotation marks `AppConfig` as a configuration class. This tells Spring that this class can define beans and configurations.
        
        ### 2. `@EnableConfigurationProperties(DataSourceConfig.class)`
        
        `@EnableConfigurationProperties(DataSourceConfig.class)` specifically registers `DataSourceConfig` as a bean, allowing it to work with `@ConfigurationProperties`. Here’s how it works:
        
        - **Registers the Bean**: `@EnableConfigurationProperties` tells Spring to create an instance of `DataSourceConfig` and add it to the Spring application context, meaning it can now be injected as a dependency.
        - **Property Binding**: This annotation ensures that Spring automatically binds the values from the properties file (with the prefix `app.datasource`) to the fields in `DataSourceConfig`.
        - **Automatic Population**: When `DataSourceConfig` is injected anywhere in your code, Spring provides an instance with fields (`url`, `username`, and `password`) pre-populated from the configuration file.
        
        ```java
        import org.springframework.boot.context.properties.EnableConfigurationProperties;
        import org.springframework.context.annotation.Configuration;
        
        @Configuration
        @EnableConfigurationProperties(DataSourceConfig.class)
        public class AppConfig {
            // Additional bean configurations if needed
        }
        ```
        
        ### 3. How `DataSourceConfig` Becomes Injectable
        
        - Once `DataSourceConfig` is registered in this way, you can inject it anywhere you need:
            
            ```
            import org.springframework.beans.factory.annotation.Autowired;
            import org.springframework.stereotype.Service;
            
            @Service
            public class SomeService {
            
                private final DataSourceConfig dataSourceConfig;
            
                @Autowired
                public SomeService(DataSourceConfig dataSourceConfig) {
                    this.dataSourceConfig = dataSourceConfig;
                }
            
                public void printDataSourceConfig() {
                    System.out.println("URL: " + dataSourceConfig.getUrl());
                    System.out.println("Username: " + dataSourceConfig.getUsername());
                }
            }
            ```
            
        
        ### Why Use `@EnableConfigurationProperties`?
        
        - **Loose Coupling**: You don’t need to hardcode configuration values in the code, promoting separation of concerns.
        - **Type-Safe Binding**: Because Spring Boot maps properties to specific fields in `DataSourceConfig`, your IDE can help catch type errors and provide autocompletion.
        - **Centralized Configuration**: `DataSourceConfig` consolidates related properties, making them easy to manage, understand, and test.
        
        ### Additional Bean Configurations in `AppConfig`
        
        If needed, `AppConfig` can also contain other beans and configurations. For example, you might add a `DataSource` bean if you wanted to customize your database connection based on `DataSourceConfig` properties.
        
        ```java
        import org.springframework.boot.context.properties.EnableConfigurationProperties;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        
        @Configuration
        @EnableConfigurationProperties(DataSourceConfig.class)
        public class AppConfig {
        
            private final DataSourceConfig dataSourceConfig;
        
            public AppConfig(DataSourceConfig dataSourceConfig) {
                this.dataSourceConfig = dataSourceConfig;
            }
        
            @Bean
            public DataSource customDataSource() {
                // Configure a DataSource object using properties from dataSourceConfig
                DataSource dataSource = new DataSource();
                dataSource.setUrl(dataSourceConfig.getUrl());
                dataSource.setUsername(dataSourceConfig.getUsername());
                dataSource.setPassword(dataSourceConfig.getPassword());
                return dataSource;
            }
        }
        ```
        
        Ref.: [https://chatgpt.com/share/67272292-f0bc-8006-8e19-08445ef05e6e](https://chatgpt.com/share/67272292-f0bc-8006-8e19-08445ef05e6e)
        

## **8. Customizing Beans with `@Primary`**

- When multiple beans of the same type exist, you can specify which one should be used by default with the **`@Primary`** annotation. This helps avoid **`NoUniqueBeanDefinitionException`** when autowiring beans of the same type.
    - من نفس النوع بنقدر نحددله مين منهم Bean لما يكون في عندنا أكثر من 
    default اللي رح يعتبرها ك

```java
@Bean
@Primary
public MyService primaryService() {
    return new MyService("Primary Service");
}

@Bean
public MyService secondaryService() {
    return new MyService("Secondary Service");
}
```

## 9. Using Factory Beans for Complex Bean Customization ( Will Go back again )

- A FactoryBean allows more complex bean instantiation, especially for objects that may need non-standard initialization.

## **10. Customizing Beans with `BeanDefinition` ( Will Go back Again )**

For very advanced cases, you can modify **`BeanDefinition`** to change bean properties like lazy loading, primary status, or other attributes programmatically within **`BeanFactoryPostProcessor`**.