package com.woniu.service;

import java.util.List;

import com.woniu.domain.Productspecification;

public interface IProductSpecificationService {
	List<Productspecification> findAll();
	void update(Productspecification productspecification);
}
