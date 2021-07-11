package entity;

import checkValid.CheckProduct;

import java.util.Objects;

public class Product {
    private String productId;
    private double price;
    private double promotionalPrice;
    private long promotionalQuantity;

    public Product(String productId, double price, double promotionalPrice, long promotionalQuantity) {
        if (CheckProduct.isProductIdValid(productId) && CheckProduct.isPriceValid(price) &&
                CheckProduct.isPriceValid(promotionalPrice) && CheckProduct.isQuantityValid(promotionalQuantity)) {
            this.productId = productId;
            this.price = price;
            this.promotionalPrice = promotionalPrice;
            this.promotionalQuantity = promotionalQuantity;
        }
    }
    public Product(String productId, double price) {
        this(productId, price, price, Long.MAX_VALUE);
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (CheckProduct.isPriceValid(price)) {
            this.price = price;
        }
    }

    public double getPromotionalPrice() {
        return promotionalPrice;
    }

    public void setPromotionalPrice(double promotionalPrice) {
        if (CheckProduct.isPriceValid(promotionalPrice)) {
            this.promotionalPrice = promotionalPrice;
        }
    }

    public long getPromotionalQuantity() {
        return promotionalQuantity;
    }

    public void setPromotionalQuantity(long promotionalQuantity) {
        if (CheckProduct.isQuantityValid(promotionalQuantity)) {
            this.promotionalQuantity = promotionalQuantity;
        }
    }

    public double getPriceByQuantity(long quantity) {
        if (quantity < 0) {
            throw new RuntimeException("Quantity negative");
        }
        if (quantity < promotionalQuantity) {
            return price * quantity;
        } else {
            return (quantity / promotionalQuantity) * promotionalPrice + (quantity % promotionalQuantity) * price;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Double.compare(product.getPrice(), getPrice()) == 0 && Double.compare(product.getPromotionalPrice(), getPromotionalPrice()) == 0 && getPromotionalQuantity() == product.getPromotionalQuantity() && Objects.equals(getProductId(), product.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getPrice(), getPromotionalPrice(), getPromotionalQuantity());
    }
}
