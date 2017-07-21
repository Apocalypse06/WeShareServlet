<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>

<html lang="zh-Hant">

<head>
<meta charset="utf-8">
<jsp:include page="../fragment/refCss.jsp" />

<!-- member_menu.css -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dist/css/member_menu.css">
<!-- member_content.css -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dist/css/member_content.css">
<!-- member_update.css -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dist/css/member_update.css">
<!-- member_goodsCart.css -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dist/css/member_goodsCart.css">
<!-- member_message.css	-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dist/css/member_message.css">
<!-- member_feedback.css -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dist/css/member_feedback.css">

<jsp:include page="../fragment/refJs.jsp" />


<title>WeShare 微分享</title>
</head>

<body>

	<header>
		<jsp:include page="../fragment/header.jsp" />
	</header>

	<section>
		<div class="container">
			<div class="row">
				<!-- 左側導覽列 ********************************************************* -->
				<aside class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
							
					<!-- 左側導覽列title、會員圖片 -->
			        <div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<h3 id="member_menu_title"><b>會員專區</b></h3>
							<img id="member_menu_img" 
								 src="${pageContext.servletContext.contextPath}/_00_init/getImage?id=${LoginOK.indid}&type=MEMBER">
						</div>	
					</div>
								
								
					<!-- 左側導覽列選項按鈕 -->
					<ul>					
						<!-- 修改會員資料 -->
						<li>
							<a href="#member_update_content" data-toggle="tab">
								<button id="member_menu_update_button" class="btn btn-block member_menu_button">
									<span class="glyphicon glyphicon-pencil">
										<b>修改會員資料</b>
									</span>
								</button>
							</a>
						</li>
									
						<!-- 我的物資箱 -->
						<li>
							<a href="#member_goodsCart_content_jsp" data-toggle="tab">
								<button id="member_menu_goodsCart_button" class="btn btn-block member_menu_button">
									<span class="glyphicon glyphicon-gift">
										<b>我的物資箱</b>
									</span>
								</button>
							</a>
						</li>
									
						<!-- 我的站內信 -->
						<li>
							<a href="#member_message_content" data-toggle="tab">
								<button id="member_menu_message_button" class="btn btn-block member_menu_button">
									<span class="glyphicon glyphicon-envelope">
										<b>我的站內信</b>
									</span>
								</button>
							</a>
						</li>
									
						<!-- 紀錄與評價 -->
						<li>
							<a href="#member_feedback_content" data-toggle="tab">
								<button id="member_menu_feedback_button" class="btn btn-block member_menu_button">
									<span class="glyphicon glyphicon-star">	
										<b>紀錄與評價</b>
									</span>
								</button>
							</a>
						</li>
									
					</ul>
				</aside>
				
				
				<!-- 頁面內容 ********************************************************* -->
                <article class="col-xs-12 col-sm-9 col-md-9 col-lg-9 tab-content">
                
                	<!-- 修改會員資料 -->
<!-- 					<div id="member_update_content" class="tab-pane fade in active"> -->
					<div id="member_update_content" class="tab-pane fade">
						<jsp:include page="/web/member_update.jsp" />
					</div>
					
					<!-- 我的物資箱 -->
					<div id="member_goodsCart_content_jsp" class="tab-pane fade">
						<jsp:include page="/web/member_goodsCart.jsp" />
					</div>
					
					<!-- 我的站內信 -->
					<div id="member_message_content" class="tab-pane fade">
						<jsp:include page="/web/member_message.jsp" />
					</div>
					
					<!-- 紀錄與評價 -->
					<div id="member_feedback_content" class="tab-pane fade">
						<jsp:include page="/web/member_feedback.jsp" />
					</div>
                
                </article>
				
			</div>
		</div>
	</section>

	<footer>
		<jsp:include page="../fragment/footer.jsp" />
	</footer>
	
	
	<!-- 相關的js檔 -->
	<script src="${pageContext.request.contextPath}/dist/js/member_menu.js"></script>
	<script src="${pageContext.request.contextPath}/dist/js/member_feedback.js"></script>
	
	<script type="text/javascript">
	var javaRoot = "${pageContext.servletContext.contextPath}";
	
