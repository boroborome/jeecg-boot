package com.happy3w.hire.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.happy3w.hire.entity.RequirementDefine;
import com.happy3w.hire.entity.RequirementDefineLevel;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 要求的定义
 * @Author: jeecg-boot
 * @Date:   2024-04-24
 * @Version: V1.0
 */
public interface IRequirementDefineService extends IService<RequirementDefine> {

	/**
	 * 添加一对多
	 *
	 * @param requirementDefine
	 * @param requirementDefineLevelList
	 */
	public void saveMain(RequirementDefine requirementDefine,List<RequirementDefineLevel> requirementDefineLevelList) ;
	
	/**
	 * 修改一对多
	 *
   * @param requirementDefine
   * @param requirementDefineLevelList
	 */
	public void updateMain(RequirementDefine requirementDefine,List<RequirementDefineLevel> requirementDefineLevelList);
	
	/**
	 * 删除一对多
	 *
	 * @param id
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 *
	 * @param idList
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
