package grader.lib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import grader.lib.Constants.GradeScore;
import grader.lib.Constants.LetterGrade;

public class CourseTest {

    private Course test = new Course("", 4);

    private final Assignment AD_ASSIGNMENT = new Assignment(GradeScore.AD, "4/4");
    private final Assignment PR_ASSIGNMENT = new Assignment(GradeScore.PR, "3/4");
    private final Assignment BA_ASSIGNMENT = new Assignment(GradeScore.BA, "2/4");
    private final Assignment MI_ASSIGNMENT = new Assignment(GradeScore.MI, "1/4");

    @BeforeEach
    public void resetCourse() {
        test.clearCriterion();
    }
 
    @Test
    public void criteriaExistsTest() {
        assertTrue(test.criteriaExists('A'));
        assertFalse(test.criteriaExists('Z'));
        assertFalse(test.criteriaExists('E'), "No criterion E as there are only 4 criterion in this particular instance");
        assertTrue(new Course("", 5).criteriaExists('E'));
    }

    @Test
    public void getCriterionTest() {
        assertEquals(null, test.getCriterion('E'));
        assertEquals(null, test.getCriterion('Z'));

        Criterion c = new Criterion('A');

        assertEquals(c, test.getCriterion('A'), "Assert that the course criterion are initalized and blank");

        test.getCriterion('A').addAssignment(new Assignment(GradeScore.AD, "Exam"));
        c.addAssignment(new Assignment(GradeScore.AD, "Exam"));

        assertEquals(c, test.getCriterion('A'), "Assert that the course criterion state is affected like the state of a regular criterion object");
    }

    @Test
    public void gradeCalculationTest() {
        assertEquals(LetterGrade.NO_EVIDENCE, test.getGrade());

        test.getCriterion('A').addAssignment(AD_ASSIGNMENT);
        test.getCriterion('B').addAssignment(PR_ASSIGNMENT);
        assertEquals(LetterGrade.A, test.getGrade());

        test.getCriterion('C').addAssignment(BA_ASSIGNMENT);
        assertEquals(LetterGrade.B, test.getGrade());

        test.clearCriterion();
        test.getCriterion('A').addAssignment(BA_ASSIGNMENT);
        assertEquals(LetterGrade.D, test.getGrade());

        test.clearCriterion();
        test.getCriterion('A').addAssignment(MI_ASSIGNMENT);
        assertEquals(LetterGrade.U, test.getGrade());
    }
}