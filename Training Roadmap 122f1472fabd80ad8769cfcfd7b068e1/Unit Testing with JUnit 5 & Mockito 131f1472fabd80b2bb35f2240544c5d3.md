# Unit Testing with JUnit 5 & Mockito

> We will apply Unit testing for each Layer independently
> 

# Repository Layer - JUnit 5

## [**`EmployeeRepositoryUnitTest.java`**](http://EmployeeRepositoryUnitTest.java) code

```java
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeRepositoryUnitTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @DisplayName("Test 1:Save Employee Test")
    @Order(1)
    @Rollback(value = false)
    public void saveEmployeeTest(){

        //Action
        Employee employee = Employee.builder()
                .firstName("Sam")
                .lastName("Curran")
                .email("sam@gmail.com")
                .build();

        employeeRepository.save(employee);

        //Verify
        System.out.println(employee);
        Assertions.assertThat(employee.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getEmployeeTest(){

        //Action
        Employee employee = employeeRepository.findById(1L).get();
        //Verify
        System.out.println(employee);
        Assertions.assertThat(employee.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    public void getListOfEmployeesTest(){
        //Action
        List<Employee> employees = employeeRepository.findAll();
        //Verify
        System.out.println(employees);
        Assertions.assertThat(employees.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateEmployeeTest(){

        //Action
        Employee employee = employeeRepository.findById(1L).get();
        employee.setEmail("samcurran@gmail.com");
        Employee employeeUpdated =  employeeRepository.save(employee);

        //Verify
        System.out.println(employeeUpdated);
        Assertions.assertThat(employeeUpdated.getEmail()).isEqualTo("samcurran@gmail.com");

    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteEmployeeTest(){
        //Action
        employeeRepository.deleteById(1L);
        Optional<Employee> employeeOptional = employeeRepository.findById(1L);

        //Verify
        Assertions.assertThat(employeeOptional).isEmpty();
    }

}
```

## **`@DataJpaTest`**

## Used to

- **test JPA ( Java Persistance API ) repositories**
- **Configures in-memory database support,**
- **sets up JPA repositories,**
- **and enables transaction rollback after each test.**

## Main characteristics

1. **Configures In-Memory Database**: By default, it configures an in-memory database (e.g., H2) for testing purposes, eliminating the need for an external database.
2. **Limits Context to JPA Components**: Only JPA-related components, like repositories, entities, and transaction management, are loaded into the application context, making the tests lightweight and faster. 
    1. ( بنعمل تيستنج بس للريبوزتري والشغلات الاساسية اللي بتحتاجها مثل الاينتيتي لكن ما بيعمل لود لليرز اللي ما بتلزمه للتيستنج مثل الكونترولر والسيرفيس  )
3. **Rollbacks Transactions**: Transactions are rolled back after each test, ensuring data consistency without needing cleanup between tests. 
    1. هاذ معناه انو كلشي بيرجع لحالته الابتدائية بعد ما تخلص عملية التيستنج
4. **Disables Full Auto-Configuration**: It excludes services, controllers, and other components not needed for repository testing, allowing a more isolated testing environment.
5. **Supports @Transactional Tests**: It provides a transactional environment so that each test runs in a new transaction, which is rolled back at the end of the test.

## **`@TestMethodOrder(MethodOrderer.OrderAnnotation.class)`**

- The `@TestMethodOrder(MethodOrderer.OrderAnnotation.class)` annotation in JUnit 5 is used to specify the order in which test methods within a test class should be executed.
- By using this annotation, you can control the execution sequence of your tests based on the order specified by the `@Order` annotation on each test method.

## **`@Transactional`**

- Without using @Transactional
    - **Shared Database State**: Changes made in one test method (such as adding or updating entities) will persist in the database for subsequent test methods. This can cause tests to interfere with each other.
    - **Manual Cleanup Needed**: Without automatic rollback, any changes in a test method need to be manually cleaned up at the end of each test to avoid impacting other tests. Otherwise, test data can accumulate, leading to unreliable test results.
    - ( يعني كل ما تتنفذ تيست كيس, ما بينعمل كلين للداتا تبعتها, بنفوت ع التيست كيس اللي بعدها بداتا الها دخل بالتيست كيس اللي قبلها )
