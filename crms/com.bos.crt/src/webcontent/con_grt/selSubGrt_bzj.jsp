<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-07-06 10:23:37
  - Description:
-->
<head>
<title>关联保证金列表</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form" style="width:99.5%;height:auto;overflow:hidden; text-align:center;"  class="nui-form">
	<div class="nui-toolbar" style="border-bottom:0;text-align:left;">
		<a class="nui-button" iconCls="icon-add" onclick="insert()">确认</a>
		<%--<a class="nui-button" iconCls="icon-add" onclick="add()">增加</a>--%>
		<!-- <a class="nui-button" iconCls="icon-edit" onclick="view()">查看</a> -->
	</div>
	<div id="grid" class="nui-datagrid" style="width:100%;height:auto" 
		url="com.bos.grt.conGrt.selSubGrtBzj.biz.ext"allowAlternating="true"
		dataField="grts" allowResize="true" showReloadButton="false"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div type="indexcolumn">序号</div>
			<div field="MARGIN_SORT" allowSort="true"  headerAlign="center"  dictTypeId="XD_YWDB0134">保证金类型</div>
			<div field="OPEN_BANK" headerAlign="center" allowSort="true" dictTypeId="org">开户行</div>
			<div field="ACCT_NAME" headerAlign="center" allowSort="true" >开户人</div>
			<div field="MARGIN_ACCOUNT" headerAlign="center" allowSort="true" >保证金账号</div>
			<div field="CURRENCY_CD" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>
			<div field="ACC_BALANCE" headerAlign="center" allowSort="true"dataType="currency" >保证金金额</div>
			<div field="END_DATE" headerAlign="center" allowSort="true" >到期日期</div>
<!-- 			<div field="IS_JIXI" headerAlign="center" allowSort="true" dictTypeId="XD_0002">是否计息</div> -->
			<div field="MARGIN_RATE" headerAlign="center" allowSort="true" >执行利率(%)</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("grid");
	var subcontractId="<%=request.getParameter("subcontractId")%>";
	var applyId="<%=request.getParameter("applyId")%>";
	var subcontractType="<%=request.getParameter("subcontractType")%>";
	var xgbz="<%=request.getParameter("xgbz")%>";
	var ljurl="";
	
	search();
	//初始化查询
	function search(){
	    grid.load({"subcontractId":subcontractId,"applyId":applyId,"subcontractType":subcontractType});
	}
	//将抵质押物引入担保合同
	function insert(){
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一笔保证金信息");
			return false;
		}
		 

		var json = nui.encode({"tbConSubGrtRel":{"subcontractId":subcontractId,"suretyId":row.SURETY_ID}});
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
		 /* nui.open({
            url: nui.context + "/crt/con_grt/saveSubGrtRel.jsp",
            title: "押品本次担保金额", 
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
				            	alert("关联成功");
				            	CloseWindow("ok");
					        },
					        error: function (jqXHR, textStatus, errorThrown) {
					            alert(jqXHR.responseText);
					        }
				        });
                    }
                }
            }
        }); */
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
	            state:"max",
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