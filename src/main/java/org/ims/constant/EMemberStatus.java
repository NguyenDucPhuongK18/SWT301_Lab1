package org.ims.constant;

public enum EMemberStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive");

    public final String memberStatus;

    private EMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }

    public String getMemberStatus() {
        return memberStatus;
    }

    public static EMemberStatus convertFromString(String statusText) {
        for (EMemberStatus eus : EMemberStatus.values()) {
            if (eus.memberStatus.equalsIgnoreCase(statusText)) {
                return eus;
            }
        }
        return null;
    }

}
