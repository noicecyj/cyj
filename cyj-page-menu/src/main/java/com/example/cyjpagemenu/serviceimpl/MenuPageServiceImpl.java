package com.example.cyjpagemenu.serviceimpl;

import com.example.cyjpagemenu.dao.MenuPageDao;
import com.example.cyjpagemenu.entity.MenuPagePO;
import com.example.cyjpagemenu.service.MenuPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Service
public class MenuPageServiceImpl extends BaseService implements MenuPageService {

    private MenuPageDao menuPageDao;

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
    public MenuPagePO findAll(String id) {
        return menuPageDao.getAllById(id);
    }
}
