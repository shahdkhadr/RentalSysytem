# Spring Boot - Auto Configuration

# Overview

- Spring Boot is heavily attracting developers toward it because of three main features as follows:
    - **Auto-Configuration:**  such as checking for
        - The dependencies
        - The presence of certain classes in the classpath
        - The existence of a bean
        - The activation of some property
    - An opinionated approach to configuration.
    - The ability to create stand-alone applications.

### Classpath

- Is a pararmeter that tells the JVM and Java applications where to look for user-defined classes and packages when executing a program.
    - When we build the Java Program, there is a folder with name target created in root directory, which contain the classes ( user-defined classes ), when we run ( execute ) our program the classpath tells the JVM The path of the classes ( user-defined classes & any additional libraries like JAR files )

# What is Classpath?

- The classpath is essentially a list of locations ( directories & JAR files ) that the JVM searches to find compiled Java classes and resources.
    - **Directories:** Locations on the filesystem where .class files are stored
    - **JAR files:** Java Archive files that package multiple classes and resources into a single file

# Setting the Classpath

- You can set the classpath in several ways
    - **Environment Variable**: You can set the `CLASSPATH` environment variable in your operating system, which JVM will use when starting.
    - **Command Line**: When running a Java program, you can specify the classpath using the `-cp` or `-classpath` option in the command line:
        
        ```java
        java -cp /path/to/classes:/path/to/libs/* com.example.MainClass
        ```
        
    - **Manifest File**: In JAR files, you can specify the classpath in the `MANIFEST.MF` file.

# Spring Boot and Classpath

Spring Boot simplifies the configuration and setup of the classpath through its build tools and conventions:

- **Dependency Management**: Spring Boot applications typically use **Maven** or **Gradle** for dependency management. When you define dependencies in `pom.xml` (for Maven) or `build.gradle` (for Gradle), these tools automatically manage the classpath by including the necessary JAR files.
- **Default Locations**: Spring Boot automatically adds certain directories to the classpath, such as:
    - `src/main/java`: for Java source files.
    - `src/main/resources`: for configuration files and other resources.
    - `src/test/java`: for test classes.
    - `src/test/resources`: for test resources.
- **Spring Boot Starter**: When you use Spring Boot Starters (like `spring-boot-starter-web`), they bring in all the necessary dependencies and their transitive dependencies, which are added to the classpath automatically.

# Accessing Classpath Resources

- In Spring Boot, you can easily access resources from the classpath using the `@Value` annotation or the `ResourceLoader`. For example, you can **load properties files,** **configuration files**, or **static resources:**
    
    ```java
    @Value("classpath:application.properties")
    private Resource resource;
    
    @Autowired
    private ResourceLoader resourceLoader;
    
    public void loadResource() {
        Resource resource = resourceLoader.getResource("classpath:data/file.txt");
    }
    ```
    

# Common Issues Related to Classpath

- **ClassNotFoundException**: This occurs when the JVM cannot find a class that is referenced in your code, usually because it is not included in the classpath.
- **NoClassDefFoundError**: This error indicates that a class was present at compile time but cannot be found at runtime, often due to classpath issues.

# Classpath in Spring Boot Executable JARs

When you package a Spring Boot application as an executable JAR (using `mvn package` or `gradle build`), Spring Boot creates a JAR file that contains all the dependencies and resources in the classpath. This means you can run your application with a simple command:

**`java -jar myapp.jar`**

### Stand-Alone Applications

- is a software program that operates independently on a computer or device without needing to connect to a server or rely on other software applications to function

### Key Characteristics about Stand-Alone Applications

- **Self-Contained**: Stand-alone applications typically contain all the necessary files, libraries, and resources required for their operation, meaning they do not depend on external software.
- **Local Execution**: These applications run locally on the user's device, such as a desktop or laptop, rather than being hosted on a remote server or cloud environment.
- **User Interaction**: Stand-alone applications often provide a graphical user interface (GUI) that allows users to interact with the application directly.
- **Installation**: Users usually need to install the application on their device, which may involve downloading it from a website or using physical media like CDs or USB drives.
- **Examples**: Common examples of stand-alone applications include word processors (like Microsoft Word), media players (like VLC), and games that do not require an internet connection.

# Auto-Configuration in Spring Boot

- **`@Conditional`** annotation acts as a base for the Spring Boot auto-configuration annotation extensions.
    - It automatically **registers** the
        - beans with @Component, @Configuration, @Bean,
        - and meta-annotations for building custom stereotype annotations
