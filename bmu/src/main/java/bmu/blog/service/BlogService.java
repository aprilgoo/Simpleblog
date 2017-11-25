package bmu.blog.service;

import java.util.List;
import java.util.Map;

public interface BlogService {

	List<Map<String, String>> selectPostList(String opt, String keyword) throws Exception;

}
