package org.example.thithuchanh;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NationalDAO {

    public void insertNational(National national) throws SQLException {
        String sql = "INSERT INTO National (NationalName) VALUES (?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, national.getNationalName());
            stmt.executeUpdate();
        }
    }

    public void deleteNational(int nationalId) throws SQLException {
        String sql = "DELETE FROM National WHERE NationalId = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, nationalId);
            stmt.executeUpdate();
        }
    }

    public List<National> getAllNationals() throws SQLException {
        List<National> nationals = new ArrayList<>();
        String sql = "SELECT * FROM National";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                National national = new National(
                        rs.getInt("NationalId"),
                        rs.getString("NationalName")
                );
                nationals.add(national);
            }
        }
        return nationals;
    }
}
