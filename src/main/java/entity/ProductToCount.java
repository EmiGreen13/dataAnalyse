package entity;

public class ProductToCount {

    private Integer productToPriceId;
    private Integer count;
    private ProductToPrice productToPrice;


    public ProductToCount(Integer productToPriceId, Integer count){
        this.setProductToPriceId(productToPriceId);
        this.setCount(count);
    }

    public Integer getProductToPriceId() {
        return this.productToPriceId;
    }

    public void setProductToPriceId(Integer productToPriceId) {
        this.productToPriceId = productToPriceId;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof ProductToCount)) {
            return false;
        }

        ProductToCount productToCount = (ProductToCount) o;

        return productToPriceId.intValue() == productToCount.getProductToPriceId();
    }

    @Override
    public int hashCode(){
        return this.productToPriceId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public ProductToPrice getProductToPrice() {
        return productToPrice;
    }

    public void setProductToPrice(ProductToPrice productToPrice) {
        this.productToPrice = productToPrice;
    }
}
