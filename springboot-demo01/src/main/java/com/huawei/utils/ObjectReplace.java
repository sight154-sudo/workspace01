package com.huawei.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huawei.po.User;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author king
 * @date 2022/6/21-0:47
 * @Desc
 */
public class ObjectReplace {
    public static void main(String[] args) throws Exception {
        User user1 = new User(1,"zhangsan",true,12,1000d,new Date(),new Date(),new Date());

        User user2 = new User(2,"lisi",false,23,1500d,new Date(),new Date(),new Date());

        Object info = exchangeEntityInfo(user1, user2, new Object[]{"id", "userName", "age"});

        System.out.println(info);
    }

    //注意需要jackson包  或者自己将实体类继承序列化、克隆等接口也行，深拷贝时用到,将深拷贝部分的代码重新写一下

    /**
     * Description: 对象信息互换
     * date: 2020/11/26
     * @param : object1 object2互换对象
     * @param : ...param 需要忽略互换的字段
     * @return : 失败返回hashMap格式;成功返回实体类list
     * @author: qkj
     */

    public static Object exchangeEntityInfo(Object object1, Object object2, Object... param) throws Exception {
        Class aClass1 = object1.getClass();
        Class aClass2 = object2.getClass();

        if (aClass1 != aClass2) {
            Map<String,String> map = new HashMap<String, String>();
            map.put("error", "类型不一致");
            return map;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Object temp1 = objectMapper.readValue(objectMapper.writeValueAsString(object1), object1.getClass());
        Object temp2 = objectMapper.readValue(objectMapper.writeValueAsString(object2), object2.getClass());

        object1 = object2;
        object2 = temp1;

        // 所有属性
        Field[] declaredFields = object1.getClass().getDeclaredFields();
        // 所有方法
        Method[] declaredMethods = object1.getClass().getDeclaredMethods();

        ArrayList<Object> objects = new ArrayList<>();
        // 循环无需替换的参数
        for (int i = 0; i < param.length; i++) {
            // 循环交换后对象的方法
            for (Method method : declaredMethods) {
                method.setAccessible(true);
                // 如果方法名相等 则把旧数据set回去
                if (method.getName().equalsIgnoreCase("SET" + param[i])) {
                    for (Field field : declaredFields) {
                        if (field.getName().equals(param[i])) {
                            method.invoke(object1, field.get(temp1));
                        }
                    }

                    for (Field field : declaredFields) {
                        if (field.getName().equals(param[i])) {
                            method.invoke(object2, field.get(temp2));
                        }
                    }

                }
            }
        }
        objects.add(object1);
        objects.add(object2);

        return objects;
    }

}
