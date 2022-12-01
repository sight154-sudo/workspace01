package com.huawei.po;

import lombok.Getter;
import lombok.Setter;

/**
 * @author king
 * @date 2022/11/21-23:46
 * @Desc
 */
@Setter
@Getter
public class OperatorLog {
    private long id;

    private String desc;

    private Object result;

    public OperatorLog() {
    }

    public OperatorLog(long id, String desc, Object result) {
        this.id = id;
        this.desc = desc;
        this.result = result;
    }

    public OperatorLog(long id) {
        this.id = id;
    }
}
