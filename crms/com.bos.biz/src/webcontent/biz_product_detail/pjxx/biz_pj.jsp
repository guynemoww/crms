<%@page pageEncoding="UTF-8"%>
<fieldset>
		<legend>
		   		<span>票据信息</span>
		</legend>
		
		<div>
			<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
				<a class="nui-button" id="pj_add" iconCls="icon-add" onclick="addpj()">增加</a>
				<a class="nui-button" id="pj_edit" iconCls="icon-edit" onclick="editpj(0)">编辑</a>
				<a class="nui-button" id="pj_edit1" iconCls="icon-node" onclick="editpj(1)">查看</a>
				<a class="nui-button" id="pj_remove" iconCls="icon-remove" onclick="removepj()">删除</a>
				<a class="nui-button" id="pj_add_dianpiao" iconCls="icon-add" onclick="dianpiaopj()">电票</a>
				<a class="nui-button" id="pj_add_dianpiao" iconCls="icon-add" onclick="refusepj()">电票拒绝</a>
				<a class="nui-button" id="pj_import" iconCls="icon-add" onclick="importExcel()">Excel批量导入</a>
			</div>
				    
			<div id="gridpj" class="nui-datagrid" style="width:100%;height:auto" 
				url="com.bos.bizProductDetail.bizPjxx.getPjList.biz.ext" dataField="pjxxs"
				allowResize="false" showReloadButton="false"  allowCellEdit="false" 
				sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
				<div property="columns">
					<div type="checkcolumn" >选择</div>
					<div field="cprqc" headerAlign="center" allowSort="true" >出票人全称</div>
					<div field="skrqc" headerAlign="center" allowSort="true">收款人全称</div>
					<div field="pjhm" headerAlign="center" allowSort="true">票据号码</div>
					<div field="currencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>
					<div field="hpje" headerAlign="center" allowSort="true" >汇票金额</div>
					<div field="hpxs" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1123">汇票形式</div>
					<div field="hpcprq" headerAlign="center" allowSort="true">汇票出票日期</div>
					<div field="hpdqrq" headerAlign="center" allowSort="true">汇票到期日期</div>
				</div>
			</div>
		</div>
</fieldset>
<script type="text/javascript">

	var partyId = "<%=request.getAttribute("partyId") %>";
	var ecifPartyNum = "<%=request.getAttribute("ecifPartyNum") %>";
	var amountDetailId = "<%=request.getAttribute("amountDetailId") %>";
	var contractNum = "<%=request.getAttribute("contractNum") %>";
	//Excel批量导入
 	function importExcel(){
	 	nui.open({
		        url: "grt/grtImportExcel/import_Biz_Pjxx.jsp?amountDetailId="+amountDetailId+"&contractNum="+contractNum,
		        title: "Excel批量导入", 
		        width: 800, 
		    	height: 500,
		    	allowResize:true,
		    	showMaxButton: true,
		        ondestroy: function (action) {
		       		  if(action=="ok"){
		             	var json = nui.decode({"amountDetailId":amountDetailId});
						var gridpj = nui.get("gridpj");
						gridpj.load(json);
		           	 } 
		        }
		    });
 	}
	//添加纸票信息
	function addpj(){
		var amountDetailId=nui.get("amountDetail.amountDetailId").getValue();
		var jsonxx = {"amountDetailId":amountDetailId,"hpxs":"02"}; // 不能存在电票
		var msg = exeRule("RBIZ_0068", "1", jsonxx);
		if (null != msg && '' != msg) {
			nui.alert(msg);
			return;
		}
	    nui.open({
	        url: nui.context + "/biz/biz_product_detail/pjxx/biz_pj_add.jsp?amountDetailId="+amountDetailId+"&partyId="+partyId,
	        title: "新增", 
	        width: 800, 
	    	height: 500,
	    	allowResize:true,
	    	showMaxButton: true,
	        ondestroy: function (action) {
	       		 if(action=="ok"){
	             	var json = nui.decode({"amountDetailId":amountDetailId});
					var gridpj = nui.get("gridpj");
					gridpj.load(json);
	           	 }
	        }
	    });
	}
	//查询电票信息
	function dianpiaopj(){
		var amountDetailId=nui.get("amountDetail.amountDetailId").getValue();
		var json = {"amountDetailId":amountDetailId,"hpxs":"01"}; // 不能存在纸票
		var msg = exeRule("RBIZ_0068", "1", json);
		if (null != msg && '' != msg) {
			nui.alert(msg);
			return;
		}
	    nui.open({
	        url: nui.context + "/biz/biz_product_detail/pjxx/biz_pj_add_dp.jsp?amountDetailId="+amountDetailId+"&partyId="+partyId+"&ecifPartyNum="+ecifPartyNum,
	        title: "新增", 
	        width: 800, 
	    	height: 500,
	    	allowResize:true,
	    	showMaxButton: true,
	        ondestroy: function (action) {
	       		 if(action=="ok"){
	             	var json = nui.decode({"amountDetailId":amountDetailId});
					var gridpj = nui.get("gridpj");
					gridpj.load(json);
	           	 }
	        }
	    });
	}
	// 电票拒绝
	function refusepj(){
		var amountDetailId=nui.get("amountDetail.amountDetailId").getValue();
	    nui.open({
	        url: nui.context + "/biz/biz_product_detail/pjxx/biz_pj_refuse_dp.jsp?amountDetailId="+amountDetailId+"&partyId="+partyId+"&ecifPartyNum="+ecifPartyNum,
	        title: "新增", 
	        width: 800, 
	    	height: 500,
	    	allowResize:true,
	    	showMaxButton: true,
	        ondestroy: function (action) {
	       		 if(action=="ok"){
	             	var json = nui.decode({"amountDetailId":amountDetailId});
					var gridpj = nui.get("gridpj");
					gridpj.load(json);
	           	 }
	        }
	    });
	}
	
	//编辑
	function editpj(v) {
		var amountDetailId=nui.get("amountDetail.amountDetailId").getValue();
		var grid = nui.get("gridpj");
	    var row = grid.getSelected();
	    if (row) {
	    	if(v=="0" && row.hpxs=="02"){
	    		alert("电票信息不能修改！");
	    		return false;
	    	}
	        nui.open({
	            url: nui.context+"/biz/biz_product_detail/pjxx/biz_pj_edit.jsp?applyDetailId="+row.applyDetailId+"&view="+v,
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
	                    var json = nui.decode({"amountDetailId":amountDetailId});
						var gridpj = nui.get("gridpj");
						gridpj.load(json);
	           	 	}
	            }
	        });
	    }else{
	    	alert("请选择票据信息！");
	    }
	}
	//删除关联关系
	function removepj() {
		var grid = nui.get("gridpj");
	    var rows = grid.getSelected();
		if (null == rows) {
			nui.alert("请选择票据信息！");
			return false;
		}
		if(bizType != '01'){
	   nui.alert("该票据在流程或者已经生效不能删除");		
	   }else{
		var json = nui.encode({"tbBizPjxxApply":rows});
		var amountDetailId=nui.get("amountDetail.amountDetailId").getValue();
		nui.confirm("确定删除吗？","确认",function(action){
	    	if(action!="ok") return;
	        $.ajax({
	            url: "com.bos.bizProductDetail.bizPjxx.delPjxx.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	if (text.msg) {
	            		nui.alert(text.msg);
	            		return;
	            	}
	                var json = nui.decode({"amountDetailId":amountDetailId});
					var gridpj = nui.get("gridpj");
					gridpj.load(json);
	            },
	            error: function () {
	            	nui.alert("操作失败！");
	            }
	        });
	    });
      }
	}
</script>