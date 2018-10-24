package com.pt1002.modules.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pt1002.modules.mapper.ImsiMapper;
import com.pt1002.modules.pojo.Imsi;
import com.pt1002.modules.pojo.query.ImsiQuery;
import com.pt1002.modules.service.ImsiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ImsiServiceImpl implements ImsiService {
    @Autowired
    ImsiMapper imsiMapper;
    @Override
    public ImsiQuery findImsi(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<Imsi> imsis = imsiMapper.selectByExample(null);
        PageInfo<Imsi> info = new PageInfo<>(imsis);
        ImsiQuery imsiQuery = new ImsiQuery();
        imsiQuery.setTotal(info.getTotal());
        imsiQuery.setRows(info.getList());
        return imsiQuery;
    }

    @Override
    public int deleteImsi(String ids) {
        String[] split = ids.split(",");
        for (String s : split) {
            Integer id = Integer.valueOf(s);
           imsiMapper.deleteByPrimaryKey(id);
        }
        return 1;
    }

    @Override
    public int updateImsi(Imsi imsi) {
       return imsiMapper.updateByPrimaryKeySelective(imsi);
    }

    @Override
    public List<Imsi> findAll(Long recordId) {

        List<Imsi> imsis = imsiMapper.selectByCondition(recordId,null);
        return imsis;
    }

    @Override
    public ImsiQuery findAll(Long recordId, String imsi, Integer page, Integer rows) {
        if (page==null || page==0)
            page=1;
        if (rows == null || rows==0)
            rows=10;
        PageHelper.startPage(page,rows);
        List<Imsi> imsis = imsiMapper.selectByCondition(recordId,imsi);
        PageInfo<Imsi> info = new PageInfo<>(imsis);
        ImsiQuery imsiQuery = new ImsiQuery();
        imsiQuery.setPage(page.longValue());
        imsiQuery.setRecords(info.getTotal());
        imsiQuery.setTotal(info.getTotal()%Long.valueOf(rows)==0?info.getTotal()/Long.valueOf(rows):info.getTotal()/Long.valueOf(rows)+1);
        imsiQuery.setRows(info.getList());
        return imsiQuery;
    }
}
