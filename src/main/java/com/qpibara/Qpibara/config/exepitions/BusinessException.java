package com.qpibara.Qpibara.config.exepitions;


import lombok.Data;

@Data
public class BusinessException extends RuntimeException {
    private final BusinessCode businessCode;

    public BusinessException(BusinessCode businessCode) {
        super(businessCode.getDescription());
        this.businessCode = businessCode;
    }
}
