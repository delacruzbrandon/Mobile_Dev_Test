package dcbrh.ph.mobiledevtest;

public class Product {
    private String id, name, category, price, bgColor;

    @Override
    public String toString() {
        return "Product :" +
                "id: " + id +
                ", name: " + name +
                ", category: " + category +
                ", price: " + price +
                ", bgColor: " + bgColor;
    }

    public Product(String id, String name, String category, String price, String bgColor) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.bgColor = bgColor;
    }

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
