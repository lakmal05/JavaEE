package model;

public class ItemDTO {

    private String id;
    private String name;
    private String qty;
    private Double price;

    @Override
    public String toString() {
        return "ItemDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", qty='" + qty + '\'' +
                ", price=" + price +
                '}';
    }

    public ItemDTO() {
    }

    public ItemDTO(String id, String name, String qty, Double price) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.price = price;
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

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
