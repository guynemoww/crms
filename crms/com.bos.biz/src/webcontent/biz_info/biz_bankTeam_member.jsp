<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): zhufaying
  - Date: 2014-03-28 15:52:21
  - Description:
-->
<head>
<title>银团成员</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
	<input id="bankTs.syndicatedObjectCd" name="bankTs.syndicatedObjectCd"  class="nui-hidden nui-form-input " />
	<input id="bankTs.syndicatedStructId" name="bankTs.syndicatedStructId"  class="nui-hidden nui-form-input " />
	<div id="datagrid1" class="nui-datagrid" style="width:99.5%;"sortMode="client"
	  	url="com.bos.bizInfo.bizInfo.getBankTmList.biz.ext" dataField="bankMembers"
	    allowAlternating="true" multiSelect="false" showEmptyText="true"
	 	emptyText="没有查到数据" showReloadButton="false"
		onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
		sizeList="[10,20,50,100]" pageSize="10">
	    
	    <div property="columns">
	        	<div type="checkcolumn">选择</div>             
		        <div field="syndicatedObjectCd" allowSort="true" headerAlign="center"  dictTypeId="CDXY0064">银团组团对象</div>
		        <div field="memberBankName" allowSort="true" headerAlign="center">成员行名称</div>
		        <div field="membersUserNum" allowSort="true" headerAlign="center" dictTypeId="user">成员行经办人</div>
		    <%--    <div field="memberRoleCd" allowSort="true" headerAlign="center" dictTypeId="XD_SXCD1065">成员分工</div>  --%>
		        <div field="currencyCd" allowSort="true" headerAlign="center" dictTypeId="CD000001">币种</div> 
		        <div field="promiseAmount" allowSort="true" headerAlign="center" dataType="currency">承诺金额</div>
		        <div field="promiseRatio" allowSort="true" headerAlign="center">承诺比例(%)</div>
	     </div>
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	    <a class="nui-button" onclick="add" id="biz_bankTeam_member_add" iconCls="icon-add">增加</a>
	    <a class="nui-button" onclick="view" id="biz_bankTeam_member_view" iconCls="icon-node">查看</a>
	    <a class="nui-button" onclick="del" id="biz_bankTeam_member_delete" iconCls="icon-remove" >删除</a>
	</div>
</center>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("datagrid1");
	var applyId = "<%=request.getParameter("applyId") %>";
	var proFlag = "<%=request.getParameter("proFlag") %>";//流程中查看标识
	var syndicatedStructId = "";
	var syndicatedLoanAmt = "";
	initPage();
	function initPage(){
		var json = nui.encode({"applyId":applyId});
        $.ajax({
            url: "com.bos.bizInfo.bizInfo.getBankTeamStruct.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	var o = nui.decode(text);
            	syndicatedStructId = text.bankTs.syndicatedStructId;
            	syndicatedLoanAmt = text.bankTs.syndicatedLoanAmt;
            	nui.get("bankTs.syndicatedStructId").setValue(text.bankTs.syndicatedStructId);
            	nui.get("bankTs.syndicatedObjectCd").setValue(text.bankTs.syndicatedObjectCd);
            	if(text.bankTs.syndicatedLoanAmt==null||text.bankTs.syndicatedLoanAmt==''){
            		nui.get("biz_bankTeam_member_add").hide();
            		nui.get("biz_bankTeam_member_view").hide();
            		nui.get("biz_bankTeam_member_delete").hide();
            	}
            	grid.load({"syndicatedStructId":syndicatedStructId});
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
            }
        });
        
        if("1" != proFlag){
			nui.get("biz_bankTeam_member_add").hide();
			nui.get("biz_bankTeam_member_delete").hide();
		}
	}
	
	//增加
	function add(e){
		//区分内部银团与外部银团
		var syndicatedObjectCd=nui.get("bankTs.syndicatedObjectCd").getValue();
		var syndicatedStructId=nui.get("bankTs.syndicatedStructId").getValue();
		if(syndicatedObjectCd=="01"){//行外
			var infourl = nui.context+"/biz/biz_info/biz_bankTeam_member_add2.jsp?syndicatedStructId="+syndicatedStructId+"&syndicatedObjectCd="+syndicatedObjectCd+"&flg=1&syndicatedLoanAmt="+syndicatedLoanAmt;
		}else{
			var infourl = nui.context+"/biz/biz_info/biz_bankTeam_member_add.jsp?syndicatedStructId="+syndicatedStructId+"&syndicatedObjectCd="+syndicatedObjectCd+"&flg=1&syndicatedLoanAmt="+syndicatedLoanAmt;
		}
		nui.open({
			url:infourl,
			title:"提示：可点击最大化按钮放大此窗口",
			width: 800,
            height: 600,
            ondestroy: function(e) {
            	grid.load({"syndicatedStructId":syndicatedStructId});
            }
		});
	}
	//查看
	function view(e){
		var row=grid.getSelected();
		if (null == row) {
			nui.alert("请选择一条记录");
			return false;
		}
		//区分内部银团与外部银团
		var syndicatedObjectCd=nui.get("bankTs.syndicatedObjectCd").getValue();
		var syndicatedStructId=nui.get("bankTs.syndicatedStructId").getValue();
		if(syndicatedObjectCd=="01"){
			var infourl = nui.context+"/biz/biz_info/biz_bankTeam_member_add2.jsp?syndicatedStructId="+syndicatedStructId+"&syndicatedObjectCd="+syndicatedObjectCd+"&flg=2&syndicatedMemberId="+row.syndicatedMemberId;
		}else{
			var infourl = nui.context+"/biz/biz_info/biz_bankTeam_member_add.jsp?syndicatedStructId="+syndicatedStructId+"&syndicatedObjectCd="+syndicatedObjectCd+"&flg=2&syndicatedMemberId="+row.syndicatedMemberId;
		}
		nui.open({
			url:infourl,
			title:"提示：可点击最大化按钮放大此窗口",
			width: 800,
            height: 600,
            ondestroy: function(e) {
            	grid.load({"syndicatedStructId":syndicatedStructId});
            }
		});
	}
	
	//删除
	function del(){
		var row=grid.getSelected();
		if (null == row) {
			nui.alert("请选择一条记录");
			return false;
		}
		nui.confirm("确定删除吗？","确定",function(action){
			if(action=="ok"){
				if(row.syndicatedMemberId){
					var json = nui.encode({"syndicatedMemberId":row.syndicatedMemberId});
			        $.ajax({
			            url: "com.bos.bizInfo.bizInfo.delBankMember.biz.ext",
			            type: 'POST',
			            data: json,
			            cache: false,
			            contentType:'text/json',
			            success: function (text) {
			            	//alert(nui.encode(text));
			            	if(text.data&&text.data.msg){
			            		nui.alert(text.data.msg); //失败时后台直接返回出错信息
			            	}else{
			            		nui.get(grid).removeRow(row,true);/* 删除页面行 */
				            	grid.load({"syndicatedStructId":syndicatedStructId});
				            	nui.alert("删除成功");
			            	}
			            },
			            error: function (jqXHR, textStatus, errorThrown) {
			                nui.alert(jqXHR.responseText);
			            }
			        });
			    }else{
            		nui.get(grid).removeRow(row,true);/* 删除页面行 */
            	}
		    }
	    });
	}
</script>
</body>
</html>