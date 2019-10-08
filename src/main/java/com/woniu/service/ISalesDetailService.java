package com.woniu.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.woniu.domain.Salesdetail;
import com.woniu.domain.Triple;

public interface ISalesDetailService {
	void save(Salesdetail salesdetail);
	void delete(Integer sdid);
	void update(Salesdetail salesdetail);
	Salesdetail findOne(Integer sdid);
	List<Triple<String, BigDecimal, Double>> findAllBySales();
	
	Map<String,Object> findAll(int currentpage,int pagesize,String pname);
}
