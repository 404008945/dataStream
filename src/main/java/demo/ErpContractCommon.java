package demo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ErpContractCommon implements Serializable {
    /**
     * null
     */
    private Integer id;

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
     * 合同代码
     */
    private String contractNo;

    /**
     * 合同名称
     */
    private String contractName;

    /**
     * 公司
     */
    private String corporation;

    /**
     * 合同类型
     */
    private String contractType;

    /**
     * 合同版本号
     */
    private Integer edition;

    /**
     * 合同状态
     */
    private String status;

    /**
     * 变更合同编码
     */
    private String changeContractNo;

    /**
     * 变更合同名称
     */
    private String changeContractName;

    /**
     * 变更原因
     */
    private String changeReason;

    /**
     * 合同总金额
     */
    private BigDecimal moneyTotal;

    /**
     * 发票类型
     */
    private String billType;

    /**
     * 税率
     */
    private BigDecimal taxCode;

    /**
     * 净值
     */
    private BigDecimal moneyNet;

    /**
     * 签订日期
     */
    private Date dateSigned;

    /**
     * 合同经办人
     */
    private String contractStaff;

    /**
     * 合同签订时间
     */
    private Date contractDate;

    /**
     * 合同签订地点
     */
    private String contractPlace;

    /**
     * 合同签订份数
     */
    private Integer contractNumber;

    /**
     * 双方各执合同数
     */
    private Integer otherNumber;

    /**
     * 附件
     */
    private String fileKey;

    /**
     * 销售组织
     */
    private String marketingOrganization;

    /**
     * 采购组织
     */
    private String purchaseOrganization;

    /**
     * 采购模式
     */
    private String purchaseType;

    /**
     * 项目编号
     */
    private String projectNo;

    /**
     * 是否重点项目
     */
    private String keynote;

    /**
     * 所属业务
     */
    private String business;

    /**
     * 主合同编号
     */
    private String contractMajor;

    /**
     * 合同归档处
     */
    private String filingPlace;

    /**
     * 电子签章状态
     */
    private String sealStatus;

    /**
     * 终止原因
     */
    private String reasonStop;

    /**
     * 联系单附件
     */
    private String contactFilekey;

    /**
     * 费率
     */
    private BigDecimal rates;

    /**
     * 原合同编码
     */
    private String originalContract;

    /**
     * 盖章委托人
     */
    private String sealAgent;

    /**
     * 存档日期
     */
    private Date dateArchive;

    /**
     * 作废日期
     */
    private Date dateCancel;

    /**
     * 合同审批完成日期
     */
    private Date dateReview;

    /**
     * 合同归档版(统一材料、劳务盖章文件)
     */
    private String sealFile;

    /**
     * 该合同是否同步到nc：Y-已同步
     */
    private String ncTransferTag;

    /**
     * 保理合同编码
     */
    private String factoringContract;

    /**
     * 是否21年绩效考核(Y,N)
     */
    private String if2021Kpi;

    /**
     * 折扣种类：null、有折扣、无折扣
     */
    private String discountType;

    /**
     * 折扣前合同总金额
     */
    private BigDecimal originalMoneyTotal;

    /**
     * 原因说明
     */
    private String discountReason;

    /**
     * 房屋编码
     */
    private String houseNo;

    /**
     * 流程定义id
     */
    private String processDefinitionId;

    /**
     * 合同分包专业
     */
    private String specialtyType;

    /**
     * 合同价目清单是否含税
     */
    private String withTax;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName == null ? null : contractName.trim();
    }

    public String getCorporation() {
        return corporation;
    }

    public void setCorporation(String corporation) {
        this.corporation = corporation == null ? null : corporation.trim();
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType == null ? null : contractType.trim();
    }

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getChangeContractNo() {
        return changeContractNo;
    }

    public void setChangeContractNo(String changeContractNo) {
        this.changeContractNo = changeContractNo == null ? null : changeContractNo.trim();
    }

    public String getChangeContractName() {
        return changeContractName;
    }

    public void setChangeContractName(String changeContractName) {
        this.changeContractName = changeContractName == null ? null : changeContractName.trim();
    }

    public String getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason == null ? null : changeReason.trim();
    }

    public BigDecimal getMoneyTotal() {
        return moneyTotal;
    }

    public void setMoneyTotal(BigDecimal moneyTotal) {
        this.moneyTotal = moneyTotal;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType == null ? null : billType.trim();
    }

    public BigDecimal getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(BigDecimal taxCode) {
        this.taxCode = taxCode;
    }

    public BigDecimal getMoneyNet() {
        return moneyNet;
    }

    public void setMoneyNet(BigDecimal moneyNet) {
        this.moneyNet = moneyNet;
    }

    public Date getDateSigned() {
        return dateSigned;
    }

    public void setDateSigned(Date dateSigned) {
        this.dateSigned = dateSigned;
    }

    public String getContractStaff() {
        return contractStaff;
    }

    public void setContractStaff(String contractStaff) {
        this.contractStaff = contractStaff == null ? null : contractStaff.trim();
    }

    public Date getContractDate() {
        return contractDate;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    public String getContractPlace() {
        return contractPlace;
    }

    public void setContractPlace(String contractPlace) {
        this.contractPlace = contractPlace == null ? null : contractPlace.trim();
    }

    public Integer getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(Integer contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Integer getOtherNumber() {
        return otherNumber;
    }

    public void setOtherNumber(Integer otherNumber) {
        this.otherNumber = otherNumber;
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey == null ? null : fileKey.trim();
    }

    public String getMarketingOrganization() {
        return marketingOrganization;
    }

    public void setMarketingOrganization(String marketingOrganization) {
        this.marketingOrganization = marketingOrganization == null ? null : marketingOrganization.trim();
    }

    public String getPurchaseOrganization() {
        return purchaseOrganization;
    }

    public void setPurchaseOrganization(String purchaseOrganization) {
        this.purchaseOrganization = purchaseOrganization == null ? null : purchaseOrganization.trim();
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType == null ? null : purchaseType.trim();
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

    public String getKeynote() {
        return keynote;
    }

    public void setKeynote(String keynote) {
        this.keynote = keynote == null ? null : keynote.trim();
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business == null ? null : business.trim();
    }

    public String getContractMajor() {
        return contractMajor;
    }

    public void setContractMajor(String contractMajor) {
        this.contractMajor = contractMajor == null ? null : contractMajor.trim();
    }

    public String getFilingPlace() {
        return filingPlace;
    }

    public void setFilingPlace(String filingPlace) {
        this.filingPlace = filingPlace == null ? null : filingPlace.trim();
    }

    public String getSealStatus() {
        return sealStatus;
    }

    public void setSealStatus(String sealStatus) {
        this.sealStatus = sealStatus == null ? null : sealStatus.trim();
    }

    public String getReasonStop() {
        return reasonStop;
    }

    public void setReasonStop(String reasonStop) {
        this.reasonStop = reasonStop == null ? null : reasonStop.trim();
    }

    public String getContactFilekey() {
        return contactFilekey;
    }

    public void setContactFilekey(String contactFilekey) {
        this.contactFilekey = contactFilekey == null ? null : contactFilekey.trim();
    }

    public BigDecimal getRates() {
        return rates;
    }

    public void setRates(BigDecimal rates) {
        this.rates = rates;
    }

    public String getOriginalContract() {
        return originalContract;
    }

    public void setOriginalContract(String originalContract) {
        this.originalContract = originalContract == null ? null : originalContract.trim();
    }

    public String getSealAgent() {
        return sealAgent;
    }

    public void setSealAgent(String sealAgent) {
        this.sealAgent = sealAgent == null ? null : sealAgent.trim();
    }

    public Date getDateArchive() {
        return dateArchive;
    }

    public void setDateArchive(Date dateArchive) {
        this.dateArchive = dateArchive;
    }

    public Date getDateCancel() {
        return dateCancel;
    }

    public void setDateCancel(Date dateCancel) {
        this.dateCancel = dateCancel;
    }

    public Date getDateReview() {
        return dateReview;
    }

    public void setDateReview(Date dateReview) {
        this.dateReview = dateReview;
    }

    public String getSealFile() {
        return sealFile;
    }

    public void setSealFile(String sealFile) {
        this.sealFile = sealFile == null ? null : sealFile.trim();
    }

    public String getNcTransferTag() {
        return ncTransferTag;
    }

    public void setNcTransferTag(String ncTransferTag) {
        this.ncTransferTag = ncTransferTag == null ? null : ncTransferTag.trim();
    }

    public String getFactoringContract() {
        return factoringContract;
    }

    public void setFactoringContract(String factoringContract) {
        this.factoringContract = factoringContract == null ? null : factoringContract.trim();
    }

    public String getIf2021Kpi() {
        return if2021Kpi;
    }

    public void setIf2021Kpi(String if2021Kpi) {
        this.if2021Kpi = if2021Kpi == null ? null : if2021Kpi.trim();
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType == null ? null : discountType.trim();
    }

    public BigDecimal getOriginalMoneyTotal() {
        return originalMoneyTotal;
    }

    public void setOriginalMoneyTotal(BigDecimal originalMoneyTotal) {
        this.originalMoneyTotal = originalMoneyTotal;
    }

    public String getDiscountReason() {
        return discountReason;
    }

    public void setDiscountReason(String discountReason) {
        this.discountReason = discountReason == null ? null : discountReason.trim();
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo == null ? null : houseNo.trim();
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId == null ? null : processDefinitionId.trim();
    }

    public String getSpecialtyType() {
        return specialtyType;
    }

    public void setSpecialtyType(String specialtyType) {
        this.specialtyType = specialtyType == null ? null : specialtyType.trim();
    }

    public String getWithTax() {
        return withTax;
    }

    public void setWithTax(String withTax) {
        this.withTax = withTax == null ? null : withTax.trim();
    }
}