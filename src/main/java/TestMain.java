import collector.MyCollectors;
import demo.ContractDetail;
import demo.ContractInfo;

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
        List<ContractDetail> contractDetails = new ArrayList<>();
        List<ContractInfo> contractInfos = new ArrayList<>();

        //需求为erpContractMaterials根据contractNo关联erpContractCommons(erpContractCommons中合同编码唯一)
        // ，过滤出对应ErpContractCommon status为60的数据,
        // 再按照contractNo分组，组内按照moneyTotal求和
        //直接使用stream
        //step1 构建指定状态的set，用于后面过滤
        Set<String> stopContract = contractInfos.stream()
                .filter(it -> "60".equals(it.getStatus()))
                .map(ContractInfo::getContractNo).collect(Collectors.toSet());
        //step2 根据set过滤
        contractDetails = contractDetails.stream()
                .filter(it -> stopContract.contains(it.getContractNo()))
                .collect(Collectors.toList());

        //step3 根据contractNo进行求和
        Map<String, BigDecimal> result = new HashMap<>();
        contractDetails.stream().forEach(it -> {
            BigDecimal moneyTotal = Optional.ofNullable(result.get(it.getContractNo()))
                    .orElse(BigDecimal.ZERO);
            moneyTotal = moneyTotal.add(it.getMoneyTotal() != null ? it.getMoneyTotal() : BigDecimal.ZERO);
            result.put(it.getContractNo(), moneyTotal);
        });


        //使用dataStream的写
        //contractDetails 作为t1,过滤之后的erpContractCommons作为t2，t1.t2根据contractNo内连，
        // 后再转乘javaStream 使用工具类进行求和
        //使用hash优化的join实现
        Map<String /* 合同变么 */, BigDecimal /* 对应moneyTotal之和 */> dataStreamMap = CollectionDataStream.of("t1", contractDetails)
                .joinUseHashOnEqualCondition(
                        "t2",
                        contractInfos.stream().filter(it -> "60".equals(it.getStatus())).collect(Collectors.toList()),
                        agg -> agg.getTableData("t1").get("contractNo"),
                        ContractInfo::getContractNo
                ).toStream("s1", ContractDetail.class)
                .collect(MyCollectors.groupingAndSum(ContractDetail::getContractNo, ContractDetail::getMoneyTotal));


        //使用 自定义join实现

        Map<String /* 合同变么 */, BigDecimal /* 对应moneyTotal之和 */> joinDataStreamMap = CollectionDataStream.of("t1", contractDetails)
                .join("t2",
                        contractInfos.stream().filter(it -> "60".equals(it.getStatus())).collect(Collectors.toList()),
                        (agg, data) -> agg.getTableData("t1").get("contractNo").equals(data.getContractNo())
                ).toStream("s1", ContractDetail.class)
                .collect(MyCollectors.groupingAndSum(ContractDetail::getContractNo, ContractDetail::getMoneyTotal));


        System.out.println(dataStreamMap);
    }
}
