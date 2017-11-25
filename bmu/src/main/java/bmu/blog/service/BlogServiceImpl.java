package bmu.blog.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import bmu.blog.dao.BlogDAO;

@Service("blogService")
public class BlogServiceImpl implements BlogService {
	
	@Resource(name="blogDAO")
	private BlogDAO blogDAO;

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, String>> selectPostList(String opt, String keyword) throws Exception {
		return blogDAO.selectPostList(opt, keyword);
	}

}
