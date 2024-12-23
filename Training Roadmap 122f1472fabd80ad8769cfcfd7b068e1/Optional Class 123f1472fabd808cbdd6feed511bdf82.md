# Optional Class

- is a **container object** which may or may not contain a non-null value
- It was introduced in Java 8 to
    - help avoid `NullPointerException`
    - and to **make the code more readable** by explicitly handling the absence of a value.

# Key Concepts

- **Purpose**: `Optional` is used to
    - represent a value that may or may not be present,
    - making code more expressive **( جعل الكود أكثر تعبيراً )**
    - and reducing the need for null checks.
- **Generic**: `Optional` is a generic class (`Optional<T>`), meaning it can wrap any type of object (`T`).

# Common Methods

- Here are some common methods provided by the `Optional` class
    
    ## 1. Creating an Optional
    
    - `Optional.of(T value)`: Creates an `Optional` with the specified non-null value.
    - `Optional.ofNullable(T value)`: Creates an `Optional` that may contain a null value.
    - `Optional.empty()`: Creates an empty `Optional`.
    
    ```jsx
    Optional<String> optional1 = Optional.of("Hello"); // Throws NullPointerException if null
    Optional<String> optional2 = Optional.ofNullable(null); // Returns an empty Optional
    Optional<String> optional3 = Optional.empty(); // Explicitly creates an empty Optional
    ```
    
    ## 2. Checking Presence
    
    - `isPresent()`: Returns `true` if a value is present, otherwise `false`.
    - `isEmpty()`: Returns `true` if no value is present (introduced in Java 11).
    
    ```jsx
    if (optional1.isPresent()) {
    	System.out.println(optional1.get());
    }
    ```
    
    ## 3. Retrieving the Value
    
    - `get()`: Returns the value if present, otherwise throws `NoSuchElementException`.
    - `orElse(T other)`: Returns the value if present, otherwise returns `other`.
    - `orElseGet(Supplier<? extends T> supplier)`: Returns the value if present, otherwise calls the supplier to provide a value.
    - `orElseThrow(Supplier<? extends X> exceptionSupplier)`: Returns the value if present, otherwise throws an exception created by the supplied exception.
    
    ```jsx
    String value1 = optional1.orElse("Default Value");
    String value2 = optional2.orElseGet(() -> "Computed Default");
    ```
    
    ## **4. Transforming Values**
    
    - `map(Function<? super T, ? extends U> mapper)`: Applies a function to the value if it is present, and returns an `Optional` of the result.
    - `flatMap(Function<? super T, Optional<U>> mapper)`: Similar to `map`, but the function itself returns an `Optional`.
    
    ```jsx
    Optional<String> upper = optional1.map(String::toUpperCase); // Converts value to upper case
    ```
    
    ## **5. Performing an Action**
    
    - `ifPresent(Consumer<? super T> action)`: If a value is present, it performs the specified action.
    - `ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction)`: If a value is present, performs the given action with the value, otherwise performs the given empty-based action (introduced in Java 9).
    
    ```jsx
    optional1.ifPresent(System.out::println); // Prints the value if present
    ```
    

# When to Use `Optional`

- **Return Types**: Using `Optional` as a return type is helpful when the method might return an absent value.
- **Avoiding Null**: It is not recommended to use `Optional` for fields in data classes or as method parameters. For such scenarios, null checks or non-null assertions are more appropriate.

# Example Over All

```java
import java.util.Optional;

public class OptionalExample {
    public static void main(String[] args) {
        // Creating Optionals
        Optional<String> optional1 = Optional.of("Hello, World!"); // Non-null value
        Optional<String> optional2 = Optional.ofNullable(null);    // Nullable value, will be empty
        Optional<String> optional3 = Optional.empty();            // Explicitly empty Optional

        // Checking presence using isPresent() and isEmpty()
        System.out.println("optional1 is present: " + optional1.isPresent()); // true
        System.out.println("optional2 is empty: " + optional2.isEmpty());     // true

        // Retrieving values
        // Using get() - be cautious, will throw exception if empty
        if (optional1.isPresent()) {
            System.out.println("Value from optional1: " + optional1.get());
        }

        // Using orElse() to provide a default value
        String value1 = optional1.orElse("Default Value");
        String value2 = optional2.orElse("Default Value");
        System.out.println("Value from optional1 (using orElse): " + value1); // Prints actual value
        System.out.println("Value from optional2 (using orElse): " + value2); // Prints "Default Value"

        // Using orElseGet() with a supplier
        String value3 = optional2.orElseGet(() -> "Computed Default");
        System.out.println("Value from optional2 (using orElseGet): " + value3);

        // Using orElseThrow() to throw an exception if no value is present
        try {
            String value4 = optional2.orElseThrow(() -> new IllegalArgumentException("No value present"));
            System.out.println(value4);
        } catch (Exception e) {
            System.out.println("Exception from optional2: " + e.getMessage());
        }

        // Transforming values with map()
        Optional<String> upperCaseOptional = optional1.map(String::toUpperCase);
        System.out.println("Uppercase value from optional1: " + upperCaseOptional.orElse("No Value"));

        // Transforming values with flatMap() - used when the mapping function returns an Optional
        Optional<Integer> lengthOptional = optional1.flatMap(OptionalExample::getLengthIfValid);
        System.out.println("Length of value in optional1 (using flatMap): " + lengthOptional.orElse(0));

        // Performing an action if a value is present using ifPresent()
        optional1.ifPresent(value -> System.out.println("ifPresent: Hello, " + value));

        // Using ifPresentOrElse() (Java 9+)
        optional2.ifPresentOrElse(
            value -> System.out.println("Value is present: " + value),
            () -> System.out.println("Value is absent")
        );

        // A method that returns Optional to avoid nulls
        Optional<String> name = Optional.ofNullable(getName());
        String defaultName = name.orElse("Unknown");
        System.out.println("Name: " + defaultName);

        // Using map() to transform and chain operations
        Optional<Integer> nameLength = name.map(String::length);
        System.out.println("Name length: " + nameLength.orElse(0));

        // Comprehensive example using a method that returns Optional
        Optional<String> email = findEmailById(1);
        email.ifPresentOrElse(
            value -> System.out.println("Email found: " + value),
            () -> System.out.println("No email found for the given ID")
        );
    }

    // Helper method: returns length of the string only if it is valid
    public static Optional<Integer> getLengthIfValid(String input) {
        return (input != null && input.length() > 5) ? Optional.of(input.length()) : Optional.empty();
    }

    // Simulated method that may return null (e.g., from a database)
    public static String getName() {
        // Imagine this value comes from a database or external source
        return null; // Could be a non-null value in real scenarios
    }

    // Simulated method for retrieving an email by ID using Optional
    public static Optional<String> findEmailById(int id) {
        if (id == 1) {
            return Optional.of("user@example.com");
        } else {
            return Optional.empty(); // No email found for other IDs
        }
    }
}
```