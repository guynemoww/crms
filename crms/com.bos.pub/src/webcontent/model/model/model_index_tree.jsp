<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-10-16 10:38:33
  - Description:模型查看、测试
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>

<table id="dynTable" style="width:100%;height:auto;table-layout:fixed;" class="nui-form-table">

</table>

<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
	<a class="nui-button" iconCls="icon-save" onclick="save" id="btnSave">保存</a>
	<a class="nui-button" iconCls="icon-close" onclick="CloseWindow()">关闭</a>
	&nbsp;
	&nbsp;
	&nbsp;
	总分：
	<input id="totalGrade" class="nui-text" name="totalGrade" value="0.00"/>
</div>


<script type="text/javascript">
	 	nui.parse();
if ("<%=request.getParameter("view") %>"=="1") {
	nui.get("btnSave").hide();
}
function SetData(m) {
	window.model=m;
}

function initForm() {
	// 初始化模型信息
	var json=nui.encode({"item":{"modelId":"<%=request.getParameter("itemId") %>"}});
	$.ajax({
            url: "com.bos.pub.model.model.getModel.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.msg){
            		alert(text.msg);
            	} else {
            		SetData(text.item);
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
            }
	});
	
	json=nui.encode({"itemId":"<%=request.getParameter("itemId") %>"});
	// 初始化指标显示
	$.ajax({
            url: "com.bos.pub.model.model.getModelIndexTree.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.items && text.items.length>0){
            		//alert(text.items);
            		initTable(text.items);
            	} else {
            		alert("该模型没有配置指标！");
            		return;
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
            }
	});
	
	// 显示基本指标
	json=nui.encode({"itemId":"<%=request.getParameter("itemId") %>"});
	$.ajax({
            url: "com.bos.pub.model.model.getBaseIndexAndItems.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            	if(text.items && text.items.length>0){
            		//alert(text.items);
            		initIndex(text.items);
            		//initTotalGrade();
            	} else {
            		alert("该模型没有配置指标！");
            		return;
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
            }
	});
}
initForm();

//初始化模型指标显示的下拉框、输入框等
function initIndex(items) {//alert(nui.encode(items));
	var dynTable=document.getElementById("dynTable").firstChild;
	var trs = dynTable.getElementsByTagName("tr");
	for (var i=0; i<trs.length; i++) {
		var td = trs[i].children[trs[i].children.length-1];
		var it = td.item;
		var idx = getIndexItems(items, it.indexId);
		if (!idx)
			continue;
		td.idx = idx;
		td.idx.formFieldName="idx" + i;
		td.id=td.idx.formFieldName + "_td";
		td.idx.formFieldGradeName="idx" + i + "_grade";
		
		if (idx.indexType=='1') {
			//下拉框字典表
			window[td.idx.formFieldName]=idx.items;
			td.innerHTML = '<input class="nui-dictcombobox" id="' + td.idx.formFieldName
				+ '" name="' + td.idx.formFieldName
				+ '" emptyText="请选择" showNullItem="true" nullItemText="请选择" data="' + td.idx.formFieldName
				+ '" valueField="iValue" textField="iText" required="true" onvaluechanged="setIdxGrade(\''+td.idx.formFieldName+'\')"/>'
				+ '得分：<input class="nui-text" id="' + td.idx.formFieldGradeName
				+ '" name="' + td.idx.formFieldGradeName
				+ '" value="0"/>';
			nui.parse(td);
			setIdxGrade(td.idx.formFieldName);
		} else {
			var v = idx.iValue ? idx.iValue : "";
			td.innerHTML = '<input class="nui-textbox" id="' + td.idx.formFieldName
				+ '" name="' + td.idx.formFieldName
				+ '" required="true" enabled="'
				+ (idx.indexType!='3' && v ? true : false)
				+ '" value="' + v
				+ '" onvaluechanged="setIdxGrade(\''+td.idx.formFieldName+'\')"/>'
				+ '得分：<input class="nui-text" id="' + td.idx.formFieldGradeName
				+ '" name="' + td.idx.formFieldGradeName
				+ '" value="0"/>';
			nui.parse(td);
			setIdxGrade(td.idx.formFieldName);
		}
	}
}

