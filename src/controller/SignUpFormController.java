package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.event.ActionEvent;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpFormController {
    public JFXTextField txtUser;
    public JFXPasswordField txtPass;
    public static final String SECRET = "Oshada123";

    public int signUpOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
            String user = txtUser.getText();
            String pass = txtPass.getText();
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO user(userName,password) VALUES(?,AES_ENCRYPT(?,?))");
            pstm.setObject(1, user);
            pstm.setObject(2, pass);
            pstm.setObject(3, SECRET);
            System.out.println(pstm);
            return pstm.executeUpdate();
        }
    }
