
import exception.DataStreamException;
import net.sf.cglib.beans.BeanMap;
import utils.BeanUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于聚合数据，能够支持表数据的join获取数据，代表一行数据，需要能够聚合多行数据
 */
public class AggregationData {
    Map<String, BeanMap> aggregationMap;

    private AggregationData(){
        aggregationMap = new HashMap<>();
    }

    //key 为别名，value为对应对象
    public AggregationData(String tableName, Object data) {
        aggregationMap = new HashMap<>();
        aggregationMap.put(tableName, BeanUtil.beanToBeanMap(data));
    }

    public Map<String, BeanMap> getRowAllData() {
        return aggregationMap;
    }

    public BeanMap getTableData(String tableName) {
        if (!aggregationMap.containsKey(tableName)) {
            throw new DataStreamException(tableName + ".not.exists");
        }
        return aggregationMap.get(tableName);
    }

    public void setTableData(String tableName, Object data) {
        if(aggregationMap.containsKey(tableName)){
            throw new DataStreamException(tableName+".has.been.exists!");
        }
        aggregationMap.put(tableName, BeanUtil.beanToBeanMap(data));
    }


    private void setTableData(String tableName, BeanMap data) {
        if(aggregationMap.containsKey(tableName)){
            throw new DataStreamException(tableName+".has.been.exists!");
        }
        if(data == null){
            throw new DataStreamException("data.can.not.be.null");
        }
        aggregationMap.put(tableName, data);
    }

    public AggregationData copyAggregationData() {
        AggregationData aggregationData = new AggregationData();
        for (String tableName : this.getRowAllData().keySet()) {
            aggregationData.setTableData(tableName, this.getRowAllData().get(tableName));
        }
        return aggregationData;
    }
}