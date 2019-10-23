package com.bjsxt.ego.rpc.service.impl;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResult;
import com.bjsxt.ego.rpc.mapper.TbContentMapper;
import com.bjsxt.ego.rpc.pojo.TbContent;
import com.bjsxt.ego.rpc.pojo.TbContentExample;
import com.bjsxt.ego.rpc.service.TbContentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lvyelanshan
 * @create 2019-10-21 21:38
 */
@Service
public class TbContentServiceImpl implements TbContentService {
    /**
     * 注入Mapper对象
     * @param cid
     * @return
     */
    @Autowired
    private TbContentMapper tbContentMapper;


    /**
     * 根据内容分类的ID，查询该分类下的所有内容
     * 查询的是tb_content表
     * @param cid
     * @return
     */
    @Override
    public PageResult<TbContent> loadTbContentListService(Long cid,Integer page,Integer rows) {
        try {
            PageResult<TbContent> result = new PageResult<>();
            //创建TbContentExample对象
            TbContentExample example = new TbContentExample();
            TbContentExample.Criteria criteria = example.createCriteria();
            criteria.andCategoryIdEqualTo(cid);
            //指定分页查询参数
            Page pe = PageHelper.startPage(page, rows);
            //根据内容分类的ID，查询该分类下的所有内容
            List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
            result.setRows(list);
            result.setTotal(pe.getTotal());
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public EgoResult saveTbContentService(TbContent tbContent) {

        try {
            tbContentMapper.insert(tbContent);
            return EgoResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public EgoResult deleteTbContentService(List<Long> ids) {
        try {
            //创建TbContentExample对象(因为传入的是多个ID，所以查询条件是where id in)
            TbContentExample example = new TbContentExample();
            TbContentExample.Criteria criteria = example.createCriteria();
            //封装删除条件where id in
            criteria.andIdIn(ids);
            tbContentMapper.deleteByExample(example);
            /*删除成功返回EgoResult*/
            return EgoResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public EgoResult updateTbContentService(TbContent tbContent) {
        try {
            //updateByPrimaryKeySelective：如果里面有空的属性，那么这个空的属性不会更新
            tbContentMapper.updateByPrimaryKeySelective(tbContent);
            return EgoResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TbContent> loadTbContentListByCidService(Long id) {
        try {
            TbContentExample example = new TbContentExample();
            //where category_id = ?
            TbContentExample.Criteria criteria = example.createCriteria();
            criteria.andCategoryIdEqualTo(id);

            List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);

            System.out.println("后台服务提供者数据库中查询到的数据：------->");
            for (TbContent content : list) {
                System.out.println(content);
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
