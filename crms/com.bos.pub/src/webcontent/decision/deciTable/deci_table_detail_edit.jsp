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
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="tbPubDeciTable" class="nui-hidden" />
	<div class="nui-dynpanel" columns="4">
		<label>决策表编号：</label>
		<input name="tbPubDeciTable.tid" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" enabled="false"/>

		<label>决策表名称：</label>
		<input name="tbPubDeciTable.tname" required="true" class="nui-textbox nui-form-input" vtype="maxLength:100" />

		<label>创建时间：</label>
		<input name="tbPubDeciTable.createTime" required="false" class="nui-textbox nui-form-input" vtype="maxLength:10" enabled="false"/>
	</div>
</div>
				
<div class="nui-toolbar" style="border-bottom:0;padding-right:20px;text-align:right;">
	<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">保存</a>
	<a class="nui-button"  iconCls="icon-close" onclick="CloseWindow()">关闭</a>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a class="nui-button"  iconCls="icon-goto" onclick="calcTable">计算</a>
</div>

<br/>
<div id="form2" style="width:100%;height:auto;overflow:hidden;">

</div>
			
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1");
    var form2 = new nui.Form("#form2");
	if ("<%=request.getParameter("view") %>"=="1") {
		form.setEnabled(false);
		form2.setEnabled(false);
		nui.get("btnSave").hide();
	}
function initForm() {
	var json=nui.encode({"tbPubDeciTable":
		{"tid":
		"<%=request.getParameter("tid") %>"}});
	$.ajax({
        url: "com.bos.pub.deciTable.getTbPubDeciTable.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        		form.setData(text);
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
	});
	$.ajax({
        url: "com.bos.pub.deciTable.getTbPubDeciTableDetail.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        		initDetail(text);
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
	});
}
initForm();

function save() {
	form.validate();
	if (form.isValid() == false) {
		nui.alert("请将信息填写完整");
		return;
	}
	var o=form.getData();
	var json=nui.encode(o);
	//nui.alert(json);return;
	$.ajax({
        url: "com.bos.pub.deciTable.updateTbPubDeciTable.biz.ext",
        type: 'POST',
        data: json,
        cache: false,
        contentType:'text/json',
        success: function (text) {
        	if(text.msg){
        		nui.alert(text.msg);
        	} else {
        		CloseWindow("ok");
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
            nui.alert(jqXHR.responseText);
        }
	});
}

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
		html+='<td class="mini-grid-headerCell mini-grid-bottomCell ">';
		html+=tbPubDeciTableHead.hname+'('+tbPubDeciTableHead.htype+')'+'('+tbPubDeciTableHead.hop+')';
		html+='</td>';
	}
	html+='<td class="mini-grid-headerCell mini-grid-bottomCell ">';
	html+='结果';
	html+='</td>';
	html+='</tr>';
	var currentRow=0;
	var currentCol=0;
	for (var i=0,len=tbPubDeciTableCols.length; i<len; i++) {
		var tbPubDeciTableCol=tbPubDeciTableCols[i];
		if (currentRow != tbPubDeciTableCol.crow) {
			currentRow=tbPubDeciTableCol.crow;
			currentCol=1;
			if (currentRow != 1)
				html+='</tr>';
			html+='<tr>';
		}
		if (currentCol + 1 < tbPubDeciTableCol.ccol) {
			//补足单元格
		}
		
		var id=tbPubDeciTableCol.crow + '_' + tbPubDeciTableCol.ccol + '_' + tbPubDeciTableCol.cid;
		var cval=tbPubDeciTableCol.cval||'';
		if (cval=='null')
			cval='';
		html+='<td class="mini-grid-cell">';
		if (tbPubDeciTableCol.ccol > colnum) {
			//结果列
			html+='<input id="col'+id+'" class="nui-textbox nui-form-input" value="'+cval+'" onvalidation="validCol"/>';
		} else if (tbPubDeciTableHeads[tbPubDeciTableCol.ccol-1].htype=='是否型') {
			//是否下拉框
			html+='<input id="col'+id+'" class="nui-dictcombobox nui-form-input" dictTypeId="XD_0002" value="'
				+cval+'" onvalidation="validCol"/>';
		} else {
			html+='<input id="col'+id+'" class="nui-textbox nui-form-input" vtype="'
				+(tbPubDeciTableHeads[tbPubDeciTableCol.ccol-1].htype=='数值型'?'float':'')+'" value="'
				+cval+'" onvalidation="validCol"/>';
		}
		html+='</td>';
		if (i==len-1)
			html+='</tr>';
	}
	html+='</tbody>';
	html+='</table>';
	//console.log(html);
	
	div.html(html);
	nui.parse(div[0]);
	
	if ("<%=request.getParameter("view") %>"=="1") {
		form2.setEnabled(false);
	}
}

function validCol(e) {
	if (e.isValid !== true) {
		alert('输入的内容不正确！');
		return;
	}
	
	var id=e.sender.id;
	var ids=id.substr(3).split('_');
	var row=ids[0];
	var col=ids[1];
	var cid=ids[2];
	if (!row || !col || !cid) {
		alert('内容不正确！');
		return;
	}
	
	// 保存值
	var val=e.sender.getValue();
	var json=nui.encode({'tbPubDeciTableCol':{'cid':cid, 'cval':val}});
	git.mask();
	$.ajax({
        url: "com.bos.pub.deciTable.updateTbPubDeciTableCol.biz.ext",
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
        		initForm();
        	}
        },
        error: function (jqXHR, textStatus, errorThrown) {
        	git.unmask();
            nui.alert(jqXHR.responseText);
        }
	});
}

function calcTable() {
	nui.open({
        url: nui.context+"/pub/decision/deciTable/deci_table_calc.jsp?tid=<%=request.getParameter("tid") %>",
        title: "计算", 
        width: 1000,
		height: 500,
        allowResize:true,
		showMaxButton: true,
        onload: function () {
            var iframe = this.getIFrameEl();
            //iframe.contentWindow.SetData(data);
        },
        ondestroy: function (action) {
            if(action=="ok"){
                //search();
       	 	}
        }
    });
}
	</script>
</body>
</html>
