package com.easy.cloud.core.basic.pojo.dto;

import java.util.Date;

/**
 * 基础数据传输对象
 * @author daiqi
 * 创建日期 2018年1月6日 下午3:36:13
 */
public class EcBaseDTO {
	/** 表id */
	private Long id;
	private Date createDate;
	private Date updateDate;
	private Integer version;
	private Long createBy;
	private Long updateBy;
	/** 删除标志 */
	private Integer isDeleted;
	/** 备注 */
	private String remark;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public Long getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Override
	public String toString() {
		return "DqBaseDTO [id=" + id + ", createDate=" + createDate + ", updateDate=" + updateDate + ", version="
				+ version + ", createBy=" + createBy + ", updateBy=" + updateBy + ", isDeleted=" + isDeleted
				+ ", remark=" + remark + "]";
	}
	
	
}
