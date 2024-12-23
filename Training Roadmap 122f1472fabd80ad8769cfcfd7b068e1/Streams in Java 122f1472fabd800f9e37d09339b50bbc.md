# Streams in Java

- Stream API is used to process collections of objects.
- A stream in java is a sequence of objects that supports various methods that can be pipelined to produce the desired result.

# Use of Stream in Java

- 1) Stream API is a way to express and process collections of objects.
- 2) Enable us to perform operations like a)filtering b)mapping c)reducing d)sorting

- Stream in Java make data processing more efficient by supporting by functional-style operations.
- Syntax:
    - **`Streams<T> stream;`**

# Streams Features

- 1) The Stream isn't Data Structure instead it takes input from the Collections, Array or I/O channels
- 2) Streams don’t change the original data structure, they only provide the result as per the pipelined methods.
- 3) Each intermediate operation is lazily executed and returns a stream as a result, hence various intermediate operations can be pipelined. Terminal operations mark the end of the stream and return the result.

# Types of Operations in Streams

- 1)  Intermediate Operations
- 2) Terminate Operations

# **Intermediate Operations**

![image.png](Streams%20in%20Java%20122f1472fabd800f9e37d09339b50bbc/image.png)

- Intermediate Operations are the types of operations in which multiple methods are chained in a row.

## **Characteristics of Intermediate Operations**

- Methods are chained together
- Intermediate operations transform a stream into another stream
- It enables the concept of filtering where one method filters data and passes it to another method after processing

## **Benefit of Java Stream**

- No Storage
- Pipeline of Functions
- Laziness
- Can be infinite
- Can be parallelized
- Can be created from collections, arrays, Files Lines, Methods in Stream, IntStream etc.

## Important Intermediate Operations

- There are a few Intermediate Operations mentioned below

### 1. map()

- The map method is used to return a stream consisting of the results of **applying the given function to the elements of this stream**.
- Syntax
    
    **`<R> Stream<R> map(Function<? super T, ? extends R> mapper)`**
    

### Example

```jsx
List<String> result = listOfLists.stream()
                .flatMap(List::stream)               // Flatten the list of lists into a single stream
                .filter(s -> s.startsWith("S"))      // Filter elements starting with "S"
                .map(String::toUpperCase)// Transform each element to uppercase ( Apply toUpperCase() function for all elements in the strean )
                .toList();       // Collect the final result into a list
```

### 2. filter()

- The filter method is used to select elements as per the Predicate passed as an argument.
- Syntax
    
    **`Stream<T> filter(Predicate<? super T> predicate)`**
    

### Example

```jsx
// Stream pipeline demonstrating various intermediate operations
        List<String> result = listOfLists.stream()
                .flatMap(List::stream)               // Flatten the list of lists into a single stream
                .filter(s -> s.startsWith("S"))      // Filter elements starting with "S"
                .toList();       // Collect the final result into a list
```

### 3. sorted()

- The sorted method is used to sort the stream.
- **Syntax**
    
    ```
    Stream<T> sorted()
    Stream<T> sorted(Comparator<? super T> comparator)
    ```
    

### Example

```jsx
List<String> result = listOfLists.stream()
                .flatMap(List::stream)               // Flatten the list of lists into a single stream
                .filter(s -> s.startsWith("S"))      // Filter elements starting with "S"
                .map(String::toUpperCase)// Transform each element to uppercase ( Apply toUpperCase() function for all elements in the strean )
                .distinct() // Remove duplicate elements
                .sorted() // Sort elements
                .toList();       // Collect the final result into a list
```

### **4. flatMap()**

- The flatMap operation in Java Streams is used to flatten a stream of collections into a single stream of elements.
- Syntax
    
    **`<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)`**
    

### Usage

- This is particularly useful when dealing with nested lists or collections, **where you want to process each sub-collection individually but ultimately produce a single flattened stream ( Convert nested list to Single Flatten List )**

### Example

```jsx
List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("Reflection", "Collection", "Stream"),
                Arrays.asList("Structure", "State", "Flow"),
                Arrays.asList("Sorting", "Mapping", "Reduction", "Stream")
        );
System.out.println(listOfLists); // [[Reflection, Collection, Stream], [Structure, State, Flow], [Sorting, Mapping, Reduction, Stream]]
List<String> result = listOfLists.stream().flatMap(List::stream).toList();

System.out.println(result); // [Reflection, Collection, Stream, Structure, State, Flow, Sorting, Mapping, Reduction, Stream]
```

### Some Explanation

- The `flatMap` method is designed to handle this kind of scenario, where each element in a stream is itself a collection or streamable.
- `List::stream` is used to convert each nested list (inner lists) into a stream, so that `flatMap` can then combine these into a single stream.

### **5. distinct ()**

