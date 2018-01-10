<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2014-12-22 12:15:51
  - Description:
-->
<head>
<title>台账查看</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
	<div id="panel1" class="nui-panel" title="台账信息" expanded="true" 
				style="width:99.5%;height:auto;" showToolbar="false"
				showCollapseButton="true" showFooter="false" allowResize="false">
			<div id="form" style="width:99.5%;height:auto;overflow:hidden;">
					<div class="nui-dynpanel" columns="4">
						<label>客户名称：</label>
						<input name="tbNplAssetsPerforming.partyName" class="nui-textbox nui-form-input"/>
						<label>机构名称：</label>
						<input name="tbNplAssetsPerforming.orgName" class="nui-textbox nui-form-input"/>
					</div>
			</div>
			<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			    <a class="nui-button"  iconCls="icon-search" onclick="query">查询</a>
			    <a class="nui-button" iconCls="icon-reset" onclick="reset">重置</a>
			</div>
		<div id="grid" class="nui-datagrid" style="width:99.5%;height:auto" 
			 url="com.bos.npl.book.AssetsBook.getAssetsBook.biz.ext" dataField="tbNplAssetsBooks" allowResize="false" 
			 showReloadButton="false" multiSelect="true" pageSize="5" sortMode="client" 
			 showPager="true" showFooter="false" virtualScroll="true">	 
			<div property="columns">
				<div type="checkcolumn">选择</div>
				<div field="aa" headerAlign="center" type="indexcolumn"  allowSort="false">序号</div>
				<div field="partyNum" headerAlign="center" allowSort="false">客户编号</div>
				<div field="partyName" headerAlign="center" allowSort="false">客户名称</div>
				<div field="partyType" headerAlign="center" allowSort="false">客户类型</div>
				<div field="creditAmt" headerAlign="center" allowSort="false">授信额度</div>
				<div field="balanceAmt" headerAlign="center" allowSort="false">授信余额</div>
				<div field="riskClassify" headerAlign="center" allowSort="false">最新分类</div>
				<div field="holdDate" headerAlign="center" allowSort="false">认定时间</div>
				
				<div field="dispostioType" headerAlign="center" allowSort="false">处理手段</div>
				
				<div field="overdueInterest" headerAlign="center" allowSort="false">表内外应收未收利息</div>
				<div field="overduedays" headerAlign="center" allowSort="false">逾期或垫款天数</div>
				<div field="intoDate" headerAlign="center" allowSort="false">转入保全时间</div>
				<div field="clearDate" headerAlign="center" allowSort="false">结清日期</div>
				<div field="responsibleUserNum" headerAlign="center" allowSort="false">保全责任人</div>
				<div field="responsibleOrgNum" headerAlign="center" allowSort="false">保全责任机构</div>
				
				<div field="orgNum" headerAlign="center" allowSort="false" dictTypeId="org">经办机构</div>
				<div field="userNum" headerAlign="center" allowSort="false" dictTypeId="user">客户经理</div>
				<div field="assetsStatus" headerAlign="center" allowSort="false">状态</div>
			</div>
		</div>
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
	    	<%--<a class="nui-button" id="btnCreate" iconCls="icon-add" onclick="move">逆移交</a>--%>
	    	<%--<a class="nui-button" id="btnCreate" iconCls="icon-add" onclick="cre">台账维护</a>--%>
	    	<%-- 资产保全台帐管理 --%>
		    	<a class="nui-button" id="btnCreate" iconCls="icon-add" onclick="view()">查看</a>
		    	<a class="nui-button" id="btnCreate" iconCls="icon-add" onclick="retrieve1()">回收</a>
		    	<a class="nui-button" id="btnCreate" iconCls="icon-add" onclick="addStatus()">更新状态</a>
		    	<a class="nui-button" id="btnCreate" iconCls="icon-add" onclick="print()">打印台账</a>
	    	<%-- 已核销资产台帐管理 --%>
		    	<a class="nui-button" id="btnCreate" iconCls="icon-add" onclick="add2(1)">新增</a>
		    	<a class="nui-button" id="btnCreate" iconCls="icon-add" onclick="">修改</a>
		    	<a class="nui-button" id="btnCreate" iconCls="icon-add" onclick="retrieve2()">回收</a>
		    	<a class="nui-button" id="btnCreate" iconCls="icon-add" onclick="qyfq()">权益放弃</a>
		    	<a class="nui-button" id="btnCreate" iconCls="icon-add" onclick="print()">打印台账</a>
	    	<%-- 以物抵债台帐管理 --%>
		    	<a class="nui-button" id="btnCreate" iconCls="icon-add" onclick="add3()">新增</a>
		    	<a class="nui-button" id="btnCreate" iconCls="icon-add" onclick="">修改</a>
		    	<a class="nui-button" id="btnCreate" iconCls="icon-add" onclick="sqqkba()">资产抵债收取情况备案</a>
		    	<a class="nui-button" id="btnCreate" iconCls="icon-add" onclick="czqkba()">资产抵债处置情况备案</a>
		    	<a class="nui-button" id="btnCreate" iconCls="icon-add" onclick="print()">打印台账</a>
		</div>
	</div>