- If you wanted to avoid `@Transactional`, you'd likely have to manage the database state manually. This could involve:
    - **Explicitly Deleting Data**: Cleaning up any created or modified data at the end of each test.
    - **@BeforeEach and @AfterEach Annotations**: Using these annotations to reset the database state before and after each test.

### Example Code

```java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTestWithoutTransactional {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        // Clear out any leftover data from previous tests
        userRepository.deleteAll();
    }

    @Test
    public void testCreateUser() {
        // Create and save a new user entity
        User user = new User("testUser", "password123");
        userRepository.save(user);

        // Verify the user is saved
        User foundUser = userRepository.findByUsername("testUser");
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo("testUser");
    }

    @Test
    public void testUpdateUser() {
        // Create, save, and update a user entity
        User user = new User("updateUser", "initialPass");
        userRepository.save(user);

        // Update the user's password
        user.setPassword("updatedPass");
        userRepository.save(user);

        // Verify the update
        User foundUser = userRepository.findByUsername("updateUser");
        assertThat(foundUser.getPassword()).isEqualTo("updatedPass");
    }

    @Test
    public void testDeleteUser() {
        // Create and save a new user
        User user = new User("deleteUser", "deletePass");
        userRepository.save(user);

        // Delete the user
        userRepository.delete(user);

        // Verify the user is deleted
        User foundUser = userRepository.findByUsername("deleteUser");
        assertThat(foundUser).isNull();
    }
}

```

### With Transactional

### Code

```java
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest // Optimized for JPA tests, rolls back transactions automatically
@Transactional // Ensures each test runs in a separate transaction
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        // Create and save a new user entity
        User user = new User("testUser", "password123");
        userRepository.save(user);

        // Verify the user is saved
        User foundUser = userRepository.findByUsername("testUser");
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo("testUser");
    }

    @Test
    public void testUpdateUser() {
        // Create, save, and update a user entity
        User user = new User("updateUser", "initialPass");
        userRepository.save(user);

        // Update the user's password
        user.setPassword("updatedPass");
        userRepository.save(user);

        // Verify the update
        User foundUser = userRepository.findByUsername("updateUser");
        assertThat(foundUser.getPassword()).isEqualTo("updatedPass");
    }

    @Test
    public void testDeleteUser() {
        // Create and save a new user
        User user = new User("deleteUser", "deletePass");
        userRepository.save(user);

        // Delete the user
        userRepository.delete(user);

        // Verify the user is deleted
        User foundUser = userRepository.findByUsername("deleteUser");
        assertThat(foundUser).isNull();
    }
}

```

### Explanation:

1. **Isolation**: Each test method starts with a clean state, with no data from previous tests, thanks to `@Transactional`.
2. **No Manual Cleanup Needed**: No need for manual database cleanup, as the `@Transactional` annotation ensures that the database remains unaffected by test data.
3. **Automatic Rollback**: After each test, the transaction rolls back, so the database returns to its original state.

## **`@Test`**

- Is used to mark a method as a test method
- This tells JUnit that the annotated method should be executed as a test case.
- It’s a core annotation for unit testing in Java, allowing developers to write tests to validate individual pieces of code, such as methods and classes.

## Key Features of the `@Test` Annotation

1. **Test Method Identification**: Any method annotated with `@Test` is considered a test case, meaning JUnit will execute this method when running tests.
2. **Automatic Setup and Teardown**: JUnit will automatically set up any necessary state before each test and clean it up afterward (especially useful with `@BeforeEach` and `@AfterEach` annotations).
3. **Assertions for Validation**: Inside the `@Test`-annotated method, you typically use assertions (e.g., `assertEquals`, `assertTrue`) to verify that the expected results match the actual results.

### Code

