package apiTest.handlers;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.lang.reflect.Method;

public class FailedTestHandler implements TestWatcher {
    private static final int MAX_RETRY = 5;
    private static int countRetry;

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        context.getTestMethod().ifPresent(method -> {
            boolean result = retryTest(context, method);
            if (!result) {
                System.err.println(String.format("Тест провален! Количество попыток %s из %s", countRetry, MAX_RETRY));
            }
        });
    }

    private boolean retryTest(ExtensionContext context, Method method) {
        countRetry = 0;
        while (countRetry < MAX_RETRY) {
            if (restartTest(context, method)) {
                return true;
            }
            countRetry++;
        }
        return false;
    }

    private boolean restartTest(ExtensionContext context, Method method) {
        try {
            method.invoke(context.getRequiredTestInstance());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
