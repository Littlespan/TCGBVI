package com.jt.service;

import com.jt.pojo.ItemCat;
import com.jt.vo.EasyUITree;

import java.util.List;

public interface ItemCatService {
    String findItemCatNameById(Long itemCatId);
    List<EasyUITree> findItemCat(Long parentId);
//    List<EasyUITree> findItemCatListCache(Long parentId);
}
