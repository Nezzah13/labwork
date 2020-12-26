package sample;
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

public class Login implements Initializable
{
    @FXML
    private TextField txt_Login;
    @FXML
    private PasswordField psf_Password;
    @FXML
    private Label lbl_Login;
    @FXML
    private Label lbl_Password;
    @FXML
    private Label lbl_IsCorrect;
    @FXML
    private Button btn_Login;
    @FXML
    private Hyperlink hpl_Registration;


    @FXML
    private void LogIn(ActionEvent event)
    {
        sample.User user;
        String login = txt_Login.getText();
        String password = psf_Password.getText();
        user = new sample.User(login, password);
        File userData = new File( "UserData");
        boolean found = false;
        try( FileReader fileReader = new FileReader(userData);
            BufferedReader reader = new BufferedReader(fileReader);) {

            String  line;
            while ((line = reader.readLine()) != null)  {
                String [] asd = line.split(",");
                if ((user.login.equals(asd[1])) && (user.password.equals(asd[2]))) {
                    Stage stage = new Stage();
                    stage.setTitle("Программа для магазина");
                    System.out.println(getClass().getClassLoader().getResource("Klient.fxml"));
                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Klient.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    Stage stage1 = (Stage) hpl_Registration.getScene().getWindow();
                    stage1.close();
                    found = true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!found)
        {
            lbl_IsCorrect.setText("Неверный логин или пароль");
        }
    }

    @FXML
    private void Cancel(ActionEvent event)
    {
        txt_Login.setText("");
        psf_Password.setText("");
    }

    @FXML
    private void Registration(ActionEvent event) throws IOException
    {
        Stage stage = new Stage();
        stage.setTitle("Регистрация");
        Parent root = FXMLLoader.load(getClass ().getClassLoader().getResource("FXML_files/Reg.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Stage stage1 = (Stage) hpl_Registration.getScene().getWindow();
        stage1.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }
}