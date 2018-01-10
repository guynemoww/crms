<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.eos.data.datacontext.UserObject"%>
<%@page import="commonj.sdo.DataObject"%>
<%@page import="java.util.Map"%>
<%@page import="commonj.sdo.DataObject"%>
<html xmlns="http://www.w3.org/1999/xhtml"> <html> <!-- 
  - Author(s): jiangzhan
  - Date: 2016-05-09 11:39:30
  - Description:
--> <head> <%@include file="/common/nui/common.jsp"%>
</head> <body> <div id="button_div" class="nui-toolbar"
	style="border-bottom:0;"> <a class="nui-button"
	iconCls="icon-add" onclick="add()">增加</a> <a class="nui-button"
	iconCls="icon-remove" onclick="remove()">删除</a> <a class="nui-button"
	iconCls="icon-save" onclick="saveItem()">保存</a> </div> <div id="account_div"
	class="nui-toolbar" style="border-bottom:0;"> <input
	id="billAccInfo.billAccountId" name="billAccInfo.billAccountId"
	class="nui-hidden nui-form-input" /> 开户行：<input
	name="billAccInfo.billKhh" id="billAccInfo.billKhh"
	class="nui-textbox nui-form-input" vtype="maxLength:80"
	style="width:25%;margin-right:18px;" /> 账号名称：<input
	name="billAccInfo.billZhmc" id="billAccInfo.billZhmc"
	class="nui-textbox nui-form-input" vtype="maxLength:80"
	style="width:25%;margin-right:18px;" /> 账号：<input
	name="billAccInfo.billZh" id="billAccInfo.billZh"
	class="nui-textbox nui-form-input" vtype="maxLength:30"
	style="width:25%;margin-right:18px;" /> </div> <div id="grid1"
	class="nui-datagrid" style="width:100%;height:auto;"
	url="com.bos.acc.accnfdsheet.getAccFinanceBillData.biz.ext"
	dataField="items" allowResize="true" showReloadButton="false"
	showPager="false" multiSelect="false" sortMode="client"
	allowAlternating="true" allowMoveColumn="false" allowCellEdit="true"
	allowCellSelect="true" editNextOnEnterKey="true"
	oncellmousedown="onCellBeginEdit"> <div id="dataDiv"
	property="columns" style="height:auto;"> <div type="indexcolumn">序号</div>
<div field="billYear" headerAlign="center">年份 <input
	property="editor" class="nui-combobox" data="billYear"
	allowInput="false" style="width:100%;" /> </div> <div field="billMonth"
	headerAlign="center">月份 <input property="editor"
	class="nui-combobox" data="billMonth" allowInput="false"
	style="width:100%;" /> </div> <div header="存入" headerAlign="center"> <div
	property="columns"> <div field="billInCash" headerAlign="center">金额
<input property="editor" class="nui-textbox" style="width:100%;" /> </div> <div
	field="billInCount" headerAlign="center">次数 <input
	property="editor" class="nui-textbox" style="width:100%;" /> </div> </div> </div> <div
	header="支出" headerAlign="center"> <div property="columns">
<div field="billOutCash" headerAlign="center">金额 <input
	property="editor" class="nui-textbox" style="width:100%;" /> </div> <div
	field="billOutCount" headerAlign="center">次数 <input
	property="editor" class="nui-textbox" style="width:100%;" /> </div> </div> </div> <div
	field="billMonthRest" headerAlign="center">月末余额 <input
	property="editor" class="nui-textbox" style="width:100%;" /> </div> </div> </div> <script
	type="text/javascript">
var financeId="<%=request.getParameter("financeId") %>";//客户财务信息表ID
var reportType="<%=request.getParameter("reportType") %>";//财报类别
var sheetCode="<%=request.getParameter("sheetCode") %>";//子财报类型
var view="<%=request.getParameter("view") %>";//页面状态：查看1、修改0
nui.parse();
var grid = nui.get("grid1");

function search() {
   grid.load({"item":{"financeId":financeId,"reportType":reportType,"sheetCode":sheetCode}});
}
search();

	//初始化页面,设置银行账户信息
    $(document).ready(function(){
		var json = nui.encode({"financeId":financeId});    	
    	var url = "com.bos.acc.accnfdsheet.getAccFinanceBillAccount.biz.ext";
        git.exeAjax(url,json,function(text){
    		if(text.billAccInfo != null){
    			nui.get("billAccInfo.billKhh").setValue(text.billAccInfo.billKhh);
    			nui.get("billAccInfo.billZhmc").setValue(text.billAccInfo.billZhmc);
    			nui.get("billAccInfo.billZh").setValue(text.billAccInfo.billZh);
    			nui.get("billAccInfo.billAccountId").setValue(text.billAccInfo.billAccountId);
    		}
    	}); 
    	
	}); 

    
if(view == '1'){//查看
	nui.get("button_div").hide();
	grid.setEnabled(false);
	nui.get("account_div").setEnabled(false);
}

function add() {
    var newRow = { name: "New Row" };
	grid.addRow(newRow, 0);
}
function remove() {
	var row = grid.getSelected();
	if(row.billYear!="合计数"&&row.billYear!="平均数"){
		grid.removeRow(row, true);
	}
}

