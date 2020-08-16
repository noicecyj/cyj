package com.example.cyjpagemenu.serviceimpl;

import com.example.cyjpagemenu.dao.MenuPageDao;
import com.example.cyjpagemenu.entity.MenuPagePO;
import com.example.cyjpagemenu.service.MenuPageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${routePath}")
    private String path;
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
        if (routePath.length() == 0 && "".equals(routePath)) {
            routePath = path;
        }
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
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert bw != null;
            bw.close();
            fw.close();
        }

    }

}
