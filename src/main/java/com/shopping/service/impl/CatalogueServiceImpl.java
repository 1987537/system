package com.shopping.service.impl;

import com.shopping.mapper.CatalogueMapper;
import com.shopping.pojo.Catalogue;
import com.shopping.service.CatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CatalogueServiceImpl implements CatalogueService {
    @Autowired
    private CatalogueMapper catalogueMapper;
    @Override
    public int addCat(Catalogue catalogue) {
        return catalogueMapper.addCat(catalogue);
    }

    @Override
    public int deleteCatById(int cid) {
        return catalogueMapper.deleteCatById(cid);
    }

    @Override
    public int updateCatById(Catalogue catalogue) {
        return catalogueMapper.updateCatById(catalogue);
    }

    @Override
    public Catalogue queryCatByCid(int cid) {
        return catalogueMapper.queryCatByCid(cid);
    }

    @Override
    public List<Catalogue> queryListCat() {
        return catalogueMapper.queryListCat();
    }

    @Override
    public Catalogue queryLisyByCid(int cid) {
        return catalogueMapper.queryLisyByCid(cid);
    }
}
