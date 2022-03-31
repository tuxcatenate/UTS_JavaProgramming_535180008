package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Optional;

public class Form2{

    private double xOffset = 0;
    private double yOffset = 0;
    int clicked;
    Scene scene;
    Pane pndsh = new Pane();
    Stage stage = new Stage();
    AnchorPane pane = new AnchorPane();
    Connection conn, conn2,conn3;
    TableView<BookDb> tabel = new TableView<>();
    TableView<MemberDb> tabel2 = new TableView<>();
    final ObservableList<BookDb> data = FXCollections.observableArrayList();
    final ObservableList<MemberDb> data2 = FXCollections.observableArrayList();
    ObservableList<String> datakembali = FXCollections.observableArrayList();
    ListView<String> datakembaliList = new ListView<>(datakembali);
    String namamember;
    String namabook;
    String bukuid;
    //Pane Mainform = new Pane();


    public void showForm2() throws Exception {

        //pelengkap dandanan secondary stage
        stage.initStyle(StageStyle.UNDECORATED);

        pane.setOnMousePressed(mouseEvent -> {
            xOffset = mouseEvent.getSceneX();
            yOffset = mouseEvent.getSceneY();
        });
        pane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - xOffset);
            stage.setY(mouseEvent.getScreenY() - yOffset);
        });


        //TABEL



        //membuat panel yang diperlukan untuk form 2
        pane.setPrefHeight(250.0);
        pane.setPrefWidth(300.0);
        pane.setMaxWidth(Region.USE_PREF_SIZE);
        pane.setMaxHeight(Region.USE_PREF_SIZE);
        pane.setMinWidth(Region.USE_PREF_SIZE);
        pane.setMinHeight(Region.USE_PREF_SIZE);
        //panel bagian atas
        Pane panel = new Pane();
        panel.setPrefHeight(36.0);
        panel.setPrefWidth(801.0);
        panel.setStyle("-fx-background-color: #bdbdbd");
        panel.setMaxWidth(Region.USE_COMPUTED_SIZE);
        panel.setMaxHeight(Region.USE_PREF_SIZE);
        panel.setMinWidth(Region.USE_COMPUTED_SIZE);
        panel.setMinHeight(Region.USE_COMPUTED_SIZE);
        //let's put some images to a dull panel
        //imageview kedua
        Label lbdigital = new Label("Digital Library");
        lbdigital.setLayoutX(358.0);
        lbdigital.setLayoutY(10.0);
        lbdigital.setPrefHeight(Region.USE_COMPUTED_SIZE);
        lbdigital.setPrefWidth(Region.USE_COMPUTED_SIZE);
        lbdigital.setMinHeight(Region.USE_COMPUTED_SIZE);
        lbdigital.setMinWidth(Region.USE_COMPUTED_SIZE);
        lbdigital.setMaxHeight(Region.USE_COMPUTED_SIZE);
        lbdigital.setMaxWidth(Region.USE_COMPUTED_SIZE);
        FileInputStream file = new FileInputStream("src/images/minimizeimg.png");
        Image imgpane1 = new Image(file);
        ImageView img1 = new ImageView();
        img1 = new ImageView(imgpane1);
        img1.setFitHeight(20.0);
        img1.setFitWidth(30.0);
        //img1.setLayoutX(722.0);
        //img1.setLayoutY(4.0);
        img1.setPickOnBounds(true);
        img1.setPreserveRatio(true);
        Button btnmnz = new Button("", img1);
        btnmnz.setLayoutX(712.0);
        btnmnz.setLayoutY(2.0);
        btnmnz.setPrefHeight(27.0);
        btnmnz.setPrefWidth(32.0);
        btnmnz.setStyle("-fx-background-color: #bdbdbd");
        btnmnz.setOnAction(e -> {
            ((Stage) ((Button) e.getSource()).getScene().getWindow()).setIconified(true);
        });
        FileInputStream file2 = new FileInputStream("src/images/shutdown.png");
        Image imgpanel2 = new Image(file2);
        ImageView img2 = new ImageView();
        img2 = new ImageView(imgpanel2);
        img2.setFitHeight(27.0);
        img2.setFitWidth(27.0);
        //img2.setLayoutX(747.0);
        //img2.setLayoutY(-2.0);
        img2.setPickOnBounds(true);
        img2.setPreserveRatio(true);
        Button btnshutdown = new Button("", img2);
        btnshutdown.setLayoutX(747.0);
        btnshutdown.setLayoutY(-2.0);
        btnshutdown.setPrefHeight(12.0);
        btnshutdown.setPrefWidth(16.0);
        btnshutdown.setStyle("-fx-background-color: #bdbdbd");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Anda yakin ingin keluar dari program ini?");
        btnshutdown.setOnAction(e -> {
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                stage.close();

            }
        });
        panel.getChildren().addAll(lbdigital, btnmnz, btnshutdown);

        //VBOX HERE

        VBox vbox = new VBox();
        vbox.setLayoutY(36.0);
        vbox.setPrefHeight(507.0);
        vbox.setPrefWidth(190.0);
        vbox.setStyle("-fx-background-color: #9d9e9d");
        Pane panel2 = new Pane();
        //PANEL
        panel2.setPrefHeight(108.0);
        panel2.setPrefWidth(190.0);
        panel2.setStyle("-fx-background-color: #9d9e9d");
        Label admpanel = new Label("Admin Panel");
        admpanel.setLayoutY(46.0);
        admpanel.setLayoutX(90.0);
        FileInputStream fileadm = new FileInputStream("src/images/bookshelf.png");
        Image imgadm = new Image(fileadm);
        ImageView admimage = new ImageView(imgadm);
        admimage.setFitHeight(76.0);
        admimage.setFitWidth(78.0);
        admimage.setLayoutX(12.0);
        admimage.setLayoutY(16.0);
        admimage.setPickOnBounds(true);
        admimage.setPreserveRatio(true);
        panel2.getChildren().addAll(admimage, admpanel);
        FileInputStream filedboard = new FileInputStream("src/images/homeicon.png");
        Image imgboard = new Image(filedboard);
        ImageView imgdboard = new ImageView(imgboard);
        imgdboard.setFitHeight(35.0);
        imgdboard.setFitWidth(34.0);
        imgdboard.setPickOnBounds(true);
        imgdboard.setPreserveRatio(true);
        Button btndboard = new Button("Beranda", imgdboard);
        btndboard.setAlignment(Pos.BASELINE_LEFT);
        btndboard.setGraphicTextGap(10.0);
        btndboard.setMnemonicParsing(false);
        btndboard.setPrefHeight(47);
        btndboard.setLayoutX(10.0);
        btndboard.setLayoutY(118.0);
        btndboard.setPrefWidth(190.0);
        btndboard.getStylesheets().add("sample/style.css");
        btndboard.setPadding(new Insets(0, 0, 0, 30.0));
        FileInputStream filedboard2 = new FileInputStream("src/images/memberadd.png");
        Image imgboard2 = new Image(filedboard2);
        ImageView imgdboard2 = new ImageView(imgboard2);
        imgdboard2.setFitHeight(35.0);
        imgdboard2.setFitWidth(34.0);
        imgdboard2.setPickOnBounds(true);
        imgdboard2.setPreserveRatio(true);
        Button btndboard2 = new Button("Add member", imgdboard2);
        btndboard2.setAlignment(Pos.BASELINE_LEFT);
        btndboard2.setGraphicTextGap(10.0);
        btndboard2.setMnemonicParsing(false);
        btndboard2.setPrefHeight(47.0);
        btndboard2.setPrefWidth(190.0);
        btndboard2.getStylesheets().add("sample/style.css");
        btndboard2.setPadding(new Insets(0, 0, 0, 30.0));
        btndboard2.setOnAction(e -> {
            try {
                showformmember();
            } catch (FileNotFoundException | SQLException ex) {
                ex.printStackTrace();
            }
        });
        FileInputStream filedboard3 = new FileInputStream("src/images/member.png");
        Image imgboard3 = new Image(filedboard3);
        ImageView imgdboard3 = new ImageView(imgboard3);
        imgdboard3.setFitHeight(35.0);
        imgdboard3.setFitWidth(35.0);
        imgdboard3.setPickOnBounds(true);
        imgdboard3.setPreserveRatio(true);
        Button btndboard3 = new Button("Detail Member", imgdboard3);
        btndboard3.setAlignment(Pos.BASELINE_LEFT);
        btndboard3.setGraphicTextGap(10.0);
        btndboard3.setMnemonicParsing(false);
        btndboard3.setPrefHeight(47.0);
        btndboard3.setLayoutY(10.0);
        btndboard3.setLayoutX(10.0);
        btndboard3.setPrefWidth(190.0);
        btndboard3.getStylesheets().add("sample/style.css");
        btndboard3.setPadding(new Insets(0, 0, 0, 30.0));
        btndboard3.setOnAction(e -> {
            try{
                setupMember();
                PreparedStatement prst2 = null;
                ResultSet rset;
                String ambilkankueri = "SELECT * FROM member";
                conn = SqlConnection.DbConnector();
                prst2 = conn.prepareStatement(ambilkankueri);
                rset = prst2.executeQuery();

                while(rset.next()){
                    data2.add(new MemberDb(
                        rset.getString("id"),
                        rset.getString("nama"),
                        rset.getString("jenismember")
                ));
                    tabel2.setItems(data2);

                }
                conn.close();
                prst2.close();
                rset.close();


            }
            catch(Exception e2){
                e2.printStackTrace();
            }


        });
        FileInputStream filedboard4 = new FileInputStream("src/images/book.png");
        Image imgboard4 = new Image(filedboard4);
        ImageView imgdboard4 = new ImageView(imgboard4);
        imgdboard4.setFitHeight(35.0);
        imgdboard4.setFitWidth(35.0);
        imgdboard4.setPickOnBounds(true);
        imgdboard4.setPreserveRatio(true);
        Button btndboard4 = new Button("Tambah Buku", imgdboard4);
        btndboard4.setAlignment(Pos.BASELINE_LEFT);
        btndboard4.setGraphicTextGap(10.0);
        btndboard4.setMnemonicParsing(false);
        btndboard4.setPrefHeight(47.0);
        btndboard4.setLayoutY(10.0);
        btndboard4.setLayoutX(57.0);
        btndboard4.setPrefWidth(190.0);
        btndboard4.setPadding(new Insets(0, 0, 0, 30.0));
        btndboard4.getStylesheets().add("sample/style.css");
        btndboard4.setOnAction(e -> {
            try {
                showform();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        FileInputStream filedboard5 = new FileInputStream("src/images/list.png");
        Image imgboard5 = new Image(filedboard5);
        ImageView imgdboard5 = new ImageView(imgboard5);
        imgdboard5.setFitHeight(35.0);
        imgdboard5.setFitWidth(39.0);
        imgdboard5.setPickOnBounds(true);
        imgdboard5.setPreserveRatio(true);
        Button btndboard5 = new Button("Detail Buku", imgdboard5);
        btndboard5.setAlignment(Pos.BASELINE_LEFT);
        btndboard5.setGraphicTextGap(10.0);
        btndboard5.setMnemonicParsing(false);
        btndboard5.setPrefHeight(47.0);
        btndboard5.setLayoutY(10.0);
        btndboard5.setLayoutX(104.0);
        btndboard5.setPrefWidth(190.0);
        btndboard5.setPadding(new Insets(0, 0, 0, 30.0));
        btndboard5.getStylesheets().add("sample/style.css");
        btndboard5.setOnAction(e -> {
            try{
                setupBuku();
                PreparedStatement prst = null;
                ResultSet rst2;
                String ambilkueri = "SELECT * FROM buku";
                conn = SqlConnection.DbConnector();
                prst = conn.prepareStatement(ambilkueri);
                rst2 = prst.executeQuery();

                while(rst2.next()){
                    data.add(new BookDb(
                            rst2.getString("id"),
                            rst2.getString("nama"),
                            rst2.getString("nama_pengarang"),
                            rst2.getString("nama_penerbit"),
                            rst2.getInt("tahun_terbit"),
                            rst2.getString("apakahtersedia")

                    ));
                    tabel.setItems(data);
                }
                conn.close();
                prst.close();
                rst2.close();
            }catch(Exception e2){
                e2.printStackTrace();
            }

        });

        FileInputStream filedboard6 = new FileInputStream("src/images/checkbox.png");
        Image imgboard6 = new Image(filedboard6);
        ImageView imgdboard6 = new ImageView(imgboard6);
        imgdboard6.setFitHeight(33.0);
        imgdboard6.setFitWidth(34.0);
        imgdboard6.setPickOnBounds(true);
        imgdboard6.setPreserveRatio(true);
        Button btndboard6 = new Button("Pengembalian Buku", imgdboard6);
        btndboard6.setAlignment(Pos.BASELINE_LEFT);
        btndboard6.setGraphicTextGap(10.0);
        btndboard6.setMnemonicParsing(false);
        btndboard6.setPrefHeight(47.0);
        btndboard6.setLayoutY(10.0);
        btndboard6.setLayoutX(151.0);
        btndboard6.setPrefWidth(190.0);
        btndboard6.setPadding(new Insets(0, 0, 0, 30.0));
        btndboard6.setOnAction(e -> {
            //kembali();
            //pengembalian();
            returnbk();
        });
        btndboard6.getStylesheets().add("sample/style.css");

        FileInputStream filedboard7 = new FileInputStream("src/images/book.png");
        Image imgboard7 = new Image(filedboard7);
        ImageView imgdboard7 = new ImageView(imgboard7);
        //imgdboard7.setFitHeight(33.0);
        //imgdboard7.setFitWidth(34.0);
        //imgdboard7.setPickOnBounds(true);
        //imgdboard7.setPreserveRatio(true);
        Button btndboard7 = new Button("Pinjaman Buku", imgdboard7);
        btndboard7.setAlignment(Pos.BASELINE_LEFT);
        btndboard7.setGraphicTextGap(10.0);
        btndboard7.setMnemonicParsing(false);
        btndboard7.setPrefHeight(47.0);
        //btndboard7.setLayoutY(10.0);
        //btndboard7.setLayoutX(151.0);
        btndboard7.setPrefWidth(190.0);
        btndboard7.setPadding(new Insets(0, 0, 0, 30.0));
        pndsh.setLayoutX(190.0);
        pndsh.setLayoutY(36.0);
        pndsh.setPrefHeight(507.0);
        pndsh.setPrefWidth(612.0);

        btndboard7.setOnAction(e -> {
            try {

                if(clicked == 1){
                    return;
                }
                clicked = 1;
                //kembali();
                BorderPane bpdsh = new BorderPane();
                bpdsh.setLayoutX(14.0);
                bpdsh.setLayoutY(44.0);
                bpdsh.setPrefHeight(30.0);
                bpdsh.setPrefWidth(580.0);
                HBox hb = new HBox();
                hb.setLayoutX(17);
                hb.setLayoutY(102);
                hb.setPrefWidth(204.0);
                hb.setPrefHeight(139.0);
                hb.setAlignment(Pos.CENTER);
                Label bookname = new Label();
                bookname.setText("Nama buku : ");
                bookname.setPadding(new Insets(10,10,10,10));
                Label author = new Label();
                author.setText("Nama Pengarang :");
                author.setPadding(new Insets(10,10,10,10));
                Label ketersediaan = new Label("Ketersediaan");
                ketersediaan.setPadding(new Insets(15,10,10,10));
                TextField inpdetail1 = new TextField();
                inpdetail1.setPromptText("Kode Buku");

                inpdetail1.setOnAction(err -> {
                    try{
                    conn = SqlConnection.DbConnector();
                    PreparedStatement prst3 = null;
                    ResultSet rst3;
                    Boolean flag = false;
                    String id = inpdetail1.getText();
                    String kueri = "SELECT * FROM buku WHERE id = '"+ id +"'";
                    prst3 = conn.prepareStatement(kueri);
                    rst3 = prst3.executeQuery();
                        try{
                            while(rst3.next()){
                                namabook = rst3.getString("nama");
                                String bookauthor =  rst3.getString("nama_pengarang");
                                String bookstatus = rst3.getString("apakahtersedia");
                                flag = true;

                                bookname.setText(namabook);
                                author.setText(bookauthor);
                                String status;
                                System.out.println(bookstatus);
                                if(bookstatus.equals("true")){
                                    ketersediaan.setText("tersedia");
                                }
                                else{
                                    ketersediaan.setText("tidak tersedia");
                                }

                            }
                            if(!flag){
                                bookname.setText("Tidak ada buku tersedia");
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        } finally {
                            try {
                                conn.close();
                                rst3.close();
                                prst3.close();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                    }} catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    //Boolean flag = false;
                    /*
                    try{
                        while(rst3.next()){
                            namabook = rst3.getString("nama");
                            String bookauthor =  rst3.getString("nama_pengarang");
                            String bookstatus = rst3.getString("apakahtersedia");
                            flag = true;

                            bookname.setText(namabook);
                            author.setText(bookauthor);
                            String status;
                            System.out.println(bookstatus);
                            if(bookstatus.equals("true")){
                                ketersediaan.setText("tersedia");
                            }
                            else{
                                ketersediaan.setText("tidak tersedia");
                            }

                        }
                        if(!flag){
                            bookname.setText("Tidak ada buku tersedia");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    finally {
                        try {
                            rstt.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }


                    } */

                });
                hb.getChildren().add(inpdetail1);
                VBox output = new VBox();
                output.setLayoutX(240.0);
                output.setLayoutY(120.0);
                output.setPrefWidth(246.0);
                output.setPrefHeight(139.0);
                output.getChildren().addAll(bookname,author,ketersediaan);
                HBox hb2 = new HBox();
                hb2.setLayoutY(242.0);
                hb2.setLayoutX(22.0);
                hb2.setAlignment(Pos.CENTER);
                hb2.setPrefHeight(139.0);
                hb2.setPrefWidth(204.0);
                Label membername = new Label();
                membername.setText("Nama Member");
                membername.setPadding(new Insets(10,10,10,10));
                Label jenis = new Label();
                jenis.setText("Jenis Member :  ");
                jenis.setPadding(new Insets(10,10,10,10));
                Label idmember = new Label("id");
                idmember.setPadding(new Insets(10,10,10,10));
                TextField inpdetail2 = new TextField();
                inpdetail2.setPromptText("input id Member");
                inpdetail2.setLayoutY(250.0);
                inpdetail2.setLayoutX(39.0);
                inpdetail2.setOnAction(err -> {
                    try{
                        conn = SqlConnection.DbConnector();
                        PreparedStatement prst4 = null;
                        ResultSet rst4;
                    String id = inpdetail2.getText();
                    String kueri = "SELECT * FROM member WHERE id = '"+ id +"'";
                    //ResultSet rstt = cnn2.execQuery(kueri);
                    Boolean flag = false;
                        prst4 = conn.prepareStatement(kueri);
                        rst4 = prst4.executeQuery();
                        try{
                        while(rst4.next()){
                            namamember = rst4.getString("nama");
                            String idmember2 =  rst4.getString("id");
                            String jenismb = rst4.getString("jenismember");
                            //String bookstatus = rstt.getString("apakahtersedia");
                            flag = true;

                            membername.setText(namamember);
                            jenis.setText(String.valueOf(jenismb));
                            idmember.setText(idmember2);
                            String status;
                            //System.out.println(bookstatus);
                            // if(bookstatus.equals("true")){
                            //   ketersediaan.setText("tersedia");
                            //}
                            //else{
                            //  ketersediaan.setText("tidak tersedia");
                            //}

                        }
                        if(!flag){
                            membername.setText("Tidak ada member terkait");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    finally {
                        try {
                            conn.close();
                            prst4.close();
                            rst4.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                });
                hb2.getChildren().add(inpdetail2);
                VBox vb2 = new VBox();
                vb2.setLayoutX(232.0);
                vb2.setLayoutY(290.0);
                vb2.setPrefWidth(246.0);
                vb2.setPrefHeight(139.0);
                vb2.getChildren().addAll(membername,jenis,idmember);
                Button pinjam = new Button("Pinjam");
                pinjam.setLayoutY(397.0);
                pinjam.setLayoutX(275.0);
                FileInputStream gmbdsh = new FileInputStream("src/images/information.png");
                Image imgboard8 = new Image(gmbdsh);
                ImageView imgdboard8 = new ImageView(imgboard8);
                imgdboard8.setFitWidth(32.0);
                imgdboard8.setFitHeight(32.0);
                imgdboard8.setLayoutX(222.0);
                imgdboard8.setLayoutY(2.0);
                imgdboard8.setPickOnBounds(true);
                imgdboard8.setPreserveRatio(true);
                Label dsh = new Label("Kode Buku dan Member");
                dsh.setLayoutX(270.0);
                dsh.setLayoutY(10.0);
                Pane pntop = new Pane();
                pntop.setPrefHeight(36.0);
                pntop.setPrefWidth(580.0);
                pntop.setStyle("-fx-background-color: #ffab91");
                pntop.getChildren().addAll(imgdboard8, dsh);
                bpdsh.setAlignment(pntop, Pos.CENTER);
                bpdsh.setTop(pntop);
                pinjam.setOnAction(err -> {
                    try {
                        //SqlConnection scn = new SqlConnection();
                        conn = SqlConnection.DbConnector();
                        PreparedStatement prst5 = null;
                        //ResultSet rst5;
                        //Statement s = conn.createStatement();
                        String string1 = null;
                        String string2 = null;
                        String reslt = null;
                        //scn.setuptabelpinjam();
                        //Bagian menambahkan action peminjaman buku
                        String membid = inpdetail2.getText();
                        bukuid = inpdetail1.getText();

                        Alert warning = new Alert(Alert.AlertType.CONFIRMATION);
                        warning.setTitle("Konfirmasi peminjaman");
                        warning.setHeaderText(null);
                        warning.setContentText("Apa anda yakin ingin meminjamkan buku " + namabook + "\n to " + namamember + " ?");
                        Optional<ButtonType> respon = warning.showAndWait();
                        if (respon.get() == ButtonType.OK) {
                            TextInputDialog dialog = new TextInputDialog("input");
                            dialog.setTitle("Text Input Dialog");
                            dialog.setHeaderText("Input lama haru");
                            dialog.setContentText("Inputkan lama hari :");

                            Optional<String> result = dialog.showAndWait();
                            if (result.isPresent()){
                                reslt = result.get();
                                System.out.println(reslt);
                                try {
                                    string1 = "INSERT INTO pinjam(IDbuku,IDmember,lama_pinjam) VALUES (?,?,?)";
                                    //string2 = "UPDATE buku SET apakahtersedia = 'false' WHERE id = '" + bukuid + "'";
                                    string2 = "UPDATE buku SET apakahtersedia = ?  " +  "WHERE id = ?";
                                    prst5 = conn.prepareStatement(string1);
                                    prst5.setString(1, bukuid);
                                    prst5.setString(2, membid);
                                    prst5.setString(3, reslt);
                                    prst5.executeUpdate();
                                    //rst5 = prst5.executeQuery();
                                } finally {
                                    //conn.close();
                                    prst5.close();
                                }

                                try {
                                    String apakahtersedia = "false";
                                    prst5 = conn.prepareStatement(string2);
                                    prst5.setString(1, apakahtersedia);
                                    prst5.setString(2, bukuid);
                                    prst5.executeUpdate();
                                } finally {
                                    //conn.close();
                                    prst5.close();
                                }


                            }

                                /*string1 = "INSERT INTO pinjam(IDbuku,IDmember,lama_pinjam) VALUES ("
                                        + "'" + bukuid + "',"
                                        + "'" + membid + "',"
                                        + "'" + reslt + "')";*/
                                //string1 = "INSERT INTO pinjam(IDbuku,IDmember) VALUES (?,?)";
                                //string2 = "UPDATE buku SET apakahtersedia = 'false' WHERE id = '" + bukuid + "'";
                               // string2 = "UPDATE buku SET apakahtersedia = ? , " +  "WHERE id = ?";
                               //prst5 = conn.prepareStatement(string1);
                                //prst5.executeUpdate();
                            /*
                                try {
                                    string1 = "INSERT INTO pinjam(IDbuku,IDmember) VALUES (?,?)";
                                    //string2 = "UPDATE buku SET apakahtersedia = 'false' WHERE id = '" + bukuid + "'";
                                    string2 = "UPDATE buku SET apakahtersedia = ? , " +  "WHERE id = ?";
                                    prst5 = conn.prepareStatement(string1);
                                    prst5.setString(1, bukuid);
                                    prst5.setString(2, membid);
                                    //prst5.setString(3, reslt);
                                    prst5.executeUpdate();
                                    //rst5 = prst5.executeQuery();
                                } finally {
                                    //conn.close();
                                    prst5.close();
                                }
                               */

                                /*
                                try {
                                    String apakahtersedia = "false";
                                    prst5 = conn.prepareStatement(string2);
                                    prst5.setString(1, apakahtersedia);
                                    prst5.setString(2, bukuid);
                                    prst5.executeUpdate();
                                } finally {
                                    conn.close();
                                    prst5.close();
                                }
                                */

                                //conn.commit();
                            }


                            /*
                            //tanpa preparestatement agar simple
                            string1 = "INSERT INTO pinjam(IDbuku,IDmember,lama_pinjam) VALUES ("
                                    + "'" + bukuid + "',"
                                    + "'" + membid + "',"
                                    + "'" + reslt + "')";

                            string2 = "UPDATE buku SET apakahtersedia = 'false' WHERE id = '" + bukuid + "'";

                            //menjadikan value pada kolom apakahtersedia menjadi false
                            //false karena jika buku sudah dipinjam maka status berubah menjadi terpinjam , ketika akan dicek kembali maka akan false

                            //s.close();
                            //s.executeUpdate(string2);

                            */
                            /*
                            if (scn.execAction(string1)  && scn.execAction(string2)) {
                                Alert alerterror = new Alert(Alert.AlertType.ERROR);
                                alerterror.setTitle("gagal");
                                alerterror.setHeaderText(null);
                                alerterror.setContentText("peminjaman buku gagal");
                            } else {
                                Alert alertperhatian = new Alert(Alert.AlertType.INFORMATION);
                                alertperhatian.setTitle("Sukses");
                                alertperhatian.setHeaderText(null);
                                alertperhatian.setContentText("peminjaman buku berhasil");
                            }
                            */



                    }catch (SQLException ex) {
                        ex.printStackTrace();
                    } finally {
                        if (conn != null) {
                            try {
                                conn.close();

                            }
                            catch(Exception e3){
                                e3.printStackTrace();
                            }
                        }
                    }
                });


                pndsh.getChildren().addAll(bpdsh,hb,output,hb2,vb2,pinjam);
                //pndsh.getChildren().add(btn);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });



        vbox.getChildren().addAll(panel2, btndboard, btndboard2, btndboard3, btndboard4, btndboard5,btndboard6,btndboard7);
        //PANEL DASHBOARD ATAS
        // pndsh = new Pane();
        /*
        pndsh.setLayoutX(190.0);
        pndsh.setLayoutY(36.0);
        pndsh.setPrefHeight(507.0);
        pndsh.setPrefWidth(612.0);
        */
        //Mainform.setLayoutX(190.0);
        //Mainform.setLayoutY(36.0);
        //Mainform.setPrefWidth(612.0);
        //Mainform.setPrefHeight(507.0);
        //Mainform.setPickOnBounds(true);
        /*
        BorderPane bpdsh = new BorderPane();
        bpdsh.setLayoutX(14.0);
        bpdsh.setLayoutY(44.0);
        bpdsh.setPrefHeight(30.0);
        bpdsh.setPrefWidth(580.0);
        HBox hb = new HBox();
        hb.setLayoutX(17);
        hb.setLayoutY(102);
        hb.setPrefWidth(204.0);
        hb.setPrefHeight(139.0);
        hb.setAlignment(Pos.CENTER);
        Label bookname = new Label();
        bookname.setText("Nama buku : ");
        bookname.setPadding(new Insets(10,10,10,10));
        Label author = new Label();
        author.setText("Nama Pengarang :");
        author.setPadding(new Insets(10,10,10,10));
        Label ketersediaan = new Label("Ketersediaan");
        ketersediaan.setPadding(new Insets(15,10,10,10));
        TextField inpdetail1 = new TextField();
        inpdetail1.setPromptText("Kode Buku");
        inpdetail1.setOnAction(e -> {
            SqlConnection cnn = null;
            try {
                cnn = new SqlConnection();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            String id = inpdetail1.getText();
            String kueri = "SELECT * FROM buku WHERE id = '"+ id +"'";
             ResultSet rstt = cnn.execQuery(kueri);
             Boolean flag = false;
             try{
                 while(rstt.next()){
                     namabook = rstt.getString("nama");
                     String bookauthor =  rstt.getString("nama_pengarang");
                     String bookstatus = rstt.getString("apakahtersedia");
                     flag = true;

                     bookname.setText(namabook);
                     author.setText(bookauthor);
                     String status;
                     System.out.println(bookstatus);
                     if(bookstatus.equals("true")){
                        ketersediaan.setText("tersedia");
                     }
                     else{
                         ketersediaan.setText("tidak tersedia");
                     }

                 }
                 if(!flag){
                     bookname.setText("Tidak ada buku tersedia");
                 }
             } catch (SQLException ex) {
                 ex.printStackTrace();
             }
             finally {
                 try {
                     rstt.close();
                 } catch (SQLException ex) {
                     ex.printStackTrace();
                 }
             }

        });
        hb.getChildren().add(inpdetail1);
        VBox output = new VBox();
        output.setLayoutX(240.0);
        output.setLayoutY(120.0);
        output.setPrefWidth(246.0);
        output.setPrefHeight(139.0);
        output.getChildren().addAll(bookname,author,ketersediaan);
        HBox hb2 = new HBox();
        hb2.setLayoutY(242.0);
        hb2.setLayoutX(22.0);
        hb2.setAlignment(Pos.CENTER);
        hb2.setPrefHeight(139.0);
        hb2.setPrefWidth(204.0);
        Label membername = new Label();
        membername.setText("Nama Member");
        membername.setPadding(new Insets(10,10,10,10));
        Label nim = new Label();
        nim.setText("NIM :  ");
        nim.setPadding(new Insets(10,10,10,10));
        Label idmember = new Label("id");
        idmember.setPadding(new Insets(10,10,10,10));
        TextField inpdetail2 = new TextField();
        inpdetail2.setPromptText("input NIM");
        inpdetail2.setLayoutY(250.0);
        inpdetail2.setLayoutX(39.0);
        inpdetail2.setOnAction(e -> {
            SqlConnection cnn2 = null;
            try {
                cnn2 = new SqlConnection();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            String id = inpdetail2.getText();
            String kueri = "SELECT * FROM member WHERE nim = '"+ id +"'";
            ResultSet rstt = cnn2.execQuery(kueri);
            Boolean flag = false;
            try{
                while(rstt.next()){
                    namamember = rstt.getString("nama");
                    String idmember2 =  rstt.getString("id");
                    Integer nimmb = rstt.getInt("nim");
                    //String bookstatus = rstt.getString("apakahtersedia");
                    flag = true;

                    membername.setText(namamember);
                    nim.setText(String.valueOf(nimmb));
                    idmember.setText(idmember2);
                    String status;
                    //System.out.println(bookstatus);
                   // if(bookstatus.equals("true")){
                     //   ketersediaan.setText("tersedia");
                    //}
                    //else{
                      //  ketersediaan.setText("tidak tersedia");
                    //}

                }
                if(!flag){
                    membername.setText("Tidak ada member terkait");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            finally {
                try {
                    rstt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        hb2.getChildren().add(inpdetail2);
        VBox vb2 = new VBox();
        vb2.setLayoutX(232.0);
        vb2.setLayoutY(290.0);
        vb2.setPrefWidth(246.0);
        vb2.setPrefHeight(139.0);
        vb2.getChildren().addAll(membername,nim,idmember);
        Button pinjam = new Button("Pinjam");
        pinjam.setLayoutY(397.0);
        pinjam.setLayoutX(275.0);
        FileInputStream gmbdsh = new FileInputStream("src/images/information.png");
        Image imgboard8 = new Image(gmbdsh);
        ImageView imgdboard8 = new ImageView(imgboard7);
        imgdboard8.setFitWidth(32.0);
        imgdboard8.setFitHeight(32.0);
        imgdboard8.setLayoutX(222.0);
        imgdboard8.setLayoutY(2.0);
        imgdboard8.setPickOnBounds(true);
        imgdboard8.setPreserveRatio(true);
        Label dsh = new Label("Kode Buku dan Member");
        dsh.setLayoutX(270.0);
        dsh.setLayoutY(10.0);
        Pane pntop = new Pane();
        pntop.setPrefHeight(36.0);
        pntop.setPrefWidth(580.0);
        pntop.setStyle("-fx-background-color: #ffab91");
        pntop.getChildren().addAll(imgdboard7, dsh);
        bpdsh.setAlignment(pntop, Pos.CENTER);
        bpdsh.setTop(pntop);
        pinjam.setOnAction(e -> {
            try {
                SqlConnection scn = new SqlConnection();
                //scn.setuptabelpinjam();
                //Bagian menambahkan action peminjaman buku
                String membid = inpdetail2.getText();
                bukuid = inpdetail1.getText();

                Alert warning = new Alert(Alert.AlertType.CONFIRMATION);
                warning.setTitle("Konfirmasi peminjaman");
                warning.setHeaderText(null);
                warning.setContentText("Apa anda yakin ingin meminjamkan buku " + namabook + "\n to " + namamember + " ?");

                Optional<ButtonType> respon = warning.showAndWait();
                if (respon.get() == ButtonType.OK) {
                    //tanpa preparestatement agar simple
                    String string1 = "INSERT INTO pinjam(IDbuku,IDmember) VALUES ("
                            + "'" + bukuid + "',"
                            + "'" + membid + "')";
                    //menjadikan value pada kolom apakahtersedia menjadi false
                    //false karena jika buku sudah dipinjam maka status berubah menjadi terpinjam , ketika akan dicek kembali maka akan false
                    String string2 = "UPDATE buku SET apakahtersedia = 'false' WHERE id = '" + bukuid + "'";

                    if (scn.execAction(string1) && scn.execAction(string2)) {
                        Alert alertperhatian = new Alert(Alert.AlertType.INFORMATION);
                        alertperhatian.setTitle("Sukses");
                        alertperhatian.setHeaderText(null);
                        alertperhatian.setContentText("peminjaman buku berhasil");
                    } else {
                        Alert alerterror = new Alert(Alert.AlertType.ERROR);
                        alerterror.setTitle("gagal");
                        alerterror.setHeaderText(null);
                        alerterror.setContentText("peminjaman buku gagal");
                    }
                }
                } catch (SQLException ex) {
                ex.printStackTrace();
            }
            });


        pndsh.getChildren().addAll(bpdsh,hb,output,hb2,vb2,pinjam);
        //pndsh.getChildren().add(btn);
        */
        //Main panel calling her children
        pane.getChildren().addAll(panel, vbox, pndsh);
        scene = new Scene(pane, 800, 543);
        stage.setScene(scene);
        stage.show();
    }

    public void kembali() throws IOException {
        Stage stg = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("listview.fxml"));
        stg.setTitle("Hello World");
        stg.setScene(new Scene(root, 600, 600));
        stg.show();
    }


/*
    public void pengembalian() throws SQLException {
        //AnchorPane bpreturn = new AnchorPane();
        BorderPane bpreturn = new BorderPane();
        Stage stgreturn = new Stage();
        Scene scnreturn;
        SqlConnection sql = new SqlConnection();
        bpreturn.setPadding(new Insets(10,15,15,10));
        Label header = new Label("Masukkan id buku dibawah");
        header.setLayoutX(175.0);
        header.setLayoutY(20.0);
        TextField bukuidtxt = new TextField();
        bukuidtxt.setPromptText("Masukan id buku");
        bukuidtxt.setLayoutX(206.0);
        bukuidtxt.setLayoutY(46.0);
        bukuidtxt.setPromptText("Masukan id buku");
        bpreturn.setTop(header);
        bpreturn.setAlignment(header,Pos.TOP_CENTER);
        bpreturn.setAlignment(bukuidtxt,Pos.BOTTOM_CENTER);
        Button btnkembali = new Button("kembalikan");
        btnkembali.setLayoutX(253.0);
        btnkembali.setLayoutY(353.0);
        btnkembali.setMnemonicParsing(false);
        datakembaliList.setLayoutX(29.0);
        datakembaliList.setLayoutY(85.0);
        datakembaliList.setPrefWidth(539.0);
        datakembaliList.setPrefHeight(252.0);
        Button proses = new Button("Proses");
        proses.setLayoutY(46.0);
        proses.setLayoutX(412.0);
        String idbk = bukuidtxt.getText();
        proses.setOnAction(e -> {
        try {
            String kuerireturn = "SELECT * FROM pinjam WHERE IDbuku = '" + idbk + "'";
            ResultSet rstreturn = sql.execQuery(kuerireturn);
            while (rstreturn.next()) {
                String bukuidreturn = idbk;
                String memberidreturn = rstreturn.getString("IDmember");
                Timestamp waktupinjam = rstreturn.getTimestamp("waktu_pinjam");

                datakembali.add("Waktu dan tanggal pinjam : " + waktupinjam.toLocalDateTime());

                datakembali.add("Informasi buku : ");
                String kueriinfo = "SELECT * FROM buku WHERE id = '" + bukuidreturn + "'";
                while (rstreturn.next()) {
                    datakembali.add("Nama Buku : " + rstreturn.getString("nama"));
                    datakembali.add("ID buku : " + rstreturn.getString("id"));
                    datakembali.add("Nama Pengarang : " + rstreturn.getString("nama_pengarang"));
                    datakembali.add("Nama Penerbit : " + rstreturn.getString("nama_penerbit"));
                }
                String kuerimember = "SELECT * FROM member WHERE id = '" + memberidreturn + "'";
                rstreturn = sql.execQuery(kuerimember);
                datakembali.add("Informasi member / peminjam: ");
                while (rstreturn.next()) {
                    datakembali.add("Nama member/peminjam : " + rstreturn.getString("nama"));
                    datakembali.add("Nim member/peminjam : " + rstreturn.getString("nim"));
                }
            }
            rstreturn.close();
        }
        catch(SQLException e2){
            e2.printStackTrace();
        }
            datakembaliList.getItems().setAll(datakembali);
        });
       bpreturn.setCenter(datakembaliList);
       bpreturn.setAlignment(datakembaliList,Pos.CENTER);
       //bpreturn.getChildren().addAll(header,bukuidtxt,proses,btnkembali,datakembaliList);
       scnreturn = new Scene(bpreturn,600,600);
       stgreturn.setScene(scnreturn);
       stgreturn.show();
    }
*/
    public void returnbk(){

        Stage formstage2 = new Stage();
        Scene formscene;
        GridPane formrt = new GridPane();
        formrt.setAlignment(Pos.CENTER);
        formrt.setHgap(10);
        formrt.setVgap(10);
        formrt.setPadding(new Insets(25, 25, 25, 25));

        Text title = new Text("PENGEMBALIAN");
        title.setFill(Color.BLUEVIOLET);
        title.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        formrt.add(title, 0, 0, 2, 1);

        Label lblNama = new Label("ID BUKU : ");
        formrt.add(lblNama, 0, 1);

        TextField txtIdBuku = new TextField();
        txtIdBuku.setPromptText("masukan id Buku");
        formrt.add(txtIdBuku, 1, 1);

        Label lbAge = new Label("ID MEMBER : ");
        formrt.add(lbAge, 0, 2);

        TextField id = new TextField();
        id.setPromptText("Masukan Id member");
        formrt.add(id, 1, 2);

        HBox hbt2 = new HBox();
        Button btnSave = new Button("kembalikan buku");
        //PreparedStatement prst5 = null;
        //ResultSet rst5;
        /*
        btnSave.setOnAction(e -> {
            PreparedStatement prst5 = null;
            ResultSet rst5;
            String Idmember = id.getText();
            //String Author = author.getText();
            String IdBuku = txtIdBuku.getText();
            String memberid;
            String bukuid;
            //String penerbitBuku = txtTerbit.getText();
            //Integer tahunTerbit = Integer.valueOf(txtTaun.getText());
            try {
                //SqlConnection scn = new SqlConnection();
                conn = SqlConnection.DbConnector();
                //PreparedStatement prst5 = null;
                //ResultSet rst5;
                //Statement s = conn.createStatement();
                String kueri = "SELECT * FROM member WHERE id = '" + Idmember + "'";
                String kueri2 = "SELECT nama FROM buku WHERE id = '" + IdBuku + "'";
                //ResultSet rstt = cnn2.execQuery(kueri);
                Boolean flag = false;
                try {
                    prst5 = conn.prepareStatement(kueri);
                    rst5 = prst5.executeQuery();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    prst5.close();
                }

                //rst5 = prst5.executeQuery();
                try {
                    while(rst5.next()){
                        memberid = rst5.getString("nama");
                        //bukuid =  rst5.getString("id");
                        //Integer nimmb = rstt.getInt("nim");

                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                try {
                        prst5 = conn.prepareStatement(kueri2);
                        rst5 = prst5.executeQuery();

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } finally {
                        try {
                            prst5.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                    try {
                        while(rst5.next()){
                            memberid = rst5.getString("nama");
                            //bukuid =  rst5.getString("id");
                            //Integer nimmb = rstt.getInt("nim");

                        }
                    String stringrt1 = null;
                    String stringrt2 = null;
                    String reslt = null;
                    //scn.setuptabelpinjam();
                    //Bagian menambahkan action peminjaman buku


                    Alert warning = new Alert(Alert.AlertType.CONFIRMATION);
                    warning.setTitle("Konfirmasi pengembalian");
                    warning.setHeaderText(null);
                    warning.setContentText("Apa anda yakin ingin mengembalikan buku " + namabook + "\n dari " + namamember + " ?");
                    Optional<ButtonType> respon = warning.showAndWait();
                    if (respon.get() == ButtonType.OK) {

                        try {
                            stringrt1 = "INSERT INTO pinjam(IDbuku,IDmember,lama_pinjam) VALUES (?,?,?)";
                            //string2 = "UPDATE buku SET apakahtersedia = 'false' WHERE id = '" + bukuid + "'";
                            stringrt2 = "UPDATE buku SET apakahtersedia = ?  " + "WHERE id = ?";
                            prst5 = conn.prepareStatement(stringrt1);
                            prst5.setString(1, bukuid);
                            prst5.setString(2, membid);
                            prst5.setString(3, reslt);
                            prst5.executeUpdate();
                            //rst5 = prst5.executeQuery();
                        } finally {
                            //conn.close();
                            prst5.close();
                        }


                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                        try {
                            prst5.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
            }});
        */
        hbt2.setAlignment(Pos.CENTER_LEFT);
        hbt2.getChildren().add(btnSave);
        formrt.add(hbt2,0,6);
        formscene = new Scene(formrt,400,400);
        formstage2.setScene(formscene);
        formstage2.setTitle("ADD BUKU");
        formstage2.show();
    }


    public void setupMember(){
        Stage frmbuku2 = new Stage();
        Scene scnbuku2;
        BorderPane tb2 = new BorderPane();
        tb2.setPadding(new Insets(10,10,10,10));

        TableColumn column1 = new TableColumn("ID member");
        column1.setMinWidth(25);
        column1.setCellValueFactory(new PropertyValueFactory<>("idmember"));

        TableColumn column2 = new TableColumn("Nama Member");
        column2.setMinWidth(60);
        column2.setCellValueFactory(new PropertyValueFactory<>("Namamember"));

        TableColumn column3 = new TableColumn("Jenis Member");
        column3.setMinWidth(60);
        column3.setCellValueFactory(new PropertyValueFactory<>("jenismember"));


        tabel2.getColumns().addAll(column1,column2,column3);

        tb2.setCenter(tabel2);
        BorderPane.setMargin(tabel2,new Insets(0,10,10,0));
        scnbuku2 = new Scene(tb2,400,400);
        frmbuku2.setScene(scnbuku2);
        frmbuku2.show();


    }


    public void setupBuku() {
        Stage frmbuku = new Stage();
        Scene scnbuku;
        BorderPane tb = new BorderPane();
        tb.setPadding(new Insets(10,10,10,10));

            TableColumn column1 = new TableColumn("ID");
            column1.setMinWidth(25);
            column1.setCellValueFactory(new PropertyValueFactory<>("id"));

            TableColumn column2 = new TableColumn("Nama Buku");
            column2.setMinWidth(80);
            column2.setCellValueFactory(new PropertyValueFactory<>("namaBuku"));

            TableColumn column3 = new TableColumn("Nama Pengarang");
            column3.setMinWidth(80);
            column3.setCellValueFactory(new PropertyValueFactory<>("namaPengarang"));

            TableColumn column4 = new TableColumn("Nama Penerbit");
            column4.setMinWidth(80);
            column4.setCellValueFactory(new PropertyValueFactory<>("namaPenerbit"));

            TableColumn column5 = new TableColumn("Tahun Penerbit");
            column4.setMinWidth(80);
            column4.setCellValueFactory(new PropertyValueFactory<>("tahunTerbit"));

            TableColumn column6 = new TableColumn("Ketersediaan");
            column5.setMinWidth(80);
            column5.setCellValueFactory(new PropertyValueFactory<>("apakahTersedia"));

        tabel.getColumns().addAll(column1,column2,column3,column4,column5);

        tb.setCenter(tabel);
        BorderPane.setMargin(tabel,new Insets(0,10,10,0));
        scnbuku = new Scene(tb,800,800);
        frmbuku.setScene(scnbuku);
        frmbuku.show();

    }



    public void showform() throws FileNotFoundException{
        Stage formstage = new Stage();
        Scene formscene;
        GridPane form = new GridPane();
        form.setAlignment(Pos.CENTER);
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(25, 25, 25, 25));

        Text title = new Text("TAMBAH BUKU");
        title.setFill(Color.BLUEVIOLET);
        title.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        form.add(title, 0, 0, 2, 1);

        Label lblNama = new Label("Nama Buku : ");
        form.add(lblNama, 0, 1);

        TextField txtNamaBuku = new TextField();
        txtNamaBuku.setPromptText("masukan nama Buku");
        form.add(txtNamaBuku, 1, 1);

        Label lbAge = new Label("ID buku : ");
        form.add(lbAge, 0, 2);

        TextField id = new TextField();
        id.setPromptText("Masukan Id Buku");
        form.add(id, 1, 2);
        Label txtJns = new Label("Nama Pengarang : ");
        form.add(txtJns,0,3);
        TextField author = new TextField();
        author.setPromptText("Masukan nama pengarang");
        form.add(author,1,3);
        Label pekerjaan = new Label("Penerbit :");
        form.add(pekerjaan , 0,4);
        TextField txtTerbit = new TextField();
        txtTerbit.setPromptText("Masukkan penerbit");
        form.add(txtTerbit,1,4);
        Label taun  = new Label("Tahun terbit :");
        form.add(taun , 0,5);
        TextField txtTaun = new TextField();
        txtTaun.setPromptText("Masukkan tahun terbit");
        form.add(txtTaun,1,5);
        HBox hbt = new HBox();
        Button btnSave = new Button("Save");
        //berain sql untuk button save ( simpan)
        btnSave.setOnAction(e -> {
            String IdBuku = id.getText();
            String Author = author.getText();
            String namaBuku = txtNamaBuku.getText();
            String penerbitBuku = txtTerbit.getText();
            Integer tahunTerbit = Integer.valueOf(txtTaun.getText());

            if(IdBuku.isEmpty()||Author.isEmpty()||namaBuku.isEmpty()||penerbitBuku.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Harap masukkan semua data di kolom teks yang ada");
                alert.showAndWait();
                return;
            }
            //ResultSet rs = null;
            PreparedStatement stmt = null;
            try {
                String defaultVal = "true";
                //SqlConnection sqlconn = new SqlConnection();
                //sqlconn.setuptabelbuku();
                conn = SqlConnection.DbConnector();
                //String parseString;
                String prepsmt = "INSERT INTO buku(id, nama, nama_pengarang, nama_penerbit, tahun_terbit, apakahtersedia) VALUES (?,?,?,?,?,?)";
                //String sql = "INSERT INTO buku(id,nama,nama_pengarang,nama_penerbit,apakahtersedia) VALUES('" + IdBuku + "','" + Author + "','" + namaBuku + "','" + penerbitBuku + "','" + boolDefaultVal + "')";
                //parseString = stmt.toString();
                stmt = conn.prepareStatement(prepsmt);
                // we almost dump this function ( sebenarnya untuk mencegah SQL injection tapi banyak pertimbangan error )
                //there' a reason why codes below and above sometimes won't work , tidak bisa mempass parameter stmt yang non-string di fungsi execaction()
                stmt.setString(1,IdBuku);
                stmt.setString(2,namaBuku);
                stmt.setString(3,Author);
                stmt.setString(4,penerbitBuku);
                stmt.setInt(5,tahunTerbit);
                stmt.setString(6,defaultVal);
                stmt.executeUpdate();
                //rs = stmt.executeUpdate();

                Alert alrt = new Alert(Alert.AlertType.WARNING);
                alrt.setHeaderText(null);
                alrt.setContentText("success");
                alrt.showAndWait();


            } catch (SQLException ex) {
                ex.printStackTrace();
                Alert alrt2 = new Alert(Alert.AlertType.ERROR);
                alrt2.setHeaderText(null);
                alrt2.setContentText("failed ,error : " + ex.getLocalizedMessage());
                alrt2.showAndWait();

            }
            finally {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (Exception e1) {
                        e1.printStackTrace();
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

        });
        hbt.setAlignment(Pos.CENTER_LEFT);
        hbt.getChildren().add(btnSave);
        form.add(hbt,0,6);
        formscene = new Scene(form,400,400);
        formstage.setScene(formscene);
        formstage.setTitle("ADD BUKU");
        formstage.show();

    }

    public void pinjambuku(){

        //unused
    }

    public void showformmember() throws FileNotFoundException, SQLException {
        Stage formstage = new Stage();
        Scene formscene;
        GridPane form = new GridPane();
        form.setAlignment(Pos.CENTER);
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(25, 25, 25, 25));

        Text title = new Text("TAMBAH MEMBER");
        title.setFill(Color.GREENYELLOW);
        title.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        form.add(title, 0, 0, 2, 1);

        Label lblMember = new Label("Nama Member : ");
        form.add(lblMember, 0, 1);

        TextField txtNamaMember = new TextField();
        txtNamaMember.setPromptText("masukan nama Member");
        form.add(txtNamaMember, 1, 1);

        Label lbAge = new Label("Jenis member : ");
        form.add(lbAge, 0, 2);

        TextField Nim = new TextField();
        Nim.setPromptText("Masukan Jenis Member");
        form.add(Nim, 1, 2);

        Label lbid = new Label("id member : ");
        form.add(lbid, 0, 3);

        TextField id = new TextField();
        id.setPromptText("Masukan id Member");
        form.add(id, 1, 3);
        HBox hbt = new HBox();
        Button btnSave2 = new Button("Save");
        //berain sql untuk button save ( simoan)
        btnSave2.setOnAction(e -> {
            String idmember = id.getText();
            String NamaMember = txtNamaMember.getText();
            String NimMember= Nim.getText();
            if(NamaMember.isEmpty()||NimMember.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Harap masukkan semua data di kolom teks yang ada");
                alert.showAndWait();
                return;

            }
            //ResultSet rs = null;
            PreparedStatement stmt2 = null;
            try {
                //String defaultVal = "true";
                conn = SqlConnection.DbConnector();
                //SqlConnection sqlconn = new SqlConnection();
                //String parseString;
                String prepsmt2 = "INSERT INTO member VALUES (?,?,?)";
                //String sql = "INSERT INTO buku(id,nama,nama_pengarang,nama_penerbit,apakahtersedia) VALUES('" + IdBuku + "','" + Author + "','" + namaBuku + "','" + penerbitBuku + "','" + boolDefaultVal + "')";
                //parseString = stmt.toString();
                stmt2 = conn.prepareStatement(prepsmt2);
                // we almost dump this function ( sebenarnya untuk mencegah SQL injection tapi banyak pertimbangan error )
                //there' a reason why codes below and above sometimes won't work , tidak bisa mempass parameter stmt yang non-string di fungsi execaction()
                stmt2.setString(1,idmember);
                stmt2.setString(2,NamaMember);
                stmt2.setString(3,NimMember);
                stmt2.executeUpdate();
                //rs = stmt.executeUpdate();

                Alert alrt = new Alert(Alert.AlertType.WARNING);
                alrt.setHeaderText(null);
                alrt.setContentText("success menambahkan");
                alrt.showAndWait();


            } catch (SQLException ex) {
                ex.printStackTrace();
                Alert alrt2 = new Alert(Alert.AlertType.ERROR);
                alrt2.setHeaderText(null);
                alrt2.setContentText("failed ,error : " + ex.getLocalizedMessage());
                alrt2.showAndWait();

            }
            finally {
                if (stmt2 != null) {
                    try {
                        stmt2.close();
                    } catch (Exception e1) {
                        e1.printStackTrace();
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

        });
        hbt.setAlignment(Pos.CENTER_LEFT);
        hbt.getChildren().add(btnSave2);
        form.add(hbt,0,5);
        formscene = new Scene(form,400,400);
        formstage.setScene(formscene);
        formstage.setTitle("ADD MEMBER");
        formstage.show();

    }


}
