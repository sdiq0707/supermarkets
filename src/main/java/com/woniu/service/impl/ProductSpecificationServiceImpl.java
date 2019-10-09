package com.woniu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniu.dao.ProductspecificationMapper;
import com.woniu.domain.Productspecification;
import com.woniu.service.IProductSpecificationService;
@Service
@Transactional
public class ProductSpecificationServiceImpl implements IProductSpecificationService {
	@Autowired
	private ProductspecificationMapper mapper;
	@Override
	public List<Productspecification> findAll() {
		List<Productspecification> list = mapper.selectByExample(null);
		return list;
	}
	@Override
	public void update(Productspecification productspecification) {
		mapper.updateByPrimaryKeySelective(productspecification);
	}

}
