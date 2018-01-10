<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<!-- 
  - Author(s): jiangzhan
  - Date: 2016-05-09 11:39:30
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<div id="button_div" class="nui-toolbar" style="border-bottom: 0;">
		<a class="nui-button" iconCls="icon-add" onclick="add()">增加</a> <a
			class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a> <a
			class="nui-button" iconCls="icon-save" onclick="saveItem">保存</a>
		<!-- <a class="nui-button" onclick="search">刷新</a> -->
	</div>

	<div id="grid1" class="nui-datagrid" style="width: 100%; height: 90%;"
		url="com.bos.acc.accnfdsheet.getAccFinanceLrData.biz.ext"
		dataField="items" allowResize="true" showReloadButton="false"
		allowAlternating="true" multiSelect="false" sortMode="client"
		showPager="false" allowMoveColumn="false" allowCellEdit="true"
		allowCellSelect="true" editNextOnEnterKey="true"
		oncellmousedown="onCellBeginEdit">
		<div id="form" property="columns" style="height: auto;"
			class="nui-form">
			<div type="indexcolumn">序号</div>
			<div field="profitMonth" headerAlign="center" dateFormat="yyyy-MM-dd">
				月份 <input property="editor" style="width: 100%;"
					class="nui-datepicker nui-form-input"
					maxDate="<%=GitUtil.getBusiDateStr()%>" allowInput="false" />
			</div>
			<div field="profitZyywsr" headerAlign="center">
				主营业务收入 <input property="editor" class="nui-textbox"
					style="width: 100%;" />
			</div>
			<div field="profitZyywcb" headerAlign="center">
				主营业务成本 <input property="editor" class="nui-textbox"
					style="width: 100%;" />
			</div>
			<div field="profitJyfyhj" headerAlign="center">
				经营费用合计 <input property="editor" class="nui-textbox"
					style="width: 100%;" />
			</div>
			<div field="profitLwgz" headerAlign="center">
				劳务工资 <input property="editor" class="nui-textbox"
					style="width: 100%;" />
			</div>
			<div field="profitZj" headerAlign="center">
				租金 <input property="editor" class="nui-textbox" style="width: 100%;" />
			</div>
			<div field="profitSdf" headerAlign="center">
				水电费 <input property="editor" class="nui-textbox"
					style="width: 100%;" />
			</div>
			<div field="profitS" headerAlign="center">
				税 <input property="editor" class="nui-textbox" style="width: 100%;" />
			</div>
			<div field="profitQtjyfy" headerAlign="center">
				其他经营费用 <input property="editor" class="nui-textbox"
					style="width: 100%;" />
			</div>
			<div field="profitCwfy" headerAlign="center">
				财务费用 <input property="editor" class="nui-textbox"
					style="width: 100%;" />
			</div>
			<div field="profitJtkz" headerAlign="center">
				家庭开支 <input property="editor" class="nui-textbox"
					style="width: 100%;" />
			</div>
			<div field="profitTzxsr" headerAlign="center">
				投资性收入 <input property="editor" class="nui-textbox"
					style="width: 100%;" />
			</div>
			<div field="profitTzxzc" headerAlign="center">
				投资性支出 <input property="editor" class="nui-textbox"
					style="width: 100%;" />
			</div>
			<div field="profitQtyyjsr" headerAlign="center">
				其他营业净收入 <input property="editor" class="nui-textbox"
					style="width: 100%;" />
			</div>
			<div field="profitLrze" headerAlign="center">
				利润总额 <input property="editor" class="nui-textbox"
					style="width: 100%;" />
			</div>
		</div>
	</div>
	<script type="text/javascript">
var financeId="<%=request.getParameter("financeId") %>";//客户财务信息表ID
var reportType="<%=request.getParameter("reportType") %>";//财报类别
var sheetCode="<%=request.getParameter("sheetCode") %>";//子财报类型
var view="<%=request.getParameter("view") %>";//页面状态：查看1、修改0
nui.parse();
var grid = nui.get("grid1");
var form = new nui.Form("#grid1");

function search() {
   grid.load({"item":{"financeId":financeId,"reportType":reportType,"sheetCode":sheetCode}});
}
search();


if(view == '1'){//查看
	nui.get("button_div").hide();
	grid.setEnabled(false);
}	

function add() {
    var newRow = { name: "New Row" };
	grid.addRow(newRow, 0);
}
function remove() {
	grid.removeRow(grid.getSelected(), true);
}

function onCellBeginEdit(e) {
	var editor = grid.getCellEditor(e.column, e.record);
	if (e.field == 'funcname' && e.record.name != 'New Row') {
		editor.disable();
		return;
	}
	editor.enable();
}

