package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;

public class Controller {

    @FXML
    private Label notiTextField;

    @FXML
    private TextField userNameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    void onLogin() {

        notiTextField.setText(String.format("User Name: %s start loging in", userNameTextField.getText()));

        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@199.212.26.208:1521:sqld", "comp214_f19_ers_75", "password")) {
            if (connection != null) {
                System.out.println("Connected to the database!");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(String.format("select IDSHOPPER, FIRSTNAME, LASTNAME from bb_shopper where IDSHOPPER = fun_authen_user('%s','%s')", userNameTextField.getText(), passwordTextField.getText()));
                User user = new User();
                while (resultSet.next()) {
                    user.id = resultSet.getInt(1);
                    user.firstName = resultSet.getString(2);
                    user.lastName = resultSet.getString(3);
                }
                notiTextField.setText(String.format("User: %s logged in", user));
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
