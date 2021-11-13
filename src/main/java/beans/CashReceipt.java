package beans;


import java.math.BigDecimal;
import java.util.List;
import java.util.Date;

public class CashReceipt {
    private List<LineCheck> lineChecks;
    private Date date;
    private BigDecimal totalCost;

    public CashReceipt() {
    }

    public CashReceipt(List<LineCheck> lineChecks, java.util.Date date) {
        this.lineChecks = lineChecks;
        this.date = date;
    }

    public List<LineCheck> getLineChecks() {
        return lineChecks;
    }

    public void setLineChecks(List<LineCheck> lineChecks) {
        this.lineChecks = lineChecks;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
}
