package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

//setter getter dan logika tabel untuk MemberDb
public class MemberDb {
    private final SimpleStringProperty id;
    private final SimpleStringProperty namamember;
    private final SimpleStringProperty jenismember;
    //private final SimpleStringProperty namapenerbit;
    //private final SimpleStringProperty apakahtersedia;


    public MemberDb(String idmember,String namaMember,String jenismember){
        this.id = new SimpleStringProperty(idmember);
        this.namamember = new SimpleStringProperty(namaMember);
        this.jenismember = new SimpleStringProperty(jenismember);
        //this.namapenerbit = new SimpleStringProperty(namaPenerbit);
        //this.apakahtersedia =  new SimpleStringProperty(apakahTersedia);

    }

    public String getIdmember(){
        return id.get();
    }
    public String getNamamember(){
        return namamember.get();
    }
    public String getJenismember(){
        return jenismember.get();
    }
    //public String getNamaPenerbit(){
      //  return namapenerbit.get();
    //}/
    //public String getApakahTersedia(){
        //return apakahtersedia.get();
    //}
    public void setidmember(String idmember){
        id.set(idmember);
    }
    public void setnamamember(String Namamember){
        namamember.set(Namamember);
    }
    public void setjenismember(String Jenismember){
        jenismember.set(Jenismember);
    }
    //public void setNamaPenerbit(String penerbit){
      //  namapenerbit.set(penerbit);
    //}
    //public void setApakahTersedia(String apakahTersedia){
      //  apakahtersedia.set(apakahTersedia);
    //}



}
