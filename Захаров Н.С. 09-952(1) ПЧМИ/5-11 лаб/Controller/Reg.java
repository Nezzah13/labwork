package sample.Controller;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.User;

public class Reg implements Initializable
{
    @FXML
    private Label lbl_Login;
    @FXML
    private Label lbl_Password;
    @FXML
    private Label lbl_ConfirmPassword;
    @FXML
    private TextField txt_Login;
    @FXML
    private PasswordField psf_Password;
    @FXML
    private PasswordField psf_ConfirmPassword;
    @FXML
    private Button btn_Registration;

    @FXML
    private void Registration(ActionEvent event) throws IOException
    {
        if(psf_Password.getText().equals(psf_ConfirmPassword.getText()))
        {
            boolean alreadyExists = false;
            User user;
            String login = txt_Login.getText();
            String password = psf_ConfirmPassword.getText();
            String fileLogin = "";
            String filePassword = "";
            user = new User(login, password);
            File userData = new File(System.getProperty("user.dir") + "/UserData");
            FileReader fileReader = new FileReader(userData);
            BufferedReader reader = new BufferedReader(fileReader);


            String tmp = reader.readLine();
            while (((fileLogin = reader.readLine()) != null) && ((filePassword = reader.readLine()) != null))
            {
                if (user.login.equals(fileLogin))
                {
                    alreadyExists = true;
                }
            }
            fileReader.close();
            reader.close();
            if(alreadyExists)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Внимание");
                alert.setHeaderText("Пользователь с таким логином уже существует");
                alert.showAndWait();
            }
            else
            {
                File file = new File(System.getProperty("user.dir") + "/UserData");
                FileWriter fr = null;
                try {
                    fr = new FileWriter(file,true);
                    fr.write(user.login);
                    fr.append('\n');
                    fr.write(user.password);
                    fr.append('\n');

                } catch (IOException e) {
                    e.printStackTrace();
                }
                fr.close();


                txt_Login.setText("");
                psf_Password.setText("");
                psf_ConfirmPassword.setText("");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Успешно");
                alert.setHeaderText("Пользователь создан");
                alert.showAndWait();


                Stage stage = new Stage();
                stage.setTitle("Вход");
                Parent root = FXMLLoader.load(getClass ().getResource("LogIn.fxml"));
                TextInputDialog dialog = new TextInputDialog("");
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                Stage stage1 = (Stage) btn_Registration.getScene().getWindow();
                stage1.close();
            }
        }
    }

    @FXML
    private void ReturnLogIn(ActionEvent event) throws IOException
    {
        Stage stage = new Stage();
        stage.setTitle("Вход");
        Parent root = FXMLLoader.load(getClass ().getResource("LogIn.fxml"));
        TextInputDialog dialog = new TextInputDialog("");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Stage stage1 = (Stage) btn_Registration.getScene().getWindow();
        stage1.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){}

}