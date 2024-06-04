package org.ims.constant;

public interface ICandidateQuery {
    String GET_CANDIDATES = "SELECT\n"
            + "*\n"
            + "FROM Candidate\n"
            + "WHERE candidate_name LIKE ?";

    String GET_ONE_CANDIDATE = "SELECT\n"
            + "*\n"
            + "FROM Candidate\n"
            + "WHERE candidate_id = ?\n";

    String INSERT_A_NEW_CANDIDATE = "INSERT INTO Candidate\n"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)\n";

    String DELETE_A_CANDIDATE = "DELETE FROM Candidate\n"
            + "WHERE candidate_id = ?\n";

    String UPDATE_A_CANDIDATE = "UPDATE Candidate\n"
            + "SET candidate_name = ?,\n"
            + "    candidate_address = ?,\n"
            + "    candidate_phone_number = ?,\n"
            + "    candidate_email = ?,\n"
            + "    candidate_image = ?,\n"
            + "    candidate_dob = ?,\n"
            + "    candidate_cv_attachment = ?,\n"
            + "    candidate_status = ?,\n"
            + "    candidate_note = ?\n"
            + "WHERE candidate_id = ?\n";
}
