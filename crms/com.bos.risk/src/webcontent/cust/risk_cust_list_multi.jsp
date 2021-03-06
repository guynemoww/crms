<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@include file="/common/nui/common.jsp" %>
	</head>
	<body>
		<center>
			<div id="form" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;margin-top:5px;" >
				<input name="item.custFlag" id="item.custFlag" class="nui-hidden" value="<%=request.getParameter("custFlag")%>"/>
				<div id="menuField" class="nui-dynpanel" columns="6">
					<label>客户名称</label>
					<input id="item.partyName" name="item.partyName" required="false" class="nui-textbox nui-form-input"  />
			
					<label>证件类型</label>
					<input id="item.certType" name="item.certType" required="false"  class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002"  allowInput="false" />
					
					<label>证件号码</label>
					<input id="item.partyName" name="item.certNum" required="false" class="nui-textbox nui-form-input" />
					
				</div>
				<div class="nui-toolbar" style="text-align:right;border:none" >
					<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
	  				<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
				</div>
			</div>
			
			<!-- 客户信息展示 -->
			<div id="datagrid" 
				class="nui-datagrid" 
				style="width:99.5%;height:auto;"
				sortMode="client" 
				url="com.bos.risk.cust.queryRiskCustList.biz.ext" 
				dataField="items" 
				allowAlternating="true" 
				multiSelect="true" 
				showEmptyText="true" 
				showPager="true" 
				emptyText="没有查到数据" 
				showReloadButton="false" 
				showColumnsMenu="true" 
				onrowdblclick="" 
				allowCellEdit="true" 
				allowCellSelect="true"
				sizeList="[10,20,50,100,500]" 
				pageSize="10">
				<div property="columns">
					<div type="checkcolumn" ></div>
				 	<div field="partyName" allowSort="true" headerAlign="center" autoEscape="false">客户名称</div>
	       			<div field="certType" allowSort="true" headerAlign="center" dictTypeId="CDKH0002">证件类型</div>       
	        		<div field="certNum" allowSort="true" headerAlign="center" >证件号码</div>
					<div field="totalCredAmt" allowSort="true" headerAlign="center" dataType="currency">授信余额</div>
					<div field="sortStatus" allowSort="true" headerAlign="center">状态</div><!-- 已分类、未分类 -->
				</div>
			</div>
			<div style="margin-top:10px;">
				<input class="nui-button" style="margin-right:5px;" text="发起分类备案" iconCls="icon-upload" onclick="startRiskClassify()" />
			</div>
		</center>

		<script type="text/javascript">
			nui.parse();
			var grid = nui.get("datagrid");
	  		var form = new nui.Form("#form");
	  		var claMethod = "<%=request.getParameter("claMethod")%>";
	  		var custFlag = "<%=request.getParameter("custFlag")%>";
	  		
	  		query();
	  		
	  		function reset(){
	  			var menuField = new nui.Form("#menuField");
	  			menuField.reset();
				query();
			}
	  		
	  		function query(){
	  		   git.mask();
		       var o = form.getData();
		       grid.load(o);
			   git.unmask();
			}
			
	  		//发起分类
			 function startRiskClassify(){
				var rows = grid.getSelecteds();
				if(rows.length > 0){
		            var partyIds = [];
		            var partyIdStr = "";
		            for(var i=0; i<rows.length;i++){
		            	partyIds.push(rows[i].partyId);
		            	if(i == rows.length - 1){
			            	partyIdStr += rows[i].partyId;
		            	}else{
			            	partyIdStr += rows[i].partyId+",";
		            	}
		            }
				 	git.mask();
				 	var json = nui.encode({partyIds:partyIds,claMethod:claMethod,custFlag:custFlag});
					$.ajax({
						url:"com.bos.risk.sort.addRiskSortProcess.biz.ext",
						type:'POST',
						data:json,
						cache: false,
						contentType:'text/json',
						success:function(text){
							var returnJson = nui.decode(text);
							git.unmask();
							var resultCode = returnJson.resultCode;
							var resultMsg = returnJson.resultMsg;
							var acApplyId = returnJson.acApplyId;
							var processId = returnJson.processId;
							var workItemId = returnJson.workItemId;
							if(resultCode == '0000'){
								nui.open({
						            url: nui.context + "/risk/sort/risk_sort_tree.jsp?bizId="+acApplyId+"&bizType="+claMethod+"&custFlag="+custFlag+"&processInstId="+processId+"&workItemId="+workItemId+"&wflow=2",
						            title: "待分类列表", 
					        		state:"max",
						        	allowResize:true,
						        	showMaxButton: true,
						        	onload: function(e){
						            	var iframe = this.getIFrameEl();
						            	var text = iframe.contentWindow.document.body.innerText;
						            },
						            ondestroy: function (action) {
						                
						            }
					        	});
							}else{  //没有要发起的借据
								nui.alert(resultMsg, "系统提示"+resultCode);
							}
						},
			            error: function() {
			            	nui.alert("分类清单发起分类失败!", "系统提示"+resultCode);
			            	git.unmask();
				        }
					});
				}else{
					alert("请选中一条记录");
				}
			}
		</script>
	</body>
</html>