function setIdxGrade(id) {
	var td = document.getElementById(id + "_td");
	var idx = td.idx; //指标
	//if (idx.indexType=='1') {
		//下拉框字典表
		var v = nui.get(id).getValue() || '0';
		idx.iValue = v;
		if (idx.gradeType=='1') {
			//直接得分
			idx.iGrade = v;
		}
		if (idx.gradeType=='2') {
			//比较范围得分
			var ranges = idx.ranges;
			if (!ranges) {
				idx.iGrade = 0;
			} else {
				for (var i=0; i<ranges.length; i++) {
					var g="var value="+v+";value "+ranges[i].rOperator+" "+ranges[i].rTarget+" ? "+ranges[i].rGrade+" : null";
					idx.iGrade = eval(g);
					if (idx.iGrade === 0 || !!idx.iGrade) {
						break;
					}
				}
			}
		}
		if (idx.gradeType=='3') {
			//计算公式得分
			idx.iGrade=idx.iGrade || 0;
			//idx.iGrade = eval("var value="+v+";" +idx.gradeExpr);
		}
	//}
	
	idx.iGrade = idx.iGrade ? idx.iGrade : 0;
	try {
		idx.iGrade=parseFloat(idx.iGrade).toFixed(2);
	} catch (e){}
	if (isNaN(idx.iGrade))
		idx.iGrade = 0;
	nui.get(td.idx.formFieldGradeName).setValue(idx.iGrade);
	initTotalGrade();
}
//计算总得分
function initTotalGrade() {
	var total = 0;
	var str = "";
	for (var i=0; i<300; i++) {
		//假设最多300个指标（叶子节点）
		var id="idx" + i + "_grade";
		var g=nui.get(id);
		if (!g)
			continue;
		var grade=parseFloat(g.getValue());
		if (isNaN(grade)== false)
			total += grade;
		
		id="idx" + i + "_td";
		//var ind = document.getElementById(id).idx.indexInd;
		var ind = document.getElementById(id).item.miName;
		str += "var " + ind + "=" + grade + ";";
	}
	total = parseFloat(total).toFixed(2);
	if (window.model.gradeType=='2') {//模型总分计算方式：公式计算得分
		try {// 原使用aviator时需要，改为自行开发后不需要，因为已经后台计算好指标得分
			str += "var 下一步 = null;";
			var expr = window.model.gradeExpr;
			// aviator最中，以“def ”或“定义：”表示变量定义，以回车表示表达式结束
			//expr = expr.replace(/def /g,'var ').replace(/定义：/g,'var ').replace(/\n/g,';');
			//str += expr;
			//var tmp = eval(str);
			var tmp = git_ruleEval(str, expr);
			tmp = parseFloat(tmp).toFixed(2); 
			if (isNaN(tmp) || tmp== -Infinity || tmp == Infinity)
				tmp = "0.00";
			nui.get("totalGrade").setValue(tmp);
		} catch(e){
			//alert("计算总得分时出现错误："+e.message);
			nui.get("totalGrade").setValue(total);
		}
	} else {//模型总分计算方式：指标得分累加
		nui.get("totalGrade").setValue(total);
	}
}

//根据模型指标获取基本指标
function getIndexItems(items, indexId) {
	if (!indexId)
		return null;
	for (var i=0; i<items.length; i++) {
		if (items[i].indexId==indexId) {
			return items[i];
		}
	}
	return null;
}

//根据模型指标构造树结构的表格
var maxLevel=1;
function initTable(items) {
	var tempMaxLevel=maxLevel;
	for (var i=0;i<items.length;i++) {
		if (items[i].pMiId=='0') {
			initItem(items[i],items,tempMaxLevel);
		}
	}
	//alert(nui.encode(items[0]));
	maxLevel += 1;//最后一列最后指标值输入列
	for (var i=0;i<items.length;i++) {
		var len = getRowCount(items[i]);
		if (len == 1 && items[i].indexId)
			initItemTr(items[i]);
	}
}

