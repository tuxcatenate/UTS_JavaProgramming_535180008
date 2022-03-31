package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.*;

public class Controller {
    SqlConnection sql = new SqlConnection();
    Connection conn;

    @FXML
    private Label lbalert;

    @FXML
    private TextField txtbookid;

    @FXML
    private ListView<String> listdata;

    @FXML
    private Button btnkembali;

    public Controller() throws SQLException {
    }

    @FXML
    void loadKembalian(ActionEvent event) throws SQLException {
        ObservableList<String> datakembali = FXCollections.observableArrayList();
        conn = SqlConnection.DbConnector();
        PreparedStatement prst = null;
        String bookid = txtbookid.getText();
        System.out.println(bookid);
        Label header = new Label("Masukkan id buku dibawah");
        String kuerireturn = "SELECT * FROM pinjam WHERE IDbuku = '" + bookid + "'";
        prst = conn.prepareStatement(kuerireturn);
        ResultSet rstreturn = prst.executeQuery();
                while (rstreturn.next()) {
                    String bukuidreturn = bookid;
                    String memberidreturn = rstreturn.getString("IDmember");
                    Timestamp waktupinjam = rstreturn.getTimestamp("waktu_pinjam");
                    System.out.println(waktupinjam);

                    datakembali.add("Waktu dan tanggal pinjam : " + waktupinjam.toLocalDateTime());

                    datakembali.add("Informasi buku : ");
                    String kueriinfo = "SELECT * FROM buku WHERE id = '" + bukuidreturn + "'";
                    PreparedStatement rss1 = null;
                    rss1 = conn.prepareStatement(kueriinfo);
                    ResultSet rs1 = rss1.executeQuery();
                    while (rs1.next()) {
                        datakembali.add("Nama Buku : " + rs1.getString("nama"));
                        datakembali.add("ID buku : " + rs1.getString("id"));
                        datakembali.add("Nama Pengarang : " + rs1.getString("nama_pengarang"));
                        datakembali.add("Nama Penerbit : " + rs1.getString("nama_penerbit"));
                    }
                    PreparedStatement rss2 = null;
                    String kuerimember = "SELECT * FROM member WHERE id = '" + memberidreturn + "'";
                    rss2 = conn.prepareStatement(kuerimember);
                    ResultSet rsst = prst.executeQuery();
                    datakembali.add("Informasi member / peminjam: ");
                    while (rs1.next()) {
                        datakembali.add("Nama member/peminjam : " + rs1.getString("nama"));
                        datakembali.add("Nim member/peminjam : " + rs1.getString("nim"));
                    }
                }
        listdata.setItems(datakembali);

            }
        }







