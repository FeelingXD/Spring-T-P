<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>

<style>
	
	
	.loginWrapper{
		margin-top: 50px;
		margin-left:auto;
		margin-right:auto;
		max-width: 400px;
		height: 250px;
		border-radius:5px;
		text-align: center;
		line-height:1.8;
	}
	
	#kakao-login-btn{
		margin-top:20px;
    	border-top: 1px solid #DDDDDD99;
	}
	.itemListWrapper{
		width: 960px;
		margin: 5px auto;
		text-align: center;
	}
	.itemList{
	    width: 300px;
	    display: inline-block;
	    height: 400px;
	    margin: 25px 7px; 
	    border-radius: 16px;
	    overflow: hidden;
	    
   		box-shadow: 0px 3px 1px 1px #aaa;
	}
	.imageArea{
		width: 100%;
		height: 200px;
		background: #EEEEEE;
		overflow:hidden;
		position : relative;
	}
	
	.imageArea > img{
		width: 100%;
		height: 100%;
	}
	
	.modify-btn{
		display: none;
		position : absolute;
		right: 20px;
		top: 20px;
		width: 20px !important;
		height: 20px !important;
		cursor:pointer;
	}
	.reviewArea{
		width: 100%;
		height: 180px;
		text-align: left;
	}
	.reviewTitle{
		width: 100%;
		height: 20px;
		text-align: left;
		padding : 5px;
	}
	
	.reviewArea > textarea {
		resize: none;
		width: 100%;
		height: 168px;
		background:#AAEECC11;
		overflow:hidden;
    	border: 0px !important;
		padding : 5px;
	}
	.slide-child{
		transform: translateY(50px);
        opacity: 0;
        transition: all 1s;
    }
    .is-visible{
		transform: translateY(0px);
        opacity: 1;
    }
    .write-btn{
    	width: 100px;
    	height: 28px;
    	margin: 0px auto;
    	background: linear-gradient(to right, #C02425, #F0CB35);
    	color:white;
    	border-radius: 5px;
    	opacity: 0.8;
    	cursor:pointer;
    	padding-top:5px;
    	margin-top:20px;
    	border-top: 1px solid #DDDDDD99;
    } 
    .write-btn:hover{
    	
    	opacity: 1;
    }
   	.ormWrapper{
   		padding: 30px;
   		max-width: 1080px;
   		margin: 10px auto;
   	}
	.orm-cover{
		width : 140px;
		height : 40px;
		display: flex;
		border-radius: 5px;
		overflow: hidden;
	}	
	   	
	.myBatis{
		width: 50%;
		background: #EEEEEE;
		cursor: pointer;
		text-align: center;
		padding-top: 10px;
	}
	.jpa{
		width: 50%;
		background: #EEEEEE;
		cursor: pointer;
		text-align: center;
		padding-top: 10px;
	}
	.myBatis.selected{
	
		background: #d8d8d8;
	}
	.jpa.selected{
		
		background: #d8d8d8;
	}
   	
    .cover-form{
	    width: 400px;
	    height: 450px;
	    background: white;
	    position: fixed;
	    z-index: 10;
	    border-radius: 10px;
	    padding-top: 10px;
    }
    .form-title{
    	font-size: 25px;
    	font-weight: bold;
    	width: 100%;
    	text-align: center;
    }
    .form-desc{
    	font-size: 14px;
    	text-align: left;
    	padding: 20px;
    }
    
    .input-title{
    	width: 200px;
    	height: 40px;
    	text-align: left;
    	font-size: 14px;
    	padding: 3px;
    }
    .input-content{
    	width: 250px;
    	height: 200px;
    	padding: 3px;
    }
</style>

<script type="text/javascript">

$(document).ready(function(){
	
	$("#cover-form").hide();
	<c:if test="${ user ne null }" >
		$(".modify-btn").show();
	</c:if>
	
	<c:if test="${ type ne null and type eq 'jpa'}" >
		$(".jpa").addClass('selected');
		$(".myBatis").removeClass('selected');
	</c:if>
	<c:if test="${ type eq null or type eq 'myBatis'}" >
		$(".jpa").removeClass('selected');
		$(".myBatis").addClass('selected');
	</c:if>
	
	
	<c:if test="${ user eq null }" >
	$("#write-btn").hide();
	$(".modify-btn").hide();
	
	Kakao.init('76a34fcaefc9ee59cfe964e231bf3bbf');
    // 카카오 로그인 버튼을 생성합니다.
    Kakao.Auth.createLoginButton({
      container: '#kakao-login-btn',
      success: function(authObj) {
        //alert(JSON.stringify(authObj));
        
       
       Kakao.API.request({url:'/v2/user/me',
    	   success:function (res){
    		   var id = res.id;
    		   var email = (res.kaccount_email ? res.kaccount_email : '');
    		   var nickname = (res.properties && res.properties.nickname ? res.properties.nickname : '');
    		   
    		  
    		   nickname = '치킨';
    		   
    		   $("#logininfo").text(nickname);
    		   $.post("/kakaoLogin",
	   			   {id:id, email : email, nickname : nickname}
	   			 	, function (data){
	   			 		
	   			 		
	   			 		if(data == 1){
	   			 			alert("로그인이 완료 되었습니다.");
	   			 			$("#kakao-login-btn").hide();
		   			 		$("#write-btn").show();
		   			 		$(".modify-btn").show();
		   			 	
	   			 		}
	   			 	}
    		   )
    		   
    		   
    		   
    	   },
    	   fail:function (error){
    		   
    	   }})
       
      },
      fail: function(err) {
         alert(JSON.stringify(err));
      }
    });
    </c:if>
    var slideAelements = $('.slide-child')
    
    
    function animateSlideA() {
      slideAelements.each(function (i) {
          setTimeout(function () {
              slideAelements.eq(i).addClass('is-visible');
          }, 300 * (i + 1));
      });
    }
    animateSlideA() ;
    
    
    $("#write-btn").click(function (){
    	
    	$("#cover-type").val("<c:out value="${type}"/>");
    	$("#cover-form").show();
    	
    });
	$("#cancelBtn").click(function (){
    	
    	$("#cover-form").hide();
    });
	
	$(".jpa").click(function (){
		$(".jpa").addClass('selected');
		$(".myBatis").removeClass('selected');
		location.href="/login?type=jpa";
    });
	$(".myBatis").click(function (){
		$(".myBatis").addClass('selected');
		$(".jpa").removeClass('selected');
		location.href="/login?type=myBatis";
    });
    
    
	$("#itemList .modify-btn").on("click", function() {
         
        $("#cover-title").val($(this).data("title"));
        $("#cover-content").val($(this).data("content"));
        $("#cover-seq").val($(this).data("seq"));
        $("#cover-type").val("<c:out value="${type}"/>");
       
        $("#cover-form").show();
       
        
    });
	
    $("#btnSubmit").click(function (event) {
    	 
        //preventDefault 는 submit을 막음
        event.preventDefault();
 
        var form = $('#fileUploadForm')[0];
 
        var data = new FormData(form);
 
        $("#btnSubmit").prop("disabled", true);
 
        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "/fileUpload",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
                alert("complete");
                $("#btnSubmit").prop("disabled", false);
                $("#cover-form").hide();
                location.reload();
            },
            error: function (e) {
                console.log("ERROR : ", e);
                $("#btnSubmit").prop("disabled", false);
                alert("fail");
                $("#cover-form").hide();
            }
        });
 
    });
    
});
</script>

