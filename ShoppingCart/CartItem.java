package ShoppingCart;

import product.*;

public class CartItem {

    private Product product;  // สินค้า
    private Size size;        // ขนาด
    private int quantity;     // จำนวน

    public CartItem(Product product, Size size, int quantity) {
        this.product = product;
        this.size = size;
        this.quantity = quantity;
    }

    // getter / setter
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
}
