package com.coviam.finance.backend.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "currencyMaster")
public class CurrencyMaster {

  @Id
  @Column(name = "currency_id")
  private Long currencyId;

  @Column(name = "currency_code")
  private String currencyCode;

  @Column(name = "currency_description")
  private String currencyDescription;

}
