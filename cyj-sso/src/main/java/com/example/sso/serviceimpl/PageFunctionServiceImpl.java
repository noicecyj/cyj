package com.example.sso.serviceimpl;
import com.example.sso.dao.PageFunctionDao;
import com.example.sso.entity.PageFunctionPO;
import com.example.sso.service.PageFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
/**
 * @author 曹元杰
 * @version 1.0
 * @date 2020-08-09
 */
@Service
public class PageFunctionServiceImpl extends BaseService implements PageFunctionService {

    private PageFunctionDao pageFunctionDao;

    @Autowired
    public void setPageFunctionDao(PageFunctionDao pageFunctionDao) {
        this.pageFunctionDao = pageFunctionDao;
    }

    @Override
    public PageFunctionPO addOne(PageFunctionPO po) {
        return pageFunctionDao.save(po);
    }

    @Override
    public void deleteOne(String id) {
        pageFunctionDao.deleteById(id);
    }

    @Override
    public PageFunctionPO updateOne(PageFunctionPO po) {
        return pageFunctionDao.saveAndFlush(po);
    }

    @Override
    public Page<PageFunctionPO> findAll(String id, Integer pageNumber, Integer pageSize, String sortCode) {
        Sort sort = Sort.by(sortCode);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return pageFunctionDao.findAll(pageable);
    }

}
