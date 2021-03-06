package com.example.cyjentitycreater.serviceimpl;

import com.example.cyjentitycreater.entity.AppServicePO;
import com.example.cyjentitycreater.entity.EntityNamePO;
import com.example.cyjentitycreater.service.AppServiceService;
import com.example.cyjentitycreater.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Component
public class ComponentServiceImpl extends BaseService {

    private EntityNameServiceImpl entityNameService;
    private AppServiceService appServiceService;

    @Autowired
    public void setAppServiceService(AppServiceService appServiceService) {
        this.appServiceService = appServiceService;
    }

    @Autowired
    public void setEntityNameService(EntityNameServiceImpl entityNameService) {
        this.entityNameService = entityNameService;
    }

    public void createComponentFile(String pagePath, EntityNamePO po, String[] choose) throws IOException {
        AppServicePO appServicePO = appServiceService.findOneById(po.getAppName());
        String componentName = BeanUtils.captureName(BeanUtils.underline2Camel(po.getName()));
        String componentPath = pagePath + "/" + componentName;
        for (String cho : choose) {
            if ("model".equals(cho)) {
                createJavaFile(componentPath + "/models");
                createJavaFile(componentPath + "/models", createModelsJsx(po, null));
            } else if ("service".equals(cho)) {
                createJavaFile(componentPath + "/services");
                createJavaFile(componentPath + "/services", createServiceJsx(po, appServicePO, null));
            }
            if (po.getRelEntity() != null && !"".equals(po.getRelEntity())) {
                String str = po.getRelEntity().substring(po.getRelEntity().indexOf("[") + 1, po.getRelEntity().indexOf("]"));
                String[] relEntities = str.split(",");
                for (String relEntity : relEntities) {
                    EntityNamePO subPo = entityNameService.findOneById(relEntity);
                    AppServicePO subAppServicePO = appServiceService.findOneById(subPo.getAppName());
                    if ("model".equals(cho)) {
                        createJavaFile(componentPath + "/models", createModelsJsx(subPo, po.getName()));
                    } else if ("service".equals(cho)) {
                        createJavaFile(componentPath + "/services", createServiceJsx(subPo, subAppServicePO, po.getName()));
                    }
                }
                if ("index".equals(cho)) {
                    createJavaFile(componentPath, createIndexJsx(po, relEntities));
                    createJavaFile(componentPath, createIndexStore(po, relEntities));
                }
            } else {
                if ("index".equals(cho)) {
                    createJavaFile(componentPath, createIndexJsx(po, null));
                    createJavaFile(componentPath, createIndexStore(po, null));
                }
            }
            if ("index".equals(cho)) {
                createJavaFile(componentPath, createIndexCss());

            }
        }

    }


