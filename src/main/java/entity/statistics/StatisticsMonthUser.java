package entity.statistics;


import java.sql.Date;

public class StatisticsMonthUser {

    private Integer productId;
    private String hierarchyCode;
    private Double price;
    private Integer quantity;
    private Double receipts;
    private Date date;
    private String description;

    public StatisticsMonthUser(){}

    public StatisticsMonthUser(
            Integer productId,
            String hierarchyCode,
            Double price,
            Integer quantity,
            Double receipts,
            Date date,
            String description
    ){
        this.setProductId(productId);
        this.setHierarchyCode(hierarchyCode);
        this.setPrice(price);
        this.setQuantity(quantity);
        this.setReceipts(receipts);
        this.setDate(date);
        this.setDescription(description);
    }


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getHierarchyCode() {
        return hierarchyCode;
    }

    public void setHierarchyCode(String hierarchyCode) {
        this.hierarchyCode = hierarchyCode;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getReceipts() {
        return receipts;
    }

    public void setReceipts(Double receipts) {
        this.receipts = receipts;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
