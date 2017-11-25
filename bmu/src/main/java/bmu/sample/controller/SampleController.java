package bmu.sample.controller;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bmu.common.common.CommandMap;
import bmu.sample.service.SampleService;


@Controller
// Controller 객체 선언
// 웹 클라이언트에서 들어온 요청을 해당 비즈니스 로직을 호출하고,
// 수행결과와 함께 응답을 해주는 Dispatcher 역할을 한다
public class SampleController {
	Logger log = Logger.getLogger(this.getClass());
	// Log4j 로그를 선언

		
	@Resource(name="sampleService")
    private SampleService sampleService;
	// Service 영역의 접근을 위한 선언
	// 필요한 bean을 수동으로 등록(sampleService)	

     
    @RequestMapping(value="/sample/openBoardList.do")
    public ModelAndView openSampleBoardList(@RequestParam(defaultValue="TITLE") String opt, @RequestParam(defaultValue="") String keyword, Map<String,Object>commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("/sample/boardList");
         
       /** 
        * 원래 게시판 리스트 불러오는 소스
        * 
        * List<Map<String,Object>> list = sampleService.selectBoardList(commandMap);
        mv.addObject("list", list);        
        return mv; 
       **/    
       
        /**  
         * 검색 기능 추가한 리스트 소스 
         * 
         **/
        
        List<Map<String, String>> list = sampleService.searchBoard(opt, keyword);
     //   int count = sampleService.countArticle(opt, keyword);      
       
    	Map<String,Object>map = new HashMap<String, Object>();
    	map.put("list", list);
    //	map.put("count", count);
    	map.put("opt", opt);
    	map.put("keyword", keyword);
    	mv.addObject("map", map);
    	mv.setViewName("/sample/boardList");
    	
        return mv;
        
    }
    
    @RequestMapping(value="/sample/openBoardWrite.do")
    public ModelAndView openBoardWrite(CommandMap commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("/sample/boardWrite");
         
        return mv;
    }
    
    @RequestMapping(value="/sample/insertBoard.do")
	public ModelAndView insertBoard(CommandMap commandMap, HttpServletRequest request) throws Exception {
	
	ModelAndView mv = new ModelAndView("redirect:/sample/openBoardList.do");
	
	sampleService.insertBoard(commandMap.getMap(), request);
	
	return mv;    	
}
    //첨부파일은 commandMap에서 처리하지 않았기 때문에 httpServletRequest를 추가로 받도록 해줌
    
    
    @RequestMapping(value="/sample/openBoardDetail.do")
    public ModelAndView openBoardDetail(CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/sample/boardDetail");
    	
    	Map<String,Object> map = sampleService.selectBoardDetail(commandMap.getMap());    	
    	mv.addObject("map", map.get("map"));
    	mv.addObject("list", map.get("list"));
    	
		return mv;
    	  	
    }
    
    // 게시물 수정 페이지 열기
    @RequestMapping(value="/sample/openBoardUpdate.do")
    
    public ModelAndView openBoardUpdate(CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("/sample/boardUpdate");
    	
    	Map<String,Object> map = sampleService.selectBoardDetail(commandMap.getMap());
    	mv.addObject("map", map.get("map"));
    	mv.addObject("list", map.get("list"));
    	
    	return mv;    	
    }
    
    
    // 게시물 수정 기능
    @RequestMapping(value="/sample/updateBoard.do")
    
    public ModelAndView updateBoard(CommandMap commandMap, HttpServletRequest request) throws Exception {
    	ModelAndView mv = new ModelAndView("redirect:/sample/openBoardDetail");
    	
    	sampleService.updateBoard(commandMap.getMap(), request);
    	
    	// IDX를 기준으로 
    	mv.addObject("IDX", commandMap.get("IDX"));
    	return mv;
    }
    
    
    // 게시물 삭제 기능
    @RequestMapping(value="/sample/deleteBoard.do")
    
    public ModelAndView deleteBoard(CommandMap commandMap) throws Exception {
    	ModelAndView mv = new ModelAndView("redirect:/sample/openBoardList.do");
    	 
    	sampleService.deleteBoard(commandMap.getMap());
    	return mv;
    	
    }    
    
}
    













    
    