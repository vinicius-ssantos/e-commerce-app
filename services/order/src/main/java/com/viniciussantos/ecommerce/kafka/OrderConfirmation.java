package com.viniciussantos.ecommerce.kafka;

import com.viniciussantos.ecommerce.customer.CustomerResponse;
import com.viniciussantos.ecommerce.order.PaymentMethod;
import com.viniciussantos.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> purchases
) {
}
