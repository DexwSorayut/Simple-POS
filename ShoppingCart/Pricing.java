package ShoppingCart;

import java.util.*;

public class Pricing {
     public static double calculateItemPrice(CartItem item) {
        double basePrice = item.getProduct().getPrice();
        double extra = item.getSize().getExtraPrice();
        return (basePrice + extra) * item.getQuantity();
    }

    public static double calculateTotal(List<CartItem> cart) {
        double total = 0;
        for (CartItem item : cart) {
            total += calculateItemPrice(item);
        }
        return total;
    }
}
