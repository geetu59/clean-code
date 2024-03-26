package com.c.refactoring.movie;

import com.c.refactoring.StringUtils;

import java.util.Arrays;
import java.util.List;

public class Movie {

    public static final List<String> VALID_B_RATINGS = Arrays.asList("B1", "B2", "B3", "B4");
    String rating;

    public Movie(String rating) {
        super();
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    /*Axx or By
    Where x represents any digit between 0 and 9, and y represents 
    any digit between 1 and 4
    Refactor:
    1. Remove first condition of rating!=null, beside add rating ==null on top only
    2. Since this.getRating is used frequently, put it in variable
    3. Since, if else  if both are independent logical statements, make other if only
    4. Move logic of if in other method
    5. For B rating, we know it can have only 4 possible values, so put them in list and check if your rating is there
    beside of doing substring and putting multiple conditions*/
    public boolean isValidRating() {
        String rating = this.getRating();

        if (rating == null) return false;

        if (isBRating(rating)) return true;

        if (isARating(rating)) return true;

        return false;
    }

    private static boolean isBRating(String rating) {
        return VALID_B_RATINGS.contains(rating);
    }

    private static boolean isARating(String rating) {
        String firstChar = rating.substring(0, 1);
        return firstChar.equalsIgnoreCase("A")
                && rating.length() == 3
                && StringUtils.isNumeric(rating.substring(1, 3));
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
