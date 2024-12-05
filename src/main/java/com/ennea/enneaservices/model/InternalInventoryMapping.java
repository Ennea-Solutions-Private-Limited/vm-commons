package com.ennea.enneaservices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDateTime;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InternalInventoryMapping {

    private Long supplierId;

    private String productCode;

    private String productName;

    private LocalDateTime creationDateTime;

    public InternalInventoryMapping(Long supplierId, String productCode, String productName) {
        this.supplierId = supplierId;
        this.productCode = productCode;
        this.productName = productName;
        this.creationDateTime = LocalDateTime.now();
    }

    @JsonIgnore
    public String getCombo() {
        return supplierId.toString() + ":::" + productCode;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o){
            return true;
        }

        if(o == null || getClass() != o.getClass()){
            return false;
        }

        InternalInventoryMapping that = (InternalInventoryMapping) o;

        return new EqualsBuilder().append(getSupplierId(), that.getSupplierId())
            .append(getProductCode(), that.getProductCode())
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getSupplierId()).append(getProductCode()).toHashCode();
    }
}