// 	修改會員資料__按下選單的按鈕，要求資料
	$('#member_menu_update_button').click(function(){
		var xhrMbData = new XMLHttpRequest();
		var servletPath = javaRoot + '/web/_03_updateMember/controller/FindMemberServlet';
		var responseMbData;
	
		xhrMbData.open('GET', servletPath, true);
		xhrMbData.send();
		xhrMbData.onreadystatechange = function(){
			if(xhrMbData.status == 200 && xhrMbData.readyState == 4){
				responseMbData = JSON.parse(xhrMbData.responseText);
// 				console.log("responseMbData = ");
// 				console.log(responseMbData);
				// 產生會員資料
				showMbData(responseMbData, javaRoot);
				
				//修改會員密碼
// 				$( "#passsword_update_button" ).click(function() {
// 					console.log("#passsword_update_button click");
// // 					$("#passsword_update_content").slideToggle();						  
// 				});
			}
		}
	});
	
	
// 	我的物資箱__按下選單的按鈕，要求資料
	$('#member_menu_goodsCart_button').click(function(){
		var xhrGCData = new XMLHttpRequest();
		var servletPath = javaRoot + '/web/_04_productMaintain/controller/DisplayPageProducts';
		var responseGCData;
	
		xhrGCData.open('GET', servletPath, true);
		xhrGCData.send();
		xhrGCData.onreadystatechange = function(){
			if(xhrGCData.status == 200 && xhrGCData.readyState == 4){
				responseGCData = JSON.parse(xhrGCData.responseText);
				console.log("responseGCData = ");
				console.log(responseGCData);
				// 產生會員資料
				showGCData(responseGCData, javaRoot);
				
//	 			預覽上傳照片
				$("#upload_img_goods").change(function(){
					if (this.files && this.files[0]) {
						var reader = new FileReader();
						reader.onload = function (e) {
							$("#preview_img_goods").attr("src", e.target.result);
						}
						reader.readAsDataURL(this.files[0]);
					}				
				});
				
				
//	 			按下物資title滑出選單 
				$(".goods_item_title").click(function() {
					$(this).closest(".goods_item_caption").find(".goods_item_content").slideToggle();
				});
				
				
//		 		點選修改按鈕
				$(".goods_item_update_button").click(function() {
//	 				 送出按鈕出現，表單可以修改、背景變灰色，修改按鈕消失  
					$(this).closest(".goods_item_content_li").find(".goods_item_submit_button").show();
					$(this).closest(".goods_item_content").find("input").attr("disabled", false);
					$(this).closest(".goods_item_content").find("select").attr("disabled", false);
					$(this).closest(".goods_item_content").find(".goods_item_content_li_input").css("background-color", "#E0E0E0").css("border-radius", "8px");
					$(this).hide();																				
				});


//		 		點選送出按鈕
				$(".goods_item_submit_button").click(function() {
					var goodsno = $(this).val();
					goodsCartUpdate($(this), goodsno);
				});
				
				
//		 		點選刪除按鈕
				$('.goods_item_delete_button').click(function(){
					$(this).closest(".block_goods_item").hide();
				});
			}
		}
	});
	
	
// 	紀錄與評價__按下選單的按鈕，要求資料
	$('#member_menu_feedback_button').click(function(){
		var xhrDealData = new XMLHttpRequest();
		var servletPath = javaRoot + '/web/_05_deal/controller/FindDEALByKey';
		var responseDealData;
	
		xhrDealData.open('GET', servletPath, true);
		xhrDealData.send();
		xhrDealData.onreadystatechange = function(){
			if(xhrDealData.status == 200 && xhrDealData.readyState == 4){
				responseDealData = JSON.parse(xhrDealData.responseText);

				// 點等待回應中
				$('#btStatus0').click(function(){
					showStatus0Date(responseDealData, javaRoot);
					// 點接受交易
					$('.btAgree').click(function(){
						dealAgree($(this));
					});
					// 點取消交易
					$('.btCancel').click(function(){
						dealCancel($(this));
					});
				});
				
				// 點已接受
				$('#btStatus1').click(function(){
					showStatus1Date(responseDealData, javaRoot);
					// 點結單的傳送訊息
					$('.btSendDealMsgClass').click(function(){
						sendDealMsg($(this));
					});
				});
				
				// 點已完成
				$('#btStatus2').click(function(){
					showStatus2Date(responseDealData, javaRoot);
					// 點送出評價
					$('.btGiveFeedbackClass').click(function(){
						sendFeedback($(this));
					});
				});
				
				// 點已取消
				$('#btStatus3').click(function(){
					showStatus3Date(responseDealData, javaRoot);
				});
				
			}
		}
	});
	</script>

</body>
</html>