package com.company.oop.dealership.core;

import com.company.oop.dealership.commands.LoginTests;
import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;
import com.company.oop.dealership.models.UserImplTests;
import com.company.oop.dealership.models.contracts.*;
import com.company.oop.dealership.models.enums.UserRole;
import com.company.oop.dealership.utils.VehicleBaseConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.company.oop.dealership.models.CarImplTests.VALID_SEATS;
import static com.company.oop.dealership.models.CommentImplTests.VALID_AUTHOR;
import static com.company.oop.dealership.models.CommentImplTests.VALID_CONTENT;
import static com.company.oop.dealership.models.MotorcycleImplTests.VALID_CATEGORY;
import static com.company.oop.dealership.models.TruckImplTests.VALID_WEIGHT_CAP;
import static org.junit.jupiter.api.Assertions.*;

public class VehicleDealershipRepositoryTests {

    VehicleDealershipRepository repository;

    @BeforeEach
    public void beforeEach() {
        repository = new VehicleDealershipRepositoryImpl();
    }

    @Test
    public void addUser_Should_ThrowException_When_UserAlreadyExists() {
        // Arrange
        User user = addInitializedUserToRepo(repository);

        // Act
        repository.addUser(user);

        // Assert
        assertEquals(1, repository.getUsers().size());
    }

    @Test
    public void addUser_Should_AddUser_When_UserDoesNotExist() {
        // Arrange
        User user = UserImplTests.initializeTestUser();

        // Act
        repository.addUser(user);

        // Assert
        assertEquals(1, repository.getUsers().size());
    }

    @Test
    public void findUserByUsername_Should_ThrowException_When_UserDoesNotExist() {
        //Arrange, Act, Assert
        assertThrows(IllegalArgumentException.class, () -> repository.findUserByUsername(UserImplTests.VALID_USERNAME));
    }

    @Test
    public void findUserByUsername_Should_ReturnUser_When_UserExists() {
        // Arrange
        User user = addInitializedUserToRepo(repository);

        // Act
        User foundUser = repository.findUserByUsername(user.getUsername());

        // Assert
        assertAll(
                () -> assertEquals(user.getUsername(), foundUser.getUsername()),
                () -> assertEquals(user.getFirstName(), foundUser.getFirstName()),
                () -> assertEquals(user.getLastName(), foundUser.getLastName()),
                () -> assertEquals(user.getRole(), foundUser.getRole()));
    }

    @Test
    public void getLoggedInUser_Should_ThrowException_When_NoUserIsLoggedIn() {
        //Arrange, Act, Assert
        assertThrows(IllegalArgumentException.class, () -> repository.getLoggedInUser());
    }

    @Test
    public void getLoggedInUser_Should_ReturnUser_When_UserIsLoggedIn() {
        // Arrange
        User user = LoginTests.loginInitializedUserToRepository(repository);

        // Act
        User loggedInUser = repository.getLoggedInUser();

        // Assert
        assertAll(
                () -> assertEquals(user.getUsername(), loggedInUser.getUsername()),
                () -> assertEquals(user.getFirstName(), loggedInUser.getFirstName()),
                () -> assertEquals(user.getLastName(), loggedInUser.getLastName()),
                () -> assertEquals(user.getRole(), loggedInUser.getRole()));
    }

    @Test
    public void login_Should_LoginUser_When() {
        // Arrange, Act
        User user = UserImplTests.initializeTestUser();
        repository.login(user);
        User loggedInUser = repository.getLoggedInUser();

        // Assert
        assertAll(
                () -> assertEquals(user.getUsername(), loggedInUser.getUsername()),
                () -> assertEquals(user.getFirstName(), loggedInUser.getFirstName()),
                () -> assertEquals(user.getLastName(), loggedInUser.getLastName()),
                () -> assertEquals(user.getRole(), loggedInUser.getRole()));
    }

    @Test
    public void hasLoggedInUser_Should_ReturnTrue_When_UserIsLoggedIn() {
        // Arrange, Act
        User user = UserImplTests.initializeTestUser();
        repository.login(user);

        // Assert
        assertTrue(repository.hasLoggedInUser());
    }

    @Test
    public void hasLoggedInUser_Should_ReturnFalse_When_NoUserIsLoggedIn() {
        // Arrange, Act, Assert
        assertFalse(repository.hasLoggedInUser());
    }

