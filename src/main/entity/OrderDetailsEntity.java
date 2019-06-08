package main.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "orderDetails", schema = "dbo", catalog = "wholesale")
@IdClass(OrderDetailsEntityPK.class)
public class OrderDetailsEntity {
    private int oorderId;
    @Column(nullable=false)
    private int beverageId;
    private int quantity;
    private OorderEntity oorderByOorderId;
    private BeverageEntity beverageByBeverageId;

    @Id
    @Column(name = "oorderId", nullable = false)
    public int getOorderId() {
        return oorderId;
    }

    public void setOorderId(int oorderId) {
        this.oorderId = oorderId;
    }

    @Id
    @Column(name = "beverageId", nullable = false)
    public int getBeverageId() {
        return beverageId;
    }

    public void setBeverageId(int beverageId) {
        this.beverageId = beverageId;
    }

    @Basic
    @Column(name = "quantity", nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailsEntity that = (OrderDetailsEntity) o;
        return oorderId == that.oorderId &&
                beverageId == that.beverageId &&
                quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(oorderId, beverageId, quantity);
    }

    @ManyToOne
    @JoinColumn(name = "oorderId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public OorderEntity getOorderByOorderId() {
        return oorderByOorderId;
    }

    public void setOorderByOorderId(OorderEntity oorderByOorderId) {
        this.oorderByOorderId = oorderByOorderId;
    }

    @ManyToOne
    @JoinColumn(name = "beverageId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public BeverageEntity getBeverageByBeverageId() {
        return beverageByBeverageId;
    }

    public void setBeverageByBeverageId(BeverageEntity beverageByBeverageId) {
        this.beverageByBeverageId = beverageByBeverageId;
    }

    @Override
    public String toString() {
        return "oorderId: " + oorderId + ", beverageId: " + beverageId + " quantity: " + quantity;
    }
}
