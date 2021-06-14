package sample;

public class Urunler {
    private String ID;
    private String stokSuan;
    private String stokGelecek;
    private String marka;
    private String ad;
    private String fiyat;

    public Urunler(String ID, String marka, String ad, String fiyat) {
        this.ID = ID;
        this.marka = marka;
        this.ad = ad;
        this.fiyat = fiyat;
    }
    public Urunler(String ID, String marka, String ad, String stokSuan, String degisken) {
        this.ID = ID;
        this.stokSuan = stokSuan;
        this.stokGelecek = degisken;
        this.marka = marka;
        this.ad = ad;
        this.fiyat = degisken;
    }

    public String getFiyat() {
        return fiyat;
    }
    public void setFiyat(String fiyat) {
        this.fiyat = fiyat;
    }
    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getStokSuan() {
        return stokSuan;
    }
    public void setStokSuan(String stokSuan) {
        this.stokSuan = stokSuan;
    }
    public String getStokGelecek() {
        return stokGelecek;
    }
    public void setStokGelecek(String stokGelecek) {
        this.stokGelecek = stokGelecek;
    }
    public String getMarka() {
        return marka;
    }
    public void setMarka(String marka) {
        this.marka = marka;
    }
    public String getAd() {
        return ad;
    }
    public void setAd(String ad) {
        this.ad = ad;
    }
}
