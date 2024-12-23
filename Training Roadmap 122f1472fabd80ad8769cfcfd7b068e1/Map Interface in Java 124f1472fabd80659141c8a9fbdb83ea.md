# Map Interface in Java

**Ref.:** https://www.geeksforgeeks.org/map-interface-java-examples/

# Map Creation

```java
import java.util.HashMap;
import java.util.Map;

public class MapCreationExample {
    public static void main(String[] args) {
        // Create a Map using HashMap
        Map<String, Integer> map = new HashMap<>();
      
       //here, we can write all other required operations

        // Displaying the Map
        System.out.println("Map elements: " + map);
    }
}
```

- In Java, **Map Interface** is present in **`java.util` package**
    - **represents a mapping between a key and value.**
- ****Java **Map interface** is **not a subtype**  the **Collection interface**
- Therefore it **behaves a bit differently** from the rest of collection types
    - A map **contains unique keys**
- The Map interface allows for efficient data storage in key-value pairs, making it essential for many applications.

# **Why and When to use Maps.**

- Maps are perfect to use for key-value association mapping such as dictionaries.
- The maps are used to perform lookups by keys or when someone wants to retrieve and update elements by keys

## Common Scenarios

- A map of error codes and their descriptions.
- A map of zip codes and cities.
- A map of managers and employees. Each manager (key) is associated with a list of employees (value) he manages.
- A map of classes and students. Each class (key) is associated with a list of students (value).

![image.png](Map%20Interface%20in%20Java%20124f1472fabd80659141c8a9fbdb83ea/image.png)

# Creating Map Objects

