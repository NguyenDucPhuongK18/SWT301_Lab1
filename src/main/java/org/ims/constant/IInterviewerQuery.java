package org.ims.constant;

public interface IInterviewerQuery {
    String GET_INTERVIEWS_AND_MEMBERS = "SELECT\n"
            + "*\n"
            + "FROM Interview iv\n"
            + "JOIN Interviewer ivr ON iv.interview_id = ivr.interview_id\n"
            + "JOIN [Member] m ON m.member_id = ivr.member_id\n";

    String INSERT_INTERVIEWERS_OF_AN_INTERVIEW = "INSERT INTO Interviewer\n"
            + "VALUES (?, ?)\n";
    
    String DELETE_INTERVIEWERS_OF_AN_INTERVIEW = "DELETE FROM Interviewer\n"
            + "WHERE interview_id = ?";
}