- **`@EnableAutoConfiguration` is used for enable the auto-configuration feature of Application Context by scanning the classpath components and registering the beans**
    - **(  وظيفتها تفعّل الاوتو كونفيجريشن في الابليكيشن كونتيكست من خلال فحص مكونات الكلاس باث وتسجيل او تعريف البينز )**
    - This annotation is wrapped **inside the `@SpringBootApplication`** annotation along with **`@ComponentScan`** and **`@SpringBootConfiguration`** annotations.
    - When running main() method, this annotation initiates auto-configuration.
        - Auto-Configuration لما نعمل رن بينعمل انيشالايزيشن لل

> ***Note**: You should use the ‘@EnableAutoConfiguration’ annotation **only one time** in your application.*
> 

> *‘spring-boot-autoconfigure.jar’ is the file that looks after all the auto-configuration. 
( ملف الجار هاذ بيظهر بعد ما ينتهي من تنفيذ كل الاوتو كونفيجريشن )*
> 

> *All auto-configuration logic for MVC, data, JMS, and other frameworks is present in a single jar
كل الكونفيجريشن لوجيك, بالنهاية بتكون موجود بداخل ملف واحد وهو الجار فايل*
> 

![image.png](Spring%20Boot%20-%20Auto%20Configuration%20132f1472fabd8002b6d1f5d5d9d829a6/image.png)

# Working of Auto-Configuration in Spring Boot

## 1. Dependencies

- Auto-Configuration is the main focus of the Spring Boot development.
- Our Spring application needs a respective set of dependencies to work.
- Spring Boot auto-configures a pre-set of the required dependencies without a need to configure them manually.
- This greatly helps and can be seen when we want to create a stand-alone application.
- When we build our application, Spring Boot looks after our dependencies and configures both the underlying Spring Framework and required jar dependencies (third-party libraries ) on the classpath according to our project built.
- It helps us to avoid errors like mismatches or incompatible versions of different libraries.
- If you want to override these defaults, you can override them after initialization.

## Tools

### Maven

**Example 1: pom.xml**

### code

