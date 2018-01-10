<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s):lujinbin
  - Date: 2013-12-10 16:17:00
  - Description:
-->
<head>
<title>查询计分卡项目事项</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<br/>
<div align="center">
<div id="form1" style="width:50%;height:auto;overflow:hidden;" >
		<div class="nui-dynpanel" columns="2">
			<label>项目名称：</label>
		<input id="projectName" name="tbMatterBaseMessage.scoreMessageId" required="false"  class="nui-buttonEdit" onbuttonclick="selectProject" />
			
		</div>
</div>
</div>
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button"   onclick="query()">查询</a>
    <a class="nui-button"   onclick="CloseWindow()">返回</a>
</div>


<br />


<div id="datagrid1"    class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.pub.scoreMatter.getTbMatterBaseMessageList.biz.ext" dataField="tbMatterBaseMessages"
	    allowAlternating="true" multiSelect="false"  showReloadButton="false"
	    sizeList="[10,20,50,100]">
	    <div property="columns">
	        <div type="checkcolumn">选择</div>
	      <div field="scoreMatter" headerAlign="center" allowSort="true" >计分事项</div>
	     </div>
	</div>
<div id="dataConfirm"  class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button"  onclick="selected()">选中</a>
</div>


<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form1");
	var grid = nui.get("datagrid1");
	
	nui.get("datagrid1").hide();
	nui.get("dataConfirm").hide();
	
function selectProject(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/openOrder/selectProjectMatter.jsp",
            showMaxButton: true,
            title: "选择计分项目",
            width: 750,
            height: 450,
            ondestroy: function (action) {            
                if (action =="ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.getData();
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.projectNumber);
                        btnEdit.setText(data.projectName);
                    }
                }
            }
        });            
    }
    
    function query(){
 
    	form.validate();
        if (form.isValid()==false) return;
        
       var o = form.getData();
       
       if(nui.get("projectName").getValue()==""){
          alert("请选择查询条件");
          return;
       }else{
       		 grid.load ( o, function (text) {
            	if(text.msg){
            		nui.alert(text.msg);
            	} else {
            		//alert("123");
            		nui.get("datagrid1").show();
					nui.get("dataConfirm").show();
					
            	}
            } );
       }
    
    }
    
    function selected() {
      var row = grid.getSelected();
        if (row) {
            CloseWindow("ok");
        } else {
            alert("请选中一条记录");
        }
    } 
    
    function getData(){
    var row = grid.getSelected();
      if (row) {
            return row;
        } else {
            return null;
        }
    }
    function getProjectName(){
     var projectName;
      projectName=nui.get("projectName").getText();
      return projectName;
    }
    
    
  </script>
  
  
</body>
</html>