package blctelkom.id.srimandali.Menu.Constructor;

/**
 * Created by waski on 30/05/2018.
 */

public class KameraPengamatConstructor {
    public String namaTempat;
    public String urlGambar;

    public KameraPengamatConstructor(String namaTempat, String urlGambar) {
        this.namaTempat = namaTempat;
        this.urlGambar = urlGambar;
    }

    public String getNamaTempat() {
        return namaTempat;
    }

    public void setNamaTempat(String namaTempat) {
        this.namaTempat = namaTempat;
    }

    public String getUrlGambar() {
        return urlGambar;
    }

    public void setUrlGambar(String urlGambar) {
        this.urlGambar = urlGambar;
    }
}
