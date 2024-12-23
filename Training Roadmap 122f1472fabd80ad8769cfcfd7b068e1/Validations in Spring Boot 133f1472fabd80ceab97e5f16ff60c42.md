# Validations in Spring Boot

- Validation is like a quality check for data .
    - Like as a teacher marks your answers right or wrong,
    - validation checks if the information people give to your program is correct and follows the rules.

- Spring Boot provides various mechanisms for
    - validation,
    - including annotations,
    - custom validators,
    - error handling
    - and group validation.

# Validation Annotations

- For mark fields with specific validation rules.
- an example of validating a simple registration form for a user:
    
    ```java
    public class UserRegistrationForm {
        @NotBlank(message = "Please provide a username")
        private String username;
        @Email(message = "Please provide a valid email address")
        private String email;
        @Size(min = 8, message = "Password must be at least 8 characters long")
        private String password;
        // Getters and setters
    }
    ```
    
1. **`@NotNull`** → Ensures a field is not null
    1. ما تكون قيمة الفيلد نل
2. **`@NotBlank`** → Enforces non-nullity and requires at least one non-whitespace character
    1.  ( ويكون على الاقل في كاراكتر واحد غير الوايت سبيس null ما تكون قيمة الفيلد  )
3. **`@NotEmpty` →** Gurantess that collections or arrays are not empty 
    1. Arrays & Collectionsبنستخدمها مع ال
        
        ![image.png](Validations%20in%20Spring%20Boot%20133f1472fabd80ceab97e5f16ff60c42/image.png)
        
4. **`@Min(value)` →** Check if a numeric field is greater than or equal to the specified minimum value
5. **`@Max(value)`** → Check if a numeric field is less than or equal to the specific maximum value
6. **`@Size(min, max)`** → Validates if a string or collection size is within a specific range
    1. واقع ضمن رينج معين String or Collections نفحص اذا طول ال
7. **`@Pattern(regex)`** → Verifies if a field matches the provided regular expression
8. **`@Email` →** Ensures a field contains a valid email address format
9. **`@Digits(integer, fraction)`** → Validates that a numeric field has a specified number of integer and fraction digits 
    1. 10.55 مثلا لو كان عندنا الرقم اللي جاي من اليوزر 
        1. **`@Digits(2, 2)`** بكون الفاليديشن المناسب 
10. **`@Past`** and **`@Future` →** Checks if date or time fields is in the past and future respectively
11. **`@AssertTrue`** and **`@AssertFalse` →** Ensures that a boolean field is true. and false respectively
12. **`@CreditCardNumber`** → Validates that a field contains a valid credit card number.
13. **`@Valid`** → Triggers validation of nested objects or properties.
    
    ### **`@Valid`**
    
    - When you apply the **`@Valid`** annotation to a **method parameter**,
        - Spring Boot automatically triggers validation for that parameter before the method is invoked.
    - It is placed before the object to indicate that it should be validated
    - This means that the incoming data for that parameter will be validated against the specified validation rules
        - Validatorعشان نقلله يروح يشيّك على ال @Valid هاذ الحكي معناه انو لازم نحط ال
        entity & dtoاللي حطيناهم بكلاسات ال
        على الفيلدس Validationبصير يعتبر كأنه ما في  @Valid اذا ما حطينا ال
        
        ```java
        @RestController
        @RequestMapping("/user")
        public class ApiController {
        
            @PostMapping("/create")
            public ResponseEntity<String> createUser(@RequestBody @Valid User user, BindingResult bindingResult) {
                if (bindingResult.hasErrors()) {
                    return ResponseEntity.badRequest().body("Validation failed");
                }
                userService.saveUser(user);
                return ResponseEntity.ok("User created successfully");
            }
        }
        ```
        
    - If the data fails validation, Spring Boot will automatically generate validation error messages and associate them with the appropriate fields in the input data.
    - These validation errors are typically captured in a `BindingResult` object, which you can access to analyze and handle validation failures.
14. **`@Validated`** 
    
    ### **`@Validated`**
    
    - It was introduced to facilitate validation groups and to provide a mechanism to apply validation rules to specific.
    - Unlike the standard **`@Valid`** annotation, which validates the entire bean object, **`@Valedated`** allows you to specify which validation groups to apply during the validation process
    - **Validation on Nested Properties**
        - When dealing with complex objects that have nested properties requiring validation, you can use the `@Valid` annotation along with validation annotations to ensure that both the top-level object and its nested properties are properly validated.
    - Example
        
        ```java
        public class Order {
            @NotNull
            private String orderId;
            @Valid
            private ShippingAddress shippingAddress;
            // Other properties, getters, setters...
        }
        ```
        
        ```java
        public class ShippingAddress {
            @NotNull
            private String street;
            @NotNull
            @Size(min = 2, max = 50)
            private String city;
            @NotNull
            private String zipCode;
        }
        ```
        

# **Controller Integration**

- Validation often takes place in the controller, where user input is received.
    - في المكان اللي بنتسقبل Controller Layer بالعادة الفاليديشن بتكون في ال
    فيه الداتا اللي جاية من اليوزر

```java
@Controller
public class UserController {
    @PostMapping("/register")
    public String registerUser(@Valid UserRegistrationForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "registrationForm"; // Return back to the form with error messages
        }
        // If no errors, proceed with user registration
        // ...
        return "redirect:/login";
    }
}
```

# **Validation in Controller**

```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Handle validation errors
            return ResponseEntity.badRequest().body("Validation errors found.");
        }
        // Process user and create a new user
        // ...
        return ResponseEntity.ok("User created successfully.");
    }
}
```

- In this example, the `@Valid` annotation is used in the `createUser` method to validate the `User` object received in the request body.
- The `BindingResult` object is used **to capture any validation errors.**

# **Global Exception Handling**

- Validation errors are inevitable ( حتمي لا مفرّ منه ). Spring Boot provides a way to handle them globally:
    
    ```java
    @ControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
            // Create a map of field errors
            // Return appropriate error response
        }
    }
    ```
    
- The **`@ControllerAdvice`** annotation marks a class as a global exception handler and we handle **`MethodArgumentNotValidException` ,** which is thrown when validation fails.

# Custom Validation

- A custom validation annotation is created by defining a new annotation. This annotation specifies the validation rules that you want to apply to fields or methods in your classes.
    - **`@Target`**: Defines where the annotation can be applied. In the example, it's specified for fields and methods.
    - **`@Retention`**: Specifies how long the annotation should be retained. `RUNTIME` means it will be available at runtime for validation.
    - **`@Constraint`**: Specifies the validator class responsible for implementing the validation logic.