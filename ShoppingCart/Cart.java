package ShoppingCart;

import java.util.ArrayList;
import java.util.List;

import product.Product;
import product.Size;

public class Cart {
    private ArrayList<CartItem> cartItems;
    public static List<CartItem> items = new ArrayList<>();

    public Cart() {
        cartItems = new ArrayList<>();
    }

    // เพิ่มสินค้า
    public static void addItem(CartItem item) {
        items.add(item);
    }

    public static List<CartItem> getItems() {
        return items;
    }

    // ลบสินค้าจากตะกร้า (optional)
    public void removeItem(Product product, Size size) {
        cartItems.removeIf(item -> item.getProduct().getProductID().equals(product.getProductID())
                && item.getSize() == size);
    }

    // คืนรายการสินค้าในตะกร้า
    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    // ล้างตะกร้า
    public void clear() {
        cartItems.clear();
    }
}
