package ShoppingCart;

import product.Product;
import product.Size;

public class CartItem {
    private Product product; // สินค้า
    private Size size;       // ขนาด (Normal / Large / Largest)
    private int quantity;    // จำนวน

    public CartItem(Product product, Size size, int quantity) {
        this.product = product;
        this.size = size;
        this.quantity = quantity;
    }

    // --- Getter / Setter ---
    public Product getProduct() {
        return product;
    }

    public Size getSize() {
        return size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // --- ราคาเพิ่มจากขนาด ---
    public double getSizePrice() {
        return size.getExtraPrice();
    }
}