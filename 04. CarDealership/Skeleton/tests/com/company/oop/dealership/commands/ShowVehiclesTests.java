package com.company.oop.dealership.commands;

import com.company.oop.dealership.core.VehicleDealershipRepositoryImpl;
import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;
import com.company.oop.dealership.models.contracts.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.company.oop.dealership.commands.LoginTests.loginInitializedUserToRepository;
import static com.company.oop.dealership.utils.TestUtilities.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShowVehiclesTests {
    private ShowVehiclesCommand showVehiclesCommand;
    private VehicleDealershipRepository repository;

    @BeforeEach
    public void before() {
        repository = new VehicleDealershipRepositoryImpl();
        showVehiclesCommand = new ShowVehiclesCommand(repository);
    }

    @Test
    public void should_ThrowException_When_NoUserIsLoggedIn() {
        // Arrange, Act, Assert
        assertThrows(IllegalArgumentException.class, () -> showVehiclesCommand.execute(List.of()));
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = getList(ShowVehiclesCommand.EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> showVehiclesCommand.execute(params));
    }

    @Test
    public void should_ShowCategory_When_ArgumentsAreValid() {
        // Arrange
        User user = loginInitializedUserToRepository(repository);
        List<String> params = List.of(user.getUsername());

        // Act, Assert
        Assertions.assertDoesNotThrow(() -> showVehiclesCommand.execute(params));
    }
}
