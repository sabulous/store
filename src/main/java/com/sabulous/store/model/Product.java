package com.sabulous.store.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long productId;
    
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_desc")
    private String productDesc;

    @Column(name = "unit_price")
    private Integer unitPriceInPence;

    @Column(name = "promo_applicable")
    private Boolean promoApplicable;

    @Column(name = "deal_applicable")
    private Boolean dealApplicable;

    protected Product() {}

    public Product(String productName, String productDesc) {
        this.productName = productName;
        this.productDesc = productDesc;
    }

    @Override
    public String toString() {
        return "\n#" + productId
                + "\nname:" + productName
                + "\ndesc:" + productDesc;
    }
    public Long getProductId() {
        return productId;
    }

    /**
     * @return the categoryId
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the productDesc
     */
    public String getProductDesc() {
        return productDesc;
    }

    /**
     * @param productDesc the productDesc to set
     */
    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    /**
     * @return the unitPriceInPence
     */
    public Integer getUnitPriceInPence() {
        return unitPriceInPence;
    }

    /**
     * @param unitPriceInPence the unitPriceInPence to set
     */
    public void setUnitPriceInPence(Integer unitPriceInPence) {
        this.unitPriceInPence = unitPriceInPence;
    }

    /**
     * @return the promoApplicable
     */
    public Boolean getPromoApplicable() {
        return promoApplicable;
    }

    /**
     * @param promoApplicable the promoApplicable to set
     */
    public void setPromoApplicable(Boolean promoApplicable) {
        this.promoApplicable = promoApplicable;
    }

    /**
     * @return the dealApplicable
     */
    public Boolean getDealApplicable() {
        return dealApplicable;
    }

    /**
     * @param dealApplicable the dealApplicable to set
     */
    public void setDealApplicable(Boolean dealApplicable) {
        this.dealApplicable = dealApplicable;
    }
}