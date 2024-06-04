package org.ims.constant;

public enum ECandidateStatus {
    QUALIFIED("Qualified"),
    WAITING("Waiting"),
    UNQUALIFIED("Unqualified");

    public final String candidateStatus;

    ECandidateStatus(String candidateStatus) {
        this.candidateStatus = candidateStatus;
    }

    public String getCandidateStatus() {
        return candidateStatus;
    }

    public static ECandidateStatus convertFromString(String statusText) {
        for (ECandidateStatus ecs : ECandidateStatus.values()) {
            if (ecs.candidateStatus.equalsIgnoreCase(statusText)) {
                return ecs;
            }
        }
        return null;
    }

}
