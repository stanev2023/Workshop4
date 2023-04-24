package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Comment;
import com.company.oop.dealership.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommentImplTests {

    public static final int USERNAME_LEN_MIN = 2;
    public static final int CONTENT_LEN_MIN = 3;

    public static final String VALID_CONTENT = TestUtilities.getString(CONTENT_LEN_MIN + 1);
    public static final String VALID_AUTHOR = TestUtilities.getString(USERNAME_LEN_MIN + 1);
    public static final String INVALID_CONTENT = TestUtilities.getString(CONTENT_LEN_MIN - 1);

    @Test
    public void commentImpl_Should_ImplementCommentInterface() {
        // Arrange, Act
        CommentImpl comment = initializeTestComment();
        // Assert
        Assertions.assertTrue(comment instanceof Comment);
    }

    @Test
    public void constructor_Should_ThrowException_When_ModelNameLengthOutOfBounds() {
        // Arrange, Act, Assert
        assertThrows(IllegalArgumentException.class, () -> new CommentImpl(
                INVALID_CONTENT,
                VALID_AUTHOR
        ));
    }

    @Test
    public void constructor_Should_CreateNewComment_When_ParametersAreCorrect() {
        // Arrange, Act
        CommentImpl comment = initializeTestComment();

        // Assert
        assertEquals(VALID_CONTENT, comment.getContent());
    }

    public static CommentImpl initializeTestComment() {
        return new CommentImpl(
                VALID_CONTENT,
                VALID_AUTHOR);
    }

}