    private String[] createModelsJsx(EntityNamePO po, String entityName) {
        StringBuilder sb = new StringBuilder();
        String underComponentName = BeanUtils.underline2Camel(po.getName());
        String ComponentName = BeanUtils.captureName(BeanUtils.underline2Camel(po.getName()));
        sb.append("import ").append(underComponentName).append("Service from '../services/").append(underComponentName).append("';\r\n");
        sb.append("import initService from '../../../services/init';\r\n");
        sb.append("\r\n");
        sb.append("export default {\r\n");
        sb.append("\r\n");
        sb.append("  namespace: '").append(underComponentName).append("',\r\n");
        sb.append("\r\n");
        sb.append("  state: {\r\n");
        sb.append("    ").append(underComponentName).append("TableData: [],\r\n");
        sb.append("    ").append(underComponentName).append("Visible: false,\r\n");
        sb.append("    ").append(underComponentName).append("FormData: {},\r\n");
        sb.append("    ").append(underComponentName).append("LoadingVisible: true,\r\n");
        sb.append("    ").append(underComponentName).append("Total: 0,\r\n");
        sb.append("    ").append(underComponentName).append("Current: 1,\r\n");
        sb.append("    ").append(po.getFormModelCode()).append(": [],\r\n");
        sb.append("    ").append(po.getTableModelCode()).append(": [],\r\n");
        if (entityName != null) {
            sb.append("    ").append("divVisible: true,\r\n");
            sb.append("    ").append(BeanUtils.underline2Camel(entityName)).append("Id: '',\r\n");
        } else {
            sb.append("    // <=============================自定义状态 start =============================>\r\n");
            sb.append("    // <=============================自定义状态 end   =============================>\r\n");
        }
        sb.append("  },\r\n");
        sb.append("\r\n");
        sb.append("  reducers: {\r\n");
        sb.append("    setState(prevState, payload) {\r\n");
        sb.append("      return { ...prevState, ...payload };\r\n");
        sb.append("    },\r\n");
        sb.append("  },\r\n");
        sb.append("\r\n");
        sb.append("  effects: (dispatch) => ({\r\n");
        sb.append("    /**\r\n");
        sb.append("     * 数据\r\n");
        sb.append("     *\r\n");
        sb.append("     * @param {*} data\r\n");
        sb.append("     */\r\n");
        sb.append("    async ").append(underComponentName).append("Page(data) {\r\n");
        if (entityName != null) {
            sb.append("      const dataRes = await ").append(underComponentName).append("Service.").append(underComponentName).append("Page(data.").append(BeanUtils.underline2Camel(entityName)).append("Id, data.current);\r\n");
        } else {
            sb.append("      const dataRes = await ").append(underComponentName).append("Service.").append(underComponentName).append("Page(data.current);\r\n");
        }
        sb.append("      const ").append(underComponentName).append(" = await initService.transformData(dataRes.data.content, data.").append(underComponentName).append("Table);\r\n");
        sb.append("      const payload = {\r\n");
        sb.append("        ").append(underComponentName).append("Total: dataRes.data.totalElements,\r\n");
        sb.append("        ").append(underComponentName).append("TableData: ").append(underComponentName).append(".data.objectList,\r\n");
        sb.append("        ").append(underComponentName).append("Current: data.current,\r\n");
        sb.append("        ").append(underComponentName).append("LoadingVisible: false,\r\n");
        sb.append("      };\r\n");
        sb.append("      dispatch.").append(underComponentName).append(".setState(payload);\r\n");
        sb.append("    },\r\n");
        sb.append("    /**\r\n");
        sb.append("     * 编辑\r\n");
        sb.append("     *\r\n");
        sb.append("     * @param {*} data\r\n");
        sb.append("     */\r\n");
        sb.append("    async ").append(underComponentName).append("Edit(data) {\r\n");
        sb.append("      if (data) {\r\n");
        sb.append("        const ").append(underComponentName).append(" = await ").append(underComponentName).append("Service.find").append(ComponentName).append("ById(data.id);\r\n");
        sb.append("        const fromData = {\r\n");
        sb.append("          ...").append(underComponentName).append(".data,\r\n");
        sb.append("        };\r\n");
        sb.append("        const payload = {\r\n");
        sb.append("          ").append(underComponentName).append("FormData: fromData,\r\n");
        sb.append("          ").append(underComponentName).append("Visible: true,\r\n");
        sb.append("        };\r\n");
        sb.append("        dispatch.").append(underComponentName).append(".setState(payload);\r\n");
        sb.append("      } else {\r\n");
        sb.append("        const payload = {\r\n");
        sb.append("          ").append(underComponentName).append("FormData: {},\r\n");
        sb.append("          ").append(underComponentName).append("Visible: true,\r\n");
        sb.append("        };\r\n");
        sb.append("        dispatch.").append(underComponentName).append(".setState(payload);\r\n");
        sb.append("      }\r\n");
        sb.append("    },\r\n");
        sb.append("    /**\r\n");
        sb.append("     * 删除\r\n");
        sb.append("     *\r\n");
        sb.append("     * @param {*} data\r\n");
        sb.append("     */\r\n");
        sb.append("    async ").append(underComponentName).append("Delete(data) {\r\n");
        sb.append("      await ").append(underComponentName).append("Service.").append(underComponentName).append("Delete(data.record);\r\n");
        if (entityName != null) {
            sb.append("      const dataRes = await ").append(underComponentName).append("Service.").append(underComponentName).append("Page(data.record.id, data.").append(underComponentName).append("Current);\r\n");
        } else {
            sb.append("      const dataRes = await ").append(underComponentName).append("Service.").append(underComponentName).append("Page(data.").append(underComponentName).append("Current);\r\n");
        }
        sb.append("      const ").append(underComponentName).append(" = await initService.transformData(dataRes.data.content, data.").append(underComponentName).append("Table);\r\n");
        sb.append("      const payload = {\r\n");
        sb.append("        ").append(underComponentName).append("Total: dataRes.data.totalElements,\r\n");
        sb.append("        ").append(underComponentName).append("TableData: ").append(underComponentName).append(".data.objectList,\r\n");
        sb.append("        ").append(underComponentName).append("Current: data.").append(underComponentName).append("Current,\r\n");
        sb.append("      };\r\n");
        sb.append("      dispatch.").append(underComponentName).append(".setState(payload);\r\n");
        sb.append("    },\r\n");
        sb.append("    /**\r\n");
        sb.append("     * 保存\r\n");
        sb.append("     *\r\n");
        sb.append("     * @param {*} data\r\n");
        sb.append("     */\r\n");
        sb.append("    async ").append(underComponentName).append("Save(data) {\r\n");
        if (entityName != null) {
            sb.append("      await ").append(underComponentName).append("Service.").append(underComponentName).append("Save(data.").append(BeanUtils.underline2Camel(entityName)).append("Id, data.").append(underComponentName).append("FormData);\r\n");
            sb.append("      const dataRes = await ").append(underComponentName).append("Service.").append(underComponentName).append("Page(data.").append(BeanUtils.underline2Camel(entityName)).append("Id, data.").append(underComponentName).append("Current);\r\n");
        } else {
            sb.append("      await ").append(underComponentName).append("Service.").append(underComponentName).append("Save(data.").append(underComponentName).append("FormData);\r\n");
            sb.append("      const dataRes = await ").append(underComponentName).append("Service.").append(underComponentName).append("Page(data.").append(underComponentName).append("Current);\r\n");
        }
        sb.append("      const ").append(underComponentName).append(" = await initService.transformData(dataRes.data.content, data.").append(underComponentName).append("Table);\r\n");
        sb.append("      const payload = {\r\n");
        sb.append("        ").append(underComponentName).append("Total: dataRes.data.totalElements,\r\n");
        sb.append("        ").append(underComponentName).append("TableData: ").append(underComponentName).append(".data.objectList,\r\n");
        sb.append("        ").append(underComponentName).append("Current: data.").append(underComponentName).append("Current,\r\n");
        sb.append("        ").append(underComponentName).append("Visible: false,\r\n");
        sb.append("      };\r\n");
        sb.append("      dispatch.").append(underComponentName).append(".setState(payload);\r\n");
        sb.append("    },\r\n");
        sb.append("    /**\r\n");
        sb.append("     * 获取字典\r\n");
        sb.append("     *\r\n");
        sb.append("     * @param {*} data\r\n");
        sb.append("     */\r\n");
        sb.append("    findCatalogByValue(data) {\r\n");
        sb.append("      initService.findCatalogByValue(data).then(res => {\r\n");
        sb.append("        const formArr = [];\r\n");
        sb.append("        res.forEach(item => {\r\n");
        sb.append("          formArr.push({\r\n");
        sb.append("            label: item.dictionaryName,\r\n");
        sb.append("            value: item.dictionaryValue,\r\n");
        sb.append("          });\r\n");
        sb.append("        });\r\n");
        sb.append("        const payload = JSON.parse(JSON.stringify({\r\n");
        sb.append("          data: formArr,\r\n");
        sb.append("        }).replace(/data/g, data));\r\n");
        sb.append("        dispatch.").append(underComponentName).append(".setState(payload);\r\n");
        sb.append("      });\r\n");
        sb.append("    },\r\n");
        sb.append("    /**\r\n");
        sb.append("     * 设置表单数据\r\n");
        sb.append("     *\r\n");
        sb.append("     * @param {*} data\r\n");
        sb.append("     */\r\n");
        sb.append("    setDataForm(data) {\r\n");
        sb.append("      const payload = {\r\n");
        sb.append("        ").append(underComponentName).append("FormData: data,\r\n");
        sb.append("      };\r\n");
        sb.append("      dispatch.").append(underComponentName).append(".setState(payload);\r\n");
        sb.append("    },\r\n");
        if (entityName != null) {
            sb.append("    /**\r\n");
            sb.append("     * 点击行\r\n");
            sb.append("     *\r\n");
            sb.append("     * @param {*} data\r\n");
            sb.append("     */\r\n");
            sb.append("    async onRowClick(value) {\r\n");
            sb.append("      const dataRes = await ").append(underComponentName).append("Service.").append(underComponentName).append("Page(value.record.id, 1);\r\n");
            sb.append("      const dataTableRes = await initService.findDataTableByName('").append(po.getTableModelCode()).append("');\r\n");
            sb.append("      const dataFormRes = await initService.findDataFormByName('").append(po.getFormModelCode()).append("');\r\n");
            sb.append("      const data = await initService.transformData(dataRes.data.content, dataTableRes.data, dataFormRes.data);\r\n");
            sb.append("      const payload = {\r\n");
            sb.append("        divVisible: !value.selected,\r\n");
            sb.append("        ").append(underComponentName).append("Table: dataTableRes.data,\r\n");
            sb.append("        ").append(underComponentName).append("Form: data.data.objectForm,\r\n");
            sb.append("        ").append(underComponentName).append("Total: dataRes.data.totalElements,\r\n");
            sb.append("        ").append(underComponentName).append("TableData: data.data.objectList,\r\n");
            sb.append("        ").append(underComponentName).append("Current: 1,\r\n");
            sb.append("        ").append(BeanUtils.underline2Camel(entityName)).append("Id: value.record.id,\r\n");
            sb.append("        ").append(underComponentName).append("LoadingVisible: false,\r\n");
            sb.append("      };\r\n");
            sb.append("      dispatch.").append(underComponentName).append(".setState(payload);\r\n");
            sb.append("    },\r\n");
        } else {
            sb.append("    /**\r\n");
            sb.append("     * 获取表格和表格初始化数据\r\n");
            sb.append("     *\r\n");
            sb.append("     * @param {*} data\r\n");
            sb.append("     */\r\n");
            sb.append("    async findDataTableAndFormByName() {\r\n");
            sb.append("      const dataRes = await ").append(underComponentName).append("Service.").append(underComponentName).append("Page(1);\r\n");
            sb.append("      const dataTableRes = await initService.findDataTableByName('").append(po.getTableModelCode()).append("');\r\n");
            sb.append("      const dataFormRes = await initService.findDataFormByName('").append(po.getFormModelCode()).append("');\r\n");
            sb.append("      const data = await initService.transformData(dataRes.data.content, dataTableRes.data, dataFormRes.data);\r\n");
            sb.append("      const payload = {\r\n");
            sb.append("        ").append(underComponentName).append("Table: dataTableRes.data,\r\n");
            sb.append("        ").append(underComponentName).append("Form: data.data.objectForm,\r\n");
            sb.append("        ").append(underComponentName).append("Total: dataRes.data.totalElements,\r\n");
            sb.append("        ").append(underComponentName).append("TableData: data.data.objectList,\r\n");
            sb.append("        ").append(underComponentName).append("Current: 1,\r\n");
            sb.append("        ").append(underComponentName).append("LoadingVisible: false,\r\n");
            sb.append("      };\r\n");
            sb.append("      dispatch.").append(underComponentName).append(".setState(payload);\r\n");
            sb.append("    },\r\n");
        }
        sb.append("    // <=============================自定义方法 start =============================>\r\n");
        sb.append("    // <=============================自定义方法 end   =============================>\r\n");
        sb.append("  }),\r\n");
        sb.append("};");
        String modelData = sb.toString();
        return new String[]{modelData, underComponentName + ".jsx"};
    }

