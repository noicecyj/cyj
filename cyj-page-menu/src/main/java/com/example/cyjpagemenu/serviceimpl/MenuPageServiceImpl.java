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
    public void createRouteFile(String routePath) {
        if (routePath.length() == 0 && "".equals(routePath)){
            routePath = path;
        }
        logger.info(routePath);
    }


}
