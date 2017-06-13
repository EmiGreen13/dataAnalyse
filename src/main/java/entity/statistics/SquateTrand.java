package entity.statistics;


public class SquateTrand {

    private Integer month;
    private Double receipts;
    private Double avg;

    public SquateTrand(){}

    public SquateTrand(
            Integer month,
            Double receipts,
            Double avg
    ){
        this.setMonth(month);
        this.setReceipts(receipts);
        this.setAvg(avg);
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

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }
}