```java
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    public void testAdd() {
        // Arrange: Prepare any necessary data or state
        Calculator calculator = new Calculator();

        // Act: Perform the action to test
        int result = calculator.add(2, 3);

        // Assert: Verify that the result is what you expect
        assertEquals(5, result, "2 + 3 should equal 5");
    }
}
```

## **`@DisplayName`**

- allows you to specify a custom name for a test method,
- making the test output more readable and descriptive.
- This can be especially helpful in complex test cases or when you want to convey specific information about the test’s purpose.

## Code

```java
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @DisplayName("Test 1: Save Employee Test")
    void whenSaveEmployee_thenEmployeeIsPersisted() {
        // Given
        Employee employee = new Employee("John", "Doe", "john.doe@example.com");

        // When
        Employee savedEmployee = employeeRepository.save(employee);

        // Then
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isNotNull();
        assertThat(savedEmployee.getFirstName()).isEqualTo("John");
        assertThat(savedEmployee.getLastName()).isEqualTo("Doe");
        assertThat(savedEmployee.getEmail()).isEqualTo("john.doe@example.com");
    }
}
```

## **`@Order(1)`**

- Allows you to define the execution order of test methods within a test class.
- This is especially useful when you want certain tests to run in a specific sequence, for instance, setting up initial data or verifying prerequisites in one test before other tests run.
- The `@Order` annotation works with test methods as well as with lifecycle methods (like `@BeforeEach` and `@AfterEach`), and it accepts an integer value, with lower numbers running first.

## Code

```java
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @Order(1)
    @DisplayName("Test 1: Save Employee Test")
    void whenSaveEmployee_thenEmployeeIsPersisted() {
        // Given
        Employee employee = new Employee("John", "Doe", "john.doe@example.com");

        // When
        Employee savedEmployee = employeeRepository.save(employee);

        // Then
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isNotNull();
        assertThat(savedEmployee.getFirstName()).isEqualTo("John");
        assertThat(savedEmployee.getLastName()).isEqualTo("Doe");
        assertThat(savedEmployee.getEmail()).isEqualTo("john.doe@example.com");
    }

    @Test
    @Order(2)
    @DisplayName("Test 2: Find Employee Test")
    void whenFindById_thenReturnEmployee() {
        // Assuming the employee from Test 1 exists
        Employee foundEmployee = employeeRepository.findById(1L).orElse(null);
        
        // Then
        assertThat(foundEmployee).isNotNull();
        assertThat(foundEmployee.getFirstName()).isEqualTo("John");
    }
}
```

## **`@Rollback(false)`** & **`@Rollback(true)`**

- **`@Rollback(true)`**
    - معناها احذف كلشي الو علاقة بالتيست كيس اللي تنفذت
    - يعني اي اشي تسيّف بالداتابيس من نتائج التيست كيس, لما ينتهي تنفيذ التيست كيس بينحذف كلشي الو علاقة بهاي التيست كيس
- **`@Rollback(false)`**
    - `@Rollback(false)` if you need the changes to persist, for instance, to verify data between multiple tests or to inspect the database state after a test runs.
    - بنستخدمها في حالة كانو التيست كيسيز اللي عندنا في منهم بيعتمدو على بعض, او بيلزمهم داتا من بعض , فبنخلي الداتا تتسيّف بالداتابيس

## Common Assertions methods

- **assertEquals(expected, actual)**:
    - Verifies that two values are equal.
    - Example: `assertEquals(5, 2 + 3);`
- **assertNotEquals(expected, actual)**:
    - Verifies that two values are not equal.
    - Example: `assertNotEquals(5, 2 + 2);`
- **assertTrue(condition)**:
    - Asserts that a condition is true.
    - Example: `assertTrue(5 > 3);`
- **assertFalse(condition)**:
    - Asserts that a condition is false.
    - Example: `assertFalse(5 < 3);`
- **assertNull(object)**:
    - Asserts that an object is null.
    - Example: `assertNull(null);`
- **assertNotNull(object)**:
    - Asserts that an object is not null.
    - Example: `assertNotNull(new Object());`
