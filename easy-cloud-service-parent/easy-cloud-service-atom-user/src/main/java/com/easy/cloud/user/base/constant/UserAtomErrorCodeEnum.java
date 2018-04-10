package com.easy.cloud.user.base.constant;

import com.easy.cloud.core.basic.constant.error.EcBaseErrorCodeInf;


/**
 * 
 * <p>
 * 用户原子服务错误码
 * </p>
 *
 * <pre>
 * 详细描述
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月2日 下午4:25:50
 */
public enum UserAtomErrorCodeEnum implements EcBaseErrorCodeInf{
	;

	private String errorCode;
	private String errorMsg;

	private UserAtomErrorCodeEnum(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	@Override
	public String getErrorCode() {
		return errorCode;
	}

	@Override
	public String getErrorMsg() {
		return errorMsg;
	}
	
}