function checkData(value,n){
	var flag = true;
	if(n==1){
		if(value){
			value = value+"";//拼接字符串是因为调用这个方法前经过了getItemData取值将value变成了浮点型需要转换成字符串类型
			if(!(value.match(/^[0-9]+\.{0,1}[0-9]{0,2}$/))){
				flag = false;
			}
		}
		return flag;
	}else{//验证日期
		var d = new Date(Date.parse(value.replace("-","/")));
		var year = d.getYear();
		var month = d.getMonth();
		var day = d.getDate();
		//获取当前月最后一天
		var t = new Date(year,month+1,0);
		if(day != t.getDate()){
			flag = false;
		}
		return flag;
	}
}
	
	function getItemData(value){
		if(value == null){
			return 0;
		}else{
			return value;		
		}
	}
	
	function checkItemData(obj,n){
		var profitMonth = obj.profitMonth;//月份
		if(profitMonth==null){
			return "第【"+(n+1)+"】行月份信息不能为空！";
		}
		if(!checkData(profitMonth,2)){
			return "第【"+(n+1)+"】行【月份】信息录入不正确，请录入指定月的最后一天！";
		} 
		
		var msg = "信息输入不正确，可以非必输，如果要输入请输入正确的数字，可以是整数或者浮点数，小数位最多两位!";
		var profitZyywsr = getItemData(obj.profitZyywsr);//主营业务收入
		if(!checkData(profitZyywsr,1)){
			return "第【"+(n+1)+"】行科目【主营业务收入】"+msg;
		}
		
        var profitZyywcb  = getItemData(obj.profitZyywcb);//主营业务成本
        if(!checkData(profitZyywcb,1)){
			return "第【"+(n+1)+"】行科目【主营业务成本】"+msg;
		}
        	
        var profitJyfyhj  = getItemData(obj.profitJyfyhj);//经营费用合计
        if(!checkData(profitJyfyhj,1)){
			return "第【"+(n+1)+"】行科目【经营费用合计】"+msg;
		}
       
        var profitLwgz  = getItemData(obj.profitLwgz);//劳务工资
         if(!checkData(profitLwgz,1)){
			return "第【"+(n+1)+"】行科目【劳务工资】"+msg;
		}
         
        var profitZj  = getItemData(obj.profitZj);//租金
         if(!checkData(profitZj,1)){
			return "第【"+(n+1)+"】行科目【租金】"+msg;
		}
        
        var profitSdf  = getItemData(obj.profitSdf);//水电费
         if(!checkData(profitSdf,1)){
			return "第【"+(n+1)+"】行科目【水电费】"+msg;
		}
        
        var profitS  = getItemData(obj.profitS);//税
         if(!checkData(profitS,1)){
			return "第【"+(n+1)+"】行科目【税】"+msg;
		}
         
        var profitQtjyfy  = getItemData(obj.profitQtjyfy);//其他经营费用
         if(!checkData(profitQtjyfy,1)){
			return "第【"+(n+1)+"】行科目【其他经营费用】"+msg;
		}
        
        var profitCwfy  = getItemData(obj.profitCwfy);//财务费用
         if(!checkData(profitCwfy,1)){
			return "第【"+(n+1)+"】行科目【财务费用】"+msg;
		}
        
        var profitJtkz  = getItemData(obj.profitJtkz);//家庭开支
         if(!checkData(profitJtkz,1)){
			return "第【"+(n+1)+"】行科目【家庭开支】"+msg;
		}
		
        var profitTzxsr  = getItemData(obj.profitTzxsr);//投资性收入
         if(!checkData(profitTzxsr,1)){
			return "第【"+(n+1)+"】行科目【投资性收入】"+msg;
		}
		
        var profitTzxzc  = getItemData(obj.profitTzxzc);//投资性支出
         if(!checkData(profitTzxzc,1)){
			return "第【"+(n+1)+"】行科目【投资性支出】"+msg;
		}
       
        var profitQtyyjsr = getItemData(obj.profitQtyyjsr);//其他营业净收入
         if(!checkData(profitQtyyjsr,1)){
			return "第【"+(n+1)+"】行科目【其他营业净收入】"+msg;
		}
        
       	var profitLrze  = getItemData(obj.profitLrze);//利润总额
       	 if(!checkData(profitLrze,1)){
			return "第【"+(n+1)+"】行科目【利润总额】"+msg;
		}
		
		var a = parseFloat(profitZyywsr)-parseFloat(profitZyywcb)-parseFloat(profitJyfyhj)-parseFloat(profitCwfy)-parseFloat(profitJtkz)+parseFloat(profitTzxsr)-parseFloat(profitTzxzc)+parseFloat(profitQtyyjsr);
		var b = parseFloat(profitLrze);
		if(a!=b){
            return "第"+(n+1)+"行触发平衡校验：请检查本期值是否满足平衡公式【利润总额＝（主营业务收入－主营业务成本－经营费用合计－财务费用-家庭开支）+（投资性收入-投资性支出）+其他业务净收入】";
        }
            	
        if(parseFloat(profitJyfyhj)!=(parseFloat(profitLwgz)+parseFloat(profitZj)+parseFloat(profitSdf)+parseFloat(profitS)+parseFloat(profitQtjyfy))){
            return "第"+(n+1)+"行触发平衡校验：请检查本期值是否满足平衡公式【经营费用合计=劳务工资+租金+水电费+税+其他经营费用】";
        }
	}
	
	function getDeadLine(value){
		var d = new Date(Date.parse(value.replace("-","/")));
		var str = d.getYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
		return str;
	}

	function saveItem() {
		var g=grid;
		var datas = g.getData();
		if(datas.length<=0){
			alert("请先添加财报信息！");
			return ;
		}
		
		for(var i=0;i<datas.length;i++) {
			var msg = checkItemData(datas[i],i);
	        if(msg){
	        	alert(msg);
	        	return;
	        }
	        datas[i].financeDeadline = getDeadLine(datas[i].profitMonth);
		}
		nui.confirm("如果财报截止日期（年份、月份）重复，后一条财报信息将会覆盖前面重复财报信息，确定要保存吗？","确认",function(action){
            	if(action!="ok") return;
            	var data = {items:datas,"paramObject":{"financeId":financeId,"reportType":reportType,"sheetCode":sheetCode}};
	    		var json = nui.encode(data);
                $.ajax({
                     url: "com.bos.acc.accnfdsheet.saveAccCopfinanceSheetLr.biz.ext",
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

	</script>
</body>
</html>