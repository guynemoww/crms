<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2015-07-06 10:23:37
  - Description:
-->
<head>
<title>保证人列表</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form" style="width:99.5%;height:auto;overflow:hidden; text-align:center;"  class="nui-form">
	<div class="nui-toolbar" style="border-bottom:0;text-align:left;">
		<a class="nui-button" iconCls="icon-add" onclick="insert()">确认</a>
		<%--<a class="nui-button" iconCls="icon-add" onclick="add()">增加</a>--%>
		<a class="nui-button" iconCls="icon-zoomin" onclick="view()">查看</a>
	</div>
	<div id="grid" class="nui-datagrid" style="width:100%;height:auto" 
		url="com.bos.grt.conGrt.selSubGrtBzr.biz.ext"allowAlternating="true"
		dataField="grts" allowResize="true" showReloadButton="false"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div type="indexcolumn" width="50px;">序号</div>
			<div field="PARTY_NAME" headerAlign="center" allowSort="true" >保证人名称</div>
			<div field="CERT_TYPE" allowSort="true"  headerAlign="center"  dictTypeId="CDKH0002">证件类型</div>
			<div field="CERT_NUM" allowSort="true"  headerAlign="center"  >证件号码</div>
			<div field="SURETY_AMT" headerAlign="center" allowSort="true"  dataType="currency">申请担保金额（元）</div>
			<div field="USED_AMT" headerAlign="center" allowSort="true"  dataType="currency">已担保金额（元）</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("grid");
	var subcontractId="<%=request.getParameter("subcontractId")%>";
	var applyId="<%=request.getParameter("applyId")%>";
	var subcontractType="<%=request.getParameter("subcontractType")%>";
	search();
	//初始化查询
	function search(){
	    grid.load({"subcontractId":subcontractId,"applyId":applyId,"subcontractType":subcontractType});
	}
	//将抵质押物引入担保合同
	function insert(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔押品信息");
			return false;
		}
		var json=nui.encode({"subcontractId":subcontractId});
		$.ajax({
        	url: "com.bos.grt.grtManage.mortgageSort.getSubCon.biz.ext",
        	type: 'POST',
        	data: json,
        	cache: false,
        	contentType:'text/json',
        	success: function (text) {
        		var o=nui.decode(text);
				if("1" == o.conSub.ifTopSubcon){
					nui.open({
			            url: nui.context + "/crt/con_grt/saveSubGrtRel_bzr.jsp?subcontractId="+subcontractId+"&suretyId="+row.SURETY_ID,
			            title: "保证人担保确认金额", 
			            width: 400,
			    		height: 200,
			            allowResize:true,
			    		showMaxButton: true,
			            ondestroy: function (action) {
			                if (action == "ok") {
			                    var iframe = this.getIFrameEl();
			                    var data = iframe.contentWindow.GetData();
			                    data = nui.clone(data);
			                    if (data) {
			        				var json = nui.encode({"tbConSubGrtRel":{"subcontractId":subcontractId,"suretyId":row.SURETY_ID,"suretyAmt":data.suretyAmt}});
									//将担保物关联到抵质押合同
									$.ajax({
								        url: "com.bos.grt.conGrt.insertSubGrtRel.biz.ext",
								        type: 'POST',
								        data: json,
								        contentType:'text/json',
								        cache: false,
								        success: function (data) {
							            	alert(data.msg);
							            	CloseWindow("ok");
								        },
								        error: function (jqXHR, textStatus, errorThrown) {
								            alert(jqXHR.responseText);
								        }
							        });
			                    }
			                }
			            }
			        });
				}else{
    				var json = nui.encode({"tbConSubGrtRel":{"subcontractId":subcontractId,"suretyId":row.SURETY_ID}});
					//将担保物关联到抵质押合同
					$.ajax({
				        url: "com.bos.grt.conGrt.insertSubGrtRel.biz.ext",
				        type: 'POST',
				        data: json,
				        contentType:'text/json',
				        cache: false,
				        success: function (data) {
		            		alert("保存成功");
							CloseWindow("ok");
				        },
				        error: function (jqXHR, textStatus, errorThrown) {
				            alert(jqXHR.responseText);
				        }
			        });
				}
        	},
        	error: function (jqXHR, textStatus, errorThrown) {
            	nui.alert(jqXHR.responseText);
        	}
		});
	}
	function view(){
        var row = grid.getSelected();
		var v ="1";
        if (row) {		   
        	var relationId=row.RELATION_ID;
        	nui.open({
        		url: nui.context+"/grt/guaranMainManager/guarantee_apply_list_guaranteer_add.jsp?suretyId="+row.SURETY_ID+"&view="+v,
	            showMaxButton: true,
	            title:"保证人信息",
	            width: 800,
	            height: 500,
	            ondestroy: function(e) {
	            	search();
	            }
	        });
        } else {
            alert("请选中一条记录");
        } 
	}
</script>
</body>
</html>