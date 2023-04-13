package com.huawei.po;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author king
 * @date 2023/3/29-23:49
 * @Desc
 */
@Setter
@Getter
public class ConfigEntity {
    private Long rowId;
    private String lv1;

    private String channelSpec;
    private String creditSpec;

    private String saleSpec;

    private String contentSpec;

    private String channelFirst;

    private String channelFinal;

    private Person person;


    public void judgeSpec2(ConfigEntity entity) {
        /*if (StringUtils.isNotBlank(entity.getChannelSpec())) {
            this.channelSpec = entity.getChannelSpec();
        }
        if (StringUtils.isNotBlank(entity.getCreditSpec())) {
            this.creditSpec = entity.getCreditSpec();
        }
        if (StringUtils.isNotBlank(entity.getSaleSpec())) {
            this.saleSpec = entity.getSaleSpec();
        }
        if (StringUtils.isNotBlank(entity.getContentSpec())) {
            this.contentSpec = entity.getContentSpec();
        }
        if (StringUtils.isNotBlank(entity.getChannelFirst())) {
            this.channelFirst = entity.getChannelFirst();
        }
        if (StringUtils.isNotBlank(entity.getChannelFinal())) {
            this.channelFinal = entity.getChannelFinal();
        }*/
        // 使用java8 函数式编程优化代码
        Optional.ofNullable(entity.getChannelSpec()).ifPresent(this::setChannelSpec);
        Optional.ofNullable(entity.getCreditSpec()).ifPresent(this::setCreditSpec);

        // 或使用BiConsumer函数接口编程
        setIfNotBlank(entity.getSaleSpec(), ConfigEntity::setSaleSpec);
        try {
            MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private void setIfNotBlank(String value, BiConsumer<ConfigEntity, String> consumer) {
        Optional.ofNullable(value).filter(StringUtils::isNotBlank).ifPresent(v -> consumer.accept(this, v));
    }

    public void judgeSpec(ConfigEntity entity, Set<String> set) {
        if (StringUtils.isNotBlank(entity.getChannelSpec())) {
            set.add(entity.getChannelSpec());
        }
        if (StringUtils.isNotBlank(entity.getCreditSpec())) {
            set.add(entity.getCreditSpec());
        }
        if (StringUtils.isNotBlank(entity.getSaleSpec())) {
            set.add(entity.getSaleSpec());
        }
        if (StringUtils.isNotBlank(entity.getContentSpec())) {
            set.add(entity.getContentSpec());
        }
        if (StringUtils.isNotBlank(entity.getChannelFirst())) {
            set.add(entity.getChannelFirst());
        }
        if (StringUtils.isNotBlank(entity.getChannelFinal())) {
            set.add(entity.getChannelFinal());
        }
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.isSynthetic());
        }
    }


    public static void main(String[] args) {
        ConfigEntity entity = new ConfigEntity();
        Class clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.isSynthetic());
        }
    }
}
