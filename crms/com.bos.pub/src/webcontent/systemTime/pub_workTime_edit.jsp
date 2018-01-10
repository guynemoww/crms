<%@page pageEncoding="UTF-8"%>
<%@include file="/common/nui/common.jsp"%>
<html>
<!-- 
  - Author(s): 朱发营
  - Date: 2014-11-07 16:16:19
  - Description:
-->
<head>
<title>修改营业时间</title>
</head>
<body>
	 <div id="panel1" class="nui-panel" title="营业时间" iconCls="icon-edit" style="width:99%;height:auto;" >
		<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
			<div class="nui-dynpanel" columns="4" id="table1">
				<label>营业时间：</label>
				<input id="workTime.operatingDate" name="workTime.operatingDate" allowInput="false" class="nui-datepicker nui-form-input"  format="yyyy-MM-dd"/>
			</div>
		</div>
		
		<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
		    borderStyle="border:0;">
		     <a class="nui-button" iconCls="icon-save" id="btn_save" onclick="updateWorkTime" >保存</a>   
		</div>
	</div>
</body>

<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	
	$.ajax({
            url: "com.bos.pub.sys.getWorkTime.biz.ext",
            type: 'POST',
            cache: false,
            contentType:'text/json',
            success: function (text) {
    			     form.setData(text);   
            }
    });
    
    function updateWorkTime(){
    	form.validate();
        if (form.isValid()==false) return;
        
        nui.get("btn_save").setEnabled(false);
        var o = form.getData();
        var json = nui.encode(o);
        $.ajax({
            url: "com.bos.pub.sys.updateWorkTime.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
           		if(text.msg){
           			nui.alert(text.msg);
           			nui.get("btn_save").setEnabled(true);
           			return;
           		} 
           		nui.alert("保存成功");
           		nui.get("btn_save").setEnabled(true);
            }
        });
    }
</script>
</html>