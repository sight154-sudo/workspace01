package com.huawei.enums;

import java.util.stream.IntStream;

/**
 * @author king
 * @date 2023/2/21-23:49
 * @Desc
 */
public enum StatusEnum {
    CREATED(1001,"已创建"),
    PAID(1002,"已支付"),
    DELIVERED(1003,"已送到"),
    FINISHED(1004,"已完成"),
    ;

    private Integer status;
    private String desc;

    StatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static void main(String[] args) {
        Integer status = 1001;
        System.out.println(StatusEnum.CREATED.status == status);
        IntStream.range(1,100).forEach(System.out::println);
    }

}
