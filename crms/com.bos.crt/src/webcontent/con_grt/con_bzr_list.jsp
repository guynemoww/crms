<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-07-05 16:16:18
  - Description:
-->
<head>
<title>保证人合同</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form" style="width:99.5%;height:auto;overflow:hidden; text-align:center;"  class="nui-form">
	<div class="nui-toolbar" style="border-bottom:0;text-align:left;">
		<a class="nui-button" iconCls="icon-add" onclick="add()" id="addBtn">增加</a>
		<a class="nui-button" iconCls="icon-save" onclick="insert()" id="importBtn">引入</a>
		<a class="nui-button" iconCls="icon-zoomin" onclick="edit(1)">查看</a>
		<a class="nui-button" iconCls="icon-edit"  onclick="edit(0)" id="editBtn">编辑</a>
		<a class="nui-button" iconCls="icon-remove" onclick="remove()" id="deleteBtn">删除</a>
	</div>
	<div id="grid" class="nui-datagrid" style="width:100%;height:auto" 
		url="com.bos.grt.conGrt.getConGrtBZRList.biz.ext"allowAlternating="true"
		dataField="conGrts" allowResize="true" showReloadButton="false"
		sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div field="SUBCONTRACT_NUM" headerAlign="center" allowSort="true" >保证合同编号</div>
			<div field="PARTY_NAME" headerAlign="center" allowSort="true" >保证人名称</div>
			<div field="IF_TOP_SUBCON" allowSort="true"  headerAlign="center"  dictTypeId="XD_0002">是否最高额</div>
			<div field="BZ" allowSort="true"  headerAlign="center"  dictTypeId="CD000001">币种</div>
			<div field="SUBCONTRACT_AMT"  headerAlign="center" dataType="currency" align="right">担保合同金额</div>
			<div field="SURETY_AMT"  headerAlign="center" dataType="currency" align="right">本次担保金额</div>
			<div field="OPERATION_TYPE" allowSort="true"  headerAlign="center"  dictTypeId="XD_SXCD1069">操作类型标识</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	nui.parse();
	var grid = nui.get("grid");
	var contractId="<%=request.getParameter("contractId")%>";
	var subcontractType="<%=request.getParameter("subcontractTypeCd")%>";
	var applyId="<%=request.getParameter("applyId")%>";
	var contractType="<%=request.getParameter("contractType")%>";
	var partyId="<%=request.getParameter("partyId")%>";
	var proFlag="<%=request.getParameter("proFlag")%>";
	var xgbz="<%=request.getParameter("xgbz")%>";
	if("1"==xgbz){
		nui.get("addBtn").hide();
		nui.get("importBtn").hide();
 	}
	if(proFlag!='1'){
		nui.get("addBtn").hide();
		nui.get("importBtn").hide();
		nui.get("editBtn").hide();
		nui.get("deleteBtn").hide();
	}
	
	search();
	//初始化查询
	function search(){
	    grid.load({"contractId":contractId,"subcontractType":subcontractType});
	}
	//添加担保合同
	function add(){
<%--		//添加担保合同信息
        var btnEdit = this;
        nui.open({
            url: nui.context + "/crt/con_grt/selectPartyBzr.jsp?applyId="+applyId+"&subcontractType="+subcontractType,
            title: "选择",
            width: 600,
            height: 300,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                        //btnEdit.setValue(data.PARTYID);
                        //btnEdit.setText(data.PARTYNAME);
                        var partyId=data.PARTYID;--%>
                        var conPartyId=partyId;
                        var json = nui.encode({"subContract":{"subcontractType":subcontractType,"conPartyId":conPartyId<%--,"partyId":partyId--%>}});
						$.ajax({
				            url: "com.bos.grt.conGrt.saveGrtCon.biz.ext",
				            type: 'POST',
				            data: json,
				            cache: false,
				            contentType:'text/json',
				            cache: false,
				            success: function (mydata) {
				            	var o = nui.decode(mydata);
								//添加担保合同与业务合同关联关系
								var json1 = nui.encode({"conSubcontractRel":{"contractId":contractId,"subcontractId":o.subContract.subcontractId,"ifEffective":"1"}});
								$.ajax({
						            url: "com.bos.grt.conGrt.insertGrtConRel.biz.ext",
						            type: 'POST',
						            data: json1,
						            cache: false,
						            contentType:'text/json',
						            cache: false,
						            success: function (mydata) {
						            	var o = nui.decode(mydata);
						            	//跳往担保合同信息页面（已生成担保合同ID）
					            		nui.open({
								            url: nui.context + "/crt/con_grt/con_bzr_tab.jsp?subcontractId="+o.conSubcontractRel.subcontractId+"&applyId="+applyId+"&contractType="+contractType+"&conSubconId="+o.conSubcontractRel.conSubconId+"&contractId="+contractId,
								            showMaxButton: true,
								            title: "保证合同明细信息",
								            width: 800,
								            height: 500,
								            state:"max",
								            ondestroy: function(e) {
								            	//CloseWindow("ok");
								            	search();
								            }
								        });
									}
						        })
							}
				        });
         <%--           }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	}
        });   --%> 
	}
	function insert(){
        nui.open({
            url: nui.context + "/crt/con_grt/selectMaxLoancon.jsp?guarantyType="+subcontractType+"&contractId="+contractId+"&partyId="+partyId+"&applyId="+applyId+"&contractType="+contractType,
            title: "引入最高额担保合同", 
            width: 800,
    		height: 500,
            allowResize:true,
    		showMaxButton: true,
            onload: function () {
                var iframe = this.getIFrameEl();
                //var data = row;
                search();
            },
            ondestroy: function (action) {
            	search();
            }
        });
    }
    function edit(v){
        var row = grid.getSelected();
       	if(!row){
        	return alert("请选择一条记录");
        }
        if(xgbz=="1" && v=='0' ){
        if( row.IF_TOP_SUBCON!="1"){
        	alert("只能对最高额合同进行编辑！");
         	return;
        }
	        nui.open({
	            url: nui.context + "/crt/con_grt/saveConSubRel.jsp?subcontractId="+row.SUBCONTRACT_ID+"&conSubconId="+row.CON_SUBCON_ID+"&xgbz="+xgbz+"&contractId="+contractId,
	            title: "担保合同信息", 
	            width: 800,
	            height: 500,
 	            allowResize:true,
	    		showMaxButton: true,
	            onload: function () {
	                var iframe = this.getIFrameEl();
	                //var data = row;
	                search();
	            },
	            ondestroy: function (action) {
	            	search();
	            }
	        });        
        }else{
        
        if("01" == row.OPERATION_TYPE&&v=='0'){
        	alert("引入合同不允许编辑");
        	return;
        }
	        nui.open({
	            url: nui.context + "/crt/con_grt/con_bzr_tab.jsp?subcontractId="+row.SUBCONTRACT_ID+"&applyId="+applyId+"&contractType="+contractType+"&view="+v+"&applyId="+applyId+"&conSubconId="+row.CON_SUBCON_ID+"&applyId="+applyId+"&contractId="+contractId,
	            title: "担保合同信息", 
	            width: 800,
	    		height: 500,
	    		state:"max",
	            allowResize:true,
	    		showMaxButton: true,
	            onload: function () {
	                var iframe = this.getIFrameEl();
	                search();
	            },
	            ondestroy: function (action) {
	            	search();
	            }
	        });        
        }
    }
  
    function remove(){
        var row = grid.getSelected();
        if (row) {    
	    	 nui.confirm("确定删除吗？","确认",function(action){
	        	if(action!="ok") return;
	            	//var json=nui.encode({"item":{"relationId":row.RELATION_ID,"_entity":"com.bos.dataset.biz.TbBizGrtRel"}});
		            var relationId=row.RELATION_ID;
		        	var conSubconId=row.CON_SUBCON_ID;
		        	var subcontractId=row.SUBCONTRACT_ID;
					var json = nui.encode({"bizRel":{"relationId":relationId},"conSubRel":{"conSubconId":conSubconId},"conSub":{"subcontractId":subcontractId}});
					$.ajax({
			            url: "com.bos.grt.conGrt.delConBZRRel.biz.ext",
			            type: 'POST',
			            data: json,
			            cache: false,
			            contentType:'text/json',
			            cache: false,
			            success: function (mydata) {
			            	var o = nui.decode(mydata);
							search();
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