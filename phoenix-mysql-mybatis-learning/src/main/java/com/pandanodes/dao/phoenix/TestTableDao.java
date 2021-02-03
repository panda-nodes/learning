package com.pandanodes.dao.phoenix;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestTableDao {

    List<Map<String,Object>> list();
}
