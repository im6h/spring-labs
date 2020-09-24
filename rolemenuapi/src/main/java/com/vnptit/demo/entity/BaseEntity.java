

package com.vnptit.demo.entity;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;


/**
 * BaseEntity is an abstract class for all Entity .
 * provides all attribute in all entity need use
 * Do not modify this class. This is a first-level abstraction class.
 * If you want use BaseEntity, make another class extends.
 */

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ApiModelProperty(value = "ngay tao")
  @Column
  @CreatedDate
  private Date createdDate;

  @ApiModelProperty(value = "Ngay cap nhat")
  @Column
  @LastModifiedDate
  private Date modifiedDate;

  public Long getId() {
    return id;
  }


  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }


  public Date getModifiedDate() {
    return modifiedDate;
  }

  public void setModifiedDate(Date modifiedDate) {
    this.modifiedDate = modifiedDate;
  }
}