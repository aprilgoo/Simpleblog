<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="/layout/include-header.jspf" %>
<title>My blog</title>
</head>
<body>
 <c:choose>
 	<c:when test="${fn:length(map.list) > 0}">
 		<c:forEach items="${map.list}" var="row">
 			 <div class="container">
			  <div class="row">
		        <div class="col-lg-8 col-md-10 mx-auto">
		          <div class="post-preview">
		            <a href="post.html">
		              <h2 class="post-title">
		                <td>${row.TITLE}</td>
		              </h2>
		              <h3 class="post-subtitle">
		                ${row.SUB_TITLE}
		              </h3>   
		            </a>
		            <p class="post-meta">Posted by
		              <a href="#">${row.CREA_ID}</a>
		              ${row.CREA_DTM}</p>
		          </div>
		        </div>
			</div>
		</div>
		
	</c:forEach> 	
 </c:when>
</c:choose>
		          <hr> 
		           <!-- Pager -->
		          <div class="clearfix">
		            <a class="btn btn-primary float-right" href="#">Older Posts &rarr;</a>
		        	  </div>
		
<%@include file="/layout/include-body.jspf" %>
</body>
</html>

