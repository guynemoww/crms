<%@page pageEncoding="UTF-8" import="commonj.sdo.DataObject"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-04-23 17:55:38
  - Description:
-->
<head>
<title>不规则还款计划</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>
<body>
	<iframe name="exportFrame" id="exportFrame" src="" style="display:none;"></iframe>

		<div id="form" class="nui-form"style="width: 99.4%; height: auto; overflow: hidden;">
			<div class="nui-dynpanel" columns="6" id="cs">
				<label>本金：</label> 
				<input id="bj" name="bj"class="nui-textbox nui-form-input" required="true"dataType="currency1"vtype="float;maxLength:20;range:100,100000000000" /> 
				<label>年利率（%）：</label>
				<input id="yearrate" name="yearrate"class="nui-textbox nui-form-input" required="true" vtype="float;maxLength:20;range:0.01,20"/>
				<label>起息日：</label>
				<input id="dkqq" name="dkqq" class="nui-datepicker" required="true"   /> 
				<label>贷款期限（月）：</label>
				<input id="dkqx" name="dkqx" class="nui-textbox nui-form-input"required="true"  /> 
				<label>还款方式：</label> 
				<input id="hkfs"name="hkfs" class="nui-dictcombobox nui-form-input" style="width:200px"dictTypeId="XD_SXCD1162" required="true" /> 
				<label>结息周期：</label>
				<input id="jxzq" name="jxzq" class="nui-dictcombobox nui-form-input"dictTypeId="XD_SXCD1018" required="true" /> 
				<label id="schkq_lab" >首次还款日期：</label> 
				<input id="schkq" class="  nui-datepicker nui-form-input" name="schkq"    />  

			</div>
			<div class="nui-toolbar" style="border-bottom: 0; text-align: left;"
				id="hkjhdiv">
				<a class="nui-button" iconCls="icon-zoomin" onclick="initDataTable()">查询</a> 
				<a class="nui-button" iconCls="icon-reload" onclick="refresh()">重新计算</a> 
				<a class="nui-button" iconCls="icon-remove" onclick="remove('hkjh')">删除</a>
				<a class="nui-button" iconCls="icon-download" onclick="dc()">导出EXCEL</a>
			</div>
			<div id="hkjh" class="nui-datagrid" style="width: 100%; height: auto"
				url="com.bos.bizInfo.bizInfo.getHkjhList.biz.ext" dataField="hkjhs"
				allowResize="true" showReloadButton="false" allowCellEdit="true"
				allowCellSelect="true" showPager="false"
				sizeList="[10,20,30,50,100]" multiSelect="false" pageSize="100"
				sortMode="client">
				<div property="columns">
					<div type="checkcolumn">选择</div>
					<div type="indexcolumn" width="40px">期次</div>
					<div field="END_DATE" headerAlign="center" dateFormat="yyyy-MM-dd">还款日期 
					<input property="editor" class="nui-datepicker"dateFormat="yyyy-MM-dd" onvaluechanged="dataChange(1)" />
					</div>
					<div field="DAYS" headerAlign="center">天数 <input property="editor" class="nui-text" /></div>
					<div field="AMT" headerAlign="center">还款金额 <input property="editor" class="nui-textbox" onvaluechanged="dataChange(2)"/></div>
					<div field="BJ" headerAlign="center">本金 <input property="editor" class="nui-textbox"onvaluechanged="dataChange(3)" />
					</div>
					<div field="LX" headerAlign="center">利息 <input property="editor" class="nui-text" /></div>
					<div field="SYBJ" headerAlign="center">剩余本金 <input property="editor" class="nui-text" /></div>
				</div>
			</div>
		</div>
	<script type="text/javascript">
	nui.parse();
	nui.get("schkq").hide();
	$("#schkq_lab").css("display","none");

	
	var thisUserId="<%=com.bos.pub.GitUtil.getCurrentUserId()%>";
		var amountDetailId = "XW" + thisUserId;
		var form = new nui.Form("#form");
		nui.get("hkfs").setData(getDictData('XD_SXCD1162', 'str', '1410'));
		nui.get("jxzq").setData(getDictData('XD_SXCD1018', 'str', '1,2,3,4,5'));

		function initDataTable() {
		
		
		
			if (form.isValid() == true) {
				nui.get("schkq").show();
				$("#schkq_lab").css("display", "block");
			} else {
				alert("请完整输入必输项！");
				return;
			}

			var dkqx1 = nui.get("dkqx").getValue();
			if (dkqx1 > 60) {
				alert("贷款期限不能超过60个月");
				return;
			}

			git.mask();

			var json = nui.encode({
				"amountDetailId" : amountDetailId
			});
			$.ajax({
				url : "com.bos.bizInfo.bizInfo.delHkjhList.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				cache : false,
				success : function(mydata) {
				}
			});
			var bj = nui.get("bj").getValue();
			var yearrate = nui.get("yearrate").getValue() / 100;
			var jxzq = nui.get("jxzq").getValue();
			var dkqq = nui.get("dkqq").getValue();
			var dkqx = nui.get("dkqx").getValue();
			var hkqc = dkqx;
			if (jxzq == 2) {
				hkqc = Math.ceil(dkqx / 3);
			}
			if (jxzq == 3) {
				hkqc = Math.ceil(dkqx / 6);
			}
			if (jxzq == 4) {
				hkqc = Math.ceil(dkqx / 12);
			}

			var json = nui.encode({
				"bj" : bj,
				"yearrate" : yearrate,
				"jxzq" : jxzq,
				"dkqq" : dkqq,
				"dkqx" : dkqx,
				"hkqc" : hkqc
			});
			$.ajax({
				url : "com.bos.bizInfo.bizInfo.SaveHkssQukList.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				cache : false,
				success : function(mydata) {
					if (mydata.resultMap.resultMsg != '1') {
						git.unmask();
						nui.alert(mydata.resultMap.resultMsg);
						return;
					}
					git.unmask();
				
						if (nui.get("schkq").getValue() != null
					&& nui.get("schkq").getValue() != "") {
					 firstRepayDayChange();
					 nui.get("schkq").setValue("");
 			}else{
 			
						loadformhkjh();
 			}
				}
			});
		}
		//重新开始
		function refresh() {
		 nui.get("schkq").setValue("");
			var json = nui.encode({
				"amountDetailId" : amountDetailId
			});
			git.mask();
			$.ajax({
				url : "com.bos.bizInfo.bizInfo.delHkjhList.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				cache : false,
				success : function(mydata) {
					git.unmask();
					initDataTable();
				}
			});
		}
		//动态列表删除操作
		function remove(gr) {
			var row = nui.get(gr).getSelected();
			git.mask();
			if (row) {
				nui
						.confirm(
								"确定删除吗？",
								"确认",
								function(action) {
									if (action != "ok")
										return;
									//删除数据库数据
									if (row.UUID) {
										var bj = nui.get("bj").getValue();
										var dkqx = nui.get("dkqx").getValue();
										var hkqc = dkqx;
										if (jxzq == 2) {
											hkqc = Math.ceil(dkqx / 3);
										}
										if (jxzq == 3) {
											hkqc = Math.ceil(dkqx / 6);
										}
										if (jxzq == 4) {
											hkqc = Math.ceil(dkqx / 12);
										}
										var yearrate = nui.get("yearrate")
												.getValue() / 100;
										var jxzq = nui.get("jxzq").getValue();
										var dkqq = nui.get("dkqq").getValue();
										var json = nui
												.encode({
													"delqc" : row.QC,
													"bj" : bj,
													"hkqc" : hkqc,
													"yearrate" : yearrate,
													"jxzq" : jxzq,
													"dkqq" : dkqq,
													"uuid" : row.UUID,
													"amountDetailId" : row.AMOUNT_DETAIL_ID
												});
										$
												.ajax({
													url : "com.bos.bizInfo.bizInfo.delSingleHkjhQuk.biz.ext",
													type : 'POST',
													data : json,
													contentType : 'text/json',
													cache : false,
													success : function(mydata) {
														nui.get(gr).removeRow(
																row, true);/* 删除页面行 */
														git.unmask();
														loadformhkjh();

													}
												});
									}
								});
			} else {
				nui.alert("请选中一条记录");
							git.unmask();
				
				return;
			}
		}

		function loadformhkjh() {
			var json = nui.decode({
				"amountDetailId" : amountDetailId
			});
			var grid = nui.get("hkjh");
			grid.load(json);
		}

		function dataChange(a) {
			nui.get("hkjh").commitEdit();
			var hkjh = nui.get("hkjh").getSelected();
			var o = form.getData();
			o.hkjh = hkjh;
			o.hkjh.qc = o.hkjh.QC;
			o.hkjh.endDate = hkjh.END_DATE;
			o.hkjh.days = hkjh.DAYS;
			o.hkjh.amt = hkjh.AMT;
			o.hkjh.bj = hkjh.BJ;
			o.hkjh.lx = hkjh.LX;
			o.hkjh.sybj = hkjh.SYBJ;
			o.hkjh.bz1 = hkjh.BZ1;
			o.hkjh.bz2 = hkjh.BZ2;
			o.hkjh.bz3 = hkjh.BZ3;
			o.hkjh.amountDetailId = hkjh.AMOUNT_DETAIL_ID;
			o.a = a;

			var bj = nui.get("bj").getValue();
			var dkqx = nui.get("dkqx").getValue();
			var hkqc = dkqx;
			if (jxzq == 2) {
				hkqc = Math.ceil(dkqx / 3);
			}
			if (jxzq == 3) {
				hkqc = Math.ceil(dkqx / 6);
			}
			if (jxzq == 4) {
				hkqc = Math.ceil(dkqx / 12);
			}
			var yearrate = nui.get("yearrate").getValue() / 100;
			var jxzq = nui.get("jxzq").getValue();
			var dkqq = nui.get("dkqq").getValue();
			o.bj = bj;
			o.hkqc = hkqc;
			o.yearrate = yearrate;
			o.jxzq = jxzq;
			o.dkqq = dkqq;
			var json = nui.encode(o);
			$.ajax({
				url : "com.bos.bizInfo.bizInfo.updateHkjhFlgQuk.biz.ext",
				type : 'POST',
				data : json,
				cache : false,
				contentType : 'text/json',
				cache : false,
				success : function(mydata) {
					git.unmask();

					loadformhkjh();
					 if(mydata.resultMap.resultMsg =='1'){
					
					}else{
						nui.alert(mydata.resultMap.resultMsg);
						return;
					} 
				}
			});
		}

		function firstRepayDayChange() {
			if (nui.get("schkq").getValue() == null
					|| nui.get("schkq").getValue() == "") {
				return;
			}
			git.mask();
			var bj = nui.get("bj").getValue();
			var dkqx = nui.get("dkqx").getValue();

			var hkqc = dkqx;
			if (jxzq == 2) {
				hkqc = Math.ceil(dkqx / 3);
			}
			if (jxzq == 3) {
				hkqc = Math.ceil(dkqx / 6);
			}
			if (jxzq == 4) {
				hkqc = Math.ceil(dkqx / 12);
			}
			var yearrate = nui.get("yearrate").getValue() / 100;
			var jxzq = nui.get("jxzq").getValue();
			var dkqq = nui.get("dkqq").getValue();
			var schkq = nui.get("schkq").getValue();
			var json = nui.encode({
				"amountDetailId" : amountDetailId,
				"schkq" : schkq,
				"bj" : bj,
				"hkqc" : hkqc,
				"yearrate" : yearrate,
				"jxzq" : jxzq,
				"dkqq" : dkqq
			});
			$
					.ajax({
						url : "com.bos.bizInfo.bizInfo.changeFirstRepayDateQuk.biz.ext",
						type : 'POST',
						data : json,
						contentType : 'text/json',
						cache : false,
						success : function(mydata) {
							git.unmask();

							loadformhkjh();
							if(mydata.resultMap.resultMsg =='1'){
										git.unmask();
							
							}else{
								nui.alert(mydata.resultMap.resultMsg);
								return;
							}
						}
					});
		}
		
		function dc(){
			git.mask();
				var ifrm = document.getElementById("exportFrame");
		
				var json = nui.encode({"amountDetailId":amountDetailId});

 		 
 		$.ajax({
	            url: "com.bos.csm.pub.ibatis.loan_XWHKSSDownloadEXCEL.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	git.unmask("form");
	            	if(text.msg){
					git.unmask();
           ifrm.src=nui.context +"/pub/io/file/download.jsp?deleteFile=true";
	            	
	            	}else{
	            	git.unmask();
	            	 nui.alert("下载数据有误！");
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	       			git.unmask("form");
	                nui.alert(jqXHR.responseText);
	            }
		});
		}
	</script>
</body>
</html>