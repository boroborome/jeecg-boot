package com.happy3w.hire.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.happy3w.hire.entity.RequirementDefineLevel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 要求定义的具体条目
 * @Author: jeecg-boot
 * @Date:   2024-04-24
 * @Version: V1.0
 */
public interface RequirementDefineLevelMapper extends BaseMapper<RequirementDefineLevel> {

	/**
	 * 通过主表id删除子表数据
	 *
	 * @param mainId 主表id
	 * @return boolean
	 */
	public boolean deleteByMainId(@Param("mainId") String mainId);

  /**
   * 通过主表id查询子表数据
   *
   * @param mainId 主表id
   * @return List<RequirementDefineLevel>
   */
	public List<RequirementDefineLevel> selectByMainId(@Param("mainId") String mainId);
}
