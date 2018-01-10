<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-06-03 10:28:23
  - Description:
-->
<head>
<title>汇票明细信息</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<iframe name="exportFrame" id="exportFrame" src="" style="display:none;"></iframe>
<div id="form1" style="width:100%;height:auto" class="nui-form">
	<div class="nui-toolbar" style="text-align:left;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
		<a class="nui-button" id="view" iconCls="icon-node" onclick="view(1)">查看</a>
		<!-- <a class="nui-button" id="view_add" iconCls="icon-add" onclick="addpj()">增加</a> -->
		<!-- <a class="nui-button" id="view_edit" iconCls="icon-edit" onclick="editpj(0)">编辑</a> -->
		<!-- <a class="nui-button" id="view_remove" iconCls="icon-remove" onclick="removepj()">删除</a> -->
		<!-- <a class="nui-button" id="view_removeAll" iconCls="icon-remove" onclick="removeAllPj()">全部删除</a> -->
		<!-- <a class="nui-button"  iconCls="icon-upload" id="view_import" onclick="uploadExcel();" disableOnClick="true"/>导入</a> -->
		<!-- <a class="nui-button" iconCls="icon-download" onclick="dc()">导出EXCEL</a> -->
	</div>
	<div id="gridpj" class="nui-datagrid" style="width:100%;height:auto" 
		url="com.bos.pay.pjxx.queryHpxxs.biz.ext" dataField="hps"
		allowResize="false" showReloadButton="false"  allowCellEdit="false" 
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div type="indexcolumn" >序号</div>
			<div field="pyeAcctNm" headerAlign="center" allowSort="true" width="20%">收款人全称</div>
			<div field="loanAmt" headerAlign="center" allowSort="true" >出票金额</div>
			<div field="issuDt" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">出票日期</div>
			<div field="drftExpDt" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">汇票到期日</div>
			<div field="pjhm" headerAlign="center" allowSort="true" >汇票号码</div>
			<div field="hpzt" headerAlign="center" allowSort="true" >汇票状态</div><!-- 汇票状态0-正常； 1-删除 -->
		</div>
	</div>
</div>
<script type="text/javascript">	
	nui.parse();
	var proFlag ="<%=request.getParameter("proFlag") %>";
	var loanId ="<%=request.getParameter("loanId") %>"; //放款ID
/* 	if(proFlag!='1'){
		$("#view_add").css("display","none");
		$("#view_edit").css("display","none");
		$("#view_remove").css("display","none");
		$("#view_removeAll").css("display","none");
		$("#view_import").css("display","none");
	} */
	search();
	function search(){
		var json = nui.decode({"loanId":loanId});
		var gridpj = nui.get("gridpj");
		gridpj.load(json);
	}
	
	// 引用查看
	function view(v){
		var grid = nui.get("gridpj");
	    var row = grid.getSelected();
	    if (row) {
	        nui.open({
	            url: nui.context+"/biz/biz_product_detail/pjxx/biz_pj_edit.jsp?applyDetailId="+row.applyDetailId+"&view="+1,
	            title: "查看票据信息", 
	            width: 800,
	    		height: 500,
	            allowResize:true,
	    		showMaxButton: true,
	            onload: function () {
	                var iframe = this.getIFrameEl();
	                var data = row;
	                //iframe.contentWindow.SetData(data);
	            }
	        });
	    }else{
	    	alert("请选择票据信息！");
	    }
	}
	
	
/* 	//添加pj信息
	function addpj(){
	    nui.open({
	        url: nui.context + "/pay/pjxx/pay_pj_add.jsp?loanId="+loanId,
	        title: "新增", 
	        width: 800, 
	    	height: 500,
	    	allowResize:true,
	    	showMaxButton: true,
	        ondestroy: function (action) {
	       		 if(action=="ok"){
	             	search();
	           	 }
	        }
	    });
	}
	//编辑票据信息
	function editpj(v) {
		var grid = nui.get("gridpj");
	    var row = grid.getSelected();
	    if (row) {
	        nui.open({
	            url: nui.context+"/pay/pjxx/pay_pj_edit.jsp?moneyUseId="+row.moneyUseId+"&view="+v,
	            title: "编辑", 
	            width: 800,
	    		height: 500,
	            allowResize:true,
	    		showMaxButton: true,
	            onload: function () {
	                var iframe = this.getIFrameEl();
	                var data = row;
	                //iframe.contentWindow.SetData(data);
	            },
	            ondestroy: function (action) {
	                if(action=="ok"){
	             		search();
	           	 	}
	            }
	        });
	    }else{
	    	alert("请选择项目信息！");
	    }
	}
	//删除票据
	function removepj() {
		var grid = nui.get("gridpj");
	    var rows = grid.getSelected();
		if (null == rows) {
			nui.alert("请选择项目信息！");
			return false;
		}
		var json = nui.encode({"tbLoanHpAmt":rows});
		nui.confirm("确定删除吗？","确认",function(action){
	    	if(action!="ok") return;
	        $.ajax({
	            url: "com.bos.pay.pjxx.deleteHpxx.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	if (text.msg) {
	            		nui.alert(text.msg);
	            		return;
	            	}
	             	search();
	            },
	            error: function () {
	            	nui.alert("操作失败！");
	            }
	        });
	    }); 
	}
	//删除全部票据
	function removeAllPj() {
		var grid = nui.get("gridpj");
		var json = nui.encode({"loanId":loanId});
		nui.confirm("确定全部删除吗？","确认",function(action){
	    	if(action!="ok") return;
	        $.ajax({
	            url: "com.bos.pay.pjxx.deleteAllPj.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	if (text.msg) {
	            		nui.alert(text.msg);
	            		return;
	            	}
	             	search();
	            },
	            error: function () {
	            	nui.alert("操作失败！");
	            }
	        });
	    }); 
	}
	
	function uploadExcel(){
		nui.open({
            url: nui.context+"/pay/pjxx/pay_pj_upload.jsp?loanId="+loanId,
            showMaxButton: true,
            title: "导入", 
            width: 800,
	        height: 400,
            ondestroy: function (action) {
            	search();
            }
        });
	}
	//导出excel
	function dc(){
		git.mask();
		var ifrm = document.getElementById("exportFrame");
		var json = nui.encode({"loanId":loanId});
		$.ajax({
			url : "com.bos.pay.pjxx.pjxxDownloadEXCEL.biz.ext",
			type : 'POST',
			data : json,
			cache : false,
			contentType : 'text/json',
			success : function(text) {
				git.unmask("form1");
				if (text.msg) {
					git.unmask();
					ifrm.src = nui.context + "/pub/io/file/download.jsp?deleteFile=true";
				} else {
					git.unmask();
					nui.alert("下载数据有误！");
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				git.unmask("form1");
				nui.alert(jqXHR.responseText);
			}
		});
	} */
</script>
</body>
</html>