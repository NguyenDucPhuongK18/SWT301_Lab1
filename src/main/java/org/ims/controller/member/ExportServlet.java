package org.ims.controller.member;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@WebServlet(name = "MemberExportServlet", urlPatterns = {"/member/export"})
public class ExportServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=InterviewManagement;encrypt=false";
        String username = "pi";
        String password = "pien";

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=\"Member.xlsx\"");

        String sql = "SELECT * FROM Member";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery();
             XSSFWorkbook workbook = new XSSFWorkbook()) {

            XSSFSheet spreadsheet = workbook.createSheet("Member");

            XSSFRow row;

            Map<String, Object[]> members = new TreeMap<>();

            members.put("0",
                    new Object[] {"full_name", "account", "password", "dob", "created_time", "address", "email",
                    "phone_number", "note", "status", "role"});
            while (resultSet.next()) {
                members.put(String.valueOf(resultSet.getInt("member_id")),
                        new Object[]{
                                resultSet.getString("member_full_name"),
                                resultSet.getString("member_account"),
                                resultSet.getString("member_password"),
                                resultSet.getString("member_dob"),
                                resultSet.getDate("member_created_time"),
                                resultSet.getString("member_address"),
                                resultSet.getString("member_email"),
                                resultSet.getString("member_phone_number"),
                                resultSet.getString("member_note"),
                                resultSet.getString("member_status"),
                                resultSet.getString("member_role")});
            }
            Set<String> keyid = members.keySet();
            int rowid = 0;

            for (String key : keyid) {

                row = spreadsheet.createRow(rowid++);
                Object[] objectArr = members.get(key);
                int cellid = 0;

                for (Object obj : objectArr) {
                    Cell cell = row.createCell(cellid++);
                    cell.setCellValue(String.valueOf(obj));
                }
            }

            OutputStream out = response.getOutputStream();

            workbook.write(out);
            out.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace(System.out);
        }
    }
}


