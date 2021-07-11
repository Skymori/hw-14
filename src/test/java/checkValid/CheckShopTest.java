package checkValid;

import entity.Product;
import org.junit.jupiter.api.Test;
import service.ShopService;

import java.util.concurrent.ConcurrentHashMap;

import static org.assertj.core.api.Assertions.*;
public class CheckShopTest {
    private static final double EPSILON = 0.000000001;
    private static Product testProduct;
    @Test
    void calculateTotalCost() {
        String productbucketWithSpaces = " ABCDABA";
        String productbucketCorrectString = "ABCDABACCAACCC";
        String productbucketWithNotAlphabetic = " A2BC4DABA";
        String productbucketWithSmallLetters = " AbcDABa";
        String productbucketWithRussianLetters = " ABCПРDABA";
        String productbucketWithIdThatAreNotInAllProducts = " ABCDABAEF";

        assertThat(ChekShop.calculateTotalCost(productbucketWithSpaces)).isEqualTo(13.25, offset(EPSILON));
        assertThat(ChekShop.calculateTotalCost(productbucketCorrectString)).isEqualTo(19.75, offset(EPSILON));
        assertThat(ChekShop.calculateTotalCost(productbucketWithNotAlphabetic)).isEqualTo(13.25, offset(EPSILON));
        assertThat(ChekShop.calculateTotalCost(productbucketWithSmallLetters)).isEqualTo(13.25, offset(EPSILON));
        assertThat(ChekShop.calculateTotalCost("")).isEqualTo(0D, offset(EPSILON));
        assertThat(ChekShop.calculateTotalCost(null)).isEqualTo(0D, offset(EPSILON));

        assertThatThrownBy(() -> {
            ChekShop.calculateTotalCost(productbucketWithRussianLetters);
        }).isInstanceOf(RuntimeException.class)
                .hasMessage("Some product ids are not valid");
        assertThatThrownBy(() -> {
            ChekShop.calculateTotalCost(productbucketWithIdThatAreNotInAllProducts);
        }).isInstanceOf(RuntimeException.class)
                .hasMessage("Some product ids are not valid");


        ShopService.setAllProducts(new ConcurrentHashMap<>());
        assertThatThrownBy(() -> {
            ChekShop.calculateTotalCost(productbucketWithIdThatAreNotInAllProducts);
        }).isInstanceOf(RuntimeException.class)
                .hasMessage("DataBaseOfPrices is not available");
    }
}
