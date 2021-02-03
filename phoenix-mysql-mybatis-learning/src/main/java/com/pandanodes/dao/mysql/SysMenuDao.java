package com.pandanodes.dao.mysql;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysMenuDao {
    List<Map<String,Object>> getMenuList();
}
