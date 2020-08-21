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
                po.getComponentName() + "/service");
        File fileComponentService = new File(pagePath + "/" +
                po.getComponentName() + "/service" + po.getPath() + ".jsx");
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
                    createFile(dirComponentService,createServiceJsx(po));
                }
            }
            if (fileComponentIndex.createNewFile()) {
                createFile(fileComponentIndex,createIndexJsx());
            }
            if (fileComponentCss.createNewFile()) {
                createFile(fileComponentCss,createIndexCss());
            }
        }
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
        return "import " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Service from '../services/" + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "'\r\n" +
                "\r\n" +
                "export default {\r\n" +
                "\r\n" +
                "  namespace: '" + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "',\r\n" +
                "\r\n" +
                "  state: {\r\n" +
                "    " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "TableData: [],\r\n" +
                "    " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Visible: false,\r\n" +
                "    " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "FormData: {},\r\n" +
                "    " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "LoadingVisible: true,\r\n" +
                "    " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Total: 0,\r\n" +
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
                "    " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Page() {\r\n" +
                "      " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Service." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Page().then(res => {\r\n" +
                "        const payload = {\r\n" +
                "          " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Total: res.data[1],\r\n" +
                "          " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "TableData: [res.data[0]],\r\n" +
                "          " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "LoadingVisible: false\r\n" +
                "        }\r\n" +
                "        dispatch." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + ".setState(payload);\r\n" +
                "      })\r\n" +
                "    },\r\n" +
                "    edit" + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "(data) {\r\n" +
                "      if (data) {\r\n" +
                "        const payload = {\r\n" +
                "          " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "FormData: data,\r\n" +
                "          " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Visible: true\r\n" +
                "        }\r\n" +
                "        dispatch." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + ".setState(payload);\r\n" +
                "      } else {\r\n" +
                "        const payload = {\r\n" +
                "          " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "FormData: {},\r\n" +
                "          " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Visible: true\r\n" +
                "        }\r\n" +
                "        dispatch." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + ".setState(payload);\r\n" +
                "      }\r\n" +
                "    },\r\n" +
                "    delete" + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "(data) {\r\n" +
                "      " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Service." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Delete(data).then(() => {\r\n" +
                "        " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Service." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Page().then(res => {\r\n" +
                "          const payload = {\r\n" +
                "            " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Total: res.data[1],\r\n" +
                "            " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "TableData: [res.data[0]],\r\n" +
                "          }\r\n" +
                "          dispatch." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + ".setState(payload);\r\n" +
                "        })\r\n" +
                "      })\r\n" +
                "    },\r\n" +
                "    save" + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "(data) {\r\n" +
                "      " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Service." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Save(data." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "FormData).then(() => {\r\n" +
                "        " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Service." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Page().then(res => {\r\n" +
                "          const payload = {\r\n" +
                "            " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Total: res.data[1],\r\n" +
                "            " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "TableData: [res.data[0]],\r\n" +
                "          }\r\n" +
                "          dispatch." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + ".setState(payload);\r\n" +
                "        })\r\n" +
                "      })\r\n" +
                "      const payload = { " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Visible: false }\r\n" +
                "      dispatch." + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + ".setState(payload);\r\n" +
                "    },\r\n" +
                "  })\r\n" +
                "};\r\n";
    }

    private String createServiceJsx(MenuPagePO po) {
        return "import { request } from 'ice';\r\n" +
                "\r\n" +
                "export default {\r\n" +
                "  " + BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Page() {\r\n" +
                "    return request({\r\n" +
                "      url: '" + po.getApiPath() + "/" +
                BeanUtils.toLowerCaseFirstOne(po.getComponentName()) + "Page',\r\n" +
                "      method: 'post'\r\n" +
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
                "}\r\n";
    }

    private String createIndexJsx() {
        StringBuilder sb = new StringBuilder();
        sb.append("import { ResponsiveGrid, Button, Table, Box, Dialog, Form, Input, Loading } from '@alifd/next';\r\n");
        sb.append("import React, { useEffect } from 'react';\r\n");
        sb.append("import { store as pageStore } from 'ice/PageMenu';\r\n");
        sb.append("import styles from './index.module.scss';\r\n");
        sb.append("\r\n");
        sb.append("const { Cell } = ResponsiveGrid;\r\n");
        sb.append("const FormItem = Form.Item;\r\n");
        sb.append("\r\n");
        sb.append("function MenuPage() {\r\n");
        sb.append("  const [pagemenuState, pagemenuDispatchers] = pageStore.useModel('pagemenu');\r\n");
        sb.append("  const dispatchers = pageStore.useModelDispatchers('pagemenu');\r\n");
        sb.append("  const pagemenuTableData = JSON.parse(JSON.stringify(pagemenuState.pagemenuTableData));\r\n");
        sb.append("\r\n");
        sb.append("  useEffect(() => {\r\n");
        sb.append("    pagemenuDispatchers.PageMenuPage();\r\n");
        sb.append("  }, [pagemenuDispatchers]);\r\n");
        sb.append("\r\n");
        sb.append("  const menuPageRender = (value, index, record) => {\r\n");
        sb.append("    return <div className={styles.opt}>\r\n");
        sb.append("      <Button type=\"primary\" size=\"small\" onClick={() => pagemenuDispatchers.editPageMenu(record)}> 编辑 </Button>\r\n");
        sb.append("      <Button type=\"primary\" size=\"small\" onClick={() => pagemenuDispatchers.deletePageMenu(record)} warning> 删除 </Button>\r\n");
        sb.append("    </div>;\r\n");
        sb.append("  };\r\n");
        sb.append("\r\n");
        sb.append("  return (\r\n");
        sb.append("    <ResponsiveGrid gap={20}>\r\n");
        sb.append("      <Cell colSpan={12}>\r\n");
        sb.append("        <div className={styles.Main}>\r\n");
        sb.append("          <div className={styles.add}>\r\n");
        sb.append("            <Button type=\"primary\" onClick={() => pagemenuDispatchers.editPageMenu()}> 添加菜单 </Button>\r\n");
        sb.append("            <Button type=\"primary\" onClick={() => pagemenuDispatchers.createRouteFile()}> 生成路由文件 </Button>\r\n");
        sb.append("            <Dialog title=\"菜单\" visible={pagemenuState.pagemenuVisible}\r\n");
        sb.append("              onOk={() => pagemenuDispatchers.savePageMenu({\r\n");
        sb.append("                pagemenuFormData: pagemenuState.pagemenuFormData\r\n");
        sb.append("              })}\r\n");
        sb.append("              onCancel={() => dispatchers.setState({ pagemenuVisible: false })}\r\n");
        sb.append("              onClose={() => dispatchers.setState({ pagemenuVisible: false })}\r\n");
        sb.append("              style={{ width: '30%' }}>\r\n");
        sb.append("              <Form style={{ width: '100%' }} {...pagemenuState.formItemLayout}\r\n");
        sb.append("                value={pagemenuState.pagemenuFormData}\r\n");
        sb.append("                onChange={value => dispatchers.setState({ pagemenuFormData: value })}>\r\n");
        sb.append("                  11111111111111111111111\r\n");
        sb.append("              </Form>\r\n");
        sb.append("            </Dialog>\r\n");
        sb.append("          </div>\r\n");
        sb.append("          <Loading tip=\"加载中...\" visible={pagemenuState.pagemenuLoadingVisible}>\r\n");
        sb.append("            <Table hasBorder className={styles.Table} dataSource={pagemenuTableData} isTree primaryKey=\"id\">\r\n");
        sb.append("              <Table.Column title=\"操作\" lock=\"right\" width=\"160px\" cell={menuPageRender} />\r\n");
        sb.append("            </Table>\r\n");
        sb.append("            <Box margin={[15, 0, 0, 0]} direction=\"row\" align=\"center\" justify=\"space-between\">\r\n");
        sb.append("              <div className={styles.total}> 共 <span>{pagemenuState.pagemenuTotal}</span> 条 </div>\r\n");
        sb.append("            </Box>\r\n");
        sb.append("          </Loading>\r\n");
        sb.append("        </div>\r\n");
        sb.append("      </Cell>\r\n");
        sb.append("    </ResponsiveGrid>\r\n");
        sb.append("  )\r\n");
        sb.append("}\r\n");
        sb.append("\r\n");
        sb.append("export default MenuPage;\r\n");
        return null;
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
                "}\r\n";
    }

}
