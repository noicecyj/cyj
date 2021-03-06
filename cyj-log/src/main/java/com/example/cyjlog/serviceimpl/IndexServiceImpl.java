package com.example.cyjlog.serviceimpl;

import com.example.cyjlog.dao.IndexDao;
import com.example.cyjlog.dao.LogDao;
import com.example.cyjlog.entity.LogPO;
import com.example.cyjlog.entity.QLogPO;
import com.example.cyjlog.entity.ServerPO;
import com.example.cyjlog.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Service
public class IndexServiceImpl extends BaseService implements IndexService {

    private LogDao logDao;
    private IndexDao indexDao;

    @Autowired
    public void setLogDao(LogDao logDao) {
        this.logDao = logDao;
    }

    @Autowired
    public void setIndexDao(IndexDao indexDao) {
        this.indexDao = indexDao;
    }

    @Override
    public List<LogPO> findLogsByPort(String port) {
        return logDao.findAllByAppPortOrderByCreateDate(port);
    }

    @Override
    public List<LogPO> findLogsByName(String name) {
        ServerPO po = indexDao.findByServerName(name);
        return logDao.findAllByAppPortOrderByCreateDate(po.getServerPort());
    }

    @Override
    public List<LogPO> findLogsByPortAndTime(String port, String time1, String time2) {
        QLogPO po = QLogPO.logPO;
        if (time1 == null && time2 == null) {
            return queryFactory.selectFrom(po).where(po.appPort.eq(port)).fetch();
        } else if (time1 == null) {
            return queryFactory.selectFrom(po).where(po.appPort.eq(port), po.createDate.gt(time2)).fetch();
        } else if (time2 == null) {
            return queryFactory.selectFrom(po).where(po.appPort.eq(port), po.createDate.lt(time1)).fetch();
        }
        return queryFactory.selectFrom(po).where(po.appPort.eq(port), po.createDate.between(time1, time2)).fetch();
    }

    @Override
    public void deleteLogsByPort(String port) {
        List<LogPO> pos = logDao.findAllByAppPortOrderByCreateDate(port);
        logDao.deleteAll(pos);
    }
}
