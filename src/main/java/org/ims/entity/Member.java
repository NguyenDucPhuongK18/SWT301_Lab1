package org.ims.entity;

import org.ims.constant.EMemberRole;
import org.ims.constant.EMemberStatus;

import java.io.Serializable;
import java.time.LocalDate;

public class Member implements Serializable {

    private int memberId;
    private String memberFullName;
    private String memberAccount;
    private String memberPassword;
    private LocalDate memberDob;
    private LocalDate memberCreatedTime;
    private String memberAddress;
    private String memberEmail;
    private String memberPhoneNumber;
    private String memberImage;
    private String memberNote;
    private EMemberStatus memberStatus;
    private EMemberRole memberRole;

    public Member() {
    }

    public Member(int memberId, String memberFullName, String memberAccount, String memberPassword,
            LocalDate memberDob, LocalDate memberCreatedTime, String memberAddress, String memberEmail,
            String memberPhoneNumber, String memberImage, String memberNote, EMemberStatus memberStatus,
            EMemberRole memberRole) {
        this.memberId = memberId;
        this.memberFullName = memberFullName;
        this.memberAccount = memberAccount;
        this.memberPassword = memberPassword;
        this.memberDob = memberDob;
        this.memberCreatedTime = memberCreatedTime;
        this.memberAddress = memberAddress;
        this.memberEmail = memberEmail;
        this.memberPhoneNumber = memberPhoneNumber;
        this.memberImage = memberImage;
        this.memberNote = memberNote;
        this.memberStatus = memberStatus;
        this.memberRole = memberRole;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberFullName() {
        return memberFullName;
    }

    public void setMemberFullName(String memberFullName) {
        this.memberFullName = memberFullName;
    }

    public String getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(String memberAccount) {
        this.memberAccount = memberAccount;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public LocalDate getMemberDob() {
        return memberDob;
    }

    public void setMemberDob(LocalDate memberDob) {
        this.memberDob = memberDob;
    }

    public LocalDate getMemberCreatedTime() {
        return memberCreatedTime;
    }

    public void setMemberCreatedTime(LocalDate memberCreatedTime) {
        this.memberCreatedTime = memberCreatedTime;
    }

    public String getMemberAddress() {
        return memberAddress;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberPhoneNumber() {
        return memberPhoneNumber;
    }

    public void setMemberPhoneNumber(String memberPhoneNumber) {
        this.memberPhoneNumber = memberPhoneNumber;
    }

    public String getMemberImage() {
        return memberImage;
    }

    public void setMemberImage(String memberImage) {
        this.memberImage = memberImage;
    }

    public String getMemberNote() {
        return memberNote;
    }

    public void setMemberNote(String memberNote) {
        this.memberNote = memberNote;
    }

    public EMemberStatus getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(EMemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }

    public EMemberRole getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(EMemberRole memberRole) {
        this.memberRole = memberRole;
    }

}
