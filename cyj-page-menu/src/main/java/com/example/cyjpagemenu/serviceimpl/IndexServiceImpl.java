package com.example.cyjpagemenu.serviceimpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.cyjpagemenu.api.DictionaryApiService;
import com.example.cyjpagemenu.api.QueryApiService;
import com.example.cyjpagemenu.dao.DataFormDao;
import com.example.cyjpagemenu.dao.DataTableDao;
import com.example.cyjpagemenu.entity.DataFormItemPO;
import com.example.cyjpagemenu.entity.DataFormPO;
import com.example.cyjpagemenu.entity.DataTableItemPO;
import com.example.cyjpagemenu.entity.DataTablePO;
import com.example.cyjpagemenu.entity.QDataFormItemPO;
import com.example.cyjpagemenu.entity.QDataFormPO;
import com.example.cyjpagemenu.entity.QDataTableItemPO;
import com.example.cyjpagemenu.entity.QDataTablePO;
import com.example.cyjpagemenu.entity.dto.DictionaryDTO;
import com.example.cyjpagemenu.entity.vo.DataSourceVO;
import com.example.cyjpagemenu.service.IndexService;
import com.example.cyjpagemenu.utils.BeanUtils;
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


    private DataFormServiceImpl dataFormService;
    private DataFormItemServiceImpl dataFormItemService;
    private DataTableServiceImpl dataTableService;
    private DataTableItemServiceImpl dataTableItemService;
    private QueryApiService queryApiService;
    private DictionaryApiService dictionaryApiService;
    private DataTableDao dataTableDao;
    private DataFormDao dataFormDao;

    @Autowired
    public void setDataTableDao(DataTableDao dataTableDao) {
        this.dataTableDao = dataTableDao;
    }

    @Autowired
    public void setDataFormDao(DataFormDao dataFormDao) {
        this.dataFormDao = dataFormDao;
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
    public DataFormPO findFormByName(String name) {
        if (dataFormDao.findDataFormPOByDataFormName(name).isPresent()) {
            return dataFormDao.findDataFormPOByDataFormName(name).get();
        }
        return null;
    }

    @Override
    public DataTablePO findTableByName(String name) {
        if (dataTableDao.findDataTablePOByDataTableName(name).isPresent()) {
            return dataTableDao.findDataTablePOByDataTableName(name).get();
        }
        return null;
    }

    @Override
    public void formAndTableGenerate(String name, JSONArray jsonArray) {
        DataTablePO dataTablePO = findTableByName(name + "Table");
        if (dataTablePO == null){
            dataTablePO = new DataTablePO();
            dataTablePO.setDataTableName(name + "Table");
            dataTableService.addOne(dataTablePO);
        }
        DataFormPO dataFormPO = findFormByName(name + "Form");
        if (dataFormPO == null){
            dataFormPO = new DataFormPO();
            dataFormPO.setDataFormName(name + "Form");
            dataFormService.addOne(dataFormPO);
        }
        dataFormService.deleteAll(dataFormPO.getId());
        dataTableService.deleteAll(dataTablePO.getId());
        List<JSONObject> objectList = JSONObject.parseArray(jsonArray.toString(), JSONObject.class);
        for (JSONObject object : objectList) {
            System.out.println(object);
            if (!("id".equals(object.getString("entityName")) || "sort_code".equals(object.getString("entityName")))) {
                DataFormItemPO dataFormItemPO = new DataFormItemPO();
                dataFormItemPO.setJsonData("{\"label\":\"" + object.getString("description") +
                        "\",\"required\":\"false\",\"name\":\"" +
                        BeanUtils.underline2Camel(object.getString("entityName")) + "\",\"type\":\"Input\"}");
                dataFormItemPO.setSortCode(object.getString("sortCode"));
                dataFormItemPO.setPid(dataFormPO.getId());
                dataFormItemService.addOne(dataFormItemPO);
                DataTableItemPO dataTableItemPO = new DataTableItemPO();
                dataTableItemPO.setJsonData("{\"title\":\"" + object.getString("description") + "\",\"dataIndex\":\"" +
                        BeanUtils.underline2Camel(object.getString("entityName")) + "\"}");
                dataTableItemPO.setSortCode(object.getString("sortCode"));
                dataTableItemPO.setPid(dataTablePO.getId());
                dataTableItemService.addOne(dataTableItemPO);
            }
        }
    }

    @Override
    public Object transformData(DataSourceVO dataSourceVO) {
        JSONObject jsonObjectRet = new JSONObject();
        Object dataSource = dataSourceVO.getDataSource();
        Object dataTableItem = dataSourceVO.getDataTableItemList();
        Object dataFormItem = dataSourceVO.getDataFormItemList();
        List<DataTableItemPO> dataTableItemPOList = JSONArray
                .parseArray(JSONObject.toJSONString(dataTableItem, SerializerFeature.WriteMapNullValue), DataTableItemPO.class);
        List<DataFormItemPO> dataFormItemPOList = JSONArray
                .parseArray(JSONObject.toJSONString(dataFormItem, SerializerFeature.WriteMapNullValue), DataFormItemPO.class);
        if (dataSource != null) {
            List<Object> dataSourceList = JSONArray.parseArray(JSONObject.toJSONString(dataSource, SerializerFeature.WriteMapNullValue));
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
                    } else if ("dictionary".equals(dataSourceType)) {
                        String dataSourceStr = jsonData.getString("dataSource");
                        String dataIndexStr = jsonData.getString("dataIndex");
                        list.add("dictionary");
                        list.add(dataSourceStr);
                        dataTip.put(dataIndexStr, list);
                    }
                }
            }
            if (dataFormItemPOList != null) {
                List<Object> objectForm = new ArrayList<>();
                for (DataFormItemPO dataFormItemPO : dataFormItemPOList) {
                    JSONObject jsonData = JSONObject.parseObject(dataFormItemPO.getJsonData());
                    String dataSourceType = jsonData.getString("dataSourceType");
                    if (dataSourceType != null) {
                        if ("dataBase".equals(dataSourceType)) {
                            String dataSourceStr = jsonData.getString("dataSource");
                            Object data = queryApiService.FindAllSql(dataSourceStr);
                            JSONObject dataJson = JSONObject.parseObject(JSONObject.toJSONString(data, SerializerFeature.WriteMapNullValue));
                            Object dataItem = dataJson.get("data");
                            List<Object> dataList = JSONArray.parseArray(JSONObject.toJSONString(dataItem, SerializerFeature.WriteMapNullValue));
                            List<Object> objects = new ArrayList<>();
                            for (Object o : dataList) {
                                JSONObject object = JSONObject.parseObject(JSONObject.toJSONString(o, SerializerFeature.WriteMapNullValue));
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("label", object.getString("name"));
                                jsonObject.put("value", object.getString("id"));
                                Object o1 = JSONObject.parse(JSONObject.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue));
                                objects.add(o1);
                            }
                            JSONArray jsonArray = JSONArray.parseArray(JSONObject.toJSONString(objects, SerializerFeature.WriteMapNullValue));
                            jsonData.put("dataSource", jsonArray);
                        } else if ("dictionary".equals(dataSourceType)) {
                            String dataSourceStr = jsonData.getString("dataSource");
                            List<DictionaryDTO> dictionaryDTOS = dictionaryApiService.findCatalogByValue(dataSourceStr);
                            List<Object> objects = new ArrayList<>();
                            for (DictionaryDTO o : dictionaryDTOS) {
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("label", o.getDictionaryName());
                                jsonObject.put("value", o.getDictionaryName());
                                Object o1 = JSONObject.parse(JSONObject.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue));
                                objects.add(o1);
                            }
                            JSONArray jsonArray = JSONArray.parseArray(JSONObject.toJSONString(objects, SerializerFeature.WriteMapNullValue));
                            jsonData.put("dataSource", jsonArray);
                        }
                    }
                    jsonData.put("id", dataFormItemPO.getId());
                    Object o = JSONObject.parse(JSONObject.toJSONString(jsonData, SerializerFeature.WriteMapNullValue));
                    objectForm.add(o);
                }
                jsonObjectRet.put("objectForm", JSONObject.parse(JSONObject.toJSONString(objectForm, SerializerFeature.WriteMapNullValue)));
            }
            List<Object> objectList = new ArrayList<>();
            for (Object dataObj : dataSourceList) {
                JSONObject newJsonObject = new JSONObject();
                JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(dataObj, SerializerFeature.WriteMapNullValue));
                if (dataTip.isEmpty()) {
                    objectList.add(jsonObject);
                } else {
                    for (String dataIndex : dataTip.keySet()) {
                        if (jsonObject.get(dataIndex) != null) {
                            if ("dataBase".equals(dataTip.get(dataIndex).get(0))) {
                                Object data = queryApiService.findSqlById(dataTip.get(dataIndex).get(1), jsonObject.getString(dataIndex));
                                JSONObject jsonData = JSONObject.parseObject(JSONObject.toJSONString(data, SerializerFeature.WriteMapNullValue));
                                JSONObject jsonObject1 = jsonData.getJSONObject("data");
                                String name = jsonObject1.getString("name");
                                jsonObject.put(dataIndex, name);
                                newJsonObject = jsonObject;
                            } else if ("dictionary".equals(dataTip.get(dataIndex).get(0))) {
                                DictionaryDTO data = dictionaryApiService
                                        .findDictionaryByCatalogValueAndDictionaryKey(dataTip.get(dataIndex).get(1), jsonObject.getString(dataIndex));
                                jsonObject.put(dataIndex, data.getDictionaryValue());
                                newJsonObject = jsonObject;
                            }
                            objectList.add(newJsonObject);
                        } else {
                            objectList.add(jsonObject);
                        }
                    }
                }
            }
            jsonObjectRet.put("objectList", JSONObject.parse(JSONObject.toJSONString(objectList, SerializerFeature.WriteMapNullValue)));
        }
        return jsonObjectRet;
    }
}
