<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 3231
  - Date: 2015-05-15 21:31:26
  - Description:获取评级结果，根据原页面修改  
-->
<head>
<title>评级调整</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="form" style="width:100%;height:auto;">
		<input id="adjustOptions.iraApplyId" class="nui-hidden nui-form-input" name="adjustOptions.iraApplyId"/>
		<input id="ratingApply.iraApplyId" class="nui-hidden nui-form-input" name="ratingApply.iraApplyId"/>
		<div id="dynpanel1" class="nui-dynpanel" columns="4">
	  		<label class="nui-form-label">系统评级结果（R0）：</label>
			<input id="ratingApply.governmentAdjustRatingCd" name="ratingApply.governmentAdjustRatingCd" class="nui-text nui-form-input" enabled="false" vtype="maxLength:100" />
			<label class="nui-form-label"></label>
			<label class="nui-form-label"></label>
	  		<label class="nui-form-label">是否手工调整：</label>
			<input id="adjustOptions.isAdjust" name="adjustOptions.isAdjust" required="true" data="data" valueField="dictID" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" onvaluechanged="ifTz"/>
			<label class="nui-form-label"></label>
			<label class="nui-form-label "></label>
			<label class="nui-form-label">客户经理评级结果(R1)：</label>
			<input id="ratingApply.gpModelVer" name ="ratingApply.gpModelVer" class="nui-dictcombobox nui-form-input" data="ratingCd" required="true" enabled="false"/>
			<label class="nui-form-label">手工调整说明：</label>
			<input id="adjustOptions.adjustDescribe" name="adjustOptions.adjustDescribe" class="nui-textarea nui-form-input" vtype="maxLength:100" />
			<label class="nui-form-label"></label>
			<label class="nui-form-label nui-hidden">评级结果（R2）：</label>
			<input id="ratingApply.generalAdjustRatingCd" name ="ratingApply.generalAdjustRatingCd" class=" nui-form-input nui-hidden" data="ratingCd" enabled="false"/>
		</div>
		<div id="dynpanel1" class="nui-dynpanel" columns="1">
			<label class="nui-form-label" ></label>
  		</div>
	</div>
	<div  class="nui-toolbar" style="border-bottom:0;text-align:right;margin-top: 20px;">
 					<label id="ts" class="nui-form-label"   style="font-size:medium;color:red"  >修改评级结果后请保存</label>
			 <a id="save" class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
	</div>
