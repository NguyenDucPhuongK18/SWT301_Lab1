package org.ims.constant;

public enum EInterviewStatus {
    FINISHED("Finished"),
    SCHEDULED("Scheduled");
    public final String interviewStatus;

    EInterviewStatus(String interviewStatus) {
        this.interviewStatus = interviewStatus;
    }

    public String getInterviewStatus() {
        return interviewStatus;
    }

    public static EInterviewStatus convertFromString(String statusText) {
        for (EInterviewStatus eis : EInterviewStatus.values()) {
            if (eis.interviewStatus.equalsIgnoreCase(statusText)) {
                return eis;
            }
        }
        return null;
    }

}
