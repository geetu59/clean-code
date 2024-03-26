package com.c.refactoring.lock;

import java.util.Date;
import java.util.List;

public class UserLoginChecker {

    public static final int MAX_LOCK_PERIOD_IN_MS = 60 * 60 * 1000;

    /**
     * {@inheritDoc}.
     */
    /*Refactor:
     * 1. remove unused params from method
     * 2. Method checks for existing locks and accordingly provided to new userTryingToLogin and hence rename variable
     * 3. Creating lock obj wherever we are setting and then move corresponding lines to method
     * 4. When we have if() return, no else then remove that if
     * 5. When we return in if else blocks then remove else if/else, only have if
     * 6. Put variables near to where it is being used
     * 7. If user is same then give write access and if it's first screen then also, so remove condition from there and add it first*/
    public Lock isUserAllowedToLogin(boolean firstScreen, User userTryingToLogin, List existingLocks) {
        if (existingLocks.size() == 0 && existingLocks.get(0) == null) return createWriteLock();

        Object[] object = (Object[]) existingLocks.get(0);
        String userIdWithLock = (String) object[0];

        if (userIdWithLock == null) return createWriteLock();

        boolean userSameAsTryingToLogin = userIdWithLock.equalsIgnoreCase(userTryingToLogin.getUserId());
        if (userSameAsTryingToLogin) return createWriteLock();

        Date time = new Date();
        Date lockTimestamp = (Date) object[1];
        long timeElapsedSinceLock = time.getTime() - lockTimestamp.getTime();
        if (firstScreen || timeElapsedSinceLock > MAX_LOCK_PERIOD_IN_MS) return createWriteLock();

        String lockMsg = Constants.LOCK_TEXT.replaceAll("@@USER@@", userIdWithLock);
        return createReadLock(lockMsg);
    }

    private static Lock createWriteLock() {
        Lock lock = new Lock();
        lock.setRead(false);
        return lock;
    }

    private static Lock createReadLock(String lockMsg) {
        Lock lock = new Lock();
        lock.setRead(true);
        lock.setLockReason(lockMsg);
        return lock;
    }
}