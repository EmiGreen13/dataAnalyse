package entity;


public class ProductToPrice {

    private Integer productToPriceId;
    private Integer hierarchyId;
    private Integer productId;
    private String description;
    private String content;
    private Double price;
    private String hierarchyCode;

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof ProductToPrice)) {
            return false;
        }

        ProductToPrice productToPrice = (ProductToPrice) o;

        return getProductToPriceId().intValue() == productToPrice.getProductToPriceId().intValue()
                & getHierarchyId().intValue() == productToPrice.getHierarchyId().intValue()
                & getProductId().intValue() == productToPrice.getProductId().intValue()
                & getDescription().equals(productToPrice.getDescription())
                & getContent().equals(productToPrice.getContent())
                & getPrice().doubleValue() == productToPrice.getPrice().doubleValue();
    }

    @Override
    public int hashCode(){
        return this.getProductToPriceId();
    }

    public ProductToPrice(){}

    public ProductToPrice(
        Integer productToPriceId,
        Integer hierarchyId,
        Integer productId,
        String description,
        String content,
        Double price
    ){
        this.setProductToPriceId(productToPriceId);
        this.setHierarchyId(hierarchyId);
        this.setProductId(productId);
        this.setDescription(description);
        this.setContent(content);
        this.setPrice(price);
    }

    public ProductToPrice(
            Integer productToPriceId,
            Integer hierarchyId,
            Integer productId,
            String description,
            String content,
            Double price,
            String hierarchyCode
    ){
        this.setProductToPriceId(productToPriceId);
        this.setHierarchyId(hierarchyId);
        this.setProductId(productId);
        this.setDescription(description);
        this.setContent(content);
        this.setPrice(price);
        this.setHierarchyCode(hierarchyCode);
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHierarchyId() {
        return hierarchyId;
    }

    public void setHierarchyId(Integer hierarchyId) {
        this.hierarchyId = hierarchyId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getProductToPriceId() {
        return productToPriceId;
    }

    public void setProductToPriceId(Integer productToPriceId) {
        this.productToPriceId = productToPriceId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getHierarchyCode() {
        return hierarchyCode;
    }

    public void setHierarchyCode(String hierarchyCode) {
        this.hierarchyCode = hierarchyCode;
    }
}
