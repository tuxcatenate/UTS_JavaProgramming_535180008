package sample;

import javafx.scene.control.Alert;

import java.sql.*;

import static java.lang.Class.forName;

//logika koneksi sql
//koneksi diclose sehabis create,update,read,delete karena sqlite hanya bisa 1 koneksi saja
//sql tidak concurrency/menerima lebih dari 1 koneksi
public class SqlConnection {
    Statement stmt2 = null;
    PreparedStatement pstmt2 = null;
    Statement stmt = null;
    static Connection conn = null;
    //private static SqlConnection handler;
    String dbname = "userdb.sqlite";

    public SqlConnection() throws SQLException {
        getConn();
        //setuptabelbuku(); //uncomment jika sudah tidak diperlukan
    }

    public static Connection DbConnector() {
        {
            try {
                conn = null;
                Class.forName("org.sqlite.JDBC");
                //jika nama file DB tidak sesuai maka sqlite akan gagal menemukannya
                //jika memungkinkan , tempatkan di directory root yang bernama "src"
                conn = DriverManager.getConnection("jdbc:sqlite:userdb.sqlite");
                return conn;
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e);
            }
            return null;

        }

    }

    public void getConn(){
        SqlConnection.DbConnector();
    }


    public boolean execAction(String qu) throws SQLException {
        try {
            conn = DbConnector();
            stmt = conn.createStatement();
            stmt.execute(qu);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alerterr = new Alert(Alert.AlertType.ERROR);
            alerterr.setTitle("Error Terjadi");
            alerterr.setHeaderText("Error ,ditampilkan dalam eksepsi Java");
            alerterr.setContentText("Error"+e.getMessage()+"");
            alerterr.showAndWait();
            //munculkan error ini di console bawah
            System.out.println(e.getLocalizedMessage()+ e);
            return false;

        }
        finally {
            conn.close();
            stmt.close();
        }

    }



    //basically the same logikanya seperti setuptabelbuku
    void setuptabelmember() throws SQLException {
        //lowercase karena biasa dalam pemanggilan select atau fungsi sql lain rawan salah ketik, kalau tidak hati- hati bisa error
        //'case sensitive'
        String namatabel2 = "member";
        try{
            stmt = conn.createStatement();
            DatabaseMetaData md = conn.getMetaData();
            ResultSet tabel = md.getTables(null,null,namatabel2,null);
            if(tabel.next()){
                System.out.println("Tabel dengan nama "+namatabel2+" sudah ada , langsung lanjutkan");
            }else{
                //match dengan format syntax sqlite karena setiap beberapa program sql syntax nya beda sedikit(saat kuliah pakai oracle DB)
                stmt.execute(" CREATE TABLE " + namatabel2 + " (" +
                        " id TEXT PRIMARY KEY ," +
                        " nama TEXT ," +
                        " jenismember TEXT );" );
            }
            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
            //tangkap errornya dengan exception

        }
    }

    void setuptabelpinjam() throws SQLException{
        String namatabelpinjam = "pinjam";
        try{
            stmt = conn.createStatement();
            DatabaseMetaData md = conn.getMetaData();
            ResultSet tabel = md.getTables(null,null,namatabelpinjam,null);
            if(tabel.next()){
                System.out.println("Tabel dengan nama "+namatabelpinjam+" sudah ada , langsung lanjutkan");
            }else{
                //match dengan format syntax sqlite karena setiap beberapa program sql berbeda syntax( saat mata kuliah database pakai oracle Db)
                //now,localtime pada waktu pinjam berarti mengambil waktu sekarang ( current time ) dengan zona waktu sesuai zona waktu komputer
                //referensi manual datetime diambil dari dokumentasi website resmi sqlite
                stmt.execute(" CREATE TABLE " + namatabelpinjam + " (" +
                        " IDbuku TEXT PRIMARY KEY ," +
                        " IDmember TEXT ," +
                        " lama_pinjam TEXT);" );
                        //" FOREIGN KEY (IDbuku) REFERENCES buku(id), " +
                        ///" FOREIGN KEY (IDmember) REFERENCES member(id));" );
            }
        }
        catch (SQLException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
            //tangkap errornya dengan exception

        }
        stmt.close();

    }

    //yang dibawah ini akan mengecek apakah tabel itu sudah ada(sudah ter store ke sql) atau belum dan mensetup tabel untuk buku
    void setuptabelbuku() throws SQLException {
        //lowercase karena biasa dalam pemanggilan select atau fungsi sql lain rawan typo, kalau tidak hati2 bisa error
        //'case sensitive'
        String namatabel = "buku";
        try{
            stmt = conn.createStatement();
            DatabaseMetaData md = conn.getMetaData();
            ResultSet tabel = md.getTables(null,null,namatabel,null);
            if(tabel.next()){
                System.out.println("Tabel dengan nama "+namatabel+" sudah ada , langsung lanjutkan");
            }else{
                //match dengan format syntax sqlite karena setiap beberapa program sql berbeda syntax(saat mata kuliah database pakai oracle DB)
                stmt.execute(" CREATE TABLE " + namatabel + " (" +
                        " id TEXT PRIMARY KEY ," +
                        " nama TEXT ," +
                        " nama_pengarang TEXT ," +
                        " nama_penerbit TEXT, " +
                        " tahun_terbit INTEGER, " +
                        " apakahtersedia TEXT DEFAULT 'true');" );
            }
        }
        catch (SQLException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
            //tangkap errornya dengan exception

        }
    }

    public ResultSet execQuery(String query1) {
        ResultSet sethasil = null;
        try{
            conn = DbConnector();
            stmt = conn.createStatement();
            sethasil = stmt.executeQuery(query1);

        }catch (SQLException e) {
           // e.printStackTrace();
            System.out.println("eksepsi pada query : " + e.getLocalizedMessage());
        }
        return sethasil;
    }


}
