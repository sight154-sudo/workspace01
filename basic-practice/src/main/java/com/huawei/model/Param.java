package com.huawei.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author king
 * @date 2022/9/7-0:26
 * @Desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Param {
    private String memberId;
    private String name;
}
