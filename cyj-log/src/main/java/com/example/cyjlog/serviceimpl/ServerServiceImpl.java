package com.example.cyjlog.serviceimpl;

import com.example.cyjlog.dao.ServerDao;
import com.example.cyjlog.entity.ServerPO;
import com.example.cyjlog.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Service
public class ServerServiceImpl extends BaseService implements ServerService {

    private ServerDao serverDao;

    @Autowired
    public void setServerDao(ServerDao serverDao) {
        this.serverDao = serverDao;
    }

    @Override
    public ServerPO addOne(ServerPO po) {
        return serverDao.save(po);
    }

    @Override
    public void deleteOne(String id) {
        serverDao.deleteById(id);
    }

    @Override
    public ServerPO updateOne(ServerPO po) {
        return serverDao.saveAndFlush(po);
    }

    @Override
    public List<ServerPO> findAll() {
        return serverDao.findAll();
    }
}
