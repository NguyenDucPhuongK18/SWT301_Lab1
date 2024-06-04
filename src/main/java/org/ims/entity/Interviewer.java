package org.ims.entity;

public class Interviewer {

    private Interview interview;
    private Member user;

    public Interviewer() {
    }

    public Interviewer(Interview interview, Member user) {
        this.interview = interview;
        this.user = user;
    }

    public Interview getInterview() {
        return interview;
    }

    public void setInterview(Interview interview) {
        this.interview = interview;
    }

    public Member getUser() {
        return user;
    }

    public void setUser(Member user) {
        this.user = user;
    }

}