    private String[] createServiceJsx(EntityNamePO po, AppServicePO appServicePO, String entityName) {
        StringBuilder sb = new StringBuilder();
        String underComponentName = BeanUtils.underline2Camel(po.getName());
        String ComponentName = BeanUtils.captureName(BeanUtils.underline2Camel(po.getName()));
        sb.append("import { request } from 'ice';\r\n");
        sb.append("\r\n");
        sb.append("export default {\r\n");
        sb.append("  /**\r\n");
        sb.append("   * 数据\r\n");
        sb.append("   *\r\n");
        if (entityName != null) {
            sb.append("   * @param {*} id\r\n");
        }
        sb.append("   * @param {*} value\r\n");
        sb.append("   * @return {*} \r\n");
        sb.append("   */\r\n");
        if (entityName != null) {
            sb.append("  ").append(underComponentName).append("Page(id, value) {\r\n");
        } else {
            sb.append("  ").append(underComponentName).append("Page(value) {\r\n");
        }
        sb.append("    return request({\r\n");
        sb.append("      url: '/").append(appServicePO.getAppApi()).append("/").append(underComponentName).append("Page',\r\n");
        sb.append("      method: 'post',\r\n");
        sb.append("      params: {\r\n");
        if (entityName != null) {
            sb.append("        id,\r\n");
        }
        sb.append("        pageNumber: value,\r\n");
        sb.append("        pageSize: 13,\r\n");
        sb.append("        sortCode: 'sortCode',\r\n");
        sb.append("      },\r\n");
        sb.append("    });\r\n");
        sb.append("  },\r\n");
        sb.append("  /**\r\n");
        sb.append("   * 保存\r\n");
        sb.append("   *\r\n");
        sb.append("   * @param {*} ").append(underComponentName).append("FormData\r\n");
        if (entityName != null) {
            sb.append("   * @param {*} ").append(BeanUtils.underline2Camel(entityName)).append("Id\r\n");
        }
        sb.append("   * @return {*} \r\n");
        sb.append("   */\r\n");
        if (entityName != null) {
            sb.append("  ").append(underComponentName).append("Save(").append(BeanUtils.underline2Camel(entityName)).append("Id, ").append(underComponentName).append("FormData) {\r\n");
        } else {
            sb.append("  ").append(underComponentName).append("Save(data) {\r\n");
        }
        sb.append("    return request({\r\n");
        sb.append("      url: '/").append(appServicePO.getAppApi()).append("/").append(underComponentName).append("Save',\r\n");
        sb.append("      method: 'post',\r\n");
        if (entityName != null) {
            sb.append("      data: { ...").append(underComponentName).append("FormData, pid: ").append(BeanUtils.underline2Camel(entityName)).append("Id },\r\n");
        } else {
            sb.append("      data,\r\n");
        }
        sb.append("    });\r\n");
        sb.append("  },\r\n");
        sb.append("  /**\r\n");
        sb.append("   * 删除\r\n");
        sb.append("   *\r\n");
        sb.append("   * @param {*} record\r\n");
        sb.append("   * @return {*} \r\n");
        sb.append("   */\r\n");
        sb.append("  ").append(underComponentName).append("Delete(record) {\r\n");
        sb.append("    return request({\r\n");
        sb.append("      url: '/").append(appServicePO.getAppApi()).append("/").append(underComponentName).append("Delete',\r\n");
        sb.append("      method: 'post',\r\n");
        sb.append("      params: {\r\n");
        sb.append("        id: record.id,\r\n");
        sb.append("      },\r\n");
        sb.append("    });\r\n");
        sb.append("  },\r\n");
        sb.append("  /**\r\n");
        sb.append("   * 根据ID查询\r\n");
        sb.append("   * \r\n");
        sb.append("   * @param {*} id\r\n");
        sb.append("   * @return {*} \r\n");
        sb.append("   */\r\n");
        sb.append("  find").append(ComponentName).append("ById(id) {\r\n");
        sb.append("    return request({\r\n");
        sb.append("      url: '/").append(appServicePO.getAppApi()).append("/find").append(ComponentName).append("ById',\r\n");
        sb.append("      method: 'post',\r\n");
        sb.append("      params: {\r\n");
        sb.append("        id,\r\n");
        sb.append("      },\r\n");
        sb.append("    });\r\n");
        sb.append("  },\r\n");
        sb.append("  // <=============================自定义请求 start =============================>\r\n");
        sb.append("  // <=============================自定义请求 end   =============================>\r\n");
        sb.append("};");
        String serviceData = sb.toString();
        return new String[]{serviceData, underComponentName + ".jsx"};
    }

