package entity;

public class ProductToCount {

    private Product product;
    private Integer count;

    public ProductToCount(Product product, Integer count){
        this.setProduct(product);
        this.setCount(count);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
