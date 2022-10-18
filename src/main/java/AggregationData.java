
import exception.DataStreamException;
import utils.BeanUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 用于聚合数据，能够支持表数据的join获取数据，代表一行数据，需要能够聚合多行数据
 */
public class AggregationData {
    Map<String, Map> aggregationMap;

    private AggregationData(){
        aggregationMap = new HashMap<>();
    }

    //key 为别名，value为对应对象
    public AggregationData(String tableName, Object data) {
        aggregationMap = new HashMap<>();
        aggregationMap.put(tableName, BeanUtil.beanToMap(data));
    }

    public Map<String, Map> getRowAllData() {
        return aggregationMap;
    }

    public Map getTableData(String tableName) {
        if (!aggregationMap.containsKey(tableName)) {
            throw new DataStreamException(tableName + ".not.exists");
        }
        return aggregationMap.get(tableName);
    }

    public void setTableData(String tableName, Object data) {
        if(aggregationMap.containsKey(tableName)){
            throw new DataStreamException(tableName+".has.been.exists!");
        }
        aggregationMap.put(tableName, BeanUtil.beanToMap(data));
    }


    private void setTableData(String tableName, Map<String, Object> data) {
        Map<String, Object> tableData =
                Optional.ofNullable(aggregationMap.get(tableName)).orElse(new HashMap<String, Object>());
        tableData.putAll(data);
        aggregationMap.put(tableName, tableData);
    }

    public AggregationData copyAggregationData() {
        AggregationData aggregationData = new AggregationData();
        for (String tableName : this.getRowAllData().keySet()) {
            aggregationData.setTableData(tableName, this.getRowAllData().get(tableName));
        }
        return aggregationData;
    }
}