package bmu.common.logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//인터셉터는 HandlerInterceptorAdapter 클래스를 상속받아서 만듦
//전처리기와 후처리기 구현
//전처리기 : client -> controller
//후처리기 : controller -> client


public class LoggerInterceptor extends HandlerInterceptorAdapter {	
	protected Log log = LogFactory.getLog(LoggerInterceptor.class);
	//Log4j의 Log 객체를 log라는 이름으로 생성
	//생성자에 현재 클래스 입력
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("-------------------------- Start -----------------------------");
			log.debug("Request URI \t:    " + request.getRequestURI());		
			//현재 호출된 URI가 무엇인지 보여줌
		}
		return super.preHandle(request, response, handler);
		
	}
		
		 @Override
		    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		        if (log.isDebugEnabled()) {
		            log.debug("-------------------------- End -----------------------------\n");
		    		        
		        }
		        
		        
		    }		
	}	

// preHandle과 postHandle 메서드가 전처리기와 후처리기에 해당됨


