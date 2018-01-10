<%@page pageEncoding="UTF-8"%>
<fieldset>
		<legend>
		   		<span>贴现信息</span>
		</legend>
		<div>
			<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
				<a class="nui-button" id="tx_add" iconCls="icon-add" onclick="addtx()">增加</a>
				<a class="nui-button" id="tx_edit" iconCls="icon-edit" onclick="edittx(0)">编辑</a>
				<a class="nui-button" id="tx_edit1" iconCls="icon-node" onclick="edittx(1)">查看</a>
				<a class="nui-button" id="tx_remove" iconCls="icon-remove" onclick="removetx()">删除</a>
				<a class="nui-button" id="tx_add_dianpiao" iconCls="icon-add" onclick="dianpiaotx()">电票</a>
				<a class="nui-button" id="pj_import" iconCls="icon-add" onclick="importExcel2()">Excel批量导入</a>
			</div>
			
			<div id="gridtx" class="nui-datagrid" style="width:100%;height:auto" 
				url="com.bos.bizProductDetail.bizPjxx.GetTxList.biz.ext" dataField="txxxs"
				allowResize="false" showReloadButton="false"  allowCellEdit="false" 
				sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
				<div property="columns">
					<div type="checkcolumn" >选择</div>
					<div field="takeoutacname" headerAlign="center" allowSort="true">出票人名称</div>
					<div field="benename" headerAlign="center" allowSort="true">收款人名称</div>
					<div field="billtype" headerAlign="center" allowSort="true"  dictTypeId="XD_SXCD1123">汇票类型</div>
					<div field="billmodel" headerAlign="center" allowSort="true" dictTypeId="XD_SXYW0203">汇票模式</div>
					<div field="billno" headerAlign="center" allowSort="true">汇票号码</div>
					<div field="currsign" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>
					<div field="billamt" headerAlign="center" allowSort="true" >票面金额</div>
					<div field="billbegindate" headerAlign="center" allowSort="true">出票日期</div>
					<div field="billenddate" headerAlign="center" allowSort="true">到期日期</div>
					<div field="forbidflag" headerAlign="center" allowSort="true" dictTypeId="XD_JZBSBJ01">禁止背书标记</div>
				</div>
			</div>
		</div>
</fieldset>
<script type="text/javascript">	
	var partyId = "<%=request.getAttribute("partyId") %>";
	var ecifPartyNum = "<%=request.getAttribute("ecifPartyNum") %>";
	
	var amountDetailId = "<%=request.getAttribute("amountDetailId") %>";
	
	//Excel批量导入
 	function importExcel2(){
	 	nui.open({
		        url: nui.context +"grt/grtImportExcel/import_BizTxxx.jsp?amountDetailId="+amountDetailId,
		        title: "Excel批量导入", 
		        width: 800, 
		    	height: 500,
		    	allowResize:true,
		    	showMaxButton: true,
		        ondestroy: function (action) {
		       		  if(action=="ok"){
		             	var json = nui.decode({"amountDetailId":amountDetailId});
						var gridtx = nui.get("gridtx");
						gridtx.load(json);
		           	 } 
		        }
		    });
 	}
	
	
	//添加贴现纸票
	function addtx(){
		var amountDetailId=nui.get("amountDetail.amountDetailId").getValue();
		var productType =nui.get("amountDetail.productType").getValue();
		var jsonxx = {"amountDetailId":amountDetailId,"hpxs":"02"}; // 不能存在电票
		var msg = exeRule("RBIZ_0069", "1", jsonxx);
		if (null != msg && '' != msg) {
			nui.alert(msg);
			return;
		}
	    nui.open({
	        url: nui.context + "/biz/biz_product_detail/pjxx/biz_tx_add.jsp?amountDetailId="+amountDetailId+"&productType="+productType+"&partyId="+partyId,
	        title: "新增", 
	        width: 800, 
	    	height: 500,
	    	allowResize:true,
	    	showMaxButton: true,
	        ondestroy: function (action) {
	       		 if(action=="ok"){
	             	var json = nui.decode({"amountDetailId":amountDetailId});
					var gridtx = nui.get("gridtx");
					gridtx.load(json);
	           	 }
	        }
	    });
	}
	// 添加贴现电票
	function dianpiaotx(){
		var amountDetailId=nui.get("amountDetail.amountDetailId").getValue();
	    nui.open({
	        url: nui.context + "/biz/biz_product_detail/pjxx/biz_tx_add_dp.jsp?amountDetailId="+amountDetailId+"&partyId="+partyId+"&ecifPartyNum="+ecifPartyNum,
	        title: "新增", 
	        width: 800, 
	    	height: 500,
	    	allowResize:true,
	    	showMaxButton: true,
	        ondestroy: function (action) {
	       		 if(action=="ok"){
	             	var json = nui.decode({"amountDetailId":amountDetailId});
					var gridtx = nui.get("gridtx");
					gridtx.load(json);
	           	 }
	        }
	    });
	}
	//编辑贴现
	function edittx(v) {
		var amountDetailId=nui.get("amountDetail.amountDetailId").getValue();
		var grid = nui.get("gridtx");
	    var row = grid.getSelected();
	    if (row) {
	        nui.open({
	            url: nui.context+"/biz/biz_product_detail/pjxx/biz_tx_edit.jsp?applyDetailId="+row.applyDetailId+"&view="+v,
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
						var gridpj = nui.get("gridtx");
						gridpj.load(json);
	           	 	}
	            }
	        });
	    }else{
	    	alert("请选择贴现信息！");
	    }
	}
	//
	function removetx() {
		var grid = nui.get("gridtx");
	    var rows = grid.getSelected();
		if (null == rows) {
			nui.alert("请选择贴现信息！");
			return false;
		}
	if(bizType != '01'){
	   nui.alert("该票据在流程或者已经生效不能删除");
	}else{
		var json = nui.encode({"tbBizTxxxApply":rows});
		var amountDetailId=nui.get("amountDetail.amountDetailId").getValue();
		nui.confirm("确定删除吗？","确认",function(action){
	    	if(action!="ok") return;
	        $.ajax({
	            url: "com.bos.bizProductDetail.bizPjxx.DelTxxx.biz.ext",
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
					var gridpj = nui.get("gridtx");
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