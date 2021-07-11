package service;

import entity.Product;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static shop.ProductShop.ALL_PRODUCTS;

public class ShopService {
    public static Map<String, Product> getAllProducts() {
        return new HashMap<>(ALL_PRODUCTS);
    }
    public static void setAllProducts(Map<String, Product> allProducts) {
        Objects.requireNonNull(allProducts);
        ALL_PRODUCTS = allProducts;
    }

    public static void addOrUpdateProduct(Product product) {
        Objects.requireNonNull(product);
        ALL_PRODUCTS.put(product.getProductId(), product);
    }

    public static void removeProductById(String productId) {
        Objects.requireNonNull(productId);
        ALL_PRODUCTS.remove(productId);
    }

    public static void removeProductByProduct(Product product) {
        Objects.requireNonNull(product);
        ALL_PRODUCTS.remove(product.getProductId(), product);
    }

    public static Product getProductById(String productId) {
        Objects.requireNonNull(productId);
        return ALL_PRODUCTS.getOrDefault(productId, null);
    }
}
