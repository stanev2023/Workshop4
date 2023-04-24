package com.company.oop.dealership.commands;

import com.company.oop.dealership.core.VehicleDealershipRepositoryImpl;
import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;
import com.company.oop.dealership.models.contracts.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.company.oop.dealership.models.UserImplTests.initializeTestUser;

public class LogoutTests {
    private VehicleDealershipRepository repository;
    private LogoutCommand logoutCommand;

    @BeforeEach
    public void before() {
        repository = new VehicleDealershipRepositoryImpl();
        logoutCommand = new LogoutCommand(repository);
    }

    @Test
    public void should_Throw_When_UserNotLoggedIn() {

        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> logoutCommand.execute(new ArrayList<>()));
    }

    @Test
    public void should_LogoutUser() {
        // Arrange
        User userToLogIn = initializeTestUser();
        repository.login(userToLogIn);

        // Act
        logoutCommand.execute(new ArrayList<>());

        // Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> repository.getLoggedInUser());
    }
}
