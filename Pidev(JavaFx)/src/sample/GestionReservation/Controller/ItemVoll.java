package sample.GestionReservation.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sample.GestionReservation.Services.MyListener;
import sample.GestionReservation.model.Transport;
import sample.GestionReservation.model.Vol;
import sample.main.Main;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;


public class ItemVoll implements Initializable {

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
        myListener.onClickListener(vols);


    }
    private Vol vols;

    private MyListener myListener;




    public void setData(Vol vols, MyListener myListener) {
        this.vols = vols;
        this.myListener = myListener;
        nameLabel.setText(vols.getNomv());
        priceLable.setText(Main.CURRENCY + vols.getChouffeur());
        idlab.setText(vols.getNumv());
        //Image image = new Image("file:///"+fruit.getImgSrc()+"");
        //img.setImage(image);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void likebtn(MouseEvent mouseEvent) {
    }


/*
    public int countlike(int id)
    {
        int nb=0;
        String query = "SELECT * FROM fav WHERE idoffre="+id+ " ";
        Statement st;
        ResultSet rs;
        try {
            st= connection.createStatement();
            rs =st.executeQuery(query);

            while (rs.next()){
             nb+= rs.getInt("VL");
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



    public ItemFruit() { connection = (Connection) DataBase.conDB();}









    @FXML
    void likebtn(MouseEvent event) {

        likeoff++;
        numObject(likeoff);
        System.out.println("id offres est : " + transport.getReference() + " nbr like : " + likeoff + " ");




            try{
                String st="insert into fav (idFav,idCli,idoffre,VL) values(?,?,?,?)";
                preparedStatement=(PreparedStatement)connection.prepareStatement(st);
                preparedStatement.setString(1,random_id());
                preparedStatement.setString(2,"xxx");
                preparedStatement.setString(3,transport.getReference());
                preparedStatement.setInt(4,likeoff);


                preparedStatement.executeUpdate();
                preparedStatement.close();

        } catch (Exception e)
        {
            System.out.println();
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
                liks =new Favoris(rs.getString("idFav"),rs.getString("idoffre"),rs.getString("idCli"),rs.getInt("VL"),rs.getDate("datelike").toLocalDate());
                likeList.add(liks);
                Like= rs.getInt("VL");
                numObject(Like);
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       ObservableList<Favoris> fv = GETLIke();

        System.out.println(countlike(2));
//numObject(Like);

    }*/
}
