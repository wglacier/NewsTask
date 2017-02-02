package com.glacier.news.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.glacier.news.dao.NewsItemDao;
import com.glacier.news.model.NewsItem;


@RestController
public class NewsItemRestController {

	private NewsItemDao newsItemDao = new NewsItemDao();
	
	/**
	 * display default page for demo
	 * @return
	 */
	@GetMapping("/")
	public ModelAndView defaultPage() {
		//return USAGE_PAGE;
		return new ModelAndView("index");  
	}

	/**
	 * display the retrieved content for demo
	 * @param searchText
	 * @return
	 */
	@GetMapping("/search/ui/{text}")
	@ResponseBody
	public ModelAndView searchByKeywordUi(@PathVariable("text") String searchText) {

		List<NewsItem> retList = newsItemDao.searchByKeywork(searchText);

		return new ModelAndView("contentSearch", "articles", retList);
	}
	
	/**
	 * for api calls to search by keyword
	 * @param searchText
	 * @return
	 */
	@GetMapping("/search/api/{text}")
	@ResponseBody
	public ResponseEntity<List<NewsItem>> searchByKeywordApi(@PathVariable("text") String searchText) {

		List<NewsItem> retList = newsItemDao.searchByKeywork(searchText);

		return new ResponseEntity<List<NewsItem>>(retList, HttpStatus.OK);
	}
}