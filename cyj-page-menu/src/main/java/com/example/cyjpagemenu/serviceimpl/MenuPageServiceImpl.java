package com.example.cyjpagemenu.serviceimpl;

import com.example.cyjpagemenu.dao.MenuPageDao;
import com.example.cyjpagemenu.entity.MenuPagePO;
import com.example.cyjpagemenu.service.MenuPageService;
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
                    createFile(fileComponentModels,createModelsJsx());
                }
            }
            if (dirComponentService.mkdir()) {
                if (fileComponentService.createNewFile()) {
                    createFile(dirComponentService,createServiceJsx());
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

    private String createModelsJsx() {
        return null;
    }

    private String createServiceJsx() {
        return null;
    }

    private String createIndexJsx() {
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
