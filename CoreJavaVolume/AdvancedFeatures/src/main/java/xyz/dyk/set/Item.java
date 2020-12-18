package xyz.dyk.set;

import xyz.dyk.bytecodeAnnotations.LogEntry;

import java.util.Objects;

/**
 * An item with a description and a part number.
 */
public class Item {
    private String description;
    private int partNumber;

    public Item(String description, int partNumber) {
        this.description = description;
        this.partNumber = partNumber;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Item{" +
                "description='" + description + '\'' +
                ", partNumber=" + partNumber +
                '}';
    }

    @Override
    @LogEntry(logger = "xyz.dyk")
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return partNumber == item.partNumber &&
                Objects.equals(description, item.description);
    }

    @Override
    @LogEntry(logger = "xyz.dyk")
    public int hashCode() {
        return Objects.hash(description, partNumber);
    }
}
