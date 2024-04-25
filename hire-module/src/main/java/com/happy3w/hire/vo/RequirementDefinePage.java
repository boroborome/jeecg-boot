package com.happy3w.hire.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.happy3w.hire.entity.RequirementDefineLevel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Description: 要求的定义
 * @Author: jeecg-boot
 * @Date:   2024-04-24
 * @Version: V1.0
 */
@Data
@ApiModel(value="requirement_definePage对象", description="要求的定义")
public class RequirementDefinePage {

	/**主键*/
	@ApiModelProperty(value = "主键")
    private String id;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**更新人*/
	@ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "更新日期")
    private Date updateTime;
	/**所属部门*/
	@ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
	/**需求名称*/
	@Excel(name = "需求名称", width = 15)
	@ApiModelProperty(value = "需求名称")
    private String name;
	/**需求描述*/
	@Excel(name = "需求描述", width = 15)
	@ApiModelProperty(value = "需求描述")
    private String description;

	@ExcelCollection(name="要求定义的具体条目")
	@ApiModelProperty(value = "要求定义的具体条目")
	private List<RequirementDefineLevel> requirementDefineLevelList;

}
