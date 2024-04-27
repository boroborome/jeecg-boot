package com.happy3w.hire.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.happy3w.hire.entity.JobPositionRequirement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

@Data
@ApiModel(value="job_positionPage对象", description="工作岗位")
public class JobPositionPage {

    /**主键*/
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
    /**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
    /**创建日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
    /**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
    /**更新日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
    /**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
    /**名称*/
    @Excel(name = "名称", width = 15)
    @ApiModelProperty(value = "名称")
    private java.lang.String title;
    /**地点*/
    @Excel(name = "地点", width = 15)
    @ApiModelProperty(value = "地点")
    private java.lang.String location;
    /**部门*/
    @Excel(name = "部门", width = 15)
    @ApiModelProperty(value = "部门")
    private java.lang.String department;
    /**详细地址*/
    @Excel(name = "详细地址", width = 15)
    @ApiModelProperty(value = "详细地址")
    private java.lang.String detailUrl;
    /**备注*/
    @Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
    /**薪资最低*/
    @Excel(name = "薪资最低", width = 15)
    @ApiModelProperty(value = "薪资最低")
    private java.lang.Integer salaryStart;
    /**薪资最高*/
    @Excel(name = "薪资最高", width = 15)
    @ApiModelProperty(value = "薪资最高")
    private java.lang.Integer salaryEnd;

    @ExcelCollection(name="工作要求")
    @ApiModelProperty(value = "工作要求")
    private List<JobPositionRequirement> jobPositionRequirementList;

}
