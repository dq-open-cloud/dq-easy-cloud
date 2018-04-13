package com.easy.cloud.core.common.json.filter;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.json.pojo.dto.EcJsonFilterDTO;
import com.easy.cloud.core.common.string.utils.EcStringUtils;

/**
 * json属性过滤器--不序列化byte数组以及filterDTOMap中的属性
 * @author daiqi
 * @date 2018年2月28日 下午8:10:33
 */
public class EcJsonPropertyFilter implements PropertyFilter{
	static {
        //全局配置关闭循环引用检测
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.DisableCircularReferenceDetect.getMask();
    }

	private Map<String, EcJsonFilterDTO> filterDTOMap;

	public EcJsonPropertyFilter(Map<String, EcJsonFilterDTO> filterDTOMap) {
		this.filterDTOMap = filterDTOMap;
	}

	@Override
	public boolean apply(Object targetObj, String targetPropertyName, Object targetPropertyValue) {
//		目标对象或者或目标对象的属性值为空直接返回true
		if (EcBaseUtils.isNull(targetObj) || EcBaseUtils.isNull(targetPropertyValue)) {
			return true;
		}
		if (targetPropertyValue.getClass().isArray()) {
			return false;
		}
		for (Map.Entry<String, EcJsonFilterDTO> item : this.filterDTOMap.entrySet()) {
			// isAssignableFrom()，用来判断类型间是否有继承关系
			EcJsonFilterDTO filterDTO = item.getValue();
			if (targetObj.getClass().isAssignableFrom(filterDTO.getFilterPropertyClass())){
				return false;
			}
			if (!filterDTO.getTargetObjClass().isAssignableFrom(targetObj.getClass())){
				continue;
			}
//			过滤属性class为空--校验属性名称
			if (EcBaseUtils.isNull(filterDTO.getFilterPropertyClass())){
//				属性名称为空直接返回
				if (EcStringUtils.isEmpty(filterDTO.getTargetObjPropertyName())){
					continue;
				}
//				属性相等,不参与json序列号
				if (EcStringUtils.equalsIgnoreCase(filterDTO.getTargetObjPropertyName(), targetPropertyName)){
					return false;
				}
			}
			if (filterDTO.getFilterPropertyClass().isAssignableFrom(targetPropertyValue.getClass())){
				return false;
			}
		}
		return true;
	}

	public EcJsonPropertyFilter buildFilterDTOMap(Map<String, EcJsonFilterDTO> filterDTOMap) {
		this.filterDTOMap = filterDTOMap;
		return this;
	}


}
