// 產生網頁內容
function showData(data, path) {
	var javaRoot = path;
	var resultData;
	if(data.length == 0){
		resultData = 
		`<div style="font-size:24px; color:#ff0000; text-align:center; margin-top:30px;">
			找不到物資唷~請重新查詢！
		</div>`;
		$('#gTransBlock').html(resultData);		
		$('#blockGTransOption').empty();
	}
	resultData =
		`<!-- 左邊物資圖片 -->
		<div class="col-xs-12 col-sm-6 col-md-5 col-lg-6">
			<!-- 物資圖片 -->
			<div id="gTransImgLayout">
				<img id="gTransImg" class="img-responsive" src="${javaRoot}/_00_init/getImage?id=${responseData[0].goodsno}&type=GOODS">
			</div>
		</div>
		<!-- 右邊物資資訊 -->
		<div class="col-xs-12 col-sm-6 col-md-5 col-lg-5">
			<div id="gTransInfo" class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="row gTransInfoRow">
						<ul type="none">
							<li>索取物品
								<span>${data[0].goodsname}</span>
							</li>
							<li>索取對象
								<span>${data[0].indname_TEMP}</span>
							</li>
							<li>索取數量
								<input id="dealQty" type="number" class="form-control" value="1" min="1" max="${data[0].qty}" step="1" required="required">
							</li>
							<li>索取方式
								<select id="endShipway" class="form-control" required="required">
									<option value="1">郵寄</option>
									<option value="0">面交</option>
								</select>
							</li>
							<li>留言訊息
								<textarea id="dealNote" class="form-control" rows="5" maxlength="200" placeholder="留言最多200字"></textarea>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>`;
	$('#gTransBlock').prepend(resultData);
}

// 點擊傳送訊息
function sendTrans(){
	var goodsNo = `${responseData[0].goodsno}`;
	var dealQty = $('#dealQty').val();
	var endShipway = $('#endShipway').val();
	var dealNote = $('#dealNote').val();
	var dealImage = localStorage.getItem('dealImage');
	var transServletPath = javaRoot + '/web/_05_deal/controller/InsertDEAL';
	
	// post傳資料(JSON格式)
	var transDataString = JSON.stringify({
		GOODSNO : goodsNo,
		DEALQTY : dealQty,
		ENDSHIPWAY : endShipway,
		DEALNOTE : dealNote,
		DEALIMAGE : dealImage
	});
	
	$.ajax({
		type: 'post',
		url: transServletPath,
		data: transDataString,
		dataType: 'json',
		success: function(response){
			console.log("response = " + response);
			console.log("responseAns = " + response.Ans);
			// 出現錯誤訊息
			if(response.Ans == "TRUE"){
				$('#msgText').html("資料已送出，詳細資訊請至會員專區瀏覽");
				$('#aMsgClose').attr('href', 'wishGoods.jsp?goodsno=' + `${responseData[0].goodsno}`);
			} else {
				$('#msgText').html("發生了一點錯誤，請確認是否已登入，並重新進入此頁面，謝謝!");
			}
		},
		error: function(response){
			// 出現錯誤訊息
			$('#msgText').html("發生了一點錯誤，請重新進入此頁面，謝謝!");
		}
	});
}