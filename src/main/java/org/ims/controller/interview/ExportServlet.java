package org.ims.controller.interview;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ims.dao.CandidateDAO;
import org.ims.dao.InterviewDAO;
import org.ims.dao.MemberDAO;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@WebServlet(name = "InterviewExportServlet", urlPatterns = {"/interview/export"})
public class ExportServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=InterviewManagement;encrypt=false";
        String username = "pi";
        String password = "pien";

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=\"Interview.xlsx\"");

        String sql = "SELECT * FROM Interview";
        final String interviewID = "interview_id" ;
        try (Connection con = DriverManager.getConnection(url, username, password); XSSFWorkbook workbook = new XSSFWorkbook()) {
            try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
                XSSFSheet interview = workbook.createSheet("Interview");

                CandidateDAO cD = new CandidateDAO();

                XSSFRow row;

                Map<String, Object[]> jobs = new TreeMap<>();

                jobs.put("0", new Object[]{"title", "start_time", "end_time", "location", "status", "result", "note", "candidate"});
                while (rs.next()) {
                    jobs.put(String.valueOf(rs.getInt(interviewID)), new Object[]{rs.getString("interview_title"), rs.getDate("interview_start_time"), rs.getDate("interview_end_time"), rs.getString("interview_location"), rs.getString("interview_status"), rs.getString("interview_result"), rs.getString("interview_note"), cD.getOneCandidate(rs.getString("candidate_id")).getCandidateFullName()});
                }
                Set<String> keyid = jobs.keySet();
                int rowid = 0;

                for (String key : keyid) {

                    row = interview.createRow(rowid++);
                    Object[] objectArr = jobs.get(key);
                    int cellid = 0;

                    for (Object obj : objectArr) {
                        Cell cell = row.createCell(cellid++);
                        cell.setCellValue(String.valueOf(obj));
                    }
                }
            }

            sql = "SELECT * FROM Interviewer";

            try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
                XSSFSheet interviewer = workbook.createSheet("Interviewer");

                InterviewDAO iD = new InterviewDAO();
                MemberDAO mD = new MemberDAO();

                XSSFRow row;

                Map<String, Object[]> jobs = new TreeMap<>();

                int actualKey = 0;

                jobs.put("0", new Object[]{"interview_title", "interviewer"});
                while (rs.next()) {
                    jobs.put(String.valueOf(rs.getInt(interviewID) + actualKey), new Object[]{iD.getOneInterview(rs.getString(interviewID)).getInterviewTitle(), mD.getOneMember(rs.getString("member_id")).getMemberFullName()});
                    actualKey++;
                }
                Set<String> keyid = jobs.keySet();
                int rowid = 0;

                for (String key : keyid) {

                    row = interviewer.createRow(rowid++);
                    Object[] objectArr = jobs.get(key);
                    int cellid = 0;

                    for (Object obj : objectArr) {
                        Cell cell = row.createCell(cellid++);
                        cell.setCellValue(String.valueOf(obj));
                    }
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


