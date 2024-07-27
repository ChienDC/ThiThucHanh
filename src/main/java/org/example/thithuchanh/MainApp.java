package org.example.thithuchanh;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.SQLException;

public class MainApp extends Application {

    private PlayerDAO playerDAO = new PlayerDAO();
    private NationalDAO nationalDAO = new NationalDAO();

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);

        // Tạo bảng để hiển thị người chơi
        TableView<Player> table = new TableView<>();
        TableColumn<Player, Integer> idColumn = new TableColumn<>("Player Id");
        TableColumn<Player, String> nameColumn = new TableColumn<>("Player Name");
        TableColumn<Player, Integer> scoreColumn = new TableColumn<>("High Score");
        TableColumn<Player, Integer> levelColumn = new TableColumn<>("Level");
        TableColumn<Player, String> nationalColumn = new TableColumn<>("National");

        idColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getPlayerId()));
        nameColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getPlayerName()));
        scoreColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getHighScore()));
        levelColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getLevel()));
        nationalColumn.setCellValueFactory(cellData -> {
            try {
                National national = nationalDAO.getAllNationals().stream()
                        .filter(n -> n.getNationalId() == cellData.getValue().getNationalId())
                        .findFirst().orElse(new National(-1, "Unknown"));
                return new ReadOnlyObjectWrapper<>(national.getNationalName());
            } catch (SQLException e) {
                e.printStackTrace();
                return new ReadOnlyObjectWrapper<>("Error");
            }
        });

        table.getColumns().addAll(idColumn, nameColumn, scoreColumn, levelColumn, nationalColumn);

        // Các nút chức năng
        Button addPlayerButton = new Button("Add Player");
        Button removePlayerButton = new Button("Remove Player");
        Button showAllButton = new Button("Show All Players");
        Button findPlayerButton = new Button("Find Player");
        Button top10Button = new Button("Show Top 10 Players");

        // Bố cục giao diện
        VBox controls = new VBox(10, addPlayerButton, removePlayerButton, showAllButton, findPlayerButton, top10Button);
        root.setLeft(controls);
        root.setCenter(table);

        // Xử lý sự kiện
        addPlayerButton.setOnAction(e -> {
            // Code để thêm người chơi mới
        });

        removePlayerButton.setOnAction(e -> {
            // Code để xóa người chơi
        });

        showAllButton.setOnAction(e -> {
            try {
                table.getItems().setAll(playerDAO.displayAll());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        findPlayerButton.setOnAction(e -> {
            // Code để tìm người chơi theo tên
        });

        top10Button.setOnAction(e -> {
            try {
                table.getItems().setAll(playerDAO.displayTop10());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        primaryStage.setTitle("HeroGame Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
