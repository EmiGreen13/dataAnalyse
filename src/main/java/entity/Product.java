package entity;


public class Product {

    private Integer productId;
    private Integer hierarchyId;
    private String description;
    private String content;

    public Product(){}

    public Product(
        Integer hierarchyId,
        Integer productId,
        String description,
        String content
    ){
        this.setHierarchyId(hierarchyId);
        this.setProductId(productId);
        this.setDescription(description);
        this.setContent(content);
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
}