- Map is an interface, so we can’t create object of type map.
- We always need a class that extends this map in order to create an object.
- And also, after the introduction of [**Generics**](https://www.geeksforgeeks.org/generics-in-java/) in Java 1.5, it is possible to restrict the type of object that can be stored in the Map.

# Syntax - Defining Type-Safe Map

```
Map hm = new HashMap();
// Obj is the type of the object to be stored in Map
```

# Characteristics of a Map Interface

- A Map can’t duplicate keys and each key can map to at most one value
    - Some implementations **allow null key and null values** like the **HashMap** and **LinkedHashMap**,
    - but some **don’t** like the **TreeMap**
- The order of map depends on the specific implementations, For Example
    - [**TreeMap**](https://www.geeksforgeeks.org/treemap-in-java/) and [**LinkedHashMap**](https://www.geeksforgeeks.org/linkedhashmap-class-java-examples/) have predictable orders,
    - while [**HashMap**](https://www.geeksforgeeks.org/java-util-hashmap-in-java/) does not.
- There are two interfaces for implementing Map in java. They are Map and [**SortedMap**](https://www.geeksforgeeks.org/sortedmap-java-examples/), and three classes: HashMap, TreeMap, and LinkedHashMap.

# Methods in Java Map Interface

Table: https://www.geeksforgeeks.org/map-interface-java-examples/

# Hierarchy of Map Interface in Java

![image.png](Map%20Interface%20in%20Java%20124f1472fabd80659141c8a9fbdb83ea/image%201.png)

# HashMap

- HashMap is a part of Java’s collection since 1.2. It Provides the basic implementation of the Map interface of Java.
- It stores the data in (Key, Value) pairs. To access a value one must know its key.
- This class uses a technique called **Hashing** . Hashing is a technique of converting a large String to a small String that represent the same String,
    - A shorter value helps in indexing and faster searches.

```java
// Java Program to illustrate the Hashmap Class

// Importing required classes
import java.util.*;

// Main class
public class GFG {

    // Main driver method
    public static void main(String[] args)
    {

        // Creating an empty HashMap
        Map<String, Integer> map = new HashMap<>();

        // Inserting entries in the Map
        // using put() method
        map.put("vishal", 10);
        map.put("sachin", 30);
        map.put("vaibhav", 20);

        // Iterating over Map
        for (Map.Entry<String, Integer> e : map.entrySet())

            // Printing key-value pairs
            System.out.println(e.getKey() + " "
                               + e.getValue());
    }
}

```

# LinkedHashMap

- LinkedHashMap is just like HashMap with the **additional feature of maintaining an order of elements inserted into it.**

## HashMap Vs. LinkedHashMap

- HashMap provided the **advantage of quick insertion**, **search**, and **deletion**
    - but it **never** maintained the **track and order** of insertion which the **LinkedHashMap** provides **where the elements can be accessed in their insertion order.**

```java
// Java Program to Illustrate the LinkedHashmap Class

// Importing required classes
import java.util.*;

// Main class
public class GFG {

    // Main driver method
    public static void main(String[] args)
    {

        // Creating an empty LinkedHashMap
        Map<String, Integer> map = new LinkedHashMap<>();

        // Inserting pair entries in above Map
        // using put() method
        map.put("vishal", 10);
        map.put("sachin", 30);
        map.put("vaibhav", 20);

        // Iterating over Map
        for (Map.Entry<String, Integer> e : map.entrySet())

            // Printing key-value pairs
            System.out.println(e.getKey() + " "
                               + e.getValue());
    }
}

```

# TreeMap

- The **TreeMap** is used to **implement the Map Interface** and **NavigableMap** along the Abstract Class.
- The map is sorted according to
    - the natural ordering of its keys,
    - or by a Comparator provided at map creation time, depending on which constructor is used.
- This proves to be an efficient way of sorting and sorting the key-values pairs
    
    ```java
    // Java Program to Illustrate TreeMap Class
    
    // Importing required classes
    import java.util.*;
    
    // Main class
    public class GFG {
    
        // Main driver method
        public static void main(String[] args)
        {
    
            // Creating an empty TreeMap
            Map<String, Integer> map = new TreeMap<>();
    
            // Inserting custom elements in the Map
            // using put() method
            map.put("vishal", 10);
            map.put("sachin", 30);
            map.put("vaibhav", 20);
    
            // Iterating over Map using for each loop
            for (Map.Entry<String, Integer> e : map.entrySet())
    
                // Printing key-value pairs
                System.out.println(e.getKey() + " "
                                   + e.getValue());
        }
    }
    ```
    

# Performing operations using Map Interface and HashMap Class

## **`put()`**

- method for adding element to map

```java
// Java program to demonstrate
// the working of Map interface

import java.util.*;
class GFG {
    public static void main(String args[])
    {
        // Default Initialization of a
        // Map
        Map<Integer, String> hm1 = new HashMap<>();

        // Initialization of a Map
        // using Generics
        Map<Integer, String> hm2
            = new HashMap<Integer, String>();

        // Inserting the Elements
        hm1.put(1, "Geeks");
        hm1.put(2, "For");
        hm1.put(3, "Geeks");

        hm2.put(new Integer(1), "Geeks");
        hm2.put(new Integer(2), "For");
        hm2.put(new Integer(3), "Geeks");

        System.out.println(hm1);
        System.out.println(hm2);
    }
}
```

- If you want to change a specific value, you can use put() again

## **`remove()`**

```java
// Java program to demonstrate
// the working of Map interface

import java.util.*;
class GFG {

    public static void main(String args[])
    {

        // Initialization of a Map
        // using Generics
        Map<Integer, String> hm1
            = new HashMap<Integer, String>();

        // Inserting the Elements
        hm1.put(new Integer(1), "Geeks");
        hm1.put(new Integer(2), "For");
        hm1.put(new Integer(3), "Geeks");
        hm1.put(new Integer(4), "For");

        // Initial Map
        System.out.println(hm1);

        hm1.remove(new Integer(4));

        // Final Map
        System.out.println(hm1);
    }
}
```

## **Iterating through the Map**

- There are multiple ways to iterate through the Map.
- The most famous way is to use a for-each loop and get the keys.
- The value of the key is found by using the getValue() method.

## Counting the Occurrence of number using HashMap

- We are using **`putIfAbsent()`** along with **`Collections.frequency()`** to count the occurrence of numbers.

### Example

```java
// Java program to Count the Occurrence 
// of numbers using Hashmap
import java.util.*;

class HelloWorld {
    public static void main(String[] args)
    {
        int a[] = { 1, 13, 4, 1, 41, 31, 31, 4, 13, 2 };

        // put all elements in arraylist
        ArrayList<Integer> aa = new ArrayList();
        for (int i = 0; i < a.length; i++) {
            aa.add(a[i]);
        }

        HashMap<Integer, Integer> h = new HashMap();

        // counting occurrence of numbers
        for (int i = 0; i < aa.size(); i++) {
            h.putIfAbsent(aa.get(i), Collections.frequency(
                                         aa, aa.get(i)));
        }
        System.out.println(h);
    }
}

```