- **assertArrayEquals(expectedArray, actualArray)**:
    - Verifies that two arrays are equal.
    - Example: `assertArrayEquals(new int[]{1, 2, 3}, new int[]{1, 2, 3});`
- **assertIterableEquals(expectedIterable, actualIterable)**:
    - Asserts that two `Iterable` objects are equal.
    - Example: `assertIterableEquals(List.of(1, 2, 3), List.of(1, 2, 3));`
- **assertThrows(expectedType, executable)**:
    - Asserts that a specific exception is thrown by the executable.
    - Example: `assertThrows(IllegalArgumentException.class, () -> { throw new IllegalArgumentException(); });`
- **assertDoesNotThrow(executable)**:
    - Asserts that an executable does not throw any exceptions.
    - Example: `assertDoesNotThrow(() -> { /* code that should not throw */ });`
- **assertTimeout(timeout, executable)**:
    - Asserts that an executable completes within a specified time.
    - Example: `assertTimeout(Duration.ofSeconds(1), () -> { /* some long-running operation */ });`
- **assertAll(executables)**:
    - Asserts that all provided executables complete without throwing exceptions.
    - Example
        
        ```java
        assertAll("group of assertions",
            () -> assertEquals(1, 1),
            () -> assertTrue(true)
        );
        ```
        

# Controller Layer - JUnit 5 & Mockito

## [**`EmployeeControllerUnitTests.java](http://EmployeeControllerUnitTests.java)`** code

```java
package test.example.springboot.test.demo.Controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import test.example.springboot.test.demo.Model.Employee;
import test.example.springboot.test.demo.Service.EmployeeService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    Employee employee;

    @BeforeEach
    public void setup(){

         employee = Employee.builder()
                .id(1L)
                .firstName("John")
                .lastName("Cena")
                .email("john@gmail.com")
                .build();

    }

    //Post Controller
    @Test
    @Order(1)
    public void saveEmployeeTest() throws Exception{
        // precondition
        given(employeeService.saveEmployee(any(Employee.class))).willReturn(employee);

        // action
        ResultActions response = mockMvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));

        // verify
        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName",
                        is(employee.getFirstName())))
                .andExpect(jsonPath("$.lastName",
                        is(employee.getLastName())))
                .andExpect(jsonPath("$.email",
                        is(employee.getEmail())));
    }

    //Get Controller
    @Test
    @Order(2)
    public void getEmployeeTest() throws Exception{
        // precondition
        List<Employee> employeesList = new ArrayList<>();
        employeesList.add(employee);
        employeesList.add(Employee.builder().id(2L).firstName("Sam").lastName("Curran").email("sam@gmail.com").build());
        given(employeeService.getAllEmployees()).willReturn(employeesList);

        // action
        ResultActions response = mockMvc.perform(get("/api/employees"));

        // verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(employeesList.size())));

    }

    //get by Id controller
    @Test
    @Order(3)
    public void getByIdEmployeeTest() throws Exception{
        // precondition
        given(employeeService.getEmployeeById(employee.getId())).willReturn(Optional.of(employee));

        // action
        ResultActions response = mockMvc.perform(get("/api/employees/{id}", employee.getId()));

        // verify
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(employee.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(employee.getLastName())))
                .andExpect(jsonPath("$.email", is(employee.getEmail())));

    }

    //Update employee
    @Test
    @Order(4)
    public void updateEmployeeTest() throws Exception{
        // precondition
        given(employeeService.getEmployeeById(employee.getId())).willReturn(Optional.of(employee));
        employee.setFirstName("Max");
        employee.setEmail("max@gmail.com");
        given(employeeService.updateEmployee(employee,employee.getId())).willReturn(employee);

        // action
        ResultActions response = mockMvc.perform(put("/api/employees/{id}", employee.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));

        // verify
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(employee.getFirstName())))
                .andExpect(jsonPath("$.email", is(employee.getEmail())));
    }

    // delete employee
    @Test
    public void deleteEmployeeTest() throws Exception{
        // precondition
        willDoNothing().given(employeeService).deleteEmployee(employee.getId());

        // action
        ResultActions response = mockMvc.perform(delete("/api/employees/{id}", employee.getId()));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }
}
```

