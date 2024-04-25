package com.happy3w.hire.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.happy3w.hire.entity.RequirementDefine;
import com.happy3w.hire.entity.RequirementDefineLevel;
import com.happy3w.hire.service.IRequirementDefineLevelService;
import com.happy3w.hire.service.IRequirementDefineService;
import com.happy3w.hire.vo.RequirementDefinePage;
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
import org.springframework.beans.factory.annotation.Autowired;
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
 * @Description: 要求的定义
 * @Author: jeecg-boot
 * @Date:   2024-04-24
 * @Version: V1.0
 */
@Api(tags="要求的定义")
@RestController
@RequestMapping("/hire/requirementDefine")
@Slf4j
public class RequirementDefineController {
	@Autowired
	private IRequirementDefineService requirementDefineService;
	@Autowired
	private IRequirementDefineLevelService requirementDefineLevelService;
	
	/**
	 * 分页列表查询
	 *
	 * @param requirementDefine
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "要求的定义-分页列表查询")
	@ApiOperation(value="要求的定义-分页列表查询", notes="要求的定义-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<RequirementDefine>> queryPageList(RequirementDefine requirementDefine,
														  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
														  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
														  HttpServletRequest req) {
		QueryWrapper<RequirementDefine> queryWrapper = QueryGenerator.initQueryWrapper(requirementDefine, req.getParameterMap());
		Page<RequirementDefine> page = new Page<RequirementDefine>(pageNo, pageSize);
		IPage<RequirementDefine> pageList = requirementDefineService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param requirementDefinePage
	 * @return
	 */
	@AutoLog(value = "要求的定义-添加")
	@ApiOperation(value="要求的定义-添加", notes="要求的定义-添加")
    @RequiresPermissions("hire:requirement_define:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody RequirementDefinePage requirementDefinePage) {
		RequirementDefine requirementDefine = new RequirementDefine();
		BeanUtils.copyProperties(requirementDefinePage, requirementDefine);
		requirementDefineService.saveMain(requirementDefine, requirementDefinePage.getRequirementDefineLevelList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param requirementDefinePage
	 * @return
	 */
	@AutoLog(value = "要求的定义-编辑")
	@ApiOperation(value="要求的定义-编辑", notes="要求的定义-编辑")
    @RequiresPermissions("hire:requirement_define:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody RequirementDefinePage requirementDefinePage) {
		RequirementDefine requirementDefine = new RequirementDefine();
		BeanUtils.copyProperties(requirementDefinePage, requirementDefine);
		RequirementDefine requirementDefineEntity = requirementDefineService.getById(requirementDefine.getId());
		if(requirementDefineEntity==null) {
			return Result.error("未找到对应数据");
		}
		requirementDefineService.updateMain(requirementDefine, requirementDefinePage.getRequirementDefineLevelList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "要求的定义-通过id删除")
	@ApiOperation(value="要求的定义-通过id删除", notes="要求的定义-通过id删除")
    @RequiresPermissions("hire:requirement_define:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		requirementDefineService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "要求的定义-批量删除")
	@ApiOperation(value="要求的定义-批量删除", notes="要求的定义-批量删除")
    @RequiresPermissions("hire:requirement_define:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.requirementDefineService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "要求的定义-通过id查询")
	@ApiOperation(value="要求的定义-通过id查询", notes="要求的定义-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<RequirementDefine> queryById(@RequestParam(name="id",required=true) String id) {
		RequirementDefine requirementDefine = requirementDefineService.getById(id);
		if(requirementDefine==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(requirementDefine);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "要求定义的具体条目通过主表ID查询")
	@ApiOperation(value="要求定义的具体条目主表ID查询", notes="要求定义的具体条目-通主表ID查询")
	@GetMapping(value = "/queryRequirementDefineLevelByMainId")
	public Result<List<RequirementDefineLevel>> queryRequirementDefineLevelListByMainId(@RequestParam(name="id",required=true) String id) {
		List<RequirementDefineLevel> requirementDefineLevelList = requirementDefineLevelService.selectByMainId(id);
		return Result.OK(requirementDefineLevelList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param requirementDefine
    */
    @RequiresPermissions("hire:requirement_define:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RequirementDefine requirementDefine) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<RequirementDefine> queryWrapper = QueryGenerator.initQueryWrapper(requirementDefine, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //配置选中数据查询条件
      String selections = request.getParameter("selections");
      if(oConvertUtils.isNotEmpty(selections)) {
         List<String> selectionList = Arrays.asList(selections.split(","));
         queryWrapper.in("id",selectionList);
      }
      //Step.2 获取导出数据
      List<RequirementDefine> requirementDefineList = requirementDefineService.list(queryWrapper);

      // Step.3 组装pageList
      List<RequirementDefinePage> pageList = new ArrayList<RequirementDefinePage>();
      for (RequirementDefine main : requirementDefineList) {
          RequirementDefinePage vo = new RequirementDefinePage();
          BeanUtils.copyProperties(main, vo);
          List<RequirementDefineLevel> requirementDefineLevelList = requirementDefineLevelService.selectByMainId(main.getId());
          vo.setRequirementDefineLevelList(requirementDefineLevelList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "要求的定义列表");
      mv.addObject(NormalExcelConstants.CLASS, RequirementDefinePage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("要求的定义数据", "导出人:"+sysUser.getRealname(), "要求的定义"));
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
    @RequiresPermissions("hire:requirement_define:importExcel")
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
              List<RequirementDefinePage> list = ExcelImportUtil.importExcel(file.getInputStream(), RequirementDefinePage.class, params);
              for (RequirementDefinePage page : list) {
                  RequirementDefine po = new RequirementDefine();
                  BeanUtils.copyProperties(page, po);
                  requirementDefineService.saveMain(po, page.getRequirementDefineLevelList());
              }
              return Result.OK("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.OK("文件导入失败！");
    }

}
