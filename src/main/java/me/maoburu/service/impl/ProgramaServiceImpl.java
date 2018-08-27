package me.maoburu.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import me.maoburu.constant.Constants;
import me.maoburu.dao.ProgramaDao;
import me.maoburu.pojo.Item;
import me.maoburu.pojo.Page;
import me.maoburu.pojo.Programa;
import me.maoburu.service.ProgramaService;

@Service
public class ProgramaServiceImpl implements ProgramaService{

	@Resource
	private ProgramaDao programaDao;
	
	@Override
	public Page listMainPrograma(int pageNum, int size) {
		PageHelper.startPage(pageNum, size);
		List<Programa> programaList = programaDao.listMainPrograma();
		long total = new PageInfo(programaList).getTotal();
		Page page = new Page();
		page.setRows(programaList);
		page.setTotal(total);
		return page;
	}
	
	@Override
	public Page listPrograma(String id, int pageNum, int size) {
		PageHelper.startPage(pageNum, size);
		List<Programa> programaList = programaDao.listPrograma(id);
		long total = new PageInfo(programaList).getTotal();
		Page page = new Page();
		page.setRows(programaList);
		page.setTotal(total);
		return page;
	}

	@Override
	public Page listRelateItem(String id, String name, String status, int pageNum, int size) {
		PageHelper.startPage(pageNum, size);
		List<Item> programaList = programaDao.listRelateItem(id, name, status);
		long total = new PageInfo(programaList).getTotal();
		Page page = new Page();
		page.setRows(programaList);
		page.setTotal(total);
		return page;
	}

	@Override
	public Programa getProgramaById(String id) {
		return programaDao.getProgramabyId(id);
	}

	@Override
	@Transactional
	public int addOrUpdatePrograma(Programa programa, String operation) {
		if ("update".equals(operation)) {
			return programaDao.updatePrograma(programa);
		} else {
			programa.setId(UUID.randomUUID().toString().replace("-", ""));
			if (programa.getOnlineTime() == null) {
				programa.setOnlineTime(new Date());
			}
			if (programa.getOfflineTime() == null) {
				Date date;
				try {
					date = new SimpleDateFormat("yyyy-MM-dd").parse(Constants.DEFAULT_OFFLINE_DATE);
					programa.setOfflineTime(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			return programaDao.addPrograma(programa);
		}
	}

	

	@Override
	public Page list(int pageNum, int size) {
		PageHelper.startPage(pageNum, size);
		List<Programa> programaList = programaDao.list();
		long total = new PageInfo(programaList).getTotal();
		Page page = new Page();
		page.setRows(programaList);
		page.setTotal(total);
		return page;
	}

	@Override
	public int relatePrograma(String id, String parentId) {
		if (programaDao.checkRelatePrograma(id, parentId) == 0) {
			int position = programaDao.getMaxPosition(parentId, "0") + 1;
			return programaDao.relatePrograma(id, parentId, position);
		} 
		return -1;
	}

	@Override
	public Page listItem(String name, int pageNum, int size) {
		PageHelper.startPage(pageNum, size);
		List<Item> programaList = programaDao.listItem(name);
		long total = new PageInfo(programaList).getTotal();
		Page page = new Page();
		page.setRows(programaList);
		page.setTotal(total);
		return page;
	}

	@Override
	public int relateItem(String id, String parentId) {
		if (programaDao.checkRelateItem(id, parentId) == 0) {
			int position = programaDao.getMaxPosition(parentId, "1") + 1;
			return programaDao.relateItem(id, parentId, position);
		} 
		return -1;
	}
	
	@Transactional
	@Override
	public int upPrograma(String id, String parentId, int position) {
		if (position == 1){
			return -1;
		}
		programaDao.setProgramaPosition(null, parentId, position - 1, position);
		programaDao.setProgramaPosition(id, parentId, position, position - 1);
		return 1;
	}

	@Override
	public int downPrograma(String id, String parentId, int position) {
		int maxPosition = programaDao.getMaxPosition(parentId, "0");
		if (position == maxPosition) {
			return -1;
		}
		programaDao.setProgramaPosition(null, parentId, position + 1, position);
		programaDao.setProgramaPosition(id, parentId, position, position + 1);
		return 1;
	}

	@Override
	@Transactional
	public int removeRelate(String id, String parentId,int position, String markId) {
		
		int result = programaDao.removeRelate(id, parentId, markId);
		
		int updateResult = programaDao.updateOtherPosition(id, parentId, position, markId);
		
		if (result != -1 && updateResult != -1) {
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public int upItem(String id, String parentId, int position) {
		if (position == 1){
			return -1;
		}
		programaDao.setItemPosition(null, parentId, position - 1, position);
		programaDao.setItemPosition(id, parentId, position, position - 1);
		return 1;
	}
	
	@Override
	public int downItem(String id, String parentId, int position) {
		int maxPosition = programaDao.getMaxPosition(parentId, "1");
		if (position == maxPosition) {
			return -1;
		}
		programaDao.setItemPosition(null, parentId, position + 1, position);
		programaDao.setItemPosition(id, parentId, position, position + 1);
		return 1;
	}

	@Override
	public String getItemById(String id, String parentId, String type) {
		Map<String, String> map = programaDao.getItemById(id, parentId);
		if (map == null) { 
			return null;
		}
		return map.get(type);
	}

	@Override
	public int updateRelatePoster(String id, String parentId, String type, String poster) {
		return  programaDao.updateRelatePoster(id, parentId, type, poster);
	}
}
