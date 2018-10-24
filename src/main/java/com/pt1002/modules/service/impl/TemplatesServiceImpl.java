package com.pt1002.modules.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pt1002.modules.mapper.TemplatesMapper;
import com.pt1002.modules.pojo.Templates;
import com.pt1002.modules.pojo.TemplatesExample;
import com.pt1002.modules.pojo.TemplatesWithBLOBs;
import com.pt1002.modules.pojo.query.TemQuery;
import com.pt1002.modules.service.TemplatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class TemplatesServiceImpl implements TemplatesService {

    @Autowired
    TemplatesMapper templatesMapper;

    @Override
    public TemQuery findPage(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<TemplatesWithBLOBs> templates = templatesMapper.selectByExampleWithBLOBs(null);
        for (TemplatesWithBLOBs template : templates) {
                if (template.getTemplate()!=null){
                    template.setbTemplate(true);
                }
                if (template.getHiRes()!=null){
                    template.setbHiRes(true);
                }
                if (template.getHiResTex()!=null){
                    template.setbHiResTex(true);
                }
        }
        PageInfo<TemplatesWithBLOBs> info = new PageInfo<TemplatesWithBLOBs>(templates);

        TemQuery temQuery = new TemQuery();
        temQuery.setTotal(info.getTotal());
        temQuery.setRows(info.getList());
        return temQuery;
    }

    @Override
    public void temDelete(String ids) {
        String[] split = ids.split(",");
        for (String s : split) {
            Integer id = Integer.valueOf(s);
            templatesMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public void temUpdate(TemplatesWithBLOBs templates) {
        templatesMapper.updateByPrimaryKeyWithBLOBs(templates);
    }

    @Override
    public TemQuery findAll(String id, Integer page, Integer rows) {
        if (page==null && page==0)
            page=1;
        if (rows==null && rows==0)
            rows=10;
        PageHelper.startPage(page,rows);
        TemplatesExample example = new TemplatesExample();
        TemplatesExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(id);
        List<TemplatesWithBLOBs> bs = templatesMapper.selectByExampleWithBLOBs(example);
        PageInfo<TemplatesWithBLOBs> info = new PageInfo<>(bs);
        TemQuery temQuery = new TemQuery();
        temQuery.setRows(info.getList());
        temQuery.setPage(page);
        temQuery.setRecords(info.getTotal());
        temQuery.setTotal(info.getTotal()%Long.valueOf(rows)==0?info.getTotal()/Long.valueOf(rows):info.getTotal()/Long.valueOf(rows)+1);
        return temQuery;
    }
}
