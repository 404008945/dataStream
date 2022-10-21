import collector.MyCollectors;
import demo.ErpContractCommon;
import demo.ErpContractMaterial;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class TestMain {
    public static void main(String[] args) {
        //现在有两个集合
        List<ErpContractMaterial> erpContractMaterials = new ArrayList<>();
        List<ErpContractCommon> erpContractCommons = new ArrayList<>();

        //需求为erpContractMaterials根据contractNo关联erpContractCommons(erpContractCommons中合同编码唯一)
        // ，过滤出对应ErpContractCommon status为60的数据,
        // 再按照contractNo分组，组内按照moneyTotal求和
        //直接使用stream
        //step1 构建指定状态的set，用于后面过滤
        Set<String> stopContract = erpContractCommons.stream()
                .filter(it -> "60".equals(it.getStatus()))
                .map(ErpContractCommon::getContractNo).collect(Collectors.toSet());
        //step2 根据set过滤
        erpContractMaterials = erpContractMaterials.stream()
                .filter(it -> stopContract.contains(it.getContractNo()))
                .collect(Collectors.toList());

        //step3 根据contractNo进行求和
        Map<String, BigDecimal> streamMap = new HashMap<>();
        erpContractMaterials.stream().forEach(it -> {
            BigDecimal moneyTotal = Optional.ofNullable(streamMap.get(it.getContractNo()))
                    .orElse(BigDecimal.ZERO);
            moneyTotal = moneyTotal.add(it.getMoneyTotal() != null ? it.getMoneyTotal() : BigDecimal.ZERO);
            streamMap.put(it.getContractNo(), moneyTotal);
        });


        //使用dataStream的写
        //erpContractMaterials 作为t1,过滤之后的erpContractCommons作为t2，t1.t2根据contractNo内连，
        // 后再转乘javaStream 使用工具类进行求和
        Map<String, BigDecimal> dataStreamMap = CollectionDataStream.of("t1", erpContractMaterials)
                .joinUseHashOnEqualCondition(
                        erpContractCommons.stream().filter(it -> "60".equals(it.getStatus())).collect(Collectors.toList()),
                        "t2",
                        agg -> agg.getTableData("t1").get("contractNo"),
                        ErpContractCommon::getContractNo
                ).toStream("s1", ErpContractMaterial.class)
                .collect(MyCollectors.groupingAndSum(ErpContractMaterial::getContractNo, ErpContractMaterial::getMoneyTotal));

        System.out.println(dataStreamMap);
    }
}
