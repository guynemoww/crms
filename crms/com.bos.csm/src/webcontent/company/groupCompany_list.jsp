<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-5-10:
-->
<head>
<title>集团客户维护</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width: 100%; height: auto;">
	<div title="集团客户">
			<div id="form1" class="nui-form" style="width: 99.5%; height: auto; overflow: hidden;">
				<div class="nui-dynpanel" columns="6">
					<label>集团客户名称：</label> 
					<input id="item.partyName"class="nui-textbox nui-form-input" name="item.partyName" />
					
					<label>集团成员名称：</label> 
					<input id="item.cyName"class="nui-textbox nui-form-input" name="item.memberName" />
					
					<label>集团成员证件类型：</label>
					<input id="item.certType"class="nui-dictcombobox nui-form-input"   dictTypeId="CDKH0002"  allowInput="false"  name="item.certType" />
					
					<label>集团成员证件号码：</label> 
					<input id="item.certNum"class="nui-textbox nui-form-input" name="item.certNum"  />
					
					<label>认定状态</label>
					<input id="item.status" class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD0231" name="item.status" />
				</div>
				<div class="nui-toolbar" style="text-align: right; padding-right: 20px; border: 0">
					<a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
					<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
				</div>
			</div>


			<div style="width:99.5%">
		<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
					<a id="add" class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
					<a id="edit" class="nui-button" iconCls="icon-edit" onclick="edit()">编辑</a>
					<a id="query" class="nui-button" iconCls="icon-zoomin" onclick="query()">查看</a> 
					<a id="groupComfirm" class="nui-button" iconCls="icon-edit" onclick="comfirm()">重新认定</a>
				</div>
			</div>

			<div id="grid1" class="nui-datagrid"style="width: 99.5%; height: auto"
			url="com.bos.csm.company.company.getCompanyList.biz.ext"
			dataField="items" allowResize="true" showReloadButton="false"
			onselectionchanged="onSelectionChanged" allowAlternating="true"
			sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10"
			sortMode="client">
			<div property="columns">
				<div type="checkcolumn">选择</div>
				<div field="partyNum" headerAlign="center" allowSort="true">集团客户编号</div>
				<div field="partyNames1" headerAlign="center" allowSort="true" autoEscape="false">集团客户名称</div>
				<div field="groupManageWayCd" headerAlign="center"allowSort="true" dictTypeId="XD_KHCD0204">集团客户管理方式</div>
				<div field="status" headerAlign="center" allowSort="true"dictTypeId="XD_KHCD0231">认定状态</div>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
	nui.parse();
	git.mask();
	var form = new nui.Form("#form1");
	var grid = nui.get("grid1");
	grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
				e.data[i]['partyNames1']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].partyId+ '\');">'+e.data[i]['partyName']+'</a>';
			}
		});

	function search() {
		var data = form.getData(); //获取表单多个控件的数据
		grid.load(data);
		
		//字典过滤，过滤掉202的证件类型
	    var arr = git.getDictDataUnFilter("XD_KHCD0231",'04');
		nui.get("item.status").setData(arr);
		git.unmask();
	}
	search();

	function reset() {
		form.reset();
		search();
	}

	function edit() {
		var row = grid.getSelected();
		if (row) {
			var url = nui.context
					+ "/csm/company/groupCompany_edit.jsp?partyId="+ row.partyId;//修改
			nui.open({
				url : url,
				showMaxButton : true,
				title : "编辑集团客户信息",
				width : 900,
				height : 300,
				onload : function(e) {
					var iframe = this.getIFrameEl();
					var text = iframe.contentWindow.document.body.innerText;
					//alert(text);
				},
				ondestroy : function(action) {
					search();
				}
			});
		} else {
			alert("请选中一条记录");
		}

	}

	function query() {
		var row = grid.getSelected();
		if (row) {
			var url = nui.context
					+ "/csm/company/groupCompany_tree.jsp?partyId="
					+ row.partyId + "&qote=1&wflow=1&partyNum=" + row.partyNum;//查看
			nui.open({
				url : url,
				showMaxButton : true,
				title : "查看客户信息",
				width : 1024,
				height : 768,
				state : "max",
				onload : function(e) {
					var iframe = this.getIFrameEl();
					var text = iframe.contentWindow.document.body.innerText;
					//alert(text);
				},
				ondestroy : function(action) {
					//search();
				}
			});
		} else {
			alert("请选中一条记录");
		}

	}

	function comfirm() {
		var row = grid.getSelected();
		if (row) {
		//校验集团是否在业务流程中
        var json = {"partyId":row.partyId};
   	    msg = exeRule("RCSM_00102","1",json);
   	    if(null != msg && '' != msg){
	   	     nui.alert(msg);
	   	     return;
   	     }
			
			var json = "{groupCompany:{partyId:"+row.partyId+",groupManageWayCd:"+row.groupManageWayCd+"},party:{partyNum:"+row.partyNum+",partyName:"+row.partyName+"}}";
			$.ajax({
	            url: "com.bos.csm.company.company.reComfirmCompany.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	       			git.unmask("form1");
	            	if(text.msg){
	            		alert(text.msg);
	            	} else {
						openSubmitView(text.response);
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
	        });
		} else {
			alert("请选中一条记录");
		}

	}
	//弹出审批意见页面
	function openSubmitView(response){
		var url = nui.context + "/csm/company/groupCompany_tree.jsp?partyId="
					+ response.bizId+"&partyNum="+response.partyNum+"&qote=2&processInstId="+response.processInstId+"&isSrc=2&wflow=2";
		nui.open({
	               url: url,
	               title: "提交审批", 
	               width: 550, 
	               height: 260,
	               state:"max",
	               onload: function () {
	               },
	               ondestroy: function (action) {
	               	  search();
	               }
	           });
	
	}

	function add() {
		nui.open({
			url : nui.context + "/csm/company/groupCompany_add.jsp",
			showMaxButton : true,
			title : "新增集团客户",
			width : 600,
			height : 300,
			onload : function(e) {
				var iframe = this.getIFrameEl();
				var text = iframe.contentWindow.document.body.innerText;
			},
			ondestroy : function(action) {
				search();
			}
		});
	}

	function hostRightChange(v) {
		var row = grid.getSelected();
		if (row) {
			nui.open({
				url : nui.context
						+ "/csm/group/groupCust_host_change.jsp?partyId="
						+ row.partyId,
				title : "集团管户权变更",
				width : 800,
				height : 500,
				allowResize : true,
				showMaxButton : true,
				onload : function() {
					var iframe = this.getIFrameEl();
					var data = row;
				},
				ondestroy : function(action) {
					//search();
				}
			});
		} else {
			alert("请选中一条记录");
		}

	}

	function credit() {
		var row = grid.getSelected();
		if (row) {
			var url = "/crd/crd_group/crd_group_tab.jsp?corpid=" + row.partyId;
			git.go(url);
		} else {
			alert("请选中一条记录");
		}

	}

	function onSelectionChanged() {
		var row = grid.getSelected();
		if (row) {
			if(row.status == '03') {
				nui.get("groupComfirm").show();
			} else {
				nui.get("groupComfirm").hide();
			}
		}
	}
</script>
</body>

</html>