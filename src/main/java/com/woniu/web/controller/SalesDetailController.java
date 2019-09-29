package com.woniu.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woniu.domain.Salesdetail;
import com.woniu.service.ISalesDetailService;

@RestController
@RequestMapping("salesDetail")
public class SalesDetailController {
	@Autowired ISalesDetailService service;
	@PostMapping
	public void save(@RequestBody Salesdetail salesdetail) {
		service.save(salesdetail);
	}
	@DeleteMapping
	public void delete(Integer sdid) {
		service.delete(sdid);
	}
	@PutMapping("update")
	public void update(Salesdetail salesdetail) {
		System.out.println("SalesDetailController.update()");
		System.out.println("xxxx "+salesdetail);
		service.update(salesdetail);
	}
	@GetMapping(value="{sdid}")
	public Salesdetail findOne(Integer sdid ) {
		Salesdetail salesdetail = service.findOne(sdid);
		return salesdetail;
	}
	@PostMapping("/findAll")
	public Map<String,Object> findAll(int currentpage,int pagesize,String pname) {
		System.out.println("SalesDetailController.findAll()"+currentpage+"  "+pagesize+" "+pname);
		Map<String, Object> map = service.findAll(currentpage,pagesize,pname);
		System.out.println(map);
		return map;
	}
	
}
