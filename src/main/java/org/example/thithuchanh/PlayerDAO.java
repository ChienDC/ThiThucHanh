package org.example.thithuchanh;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {

    public void insertPlayer(Player player) throws SQLException {
        String sql = "INSERT INTO Player (NationalId, PlayerName, HighScore, Level) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, player.getNationalId());
            stmt.setString(2, player.getPlayerName());
            stmt.setInt(3, player.getHighScore());
            stmt.setInt(4, player.getLevel());
            stmt.executeUpdate();
        }
    }

    public void deletePlayer(int playerId) throws SQLException {
        String sql = "DELETE FROM Player WHERE PlayerId = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, playerId);
            stmt.executeUpdate();
        }
    }

    public List<Player> displayAll() throws SQLException {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM Player";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Player player = new Player(
                        rs.getInt("PlayerId"),
                        rs.getString("PlayerName"),
                        rs.getInt("HighScore"),
                        rs.getInt("Level"),
                        rs.getInt("NationalId")
                );
                players.add(player);
            }
        }
        return players;
    }

    public List<Player> displayAllByPlayerName(String playerName) throws SQLException {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM Player WHERE PlayerName = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, playerName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Player player = new Player(
                            rs.getInt("PlayerId"),
                            rs.getString("PlayerName"),
                            rs.getInt("HighScore"),
                            rs.getInt("Level"),
                            rs.getInt("NationalId")
                    );
                    players.add(player);
                }
            }
        }
        return players;
    }

    public List<Player> displayTop10() throws SQLException {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT TOP 10 * FROM Player ORDER BY HighScore DESC";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Player player = new Player(
                        rs.getInt("PlayerId"),
                        rs.getString("PlayerName"),
                        rs.getInt("HighScore"),
                        rs.getInt("Level"),
                        rs.getInt("NationalId")
                );
                players.add(player);
            }
        }
        return players;
    }
}
