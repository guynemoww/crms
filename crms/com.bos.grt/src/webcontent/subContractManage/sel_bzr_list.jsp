<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
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
	 <a id="editCust" class="nui-button" iconCls="icon-zoomin"
						onclick="view()">查看</a>
	</div>
	<div id="grid" class="nui-datagrid" style="width:100%;height:auto" 
		url="com.bos.grt.subContractManage.subContract.findUnBzrList.biz.ext"
		dataField="grts" allowResize="true" showReloadButton="false" allowAlternating="true" 
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div field="GUARANTEE_TYPE" allowSort="true"  headerAlign="center"  dictTypeId="XD_YWDB0131">保证类型</div>
			<div field="PARTY_NAME" headerAlign="center" allowSort="true" >保证人名称</div>
			<div field="CERT_TYPE" allowSort="true"  headerAlign="center"  dictTypeId="CDKH0002">证件类型</div>
			<div field="CERT_NUM" allowSort="true"  headerAlign="center"  >证件号码</div>
			<div field="SURETY_AMT" headerAlign="center" allowSort="true" dataType="currency" >本次申请担保金额</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("grid");
	var subcontractId="<%=request.getParameter("subcontractId")%>";
	var subcontractType="<%=request.getParameter("subcontractType")%>";
		var array="<%=request.getParameter("array")%>";
	
	search();
	//初始化查询
	function search(){
	    grid.load({"subcontractId":subcontractId,"subcontractType":subcontractType});
	}
	//将抵质押物引入担保合同
	function insert(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一条记录");
			return false;
		}
		
	if(array!=""){
			var arr=array.split(",");
			for(var i=0;i<arr.length;i++){
				if(arr[i]==row.PARTY_ID){
					alert("同一个保证人不能重复关联");
					return;
				}
			}
		}
		
		nui.open({
	        url: nui.context + "/grt/subContractManage/grt_surety_amt.jsp?subcontractId="+subcontractId+"&suretyId="+row.SURETY_ID,
			            title: "保证人本次担保金额", 
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
			        				var json = nui.encode({"tbConSubTmp":{"subcontractId":subcontractId,"conSuretyId":row.SURETY_ID,"suretyAmt":data.suretyAmt}});
									//将担保物关联到抵质押合同
									$.ajax({
					        			url: "com.bos.grt.subContractManage.subContract.insertTbConSubTmp.biz.ext",
								        type: 'POST',
								        data: json,
								        contentType:'text/json',
								        cache: false,
								        success: function (data) {
								        	if(data.msg){
							            		alert(data.msg);
							            	}else{
							            		CloseWindow("ok");
							            	}
								        },
								        error: function (jqXHR, textStatus, errorThrown) {
								            alert(jqXHR.responseText);
								        }
							        });
			                    }
			                }
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
	            title:"押品信息",
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