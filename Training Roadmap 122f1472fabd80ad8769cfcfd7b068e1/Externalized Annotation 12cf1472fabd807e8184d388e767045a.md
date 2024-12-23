# Externalized Annotation

- **Externalized Annotation** allows us to separate configuration from code .
    - ( بتسمحلنا نفصل الكونفيجريشن عن الكود )
    - so you that you can use same code in different environments without modification.
- This approach us often applied using annotations like **`@Value`** or **`@ConfigurationProperties`** to inject properties from external sources ( such as application properties or YAML files ) into your spring boot .

# **`@Value`**

- The **`@Value`** annotation can **inject individual properties directly into fields in your Spring beans.**
- It’s ideal for simple configuration that don’t need complex mapping.

## Example

```java
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyService {

    @Value("${myapp.datasource.url}")
    private String datasourceUrl;

    @Value("${myapp.datasource.username}")
    private String username;

    @Value("${myapp.datasource.password}")
    private String password;

    // Getters and other methods
}

```

- In this example, values for `datasourceUrl`, `username`, and `password` are taken from `application.properties` (or `application.yml`)

```java
myapp.datasource.url=jdbc:mysql://localhost:3306/mydb
myapp.datasource.username=root
myapp.datasource.password=secret
```

# **`@ConfigurationProperties`**

- The **`@ConfigurationProperties`** annotation is more powerful than **`@Value`** for complex configuration , allowing you to map entire properties hierarchies to POJOs
    
    ## POJO
    
    - POJO stands for **Plain Old Java Object**. It refers to a simple Java object that is not bound by any specific framework conventions. POJOs typically have the following characteristics:
        1. **No Special Annotations or Framework Dependencies**: They don’t rely on any specific libraries or frameworks, making them easy to use and portable.
        2. **Private Fields**: POJOs usually have private member variables (fields) to encapsulate the data.
        3. **Getters and Setters**: They typically provide public getter and setter methods to access and modify the private fields.
        4. **Constructors**: POJOs may have one or more constructors to create instances of the object.
        5. **Serializable**: They can implement the `Serializable` interface if the object needs to be serialized (converted to a byte stream for storage or transmission).
- It’s especially useful when you have a structured set of properties that can be grouped into a dedicated class.

## Example

### **`ConfigController.java`**

```java
package com.boostmytool.beststore.controllers;

import com.boostmytool.beststore.dto.ProductDTO;
import com.boostmytool.beststore.services.MyService;
import com.boostmytool.beststore.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ConfigController {

    @Autowired
    private final MyService myService;

    public ConfigController(MyService myService) {
        this.myService = myService;
    }
    @GetMapping("/test")
    public void printResult(){
        myService.connect();
    }
}
```

### **`DatasourceConfig.java`**

```java
package com.boostmytool.beststore.services;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DatasourceConfig {
    private String url;
    private String username;
    private String password;
}
```

### **`MyService.java`**

```java
package com.boostmytool.beststore.services;

import com.boostmytool.beststore.services.DatasourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private final DatasourceConfig datasourceConfig;

    @Autowired
    public MyService(DatasourceConfig datasourceConfig) {
        this.datasourceConfig = datasourceConfig;
    }

    public void connect() {
        System.out.println("Connecting to " + datasourceConfig.getUrl());
    }
}
```

### **`application.properties`**

```java
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/beststore
spring.datasource.username=root
spring.datasource.password=

spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.thymeleaf.prefix=classpath:/templates/

```

### Result

![image.png](Externalized%20Annotation%2012cf1472fabd807e8184d388e767045a/image.png)

# **`@PropertySource`**

- Is used when you want to load properties from a custom file
    - (other than the default `application.properties` or `application.yml`).
- You typically use this annotation in a configuration class.
- [**`application.properties`**](http://application.properties) نفس فكرة ملف ال
- resources الفايل لازم ننشئه بداخل فولد ال

## Example

### **`ConfigController.java`**

```java
package com.boostmytool.beststore.controllers;

import com.boostmytool.beststore.dto.ProductDTO;
import com.boostmytool.beststore.services.MyService;
import com.boostmytool.beststore.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ConfigController {

    @Autowired
    private final MyService myService;

    public ConfigController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/test")
    public void printResult(){
        myService.connect();
    }

    @GetMapping("/test-custom-file-property")
    public void printCustomResult(){
        myService.customProperty();
    }
}
```

### **`CustomPropertyConfig.java`**

```java
package com.boostmytool.beststore.services;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Value;

@Data
@Configuration
@PropertySource("classpath:custom.properties")
public class CustomPropertyConfig {

    @Value("${custom.property}")
    private String customProperty;
}
```

### **`MyService.java`**

```java
package com.boostmytool.beststore.services;

import com.boostmytool.beststore.services.DatasourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private final DatasourceConfig datasourceConfig;
    private final CustomPropertyConfig customPropertyConfig;

    public MyService(DatasourceConfig datasourceConfig, CustomPropertyConfig customPropertyConfig) {
        this.datasourceConfig = datasourceConfig;
        this.customPropertyConfig = customPropertyConfig;
    }
    public void connect() {
        System.out.println("Connecting to " + datasourceConfig.getUrl());
    }

    public void customProperty() {
        System.out.println("The result of custom properties file: " +customPropertyConfig.getCustomProperty() );
    }
}
```

### [**`custom.properties`**](http://custom.properties) file Content

`custom.property=TestCustomProperty`

### **Result**

![image.png](Externalized%20Annotation%2012cf1472fabd807e8184d388e767045a/image%201.png)