package by.custom.utilcalculator.domain;

import by.custom.utilcalculator.domain.constants.steps.CountryOrigin;
import by.custom.utilcalculator.domain.constants.steps.OwnersType;

import java.io.Serializable;

public class PassengerStepsChecker implements Serializable {
    public boolean isStepOk(final Enum[] steps) {
        if (containsNullBeforeFilledNodes(steps)) {
            return false;
        } else {
            return !isQueueWrong(steps);
        }
    }


    private boolean isQueueWrong(final Enum[] steps) {
        if ((steps[0].equals(CountryOrigin.EAES) || steps[1].equals(OwnersType.PHYSICAL)) && (steps[3] != null || steps[4] != null)) {
            return true;
        } else return false;
    }

    private boolean containsNullBeforeFilledNodes(final Enum[] steps) {
        boolean isNull = false;

        int intForStartChecking = 0;
        for (int i = steps.length - 1; i >= 0; i--) {
            if (steps[i] != null) {
                intForStartChecking = i;
                break;
            }
        }

        for (int i = intForStartChecking; i >= 0; i--) {
            if (steps[i] == null) {
                isNull = true;
                break;
            }
        }

        return isNull;
    }

    private static class StepsCheckerHolder {
        private static final PassengerStepsChecker CHECKER_INSTANCE = new PassengerStepsChecker();
    }

    private PassengerStepsChecker() {
    }

    public static PassengerStepsChecker getInstance() {
        return StepsCheckerHolder.CHECKER_INSTANCE;
    }
}
