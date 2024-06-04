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
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=InterviewManagement;encrypt=false";
        String username = "pi";
        String password = "pien";

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=\"Candidate.xlsx\"");

        String sql = "SELECT * FROM Candidate";

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

