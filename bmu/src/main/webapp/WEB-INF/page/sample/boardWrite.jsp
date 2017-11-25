<%@ page language="java" contentType="text/html; charset=utf-8" 
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/layout/include-header.jspf" %>
</head>
<body>
    <form id="frm" name="frm" enctype="multipart/form-data">
    <!--  사진, 동영상 등 글자가 아닌 파일은 모두 multipart 형식의 데이터여야 한다 -->
        <table class="board_view">
            <colgroup>
                <col width="15%">
                <col width="*"/>
            </colgroup>
            <caption>게시글 작성</caption>
            <tbody>
                <tr>
                    <th scope="row">제목</th>
                    <td><input type="text" id="TITLE" name="TITLE" class="wdp_90"></input></td>
                </tr>
                <tr>
                    <td colspan="2" class="view_text">
                        <textarea rows="20" cols="100" title="내용" id="CONTENTS" name="CONTENTS"></textarea>
                    </td>
                </tr>
            </tbody>
        </table>
        <div id="fileDiv">
            <p>
                <input type="file" id="file" name="file_0">
                <a href="#this" class="btn" id="delete" name="delete">삭제</a>
            </p>
        </div>
         
        <br/><br/>
        <a href="#this" class="btn" id="addFile">파일 추가</a>
        <a href="#this" class="btn" id="write" >작성하기</a>
        <a href="#this" class="btn" id="list" >목록으로</a>
    </form>
    <%@ include file="/layout/include-body.jspf" %>
    <!-- 목록으로 버튼 -->
    <script type="text/javascript">
    var gfv_count = 1;
    //전역변수 선언, 태그가 추가될 때마다 값을 1씩 증가시켜서 name값이 계속 바뀌도록 함
    
    $(document).ready(function(){
        $("#list").on("click", function(e){ //목록으로 버튼
            e.preventDefault();
            fn_openBoardList();
        });
         
        $("#write").on("click", function(e){ //작성하기 버튼
            e.preventDefault();
            fn_insertBoard();
        });
        
        $("#addFile").on("click", function(e){ //파일 추가 버튼
            e.preventDefault();
            fn_addFile();
        });
         
        $("a[name='delete']").on("click", function(e){ //삭제 버튼
            e.preventDefault();
            fn_deleteFile($(this));
        });

    });
     
    function fn_openBoardList(){
        var comSubmit = new ComSubmit();
        comSubmit.setUrl("<c:url value='/sample/openBoardList.do' />");
        comSubmit.submit();
    }
     
    function fn_insertBoard(){
        var comSubmit = new ComSubmit("frm");
        comSubmit.setUrl("<c:url value='/sample/insertBoard.do' />");
        comSubmit.submit();
    }

           	//form의 id를 인자값으로 넘겨줌
           	//comSubmit 객체는 객체가 생성될 때, 폼의 아이디가 인자값으로 들어오면 
           	//그 폼을 전송하고 파라미터가 없으면 숨겨둔 폼을 이용하여 데이터를 전송하도록 구현
           	//전송할 데이터가 있는 id=frm인 form을 이용하도록 id를 넘겨줌
    function fn_addFile() {
          var str = "<p><input type='file' name='file_"+(gfv_count++)+" '><a href ='#this' class='btn' name='delete'>삭제</a></p>";	
           $("#fileDiv").append(str);
           $("a[name='delete']").on("click", function(e){
        	  e.preventDefault();
        	  fn_deleteFile($(this));        	   
           });          		
           	}
           	
           	function fn_deleteFile(obj) {
           		
           		obj.parent().remove();
           	}
     
       </script>   

</body>
</html>





