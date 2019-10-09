package com.woniu.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniu.dao.ReducepriceMapper;
import com.woniu.domain.Product;
import com.woniu.domain.Reduceprice;
import com.woniu.service.IReducepriceService;
@Service
@Transactional
public class ReducpriceServiceImpl implements IReducepriceService {
	@Autowired
	private ReducepriceMapper mapper;
	@Override
	public void save(Reduceprice reduceprice) {
		mapper.insertSelective(reduceprice);
	}

	@Override
	public void delete(Integer rid) {
   mapper.deleteByPrimaryKey(rid);
	}

	@Override
	public void update(Reduceprice reduceprice) {
     mapper.updateByPrimaryKeySelective(reduceprice);

	}

	@Override
	public Reduceprice select(Integer rid) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(rid);
	}

	@Override
	public List<Reduceprice> select() {
		// TODO Auto-generated method stub
		return mapper.selectByExample(null);
	}

	//使用与截止
	@Override
	public void upAndDown(Integer productid) {
		Reduceprice re=select(productid);
		String others=re.getRothers();
		if(others!=null) {
		if(others.equals("使用中")) {
			re.setRothers("已截止");
			re.setRedate(new Date());
		}else {
			re.setRothers("使用中");
			re.setRsdate(new Date());
			Date date=new Date();
			Calendar no=Calendar.getInstance();
			no.setTime(date);
			no.set(Calendar.DATE,no.get(Calendar.DATE)+15);
			re.setRedate(no.getTime());
		}
		update(re);
	}

}

	@Override
	public List<Reduceprice> select(String name) {
		// TODO Auto-generated method stub
		return mapper.selectProductByPname(name);
	}
	
}
