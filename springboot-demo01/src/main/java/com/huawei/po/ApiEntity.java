package com.huawei.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author king
 * @date 2022/7/22-23:38
 * @Desc
 */
@Data
public class ApiEntity {
    private String id;
    private String apiUUID;
    private String apiName;
    private String apiMethod;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createtime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifytime;
    private Integer deleteflag;
}
