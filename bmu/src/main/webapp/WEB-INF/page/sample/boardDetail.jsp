<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/layout/include-header.jspf" %>
</head>
<body>
    <table class="board_view">
        <colgroup>
            <col width="15%"/>
            <col width="35%"/>
            <col width="15%"/>
            <col width="35%"/>
        </colgroup>
        <caption>게시글 상세</caption>
        <br />
        <br />
        <tbody>
            <tr>
                <th scope="row">글 번호</th>
                <td>${map.IDX }</td>
                 <input type="hidden" id="IDX" name="IDX" value="${map.IDX }">                
                <th scope="row">조회수</th>
                <td>${map.HIT_CNT}</td>
            </tr>
            <tr>
                <th scope="row">작성자</th>
                <td>${map.CREA_ID}</td>
                <th scope="row">작성시간</th>
                <td>${map.CREA_DTM}</td>
            </tr>
            <tr>
                <th scope="row">제목</th>
                <td colspan="3">${map.TITLE}</td>
            </tr>
            <tr>
                <td colspan="4">${map.CONTENTS}</td>
            </tr>
            <tr>
            	<th scope="row">첨부파일</th>
            	<td colspan="3">
            		<c:choose> 
            		<c:when test = "${fn:length(list) > 0}">
            			<c:forEach var="row" items="${list}">            				
            				<input type="hidden" id="IDX" value="${row.IDX }">
            				
            				<a href="#this" name="file">${row.ORIGINAL_FILE_NAME }</a>
            				(${row.FILE_SIZE }kb)
            				<br />
            			</c:forEach>
            			</c:when>
            			<c:otherwise>            			
            				첨부 파일이 없습니다.          			           			
            			</c:otherwise>
            		</c:choose>
            	</td>
            </tr>
        </tbody>
    </table>
     <br />
     
    <a href="#this" class="btn" id="list">목록으로</a>
    <a href="#this" class="btn" id="update">수정하기</a>
    <a href="#this" class="btn" id="delete">삭제하기</a>
     
    <%@ include file="/layout/include-body.jspf" %>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#list").on("click", function(e){ //목록으로 버튼
                e.preventDefault();
                fn_openBoardList();
            });
             
            $("#update").on("click", function(e){ //수정하기 버튼
                e.preventDefault();
                fn_openBoardUpdate();
            });         
            
            $("#delete").on("click", function(e){ //삭제하기 버튼
                e.preventDefault();
                fn_deleteBoard();
            });
            
            $("a[name='file']").on("click", function(e){ //파일 이름
            	e.preventDefault();       
            	fn_downloadFile($(this));
            });
              
        });
         
        function fn_openBoardList(){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='/sample/openBoardList.do' />");
            comSubmit.submit();
        }
         
        function fn_openBoardUpdate(){
            var idx = "${map.IDX}";
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='/sample/openBoardUpdate.do' />");
            comSubmit.addParam("IDX", idx);
            comSubmit.submit();
        }      
        
        function fn_deleteBoard(){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='/sample/deleteBoard.do' />");
            comSubmit.addParam("IDX", $("#IDX").val());
            comSubmit.submit();
            
        }
        
        function fn_downloadFile(obj) {
        	var idx = obj.parent().find("#IDX").val();
        	var comSubmit = new ComSubmit();
        	$("#commonForm").children().remove();
        	comSubmit.setUrl("<c:url value='/common/downloadFile.do' />");
        	comSubmit.addParam("IDX", idx);
        	comSubmit.submit();
        	
        }
        
    </script>
</body>
</html>