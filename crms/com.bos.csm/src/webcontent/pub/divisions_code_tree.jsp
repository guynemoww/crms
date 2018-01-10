<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 夏群
  - Date: 2014-05-05 13:45:25
  - Description:
-->
<head>
<title>行政区划树</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="4">
		<label>国家：</label>
		<input id="item.nationalityCd" name="item.nationalityCd" class="nui-text nui-form-input" dictTypeId="CD000003" />

		<label>省/直辖：</label>
		<input id="item.provinceCd" name="item.provinceCd" required="true" class="nui-combobox nui-form-input" onvaluechanged="d1Change" valueField="dictid" textField="dictname"  />

		<label>市：</label>
		<input  id="item.cityCd" name="item.cityCd" required="true"class="nui-combobox nui-form-input"  onvaluechanged="d2Change" valueField="dictid" textField="dictname"  />

		<label>区县：</label>
		<input id="item.district"  name="item.district" required="true" class="nui-combobox nui-form-input" valueField="dictid" textField="dictname"  />
		</div>
</div>
<div class="nui-toolbar" style="border-bottom:0;text-align:right">
    <a class="nui-button"  iconCls="icon-save" onclick="save">确定</a>
    <a class="nui-button" id="cancelBtn_01" iconCls="icon-cancel" onclick="CloseWindow()">关闭</a>
</div>
 <script type="text/javascript">
	 nui.parse();
	 var form = new nui.Form("#form1");
	var nationalityCd = "<%=request.getParameter("nationalityCd") %>";
	if(nationalityCd){
	
		nui.get("item.nationalityCd").setValue(nationalityCd);
		var t = nui.get("item.nationalityCd").getValue();
		nationChange(nationalityCd);
		if(nationalityCd=="MAC"||nationalityCd=="HKG"||nationalityCd=="HKG"){
			nui.get("item.provinceCd").setRequired(false);
			nui.get("item.cityCd").setRequired(false);
			nui.get("item.district").setRequired(false);
		}
	}
	
	function nationChange(nationalityCd){
		git.getDistrictsByParentid(nationalityCd,function(data){
		nui.get("item.provinceCd").setData(data.items);
	});} 
	
	function d1Change(e){
		git.getDistrictsByParentid(e.sender.getValue(),function(data){
		var parentid = e.sender.getValue();
		if(parentid=='110000000000'||parentid=='120000000000'||parentid=='310000000000'||parentid=='500000000000'){
			var cityDate = nui.encode([{"dictid":parentid,"dictname":e.sender.getText()}]);
			nui.get('item.cityCd').setData(cityDate);
		}else{
			nui.get('item.cityCd').setData(data.items);
		}
	});}
	function d2Change(e){
		git.getDistrictsByParentid(e.sender.getValue(),function(data){
		nui.get("item.district").setData(data.items);
	});}
	
	

	function save(){
		CloseWindow("ok");
	}
 function getData(){
    var o=form.getData();
    var data=nui.encode(o);
      return data;
    }
    
	</script>
</body>
</html>