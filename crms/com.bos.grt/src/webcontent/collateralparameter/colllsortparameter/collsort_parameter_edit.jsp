<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-11-22
  - Description:TB_CSM_APTITUDE_INFO, com.bos.dataset.csm.TbCsmAptitudeInfo
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
    <div id="form1" style="width:100%;height:auto;overflow:hidden;">
    	<input name="tbGrtSortarguments.sortType" id="tbGrtSortarguments.sortType" class="nui-hidden" />
    	<div class="nui-dynpanel" columns="4">
    	   <label>押品分类名称：</label>
		   <input name="tbGrtSortarguments.sortName" required="true" class="nui-textbox nui-form-input" vtype="maxLength:60" />

		   <label>押品分类父节点名称：</label>
		   <input name="parentSortType"  required="false" class="nui-textbox nui-form-input"/>

		   <label>最高抵质押率(%)：</label>
		   <input name="tbGrtSortarguments.hightMpRate" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" />

		   <label>重估频率：</label>
		   <input name="tbGrtSortarguments.assessCycle" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10"/>
		   
		   <label>抵质押类型：</label>
		   <input name="tbGrtSortarguments.collType" required="false" class="nui-dictcombobox nui-form-input" dictTypeId="YP_GLCD156" vtype="maxLength:10"/>
		   
		   <label>停用标志：</label>
		   <input name="tbGrtSortarguments.ableState"  required="false" class="nui-dictcombobox nui-form-input"  dictTypeId="XD_0002" vtype="maxLength:10"/>

		   <label>创建日期：</label>
		   <input name="tbGrtSortarguments.createDate" required="false" Enabled="false" class="nui-datepicker nui-form-input"  format="yyyy-MM-dd"/>
		   
		   <label>更新日期：</label>
		   <input name="tbGrtSortarguments.updateDate" id="tbGrtSortarguments.updateDate" required="false" Enabled="false" class="nui-datepicker nui-form-input" value="<%=com.bos.pub.GitUtil.getBusiDate()%>"  format="yyyy-MM-dd"/>
    	</div>
    </div>
    
    <div class="nui-toolbar" style="border-bottom:0;text-align:center;">
	   <a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
	   <a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
    </div>
    
    <script type="text/javascript">
 		nui.parse();
   	 	var form = new nui.Form("#form1");
   	 	var sortType = "<%=request.getParameter("sortType")%>";
   	 	
		if ("<%=request.getParameter("view") %>"=="1") {
			form.setEnabled(false);
			nui.get("btnSave").hide();
		}
		
		function initForm() {
		   var json = nui.encode({"tbGrtSortarguments":{"sortType":sortType}});
		   $.ajax({
	            url: "com.bos.grt.collateralparameter.collsortparameters.querySortParameterByRankTypeId.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	if(text.msg){
	            		nui.alert(text.msg);
	            	} else {
	            		form.setData(text);
	            		nui.get("tbGrtSortarguments.updateDate").setValue("<%=com.bos.pub.GitUtil.getBusiDate()%>");
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
		});
	  }
	  initForm();
	  
	  //数据更新后保存
	  function save(){
	     //校验
	     form.validate();
	     if(form.isValid()==false) return;
	     var o = form.getData();
	     var json = nui.encode(o);
	     $.ajax({
	       	   url:"com.bos.grt.collateralparameter.collsortparameters.updateSortParameters.biz.ext",
	       	   type:'POST',
	       	   data:json,
	       	   contentType:'text/json',
	       	   success:function(text){
	       	   		if(text.msg){
	       	   		   nui.alert("保存成功！");
	       	   		   CloseWindow("ok");
	       	   		}else{
	       	   		   nui.alert(text.msg);
	       	   		}
	       	   },
	       	   error:function(jqXHR, textStatus, errorThrown){
	       	   	  nui.alert(jqXHR.responseText);
	       	   }}); 
	 }
   </script>
</body>
</html>