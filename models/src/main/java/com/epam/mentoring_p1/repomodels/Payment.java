package com.epam.mentoring_p1.repomodels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Payment {

    @Id
    @GeneratedValue
    private Long paymentId;
    private LocalDate paymentDate;
    private BigDecimal paymentAmount;

    public Payment() {
    }

    public Payment(LocalDate paymentDate, BigDecimal paymentAmount) {
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
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
