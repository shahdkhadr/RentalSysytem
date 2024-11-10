package com.rental.rental;

import com.rental.rental.controller.PaymentControllerUnitTests;
import com.rental.rental.repository.PaymentRepositoryUnitTests;
import com.rental.rental.service.PaymentServiceUnitTests;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;


@Suite
@SelectPackages({"com.rental.rental.controller", "com.rental.rental.service", "com.rental.rental.repository"})
@SelectClasses({
		PaymentControllerUnitTests.class,
		PaymentRepositoryUnitTests.class,
		PaymentServiceUnitTests.class
})
class RentalApplicationTests {

}
