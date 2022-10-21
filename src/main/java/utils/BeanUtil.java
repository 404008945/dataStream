package utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import exception.DataStreamException;

import java.util.Map;

public class BeanUtil {
    public static Map<String, Object> beanToMap(Object bean) {
        if (bean == null) {
            throw new DataStreamException("bean.can.not.be.null");
        }
        return JSON.parseObject(JSON.toJSONString(bean), new TypeReference<Map<String, Object>>() {
        });
    }

    public static <T> T mapToBean(Map data, Class<T> clazz) {
        if (data == null) {
            throw new DataStreamException("bean.can.not.be.null");
        }
        return JSON.parseObject(JSON.toJSONString(data),clazz);
    }
}