    private String[] createIndexJsx(EntityNamePO po, String[] relEntities) {
        StringBuilder sb = new StringBuilder();
        String underComponentName = BeanUtils.underline2Camel(po.getName());
        sb.append("import { ResponsiveGrid, Button, Dialog, Loading } from '@alifd/next';\r\n");
        sb.append("import React, { useEffect } from 'react';\r\n");
        sb.append("import pageStore from '@/pages/").append(BeanUtils.captureName(underComponentName)).append("/store';\r\n");
        sb.append("import DataFormTemple from '@/components/dataForm';\r\n");
        sb.append("import DataTableTemple from '@/components/dataTable';\r\n");
        sb.append("import styles from './index.module.scss';\r\n");
        sb.append("\r\n");
        sb.append("const { Cell } = ResponsiveGrid;\r\n");
        sb.append("\r\n");
        sb.append("function ").append(BeanUtils.captureName(underComponentName)).append("Page() {\r\n");
        sb.append("  const [").append(underComponentName).append("State, ").append(underComponentName).append("Dispatchers] = pageStore.useModel('").append(underComponentName).append("');\r\n");
        sb.append("  const ").append(underComponentName).append(" = pageStore.useModelDispatchers('").append(underComponentName).append("');\r\n");
        sb.append("\r\n");
        if (relEntities != null) {
            for (String relEntity : relEntities) {
                EntityNamePO relPo = entityNameService.findOneById(relEntity);
                String underRelEntity = BeanUtils.underline2Camel(relPo.getName());
                sb.append("  const [").append(underRelEntity).append("State, ").append(underRelEntity).append("Dispatchers] = pageStore.useModel('").append(underRelEntity).append("');\r\n");
                sb.append("  const ").append(underRelEntity).append(" = pageStore.useModelDispatchers('").append(underRelEntity).append("');\r\n");
                sb.append("\r\n");
            }
        }
        sb.append("  useEffect(() => {\r\n");
        sb.append("    ").append(underComponentName).append("Dispatchers.findDataTableAndFormByName();\r\n");
        sb.append("  }, [").append(underComponentName).append("Dispatchers]);\r\n");
        sb.append("\r\n");
        sb.append("  const ").append(underComponentName).append("PageRender = (value, index, record) => {\r\n");
        sb.append("    return <div className={ styles.opt }>\r\n");
        sb.append("      <Button type=\"primary\" size=\"small\" onClick={ () => ").append(underComponentName).append("Dispatchers.").append(underComponentName).append("Edit(record) }> 编辑 </Button>\r\n");
        sb.append("      <Button type=\"primary\" size=\"small\" onClick={ () => ").append(underComponentName).append("Dispatchers.").append(underComponentName).append("Delete({\r\n");
        sb.append("        record,\r\n");
        sb.append("        ").append(underComponentName).append("Current: ").append(underComponentName).append("State.").append(underComponentName).append("Current,\r\n");
        sb.append("        ").append(underComponentName).append("Table: ").append(underComponentName).append("State.").append(underComponentName).append("Table,\r\n");
        sb.append("      }) } warning> 删除 </Button>\r\n");
        sb.append("    </div>;\r\n");
        sb.append("  };\r\n");
        sb.append("\r\n");
        if (relEntities != null) {
            for (String relEntity : relEntities) {
                EntityNamePO relPo = entityNameService.findOneById(relEntity);
                String underRelEntity = BeanUtils.underline2Camel(relPo.getName());
                sb.append("  const ").append(underRelEntity).append("PageRender = (value, index, record) => {\r\n");
                sb.append("    return <div className={ styles.opt }>\r\n");
                sb.append("      <Button type=\"primary\" size=\"small\" onClick={ () => ").append(underRelEntity).append("Dispatchers.").append(underRelEntity).append("Edit(record) }> 编辑 </Button>\r\n");
                sb.append("      <Button type=\"primary\" size=\"small\" onClick={ () => ").append(underRelEntity).append("Dispatchers.").append(underRelEntity).append("Delete({\r\n");
                sb.append("        record,\r\n");
                sb.append("        ").append(underRelEntity).append("Current: ").append(underRelEntity).append("State.").append(underRelEntity).append("Current,\r\n");
                sb.append("        ").append(underRelEntity).append("Table: ").append(underRelEntity).append("State.").append(underRelEntity).append("Table,\r\n");
                sb.append("      }) } warning> 删除 </Button>\r\n");
                sb.append("    </div>;\r\n");
                sb.append("  };\r\n");
                sb.append("\r\n");
            }
        }
        sb.append("  return (\r\n");
        sb.append("    <ResponsiveGrid gap={ 20 }>\r\n");
        sb.append("      <Cell colSpan={ 12 }>\r\n");
        sb.append("        <div className={ styles.Main }>\r\n");
        sb.append("          <div className={ styles.add }>\r\n");
        sb.append("            <Button type=\"primary\" onClick={ () => ").append(underComponentName).append("Dispatchers.").append(underComponentName).append("Edit() }> 添加菜单 </Button>\r\n");
        sb.append("            <Dialog title=\"菜单\" visible={ ").append(underComponentName).append("State.").append(underComponentName).append("Visible } footer={ false }\r\n");
        sb.append("              onClose={ () => ").append(underComponentName).append(".setState({ ").append(underComponentName).append("Visible: false }) }\r\n");
        sb.append("              style={ { width: '30%' } }>\r\n");
        sb.append("              <DataFormTemple\r\n");
        sb.append("                items={ ").append(underComponentName).append("State.").append(po.getFormModelCode()).append(" }\r\n");
        sb.append("                dispatchers={ value => ").append(underComponentName).append("Dispatchers.setDataForm(value) }\r\n");
        sb.append("                onOk={ () => ").append(underComponentName).append("Dispatchers.").append(underComponentName).append("Save({\r\n");
        sb.append("                  ").append(underComponentName).append("FormData: ").append(underComponentName).append("State.").append(underComponentName).append("FormData,\r\n");
        sb.append("                  ").append(underComponentName).append("Current: ").append(underComponentName).append("State.").append(underComponentName).append("Current,\r\n");
        sb.append("                  ").append(underComponentName).append("Table: ").append(underComponentName).append("State.").append(underComponentName).append("Table,\r\n");
        sb.append("                }) }\r\n");
        sb.append("                formDataValue={ ").append(underComponentName).append("State.").append(underComponentName).append("FormData } />\r\n");
        sb.append("            </Dialog>\r\n");
        sb.append("          </div>\r\n");
        sb.append("          <Loading tip=\"加载中...\" visible={ ").append(underComponentName).append("State.").append(underComponentName).append("LoadingVisible }>\r\n");
        sb.append("            <DataTableTemple\r\n");
        sb.append("              dataSource={ ").append(underComponentName).append("State.").append(underComponentName).append("TableData }\r\n");
        sb.append("              items={ ").append(underComponentName).append("State.").append(po.getTableModelCode()).append(" }\r\n");
        sb.append("              total={ ").append(underComponentName).append("State.").append(underComponentName).append("Total }\r\n");
        sb.append("              getPage={ current => ").append(underComponentName).append("Dispatchers.").append(underComponentName).append("Page({ current, ").append(underComponentName).append("Table: ").append(underComponentName).append("State.").append(underComponentName).append("Table, }) }\r\n");
        if (relEntities != null) {
            sb.append("              rowSelection={ {\r\n");
            sb.append("                mode: 'single',\r\n");
            sb.append("                onSelect: (selected, record) => {\r\n");
            for (String relEntity : relEntities) {
                EntityNamePO relPo = entityNameService.findOneById(relEntity);
                String underRelEntity = BeanUtils.underline2Camel(relPo.getName());
                sb.append("                  ").append(underRelEntity).append("Dispatchers.onRowClick({ selected, record });\r\n");
            }
            sb.append("                },\r\n");
            sb.append("              } }\r\n");
        }
        sb.append("              pageRender={ ").append(underComponentName).append("PageRender } />\r\n");
        sb.append("          </Loading>\r\n");
        sb.append("        </div>\r\n");
        sb.append("      </Cell>\r\n");
        if (relEntities != null) {
            for (String relEntity : relEntities) {
                EntityNamePO relPo = entityNameService.findOneById(relEntity);
                String underRelEntity = BeanUtils.underline2Camel(relPo.getName());
                sb.append("      <Cell colSpan={ 12 } hidden={ ").append(underRelEntity).append("State.divVisible }>\r\n");
                sb.append("        <div className={ styles.Main }>\r\n");
                sb.append("          <div className={ styles.add }>\r\n");
                sb.append("            <Button type=\"primary\" onClick={ () => ").append(underRelEntity).append("Dispatchers.").append(underRelEntity).append("Edit() }> 添加菜单 </Button>\r\n");
                sb.append("            <Dialog title=\"菜单\" visible={ ").append(underRelEntity).append("State.").append(underRelEntity).append("Visible } footer={ false }\r\n");
                sb.append("              onClose={ () => ").append(underRelEntity).append(".setState({ ").append(underRelEntity).append("Visible: false }) }\r\n");
                sb.append("              style={ { width: '30%' } }>\r\n");
                sb.append("              <DataFormTemple\r\n");
                sb.append("                items={ ").append(underRelEntity).append("State.").append(relPo.getFormModelCode()).append(" }\r\n");
                sb.append("                dispatchers={ value => ").append(underRelEntity).append("Dispatchers.setDataForm(value) }\r\n");
                sb.append("                onOk={ () => ").append(underRelEntity).append("Dispatchers.").append(underRelEntity).append("Save({\r\n");
                sb.append("                  ").append(underRelEntity).append("FormData: ").append(underRelEntity).append("State.").append(underRelEntity).append("FormData,\r\n");
                sb.append("                  ").append(underRelEntity).append("Current: ").append(underRelEntity).append("State.").append(underRelEntity).append("Current,\r\n");
                sb.append("                  ").append(underRelEntity).append("Table: ").append(underRelEntity).append("State.").append(underRelEntity).append("Table,\r\n");
                sb.append("                  ").append(underComponentName).append("Id: ").append(underRelEntity).append("State.").append(underComponentName).append("Id,\r\n");
                sb.append("                }) }\r\n");
                sb.append("                formDataValue={ ").append(underRelEntity).append("State.").append(underRelEntity).append("FormData } />\r\n");
                sb.append("            </Dialog>\r\n");
                sb.append("          </div>\r\n");
                sb.append("          <Loading tip=\"加载中...\" visible={ ").append(underRelEntity).append("State.").append(underRelEntity).append("LoadingVisible }>\r\n");
                sb.append("            <DataTableTemple\r\n");
                sb.append("              dataSource={ ").append(underRelEntity).append("State.").append(underRelEntity).append("TableData }\r\n");
                sb.append("              items={ ").append(underRelEntity).append("State.").append(relPo.getTableModelCode()).append(" }\r\n");
                sb.append("              total={ ").append(underRelEntity).append("State.").append(underRelEntity).append("Total }\r\n");
                sb.append("              getPage={ current => ").append(underRelEntity).append("Dispatchers.").append(underRelEntity).append("Page({ ").append(underComponentName).append("Id: ").append(underRelEntity).append("State.").append(underComponentName).append("Id, current }) }\r\n");
                sb.append("              pageRender={ ").append(underRelEntity).append("PageRender } />\r\n");
                sb.append("          </Loading>\r\n");
                sb.append("        </div>\r\n");
                sb.append("      </Cell>\r\n");
            }
        }
        sb.append("    </ResponsiveGrid>\r\n");
        sb.append("  );\r\n");
        sb.append("}\r\n");
        sb.append("\r\n");
        sb.append("export default ").append(BeanUtils.captureName(underComponentName)).append("Page;");
        String indexData = sb.toString();
        return new String[]{indexData, "index.jsx"};
    }

