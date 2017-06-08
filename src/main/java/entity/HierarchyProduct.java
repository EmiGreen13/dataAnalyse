package entity;

public class HierarchyProduct {
    private Integer hierarchyId;
    private String hierarchyCode;
    private Integer hierarchyLevel;
    private Integer referencedHierarchyLevel;
    private Integer parentHierarchyId;
    private Integer productId;
    private String content;
    private String description;

    public HierarchyProduct(){}

    public HierarchyProduct(
            Integer hierarchyId,
            String hierarchyCode,
            Integer hierarchyLevel,
            Integer referencedHierarchyLevel,
            Integer parentHierarchyId,
            String description
    ){
        this.setHierarchyId(hierarchyId);
        this.setHierarchyCode(hierarchyCode);
        this.setHierarchyLevel(hierarchyLevel);
        this.setReferencedHierarchyLevel(referencedHierarchyLevel);
        this.setParentHierarchyId(parentHierarchyId);

        this.setDescription(description);
    }

    public HierarchyProduct(
            Integer hierarchyId,
            String hierarchyCode,
            Integer hierarchyLevel,
            Integer referencedHierarchyLevel,
            Integer parentHierarchyId,
            Integer productId,
            String content,
            String description
    ){
        this.setHierarchyId(hierarchyId);
        this.setHierarchyCode(hierarchyCode);
        this.setHierarchyLevel(hierarchyLevel);
        this.setReferencedHierarchyLevel(referencedHierarchyLevel);
        this.setParentHierarchyId(parentHierarchyId);
        this.setProductId(productId);
        this.setContent(content);
        this.setDescription(description);
    }

    public Integer getHierarchyId() {
        return hierarchyId;
    }

    public void setHierarchyId(Integer hierarchyId) {
        this.hierarchyId = hierarchyId;
    }

    public String getHierarchyCode() {
        return hierarchyCode;
    }

    public void setHierarchyCode(String hierarchyCode) {
        this.hierarchyCode = hierarchyCode;
    }

    public Integer getHierarchyLevel() {
        return hierarchyLevel;
    }

    public void setHierarchyLevel(Integer hierarchyLevel) {
        this.hierarchyLevel = hierarchyLevel;
    }

    public Integer getReferencedHierarchyLevel() {
        return referencedHierarchyLevel;
    }

    public void setReferencedHierarchyLevel(Integer referencedHierarchyLevel) {
        this.referencedHierarchyLevel = referencedHierarchyLevel;
    }

    public Integer getParentHierarchyId() {
        return parentHierarchyId;
    }

    public void setParentHierarchyId(Integer parentHierarchyId) {
        this.parentHierarchyId = parentHierarchyId;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
