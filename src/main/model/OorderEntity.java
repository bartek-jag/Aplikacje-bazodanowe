package main.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "oorder", schema = "dbo", catalog = "wholesale")
public class OorderEntity {
    private int id;
    private Timestamp orderDate;
    private CustomerEntity customerByCustomerId;
    private EmployeeEntity employeeByEmployeeId;
    private Collection<OrderDetailsEntity> orderDetailsById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Basic
    @Column(name = "orderDate", nullable = false)
    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OorderEntity that = (OorderEntity) o;
        return id == that.id &&
                Objects.equals(orderDate, that.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDate);
    }

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id", nullable = false)
    public CustomerEntity getCustomerByCustomerId() {
        return customerByCustomerId;
    }

    public void setCustomerByCustomerId(CustomerEntity customerByCustomerId) {
        this.customerByCustomerId = customerByCustomerId;
    }

    @ManyToOne
    @JoinColumn(name = "employeeId", referencedColumnName = "id", nullable = false)
    public EmployeeEntity getEmployeeByEmployeeId() {
        return employeeByEmployeeId;
    }

    public void setEmployeeByEmployeeId(EmployeeEntity employeeByEmployeeId) {
        this.employeeByEmployeeId = employeeByEmployeeId;
    }

    @OneToMany(mappedBy = "oorderByOorderId")
    public Collection<OrderDetailsEntity> getOrderDetailsById() {
        return orderDetailsById;
    }

    public void setOrderDetailsById(Collection<OrderDetailsEntity> orderDetailsById) {
        this.orderDetailsById = orderDetailsById;
    }
}
