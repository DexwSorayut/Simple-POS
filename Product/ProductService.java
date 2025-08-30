public class ProductService {

    
    public ProductService(){

        ProductRepository repo = new ProductRepository();
        String catalog = null;
        // โหลดสินค้า
        repo.loadFromFile();

        // เพิ่มสินค้าใน Catalog TEA
        try {
            catalog = "Tea";
            repo.AddProduct(new Product("T001", "Milk Tea", 25,catalog));
            repo.AddProduct(new Product("T002", "Green Tea", 25,catalog));
            repo.AddProduct(new Product("T003", "Green Tea", 15,catalog));
            repo.AddProduct(new Product("T004", "Black Tea", 15,catalog));
            repo.AddProduct(new Product("T005", "Strawberry Tea", 35,catalog));
            repo.AddProduct(new Product("T006", "ชาพีช", 35,catalog));
            repo.AddProduct(new Product("T007", "ชาบลูเบอร์รี่", 35,catalog));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        // เพิ่มสินค้าใน Catalog MILK
        try {
            catalog = "Milk";
            repo.AddProduct(new Product("M001", "นมสด", 15,catalog));
            repo.AddProduct(new Product("M002", "นมชมพู", 20,catalog));
            repo.AddProduct(new Product("M003", "โกโก้", 20,catalog));
            repo.AddProduct(new Product("M004", "นมสตอร์เบอรี่", 30,catalog));
            repo.AddProduct(new Product("M005", "โอวันติน", 20,catalog));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        // เพิ่มสินค้าใน Catalog COFFEE
        try {
            catalog = "Coffee";
            repo.AddProduct(new Product("C001", "ลาเต้", 40,catalog));
            repo.AddProduct(new Product("C002", "มอคค่า", 40,catalog));
            repo.AddProduct(new Product("C003", "อเมริกาโน่", 35,catalog));
            repo.AddProduct(new Product("C004", "เอสเพรสโซ่", 40,catalog));
            repo.AddProduct(new Product("C005", "คาปูชิโน่", 40,catalog));
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
