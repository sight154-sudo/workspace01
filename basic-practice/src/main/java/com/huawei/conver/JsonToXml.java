package com.huawei.conver;

import org.json.JSONObject;
import org.json.XML;

/**
 * @author king
 * @date 2022/3/9-0:03
 * @Desc
 */
public class JsonToXml {
    public static void main(String[] args) {
//        String jsonStr = "{student : { age:30, name : Kumar, technology : Java } }";
//        String jsonStr = "{student : [{\"age\":30},{\"name\" : \"Kumar\"},{\"technology\":\"Java\"}]}";
        String jsonStr = "{student : { age:30, name : Kumar, technology : Java, hobby: [1,2,3] } }";
        JSONObject json = new JSONObject(jsonStr);
        String xml = XML.toString(json);
        System.out.println(xml);
    }
}
