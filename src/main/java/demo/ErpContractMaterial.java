package demo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ErpContractMaterial implements Serializable {
    /**
     * null
     */
    private Integer id;

    /**
     * 合同编号
     */
    private String contractNo;

    /**
     * 物料代码
     */
    private String materialNo;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 采购价格
     */
    private BigDecimal pricePurchase;

    /**
     * 价格单位
     */
    private String unitPrice;

    /**
     * 数量
     */
    private BigDecimal quantity;

    /**
     * 数量单位
     */
    private String unitQuantity;

    /**
     * 总金额
     */
    private BigDecimal moneyTotal;

    /**
     * 税额
     */
    private BigDecimal moneyTax;

    /**
     * 税率
     */
    private BigDecimal tax;

    /**
     * 净值
     */
    private BigDecimal moneyNet;

    /**
     * 预计到货日期
     */
    private Date dateArrival;

    /**
     * 送货工厂/项目
     */
    private String factory;

    /**
     * 采购订单容差
     */
    private BigDecimal tolerance;

    /**
     * 规格
     */
    private String specification;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 安装加工单价
     */
    private BigDecimal priceInstall;

    /**
     * 安装加工税率
     */
    private BigDecimal taxInstall;

    /**
     * 技术参数饰面颜色
     */
    private String dataTechnical;

    /**
     * 材料总数量含补充协议
     */
    private BigDecimal quantityAll;

    /**
     * 状态
     */
    private String status;

    /**
     * null
     */
    private String uuid;

    /**
     * null
     */
    private String uuidT;

    /**
     * null
     */
    private String creater;

    /**
     * null
     */
    private Date createTime;

    /**
     * null
     */
    private String updater;

    /**
     * null
     */
    private Date updateTime;

    /**
     * 需求单细项id
     */
    private String referenceItem;

    /**
     * 备注
     */
    private String remark;

    /**
     * 综合单价
     */
    private BigDecimal priceSynthetical;

    /**
     * grid编号
     */
    private Integer itemNo;

    /**
     * 是否含运费
     */
    private String freight;

    /**
     * 供货区域id
     */
    private Integer districtId;

    /**
     * 原合同单价
     */
    private BigDecimal oldPricePurchase;

    /**
     * 原合同综合单价
     */
    private BigDecimal oldPriceSynthetical;

    /**
     * 材料类型
     */
    private String classify;

    /**
     * 偏移系数
     */
    private BigDecimal offsetCoefficient;

    /**
     * RD编码
     */
    private String rdNo;

    /**
     * 创新材料金额
     */
    private BigDecimal rdMoney;

    /**
     * 创新材料数量
     */
    private BigDecimal rdQuantity;

    /**
     * 限价
     */
    private BigDecimal moneyLimite;

    /**
     * 终止合同发货单执行数量
     */
    private BigDecimal orderQuantity;

    /**
     * 工程检测需求单id
     */
    private Integer detectionId;

    /**
     * 招标需求单号
     */
    private String internalNo;

    /**
     * 招标号
     */
    private String processRequest;

    /**
     * 折扣前采购价格
     */
    private BigDecimal orginalPricePurchase;

    /**
     * 折扣前综合单价
     */
    private BigDecimal orginalPriceSynthetical;

    /**
     * 折扣前总金额
     */
    private BigDecimal orginalMoneyTotal;

    /**
     * 折扣前安装单价
     */
    private BigDecimal orginalPriceInstall;

    /**
     * 流程实例id
     */
    private String processInstanceId;

    /**
     * 供货区域
     */
    private String districtArea;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    public String getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo == null ? null : materialNo.trim();
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName == null ? null : materialName.trim();
    }

    public BigDecimal getPricePurchase() {
        return pricePurchase;
    }

    public void setPricePurchase(BigDecimal pricePurchase) {
        this.pricePurchase = pricePurchase;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice == null ? null : unitPrice.trim();
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getUnitQuantity() {
        return unitQuantity;
    }

    public void setUnitQuantity(String unitQuantity) {
        this.unitQuantity = unitQuantity == null ? null : unitQuantity.trim();
    }

    public BigDecimal getMoneyTotal() {
        return moneyTotal;
    }

    public void setMoneyTotal(BigDecimal moneyTotal) {
        this.moneyTotal = moneyTotal;
    }

    public BigDecimal getMoneyTax() {
        return moneyTax;
    }

    public void setMoneyTax(BigDecimal moneyTax) {
        this.moneyTax = moneyTax;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getMoneyNet() {
        return moneyNet;
    }

    public void setMoneyNet(BigDecimal moneyNet) {
        this.moneyNet = moneyNet;
    }

    public Date getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(Date dateArrival) {
        this.dateArrival = dateArrival;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory == null ? null : factory.trim();
    }

    public BigDecimal getTolerance() {
        return tolerance;
    }

    public void setTolerance(BigDecimal tolerance) {
        this.tolerance = tolerance;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification == null ? null : specification.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public BigDecimal getPriceInstall() {
        return priceInstall;
    }

    public void setPriceInstall(BigDecimal priceInstall) {
        this.priceInstall = priceInstall;
    }

    public BigDecimal getTaxInstall() {
        return taxInstall;
    }

    public void setTaxInstall(BigDecimal taxInstall) {
        this.taxInstall = taxInstall;
    }

    public String getDataTechnical() {
        return dataTechnical;
    }

    public void setDataTechnical(String dataTechnical) {
        this.dataTechnical = dataTechnical == null ? null : dataTechnical.trim();
    }

    public BigDecimal getQuantityAll() {
        return quantityAll;
    }

    public void setQuantityAll(BigDecimal quantityAll) {
        this.quantityAll = quantityAll;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getUuidT() {
        return uuidT;
    }

    public void setUuidT(String uuidT) {
        this.uuidT = uuidT == null ? null : uuidT.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getReferenceItem() {
        return referenceItem;
    }

    public void setReferenceItem(String referenceItem) {
        this.referenceItem = referenceItem == null ? null : referenceItem.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public BigDecimal getPriceSynthetical() {
        return priceSynthetical;
    }

    public void setPriceSynthetical(BigDecimal priceSynthetical) {
        this.priceSynthetical = priceSynthetical;
    }

    public Integer getItemNo() {
        return itemNo;
    }

    public void setItemNo(Integer itemNo) {
        this.itemNo = itemNo;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight == null ? null : freight.trim();
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public BigDecimal getOldPricePurchase() {
        return oldPricePurchase;
    }

    public void setOldPricePurchase(BigDecimal oldPricePurchase) {
        this.oldPricePurchase = oldPricePurchase;
    }

    public BigDecimal getOldPriceSynthetical() {
        return oldPriceSynthetical;
    }

    public void setOldPriceSynthetical(BigDecimal oldPriceSynthetical) {
        this.oldPriceSynthetical = oldPriceSynthetical;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify == null ? null : classify.trim();
    }

    public BigDecimal getOffsetCoefficient() {
        return offsetCoefficient;
    }

    public void setOffsetCoefficient(BigDecimal offsetCoefficient) {
        this.offsetCoefficient = offsetCoefficient;
    }

    public String getRdNo() {
        return rdNo;
    }

    public void setRdNo(String rdNo) {
        this.rdNo = rdNo == null ? null : rdNo.trim();
    }

    public BigDecimal getRdMoney() {
        return rdMoney;
    }

    public void setRdMoney(BigDecimal rdMoney) {
        this.rdMoney = rdMoney;
    }

    public BigDecimal getRdQuantity() {
        return rdQuantity;
    }

    public void setRdQuantity(BigDecimal rdQuantity) {
        this.rdQuantity = rdQuantity;
    }

    public BigDecimal getMoneyLimite() {
        return moneyLimite;
    }

    public void setMoneyLimite(BigDecimal moneyLimite) {
        this.moneyLimite = moneyLimite;
    }

    public BigDecimal getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(BigDecimal orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Integer getDetectionId() {
        return detectionId;
    }

    public void setDetectionId(Integer detectionId) {
        this.detectionId = detectionId;
    }

    public String getInternalNo() {
        return internalNo;
    }

    public void setInternalNo(String internalNo) {
        this.internalNo = internalNo == null ? null : internalNo.trim();
    }

    public String getProcessRequest() {
        return processRequest;
    }

    public void setProcessRequest(String processRequest) {
        this.processRequest = processRequest == null ? null : processRequest.trim();
    }

    public BigDecimal getOrginalPricePurchase() {
        return orginalPricePurchase;
    }

    public void setOrginalPricePurchase(BigDecimal orginalPricePurchase) {
        this.orginalPricePurchase = orginalPricePurchase;
    }

    public BigDecimal getOrginalPriceSynthetical() {
        return orginalPriceSynthetical;
    }

    public void setOrginalPriceSynthetical(BigDecimal orginalPriceSynthetical) {
        this.orginalPriceSynthetical = orginalPriceSynthetical;
    }

    public BigDecimal getOrginalMoneyTotal() {
        return orginalMoneyTotal;
    }

    public void setOrginalMoneyTotal(BigDecimal orginalMoneyTotal) {
        this.orginalMoneyTotal = orginalMoneyTotal;
    }

    public BigDecimal getOrginalPriceInstall() {
        return orginalPriceInstall;
    }

    public void setOrginalPriceInstall(BigDecimal orginalPriceInstall) {
        this.orginalPriceInstall = orginalPriceInstall;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId == null ? null : processInstanceId.trim();
    }

    public String getDistrictArea() {
        return districtArea;
    }

    public void setDistrictArea(String districtArea) {
        this.districtArea = districtArea == null ? null : districtArea.trim();
    }
}