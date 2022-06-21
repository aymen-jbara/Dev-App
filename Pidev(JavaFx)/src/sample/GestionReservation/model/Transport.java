package sample.GestionReservation.model;

public class Transport {
    private String reference;
    private String typee;
    private String availability;
    private String driver;
    private String aprtir;
    private String vers;

    public Transport(String reference, String typee, String availability, String driver, String aprtir, String vers) {
        this.reference = reference;
        this.typee = typee;
        this.availability = availability;
        this.driver = driver;
        this.aprtir = aprtir;
        this.vers = vers;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getTypee() {
        return typee;
    }

    public void setTypee(String typee) {
        this.typee = typee;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getAprtir() {
        return aprtir;
    }

    public void setAprtir(String aprtir) {
        this.aprtir = aprtir;
    }

    public String getVers() {
        return vers;
    }

    public void setVers(String vers) {
        this.vers = vers;
    }
}