    private String[] createIndexStore(EntityNamePO po, String[] relEntities) {
        StringBuilder sb = new StringBuilder();
        String underComponentName = BeanUtils.underline2Camel(po.getName());
        sb.append("import { createStore } from 'ice';\r\n");
        sb.append("import ").append(underComponentName).append(" from './models/").append(underComponentName).append("';\r\n");
        if (relEntities != null) {
            for (String relEntity : relEntities) {
                EntityNamePO relPo = entityNameService.findOneById(relEntity);
                String underRelEntity = BeanUtils.underline2Camel(relPo.getName());
                sb.append("import ").append(underRelEntity).append(" from './models/").append(underRelEntity).append("';\r\n");
            }
        }
        sb.append("\r\n");
        sb.append("const store = createStore({\r\n");
        sb.append("  ").append(underComponentName).append(",\r\n");
        if (relEntities != null) {
            for (String relEntity : relEntities) {
                EntityNamePO relPo = entityNameService.findOneById(relEntity);
                String underRelEntity = BeanUtils.underline2Camel(relPo.getName());
                sb.append("  ").append(underRelEntity).append(",\r\n");
            }
        }
        sb.append("});\r\n");
        sb.append("\r\n");
        sb.append("export default store;");
        String storeData = sb.toString();
        return new String[]{storeData, "store.js"};
    }

