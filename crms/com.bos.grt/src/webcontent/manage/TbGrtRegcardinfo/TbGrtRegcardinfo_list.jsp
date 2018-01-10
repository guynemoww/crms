<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-03-31
  - Description:TB_GRT_REGCARDINFO, com.bos.dataset.grt.TbGrtRegcardinfo
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-add" onclick="add()" id="addButton">增加</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(0)" id="editButton1">编辑</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)" id="editButton2">查看</a>
	<a class="nui-button" iconCls="icon-remove" onclick="remove()" id="removeButton">删除</a>
</div>
	    
<div id="grid5" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.grt.manage.TbGrtRegcardinfo.getTbGrtRegcardinfoList.biz.ext"
	dataField="tbGrtRegcardinfos"
	allowResize="false" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="registerCertiNo" headerAlign="center" allowSort="true" >登记权证编号</div>
		<div field="regOrgName" headerAlign="center" allowSort="true" >登记机构名称</div>
		<div field="regOrgMoney" headerAlign="center" allowSort="true" >登记金额</div>
		<div field="cardRegDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd" >登记生效日期</div>
		<div field="regDueDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd" >登记到期日期</div>
       	<div field="saveOrg" headerAlign="center" allowSort="true" dictTypeId="org" >保管机构</div>
   </div>
</div>
			
<script type="text/javascript">
 	nui.parse();
 	
 	//押品主键ID
	var suretyId ="<%=request.getParameter("suretyId")%>";
 	
	var grid = nui.get("grid5");
	if ("<%=request.getParameter("view") %>"=="1") {
		nui.get("addButton").hide();
		nui.get("editButton1").hide();
		nui.get("removeButton").hide();
	}
	
    function search() {
    	var json=({"tbGrtRegcardinfo":{"suretyId":suretyId}});
        grid.load(json);
    }
    search();
    
    function reset(){
		form.reset();
	}
	
    function add() {
    	var suretyId= '<%=request.getParameter("suretyId") %>';
        nui.open({																				
            url: nui.context+"/grt/manage/TbGrtRegcardinfo/TbGrtRegcardinfo_add.jsp?suretyId="+suretyId,
            title: "新增", 
            width: 800, 
        	height: 500,
        	allowResize: false,
        	showMaxButton: true,
            ondestroy: function (action) {
                if(action=="ok"){
                    search();
                }
            }
        });
    }
    
    function edit(v) {
        var row = grid.getSelected();
        var title1;
        if(v == "0"){
        	title1 = "编辑";
        }else if(v == "1"){
        	title1 = "查看";
        }
        if (row) {
            nui.open({
                url:nui.context+ "/grt/manage/TbGrtRegcardinfo/TbGrtRegcardinfo_add.jsp?suretyKeyId="+row.suretyKeyId+"&view="+v+"&suretyId="+row.suretyId,
                title: title1, 
                width: 800,
        		height: 500,
                allowResize: false,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                    //iframe.contentWindow.SetData(data);
                    grid.reload();
                },
                ondestroy: function (action) {
                    if(action=="ok"){
                        search();
               	 	}
                }
            }); 
        } else {
            alert("请选中一条记录");
        }
    }
    
    function remove() {
        var row = grid.getSelected();

        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"tbGrtRegcardinfo":{"suretyKeyId":row.suretyKeyId}});
            	git.mask();
                $.ajax({
                    url: "com.bos.grt.manage.TbGrtRegcardinfo.delTbGrtRegcardinfo.biz.ext",
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
                        git.unmask();
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

	</script>
</body>
</html>

