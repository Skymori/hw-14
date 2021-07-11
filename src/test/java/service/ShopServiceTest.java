package service;

import entity.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.Map;

public class ShopServiceTest {
    private static Map<String, Product> allProductCopy;
    private static final double EPSILON = 0.000000001;
    private static Product h;


    @BeforeEach
    void setUp() {
        allProductCopy = ShopService.getAllProducts();
        h = new Product("H", 1.5D, 4D, 3L);
        ShopService.addOrUpdateProduct(h);
    }
    @AfterEach
    void tearDown() {
        ShopService.setAllProducts(allProductCopy);
    }
    @Test
    void getAllProducts() {
        assertThat(ShopService.getAllProducts().size()).isGreaterThan(0);
        assertThat(ShopService.getAllProducts()).containsValue(h);
    }
    @Test
    void setAllProducts() {
        Product a = new Product("A", 1.25D, 3D, 3L);
        Product b = new Product("B", 4.25D);

        Map<String, Product> testMap = Map.of(a.getProductId(), a, b.getProductId(), b);
        ShopService.setAllProducts(testMap);
        assertThat(testMap).isEqualTo(ShopService.getAllProducts());

        assertThatThrownBy(() -> ShopService.setAllProducts(null)).isInstanceOf(NullPointerException.class);
    }
    @Test
    void addOrUpdateProduct() {
        assertThat(ShopService.getAllProducts()).containsKey(h.getProductId());
        assertThat(ShopService.getAllProducts()).containsValue(h);
        h.setPromotionalPrice(4.25D);
        assertThat(ShopService.getProductById(h.getProductId()).getPromotionalPrice()).isEqualTo(4.25D, offset(EPSILON));
        assertThatThrownBy(() -> ShopService.addOrUpdateProduct(null)).isInstanceOf(NullPointerException.class);
    }
    @Test
    void removeProductById() {
        assertThat(ShopService.getAllProducts()).containsValue(h);
        ShopService.removeProductById("H");
        assertThat(ShopService.getAllProducts()).doesNotContainValue(h);
        assertThatThrownBy(() -> ShopService.removeProductById(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void removeProductByProduct() {
        assertThat(ShopService.getAllProducts()).containsValue(h);
        ShopService.removeProductByProduct(h);
        assertThat(ShopService.getAllProducts()).doesNotContainValue(h);
        assertThatThrownBy(() -> ShopService.removeProductByProduct(null)).isInstanceOf(NullPointerException.class);
    }
    @Test
    void getProductById() {
        assertThat(ShopService.getProductById(h.getProductId())).isEqualTo(h);
        assertThatThrownBy(() -> ShopService.getProductById(null)).isInstanceOf(NullPointerException.class);
    }
}
