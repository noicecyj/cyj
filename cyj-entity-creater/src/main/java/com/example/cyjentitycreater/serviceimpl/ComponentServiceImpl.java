package com.example.cyjentitycreater.serviceimpl;

import com.example.cyjentitycreater.entity.CreateVO;
import com.example.cyjentitycreater.utils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.example.cyjentitycreater.utils.BeanUtils.componentName;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Component
public class ComponentServiceImpl extends BaseService {

    private final Logger logger = LoggerFactory.getLogger(ComponentServiceImpl.class);

    public void createComponentFile(String pagePath, CreateVO createVO) throws IOException {
        String componentName = BeanUtils.captureName(BeanUtils.underline2Camel(createVO.getName()));
        String componentPath = pagePath + "/" + componentName;
        createJavaFile(componentPath + "/models");
        createJavaFile(componentPath + "/services");
        createJavaFile(componentPath + "/models", createModelsJsx(createVO));
        createJavaFile(componentPath + "/services", createServiceJsx(createVO));
        createJavaFile(componentPath, createIndexJsx(createVO));
        createJavaFile(componentPath, createIndexCss());
    }

    private String[] createModelsJsx(CreateVO createVO) {
        StringBuilder sb = new StringBuilder();
        String underComponentName = BeanUtils.underline2Camel(createVO.getName());
        sb.append("import ").append(underComponentName).append("Service from '../services/").append(BeanUtils.captureName(underComponentName)).append("';\r\n");
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
        if (createVO.getRelEntity() != null && !"".equals(createVO.getRelEntity())) {
            String[] relEntitys = createVO.getRelEntity().split(",");
            for (String relEntity : relEntitys) {
                String underRelEntity = BeanUtils.underline2Camel(relEntity);
                sb.append("    ").append(underRelEntity).append("TableData: [],\r\n");
                sb.append("    ").append(underRelEntity).append("Visible: false,\r\n");
                sb.append("    ").append(underRelEntity).append("FormData: {},\r\n");
                sb.append("    ").append(underRelEntity).append("LoadingVisible: true,\r\n");
                sb.append("    ").append(underRelEntity).append("Total: 0,\r\n");
                sb.append("    ").append(underRelEntity).append("Current: 1,\r\n");
                sb.append("    ").append(underRelEntity).append("DivVisible: true,\r\n");
            }
            sb.append("    ").append(underComponentName).append("Id: '',\r\n");
        }
        sb.append("    formItemLayout: {\r\n");
        sb.append("      labelCol: {\r\n");
        sb.append("        fixedSpan: 6,\r\n");
        sb.append("      },\r\n");
        sb.append("      wrapperCol: {\r\n");
        sb.append("        span: 40,\r\n");
        sb.append("      },\r\n");
        sb.append("    },\r\n");
        sb.append("  },\r\n");
        sb.append("\r\n");
        sb.append("  reducers: {\r\n");
        sb.append("    setState(prevState, payload) {\r\n");
        sb.append("      return { ...prevState, ...payload };\r\n");
        sb.append("    },\r\n");
        sb.append("  },\r\n");
        sb.append("\r\n");
        sb.append("  effects: (dispatch) => ({\r\n");
        sb.append("    ").append(underComponentName).append("Page(data) {\r\n");
        sb.append("      ").append(underComponentName).append("Service.").append(underComponentName).append("Page(data).then(res => {\r\n");
        sb.append("        const payload = {\r\n");
        sb.append("          ").append(underComponentName).append("Total: res.data.totalElements,\r\n");
        sb.append("          ").append(underComponentName).append("TableData: res.data.content,\r\n");
        sb.append("          ").append(underComponentName).append("Current: data,\r\n");
        sb.append("          ").append(underComponentName).append("LoadingVisible: false,\r\n");
        sb.append("        };\r\n");
        sb.append("        dispatch.").append(BeanUtils.captureName(underComponentName)).append(".setState(payload);\r\n");
        if (createVO.getRelEntity() != null && !"".equals(createVO.getRelEntity())) {
            String[] relEntitys = createVO.getRelEntity().split(",");
            sb.append("        if (data !== 1) {\r\n");
            sb.append("          const payload2 = {\r\n");
            for (String relEntity : relEntitys) {
                String underRelEntity = BeanUtils.underline2Camel(relEntity);
                sb.append("            ").append(underRelEntity).append("DivVisible: true,\r\n");
            }
            sb.append("          };\r\n");
            sb.append("          dispatch.").append(BeanUtils.captureName(underComponentName)).append(".setState(payload2);\r\n");
            sb.append("        }\r\n");
        }
        sb.append("      });\r\n");
        sb.append("    },\r\n");
        sb.append("    ").append(underComponentName).append("Edit(data) {\r\n");
        sb.append("      if (data) {\r\n");
        sb.append("        const payload = {\r\n");
        sb.append("          ").append(underComponentName).append("FormData: data,\r\n");
        sb.append("          ").append(underComponentName).append("Visible: true,\r\n");
        sb.append("        };\r\n");
        sb.append("        dispatch.").append(BeanUtils.captureName(underComponentName)).append(".setState(payload);\r\n");
        sb.append("      } else {\r\n");
        sb.append("        const payload = {\r\n");
        sb.append("          ").append(underComponentName).append("FormData: {},\r\n");
        sb.append("          ").append(underComponentName).append("Visible: true,\r\n");
        sb.append("        };\r\n");
        sb.append("        dispatch.").append(BeanUtils.captureName(underComponentName)).append(".setState(payload);\r\n");
        sb.append("      }\r\n");
        sb.append("    },\r\n");
        sb.append("    ").append(underComponentName).append("Delete(data) {\r\n");
        sb.append("      ").append(underComponentName).append("Service.").append(underComponentName).append("Delete(data.record).then(() => {\r\n");
        sb.append("        ").append(underComponentName).append("Service.").append(underComponentName).append("Page(data.").append(underComponentName).append("Current).then(res => {\r\n");
        sb.append("          const payload = {\r\n");
        sb.append("            ").append(underComponentName).append("Total: res.data.totalElements,\r\n");
        sb.append("            ").append(underComponentName).append("TableData: res.data.content,\r\n");
        sb.append("            ").append(underComponentName).append("Current: data.").append(underComponentName).append("Current,\r\n");
        if (createVO.getRelEntity() != null && !"".equals(createVO.getRelEntity())) {
            String[] relEntitys = createVO.getRelEntity().split(",");
            for (String relEntity : relEntitys) {
                String underRelEntity = BeanUtils.underline2Camel(relEntity);
                sb.append("            ").append(underRelEntity).append("DivVisible: true,\r\n");
            }
        }
        sb.append("          };\r\n");
        sb.append("          dispatch.").append(BeanUtils.captureName(underComponentName)).append(".setState(payload);\r\n");
        sb.append("        });\r\n");
        sb.append("      });\r\n");
        sb.append("    },\r\n");
        sb.append("    ").append(underComponentName).append("Save(data) {\r\n");
        sb.append("      ").append(underComponentName).append("Service.").append(underComponentName).append("Save(data.").append(underComponentName).append("FormData).then(() => {\r\n");
        sb.append("        ").append(underComponentName).append("Service.").append(underComponentName).append("Page(data.").append(underComponentName).append("Current).then(res => {\r\n");
        sb.append("          const payload = {\r\n");
        sb.append("            ").append(underComponentName).append("Total: res.data.totalElements,\r\n");
        sb.append("            ").append(underComponentName).append("TableData: res.data.content,\r\n");
        sb.append("            ").append(underComponentName).append("Current: data.").append(underComponentName).append("Current,\r\n");
        if (createVO.getRelEntity() != null && !"".equals(createVO.getRelEntity())) {
            String[] relEntitys = createVO.getRelEntity().split(",");
            for (String relEntity : relEntitys) {
                String underRelEntity = BeanUtils.underline2Camel(relEntity);
                sb.append("            ").append(underRelEntity).append("DivVisible: true,\r\n");
            }
        }
        sb.append("          };\r\n");
        sb.append("          dispatch.").append(BeanUtils.captureName(underComponentName)).append(".setState(payload);\r\n");
        sb.append("        });\r\n");
        sb.append("      });\r\n");
        sb.append("      const payload = { ").append(underComponentName).append("Visible: false };\r\n");
        sb.append("      dispatch.").append(BeanUtils.captureName(underComponentName)).append(".setState(payload);\r\n");
        sb.append("    },\r\n");
        if (createVO.getRelEntity() != null && !"".equals(createVO.getRelEntity())) {
            String[] relEntitys = createVO.getRelEntity().split(",");
            for (String relEntity : relEntitys) {
                String underRelEntity = BeanUtils.underline2Camel(relEntity);
                sb.append("    ").append(underRelEntity).append("Page(data) {\r\n");
                sb.append("      ").append(underComponentName).append("Service.").append(underRelEntity).append("Page(data.id, data.current).then(res => {\r\n");
                sb.append("        const payload = {\r\n");
                sb.append("          ").append(underRelEntity).append("Total: res.data.totalElements,\r\n");
                sb.append("          ").append(underRelEntity).append("TableData: res.data.content,\r\n");
                sb.append("          ").append(underRelEntity).append("Current: data,\r\n");
                sb.append("          ").append(underRelEntity).append("LoadingVisible: false,\r\n");
                sb.append("        };\r\n");
                sb.append("        dispatch.").append(BeanUtils.captureName(underComponentName)).append(".setState(payload);\r\n");
                sb.append("      });\r\n");
                sb.append("    },\r\n");
                sb.append("    ").append(underRelEntity).append("Edit(data) {\r\n");
                sb.append("      if (data) {\r\n");
                sb.append("        const payload = {\r\n");
                sb.append("          ").append(underRelEntity).append("FormData: data,\r\n");
                sb.append("          ").append(underRelEntity).append("Visible: true,\r\n");
                sb.append("        };\r\n");
                sb.append("        dispatch.").append(BeanUtils.captureName(underComponentName)).append(".setState(payload);\r\n");
                sb.append("      } else {\r\n");
                sb.append("        const payload = {\r\n");
                sb.append("          ").append(underRelEntity).append("FormData: {},\r\n");
                sb.append("          ").append(underRelEntity).append("Visible: true,\r\n");
                sb.append("        };\r\n");
                sb.append("        dispatch.").append(BeanUtils.captureName(underComponentName)).append(".setState(payload);\r\n");
                sb.append("      }\r\n");
                sb.append("    },\r\n");
                sb.append("    ").append(underRelEntity).append("Save(data) {\r\n");
                sb.append("      ").append(underComponentName).append("Service.").append(underRelEntity).append("Save(data.").append(underRelEntity).append("FormData, data.").append(underRelEntity).append("Id).then(() => {\r\n");
                sb.append("        ").append(underComponentName).append("Service.").append(underRelEntity).append("Page(data.").append(underRelEntity).append("Id, data.").append(underRelEntity).append("Current).then(res => {\r\n");
                sb.append("          const payload = {\r\n");
                sb.append("            ").append(underRelEntity).append("Total: res.data.totalElements,\r\n");
                sb.append("            ").append(underRelEntity).append("TableData: res.data.content,\r\n");
                sb.append("            ").append(underRelEntity).append("Current: data.").append(underRelEntity).append("Current,\r\n");
                sb.append("          };\r\n");
                sb.append("          dispatch.").append(BeanUtils.captureName(underComponentName)).append(".setState(payload);\r\n");
                sb.append("        });\r\n");
                sb.append("      });\r\n");
                sb.append("      const payload = { ").append(underRelEntity).append("Visible: false };\r\n");
                sb.append("      dispatch.").append(BeanUtils.captureName(underComponentName)).append(".setState(payload);\r\n");
                sb.append("    },\r\n");
                sb.append("    ").append(underRelEntity).append("OnRowClick(data) {\r\n");
                sb.append("      ").append(underComponentName).append("Service.").append(underRelEntity).append("Page(data.record.id, 1).then(res => {\r\n");
                sb.append("        const payload = {\r\n");
                sb.append("          ").append(underRelEntity).append("Total: res.data.totalElements,\r\n");
                sb.append("          ").append(underRelEntity).append("TableData: res.data.content,\r\n");
                sb.append("          ").append(underRelEntity).append("Current: 1,\r\n");
                sb.append("          ").append(underRelEntity).append("DivVisible: !data.selected,\r\n");
                sb.append("        };\r\n");
                sb.append("        dispatch.").append(BeanUtils.captureName(underComponentName)).append(".setState(payload);\r\n");
                sb.append("      });\r\n");
                sb.append("      const payload = {\r\n");
                sb.append("        ").append(underComponentName).append("Id: data.record.id,\r\n");
                sb.append("      };\r\n");
                sb.append("      dispatch.").append(BeanUtils.captureName(underComponentName)).append(".setState(payload);\r\n");
                sb.append("    },\r\n");
            }
        }
        sb.append("  }),\r\n");
        sb.append("};");
        String modelData = sb.toString();
        return new String[]{modelData, componentName(createVO)};
    }

