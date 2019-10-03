package com.epam.mentoring_p1.dtomodels;

import java.io.Serializable;
import java.math.BigDecimal;

public final class PaymentDTO implements Serializable {

  private BigDecimal paymentValue;

  public BigDecimal getPaymentValue() {
    return paymentValue;
  }
}
