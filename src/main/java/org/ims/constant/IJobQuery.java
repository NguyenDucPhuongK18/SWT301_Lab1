package org.ims.constant;

public interface IJobQuery {

    String GET_JOBS = "SELECT\n"
            + "*\n"
            + "FROM Job\n"
            + "WHERE job_title LIKE ?";

    String GET_ONE_JOB = "SELECT\n"
            + "    *\n"
            + "FROM Job\n"
            + "WHERE job_id = ?\n";

    String INSERT_A_NEW_JOB = "INSERT INTO Job\n"
            + "VALUES (?, ?, ?, ?, ?)\n";

    String DELETE_A_JOB = "DELETE FROM Job\n"
            + "WHERE job_id = ?\n";

    String UPDATE_A_JOB = "UPDATE Job\n"
            + "SET job_title = ?,\n"
            + "    job_description = ?,\n"
            + "    job_status = ?,\n"
            + "    job_salary_from = ?,\n"
            + "    job_salary_to = ?\n"
            + "WHERE job_id = ?\n";
}