    @Test
    public void logout_Should_LogoutUser_When_UserIsLoggedIn() {
        // Arrange
        User user = UserImplTests.initializeTestUser();
        repository.login(user);

        // Act
        repository.logout();

        // Assert
        assertThrows(IllegalArgumentException.class, () -> repository.getLoggedInUser());
    }

    @Test
    public void createCar_Should_ReturnCar_When_InputIsValid() {
        // Arrange, Act
        Car testCar = repository.createCar(
                VehicleBaseConstants.VALID_MAKE,
                VehicleBaseConstants.VALID_MODEL,
                VehicleBaseConstants.VALID_PRICE,
                VALID_SEATS
        );

        // Assert
        assertAll(
                () -> assertEquals(testCar.getMake(), VehicleBaseConstants.VALID_MAKE),
                () -> assertEquals(testCar.getModel(), VehicleBaseConstants.VALID_MODEL),
                () -> Assertions.assertEquals(testCar.getPrice(), VehicleBaseConstants.VALID_PRICE),
                () -> assertEquals(testCar.getSeats(), VALID_SEATS));
    }

    @Test
    public void createMotorcycle_Should_ReturnMotorcycle_When_InputIsValid() {
        // Arrange, Act
        Motorcycle testMotorcycle = repository.createMotorcycle(
                VehicleBaseConstants.VALID_MAKE,
                VehicleBaseConstants.VALID_MODEL,
                VehicleBaseConstants.VALID_PRICE,
                VALID_CATEGORY
        );

        // Assert
        assertAll(
                () -> assertEquals(testMotorcycle.getMake(), VehicleBaseConstants.VALID_MAKE),
                () -> assertEquals(testMotorcycle.getModel(), VehicleBaseConstants.VALID_MODEL),
                () -> Assertions.assertEquals(testMotorcycle.getPrice(), VehicleBaseConstants.VALID_PRICE),
                () -> assertEquals(testMotorcycle.getCategory(), VALID_CATEGORY));
    }

    @Test
    public void createTruck_Should_ReturnTruck_When_InputIsValid() {
        // Arrange, Act
        Truck testTruck = repository.createTruck(
                VehicleBaseConstants.VALID_MAKE,
                VehicleBaseConstants.VALID_MODEL,
                VehicleBaseConstants.VALID_PRICE,
                VALID_WEIGHT_CAP
        );

        // Assert
        assertAll(
                () -> assertEquals(testTruck.getMake(), VehicleBaseConstants.VALID_MAKE),
                () -> assertEquals(testTruck.getModel(), VehicleBaseConstants.VALID_MODEL),
                () -> Assertions.assertEquals(testTruck.getPrice(), VehicleBaseConstants.VALID_PRICE),
                () -> assertEquals(testTruck.getWeightCapacity(), VALID_WEIGHT_CAP));
    }

    @Test
    public void createUser_Should_ReturnUser_When_InputIsValid() {
        // Arrange, Act
        User testUser = repository.createUser(
                UserImplTests.VALID_USERNAME,
                UserImplTests.VALID_FIRST_NAME,
                UserImplTests.VALID_LAST_NAME,
                UserImplTests.VALID_PASSWORD,
                UserRole.NORMAL
        );

        // Assert
        assertAll(
                () -> assertEquals(testUser.getUsername(), UserImplTests.VALID_USERNAME),
                () -> assertEquals(testUser.getFirstName(), UserImplTests.VALID_FIRST_NAME),
                () -> assertEquals(testUser.getLastName(), UserImplTests.VALID_LAST_NAME),
                () -> assertEquals(testUser.getRole(), UserRole.NORMAL));
    }

    @Test
    public void createComment_Should_ReturnCar_When_InputIsValid() {
        // Arrange, Act
        Comment testComment = repository.createComment(
                VALID_CONTENT,
                VALID_AUTHOR
        );

        // Assert
        assertAll(
                () -> assertEquals(testComment.getContent(), VALID_CONTENT),
                () -> assertEquals(testComment.getAuthor(), VALID_AUTHOR));
    }

    public static User addInitializedUserToRepo(VehicleDealershipRepository repository) {
        User testUser = UserImplTests.initializeTestUser();
        repository.addUser(testUser);
        return testUser;
    }

}
