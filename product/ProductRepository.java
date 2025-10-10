package product;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;


public class ProductRepository {
    private ArrayList <Product> products = new ArrayList<>();
    private ArrayList <Catalog> catalogs = new ArrayList<>();

    private void checkRep(){
        if(products == null){
            throw new RuntimeException("Error : Product is null");
        }
        for(int i = 0; i < products.size();i++){
            for(int j = i+1;j < products.size();j++){
                if(products.get(i).equals(products.get(j))){
                    throw new RuntimeException("Error : The product is already in product.");
                }
            }
        }
    }

    public ProductRepository(){
        checkRep();
    }

    public void AddProduct(Product product){
        if(product != null && !products.contains(product)) {
            products.add(product);
        }
        checkRep();
    }

    public void RemoveProductByID(String ProductID){
        for (Product p : products) {
            products.remove(p.getProductID().equals(ProductID));
        }
        checkRep();
    }

    public ArrayList<Product> getAllProducts() {    
        if(products == null){
            throw new RuntimeException("Product is null");
        }
        return products;
    }


    // บันทึกสินค้าเป็น CSV
    public void saveToFile(){
        File F = null;
        FileWriter FW = null;
        BufferedWriter BW = null;
        try {
            ArrayList <Catalog> catalogs = new ArrayList<>();

            for (Product p : products) {
                if (!catalogs.contains(p.getCatalog())) {
                    catalogs.add(p.getCatalog());
                }
            }

            F = new File("./File & Image/ProductCatalog.csv");
            FW = new FileWriter(F , false);
            BW = new BufferedWriter(FW);
            for (Catalog catalog : catalogs) {
            BW.write("[" + catalog + "]\n");
            BW.write("ProductID,ProductName,Price,Catalog\n");
            for (Product p : getAllProducts()) {
                if (p.getCatalog().equals(catalog)) {
                    BW.write(p.getProductID() + "," + p.getProductName() + "," + p.getPrice() + "," + p.getCatalog() + "\n");
                }
            }
            BW.write("\n"); // เว้นบรรทัดให้อ่านง่าย
        }
            System.out.println("Saved File product.");
        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
        finally{
            try {
                BW.close(); FW.close(); 
            } catch (Exception e) {
                System.out.println("Error closing file: " + e.getMessage());
            }
        }
    }

    // โหลดสินค้า CSV
    public void loadFromFile(){
        products.clear();
        File F = new File("./File & Image/ProductCatalog.csv");

        try (BufferedReader BR = new BufferedReader(new FileReader(F))) {
            String line;
            String currentCatalog = null;

            while ((line = BR.readLine()) != null) {
                line = line.trim();

                // ข้ามบรรทัดว่าง
                if (line.isEmpty()) continue;

                // ถ้าเจอ section เช่น [Tea]
                if (line.startsWith("[") && line.endsWith("]")) {
                    currentCatalog = line.substring(1, line.length() - 1);
                    continue;
                }

                // ข้าม header
                if (line.startsWith("ProductID")) continue;

                String[] Data = line.split(",");
                if (Data.length >= 3) {
                    String ID = Data[0].trim();
                    String Name = Data[1].trim();
                    double Price = Double.parseDouble(Data[2].trim());

                    // ถ้ามี column Catalog ในไฟล์ให้ใช้มัน ถ้าไม่มีให้ใช้ section
                    Catalog catalog;
                    if (Data.length > 3) {
                        catalog = Catalog.valueOf(Data[3].trim().toUpperCase());
                    } else {
                        catalog = Catalog.valueOf(currentCatalog.toUpperCase());
                    }

                    products.add(new Product(ID, Name, Price, catalog));
                }
            }
            //System.out.println("Loaded Product File.");
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
}