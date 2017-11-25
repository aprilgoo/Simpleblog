<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>bmu</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/layout/include-header.jspf" %>
</head>
<body>
<h2>Search List</h2>
${map.count}개의 게시물이 있습니다.
<form id="frm" name="frm">
<table style="border:1px solid #ccc" class="board_list">
    <colgroup>
        <col width="10%"/>
        <col width="*"/>
        <col width="15%"/>
        <col width="20%"/>
    </colgroup>
    <thead>
        <tr>
            <th scope="col">NO</th>
            <th scope="col">TITLE</th>
            <th scope="col">HIT</th>
            <th scope="col">DATE</th>
        </tr>
    </thead>
    <tbody>       
                <c:forEach items="${map.list}" var="row">
                    <tr>
                        <td>${row.IDX }</td>
                        <td class="title"><a href="#this" name="title">${row.TITLE}</a>      
                        <td>${row.HIT_CNT }</td>
                        <td>${row.CREA_DTM }</td>
                    </tr>
                </c:forEach>        
    </tbody>
</table>	      
<br />
<a href="#this" class="btn" id="write">글쓰기</a>
<select name="opt">
	<option value="all" <c:out value="${map.opt == 'all'?'selected':''}" />>제목+내용</option>
	<option value="title" <c:out value="${map.opt == 'title'?'selected':''}" />>제목</option>
	<option value="contents" <c:out value="${map.opt == 'contents'?'selected':''}" />>내용</option>
</select>
<input size=24 name="keyword" value=" ${map.keyword}"></td> 
<a href="#this" class="btn" id="search" >검색</a>
</form>

<%@include file="/layout/include-body.jspf" %>

<script type="text/javascript">
	$(document).ready(function(){
		$("#write").on("click", function(e){ //글쓰기 버튼
		e.preventDefault();
			fn_openBoardWrite();				
	});	
	
	$("a[name='title']").on("click", function(e){ //제목
		e.preventDefault();
		fn_openBoardDetail($(this));
	});		
	
		$("#search").on("click", function(e){ //검색 버튼
			e.preventDefault();
			fn_SearchBoard();			
	});				
});

function fn_openBoardWrite() {
	var comSubmit = new ComSubmit();
	comSubmit.setUrl("<c:url value='/sample/openBoardWrite.do' />");
	comSubmit.submit();
}

function fn_openBoardDetail(obj){
	var comSubmit = new ComSubmit();
	comSubmit.setUrl("<c:url value='/sample/openBoardDetail.do' />");
	comSubmit.addParam("IDX", obj.parent().find("#IDX").val());
	comSubmit.submit();	
}

function fn_SearchBoard(){
	var comSubmit = new ComSubmit("frm");
	comSubmit.setUrl("<c:url value='/sample/SearchBoard.do' />");	
	comSubmit.submit();	
}
//제목을 클릭하면 fn_openBoardDetail이라는 함수가 실행됨
//인자값으로 $(this)를 넘김 <- 게시글 제목의 <a>태그
//addParam -> 서버로 전송될 키와 값을 인자값으로 받음


</script>
</body>
</html>