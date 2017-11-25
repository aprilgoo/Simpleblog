package bmu.sample.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface SampleService {
	// 비즈니스 로직의 수행을 위한 메서드를 정의
	
	List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception;
	//throws Exception : 추후 에러처리를 위함
	
	List<Map<String, String>> searchBoard(String opt, String keyword) throws Exception;
	
	public int countArticle(String opt, String keyword) throws Exception;

	void insertBoard(Map<String, Object> map, HttpServletRequest request) throws Exception;

	Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception;

	void updateBoard(Map<String, Object> map, HttpServletRequest request) throws Exception;

	void deleteBoard(Map<String, Object> map) throws Exception;		

	
	

	}
