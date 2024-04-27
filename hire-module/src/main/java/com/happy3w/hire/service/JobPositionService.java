package com.happy3w.hire.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happy3w.hire.entity.JobPosition;
import com.happy3w.hire.entity.JobPositionRequirement;
import com.happy3w.hire.entity.JobPosition;
import com.happy3w.hire.entity.JobPositionRequirement;
import com.happy3w.hire.mapper.JobPositionMapper;
import com.happy3w.hire.mapper.JobPositionRequirementMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Service
public class JobPositionService extends ServiceImpl<JobPositionMapper, JobPosition> {
	private final JobPositionMapper jobPositionMapper;
	private final JobPositionRequirementMapper jobPositionRequirementMapper;

	public JobPositionService(JobPositionMapper jobPositionMapper,
							  JobPositionRequirementMapper jobPositionRequirementMapper) {
		this.jobPositionMapper = jobPositionMapper;
		this.jobPositionRequirementMapper = jobPositionRequirementMapper;
	}

	@Transactional(rollbackFor = Exception.class)
	public void saveMain(JobPosition jobPosition, List<JobPositionRequirement> jobPositionRequirementList) {
		jobPositionMapper.insert(jobPosition);
		if(jobPositionRequirementList !=null && jobPositionRequirementList.size()>0) {
			for(JobPositionRequirement entity: jobPositionRequirementList) {
				//外键设置
				entity.setJobId(jobPosition.getId());
				jobPositionRequirementMapper.insert(entity);
			}
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public void updateMain(JobPosition JobPosition,List<JobPositionRequirement> jobPositionRequirementList) {
		jobPositionMapper.updateById(JobPosition);
		
		//1.先删除子表数据
		jobPositionRequirementMapper.deleteByMainId(JobPosition.getId());
		
		//2.子表数据重新插入
		if(jobPositionRequirementList!=null && jobPositionRequirementList.size()>0) {
			for(JobPositionRequirement entity:jobPositionRequirementList) {
				//外键设置
				entity.setRdId(JobPosition.getId());
				jobPositionRequirementMapper.insert(entity);
			}
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		jobPositionRequirementMapper.deleteByMainId(id);
		jobPositionMapper.deleteById(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			jobPositionRequirementMapper.deleteByMainId(id.toString());
			jobPositionMapper.deleteById(id);
		}
	}
	
}
