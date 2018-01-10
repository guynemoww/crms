<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-6-3 8:26:27
  - Description:
-->
<head>
<title>还款计划表试算</title>
<%@include file="/common/nui/common.jsp"%>
<script type="text/javascript" src="<%=contextPath%>/biz/biz_js/biz.js"></script>

<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
</head>
<body>
	<iframe name="exportFrame" id="exportFrame" src="" style="display:none;"></iframe>

	
		
			<center>
				<div id="form1" class="nui-form"
					style="width: 99.5%; height: auto; overflow: hidden;">
					<div class="nui-dynpanel" columns="8" id="table1">
						
						<label>本金：</label>
						<input id="item.bz" name="item.bz" class="nui-textbox nui-form-input" required="true" dataType="currency1" vtype="float;maxLength:20;range:100,100000000000" />
						<label>年利率（%）：</label>
						<input id="item.nll" name="item.nll" class="nui-textbox nui-form-input" required="true" vtype="float;maxLength:11;range:0,100"/>
						<label>起息日：</label>
						<input id="item.startDate" name="item.startDate"    class="nui-datepicker nui-form-input"  required="true"/>
						<label>到期日：</label>
						<input id="item.endDate" name="item.endDate"    class="nui-datepicker nui-form-input" required="true"/>
						<label>还款方式：</label>
						<input id="item.hkfs" name="item.hkfs" class="nui-dictcombobox nui-form-input"   dictTypeId="XD_SXCD1162" onvaluechanged="conRpTpChg" required="true"/>
						<label id="sc">首次还本期次：</label>
						<input id="item.schbqc" name="item.schbqc" class="nui-textbox nui-form-input"  vtype="int;range:2,10000" />
						<label>结息周期：</label>
						<input id="item.jxzq" name="item.jxzq" class="nui-dictcombobox nui-form-input" dictTypeId="XD_SXCD1018" required="true"/>
						<label>指定还款日：</label>
						<input id="item.zdhkr" name="item.zdhkr" class="nui-textbox nui-form-input" vtype="int;range:1,31" required="true"/>
						<label>间隔天数：</label>
						<input id="item.jgtq" name="item.jgtq" class="nui-textbox nui-form-input" vtype="int;range:2,10000" required="true" value="7" enabled="false"/>
					
					
					</div>
					<div class="nui-toolbar"
						style="text-align: right; border: 0; padding-right: 20px;">
			<!--  		<a class="nui-button" iconCls="icon-download" onclick="dc()">导出EXCEL</a> -->	
						<a class="nui-button" iconCls="icon-search" onclick="queryInit()" id="cx">查询</a>
						<a class="nui-button" onclick="reset()" iconCls="icon-reset">重置</a>
						<a class="nui-button" iconCls="icon-download" onclick="dc()">导出EXCEL</a>
						
					</div>
				
				
				<div id="hbjhgrid">
	<fieldset>
		  	<legend>
		   		<span>还本计划</span>
		    </legend>
			<div class="nui-toolbar" style="border-bottom:0;text-align: left;" id="feediv">
				<a class="nui-button" iconCls="icon-add" onclick="add('grid3')">增加</a>
				<a class="nui-button" iconCls="icon-remove" onclick="remove('grid3')">删除</a>
				<a class="nui-button" iconCls="icon-search" onclick="queryInit()" id="ss">试算</a>
				
			</div>
			<div id="grid3" class="nui-datagrid" style="width:100%;height:120px" 
				url="" dataField=""
				allowResize="true" showReloadButton="false" allowCellEdit="true" 
			    allowCellSelect="true"
				sizeList="[20,20,20,20,20,100]" multiSelect="false" pageSize="500" sortMode="client" showPager="false" >
				<div property="columns">
					<div type="checkcolumn" >选择</div>
					<div type="indexcolumn" >期次</div> 
							<div   field="hbDate" allowSort="true" width="" headerAlign="center"
							dictTypeId="" dateFormat="yyyyMMdd">还款日期
							<input property="editor" class="nui-datepicker" onvaluechanged=""  dateFormat="yyyyMMdd"/>
							</div>
					<div   field="hbDCurPrin" allowSort="true" width="" headerAlign="center"
							dictTypeId="">本金
							<input property="editor" class="nui-textbox"  onvaluechanged="" />
							</div>
					 
				</div>
			</div>
		</fieldset>
	</div>
		</div>		
				<div id="datagrid1" class="nui-datagrid"
					style="width: 99.5%; height: auto"
					url="com.bos.payInfo.PayInfo.getLoanHkss.biz.ext" dataField="hkss"
					allowResize="true"  showReloadButton="false" showPager="false" 
					sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" onselectionchanged="selectPo" sortMode="client">
					<div property="columns">
					
					
							
							<div field="currPeri" allowSort="true" width="" headerAlign="center"
							dictTypeId="">期次</div>
							
							
					<!--  		<div field="forwProvDate" allowSort="true" width="" headerAlign="center"
							dictTypeId="">还款日期</div>
							-->
						<div field="nextProvDate" allowSort="true" width="" headerAlign="center"
							dictTypeId="">还款日期
							<input property="editor" class="nui-datepicker" onvaluechanged=""/>
							</div>
								
							
						<div field="ts" allowSort="true" width=""
							headerAlign="center" dictTypeId="">天数
							<input property="editor" class="nui-text"/>
							</div>
							
						<div field="DTotalAmt" allowSort="true" width="" headerAlign="center"
							dictTypeId="">还款金额
							<input property="editor" class="nui-textbox"  onvaluechanged=""/>
							</div>
							
						<div field="DCurPrin" allowSort="true" width="" headerAlign="center"
							dictTypeId="">本金
							<input property="editor" class="nui-textbox"  onvaluechanged=""/>
							</div>
							
						<div field="DCurItr" allowSort="true" width="" headerAlign="center"
							dictTypeId="">利息
							<input property="editor" class="nui-text" />
							</div>
							
						<div field="sybj" allowSort="true" width="" headerAlign="center"
							dictTypeId="">剩余本金
							<input property="editor" class="nui-text" />
							</div>
							
					
					</div>
				</div>
			</center>
	
	<script type="text/javascript">
		nui.parse();
 		var form = new nui.Form("#form1");
 		
		var grid = nui.get("datagrid1");
		nui.get("item.hkfs").setData(getDictData('XD_SXCD1162','str','0100,0200,1100,1400,1500'));
		nui.get("item.jxzq").setData(getDictData('XD_SXCD1018','str','1,2,3,4,6'));
		
		$("#hbjhgrid").css("display","none");
				$("#sc").css("display","none");
			nui.get("item.schbqc").hide();
			nui.get('table1').refreshTable();
		function queryInit() {
			var hbjh = nui.get("grid3").getChanges();
			if(hbjh){
			var beginDate = nui.get("item.startDate").getValue();
			var endDate = nui.get("item.endDate").getValue();
			var dkje = nui.get("item.bz").getValue();
			var hkfs=nui.get("item.hkfs").getValue();
			var countnum=-1;
			var hkbj=0;
			//nui.alert(beginDate+"!!!"+endDate);
			if(hkfs=="1400" && hbjh[0]==null){
			nui.alert("请填写还本计划！");
					return;
			}
			
			
			if(hkfs=="1400"){
			for(var i=0;i<hbjh.length;i++){
			  hkbj=hkbj+parseFloat(hbjh[i].hbDCurPrin);
			  countnum=countnum+1;
				if(hbjh[i].hbDate==null ||hbjh[i].hbDate=='' ||
				hbjh[i].hbDCurPrin==null ||hbjh[i].hbDCurPrin=='' ){
					nui.alert("请将还本计划填写完整！");
					return;
				}
			 
				if(hbjh[i].hbDate.substr(0,10)<beginDate.substr(0,10)
				  ||hbjh[i].hbDate.substr(0,10)>endDate.substr(0,10)){
					nui.alert("提款日期必须在合同起期和止期之间！");
 					return;
				}
				
				
			}
					//nui.alert(countnum);
					//nui.alert(hbjh[countnum].hbDate.substr(0,10)+"!!!!");
				if(hbjh[countnum].hbDate.substr(0,10)!=endDate.substr(0,10) ){
					nui.alert("还本计划最后一期必须与贷款止期相同！");
					return;
				}
				//nui.alert(hkbj+"!!!!"+dkje);
				if(hkbj!=dkje ){
					nui.alert("还本计划本金之和与贷款金额不相等！");
					return;
				}
			
		}
			
			if (form.isValid()==false) {
	        	nui.alert("请输入必填项。");
	        	return;   
	        } 
		}
			var o = form.getData();//逻辑流必须返回total
 			o.hbjh=hbjh;
	        if((beginDate!=""||endDate!="")&& beginDate>=endDate){
			nui.alert("还款起期不能大于等于止期！");
					return;
			}
 			
		//	grid.load(o);
		 	grid.load(o, function(text) {
		 	 
		  	}); 
			
			git.unmask();
		}
		

 
		function reset() {
			form.reset();
			queryInit();
		}

			function conRpTpChg(){
		var hkfs = nui.get("item.hkfs").getValue();
 		if(hkfs=='0300'||hkfs=='0400'){
 				$("#sc").css("display","block");
				nui.get("item.schbqc").show();
 			nui.get("item.schbqc").setRequired(true);
 			nui.get("item.schbqc").validate();
 			nui.get('table1').refreshTable();
 			
		}else{
 		$("#sc").css("display","none");
		nui.get("item.schbqc").hide();
			nui.get("item.schbqc").setRequired(false);
			nui.get("item.schbqc").validate();
			nui.get('table1').refreshTable();
			
		}
		if(hkfs=='1400'){
				$("#hbjhgrid").css("display","block");
				nui.get("cx").hide();
		
		}else{
		$("#hbjhgrid").css("display","none");
		nui.get("cx").show();
		}
		
	}
	
		function add(gr) {
    	var count = nui.get(gr).getData().length==""?0:nui.get(gr).getData().length;
     
    		var row={"periodsNumber":++count,"currencyCd":'CNY'};
    	
        nui.get(gr).addRow(row,count);
    }
    
    
        function remove(gr) {
        var row = nui.get(gr).getSelected();
        if (row) {
        	
            	
            		nui.get(gr).removeRow(row,true);/* 删除页面行 */
           
        } else {
            nui.alert("请选中一条记录");
        }
    }
    
    function dc(){
			git.mask();
				var ifrm = document.getElementById("exportFrame");
				var hbjh = nui.get("grid3").getChanges();
		
		 var o = form.getData();//逻辑流必须返回total
  		 o.hbjh=hbjh;
	
		 	 	var json = nui.encode(o);
		 
 		$.ajax({
	            url: "com.bos.payInfo.PayInfo.getLoanHkssDC.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	git.unmask("form1");
	            	if(text.msg){
					git.unmask();
           ifrm.src=nui.context +"/pub/io/file/download.jsp?deleteFile=true";
	            	
	            	}else{
	            	git.unmask();
	            	 nui.alert("下载数据有误！");
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	       			git.unmask("form1");
	                nui.alert(jqXHR.responseText);
	            }
		});
		}
		
		function onselectSettle(e){
		}
	</script>
</body>
</html>