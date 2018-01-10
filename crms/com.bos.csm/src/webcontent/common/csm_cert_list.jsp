<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-11-28
  - Description:TB_CSM_ADDRESS, com.bos.dataset.csm.TbCsmAddress
-->
<head>
<%@include file="/common/nui/common.jsp" %>
<script type="text/javascript" src="<%=contextPath%>/csm/js/csmValidate.js"></script>
</head>
<body>
<center>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="item.partyId" id="item.partyId" class="nui-hidden" />
	
</div>
 <div style="width:99.5%;">
		<div   class="nui-toolbar" style="border-bottom:0;text-align:left">
			<a id = "add" class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
			<a id = "save" class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
			<a id = "remove" class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
		</div>
	</div>   
<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto" 
	url="com.bos.csm.common.certInfo.getCertList.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false" allowCellEdit="true" allowCellSelect="true"
	sizeList="[10,15,20,50,50,100]" multiSelect="true" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="certificateTypeCd" headerAlign="center" allowSort="true" oncellbeginedit="oncellbeginedit" dictTypeId="CDKH0002" >证件类型
			<input property="editor" required="true" class="nui-dictcombobox" valueField="dictID" textField="dictName" data="getDictDatas()"/>
		</div>
		<div field="certificateCode" headerAlign="center" allowSort="true" >证件号码
			<input property="editor" required="true" class="nui-textbox nui-form-input" />
		</div>
		<div field="signDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd" >签发日期
			<input property="editor" required="true"  class="nui-datepicker" />
		</div>
		<div field="endDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">到期日期
			<input property="editor" required="true" class="nui-datepicker" />
		</div>
	</div>
</div>
</center>
<script type="text/javascript">
 	nui.parse();
 	git.mask();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	var partyId = "<%=request.getParameter("partyId") %>";
	var qote = "<%=request.getParameter("qote")%>" ;
	var cType = "<%=request.getParameter("cType")%>" ;
	
	if(qote==1){
	   nui.get("add").hide();
	   nui.get("save").hide();
	   nui.get("remove").hide();
	}	
    function search() {
		if (partyId) {
			nui.get("item.partyId").setValue(partyId);
		}
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
        git.unmask();
    }
	
	function getDictDatas(){
    	if("<%=request.getParameter("cType") %>"=="1"){
    		data = getDictData('CDKH0002','str','10100,11101,11102,11300,10400,99999');
	    }else{
	    	data = getDictData('CDKH0002','str','20001,20101,20102,21300,21310,99999');
	    }
	    return data;
    }
	
    search();
    var certData;
     function init(){
	 		var cType = "<%=request.getParameter("cType") %>";
	 		var json = null;
	 		if("1"==cType){
	 		   json = nui.encode({parentId:"10000"});
	 		}else{
	 		   json = nui.encode({parentId:"20000"});
	 		}
	        
	         $.ajax({
	            url: "com.bos.csm.pub.getDict.getCertTypeDict.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	                //nui.get("certType").setData(text.levels);
	                certData = text.levels;
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
		     });
	      }
     //init();
    
    function add() {            
            var row = { name: "New Row" , partyId: partyId }
            grid.addRow(row, 0);
        }
        
    function save() {
      var rows = grid.getSelected();
      if(!rows){
      	alert("请选择要保存的内容");
      	return;
      }
      var json = nui.encode({"rows":rows});
      $.ajax({
	            url: "com.bos.csm.common.certInfo.saveAndUpdateCert.biz.ext",
	            type: 'POST',
	            data: json,
	            cache: false,
	            contentType:'text/json',
	            success: function (text) {
	            	if(text.msg){
	            		alert(text.mag);
	            	}else{
	           			alert("保存成功"); 		
	            	}
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	                nui.alert(jqXHR.responseText);
	            }
		     });
      
    }
    
    function remove() {
        var row = grid.getSelected();
        
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"itemId": row.certificateId});
                $.ajax({
                     url: "com.bos.csm.common.certInfo.delCert.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	if (text.msg) {
                    		nui.alert(text.msg);
                    		return;
                    	}
                    	git.mask();
                        grid.reload();
                        git.unmask();
                    },
                    error: function () {
                    	nui.alert("操作失败！");
                    }
                });
            }); 
        } else {
            nui.alert("请选中一条记录");
        }
    }
    
    //证件类型不可修改
	function oncellbeginedit(e) {
		var state = e.record._state;
		if(state==null || state=="") {
			if(e.value!="") {
				e.cancel=true;
			}
		}
	}
</script>
</body>
</html>
