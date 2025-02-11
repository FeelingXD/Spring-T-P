<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<style>
	html, body{
		padding:0;
		margin:0;
		height:100%;
	}

	.header{
		height: 50px;
		width: 100%;
		background: #C02425;  /* fallback for old browsers */
		background: -webkit-linear-gradient(to right, #F0CB35, #C02425);  /* Chrome 10-25, Safari 5.1-6 */
		background: linear-gradient(to right, #F0CB35, #C02425); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
		
	}
	
	.headerWrapper{
		width: 1024px;
		margin:2px auto;
	}
	.logo{
		padding-top:15px;
		font-weight:bold;
    	color: #FFF;
    	float:left;
	}
	.logininfo{
	    width: 150px;
	    height: 50px;
	    float: right;
	    color: #FFF;
	    padding: 12px;
	}
</style>
<div class="header">
	<div class="headerWrapper">
		<div class="logo">OffREV
		
		</div>
		<div id="logininfo" class="logininfo">
				
		<c:if test="${ user != null }" >
			<c:out value="${ user.nickname }" />
		</c:if>
		</div>
	</div>
	
	
</div>

</html>
