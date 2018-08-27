package me.maoburu.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import me.maoburu.constant.Constants;
import me.maoburu.dao.ItemDao;
import me.maoburu.pojo.Item;
import me.maoburu.pojo.Page;
import me.maoburu.pojo.Video;
import me.maoburu.service.ItemService;

@Service
public class itemServiceImpl implements ItemService {
	
	@Resource
	private ItemDao itemDao;
	
	@Override
	public Page listItem(String name, String status, String charge, String type, int pageNum, int size) {
		PageHelper.startPage(pageNum, size);
		List<Item> itemList = itemDao.listItem(name, status, charge, type);
		Long total = new PageInfo(itemList).getTotal();
		Page page = new Page();
		page.setRows(itemList);
		return page;
	}

	@Override
	public Page ajaxListRelateVideo(String id, String name, String status, int pageNum, int size) {
		PageHelper.startPage(pageNum, size);
		List<Video> videoList = itemDao.ajaxListRelateVideo(id, name, status);
		long total = new PageInfo(videoList).getTotal();
		Page page = new Page();
		page.setRows(videoList);
		page.setTotal(total);
		return page;
	}

	@Override
	public Item getItemById(String id) {
		return itemDao.getItemById(id);
	}

	@Override
	public int addOrUpdateItem(Item item, String operation) {
		if ("update".equals(operation)) {
			return itemDao.updateItem(item);
		} else {
			item.setId(UUID.randomUUID().toString().replace("-", ""));
			if (item.getOnlineTime() == null) {
				item.setOnlineTime(new Date());
			}
			if (item.getOfflineTime() == null) {
				Date date;
				try {
					date = new SimpleDateFormat("yyyy-MM-dd").parse(Constants.DEFAULT_OFFLINE_DATE);
					item.setOfflineTime(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			return itemDao.addItem(item);
		}
	}

	@Override
	public Page listUnrelateVideo(String name, int pageNum, int size) {
		PageHelper.startPage(pageNum, size);
		List<Video> itemList = itemDao.listUnrelateVideo(name);
		long total = new PageInfo(itemList).getTotal();
		Page page = new Page();
		page.setRows(itemList);
		page.setTotal(total);
		return page;
	}

	@Override
	public int relateVideo(String id, String parentId) {
		return itemDao.relateVideo(id, parentId);
	}

	@Override
	@Transactional
	public int upVideo(String id, String parentId, int position) {
		if (position == 1){
			return -1;
		}
		itemDao.setItemPosition(null, parentId, position - 1, position);
		itemDao.setItemPosition(id, parentId, position, position - 1);
		return 1;
	}
	
	@Override
	@Transactional
	public int downVideo(String id, String parentId, int position) {
		int maxPosition = itemDao.getMaxPosition(parentId);
		if (position == maxPosition) {
			return -1;
		}
		itemDao.setItemPosition(null, parentId, position + 1, position);
		itemDao.setItemPosition(id, parentId, position, position + 1);
		return 1;
	}

	@Override
	public int removeRelateVideo(String id) {
		return itemDao.removeRelateVideo(id);
	}

	@Override
	public int ajaxUpdateItemCharge(String id, String charge) {
		return itemDao.updateItemCharge(id, charge);
	}

	@Override
	public int ajaxUpdateItemStatus(String id, String status) {
		return itemDao.updateItemStatus(id, status);
	}

	@Override
	public int ajaxUpdatePoster(String id, String poster) {
		return itemDao.updatePoster(id, poster);
	}

	
	
}