<script type="text/javascript">
		//大公国际长期评级
		var ratingCd = [{ id: 'AAA', text: 'AAA'}, { id: 'AA', text: 'AA'}, { id: 'A', text: 'A'},
						{ id: 'BBB', text: 'BBB'}, { id: 'BB', text: 'BB'}, { id: 'B', text: 'B'},
						{ id: 'CCC', text: 'CCC'}, { id: 'CC', text: 'CC'}, { id: 'C', text: 'C'}];
		
		nui.parse();
		var form = new nui.Form("#form");
		var iraApplyId ="<%=request.getParameter("applyId") %>";
		
		initPage();
		function initPage(){
	        var json = nui.encode({"iraApplyId":iraApplyId});
	        var proFlag ="<%=request.getParameter("proFlag") %>";
			if(proFlag!=1){
				nui.get("save").hide();
				$("#ts").css("display","none");
				nui.get("ratingApply.generalAdjustRatingCd").setEnabled(false);
				nui.get("adjustOptions.adjustDescribe").setEnabled(false);
				nui.get("adjustOptions.isAdjust").setEnabled(false);
				nui.get("ratingApply.gpModelVer").setEnabled(false);
			}
			$.ajax({
	            url: "com.bos.irm.irmApply.irmApply.getModeScaleToCombobx.biz.ext",
	            type: 'POST',
	            data: json,
	            async:false,
	            cache: false,
	            contentType:'text/json',
	            cache: false,
	            async: false,
	            success: function (mydata) {
	            	debugger;
	            	if(mydata && mydata.scales && mydata.scales.length>0){
		            	for(var i = 0;i<mydata.scales.length;i++){
			            	if(!mydata.scales[i].id&& mydata.scales[i].ID){
			            		mydata.scales[i].id = mydata.scales[i].ID;
			            	}
		            		if(!mydata.scales[i].text&& mydata.scales[i].TEXT){
			            		mydata.scales[i].text = mydata.scales[i].TEXT;
			            	}
		            	}
		            	nui.get("ratingApply.gpModelVer").setData(mydata.scales);
	            	}
 				}
	        });
			
			$.ajax({
	            url: "com.bos.irm.irmApply.irmApply.getIrmApplyCd.biz.ext",
	            type: 'POST',
	            data: json,
	            async:false,
	            cache: false,
	            contentType:'text/json',
	            cache: false,
	            success: function (mydata) {
	            	var o = nui.decode(mydata);
	            	form.setData(o);
	            	if("" ==o.adjustOptions.isAdjust || null==o.adjustOptions.isAdjust){
	            		nui.get("adjustOptions.isAdjust").setValue(0);
	            	}
					nui.get("ratingApply.generalAdjustRatingCd").setEnabled(false);
					nui.get("adjustOptions.adjustDescribe").setEnabled(false);
					nui.get("ratingApply.gpModelVer").setEnabled(false);
 				}
	        });
			
           var ret = checkBeforeSub();
           ifTz();
           var sflag="1";
 		}

		function ifTz(){
			var reateType= nui.get("adjustOptions.isAdjust").getValue();
			if("1"==reateType){
				nui.get("ratingApply.generalAdjustRatingCd").setEnabled(true);
				nui.get("ratingApply.gpModelVer").setEnabled(true);
				nui.get("adjustOptions.adjustDescribe").setEnabled(true);
 			}else{
				nui.get("ratingApply.generalAdjustRatingCd").setValue(nui.get("ratingApply.governmentAdjustRatingCd").getValue());
				nui.get("ratingApply.gpModelVer").setValue(nui.get("ratingApply.governmentAdjustRatingCd").getValue());
				nui.get("adjustOptions.adjustDescribe").setValue("");
				nui.get("ratingApply.generalAdjustRatingCd").setEnabled(false);
				nui.get("ratingApply.gpModelVer").setEnabled(false);
				nui.get("adjustOptions.adjustDescribe").setEnabled(false);
 			}
		}
		function save(){
 			var o = form.getData();
			if("1" == o.adjustOptions.isAdjust &&("" ==o.ratingApply.governmentAdjustRatingcd || ""==o.adjustOptions.adjustDescribe)){
				alert("请输入调整信息");
				return;
			}
			if(o.ratingApply.governmentAdjustRatingCd==o.ratingApply.gpModelVer && o.adjustOptions.isAdjust==1){
				alert("调整后等级不能和调整前相同！");
				return;
			}
			if(!o.ratingApply.governmentAdjustRatingCd || ""==o.ratingApply.governmentAdjustRatingCd){
				alert("请先获取系统评级结果！");
				return;
			}
			if(!o.ratingApply.gpModelVer || ""==o.ratingApply.gpModelVer){
				alert("请先获取客户经理评级结果！");
				return;
			}
	        var json = nui.encode(o);
			$.ajax({
	            url: "com.bos.irm.irmApply.irmApply.saveIrmApplyCd.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            cache: false,
	            success: function (mydata) {
	            	var o = nui.decode(mydata);
	            	 var ret = checkBeforeSub();
	            	alert("保存成功!");
	            	form.setData(o);
				}
	        });
	        var ret = checkBeforeSub();
 		}
		function checkBeforeSub(){
			var iraApplyId ="<%=request.getParameter("applyId") %>"; //协议申请ID
			var processInstId ="<%=request.getParameter("processInstId") %>"; //协议申请ID
			var json = nui.encode({"iraApplyId":iraApplyId,"processInstId":processInstId});
		    $.ajax({
	            url: "com.bos.irm.irmApply.irmApply.setRatingCd.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            cache: false,
	            success: function (text) {
	            return "1";
            }
        });
	}
</script>
</body>
</html>