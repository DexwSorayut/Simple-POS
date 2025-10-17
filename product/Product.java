package product;

/**
 * <b>ADT ที่ไม่มีการเปลี่ยนรูป (Immutable): </b>สำหรับการจัดเก็บข้อมูลสินค้า 
 * <ul>
 *      <li>Product เป็น final</li>
 *      <li>Product เป็น Immutability</li>
 * </ul>
 */
public class Product {
    private String ProductID ;
    private String ProductName ;
    private double Price ;
    private Catalog catalog;
    //private String selectedCatalog = "tea";

    // Rep Invariant (RI) : เงื่อนไขข้อมูลที่ต้องเป็นจริงเสมอ
    //  - ProductID and ProductName are not null or blank.
    //  - Price >= 0.
    // 
    // Abstracttion Function (AF) : 
    //  - AF(ProductID , ProductName , Price) = A product with the ProductID , ProductName and Price.

    /**
     * checkRep มีหน้าที่ตรวจสอบความถูกต้องของ RI
     */
    private void checkRep(){
        if (ProductID == null || ProductID.isBlank()) {
            throw new RuntimeException("Error : ProductID is null");
        }
        if (ProductName == null || ProductName.isBlank()) {
            throw new RuntimeException("Error : ProductName is null");
        }
        if (Price < 0) {
            throw new RuntimeException("Error : Price is less than 0");
        }
        if (catalog == null){
            throw new RuntimeException("Error : Catalog is null");
        }
    }

    /**
     * Constructor Product มีหน้าที่ set ค่า
     * @param ProductID รหัสสินค้า
     * @param ProductName ชื่อสินค้า
     * @param Price ราคาสินค้า
     */
    public Product(String ProductID , String ProductName , double Price , Catalog catalog){
        this.ProductID = ProductID ;
        this.ProductName = ProductName ;
        this.Price = Price ;
        this.catalog = catalog ;
        checkRep();
    }

    public String getProductID(){ 
        return ProductID; 
    }

    public String getProductName(){ 
        return ProductName; 
    }

    public double getPrice(){ 
        return Price; 
    }

    public Catalog getCatalog(){
        return catalog;
    }

    //private String getCurrentCatalog() {
    //    return selectedCatalog;
    //}

    @Override
        public String toString() {  
            return getProductID() + " , " + getProductName() + " , " + getPrice() + " , " + getCatalog() + ".\n";
        }
    
}
