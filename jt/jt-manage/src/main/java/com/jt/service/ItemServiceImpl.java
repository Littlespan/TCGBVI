package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.ItemDescMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.mapper.ItemMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
    private ItemDescMapper itemDescMapper;

    @Override
    public EasyUITable query(Integer page, Integer rows) {
        QueryWrapper<Item> qw = new QueryWrapper<>();
        qw.orderByDesc("updated");
        IPage<Item> iPage = new Page<>(page,rows);
        iPage = itemMapper.selectPage(iPage,qw);
        int total =(int) iPage.getTotal();
        List<Item> itemList = iPage.getRecords();
        return new EasyUITable(total,itemList);
    }

    @Override
    @Transactional
    public SysResult deleteById(Integer... ids) {
        itemMapper.deleteBatchIds(Arrays.asList(ids));
        itemDescMapper.deleteBatchIds(Arrays.asList(ids));
        return SysResult.success();
    }

    @Override
    @Transactional
    public SysResult saveItem(Item item, ItemDesc itemDesc) {
        item.setStatus(1);
        itemMapper.insert(item);
        itemDesc.setItemId(item.getId());
        itemDescMapper.insert(itemDesc);
        return SysResult.success();
    }

    @Override
    public SysResult instockItemByIds(Long... ids) {
        for (long id:ids) {
            Item item = itemMapper.selectById(id).setStatus(2);
            item.setUpdated(new Date());
            itemMapper.updateById(item);
        }
        return SysResult.success();
    }

    @Override
    public SysResult reshelfItemById(Long... ids) {
        for (long id:ids) {
            Item item = itemMapper.selectById(id).setStatus(1);
            item.setUpdated(new Date());
            itemMapper.updateById(item);
        }
        return SysResult.success();
    }

    @Override
    @Transactional
    public SysResult updateItemById(Item item,ItemDesc itemDesc) {
        itemMapper.updateById(item);

        itemDesc.setItemId(item.getId());
        itemDescMapper.updateById(itemDesc);
        return SysResult.success();
    }

    @Override
    public SysResult findItemDescById(Long itemId) {
        ItemDesc itemDesc = itemDescMapper.selectById(itemId);
        return SysResult.success(itemDesc);
    }

    @Override
    public List<Item> getItems() {
        return itemMapper.selectList(null);
    }
}