- Removes duplicate elements. It returns a stream consisting of the distinct elements (according to Object.equals(Object)).
- **Syntax**
    
    ```
    Stream<T> distinct()
    ```
    

### Example

```jsx
List<String> result = listOfLists.stream()
                .flatMap(List::stream)               // Flatten the list of lists into a single stream
                .filter(s -> s.startsWith("S"))      // Filter elements starting with "S"
                .map(String::toUpperCase)// Transform each element to uppercase ( Apply toUpperCase() function for all elements in the strean )
                .distinct() // Remove duplicate elements
                .toList();       // Collect the final result into a list
```

### **6. peek()**

- **Performs an action on each element without modifying the stream**. It returns a stream consisting of the elements of this stream, additionally performing the provided action on each element as elements are consumed from the resulting stream.
- **Syntax**
    
    ```
    Stream<T> peek(Consumer<? super T> action)
    ```
    

### Example

```jsx
List<String> result = listOfLists.stream()
                .flatMap(List::stream)               // Flatten the list of lists into a single stream
                .filter(s -> s.startsWith("S"))      // Filter elements starting with "S"
                .map(String::toUpperCase)// Transform each element to uppercase ( Apply toUpperCase() function for all elements in the stream )
                .distinct() // Remove duplicate elements
                .sorted() // Sort elements
                .peek(element -> intermediateResults.add(element)) // Perform an action (add to set) on each element
                .toList();       // Collect the final result into a list
```

# Terminal Operations

- Terminal Operations are the type of Operations that return the result.
- These Operations are not processed further **just return a final result value.**

## Important Terminal Operations

### **1. collect()**

- The collect method is used to return the result of the intermediate operations performed on the stream.
- Syntax
`<R, A> R collect(Collector<? super T, A, R> collector)`

### **2. forEach()**

- The forEach method is used **to iterate through every element** of the stream.
- Syntax
    
    ```
    void forEach(Consumer<? super T> action)
    ```
    

### **3. reduce()**

- The reduce method is used **to reduce the elements of a stream to a single value.** The reduce method takes a BinaryOperator as a parameter.
- Syntax
    
    ```
    T reduce(T identity, BinaryOperator<T> accumulator)
    Optional<T> reduce(BinaryOperator<T> accumulator)
    ```
    

### **4. count()**

- Returns the **count of elements in the stream**.
- Syntax
    
    ```jsx
    long count()
    ```
    

### **5. findFirst()**

- Returns the first element of the stream, if present.
- Syntax
    
    ```
    Optional<T> findFirst()
    ```
    

### **6. allMatch()**

- Checks if **all elements of the stream match a given predicate.**
- Syntax
`boolean allMatch(Predicate<? super T> predicate)`

### **7. anyMatch()**

- Checks if **any element of the stream matches a given predicate.**
- Syntax
    
    `boolean anyMatch(Predicate<? super T> predicate)`
    

## Example

```jsx
package org.example.others.streams;

import java.util.*;
import java.util.stream.Collectors;

public class Streams {
    public static void main(String[] args) {
        // Sample data
        List<String> names = Arrays.asList(
                "Reflection", "Collection", "Stream",
                "Structure", "Sorting", "State"
        );

        // forEach: Print each name
        System.out.println("forEach:");
        names.stream().forEach(System.out::println);

        // collect: Collect names starting with 'S' into a list
        List<String> sNames = names.stream()
                .filter(name -> name.startsWith("S"))
                .collect(Collectors.toList());
        System.out.println("\ncollect (names starting with 'S'):");
        sNames.forEach(System.out::println); //  println بدي أطبق الميثود اللي اسمها
                                            // الموجودة في ال System.out

        // reduce: Concatenate all names into a single string
        String concatenatedNames = names.stream().reduce(
                "",
                (partialString, element) -> partialString + " " + element
        );
        System.out.println("\nreduce (concatenated names):");
        System.out.println(concatenatedNames.trim());

        // count: Count the number of names
        long count = names.stream().count();
        System.out.println("\ncount:");
        System.out.println(count);

        // findFirst: Find the first name
        Optional<String> firstName = names.stream().findFirst();
        System.out.println("\nfindFirst:");
        firstName.ifPresent(System.out::println);

        // allMatch: Check if all names start with 'S'
        boolean allStartWithS = names.stream().allMatch(
                name -> name.startsWith("S")
        );
        System.out.println("\nallMatch (all start with 'S'):");
        System.out.println(allStartWithS);

        // anyMatch: Check if any name starts with 'S'
        boolean anyStartWithS = names.stream().anyMatch(
                name -> name.startsWith("S")
        );
        System.out.println("\nanyMatch (any start with 'S'):");
        System.out.println(anyStartWithS);
    }
}
```

