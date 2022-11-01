package utils;

import exception.DataStreamException;
import net.sf.cglib.beans.BeanMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class BeanUtil {

    /**
     * 将javaBean转换为hashMap返回
     * @param bean
     * @param bean
     * @return
     */
    public static Map<String, Object> beanToMap(Object bean) {
        if (bean == null) {
            throw new DataStreamException("bean.can.not.be.null");
        }
        Map<String, Object> map = new HashMap<>();

        BeanMap beanMap = BeanMap.create(bean);
        for (Object key : beanMap.keySet()) {
            map.put(key + "", beanMap.get(key));
        }

        return map;
    }

    public static BeanMap beanToBeanMap(Object bean) {
        if (bean == null) {
            throw new DataStreamException("bean.can.not.be.null");
        }
        return BeanMap.create(bean);
    }

    public static <T> T mapToBean(Map map, T bean) {
        if (map == null) {
            throw new DataStreamException("bean.can.not.be.null");
        }
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    public static <T> T beanMapToBean(BeanMap beanMap) {
        if (beanMap == null) {
            throw new DataStreamException("bean.can.not.be.null");
        }
        return (T) beanMap.getBean();
    }

    /**
     * 将List转换为List<Map<String, Object>>
     * @param objList
     * @return
     */
    public static List<Map<String, Object>> objectsToMaps(List objList) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (objList != null && objList.size() > 0) {
            Map<String, Object> map = null;
            Object bean;
            for (int i = 0,size = objList.size(); i < size; i++) {
                bean = objList.get(i);
                map = beanToMap(bean);
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 将List<Map<String,Object>>转换为List
     * @param maps
     * @param clazz
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static<T> List<T> mapsToObjects(List<Map<String, Object>> maps,Class<T> clazz){
        List list = new ArrayList();
        if (maps != null && maps.size() > 0) {
            Map<String, Object> map = null;
            T bean;
            for (int i = 0,size = maps.size(); i < size; i++) {
                map = maps.get(i);
                try {
                    bean = clazz.newInstance();
                } catch (Exception e) {
                    throw new DataStreamException("使用无参构造创建实例事变" + e.getMessage());
                }
                mapToBean(map, bean);
                list.add(bean);
            }
        }
        return list;
    }


    public static <T> T mapToBean(Map data, Class<T> clazz) {
        if (data == null) {
            throw new DataStreamException("bean.can.not.be.null");
        }
        if (clazz == null) {
            throw new DataStreamException("clazz.can.not.be.null");
        }
        if(data instanceof BeanMap){
            return beanMapToBean((BeanMap)data);
        }
        T bean;
        try {
            bean = clazz.newInstance();
        } catch (Exception e) {
            throw new DataStreamException("使用无参构造创建实例事变" + e.getMessage());
        }
        return mapToBean(data, bean);
    }
}
