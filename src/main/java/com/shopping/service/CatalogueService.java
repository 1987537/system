package com.shopping.service;

import com.shopping.pojo.Catalogue;

import java.util.List;

public interface CatalogueService {
    int addCat(Catalogue catalogue);
    int deleteCatById(int cid);
    int updateCatById(Catalogue catalogue);
    Catalogue queryCatByCid(int cid);
    List<Catalogue> queryListCat();
    Catalogue queryLisyByCid(int cid);
}
