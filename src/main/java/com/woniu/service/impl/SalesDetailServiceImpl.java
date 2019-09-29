package com.woniu.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woniu.dao.SalesdetailMapper;
import com.woniu.domain.Salesdetail;
import com.woniu.domain.Triple;
import com.woniu.service.ISalesDetailService;
@Service
public class SalesDetailServiceImpl implements ISalesDetailService {
	@Autowired
	private SalesdetailMapper mapper;
	@Override
	public void save(Salesdetail salesdetail) {
		mapper.insertSelective(salesdetail);
	}

	@Override
	public void delete(Integer sdid) {
		mapper.deleteByPrimaryKey(sdid);
	}

	@Override
	public void update(Salesdetail salesdetail) {
		mapper.updateByPrimaryKeySelective(salesdetail);
	}

	@Override
	public List<Salesdetail> findAll() {
		List<Salesdetail> list = mapper.findAllSaleDetail();
		return list;
	}
	
	@Override
	public Map<String,Object> findAll(int currentpage,int pagesize,String pname) {
		
		Map <String,Object> map1=new HashMap<>();
		map1.put("startLine", (currentpage-1)*pagesize);
		map1.put("size",pagesize);
		map1.put("pname","%" + pname + "%");
		// 需要在mapper和XML中加此方法
	//	List<Salesdetail> list = mapper.findAllSaleDetail(map1);
		
		List<Salesdetail> list = mapper.findAllSaleDetail();
		
		int count = mapper.countByExample(null);
		Map <String,Object> map=new HashMap<>();
		map.put("list", list);
		map.put("count", count);
		map.put("pname",pname );
//		List<Salesdetail> list = mapper.selectByExample(null);
		return map;
	}

	@Override
	public Salesdetail findOne(Integer sdid) {
		Salesdetail salesdetail=mapper.selectByPrimaryKey(sdid);
		return salesdetail;
	}

	@Override
	public List<Triple<String, BigDecimal, Double>> findAllBySales() {
		return mapper.findAllBySales();
	}

}
