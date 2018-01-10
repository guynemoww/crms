<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<%@include file="/common/nui/common.jsp" %>
	<%@page import="commonj.sdo.DataObject,com.eos.data.datacontext.UserObject"%>
	<title>押品出库</title>
</head>
<body>
	<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="6">
			<label>出库类型：</label>
			<input id="outType" name="outType" required="true" 
			class="nui-combobox nui-form-input" onitemclick="selectTrade" enabled="false" valueField="dictId" textField="dictName"/>	
			
			<label>出库原因：</label>
			<input id="outReason" name="outReason" required="true" 
			class="nui-combobox nui-form-input"  valueField="dictId" textField="dictName" />	
			
			<label class="nui-form-label" id = "fdate">预计归还时间：</label>
			<input id="cardInRevertDate" name="cardInRevertDate" class="nui-datepicker nui-form-input"  
			minDate="<%=GitUtil.getBusiDateStr()%>" required="false"  format="yyyy-MM-dd"/>

		</div>
		<div class="nui-toolbar" style="text-align:right;border:0;padding-right:20px;"> 
		   <a class="nui-button" iconCls="icon-save" id="btnsave" onclick="save()">保存</a>
		</div>
	</div>
	<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
			url="com.bos.grt.regmanage.collateralout.queryOutList.biz.ext"
			dataField="arrays" allowResize="false" showReloadButton="false" 
			sizeList="[10,15,20,50,100]" multiSelect="true" pageSize="10" sortMode="client">
			<div property="columns">
				<div type="checkcolumn" ></div>
				<div field="REGISTER_CERTI_NO" allowSort="true">登记权证编号</div>
				<div field="CONTRACT_NUM" allowSort="true">借款合同编号</div>
				<div field="PARTY_NAME" allowSort="true">抵质押人名称</div>
				<div field="CARD_REG_DATE"  allowSort="true" format="yyyy-MM-dd">登记生效日期</div>
				<div field="REG_DUE_DATE" allowSort="true" format="yyyy-MM-dd">登记到期日期</div>
				<div field="SAVE_ORG" dictTypeId="org">凭证保管地</div>
				<div field="CON_STATUS" dictTypeId="org">关联主合同状态</div>
			</div>
   		</div>
				
	<script type="text/javascript">
	 	nui.parse();
	 	git.mask("form1");
    	var form = new nui.Form("#form1");
		var grid = nui.get("grid1");
		
		var outId ="<%=request.getParameter("outId") %>"; //押品出库ID
		var proFlag = "<%=request.getParameter("proFlag") %>";//1：可修改。0：不可修改.-1:过程查看，不可修改且不展示提交按钮
		
		var json2 = nui.encode({parentId:"",typeId:"XD_SXFS0003"});
	     $.ajax({
	        url: "com.bos.csm.pub.getDict.getDict.biz.ext",
	        type: 'POST',
	        data: json2,
	  	    cache: true,
	       	async:false,
	        contentType:'text/json',
	        success: function (text) {
	            git.unmask();
	            nui.get("outType").setData(text.levels);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            git.unmask();
	            nui.alert(jqXHR.responseText);
	        }
	     });
	     var jsonReason= nui.encode({parentId:"",typeId:"XD_SXFS0004"});
		 $.ajax({
		        url: "com.bos.csm.pub.getDict.getDictForParentIdNull.biz.ext",
		        type: 'POST',
		        data: jsonReason,
		        cache: true,
		        contentType:'text/json',
		        success: function (text) {
		            git.unmask();
		            nui.get("outReason").setData(text.levels);
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            git.unmask();
		            nui.alert(jqXHR.responseText);
		        }
		    });
		    //展示权证信息
		function goToRard(e){
			var rows= grid.getSelecteds();
			if(rows.length==1){
				var row = grid.getSelected();
				var suretyKeyId=row.SURETY_KEY_ID;
				var partyId=row.PARTY_ID;
				//编辑登记权证
		        nui.open({
		            url: nui.context + "/grt/manage/TbGrtRegcardinfo/TbGrtRegcardinfo_view.jsp?suretyKeyId="+suretyKeyId+"&view=0&partyId="+partyId,
		            showMaxButton: true,
		            title: "",
		            width: 800,
		            height: 500,
		           // state:"max",
		            onload: function(e){
		            	var iframe = this.getIFrameEl();
		            }
		  	 	 });	
			}
		}
		function search() {
	        grid.load({"outId":outId});
	        
	        grid.on("preload",function(e){
	       		if (!e.data || e.data.length < 1)
	       			return;
	       		for (var i=0; i<e.data.length; i++){
	       			e.data[i]['CONTRACT_NUM']='<a href="#" onclick="bizView3231(\''+ e.data[i].CONTRACT_NUM+ '\');">'+e.data[i]['CONTRACT_NUM']+'</a>';
	       		}
	       });
	       
	       grid.on("preload",function(e){
	   		if (!e.data || e.data.length < 1)
	   			return;
	   		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
	   			e.data[i]['REGISTER_CERTI_NO']='<a href="#" onclick="goToRard();return false;" value="'
	   				+ e.data[i].REGISTER_CERTI_NO
	   				+ '">'+e.data[i]['REGISTER_CERTI_NO']+'</a>';
	   		}
	  	});
	  	
	     var json=nui.encode({"outId":outId});
		
		//查询出库原因。出库类型。预计时间并赋值
	     $.ajax({
	        	url: "com.bos.grtPro.outDetail.getOutType.biz.ext",
	        	type: 'POST',
	        	data: json,
	        	cache: false,
	        	contentType:'text/json',
	        	success: function (text) {
	        		if(text.msg !=null){
	            		nui.alert(text); //失败时后台直接返回出错信息
	            		return;
	            	}
	            var o = nui.encode(text.fmap);
	            
	            var outtype = text.fmap.outType;
	            var outReason = text.fmap.outReason;
	            var cardInRevertDate = text.fmap.cardInRevertDate;
	            nui.get("outType").value = outtype;
	      		    nui.get("outReason").value = outReason;
	      		   if(proFlag == "1" && outtype == "21" && '<%=((UserObject)session.getAttribute("userObject")).getAttributes().get("legorg") %>' == "5099") {
	      		   	    nui.get("outReason").setEnabled(false);
	      		   }
	      		    
	            	if(outtype=="21"){
	            		selectTrade("21");
	            		nui.get("cardInRevertDate").hide();
	            		$("#fdate").css("display","none");
	            	}else if(outtype=="22"){
	            		selectTrade("22");
	            	}
	            nui.get("cardInRevertDate").value = cardInRevertDate;
	            	git.unmask("form1");
	                 var o = nui.decode(text.fmap);
	                 form.setData(o);
	        	},
	        	error: function (jqXHR, textStatus, errorThrown) {
	            	nui.alert(jqXHR.responseText);
	        	}
			});
			
			//proFlag   如果流程标识为0表示为查看，隐藏保存按钮禁用控件
				if(proFlag=="0"||proFlag=="-1" ){
					form.setEnabled(false);
					nui.get("btnsave").hide();
				}
	    }
	    search();
	    
		function save(){
		form.validate();
        if (form.isValid()==false) {
        	nui.alert("请完整填写信息！");
        	return;
        }
		var outType = nui.get("outType").value;
		var outReason = nui.get("outReason").value;
		var cardInRevertDate = nui.get("cardInRevertDate").value;
				
		//如果出库类型为 临时出库，则必须输入预计归还时间
		var o = form.getData();
		var json=nui.encode({"outType":outType,"outReason":outReason,"cardInRevertDate":cardInRevertDate,"productId":outId});
		
	     $.ajax({
	        	url: "com.bos.grtPro.outDetail.updateOutDetail.biz.ext",
	        	type: 'POST',
	        	data: json,
	        	cache: false,
	        	contentType:'text/json',
	        	success: function (text) {
	        		if(text.msg !=null){
	            		nui.alert(text.msg); //失败时后台直接返回出错信息
	            		return;
	            	}
	            	alert("保存成功！");
	        	},
	        	error: function (jqXHR, textStatus, errorThrown) {
	            	nui.alert(jqXHR.responseText);
	        	}
			});
		}
		
	function selectTrade(outType) {
		if(outType == "22"){
			nui.get("cardInRevertDate").set({required: true});
			nui.get("cardInRevertDate").setValue(null);
			var json = nui.encode({parentId:"22",typeId:"XD_SXFS0004"});
		     $.ajax({
		        url: "com.bos.csm.pub.getDict.getDict.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: true,
		        contentType:'text/json',
		        success: function (text) {
		            git.unmask();
		            nui.get("outReason").setData(text.levels);
		      //      nui.get("outReason").setValue(outReason);
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            git.unmask();
		            nui.alert(jqXHR.responseText);
		        }
		     });
		}else if(outType=="21"){
			 nui.get("cardInRevertDate").setValue(null);
			 var json = nui.encode({parentId:"21",typeId:"XD_SXFS0004"});
		     $.ajax({
		        url: "com.bos.csm.pub.getDict.getDict.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: true,
		        contentType:'text/json',
		        success: function (text) {
		            git.unmask();
		            nui.get("outReason").setData(text.levels);
		       //     nui.get("outReason").setValue(null);
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            git.unmask();
		            nui.alert(jqXHR.responseText);
		        }
		     });
		}	
	}
	
	
	</script>
</body>
</html>