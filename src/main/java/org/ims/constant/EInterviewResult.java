package org.ims.constant;

public enum EInterviewResult {
    PASSED("Passed"),
    WAITING("Waiting"),
    FAILED("Failed");

    public final String interviewResult;

    EInterviewResult(String interviewResult) {
        this.interviewResult = interviewResult;
    }

    public String getInterviewResult() {
        return interviewResult;
    }

    public static EInterviewResult convertFromString(String statusText) {
        for (EInterviewResult eir : EInterviewResult.values()) {
            if (eir.interviewResult.equalsIgnoreCase(statusText)) {
                return eir;
            }
        }
        return null;
    }
}
