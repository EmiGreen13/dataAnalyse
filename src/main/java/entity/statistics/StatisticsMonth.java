package entity.statistics;

public class StatisticsMonth {

    private Integer month;
    private Double receipts;

    public StatisticsMonth(){}

    public StatisticsMonth(Integer month, Double receipts){
        this.setMonth(month);
        this.setReceipts(receipts);
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getReceipts() {
        return receipts;
    }

    public void setReceipts(Double receipts) {
        this.receipts = receipts;
    }
}
