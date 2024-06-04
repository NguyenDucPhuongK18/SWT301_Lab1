package org.ims.entity;

import org.ims.constant.EInterviewResult;
import org.ims.constant.EInterviewStatus;

import java.time.LocalDate;

public class Interview {

    private int interviewId;
    private String interviewTitle;
    private LocalDate interviewStartTime;
    private LocalDate interviewEndTime;
    private String interviewLocation;
    private EInterviewStatus interviewStatus;
    private EInterviewResult interviewResult;
    private String interviewNote;
    private Candidate candidate;
    private Job job;

    public Interview() {
    }

    public Interview(int interviewId, String interviewTitle, LocalDate interviewStartTime,
            LocalDate interviewEndTime, String interviewLocation, EInterviewStatus interviewStatus,
            EInterviewResult interviewResult, String interviewNote, Candidate candidate, Job job) {
        this.interviewId = interviewId;
        this.interviewTitle = interviewTitle;
        this.interviewStartTime = interviewStartTime;
        this.interviewEndTime = interviewEndTime;
        this.interviewLocation = interviewLocation;
        this.interviewStatus = interviewStatus;
        this.interviewResult = interviewResult;
        this.interviewNote = interviewNote;
        this.candidate = candidate;
        this.job = job;
    }

    public int getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
    }

    public String getInterviewTitle() {
        return interviewTitle;
    }

    public void setInterviewTitle(String interviewTitle) {
        this.interviewTitle = interviewTitle;
    }

    public LocalDate getInterviewStartTime() {
        return interviewStartTime;
    }

    public void setInterviewStartTime(LocalDate interviewStartTime) {
        this.interviewStartTime = interviewStartTime;
    }

    public LocalDate getInterviewEndTime() {
        return interviewEndTime;
    }

    public void setInterviewEndTime(LocalDate interviewEndTime) {
        this.interviewEndTime = interviewEndTime;
    }

    public String getInterviewLocation() {
        return interviewLocation;
    }

    public void setInterviewLocation(String interviewLocation) {
        this.interviewLocation = interviewLocation;
    }

    public EInterviewStatus getInterviewStatus() {
        return interviewStatus;
    }

    public void setInterviewStatus(EInterviewStatus interviewStatus) {
        this.interviewStatus = interviewStatus;
    }

    public EInterviewResult getInterviewResult() {
        return interviewResult;
    }

    public void setInterviewResult(EInterviewResult interviewResult) {
        this.interviewResult = interviewResult;
    }

    public String getInterviewNote() {
        return interviewNote;
    }

    public void setInterviewNote(String interviewNote) {
        this.interviewNote = interviewNote;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
