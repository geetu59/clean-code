package com.c.refactoring.lock;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UserLoginCheckerTest {
    public static final int ID = 10;
    public static final String STATUS = "NOT_USED";
    UserLoginChecker userLoginChecker = new UserLoginChecker();

    @Test
    public void testisUserAllowedToLogin_DifferentUserTriesImmediatelyAfter() {
        Object[] existingLock = new Object[]{"TEST_USER_ID_1", new Date()};

        Lock lock = userLoginChecker.isUserAllowedToLogin(true, new User(
                "TEST_USER_ID_2"), Arrays.asList(new Object[][]{existingLock}));

        assertReadAccess(lock);
    }

    @Test
    public void testisUserAllowedToLogin_SameUserReturnsToFirstScreen() {
        Object[] existingLock = new Object[]{"TEST_USER_ID", new Date()};

        Lock lock = userLoginChecker.isUserAllowedToLogin(true, new User(
                "TEST_USER_ID"), Arrays.asList(new Object[][]{existingLock}));

        assetWriteAccess(lock);
    }

    @Test
    public void testisUserAllowedToLogin_SameUserReturnsToSecondScreen() {
        Object[] existingLock = new Object[]{"TEST_USER_ID", new Date()};

        Lock lock = userLoginChecker.isUserAllowedToLogin(false, new User(
                "TEST_USER_ID"), Arrays.asList(new Object[][]{existingLock}));

        assetWriteAccess(lock);
    }

    @Test
    public void testisUserAllowedToLogin_User2TriesToLoginToFirstScreen3hoursAfterUser1() {
        Object[] existingLock = new Object[]{"TEST_USER_ID_1", threeHoursBefore()};

        Lock lock = userLoginChecker.isUserAllowedToLogin(true, new User(
                "TEST_USER_ID_2"), Arrays.asList(new Object[][]{existingLock}));

        assetWriteAccess(lock);
    }

    @Test
    public void testisUserAllowedToLogin_User2TriesToLoginToSecondScreen3hoursAfterUser1() {
        Object[] existingLock = new Object[]{"TEST_USER_ID_1", threeHoursBefore()};

        Lock lock = userLoginChecker.isUserAllowedToLogin(false, new User(
                "TEST_USER_ID_2"), Arrays.asList(new Object[][]{existingLock}));

        assertReadAccess(lock);
    }

    public Date threeHoursBefore() {
        Date now = new Date();
        return new Date(now.getTime() - 3 * 60 * 60 * 1000);
    }

    private static void assetWriteAccess(Lock lock) {
        assertFalse(lock.isReadAccess());
        assertNull(lock.getLockReason());
    }

    private static void assertReadAccess(Lock lock) {
        assertTrue(lock.isReadAccess());
        assertNotNull(lock.getLockReason());
    }

}