> ***Note: Intermediate Operations are running based on the concept of Lazy Evaluation, which ensures that every method returns a fixed value(Terminal operation) before moving to the next method.***
> 

# Important Points/Observations of Java Stream

1. A stream consists of a source followed by zero or more intermediate methods combined together (pipelined) and a terminal method to process the objects obtained from the source as per the methods described.
2. Stream is used to compute elements as per the pipelined methods without altering the original value of the object.

# Exercise

### Stream API

The Stream API is one of the major features introduced in Java 8. It allows for functional-style operations on collections of data.

- **Stream**: A sequence of elements that supports various methods to perform computations. It does **not** store data but focuses on data processing.
- **Pipelines**: Streams are usually processed through a series of steps called a pipeline, which includes:
    - **Source**: The data source (e.g., List, Array).
    - **Intermediate Operations**: These transform the stream (e.g., `filter`, `map`).
    - **Terminal Operation**: Ends the stream pipeline (e.g., `forEach`, `collect`).

### Lambda Expressions

Lambda expressions are a concise way to represent anonymous functions (i.e., functions without a name) in Java. They allow us to pass behavior as a parameter to methods, especially in the context of Java Streams.

**Syntax**: `(parameters) -> expression`

```java

numbers.stream().filter(n -> n > 5).forEach(System.out::println);
```

- **n -> n > 5**: This is a lambda expression where `n` is the parameter, and `n > 5` is the condition for filtering.
- **Purpose**: Used to simplify code and make it more readable, especially when working with functional-style programming.

### **`(a, b) -> b - a`**:

- The expression `(a, b) -> b - a` is a **lambda expression** that provides the custom comparator to the `sorted()` method.
- A **comparator** in Java is a function that defines the sorting logic between two elements, `a` and `b`.

### **How the Lambda Expression Works**:

- The lambda expression `(a, b) -> b - a` compares two integers `a` and `b`.
- The result of `b - a` determines their relative order:
    - If `b - a` is **positive**, it means `b` is greater than `a`, so `b` will come before `a` in the sorted order (this creates **descending** order).
    - If `b - a` is **negative**, it means `a` is greater than `b`, so `a` will come before `b` in the sorted order.
    - If `b - a` is **zero**, it means `a` and `b` are equal, so their relative order does not change.

### filter

The `filter()` method is an intermediate operation that selects elements from the stream based on a given condition.

Example:

```java

strings.stream().filter(s -> s.startsWith("J")).collect(Collectors.toList());
```

- **Lambda**: `s -> s.startsWith("J")`: Filters strings that start with "J".
- **Purpose**: Used to filter data based on a condition.

### map

The `map()` method is used to transform elements in the stream. It applies a function to each element and returns a stream with the transformed elements.

Example:

```java

numbers.stream().map(n -> n * n).forEach(System.out::println);
```

- **Lambda**: `n -> n * n`: Maps each integer to its square.
- **Purpose**: Used to modify or transform elements in a stream.

### sorted

The `sorted()` method is used to sort the elements in the stream. It can be used with natural ordering or custom comparators.

Example:

```java

numbers.stream().sorted((a, b) -> b - a).forEach(System.out::println);
```

- **Lambda**: `(a, b) -> b - a`: Sorts integers in descending order.
- **Purpose**: Sorting the stream elements.

### reduce

The `reduce()` method is a terminal operation that combines all the elements of the stream into a single result using a given function. It's commonly used for summing, finding minimum/maximum, etc.

Example:

```java
List<Integer> numbers = Arrays.asList(2, 4, 6);
int sum = numbers.stream().reduce(0, (a, b) -> a + b);
```

- **Lambda**: `(a, b) -> a + b`: Adds two numbers together to get a cumulative sum.
- **Purpose**: Aggregate all elements into a single result.
    
    **`0` is the identity**
    
    : This is the starting value for the reduction.
    
    - **Why `0`?** Because when you're summing numbers, adding `0` doesn't change the result. For example, `0 + 2 = 2`, so it acts as a neutral or "identity" value.
    1. Start with the **identity** value: `0`.
    2. Take the first number in the list, which is `2`, and add it to the identity:
        - `0 + 2 = 2`
    3. Now, take the next number, `4`, and add it to the result of the previous step:
        - `2 + 4 = 6`
    4. Finally, take the next number, `6`, and add it to the result from the previous step:
        - `6 + 6 = 12`
    
    So, the final result is `12`.
    

### collect

The `collect()` method is a terminal operation that transforms the elements of the stream into a different type, such as a `List`, `Set`, or `Map`.

Example:

```java

Set<Integer> set = numbers.stream().collect(Collectors.toSet());
```

- **Purpose**: Used to collect the elements of the stream into a collection (e.g., List, Set).