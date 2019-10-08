package com.woniu.service;

import java.util.List;

import com.woniu.domain.Sales;
import com.woniu.domain.Triple;

public interface ISalesService {
	void save(Sales sales);
	void delete(Integer saleid);
	void update(Sales sales);
	List<Sales> findAll();
	Sales findOne(Integer saleid);
	
	List<Triple<String,Long, Double>> saleRank();
}
