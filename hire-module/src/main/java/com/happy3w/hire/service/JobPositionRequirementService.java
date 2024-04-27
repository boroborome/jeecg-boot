package com.happy3w.hire.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.happy3w.hire.entity.JobPositionRequirement;
import com.happy3w.hire.mapper.JobPositionRequirementMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPositionRequirementService extends ServiceImpl<JobPositionRequirementMapper, JobPositionRequirement> {
	
	private final JobPositionRequirementMapper jobPositionRequirementMapper;

	public JobPositionRequirementService(JobPositionRequirementMapper jobPositionRequirementMapper) {
		this.jobPositionRequirementMapper = jobPositionRequirementMapper;
	}

	public List<JobPositionRequirement> selectByMainId(String mainId) {
		return jobPositionRequirementMapper.selectByMainId(mainId);
	}
}
