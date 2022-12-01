package com.huawei.convert;

import com.huawei.po.OperatorLog;

/**
 * @author king
 * @date 2022/11/22-0:06
 * @Desc
 */
public interface IConvert<PARAM> {
    OperatorLog convert(PARAM param);
}
