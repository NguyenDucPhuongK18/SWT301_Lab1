package org.ims.constant;

public enum EMemberRole {
    ADMINISTRATOR("Administrator"),
    HR_MANAGER("HR Manager"),
    JOB_MANAGER("Job Manager"),
    MEMBER("Member");
    public final String memberRole;

    EMemberRole(String memberRole) {
        this.memberRole = memberRole;
    }

    public String getMemberRole() {
        return memberRole;
    }

    public static EMemberRole convertFromString(String roleText) {
        for (EMemberRole ems : EMemberRole.values()) {
            if (ems.memberRole.equalsIgnoreCase(roleText)) {
                return ems;
            }
        }
        return null;
    }
}
