package com.huawei.concurrent;

import cn.hutool.core.date.DateUtil;
import com.sun.xml.internal.ws.api.message.Attachment;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * @author king
 * @date 2024/6/26-23:36
 * @Desc
 */
public class TestDemo {

    @Test
    public void testDemo() {
        List<Attachment> attachments = new ArrayList<>();
        String format = "yyyy-MM-dd HH:mm:ss";
        Date minDate = new Date(Long.MIN_VALUE);
        System.out.println(DateUtil.format(minDate, format));
        attachments.add(new Attachment(19l, "DOC001", DateUtil.parse("2024-06-26 10:00:00", format).toJdkDate()));
        attachments.add(new Attachment(19l, "DOC001", DateUtil.parse("2024-06-25 10:10:00", format).toJdkDate()));
        attachments.add(new Attachment(20l, "DOC002", DateUtil.parse("2024-06-27 10:13:00", format).toJdkDate()));
        attachments.add(new Attachment(17l, "DOC003", DateUtil.parse("2024-06-24 10:15:00", format).toJdkDate()));
        attachments.add(new Attachment(17l, "DOC003", null));
        attachments.add(new Attachment(16l, "DOC004", null));
        List<Attachment> list1 = attachments.stream()
                .collect(Collectors.groupingBy(Attachment::getConfigId)).values().stream().map(list -> list.stream().max(Comparator.comparing(Attachment::getLastUpdateDate, Comparator.nullsFirst(Comparator.naturalOrder()))).get()).collect(Collectors.toList());

        System.out.println(list1);
    }


    @Setter
    @Getter
    class Attachment {
        private Long configId;
        private String docNum;
        private Date lastUpdateDate;

        public Attachment(Long configId, String docNum, Date lastUpdateDate) {
            this.configId = configId;
            this.docNum = docNum;
            this.lastUpdateDate = lastUpdateDate;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Attachment.class.getSimpleName() + "[", "]")
                    .add("configId=" + configId)
                    .add("docNum='" + docNum + "'")
                    .add("lastUpdateDate=" + lastUpdateDate)
                    .toString();
        }
    }
}
