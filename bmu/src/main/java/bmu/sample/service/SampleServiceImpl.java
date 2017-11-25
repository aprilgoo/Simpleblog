package bmu.sample.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import bmu.common.util.FileUtils;
import bmu.sample.dao.SampleDAO;
 
@Service("sampleService")
public class SampleServiceImpl implements SampleService{
	Logger log = Logger.getLogger(this.getClass());	
			
	
	@Resource(name="fileUtils")
    private FileUtils fileUtils;

	@Resource(name="sampleDAO")
	private SampleDAO sampleDAO;

	@Override
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) 
			throws Exception {
		return sampleDAO.selectBoardList(map);
	}
	// service 인터페이스를 통해 정의된 메서드를 실제로 구현
	//@service 어노테이션을 통해서 service 객체임을 선언함
	// 객체의 이름은 sampleService임을 선언

	@Override
	public void insertBoard(Map<String, Object> map, HttpServletRequest request) throws Exception {
	    sampleDAO.insertBoard(map);
	    
	    List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(map, request);
        for(int i=0, size=list.size(); i<size; i++){
            sampleDAO.insertFile(list.get(i));
        }	  
	}

	@Override
	public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception {
		sampleDAO.updateHitCnt(map);
		Map<String, Object> resultMap = new HashMap<String,Object>();
		Map<String, Object> tempMap = sampleDAO.selectBoardDetail(map);
		//게시물의 상세 정보를 가져옴
		resultMap.put("map", tempMap);
		//결과 값 저장
		List<Map<String,Object>> list = sampleDAO.selectFileList(map);
		resultMap.put("list", list);
		//첨부파일 목록 저장
		
		return resultMap;
	}
	@Override
	public void updateBoard(Map<String, Object> map, HttpServletRequest request) throws Exception {
		sampleDAO.updateBoard(map);
		
		sampleDAO.deleteFileList(map);
		List<Map<String,Object>>list = fileUtils.parseUpdateFileInfo(map, request);
		Map<String,Object> tempMap = null;
		for(int i=0, size=list.size(); i<size; i++) {
			
			tempMap = list.get(i);
			if(tempMap.get("IS_NEW").equals("Y")) {
				sampleDAO.insertFile(tempMap);
				}
			else {
				sampleDAO.updateFile(tempMap);
			}
			
		}
		
	}

	@Override
	public void deleteBoard(Map<String, Object> map) throws Exception {
		sampleDAO.deleteBoard(map);
		
	}

	@Override
	public int countArticle(String opt, String keyword) throws Exception {
		return sampleDAO.countArticle(opt, keyword);
	}

	@Override
	public List<Map<String, String>> searchBoard(String opt, String keyword) throws Exception {
		 return sampleDAO.searchBoard(opt, keyword);
	}


	
		
}
