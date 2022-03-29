package com.huawei.conver;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

/**
 * @author king
 * @date 2022/3/3-23:18
 * @Desc
 */
public class JsonToXmlDemo {
    public static void main(String[] args) {
        String head = "{\"query:agent\":\"aaa\",\"query:content\":\"2021-10-1\"}";
        String param = "{\"query:pageIndex\":\"<? aaa ?>\",\"query:pageSize\":\"100\"}";
        String content = "{\"ser:head\":"+head+",\"ser:param\":"+param+",\"ser:systemId\":\"systemId\"}";
        content = "{}";
        JSONObject json = JSONObject.fromObject(content);
        XMLSerializer xmlSerializer = new XMLSerializer();
        xmlSerializer.setRootName("huawei");
        xmlSerializer.setTypeHintsEnabled(false);
        String res = xmlSerializer.write(json);
        System.out.println("res = " + res);
    }
}
