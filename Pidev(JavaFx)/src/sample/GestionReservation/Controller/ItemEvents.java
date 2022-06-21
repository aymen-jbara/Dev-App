package sample.GestionReservation.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sample.GestionOffre.models.Favoris;
import sample.GestionReservation.Services.MyListener;
import sample.GestionReservation.model.Event;
import sample.GestionReservation.model.Transport;
import sample.connection.DataBase;
import sample.main.Main;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;


public class ItemEvents implements Initializable {

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;

    @FXML
    private Label idlab;

    @FXML
    private ImageView nbrlike;

   private int likeoff = 0;


    PreparedStatement preparedStatement;
    Connection connection;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickk3(event);


    }
    private Event event;

    private MyListener myListener;


    private int nblike=0;


    public void setChosenEvents(Event event, MyListener myListener) {
        this.event = event;
        this.myListener = myListener;
        nameLabel.setText(event.getName());
        priceLable.setText(Main.CURRENCY + event.getPrix());
        idlab.setText(event.getId());
       // numObject(countlike(transport.getReference()));
       //nblike = countlike(transport.getReference());
       // System.out.println(nblike);
        Image image = new Image("file:///"+event.getLocation()+"");
        img.setImage(image);
    }

/*



    public int countlike(String id)
    {

        int nb=0;
        String query = "SELECT * FROM fav WHERE idoffre = '"+id+"' ";
        Statement st;
        ResultSet rs;

        try {
            st= connection.createStatement();
            rs =st.executeQuery(query);

            while (rs.next()){
             nb = rs.getInt("VL");
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return nb;

    }

    public String random_id() {

        Random random = new Random();

        String generatedString = random.ints(6,65,90)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();


        return generatedString;
    }



    public ItemEvents() { connection = (Connection) DataBase.conDB();}








   private String id;
    @FXML
    void likebtn(MouseEvent event) {

        likeoff++;
        System.out.println(nblike);
       numObject(likeoff);
       // nblike+=likeoff;
        System.out.println("id offres est : " + transport.getReference() + " nbr like : " + likeoff + " ");

        id= random_id();

            if (likeoff==1) {

                try {


                    String st = "insert into fav (idFav,idoffre,VL) values(?,?,?)";
                    preparedStatement = (PreparedStatement) connection.prepareStatement(st);
                    preparedStatement.setString(1, id);
                    preparedStatement.setString(2, transport.getReference());
                    preparedStatement.setInt(3, likeoff);


                    preparedStatement.executeUpdate();
                    preparedStatement.close();

                } catch (Exception e) {
                    System.out.println();
                }
            }else if(likeoff>1)
            {
                try {

                    String st = "UPDATE fav SET   VL= ?  WHERE  idoffre= ?";
                    preparedStatement = (PreparedStatement) connection.prepareStatement(st);

                    preparedStatement.setInt(1, likeoff);
                    preparedStatement.setString(2, transport.getReference());

                    preparedStatement.executeUpdate();
                    preparedStatement.close();

                } catch (Exception e) {
                    System.out.println();
                }
            }

    }

    Integer Like;
    public ObservableList<Favoris> GETLIke(){

        ObservableList<Favoris> likeList = FXCollections.observableArrayList();


        String query = "SELECT * FROM fav";
        Statement st;
        ResultSet rs;
        try {
            st= connection.createStatement();
            rs =st.executeQuery(query);
            Favoris liks;
            while (rs.next()){
                liks =new Favoris(rs.getString("idFav"),rs.getString("idoffre"),rs.getInt("VL"),rs.getDate("datelike").toLocalDate());
                likeList.add(liks);
                Like= rs.getInt("VL");
              //  numObject(Like);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return likeList;

    }






    public void numObject(int nbr)
    {



        if(nbr==11)
            nbr=0;

        if(nbr==1)
        {
            nbrlike.setVisible(true);
            nbrlike.setImage(new Image("file:///C:\\Users\\LEGION-5\\Documents\\GitHub\\offres-reservation_integration\\offres\\src\\sample\\GestionReservation\\img\\1_50px.png"));
        }else if(nbr==2)
        {
            nbrlike.setImage(new Image("file:///C:\\Users\\LEGION-5\\Documents\\GitHub\\offres-reservation_integration\\offres\\src\\sample\\GestionReservation\\img\\2_50px.png"));
    }else if(nbr==3)
        {
            nbrlike.setImage(new Image("file:///C:\\Users\\LEGION-5\\Documents\\GitHub\\offres-reservation_integration\\offres\\src\\sample\\GestionReservation\\img\\3_50px.png"));
        }else if(nbr==4)
        {
            nbrlike.setImage(new Image("file:///C:\\Users\\LEGION-5\\Documents\\GitHub\\offres-reservation_integration\\offres\\src\\sample\\GestionReservation\\img\\4_50px.png"));
        }else if(nbr==5)
        {
            nbrlike.setImage(new Image("file:///C:\\Users\\LEGION-5\\Documents\\GitHub\\offres-reservation_integration\\offres\\src\\sample\\GestionReservation\\img\\5_50px.png"));
        }else if(nbr==6)
        {
            nbrlike.setImage(new Image("file:///C:\\Users\\LEGION-5\\Documents\\GitHub\\offres-reservation_integration\\offres\\src\\sample\\GestionReservation\\img\\6_50px.png"));
        }else if(nbr==7)
        {
            nbrlike.setImage(new Image("file:///C:\\Users\\LEGION-5\\Documents\\GitHub\\offres-reservation_integration\\offres\\src\\sample\\GestionReservation\\img\\7_50px.png"));
        }else if(nbr==8)
        {
            nbrlike.setImage(new Image("file:///C:\\Users\\LEGION-5\\Documents\\GitHub\\offres-reservation_integration\\offres\\src\\sample\\GestionReservation\\img\\8_50px.png"));
        }else if(nbr==9)
        {
            nbrlike.setImage(new Image("file:///C:\\Users\\LEGION-5\\Documents\\GitHub\\offres-reservation_integration\\offres\\src\\sample\\GestionReservation\\img\\9_50px.png"));
        }else if(nbr==10)
        {

            nbrlike.setImage(new Image("file:///C:\\Users\\LEGION-5\\Documents\\GitHub\\offres-reservation_integration\\offres\\src\\sample\\GestionReservation\\img\\10_50px.png"));

        }else if(nbr == 0)
        {
            nbrlike.setVisible(false);
        }
    }
*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

       // numObject();
    }

    public void likebtn(MouseEvent mouseEvent) {
    }
}
