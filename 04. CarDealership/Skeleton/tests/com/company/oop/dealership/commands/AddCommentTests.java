package com.company.oop.dealership.commands;

import com.company.oop.dealership.core.VehicleDealershipRepositoryImpl;
import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;
import com.company.oop.dealership.models.contracts.Comment;
import com.company.oop.dealership.models.contracts.User;
import com.company.oop.dealership.commands.contracts.Command;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.company.oop.dealership.commands.LoginTests.loginInitializedUserToRepository;
import static com.company.oop.dealership.models.CommentImplTests.VALID_CONTENT;
import static com.company.oop.dealership.models.TruckImplTests.initializeTestTruck;
import static com.company.oop.dealership.utils.TestUtilities.getList;

public class AddCommentTests {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    private VehicleDealershipRepository repository;
    private Command addCommentsCommand;
    private User user;

    @BeforeEach
    public void before() {
        repository = new VehicleDealershipRepositoryImpl();
        addCommentsCommand = new AddCommentCommand(repository);
        user = loginInitializedUserToRepository(repository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> addCommentsCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_VehicleIndexIsNotNumber() {
        // Arrange
        List<String> params = List.of(
                VALID_CONTENT,
                user.getUsername(),
                "INVALID_INDEX");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> addCommentsCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_VehicleDoesNotExist() {
        // Arrange
        List<String> params = List.of(
                VALID_CONTENT,
                user.getUsername(),
                "1");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> addCommentsCommand.execute(params));
    }

    @Test
    public void should_Create_When_InputIsValid() {
        // Arrange
        user.addVehicle(initializeTestTruck());

        List<String> params = List.of(
                VALID_CONTENT,
                user.getUsername(),
                "1");

        // Act
        addCommentsCommand.execute(params);

        //Assert
        Comment comment = repository.getLoggedInUser().getVehicles().get(0).getComments().get(0);
        Assertions.assertEquals(VALID_CONTENT, comment.getContent());
        Assertions.assertEquals(user.getUsername(), comment.getAuthor());
    }
}
