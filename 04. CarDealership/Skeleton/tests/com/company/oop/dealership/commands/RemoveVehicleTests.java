package com.company.oop.dealership.commands;

import com.company.oop.dealership.core.VehicleDealershipRepositoryImpl;
import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;
import com.company.oop.dealership.models.contracts.Car;
import com.company.oop.dealership.models.contracts.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.company.oop.dealership.commands.LoginTests.loginInitializedUserToRepository;
import static com.company.oop.dealership.models.CarImplTests.initializeTestCar;
import static com.company.oop.dealership.utils.TestUtilities.getList;

public class RemoveVehicleTests {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private RemoveVehicleCommand removeVehicleCommand;
    private User user;

    @BeforeEach
    public void before() {
        VehicleDealershipRepository repository = new VehicleDealershipRepositoryImpl();
        removeVehicleCommand = new RemoveVehicleCommand(repository);
        user = loginInitializedUserToRepository(repository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> removeVehicleCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_VehicleIndexIsInvalid() {
        // Arrange
        List<String> params = List.of(
                "INVALID_INDEX");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> removeVehicleCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_VehicleDoesNotExist() {
        // Arrange
        List<String> params = List.of(
                "1");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> removeVehicleCommand.execute(params));
    }

    @Test
    public void should_RemoveCommentFromUser_When_ArgumentsAreValid() {
        // Arrange
        Car testCar = initializeTestCar();
        user.addVehicle(testCar);

        // Act
        removeVehicleCommand.executeCommand(List.of(
                "1"));

        // Act, Assert
        Assertions.assertEquals(0, user.getVehicles().size());
    }
}
