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
import com.woniu.domain.Vip;
import com.woniu.service.IVipService;

@Controller
@RequestMapping("vips")
public class VipController {

@Autowired
   private IVipService service;
@PostMapping
@ResponseBody()
public void save(@RequestBody Vip vip) {
	service.save(vip) ;
}
@DeleteMapping
@ResponseBody
public void delete(Integer vipid) {
	service.delete(vipid);
}
@PutMapping
@ResponseBody
public void update(Vip vip) {
	service.update(vip);
}
@GetMapping
@ResponseBody
public List<Vip> findAll(){
	return service.findAll();
}
@GetMapping(value="{vipphone}")
@ResponseBody
public Map<String, Object> findOne(@PathVariable Integer vipphone) {
	List<Vip> list=new ArrayList<>();
	list.add(service.find(vipphone));
	 Map<String, Object> map = new HashMap<String, Object>();
	System.out.println(list);
	map.put("list", list);
	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!1");
	System.out.println(map);
	return  map;
}

}
