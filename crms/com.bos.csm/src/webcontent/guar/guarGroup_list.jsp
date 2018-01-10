<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2015-5-11 10:58:51
  - Description:
-->
<head>
<title>联保客户维护</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
		<div id="tabs1" class="nui-tabs" activeIndex="0"
			style="width: 100%; height: auto;">
			<div title="联保客户">
				<div id="form1" class="nui-form"
					style="width: 99.5%; height: auto; overflow: hidden;">
					<div class="nui-dynpanel" columns="6">
						<label>联保小组编号：</label> 
						<input id="item.partyNum"
							class="nui-textbox nui-form-input" name="item.partyNum" />
						<label>成员客户名称：</label> 
						<input id="item.memberName"
							class="nui-textbox nui-form-input" name="item.memberName" />
						<label>成员证件类型：</label>
						<input id="item.certType" name="item.certType" class="nui-dictcombobox nui-form-input"  dictTypeId="CDKH0002"  allowInput="false" />
						<label>成员证件号码：</label>			
						<input id="item.certNum"  class="nui-textbox nui-form-input" name="item.certNum" onvalidation=""  />
						<label>成员中征码：</label>			
						<input id="item.middleCode"  class="nui-textbox nui-form-input" name="item.middleCode" vtype="int;minLength:16;maxLength:16" onvalidation=""  />
						<label>认定状态:</label>
						<input class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD0231" name="item.jointGuaranteeStatus" />
					</div>
					<div class="nui-toolbar"
						style="text-align: right; padding-right: 20px; border: 0">
						<a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
						<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
					</div>
				</div>


				<div style="width: 99.5%; margin-top: 10px;">
					<div class="nui-toolbar" style="border-bottom: 0; text-align: left">
						<a id="add" class="nui-button" iconCls="icon-add" onclick="add()">新增</a>
						<a id="query" class="nui-button" iconCls="icon-zoomin" onclick="query()">查看</a> 
						<!-- 
						<a id="editCust" class="nui-button" iconCls="icon-edit" onclick="edit()">编辑</a>
						<a id="groupComfirm" class="nui-button" iconCls="icon-edit" onclick="comfirm()">联保小组认定</a>
						 -->
						<a id="groupDismiss" class="nui-button" iconCls="icon-remove" onclick="remove()">解散</a>
					</div>
				</div>

				<div id="grid1" class="nui-datagrid"style="width: 99.5%; height: auto"allowAlternating="true"
					url="com.bos.csm.guar.guarGroup.getGuarGroupList.biz.ext"
					dataField="items" allowResize="true" showReloadButton="false"
					onselectionchanged="onSelectionChanged"
					sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10"
					sortMode="client">
					<div property="columns">
						<div type="checkcolumn">选择</div>
						<div field="partyNum" headerAlign="center" allowSort="true">联保小组编号</div>
						<div field="partyName" headerAlign="center" allowSort="true">联保小组名称</div>
						<div field="jointGuaranteeType" headerAlign="center" allowSort="true"
							autoEscape="false" dictTypeId="XD_KHCD4001">联保小组类型</div>
						<div field="memberNum" headerAlign="center"
							allowSort="true" >成员人数</div>
						<div field="jointGuaranteeStatus" headerAlign="center" allowSort="true"
							dictTypeId="XD_KHCD0231">认定状态</div>
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
	
	function clickCust(partyId, partyNum) {
		var ps = partyId.split(",");
		partyId = ps[0];
		partyNum = ps[1];
		var url = nui.context + "/csm/guar/guarGroup_tree.jsp?partyId="
				+ partyId + "&qote=1&partyNum=" + partyNum;
		//查看
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
	}

	function search() {
		var data = form.getData(); //获取表单多个控件的数据
		grid.load(data);
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
					+ "/csm/guar/guarGroup_tree.jsp?partyId="
					+ row.partyId + "&qote=2&partyNum=" + row.partyNum;
			//修改
			nui.open({
				url : url,
				showMaxButton : true,
				title : "编辑客户信息",
				width : 1024,
				height : 768,
				state : "max",
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
					+ "/csm/guar/guarGroup_tree.jsp?partyId="
					+ row.partyId + "&qote=1&partyNum=" + row.partyNum;
			//查看
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
			var json = "{guarGroup:{partyId:"+row.partyId+",manageWay:"+row.manageWay+"},party:{partyNum:"+row.partyNum+",partyName:"+row.partyNum+"}}";
			$.ajax({
	            url: "com.bos.csm.guar.guarGroup.comfirmGuarGroup.biz.ext",
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
		var url = nui.context + "/csm/guar/guarGroup_tree.jsp?bizId="
					+ response.bizId + "&qote=2&processInstId="+response.processInstId+"&isSrc=2";
		nui.open({
	               url: url,
	               title: "提交审批", 
	               width: 550, 
	               height: 260,
	               state:"max",
	               onload: function () {
	               },
	               ondestroy: function (action) {
	               }
	           });
	}

	function add() {
		nui.open({
			url : nui.context + "/csm/guar/guarGroup_add.jsp",
			showMaxButton : true,
			title : "新增联保客户",
			width : 400,
			height : 200,
			onload : function(e) {
				var iframe = this.getIFrameEl();
				var text = iframe.contentWindow.document.body.innerText;
				//alert(text);
			},
			ondestroy : function(action) {
				search();
			}
		});
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
			if(row.jointGuaranteeStatus=='01'){
				nui.get("groupDismiss").hide();
			}else if(row.jointGuaranteeStatus=='02'){
				nui.get("groupDismiss").hide();
			}else if(row.jointGuaranteeStatus=='04'){
				nui.get("groupDismiss").hide();
			}else{
				nui.get("groupDismiss").show();
			}
		}

	}
	
	function remove() {
        var row = grid.getSelected();
        //校验是否结清
 		var json = {"partyId":row.partyId};
   	    msg = exeRule("PUB_0016","1",json);
   	    if(null != msg && '' != msg){
	   	     nui.alert(msg);
	   	     return;
   	    }
        
        if (row) {
        	nui.confirm("确定解散吗？","确认",function(action){
            	if(action!="ok") return;
            	git.mask();
            	var json=nui.encode({"guarGroup":{"partyId":row.partyId,"jointGuaranteeStatus":"04"}});
                $.ajax({
                     url: "com.bos.csm.guar.guarGroup.saveGuarGroupEcif.biz.ext",//saveGuarGroupEcif
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	git.unmask();
                    	if(text.map.msg!="AAAAAAA"){
                    	alert("调用ECIF接口失败：" +text.map.msgg);
                    	}else{
                    	if (text.msg) {
                    		alert(text.msg);
                    		return;
                    	}else{
                    		if(text.flag){
		            		var node = text.node;
		            		}
		            		
                    	}
                    	}
                        git.mask();
                        search();
                 
                    },
                    error: function () {
                    	git.unmask();
                    	nui.alert("操作失败！");
                    }
                });
            }); 
        } else {
            nui.alert("请选中一条记录");
        }
    }
	
</script>
</body>

</html>