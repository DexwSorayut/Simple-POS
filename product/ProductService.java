package product;

public class ProductService {
    public ProductService(){

        ProductRepository repo = new ProductRepository();
        Catalog catalog ;
        // โหลดสินค้า
        repo.loadFromFile();

        // เพิ่มสินค้าใน Catalog TEA
        try {
            catalog = Catalog.TEA;
            repo.AddProduct(new Product("T001", "Milk Tea", 25,catalog));
            repo.AddProduct(new Product("T002", "Green Tea", 25,catalog));
            repo.AddProduct(new Product("T003", "Tea", 15,catalog));
            repo.AddProduct(new Product("T004", "Black Tea", 15,catalog));
            repo.AddProduct(new Product("T005", "Strawberry Tea", 35,catalog));
            repo.AddProduct(new Product("T006", "Peach Tea", 35,catalog));
            repo.AddProduct(new Product("T007", "Blueberry Tea", 35,catalog));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        // เพิ่มสินค้าใน Catalog MILK
        try {
            catalog = Catalog.MILK;
            repo.AddProduct(new Product("M001", "Milk", 15,catalog));
            repo.AddProduct(new Product("M002", "Pink Milk", 20,catalog));
            repo.AddProduct(new Product("M003", "Cocoa", 20,catalog));
            repo.AddProduct(new Product("M004", "Strawberry Milk", 30,catalog));
            repo.AddProduct(new Product("M005", "Ovaltine", 20,catalog));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        // เพิ่มสินค้าใน Catalog COFFEE
        try {
            catalog = Catalog.COFFEE;
            repo.AddProduct(new Product("C001", "Latte", 40,catalog));
            repo.AddProduct(new Product("C002", "Mocha", 40,catalog));
            repo.AddProduct(new Product("C003", "Americano", 35,catalog));
            repo.AddProduct(new Product("C004", "Espresso", 40,catalog));
            repo.AddProduct(new Product("C005", "Cappuccino", 40,catalog));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        // เพิ่มสินค้าใน Catalog JUICE
        try {
            catalog = Catalog.JUICE;
            repo.AddProduct(new Product("J001", "Orange juice", 20,catalog));
            repo.AddProduct(new Product("J002", "Pineapple juice", 20,catalog));
            repo.AddProduct(new Product("J003", "Lemon juice", 20,catalog));
            repo.AddProduct(new Product("J004", "Watermelon juice", 20,catalog));
            repo.AddProduct(new Product("J005", "Apple juice", 20,catalog));
            repo.AddProduct(new Product("J006", "Passion fruit juice", 25,catalog));
            repo.AddProduct(new Product("J007", "Kiwi juice", 25,catalog));
            repo.AddProduct(new Product("J008", "Blueberry Juice", 25,catalog));
            repo.AddProduct(new Product("J009", "Strawberry juice", 25,catalog));
            repo.AddProduct(new Product("J010", "Tomato juice", 20,catalog));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        // เพิ่มสินค้าใน Catalog SODA
        try {
            catalog = Catalog.SODA;
            repo.AddProduct(new Product("S001", "Coca-Cola", 10,catalog));
            repo.AddProduct(new Product("S002", "Pepsi", 15,catalog));
            repo.AddProduct(new Product("S003", "Est-Cola", 10,catalog));
            repo.AddProduct(new Product("S004", "Fanta", 15,catalog));
            repo.AddProduct(new Product("S005", "Sprite", 15,catalog));
            repo.AddProduct(new Product("S006", "7-UP", 15,catalog));
            repo.AddProduct(new Product("S007", "Singha", 15,catalog));
            repo.AddProduct(new Product("S008", "Schwepppes", 15,catalog));
            repo.AddProduct(new Product("S009", "Mirinda", 12,catalog));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        // เพิ่มสินค้าใน Catalog WATER
        try {
            catalog = Catalog.WATER;
            repo.AddProduct(new Product("W001", "Crystal drinking water", 10,catalog));
            repo.AddProduct(new Product("W002", "Namthip drinking water", 8,catalog));
            repo.AddProduct(new Product("W003", "Chang drinking water", 9,catalog));
            repo.AddProduct(new Product("W004", "Mineral water", 12,catalog));
            repo.AddProduct(new Product("W005", "Aura Water", 12,catalog));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        // แสดงทั้งหมด
        for(Product p : repo.getAllProducts()){
            System.out.println(p.getProductID() + " - " + p.getProductName() + " : " + p.getPrice());
        }

        // บันทึกไฟล์
        repo.saveToFile();
    }
}
