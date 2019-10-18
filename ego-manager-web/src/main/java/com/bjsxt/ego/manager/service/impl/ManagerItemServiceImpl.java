package com.bjsxt.ego.manager.service.impl;

import com.bjsxt.ego.beans.*;
import com.bjsxt.ego.manager.service.ManagerItemService;
import com.bjsxt.ego.rpc.pojo.TbItem;
import com.bjsxt.ego.rpc.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author lvyelanshan
 * @create 2019-10-18 10:41
 */
@Service
public class ManagerItemServiceImpl implements ManagerItemService {
    //注入远程服务的代理对象

    @Autowired(required = false)
    private ItemService itemServiceProxy;

    //通过Spring的EL表达式注入ftp信息
    @Value("${FTP_HOST}")
    private String FTP_HOST;
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FTP_PATH}")
    private String FTP_PATH;
    @Value("${IMAGE_HTTP_PATH}")
    private String IMAGE_HTTP_PATH;


    @Override
    public PageResult<TbItem> selectItemListService(Integer page, Integer rows) {

        return itemServiceProxy.selectItemList(page,rows);

    }

    /**
     * 完成商品的上架
     * @param ids
     * @return
     */
    @Override
    public EgoResult reshelfItem(Long[] ids) {
        //将IDS数组转化为List集合
        List<Long> itemIds = Arrays.asList(ids);
        //调用远程服务
        return itemServiceProxy.updataItemStatus(itemIds,true);
    }

    /**
     * 完成商品的下架
     * @param ids
     * @return
     */
    @Override
    public EgoResult instockItem(Long[] ids) {
        //将IDS数组转化为List集合
        List<Long> itemIds = Arrays.asList(ids);
        //调用远程服务
        return itemServiceProxy.updataItemStatus(itemIds,false);
    }

    /**
     * 完成商品的删除
     * @param ids：需要删除的商品的ID的集合
     * @return
     */
    @Override
    public EgoResult deleteItemService(Long[] ids) {
        List<Long> itemList = Arrays.asList(ids);
        //调用远程删除商品信息的服务即可
      return  itemServiceProxy.deleteItem(itemList);

    }

    /**
     * 实现客户端浏览器上传文件到TomCat服务器，服务器再将图片上传到图片服务器中
     * @param file：封装了我们从客户端浏览器上传到服务器的文件，那么通过这个对象就能够获取到相应的文件信息
     * @return
     */
    @Override
    public PictureResult uploadItemPic(MultipartFile file) {
        boolean flag = false;
        String filename=null;

        try {
            //获得新的文件名字
            filename= IDUtils.genImageName();
            //获得上传的文件的原始名字，通过这个名字能获取到文件的后缀名
            String oriName = file.getOriginalFilename();
            //获得文件的扩展名
            String ext = oriName.substring(oriName.lastIndexOf("."));
            //将工具类随机生成的文件名和原始文件名的后缀拼接组成新的文件名
            filename = filename+ext;
            //获取上传文件的流对象
            InputStream local = file.getInputStream();

            //实现文件上传到ftp,文件上传成功相当于将flag改成true
            flag = FtpUtils.uploadFile(FTP_HOST,FTP_PORT,FTP_USERNAME,FTP_PASSWORD,FTP_PATH,filename,local);
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
        }
        PictureResult result = null;
        if(flag){ //如果上传成功，就创建一个PictureResult对象
            result = new PictureResult();
            result.setError(0);//0表示上传成功
            result.setUrl(IMAGE_HTTP_PATH+"/"+filename);//表示图片的完整路径
            result.setMessage("ok");
        }else { //上传失败
            result = new PictureResult();
            result.setError(1);//1表示上传失败
            result.setUrl("url");//表示图片的完整路径
            result.setMessage("error");

        }

        return result;
    }
}
