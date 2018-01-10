<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<%@include file="/common/nui/common.jsp" %>
	<title>押品入库</title>
</head>
<body>
	<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="6">
			<label>入库类型：</label>
			<input id="inType" name="inType" required="true" 
			class="nui-combobox nui-form-input" onitemclick="selectTrade" valueField="dictId" textField="dictName" />	
			<label>入库原因：</label>
			<input id="inReason" name="inReason" required="true" 
			class="nui-combobox nui-form-input"  valueField="dictId" textField="dictName" />	
		</div>
		<div class="nui-toolbar" style="text-align:right;border:0;padding-right:20px;"> 
		   <a class="nui-button" iconCls="icon-save" id="btnsave" onclick="save()">保存</a>
		</div>
	</div>
		<div id="grid1" class="nui-datagrid" style="width:100%;height:200px;" 
			url="com.bos.grt.manage.TbGrtRegcardinfo.getTbGrtRegcardByInid.biz.ext" allowAlternating="true"
			dataField="arrays" allowResize="false" showReloadButton="false" 
			sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
			<div property="columns">
				<div type="checkcolumn" ></div>
				<div field="REGISTER_CERTI_NO" allowSort="true">权证编号</div>
				<div field="CARD_TYPE" allowSort="true" dictTypeId="XD_SXFS0002">权证类型</div>
			<!-- 	<div field="REG_EFFECTICE_MODE" allowSort="true" dictTypeId="XD_SXFS0001">权证生效方式</div> -->
				<div field="REG_ORG_NAME" allowSort="true">登记机构名称</div>
				<div field="REG_ORG_MONEY" allowSort="true">登记金额(元)</div>
			</div>
   		</div>
				
	<script type="text/javascript">
	 	nui.parse();
	 	git.mask("form1");
    	var form = new nui.Form("#form1");
		var grid = nui.get("grid1");
		var outId ="<%=request.getParameter("outId") %>"; //押品入库ID
		var proFlag = "<%=request.getParameter("proFlag") %>";//1：可修改。0：不可修改.-1:过程查看，不可修改且不展示提交按钮
		
		grid.load({"inId":outId});
		
		grid.on("preload",function(e){
	   		if (!e.data || e.data.length < 1)
	   			return;
	   		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
	   			e.data[i]['REGISTER_CERTI_NO']='<a href="#" onclick="goToRard();return false;" value="'
	   				+ e.data[i].REGISTER_CERTI_NO
	   				+ '">'+e.data[i]['REGISTER_CERTI_NO']+'</a>';
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
		          //  state:"max",
		            onload: function(e){
		            	var iframe = this.getIFrameEl();
		            }
		  	 	 });	
			}
		}
		var json2 = nui.encode({parentId:"",typeId:"XD_SXFS0006"});
	     $.ajax({
	        url: "com.bos.csm.pub.getDict.getDict.biz.ext",
	        type: 'POST',
	        data: json2,
	  	    cache: true,
	       	async:false,
	        contentType:'text/json',
	        success: function (text) {
	            git.unmask();
	            nui.get("inType").setData(text.levels);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            git.unmask();
	            nui.alert(jqXHR.responseText);
	        }
	     });
	     var jsonReason= nui.encode({parentId:"",typeId:"XD_SXFS0005"});
		 $.ajax({
		        url: "com.bos.csm.pub.getDict.getDictForParentIdNull.biz.ext",
		        type: 'POST',
		        data: jsonReason,
		        cache: true,
		        contentType:'text/json',
		        success: function (text) {
		            git.unmask();
		            nui.get("inReason").setData(text.levels);
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            git.unmask();
		            nui.alert(jqXHR.responseText);
		        }
		    });
		
		function search() {
	 //    grid.load({"outId":outId});
	        
	     var json=nui.encode({"outId":outId});
		//查询出库原因。出库类型。预计时间并赋值
	     $.ajax({
	        	url: "com.bos.grtPro.outDetail.getInReason.biz.ext",
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
	            
	            var inType = text.fmap.inType;
	            var inReason = text.fmap.inReason;
	            nui.get("inType").value = inType;
	            nui.get("inReason").value = inReason;
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
		//校验完整性
		form.validate();
        if (form.isValid()==false) {
        	nui.alert("请完整填写信息！");
        	return;
        }
		
		var inType = nui.get("inType").value;
		var inReason = nui.get("inReason").value;
		
		var json=nui.encode({"inType":inType,"inReason":inReason,"productId":outId});
	     $.ajax({
	        	url: "com.bos.grtPro.outDetail.updateInReason.biz.ext",
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
		
		function selectTrade(e) {
		var json = nui.encode({parentId:nui.get("inType").getValue(),typeId:"XD_SXFS0005"});
	     $.ajax({
	        url: "com.bos.csm.pub.getDict.getDict.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: true,
	        contentType:'text/json',
	        success: function (text) {
	            git.unmask();
	            nui.get("inReason").setData(text.levels);
	            nui.get("inReason").setValue(null);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            git.unmask();
	            nui.alert(jqXHR.responseText);
	        }
	     });
	}
	</script>
</body>
</html>