package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BookDb {
    //logika tabel dibawah ini
    //setter dan getter ntuk bookdb
    private final SimpleStringProperty id;
    private final SimpleStringProperty nama;
    private final SimpleStringProperty namapengarang;
    private final SimpleStringProperty namapenerbit;
    private final SimpleIntegerProperty tahunterbit;
    private final SimpleStringProperty apakahtersedia;


    public BookDb(String idbuku,String namaBuku,String namaPengarang,String namaPenerbit,Integer tahunTerbit,String apakahTersedia){
        this.id = new SimpleStringProperty(idbuku);
        this.nama = new SimpleStringProperty(namaBuku);
        this.namapengarang = new SimpleStringProperty(namaPengarang);
        this.namapenerbit = new SimpleStringProperty(namaPenerbit);
        this.tahunterbit = new SimpleIntegerProperty(tahunTerbit);
        this.apakahtersedia =  new SimpleStringProperty(apakahTersedia);

    }

    public String getId(){
        return id.get();
    }
    public String getNamaBuku(){
        return nama.get();
    }
    public String getNamaPengarang(){
        return namapengarang.get();
    }
    public String getNamaPenerbit(){
        return namapenerbit.get();
    }
    public Integer getTahunTerbit(){
        return tahunterbit.get();
    }
    public String getApakahTersedia(){
        return apakahtersedia.get();
    }
    public void setId(String idbuku){
        id.set(idbuku);
    }
    public void setNamaBuku(String namaBuku){
        nama.set(namaBuku);
    }
    public void setNamaPengarang(String namaPengarang){
        namapengarang.set(namaPengarang);
    }
    public void setNamaPenerbit(String penerbit){
        namapenerbit.set(penerbit);
    }
    public void setTahunTerbit(Integer terbit){
        tahunterbit.set(terbit);
    }
    public void setApakahTersedia(String apakahTersedia){
        apakahtersedia.set(apakahTersedia);
    }


}
