package com.happy3w.hire.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value="job_position对象", description="工作岗位")
@Data
@TableName("job_position")
public class JobPosition implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
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

	@Excel(name = "名称", width = 15)
    @ApiModelProperty(value = "名称")
    private String title;

    @Excel(name = "地点", width = 100)
    @ApiModelProperty(value = "地点")
    private String location;

    @Excel(name = "部门", width = 15)
    @ApiModelProperty(value = "部门")
    private String department;

    @Excel(name = "详细地址", width = 150)
    @ApiModelProperty(value = "详细地址")
    private String detailUrl;

    @Excel(name = "薪资最低", width = 50)
    @ApiModelProperty(value = "薪资最低")
    private Integer salaryStart;

    @Excel(name = "薪资最高", width = 50)
    @ApiModelProperty(value = "薪资最高")
    private Integer salaryEnd;

	@Excel(name = "备注", width = 250)
    @ApiModelProperty(value = "备注")
    private String remark;
}
