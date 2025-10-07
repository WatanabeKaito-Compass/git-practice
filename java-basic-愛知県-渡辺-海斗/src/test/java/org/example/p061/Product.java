package org.example.p061;

/**
 * 製品クラス
 * ストリームAPI演習で使用
 */
public class Product {
    private String name;
    private String category;
    private double price;
    private int quantity;

    public Product(String name, String category, double price, int quantity) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    // ゲッター
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // セッター
    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * 売上金額を計算する
     * @return 売上金額（価格 × 数量）
     */
    public double getSalesAmount() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return String.format("Product{name='%s', category='%s', price=%.2f, quantity=%d, sales=%.2f}", 
                           name, category, price, quantity, getSalesAmount());
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
        return java.util.Objects.hash(name, category, price, quantity);
    }
}

