package service;

import java.math.BigDecimal;
import java.util.Date;

public class Revenue {
    private Date date;
    private BigDecimal revenuePerDate;

    public Revenue() {
    }

    public Revenue(Date date, BigDecimal revenuePerDate) {
        this.date = date;
        this.revenuePerDate = revenuePerDate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getRevenuePerDate() {
        return revenuePerDate;
    }

    public void setRevenuePerDate(BigDecimal revenuePerDate) {
        this.revenuePerDate = revenuePerDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Revenue revenue = (Revenue) o;

        if (date != null ? !date.toString().equals(revenue.date.toString()) : revenue.date != null) return false;
        return revenuePerDate != null ? revenuePerDate.toString().equals(revenue.revenuePerDate.toString()) : revenue.revenuePerDate == null;

    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (revenuePerDate != null ? revenuePerDate.hashCode() : 0);
        return result;
    }
}
