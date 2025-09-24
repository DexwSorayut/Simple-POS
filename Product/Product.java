/**
 * <b>ADT ที่ไม่มีการเปลี่ยนรูป (Immutable): </b>สำหรับการจัดเก็บข้อมูลสินค้า 
 * <ul>
 *      <li>Product เป็น final</li>
 *      <li>Product เป็น Immutability</li>
 * </ul>
 */
public final class Product {
    private final String ProductID ;
    private final String ProductName ;
    private final double Price ;
    private final String Catalog ;

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
        if (Catalog == null){
            throw new RuntimeException("Error : Catalog is null");
        }
    }

    /**
     * Constructor Product มีหน้าที่ set ค่า
     * @param ProductID รหัสสินค้า
     * @param ProductName ชื่อสินค้า
     * @param Price ราคาสินค้า
     */
    public Product(String ProductID , String ProductName , double Price , String Catalog){
        this.ProductID = ProductID ;
        this.ProductName = ProductName ;
        this.Price = Price ;
        this.Catalog = Catalog ;
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

    public String getCatalog(){
        return Catalog;
    }
    
}
