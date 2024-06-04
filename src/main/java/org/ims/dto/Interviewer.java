package org.ims.dto;

import org.ims.entity.Interview;
import org.ims.entity.Member;

public class Interviewer {

    public Member member;
    public Interview interview;

    public Interviewer() {
    }

    public Interviewer(Member member, Interview interview) {
        this.member = member;
        this.interview = interview;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Interview getInterview() {
        return interview;
    }

    public void setInterview(Interview interview) {
        this.interview = interview;
    }

}
