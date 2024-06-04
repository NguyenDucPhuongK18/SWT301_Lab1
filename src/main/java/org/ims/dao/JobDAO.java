package org.ims.dao;

import org.ims.constant.EJobStatus;
import org.ims.constant.IJobQuery;
import org.ims.entity.Job;
import org.ims.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobDAO {

    public Job rowMapper(ResultSet rs) throws SQLException {
        String jobStatus = rs.getString("job_status");
        EJobStatus enum_jobStatus = null;
        try {
            enum_jobStatus = EJobStatus.convertFromString(jobStatus);
        } catch (IllegalArgumentException ignored) {
        }
        return new Job(
                rs.getInt("job_id"),
                rs.getString("job_title"),
                rs.getString("job_description"),
                enum_jobStatus,
                rs.getFloat("job_salary_from"),
                rs.getFloat("job_salary_to")
        );
    }

    public List<Job> getJobs(String jobTitle) throws SQLException {
        List<Job> jobList = new ArrayList<>();
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(IJobQuery.GET_JOBS)) {
            ps.setString(1, jobTitle);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                jobList.add(rowMapper(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return jobList;
    }

    public Job getOneJob(String jobId) {
        Job job = new Job();
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(IJobQuery.GET_ONE_JOB)) {
            ps.setInt(1, Integer.parseInt(jobId));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                job = rowMapper(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return job;
    }

    public void insertANewJob(Job job) {
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(IJobQuery.INSERT_A_NEW_JOB)) {
            c.setAutoCommit(false);
            updateOrInsert(job, ps);
            ps.executeUpdate();
            c.commit();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            try {
                if (DatabaseConnection.getConnection() != null) {
                    DatabaseConnection.getConnection().rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
    }

    public void deleteAJob(String jobId) {
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(IJobQuery.DELETE_A_JOB)) {
            c.setAutoCommit(false);
            ps.setInt(1, Integer.parseInt(jobId));
            ps.executeUpdate();
            c.commit();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            try {
                if (DatabaseConnection.getConnection() != null) {
                    DatabaseConnection.getConnection().rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
    }

    public void updateAJob(String jobId, Job updatedOne) {
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(IJobQuery.UPDATE_A_JOB)) {
            c.setAutoCommit(false);
            JobDAO jD = new JobDAO();
            Job found = jD.getOneJob(jobId);
            if (found != null) {
                updateOrInsert(updatedOne, ps);
                ps.setInt(6, Integer.parseInt(jobId));
                ps.executeUpdate();
            }
            c.commit();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            try {
                if (DatabaseConnection.getConnection() != null) {
                    DatabaseConnection.getConnection().rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
    }

    private void updateOrInsert(Job job, PreparedStatement ps) throws SQLException {
        ps.setString(1, job.getJobTitle());
        ps.setString(2, job.getJobDescription());
        ps.setString(3, job.getJobStatus().getJobStatus());
        ps.setFloat(4, job.getJobSalaryFrom());
        ps.setFloat(5, job.getJobSalaryTo());
    }
}