<html>
<title>오프라인 리뷰 웹테스트</title>



<body>

<div class="ormWrapper">
	<div class="orm-cover" >
		<div class="myBatis selected">myBatis</div>
		<div class="jpa">Jpa</div>
	</div>
	<div>
		<input id=searchText type="text" value="" name="searchText" />
	</div>
</div>
<div class="loginWrapper">
	
	<div>
		<div class="slide-child">OffREV 는 Offline</div>
		<div class="slide-child">Review Platform 의 약자로써</div>
		<div class="slide-child">오프라인 후기 정보들을</div>
		<div class="slide-child">모아모아 제공합니다.</div>
	</div>
	<c:if test="${ user == null }" >
	<div id="kakao-login-btn">
		
	</div>
	</c:if>
	<div id="cover-form" class="cover-form">
		<div class="form-title">리뷰 쓰기</div>
		<div class="form-desc">오프라인 행사 리뷰를 작성해주세요.</div>
		<form method="POST" action="/fileUpload" enctype="multipart/form-data" id="fileUploadForm">
			제목 <input id="cover-title" type=text name="title" class="input-title" />
			<br/>내용<br/>
			<textarea id="cover-content" name="content"  class="input-content" ></textarea>
			
			<input type=hidden id="cover-seq" name="seq" value=0 />
			
			<input type=hidden id="cover-type" name="type" value="" />
			
		    <input type=file name="mediaFile" > <br/>
		    <input type="submit" value="저장하기" id="btnSubmit"/>
		    <input type="button" value="취소하기" id="cancelBtn"/>
		</form>
		
	</div>
	<div id="write-btn" class="write-btn">
		글쓰기
	</div>
	
</div>

<div class="itemListWrapper">
<c:forEach var="item" items="${reviewList }" >
	<div id="itemList" class="itemList slide-child">
	   <div class="imageArea">
	    	
	    <c:if test="${item.imageUrl ne '' }"> 
	    	<img src="<c:out value="${ item.imageUrl }" />"  
	    		/>
	    </c:if>
	   		<img class="modify-btn" src="/img/modify.png"
	   		
	    		data-seq="<c:out value="${ item.seq }" />"
	    		data-title="<c:out value="${ item.title }" />"
	    		data-content="<c:out value="${ item.content }" />" />
	   </div>
	   <div class="reviewArea">
	   	  <div class="reviewTitle" ><c:out value="${ item.title }" /> </div>
	      <textarea readonly><c:out value="${ item.content }" /></textarea>
	   </div>
	</div>
</c:forEach>
</div> 



</body>
</html>

