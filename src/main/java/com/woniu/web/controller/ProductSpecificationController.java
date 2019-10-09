package com.woniu.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniu.domain.Productspecification;
import com.woniu.service.IProductSpecificationService;

@Controller
@RequestMapping("productspecification")
public class ProductSpecificationController {
	@Autowired
	private IProductSpecificationService service;
	@ResponseBody
	@GetMapping
	public List<Productspecification> findAll() {
		List<Productspecification> list = service.findAll();
		return list;
	}
	@ResponseBody
	@PutMapping
	public void update(Productspecification productspecification) {
		service.update(productspecification);
	}
}
