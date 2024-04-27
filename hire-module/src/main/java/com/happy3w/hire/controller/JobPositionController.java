package com.happy3w.hire.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.happy3w.hire.entity.JobPosition;
import com.happy3w.hire.entity.JobPositionRequirement;
import com.happy3w.hire.service.JobPositionRequirementService;
import com.happy3w.hire.service.JobPositionService;
import com.happy3w.hire.vo.JobPositionPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Description: 工作岗位
 * @Author: jeecg-boot
 * @Date:   2024-04-27
 * @Version: V1.0
 */
@Api(tags="工作岗位")
@RestController
@RequestMapping("/hire/jobPosition")
@Slf4j
public class JobPositionController {
    private final JobPositionService jobPositionService;
    private final JobPositionRequirementService jobPositionRequirementService;

    public JobPositionController(JobPositionService jobPositionService, JobPositionRequirementService jobPositionRequirementService) {
        this.jobPositionService = jobPositionService;
        this.jobPositionRequirementService = jobPositionRequirementService;
    }

    //@AutoLog(value = "工作岗位-分页列表查询")
    @ApiOperation(value="工作岗位-分页列表查询", notes="工作岗位-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<JobPosition>> queryPageList(JobPosition jobPosition,
                                                    @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                    @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                    HttpServletRequest req) {
        QueryWrapper<JobPosition> queryWrapper = QueryGenerator.initQueryWrapper(jobPosition, req.getParameterMap());
        Page<JobPosition> page = new Page<JobPosition>(pageNo, pageSize);
        IPage<JobPosition> pageList = jobPositionService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     *   添加
     *
     * @param jobPositionPage
     * @return
     */
    @AutoLog(value = "工作岗位-添加")
    @ApiOperation(value="工作岗位-添加", notes="工作岗位-添加")
    @RequiresPermissions("hire:job_position:add")
    @PostMapping(value = "/add")
    public Result<String> add(@RequestBody JobPositionPage jobPositionPage) {
        JobPosition jobPosition = new JobPosition();
        BeanUtils.copyProperties(jobPositionPage, jobPosition);
        jobPositionService.saveMain(jobPosition, jobPositionPage.getJobPositionRequirementList());
        return Result.OK("添加成功！");
    }

    /**
     *  编辑
     *
     * @param jobPositionPage
     * @return
     */
    @AutoLog(value = "工作岗位-编辑")
    @ApiOperation(value="工作岗位-编辑", notes="工作岗位-编辑")
    @RequiresPermissions("hire:job_position:edit")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
    public Result<String> edit(@RequestBody JobPositionPage jobPositionPage) {
        JobPosition jobPosition = new JobPosition();
        BeanUtils.copyProperties(jobPositionPage, jobPosition);
        JobPosition jobPositionEntity = jobPositionService.getById(jobPosition.getId());
        if(jobPositionEntity==null) {
            return Result.error("未找到对应数据");
        }
        jobPositionService.updateMain(jobPosition, jobPositionPage.getJobPositionRequirementList());
        return Result.OK("编辑成功!");
    }

    /**
     *   通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "工作岗位-通过id删除")
    @ApiOperation(value="工作岗位-通过id删除", notes="工作岗位-通过id删除")
    @RequiresPermissions("hire:job_position:delete")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam(name="id",required=true) String id) {
        jobPositionService.delMain(id);
        return Result.OK("删除成功!");
    }

    /**
     *  批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "工作岗位-批量删除")
    @ApiOperation(value="工作岗位-批量删除", notes="工作岗位-批量删除")
    @RequiresPermissions("hire:job_position:deleteBatch")
    @DeleteMapping(value = "/deleteBatch")
    public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
        this.jobPositionService.delBatchMain(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    //@AutoLog(value = "工作岗位-通过id查询")
    @ApiOperation(value="工作岗位-通过id查询", notes="工作岗位-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<JobPosition> queryById(@RequestParam(name="id",required=true) String id) {
        JobPosition jobPosition = jobPositionService.getById(id);
        if(jobPosition==null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(jobPosition);

    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    //@AutoLog(value = "工作要求通过主表ID查询")
    @ApiOperation(value="工作要求主表ID查询", notes="工作要求-通主表ID查询")
    @GetMapping(value = "/queryJobPositionRequirementByMainId")
    public Result<List<JobPositionRequirement>> queryJobPositionRequirementListByMainId(@RequestParam(name="id",required=true) String id) {
        List<JobPositionRequirement> jobPositionRequirementList = jobPositionRequirementService.selectByMainId(id);
        return Result.OK(jobPositionRequirementList);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param jobPosition
     */
    @RequiresPermissions("hire:job_position:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, JobPosition jobPosition) {
        // Step.1 组装查询条件查询数据
        QueryWrapper<JobPosition> queryWrapper = QueryGenerator.initQueryWrapper(jobPosition, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        //配置选中数据查询条件
        String selections = request.getParameter("selections");
        if(oConvertUtils.isNotEmpty(selections)) {
            List<String> selectionList = Arrays.asList(selections.split(","));
            queryWrapper.in("id",selectionList);
        }
        //Step.2 获取导出数据
        List<JobPosition> jobPositionList = jobPositionService.list(queryWrapper);

        // Step.3 组装pageList
        List<JobPositionPage> pageList = new ArrayList<JobPositionPage>();
        for (JobPosition main : jobPositionList) {
            JobPositionPage vo = new JobPositionPage();
            BeanUtils.copyProperties(main, vo);
            List<JobPositionRequirement> jobPositionRequirementList = jobPositionRequirementService.selectByMainId(main.getId());
            vo.setJobPositionRequirementList(jobPositionRequirementList);
            pageList.add(vo);
        }

        // Step.4 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "工作岗位列表");
        mv.addObject(NormalExcelConstants.CLASS, JobPositionPage.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("工作岗位数据", "导出人:"+sysUser.getRealname(), "工作岗位"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequiresPermissions("hire:job_position:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            // 获取上传文件对象
            MultipartFile file = entity.getValue();
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<JobPositionPage> list = ExcelImportUtil.importExcel(file.getInputStream(), JobPositionPage.class, params);
                for (JobPositionPage page : list) {
                    JobPosition po = new JobPosition();
                    BeanUtils.copyProperties(page, po);
                    jobPositionService.saveMain(po, page.getJobPositionRequirementList());
                }
                return Result.OK("文件导入成功！数据行数:" + list.size());
            } catch (Exception e) {
                log.error(e.getMessage(),e);
                return Result.error("文件导入失败:"+e.getMessage());
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                    log.error("Failed when close file.", e);
                }
            }
        }
        return Result.OK("文件导入失败！");
    }

}