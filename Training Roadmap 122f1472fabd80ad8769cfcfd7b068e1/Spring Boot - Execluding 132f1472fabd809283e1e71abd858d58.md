# Spring Boot - Execluding

- In Spring Boot, auto-configuration is a mechanism that automatically configures your application based on the dependencies and settings it detects. This feature is designed to simplify application setup by providing sensible defaults.
- However, there may be cases where you want to exclude certain default configurations that Spring Boot would otherwise load automatically. The `@EnableAutoConfiguration` annotation helps with this.
    
    بنقدر نستثني الشغلات اللي بدناش يتطبق عليها الديفولت او الاوتو كونفيجريشن Execlude  من خلال ال 
    
- Here's how `@EnableAutoConfiguration(exclude = { ... })` works and how you can use it effectively.

# How `@EnableAutoConfiguration` Works

- When you create a Spring Boot application, the `@SpringBootApplication` annotation is usually present in your main application class. This annotation combines three others:
    
    ```java
    @SpringBootApplication
    public class MyApplication {
        public static void main(String[] args) {
            SpringApplication.run(MyApplication.class, args);
        }
    }
    ```
    
- `@SpringBootApplication` includes:
    1. `@Configuration`: Marks the class as a source of bean definitions.
    2. `@EnableAutoConfiguration`: Enables auto-configuration, allowing Spring Boot to load various components automatically based on your dependencies and settings.
    3. `@ComponentScan`: Enables component scanning in the package where the main class is located.

# Excluding Specific Auto-Configurations

- Sometimes, you might want to exclude a particular auto-configuration class, which prevents Spring Boot from applying specific default configurations.
- For instance, if you add the `spring-boot-starter-data-jpa` dependency, Spring Boot will configure a default DataSource, transaction manager, and other JPA-related beans. However, if you want to manage these configurations manually or don’t need them, you can exclude them.
- Here’s how to exclude an auto-configuration class:
- Example
    - Suppose you want to exclude the `DataSourceAutoConfiguration` class, which configures the default data source:
        
        ```java
        @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
        public class MyApplication {
            public static void main(String[] args) {
                SpringApplication.run(MyApplication.class, args);
            }
        }
        ```
        
    - In this example:
        - `DataSourceAutoConfiguration.class` is excluded, so Spring Boot will not automatically configure a data source. If your application doesn’t have a specific data source configuration after this exclusion, Spring Boot may throw an exception if it tries to access the database, since the configuration will be missing.

# Why Exclude Auto-Configuration?

Excluding auto-configuration can be useful for several reasons:

1. **Avoid Unwanted Dependencies or Behaviors**: Sometimes, you may want to disable default configurations that come with certain dependencies, especially if you want more control over how they’re configured.
2. **Custom Configurations**: You might want to replace default beans with custom ones. For example, if you want to configure the `DataSource` manually, excluding `DataSourceAutoConfiguration` prevents conflicts and lets you control the setup.
3. **Testing**: During testing, you may want to load only specific configurations, excluding others to simplify the test environment.

# Exclude Multiple Auto-Configuration Classes

```java
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
```

# Alternative Way: `spring.autoconfigure.exclude`

- In addition to using `@EnableAutoConfiguration` at the class level, you can exclude auto-configurations in your `application.properties` or `application.yml` file:

**`spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration`**

# Common Auto-Configuration Classes You Might Exclude

Some commonly excluded auto-configurations include:

- `DataSourceAutoConfiguration`: Excludes the default database configuration.
- `HibernateJpaAutoConfiguration`: Excludes Hibernate JPA configuration.
- `SecurityAutoConfiguration`: Excludes default Spring Security configuration.
- `WebMvcAutoConfiguration`: Excludes default Spring MVC setup, useful if you’re building a non-web application.

# Ref.

[Java Spring Boot - Execlude](https://chatgpt.com/share/6726489f-7558-8006-b12c-1b5985b39fc5)