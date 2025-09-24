import UI.Discount;
import User.*;

public class Main {
    public static void main(String[] args) {
        new ProductService();
        System.out.println();
        new UserService();
        new Discount().setVisible(true);
    }
}
