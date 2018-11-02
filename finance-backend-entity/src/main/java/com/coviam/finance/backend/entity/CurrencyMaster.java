package com.coviam.finance.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.coviam.finance.master.model.constants.Constants;
import com.coviam.finance.master.model.constants.FieldNames;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Vishal B on Oct, 2018
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = Constants.CURRENCY_MASTER)
public class CurrencyMaster extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = FieldNames.CURRENCY_ID)
  private Long currencyId;

  @Column(name = FieldNames.CURRENCY_CODE)
  private String currencyCode;

  @Column(name = FieldNames.CURRENCY_DESCRIPTION)
  private String currencyDescription;

}
