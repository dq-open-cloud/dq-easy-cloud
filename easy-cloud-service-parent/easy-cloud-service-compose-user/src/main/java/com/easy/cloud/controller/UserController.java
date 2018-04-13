package com.easy.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easy.cloud.core.basic.controller.EcBaseController;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.user.base.pojo.dto.UserDTO;
import com.easy.cloud.user.pojo.query.UserComposeQuery;
import com.easy.cloud.user.service.inf.UserService;

@RestController
@RequestMapping("user")
public class UserController extends EcBaseController{
	@Autowired
	private UserService userService;
	
	/**
	 * <p>
	 * 用户注册接口
	 * </p>
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     userName : zhangsan : 用户名 : 是
	 *     password : 123456 : 密码 : 是
	 *     email : dq@163.com : 邮箱 : 是
	 * </pre>
	 * @param userDTO
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月5日 下午7:44:23
	 */
	@RequestMapping("/register")
	public EcBaseServiceResult register(@RequestBody UserDTO userDTO){
		return userService.register(userDTO);
	}
	
	/**
	 * <p>
	 * 用户登录
	 * </p>
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     userName : zhangsan : 用户名 : 是
	 *     password : 123456 : 密码 : 是
	 * </pre>
	 * @param userComposeQuery
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月5日 下午7:20:12
	 */
	@RequestMapping("/login")
	public EcBaseServiceResult login(@RequestBody UserComposeQuery userComposeQuery){
//		return userService.login(userComposeQuery);
		return userService.login(userComposeQuery, userComposeQuery.getUserName());
	}
}
