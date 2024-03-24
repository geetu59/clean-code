package com.b.simple.design.business.student;

public class StudentHelper {

    public static final int GRADE_B_LOWER_LIMIT = 51;
    public static final int GRADE_B_UPPER_LIMIT = 80;
    public static final int EXTRA_LIMIT = 10;

    /* PROBLEM 1 */
    /*
     * You get a grade B if marks are between 51 and 80 (both inclusive). Except for Maths where the upper limit is increased by 10.
     * Refactoring:
     * 1. Make constants for lower and upper limit.
     * 2. Check for isMaths, changes upper limit, so extract it out and update upper limit accordingly to isMaths check
     * 3. In upper limit also, some extra is getting added, based on isMaths condition and hence further extract it out.
     */
    public boolean isGradeB(int marks, boolean isMaths) {
        int extraLimit = isMaths ? EXTRA_LIMIT : 0;
        int upperLimit = GRADE_B_UPPER_LIMIT + extraLimit;
        return marks >= GRADE_B_LOWER_LIMIT && marks <= upperLimit;
    }

    /* PROBLEM 2 */
	/*
	You are awarded a grade based on your marks.
	Grade A = 91 to 100, Grade B = 51 to 90, Otherwise Grade C
	Except for Maths where marks to get a Grade are 5 higher than required for other subjects.
	Refactor
	1. Fns isGradeA are very small, so inline them.
	2. Naming of methods should be consistent.
	3. Not removing comment as it is telling business logic
	4. Since we have to return grade only, so no need to put it in variable, directly return
	5. Since, we directly return, so no need of else if
	6. If you see, 90+5, 50+5, so extra limit is 5 and hence extract it
	7. isMaths check if for extra limit and hence put it in extraLimit
	8. Inline lowerLimitForAGrade
	9. Dont need marks>90 check as first if will return if that's the case
	*/

    public String getGrade(int mark, boolean isMaths) {
        int extraLimit = isMaths ? 5 : 0;
        if (mark > 90 + extraLimit)
            return Grade.A.toString();
        if (mark > 50 + extraLimit) {
            return Grade.B.toString();
        }
        return Grade.C.toString();
    }

    /*  PROBLEM 3
     * You and your Friend are planning to enter a Subject Quiz.
     * However, there is a marks requirement that you should attain to qualify.
     *
     * Return value can be YES, NO or MAYBE.
     *
     * YES If either of you are very good at the subject(has 80 or more marks)
     * However, there is an exception that if either of you is not good in the subject(20 or less marks), it is NO.
     * In all other conditions, return MAYBE.
     *
     * However, the definition for good and not good are 5 marks higher if the subject is Mathematics.
     *
     * marks1 - your marks
     * marks2 - your friends marks
     * Refactoring:
     * 1. Check the extra limit for maths, extract it and apply limit there itself
     * 2. You may extract logic inside if condition to a method
     */

    public String willQualifyForQuiz(int marks1, int marks2, boolean isMaths) {
        int extraLimit = isMaths ? 5 : 0;
        if ((marks1 <= 20 + extraLimit) || (marks2 <= 20 + extraLimit)) return "NO";
        if ((marks1 >= 80 + extraLimit) || (marks2 >= 80 + extraLimit)) return "YES";
        return "MAYBE";
    }

}