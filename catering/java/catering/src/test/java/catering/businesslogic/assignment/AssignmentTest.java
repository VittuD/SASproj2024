package catering.businesslogic.assignment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssignmentTest {

    @Test
    void getDescription() {
        Assignment assignment = new Assignment.Builder()
                .description("Test description")
                .build();
        assertEquals("Test description", assignment.getDescription());
    }
}