package com.woniu.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woniu.dao.SalesMapper;
import com.woniu.domain.Sales;
import com.woniu.domain.Triple;
import com.woniu.service.ISalesService;
@Service
public class SalesServiceImpl implements ISalesService {
	@Autowired
	private SalesMapper mapper;
	@Override
	public void save(Sales sales) {
		mapper.insertSelective(sales);
	}

	@Override
	public void delete(Integer saleID) {
		mapper.deleteByPrimaryKey(saleID);
	}

	@Override
	public void update(Sales sales) {
		mapper.updateByPrimaryKeySelective(sales);
	}

	@Override
	public List<Sales> findAll() {
		List<Sales> list = mapper.selectFindAll();
		return list;
	}

	@Override
	public Sales findOne(Integer saleid) {
		Sales sales = mapper.selectByPrimaryKey(saleid);
		return sales;
	}

	@Override
	public List<Triple<String, Long, Double>> saleRank() {
		return mapper.saleRank();
	}

	@Override
	public List<Triple<String, Long, Double>> businessAnalysis(Integer year, Integer month) {
		return mapper.businessAnalysis(year,month);
	}


}
