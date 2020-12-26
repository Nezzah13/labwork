package sample.Controller;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AddProduct {

    @FXML
    private TextField productNameField;
    @FXML
    private TextField productIdField;
    @FXML
    private TextField productCountField;
    @FXML
    private TextField productSumField;

    private Stage dialogStage;
    private  Product product;
    private boolean okClicked = false;



    public void setDialogStage (Stage dialogStage)
    {
        this.dialogStage = dialogStage;
    }

    public void setProduct (Product product)
    {
        this.product = product;

        productNameField.setText(product.getProductName());
        productIdField.setText(product.getProductId().toString());
        productCountField.setText(product.getProductCount().toString());
        productSumField.setText(product.getProductSum().toString());
    }

    public boolean isOkClicked ()
    {
        return okClicked;
    }

    private boolean isInputValid ()
    {
        String errorMessage = "";
        if ((productNameField.getText() == null) || (productNameField.getText().length() == 0))
        {
            errorMessage = errorMessage + "Нет доступного наименования товара\n";
        }
        if ((productIdField.getText() == null) || (productIdField.getText().length() == 0))
        {
            errorMessage = errorMessage + "Нет доступного артикула\n";
        }
        if ((productCountField.getText() == null) || (productCountField.getText().length() == 0))
        {
            errorMessage = errorMessage + "Нет доступной суммы\n";
        }
        if ((productSumField.getText() == null) || (productSumField.getText().length() == 0))
        {
            errorMessage = errorMessage + "Нет доступного количества\n";
        }
        if (errorMessage.length() == 0)
        {
            return true;
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Некорректные поля");
            alert.setHeaderText("Внесите корректную информацию");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    @FXML
    private void handleOk ()
    {
        if (isInputValid())
        {
            product.setProductName(productNameField.getText());
            product.setProductId(Integer.parseInt(productIdField.getText()));
            product.setProductCount(Integer.parseInt(productCountField.getText()));
            product.setProductSum(Double.parseDouble(productSumField.getText()));

            product.printProduct(System.getProperty("user.dir") + "/ProductsData");

            okClicked = true;
            dialogStage.close();
        }

    }

    @FXML
    private void handleCancel ()
    {
        dialogStage.close();
    }
}
