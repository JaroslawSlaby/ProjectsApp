package com.epam.mentoring_p1.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
public class Payment {

    @Id
    @GeneratedValue
    private Long paymentId;
    private Date paymentDate;
    private BigDecimal paymentAmount;


    public boolean isValidPayment() {
        return paymentAmount.doubleValue() > 0d;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        if(paymentAmount.doubleValue() > 0d)
            this.paymentAmount = paymentAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return paymentDate.equals(payment.paymentDate) &&
                paymentAmount.equals(payment.paymentAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentDate, paymentAmount);
    }
}
