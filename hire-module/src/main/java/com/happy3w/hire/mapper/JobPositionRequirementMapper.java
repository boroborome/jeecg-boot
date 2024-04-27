package com.happy3w.hire.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.happy3w.hire.entity.JobPositionRequirement;
import com.happy3w.hire.entity.RequirementDefineLevel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface JobPositionRequirementMapper extends BaseMapper<JobPositionRequirement> {

    @Delete(value = "DELETE FROM job_position_requirement WHERE job_id=#{mainId}")
    public boolean deleteByMainId(@Param("mainId") String mainId);

    @Select("SELECT * FROM  job_position_requirement WHERE job_id = #{mainId}")
    public List<JobPositionRequirement> selectByMainId(@Param("mainId") String mainId);
}
