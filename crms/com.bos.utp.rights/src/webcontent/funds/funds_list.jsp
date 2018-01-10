<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 
  - Author(s): cp
  - Date: 2017-07-15 16:23:04
  - Description:
-->
<head>
<title>公积金列表</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
	<div id="tabs1" class="nui-tabs" activeIndex="0" style="width: 100%; height: auto;">
		<div title="公积金账号信息查询">
			<div id="form1" class="nui-form" style="width: 99.5%; height: auto; overflow: hidden;">
				<div class="nui-dynpanel" columns="6">
					<label>机构名称：</label>
					<input id="item.orgCode" name="item.orgCode" required=true class="nui-buttonEdit" allowInput="false" onbuttonclick="selectEmpOrg" dictTypeId="org"/>
				</div>
				<div class="nui-toolbar" style="text-align: right; border: none">
					<a class="nui-button" iconCls="icon-search" onclick="initPage()">查询</a>
				</div>
			</div>
			<div style="width: 99.5%">
				<div class="nui-toolbar" style="width: 99.5% border-top:1px solid #8ba0bc; border-bottom: none; text-align: left; margin-top: 7px">
					<a id="addCust" style="margin-left: 5px" class="nui-button" iconCls="icon-add" onclick="addcust()">增加</a>
					<a id="editCust" class="nui-button" iconCls="icon-edit" onclick="edit()">编辑</a>
					<!-- <a id="Synchronization" class="nui-button" iconCls="icon-upload" onclick="SynchronizationEcif()">同步ECIF信息</a> -->
				</div>
			</div>
			<div id="datagrid1" class="nui-datagrid" style="width: 99.5%; height: auto;" sortMode="client" url="com.bos.utp.rights.funds.fundlist.biz.ext" dataField="items" allowResize="true" showReloadButton="false" allowAlternating="true" sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
				<div property="columns">
					<div type="checkcolumn">选择</div>
					<div field="orgCode" headerAlign="center" allowSort="true" align="center" autoEscape="false" width="20%" dictTypeId="org">机构名称</div>
					<div field="entrustAcc" headerAlign="center" allowSort="true" align="center" width="20%">委托存款账号</div>
					<div field="entrustReturnAcc" headerAlign="center" align="center" allowSort="true" width="20%">委托贷款收息账号</div>
					<div field="entrustReturnPrincipalAcc" headerAlign="center" align="center" allowSort="true" width="20%">委托人收本账号</div>
					<div field="entrustReturnInterestAcc" headerAlign="center" align="center" allowSort="true" width="20%">委托人收息账号</div>
				</div>
			</div>
		</div>
	</div>


	<script type="text/javascript">
    	nui.parse();
    	 var form = new nui.Form("#form1"); 
		 var grid = nui.get("datagrid1");
		 initPage();
	     function initPage(){
	    		var data = form.getData();
	       		 grid.load(data);
	    	}
    	function addcust() {
			nui.open({
						url : nui.context
								+ '/utp/rights/funds/funds_add.jsp',
						showMaxButton : true,
						title : "添加公积金账号信息",
						width : 900,
						height : 300,
						onload : function(e) {
							var iframe = this.getIFrameEl();
							var text = iframe.contentWindow.document.body.innerText;
						},
						ondestroy : function(action) {
							if (action == "ok") {
								initPage();
							}
						}
					});
		}
		function edit(v) {
			var row = grid.getSelected();
			if (row) {
				var url = "/utp/rights/funds/funds_edit.jsp?orgCode="+ row.orgCode;
				nui.open({
							url : nui.context + url,
							showMaxButton : true,
							title : "修改公积金账号信息",
							width : 900,
							height : 300,
							onload : function(e) {
								var iframe = this.getIFrameEl();
								var text = iframe.contentWindow.document.body.innerText;
							},
							ondestroy : function(action) {
								initPage();
							}
						});

			} else {
				alert("请选中一条记录");
			}

		}
		 function selectEmpOrg(e) {
	        var btnEdit = this;
	        nui.open({
	            url: nui.context + "/utp/org/employee/select_all_org_tree.jsp",
	            showMaxButton: true,
	            title: "选择机构",
	            width: 800,
	            height: 500,
	            ondestroy: function (action) {            
	                if (action == "ok") {
	                    var iframe = this.getIFrameEl();
	                    var data = iframe.contentWindow.GetData();
	                    data = nui.clone(data);
	                    
	                    if (data) {
	                    
	                    	if(data.auditbankno==null || data.auditbankno=='' || data.auditbankno == 'null'){
	                    		nui.alert("该机构无对应核算机构!");
	                    	}
	                    	
	                    	self.orglevel=data.orglevel;
	                        btnEdit.setValue(data.orgcode);
	                        btnEdit.setText(data.orgname);
	                    }
	                }
	            }
	        });            
	    }
    </script>
</body>
</html>