import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;


public class ProductRepository {
    private ArrayList <Product> products = new ArrayList<>();

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

      public ArrayList<Product> getAllProducts(){
        ArrayList<Product> uniqueProducts = new ArrayList<>();
        HashSet<String> SeeProduct = new HashSet<>();

        for (Product p : products) {
            String keyID = "ID:" + p.getProductID();
            String keyName = "NAME:" + p.getProductName().toLowerCase();

        // ถ้ามีอันใดอันหนึ่งเคยเจอแล้ว แสดงว่าซ้ำ → ข้าม
            if (SeeProduct.contains(keyID) || SeeProduct.contains(keyName) ) {
                continue;
            }

        // ถ้ายังไม่เจอ → เก็บเข้า list และ mark ว่าเจอแล้ว
            uniqueProducts.add(p);
            SeeProduct.add(keyID);
            SeeProduct.add(keyName);
        }
        return uniqueProducts;
    }

    // บันทึกสินค้าเป็น CSV
    public void saveToFile(){
        File F = null;
        FileWriter FW = null;
        BufferedWriter BW = null;
        try {
            ArrayList <String> catalogs = new ArrayList<>();

            for (Product p : products) {
                if (!catalogs.contains(p.getCatalog())) {
                    catalogs.add(p.getCatalog());
                }
            }

            F = new File("./File & Image/ProductCatalog.csv");
            FW = new FileWriter(F , false);
            BW = new BufferedWriter(FW);
            for (String catalog : catalogs) {
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
        products.clear(); // ล้าง list ก่อน
        File F = null;
        FileReader FR = null;
        BufferedReader BR = null;
        try {
            F = new File("./File & Image/ProductCatalog.csv");
            FR = new FileReader(F);
            BR = new BufferedReader(FR);

            String line;
            String currentCatalog = null;

            while ((line = BR.readLine()) != null) {
                line = line.trim();

                // ข้ามบรรทัดว่าง
                if (line.isEmpty()) continue;

                // เจอ Section ใหม่ เช่น [TEA]
                if (line.startsWith("[") && line.endsWith("]")) {
                    currentCatalog = line.substring(1, line.length() - 1);
                    continue;
                }
            
                // ข้าม header
                if (line.startsWith("ProductID")) continue;

                String Data[] = line.split(",");
                if(Data.length >= 3){
                    String ID = Data[0].trim(); //.trim() คือการตัดเว้นวรรค ซ้าย-ขวา ของข้อมูลออกไป
                    String Name = Data[1].trim();
                    double Price = Double.parseDouble(Data[2].trim());
                    String Catalog = Data[3].trim() ;
                    products.add(new Product(ID, Name, Price, Catalog));
                }
            }
            System.out.println("Loaded Product File.");
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
}