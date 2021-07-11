package shop;

import entity.Product;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProductShop {
    public static Map<String, Product> ALL_PRODUCTS = new ConcurrentHashMap<>();

    static {
        Product a = new Product("A", 1.25D, 3D, 3L);
        Product b = new Product("B", 4.25D);
        Product c = new Product("C", 1.00D, 5D, 6L);
        Product d = new Product("D", 0.75D);

        ALL_PRODUCTS.put(a.getProductId(), a);
        ALL_PRODUCTS.put(b.getProductId(), b);
        ALL_PRODUCTS.put(c.getProductId(), c);
        ALL_PRODUCTS.put(d.getProductId(), d);
    }

}