function initItemTr(item) {
	var its = getToTopItemList(item);
	
	var dynTable=document.getElementById("dynTable").firstChild;
	var tr=document.createElement("tr");
	dynTable.appendChild(tr);
	
	for (var i=its.length-1; i>=0; i--) {
		var it=its[i];
		if (it.added==1) {
			continue;
		}
		
		var td=document.createElement("td");
		var desc = it.indexDesc ? it.indexDesc : it.INDEXDESC;
		if (desc) {
			td.innerHTML = it.miName + '<br/><span style="font-style:italic;color:gray;font-size:80%;">' + desc + '</span>';
		} else {
			td.innerHTML = it.miName;
		}
		tr.appendChild(td);
		it.added=1;
		
		var len = getRowCount(it);
		if (len > 1) {
			td.rowSpan="" + len;
		}
	}
	
	if (its.length < maxLevel-1) {//最后一列最后指标值输入列
		for (var i=0; i<maxLevel-1-its.length; i++) {
			var td=document.createElement("td");
			td.innerHTML = "&nbsp;";
			tr.appendChild(td);
		}
	}
	var td=document.createElement("td");
	td.innerHTML = "&nbsp;";
	tr.appendChild(td);
	td.item=item;
}

function getToTopItemList(item,its) {
	if (!its) {
		its = [];
		its[its.length]=item;
	}
	while(item.pItem) {
		item=item.pItem;
		its[its.length]=item;
	}
	return its;
}

function getRowCount(item) {
	var len = 1;
	if (!item.children || item.children < 1) {
		return len;
	}
	len = 0;
	for (var i=0; i<item.children.length; i++) {
		len += getRowCount(item.children[i]);
	}
	return len;
}

function initItem(item,items,le) {
	for (var i=0; i<items.length;i++) {
		if (items[i].pMiId==item.miId) {
			items[i].pItem=item;
			if (!item.children) {
				item.children=[];
			}
			item.children[item.children.length]=items[i];
			if (le + 1 > maxLevel) {
				maxLevel = le + 1;
			}
			initItem(items[i],items,le+1);
		}
	}
}

function save() {
	var form = new nui.Form("dynTable");
	form.validate();
	if (!form.isValid()) {
		alert("请将数据填写完整！");
		return;
	}
	
	var data={};
	data.modelRe={"modelGrade":nui.get("totalGrade").getValue(),
		"tbPubModel":{"modelId":"<%=request.getParameter("itemId") %>"}
		};
	var json = nui.encode(data);
	nui.ajax({
                url: "com.bos.pub.model.model.addModelRe.biz.ext",
                type: 'POST',
                data: json,
                success: function (text) {
                	if (text.msg) {
                		alert(text.msg);
                		return;
                	}
                	saveItems(text.modelRe);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
	});
}

function saveItems(modelRe) {
	var arr=[];
	for (var i=0; i<300; i++) {
		//假设最多300个指标（叶子节点）
		var id="idx" + i + "_td";
		var td=document.getElementById(id);
		if (!td)
			continue;
		var item=td.item;
		var idx=td.idx;
		if (!item || !idx)
			continue;
		
		arr[arr.length]={"tbPubModelRe":{"reId":modelRe.reId},
			"indexId":idx.indexId,
			"itemId":item.miId,
			"itemValue":idx.iValue,
			"itemGrade":idx.iGrade
			};
	}
	
	var data={};
	data.modelReDetails=arr;
	var json = nui.encode(data);
	nui.ajax({
                url: "com.bos.pub.model.model.addModelReDetail.biz.ext",
                type: 'POST',
                data: json,
                success: function (text) {
                	if (text.msg) {
                		alert(text.msg);
                		return;
                	}
                	alert("保存成功");
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
	});
}
</script>
</body>
</html>