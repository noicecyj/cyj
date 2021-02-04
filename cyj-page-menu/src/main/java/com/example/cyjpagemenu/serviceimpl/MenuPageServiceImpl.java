package com.example.cyjpagemenu.serviceimpl;

import com.example.cyjcommon.utils.VoPoConverter;
import com.example.cyjpagemenu.dao.MenuPageDao;
import com.example.cyjpagemenu.entity.MenuPagePO;
import com.example.cyjpagemenu.entity.vo.MenuPageVO;
import com.example.cyjpagemenu.service.MenuPageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2021-02-04
 */
@PropertySource(value = {"classpath:config.properties",}, encoding = "utf-8")
@Service
@Transactional(rollbackFor = Exception.class)
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
    public Page<MenuPagePO> findAll(Integer pageNumber, Integer pageSize, String sortCode) {
        Sort sort = Sort.by(sortCode);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return menuPageDao.findAll(pageable);
    }


    @Override
    public MenuPagePO findOneById(String id) {
        if (menuPageDao.findById(id).isPresent()) {
            return menuPageDao.findById(id).get();
        }
        return null;
    }

    @Override
    public MenuPageVO findAll() {
        MenuPagePO menuPagePO = menuPageDao.getOne("001");
        MenuPageVO menuPageVO = new MenuPageVO();
        VoPoConverter.copyProperties(menuPagePO, menuPageVO);
        return menuPageVO;
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
            sb.append("import ").append(po.getComponentName()).append("Page").append(" from '@/pages/")
                    .append(po.getComponentName()).append("';\r\n");
        }
        sb.append("\r\n");
        sb.append("const routerConfig = [\r\n");
        sb.append("  {\r\n");
        sb.append("    path: '/',\r\n");
        sb.append("    component: BasicLayout,\r\n");
        sb.append("    children: [\r\n");
        for (MenuPagePO po : pos) {
            sb.append("      {\r\n");
            sb.append("        path: '").append(po.getApiPath()).append("',\r\n");
            sb.append("        component: ").append(po.getComponentName()).append("Page").append(",\r\n");
            sb.append("      },\r\n");
        }
        sb.append("    ],\r\n");
        sb.append("  },\r\n");
        sb.append("];\r\n");
        sb.append("export default routerConfig;\r\n");
        File file = new File(routePath + "\\routes.js");
        createFile(file, sb.toString());
    }

    private void createFile(File file, String context) throws IOException {
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

}
