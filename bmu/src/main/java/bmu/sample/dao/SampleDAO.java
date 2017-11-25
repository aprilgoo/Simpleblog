package bmu.sample.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import bmu.common.dao.AbstractDAO;
 
@Repository("sampleDAO")
// @Repository로 이 클래스가 DAO임을 선언
public class SampleDAO extends AbstractDAO{
	
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception{
        return (List<Map<String, Object>>)selectList("sample.selectBoardList", map);

        //selectList는 MyBatis의 기본 기능 : 리스트를 조회할 때 사용
        //메서드의 인자는 두 가지로 첫번째는 쿼리 이름, 두 번째는 쿼리가 실행되는데 필요한 변수들
        //sample.selectBoardList가 쿼리 이름이며, Map<String,Object> map이 쿼리 실행시 필요한 변수들임
        //결과값은 형변환을 함
    }

	public void insertBoard(Map<String, Object> map) throws Exception {
		insert("sample.insertBoard", map);		
	}
	
	public void updateHitCnt(Map<String, Object>map) throws Exception {
		update("sample.updateHitCnt", map);		
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("sample.selectBoardDetail", map);
	}    
	//게시글의 상세내용을 조회하고 해당 게시글의 조회수를 1 증가시킴

	public void updateBoard(Map<String, Object> map) throws Exception {
		update("sample.updateBoard", map);
		
	}

	public void deleteBoard(Map<String, Object> map) throws Exception {
		update("sample.deleteBoard", map);		
	}
	
	public void insertFile(Map<String, Object> map) throws Exception{
		insert("sample.insertFile", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectFileList(Map<String, Object> map) throws Exception {
		
		return (List<Map<String, Object>>)selectList("sample.selectFileList", map);
		//selectFile 쿼리를 호출
	}
	
	public void deleteFileList(Map<String, Object> map) throws Exception{
		update("sample.deleteFileList", map);
	}

	public void updateFile(Map<String, Object> map) throws Exception{
		update("sample.updateFile", map);
	}

	public int countArticle(String opt, String keyword) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("opt", opt);
		map.put("keyword", keyword);
		return (int)selectOne("sample.countArticle", map);		
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, String>>searchBoard(String opt, String keyword) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("opt", opt);
		map.put("keyword", keyword);
		return (List<Map<String, String>>)selectList("sample.searchBoard", map);
	}
}


















