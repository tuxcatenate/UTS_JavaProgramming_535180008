package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main extends Application {
    Scene scene;
    Connection conn;
    //using preparedstatement to avoid SQL Injection
    PreparedStatement pst = null;
    //resultset will return the answer;
    ResultSet rs = null;
    //Stage back;

    //defining offsets

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //primaryStage.setTitle("Hello World");
        //primaryStage.setScene(new Scene(root, 600, 430));
        //primaryStage.show();

        //back = stage;
        stage.setTitle("Digital Library");
        stage.initStyle(StageStyle.UNDECORATED);

        AnchorPane anchor = new AnchorPane();
        anchor.setStyle("-fx-background-color: #fff8e1");
        anchor.setPrefHeight(426);
        anchor.setPrefWidth(600);
        //grabbing the root
        //event handler agar window dapat di drag and drop karena undecorated stage yang dipilih
        anchor.setOnMousePressed(mouseEvent -> {
            xOffset = mouseEvent.getSceneX();
            yOffset = mouseEvent.getSceneY();
        });
        anchor.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - xOffset);
            stage.setY(mouseEvent.getScreenY() - yOffset);
        });

        //let's decorate

        //menambahkan label awal
        Label label = new Label("E-Library");
        label.setLayoutX(96);
        label.setLayoutY(21);
        label.setPrefHeight(31);
        label.setPrefWidth(143);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        //menambalkan label untuk dibawahnya
        Label label2 = new Label("Digital Library - Window to Knowledge");
        label2.setLayoutX(97);
        label2.setLayoutY(51);
        //adding a picture seems nice
        FileInputStream inp = new FileInputStream("src/images/library.png");
        Image img = new Image(inp);
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(57);
        imgView.setFitWidth(75);
        imgView.setLayoutX(40);
        imgView.setLayoutY(15);
        imgView.setPreserveRatio(true);
        imgView.setPickOnBounds(true);

        //menambahkan aribut panel kedalam anchorpane
        Pane pane = new Pane();
        pane.setPrefWidth(Double.parseDouble("600"));
        pane.setPrefHeight(Double.parseDouble("38"));
        pane.setStyle("-fx-background-color: #ffcc80");
        pane.getChildren().addAll(imgView, label, label2);
        //pane bagian login sama objek objeknya
        Pane paneLogin = new Pane();
        paneLogin.setPrefWidth(552);
        paneLogin.setPrefHeight(287);
        paneLogin.setLayoutX(24);
        paneLogin.setLayoutY(78);
        Pane paneAwal = new Pane();
        paneAwal.setPrefHeight(57);
        paneAwal.setPrefWidth(552);
        paneAwal.setStyle("-fx-background-color : #ffcc80");
        Label label3 = new Label("Login");
        label3.setLayoutX(218);
        label3.setLayoutY(-1.0);
        label3.setPrefWidth(109);
        label3.setPrefHeight(57);
        label3.setTextFill(Color.web("#fffdfd"));
        label3.setStyle("-fx-font : 38 arial;");

        paneAwal.getChildren().add(label3);
        paneLogin.getChildren().addAll(paneAwal);
        //gridpane
        GridPane grid = new GridPane();
        grid.setLayoutX(165);
        grid.setLayoutY(180);
        grid.setMinWidth(Region.USE_COMPUTED_SIZE);
        grid.setMinHeight(Region.USE_COMPUTED_SIZE);
        grid.setMaxWidth(Region.USE_COMPUTED_SIZE);
        grid.setMaxHeight(Region.USE_COMPUTED_SIZE);
        grid.setPrefHeight(113);
        grid.setPrefWidth(191);
        grid.setVgap(40);
        grid.setHgap(20);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.SOMETIMES);
        col1.setMinWidth(10);
        col1.setMaxWidth(100);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.SOMETIMES);
        col2.setMinWidth(10);
        col2.setMaxWidth(100);
        col2.setHgrow(Priority.SOMETIMES);
        RowConstraints row1 = new RowConstraints();
        row1.setMinHeight(10);
        row1.setPrefHeight(30);
        row1.setVgrow(Priority.SOMETIMES);
        RowConstraints row2 = new RowConstraints();
        row2.setMaxHeight(38);
        row2.setMinHeight(10);
        row2.setPrefHeight(38);
        row2.setVgrow(Priority.SOMETIMES);
        RowConstraints row3 = new RowConstraints();
        row3.setMaxHeight(8);
        row3.setMinHeight(0);
        row3.setPrefHeight(0);
        row3.setVgrow(Priority.SOMETIMES);
        RowConstraints row4 = new RowConstraints();
        row4.setMaxHeight(8);
        row4.setMinHeight(10);
        row4.setPrefHeight(30);
        row4.setVgrow(Priority.SOMETIMES);
        grid.getColumnConstraints().addAll(col1, col2);
        grid.getRowConstraints().addAll(row1, row2, row3, row4);
        TextField txt1 = new TextField();
        txt1.setLayoutX(185);
        txt1.setLayoutY(100);
        txt1.setMaxHeight(Region.USE_PREF_SIZE);
        txt1.setMaxWidth(Region.USE_PREF_SIZE);
        txt1.setMinHeight(Region.USE_PREF_SIZE);
        txt1.setMinWidth(Region.USE_PREF_SIZE);
        txt1.setPrefHeight(40);
        txt1.setPrefWidth(196);
        txt1.setStyle("-fx-border-radius: 10");
        txt1.setStyle("-fx-background-radius: 10");
        txt1.setPadding(new Insets(10, 10, 10, 10));
        grid.setRowIndex(txt1, 1);
        grid.setColumnIndex(txt1, 1);
        Label label4 = new Label("User");
        label4.setFont(new Font("Arial", 28));
        label4.setMaxHeight(Region.USE_COMPUTED_SIZE);
        label4.setMaxWidth(Region.USE_COMPUTED_SIZE);
        label4.setMinHeight(Region.USE_COMPUTED_SIZE);
        label4.setMinWidth(Region.USE_COMPUTED_SIZE);
        label4.setOpaqueInsets(new Insets(10, 10, 10, 10));
        grid.setRowIndex(label4, 0);
        grid.setColumnIndex(label4, 0);
        TextField txt2 = new TextField();
        txt2.setMaxHeight(Region.USE_PREF_SIZE);
        txt2.setMaxWidth(Region.USE_PREF_SIZE);
        txt2.setMinHeight(Region.USE_PREF_SIZE);
        txt2.setMinWidth(Region.USE_PREF_SIZE);
        txt2.setPrefHeight(40);
        txt2.setPrefWidth(196);
        txt2.setStyle("-fx-border-radius: 10");
        txt2.setStyle("-fx-background-radius: 10");
        txt2.setPadding(new Insets(10, 10, 10, 10));
        grid.setRowIndex(txt2, 0);
        grid.setColumnIndex(txt2, 1);
        Label label5 = new Label();
        label5.setPrefHeight(51);
        label5.setPrefWidth(92);
        label5.setText("Pass");
        label5.setMaxHeight(Region.USE_COMPUTED_SIZE);
        label5.setMaxWidth(Region.USE_COMPUTED_SIZE);
        label5.setMinHeight(Region.USE_COMPUTED_SIZE);
        label5.setMinWidth(Region.USE_COMPUTED_SIZE);
        label5.setFont(new Font("Arial", 22));
        grid.setRowIndex(label5, 1);
        grid.setColumnIndex(label5, 0);
        FileInputStream input = new FileInputStream("src/images/login.png");
        Image img2 = new Image(input);
        ImageView iw = new ImageView(img2);
        Button btnLogin = new Button("Login", iw);
        //btnLogin.setMaxSize(100,200);
        grid.setRowIndex(btnLogin, 2);
        grid.setColumnIndex(btnLogin, 1);
        Pane paneBawah = new Pane();
        paneBawah.setLayoutY(376);
        paneBawah.setPrefWidth(600);
        paneBawah.setPrefHeight(51);
        paneBawah.setStyle("-fx-background-color: #ffffb1");
        Label loginstat = new Label();
        loginstat.setLayoutX(248.0);
        loginstat.setLayoutX(18.0);
        loginstat.setTextFill(Paint.valueOf("#d72121"));
        paneBawah.getChildren().add(loginstat);
        SqlConnection sql = new SqlConnection();
        //sql.setuptabelbuku();
        //sql.setuptabelmember();
        //sql.setuptabelpinjam();
        CheckConnection();


        //simple sql dimulai disini
        btnLogin.setOnAction(e -> {
            String query = "SELECT * FROM User WHERE username=? and password=? ";
                    try {
                        //prepared statement required here , you obviously hate sql injection vulnerability
                        //let's ditch that standard query passing
                        pst = conn.prepareStatement(query);
                        pst.setString(1, txt1.getText());
                        pst.setString(2,txt2.getText());
                        rs = pst.executeQuery();
                        if(rs.next()){
                            label3.setText("Login berhasil");
                            stage.close();
                            Form2 form = new Form2();
                            form.showForm2();
                        }else{
                            loginstat.setText("Login gagal");
                            return;
                        }

                    } catch (Exception ex) {
                        //ex.printStackTrace();
                        System.out.println(ex);
                    }
                    finally {
                        if (pst != null) {
                            try {
                                pst.close();
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                        if (rs != null) {
                            try {
                                rs.close();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                        if (conn != null) {
                            try {
                                conn.close();
                            }
                            catch(Exception e3){
                            e3.printStackTrace();
                        }
                    }
                    }
                }
                );

        grid.getChildren().addAll(txt1, label4, txt2, label5, btnLogin);
        anchor.getChildren().addAll(pane, paneLogin, grid, paneBawah);
        scene = new Scene(anchor, 600, 430);
        stage.setScene(scene);
        stage.show();

        //scene = new Scene()
    }



    //constructor untuk cek koneksi
    public void CheckConnection() throws FileNotFoundException, SQLException {
        //jika di uncomment maka akan error
        conn = SqlConnection.DbConnector();
        //if condition , tampilkan di jendela output bawah sebagai penanda status sql
        if(conn == null){
            System.out.println("Koneksi gagal");
            //kirim kode status dengan nilai 1 (langsung keluar)
            System.exit(1);
        }
        else{
            System.out.println("Koneksi Sql Sukses");
        }
    }



    public static void main(String[] args) {
        launch(args);
    }


}