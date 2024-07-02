package com.viniciussantos.ecommerce.payment;



import com.viniciussantos.ecommerce.customer.CustomerResponse;
import com.viniciussantos.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
    BigDecimal amount,
    PaymentMethod paymentMethod,
    Integer orderId,
    String orderReference,
    CustomerResponse customer
) {
}
