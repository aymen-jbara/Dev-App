package sample.GestionReservation.model;

import java.util.Date;

public class Vol {
private String nomv;
private String numv;
private String apartir;
private String vers;
private Date dated;
private String chouffeur;


    public Vol(String nomv, String numv, String apartir, String vers, Date dated, String chouffeur) {
        this.nomv = nomv;
        this.numv = numv;
        this.apartir = apartir;
        this.vers = vers;
        this.dated = dated;
        this.chouffeur = chouffeur;
    }

    public String getNomv() {
        return nomv;
    }

    public void setNomv(String nomv) {
        this.nomv = nomv;
    }

    public String getNumv() {
        return numv;
    }

    public void setNumv(String numv) {
        this.numv = numv;
    }

    public String getApartir() {
        return apartir;
    }

    public void setApartir(String apartir) {
        this.apartir = apartir;
    }

    public String getVers() {
        return vers;
    }

    public void setVers(String vers) {
        this.vers = vers;
    }

    public Date getDated() {
        return dated;
    }

    public void setDated(Date dated) {
        this.dated = dated;
    }

    public String getChouffeur() {
        return chouffeur;
    }

    public void setChouffeur(String chouffeur) {
        this.chouffeur = chouffeur;
    }
}