    private String[] createServiceJsx(CreateVO createVO) {
        StringBuilder sb = new StringBuilder();
        String underComponentName = BeanUtils.underline2Camel(createVO.getName());
        sb.append("import { request } from 'ice';\r\n");
        sb.append("\r\n");
        sb.append("export default {\r\n");
        sb.append("  ").append(underComponentName).append("Page(value) {\r\n");
        sb.append("    return request({\r\n");
        sb.append("      url: '/").append(createVO.getApi()).append("/").append(underComponentName).append("Page',\r\n");
        sb.append("      method: 'post',\r\n");
        sb.append("      params: {\r\n");
        sb.append("        pageNumber: value,\r\n");
        if (createVO.getRelEntity() != null && !"".equals(createVO.getRelEntity())) {
            sb.append("        pageSize: 5,\r\n");
        } else {
            sb.append("        pageSize: 13,\r\n");
        }
        sb.append("        sortCode: 'sortCode',\r\n");
        sb.append("      },\r\n");
        sb.append("    });\r\n");
        sb.append("  },\r\n");
        sb.append("  ").append(underComponentName).append("Delete(record) {\r\n");
        sb.append("    return request({\r\n");
        sb.append("      url: '/").append(createVO.getApi()).append("/").append(underComponentName).append("Delete',\r\n");
        sb.append("      method: 'post',\r\n");
        sb.append("      params: {\r\n");
        sb.append("        id: record.id,\r\n");
        sb.append("      },\r\n");
        sb.append("    });\r\n");
        sb.append("  },\r\n");
        sb.append("  ").append(underComponentName).append("Save(data) {\r\n");
        sb.append("    return request({\r\n");
        sb.append("      url: '/").append(createVO.getApi()).append("/").append(underComponentName).append("Save',\r\n");
        sb.append("      method: 'post',\r\n");
        sb.append("      data,\r\n");
        sb.append("    });\r\n");
        sb.append("  },\r\n");
        if (createVO.getRelEntity() != null && !"".equals(createVO.getRelEntity())) {
            String[] relEntitys = createVO.getRelEntity().split(",");
            for (String relEntity : relEntitys) {
                String underRelEntity = BeanUtils.underline2Camel(relEntity);
                sb.append("  ").append(underRelEntity).append("Page(id, value) {\r\n");
                sb.append("    return request({\r\n");
                sb.append("      url: '/").append(createVO.getApi()).append("/").append(underRelEntity).append("Page',\r\n");
                sb.append("      method: 'post',\r\n");
                sb.append("      params: {\r\n");
                sb.append("        id,\r\n");
                sb.append("        pageNumber: value,\r\n");
                sb.append("        pageSize: 5,\r\n");
                sb.append("        sortCode: 'sortCode',\r\n");
                sb.append("      },\r\n");
                sb.append("    });\r\n");
                sb.append("  },\r\n");
                sb.append("  ").append(underRelEntity).append("Delete(record) {\r\n");
                sb.append("    return request({\r\n");
                sb.append("      url: '/").append(createVO.getApi()).append("/").append(underRelEntity).append("Delete',\r\n");
                sb.append("      method: 'post',\r\n");
                sb.append("      params: {\r\n");
                sb.append("        id: record.id,\r\n");
                sb.append("      },\r\n");
                sb.append("    });\r\n");
                sb.append("  },\r\n");
                sb.append("  ").append(underRelEntity).append("Save(data, id) {\r\n");
                sb.append("    return request({\r\n");
                sb.append("      url: '/").append(createVO.getApi()).append("/").append(underRelEntity).append("Save',\r\n");
                sb.append("      method: 'post',\r\n");
                sb.append("      data: { ...data, pid: id },\r\n");
                sb.append("    });\r\n");
                sb.append("  },\r\n");
            }
        }
        sb.append("};");
        String serviceData = sb.toString();
        return new String[]{serviceData, componentName(createVO)};
    }

