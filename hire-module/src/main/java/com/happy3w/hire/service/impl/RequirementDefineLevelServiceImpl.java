package com.happy3w.hire.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happy3w.hire.entity.RequirementDefineLevel;
import com.happy3w.hire.mapper.RequirementDefineLevelMapper;
import com.happy3w.hire.service.IRequirementDefineLevelService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 要求定义的具体条目
 * @Author: jeecg-boot
 * @Date:   2024-04-24
 * @Version: V1.0
 */
@Service
public class RequirementDefineLevelServiceImpl extends ServiceImpl<RequirementDefineLevelMapper, RequirementDefineLevel> implements IRequirementDefineLevelService {
	
	private final RequirementDefineLevelMapper requirementDefineLevelMapper;

	public RequirementDefineLevelServiceImpl(RequirementDefineLevelMapper requirementDefineLevelMapper) {
		this.requirementDefineLevelMapper = requirementDefineLevelMapper;
	}

	@Override
	public List<RequirementDefineLevel> selectByMainId(String mainId) {
		return requirementDefineLevelMapper.selectByMainId(mainId);
	}
}
