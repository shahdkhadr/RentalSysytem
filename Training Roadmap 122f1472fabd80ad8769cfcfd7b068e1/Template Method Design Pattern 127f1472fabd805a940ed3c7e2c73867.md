# Template Method Design Pattern

- Template Method → Behavioral Design Pattern
- Template Method define the skeleton( الهيكل العظمي ) of an algorithm is a superclass but allows subclasses to override specific steps of the algorithm without changing the structure.

> ببساطة بنستخدمها للأشياء اللي بتكون بتوخذ نفس الخطوات بالتنفيذ, لكن بكون في اختلاف في الامبليمنتيشن لبعض الخطوات
> 
- It **promotes code reuse** by encapsulating the common algorithmic structure in the super class while allowing subclasses to provide concrete implementations for certain steps
    - Superclassلأنه بنحط كل الكود المشترك بينهم في ال Reusable بيطلع الكود معنا
        - يحط الامبليمنتيشن اللي بدو اياه للخطوات subclass وبنخلي كل
        - thus enabling **customization** and flexibility.

# What's the Template Method Design Pattern ?

- Defines the skeleton of an algorithm or operations in a **super class( Often Abstract )**
    - and leaves the details to be implemented by child classes.
- It allows subclasses to customize specific parts of the algorithm without altering its overall structure.
    - The overall structure and sequence of the algorithm are preserved by the parent class.
        - ( Parent Classالستركتشر الأساسي وخطوات تنفيذ الألجوريثيم بكونو موجودين عند ال )
            - وما حدا بيقدر يغيّر ع البنية تبعتهم
    - Template means Preset format like HTML templates which has a fixed preset format. Similarly in the template method pattern, we have a preset structure method called template method which consists of steps.
    - These **steps** can be an abstract method that will be implemented by its subclasses.
- This is one of the easiest to understand and implement. This design pattern is used popularly in framework development and helps to avoid code duplication. **( Avoid Code Duplication )**

# Components of Template Method Design Pattern

![image.png](Template%20Method%20Design%20Pattern%20127f1472fabd805a940ed3c7e2c73867/image.png)

## 1. Abstract Class or Interface

- This is the superclass that defines the template method. It provides a skeleton for the algorithm, where certain steps are defined but others are left abstract or defined as hooks that subclasses can override. It may also include concrete methods that are common to all subclasses and are used within the template method.

## 2. Template Method

- This is the method within the abstract class that defines the overall algorithm structure by calling various steps in a specific order.
- It’s **often declared as final** to
    - prevent subclasses from changing the algorithm’s structure.
- The template method usually consists of a series of method calls (either abstract or concrete) that make up the algorithm’s steps.

## 3. Abstract ( or Hook ) Methods

- These are methods declared within the abstract class but not implemented. They serve as placeholders for steps in the algorithm that should be implemented by subclasses.
- Subclasses must provide concrete implementations for these methods to complete the algorithm.

## 4. Concrete Class

- These are the subclasses that extend the abstract class and provide concrete implementations for the abstract methods defined in the superclass.
- Each subclass can override certain steps of the algorithm to customize the behavior without changing the overall structure.

# Template Method Design Pattern example

> *Let’s consider a scenario where we have a process for making different types of beverages ( مشروبات ), such as tea and coffee. While the overall process of making beverages is similar (e.g., boiling water, adding ingredients), the specific steps and ingredients vary for each type of beverage.*
> 

## **Benefit of Using Template Method Pattern:**

- Using the Template Method pattern in this scenario allows us to define a common structure for making beverages in a superclass while allowing subclasses to customize specific steps, such as adding ingredients, without changing the overall process.
- This promotes code reuse, reduces duplication, and provides a flexible way to accommodate variations in beverage preparation.

# **When to use the Template Method Design Pattern?**

- Common Algorithm with variations
- Code Reusability
- Enforcing Structure
- Reducing Duplication

# **When not to use the Template Method Design Pattern?**

- **When Algorithms are Highly Variable**
- **Tight Coupling Between Steps**: If there’s tight coupling between the steps of the algorithm, such that changes in one step necessitate changes in other steps, the Template Method pattern may not provide sufficient flexibility.
- **Inflexibility with Runtime Changes**: If you anticipate frequent changes in the algorithm structure or steps at runtime, using the Template Method pattern might not be the best choice, as it relies on predefined structure and behavior.
- **Overhead in Abstraction**: If the cost of abstraction and inheritance outweighs the benefits of code reuse and structure enforcement, **it’s better to avoid using the Template Method pattern and opt for simpler solutions.**