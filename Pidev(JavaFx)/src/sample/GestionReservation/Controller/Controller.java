package sample.GestionReservation.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    private MediaView mediaView;

    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;



    private Button btnPlay;
    private Button btnPause;




    public void PathVideo(String path)
    {

        file = new File(path);
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MarketController.weww();
        String idt = MarketController.iddddddd();
        System.out.println(idt);
        PathVideo(idt);

    }


    public void palyAction(ActionEvent actionEvent) {

        mediaPlayer.play();


    }

    public void pauseAction(ActionEvent actionEvent) {

        mediaPlayer.pause();
    }

    public void resetAction(ActionEvent actionEvent) {
        mediaPlayer.seek(Duration.seconds(0.0));
    }
}
