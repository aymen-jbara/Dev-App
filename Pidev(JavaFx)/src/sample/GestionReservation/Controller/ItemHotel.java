package sample.GestionReservation.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sample.main.Main;
import sample.GestionReservation.Services.MyListener;

import sample.GestionReservation.model.Hotel;

public class ItemHotel {

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;

    @FXML
    private Label idlab;



    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickk(hotel);


    }
    private Hotel hotel;

    private MyListener myListener;




    public void setDataH(Hotel hotel, MyListener myListener) {
        this.hotel = hotel;
        this.myListener = myListener;
        nameLabel.setText(hotel.getNom());
        priceLable.setText(Main.CURRENCY + hotel.getPrix());
        idlab.setText(hotel.getId());
      //  Image image = new Image("file:///"+hotel.getPath_image()+"");
       // img.setImage(image);
    }



}
