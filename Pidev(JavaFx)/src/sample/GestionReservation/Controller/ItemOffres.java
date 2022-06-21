package sample.GestionReservation.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import sample.GestionOffre.models.Offre;
import sample.GestionReservation.Services.MyListener;
import sample.connection.DataBase;

import javax.print.attribute.standard.Media;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;


public class ItemOffres implements Initializable {




    @FXML
    private ImageView img;



    @FXML
    private ImageView nbrlike;

   private int likeoff = 0;


    PreparedStatement preparedStatement;
    Connection connection;


    @FXML
    private MediaView mediaView;
    @FXML
    private Button playButton,pauseButton,resetButton;
    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;








    @FXML
    void paginationClick(ActionEvent event)

    {

        Stage primaryStage = new Stage();


        AnchorPane anchor = new AnchorPane();

        ArrayList<String> images = new ArrayList<>();
        images.add("sample/GestionOffre/Images/dub.jpg");
        images.add("sample/GestionOffre/Images/dub2.jpg");
        images.add("sample/GestionOffre/Images/dub6.jpg");
        images.add("sample/GestionOffre/Images/dub5.jpg");




        javafx.scene.control.Pagination p = new javafx.scene.control.Pagination(4);
        p.setPageFactory(n -> new ImageView(images.get(n)));

        AnchorPane.setTopAnchor(p,10.0);
        AnchorPane.setBottomAnchor(p,10.0);
        AnchorPane.setLeftAnchor(p,10.0);
        AnchorPane.setRightAnchor(p,10.0);

        p.setStyle("-fx-border-color: pink");

        anchor.getChildren().add(p);
        Scene scene = new Scene(anchor,400,300);



        primaryStage.setScene(scene);
        primaryStage.show();








    }



    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickk2(offres);


    }
    private Offre offres;

    private MyListener myListener;


    private int nblike=0;



    public void setDataOffres(Offre offres, MyListener myListener) {
        this.offres = offres;
        this.myListener = myListener;

        numObject(countlike(offres.getIdoffre()));
        nblike = countlike(offres.getIdoffre());

      //  numObject(countlike(transport.getReference()));
    //   nblike = countlike(transport.getReference());
       // System.out.println(nblike);
        
        Image image = new Image("file:///"+offres.getPath_image()+"");
        img.setImage(image);
    }





    public int countlike(String id)
    {

        int nb=0;
        String query = "SELECT * FROM fav WHERE idoffre = '"+id+ "' ";
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



    public ItemOffres() { connection = (Connection) DataBase.conDB();}








   private String id;
    @FXML
    void likebtn(MouseEvent event) {

        nblike++;
        System.out.println(nblike);
        numObject(nblike);
       // nblike+=likeoff;
        System.out.println("id offres est : " + offres.getIdoffre() + " nbr like : " + nblike + " ");

        id= random_id();

            if (nblike==1) {

                try {


                    String st = "insert into fav (idFav,idoffre,VL) values(?,?,?)";
                    preparedStatement = (PreparedStatement) connection.prepareStatement(st);
                    preparedStatement.setString(1, id);
                    preparedStatement.setString(2, offres.getIdoffre());
                    preparedStatement.setInt(3, nblike);


                    preparedStatement.executeUpdate();
                    preparedStatement.close();

                } catch (Exception e) {
                    System.out.println();
                }
            }else if(nblike>1)
            {
                try {

                    String st = "UPDATE fav SET   VL= ?  WHERE  idoffre= ?";
                    preparedStatement = (PreparedStatement) connection.prepareStatement(st);

                    preparedStatement.setInt(1, nblike);
                    preparedStatement.setString(2, offres.getIdoffre());

                    preparedStatement.executeUpdate();
                    preparedStatement.close();

                } catch (Exception e) {
                    System.out.println();
                }
            }

    }

    /*
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
*/





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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

       // numObject();
    }

    public void videoAction(ActionEvent actionEvent) {
    }
}
