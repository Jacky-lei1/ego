package com.bjsxt.ego.search.dao.impl;

import com.bjsxt.ego.search.dao.ItemDao;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author lvyelanshan
 * @create 2019-10-23 21:47
 */
@Repository
public class ItemDaoImpl implements ItemDao {
    //注入Solr集群的访问对象(在applicationContext-dao.xml文件中创建过bean)
    @Autowired
    private CloudSolrServer solrServer;


    @Override
    public QueryResponse loadItem(SolrQuery params) {
        try {
            return solrServer.query(params);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
