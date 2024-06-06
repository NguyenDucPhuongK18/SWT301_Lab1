package org.ims.dao;

import org.ims.constant.EMemberRole;
import org.ims.constant.EMemberStatus;
import org.ims.constant.IMemberQuery;
import org.ims.entity.Member;
import org.ims.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemberDAO {
    public Member rowMapper(ResultSet rs) throws SQLException {

        String memberStatus = rs.getString("member_status");
        String memberRole = rs.getString("member_role");
        EMemberStatus enum_memberStatus = null;
        EMemberRole enum_memberRole = null;
        try {
            enum_memberStatus = EMemberStatus.convertFromString(memberStatus);
            enum_memberRole = EMemberRole.convertFromString(memberRole);
        } catch (IllegalArgumentException ignored) {
        }
        return new Member(
                rs.getInt("member_id"),
                rs.getString("member_full_name"),
                rs.getString("member_account"),
                rs.getString("member_password"),
                rs.getDate("member_dob").toLocalDate(),
                rs.getDate("member_created_time").toLocalDate(),
                rs.getString("member_address"),
                rs.getString("member_email"),
                rs.getString("member_phone_number"),
                rs.getString("member_image"),
                rs.getString("member_note"),
                enum_memberStatus,
                enum_memberRole
        );
    }

    public List<Member> getMembers(String memberFullName) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(IMemberQuery.GET_MEMBERS)) {
            ps.setString(1, memberFullName);
            ResultSet rs = ps.executeQuery();
            List<Member> memberList = new ArrayList<>();
            while (rs.next()) {
                memberList.add(rowMapper(rs));
            }
            return memberList;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return Collections.emptyList();
    }

    public Member getOneMember(String memberId) {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(IMemberQuery.GET_ONE_MEMBER)) {
            ps.setInt(1, Integer.parseInt(memberId));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rowMapper(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    public Member getOneMemberByMembernameAndPassword(String memberName, String memberPassword) {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(IMemberQuery.LOG_IN_CHECK)) {
            ps.setString(1, memberName);
            ps.setString(2, memberPassword);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rowMapper(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    public void insertANewMember(Member member) {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(IMemberQuery.INSERT_A_NEW_MEMBER)) {
            c.setAutoCommit(false);
            updateOrInsert(member, ps);
            ps.executeUpdate();
            c.commit();
            try {
                c.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void deleteAMember(String memberId){
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(IMemberQuery.DELETE_A_MEMBER)) {
            c.setAutoCommit(false);
            ps.setInt(1, Integer.parseInt(memberId));
            ps.executeUpdate();
            c.commit();
            try {
                c.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void updateAMember(String memberId, Member updatedOne) {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(IMemberQuery.UPDATE_A_MEMBER)) {
            Member found = getOneMember(memberId);
            if (found != null) {
                updateOrInsert(updatedOne, ps);
                ps.setInt(12, Integer.parseInt(memberId));
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateOrInsert(Member member, PreparedStatement ps) throws SQLException {
        ps.setString(1, member.getMemberFullName());
        ps.setString(2, member.getMemberAccount());
        ps.setString(3, member.getMemberPassword());
        ps.setDate(4, Date.valueOf(member.getMemberDob()));
        ps.setString(5, member.getMemberAddress());
        ps.setString(6, member.getMemberEmail());
        ps.setString(7, member.getMemberPhoneNumber());
        ps.setString(8, member.getMemberImage());
        ps.setString(9, member.getMemberStatus().getMemberStatus());
        ps.setString(10, member.getMemberRole().getMemberRole());
        ps.setString(11, member.getMemberNote());
    }
}
