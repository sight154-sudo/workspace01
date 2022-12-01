package com.huawei.po;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author king
 * @date 2022/11/21-23:14
 * @Desc
 */
@Setter
@Getter
public class OrderVo {
    @NotNull(message = "invalid.param")
    private String id;

    private Double price;
}
