package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Motorcycle;
import com.company.oop.dealership.models.contracts.Vehicle;
import com.company.oop.dealership.utils.TestUtilities;
import com.company.oop.dealership.utils.VehicleBaseConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MotorcycleImplTests {

    public static final int CATEGORY_LEN_MIN = 3;
    public static final String VALID_CATEGORY = TestUtilities.getString(CATEGORY_LEN_MIN + 1);
    public static final String INVALID_CATEGORY = TestUtilities.getString(CATEGORY_LEN_MIN - 1);

    @Test
    public void motorcycleImpl_Should_ImplementMotorcycleInterface() {
        // Arrange, Act
        MotorcycleImpl motorcycle = initializeTestMotorcycle();
        // Assert
        Assertions.assertTrue(motorcycle instanceof Motorcycle);
    }

    @Test
    public void motorcycleImpl_Should_ImplementVehicleInterface() {
        // Arrange, Act
        MotorcycleImpl motorcycle = initializeTestMotorcycle();
        // Assert
        Assertions.assertTrue(motorcycle instanceof Vehicle);
    }

    @Test
    public void constructor_Should_ThrowException_When_MakeNameLengthOutOfBounds() {
        // Arrange, Act, Assert
        assertThrows(IllegalArgumentException.class, () ->
                new MotorcycleImpl(
                        VehicleBaseConstants.INVALID_MAKE,
                        VehicleBaseConstants.VALID_MODEL,
                        VehicleBaseConstants.VALID_PRICE,
                        VALID_CATEGORY));
    }

    @Test
    public void constructor_Should_ThrowException_When_ModelNameLengthOutOfBounds() {
        // Arrange, Act, Assert
        assertThrows(IllegalArgumentException.class, () ->
                new MotorcycleImpl(
                        VehicleBaseConstants.VALID_MAKE,
                        VehicleBaseConstants.INVALID_MODEL,
                        VehicleBaseConstants.VALID_PRICE,
                        VALID_CATEGORY));
    }

    @Test
    public void constructor_Should_ThrowException_When_PriceIsInvalid() {
        // Arrange, Act, Assert
        assertThrows(IllegalArgumentException.class, () ->
                new MotorcycleImpl(
                        VehicleBaseConstants.VALID_MAKE,
                        VehicleBaseConstants.VALID_MODEL,
                        -1,
                        VALID_CATEGORY));
    }

    @Test
    public void constructor_Should_ThrowException_When_CategoryLengthOutOfBounds() {
        // Arrange, Act, Assert
        assertThrows(IllegalArgumentException.class, () ->
                new MotorcycleImpl(
                        VehicleBaseConstants.VALID_MAKE,
                        VehicleBaseConstants.VALID_MODEL,
                        VehicleBaseConstants.VALID_PRICE,
                        INVALID_CATEGORY));
    }

    @Test
    public void constructor_Should_CreateNewMotorcycle_When_ParametersAreCorrect() {
        // Arrange, Act
        MotorcycleImpl motorcycle = initializeTestMotorcycle();

        // Assert
        assertAll(
                () -> assertEquals(VehicleBaseConstants.VALID_MAKE, motorcycle.getMake()),
                () -> assertEquals(VehicleBaseConstants.VALID_MODEL, motorcycle.getModel()),
                () -> Assertions.assertEquals(VehicleBaseConstants.VALID_PRICE, motorcycle.getPrice()),
                () -> assertEquals(VALID_CATEGORY, motorcycle.getCategory())
        );
    }

    public static MotorcycleImpl initializeTestMotorcycle() {
        return new MotorcycleImpl(
                VehicleBaseConstants.VALID_MAKE,
                VehicleBaseConstants.VALID_MODEL,
                VehicleBaseConstants.VALID_PRICE,
                VALID_CATEGORY);
    }
}
