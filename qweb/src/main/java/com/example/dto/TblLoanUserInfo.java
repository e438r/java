package com.example.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户信息表
 *
 * @author shining.cui
 */
public class TblLoanUserInfo {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 服务商编号，比如 JIMULOAN
     */
    private String servId;

    /**
     * 产品编号, 拿去花:IOUS, 现金贷:CASH, 商务贷:BUSI
     */
    private String productNo;

    /**
     * 渠道编码: QUNAR, CTRIP
     */
    private String channelCode;

    /**
     * 产品子类型 0:普通产品 1:校园产品
     */
    private int productSubtype;

    /**
     * 网关用户流水号
     * 在“协议类校验与补充数据”时生成，逻辑为 "GWU" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmss") + tblLoanRegisterInfoDao.getLoanSequence()
     */
    private String gwUserId;

    /**
     * 贷款最大额度
     */
    private BigDecimal loanMaxAmount;

    /**
     * 用户状态 0:初始化 1:申请成功 2:已开通 3:已失效 4:授信失败 5:激活失败
     */
    private Integer userStatus;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 生效时间
     */
    private Date effectDate;

    /**
     * 失效时间
     */
    private Date expiryDate;

    /**
     * 完成时间
     */
    private Date finishDate;

    /**
     * 协议编号，授信时返回的合同编号，有的通道不返回
     */
    private String protocolId;

    /**
     * 服务商用户编号
     */
    private String servUserId;

    /**
     * 证件类型
     */
    private Integer credType;

    /**
     * 证件号索引
     */
    private String credCodeIndex;

    /**
     * 卡类型 0储蓄卡,1信用卡
     */
    private Integer cardType;

    /**
     * 卡索引
     */
    private String cardNoIndex;

    /**
     * 手机号索引
     */
    private String mobileIndex;

    /**
     * 备注
     */
    private String remark;

    /**
     * 贷款声明
     */
    private String statement;

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误描述
     */
    private String errorInfo;

    /**
     * 月利率，现金贷为日利率*30
     */
    private BigDecimal loanRate;

    /**
     * 分期贷款费率
     */
    private String multiLoanRate;

    /**
     * 网关申请ID
     */
    private String gwRegisterId;

    /**
     * 固定还款日
     */
    private Integer fixedDueDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getServId() {
        return servId;
    }

    public void setServId(String servId) {
        this.servId = servId == null ? null : servId.trim();
    }

    public String getGwUserId() {
        return gwUserId;
    }

    public void setGwUserId(String gwUserId) {
        this.gwUserId = gwUserId == null ? null : gwUserId.trim();
    }

    public BigDecimal getLoanMaxAmount() {
        return loanMaxAmount;
    }

    public void setLoanMaxAmount(BigDecimal loanMaxAmount) {
        this.loanMaxAmount = loanMaxAmount;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(String protocolId) {
        this.protocolId = protocolId == null ? null : protocolId.trim();
    }

    public String getServUserId() {
        return servUserId;
    }

    public void setServUserId(String servUserId) {
        this.servUserId = servUserId == null ? null : servUserId.trim();
    }

    public Integer getCredType() {
        return credType;
    }

    public void setCredType(Integer credType) {
        this.credType = credType;
    }

    public String getCredCodeIndex() {
        return credCodeIndex;
    }

    public void setCredCodeIndex(String credCodeIndex) {
        this.credCodeIndex = credCodeIndex == null ? null : credCodeIndex.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement == null ? null : statement.trim();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode == null ? null : errorCode.trim();
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo == null ? null : errorInfo.trim();
    }

    public BigDecimal getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(BigDecimal loanRate) {
        this.loanRate = loanRate;
    }

    public String getGwRegisterId() {
        return gwRegisterId;
    }

    public void setGwRegisterId(String gwRegisterId) {
        this.gwRegisterId = gwRegisterId;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getCardNoIndex() {
        return cardNoIndex;
    }

    public void setCardNoIndex(String cardNoIndex) {
        this.cardNoIndex = cardNoIndex;
    }

    public String getMobileIndex() {
        return mobileIndex;
    }

    public void setMobileIndex(String mobileIndex) {
        this.mobileIndex = mobileIndex;
    }

    public String getMultiLoanRate() {
        return multiLoanRate;
    }

    public void setMultiLoanRate(String multiLoanRate) {
        this.multiLoanRate = multiLoanRate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public int getProductSubtype() {
        return productSubtype;
    }

    public void setProductSubtype(int productSubtype) {
        this.productSubtype = productSubtype;
    }

    public Integer getFixedDueDate() {
        return fixedDueDate;
    }

    public void setFixedDueDate(Integer fixedDueDate) {
        this.fixedDueDate = fixedDueDate;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TblLoanUserInfo{");
        sb.append("id=").append(id);
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", servId='").append(servId).append('\'');
        sb.append(", productNo='").append(productNo).append('\'');
        sb.append(", channelCode='").append(channelCode).append('\'');
        sb.append(", productSubtype=").append(productSubtype);
        sb.append(", gwUserId='").append(gwUserId).append('\'');
        sb.append(", loanMaxAmount=").append(loanMaxAmount);
        sb.append(", userStatus=").append(userStatus);
        sb.append(", createDate=").append(createDate);
        sb.append(", effectDate=").append(effectDate);
        sb.append(", expiryDate=").append(expiryDate);
        sb.append(", finishDate=").append(finishDate);
        sb.append(", protocolId='").append(protocolId).append('\'');
        sb.append(", servUserId='").append(servUserId).append('\'');
        sb.append(", credType=").append(credType);
        sb.append(", credCodeIndex='").append(credCodeIndex).append('\'');
        sb.append(", cardType=").append(cardType);
        sb.append(", cardNoIndex='").append(cardNoIndex).append('\'');
        sb.append(", mobileIndex='").append(mobileIndex).append('\'');
        sb.append(", remark='").append(remark).append('\'');
        sb.append(", statement='").append(statement).append('\'');
        sb.append(", errorCode='").append(errorCode).append('\'');
        sb.append(", errorInfo='").append(errorInfo).append('\'');
        sb.append(", loanRate=").append(loanRate);
        sb.append(", multiLoanRate='").append(multiLoanRate).append('\'');
        sb.append(", gwRegisterId='").append(gwRegisterId).append('\'');
        sb.append(", fixedDueDate=").append(fixedDueDate);
        sb.append('}');
        return sb.toString();
    }

}