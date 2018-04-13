package com.easy.cloud.core.common.log.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.easy.cloud.core.common.log.constant.EcLogConstant.EcLogLevel;
import com.easy.cloud.core.common.log.constant.EcLogConstant.EcLogMode;
import com.easy.cloud.core.common.log.constant.EcLogConstant.EcLogType;
import com.easy.cloud.core.common.log.proxy.impl.EcLogBaseProxy;

/**
 * 
 * <p>
 *  日志记录注解
 * </p>
 *
 * @author daiqi 创建时间 2018年2月7日 下午4:57:04
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface EcLog {
	/** 日志记录方式 详情@Link DqLogConstant.DqLogMode */
	int dqLogMode() default EcLogMode.CONSOLE;

	/** 日志记录级别 详情@Link DqLogConstant.DqLogLevel */
	int dqLogLevel() default EcLogLevel.DEBUG;
	
	/** 日志处理基础委托类class */
	Class<?> dqLogProxyClass() default EcLogBaseProxy.class;
	
	/** 日志记录类型 详情@Link DqLogConstant.DqLogType */
	int dqLogType() default EcLogType.OTHER;
	
	/** 日志开关---false则关闭日志记录---默认打开 */
	boolean dqLogSwitch() default true;
	
	/** 日志分析开关--false则关闭日志分析---默认为打开 */
	boolean dqLogAnalysisSwitch() default true;
}
