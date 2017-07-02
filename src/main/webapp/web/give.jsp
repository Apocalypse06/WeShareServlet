<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="zh-Hant">

<head>
	<meta charset="utf-8">
	<jsp:include page="../fragment/refCss.jsp" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dist/css/give.css">
	<jsp:include page="../fragment/refJs.jsp" />
	<title>WeShare 微分享</title>
</head>

<body> 
	<header>
		<jsp:include page="../fragment/header.jsp" />
	</header>

	<section>
		<div id="sectionGive" class="container-fluid">
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
														<input class="findCol" type="hidden" value="server">
														<input class="findVal" type="hidden" value="server">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>個人</span>
														<input class="findCol" type="hidden" value="server">
														<input class="findVal" type="hidden" value="server">
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
														<span>家電產品</span>
														<input class="findCol" type="hidden" value="server">
														<input class="findVal" type="hidden" value="server">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>生活用品</span>
														<input class="findCol" type="hidden" value="server">
														<input class="findVal" type="hidden" value="server">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>服飾配件</span>
														<input class="findCol" type="hidden" value="server">
														<input class="findVal" type="hidden" value="server">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>食品</span>
														<input class="findCol" type="hidden" value="server">
														<input class="findVal" type="hidden" value="server">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>其他</span>
														<input class="findCol" type="hidden" value="server">
														<input class="findVal" type="hidden" value="server">
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
														<span>基隆市</span>
														<input class="findCol" type="hidden" value="server">
														<input class="findVal" type="hidden" value="server">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>臺北市</span>
														<input class="findCol" type="hidden" value="server">
														<input class="findVal" type="hidden" value="server">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>新北市</span>
														<input class="findCol" type="hidden" value="server">
														<input class="findVal" type="hidden" value="server">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>桃園市</span>
														<input class="findCol" type="hidden" value="server">
														<input class="findVal" type="hidden" value="server">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>新竹市</span>
														<input class="findCol" type="hidden" value="server">
														<input class="findVal" type="hidden" value="server">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>新竹縣</span>
														<input class="findCol" type="hidden" value="server">
														<input class="findVal" type="hidden" value="server">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>苗栗縣</span>
														<input class="findCol" type="hidden" value="server">
														<input class="findVal" type="hidden" value="server">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>臺中市</span>
														<input class="findCol" type="hidden" value="server">
														<input class="findVal" type="hidden" value="server">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>南投縣</span>
														<input class="findCol" type="hidden" value="server">
														<input class="findVal" type="hidden" value="server">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>彰化縣</span>
														<input class="findCol" type="hidden" value="server">
														<input class="findVal" type="hidden" value="server">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>雲林縣</span>
														<input class="findCol" type="hidden" value="server">
														<input class="findVal" type="hidden" value="server">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>嘉義市</span>
														<input class="findCol" type="hidden" value="server">
														<input class="findVal" type="hidden" value="server">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>嘉義縣</span>
														<input class="findCol" type="hidden" value="server">
														<input class="findVal" type="hidden" value="server">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>臺南市</span>
														<input class="findCol" type="hidden" value="server">
														<input class="findVal" type="hidden" value="server">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>高雄市</span>
														<input class="findCol" type="hidden" value="server">
														<input class="findVal" type="hidden" value="server">
													</button>
												</li>
												<li>
													<button class="btn btnFilterType">
														<span>屏東縣</span>
														<input class="findCol" type="hidden" value="server">
														<input class="findVal" type="hidden" value="server">
													</button>
												</li>
											</ul>
										</div>
									</div>
								</div>

								<!-- 收和按鈕 -->
								<button id="slideMenuTrigger" class="btn btn-default" type="">
									<i class="fa fa-filter" aria-hidden="true"></i>
								</button>
							</div>
						</div>

						<!-- 右邊需求物資列 -->
						<div id="sectionGoods" class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

							<!-- 右邊需求物資第一列 -->
							<div id="divGiveGoods1" class="row">
								<!-- 右邊需求物資第一列第一項 -->
								<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
									<div class="giveGoods">
										<a href="#" class="giveGoodsA">
											<!-- 需求物資圖片 -->
											<div class="giveGoodsImgLayout">
												<img class="img-responsive giveGoodsImg" src="../dist/img/300x300/20170627_22.png">
											</div>
											<!-- 需求物資文字敘述 -->
											<div class="giveGoodsOverlay">
												<div class="giveGoodsOverlayText">
													<!-- 需求物資名稱 -->
													<div class="giveGoodsName giveGoodsContent">
														<span>Lepa Vida</span>
													</div>
													<!-- 需求物資發佈者 -->
													<div class="giveGoodsBy giveGoodsContent">
														<span>By <span class="month">Matter</span> Of Mind from France</span>
													</div>
													<!-- 需求物資描述 -->
													<div class="giveGoodsDesc giveGoodsContent col-xs-10 col-xs-offset-1 col-sm-5 col-sm-offset-1 col-md-5 col-md-offset-1 col-lg-4 col-lg-offset-1">
														<span>詳細資訊</span>
													</div>
												</div>
											</div>
										</a>
									</div>
								</div>
								<!--右邊物資第一列第二項-->
								<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
									<div class="giveGoods">
										<a href="#" class="giveGoodsA">
											<!-- 需求物資圖片 -->
											<div class="giveGoodsImgLayout">
												<img class="img-responsive giveGoodsImg" src="../dist/img/300x300/20170627_23.png">
											</div>
											<!-- 需求物資文字敘述 -->
											<div class="giveGoodsOverlay">
												<div class="giveGoodsOverlayText">
													<!-- 需求物資名稱 -->
													<div class="giveGoodsName giveGoodsContent">
														<span>Lepa Vida</span>
													</div>
													<!-- 需求物資發佈者 -->
													<div class="giveGoodsBy giveGoodsContent">
														<span>By <span class="month">Matter</span> Of Mind from France</span>
													</div>
													<!-- 需求物資描述 -->
													<div class="giveGoodsDesc giveGoodsContent col-xs-10 col-xs-offset-1 col-sm-5 col-sm-offset-1 col-md-5 col-md-offset-1 col-lg-4 col-lg-offset-1">
														<span>詳細資訊</span>
													</div>
												</div>
											</div>
										</a>
									</div>
								</div>
								<!--右邊物資第一列第三項-->
								<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
									<div class="giveGoods">
										<a href="#" class="giveGoodsA">
											<!-- 需求物資圖片 -->
											<div class="giveGoodsImgLayout">
												<img class="img-responsive giveGoodsImg" src="../dist/img/300x300/20170627_24.png">
											</div>
											<!-- 需求物資文字敘述 -->
											<div class="giveGoodsOverlay">
												<div class="giveGoodsOverlayText">
													<!-- 需求物資名稱 -->
													<div class="giveGoodsName giveGoodsContent">
														<span>Lepa Vida</span>
													</div>
													<!-- 需求物資發佈者 -->
													<div class="giveGoodsBy giveGoodsContent">
														<span>By <span class="month">Matter</span> Of Mind from France</span>
													</div>
													<!-- 需求物資描述 -->
													<div class="giveGoodsDesc giveGoodsContent col-xs-10 col-xs-offset-1 col-sm-5 col-sm-offset-1 col-md-5 col-md-offset-1 col-lg-4 col-lg-offset-1">
														<span>詳細資訊</span>
													</div>
												</div>
											</div>
										</a>
									</div>
								</div>
							</div>

							<!-- 右邊需求物資第二列 -->
							<div id="divGiveGoods1" class="row">
								<!-- 右邊需求物資第二列第一項 -->
								<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
									<div class="giveGoods">
										<a href="#" class="giveGoodsA">
											<!-- 需求物資圖片 -->
											<div class="giveGoodsImgLayout">
												<img class="img-responsive giveGoodsImg" src="../dist/img/300x300/20170627_25.png">
											</div>
											<!-- 需求物資文字敘述 -->
											<div class="giveGoodsOverlay">
												<div class="giveGoodsOverlayText">
													<!-- 需求物資名稱 -->
													<div class="giveGoodsName giveGoodsContent">
														<span>Lepa Vida</span>
													</div>
													<!-- 需求物資發佈者 -->
													<div class="giveGoodsBy giveGoodsContent">
														<span>By <span class="month">Matter</span> Of Mind from France</span>
													</div>
													<!-- 需求物資描述 -->
													<div class="giveGoodsDesc giveGoodsContent col-xs-10 col-xs-offset-1 col-sm-5 col-sm-offset-1 col-md-5 col-md-offset-1 col-lg-4 col-lg-offset-1">
														<span>詳細資訊</span>
													</div>
												</div>
											</div>
										</a>
									</div>
								</div>
								<!--右邊物資第二列第二項-->
								<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
									<div class="giveGoods">
										<a href="#" class="giveGoodsA">
											<!-- 需求物資圖片 -->
											<div class="giveGoodsImgLayout">
												<img class="img-responsive giveGoodsImg" src="../dist/img/300x300/20170627_26.png">
											</div>
											<!-- 需求物資文字敘述 -->
											<div class="giveGoodsOverlay">
												<div class="giveGoodsOverlayText">
													<!-- 需求物資名稱 -->
													<div class="giveGoodsName giveGoodsContent">
														<span>Lepa Vida</span>
													</div>
													<!-- 需求物資發佈者 -->
													<div class="giveGoodsBy giveGoodsContent">
														<span>By <span class="month">Matter</span> Of Mind from France</span>
													</div>
													<!-- 需求物資描述 -->
													<div class="giveGoodsDesc giveGoodsContent col-xs-10 col-xs-offset-1 col-sm-5 col-sm-offset-1 col-md-5 col-md-offset-1 col-lg-4 col-lg-offset-1">
														<span>詳細資訊</span>
													</div>
												</div>
											</div>
										</a>
									</div>
								</div>
								<!--右邊物資第二列第三項-->
								<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
									<div class="giveGoods">
										<a href="#" class="giveGoodsA">
											<!-- 需求物資圖片 -->
											<div class="giveGoodsImgLayout">
												<img class="img-responsive giveGoodsImg" src="../dist/img/300x300/20170627_27.png">
											</div>
											<!-- 需求物資文字敘述 -->
											<div class="giveGoodsOverlay">
												<div class="giveGoodsOverlayText">
													<!-- 需求物資名稱 -->
													<div class="giveGoodsName giveGoodsContent">
														<span>Lepa Vida</span>
													</div>
													<!-- 需求物資發佈者 -->
													<div class="giveGoodsBy giveGoodsContent">
														<span>By <span class="month">Matter</span> Of Mind from France</span>
													</div>
													<!-- 需求物資描述 -->
													<div class="giveGoodsDesc giveGoodsContent col-xs-10 col-xs-offset-1 col-sm-5 col-sm-offset-1 col-md-5 col-md-offset-1 col-lg-4 col-lg-offset-1">
														<span>詳細資訊</span>
													</div>
												</div>
											</div>
										</a>
									</div>
								</div>
							</div>

							<!-- 右邊需求物資第三列 -->
							<div id="divGiveGoods1" class="row">
								<!-- 右邊需求物資第三列第一項 -->
								<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
									<div class="giveGoods">
										<a href="#" class="giveGoodsA">
											<!-- 需求物資圖片 -->
											<div class="giveGoodsImgLayout">
												<img class="img-responsive giveGoodsImg" src="../dist/img/300x300/20170627_28.png">
											</div>
											<!-- 需求物資文字敘述 -->
											<div class="giveGoodsOverlay">
												<div class="giveGoodsOverlayText">
													<!-- 需求物資名稱 -->
													<div class="giveGoodsName giveGoodsContent">
														<span>Lepa Vida</span>
													</div>
													<!-- 需求物資發佈者 -->
													<div class="giveGoodsBy giveGoodsContent">
														<span>By <span class="month">Matter</span> Of Mind from France</span>
													</div>
													<!-- 需求物資描述 -->
													<div class="giveGoodsDesc giveGoodsContent col-xs-10 col-xs-offset-1 col-sm-5 col-sm-offset-1 col-md-5 col-md-offset-1 col-lg-4 col-lg-offset-1">
														<span>詳細資訊</span>
													</div>
												</div>
											</div>
										</a>
									</div>
								</div>
								<!--右邊物資第三列第二項-->
								<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
									<div class="giveGoods">
										<a href="#" class="giveGoodsA">
											<!-- 需求物資圖片 -->
											<div class="giveGoodsImgLayout">
												<img class="img-responsive giveGoodsImg" src="../dist/img/300x300/20170627_29.png">
											</div>
											<!-- 需求物資文字敘述 -->
											<div class="giveGoodsOverlay">
												<div class="giveGoodsOverlayText">
													<!-- 需求物資名稱 -->
													<div class="giveGoodsName giveGoodsContent">
														<span>Lepa Vida</span>
													</div>
													<!-- 需求物資發佈者 -->
													<div class="giveGoodsBy giveGoodsContent">
														<span>By <span class="month">Matter</span> Of Mind from France</span>
													</div>
													<!-- 需求物資描述 -->
													<div class="giveGoodsDesc giveGoodsContent col-xs-10 col-xs-offset-1 col-sm-5 col-sm-offset-1 col-md-5 col-md-offset-1 col-lg-4 col-lg-offset-1">
														<span>詳細資訊</span>
													</div>
												</div>
											</div>
										</a>
									</div>
								</div>
								<!--右邊物資第三列第三項-->
								<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
									<div class="giveGoods">
										<a href="#" class="giveGoodsA">
											<!-- 需求物資圖片 -->
											<div class="giveGoodsImgLayout">
												<img class="img-responsive giveGoodsImg" src="../dist/img/300x300/20170627_30.png">
											</div>
											<!-- 需求物資文字敘述 -->
											<div class="giveGoodsOverlay">
												<div class="giveGoodsOverlayText">
													<!-- 需求物資名稱 -->
													<div class="giveGoodsName giveGoodsContent">
														<span>Lepa Vida</span>
													</div>
													<!-- 需求物資發佈者 -->
													<div class="giveGoodsBy giveGoodsContent">
														<span>By <span class="month">Matter</span> Of Mind from France</span>
													</div>
													<!-- 需求物資描述 -->
													<div class="giveGoodsDesc giveGoodsContent col-xs-10 col-xs-offset-1 col-sm-5 col-sm-offset-1 col-md-5 col-md-offset-1 col-lg-4 col-lg-offset-1">
														<span>詳細資訊</span>
													</div>
												</div>
											</div>
										</a>
									</div>
								</div>
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
</body>

</html>