function onCellBeginEdit(e) {
	var billYear = e.row.billYear;
	var editor = grid.getCellEditor(e.column, e.record);
	if(billYear=="合计数"||billYear=="平均数"){
		editor.disable();
		return;
	}else{
		editor.enable();
	} 
}
var date = new Date();
var year = date.getYear();

var billYear = [{id:year,text:year},{id:(year-1),text:(year-1)},{id:(year-2),text:(year-2)}];
var billMonth = [{id:'01',text:'1'},{id:'02',text:'2'},{id:'03',text:'3'},{id:'04',text:'4'},
{id:'05',text:'5'},{id:'06',text:'6'},{id:'07',text:'7'},{id:'08',text:'8'},{id:'09',text:'9'},
{id:'10',text:'10'},{id:'11',text:'11'},{id:'12',text:'12'}];

//校验金额数据
function checkData(value,n){
	if(value){
		if(n==1){
			//var reg = new RegExp("^[0-9]*$");//数字
			if(!value.match(/^[0-9]+\.{0,1}[0-9]{0,2}$/)){//  str.matches("^[0-9]+\.{0,1}[0-9]{0,2}$")
				return "的值输入不正确，可以非必输，如果要输入数据请输入正确的数字，可以是整数或者浮点数,小数位最多两位!";
			}
		}else{
			if(!value.match(/^(-|\+)?\d+$/)){
				return "的值输入不正确，可以非必输，如果要输入数据请输入正确的整数！";
			}
		}
	}
}

	//校验年份和月份信息
	function checkDeadLine(obj,n){
		var billYear = obj.billYear;
		var billMonth = obj.billMonth;
		if(billYear==null || billMonth==null){
			return "第【"+(n+1)+"】行财报信息录入不完整，年份、月份(财报截止时间)信息必须录入！"
		}
	}

	//获取财报截止时间
	function getDeadLine(year,month){
		//获取当前月最后一天
		var t = new Date(year,parseFloat(month),0);
		var str = t.getYear()+"-"+(t.getMonth()+1)+"-"+t.getDate();
		return str;
	}
	
	function checkItemData(e,n){
		var str = "";
		
		//校验年份和月份信息
		str = checkDeadLine(e,n);
		if(str){
			return str;
		}
		
		//存入金额
		var billInCash = e.billInCash;
		str = checkData(billInCash,1);
		if(str){
			return "第【"+(n+1)+"】行科目【存入金额】"+str;
		}
		
		//存入次数
		var billInCount = e.billInCount;
		str = checkData(billInCount,2);
		if(str){
			return "第【"+(n+1)+"】行科目【存入次数】"+str;
		}
		 
		//支出金额
		var billOutCash = e.billOutCash;
		str = checkData(billOutCash,1);
		if(str){
			return "第【"+(n+1)+"】行科目【支出金额】"+str;
		}
		
		//支出次数
		var billOutCount = e.billOutCount;
		str = checkData(billOutCount,2);
		if(str){
			return "第【"+(n+1)+"】行科目【支出次数】"+str;
		}
		
		//月末余额
		var billMonthRest = e.billMonthRest;
		str = checkData(billMonthRest,1);
		if(str){
			return "第【"+(n+1)+"】行科目【月末余额】"+str;
		}
	}
	
	function saveItem() {
			var g=grid;
			var msg = "";
			var datas = g.getData();
			if(datas.length<=2){
				alert("请先添加财报信息！");
				return ;
			}
			/* var rows = g.getChanges();
			if(rows.length<=0){
				alert("未做任何修改");
				return ;
			} */
			
			for(var i = 0;i<datas.length-2;i++){
				msg = checkItemData(datas[i],i);
				if(msg){
					alert(msg);
					return;
				}
				datas[i].financeDeadline = getDeadLine(datas[i].billYear,datas[i].billMonth);
			}
			
			var billKhh = nui.get("billAccInfo.billKhh").getValue();
			var billZhmc = nui.get("billAccInfo.billZhmc").getValue();
			var billZh = nui.get("billAccInfo.billZh").getValue();
			msg = checkData(billZh,2);
			if(msg){
				alert("账号信息输入有误，可以非必输，如果要输入数据请输入正确的数字！");
				return;
			}
            
            nui.confirm("如果财报截止日期（年份、月份）重复，后一条财报信息将会覆盖前面重复财报信息，确定要保存吗？","确认",function(action){
            	if(action!="ok") return;
            	var billAccountId = nui.get("billAccInfo.billAccountId").getValue();
            	var data = {items:g.getData(),"paramObject":{"financeId":financeId,"reportType":reportType,"sheetCode":sheetCode,"billKhh":billKhh,"billZhmc":billZhmc,"billZh":billZh,"billAccountId":billAccountId}};
            	var json = nui.encode(data);
                $.ajax({
                    url: "com.bos.acc.accnfdsheet.saveAccCopfinanceSheetBill.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	if (text.msg) {
							nui.alert(text.msg);
							nui.parse();
							search();
						} else {
							nui.alert("保存成功！");
							nui.parse();
							search();
						}
                    },
                    error: function () {
                    	alert(jqXHR.responseText);
                    }
              });
        });
	}

	</script> </body> </html>