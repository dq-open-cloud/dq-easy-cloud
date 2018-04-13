package com.easy.cloud.user.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easy.cloud.core.common.json.utils.EcJSONUtils;

@RestController
@RequestMapping("test")
public class ConfigTest {

	@Autowired
	private Environment environment;
	
	@RequestMapping("getFrom")
	public String getFrom(){
		Map<String, Object> retMap = new HashMap<>();
		retMap.put("from", environment.getProperty("from"));
		return EcJSONUtils.parseObject(retMap, String.class);
	}
}
