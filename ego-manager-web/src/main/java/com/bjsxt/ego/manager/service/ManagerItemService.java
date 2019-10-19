package com.bjsxt.ego.manager.service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResult;
import com.bjsxt.ego.beans.PictureResult;
import com.bjsxt.ego.rpc.pojo.TbItem;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lvyelanshan
 * @create 2019-10-18 10:38
 */
public interface ManagerItemService {
    /**
     * 完成商品信息的分页查询
     */
    public PageResult<TbItem> selectItemListService(Integer page, Integer rows);

    /**
     * 完成商品的上架
     * @param ids
     * @return
     */
    public EgoResult reshelfItem(Long[] ids);

    /**
     * 完成商品的下架
     * @param ids
     * @return
     */
    public EgoResult instockItem(Long[] ids);

    /**
     * 删除商品的信息
     * @param ids：需要删除的商品的ID的集合
     * @return
     */
    public EgoResult deleteItemService(Long[] ids);

    /**
     * 完成商品图片的上传
     */
    public PictureResult uploadItemPic(MultipartFile file);


    /**
     * 完成商品详细信息的发布
     * @param item 商品对象
     * @param desc 商品描述
     * @return
     */
    public EgoResult saveItemService(TbItem item,String desc);

}
