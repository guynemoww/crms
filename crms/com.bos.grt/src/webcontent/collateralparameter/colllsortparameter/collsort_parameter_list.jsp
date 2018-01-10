<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-11-22
  - Description:TB_CSM_APTITUDE_INFO, com.bos.dataset.csm.TbCsmAptitudeInfo
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
   <div id="form1" style="width:100%;height:auto;overflow:hidden;">
	 <input name="sortType" id="sortType" class="nui-hidden" />
   </div>
   <div class="nui-toolbar" style="border-bottom:0;">
	  <a class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
	  <a class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
	  <a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
	  <a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
   </div>
   
   <div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	   url="com.bos.grt.collateralparameter.collsortparameters.querySortParameterBysortType.biz.ext" dataField="tbGrtSortargumentss"
	   allowResize="true" showReloadButton="false"
	   sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	   <div property="columns">
		  <div type="checkcolumn" field="sortType">选择</div>
		  <div field="sortName" headerAlign="center" allowSort="true" >押品分类名称</div>
		  <div field="parentSortType" headerAlign="center" allowSort="true" dictTypeId="XD_KHCD0036">押品分类编号父节点</div>
		  <div field="hightMpRate" headerAlign="center" allowSort="true" >最高抵质押率(%)</div>
		  <div field="assessCycle" headerAlign="center" allowSort="true" >重估频率</div>
		  <div field="createDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">创建日期</div>
		  <div field="updateDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd">更新日期</div>
	  </div>
  </div>  
    
   <script type="text/javascript">
   	  nui.parse();
   	  var form = new nui.Form("#form1");
   	  var grid = nui.get("grid1");
   	  var sortType = "<%=request.getParameter("sortType")%>";
   	  var parentSortType = "<%=request.getParameter("parentSortType")%>";
   	  
   	  function search() {
   	    var data =null;
		if (sortType) {
		   nui.get("sortType").setValue(sortType);
		}else{
		  return;
		}
	    var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
     }
     search();
     
     
     //添加分类
     function add() {
        nui.open({
            url: nui.context + "/collateralparameter/colllsortparameter/collsort_parameter_add.jsp?sortType="+sortType,
            title: "新增", 
            width: 800, 
        	height: 500,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
                if(action=="ok"){
                    grid.reload();
                }
            }
        });
    }
    
    //编辑
     function edit(v) {
        var row = grid.getSelected();
        if (row) {
            nui.open({
                url: nui.context + "/collateralparameter/colllsortparameter/collsort_parameter_edit.jsp?sortType="+row.sortType+"&view="+v,
                title: "编辑", 
                width: 800,
        		height: 500,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                    //iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                    if(action=="ok"){
                        grid.reload();
               	 	}
                }
            });
        } else {
            alert("请选中一条记录");
        }
     }
     
     //删除操作
     function remove(){
     	var row = grid.getSelected();
     	if(row){
     	   nui.confirm("确定删除吗？","确认",function(action){
     		 if(action!="ok")  return;
     		 var json = nui.encode({"tbGrtSortarguments":{"sortType":row.sortType}});
     		 $.ajax({
	              url: "com.bos.grt.collateralparameter.collsortparameters.deleteSortParameters.biz.ext",
		          type: 'POST',
		          data: json,
		          cache: false,
		          contentType:'text/json',
	              success: function (text) {
	                  if (text.msg) {
	                       nui.alert(text.msg);
	                       return;
	                    }
	                   grid.reload();
	                },
	              error: function () {
	                  nui.alert("操作失败！");
	                }
	          }); 
     	});}else{
     		nui.alert("请选中一条记录");
     	} 
     }
   </script>
   
</body>
</html>