```xml
<?xml version="1.0" encoding="UTF-8"?> 
<project xmlns="http://maven.apache.org/POM/4.0.0" 
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd"> 
    <modelVersion>4.0.0</modelVersion> 
    <parent> 
        <groupId>org.springframework.boot</groupId> 
        <artifactId>spring-boot-starter-parent</artifactId> 
        <version>2.5.6</version> 
        <relativePath/> <!-- lookup parent from repository -->
    </parent> 
    <groupId>sia</groupId> 
    <artifactId>taco-cloud</artifactId> 
    <version>0.0.1-SNAPSHOT</version> 
    <name>taco-cloud</name> 
    <description>Demo project for Spring Boot</description> 
    <properties> 
        <java.version>11</java.version> 
        <vaadin.version>14.7.5</vaadin.version> 
    </properties> 
    <dependencies> 
        <dependency> 
            <groupId>org.springframework.boot</groupId> 
            <artifactId>spring-boot-starter-thymeleaf</artifactId> 
        </dependency> 
        <dependency> 
            <groupId>org.springframework.boot</groupId> 
            <artifactId>spring-boot-starter-web</artifactId> 
        </dependency> 
  
        <dependency> 
            <groupId>org.springframework.boot</groupId> 
            <artifactId>spring-boot-devtools</artifactId> 
            <scope>runtime</scope> 
            <optional>true</optional> 
        </dependency> 
        <dependency> 
            <groupId>org.springframework.boot</groupId> 
            <artifactId>spring-boot-starter-test</artifactId> 
            <scope>test</scope> 
        </dependency> 
        <dependency> 
            <groupId>org.springframework.boot</groupId> 
            <artifactId>spring-boot-starter-jersey</artifactId> 
        </dependency> 
        <dependency> 
            <groupId>org.springframework.boot</groupId> 
            <artifactId>spring-boot-starter-web-services</artifactId> 
        </dependency> 
        <dependency> 
            <groupId>org.springframework.boot</groupId> 
            <artifactId>spring-boot-starter-webflux</artifactId> 
        </dependency> 
        <dependency> 
            <groupId>com.vaadin</groupId> 
            <artifactId>vaadin-spring-boot-starter</artifactId> 
        </dependency> 
        <dependency> 
            <groupId>io.projectreactor</groupId> 
            <artifactId>reactor-test</artifactId> 
            <scope>test</scope> 
        </dependency> 
        <dependency> 
            <groupId>org.springframework.boot</groupId> 
            <artifactId>spring-boot-starter</artifactId> 
        </dependency> 
        <dependency> 
            <groupId>org.projectlombok</groupId> 
            <artifactId>lombok</artifactId> 
            <optional>true</optional> 
        </dependency> 
        <dependency> 
            <groupId>org.springframework.boot</groupId> 
            <artifactId>spring-boot-starter-data-jdbc</artifactId> 
        </dependency> 
        <dependency> 
            <groupId>org.springframework.boot</groupId> 
            <artifactId>spring-boot-starter-jdbc</artifactId> 
        </dependency> 
        <dependency> 
            <groupId>com.h2database</groupId> 
            <artifactId>h2</artifactId> 
            <scope>runtime</scope> 
        </dependency> 
        <dependency> 
            <groupId>org.springframework.boot</groupId> 
            <artifactId>spring-boot-starter-data-jpa</artifactId> 
        </dependency> 
        <dependency> 
            <groupId>org.springframework.boot</groupId> 
            <artifactId>spring-boot-starter-security</artifactId> 
        </dependency> 
        <dependency> 
            <groupId>org.springframework.security</groupId> 
            <artifactId>spring-security-test</artifactId> 
            <scope>test</scope> 
        </dependency> 
        <dependency> 
            <groupId>mysql</groupId> 
            <artifactId>mysql-connector-java</artifactId> 
            <scope>runtime</scope> 
        </dependency> 
        <dependency> 
            <groupId>org.springframework.boot</groupId> 
            <artifactId>spring-boot-starter-hateoas</artifactId> 
        </dependency> 
        <dependency> 
            <groupId>org.springframework.boot</groupId> 
            <artifactId>spring-boot-starter-data-rest</artifactId> 
        </dependency> 
    </dependencies> 
  
    <build> 
        <plugins> 
            <plugin> 
                <groupId>org.springframework.boot</groupId> 
                <artifactId>spring-boot-maven-plugin</artifactId> 
                <configuration> 
                    <excludes> 
                        <exclude> 
                            <groupId>org.projectlombok</groupId> 
                            <artifactId>lombok</artifactId> 
                        </exclude> 
                    </excludes> 
                </configuration> 
            </plugin> 
        </plugins> 
    </build> 
  
    <dependencyManagement> 
        <dependencies> 
            <dependency> 
                <groupId>com.vaadin</groupId> 
                <artifactId>vaadin-bom</artifactId> 
                <version>${vaadin.version}</version> 
                <type>pom</type> 
                <scope>import</scope> 
            </dependency> 
        </dependencies> 
    </dependencyManagement> 
    <profiles> 
        <profile> 
            <id>production</id> 
            <build> 
                <plugins> 
                    <plugin> 
                        <groupId>com.vaadin</groupId> 
                        <artifactId>vaadin-maven-plugin</artifactId> 
                        <version>${vaadin.version}</version> 
                        <executions> 
                            <execution> 
                                <id>frontend</id> 
                                <phase>compile</phase> 
                                <goals> 
                                    <goal>prepare-frontend</goal> 
                                    <goal>build-frontend</goal> 
                                </goals> 
                                <configuration> 
                                    <productionMode>true</productionMode> 
                                </configuration> 
                            </execution> 
                        </executions> 
                    </plugin> 
                </plugins> 
            </build> 
        </profile> 
    </profiles> 
</project>
```

- When you build a Spring Boot project, the ‘Starter Parent’ dependency gets automatically added in the ‘pom.xml’ file.
- It notifies that the essential ‘sensible’ defaults for the application have been auto-configured and you therefore can take advantage of it.
    - السينسيبل المقصود فيها همي الديبيندينسي اللي مستخدمينهم في ال بوم اكس ام ال فايل
    
    ```
    <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>...</version>
    </parent>
    ```
    
- To add the dependency ( library of tech stacks ), you don’t need to mention the version of it because the Spring Boot automatically configures it for you.
- Also, when you update/change the Spring Boot version, all the versions of added dependencies will also get updated/changed.
    
    ```
    <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    ```
    
- It is Spring Boot’s auto-configuration that makes managing dependencies supremely easy for us.
- With the help of enabling ‘debug logging’ in the ‘application.properties’ file, we can know more about auto-configuration.

# Ref.

1. [Spring Boot - Auto-configuration - GeeksforGeeks](https://www.geeksforgeeks.org/spring-boot-auto-configuration/)
2. [Stand-Alone Applications & Java Spring Boot Classpath](https://chatgpt.com/share/67262fac-d66c-8006-9106-9b1e3425dd66)
3. [Classpath](https://chatgpt.com/share/67263078-9834-8006-85a4-af75da2c87b4)
4. [Classpath - Setting the Classpath](https://chatgpt.com/share/672635d7-616c-8006-a460-76deac00587e)