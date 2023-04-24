package com.company.oop.dealership.commands;

import com.company.oop.dealership.core.VehicleDealershipRepositoryImpl;
import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;
import com.company.oop.dealership.models.contracts.Car;
import com.company.oop.dealership.models.contracts.Motorcycle;
import com.company.oop.dealership.models.contracts.Truck;
import com.company.oop.dealership.models.enums.VehicleType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.company.oop.dealership.models.CarImplTests.VALID_SEATS;
import static com.company.oop.dealership.models.MotorcycleImplTests.VALID_CATEGORY;
import static com.company.oop.dealership.models.TruckImplTests.VALID_WEIGHT_CAP;
import static com.company.oop.dealership.utils.VehicleBaseConstants.*;
import static com.company.oop.dealership.utils.TestUtilities.getList;

public class AddVehicleTests {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;

    private VehicleDealershipRepository repository;
    private AddVehicleCommand addVehicleCommand;

    @BeforeEach
    public void before() {
        repository = new VehicleDealershipRepositoryImpl();
        addVehicleCommand = new AddVehicleCommand(repository);
        LoginTests.loginInitializedUserToRepository(repository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> addVehicleCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_VehicleTypeIsInvalid() {
        // Arrange
        List<String> params = List.of(
                "INVALID_TYPE",
                VALID_MAKE,
                VALID_MODEL,
                String.valueOf(VALID_PRICE),
                String.valueOf(VALID_SEATS));

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> addVehicleCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_PriceInputIsNotNumber() {
        // Arrange
        List<String> params = List.of(
                VehicleType.CAR.name(),
                VALID_MAKE,
                VALID_MODEL,
                "INVALID_PRICE",
                String.valueOf(VALID_SEATS));

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> addVehicleCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_CarSeatsInputIsNotNumber() {
        // Arrange
        List<String> params = List.of(
                VehicleType.CAR.name(),
                VALID_MAKE,
                VALID_MODEL,
                String.valueOf(VALID_PRICE),
                "INVALID_SEATS");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> addVehicleCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_TruckWeightInputIsNotNumber() {
        // Arrange
        List<String> params = List.of(
                VehicleType.TRUCK.name(),
                VALID_MAKE,
                VALID_MODEL,
                String.valueOf(VALID_PRICE),
                "INVALID_WEIGHT");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> addVehicleCommand.execute(params));
    }

    @Test
    public void should_CreateCar_When_InputIsValid() {
        // Act
        List<String> params = List.of(
                VehicleType.CAR.name(),
                VALID_MAKE,
                VALID_MODEL,
                String.valueOf(VALID_PRICE),
                String.valueOf(VALID_SEATS));

        // Act
        addVehicleCommand.execute(params);

        //Assert
        Car car = (Car) repository.getLoggedInUser().getVehicles().get(0);
        Assertions.assertEquals(VALID_MAKE, car.getMake());
        Assertions.assertEquals(VALID_SEATS, car.getSeats());
    }

    @Test
    public void should_CreateMotorcycle_When_InputIsValid() {
        // Act
        List<String> params = List.of(
                VehicleType.MOTORCYCLE.name(),
                VALID_MAKE,
                VALID_MODEL,
                String.valueOf(VALID_PRICE),
                VALID_CATEGORY);

        // Act
        addVehicleCommand.execute(params);

        //Assert
        Motorcycle motorcycle = (Motorcycle) repository.getLoggedInUser().getVehicles().get(0);
        Assertions.assertEquals(VALID_MAKE, motorcycle.getMake());
        Assertions.assertEquals(VALID_CATEGORY, motorcycle.getCategory());
    }

    @Test
    public void should_CreateTruck_When_InputIsValid() {
        // Act
        List<String> params = List.of(
                VehicleType.TRUCK.name(),
                VALID_MAKE,
                VALID_MODEL,
                String.valueOf(VALID_PRICE),
                String.valueOf(VALID_WEIGHT_CAP));

        // Act
        addVehicleCommand.execute(params);

        //Assert
        Truck truck = (Truck) repository.getLoggedInUser().getVehicles().get(0);
        Assertions.assertEquals(VALID_MAKE, truck.getMake());
        Assertions.assertEquals(VALID_WEIGHT_CAP, truck.getWeightCapacity());
    }
}
