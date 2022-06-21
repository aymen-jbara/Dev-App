package sample.GestionReservation.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import sample.GestionReservation.model.Event;
import sample.Sound;
import sample.connection.DataBase;

import java.io.File;
import java.net.URL;
import java.sql.*;

import java.util.ResourceBundle;

public class ConfirmationReservation implements Initializable {


    @FXML
    private Label idoffres;
    @FXML
    private Label idVol;
    @FXML
    private Label idHotel;
    @FXML
    private Label idEvent;
    @FXML
    private Label idTrans;


    private File file;
    private MediaPlayer mm;
    private Media m;

    private File file2;
    private MediaPlayer mm2;
    private Media m2;

    @FXML
    private TextField N_carte;



    private int prixV;
    private int prixH;
    private int prixT;
    private int prixE;
    private int prixO;



    public ConfirmationReservation() {connection = (Connection) DataBase.conDB();
    }
    Connection connection;
    PreparedStatement preparedStatement;



    private String idDetail_Reserv;
    private String idH;
    private String idO;
    private String idE;
    private String idV;
    private String idT;
    private String idR;

    public void btnConfirmation(ActionEvent actionEvent) {
        MarketController m = new MarketController();

        System.out.println(calculPrixT(prixV,prixT,prixH,prixO,prixO));
        if(!N_carte.getText().isEmpty()) {
            try {

                String st = "INSERT INTO detail_reser ( iddetail,idH,idT,idV,idOff, idCli,idevent,prixT) VALUES (?,?,?,?,?,?,?,?)";
                preparedStatement = (PreparedStatement) connection.prepareStatement(st);
                idDetail_Reserv = m.random_id();
                preparedStatement.setString(1, idDetail_Reserv);
                preparedStatement.setString(2, idH);
                preparedStatement.setString(3, idT);
                preparedStatement.setString(4, idV);
                preparedStatement.setString(5, idO);
                preparedStatement.setString(6, N_carte.getText());
                preparedStatement.setString(7, idE);
                preparedStatement.setInt(8, Integer.valueOf(calculPrixT(prixV,prixT,prixH,prixO,prixO)));


                preparedStatement.executeUpdate();
                preparedStatement.close();

                m.Notification("Success Reservation", "Merci ", "SUCCESS");
                m.goodSound();

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());

            }
        }
        else
        {
            m.Notification("", "Event", "ERROR");
            m.badSound();

        }

    }





    public void btnbachReservation(ActionEvent actionEvent) throws Exception{

        Stage stage = (Stage) idTrans.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/sample/GestionReservation/views/market.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        MarketController.wew();

        idE = MarketController.idEv();
        idH = MarketController.idHo();
        idO = MarketController.idOf();
        idT = MarketController.idTr();
        idV = MarketController.idVo();
        idR = MarketController.idRe();

        idEvent.setText(idE);
        idHotel.setText(idH);
        idoffres.setText(idO);
        idTrans.setText(idT);
        idVol.setText(idV);

        System.out.println(idE);
        System.out.println(idH);
        System.out.println(idO);
        System.out.println(idT);
        System.out.println(idV);
        System.out.println(idR);

        getIformation();


    }









    public void getIformation() {




        String queryVol = "select * from vol WHERE numv='"+idV+"'";

        String queryTra = "select * from reference WHERE prix='"+idT+"'";
        String queryHot = "select * from hotel WHERE idH='"+idH+"'";
        String queryOff = "select * from offre WHERE idoffre'"+idO+"'";
        String queryEvent = "select * from vol WHERE idevent='"+idE+"'";

        Statement st;
        ResultSet rs;


        try {
            st = connection.createStatement();
            rs = st.executeQuery(queryVol);

            while (rs.next()) {
           prixV =  rs.getInt("prix");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            st = connection.createStatement();
            rs = st.executeQuery(queryTra);

            while (rs.next()) {

            prixT =  rs.getInt("prix");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            st = connection.createStatement();
            rs = st.executeQuery(queryHot);

            while (rs.next()) {
            prixH =  rs.getInt("prix");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            st = connection.createStatement();
            rs = st.executeQuery(queryOff);

            while (rs.next()) {

            prixO = rs.getInt("prix");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            st = connection.createStatement();
            rs = st.executeQuery(queryEvent);

            while (rs.next()) {

           prixE =  rs.getInt("prix");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    int prixTotale=0;
    public int calculPrixT(int v,int t,int h,int o,int e)
    {

        prixTotale = v+t+h+o+e;
        return prixTotale;
    }




}
