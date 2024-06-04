package org.ims.constant;

public interface IInterviewQuery {
    String GET_INTERVIEWS = "SELECT\n"
            + "*\n"
            + "FROM Interview\n"
            + "WHERE interview_title LIKE ?";

    String GET_ONE_INTERVIEW = "SELECT\n"
            + "    *\n"
            + "FROM Interview\n"
            + "WHERE interview_id = ?\n";

    String INSERT_A_NEW_INTERVIEW = "INSERT INTO Interview\n"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)\n";

    String DELETE_A_INTERVIEW = "DELETE FROM Interview\n"
            + "WHERE interview_id = ?\n";

    String UPDATE_A_INTERVIEW = "UPDATE Interview\n"
            + "SET interview_title = ?,\n"
            + "    interview_start_time = ?,\n"
            + "    interview_end_time = ?,\n"
            + "    interview_location = ?,\n"
            + "    interview_status = ?,\n"
            + "    interview_result = ?,\n"
            + "    interview_note = ?,\n"
            + "    candidate_id = ?,\n"
            + "    job_id = ?\n"
            + "WHERE interview_id = ?\n";
}
