package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import entity.Manager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class LoginFormController {
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public AnchorPane root;

    public int verifier_auth(String email ,String pass) throws SQLException, IOException, ClassNotFoundException {

        Connection cnx;
        PreparedStatement ste ;
        cnx = DBConnection.getInstance().getConnection();


        String sql = "Select * from managers where user_name  =? and password  =?";
        ResultSet rs;
        ste=cnx.prepareStatement(sql);
        Manager u = new Manager ();

        int x=0;
        ste.setString(1, email);
        ste.setString(2, pass);
        rs = ste.executeQuery();
        if (rs.next()){

            u.setUser_id(rs.getInt(1));
            u.setUser_name(rs.getString(2));
            u.setPassword(rs.getString(3));
            u.setDepartment(rs.getString(4));


            if(rs.getString(4).equals("admin")){ x = 1; }
            if(rs.getString(4).equals("Financial")){ x = 2; }
            if(rs.getString(4).equals("Hotel")){ x = 3; }
            if(rs.getString(4).equals("Transport")){ x = 4; }
            if(rs.getString(4).equals("Event")){ x = 5; }
            if(rs.getString(4).equals("Offer")){ x = 6; }
            if(rs.getString(4).equals("Client")){ x = 7; }

        }

        return x;

    }

    public void LoginOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {


        String uNom = txtUserName.getText();
        String upass = txtPassword.getText();
        //User u = new User ();
        //ServiceUser us= new ServiceUser();
        if (verifier_auth(uNom, upass) == 1) {
            txtUserName.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/Dashboard.fxml")));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();


        }
        if (verifier_auth(uNom, upass) == 2) {
            txtUserName.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Aymen/GUIay/AddCashierFormController.fxml")));
            //Parent root = FXMLLoader.load(getClass().getResource("view/AddCashierForm.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();


        }

        if (verifier_auth(uNom, upass) == 3) {
            txtUserName.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Badis/GUIba/AddItemForm.fxml")));
            //Parent root = FXMLLoader.load(getClass().getResource("view/AddCashierForm.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();


        }
        // if(us.verifier_auth(uNom, upass,FXMain.u)==0){erreur_auth.setText("Verifier votre information !!");}

        if (verifier_auth(uNom, upass) == 4) {
            txtUserName.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Nourhene/GUIno/AddSuply.fxml")));
            //Parent root = FXMLLoader.load(getClass().getResource("view/AddCashierForm.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();

        }

        if (verifier_auth(uNom, upass) == 5) {
            txtUserName.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Omar/GUIom/FXMLEvents.fxml")));
            //Parent root = FXMLLoader.load(getClass().getResource("view/AddCashierForm.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();


        }

        if (verifier_auth(uNom, upass) == 6) {
            txtUserName.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("GestionOffre/GUI/interface.fxml")));
            //Parent root = FXMLLoader.load(getClass().getResource("view/AddCashierForm.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();

        }

        if (verifier_auth(uNom, upass) == 7) {
            txtUserName.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample/GestionReservation/views/market.fxml")));
            //Parent root = FXMLLoader.load(getClass().getResource("view/AddCashierForm.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();

        }
    }
    public void btnCloaseOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
}