    private String[] createIndexJsx(CreateVO createVO) {
        StringBuilder sb = new StringBuilder();
        String underComponentName = BeanUtils.underline2Camel(createVO.getName());
        sb.append("import { ResponsiveGrid, Button, Table, Box, Dialog, Form, Loading, Pagination } from '@alifd/next';\r\n");
        sb.append("import React, { useEffect } from 'react';\r\n");
        sb.append("import { store as pageStore } from 'ice/").append(BeanUtils.captureName(underComponentName)).append("';\r\n");
        sb.append("import styles from './index.module.scss';\r\n");
        sb.append("\r\n");
        sb.append("const { Cell } = ResponsiveGrid;\r\n");
        sb.append("const FormItem = Form.Item;\r\n");
        sb.append("\r\n");
        sb.append("function ").append(BeanUtils.captureName(underComponentName)).append("Page() {\r\n");
        sb.append("  const [").append(underComponentName).append("State, ").append(underComponentName).append("Dispatchers] = pageStore.useModel('").append(BeanUtils.captureName(underComponentName)).append("');\r\n");
        sb.append("  const dispatchers = pageStore.useModelDispatchers('").append(BeanUtils.captureName(underComponentName)).append("');\r\n");
        sb.append("\r\n");
        sb.append("  useEffect(() => {\r\n");
        sb.append("    ").append(underComponentName).append("Dispatchers.").append(underComponentName).append("Page(1);\r\n");
        sb.append("  }, [").append(underComponentName).append("Dispatchers]);\r\n");
        sb.append("\r\n");
        sb.append("  const ").append(underComponentName).append("PageRender = (value, index, record) => {\r\n");
        sb.append("    return <div className={styles.opt}>\r\n");
        sb.append("      <Button type=\"primary\" size=\"small\" onClick={() => ").append(underComponentName).append("Dispatchers.").append(underComponentName).append("Edit(record)}> 编辑 </Button>\r\n");
        sb.append("      <Button type=\"primary\" size=\"small\" onClick={() => ").append(underComponentName).append("Dispatchers.").append(underComponentName).append("Delete({\r\n");
        sb.append("        record,\r\n");
        sb.append("        ").append(underComponentName).append("Current: ").append(underComponentName).append("State.").append(underComponentName).append("Current,\r\n");
        sb.append("      })} warning> 删除 </Button>\r\n");
        sb.append("    </div>;\r\n");
        sb.append("  };\r\n");
        if (createVO.getRelEntity() != null && !"".equals(createVO.getRelEntity())) {
            String[] relEntitys = createVO.getRelEntity().split(",");
            for (String relEntity : relEntitys) {
                String underRelEntity = BeanUtils.underline2Camel(relEntity);
                sb.append("\r\n");
                sb.append("  const ").append(underRelEntity).append("PageRender = (value, index, record) => {\r\n");
                sb.append("    return <div className={styles.opt}>\r\n");
                sb.append("      <Button type=\"primary\" size=\"small\" onClick={() => ").append(underComponentName).append("Dispatchers.").append(underRelEntity).append("Edit(record)}> 编辑 </Button>\r\n");
                sb.append("      <Button type=\"primary\" size=\"small\" onClick={() => ").append(underComponentName).append("Dispatchers.").append(underRelEntity).append("Delete({\r\n");
                sb.append("        record,\r\n");
                sb.append("        ").append(underRelEntity).append("Current: ").append(underComponentName).append("State.").append(underRelEntity).append("Current\r\n");
                sb.append("      })} warning> 删除 </Button>\r\n");
                sb.append("    </div>;\r\n");
                sb.append("  };\r\n");
            }
        }
        sb.append("\r\n");
        sb.append("  return (\r\n");
        sb.append("    <ResponsiveGrid gap={20}>\r\n");
        sb.append("      <Cell colSpan={12}>\r\n");
        sb.append("        <div className={styles.Main}>\r\n");
        sb.append("          <div className={styles.add}>\r\n");
        sb.append("            <Button type=\"primary\" onClick={() => ").append(underComponentName).append("Dispatchers.").append(underComponentName).append("Edit()}> 添加菜单 </Button>\r\n");
        sb.append("            <Dialog title=\"菜单\" visible={").append(underComponentName).append("State.").append(underComponentName).append("Visible}\r\n");
        sb.append("              onOk={() => ").append(underComponentName).append("Dispatchers.").append(underComponentName).append("Save({\r\n");
        sb.append("                ").append(underComponentName).append("FormData: ").append(underComponentName).append("State.").append(underComponentName).append("FormData,\r\n");
        sb.append("                ").append(underComponentName).append("Current: ").append(underComponentName).append("State.").append(underComponentName).append("Current,\r\n");
        sb.append("              })}\r\n");
        sb.append("              onCancel={() => dispatchers.setState({ ").append(underComponentName).append("Visible: false })}\r\n");
        sb.append("              onClose={() => dispatchers.setState({ ").append(underComponentName).append("Visible: false })}\r\n");
        sb.append("              style={{ width: '30%' }}>\r\n");
        sb.append("              <Form style={{ width: '100%' }} {...").append(underComponentName).append("State.formItemLayout}\r\n");
        sb.append("                value={").append(underComponentName).append("State.").append(underComponentName).append("FormData}\r\n");
        sb.append("                onChange={value => dispatchers.setState({ ").append(underComponentName).append("FormData: value })}>\r\n");
        sb.append("                  11111111111111111111111\r\n");
        sb.append("              </Form>\r\n");
        sb.append("            </Dialog>\r\n");
        sb.append("          </div>\r\n");
        sb.append("          <Loading tip=\"加载中...\" visible={").append(underComponentName).append("State.").append(underComponentName).append("LoadingVisible}>\r\n");
        sb.append("            <Table hasBorder className={styles.Table} dataSource={").append(underComponentName).append("State.").append(underComponentName).append("TableData} isTree primaryKey=\"id\">\r\n");
        sb.append("              <Table.Column title=\"操作\" lock=\"right\" width=\"160px\" cell={").append(underComponentName).append("PageRender} />\r\n");
        sb.append("            </Table>\r\n");
        sb.append("            <Box margin={[15, 0, 0, 0]} direction=\"row\" align=\"center\" justify=\"space-between\">\r\n");
        sb.append("              <div className={styles.total}> 共 <span>{").append(underComponentName).append("State.").append(underComponentName).append("Total}</span> 条 </div>\r\n");
        sb.append("              <Pagination onChange={current => ").append(underComponentName).append("Dispatchers.").append(underComponentName).append("Page(current)}\r\n");
        sb.append("                stype=\"simple\" pageSize={5} total={").append(underComponentName).append("State.").append(underComponentName).append("Total} />\r\n");
        sb.append("            </Box>\r\n");
        sb.append("          </Loading>\r\n");
        sb.append("        </div>\r\n");
        sb.append("      </Cell>\r\n");
        if (createVO.getRelEntity() != null && !"".equals(createVO.getRelEntity())) {
            String[] relEntitys = createVO.getRelEntity().split(",");
            for (String relEntity : relEntitys) {
                String underRelEntity = BeanUtils.underline2Camel(relEntity);
                sb.append("      <Cell colSpan={12} hidden={").append(underComponentName).append("State.").append(underRelEntity).append("DivVisible}>\r\n");
                sb.append("        <div className={styles.Main}>\r\n");
                sb.append("          <div className={styles.add}>\r\n");
                sb.append("            <Button type=\"primary\" onClick={() => ").append(underComponentName).append("Dispatchers.").append(underRelEntity).append("Edit()}> 添加菜单 </Button>\r\n");
                sb.append("            <Dialog title=\"菜单\" visible={").append(underComponentName).append("State.").append(underRelEntity).append("Visible}\r\n");
                sb.append("              onOk={() => ").append(underComponentName).append("Dispatchers.").append(underRelEntity).append("Save({\r\n");
                sb.append("                ").append(underRelEntity).append("FormData: ").append(underComponentName).append("State.").append(underRelEntity).append("FormData,\r\n");
                sb.append("                ").append(underRelEntity).append("Current: ").append(underComponentName).append("State.").append(underRelEntity).append("Current,\r\n");
                sb.append("                ").append(underComponentName).append("Id: ").append(underComponentName).append("State.").append(underComponentName).append("Id,\r\n");
                sb.append("              })}\r\n");
                sb.append("              onCancel={() => dispatchers.setState({ ").append(underRelEntity).append("Visible: false })}\r\n");
                sb.append("              onClose={() => dispatchers.setState({ ").append(underRelEntity).append("Visible: false })}\r\n");
                sb.append("              style={{ width: '30%' }}>\r\n");
                sb.append("              <Form style={{ width: '100%' }} {...").append(underComponentName).append("State.formItemLayout}\r\n");
                sb.append("                value={").append(underComponentName).append("State.").append(underRelEntity).append("FormData}\r\n");
                sb.append("                onChange={value => dispatchers.setState({ ").append(underRelEntity).append("FormData: value })}>\r\n");
                sb.append("                  11111111111111111111111\r\n");
                sb.append("              </Form>\r\n");
                sb.append("            </Dialog>\r\n");
                sb.append("          </div>\r\n");
                sb.append("          <Loading tip=\"加载中...\" visible={").append(underComponentName).append("State.").append(underRelEntity).append("LoadingVisible}>\r\n");
                sb.append("            <Table hasBorder className={styles.Table} dataSource={").append(underComponentName).append("State.").append(underRelEntity).append("TableData} isTree primaryKey=\"id\">\r\n");
                sb.append("              <Table.Column title=\"操作\" lock=\"right\" width=\"160px\" cell={").append(underRelEntity).append("PageRender} />\r\n");
                sb.append("            </Table>\r\n");
                sb.append("            <Box margin={[15, 0, 0, 0]} direction=\"row\" align=\"center\" justify=\"space-between\">\r\n");
                sb.append("              <div className={styles.total}> 共 <span>{").append(underComponentName).append("State.").append(underRelEntity).append("Total}</span> 条 </div>\r\n");
                sb.append("            </Box>\r\n");
                sb.append("            <Pagination onChange={current => ").append(underComponentName).append("Dispatchers.").append(underRelEntity).append("Page({ id: ").append(underComponentName).append("State.").append(underComponentName).append("Id, current })}\r\n");
                sb.append("              stype=\"simple\" pageSize={5} total={").append(underComponentName).append("State.").append(underRelEntity).append("Total} />\r\n");
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
