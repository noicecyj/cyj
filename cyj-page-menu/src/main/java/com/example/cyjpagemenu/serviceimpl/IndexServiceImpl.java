package com.example.cyjpagemenu.serviceimpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.cyjpagemenu.api.DictionaryApiService;
import com.example.cyjpagemenu.api.QueryApiService;
import com.example.cyjpagemenu.dao.IndexDataTableDao;
import com.example.cyjpagemenu.entity.*;
import com.example.cyjpagemenu.entity.dto.DictionaryDTO;
import com.example.cyjpagemenu.entity.vo.DataSourceVO;
import com.example.cyjpagemenu.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-09-13
 */
@Service
public class IndexServiceImpl extends BaseService implements IndexService {

    private final static Logger logger = LoggerFactory.getLogger(IndexServiceImpl.class);
    private IndexDataTableDao indexDataTableDao;
    private DataFormServiceImpl dataFormService;
    private DataFormItemServiceImpl dataFormItemService;
    private DataTableServiceImpl dataTableService;
    private DataTableItemServiceImpl dataTableItemService;
    private QueryApiService queryApiService;
    private DictionaryApiService dictionaryApiService;

    @Autowired
    public void setIndexDataTableDao(IndexDataTableDao indexDataTableDao) {
        this.indexDataTableDao = indexDataTableDao;
    }

    @Autowired
    public void setDataFormService(DataFormServiceImpl dataFormService) {
        this.dataFormService = dataFormService;
    }

    @Autowired
    public void setDataFormItemService(DataFormItemServiceImpl dataFormItemService) {
        this.dataFormItemService = dataFormItemService;
    }

    @Autowired
    public void setDataTableService(DataTableServiceImpl dataTableService) {
        this.dataTableService = dataTableService;
    }

    @Autowired
    public void setDataTableItemService(DataTableItemServiceImpl dataTableItemService) {
        this.dataTableItemService = dataTableItemService;
    }

    @Autowired
    public void setQueryApiService(QueryApiService queryApiService) {
        this.queryApiService = queryApiService;
    }

    @Autowired
    public void setDictionaryApiService(DictionaryApiService dictionaryApiService) {
        this.dictionaryApiService = dictionaryApiService;
    }

    @Override
    public List<DataFormItemPO> findDataFormByName(String name) {
        QDataFormPO qDataFormPO = QDataFormPO.dataFormPO;
        QDataFormItemPO qDataFormItemPO = QDataFormItemPO.dataFormItemPO;
        return queryFactory.selectFrom(qDataFormItemPO)
                .innerJoin(qDataFormPO)
                .on(qDataFormItemPO.pid.eq(qDataFormPO.id))
                .where(qDataFormPO.dataFormName.eq(name))
                .orderBy(qDataFormItemPO.sortCode.asc()).fetch();
    }

    @Override
    public List<DataTableItemPO> findDataTableByName(String name) {
        QDataTablePO qDataTablePO = QDataTablePO.dataTablePO;
        QDataTableItemPO qDataTableItemPO = QDataTableItemPO.dataTableItemPO;
        return queryFactory.selectFrom(qDataTableItemPO)
                .innerJoin(qDataTablePO)
                .on(qDataTableItemPO.pid.eq(qDataTablePO.id))
                .where(qDataTablePO.dataTableName.eq(name))
                .orderBy(qDataTableItemPO.sortCode.asc()).fetch();
    }

    @Override
    public DataTablePO findOneByName(String name) {
        if (indexDataTableDao.findDataTablePOByDataTableName(name).isPresent()) {
            return indexDataTableDao.findDataTablePOByDataTableName(name).get();
        }
        return null;
    }

    @Override
    public void formAndTableGenerate(String name) {
        DataFormPO dataFormPO = new DataFormPO();
        dataFormPO.setDataFormName(name + "Form");
        dataFormService.addOne(dataFormPO);
        DataTablePO dataTablePO = new DataTablePO();
        dataTablePO.setDataTableName(name + "Table");
        dataTableService.addOne(dataTablePO);
    }

    @Override
    public Object transformData(DataSourceVO dataSourceVO) {
        Object dataSource = dataSourceVO.getDataSource();
        Object dataTableItem = dataSourceVO.getDataTableItemList();
        List<DataTableItemPO> dataTableItemPOList = JSONArray
                .parseArray(JSONObject.toJSONString(dataTableItem), DataTableItemPO.class);
        List<Object> dataSourceList = JSONArray.parseArray(JSONObject.toJSONString(dataSource));
        Map<String, List<String>> dataTip = new HashMap<>(16);
        for (DataTableItemPO dataTableItemPO : dataTableItemPOList) {
            JSONObject jsonData = JSONObject.parseObject(dataTableItemPO.getJsonData());
            String dataSourceType = jsonData.getString("dataSourceType");
            if (dataSourceType != null) {
                List<String> list = new ArrayList<>();
                if ("dataBase".equals(dataSourceType)) {
                    String dataSourceStr = jsonData.getString("dataSource");
                    String dataIndexStr = jsonData.getString("dataIndex");
                    list.add("dataBase");
                    list.add(dataSourceStr);
                    dataTip.put(dataIndexStr, list);
                }else if ("dictionary".equals(dataSourceType)){
                    String dataSourceStr = jsonData.getString("dataSource");
                    String dataIndexStr = jsonData.getString("dataIndex");
                    list.add("dictionary");
                    list.add(dataSourceStr);
                    dataTip.put(dataIndexStr, list);
                }
            }
        }
        List<Object> objectList = new ArrayList<>();
        for (Object dataObj : dataSourceList) {
            JSONObject newJsonObject = new JSONObject();
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(dataObj));
            if (dataTip.isEmpty()) {
                objectList.add(jsonObject);
            } else {
                for (String dataIndex : dataTip.keySet()) {
                    if (jsonObject.get(dataIndex) != null) {
                        if ("dataBase".equals(dataTip.get(dataIndex).get(0))){
                            Object data = queryApiService.findSqlById(dataTip.get(dataIndex).get(1), jsonObject.getString(dataIndex));
                            JSONObject jsonData = JSONObject.parseObject(JSONObject.toJSONString(data));
                            JSONObject jsonObject1 = jsonData.getJSONObject("data");
                            String name = jsonObject1.getString("name");
                            jsonObject.put(dataIndex, name);
                            newJsonObject = jsonObject;
                        }else if ("dictionary".equals(dataTip.get(dataIndex).get(0))){
                            DictionaryDTO data = dictionaryApiService
                                    .findDictionaryByCatalogValueAndDictionaryKey(dataTip.get(dataIndex).get(1),jsonObject.getString(dataIndex));
                            jsonObject.put(dataIndex, data.getDictionaryValue());
                            newJsonObject = jsonObject;
                        }

                    }
                }
                objectList.add(newJsonObject);
            }
        }
        return JSONObject.parse(JSONObject.toJSONString(objectList));
    }
}