## **What is Mockito?**

- Mockito is a popular **Java Framework** **used for mocking objecs in unit tests**
- Mockito can be used in unit tests to
    - mock dependencies
    - and isolate the code being tested.
- Controllers often depend on service layer components to perform business logic or interact with the application’s data layer (e.g., repositories).
- Mock the service layer components using Mockito to isolate the controller being tested from the actual service layer implementation.
    - ( بنعمل موكينج للسيرفيس لير, عشان نبقا عملنا تيست لكودنا في معزل عن السيرفيس لير الأصلية )

## **What is MockMvc?**

- **MockMvc is not part of Mockito.**
- MockMvc is a **class** provided by the **Spring Test framework specifically for testing Spring MVC controllers.**

## **`@WebMvcTest(EmployeeController.class)`**

- Is used to test only the web layer of an application.
- This means that it sets up the environment needed to test `@Controller`-annotated classes, focusing on the Spring MVC components without starting the full application context.
- It’s often used in conjunction with `@MockBean` to mock dependencies, making it possible to test just the controller's request mapping, validation, and response handling.

## **Key Features of `@WebMvcTest`**

1. **Foucused Testing:** Only loads the beans related to the web layer, like **controllers, filters, and Jackson converters.**  Other layers, like services or repositories, are not loaded unless explicitly mocked.
2. **MockMVC:** Provides an instance of `MockMvc`, a powerful tool for simulating HTTP requests and verifying responses.
3. **Limited Context:** Doesn’t load the entire Spring application context, making tests faster and more isolated.

## **`given().when()then()`**

## Precondition Setup

- **`given(employeeService.saveEmployee(any(Employee.class))).willReturn(employee);`**
    - given() → This is a part of the mockito framework, which is used to create mocks for testing
    - **`employeeService.saveEmployee(any(Employee.class))`**: This tells Mockito to mock the behavior of the `saveEmployee` method of the `employeeService` when it is called with any `Employee` object.
    - **`.willReturn(employee)`**: This specifies that when `saveEmployee` is called with any `Employee`, it should return the `employee` object defined in the test.

## Action Execution

- **`mockMvc.perform(...)`**: This is part of Spring's testing framework and is used to simulate HTTP requests to the application.
- **`post("/api/employees")`**: This indicates that a POST request is being made to the `/api/employees` endpoint.
- **`.contentType(MediaType.APPLICATION_JSON)`**: This sets the content type of the request to `application/json`, indicating that the body of the request will be in JSON format.
- **`.content(objectMapper.writeValueAsString(employee))`**: This converts the `employee` object into a JSON string using `objectMapper` (an instance of `ObjectMapper` from the Jackson library) and sets it as the body of the POST request.
- **`ResultActions response`**: The result of the request is stored in `response` for further assertions.

## Verification

- **`andDo(print())`**: This will print the result of the request to the console for debugging purposes. It’s useful for seeing the request and response details.
- **`andExpect(...)`**: These assertions check the properties of the response.
    - **`status().isCreated()`**: This checks that the response status code is `201 Created`, which indicates that the employee was successfully created.
    - **`jsonPath("$.firstName", is(employee.getFirstName()))`**: This uses JSONPath to check that the `firstName` field in the JSON response matches the `firstName` of the `employee` object.
    - **`jsonPath("$.lastName", is(employee.getLastName()))`**: Similarly checks that the `lastName` field matches.
    - **`jsonPath("$.email", is(employee.getEmail()))`**: Checks that the `email` field matches.

# Service Layer - JUnit 5 & Mockito

## [**`EmployeeServiceUnitTests.java`**](http://EmployeeServiceUnitTests.java) code

