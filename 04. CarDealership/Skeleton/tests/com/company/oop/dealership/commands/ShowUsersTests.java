package com.company.oop.dealership.commands;

import com.company.oop.dealership.core.VehicleDealershipRepositoryImpl;
import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;
import com.company.oop.dealership.models.contracts.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.company.oop.dealership.commands.LoginTests.loginInitializedUserToRepository;
import static com.company.oop.dealership.models.UserImplTests.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShowUsersTests {

    private ShowUsersCommand showUsersCommand;
    private VehicleDealershipRepository repository;

    @BeforeEach
    public void before() {
        repository = new VehicleDealershipRepositoryImpl();
        showUsersCommand = new ShowUsersCommand(repository);
    }

    @Test
    public void should_ThrowException_When_NoUserIsLoggedIn() {
        // Arrange, Act, Assert
        assertThrows(IllegalArgumentException.class, () -> showUsersCommand.execute(List.of()));
    }

    @Test
    public void should_ThrowException_When_LoggedInUserIsNotAdmin() {
        // Arrange
        loginInitializedUserToRepository(repository);

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> showUsersCommand.execute(List.of()));
    }

    @Test
    public void should_ShowCategory_When_ArgumentsAreValid() {
        // Arrange
        User testAdmin = initializeTestAdmin();
        repository.addUser(testAdmin);
        repository.login(testAdmin);

        // Act, Assert
        assertDoesNotThrow(() -> showUsersCommand.execute(List.of()));
    }
}
