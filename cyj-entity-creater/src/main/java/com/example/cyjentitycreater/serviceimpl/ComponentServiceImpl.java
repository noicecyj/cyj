package com.example.cyjentitycreater.serviceimpl;

import com.example.cyjentitycreater.entity.EntityNamePO;
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

    @Autowired
    public void setEntityNameService(EntityNameServiceImpl entityNameService) {
        this.entityNameService = entityNameService;
    }

    public void createComponentFile(String pagePath, EntityNamePO po) throws IOException {
        String componentName = BeanUtils.captureName(BeanUtils.underline2Camel(po.getName()));
        String componentPath = pagePath + "/" + componentName;
        createJavaFile(componentPath + "/models");
        createJavaFile(componentPath + "/services");
        createJavaFile(componentPath + "/models", createModelsJsx(po, null));
        createJavaFile(componentPath + "/services", createServiceJsx(po, null));
        if (po.getRelEntity() != null && !"".equals(po.getRelEntity())) {
            String str = po.getRelEntity().substring(po.getRelEntity().indexOf("[") + 1, po.getRelEntity().indexOf("]"));
            String[] relEntities = str.split(",");
            for (String relEntity : relEntities) {
                EntityNamePO subPo = entityNameService.findOneById(relEntity);
                createJavaFile(componentPath + "/models", createModelsJsx(subPo, po.getName()));
                createJavaFile(componentPath + "/services", createServiceJsx(subPo, po.getName()));
            }
            createJavaFile(componentPath, createIndexJsx(po, relEntities));
        } else {
            createJavaFile(componentPath, createIndexJsx(po, null));
        }
        createJavaFile(componentPath, createIndexCss());
    }

    private String[] createModelsJsx(EntityNamePO po, String entityName) {
        StringBuilder sb = new StringBuilder();
        String underComponentName = BeanUtils.underline2Camel(po.getName());
        sb.append("import ").append(underComponentName).append("Service from '../services/").append(underComponentName).append("';\r\n");
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
        if (entityName != null) {
            sb.append("    ").append("divVisible: true,\r\n");
            sb.append("    ").append(BeanUtils.underline2Camel(entityName)).append("Id: '',\r\n");
        } else {
            sb.append("    // <=============================自定义状态 start =============================>\r\n");
            sb.append("    \r\n");
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
        sb.append("    ").append(underComponentName).append("Page(data) {\r\n");
        if (entityName != null) {
            sb.append("      ").append(underComponentName).append("Service.").append(underComponentName).append("Page(data.id, data.current).then(res => {\r\n");
        } else {
            sb.append("      ").append(underComponentName).append("Service.").append(underComponentName).append("Page(data).then(res => {\r\n");
        }
        sb.append("        const payload = {\r\n");
        sb.append("          ").append(underComponentName).append("Total: res.data.totalElements,\r\n");
        sb.append("          ").append(underComponentName).append("TableData: res.data.content,\r\n");
        if (entityName != null) {
            sb.append("          ").append(underComponentName).append("Current: data.current,\r\n");
        } else {
            sb.append("          ").append(underComponentName).append("Current: data,\r\n");
        }
        sb.append("          ").append(underComponentName).append("LoadingVisible: false,\r\n");
        sb.append("        };\r\n");
        sb.append("        dispatch.").append(underComponentName).append(".setState(payload);\r\n");
        sb.append("      });\r\n");
        sb.append("    },\r\n");
        sb.append("    /**\r\n");
        sb.append("     * 编辑\r\n");
        sb.append("     *\r\n");
        sb.append("     * @param {*} data\r\n");
        sb.append("     */\r\n");
        sb.append("    ").append(underComponentName).append("Edit(data) {\r\n");
        sb.append("      if (data) {\r\n");
        sb.append("        const fromData = {\r\n");
        sb.append("          ...data,\r\n");
        sb.append("        }\r\n");
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
        sb.append("    ").append(underComponentName).append("Delete(data) {\r\n");
        sb.append("      ").append(underComponentName).append("Service.").append(underComponentName).append("Delete(data.record).then(() => {\r\n");
        if (entityName != null) {
            sb.append("        ").append(underComponentName).append("Service.").append(underComponentName).append("Page(data.record.id, data.").append(underComponentName).append("Current).then(res => {\r\n");
        } else {
            sb.append("        ").append(underComponentName).append("Service.").append(underComponentName).append("Page(data.").append(underComponentName).append("Current).then(res => {\r\n");
        }
        sb.append("          const payload = {\r\n");
        sb.append("            ").append(underComponentName).append("Total: res.data.totalElements,\r\n");
        sb.append("            ").append(underComponentName).append("TableData: res.data.content,\r\n");
        sb.append("            ").append(underComponentName).append("Current: data.").append(underComponentName).append("Current,\r\n");
        sb.append("          };\r\n");
        sb.append("          dispatch.").append(underComponentName).append(".setState(payload);\r\n");
        sb.append("        });\r\n");
        sb.append("      });\r\n");
        sb.append("    },\r\n");
        sb.append("    /**\r\n");
        sb.append("     * 保存\r\n");
        sb.append("     *\r\n");
        sb.append("     * @param {*} data\r\n");
        sb.append("     */\r\n");
        sb.append("    ").append(underComponentName).append("Save(data) {\r\n");
        if (entityName != null) {
            sb.append("      ").append(underComponentName).append("Service.").append(underComponentName).append("Save(data.").append(underComponentName).append("FormData, data.").append(BeanUtils.underline2Camel(entityName)).append("Id).then(() => {\r\n");
            sb.append("        ").append(underComponentName).append("Service.").append(underComponentName).append("Page(data.").append(BeanUtils.underline2Camel(entityName)).append("Id, data.").append(underComponentName).append("Current).then(res => {\r\n");
        } else {
            sb.append("      ").append(underComponentName).append("Service.").append(underComponentName).append("Save(data.").append(underComponentName).append("FormData).then(() => {\r\n");
            sb.append("        ").append(underComponentName).append("Service.").append(underComponentName).append("Page(data.").append(underComponentName).append("Current).then(res => {\r\n");
        }
        sb.append("          const payload = {\r\n");
        sb.append("            ").append(underComponentName).append("Total: res.data.totalElements,\r\n");
        sb.append("            ").append(underComponentName).append("TableData: res.data.content,\r\n");
        sb.append("            ").append(underComponentName).append("Current: data.").append(underComponentName).append("Current,\r\n");
        sb.append("          };\r\n");
        sb.append("          dispatch.").append(underComponentName).append(".setState(payload);\r\n");
        sb.append("        });\r\n");
        sb.append("      });\r\n");
        sb.append("      const payload = { ").append(underComponentName).append("Visible: false };\r\n");
        sb.append("      dispatch.").append(underComponentName).append(".setState(payload);\r\n");
        sb.append("    },\r\n");
        sb.append("    /**\r\n");
        sb.append("     * 获取字典\r\n");
        sb.append("     *\r\n");
        sb.append("     * @param {*} data\r\n");
        sb.append("     */\r\n");
        sb.append("    findCatalogByValue(data) {\r\n");
        sb.append("      ").append(underComponentName).append("Service.findCatalogByValue(data).then(res => {\r\n");
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
        sb.append("    /**\r\n");
        sb.append("     * 获取表单\r\n");
        sb.append("     *\r\n");
        sb.append("     * @param {*} data\r\n");
        sb.append("     */\r\n");
        sb.append("    async findDataFormByName(data) {\r\n");
        sb.append("      const formArray = [];\r\n");
        sb.append("      const results = [];\r\n");
        sb.append("      const ").append(underComponentName).append("Res = await ").append(underComponentName).append("Service.findDataFormByName(data);\r\n");
        sb.append("      for (let i = 0; i < ").append(underComponentName).append("Res.data.length; i++) {\r\n");
        sb.append("        if (").append(underComponentName).append("Res.data[i].type === 'Select' && ").append(underComponentName).append("Res.data[i].dataSource !== null) {\r\n");
        sb.append("          results.push(").append(underComponentName).append("Service.findCatalogByValue(").append(underComponentName).append("Res.data[i].dataSource).then(res => {\r\n");
        sb.append("            const formArr = [];\r\n");
        sb.append("            res.forEach(item => {\r\n");
        sb.append("              formArr.push({\r\n");
        sb.append("                label: item.dictionaryName,\r\n");
        sb.append("                value: item.dictionaryValue,\r\n");
        sb.append("              });\r\n");
        sb.append("            });\r\n");
        sb.append("            formArray.push({ ...").append(underComponentName).append("Res.data[i], dataSource: formArr });\r\n");
        sb.append("          }));\r\n");
        sb.append("        } else {\r\n");
        sb.append("          formArray.push(").append(underComponentName).append("Res.data[i]);\r\n");
        sb.append("        }\r\n");
        sb.append("      }\r\n");
        sb.append("      await Promise.all(results);\r\n");
        sb.append("      const payload = {\r\n");
        sb.append("        ").append(underComponentName).append("DataForm: formArray,\r\n");
        sb.append("      };\r\n");
        sb.append("      dispatch.").append(underComponentName).append(".setState(payload);\r\n");
        sb.append("    },\r\n");
        sb.append("    /**\r\n");
        sb.append("     * 获取表格\r\n");
        sb.append("     *\r\n");
        sb.append("     * @param {*} data\r\n");
        sb.append("     */\r\n");
        sb.append("    findDataTableByName(data) {\r\n");
        sb.append("      ").append(underComponentName).append("Service.findDataTableByName(data).then(res => {\r\n");
        sb.append("        const payload = {\r\n");
        sb.append("          ").append(underComponentName).append("DataTable: res.data,\r\n");
        sb.append("        };\r\n");
        sb.append("        dispatch.").append(underComponentName).append(".setState(payload);\r\n");
        sb.append("      });\r\n");
        sb.append("    },\r\n");
        sb.append("    // <=============================可选方法 start =============================>\r\n");
        if (entityName != null) {
            sb.append("    /**\r\n");
            sb.append("     * 点击行\r\n");
            sb.append("     *\r\n");
            sb.append("     * @param {*} data\r\n");
            sb.append("     */\r\n");
            sb.append("    onRowClick(data) {\r\n");
            sb.append("      ").append(underComponentName).append("Service.").append(underComponentName).append("Page(data.record.id, 1).then(res => {\r\n");
            sb.append("        const payload = {\r\n");
            sb.append("          ").append("divVisible: !data.selected,\r\n");
            sb.append("          ").append(underComponentName).append("Total: res.data.totalElements,\r\n");
            sb.append("          ").append(underComponentName).append("TableData: res.data.content,\r\n");
            sb.append("          ").append(underComponentName).append("Current: 1,\r\n");
            sb.append("        };\r\n");
            sb.append("        dispatch.").append(underComponentName).append(".setState(payload);\r\n");
            sb.append("      });\r\n");
            sb.append("      const payload = {\r\n");
            sb.append("        ").append(BeanUtils.underline2Camel(entityName)).append("Id: data.record.id,\r\n");
            sb.append("        ").append(underComponentName).append("LoadingVisible: false,\r\n");
            sb.append("      };\r\n");
            sb.append("      dispatch.").append(underComponentName).append(".setState(payload);\r\n");
            sb.append("    },\r\n");
        }
        sb.append("    // <=============================可选方法 end   =============================>\r\n");
        sb.append("    // <=============================自定义方法 start =============================>\r\n");
        sb.append("\r\n");
        sb.append("    // <=============================自定义方法 end   =============================>\r\n");
        sb.append("  }),\r\n");
        sb.append("};");
        String modelData = sb.toString();
        return new String[]{modelData, underComponentName + ".jsx"};
    }

    private String[] createServiceJsx(EntityNamePO po, String entityName) {
        StringBuilder sb = new StringBuilder();
        String underComponentName = BeanUtils.underline2Camel(po.getName());
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
        sb.append("      url: '/").append(po.getApi()).append("/").append(underComponentName).append("Page',\r\n");
        sb.append("      method: 'post',\r\n");
        sb.append("      params: {\r\n");
        if (entityName != null) {
            sb.append("        id,\r\n");
        }
        sb.append("        pageNumber: value,\r\n");
        if (entityName != null) {
            sb.append("        pageSize: 5,\r\n");
        } else {
            sb.append("        pageSize: 13,\r\n");
        }
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
            sb.append("  ").append(underComponentName).append("Save(").append(underComponentName).append("FormData, ").append(BeanUtils.underline2Camel(entityName)).append("Id) {\r\n");
        } else {
            sb.append("  ").append(underComponentName).append("Save(data) {\r\n");
        }
        sb.append("    return request({\r\n");
        sb.append("      url: '/").append(po.getApi()).append("/").append(underComponentName).append("Save',\r\n");
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
        sb.append("      url: '/").append(po.getApi()).append("/").append(underComponentName).append("Delete',\r\n");
        sb.append("      method: 'post',\r\n");
        sb.append("      params: {\r\n");
        sb.append("        id: record.id,\r\n");
        sb.append("      },\r\n");
        sb.append("    });\r\n");
        sb.append("  },\r\n");
        sb.append("  /**\r\n");
        sb.append("   * 获取字典\r\n");
        sb.append("   *\r\n");
        sb.append("   * @param {*} value\r\n");
        sb.append("   * @return {*} \r\n");
        sb.append("   */\r\n");
        sb.append("  findCatalogByValue(value) {\r\n");
        sb.append("    return request({\r\n");
        sb.append("      url: '/dictionaryApi/findCatalogByValue',\r\n");
        sb.append("      method: 'post',\r\n");
        sb.append("      params: {\r\n");
        sb.append("        value,\r\n");
        sb.append("      },\r\n");
        sb.append("    });\r\n");
        sb.append("  },\r\n");
        sb.append("  // <=============================自定义请求 start =============================>\r\n");
        sb.append("\r\n");
        sb.append("  // <=============================自定义请求 end   =============================>\r\n");
        sb.append("};");
        String serviceData = sb.toString();
        return new String[]{serviceData, underComponentName + ".jsx"};
    }

    private String[] createIndexJsx(EntityNamePO po, String[] relEntities) {
        StringBuilder sb = new StringBuilder();
        String underComponentName = BeanUtils.underline2Camel(po.getName());
        sb.append("import { ResponsiveGrid, Button, Box, Dialog, Loading, Pagination } from '@alifd/next';\r\n");
        sb.append("import React, { useEffect } from 'react';\r\n");
        sb.append("import { store as pageStore } from 'ice/").append(BeanUtils.captureName(underComponentName)).append("';\r\n");
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
        sb.append("    ").append(underComponentName).append("Dispatchers.").append(underComponentName).append("Page(1);\r\n");
        sb.append("    ").append(underComponentName).append("Dispatchers.findDataFormByName('").append(po.getFormModelCode()).append("');\r\n");
        sb.append("    ").append(underComponentName).append("Dispatchers.findDataTableByName('").append(po.getTableModelCode()).append("');\r\n");
        if (relEntities != null) {
            for (String relEntity : relEntities) {
                EntityNamePO relPo = entityNameService.findOneById(relEntity);
                String underRelEntity = BeanUtils.underline2Camel(relPo.getName());
                sb.append("    ").append(underRelEntity).append("Dispatchers.findDataFormByName('").append(relPo.getFormModelCode()).append("');\r\n");
                sb.append("    ").append(underRelEntity).append("Dispatchers.findDataTableByName('").append(relPo.getTableModelCode()).append("');\r\n");
            }
        }
        sb.append("  }, [").append(underComponentName).append("Dispatchers");
        if (relEntities != null) {
            for (String relEntity : relEntities) {
                EntityNamePO relPo = entityNameService.findOneById(relEntity);
                String underRelEntity = BeanUtils.underline2Camel(relPo.getName());
                sb.append(", ").append(underRelEntity).append("Dispatchers");
            }
        }
        sb.append("]);\r\n");
        sb.append("\r\n");
        sb.append("  const ").append(underComponentName).append("PageRender = (value, index, record) => {\r\n");
        sb.append("    return <div className={ styles.opt }>\r\n");
        sb.append("      <Button type=\"primary\" size=\"small\" onClick={ () => ").append(underComponentName).append("Dispatchers.").append(underComponentName).append("Edit(record) }> 编辑 </Button>\r\n");
        sb.append("      <Button type=\"primary\" size=\"small\" onClick={ () => ").append(underComponentName).append("Dispatchers.").append(underComponentName).append("Delete({\r\n");
        sb.append("        record,\r\n");
        sb.append("        ").append(underComponentName).append("Current: ").append(underComponentName).append("State.").append(underComponentName).append("Current,\r\n");
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
        sb.append("            <Dialog title=\"菜单\" visible={ ").append(underComponentName).append("State.").append(underComponentName).append("Visible }\r\n");
        sb.append("              onOk={ () => ").append(underComponentName).append("Dispatchers.").append(underComponentName).append("Save({\r\n");
        sb.append("                ").append(underComponentName).append("FormData: ").append(underComponentName).append("State.").append(underComponentName).append("FormData,\r\n");
        sb.append("                ").append(underComponentName).append("Current: ").append(underComponentName).append("State.").append(underComponentName).append("Current,\r\n");
        sb.append("              }) }\r\n");
        sb.append("              onCancel={ () => ").append(underComponentName).append(".setState({ ").append(underComponentName).append("Visible: false }) }\r\n");
        sb.append("              onClose={ () => ").append(underComponentName).append(".setState({ ").append(underComponentName).append("Visible: false }) }\r\n");
        sb.append("              style={ { width: '30%' } }>\r\n");
        sb.append("              <DataFormTemple items={ ").append(underComponentName).append("State.").append(underComponentName).append("DataForm }\r\n");
        sb.append("                dispatchers={ value => ").append(underComponentName).append("Dispatchers.setDataForm(value) }\r\n");
        sb.append("                formDataValue={ ").append(underComponentName).append("State.").append(underComponentName).append("FormData } />\r\n");
        sb.append("            </Dialog>\r\n");
        sb.append("          </div>\r\n");
        sb.append("          <Loading tip=\"加载中...\" visible={ ").append(underComponentName).append("State.").append(underComponentName).append("LoadingVisible }>\r\n");
        sb.append("            <DataTableTemple dataSource={ ").append(underComponentName).append("State.").append(underComponentName).append("DataTable } pageRender={ ").append(underComponentName).append("PageRender } />\r\n");
        sb.append("            <Box margin={ [15, 0, 0, 0] } direction=\"row\" align=\"center\" justify=\"space-between\">\r\n");
        sb.append("              <div className={ styles.total }> 共 <span>{ ").append(underComponentName).append("State.").append(underComponentName).append("Total }</span> 条 </div>\r\n");
        sb.append("              <Pagination onChange={ current => ").append(underComponentName).append("Dispatchers.").append(underComponentName).append("Page(current) }\r\n");
        sb.append("                stype=\"simple\" pageSize={ 5 } total={ ").append(underComponentName).append("State.").append(underComponentName).append("Total } />\r\n");
        sb.append("            </Box>\r\n");
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
                sb.append("            <Dialog title=\"菜单\" visible={ ").append(underRelEntity).append("State.").append(underRelEntity).append("Visible }\r\n");
                sb.append("              onOk={ () => ").append(underRelEntity).append("Dispatchers.").append(underRelEntity).append("Save({\r\n");
                sb.append("                ").append(underRelEntity).append("FormData: ").append(underRelEntity).append("State.").append(underRelEntity).append("FormData,\r\n");
                sb.append("                ").append(underRelEntity).append("Current: ").append(underRelEntity).append("State.").append(underRelEntity).append("Current,\r\n");
                sb.append("                ").append(underComponentName).append("Id: ").append(underRelEntity).append("State.").append(underComponentName).append("Id,\r\n");
                sb.append("              }) }\r\n");
                sb.append("              onCancel={ () => ").append(underRelEntity).append(".setState({ ").append(underRelEntity).append("Visible: false }) }\r\n");
                sb.append("              onClose={ () => ").append(underRelEntity).append(".setState({ ").append(underRelEntity).append("Visible: false }) }\r\n");
                sb.append("              style={ { width: '30%' } }>\r\n");
                sb.append("              <DataFormTemple items={ ").append(underRelEntity).append("State.").append(underRelEntity).append("DataForm }\r\n");
                sb.append("                dispatchers={ value => ").append(underRelEntity).append("Dispatchers.setDataForm(value) }\r\n");
                sb.append("                formDataValue={ ").append(underRelEntity).append("State.").append(underRelEntity).append("FormData } />\r\n");
                sb.append("            </Dialog>\r\n");
                sb.append("          </div>\r\n");
                sb.append("          <Loading tip=\"加载中...\" visible={ ").append(underRelEntity).append("State.").append(underRelEntity).append("LoadingVisible }>\r\n");
                sb.append("            <DataTableTemple dataSource={ ").append(underRelEntity).append("State.").append(underRelEntity).append("DataTable } pageRender={ ").append(underRelEntity).append("PageRender } />\r\n");
                sb.append("            <Box margin={ [15, 0, 0, 0] } direction=\"row\" align=\"center\" justify=\"space-between\">\r\n");
                sb.append("              <div className={ styles.total }> 共 <span>{ ").append(underRelEntity).append("State.").append(underRelEntity).append("Total }</span> 条 </div>\r\n");
                sb.append("              <Pagination onChange={ current => ").append(underRelEntity).append("Dispatchers.").append(underRelEntity).append("Page({ id: ").append(underRelEntity).append("State.").append(underComponentName).append("Id, current }) }\r\n");
                sb.append("                stype=\"simple\" pageSize={ 5 } total={ ").append(underRelEntity).append("State.").append(underRelEntity).append("Total } />\r\n");
                sb.append("            </Box>\r\n");
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
                "  .Table {\r\n" +
                "    .opt {\r\n" +
                "      button {\r\n" +
                "        margin-right: 5px;\r\n" +
                "      }\r\n" +
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
