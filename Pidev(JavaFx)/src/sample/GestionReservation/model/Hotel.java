package sample.GestionReservation.model;

import java.util.Date;

public class Hotel {
    private  String id;
    private  String nom;
    private int etoile;
    private  String lieu;
    private String hebergement;
    private  String Path_image ;
    private  String Path_video ;
    private String chambre ;
    private double prix;
    private Date dateValidation;


    public Hotel(String id, String nom, int etoile, String lieu, String hebergement, String path_image, String path_video, String chambre,double prix, Date dateValidation) {
        this.id = id;
        this.nom = nom;
        this.etoile = etoile;
        this.lieu = lieu;
        this.hebergement = hebergement;
        this.Path_image = path_image;
        this.Path_video = path_video;
        this.chambre = chambre;
        this.prix= prix;
        this.dateValidation=dateValidation;

    }


    public String getPath_image() {
        return Path_image;
    }

    public String getPath_video() {
        return Path_video;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getChambre() {
        return chambre;
    }

    public int getEtoile() {
        return etoile;
    }

    public String getLieu() {
        return lieu;
    }

    public String getHebergement() {
        return hebergement;
    }

    public double getPrix() {
        return prix;
    }

    public Date getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(Date dateValidation) {
        this.dateValidation = dateValidation;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", etoile=" + etoile +
                ", lieu='" + lieu + '\'' +
                ", hebergement='" + hebergement + '\'' +
                ", Path_image='" + Path_image + '\'' +
                ", Path_video='" + Path_video + '\'' +
                ", chambre='" + chambre + '\'' +
                ", prix=" + prix +
                '}';
    }
}
