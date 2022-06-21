package sample.GestionReservation.model;

public class detailReservation {
private String idR;
private String idRes;
private String idoffre;
private String idCli;
private String idevent;

    public detailReservation(String idR, String idRes, String idoffre, String idCli, String idevent) {
        this.idR = idR;
        this.idRes = idRes;
        this.idoffre = idoffre;
        this.idCli = idCli;
        this.idevent = idevent;
    }

    public String getIdR() {
        return idR;
    }

    public void setIdR(String idR) {
        this.idR = idR;
    }

    public String getIdRes() {
        return idRes;
    }

    public void setIdRes(String idRes) {
        this.idRes = idRes;
    }

    public String getIdoffre() {
        return idoffre;
    }

    public void setIdoffre(String idoffre) {
        this.idoffre = idoffre;
    }

    public String getIdCli() {
        return idCli;
    }

    public void setIdCli(String idCli) {
        this.idCli = idCli;
    }

    public String getIdevent() {
        return idevent;
    }

    public void setIdevent(String idevent) {
        this.idevent = idevent;
    }
}
