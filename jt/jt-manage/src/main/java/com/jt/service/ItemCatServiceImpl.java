package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.annotation.CacheFind;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.util.ObjectMapperUtil;
import com.jt.vo.EasyUITree;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/9/25 20:23
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
@Service
public class ItemCatServiceImpl implements ItemCatService{
    @Autowired
    private ItemCatMapper itemCatMapper;
    @Autowired(required = false)
    private Jedis jedis;

    @Override
    public String findItemCatNameById(Long itemCatId) {
        return itemCatMapper.selectById(itemCatId).getName();
    }

    @Override
    @CacheFind(preKey = "ITEM_CAT_PARENTID")
    public List<EasyUITree> findItemCat(Long parentId) {
        QueryWrapper<ItemCat> qw = new QueryWrapper<>();
        qw.eq("parent_id", parentId);
        List<ItemCat> list = itemCatMapper.selectList(qw);
        List<EasyUITree> easyUITrees = new ArrayList<>();
        for (ItemCat i:list) {
            EasyUITree easyUITree = new EasyUITree().setId(i.getId()).setText(i.getName());
            String state = i.getIsParent()?"closed":"open";
            easyUITree.setState(state);
            easyUITrees.add(easyUITree);
        }
        return easyUITrees;
    }

//    @Override
//    @CacheFind(preKey = "ITEM_CAT_PARENTID")
//    public List<EasyUITree> findItemCatListCache(Long parentId) {
//        String key = "ITEM_CAT_PARENTID::"+parentId;
//        List<EasyUITree> easyUITree = new ArrayList<>();
//        if(jedis.exists(key)){
//            long start = System.currentTimeMillis();
//            String s = jedis.get(key);
//            easyUITree = ObjectMapperUtil.toObj(s,easyUITree.getClass());
//            long end = System.currentTimeMillis();
//            System.out.println("从redis获取数据,耗时:"+ (end-start));
//        }else{
//            long start = System.currentTimeMillis();
//            easyUITree = findItemCat(parentId);
//            jedis.set(key,ObjectMapperUtil.toJson(easyUITree));
//            long end = System.currentTimeMillis();
//            System.out.println("向redis存取数据,耗时:"+ (end-start));
//        }
//        return easyUITree;
//    }
}
