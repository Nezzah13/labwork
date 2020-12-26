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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Klient implements Initializable
{
    @FXML
    private final ObservableList <Product> productData = FXCollections.observableArrayList();
    @FXML
    private TableView <Product> catalogTable;
    @FXML
    private TableColumn <Product, String>  productName;
    @FXML
    private TableColumn <Product, String>  productId;
    @FXML
    private TableColumn <Product, String>  productCount;
    @FXML
    private TableColumn <Product, String>  productSum;
    @FXML
    private Label productNameLabel;
    @FXML
    private Label productIdLabel;
    @FXML
    private Label productCountLabel;
    @FXML
    private Label productSumLabel;

    @FXML
    private void showProductDetails (Product product)
    {
        if (product != null)
        {
            productNameLabel.setText(product.getProductName().toString());
            productIdLabel.setText(product.getProductId().toString());
            productCountLabel.setText(product.getProductCount().toString());
            productSumLabel.setText(product.getProductSum().toString());
        }
        else
        {
            productNameLabel.setText("");
            productIdLabel.setText("");
            productCountLabel.setText("");
            productSumLabel.setText("");
        }
    }

    @FXML
    private void handleDeleteProduct ()
    {
        int selectedIndex = catalogTable.getSelectionModel().getFocusedIndex();
        if (selectedIndex >= 0)
        {
            catalogTable.getItems().remove(selectedIndex);
            printCatalog();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(null);
            alert.setTitle("Не выделено");
            alert.setHeaderText("Не выбран товар");
            alert.setContentText("Выберите товар в таблице");

            alert.showAndWait();
        }
    }

    public void printCatalog ()
    {
        File file = new File(System.getProperty("user.dir") + "/ProductsData");
        FileWriter fr = null;
        try
        {
            fr = new FileWriter(file);
            fr.write("Список продуктов");
            fr.append('\n');
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            fr.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }



        ObservableList <Product> Products = catalogTable.getItems();
        for (int index = 0; index < Products.size(); index = index + 1)
        {
            Products.get(index).printProduct(System.getProperty("user.dir") + "/ProductsData");
        }




        try
        {
            fr = new FileWriter(file, true);
            fr.append('\n');
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            fr.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        File userData = new File(System.getProperty("user.dir") + "/ProductsData");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(userData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(fileReader);

        String
                tmpProductName = "";
        Integer
                tmpProductId = 0,
                tmpProductCount = 0;
        Double
                tmpProductSum = 0.0;
        try {
            String tmp = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true)
        {
            try {
                tmpProductName = reader.readLine();
                if (tmpProductName.length() > 0)
                {
                    tmpProductId = Integer.valueOf(reader.readLine());
                    tmpProductCount = Integer.valueOf(reader.readLine());
                    tmpProductSum = Double.valueOf(reader.readLine());
                    productData.add(new Product(tmpProductName, tmpProductId, tmpProductCount, tmpProductSum));
                }
                else
                {
                    break;
                }
            } catch (IOException e) {
                break;
            }

        }
        try {
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productCount.setCellValueFactory(new PropertyValueFactory<>("productCount"));
        productSum.setCellValueFactory(new PropertyValueFactory<>("productSum"));
        catalogTable.setItems(productData);

        showProductDetails(null);
        catalogTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showProductDetails(newValue));
    }

    public boolean showProductEditDialog (Product product)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AddProduct.class.getResource("AddProduct.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Добавить продукт");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(null);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            AddProduct controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProduct(product);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void handleNewProduct () throws IOException
    {
        System.out.println("Проверка -1");
        Product tempProduct = new Product();
        System.out.println("Проверка 0");
        boolean okClicked = this.showProductEditDialog(tempProduct);
        System.out.println("Проверка 1");
        if (okClicked)
        {
            System.out.println("Проверка 2");
            productData.add(tempProduct);
            System.out.println("Проверка 3");
        }
        printCatalog();
    }

    @FXML
    private void handleEditProduct ()
    {
        Product selectedProduct = catalogTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null)
        {
            boolean okClicked = showProductEditDialog(selectedProduct);
            if (okClicked)
            {
                showProductDetails(selectedProduct);
                int selectedIndex = catalogTable.getSelectionModel().getSelectedIndex();
                productData.set(selectedIndex, selectedProduct);
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(null);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Нет выбранного продукта");
            alert.setContentText("Выберите продукт в таблице");

            alert.showAndWait();
        }
        printCatalog();
    }
}