</center>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("grid");
	var form = new nui.Form("#form");
	initPage();
	function initPage(){
			var json = nui.encode({"partyId":"<%=request.getParameter("corpid")%>"});
			$.ajax({
		        url: "com.bos.npl.assets.AssetsPerforming.getPatryInfoByPartyId.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (mydata) {
		        	var o = nui.decode(mydata);
		            form.setData(o);
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            nui.alert(jqXHR.responseText);
		        }
	    });
	}
	
	//查询
	query();
	function query(){
       	var form = new nui.Form("#form");
		var o = form.getData();
      	grid.load(o);
	}
	//逆移交
	function move(){
		var rows = grid.getSelecteds();
        if (rows.length > 0){
        	var json = nui.encode({"tbNplAssetsPerformings":rows});
	        $.ajax({
	            url: "com.bos.npl.assets.AssetsPerforming.movePerformingToTransfer.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (mydata) {
	                alert("逆移交成功！");
	                query();
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
	        });
        }else{
        	alert("请选择资产信息");
        }
	}

	//打开页面
	function openJsp(url){
		var grid = nui.get("grid");
		var rows = grid.getSelecteds();
		url+=rows[0].bookId;
		if (1==rows.length){
			nui.open({
				url:url,
				showMaxButton:true,
				title:"提示：可点击最大化按钮放大此窗口",
				width:"1200",
	            height:"600",
	            ondestroy: function(e) {
	            	
	            }
			});
        }else{
        	alert("请选择一条待移交资产信息！");
        }
	}
//资产保全台帐管理	
	//查看
	function view(){
		var url=nui.context+"/npl/book/assets_book_detail.jsp?bookId=";
		openJsp(url);
	}
	//回收
	function retrieve1(){
		var url=nui.context+"/npl/book/assets_book_retrieve.jsp?bookId=";
		openJsp(url);
	}
	//修改状态
	function addStatus(){
		var url=nui.context+"/npl/book/assets_book_status_add.jsp?bookId=";
		openJsp(url);
	}
	//打印
	function print(){
		alert("假装打印！");
	}
//已核销资产台帐管理
	//新增
	function add2(){
		var url=nui.context+"/npl/book/book_verification_register.jsp?bookId=";
		openJsp(url);
	}
	//修改
	//回收
	function retrieve2(){
		var url=nui.context+"/npl/book/book_verification_retrieve.jsp?bookId=";
		openJsp(url);
	}
	function qyfq(){
		alert("权益已放弃");
	}

//以物抵债台帐管理
	//新增

	function add3(){
		var url=nui.context+"/npl/book/book_mortgage_add.jsp?bookId=";
		openJsp(url);
	}
	//修改
	//收取情况备案
	function sqqkba(){
		var url=nui.context+"/npl/book/book_mortgage_retrieve.jsp?bookId=";
		openJsp(url);
	}
	//处置情况备案
	function czqkba(){
		var url=nui.context+"/npl/book/book_mortgage_disposition.jsp?bookId=";
		openJsp(url);
	}
</script>
</body>
</html>