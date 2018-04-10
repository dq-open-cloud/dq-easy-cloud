package com.easy.cloud.pay.common.refund.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.easy.cloud.pay.common.base.pojo.dto.EcBasePayDTO;
import com.easy.cloud.pay.common.payment.utils.EcPayUtils;

/**
 * 
 * <p>
 * 退款订单信息数据传输对象
 * </p>
 *
 * @author daiqi 创建时间 2018年2月23日 下午3:05:13
 */
public abstract class EcRefundOrderAbstractDTO extends EcBasePayDTO {
	/**
	 * 退款单号，每次进行退款的单号，此处唯一
	 */
	private String refundNo;
	/**
	 * 支付平台订单号,交易号
	 */
	private String tradeNo;
	/**
	 * 商户单号
	 */
	private String outTradeNo;
	/**
	 * 退款金额
	 */
	private BigDecimal refundAmount;
	/**
	 * 订单总金额
	 */
	private BigDecimal totalAmount;

	/**
	 * 交易日期
	 */
	private Date orderDate;

	public EcRefundOrderAbstractDTO() {

	}
	
	/** 压入orderDate签名数据 */
	protected abstract void putRefundNoSignData();

	/** 压入orderDate签名数据 */
	protected abstract void putTradeNoSignData();

	/** 压入orderDate签名数据 */
	protected abstract void putOutTradeNoSignData();

	/** 压入orderDate签名数据 */
	protected abstract void putRefundAmountSignData();

	/** 压入orderDate签名数据 */
	protected abstract void putTotalAmountSignData();

	/** 以分为单位 */
	public Integer getTotalAmountOfCent() {
		return EcPayUtils.yuanToCent(totalAmount);
	}

	/** 以分为单位 */
	public Integer getRefundAmountOfCent() {
		return EcPayUtils.yuanToCent(refundAmount);
	}

	public String getRefundNo() {
		return refundNo;
	}

	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
		putRefundNoSignData();
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
		putTradeNoSignData();
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
		putOutTradeNoSignData();
	}

	public BigDecimal getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
		putRefundAmountSignData();
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;

	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
		putTotalAmountSignData();
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "DqRefundOrderAbstractDTO [refundNo=" + refundNo + ", tradeNo=" + tradeNo + ", outTradeNo=" + outTradeNo
				+ ", refundAmount=" + refundAmount + ", totalAmount=" + totalAmount + ", orderDate=" + orderDate + "]";
	}

}
