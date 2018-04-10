package com.easy.cloud.pay.common.payment.service;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

import com.easy.cloud.core.common.http.constant.EcHttpConstant.EcMethodType;
import com.easy.cloud.core.common.http.pojo.bo.EcHttpRequestTemplateBO;
import com.easy.cloud.core.common.http.pojo.dto.EcHttpConfigStorageDTO;
import com.easy.cloud.pay.common.base.api.EcCallback;
import com.easy.cloud.pay.common.payment.config.dto.EcPayConfigStorageInf;
import com.easy.cloud.pay.common.payment.pojo.dto.EcPayOrderDTO;
import com.easy.cloud.pay.common.payment.pojo.query.EcOrderAbstractQuery;
import com.easy.cloud.pay.common.paymessage.pojo.dto.EcPayMessageDTO;
import com.easy.cloud.pay.common.paymessage.pojo.dto.EcPayOutMessageDTO;
import com.easy.cloud.pay.common.refund.dto.EcRefundOrderAbstractDTO;
import com.easy.cloud.pay.common.transaction.inf.EcTransactionType;
import com.easy.cloud.pay.common.transaction.pojo.dto.EcTransferOrderDTO;

/**
 * 
 * <p>
 * 支付服务
 * </p>
 *
 * @author daiqi 创建时间 2018年2月23日 下午3:33:15
 */
public interface EcPayServiceInf {

	/**
	 * 设置支付配置
	 * 
	 * @param payConfigStorage
	 *            支付配置
	 * @return 支付服务
	 */
	EcPayServiceInf setPayConfigStorage(EcPayConfigStorageInf payConfigStorage);

	/**
	 * 获取支付配置
	 *
	 * @return 支付配置
	 */
	EcPayConfigStorageInf getPayConfigStorage();

	/**
	 * 获取http请求工具
	 *
	 * @return http请求工具
	 */
	EcHttpRequestTemplateBO getHttpRequestTemplate();

	/**
	 * 设置 请求工具配置 设置并创建请求模版， 代理请求配置这里是否合理？？，
	 * 
	 * @param configStorage
	 *            http请求配置
	 * @return 支付服务
	 */
	EcPayServiceInf setRequestTemplateConfigStorage(EcHttpConfigStorageDTO configStorage);

	/**
	 * 回调校验
	 *
	 * @param params
	 *            回调回来的参数集
	 * @return 签名校验 true通过
	 */
	boolean verify(Map<String, Object> params);

	/**
	 * 签名校验
	 *
	 * @param params
	 *            参数集
	 * @param sign
	 *            签名原文
	 * @return 签名校验 true通过
	 */
	boolean signVerify(Map<String, Object> params, String sign);

	/**
	 * 支付宝需要,微信是否也需要再次校验来源，进行订单查询 校验数据来源
	 * 
	 * @param id
	 *            业务id, 数据的真实性.
	 * @return true通过
	 */
	boolean verifySource(String id);

	/**
	 * 返回创建的订单信息
	 *
	 * @param order
	 *            支付订单
	 * @return 订单信息
	 * @see PayOrder 支付订单信息
	 */
	Map<String, Object> orderInfo(EcPayOrderDTO order);

	/**
	 * 创建签名
	 *
	 * @param content
	 *            需要签名的内容
	 * @param characterEncoding
	 *            字符编码
	 * @return 签名
	 */
	String createSign(String content, String characterEncoding);

	/**
	 * 创建签名
	 *
	 * @param content
	 *            需要签名的内容
	 * @param characterEncoding
	 *            字符编码
	 * @return 签名
	 */
	String createSign(Map<String, Object> content, String characterEncoding);

	/**
	 * 将请求参数或者请求流转化为 Map
	 *
	 * @param parameterMap
	 *            请求参数
	 * @param is
	 *            请求流
	 * @return 获得回调的请求参数
	 */
	Map<String, Object> getParameterToMap(Map<String, String[]> parameterMap, InputStream is);

	/**
	 * 获取输出消息，用户返回给支付端
	 *
	 * @param code
	 *            状态
	 * @param message
	 *            消息
	 * @return 返回输出消息
	 */
	EcPayOutMessageDTO getPayOutMessage(String code, String message);

	/**
	 * 获取成功输出消息，用户返回给支付端 主要用于拦截器中返回
	 * 
	 * @param payMessage
	 *            支付回调消息
	 * @return 返回输出消息
	 */
	EcPayOutMessageDTO successPayOutMessage(EcPayMessageDTO payMessage);

	/**
	 * 获取输出消息，用户返回给支付端, 针对于web端
	 *
	 * @param orderInfo
	 *            发起支付的订单信息
	 * @param method
	 *            请求方式 "post" "get",
	 * @return 获取输出消息，用户返回给支付端, 针对于web端
	 * @see EcMethodType 请求类型
	 */
	String buildRequest(Map<String, Object> orderInfo, EcMethodType method);

	/**
	 * 生成支付二维码，用户返回给支付端,
	 *
	 * @param order
	 *            发起支付的订单信息
	 * @return 返回图片信息，支付时需要的
	 */
	BufferedImage generatePayQrCode(EcPayOrderDTO order);

	/**
	 * 刷卡付,pos主动扫码付款(条码付)
	 *
	 * @param order
	 *            发起支付的订单信息
	 * @return 返回支付结果
	 */
	Map<String, Object> microPay(EcPayOrderDTO order);

	/**
	 * 交易查询接口
	 *
	 * @param tradeNo
	 *            支付平台订单号
	 * @param outTradeNo
	 *            商户单号
	 * @return 返回查询回来的结果集，支付方原值返回
	 */
	Map<String, Object> queryPayResult(String tradeNo, String outTradeNo);

