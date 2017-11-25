package bmu.common.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

//내부적으로 맵을 하나 생성하고 그 맵에 모든 데이터를 다 담는 역할
//map 기본 기능 다시 호출
//map과 똑같이 사용할 수 있도록 getMap 메서드를 추가함
//map을 상속받으면 안 됨
//get, put 메서드만 있어도 큰 문제는 없지만, 맵의 기능들 몇가지 골라서 추가함

public class CommandMap {
	Map<String,Object> map = new HashMap<String,Object>();
	Map<String,String> login_map = new HashMap<String,String>();
	  
    public Object get(String key){
        return map.get(key);
    }
     
    public void put(String key, Object value){
        map.put(key, value);
    }    

    public Object remove(String key){
        return map.remove(key);
    }
     
    public boolean containsKey(String key){
        return map.containsKey(key);
    }
     
    public boolean containsValue(Object value){
        return map.containsValue(value);
    }
     
    public void clear(){
        map.clear();
    }
     
    public Set<Entry<String, Object>> entrySet(){
        return map.entrySet();
    }
     
    public Set<String> keySet(){
        return map.keySet();
    }
     
    public boolean isEmpty(){
        return map.isEmpty();
    }
     
    public void putAll(Map<? extends String, ?extends Object> m){
        map.putAll(m);
    }
     
    public Map<String,Object> getMap(){
        return map;
    }
    
    public Map<String,String> getLoginMap() {
		return login_map;    	    	
    }
    
}




