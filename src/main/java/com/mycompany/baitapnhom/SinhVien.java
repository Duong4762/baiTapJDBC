
package com.mycompany.baitapnhom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SinhVien {
    private String maSv, hoTen, lop;
    private double gpa;

    public String getMaSv() {
        return maSv;
    }

    public void setMaSv(String maSv) {
        this.maSv = maSv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public SinhVien(String maSv, String hoTen, String lop, double gpa) {
        this.maSv = maSv;
        this.hoTen = hoTen;
        this.lop = lop;
        this.gpa = gpa;
    }
    
    
    
    public static List<SinhVien> layDanhSachSinhVien() throws SQLException{
        Connection connection = JDBC.getConnection();
        String sql = "SELECT * FROM sinh_vien";
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        List<SinhVien> list = new ArrayList<SinhVien>();
        while(resultSet.next()){
            String maSv = resultSet.getString("ma_sinh_vien");
            String hoTen = resultSet.getString("ho_ten");
            String lop = resultSet.getString("lop");
            double gpa = resultSet.getDouble("gpa");
            SinhVien sinhVien = new SinhVien(maSv, hoTen, lop, gpa);
            list.add(sinhVien);
        }
        resultSet.close();
        JDBC.closeConnection(connection);
        return list;
    }
    
    public static void themSinhVien(SinhVien sinhVien) throws SQLException{
        Connection connection = JDBC.getConnection();
        String query = "INSERT INTO sinh_vien VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, sinhVien.getMaSv());
        preparedStatement.setString(2, sinhVien.getHoTen());
        preparedStatement.setString(3, sinhVien.getLop());
        preparedStatement.setDouble(4, sinhVien.getGpa());
        preparedStatement.executeUpdate();
        JDBC.closeConnection(connection);
    }
    
    public static void suaSinhVien(SinhVien sinhVien) throws SQLException{
        Connection connection = JDBC.getConnection();
        String query = "UPDATE sinh_vien SET ho_ten = ?, lop = ?, gpa = ? WHERE ma_sinh_vien = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(4, sinhVien.getMaSv());
        preparedStatement.setString(1, sinhVien.getHoTen());
        preparedStatement.setString(2, sinhVien.getLop());
        preparedStatement.setDouble(3, sinhVien.getGpa());
        preparedStatement.executeUpdate();
        JDBC.closeConnection(connection);
    }
    
    public static void xoaSinhVien(String maSv) throws SQLException{
        Connection connection = JDBC.getConnection();
        String query = "DELETE FROM sinh_vien WHERE ma_sinh_vien = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, maSv);
        preparedStatement.executeUpdate();
        JDBC.closeConnection(connection);
    }
    
    public static void reset() throws SQLException{
        Connection connection = JDBC.getConnection();
        String sql = "TRUNCATE TABLE sinh_vien";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        JDBC.closeConnection(connection);
    }
}
