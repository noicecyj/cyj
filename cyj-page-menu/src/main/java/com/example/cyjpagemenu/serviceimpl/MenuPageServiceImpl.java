package com.example.cyjpagemenu.serviceimpl;

import com.example.cyjpagemenu.dao.MenuPageDao;
import com.example.cyjpagemenu.entity.MenuPagePO;
import com.example.cyjpagemenu.service.MenuPageService;
import com.example.cyjpagemenu.utils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@PropertySource(value = {"classpath:config.properties",}, encoding = "utf-8")
@Service
public class MenuPageServiceImpl extends BaseService implements MenuPageService {

    private MenuPageDao menuPageDao;
    private final static Logger logger = LoggerFactory.getLogger(MenuPageServiceImpl.class);

    @Autowired
    public void setMenuPageDao(MenuPageDao menuPageDao) {
        this.menuPageDao = menuPageDao;
    }

    @Override
    public MenuPagePO addOne(MenuPagePO po) {
        return menuPageDao.save(po);
    }

    @Override
    public void deleteOne(String id) {
        menuPageDao.deleteById(id);
    }

    @Override
    public MenuPagePO updateOne(MenuPagePO po) {
        return menuPageDao.saveAndFlush(po);
    }

    @Override
    public MenuPagePO findAll() {
        return menuPageDao.getOne("001");
    }

    @Override
    public long count() {
        return menuPageDao.count();
    }

    @Override
    public void createRouteFile(String routePath) throws IOException {
        logger.info(routePath);
        List<MenuPagePO> pos = menuPageDao.findAllByComponentNameIsNotNull();
        StringBuilder sb = new StringBuilder();
        sb.append("import BasicLayout from '@/layouts/BasicLayout';\r\n");
        for (MenuPagePO po : pos) {
            sb.append("import ").append(po.getComponentName()).append("Page").append(" from '@/pages/").append(po.getComponentName()).append("';\r\n");
        }
        sb.append("\r\n");
        sb.append("const routerConfig = [\r\n");
        sb.append("  {\r\n");
        sb.append("    path: '/',\r\n");
        sb.append("    component: BasicLayout,\r\n");
        sb.append("    children: [\r\n");
        for (MenuPagePO po : pos) {
            sb.append("      {\r\n");
            sb.append("        path: '").append(po.getPath()).append("',\r\n");
            sb.append("        component: ").append(po.getComponentName()).append("Page").append(",\r\n");
            sb.append("      },\r\n");
        }
        sb.append("    ],\r\n");
        sb.append("  },\r\n");
        sb.append("];\r\n");
        sb.append("export default routerConfig;\r\n");
        File file = new File(routePath + "\\routes.js");
        createFile(file,sb.toString());
    }

    @Override
    public void createComponentFile(String pagePath, MenuPagePO po) throws IOException {
        logger.info(pagePath);
        File dirComponent = new File(pagePath + "/" +
                po.getComponentName());
        File dirComponentModels = new File(pagePath + "/" +
                po.getComponentName() + "/models");
        File fileComponentModels = new File(pagePath + "/" +
                po.getComponentName() + "/models" + po.getPath() + ".jsx");
        File dirComponentService = new File(pagePath + "/" +
                po.getComponentName() + "/services");
        File fileComponentService = new File(pagePath + "/" +
                po.getComponentName() + "/services" + po.getPath() + ".jsx");
        File fileComponentIndex = new File(pagePath + "/" +
                po.getComponentName() + "/index.jsx");
        File fileComponentCss = new File(pagePath + "/" +
                po.getComponentName() + "/index.module.scss");
        //如果文件不存在，创建一个文件
        if (dirComponent.mkdir()) {
            if (dirComponentModels.mkdir()) {
                if (fileComponentModels.createNewFile()) {
                    createFile(fileComponentModels,createModelsJsx(po));
                }
            }
            if (dirComponentService.mkdir()) {
                if (fileComponentService.createNewFile()) {
                    createFile(fileComponentService,createServiceJsx(po));
                }
            }
            if (fileComponentIndex.createNewFile()) {
                createFile(fileComponentIndex,createIndexJsx(po));
            }
            if (fileComponentCss.createNewFile()) {
                createFile(fileComponentCss,createIndexCss());
            }
        }
        po.setIsComponent("已生成");
        updateOne(po);
    }