```java
package test.example.springboot.test.demo.Service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import test.example.springboot.test.demo.Model.Employee;
import test.example.springboot.test.demo.Repository.EmployeeRepository;
import test.example.springboot.test.demo.Service.Impl.EmployeeServiceImpl;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeServiceUnitTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    public void setup(){

        employee = Employee.builder()
                .id(1L)
                .firstName("John")
                .lastName("Cena")
                .email("john@gmail.com")
                .build();

    }

    @Test
    @Order(1)
    public void saveEmployeeTest(){
        // precondition
        given(employeeRepository.save(employee)).willReturn(employee);

        //action
        Employee savedEmployee = employeeService.saveEmployee(employee);

        // verify the output
        System.out.println(savedEmployee);
        assertThat(savedEmployee).isNotNull();
    }

    @Test
    @Order(2)
    public void getEmployeeById(){
        // precondition
        given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));

        // action
        Employee existingEmployee = employeeService.getEmployeeById(employee.getId()).get();

        // verify
        System.out.println(existingEmployee);
        assertThat(existingEmployee).isNotNull();

    }

    @Test
    @Order(3)
    public void getAllEmployee(){
        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("Sam")
                .lastName("Curran")
                .email("sam@gmail.com")
                .build();

        // precondition
        given(employeeRepository.findAll()).willReturn(List.of(employee,employee1));

        // action
        List<Employee> employeeList = employeeService.getAllEmployees();

        // verify
        System.out.println(employeeList);
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isGreaterThan(1);
    }

    @Test
    @Order(4)
    public void updateEmployee(){

        // precondition
        given(employeeRepository.findById(employee.getId())).willReturn(Optional.of(employee));
        employee.setEmail("max@gmail.com");
        employee.setFirstName("Max");
        given(employeeRepository.save(employee)).willReturn(employee);

        // action
        Employee updatedEmployee = employeeService.updateEmployee(employee,employee.getId());

        // verify
        System.out.println(updatedEmployee);
        assertThat(updatedEmployee.getEmail()).isEqualTo("max@gmail.com");
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Max");
    }

    @Test
    @Order(5)
    public void deleteEmployee(){

        // precondition
        willDoNothing().given(employeeRepository).deleteById(employee.getId());

        // action
        employeeService.deleteEmployee(employee.getId());

        // verify
        verify(employeeRepository, times(1)).deleteById(employee.getId());
    }

}
```

- annotation in JUnit 5 is used **to enable Mockito's** integration with JUnit 5.
- This annotation allows you to use Mockito's features, such as
    - creating mock objects
    - and injecting dependencies, within your test cases.

## `@ExtendWith(MockitoExtension.class)`

- By using `@ExtendWith(MockitoExtension.class)`, you can utilize Mockito's `@Mock` and `@InjectMocks` annotations and avoid manually initializing mocks.

## **`@Mock`**

- **Purpose**: `@Mock` is used to create mock instances of classes or interfaces. These mock objects simulate the behavior of real dependencies without executing actual business logic.
- **Typical Usage**: Applied to dependencies of the class you want to test. By mocking dependencies, you isolate the class under test from its external components, allowing you to focus on testing its logic without interference.
- **Example**: In a service test, you would mock repositories or other services the main service interacts with.
    
    ```java
    @Mock
    private EmployeeRepository employeeRepository;
    ```
    
- In this example, `employeeRepository` is a mock object, so no actual database calls will be made.

## **`@InjectMocks`**

- **Purpose**: `@InjectMocks` is used to create an instance of the class under test and inject the mock dependencies into it. It tells Mockito to take the mocked dependencies (annotated with `@Mock`) and inject them into the target class automatically.
- **Typical Usage**: Applied to the main class you want to test, allowing it to use the mocked dependencies.
- **Example**: In a test for a service, you would annotate the service itself with `@InjectMocks` to have it depend on the mocked repository or other service layers.
    
    ```java
    @InjectMocks
    private EmployeeService employeeService;
    ```
    
- Here, `employeeService` is the class under test, and its dependencies (like `employeeRepository`) are automatically injected as mock objects.