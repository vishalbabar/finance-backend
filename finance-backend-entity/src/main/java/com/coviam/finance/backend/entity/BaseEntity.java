package com.coviam.finance.backend.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.coviam.finance.master.model.constants.FieldNames;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Vishal B on Oct, 2018
 */

@Data
@EqualsAndHashCode
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = FieldNames.STORE_ID, nullable = false)
  private int storeId;

  @Column(name = FieldNames.CREATED_DATE, nullable = false)
  private Date createdDate;

  @Column(name = FieldNames.CREATED_BY, nullable = false)
  private String createdBy;

  @Column(name = FieldNames.UPDATED_DATE)
  private Date updatedDate;

  @Column(name = FieldNames.UPDATED_BY)
  private String updatedBy;

  @Column(name = FieldNames.MARK_FOR_DELETE, nullable = false)
  private boolean markForDelete;

}
