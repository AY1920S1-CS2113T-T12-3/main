package duke.logic.command.inventory;

import org.ocpsoft.prettytime.shade.org.apache.commons.lang.StringUtils;

import java.util.Optional;

/**
 * A class that stores the details of an ingredient.
 */
public class InventoryDescriptor {
    private String name;
    private Double quantity;
    private String remarks;

    public InventoryDescriptor() {

    }

    /**
     * Creates an InventoryDescriptor constructor and sets its values with toCopy's values
     */
    public InventoryDescriptor(InventoryDescriptor toCopy) {
        setName(toCopy.name);
        setQuantity(toCopy.quantity);
        setRemarks(toCopy.remarks);
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public void setName(String name) {
        this.name = StringUtils.capitalize(name.toLowerCase());
    }

    public Optional<Double> getQuantity() {
        return Optional.ofNullable(quantity);
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Optional<String> getRemarks() {
        return Optional.ofNullable(remarks);
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
