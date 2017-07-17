<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="zh-Hant">

<head>
	<meta charset="utf-8">
	<jsp:include page="../fragment/refCss.jsp" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dist/css/wish.css">
	<jsp:include page="../fragment/refJs.jsp" />
	<title>WeShare 微分享</title>
</head>

<body> 
	<header>
		<jsp:include page="../fragment/header.jsp" />
	</header>

	<section>
		<div id="sectionWish" class="container-fluid">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="row">
						<!-- 左邊條件篩選區 -->
						<div id="sectionFilter" class="col-xs-8 col-sm-5 col-md-4 col-lg-3">
							<div id="sectionFilterContent">
								<div id="contentScroll">
									<!-- 關鍵字搜尋區塊 -->
									<div id="blockKWSearch">
										<input id="searchInput" type="search" class="form-control" placeholder="搜尋">
										<span id="btKWSearch"><i class="fa fa-search" aria-hidden="true"></i></span>
									</div>

									<!-- 需求者身份區塊 -->
									<div class="blockFilter">
										<!-- 需求者身份標題 -->
										<button id="btBy" type="button" class="btn btn-default btn-block in btFilterTitle" data-toggle="collapse" data-target="#blockBy">
											<span>需求者身份</span>
											<span class="caret caretRotate"></span>
										</button>
										<!-- 需求者身份選項 -->
										<div id="blockBy" class="collapse in btFilterBlock">
											<ul type="none">
												<li>
													<button class="btn btnFilterType">
														<span>社福團體</span>
														<input class="findCol" type="hidden" value="usertype">
														<input class="findVal" type="hidden" value="2">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>個人</span>
														<input class="findCol" type="hidden" value="usertype">
														<input class="findVal" type="hidden" value="1">
													</button>
												</li>
											</ul>
										</div>
									</div>

									<!-- 物資類別區塊 -->
									<div class="blockFilter">
										<!-- 物資類別標題 -->
										<button id="btClass" type="button" class="btn btn-default btn-block in btFilterTitle" data-toggle="collapse" data-target="#blockClass">
											<span>物資類別</span>
											<span class="caret caretRotate"></span>
										</button>
										<!-- 物資類別選項 -->
										<div id="blockClass" class="collapse in btFilterBlock">
											<ul type="none">
												<li>
													<button class="btn btnFilterType">
														<span>乾貨食品</span>
														<input class="findCol" type="hidden" value="goodstype">
														<input class="findVal" type="hidden" value="1">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>服飾配件</span>
														<input class="findCol" type="hidden" value="goodstype">
														<input class="findVal" type="hidden" value="2">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>生活用品</span>
														<input class="findCol" type="hidden" value="goodstype">
														<input class="findVal" type="hidden" value="3">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>家電機器</span>
														<input class="findCol" type="hidden" value="goodstype">
														<input class="findVal" type="hidden" value="4">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>其他類型</span>
														<input class="findCol" type="hidden" value="goodstype">
														<input class="findVal" type="hidden" value="5">
													</button>
												</li>
											</ul>
										</div>
									</div>

									<!-- 物資需求地區塊 -->
									<div class="blockFilter">
										<!-- 物資需求地標題 -->
										<button id="btLoc" type="button" class="btn btn-default btn-block in btFilterTitle" data-toggle="collapse" data-target="#blockLoc">
											<span>物資需求地</span>
											<span class="caret caretRotate"></span>
										</button>
										<!-- 物資需求地選項 -->
										<div id="blockLoc" class="collapse in btFilterBlock">
											<ul>
												<li>
													<button class="btn btnFilterType">
														<span>臺北市</span>
														<input class="findCol" type="hidden" value="goodsloc">
														<input class="findVal" type="hidden" value="7">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>新北市</span>
														<input class="findCol" type="hidden" value="goodsloc">
														<input class="findVal" type="hidden" value="4">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>桃園市</span>
														<input class="findCol" type="hidden" value="goodsloc">
														<input class="findVal" type="hidden" value="2">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>新竹縣</span>
														<input class="findCol" type="hidden" value="goodsloc">
														<input class="findVal" type="hidden" value="6">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>苗栗縣</span>
														<input class="findCol" type="hidden" value="goodsloc">
														<input class="findVal" type="hidden" value="1">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>臺中市</span>
														<input class="findCol" type="hidden" value="goodsloc">
														<input class="findVal" type="hidden" value="13">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>高雄市</span>
														<input class="findCol" type="hidden" value="goodsloc">
														<input class="findVal" type="hidden" value="15">
													</button>
												</li>
											</ul>
										</div>
									</div>
								</div>

								<!-- 收和按鈕 -->
								<button id="slideMenuTrigger" class="btn btn-default">
									<i class="fa fa-filter" aria-hidden="true"></i>
								</button>
							</div>
						</div>

						<!-- 右邊需求物資列 -->
						<div id="sectionGoods" class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<!-- 右邊需求物資 -->
							<div id="divWishGoods" class="row">

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<footer>
		<jsp:include page="../fragment/footer.jsp" />
	</footer>
	
	
	<script src="${pageContext.request.contextPath}/dist/js/wish.js"></script>
	<script type="text/javascript">
		var javaRoot = "${pageContext.servletContext.contextPath}";
		var xhr = new XMLHttpRequest();
		var servletPath = javaRoot + '/_08_query/Query.do?goodsstatus=1';
		var responseData;
		
		// 頁面載入時判斷網址後方是否有值，有的話執行"關鍵字搜尋"，沒有的話取所有許願池物資的資料
		if( location.search !== ""){
			var searchString = location.search.slice(1);
			keyWordSearch(searchString);
		} else {
			window.onload = function(){
				xhr.open('GET', servletPath, true);
				xhr.send();
				xhr.onreadystatechange = function(){
					if(xhr.status == 200 && xhr.readyState == 4){
						responseData = JSON.parse(xhr.responseText);
						// 產生物資內容
						showData(responseData, javaRoot);
					}
				}
			}
		}
		
		// 關鍵字查詢
		$('#searchInput').keydown(function(e){
			if(e.keyCode == 13){
				keyWordSearch($('#searchInput').val());
			}
		});
		$('#btKWSearch').click(function(){
			keyWordSearch($('#searchInput').val());
		});
		function keyWordSearch(e){
			servletPath = javaRoot + '/_08_query/Query.do?goodsstatus=1';
			var keyWord = e;
			servletPath += '&type=keyword&value=' + keyWord;
			xhr.open('GET', servletPath, true);
			xhr.send();
			xhr.onreadystatechange = function(){
				if(xhr.status == 200 && xhr.readyState == 4){
					responseData = JSON.parse(xhr.responseText);
					// 產生物資內容
					showData(responseData, javaRoot);
				}
			}
		}
		
		// 身份、類別、地點查詢
		$('.btnFilterType').click(function(){
			servletPath = javaRoot + '/_08_query/Query.do?goodsstatus=1';
			var col = $(this).find('.findCol').val();
			var val = $(this).find('.findVal').val();
			servletPath += '&type=' + col + '&value=' + val;
			xhr.open('GET', servletPath, true);
			xhr.send();
			xhr.onreadystatechange = function(){
				if(xhr.status == 200 && xhr.readyState == 4){
					responseData = JSON.parse(xhr.responseText);
					// 產生物資內容
					showData(responseData, javaRoot);
				}
			}
		});
	</script>
	
	
</body>

</html>