package com.bjsxt.ego.search.service.impl;

import com.bjsxt.ego.rpc.pojo.TbItem;
import com.bjsxt.ego.rpc.service.ItemService;
import com.bjsxt.ego.search.dao.ItemDao;
import com.bjsxt.ego.search.entity.Item;
import com.bjsxt.ego.search.entity.SearchResult;
import com.bjsxt.ego.search.service.SearchItemService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author lvyelanshan
 * @create 2019-10-24 9:21
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {

    //注入Dao对象
    @Autowired
    private ItemDao itemDao;

    /*注入远程代理对象*/
    @Autowired(required = false)
    private ItemService itemServiceProxy;

    @Override
    public SearchResult loadItemService(String item_keywords, Integer page) {

        SolrQuery params = new SolrQuery();
        //设置默认查询字段（默认按照定义的拷贝字段查询：item_keywords，这个字段在Solr中定义的是按照根据标题和卖点来查询）
        params.set("df","item_keywords");

        //设置查询条件,判断前端输入框中的值是否为空，如果是空的则查询所有，
        // 如果不是空的，根据前端传入的参数关键字封装查询条件，进行查询
        if(!StringUtils.isEmpty(item_keywords)){
            //设置查询条件
            params.setQuery(item_keywords);
        }else {
            //对应Solr索引库，表示查询所有
            params.set("q","*:*");
        }

        //指定分页字段进行分页查询
        Integer rows = 20;//一页显示20个商品信息
        //进行最小页的判断
        if (page<1){
            page=1;
        }
        //进行最大页判断
        Integer maxpage=100;
        if (page>maxpage){
            page=maxpage;
        }
        //start表示当前页的开始记录数
        Integer start = (page-1)*rows;

        //设置开始记录数
        params.setStart(start);
        //设置每页显示的条数
        params.setRows(rows);

        //设定高亮参数
        params.setHighlight(true);
        //设置高亮字段
        params.addHighlightField("title");
        //设置高亮字体颜色代码的前缀
        params.setHighlightSimplePre("<font color='red'>");
        //设置高亮字体颜色代码的后缀
        params.setHighlightSimplePost("</font>");

        //调用DAO方法，进行索引库查询
        QueryResponse queryResponse = itemDao.loadItem(params);
        //获得本次查询到的文档的集合(在索引库中是文档)（这个SolrDocumentList本身就是一个ArrayList）
        SolrDocumentList docList = queryResponse.getResults();
        //获得本次查询的高亮数据
        Map<String, Map<String, List<String>>> hlts = queryResponse.getHighlighting();

        //获得本次查询到的总记录数
        long total = docList.getNumFound();
        //创建List<Item>对象
        //List<Item> list = new ArrayList<>();
        //将docList转化为List<Item>，将查询到的文档集合封装成对应的商品集合
        DocumentObjectBinder binder = new DocumentObjectBinder();
        List<Item> list = binder.getBeans(Item.class, docList);

        for (Item t:list){
            String id = t.getId();
            //获得某个商品信息的高亮数据
            Map<String, List<String>> map = hlts.get(id);
            //获得某个商品的某个字段的高亮数据
            List<String> lts = map.get("title");
            if (lts!=null&&lts.size()>0){
                //如果有高亮数据则把原来的数据覆盖掉
                t.setTitle(lts.get(0));
            }

        }

        SearchResult result = new SearchResult();
        result.setMaxpage(Long.parseLong(String.valueOf(maxpage)));
        result.setTotal(total);
        result.setList(list);

        return result;
    }

    @Override
    public TbItem loadItemService(Long id) {

        return itemServiceProxy.loadTbItemById(id);
    }
}
