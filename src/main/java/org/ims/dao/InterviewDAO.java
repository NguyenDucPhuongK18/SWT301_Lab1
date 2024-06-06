package org.ims.dao;

import org.ims.constant.EInterviewResult;
import org.ims.constant.EInterviewStatus;
import org.ims.constant.IInterviewQuery;
import org.ims.constant.IInterviewerQuery;
import org.ims.dto.Interviewer;
import org.ims.entity.Candidate;
import org.ims.entity.Interview;
import org.ims.entity.Job;
import org.ims.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class InterviewDAO {
    private final Logger logger = Logger.getLogger(InterviewDAO.class.getName());
    public Interview rowMapper(ResultSet rs) throws SQLException {
        CandidateDAO cD = new CandidateDAO();
        JobDAO jD = new JobDAO();
        Candidate foundC = cD.getOneCandidate(rs.getString("candidate_id"));
        Job foundJ = jD.getOneJob(rs.getString("job_id"));
        String interviewStatus = rs.getString("interview_status");
        String interviewResult = rs.getString("interview_result");
        EInterviewStatus enum_InterviewStatus = null;
        EInterviewResult enum_InterviewResult = null;
        try {
            enum_InterviewStatus = EInterviewStatus.convertFromString(interviewStatus);
            enum_InterviewResult = EInterviewResult.convertFromString(interviewResult);
        } catch (IllegalArgumentException ignored) {
            logger.warning("Invalid interview status or result found in the database.");
        }
        return new Interview(
                rs.getInt("interview_id"),
                rs.getString("interview_title"),
                rs.getDate("interview_start_time").toLocalDate(),
                rs.getDate("interview_end_time").toLocalDate(),
                rs.getString("interview_location"),
                enum_InterviewStatus,
                enum_InterviewResult,
                rs.getString("interview_note"),
                foundC,
                foundJ
        );
    }

    public List<Interview> getInterviews(String interviewTitle) throws SQLException {
        List<Interview> interviewList = new ArrayList<>();
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(IInterviewQuery.GET_INTERVIEWS)) {
            ps.setString(1, interviewTitle);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                interviewList.add(rowMapper(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return interviewList;
    }

    public Interview getOneInterview(String interviewId) {
        Interview interview = new Interview();
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(IInterviewQuery.GET_ONE_INTERVIEW)) {
            ps.setInt(1, Integer.parseInt(interviewId));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                interview = rowMapper(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return interview;
    }

    public List<Interviewer> getInterviewersOfAnInterview() {
        List<Interviewer> interviewerList = new ArrayList<>();
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(IInterviewerQuery.GET_INTERVIEWS_AND_MEMBERS); ResultSet rs = ps.executeQuery()) {
            InterviewDAO iD = new InterviewDAO();
            MemberDAO mD = new MemberDAO();
            while (rs.next()) {
                interviewerList.add(new Interviewer(
                        mD.getOneMember(String.valueOf(rs.getInt("member_id"))),
                        iD.getOneInterview(String.valueOf(rs.getInt("interview_id")))
                ));
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return interviewerList;
    }

    public boolean insertInterviewersOfAnInterview(String interviewId, String[] interviewers) {
        int[] check = new int[interviewers.length];
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(IInterviewerQuery.INSERT_INTERVIEWERS_OF_AN_INTERVIEW)) {
            c.setAutoCommit(false);
            for (String i : interviewers) {
                ps.setInt(1, Integer.parseInt(interviewId));
                ps.setInt(2, Integer.parseInt(i));
                ps.addBatch();
            }
            check = ps.executeBatch();
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
        return check.length > 0;
    }

    public int insertANewInterview(Interview interview) {
        int insertedId = 0;
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(IInterviewQuery.INSERT_A_NEW_INTERVIEW, Statement.RETURN_GENERATED_KEYS)) {
            c.setAutoCommit(false);
            updateOrInsert(interview, ps);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    insertedId = rs.getInt(1);
                }
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
        return insertedId;
    }

    public void deleteAInterview(String InterviewId) {
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(IInterviewQuery.DELETE_A_INTERVIEW)) {
            c.setAutoCommit(false);
            ps.setInt(1, Integer.parseInt(InterviewId));
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

    public void updateAInterview(String InterviewId, Interview updatedOne) {
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(IInterviewQuery.UPDATE_A_INTERVIEW)) {
            c.setAutoCommit(false);
            InterviewDAO jD = new InterviewDAO();
            Interview found = jD.getOneInterview(InterviewId);
            if (found != null) {
                updateOrInsert(updatedOne, ps);
                ps.setInt(10, Integer.parseInt(InterviewId));
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

    public void updateInterviewers(String interviewId, String[] interviewers) {
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(IInterviewerQuery.DELETE_INTERVIEWERS_OF_AN_INTERVIEW)) {
            InterviewDAO jD = new InterviewDAO();
            Interview found = jD.getOneInterview(interviewId);
            c.setAutoCommit(false);
            if (found != null) {
                ps.setInt(1, Integer.parseInt(interviewId));
                ps.executeUpdate();
            }
            boolean isExecuted = insertInterviewersOfAnInterview(interviewId, interviewers);
            if (!isExecuted) {
                c.rollback();
                return;
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

    private void updateOrInsert(Interview interview, PreparedStatement ps) throws SQLException {
        ps.setString(1, interview.getInterviewTitle());
        ps.setDate(2, Date.valueOf(interview.getInterviewStartTime()));
        ps.setDate(3, Date.valueOf(interview.getInterviewEndTime()));
        ps.setString(4, interview.getInterviewLocation());
        ps.setString(5, interview.getInterviewStatus().interviewStatus);
        ps.setString(6, interview.getInterviewResult().interviewResult);
        ps.setString(7, interview.getInterviewNote());
        ps.setInt(8, interview.getCandidate().getCandidateId());
        ps.setInt(9, interview.getJob().getJobId());
    }
}
