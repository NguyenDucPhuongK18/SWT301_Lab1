package org.ims.entity;

import org.ims.constant.ECandidateStatus;

import java.time.LocalDate;

public class Candidate {

    private int candidateId;
    private String candidateFullName;
    private String candidateAddress;
    private String candidatePhoneNumber;
    private String candidateEmail;
    private String candidateImage;
    private LocalDate candidateDob;
    private String candidateCvAttachment;
    private ECandidateStatus candidateStatus;
    private String candidateNote;

    public Candidate() {
    }

    public Candidate(int candidateId, String candidateFullName, String candidateAddress,
            String candidatePhoneNumber, String candidateEmail, String candidateImage,
            LocalDate candidateDob, String candidateCvAttachment, ECandidateStatus candidateStatus,
            String candidateNote) {
        this.candidateId = candidateId;
        this.candidateFullName = candidateFullName;
        this.candidateAddress = candidateAddress;
        this.candidatePhoneNumber = candidatePhoneNumber;
        this.candidateEmail = candidateEmail;
        this.candidateImage = candidateImage;
        this.candidateDob = candidateDob;
        this.candidateCvAttachment = candidateCvAttachment;
        this.candidateStatus = candidateStatus;
        this.candidateNote = candidateNote;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public String getCandidateFullName() {
        return candidateFullName;
    }

    public void setCandidateFullName(String candidateFullName) {
        this.candidateFullName = candidateFullName;
    }

    public String getCandidateAddress() {
        return candidateAddress;
    }

    public void setCandidateAddress(String candidateAddress) {
        this.candidateAddress = candidateAddress;
    }

    public String getCandidatePhoneNumber() {
        return candidatePhoneNumber;
    }

    public void setCandidatePhoneNumber(String candidatePhoneNumber) {
        this.candidatePhoneNumber = candidatePhoneNumber;
    }

    public String getCandidateEmail() {
        return candidateEmail;
    }

    public void setCandidateEmail(String candidateEmail) {
        this.candidateEmail = candidateEmail;
    }

    public String getCandidateImage() {
        return candidateImage;
    }

    public void setCandidateImage(String candidateImage) {
        this.candidateImage = candidateImage;
    }

    public LocalDate getCandidateDob() {
        return candidateDob;
    }

    public void setCandidateDob(LocalDate candidateDob) {
        this.candidateDob = candidateDob;
    }

    public String getCandidateCvAttachment() {
        return candidateCvAttachment;
    }

    public void setCandidateCvAttachment(String candidateCvAttachment) {
        this.candidateCvAttachment = candidateCvAttachment;
    }

    public ECandidateStatus getCandidateStatus() {
        return candidateStatus;
    }

    public void setCandidateStatus(ECandidateStatus candidateStatus) {
        this.candidateStatus = candidateStatus;
    }

    public String getCandidateNote() {
        return candidateNote;
    }

    public void setCandidateNote(String candidateNote) {
        this.candidateNote = candidateNote;
    }

}
