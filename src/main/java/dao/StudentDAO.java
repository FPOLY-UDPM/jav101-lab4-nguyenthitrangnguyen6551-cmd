package dao;

import model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();
        //Câu lệnh sau tùy thuộc vào bảng dữ liệu trong database
        //Truy vấn database lấy id, masv, hoten
        //String sql = "SELECT id, masv, hoten FROM SINHVIEN";
        // HOẶC
        String sql = "SELECT id, masv,Ho + ' ' + TenLot + ' ' + Ten as hoten FROM SINHVIEN";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Student(
                        rs.getInt("id"),
                        rs.getString("masv"),
                        rs.getString("hoten")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public Student findById(int id) {
        String sql = "SELECT ID, MASV, Ho + '' +TenLot+ '' + Ten as hoten FROM SINHVIEN WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Student(
                        rs.getInt("id"),
                        rs.getString("masv"),
                        rs.getString("hoten")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insert(Student s) {
        String fullTrim = s.getHoten().trim();
        String ho = "";
        String tenLot = "";
        String ten = "";

        int firstSpace = fullTrim.indexOf(" ");
        int lastSpace = fullTrim.lastIndexOf(" ");

        if (firstSpace != -1) {
            ho = fullTrim.substring(0, firstSpace);
            ten = fullTrim.substring(lastSpace + 1);
            if (firstSpace != lastSpace) {
                tenLot = fullTrim.substring(firstSpace + 1, lastSpace);
            }
        } else {
            ten = fullTrim; // Nếu chỉ nhập mỗi tên, không có khoảng trắng
        }

        String sql = "INSERT INTO SINHVIEN(masv, Ho, TenLot, Ten) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getMasv());
            ps.setString(2, ho);
            ps.setString(3, tenLot);
            ps.setString(4, ten);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void update(Student s) {
        String fullTrim = s.getHoten().trim();
        String ho = "", tenLot = "", ten = "";
        int firstSpace = fullTrim.indexOf(" ");
        int lastSpace = fullTrim.lastIndexOf(" ");

        if (firstSpace != -1) {
            ho = fullTrim.substring(0, firstSpace);
            ten = fullTrim.substring(lastSpace + 1);
            if (firstSpace != lastSpace) {
                tenLot = fullTrim.substring(firstSpace + 1, lastSpace);
            }
        } else {
            ten = fullTrim;
        }

        String sql = "UPDATE SINHVIEN SET masv=?, Ho=?, TenLot=?, Ten=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getMasv());
            ps.setString(2, ho);
            ps.setString(3, tenLot);
            ps.setString(4, ten);
            ps.setInt(5, s.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void delete(int id) {
        String sql = "DELETE FROM SINHVIEN WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
