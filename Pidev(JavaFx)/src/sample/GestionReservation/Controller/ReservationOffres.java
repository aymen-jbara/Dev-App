package sample.GestionReservation.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import sample.GestionOffre.models.Offre;
import sample.GestionReservation.Services.MyListener;
import sample.GestionReservation.model.Event;
import sample.GestionReservation.model.Hotel;
import sample.GestionReservation.model.Transport;
import sample.GestionReservation.model.Vol;
import sample.connection.DataBase;
import sample.main.Main;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ReservationOffres implements Initializable {



    @FXML
    private VBox chosenFruitCard;

    @FXML
    private Button aller_ret;

    @FXML
    private Label vers;

    @FXML
    private Label fruitNameLable;

    @FXML
    private ImageView numObj;

    @FXML
    private Label fruitPriceLabel;

    public ReservationOffres() {
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
    private Button idaddtocar;

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    ObservableList<LocalDate> selectedDates = FXCollections.observableArrayList();


    @FXML
    private TextField textrechprice;

    @FXML
    private TextField textrechcolor;


    @FXML
    private DatePicker dateD;

    private static String idReservation;


    int nbrObj =0;
    int nbrLike = 0;





    private String idVolReserv;
    private String idHotelReserv;
    private String idTransReserv;

    PreparedStatement preparedStatement;

    ObservableList<Vol> dataListVol;
    ObservableList<Hotel> dataListHotel;
    ObservableList<Transport> dataListTrans;

    ObservableList<Hotel> trieListeHotel;


    List panier = new ArrayList();

    ItemVoll itemVoll;
    ItemHotel itemHotel;
    ItemTransport itemTransport;

    String IdHotel,Ref,numV;

    Connection connection;

    LocalDate now;

    private Image image;
    private MyListener myListHotel;
    private MyListener myListTrans;
    private MyListener myListVOL;



    public String random_id() {

        Random random = new Random();

        String generatedString = random.ints(6,65,90)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();


        return generatedString;
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



    private void setChosenVol(Vol vol) {
        fruitNameLable.setText(vol.getNomv());
        fruitPriceLabel.setText(Main.CURRENCY + vol.getChouffeur());
        //image = new Image("file:///" + fruit.getImgSrc() + "");
        //fruitImg.setImage(image);
        chosenFruitCard.setStyle("-fx-background-color: #9DA4A4;\n" +
                "    -fx-background-radius: 30;");
    }

    private void setChosenTrans(Transport tras) {
        fruitNameLable.setText(tras.getTypee());
        fruitPriceLabel.setText(Main.CURRENCY + tras.getAvailability());
        //image = new Image("file:///" + fruit.getImgSrc() + "");
        //fruitImg.setImage(image);
        chosenFruitCard.setStyle("-fx-background-color: #9DA4A4;\n" +
                "    -fx-background-radius: 30;");
    }

    private void setChosenHotel(Hotel hotel) {
        fruitNameLable.setText(hotel.getNom());
        fruitPriceLabel.setText(Main.CURRENCY + hotel.getPrix());

      //  image = new Image("file:///" + hotel.getPath_image() + "");
       // fruitImg.setImage(image);


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



        System.out.println(textrech.getText());






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



     //   System.out.println(textrech.getText());









        Predicate<Transport> byapartir = transport -> transport.getAprtir().toLowerCase().contains(textrech.getText().toLowerCase());
        //  Predicate<Fruit> byprice = fruit -> fruit.getPrice() == Double.valueOf(textrechprice.getText()) ;
        Predicate<Transport> byvers = transport -> transport.getVers().toLowerCase().contains(textrechcolor.getText().toLowerCase());









        List<Transport> r = dataListTrans.stream().collect(Collectors.toList());


        List<Transport> result = dataListTrans.stream().filter(byapartir)
                .collect(Collectors.toList());

        List<Transport> result3 = dataListTrans.stream().filter(byapartir).filter(byvers)
                .collect(Collectors.toList());



      //  System.out.println(result);
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


      //  System.out.println(result);
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
        if(idVolReserv != null)
        {
            numV=idVolReserv;
        }
        if(idTransReserv != null)
        {
            Ref=idTransReserv;
        }

        System.out.println(IdHotel);
        System.out.println(Ref);
        System.out.println(numV);


        if(Ref != null && numV != null && IdHotel!=null) {
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
                Notification("Secuss","Event");

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                Notification("","Event");

            }

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




//        panier.forEach(s -> System.out.println(s));

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



    @FXML
    void btnevent(ActionEvent event) {





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
    void trieparEtoile(ActionEvent event) {
        if(!selectedDates.isEmpty())
        {
            trieparEtoile();
        }else {
            trieparEtoile2();
        }

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

        nbrEtoile.setVisible(false);
        textrech.setPromptText("Recherchez Par Destination ,Offres");
        dateD.setVisible(true);
        textrechprice.setVisible(false);

        aller_ret.setOnAction(t-> {
            textrechcolor.setVisible(true);
        });


        btnrech.setOnAction(t -> {
            //  search_fruit2();

        });
    }

    @FXML
    void btntrans(ActionEvent event) {
        grid.getChildren().clear();

        afficherTrans(getTransList2());
        nbrEtoile.setVisible(false);
        textrech.setPromptText("Recherchez Par Destination ,Event");
        dateD.setVisible(true);
        aller_ret.setVisible(true);
        textrechprice.setVisible(false);
        textrechcolor.setVisible(false);

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

    private static String z= "";

    public static void wew()
    {

        String a = idReservation;
        z = a;
    }
    public static String iddddd()
    {
        return z;
    }


    @FXML
    void btnbackOffre(ActionEvent event) throws IOException {
        Stage stage = (Stage) textrechcolor.getScene().getWindow();
        stage.close();


        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/sample/GestionOffre/GUI/interface.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }




}
