package com.easy.cloud.core.generate.bo.javaclass;

import com.easy.cloud.core.basic.pojo.vo.EcBaseVO;
import com.easy.cloud.core.common.generator.code.base.pojo.rule.EcGenerateRule;
import com.easy.cloud.core.common.generator.code.java.constant.EcCodeGenerateJavaConstant.EcClassCommentEndWith;
import com.easy.cloud.core.common.generator.code.java.constant.EcCodeGenerateJavaConstant.EcClassNameEndWith;
import com.easy.cloud.core.common.generator.code.java.desc.EcJavaClassContentDesc;
import com.easy.cloud.core.common.generator.code.java.pojo.dto.EcGenerateJavaBaseDTO;

/**
 * 
 * <p>
 * 生成vo对象
 * </p>
 *
 * @author daiqi 创建时间 2018年3月27日 上午9:49:06
 */
public class EcGenerateJavaVOBO extends EcGenerateJavaDOBO {

	public EcGenerateJavaVOBO(EcGenerateJavaBaseDTO generateJavaBaseDTO, EcGenerateRule generateRule) {
		super(generateJavaBaseDTO, generateRule);
	}

	@Override
	protected void buildExtendsParentClass() {
		EcJavaClassContentDesc extendsParentClass = new EcJavaClassContentDesc();
		extendsParentClass.setName(EcBaseVO.class.getSimpleName());
		extendsParentClass.setSimpleClassType(EcBaseVO.class.getSimpleName());
		extendsParentClass.setFullClassType(EcBaseVO.class.getName());
		super.javaClassContentDesc.addExtendsParentClass(extendsParentClass);
	}

	@Override
	protected String getClassCommentEndWith() {
		return EcClassCommentEndWith.POJO_VO;
	}

	@Override
	protected String getClassNameEndWith() {
		return EcClassNameEndWith.POJO_VO;
	}

}