	/**
	 * 交易查询接口，带处理器
	 * 
	 * @param tradeNo
	 *            支付平台订单号
	 * @param outTradeNo
	 *            商户单号
	 * @param callback
	 *            处理器
	 * @param <T>
	 *            返回类型
	 * @return 返回查询回来的结果集
	 */
	<T> T query(String tradeNo, String outTradeNo, EcCallback<T> callback);

	/**
	 * 交易关闭接口
	 *
	 * @param tradeNo
	 *            支付平台订单号
	 * @param outTradeNo
	 *            商户单号
	 * @return 返回支付方交易关闭后的结果
	 */
	Map<String, Object> close(String tradeNo, String outTradeNo);

	/**
	 * 交易关闭接口
	 *
	 * @param tradeNo
	 *            支付平台订单号
	 * @param outTradeNo
	 *            商户单号
	 * @param callback
	 *            处理器
	 * @param <T>
	 *            返回类型
	 * @return 返回支付方交易关闭后的结果
	 */
	<T> T close(String tradeNo, String outTradeNo, EcCallback<T> callback);


	/**
	 * 申请退款接口
	 *
	 * @param refundOrder
	 *            退款订单信息
	 * @return 返回支付方申请退款后的结果
	 */
	Map<String, Object> refund(EcRefundOrderAbstractDTO refundOrder);

	/**
	 * 申请退款接口
	 *
	 * @param refundOrder
	 *            退款订单信息
	 * @param callback
	 *            处理器
	 * @param <T>
	 *            返回类型
	 * @return 返回支付方申请退款后的结果
	 */
	<T> T refund(EcRefundOrderAbstractDTO refundOrder, EcCallback<T> callback);

	/**
	 * 查询退款
	 *
	 * @param dqOrderQuery : DqOrderQuery : 订单查询接口
	 * @return 返回支付方查询退款后的结果
	 */
	Map<String, Object> queryRefundResult(EcOrderAbstractQuery dqOrderQuery);

	/**
	 * 查询退款
	 *
	 * @param dqOrderQuery : DqOrderQuery
	 *            订单查询对象
	 * @param callback
	 *            处理器
	 * @param <T>
	 *            返回类型
	 * @return 返回支付方查询退款后的结果
	 */
	<T> T refundQuery(EcOrderAbstractQuery dqOrderQuery, EcCallback<T> callback);

	/**
	 * 下载对账单
	 *
	 * @return 返回支付方下载对账单的结果
	 */
	Map<String, Object> downLoadBill(EcOrderAbstractQuery dqOrderQuery);

	/**
	 * 下载对账单
	 *
	 * @param billDate
	 *            账单时间：具体请查看对应支付平台
	 * @param billType
	 *            账单类型，具体请查看对应支付平台
	 * @param callback
	 *            处理器
	 * @param <T>
	 *            返回类型
	 * @return 返回支付方下载对账单的结果
	 */
	<T> T downLoadBill(EcOrderAbstractQuery dqOrderQuery, EcCallback<T> callback);

	/**
	 *
	 * @param tradeNoOrBillDate
	 *            支付平台订单号或者账单类型， 具体请 类型为{@link String }或者 {@link Date }
	 *            ，类型须强制限制，类型不对应则抛出异常{@link PayErrorException}
	 * @param outTradeNoBillType
	 *            商户单号或者 账单类型
	 * @param transactionType
	 *            交易类型
	 * @return 返回支付方对应接口的结果
	 */
	Map<String, Object> secondaryInterface(Object tradeNoOrBillDate, String outTradeNoBillType,
			EcTransactionType transactionType);

	/**
	 * 通用查询接口
	 *
	 * @param tradeNoOrBillDate
	 *            支付平台订单号或者账单日期， 具体请 类型为{@link String }或者 {@link Date }
	 *            ，类型须强制限制，类型不对应则抛出异常{@link PayErrorException}
	 *
	 * @param outTradeNoBillType
	 *            商户单号或者 账单类型
	 * @param transactionType
	 *            交易类型
	 * @param callback
	 *            处理器
	 * @param <T>
	 *            返回类型
	 * @return 返回支付方对应接口的结果
	 */
	<T> T secondaryInterface(Object tradeNoOrBillDate, String outTradeNoBillType, EcTransactionType transactionType,
			EcCallback<T> callback);

	/**
	 * 转账
	 * 
	 * @param order
	 *            转账订单
	 * @return 对应的转账结果
	 */
	Map<String, Object> transfer(EcTransferOrderDTO order);

	/**
	 * 转账
	 * 
	 * @param order
	 *            转账订单
	 * @param callback
	 *            处理器
	 * @param <T>
	 *            返回类型
	 * @return 对应的转账结果
	 */
	<T> T transfer(EcTransferOrderDTO order, EcCallback<T> callback);

	/**
	 * 转账查询
	 *
	 * @param outNo
	 *            商户转账订单号
	 * @param tradeNo
	 *            支付平台转账订单号
	 *
	 * @return 对应的转账订单
	 */
	Map<String, Object> queryTransferResult(String outNo, String tradeNo);

	/**
	 * 转账查询
	 *
	 * @param outNo
	 *            商户转账订单号
	 * @param tradeNo
	 *            支付平台转账订单号
	 * @param callback
	 *            处理器
	 * @param <T>
	 *            返回类型
	 * @return 对应的转账订单
	 */
	<T> T transferQuery(String outNo, String tradeNo, EcCallback<T> callback);

}
