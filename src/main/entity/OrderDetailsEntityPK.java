package main.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class OrderDetailsEntityPK implements Serializable {
    private int oorderId;
    private int beverageId;

    @Column(name = "oorderId", nullable = false)
    @Id
    public int getOorderId() {
        return oorderId;
    }

    public void setOorderId(int oorderId) {
        this.oorderId = oorderId;
    }

    @Column(name = "beverageId", nullable = false)
    @Id
    public int getBeverageId() {
        return beverageId;
    }

    public void setBeverageId(int beverageId) {
        this.beverageId = beverageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailsEntityPK that = (OrderDetailsEntityPK) o;
        return oorderId == that.oorderId &&
                beverageId == that.beverageId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(oorderId, beverageId);
    }
}
