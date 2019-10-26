package duke.model.sale;

import java.util.Date;
import java.util.Objects;

import static duke.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Sale in the sale list.
 */
public class Sale {

    //Identity field
    private long id;

    //Data fields
    private String description;
    private double value;
    private boolean isSpend;
    private Date saleDate;
    private String remarks;

    public Sale(String description, double value, boolean isSpend, Date saleDate, String remarks) {
        requireAllNonNull(description, saleDate, value, remarks);
        this.id = System.currentTimeMillis();
        this.description = description;
        this.value = value;
        this.isSpend = isSpend;
        if (isSpend && value > 0.0) {
            value = -value;
        }
        this.saleDate = saleDate;
        this.remarks = remarks;
    }

    /**
     * Creates a sale.
     * Every field must be present and not null.
     */
    public Sale(long id, String description, double value, boolean isSpend, Date saleDate, String remarks) {
        requireAllNonNull(id, description, value, isSpend, saleDate, remarks);

        this.id = id;
        this.description = description;
        this.value = value;
        this.isSpend = isSpend;
        if (isSpend && value > 0.0) {
            value = -value;
        }
        this.saleDate = saleDate;
        this.remarks = remarks;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isSpend() {
        return isSpend;
    }

    public void setSpend(boolean isSpend) {
        this.isSpend = isSpend;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object s) {
        if (this == s) {
            return true;
        }
        if (s == null || getClass() != s.getClass()) {
            return false;
        }
        Sale sale = (Sale) s;
        return id == sale.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}