package org.ims.controller.job;

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

@WebServlet(name = "JobExportServlet", urlPatterns = {"/job/export"})
public class ExportServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=InterviewManagement;encrypt=false";
        String username = "pi";
        String password = "pien";

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=\"Job.xlsx\"");

        String sql = "SELECT * FROM Job";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery();
             XSSFWorkbook workbook = new XSSFWorkbook()) {

            XSSFSheet spreadsheet = workbook.createSheet("Job");

            XSSFRow row;

            Map<String, Object[]> jobs = new TreeMap<>();

            jobs.put("0",
                    new Object[] {"title", "description", "status", "from", "to" });
            while (resultSet.next()) {
                jobs.put(String.valueOf(resultSet.getInt("job_id")),
                        new Object[]{
                                resultSet.getString("job_title"),
                                resultSet.getString("job_description"),
                                resultSet.getString("job_status"),
                                resultSet.getInt("job_salary_from"),
                                resultSet.getInt("job_salary_to")});
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
