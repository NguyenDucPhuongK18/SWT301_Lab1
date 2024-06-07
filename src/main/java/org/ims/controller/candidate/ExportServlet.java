package org.ims.controller.candidate;

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

@WebServlet(name = "CandidateExportServlet", urlPatterns = {"/candidate/export"})
public class ExportServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Retrieve database credentials from environment variables
        String url = System.getenv("DB_URL");
        String username = System.getenv("DB_USERNAME");
        String password = System.getenv("DB_PASSWORD");

        // Ensure the environment variables are set
        if (url == null || username == null || password == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Database credentials are not properly set in environment variables.");
            return;
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=\"Candidate.xlsx\"");

        String sql = "SELECT full_name, address, phone_number, email, dob, status, note FROM Candidate";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery();
             XSSFWorkbook workbook = new XSSFWorkbook()) {

            XSSFSheet spreadsheet = workbook.createSheet("Candidate");

            XSSFRow row;

            Map<String, Object[]> jobs = new TreeMap<>();

            jobs.put("0",
                    new Object[] {"full_name", "address", "phone_number", "email", "dob", "status", "note" });
            while (resultSet.next()) {
                jobs.put(String.valueOf(resultSet.getInt("candidate_id")),
                        new Object[]{
                                resultSet.getString("candidate_name"),
                                resultSet.getString("candidate_address"),
                                resultSet.getString("candidate_phone_number"),
                                resultSet.getString("candidate_email"),
                                resultSet.getDate("candidate_dob"),
                                resultSet.getString("candidate_status"),
                                resultSet.getString("candidate_note")});
            }
            Set<String> keyid = jobs.keySet();
            int rowid = 0;

            for (String key : keyid) {

                row = spreadsheet.createRow(rowid++);
                Object[] objectArr = jobs.get(key);
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

//Set the environment variables DB_URL, DB_USERNAME, and DB_PASSWORD in your deployment environment. For example:
//set DB_URL=jdbc:sqlserver://localhost:1433;databaseName=InterviewManagement;encrypt=false
//set DB_USERNAME=yourNewUsername
//set DB_PASSWORD=yourNewPassword