    private void createFile(File file,String context) throws IOException {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(context);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert bw != null;
            bw.close();
            fw.close();
        }
    }

    private String createModelsJsx(MenuPagePO po) {
        return "import " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Service from '../services" + po.getPath() + "'\r\n" +
                "\r\n" +
                "export default {\r\n" +
                "\r\n" +
                "  namespace: '" + po.getPath().substring(1) + "',\r\n" +
                "\r\n" +
                "  state: {\r\n" +
                "    " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "TableData: [],\r\n" +
                "    " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Visible: false,\r\n" +
                "    " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "FormData: {},\r\n" +
                "    " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "LoadingVisible: true,\r\n" +
                "    " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Total: 0,\r\n" +
                "    " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Current: 1,\r\n" +
                "    formItemLayout: {\r\n" +
                "      labelCol: {\r\n" +
                "        fixedSpan: 6\r\n" +
                "      },\r\n" +
                "      wrapperCol: {\r\n" +
                "        span: 40\r\n" +
                "      }\r\n" +
                "    }\r\n" +
                "  },\r\n" +
                "\r\n" +
                "  reducers: {\r\n" +
                "    setState(prevState, payload) {\r\n" +
                "      return { ...prevState, ...payload }\r\n" +
                "    }\r\n" +
                "  },\r\n" +
                "\r\n" +
                "  effects: (dispatch) => ({\r\n" +
                "    " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Page(data) {\r\n" +
                "      " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Service." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Page(data).then(res => {\r\n" +
                "        const payload = {\r\n" +
                "          " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Total: res.data.totalElements,\r\n" +
                "          " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "TableData: res.data.content,\r\n" +
                "          " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "LoadingVisible: false\r\n" +
                "        }\r\n" +
                "        dispatch." + po.getPath().substring(1) + ".setState(payload);\r\n" +
                "      })\r\n" +
                "    },\r\n" +
                "    " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Edit(data) {\r\n" +
                "      if (data) {\r\n" +
                "        const payload = {\r\n" +
                "          " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "FormData: data,\r\n" +
                "          " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Visible: true\r\n" +
                "        }\r\n" +
                "        dispatch." + po.getPath().substring(1) + ".setState(payload);\r\n" +
                "      } else {\r\n" +
                "        const payload = {\r\n" +
                "          " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "FormData: {},\r\n" +
                "          " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Visible: true\r\n" +
                "        }\r\n" +
                "        dispatch." + po.getPath().substring(1) + ".setState(payload);\r\n" +
                "      }\r\n" +
                "    },\r\n" +
                "    " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Delete(data) {\r\n" +
                "      " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Service." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Delete(data.record).then(() => {\r\n" +
                "        " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Service." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Page(data." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Current).then(res => {\r\n" +
                "          const payload = {\r\n" +
                "            " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Total: res.data.totalElements,\r\n" +
                "            " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "TableData: res.data.content,\r\n" +
                "            " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "LoadingVisible: false\r\n" +
                "          }\r\n" +
                "          dispatch." + po.getPath().substring(1) + ".setState(payload);\r\n" +
                "        })\r\n" +
                "      })\r\n" +
                "    },\r\n" +
                "    " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Save(data) {\r\n" +
                "      " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Service." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Save(data." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "FormData).then(() => {\r\n" +
                "        " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Service." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Page(data." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Current).then(res => {\r\n" +
                "          const payload = {\r\n" +
                "            " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Total: res.data.totalElements,\r\n" +
                "            " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "TableData: res.data.content,\r\n" +
                "            " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "LoadingVisible: false\r\n" +
                "          }\r\n" +
                "          dispatch." + po.getPath().substring(1) + ".setState(payload);\r\n" +
                "        })\r\n" +
                "      })\r\n" +
                "      const payload = { " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Visible: false }\r\n" +
                "      dispatch." + po.getPath().substring(1) + ".setState(payload);\r\n" +
                "    },\r\n" +
                "  })\r\n" +
                "};";
    }

    private String createServiceJsx(MenuPagePO po) {
        return "import { request } from 'ice';\r\n" +
                "\r\n" +
                "export default {\r\n" +
                "  " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Page(value) {\r\n" +
                "    return request({\r\n" +
                "      url: '" + po.getApiPath() + "/" +
                BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Page',\r\n" +
                "      method: 'post',\r\n" +
                "      params: {\r\n" +
                "        pageNumber: value,\r\n" +
                "        pageSize: 13,\r\n" +
                "        sortCode: 'sortCode'\r\n" +
                "      }\r\n" +
                "    });\r\n" +
                "  },\r\n" +
                "  " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Delete(record) {\r\n" +
                "    return request({\r\n" +
                "      url: '" + po.getApiPath() + "/" +
                BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Delete',\r\n" +
                "      method: 'post',\r\n" +
                "      params: {\r\n" +
                "        id: record.id\r\n" +
                "      }\r\n" +
                "    })\r\n" +
                "  },\r\n" +
                "  " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Save(data) {\r\n" +
                "    return request({\r\n" +
                "      url: '" + po.getApiPath() + "/" +
                BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Save',\r\n" +
                "      method: 'post',\r\n" +
                "      data\r\n" +
                "    })\r\n" +
                "  },\r\n" +
                "}";
    }

    private String createIndexJsx(MenuPagePO po) {
        return "import { ResponsiveGrid, Button, Table, Box, Dialog, Form, Input, Loading } from '@alifd/next';\r\n" +
                "import React, { useEffect } from 'react';\r\n" +
                "import { store as pageStore } from 'ice/" + po.getComponentName() + "';\r\n" +
                "import styles from './index.module.scss';\r\n" +
                "\r\n" +
                "const { Cell } = ResponsiveGrid;\r\n" +
                "const FormItem = Form.Item;\r\n" +
                "\r\n" +
                "function " + po.getComponentName() + "Page() {\r\n" +
                "  const [" + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "State, " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Dispatchers] = pageStore.useModel('" + po.getPath().substring(1) + "');\r\n" +
                "  const dispatchers = pageStore.useModelDispatchers('" + po.getPath().substring(1) + "');\r\n" +
                "  const " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "TableData = JSON.parse(JSON.stringify(" + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "State." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "TableData));\r\n" +
                "\r\n" +
                "  useEffect(() => {\r\n" +
                "    " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Dispatchers." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Page(1);\r\n" +
                "  }, [" + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Dispatchers]);\r\n" +
                "\r\n" +
                "  const " + po.getComponentName() + "PageRender = (value, index, record) => {\r\n" +
                "    return <div className={styles.opt}>\r\n" +
                "      <Button type=\"primary\" size=\"small\" onClick={() => " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Dispatchers." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Edit(record)}> 编辑 </Button>\r\n" +
                "      <Button type=\"primary\" size=\"small\" onClick={() => " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Dispatchers." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Delete({\r\n" +
                "        record,\r\n" +
                "        " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Current: " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "State." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Current\r\n" +
                "      })} warning> 删除 </Button>\r\n" +
                "    </div>;\r\n" +
                "  };\r\n" +
                "\r\n" +
                "  return (\r\n" +
                "    <ResponsiveGrid gap={20}>\r\n" +
                "      <Cell colSpan={12}>\r\n" +
                "        <div className={styles.Main}>\r\n" +
                "          <div className={styles.add}>\r\n" +
                "            <Button type=\"primary\" onClick={() => " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Dispatchers." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Edit()}> 添加菜单 </Button>\r\n" +
                "            <Dialog title=\"菜单\" visible={" + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "State." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Visible}\r\n" +
                "              onOk={() => " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Dispatchers." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Save({\r\n" +
                "                " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "FormData: " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "State." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "FormData,\r\n" +
                "                " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Current: " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "State." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Current\r\n" +
                "              })}\r\n" +
                "              onCancel={() => dispatchers.setState({ " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Visible: false })}\r\n" +
                "              onClose={() => dispatchers.setState({ " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Visible: false })}\r\n" +
                "              style={{ width: '30%' }}>\r\n" +
                "              <Form style={{ width: '100%' }} {..." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "State.formItemLayout}\r\n" +
                "                value={" + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "State." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "FormData}\r\n" +
                "                onChange={value => dispatchers.setState({ " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "FormData: value })}>\r\n" +
                "                  11111111111111111111111\r\n" +
                "              </Form>\r\n" +
                "            </Dialog>\r\n" +
                "          </div>\r\n" +
                "          <Loading tip=\"加载中...\" visible={" + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "State." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "LoadingVisible}>\r\n" +
                "            <Table hasBorder className={styles.Table} dataSource={" + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "TableData} isTree primaryKey=\"id\">\r\n" +
                "              <Table.Column title=\"操作\" lock=\"right\" width=\"160px\" cell={" + po.getComponentName() + "PageRender} />\r\n" +
                "            </Table>\r\n" +
                "            <Box margin={[15, 0, 0, 0]} direction=\"row\" align=\"center\" justify=\"space-between\">\r\n" +
                "              <div className={styles.total}> 共 <span>{" + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "State." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Total}</span> 条 </div>\r\n" +
                "            </Box>\r\n" +
                "          </Loading>\r\n" +
                "        </div>\r\n" +
                "      </Cell>\r\n" +
                "    </ResponsiveGrid>\r\n" +
                "  )\r\n" +
                "}\r\n" +
                "\r\n" +
                "export default " + po.getComponentName() + "Page;";
    }

    private String createIndexCss() {
        return "@import \"~@alifd/next/variables.scss\";\r\n" +
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
    }

}