    private String[] createIndexCss() {
        String css = "@import \"~@alifd/next/variables.scss\";\r\n" +
                "\r\n" +
                ".icon {\r\n" +
                "  color: #999\r\n" +
                "}\r\n" +
                "\r\n" +
                ".searchIcon {\r\n" +
                "  margin-right: 5px;\r\n" +
                "  color: #999;\r\n" +
                "}\r\n" +
                "\r\n" +
                ".Operation {\r\n" +
                "  padding: 10px 10px 5px;\r\n" +
                "  border-bottom: 1px dashed #eee;\r\n" +
                "\r\n" +
                "  .btns {\r\n" +
                "    margin-top: 14px;\r\n" +
                "    button {\r\n" +
                "      margin-left: 10px;\r\n" +
                "    }\r\n" +
                "  }\r\n" +
                "}\r\n" +
                "\r\n" +
                ".Main {\r\n" +
                "  padding: 15px 10px;\r\n" +
                "  .add {\r\n" +
                "    padding-bottom: 15px;\r\n" +
                "    button {\r\n" +
                "      margin-right: 10px;\r\n" +
                "    }\r\n" +
                "  }\r\n" +
                "\r\n" +
                "  .opt {\r\n" +
                "    button {\r\n" +
                "      margin-right: 5px;\r\n" +
                "    }\r\n" +
                "  }\r\n" +
                "\r\n" +
                "  .total {\r\n" +
                "    color: #4A5B6D;\r\n" +
                "    font-size: 12px;\r\n" +
                "    span {\r\n" +
                "      padding:0 3px;\r\n" +
                "      color: #5584FF;\r\n" +
                "    }\r\n" +
                "  }\r\n" +
                "}";
        return new String[]{css, "index.module.scss"};
    }
}
