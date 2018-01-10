<%@page pageEncoding="UTF-8"%>

<html>
<!-- 
  - Author(s): fengjiahui
  - Date: 2014-02-28 16:34:13
  - Description:批量平台客户检查的发起
-->
<head>
<%@include file="/common/nui/common.jsp"%>
<title>现场检查记录</title>
</head>
<body>
<div id="inspectform" class="nui-dynpanel" columns="4"  >
	<label>现场检查时间：</label>
	<input name="inspectDate" id="inspectDate" class="nui-datepicker nui-form-input" format="yyyy-MM-dd" /> 
	<label>现场检查地点：</label>
	<input name="inspectPlace" id="inspectPlace"  class="nui-textbox nui-form-input"/> 
	<label>现场检查人员：</label>
	<input name="inspectName" id="inspectName" class="nui-textbox nui-form-input"/> 
	<label>检查人员职务：</label>
	<input name="inspectPost" id="inspectPost" class="nui-textbox nui-form-input"/> 
	<label>企业接待人员：</label>
	<input class="nui-textbox nui-form-input" name="receptionName" id="receptionName"/>
	<label>接待人员职务：</label>
	<input class="nui-textbox nui-form-input" name="receptionPost" id="receptionPost">	
	<label>纳入现场检查的原因：</label>
	<input class="nui-combobox" name="putLocaleInspectReason" id="putLocaleInspectReason" data="putReason"/>
	<div colspan="2"></div>
	<div style="padding:2px;"><label><span >存在的主要问题:</span></label></div>
	<div colspan="3" style="margin-top:2px"><input class="nui-textarea  nui-form-input" name="exitMainProblem"  id="exitMainProblem" width="100%" height="50px"/></div>
	<div style="padding:2px;"><label><span >授信后特定的管理要求:</span></label></div>
	<div colspan="3" style="margin-top:2px"><input class="nui-textarea  nui-form-input" name="specialManageRequire"  id="specialManageRequire" width="100%" height="50px"/></div>
</div>

<div id="datagrid1" class="nui-datagrid" showPager="false" dataField="credits"
	        url="com.bos.aft.aft_spot_inspect.queryCreditMessage.biz.ext" editNextOnEnterKey="true" style="margin-top:20px;" >
	        <div property="columns"> 
	       		 <div field="productType"  headerAlign="center">授信品种</div>      
			     <div field="availableExposure"  headerAlign="center">授信余额</div>                              
	             <div field=""  headerAlign="center">是否便捷贷业务</div>
	            <div field="guarantyType"  headerAlign="center">担保方式 </div> 
	            <div field=""  headerAlign="center">保证人</div>
	        </div>
</div>	

<div class="" id="saveBtn" style="border-bottom:0;text-align:right;margin-top: 20px;">
		 <a class="nui-button" iconCls="icon-save" onclick="btnSave()">保存</a>
</div>

	<script type="text/javascript">
		var putReason = [
			{id:"1",text:"非便捷贷客户"},
			{id:"2",text:"授信后检查结论为'退出'类"},
			{id:"3",text:"分类为关注类"},
			{id:"4",text:"存在黄色及以上级别预警信号的小企业授信客户"},
			{id:"5",text:"其他"}		
		];
		var param=<%=request.getParameter("param") %>;
		var callback="<%=request.getParameter("callback") %>";
		if(callback=="y"){
			$("#saveBtn").hide();
			$(".nui-textbox").attr("allowInput","false");
			$(".nui-combobox").attr("enabled","false");
			$(".nui-datepicker").attr("enabled","false");
		}
		
		nui.parse();
		//git.mask(); //mask中可加一个参数表示要进行遮罩的页面元素，不加时默认为整个页面。
		//git.mask("form1");
		var form = new nui.Form("#inspectform");                             //现场检查记录表
		var grid = nui.get("datagrid1");                                     //授信情况表
		
		function query(){/* 加载现场检查记录信息 */
		   var json=nui.encode({"param":param});
		   nui.ajax({
                url: "com.bos.aft.aft_spot_inspect.querySpotInspect.biz.ext",
                data:json,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                	form.setData(text.spotInpsect);
                	grid.load({"partyId":param.partyId});                                //加载授信情况
                	query1();                                                            //查询存在的问题
					git.unmask();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    git.unmask();
                }
            });			
		}
		query();
    
    function btnSave(){/* 保存存在的问题信息 */
			var jsonData=nui.encode({"param":param,"localeInspect":form.getData()});
			//alert(jsonData);
			nui.ajax({
                url: "com.bos.aft.aft_spot_inspect.updateLocaleInspect.biz.ext",
                data:jsonData,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                	nui.alert(text.msg);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });		
		}
	</script>
</body>
</html>