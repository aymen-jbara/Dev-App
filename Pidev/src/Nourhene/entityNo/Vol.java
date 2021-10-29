package Nourhene.entityNo;

import java.util.Date;

public class Vol  {
    private String nomv;
    private String numv;
    private Date dated;
    private Date datea;
    private String chauffeure;
    private String depart;
    private String arriver;
    private String prix;



    public Vol(String nomv, String numv, Date dated, Date datea, String chauffeure, String depart, String arriver, String prix) {
        this.nomv= nomv;
        this.numv= numv;
        this.dated = dated;
        this.datea = datea;
        this.chauffeure = chauffeure;
        this.depart = depart ;
        this.arriver = arriver;

        this.prix = prix;
    }
    public String getPrix() { return prix; }

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

    public Date getDated() {
        return dated;
    }

    public void setDated(Date dated) {
        this.dated = dated;
    }

    public Date getDatea() {
        return datea;
    }

    public void setDatea(Date datea) {
        this.datea = datea;
    }

    public String getChauffeure() {
        return chauffeure;
    }

    public void setChauffeure(String chauffeure) {
        this.chauffeure = chauffeure;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getArriver() {
        return arriver;
    }

    public void setArriver(String arriver) {
        this.arriver = arriver;
    }

    @Override
    public String toString() {
        return "Vol{" +
                "nomv='" + nomv + '\'' +
                ", numv='" + numv + '\'' +
                ", dated=" + dated +
                ", datea=" + datea +
                ", chauffeure='" + chauffeure + '\'' +
                ", depart='" + depart + '\'' +
                ", arriver='" + arriver + '\'' +


                '}';
    }
}