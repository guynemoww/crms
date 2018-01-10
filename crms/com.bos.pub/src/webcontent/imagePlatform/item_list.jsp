<%@page pageEncoding="UTF-8"%>
<html>
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;"  >
		<input  id="item.businessNumber" name="item.businessNumber" class="nui-hidden" value="<%=request.getParameter("businessNumber")%>" />
		<input  id="item.partyNum" name="item.partyNum" class="nui-hidden" value="<%=request.getParameter("csmNum")%>" />
		<input  id="item.partyTypeCd" name="item.partyTypeCd" class="nui-hidden" value="<%=request.getParameter("partyTypeCd")%>" />
		<input  id="item.imageTypeId" name="item.imageTypeId" class="nui-hidden" value="<%=request.getParameter("imageTypeId")%>" />
		<input  id="item.imageTypeName" name="item.imageTypeName" class="nui-hidden" value="<%=request.getParameter("imageTypeName")%>" />
		<input  id="item.imageControlType" name="item.imageControlType" class="nui-hidden" value="<%=request.getParameter("imageControlType")%>" />
		<input  id="item.ismove" name="item.ismove" class="nui-hidden" value="<%=request.getParameter("ismove")%>" />
		<input  id="item.tranDate" name="item.tranDate" class="nui-hidden" />
		<input  id="item.barCode" name="item.barCode" class="nui-hidden" />
	    <input  id="item.superiorId" name="item.superiorId" class="nui-hidden" />
		<input  id="item.businessStage" name="item.businessStage" class="nui-hidden" value="1"/>
		<input  id="nodeFlag" name="nodeFlag" class="nui-hidden" value="<%=request.getParameter("nodeFlag")%>" />
		<div class="nui-dynpanel" columns="4">
			<label>文档名称：</label>
			<input  name="item.imageDocumentName" class="nui-textbox nui-form-input"  />
		</div>
		
		<div class="nui-toolbar" style="text-align:right;border:none" >
		    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
			<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
</div>
<div>
	<div class="nui-toolbar" style="width:99.5% border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
		<%--<a class="nui-button"  onclick="add()" id="add">增加</a>
		<!-- <a class="nui-button"  onclick="edit()" id="edit">编辑</a> -->
		<a class="nui-button" onclick="remove()" id="remove">删除</a>
		<span class="separator"></span>--%>
		<a class="nui-button"  iconCls="icon-upload"onclick="sunScan(0)" id="pict">上传影像资料</a>
		<a class="nui-button"  iconCls="icon-zoomin" onclick="sunScan(1)" id="viewScan">查看影像资料</a>
	</div>
</div>
<div id="grid1" class="nui-datagrid" 
	 allowAlternating="true" showReloadButton="false" sizeList="[10,20,50,100]"
	url="com.bos.pub.image.getImageDocumentList.biz.ext" dataField="items"
	allowResize="true" 	 multiSelect="false" pageSize="10" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<!-- <div field="serialNo" headerAlign="center" allowSort="true" >批次号</div>  -->
		<div field="imageDocumentName" headerAlign="center" allowSort="true" >影像文档名称</div>
		<!-- <div field="imageDocumentDesc" headerAlign="center" allowSort="true" >影像文档描述</div> -->
		<div field="partyNum" headerAlign="center" allowSort="true" >客户号</div> 
		<div field="businessNumber" headerAlign="center" allowSort="true" >业务编号</div> 
		<!-- <div field="pageNum" headerAlign="center" allowSort="true" >影像张数</div>  -->
		<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">创建人</div>
		<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org">创建机构</div>
		<div field="createTime" headerAlign="center" allowSort="true"  dateFormat="yyyy-MM-dd">创建时间</div>
	</div>
