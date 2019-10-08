package com.woniu.web.controller.personnel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniu.domain.Staff;
import com.woniu.service.IStaffService;

@Controller
@RequestMapping("staffs")
public class StaffController {

	
	
	@Autowired
	   private IStaffService service;
	@PostMapping
	@ResponseBody()
	public void save(@RequestBody Staff staff) {
		service.save(staff);
	}
	@DeleteMapping
	@ResponseBody
	public void delete(Integer staffid) {
		service.delete(staffid);
	}
	@PutMapping
	@ResponseBody
	public void update(Staff staff) {
		service.update(staff);
	}
	@GetMapping
	@ResponseBody
	public List<Staff> findAll(){
		System.out.println("222222222222222222222222222222");
		return service.findAll();
		
	}
	@GetMapping(value = "{staffid}")
	@ResponseBody
	public Map<String,Object> findOne(@PathVariable Integer staffid) {
		
		List<Staff> list=new ArrayList<>();
		list.add(service.find(staffid));
		 Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(list);
		map.put("list", list);
		return map;
	}

}
