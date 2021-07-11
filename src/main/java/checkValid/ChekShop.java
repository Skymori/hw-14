package checkValid;

import entity.Product;
import shop.ProductShop;

import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class ChekShop {
    public static double calculateTotalCost(String productBucket) {
        if (validateProductBucket(productBucket)) {
            Map<String, Long> productsWithQuantity = productBucket.chars()
                    .filter(Character::isAlphabetic)
                    .mapToObj(c -> ProductShop.ALL_PRODUCTS.get(String.valueOf((char) c).toUpperCase(Locale.ROOT)))
                    .collect(Collectors.groupingBy(Product::getProductId, Collectors.mapping(Product::getProductId, Collectors.counting())));
            return productsWithQuantity.entrySet().stream()
                    .mapToDouble(entry -> ProductShop.ALL_PRODUCTS.get(entry.getKey()).getPriceByQuantity(entry.getValue()))
                    .sum();
        } else {
            return 0D;
        }

    }

    private static boolean validateProductBucket(String productBucket) {
        if (productBucket == null || productBucket.isEmpty()) return false;
        if (ProductShop.ALL_PRODUCTS == null || ProductShop.ALL_PRODUCTS.isEmpty())
            throw new RuntimeException("DataBaseOfPrices is not available");
        long countOfUnknownProductsId = productBucket.chars()
                .filter(Character::isAlphabetic)
                .filter(c -> !ProductShop.ALL_PRODUCTS.containsKey(String.valueOf((char) c).toUpperCase(Locale.ROOT)))
                .count();
        if (countOfUnknownProductsId != 0L) {
            throw new RuntimeException("Some product ids are not valid");
        }
        return true;
    }
}
