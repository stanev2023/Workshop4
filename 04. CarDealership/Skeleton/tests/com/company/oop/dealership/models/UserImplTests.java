package com.company.oop.dealership.models;

import com.company.oop.dealership.models.enums.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.company.oop.dealership.models.CarImplTests.initializeTestCar;
import static com.company.oop.dealership.models.CommentImplTests.VALID_AUTHOR;
import static com.company.oop.dealership.models.CommentImplTests.VALID_CONTENT;
import static com.company.oop.dealership.models.UserImpl.VIP_MAX_VEHICLES_TO_ADD;
import static com.company.oop.dealership.utils.TestUtilities.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserImplTests {
    public static final int USERNAME_LEN_MIN = 2;
    public static final int PASSWORD_LEN_MIN = 5;
    public static final int LASTNAME_LEN_MIN = 2;
    public static final int FIRSTNAME_LEN_MIN = 2;
    private static final int NORMAL_ROLE_VEHICLE_LIMIT = 5;

    public static final String VALID_USERNAME = getString(USERNAME_LEN_MIN + 1);
    public static final String VALID_PASSWORD = getString(PASSWORD_LEN_MIN + 1);
    public static final String VALID_FIRST_NAME = getString(FIRSTNAME_LEN_MIN + 1);
    public static final String VALID_LAST_NAME = getString(LASTNAME_LEN_MIN + 1);
    public static final String INVALID_USERNAME = getString(USERNAME_LEN_MIN - 1);
    public static final String INVALID_PASSWORD = getString(PASSWORD_LEN_MIN - 1);
    public static final String INVALID_USERNAME_PATTERN = "U$$ernam3";
    public static final String INVALID_PASSWORD_PATTERN = "Pa-$$_w0rD";
    public static final String INVALID_FIRST_NAME = getString(FIRSTNAME_LEN_MIN - 1);
    public static final String INVALID_LAST_NAME = getString(LASTNAME_LEN_MIN - 1);

    @Test
    public void constructor_Should_ThrowException_When_UsernameLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new UserImpl(
                        INVALID_USERNAME,
                        VALID_FIRST_NAME,
                        VALID_LAST_NAME,
                        VALID_PASSWORD,
                        UserRole.NORMAL));
    }

    @Test
    public void constructor_Should_Throw_When_UsernameDoesNotMatchPattern() {
        //Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new UserImpl(
                        INVALID_USERNAME_PATTERN,
                        VALID_FIRST_NAME,
                        VALID_LAST_NAME,
                        VALID_PASSWORD,
                        UserRole.NORMAL));
    }

    @Test
    public void constructor_Should_ThrowException_When_FirstNameLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new UserImpl(
                        VALID_USERNAME,
                        INVALID_FIRST_NAME,
                        VALID_LAST_NAME,
                        VALID_PASSWORD,
                        UserRole.NORMAL));
    }

    @Test
    public void Constructor_Should_ThrowException_When_LastNameLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new UserImpl(
                        VALID_USERNAME,
                        VALID_FIRST_NAME,
                        INVALID_LAST_NAME,
                        VALID_PASSWORD,
                        UserRole.NORMAL));
    }

    @Test
    public void constructor_Should_ThrowException_When_PasswordLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new UserImpl(
                        VALID_USERNAME,
                        VALID_FIRST_NAME,
                        VALID_LAST_NAME,
                        INVALID_PASSWORD,
                        UserRole.NORMAL));
    }

    @Test
    public void Constructor_Should_ThrowException_When_PasswordDoesNotMatchPattern() {
        //Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new UserImpl(
                        VALID_USERNAME,
                        VALID_FIRST_NAME,
                        VALID_LAST_NAME,
                        INVALID_PASSWORD_PATTERN,
                        UserRole.NORMAL));
    }

    @Test
    public void constructor_Should_CreateNewUser_When_ParametersAreCorrect() {
        // Arrange, Act
        UserImpl user = initializeTestUser();

        // Assert
        assertEquals(VALID_USERNAME, user.getUsername());
    }

    @Test
    public void getVehicles_Should_ReturnCopyOfTheCollection() {
        // Arrange
        UserImpl user = initializeTestUser();

        // Act
        user.getVehicles().add(initializeTestCar());

        // Assert
        assertEquals(0, user.getVehicles().size());
    }

    @Test
    public void addComment_Should_AddCommentToTheCollection() {
        // Arrange
        UserImpl user = initializeTestUser();
        CarImpl car = initializeTestCar();
        user.addVehicle(car);

        // Act
        user.addComment(new CommentImpl(
                VALID_CONTENT,
                VALID_AUTHOR), car);

        // Assert
        assertEquals(1, car.getComments().size());
    }

    @Test
    public void addVehicle_Should_AddVehicle_WhenNormalUser() {
        // Arrange
        UserImpl user = initializeTestUser();

        // Act
        user.addVehicle(initializeTestCar());

        // Assert
        assertEquals(1, user.getVehicles().size());
    }

    @Test
    public void addVehicle_Should_ThrowException_WhenNormalUserReachedLimit() {
        // Arrange
        UserImpl user = initializeTestUser();
        for (int i = 0; i < VIP_MAX_VEHICLES_TO_ADD; i++) {
            user.addVehicle(initializeTestCar());
        }

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                user.addVehicle(initializeTestCar()));
    }

    @Test
    public void addVehicle_Should_ThrowException_WhenAdminUser() {
        // Arrange
        UserImpl user = initializeTestAdmin();

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                user.addVehicle(initializeTestCar()));
    }

    @Test
    public void addVehicle_Should_AddVehicle_WhenVipUser() {
        // Arrange
        UserImpl user = initializeTestVipUser();

        // Act
        user.addVehicle(initializeTestCar());

        // Assert
        assertEquals(1, user.getVehicles().size());
    }

    @Test
    public void addVehicle_Should_AddVehicle_WhenVipUserAndMoreThanMaxVehicles() {
        // Arrange
        UserImpl user = initializeTestVipUser();
        for (int i = 0; i < NORMAL_ROLE_VEHICLE_LIMIT; i++) {
            user.addVehicle(initializeTestCar());
        }

        // Act
        user.addVehicle(initializeTestCar());

        // Assert
        assertEquals(6, user.getVehicles().size());
    }

    public static UserImpl initializeTestUser() {
        return initializeTestUser(UserRole.NORMAL);
    }

    public static UserImpl initializeTestAdmin() {
        return initializeTestUser(UserRole.ADMIN);
    }

    public static UserImpl initializeTestVipUser() {
        return initializeTestUser(UserRole.VIP);
    }

    private static UserImpl initializeTestUser(UserRole role) {
        return new UserImpl(
                VALID_USERNAME,
                VALID_FIRST_NAME,
                VALID_LAST_NAME,
                VALID_PASSWORD,
                role);
    }

}
