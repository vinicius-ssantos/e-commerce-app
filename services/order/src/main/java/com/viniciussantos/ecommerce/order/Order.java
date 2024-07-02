package com.viniciussantos.ecommerce.order;


import com.viniciussantos.ecommerce.orderline.OrderLine;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class) // habilitar a auditoria em uma entidade JPA o que permite a utilização das anotações @CreatedDate e @LastModifiedDate.
@NoArgsConstructor
@Table(name = "customer_order")
public class Order {

  @Id
  @GeneratedValue
  private Integer id;

  @Column(unique = true,  nullable = false)
  private String reference;

  private BigDecimal totalAmount;

  @Enumerated(EnumType.STRING)
  private PaymentMethod paymentMethod;

  private String customerId;

  @OneToMany(mappedBy = "order")
  private List<OrderLine> orderLines;

  @CreatedDate // Marca um campo para ser automaticamente preenchido com a data/hora em que a entidade foi criada
  @Column(updatable = false, nullable = false)
  private LocalDateTime createdDate;

  @LastModifiedDate // Marca um campo para ser automaticamente preenchido com a data/hora da última modificação da entidade
  @Column(insertable = false)
  private LocalDateTime lastModifiedDate;
}
