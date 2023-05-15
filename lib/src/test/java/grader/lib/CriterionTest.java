package grader.lib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import grader.lib.Constants.CriterionScore;
import grader.lib.Constants.GradeScore;

public class CriterionTest {

    private static Criterion test;
    private final Assignment AD_ASSIGNMENT = new Assignment(GradeScore.AD, "4/4");
    private final Assignment PR_ASSIGNMENT = new Assignment(GradeScore.PR, "3/4");
    private final Assignment MI_ASSIGNMENT = new Assignment(GradeScore.MI, "1/4");

    @BeforeAll
    public static void initTest() {
        test = new Criterion('A');
    }

    @BeforeEach
    public void resetTestCriterion() {
        test.clearAssignments();
    }
    
    @Test
    public void testGetCriterionScore() {
        assertEquals(CriterionScore.NO_EVIDENCE, test.getCriterionScore());
        test.addAssignment(AD_ASSIGNMENT);
        assertEquals(CriterionScore.AD, test.getCriterionScore());

        test.addAssignment(PR_ASSIGNMENT);
        assertEquals(CriterionScore.AD, test.getCriterionScore());

        test.addAssignment(PR_ASSIGNMENT);
        assertEquals(CriterionScore.PR, test.getCriterionScore());


        test.addAssignment(MI_ASSIGNMENT);
        test.addAssignment(MI_ASSIGNMENT);
        assertEquals(CriterionScore.BA, test.getCriterionScore());

        test.removeAssignment(MI_ASSIGNMENT);
        assertEquals(CriterionScore.PR, test.getCriterionScore());

        test.clearAssignments();
        assertEquals(CriterionScore.NO_EVIDENCE, test.getCriterionScore());

    }

    @Test
    public void testEquals() {
        Criterion c = new Criterion('A');
        assertEquals(c, test);

        c.addAssignment(AD_ASSIGNMENT);
        assertNotEquals(c, test);

        test.addAssignment(AD_ASSIGNMENT);
        assertEquals(c, test);
    }

    @Test
    public void testCopyContrusctor() {
        Criterion c = new Criterion(test);
        assertEquals(c, test);

        c.addAssignment(AD_ASSIGNMENT);

        assertNotEquals(c, test);
    }

    @Test
    public void testNumberOfPerCriterion() {
        test.addAssignment(AD_ASSIGNMENT);
        assertEquals(1, Criterion.numberOfPerCriterion(test, CriterionScore.AD, GradeScore.PR));
        assertEquals(-2, Criterion.numberOfPerCriterion(test, CriterionScore.AD, GradeScore.AD));

        test.clearAssignments();
        test.addAssignment(AD_ASSIGNMENT);
        test.addAssignment(PR_ASSIGNMENT);
        assertEquals(0, Criterion.numberOfPerCriterion(test, CriterionScore.AD, GradeScore.PR));

        test.addAssignment(PR_ASSIGNMENT);
        test.addAssignment(PR_ASSIGNMENT);
        assertEquals(-1, Criterion.numberOfPerCriterion(test, CriterionScore.AD, GradeScore.PR));

        test.clearAssignments();
        test.addAssignment(AD_ASSIGNMENT);
        assertEquals(5, Criterion.numberOfPerCriterion(test, CriterionScore.BA, GradeScore.MI));
    }
}