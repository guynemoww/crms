<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-3-18
  日志查询详细信息
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<title>日志查询详细信息(发生变化部分)界面</title>
</head>
<body>
<div id="logDiv" style="width:98%;heigth:auto;padding:5px;">

</div>
<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-close" onclick="CloseWindow('cancel')">关闭</a>
</div>
<script type="text/javascript">
 	nui.parse();
 	var logForm=new nui.Form('#logDiv');
 	
function initForm() {
	var json=nui.encode({"map":{"log_id":"<%=request.getParameter("logId") %>","task_id":"<%=request.getParameter("taskId") %>"}});
	$.ajax({
	        url: "com.bos.pub.log.getLogDetailListBiz.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	        	initLogs(text.logs);
	        	showLogs();
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
	});
}
initForm();

var metas={};
var logData=[];
function initLogs(logs) {
	//解析日志的meta信息及数据
	logData=[];
	metas={};
	if (!logs||logs.length<1)
		return;
	for(var i=0,len=logs.length; i<len; i++) {
		var log=logs[i];
		if (log.isLogMeta === 'true') {
			metas[log.name.toLowerCase()]=log;
			continue;
		}
		logData[logData.length]=log;
	}
}
self.logids={};//当前日志记录的每个DOM节点的ID
function showLogs() {
	//展示日志详细信息
	if (logData.length<1) {
		$('#logDiv').html('<h2>无相关信息</h2>');
		return;
	}
	var html='';
	var idSequence=1;
	console.log(nui.encode(logData));
	for (var i=0,len=logData.length; i<len; i++) {
		var log=logData[i];
		var meta=metas[log.logTableName.replace(/_/g,'').toLowerCase()];
		if (!meta) {
			alert('没有给'+log.logTableName+'配置相关信息');
			meta={};
		}
		var logOpType=log.logOpType;
		logOpType=logOpType=='insert'?'新增':logOpType=='update'?'修改(修改部分为灰色)':logOpType=='delete'?'删除':'快照';
		var dname=meta.displayName||meta.name;//显示名称
		dname = dname+'：'+logOpType;
		if (logOpType!='新增') {
			//dname += '&nbsp;<a href="#" onclick="openDetailPre(\''+log.logDetailId+'\',\''+log.logTableName+'\')">取上一版本</a>';
		}
		if (logOpType=='修改(修改部分为灰色)'||logOpType=='快照') {
			//name += '&nbsp;<a href="#" onclick="showDetailPre(\''+log.logDetailId+'\',\''+log.logTableName+'\')">显示差异</a>';
		}
		html += dname+'<br/>';
		
		//构造表单
		html += '<div class="nui-dynpanel" columns="4">';
		var cols=meta.cols||[];
		for(var j=0,lenj=cols.length; j<lenj; j++) {
			var col=cols[j];
			//if (log[col[0]] == undefined && log['original_value_'+col[0]] == undefined)
			//	continue;
			//console.log('xx:' + (log[col[0]] == undefined) + ',' + (log['original_value_'+col[0]] == undefined));
			//alert(col[1]);
			if(col[1]!="主键"){
				html += '<label>'+col[1]+'：</label>\n\t';
				var text='';
				if (col[2]) {
					text=nui.getDictText(col[2], log[col[0]]) || log[col[0]];
						if(col[2]=="product"){
							text=git.getProductName(log[col[0]]);
						}
				} else {
					text=log[col[0]];
				}
				var title='';
				var style='';
				if (log['original_value_'+col[0]]) {
					title='原值为：'+log['original_value_'+col[0]];
					style='background-color:#EEE;'
				}
				html += '<label id="id'+idSequence+'" title="'+title+'" style="'+style+'">'+text+'</label>\n\n\t';
				self.logids[log.logTableName+log.logPrimaryKey+col[0]]='id'+idSequence;
				idSequence++;
			}
		}
		html += '\n</div><br/>';
	}
	$('#logDiv').html(html);
	//alert(html);
	nui.parse($('#logDiv')[0]);
}
function log2Text(meta, log) {
	meta=meta||{};
	log=log||{};
	var cols=meta.cols||[];
	var msg='';
	for(var j=0,lenj=cols.length; j<lenj; j++) {
		var col=cols[j];
		var text='';
		if (col[2]) {
			text=nui.getDictText(col[2], log[col[0]]) || log[col[0]];
		} else {
			text=log[col[0]] || '';
		}
		text=text || log[col[0]] || '';
		var label=col[1];
		if (msg.length>1)
			msg += ',';
		msg += label + ':' + text;
	}
	return msg;
}
</script>
</body>
</html>
