package org.ims.entity;

import org.ims.constant.EJobStatus;

public class Job {

    private int jobId;
    private String jobTitle;
    private String jobDescription;
    private EJobStatus jobStatus;
    private float jobSalaryFrom;
    private float jobSalaryTo;

    public Job() {
    }

    public Job(int jobId, String jobTitle, String jobDescription, EJobStatus jobStatus,
            float jobSalaryFrom, float jobSalaryTo) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobStatus = jobStatus;
        this.jobSalaryFrom = jobSalaryFrom;
        this.jobSalaryTo = jobSalaryTo;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public EJobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(EJobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public float getJobSalaryFrom() {
        return jobSalaryFrom;
    }

    public void setJobSalaryFrom(float jobSalaryFrom) {
        this.jobSalaryFrom = jobSalaryFrom;
    }

    public float getJobSalaryTo() {
        return jobSalaryTo;
    }

    public void setJobSalaryTo(float jobSalaryTo) {
        this.jobSalaryTo = jobSalaryTo;
    }

}
