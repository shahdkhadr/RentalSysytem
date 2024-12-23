# @Profile Annotation

**Ref.:** [What is @Profile annotation in java & implementation with different environment(Dev , Test & prod… | by Dev_RV | Medium](https://medium.com/@dev_RV/what-is-profile-annotation-in-java-implementation-with-different-environment-dev-test-prod-80f0f47bd8ed)

> **`*@Profile`** helps spring to identify the beans that belong to a particular environment*
> 

<aside>
💡

Development Environment, Production Environment and Test Environment

</aside>

1. Any class which is annotated with **stereotype annotations** such as @Component, @Service, @Repository, @Configuration, @Controller, @RestController, @ComponentScan and @PropertSource
2. @Profile is applied at **class level** except for the classes annotated with @Configuration 
    1. ( ما عدا الحالات اللي بنكون مستخدمين فيها ال class levelتُستخدم بال )
        1. @Configuration

# @Profile-Method Level

![image.png](@Profile%20Annotation%2012cf1472fabd802dbb77f92299596255/image.png)

# @Profile-Class Level

![image.png](@Profile%20Annotation%2012cf1472fabd802dbb77f92299596255/image%201.png)

> Spring Boot relies on application.properties file for configuration. To write configuration based on environment, you need to create different application.properties files. The file name has a naming convention as application-<user created profile name>.properties
> 

الخاص فيها Configuration بنكتب فيه ال Environment بنعمل فايل خاص لكل وحدة من ال

# Examples

## **`application-dev.properties`**

![image.png](@Profile%20Annotation%2012cf1472fabd802dbb77f92299596255/image%202.png)

## **`application-prod.properties`**

![image.png](@Profile%20Annotation%2012cf1472fabd802dbb77f92299596255/image%203.png)

## **`application-test.properties`**

![image.png](@Profile%20Annotation%2012cf1472fabd802dbb77f92299596255/image%204.png)

# Step 1: Configuration - pom.xml

```java
<profiles>
		<profile>
			<id>dev</id>
			<properties>
				<spring.profiles.active>dev</spring.profiles.active>
			</properties>
		</profile>
		<profile>
			<id>test</id>
			<activation>
				<activeByDefault>true</activeByDefault>
			</activation>
			<properties>
				<spring.profiles.active>test</spring.profiles.active>
			</properties>
		</profile>
	</profiles>

	<build>
		<resources>
			<resource>
				<directory>src/main/resources</directory>
				<filtering>true</filtering>
			</resource>
		</resources>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

```

# Step 2: [application.properties](http://application.properties) file

**`spring.profiles.active=@spring.profiles.active@`**

# Step 3: Build the project as guided below,

i. Run As -> Maven build

ii. Goals as clean package

iii. Profiles as dev

iv. Check Skip Tests