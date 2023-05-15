package grader.lib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import grader.lib.Constants.GradeScore;

public class AssignmentTest {

    private final Assignment AD_ASSIGNMENT = new Assignment(GradeScore.AD, "4/4");
    private final Assignment PR_ASSIGNMENT = new Assignment(GradeScore.PR, "3/4");
    private final Assignment MI_ASSIGNMENT = new Assignment(GradeScore.MI, "1/4");

    @Test
    public void testEquals() {
        assertNotEquals(AD_ASSIGNMENT, "a string!!");
        assertNotEquals(AD_ASSIGNMENT, PR_ASSIGNMENT);
        assertNotEquals(AD_ASSIGNMENT, new Assignment(GradeScore.AD, "Name"), "Not equal because different names");
        assertNotEquals(PR_ASSIGNMENT, new Assignment(GradeScore.AD, "3/4"), "Not equal because different scores");

        assertEquals(MI_ASSIGNMENT, new Assignment(GradeScore.MI, "1/4"));
    }

}