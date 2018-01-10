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
<title>日志查询详细信息界面</title>
</head>
<body>
<div id="logDelDiv" style="width:98%;heigth:auto;margin-top:5px;padding:0px;background-color:#EEE;">

</div>
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
	var json=nui.encode({"map":{"log_id":"<%=request.getParameter("logId") %>"}});
	$.ajax({
	        url: "com.bos.pub.log.getLogDetailList.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	        	initLogs(text.logs);
	        	self.currentLogData=self.logData;
	        	self.currentLogMetas=self.metas;
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
	for (var i=0,len=logData.length; i<len; i++) {
		var log=logData[i];
		var meta=metas[log.logTableName.replace(/_/g,'').toLowerCase()];
		if (!meta) {
			alert('没有给'+log.logTableName+'配置相关信息');
			meta={};
		}
		var logOpType=log.logOpType;
		logOpType=logOpType=='insert'?'新增':logOpType=='update'?'修改':logOpType=='delete'?'删除':'快照';
		var dname=meta.displayName||meta.name;//显示名称
		dname = dname+'：'+logOpType;
		if (logOpType!='新增') {
			dname += '&nbsp;<a href="#" onclick="openDetailPre(\''+log.logDetailId+'\',\''+log.logTableName+'\')">取上一版本</a>';
		}
		if (logOpType=='修改'||logOpType=='快照') {
			dname += '&nbsp;<a href="#" onclick="showDetailPre(\''+log.logDetailId+'\',\''+log.logTableName+'\')">显示差异</a>';
		}
		html += dname+'<br/>';
		
		//构造表单
		html += '<div class="nui-dynpanel" columns="4">';
		var cols=meta.cols||[];
		for(var j=0,lenj=cols.length; j<lenj; j++) {
			var col=cols[j];
			html += '<label>'+col[1]+'：</label>\n\t';
			var text='';
			if (col[2]) {
				text=nui.getDictText(col[2], log[col[0]]) || log[col[0]];
			} else {
				text=log[col[0]];
			}
			html += '<label id="id'+idSequence+'">'+text+'</label>\n\n\t';
			self.logids[log.logTableName+log.logPrimaryKey+col[0]]='id'+idSequence;
			idSequence++;
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
function showLogsDiff() {
	//展示日志详细信息与前一版本的差异
	if (logData.length<1) {
		alert('无上一版本信息');
		return;
	}
	var cnt=0;//发生变化的个数
	//console.log(nui.encode(logData));
	var delMessage = '';
	var oldlogids={};//用于辅助计算当前日志记录新增的记录
	for (var i=0,len=logData.length; i<len; i++) {
		var log=logData[i];
		var meta=metas[log.logTableName.replace(/_/g,'').toLowerCase()];
		if (!meta) {
			//alert('没有给'+log.logTableName+'配置相关信息');
			meta={};
		}
		var cols=meta.cols||[];
		for(var j=0,lenj=cols.length; j<lenj; j++) {
			var col=cols[j];
			oldlogids[log.logTableName+log.logPrimaryKey+col[0]]=true;//表明此字段原来有
			var preTextId=self.logids[log.logTableName+log.logPrimaryKey+col[0]];
			if (!preTextId) {// 当前值不存在，被删除了
				if (delMessage.length > 1)
					delMessage += '<br/><br/>';
				delMessage += log2Text(meta, log);
				cnt++;
				break;
			}
			var preTextObj = document.getElementById(preTextId);
			if (!preTextObj)
				continue;
			var preText = preTextObj.innerText.trim();
			if (col[2]) {
				text=nui.getDictText(col[2], log[col[0]]) || log[col[0]];
			} else {
				text=log[col[0]];
			}
			if (preText != text) {
				var tips = '当前值：' + preText + ',上一版本：' + text;
				console.log(tips);
				preTextObj.parentNode.style.backgroundColor='red';
				preTextObj.parentNode.title=tips;
				cnt++;
			} else {
				preTextObj.parentNode.style.backgroundColor='';
				preTextObj.parentNode.title='';
			}
		}
	}
	for (var i=0,len=self.currentLogData.length; i<len; i++) {// 计算新增的记录
		var log=self.currentLogData[i];
		var meta=self.currentLogMetas[log.logTableName.replace(/_/g,'').toLowerCase()];
		if (!meta) {
			meta={};
		}
		var cols=meta.cols||[];
		//console.log('preTextId cols:'+nui.encode(cols));
		for(var j=0,lenj=cols.length; j<lenj; j++) {
			var col=cols[j];
			var preTextId=self.logids[log.logTableName+log.logPrimaryKey+col[0]];
			//console.log('preTextId:'+preTextId+','+(log.logTableName+log.logPrimaryKey+col[0]));
			if (preTextId) {// 当前值存在
				if (oldlogids[log.logTableName+log.logPrimaryKey+col[0]]) {
					// 原来值存在
				} else {
					// 当前存在，原来不存在，判定为新增
					var preTextObj = document.getElementById(preTextId);
					if (!preTextObj)
						continue;
					//preTextObj.style.color='red';//新增的用红色字体表示
					var table=$(preTextObj).parents('table');
					if (table && table.length > 0) {
						table[0].style.color='red';
						table[0].title='新增的';
					}
					cnt++;
					break;
				}
			}
		}
	}
	if (delMessage.length > 0) {
		document.getElementById('logDelDiv').innerHTML = '已删除：<br/>' + delMessage;
	}
	if (cnt==0) {
		alert('所有值与上一版本相同');
	}
	self.oldlogids=oldlogids;
}

function showDetailPre(logDetailId,logTableName) {
	git.mask();
	var json=nui.encode({"map":{"detailId":logDetailId}});
	$.ajax({
	        url: "com.bos.pub.log.getLogPreByLogDetail.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	        	//alert(nui.encode(text.data));
	        	git.unmask();
	        	var data=text.data||[];
	        	if (data.length>0&&data[0].LOG_ID) {
	        		var json=nui.encode({"map":{"log_id":data[0].LOG_ID}});
					$.ajax({
					        url: "com.bos.pub.log.getLogDetailList.biz.ext",
					        type: 'POST',
					        data: json,
					        cache: false,
					        contentType:'text/json',
					        success: function (text) {
					        	initLogs(text.logs);
					        	showLogsDiff();
					        },
					        error: function (jqXHR, textStatus, errorThrown) {
					            nui.alert(jqXHR.responseText);
					        }
					});
	        	} else {
	        		alert('未查到相关信息，或没有保存上一个版本的数据！');
	        	}
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        	git.unmask();
	        }
	});
}
function openDetailPre(logDetailId,logTableName) {
	var json=nui.encode({"map":{"detailId":logDetailId}});
	$.ajax({
	        url: "com.bos.pub.log.getLogPreByLogDetail.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	        	//alert(nui.encode(text.data));
	        	var data=text.data||[];
	        	if (data.length>0&&data[0].LOG_ID) {
	        		openDetail(data[0]);
	        	} else {
	        		alert('未查到相关信息，或没有保存上一个版本的数据！');
	        	}
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            nui.alert(jqXHR.responseText);
	        }
	});
}
function openDetail(log) {
    nui.open({
        url: nui.context+"/pub/log/log_detail.jsp?logId="+log.LOG_ID,
        title: "查看明细("+nui.formatDate(nui.parseDate(log.CREATE_DBTIME),'yyyy-MM-dd HH:mm:ss')+"):"+log.LOG_DESC, 
        width: 800, 
    	height: 500,
    	allowResize:true,
    	showModal:false,
    	showMaxButton: true,
        ondestroy: function (action) {
            if(action=="ok"){
            }
        }
    });
}
</script>
</body>
</html>
