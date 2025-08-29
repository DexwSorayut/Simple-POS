public class ProductService {
    public ProductService(){

        ProductRepository repo = new ProductRepository();

        // โหลดสินค้า
        repo.loadFromFile();

        // เพิ่มสินค้าใหม่
        try {
            repo.AddProduct(new Product("D001", "Milk Tea", 40,"TEA"));
            repo.AddProduct(new Product("D002", "Coffee", 35,"COFFEE"));
            repo.AddProduct(new Product("D001", "Fanta", 15,"SODA"));
            repo.AddProduct(new Product("D003", "Coke", 15,"SODA"));
            repo.AddProduct(new Product("D004", "Milk", 10,"MILK"));
            repo.AddProduct(new Product("D005", "Apple juice", 15,"JUICE"));
            repo.AddProduct(new Product("D006", "Apple juice", 30,"JUICE"));
            repo.AddProduct(new Product("D007", "Tea", 10,"TEA"));
        } catch (RuntimeException e){
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
