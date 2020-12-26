package sample.Controller;

import javafx.scene.control.Alert;

import java.io.*;

public class Product
{
    String
            productName;
    Integer
            productId,
            productCount;
    Double
            productSum;

    public Product (String productName, Integer productId, Integer productCount, Double productSum)
    {
        this.productName = productName;
        this.productId = productId;
        this.productCount = productCount;
        this.productSum = productSum;
    }

    public Product() {
        this.productName = "";
        this.productId = 0;
        this.productCount = 0;
        this.productSum = 0.0;
    }

    public String getProductName ()
    {
        return productName;
    }

    public void setProductName (String productName)
    {
        this.productName = productName;
    }

    public Integer getProductId ()
    {
        return productId;
    }

    public void setProductId (Integer productId)
    {
        this.productId = productId;
    }

    public Integer getProductCount  ()
    {
        return productCount;
    }

    public void setProductCount (Integer productCount)
    {
        this.productCount = productCount;
    }

    public Double getProductSum ()
    {
        return productSum;
    }

    public void setProductSum (Double productSum)
    {
        this.productSum = productSum;
    }

    public void printProduct (String pathWithName)
    {
        File file = new File(pathWithName);
        FileWriter fr = null;
        try
        {
            fr = new FileWriter(file,true);
            fr.write(this.productName);
            fr.append('\n');
            fr.write(String.valueOf(this.productId));
            fr.append('\n');
            fr.write(String.valueOf(this.productCount));
            fr.append('\n');
            fr.write(String.valueOf(this.productSum));
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
}
