package org.ims.dao;

import org.ims.constant.ECandidateStatus;
import org.ims.constant.ICandidateQuery;
import org.ims.entity.Candidate;
import org.ims.util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CandidateDAO extends DatabaseConnection {

    public Candidate rowMapper(ResultSet rs) throws SQLException {
        Date sqlDate = rs.getDate("candidate_dob");
        LocalDate sqlDate_candidateDob = sqlDate.toLocalDate();
        String candidateStatus = rs.getString("candidate_status");
        ECandidateStatus enum_candidateStatus = ECandidateStatus.convertFromString(candidateStatus);
        return new Candidate(
                rs.getInt("candidate_id"),
                rs.getString("candidate_name"),
                rs.getString("candidate_address"),
                rs.getString("candidate_phone_number"),
                rs.getString("candidate_email"),
                rs.getString("candidate_image"),
                sqlDate_candidateDob,
                rs.getString("candidate_cv_attachment"),
                enum_candidateStatus,
                rs.getString("candidate_note"));
    }

    public List<Candidate> getCandidates(String candidateFullName) throws SQLException {
        List<Candidate> candidateList = new ArrayList<>();
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(ICandidateQuery.GET_CANDIDATES)) {
            ps.setString(1, candidateFullName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                candidateList.add(rowMapper(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return candidateList;
    }

    public Candidate getOneCandidate(String candidateId) throws SQLException {
        Candidate candidate = new Candidate();
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(ICandidateQuery.GET_ONE_CANDIDATE)) {
            ps.setInt(1, Integer.parseInt(candidateId));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                candidate = rowMapper(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return candidate;
    }

    public void insertANewCandidate(Candidate candidate) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(ICandidateQuery.INSERT_A_NEW_CANDIDATE)) {
            c.setAutoCommit(false);
            updateOrInsert(candidate, ps);
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

    public void deleteACandidate(String candidateId) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(ICandidateQuery.DELETE_A_CANDIDATE)) {
            c.setAutoCommit(false);
            ps.setInt(1, Integer.parseInt(candidateId));
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

    public void updateACandidate(String candidateId, Candidate updatedOne) {
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement ps = c.prepareStatement(ICandidateQuery.UPDATE_A_CANDIDATE)) {
            c.setAutoCommit(false);
            CandidateDAO cD = new CandidateDAO();
            Candidate found = cD.getOneCandidate(candidateId);
            if (found != null) {
                updateOrInsert(updatedOne, ps);
                ps.setInt(10, Integer.parseInt(candidateId));
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

    private void updateOrInsert(Candidate updatedOne, PreparedStatement ps) throws SQLException {
        ps.setString(1, updatedOne.getCandidateFullName());
        ps.setString(2, updatedOne.getCandidateAddress());
        ps.setString(3, updatedOne.getCandidatePhoneNumber());
        ps.setString(4, updatedOne.getCandidateEmail());
        ps.setString(5, updatedOne.getCandidateImage());
        ps.setDate(6, Date.valueOf(updatedOne.getCandidateDob()));
        ps.setString(7, updatedOne.getCandidateCvAttachment());
        ps.setString(8, updatedOne.getCandidateStatus().getCandidateStatus());
        ps.setString(9, updatedOne.getCandidateNote());
    }
}
