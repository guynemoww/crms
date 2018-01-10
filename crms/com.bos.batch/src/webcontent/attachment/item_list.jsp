<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-05-05
  - Description:TB_BATCH_DATALIST_ATTACHMENT, com.bos.batch.DB.TbBatchDatalistAttachment
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>

<body>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;">
<div title="借据清单" >
<center>
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">
	
	<div class="nui-dynpanel" columns="6">
		<label>数据起期：</label>
		<input name="map/startDate" required="false" 
		 class="nui-datepicker" vtype="maxLength:20"  format="yyyy-MM-dd" onvaluechanged="startDatevalueChange" id="startDate"/>
		 
		<label>数据截期：</label>
		<input name="map/endDate" required="false" 
		 class="nui-datepicker" vtype="maxLength:20" format="yyyy-MM-dd" onvaluechanged="endDatevalueChange" id="endDate"/>
	</div>
<div class="nui-toolbar" style="text-align:right;border:none">
    <a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button" style="margin-right:23px;height:21px" iconCls="icon-reset" onclick="reset()">重置</a>
</div>
</div>
				
<div style="width:99.5%">				
	<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
		<a class="nui-button"  onclick="downloadFile()">下载清单</a>
	</div>
</div>	    

<iframe name="downloadFileFrame" id="downloadFileFrame" src="" style="display:none;"></iframe>	    
<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto;" 
	url="com.bos.batch.datalistAttachment.getTbBatchDatalistAttachmentList.biz.ext"
	dataField="tbBatchDatalistAttachments" allowAlternating="true" 
	allowResize="true" showReloadButton="false"
	sizeList="[10,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" width="3%">选择</div>
	    <div field="inputdate" headerAlign="center" allowSort="true" width="20%">数据日期</div>
		<div field="datalisttype" headerAlign="center" allowSort="true" dictTypeId="XD_RZCD0010" width="20%">数据清单类型</div>
		<div field="filename" headerAlign="center" allowSort="true" width="30%">文件名</div>
        <div field="contentlength" headerAlign="center" allowSort="true" width="20%">文件长度</div>
		</div>
	</div>
	</center>
	</div>
	</div>		
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	
    function search() {
       
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    }
    search();
    
    function reset(){
		form.reset();
	}

    
    function download(){
     var row = grid.getSelected();
     
      if (row) {
          	var json=nui.encode(
            	{"datalistpath":row.datalistpath,
            	 "filename":row.filename
            	 }
            	 );
            	  $.ajax({
                     url: "com.bos.batch.datalistAttachment.downloadInventory.biz.ext2",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function () {
                      
                    },
                    error: function () {
                    	nui.alert("操作失败！");
                    }
                });
        } else {
          alert("请选中一条记录");
        }
     
    }
    
       /**
		 * 出票日期值发生改变
		 */
		function startDatevalueChange(){
			var startDate=nui.get("startDate").getValue();//开始日期
			var endDate=nui.get("endDate").getValue();//结束日期
			if(endDate!=""){
				if(!CompareStartAndendDate()){//验证不通过
					nui.alert("开始日期必须小于或等于数据截止日期！");
					nui.get("startDate").setValue("");
				}
			}
		}
			
		/**
		 * 到期日期值发生改变
		 */
		function endDatevalueChange(){
				 	var startDate=nui.get("startDate").getValue();//开始日期
			var endDate=nui.get("endDate").getValue();//结束日期
		  	if(startDate!=""){
		  		if(!CompareStartAndendDate()){//验证不通过
		  			nui.alert("开始日期必须小于或等于数据截止日期！");
		  			nui.get("endDate").setValue("");
		  		}
		  	}	
		}
		
		/**
		 * 比较开始日期和结束日期
		 */
		function CompareStartAndendDate(){
		 	var startDate=nui.get("startDate").getValue();//开始日期
			var endDate=nui.get("endDate").getValue();//结束日期
		  	if(nui.parseDate(startDate)-nui.parseDate(endDate)>0){//开始日期大于结束日期
		  		return false;
		  	}else{
		  		return true;
			}
		}
   
   
    
    function remove() {
        var row = grid.getSelected();
        
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	
            	var json=nui.encode(
            	{"tbBatchDatalistAttachment":
            	 {"belongorg":
            		row.belongorg,version:row.version
            		}
            		}
            	 );
                $.ajax({
                     url: "com.bos.pub.crud.delTbBatchDatalistAttachment.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	if (text.msg) {
                    		nui.alert(text.msg);
                    		return;
                    	}
                        search();
                    },
                    error: function () {
                    	nui.alert("操作失败！");
                    }
                });
            }); 
        } else {
            alert("请选中一条记录");
        }
    }
    
   	function downloadFile() {
     	 var row = grid.getSelected();
   	    if (row) {
   	   
		var ifrm = document.getElementById("downloadFileFrame");
	
	
        ifrm.src="com.bos.batch.datalistAttachment.downloadInventory.biz.ext2?filename="+row.filename+"&datalistpath="+row.datalistpath;
        }
        else{
          alert("请选择一条记录");
        }
    }
   
    
	</script>
</body>
</html>
