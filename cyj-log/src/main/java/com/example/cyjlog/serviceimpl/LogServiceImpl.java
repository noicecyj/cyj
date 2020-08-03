package com.example.cyjlog.serviceimpl;

import com.example.cyjlog.dao.LogDao;
import com.example.cyjlog.dao.ServerDao;
import com.example.cyjlog.entity.LogPO;
import com.example.cyjlog.entity.QLogPO;
import com.example.cyjlog.entity.ServerPO;
import com.example.cyjlog.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020/1/21 14:46
 */
@Service
public class LogServiceImpl extends BaseService implements LogService {

    private LogDao logDao;
    private ServerDao serverDao;

    @Autowired
    public void setLogDao(LogDao logDao) {
        this.logDao = logDao;
    }

    @Autowired
    public void setServerDao(ServerDao serverDao) {
        this.serverDao = serverDao;
    }

    @Override
    public List<LogPO> findLogsByPort(String port) {
        return logDao.findAllByAppPort(port);
    }

    @Override
    public List<LogPO> findLogsByName(String name) {
        ServerPO po = serverDao.findByServerName(name);
        return logDao.findAllByAppPort(po.getServerPort());
    }

    @Override
    public List<LogPO> findLogsByPortAndTime(String port, String time1, String time2, String type) {
        QLogPO po = QLogPO.logPO;
        String type1 = "1";
        String type2 = "2";
        if (type1.equals(type)) {
            return queryFactory.selectFrom(po).where(po.appPort.eq(port), po.createDate.lt(time1)).fetch();
        } else if (type2.equals(type)) {
            return queryFactory.selectFrom(po).where(po.appPort.eq(port), po.createDate.gt(time2)).fetch();
        }
        return queryFactory.selectFrom(po).where(po.appPort.eq(port), po.createDate.between(time1,time2)).fetch();
    }
}
