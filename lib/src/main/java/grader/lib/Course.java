package grader.lib;

import grader.lib.Constants.LetterGrade;

public class Course {
    private Criterion[] criteria;
    public final String NAME;
    public final int NUMBER_OF_CRITERION;

    public Course(String name, int numberOfCriterion) {
        if(numberOfCriterion > 5 || numberOfCriterion < 4) {
            throw new IllegalStateException("Course '" + name + "' must have 4 or 5 criterion");
        }

        this.NAME = name;
        this.NUMBER_OF_CRITERION = numberOfCriterion;
        criteria = new Criterion[numberOfCriterion];
        for (int i = 0; i < numberOfCriterion; i++) {
            criteria[i] = new Criterion(Constants.ALPHABET[i]);
        }
    }

    public LetterGrade getGrade() {
        if(getTotalAssignments() == 0) {
            return LetterGrade.NO_EVIDENCE;
        }
        double score = getAverage();
        if(score >= 3.405) {
            return LetterGrade.A;
        }

        if(score >= 2.745) {
            return LetterGrade.B;
        }

        if(score >= 2.145) {
            return LetterGrade.C;
        }

        if(score >= 1.595) {
            return LetterGrade.D;
        }

        return LetterGrade.U;
    }

    private int getTotalAssignments() {
        int rtn = 0;
        for (Criterion i : criteria) {
            rtn += i.getAssignmentCount();
        }
        return rtn;
    }

    private double getAverage() {
        double rtn = 0;
        int scored_criterion = 0;
        for (Criterion i : criteria) {
            if(i.getAssignmentCount() != 0) {
                rtn += i.getCriterionScore().value;
                scored_criterion++;
            }
        }
        return rtn / scored_criterion;
    }

    public Criterion getCriterion(char id) {
        if(!criteriaExists(id)) {
            return null;
        }

        return criteria[indexOf(Constants.ALPHABET, id)];
    }

    public boolean criteriaExists(char id) {
        if(id == 'E' && criteria.length == 4) {
            return false;
        }
        return indexOf(Constants.ALPHABET, id) != -1;
    }

    private int indexOf(char[] arr, char key) {
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    public void clearCriterion() {
        for (Criterion c : criteria) {
            c.clearAssignments();
        }
    }

    public String toString() {
        String rtn = NAME + "\nGrade: " + getGrade() + " based on " + criteria.length + " criterion.\n";
        for (Criterion i : criteria) {
            rtn+= "\t" + i.ID + ": " + i.getCriterionScore().toString() + "\n";
        }
        return rtn;
    }
}
