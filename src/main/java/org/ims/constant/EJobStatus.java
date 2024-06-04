package org.ims.constant;

public enum EJobStatus {
    AVAILABLE("Available"),
    UNAVAILABLE("Unavailable");

    public final String jobStatus;

    EJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public static EJobStatus convertFromString(String statusText) {
        for (EJobStatus ejs : EJobStatus.values()) {
            if (ejs.jobStatus.equalsIgnoreCase(statusText)) {
                return ejs;
            }
        }
        return null;
    }

}
