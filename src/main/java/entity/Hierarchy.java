package entity;

public class Hierarchy {
    private Integer hierarchyId;
    private String hierarchyCode;
    private Integer hierarchyLevel;
    private Integer referencedHierarchyLevel;
    private Integer parentHierarchyId;

    private String description;

    public Hierarchy(){}

    public Hierarchy(
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
}
