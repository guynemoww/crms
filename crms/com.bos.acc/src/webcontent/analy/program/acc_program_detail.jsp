<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-11-22
  - Description:TB_CSM_BOND_INFO, com.bos.dataset.csm.TbCsmBondInfo
-->
<head>
<title>财报选择</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>


	<div id="tabs1" class="nui-tabs" activeIndex="0"
		style="width: 100%; height: 100%;">
		<div title="财报选择">
			<center>
				<div id="form1" class="nui-form"
					style="width: 99.5%; height: auto; overflow: hidden;">
					<input type="hidden" name="accAnalysisDetail._entity"
						value="com.bos.dataset.acc.TbAccAnalysisDetail" class="nui-hidden" />
					<input type="hidden" name="accAnalysisDetail.finanysisProgramId"
						value="<%=request.getParameter("finanysisProgramId")%>"
						class="nui-hidden" />
				</div>
				<div class="nui-toolbar" style="border-bottom: 0; text-align: left">
					<a id="add" class="nui-button" iconCls="icon-edit" onclick="view()">查看财报</a>
					<a id="query" class="nui-button" iconCls="icon-edit"
						onclick="edit(1)">执行粉饰预警</a>
				</div>
				<div id="grid1" class="nui-datagrid"
					style="width: 99.5%; height: auto;" sortMode="client"
					url="com.bos.acc.analy.getAccAnalysisDetailList.biz.ext"
					dataField="accAnalysisDetails" allowAlternating="true"
					multiSelect="false" showEmptyText="true" showPager="true"
					emptyText="没有查到数据" showReloadButton="false" showColumnsMenu="true"
					onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
					onselectionchanged="onSelectionChanged" sizeList="[10,20,50,100]"
					pageSize="10">
					<div property="columns">
						<div type="checkcolumn">选择</div>
						<div field="financeTypeCd" headerAlign="center" allowSort="true"
							dictTypeId="XD_ACCCD0001">财务报表类型</div>
						<div field="financeDeadline" headerAlign="center" allowSort="true"
							dateFormat="yyyy-MM-dd">财报日期</div>
						<div field="calierCd" headerAlign="center" allowSort="true"
							dictTypeId="CDKH0071">报表口径</div>
						<div field="customerTypeCd" headerAlign="center" allowSort="true"
							dictTypeId="XD_ACCCD0002">财务报表类别</div>
						<div field="auditedInd" headerAlign="center" allowSort="true"
							dictTypeId="YesOrNo">是否经过审计</div>

					</div>

				</div>
				<div style="width: 99.5%">
					<div class="nui-toolbar"
						style="border-top: 1px solid #8ba0bc; border-bottom: none; text-align: right; margin-top: 7px">
						<a id="addCust" style="margin-left: 5px" class="nui-button"
							iconCls="icon-add" onclick="add()">执行分析</a>
					</div>
				</div>
			</center>
		</div>
	</div>

	<script type="text/javascript">
    nui.parse();
    var  qote="<%=request.getParameter("qote")%>";
    if(qote==1){
		nui.get("add").hide();
	   nui.get("edit").hide();
	   nui.get("remove").hide();
	}
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    }
    search();
    
    function reset(){
    	var form = new nui.Form("#form1"); 
		form.reset();
	}
	
	function view(){
	   var row = grid.getSelected();
        if (row) {
            nui.open({
                url: "acc/acccustomerfinance/acccustomerfinance_edit.jsp?financeId="+row.financeId+"&view="+'1'+"&reportType="+row.customerTypeCd
                +"&financeTypeCd="+row.financeTypeCd,
                showMaxButton: true,
                title: "查看财报", 
                width: 1024,
	            height: 540,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                    //iframe.contentWindow.SetData(data);
                }
            });
        }else {
            alert("请选中一条记录");
        }
	}
	
    //生成财务分析记录
	function add(){
			var finanysisProgramId="<%=request.getParameter("finanysisProgramId")%>";
			var sendData = nui.encode({"finanysisProgramId":finanysisProgramId});
			git.mask();
			alert("财务分析努力加载中，页面跳转后请点击确定！"); 
			$.ajax({
				url:"com.bos.acc.analy.doAccAnalysis.biz.ext",
				type:'POST',
				data:sendData,
				cache: false,
				contentType:'text/json',
				success:function(text){
					if(text.msg){
            		nui.alert(text.msg);
            	} else {
            		var url = nui.context +"/acc/analy/analysis/acc_analy_tree.jsp?finanysisProgramId="+finanysisProgramId;
            		git.go(url);
            	}
				}
			});
	     }
	//执行财报粉饰预警
    function edit(v) {
        var row = grid.getSelected();
        if (row) {
        var sendData = nui.encode({"finanysisProgramId":row.finanysisProgramId,"finanysisDetailId":row.finanysisDetailId,
        "financeId":row.financeId});
			$.ajax({
				url:"com.bos.acc.analy.addAccAlert.biz.ext",
				type:'POST',
				data:sendData,
				cache: false,
				contentType:'text/json',
				success:function(text){
				if(text.msg){
            		nui.alert(text.msg);
            	} else {
            		
            	 nui.open({
                url: "acc/analy/analysis/acc_alert_result.jsp?finanysisProgramId="+row.finanysisProgramId
            		  +"&finanysisDetailId="+row.finanysisDetailId+"&modelId=001",
                showMaxButton: true,
                title: "编辑", 
                width: 600,
	            height: 540
            });
            	}
				}
			});
        } else {
            alert("请选中一条记录");
        }
        
    }

	</script>
</body>
</html>
