package com.sy.store.mapper;

import com.sy.store.entity.District;

import java.util.List;

public interface DistrictMapper {

    List<District> findByParent(String parent);

    String findNameByCode(String code);

}
