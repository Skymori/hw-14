package checkValid;
import java.util.Locale;

public class CheckProduct {
    public static boolean isProductIdValid(String productId) {
        String englishAlphabet = "ABCDEFGHIGKLMNOPQRSTUVWXYZ";
        if (productId != null && productId.length() == 1 && englishAlphabet.contains(productId.toUpperCase(Locale.ROOT))) {
            return true;
        } else {
            throw new RuntimeException("Product id is not valid");
        }
    }
    public static boolean isPriceValid(double price) {
        if (price > 0D) {
            return true;
        } else {
            throw new RuntimeException("Price is not valid");
        }
    }
    public static boolean isQuantityValid(long quantity) {
        if (quantity > 0L) {
            return true;
        } else {
            throw new RuntimeException("Quantity is not valid");
        }
    }

}
