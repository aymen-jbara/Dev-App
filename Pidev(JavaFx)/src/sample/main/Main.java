package sample.main;

/*from w  ww.ja v  a 2s.co  m*/
import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
        import javafx.stage.Stage;

public class Main extends Application {
    public static final String CURRENCY = "$";



    @Override
    public void start(Stage primaryStage) throws Exception{
        /*
        VBox vbox = new VBox(20);
        Label l = new Label("range date ");
        ObservableSet<LocalDate> date;
        Scene scene = new Scene(vbox, 400, 400);
        primaryStage.setScene(scene);
        MultiDatePicker multiDatePicker = new MultiDatePicker().withRangeSelectionMode();

        DatePicker rangePicker = multiDatePicker.getDatePicker();





        vbox.getChildren().add(rangePicker);
        vbox.getChildren().add(l);
        primaryStage.show();



         */

        Parent root = FXMLLoader.load(getClass().getResource("../GestionReservation/views/market.fxml"));
        primaryStage.setTitle(" Aero Space ");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();



    }


    public static void main(String[] args) {
        launch(args);
    }
}
