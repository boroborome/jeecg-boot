package com.happy3w.hire.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happy3w.hire.entity.RequirementDefine;
import com.happy3w.hire.entity.RequirementDefineLevel;
import com.happy3w.hire.mapper.RequirementDefineMapper;
import com.happy3w.hire.mapper.RequirementDefineLevelMapper;
import com.happy3w.hire.service.IRequirementDefineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 要求的定义
 * @Author: jeecg-boot
 * @Date:   2024-04-24
 * @Version: V1.0
 */
@Service
public class RequirementDefineServiceImpl extends ServiceImpl<RequirementDefineMapper, RequirementDefine> implements IRequirementDefineService {

	private final RequirementDefineMapper requirementDefineMapper;
	private final RequirementDefineLevelMapper requirementDefineLevelMapper;

	public RequirementDefineServiceImpl(RequirementDefineMapper requirementDefineMapper,
										RequirementDefineLevelMapper requirementDefineLevelMapper) {
		this.requirementDefineMapper = requirementDefineMapper;
		this.requirementDefineLevelMapper = requirementDefineLevelMapper;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMain(RequirementDefine requirementDefine, List<RequirementDefineLevel> requirementDefineLevelList) {
		requirementDefineMapper.insert(requirementDefine);
		if(requirementDefineLevelList!=null && requirementDefineLevelList.size()>0) {
			for(RequirementDefineLevel entity:requirementDefineLevelList) {
				//外键设置
				entity.setRdId(requirementDefine.getId());
				requirementDefineLevelMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMain(RequirementDefine requirementDefine,List<RequirementDefineLevel> requirementDefineLevelList) {
		requirementDefineMapper.updateById(requirementDefine);
		
		//1.先删除子表数据
		requirementDefineLevelMapper.deleteByMainId(requirementDefine.getId());
		
		//2.子表数据重新插入
		if(requirementDefineLevelList!=null && requirementDefineLevelList.size()>0) {
			for(RequirementDefineLevel entity:requirementDefineLevelList) {
				//外键设置
				entity.setRdId(requirementDefine.getId());
				requirementDefineLevelMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		requirementDefineLevelMapper.deleteByMainId(id);
		requirementDefineMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			requirementDefineLevelMapper.deleteByMainId(id.toString());
			requirementDefineMapper.deleteById(id);
		}
	}
	
}
