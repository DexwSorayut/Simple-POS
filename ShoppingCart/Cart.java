package ShoppingCart;

import java.util.ArrayList;
import java.util.List;

import product.Product;
import product.Size;

public class Cart {
    private ArrayList<CartItem> cartItems;
    private static List<CartItem> items = new ArrayList<>();

    public Cart() {
        cartItems = new ArrayList<>();
    }

    // เพิ่มสินค้า
    public static void addItem(CartItem item) {
        items.add(item);
        System.out.println("Added to cart: " + item.getProduct().getProductName()
                + " - " + item.getSize() + " x " + item.getQuantity());
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
