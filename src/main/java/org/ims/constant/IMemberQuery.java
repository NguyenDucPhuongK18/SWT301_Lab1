package org.ims.constant;

public interface IMemberQuery {
    String GET_MEMBERS = "SELECT\n"
            + "    *\n"
            + "FROM [Member]\n"
            + "WHERE member_full_name LIKE ?";

    String GET_ONE_MEMBER = "SELECT\n"
            + "    *\n"
            + "FROM [Member]\n"
            + "WHERE member_id = ?\n";

    String LOG_IN_CHECK = "SELECT\n"
            + "    *\n"
            + "FROM [Member]\n"
            + "WHERE member_account = ?\n"
            + "AND member_password = ?\n";

    String INSERT_A_NEW_MEMBER = "INSERT INTO [Member]\n"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)\n";

    String DELETE_A_MEMBER = "DELETE FROM [Member]\n"
            + "WHERE member_id = ?\n";

    String UPDATE_A_MEMBER = "UPDATE [Member]\n"
            + "SET member_full_name = ?,\n"
            + "    member_account = ?,\n"
            + "    member_password = ?,\n"
            + "    member_dob = ?,\n"
            + "    member_address = ?,\n"
            + "    member_email = ?,\n"
            + "    member_phone_number = ?,\n"
            + "    member_image = ?,\n"
            + "    member_status = ?,\n"
            + "    member_role = ?,\n"
            + "    member_note = ?\n"
            + "WHERE member_id = ?\n";
}
