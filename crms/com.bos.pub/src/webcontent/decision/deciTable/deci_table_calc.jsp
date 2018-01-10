<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-05-22

  - Description:TB_PUB_DECI_TABLE, com.bos.pub.decision.TbPubDeciTable-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
	<a class="nui-button"  iconCls="icon-goto" onclick="calcTable">计算</a>
</div>

<br/>
<div id="form2" style="width:100%;height:auto;overflow:hidden;">

</div>
			
    <script type="text/javascript">
 	nui.parse();
    var form2 = new nui.Form("#form2");
	if ("<%=request.getParameter("view") %>"=="1") {
		form2.setEnabled(false);
		nui.get("btnSave").hide();
	}
function initForm() {
	var json=nui.encode({"tbPubDeciTable":
		{"tid":
		"<%=request.getParameter("tid") %>"}});
	git.mask();
	$.ajax({
        url: "com.bos.pub.deciTable.getTbPubDeciTableHead.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	git.unmask();
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        		initDetail(text);
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
        	git.unmask();
            nui.alert(jqXHR.responseText);
        }
	});
}
initForm();

var resultInputId='result';
function initDetail(data) {
	var tbPubDeciTableHeads=data.tbPubDeciTableHeads||[];
	var tbPubDeciTableCols=data.tbPubDeciTableCols||[];
	var div=$('#form2');
	div.html(''); //清空
	
	var html='<table class="mini-grid-table" border="0" cellSpacing="0" cellPadding="0">';
	html+='<tbody>';
	html+='<tr>';
	var colnum=0;
	for (var i=0,len=tbPubDeciTableHeads.length; i<len; i++) {
		var tbPubDeciTableHead=tbPubDeciTableHeads[i];
		if (tbPubDeciTableHead.hcolnum > colnum)
			colnum=tbPubDeciTableHead.hcolnum;
		html+='<td class="mini-grid-headerCell mini-grid-bottomCell">';
		html+=tbPubDeciTableHead.hname+'('+tbPubDeciTableHead.htype+')';
		html+='</td>';
	}
	html+='<td class="mini-grid-headerCell mini-grid-bottomCell">';
	html+='结果';
	html+='</td>';
	html+='</tr>';
	html+='<tr>';
	for (var i=0,len=tbPubDeciTableHeads.length; i<len; i++) {
		var tbPubDeciTableHead=tbPubDeciTableHeads[i];
		if (tbPubDeciTableHead.hcolnum > colnum)
			colnum=tbPubDeciTableHead.hcolnum;
		html+='<td class="mini-grid-cell">';
		if (tbPubDeciTableHead.htype=='是否型') {
			//是否下拉框
			html+='<input name="'+tbPubDeciTableHead.hname+'" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002"/>';
		} else {
			html+='<input name="'+tbPubDeciTableHead.hname+'" class="nui-textbox nui-form-input" vtype="'
				+(tbPubDeciTableHead.htype=='数值型'?'float':'')+'"/>';
		}
		html+='</td>';
	}
	html+='<td class="mini-grid-cell">';
	//结果列
	html+='<input id="'+resultInputId+'" name="result" class="nui-text nui-form-input"/>';
	html+='</td>';
	html+='</tr>';
			
	html+='</tbody>';
	html+='</table>';
	//console.log(html);
	
	div.html(html);
	nui.parse(div[0]);
}

function calcTable() {
	form2.validate();
	if (form2.isValid() == false) {
		nui.alert("信息填写不正确");
		return;
	}
	var o=form2.getData();
	var m={'param':o,'tbPubDeciTable':{'tid':'<%=request.getParameter("tid") %>'}};
	var json=nui.encode(m);
	
	git.mask();
	$.ajax({
        url: "com.bos.pub.deciTable.calcTbPubDeciTable.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        async: false,
        contentType:'text/json',
        success: function (text) {
        	git.unmask();
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        		var r=nui.get(resultInputId);
        		r.setValue(text.result||'');
        		alert('计算结果："'+r.getValue()+'"');
        	}
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
