package com.huawei.conver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import java.io.IOException;
import java.io.StringWriter;

/**
 * @author king
 * @date 2022/3/9-0:08
 * @Desc
 */
public class JsonToXmlByXmlMapper {
    public static void main(String[] args) throws IOException {
        final String jsonStr = "{\"name\":\"JSON\",\"integer\":1,\"double\":2.0,\"boolean\":true,\"nested\":{\"id\":42},\"array\":[1,2,3]}";
        ObjectMapper jsonMapper = new ObjectMapper();
        JsonNode node = jsonMapper.readValue(jsonStr, JsonNode.class);
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_1_1, true);
        StringWriter sw = new StringWriter();
        xmlMapper.writeValue(sw, node);
        System.out.println(sw.toString());
    }
}
