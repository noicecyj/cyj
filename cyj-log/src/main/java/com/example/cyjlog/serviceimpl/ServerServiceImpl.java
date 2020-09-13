package com.example.cyjlog.serviceimpl;

import com.example.cyjlog.entity.ServerPO;
import com.example.cyjlog.dao.ServerDao;
import com.example.cyjlog.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-09-13
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
    public Page<ServerPO> findAll(Integer pageNumber, Integer pageSize, String sortCode) {
        Sort sort = Sort.by(sortCode);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return serverDao.findAll(pageable);
    }

}
