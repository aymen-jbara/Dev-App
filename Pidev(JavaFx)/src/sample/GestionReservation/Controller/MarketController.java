package sample.GestionReservation.Controller;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

import sample.GestionOffre.models.Offre;


import sample.GestionReservation.model.Event;
import sample.GestionReservation.model.Transport;
import sample.GestionReservation.model.Vol;

import sample.connection.DataBase;
import sample.main.Main;
import sample.GestionReservation.Services.MyListener;
import sample.GestionReservation.model.Hotel;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.io.IOException;
import java.net.URL;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MarketController implements Initializable {



    @FXML
    private VBox chosenFruitCard;

    @FXML
    private Button aller_ret;

    @FXML
    private Label vers;


    private File file;
    private MediaPlayer mm;
    private Media m;

    private File file2;
    private MediaPlayer mm2;
    private Media m2;

    @FXML
    private Button tran;

    @FXML
    private Button vol;
    @FXML
    private Button hot;
    @FXML
    private Button even;
    @FXML
    private Button off;

    @FXML
    private Label fruitNameLable;

    @FXML
    private ImageView numObj;

    @FXML
    private Label fruitPriceLabel;

    public MarketController() {
        connection = (Connection) DataBase.conDB();
    }

    @FXML
    private ImageView fruitImg;

    @FXML
    private Label connlabel;
    @FXML
    private ScrollPane scroll;

    @FXML
    private TextField textrech;

    @FXML
    private Button btnrech;

    @FXML
    private Label idlab;

    @FXML
    private GridPane grid;

    @FXML
    private Button nbrEtoile;

    @FXML
    private ComboBox coboType;

    @FXML
    private ComboBox cobonbr;
    @FXML
    private ImageView QRcode;

    @FXML
    private Button idaddtocar;

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    ObservableList<LocalDate> selectedDates = FXCollections.observableArrayList();


    @FXML
    private TextField textrechprice;

    @FXML
    private TextField textrechcolor;


    @FXML
    private DatePicker dateD;




    int nbrObj =0;
    int nbrLike = 0;
    private static String pathvideo;




    private static String idVolReserv="null";
    private static String idHotelReserv="null";
    private static String idTransReserv="null";
    private static String idOffresReserv="null";
    private static String idOEventsReserv="null";
    private static String idReservation="null";


    PreparedStatement preparedStatement;

    ObservableList<Vol> dataListVol;
    ObservableList<Offre> dataListOff;
    ObservableList<Hotel> dataListHotel;
    ObservableList<Transport> dataListTrans;
    ObservableList<Event> dataListEvents;
    ObservableList<Event> dataListEvents2;


    ObservableList<Hotel> trieListeHotel;
    ObservableList<Event> trieListeEvent;
    ObservableList<Offre> trieListeOffres;




    List panier = new ArrayList();

    ItemVoll itemVoll;
    ItemHotel itemHotel;
    ItemTransport itemTransport;
    ItemEvents itemEvents;
    ItemOffres itemOffres;



    String IdHotel="null",Ref="null",numV="null",idOff="null",idEvent="null",idReserv="null";

    Connection connection;



    private Image image;
    private MyListener myListHotel;
    private MyListener myListTrans;
    private MyListener myListVOL;
    private MyListener myListOffres;
    private MyListener myListEvents;



    public String random_id() {

        Random random = new Random();

        String generatedString = random.ints(6,65,90)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();


        return generatedString;
    }
    @FXML
    protected void qrcode()
    {



        if (pathvideo.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Empty URL");
            alert.setContentText("Please input URL");
            alert.showAndWait();
            return;
        }


        try {
            final BufferedImage bi = generateData(pathvideo);
            WritableImage img = SwingFXUtils.toFXImage(bi, null);
            QRcode.setFitWidth(img.getWidth());
            QRcode.setFitHeight(img.getHeight());
            QRcode.setImage(img);
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    protected void handleSaveAs(ActionEvent event)
    {


        if (QRcode.getImage() == null) {
            if (pathvideo.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("No QR code generated");
                alert.setContentText("Make sure you have generated a QR code before saving");
                alert.showAndWait();
                return;
            }
            return;
        }

        Button btn = (Button) event.getSource();

        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image","*.png","*.jpg"));

        File file = chooser.showSaveDialog(btn.getScene().getWindow());
        if (file == null) {
            return;
        }

        Alert alert;
        try {
            Image img = QRcode.getImage();
            BufferedImage bi = SwingFXUtils.fromFXImage(img, null);
            if (file.getName().toLowerCase().endsWith("png")) {
                ImageIO.write(bi, "png", file);
            } else {
                ImageIO.write(bi, "jpg", file);
            }
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("File saved!");
            alert.showAndWait();
        } catch (Exception ex) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error saving image");
            alert.showAndWait();
        }
    }



    private BufferedImage generateData(final String url) throws IOException, WriterException {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 153, 124);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            BufferedImage bi = MatrixToImageWriter.toBufferedImage(bitMatrix);
            return bi;
        } catch (Exception ex)
        {
            throw ex;
        }
    }



    public void executeQuery(String query) {

        Statement st;
        try {
            st = connection.createStatement();
            st.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ObservableList<Hotel> getHotelList() {
        ObservableList<Hotel> hotelList = FXCollections.observableArrayList();


        String query = "select * from hotel WHERE dateValidation BETWEEN '"+selectedDates.toArray()[0]+"' AND '"+selectedDates.toArray()[1]+"' ";

        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Hotel hotels;

            while (rs.next()) {
                hotels = new Hotel(rs.getString("idH"), rs.getString("nom"), rs.getInt("etoile"), rs.getString("hebergement"), rs.getString("lieu"), rs.getString("Path_image"), rs.getString("Path_video"), rs.getString("chambre"), rs.getDouble("prix"),rs.getDate("dateValidation"));

                hotelList.add(hotels);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotelList;
    }

    public ObservableList<Hotel> getHotelList2() {
        ObservableList<Hotel> hotelList = FXCollections.observableArrayList();


        String query = "select * from hotel ";

        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Hotel hotels;

            while (rs.next()) {
                hotels = new Hotel(rs.getString("idH"), rs.getString("nom"), rs.getInt("etoile"), rs.getString("hebergement"), rs.getString("lieu"), rs.getString("Path_image"), rs.getString("Path_video"), rs.getString("chambre"), rs.getDouble("prix"),rs.getDate("dateValidation"));

                hotelList.add(hotels);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotelList;
    }


    public ObservableList<Vol> getVolList() {

        ObservableList<Vol> voltList = FXCollections.observableArrayList();



        String query = "select * from vol WHERE dated BETWEEN '"+selectedDates.toArray()[0]+"' AND '"+selectedDates.toArray()[1]+"'";
        System.out.println(query);

        Statement st;
        ResultSet rs;

        try {

            st = connection.createStatement();
            rs = st.executeQuery(query);
            Vol vol;

            while (rs.next()) {
                vol = new Vol(rs.getString("nomv"), rs.getString("numv"), rs.getString("apartir"),rs.getString("vers"), rs.getDate("dated"), rs.getString("chouffeur"));

                voltList.add(vol);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return voltList;
    }

    public ObservableList<Vol> getVolList2() {
        ObservableList<Vol> voltList = FXCollections.observableArrayList();



        String query = "select * from vol";
        System.out.println(query);

        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Vol vol;

            while (rs.next()) {
                vol = new Vol(rs.getString("nomv"), rs.getString("numv"), rs.getString("apartir"),rs.getString("vers"), rs.getDate("dated"), rs.getString("chauffeur"));

                voltList.add(vol);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return voltList;
    }



    public ObservableList<Transport> getTransList() {
        ObservableList<Transport> TransporttList = FXCollections.observableArrayList();


        //  String query = "SELECT name ,imgSrc,price,color FROM resevation r join fruit f on (f.idF=r.idF) ";
        String query = "select * from transport WHERE date BETWEEN '"+selectedDates.toArray()[0]+"' AND '"+selectedDates.toArray()[1]+"' ";


        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Transport trans;

            while (rs.next()) {
                trans = new Transport(rs.getString("reference"), rs.getString("typee"), rs.getString("availability"), rs.getString("driver"), rs.getString("apartir"), rs.getString( "vers") );
                TransporttList.add(trans);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TransporttList;
    }

    public ObservableList<Transport> getTransList2() {
        ObservableList<Transport> TransporttList = FXCollections.observableArrayList();


        //  String query = "SELECT name ,imgSrc,price,color FROM resevation r join fruit f on (f.idF=r.idF) ";
        String query = "select * from transport ";


        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Transport trans;

            while (rs.next()) {
                trans = new Transport(rs.getString("reference"), rs.getString("typee"), rs.getString("availability"), rs.getString("driver"), rs.getString("apartir"), rs.getString( "vers") );
                TransporttList.add(trans);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TransporttList;
    }


    /*********************** Event *************************/

    void trieparEvents()
    {
        trieListeHotel = getHotelList();
        List<Hotel> r = trieListeHotel.stream().sorted(Comparator.comparingInt(Hotel::getEtoile).reversed()).collect(Collectors.toList());
        grid.getChildren().clear();
        afficherHotels(r);
    }
    void trieparEvents2()
    {
        trieListeHotel = getHotelList2();
        List<Hotel> r = trieListeHotel.stream().sorted(Comparator.comparingInt(Hotel::getEtoile).reversed()).collect(Collectors.toList());
        grid.getChildren().clear();
        afficherHotels(r);
    }

    @FXML
    void trieparEtoile(ActionEvent event) {

            even.setOnAction(t-> {
                if(!selectedDates.isEmpty())
                {
                    trieparEtoile();
                }else {
                    trieparEtoile2();
                }
        });

            off.setOnAction( t -> {
                if(!selectedDates.isEmpty())
                {
                    trieparEvents();
                }else {
                    trieparEvents2();
                }

            });



    }



    public ObservableList<Event> getEventList() {

        ObservableList<Event> eventsList = FXCollections.observableArrayList();


        String query = "select * from events";

        Statement st;
        ResultSet rs;


        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Event events;

            while (rs.next()) {

                events = new Event(rs.getString("idevent"), rs.getString("name"), rs.getString("period"), rs.getString("location"), rs.getDate("date"), rs.getString("available"),rs.getInt("prix"));
                eventsList.add(events);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eventsList;
    }



    public ObservableList<Event> getEventeList2() {

        ObservableList<Event> eventsList = FXCollections.observableArrayList();

        String query = "select * from events WHERE date BETWEEN '"+selectedDates.toArray()[0]+"' AND '"+selectedDates.toArray()[1]+"' ";

        Statement st;
        ResultSet rs;


        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Event events;

            while (rs.next()) {

                events = new Event(rs.getString("idevent"), rs.getString("name"), rs.getString("period"), rs.getString("location"), rs.getDate("date"), rs.getString("available"),rs.getInt("prix"));
                eventsList.add(events);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eventsList;
    }

    private void setChosenEvents(Event events) {
        fruitNameLable.setText(events.getName());
        fruitPriceLabel.setText(Main.CURRENCY + events.getPrix());

        image = new Image("file:///" + events.getLocation() + "");
        fruitImg.setImage(image);


        chosenFruitCard.setStyle("-fx-background-color: #9DA4A4  ;\n" +
                "    -fx-background-radius: 30;");

    }

    void search_Events() {

        dataListEvents = getEventList();



        Predicate<Event> byname = name -> name.getName().toLowerCase().contains(textrech.getText().toLowerCase());

        List<Event> result = dataListEvents.stream().filter(byname)
                 .collect(Collectors.toList());
        List<Event> r = dataListEvents.stream().collect(Collectors.toList());





        System.out.println(result);
        if (textrech.getText().isEmpty()) {
            if(selectedDates.isEmpty())
            {
                grid.getChildren().clear();
                afficherEvents(r);
            }else
            {
                dataListEvents2 = getEventeList2();
                List<Event> r2 = dataListEvents2.stream().collect(Collectors.toList());


                grid.getChildren().clear();
                afficherEvents(r2);
            }
        } else {

            if(selectedDates.isEmpty())
            {
                grid.getChildren().clear();
                afficherEvents(result);
            }else
            {
               // dataListEvents2 = getEventeList2();
                List<Event> r3 = dataListEvents2.stream().filter(byname).collect(Collectors.toList());
                grid.getChildren().clear();
                afficherEvents(r3);
            }


        }

    }


    void afficherEvents(List<Event> events) {


        if (events.size() > 0) {
            setChosenEvents(events.get(0));


            myListEvents = new MyListener() {
                @Override
                public void onClickk2(Offre offres) {


                }

                @Override
                public void onClickk3(Event event) {
                    setChosenEvents(event);

                    idOEventsReserv = event.getIdevent();
                }

                @Override
                public void onClickListener(Vol vol) {

                }

                @Override
                public void onClickListener2(Transport transport) {

                }

                @Override
                public void onClickk(Hotel hotel) {

                }


            };

        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < events.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../views/Events.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                itemEvents= fxmlLoader.getController();
                itemEvents.setChosenEvents(events.get(i), myListEvents);

                if (column == 3) {
                    column = 0;
                    row++;
                }
                //  grid.getChildren();
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);


                GridPane.setMargin(anchorPane, new Insets(events.size()));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    /******************Offres**********************/


    public ObservableList<Offre> getOffreList() {

        ObservableList<Offre> OffresList = FXCollections.observableArrayList();


        String query = "select * from offre";

        Statement st;
        ResultSet rs;


        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Offre offres;

            while (rs.next()) {

                offres = new Offre(rs.getString("idoffre"), rs.getString("id_reservation"), rs.getDate("date_validite"), rs.getString("taux_de_remise"), rs.getString("description"), rs.getString("Path_image"), rs.getString("Path_video"), rs.getString("Titre"));
                OffresList.add(offres);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return OffresList;
    }



    public ObservableList<Offre> getOffreList2() {

        ObservableList<Offre> OffresList = FXCollections.observableArrayList();


        String query = "select * from offre WHERE date_validite BETWEEN '"+selectedDates.toArray()[0]+"' AND '"+selectedDates.toArray()[1]+"' ";

        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Offre offres;

            while (rs.next()) {
                offres = new Offre(rs.getString("idoffre"), rs.getString("id_reservation"), rs.getDate("date_validite"), rs.getString("taux_de_remise"), rs.getString("description"), rs.getString("Path_image"), rs.getString("Path_video"), rs.getString("Titre"));

                OffresList.add(offres);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return OffresList;
    }

    private void setChosenOffre(Offre offres) {
        fruitNameLable.setText(offres.getTitre());
        fruitPriceLabel.setText(Main.CURRENCY + offres.getTaux_de_remise());
        pathvideo = offres.getPath_video();
        image = new Image("file:///" + offres.getPath_image() + "");
        fruitImg.setImage(image);
        qrcode();


        chosenFruitCard.setStyle("-fx-background-color: #9DA4A4  ;\n" +
                "    -fx-background-radius: 30;");

    }

    void search_offres() {

        dataListOff = getOffreList2();

        List<Offre> r = dataListOff.stream().collect(Collectors.toList());
        grid.getChildren().clear();
        afficherOfrres(r);
       // System.out.println(r);

    }


    void afficherOfrres(List<Offre> offres) {


        if (offres.size() > 0) {
            setChosenOffre(offres.get(0));


            myListOffres = new MyListener() {
                @Override
                public void onClickk2(Offre offres) {
                    setChosenOffre(offres);

                    idOffresReserv = offres.getIdoffre();

                }

                @Override
                public void onClickk3(Event event) {

                }

                @Override
                public void onClickListener(Vol vol) {

                }

                @Override
                public void onClickListener2(Transport transport) {

                }

                @Override
                public void onClickk(Hotel hotel) {

                }


            };

        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < offres.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../views/Offes.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                itemOffres= fxmlLoader.getController();
                itemOffres.setDataOffres(offres.get(i), myListOffres);

                if (column == 3) {
                    column = 0;
                    row++;
                }
                //  grid.getChildren();
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);


                GridPane.setMargin(anchorPane, new Insets(offres.size()));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






/**************************************************/


    private void setChosenVol(Vol vol) {
        fruitNameLable.setText(vol.getNomv());
        fruitPriceLabel.setText(Main.CURRENCY + vol.getChouffeur());
        //image = new Image("file:///" + fruit.getImgSrc() + "");
        //fruitImg.setImage(image);
        chosenFruitCard.setStyle("-fx-background-color: #" + "9DA4A4" + ";\n" +
                "    -fx-background-radius: 30;");
    }

    private void setChosenTrans(Transport tras) {
        fruitNameLable.setText(tras.getTypee());
        fruitPriceLabel.setText(Main.CURRENCY + tras.getAvailability());
        //image = new Image("file:///" + fruit.getImgSrc() + "");
        //fruitImg.setImage(image);
        chosenFruitCard.setStyle("-fx-background-color: #" + "9DA4A4" + ";\n" +
                "    -fx-background-radius: 30;");
    }

    private void setChosenHotel(Hotel hotel) {
        fruitNameLable.setText(hotel.getNom());
        fruitPriceLabel.setText(Main.CURRENCY + hotel.getPrix());

        image = new Image("file:///" + hotel.getPath_image() + "");
        fruitImg.setImage(image);


        chosenFruitCard.setStyle("-fx-background-color: #9DA4A4  ;\n" +
                "    -fx-background-radius: 30;");

    }


    public static List<Date> getDaysBetweenDates(Date startdate, Date enddate)
    {
        List<Date> dates = new ArrayList<Date>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startdate);

        while (calendar.getTime().before(enddate))
        {
            Date result = (Date) calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }


    void search_vol() {


        dataListVol = getVolList();
        //System.out.println(textrech.getText());






     /*  List<LocalDate> dates = Stream.iterate(d, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(d, f))
                .collect(Collectors.toList());
        System.out.println(dates);*/


     //   Predicate<Fruit> byname = fruit -> fruit.getName().toLowerCase().contains(textrech.getText().toLowerCase());
        //  Predicate<Fruit> byprice = fruit -> fruit.getPrice() == Double.valueOf(textrechprice.getText()) ;
     //   Predicate<Fruit> bycolor = fruit -> fruit.getColor().toLowerCase().contains(textrechcolor.getText().toLowerCase());
       // Predicate<Fruit> bydate = date -> date.);



    //   List<Date> datee =  getDaysBetweenDates(d,f);
    //    System.out.println(datee.size());
/*
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = date1.plusDays(99);
 */
       // long diffInDays = ChronoUnit.DAYS.between(start, end);

      //  System.out.println(diffInDays);  // 99





        List<Vol> r = dataListVol.stream().collect(Collectors.toList());
        grid.getChildren().clear();
        affichervol(r);
        System.out.println(r);

     //   var result = dataList.stream().filter(byname)
       //         .collect(Collectors.toList());

      //  var result3 = dataList.stream().filter(byname).filter(bycolor)
      //          .collect(Collectors.toList());


/*
        System.out.println(result);
        if (textrech.getText().isEmpty()) {
            grid.getChildren().clear();
            afficher(r);

        } else {
            if (textrechprice.getText().isEmpty()) {
                if (textrechcolor.getText().isEmpty()) {


                    grid.getChildren().clear();
                    afficher(result);
                } else {
                    grid.getChildren().clear();
                    afficher(result3);
                }


            } else {
                Predicate<Fruit> byprice = fruit -> fruit.getPrice() == Double.valueOf(textrechprice.getText());

                if (textrechcolor.getText().isEmpty()) {
                    var result1 = dataList.stream().filter(byname).filter(byprice)
                            .collect(Collectors.toList());

                    grid.getChildren().clear();
                    afficher(result1);
                } else {
                    var result2 = dataList.stream().filter(byname).filter(bycolor).filter(byprice)
                            .collect(Collectors.toList());
                    grid.getChildren().clear();
                    afficher(result2);

                }
            }


        }

 */
    }
    void search_Transport() {

        dataListTrans = getTransList();



        System.out.println(textrech.getText());









          Predicate<Transport> byapartir = transport -> transport.getAprtir().toLowerCase().contains(textrech.getText().toLowerCase());
        //  Predicate<Fruit> byprice = fruit -> fruit.getPrice() == Double.valueOf(textrechprice.getText()) ;
          Predicate<Transport> byvers = transport -> transport.getVers().toLowerCase().contains(textrechcolor.getText().toLowerCase());









        List<Transport> r = dataListTrans.stream().collect(Collectors.toList());


          List<Transport> result = dataListTrans.stream().filter(byapartir)
                .collect(Collectors.toList());

         List<Transport> result3 = dataListTrans.stream().filter(byapartir).filter(byvers)
                 .collect(Collectors.toList());



        System.out.println(result);
        if (textrech.getText().isEmpty()) {
            grid.getChildren().clear();
            afficherTrans(r);

        } else {
                if (textrechcolor.getText().isEmpty()) {


                    grid.getChildren().clear();
                    afficherTrans(result);
                } else {
                    grid.getChildren().clear();
                    afficherTrans(result3);
                }





        }


    }

    void search_hotel() {

        dataListHotel = getHotelList();







        Predicate<Hotel> bylieu = hotel -> hotel.getNom().toLowerCase().contains(textrech.getText().toLowerCase());

        Predicate<Hotel> bychambre = hotel -> hotel.getChambre().toLowerCase().contains(textrechcolor.getText().toLowerCase());


        List<Hotel> r = dataListHotel.stream().collect(Collectors.toList());
        List<Hotel> result = dataListHotel.stream().filter(bylieu)
                .collect(Collectors.toList());

        List<Hotel> result3 = dataListHotel.stream().filter(bylieu).filter(bychambre)
                .collect(Collectors.toList());


        System.out.println(result);
        if (textrech.getText().isEmpty()) {
            grid.getChildren().clear();
            afficherHotels(r);

        } else {
            if (textrechprice.getText().isEmpty()) {
                if (textrechcolor.getText().isEmpty()) {


                    grid.getChildren().clear();
                    afficherHotels(result);
                } else {
                    grid.getChildren().clear();
                    afficherHotels(result3);
                }


            } else {
                Predicate<Hotel> byprix = hotel -> hotel.getPrix() == Double.valueOf(textrechprice.getText());

                if (textrechcolor.getText().isEmpty()) {
                    List<Hotel> result1 = dataListHotel.stream().filter(bylieu).filter(byprix)
                            .collect(Collectors.toList());

                    grid.getChildren().clear();
                    afficherHotels(result1);
                } else {
                    List<Hotel> result2 = dataListHotel.stream().filter(bylieu).filter(byprix).filter(bychambre)
                            .collect(Collectors.toList());
                    grid.getChildren().clear();
                    afficherHotels(result2);

                }
            }


        }
    }

    void search_hotels()
    {
        dataListHotel = getHotelList();

        List<Hotel> r = dataListHotel.stream().collect(Collectors.toList());
        grid.getChildren().clear();
        afficherHotels(r);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {








        dateD.setOnAction(event -> {
            if (selectedDates.size() > 1){
                selectedDates.clear();
            }
            selectedDates.add(dateD.getValue());}
        );

        dateD.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        boolean alreadySelected = selectedDates.contains(item);
                        setDisable(alreadySelected);
                        setStyle(alreadySelected ? "-fx-background-color: #4EFE00;" : "");



                    }
                };
            }
        });

        textrechprice.setPromptText("    Wellcome To");
        textrechcolor.setPromptText("       Aero Space");
        textrechprice.setVisible(true);
        textrechcolor.setVisible(true);
        vers.setVisible(false);
        nbrEtoile.setVisible(false);
        aller_ret.setVisible(false);

        dateD.setVisible(false);




        ObservableList<String> listType = FXCollections.observableArrayList("Paris", "Tunis", "Tripoli", "Dubai", "Madrid");

        coboType.getSelectionModel().select("Pays");
        coboType.setItems(listType);

        ObservableList<Integer> listNbr = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7);

        cobonbr.getSelectionModel().select("Nombre jours ");
        cobonbr.setItems(listNbr);


        if (connection == null) {
            connlabel.setTextFill(Color.TOMATO);
            connlabel.setText("Server Error : Check");
        } else {
            connlabel.setTextFill(Color.GREEN);
            connlabel.setText("Server is up : Good to go");
        }



     //   afficher2(getFruitList2());



    }

    @FXML
    void handelrech(ActionEvent event) {
/*
        off.setOnAction(e -> {
            btnrech.setOnAction(t -> {
               search_fruit();

            });

        });
*/
    }


    void affichervol(List<Vol> vols) {


        if (vols.size() > 0) {
            setChosenVol(vols.get(0));


            myListVOL = new MyListener() {
                @Override
                public void onClickListener(Vol vols) {
                    setChosenVol(vols);

                    idVolReserv = vols.getNumv();

                }

                @Override
                public void onClickListener2(Transport transport) {

                }

                @Override
                public void onClickk(Hotel hotel) {

                }

                @Override
                public void onClickk2(Offre offre) {

                }

                @Override
                public void onClickk3(Event event) {

                }
            };

        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < vols.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../views/Voll.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                itemVoll = fxmlLoader.getController();
                itemVoll.setData(vols.get(i), myListVOL);

                if (column == 3) {
                    column = 0;
                    row++;
                }
                //  grid.getChildren();
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);


                GridPane.setMargin(anchorPane, new Insets(vols.size()));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    void afficherTrans(List<Transport> trans) {


        if (trans.size() > 0) {
            setChosenTrans(trans.get(0));


            myListTrans = new MyListener() {
                @Override
                public void onClickListener(Vol vol) {



                }

                @Override
                public void onClickListener2(Transport trans) {
                    setChosenTrans(trans);

                    idTransReserv = trans.getReference();
                }

                @Override
                public void onClickk(Hotel hotel) {

                }

                @Override
                public void onClickk2(Offre offre) {

                }

                @Override
                public void onClickk3(Event event) {

                }
            };

        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < trans.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../views/transport.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                itemTransport = fxmlLoader.getController();
                itemTransport.setDataTrans(trans.get(i) , myListTrans);

                if (column == 3) {
                    column = 0;
                    row++;
                }
                //  grid.getChildren();
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);


                GridPane.setMargin(anchorPane, new Insets(trans.size()));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void afficherHotels(List<Hotel> hotels) {


        if (hotels.size() > 0) {
            setChosenHotel(hotels.get(0));


            myListHotel = new MyListener() {
                @Override
                public void onClickk(Hotel hotel) {
                    setChosenHotel(hotel);

                    idHotelReserv = hotel.getId();


                }

                @Override
                public void onClickk2(Offre offre) {

                }

                @Override
                public void onClickk3(Event event) {

                }

                @Override
                public void onClickListener(Vol vol) {

                }

                @Override
                public void onClickListener2(Transport transport) {

                }


            };

        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < hotels.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../views/hotel.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                itemHotel= fxmlLoader.getController();
                itemHotel.setDataH(hotels.get(i), myListHotel);

                if (column == 3) {
                    column = 0;
                    row++;
                }
                //  grid.getChildren();
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);


                GridPane.setMargin(anchorPane, new Insets(hotels.size()));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @FXML
    void btnaddtocar(ActionEvent event) {



        if(idHotelReserv != null)
        {
            IdHotel=idHotelReserv;
        }
        if(idTransReserv != null)
        {
            Ref=idTransReserv;
        }
        if(idVolReserv != null)
        {
            numV=idVolReserv;
        }
        if(idOEventsReserv!= null)
        {
            idEvent=idTransReserv;
        }
        if(idOffresReserv != null)
        {
            idOff=idOffresReserv;
        }


        numObject();
    }

    public void numObject()
    {


       if(IdHotel != null)
       {

           try {
               panier.add(IdHotel);
           }catch (Exception e)
           {
               System.out.println(e.getMessage());
           }
           nbrObj++;
       }
       else if(numV != null)
       {
         try {
             panier.add(numV);
         }catch (Exception e)
         {
             System.out.println(e.getMessage());
         }
           nbrObj++;
       }
       else if(Ref !=null)
       {
           try {
               panier.add(Ref);
           }catch (Exception e)
           {
               System.out.println(e.getMessage());
           }

           nbrObj++;
       }

        System.out.println("nbr object " +nbrObj +" ");


        panier.forEach(s -> System.out.println(s));

        if(nbrObj==11)
           nbrObj=0;



        if(nbrObj==1)
        {
            numObj.setVisible(true);
            numObj.setImage(new Image("file:///C:\\Users\\LEGION-5\\Documents\\GitHub\\offres-reservation_integration\\offres\\src\\sample\\GestionReservation\\img\\1_50px.png"));
        }else if(nbrObj==2)
        {
            numObj.setImage(new Image("file:///C:\\Users\\LEGION-5\\Documents\\GitHub\\offres-reservation_integration\\offres\\src\\sample\\GestionReservation\\img\\2_50px.png"));
        }else if(nbrObj==3)
        {
            numObj.setImage(new Image("file:///C:\\Users\\LEGION-5\\Documents\\GitHub\\offres-reservation_integration\\offres\\src\\sample\\GestionReservation\\img\\3_50px.png"));
        }else if(nbrObj==4)
        {
            numObj.setImage(new Image("file:///C:\\Users\\LEGION-5\\Documents\\GitHub\\offres-reservation_integration\\offres\\src\\sample\\GestionReservation\\img\\4_50px.png"));
        }else if(nbrObj==5)
        {
            numObj.setImage(new Image("file:///C:\\Users\\LEGION-5\\Documents\\GitHub\\offres-reservation_integration\\offres\\src\\sample\\GestionReservation\\img\\5_50px.png"));
        }else if(nbrObj==6)
        {
            numObj.setImage(new Image("file:///C:\\Users\\LEGION-5\\Documents\\GitHub\\offres-reservation_integration\\offres\\src\\sample\\GestionReservation\\img\\6_50px.png"));
        }else if(nbrObj==7)
        {
            numObj.setImage(new Image("file:///C:\\Users\\LEGION-5\\Documents\\GitHub\\offres-reservation_integration\\offres\\src\\sample\\GestionReservation\\img\\7_50px.png"));
        }else if(nbrObj==8)
        {
            numObj.setImage(new Image("file:///C:\\Users\\LEGION-5\\Documents\\GitHub\\offres-reservation_integration\\offres\\src\\sample\\GestionReservation\\img\\8_50px.png"));
        }else if(nbrObj==9)
        {
            numObj.setImage(new Image("file:///C:\\Users\\LEGION-5\\Documents\\GitHub\\offres-reservation_integration\\offres\\src\\sample\\GestionReservation\\img\\9_50px.png"));
        }else if(nbrObj==10)
        {

            numObj.setImage(new Image("file:///C:\\Users\\LEGION-5\\Documents\\GitHub\\offres-reservation_integration\\offres\\src\\sample\\GestionReservation\\img\\10_50px.png"));

        }else if(nbrObj == 0)
        {
            numObj.setVisible(false);
        }
    }
    public void Notification(String Title,String Message,String icon)
    {


        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.SLIDE;



        tray.setNotificationType(NotificationType.valueOf(icon));

        tray.setAnimationType(type);
        tray.setTitle(Title);
        tray.setMessage(Message);
        tray.showAndDismiss(Duration.millis(3000));

    }



    @FXML
    void btnevent(ActionEvent event) {


        grid.getChildren().clear();
        afficherEvents(getEventList());

        nbrEtoile.setVisible(false);
        textrech.setPromptText("Recherchez Par Destination ,Offres");
        dateD.setVisible(true);
        textrechprice.setVisible(false);

        aller_ret.setOnAction(t-> {
            textrechcolor.setVisible(true);
        });


        btnrech.setOnAction(t -> {
            search_Events();
        });

       // Notification("Success","Insertion Avec Succée Merci","SUCCESS");
       // Notification("Failed ! ","Sorry :( ","ERROR");

    }

    void trieparEtoile()
    {
        trieListeHotel = getHotelList();
        List<Hotel> r = trieListeHotel.stream().sorted(Comparator.comparingInt(Hotel::getEtoile).reversed()).collect(Collectors.toList());
        grid.getChildren().clear();
        afficherHotels(r);
    }
    void trieparEtoile2()
    {
        trieListeHotel = getHotelList2();
        List<Hotel> r = trieListeHotel.stream().sorted(Comparator.comparingInt(Hotel::getEtoile).reversed()).collect(Collectors.toList());
        grid.getChildren().clear();
        afficherHotels(r);
    }






    @FXML
    void btnhot(ActionEvent event) {

        grid.getChildren().clear();
        afficherHotels(getHotelList2());
        vers.setVisible(false);
        vers.setText("Etoile");
        nbrEtoile.setVisible(true);
        aller_ret.setVisible(false);
        dateD.setVisible(true);
        textrech.setPromptText("Destination");


        textrechprice.setVisible(true);
        textrechprice.setPromptText("Check-in -> Check-out");
        textrechcolor.setVisible(false);


        btnrech.setOnAction(t -> {
            search_hotels();

        });
    }

    @FXML
    void btnoff(ActionEvent event) {



        grid.getChildren().clear();
        afficherOfrres(getOffreList());

        nbrEtoile.setVisible(false);
        textrech.setPromptText("Recherchez Par Destination ,Offres");
        dateD.setVisible(true);
        textrechprice.setVisible(false);

        aller_ret.setOnAction(t-> {
            textrechcolor.setVisible(true);
        });


        btnrech.setOnAction(t -> {

        search_offres();
        });
    }

    @FXML
    void btntrans(ActionEvent event) {
        grid.getChildren().clear();
        afficherTrans(getTransList2());

        nbrEtoile.setVisible(false);
        textrech.setPromptText("Recherchez Par Destination ,Event");
        dateD.setVisible(true);
        aller_ret.setVisible(false);
        textrechprice.setVisible(false);

        aller_ret.setOnAction( t -> {
            textrechcolor.setVisible(true);
            textrechcolor.setPromptText("Vers");
        });

        btnrech.setOnAction(t -> {
            search_Transport();

        });



    }

    @FXML
    void btnvol(ActionEvent event) {

        grid.getChildren().clear();
        affichervol(getVolList2());
        nbrEtoile.setVisible(false);
        textrech.setPromptText("A Partir De ");
        dateD.setVisible(true);
        aller_ret.setVisible(true);

        textrechprice.setVisible(false);
        textrechcolor.setVisible(false);

        aller_ret.setOnAction( t -> {
            textrechcolor.setVisible(true);
            textrechcolor.setPromptText("Vers");
        });

        btnrech.setOnAction(t -> {
            search_vol();

        });


    }


    private static String idH= "";
    private static String idO= "";
    private static String idE= "";
    private static String idV= "";
    private static String idT= "";
    private static String idR= "";
    private static String z= "";



    public static void wew()
    {
        String q = pathvideo;
        String a = idVolReserv;
        String b = idHotelReserv;
        String c = idTransReserv;
        String d = idOffresReserv;
        String e = idOEventsReserv;
        String f = idReservation;
        idH = b ;
        idO = d ;
        idE = e ;
        idV = a ;
        idT = c ;
        idR = f;

    }
    public static String idHo()
    {
        return idH;
    }
    public static String idTr()
    {
        return idT;
    }
    public static String idVo()
    {
        return idV;
    }
    public static String idEv()
    {
        return idE;
    }
    public static String idOf()
    {
        return idO;
    }
    public static String idRe()
    {
        return idR;
    }

    public static void weww()
    {

        String a = pathvideo;
        z = a;
    }
    public static String iddddddd()
    {
        return z;
    }

    public void handelPanier(MouseEvent mouseEvent) throws Exception{


/*

            if(idR==null || idT==null || idV == null) {
                now = java.time.LocalDate.now();
                try {
                    String st = "INSERT INTO resevation ( idRes, idHo, referance, numv, etat, pos_map, prixT) VALUES (?,?,?,?,?,?,?)";
                    preparedStatement = (PreparedStatement) connection.prepareStatement(st);
                    idReservation = random_id();
                    preparedStatement.setString(1, idReservation);
                    preparedStatement.setString(2, IdHotel);
                    preparedStatement.setString(3, Ref);
                    preparedStatement.setString(4, numV);
                    preparedStatement.setString(5, "test");
                    preparedStatement.setString(6, "mahdi");
                    preparedStatement.setDouble(7, 1999.36);

                    preparedStatement.executeUpdate();
                    preparedStatement.close();


                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());


                }
            }

        if(idReservation != null)
        {
        idReserv=idReservation;
        }
*/
        goodSound();
        if (IdHotel !="null" || numV!="null" || Ref!="null" || idOff !="null" || idEvent != "null") {
            Stage stage = (Stage) textrech.getScene().getWindow();
            stage.close();


            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/sample/GestionReservation/views/paiement.fxml"));

            primaryStage.setScene(new Scene(root));
            primaryStage.show();


            Notification("Sucuss", "fini la reservation", "SUCCESS");
        }
        else {
           // badSound();
            Notification("", "Please Add To Panier Objet", "ERROR");
        }

    }
    public void badSound()
    {

        file = new File("C:\\Users\\LEGION-5\\Documents\\GitHub\\offres-reservation_integration\\offres\\src\\error.wav");
        m = new Media(file.toURI().toString());
        mm = new MediaPlayer(m);
        mm.play();

    }

    public void goodSound()
    {

        file2 = new File("C:\\Users\\LEGION-5\\Documents\\GitHub\\offres-reservation_integration\\offres\\src\\toggle.wav");
        m2 = new Media(file2.toURI().toString());
        mm2 = new MediaPlayer(m2);
        mm2.play();
    }

    public void videoAction(ActionEvent actionEvent) throws Exception{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/sample/GestionReservation/views/sample.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }



/*
    public void Notification(String Title,String Message)
    {


        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.SLIDE;


        tray.setAnimationType(type);
        tray.setTitle(Title);
        tray.setMessage(Message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));

    }
*/



}