</div>
<script type="text/javascript">
		nui.parse();
		var form = new nui.Form("#form1"); 
		var grid = nui.get("grid1");
		var qote = "<%=request.getParameter("view")%>";
		var postCd="<%=com.bos.pub.GitUtil.getCurrentPositionCode()%>";
		if(qote == '1'){
			//nui.get("add").hide();
			//nui.get("edit").hide();
			//nui.get("remove").hide();
			nui.get("pict").hide();
		}
		
		function search() {
			var nodeFlag = nui.get("nodeFlag").getValue();
			if(nodeFlag == 'true'){
			
				var data = form.getData(); //获取表单多个控件的数据
			    grid.load(data);
			    grid.on("preload",function(e){
		       		if (!e.data || e.data.length < 1){
		       			return;
		       		}else{
		       		
		       			nui.get("item.tranDate").setValue(e.data[0].createTime);
		       			nui.get("item.barCode").setValue(e.data[0].barCode);
		       		}
		       		
	       		});
			    
			}
		}
		search();
		
		function reset(){
			form.reset();
		}
		
		function getData(){
	  	  var data=nui.encode(grid.getSelected());
	      return data;
	    }
		
		//新增一个文档子目录
		function add() {
        	
        	var imageTypeId = nui.get("item.imageTypeId").getValue();
        	var businessNumber = nui.get("item.businessNumber").getValue();
        	var partyNum = nui.get("item.partyNum").getValue();
        	var partyTypeCd = nui.get("item.partyTypeCd").getValue();
        	var nodeFlag = nui.get("nodeFlag").getValue();
 			var url =nui.context + "/pub/imagePlatform/item_add.jsp?superiorId="+imageTypeId+"&businessNumber="+businessNumber
 			+"&partyNum="+partyNum+"&partyTypeCd="+partyTypeCd;
 			url = encodeURI(encodeURI(url));
        	if(nodeFlag!="true"){
        		alert("请选择叶子节点添加影像信息")
        		return;
        	}
            nui.open({
               	url:url,
                title: "新增", 
                width: 600, 
            	height: 300,
            	allowResize:true,
            	showMaxButton: true,
                ondestroy: function (action) {
                    if(action=="ok"){
                        grid.reload();
                    }
                }
            });
        }
        //编辑目录中的影像
        function edit() {
            var row = grid.getSelected();
            if (row) {
               var url = nui.context + "/pub/imagePlatform/item_edit.jsp?itemId="+row.imageDocumentId;
               nui.open({
	               	url:url,
	                title: "编辑", 
	                width: 600, 
	            	height: 300,
	            	allowResize:true,
	            	showMaxButton: true,
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
        
        //删除一个目录，并且删除目录中的影像
        function remove() {
            var row = grid.getSelected();
            if(row){
            	var pageNum = row.pageNum;
	     		if(null != pageNum && pageNum >0){
	     		
	     			nui.alert("该目录下存在影像信息，请先删除影像信息后，再删除目录！");
	     			return;
	     		}
	     		
	     		//判断节点类型，如果是自定义（isSrc=2）的，则可以删除，反之，不可以。
	     		var json = {"imageTypeId":row.imageDocumentTypeId};
	   	      	var msg = exeRule("RIMG_0003","1",json);
	   	      	if(null != msg && '' != msg){
		   	      nui.alert(msg+",不能删除！");
		   	      return;
	   	      	}
	     		
	        	nui.confirm("确定删除吗？","确认",function(action){
	            	if(action!="ok") return;
	            	var json=nui.encode({"item":row});
	                $.ajax({
	                    url: "com.bos.pub.image.delImageDocumentList.biz.ext",
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
	            }); 
            
            }else{
            
            	nui.alert("请选中一条记录");
            }
        }
        
        //查看/上传影像资料
        function sunScan(v){
        	var row = grid.getSelected();
			if (row) {
                  	//检验必要字段，必须传值 （客户编号，业务编号，主节点）
          	var bn = nui.get("item.businessNumber").getValue();
		   var nodeFlag = nui.get("nodeFlag").getValue();
		   var imageTypeId = nui.get("item.imageTypeId").getValue();
           var imageTypeName = nui.get("item.imageTypeName").getValue();
		   if(nodeFlag!="true"){
        		alert("请选择左侧树的叶子节点，才可添加或查看影像信息")
        		return;
        	}
          var imageDocumentId=row.imageDocumentId;
          var title = "影像上传";
          if('1'==v){
          	title = "影像查看";
          }else{
          	if(null==bn || ''==bn || 'null'==bn ||'undefined'==bn){
          		alert("数据异常：业务编号为空，不能上传影像");
          		return;
          	}
          	if(null==imageTypeId || ''==imageTypeId || 'null'==row.imageDocumentTypeId ||'undefined'==imageTypeId){
          		alert("数据异常：模板节点为空，不能上传影像");
          		return;
          	}
          
          }
            debugger;
          var ss=row.imageDocumentTypeId;
           var url =nui.context + "/pub/imagePlatform/imageScan.jsp?serialNo="+row.imageDocumentTypeId+"&imageTypeName="+imageTypeName+"&view="+v+"&barCode="+row.barCode+"&createTime="+row.createTime;
           nui.open({
               	url:url,
                title: title, 
                width: 800, 
            	height: 550,
            	allowResize:true,
            	showMaxButton: true,
                ondestroy: function (action) {
                  if("submit"==action){
                  
                	//获取子窗口对象
                	var iframe = this.getIFrameEl();
		            //获取影像张数
		            debugger;
		            var data = iframe.contentWindow.getData();
		            //设置批次号
		            nui.get("item.barCode").setValue(data.batchId);
		            
		            var retcode = data.retcode;
		            if('0' != retcode){
		            	
		            	nui.alert("影像提交失败！");
		            }else{
		            
			            //更新影像张数
	                	updateImageNum(data.images,ss,imageDocumentId);
		            }
                  }  	
                }
        	});
        }else{
        	alert("请选中一条记录");
        }
        }
        function updateImageNum(nums,ss,imageDocumentId){
        	var data = form.getData(); //获取表单多个控件的数据
        	data["map"]=nums;
        	data.item.imageTypeId=ss;
        	data.item.imageDocumentId=imageDocumentId;
        	debugger;
        	var json=nui.encode(data);
            $.ajax({
                url: "com.bos.pub.image.saveImageNum.biz.ext",
                type: 'POST',
                data: json,
                cache: false,
                contentType:'text/json',
                success: function (text) {
                	if (text.msg) {
                 		nui.alert(text.msg); 
                	}
                    grid.reload();
                },
                error: function () {
                	nui.alert("操作失败！");
                }
            });
            	search();
        }
</script>
</body>
</html>
