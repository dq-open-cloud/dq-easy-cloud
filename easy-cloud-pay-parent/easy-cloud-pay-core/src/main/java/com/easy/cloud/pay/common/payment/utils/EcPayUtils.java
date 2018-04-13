package com.easy.cloud.pay.common.payment.utils;

import java.math.BigDecimal;

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.date.utils.EcDateFormatUtils;
import com.easy.cloud.core.common.date.utils.EcDateUtils;
import com.easy.cloud.core.common.number.bigdecimal.utils.EcBigDecimalUtils;
import com.easy.cloud.pay.common.transaction.inf.EcTransactionType;

/**
 * 
 * <p>
 * 支付工具类
 * </p>
 *
 * @author daiqi 创建时间 2018年2月24日 下午5:18:01
 */
public class EcPayUtils {

	/**
	 * 
	 * <p>
	 * 比较EcTransactionType类型得两个枚举是否相等
	 * </p>
	 *
	 * <pre>
	 * transactionType1 == transactionType2 = true
	 * </pre>
	 *
	 * @param transactionType1
	 *            : EcTransactionType : 交易类型
	 * @param transactionType2
	 *            : EcTransactionType : 交易类型
	 * @return
	 *
	 * 		author daiqi 创建时间 2018年2月25日 下午7:46:30
	 */
	public static boolean equalsEcTransactionType(EcTransactionType transactionType1,
			EcTransactionType transactionType2) {
		if (EcBaseUtils.isNull(transactionType1) || EcBaseUtils.isNull(transactionType2)) {
			return false;
		}
		if (transactionType1 == transactionType2) {
			return true;
		}
		return false;
	}

	/**
	 * 元转分
	 * 
	 * @param amount
	 *            元的金额
	 * @return 分的金额
	 */
	public static Integer yuanToCent(BigDecimal amount) {
		if (EcBaseUtils.isNull(amount)) {
			return null;
		}
		return EcBigDecimalUtils.mul(amount, new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
	}

	/**
	 * 
	 * <p>
	 * 订单号生产者工具类
	 * </p>
	 *
	 * @author daiqi 创建时间 2018年2月24日 下午5:19:47
	 */
	public static class EcOrderNoGenerator {
		/** 产生微信公众号支付订单 */
		protected static String generateWxJsapiPayOrderNO() {
			return generateOrderNOCore("WXJPON");
		}

		/** 产生微信app支付订单号 */
		protected static String generateWxAppPayOrderNO() {
			return generateOrderNOCore("WXAPON");
		}

		/** 产生微信扫码支付订单 */
		protected static String generateWxQrCodePayOrderNO() {
			return generateOrderNOCore("WXQCPON");
		}

		/** 产生微信扫条形码卡支付类型 */
		protected static String generateWxMicroPayOrderNO() {
			return generateOrderNOCore("WXMPON");
		}

		/** 产生微信web支付订单 */
		protected static String generateWxMWebPayOrderNO() {
			return generateOrderNOCore("WXMWPON");
		}

		/** 产生微信转账订单号 */
		protected static String generateWxTransferOrderNO() {
			return generateOrderNOCore("WXTON");
		}

		/** 产生微信退款订单号 */
		protected static String generateWxRefundOrderNO() {
			return generateOrderNOCore("WXRON");
		}

		/** 产生支付宝即时到帐支付订单号 */
		protected static String generateZfbDirectPayOrderNO() {
			return generateOrderNOCore("ZFBDPON");
		}

		/** 产生支付宝移动网页支付订单号 */
		protected static String generateZfbWapPayOrderNO() {
			return generateOrderNOCore("ZFBWPON");
		}

		/** 产生支付宝App支付订单号 */
		protected static String generateZfbAppPayOrderNO() {
			return generateOrderNOCore("ZFBAPON");
		}

		/** 产生支付宝扫码付支付订单号 */
		protected static String generateZfbSweepPayOrderNO() {
			return generateOrderNOCore("ZFBSPON");
		}

		/** 产生支付宝条码支付订单号 */
		protected static String generateZfbBarCodePayOrderNO() {
			return generateOrderNOCore("ZFBTON");
		}

		/** 产生支付宝声波支付订单号 */
		protected static String generateZfbWaveCodePayOrderNO() {
			return generateOrderNOCore("ZFWCPON");
		}

		/** 产生支付宝转账订单号 */
		protected static String generateZfbTransferOrderNO() {
			return generateOrderNOCore("ZFBTON");
		}

		/** 产生支付宝退款订单号 */
		protected static String generateZfbRefundOrderNO() {
			return generateOrderNOCore("ZFBRON");
		}

		/**
		 * 
		 * <p>
		 * 核心产生订单号的方法
		 * </p>
		 *
		 * @param prefix
		 *            : String : 订单前缀
		 * @return String
		 * @author daiqi 创建时间 2018年2月24日 下午5:23:03
		 */
		private static String generateOrderNOCore(String prefix) {
			return prefix
					+ EcDateFormatUtils.format(EcDateUtils.getCurrentDate(), EcDateFormatUtils.FORMAT_LONG_MILLIS);
		}
	}
}
