<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

	<!-- title -->
	<div class="row">
		<div id="member_feedback_content_title" class="col-xs-12 col-sm-12 col-md-12 col-lg-12 member_contnet_title">
			<h3>
				<b>紀錄與評價</b>
			</h3>
		</div>
	</div>
	
	<!-- body -->
	<div class="row">
		<div id="member_feedback_content_body" class="col-xs-12 col-sm-12 col-md-12 col-lg-12 member_content_body">							
			
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="row">								
						<!-- 排序選項 -->
						<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
							<div class="dropdown">
								<button type="button" name="" value="" id="feedback_priority_button" class="btn btn-block dropdown-toggle" data-toggle="dropdown">
									排序
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu">
									<li><a href="#">依時間排序</a></li>
									<li><a href="#">依分數排序</a></li>
									<li><a href="#">依給評人排序</a></li>
								</ul>
							</div>
						</div>
						
						<!-- 查詢欄位 -->
						<div class="col-xs-12 col-sm-6 col-md-8 col-lg-8">
							<div class="input-group" id="feedback_search_bar">
								<input type="search" name="" value="" class="form-control" id="feedback_search_bar_input">
								<span class="input-group-btn">
									<button type="button" name="" value="" id="feedback_search_button" class="btn btn-block">
										<span class="glyphicon glyphicon-search"></span>
										搜尋
									</button>
								</span>
							</div>	
						</div>
					</div>
				</div>
			</div>
			
			
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div id="btStatusAll">
						<div class="row">
							<div class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
								<button id="btStatus0" class="btn btn-default btStatusStyle">等待回應</button>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
								<button id="btStatus1" class="btn btn-default btStatusStyle">已接受</button>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
								<button id="btStatus2" class="btn btn-default btStatusStyle">已完成</button>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
								<button id="btStatus3" class="btn btn-default btStatusStyle">已取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			
			<!-- 紀錄與評價 -->
			<div id="dealContent">

			</div>
		</div>
	</div>
	
	<!-- footer -->
	<div class="row">
		<div id="member_feedback_content_footer" class="col-xs-12 col-sm-12 col-md-12 col-lg-12 member_content_footer">	
		</div>
	</div>