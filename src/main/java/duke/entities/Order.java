package duke.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private long id = System.currentTimeMillis();
    private String customerName = "customer";
    private String customerContact = "N/A";
    private Date deliveryDate = Calendar.getInstance().getTime();
    private Map<String, Integer> items = new HashMap<>();
    private String remarks = "N/A";

    public Order() {

    }

    public Order(@JsonProperty("customerName") String customerName,
                 @JsonProperty("customerContact") String customerContact,
                 @JsonProperty("deliveryDate") Date deliveryDate) {
        this.customerName = customerName;
        this.customerContact = customerContact;
        this.deliveryDate = deliveryDate;
    }

    public void addItem(String itemName, int quantity) {
        items.put(itemName, quantity);
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
