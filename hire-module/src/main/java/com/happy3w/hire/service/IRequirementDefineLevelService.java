package com.happy3w.hire.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.happy3w.hire.entity.RequirementDefineLevel;

import java.util.List;

/**
 * @Description: 要求定义的具体条目
 * @Author: jeecg-boot
 * @Date:   2024-04-24
 * @Version: V1.0
 */
public interface IRequirementDefineLevelService extends IService<RequirementDefineLevel> {

	/**
	 * 通过主表id查询子表数据
	 *
	 * @param mainId 主表id
	 * @return List<RequirementDefineLevel>
	 */
	public List<RequirementDefineLevel> selectByMainId(String mainId);
}
