package org.example.p060;

/**
 * 商品クラス
 * ストリームAPIの演習用
 */
public class Product {
    private String name;
    private double price;
    private String category;
    private int quantity;

    public Product(String name, double price, String category, int quantity) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    // ゲッター
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    // セッター
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("Product{name='%s', price=%.2f, category='%s', quantity=%d}", 
                           name, price, category, quantity);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return Double.compare(product.price, price) == 0 && 
               quantity == product.quantity && 
               name.equals(product.name) && 
               category.equals(product.category);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, price, category, quantity);
    }
}
