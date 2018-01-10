<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): luchengchen@git.com.cn
  - Date: 2013-12-27
  - Description:TB_SYS_WARN_RESCRIPTION, com.bos.dataset.sys.TbSysWarnRescription
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.dataset.sys.TbSysWarnRescription" class="nui-hidden" />
	<input type="hidden" name="item.rescriptId" class="nui-hidden" />
	<h3>时效类预警参数</h3>
	<div class="nui-dynpanel" columns="4">
	
	<label>预警指标类型：</label>
		<input id="rescriptType" required="false" name="item.rescriptType" 
			class="nui-dictcombobox nui-form-input" vtype="maxLength:40" dictTypeId="earlyWarningType"  />
	<label>生效日期：</label>
		<input name="item.rescriptEffectDate" required="false" class="nui-DatePicker nui-form-input"  />
	</div>
</div>
<div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
	<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
</div>
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.TbSysWarnRescription.getItemList.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
        <div field="rescriptType" headerAlign="center" allowSort="true"  dictTypeId="earlyWarningType" >预警指标类型</div>
		<div field="rescriptCode" headerAlign="center" allowSort="true" >预警指标编码</div>
		<div field="rescriptName" headerAlign="center" allowSort="true" >预警指标名称</div>
		<div field="rescriptValue" headerAlign="center" allowSort="true" >预警阀值</div>
		<div field="rescriptvalueType" headerAlign="center" allowSort="true" dictTypeId="earlyWarningType">预警阀值类型</div>
		<div field="rescriptvalueUnit" headerAlign="center" allowSort="true" dictTypeId="earlyWarningType">预警阀值单位</div>
		<div field="rescriptDesc" headerAlign="center" allowSort="true" >预警指标描述</div>
		<div field="rescriptReceiver" headerAlign="center" allowSort="true" dictTypeId="user">预警信号接收人</div>
		<div field="rescriptLevel" headerAlign="center" allowSort="true" dictTypeId="earlyWarningType">预警信号等级</div>
		<div field="rescriptEffectDate" headerAlign="center" allowSort="true" >生效日期</div>
		<div field="handlingOrgId" headerAlign="center" allowSort="true" dictTypeId="org" >经办机构</div>
		<div field="handlingUserId" headerAlign="center" allowSort="true" dictTypeId="user">经办人员名称</div>
		<div field="handlingDate" headerAlign="center" allowSort="true" >经办日期</div>
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
	
    function add() {
        nui.open({
            url: nui.context + "/pub/earlyWarning/rescription/item_add.jsp",
            title: "新增", 
            width: 800, 
        	height: 500,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
                if(action=="ok"){
                    search();
                }
            }
        });
    }
            //比较时间大小
    function toDate(str){
     var sd=str.split("-");
    return new Date(sd[0],sd[1],sd[2]);
      }
      
    function edit(v) {
     
        var row = grid.getSelected();
                
         if(row){
         
          if(v==0){
                    	var a=new Date();
			         	//当前时间
			         	var time1=a.getFullYear() + "-" + (a.getMonth() + 1) + "-" + a.getDate();
			         	var time2=row.rescriptEffectDate;
                      	time2=time2.split(" ");
        				// 生效时间
			          var d1=toDate(time2[0]);
			          // 当前时间
					  var d2=toDate(time1);
					  //如果已生效
					if(d1<d2){
					        alert("已生效不能修改");
					  }else{
					            nui.open({
					               url: nui.context + "/pub/earlyWarning/rescription/item_edit.jsp?itemId="+row.rescriptId+"&view="+v,
					                title: "编辑", 
					                width: 800,
					        		height: 500,
					                allowResize:true,
					        		showMaxButton: true,
					                onload: function () {
					                    var iframe = this.getIFrameEl();
					                    var data = row;
					                   
					                },
					                ondestroy: function (action) {
					                    if(action=="ok"){
					                        search();
					               	 	}
					                }
					            });
					        }
					        
			  }else{
                		  nui.open({
					               url: nui.context + "/pub/earlyWarning/rescription/item_edit.jsp?itemId="+row.rescriptId+"&view="+v,
					                title: "编辑", 
					                width: 800,
					        		height: 500,
					                allowResize:true,
					        		showMaxButton: true,
					                onload: function () {
					                    var iframe = this.getIFrameEl();
					                    var data = row;
					                   
					                },
					                ondestroy: function (action) {
					                    if(action=="ok"){
					                        search();
					               	 	}
					                }
					            });
					
                
                }
        }else{
            alert("请选中一条记录");
        }
        
    }
    
    function remove() {
        var row = grid.getSelected();
        
        if (row) {
        
        
        var a=new Date();
		         //当前时间
		         var time1=a.getFullYear() + "-" + (a.getMonth() + 1) + "-" + a.getDate();
		         var time2=row.rescriptEffectDate;
                      time2=time2.split(" ");
        				// 生效时间
			          var d1=toDate(time2[0]);
			          // 当前时间
					  var d2=toDate(time1);
					  //如果已生效
					if(d1<d2){
					        alert("已生效不能删除");
					  }else{
										nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"item":{"rescriptId":
            		row.rescriptId,
					"_entity":"com.bos.dataset.sys.TbSysWarnRescription"}});
                $.ajax({
                     url: "com.bos.pub.TbSysWarnRescription.delItem.biz.ext",
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
					  
					  }
        
        	
        } else {
            alert("请选中一条记录");
        }
    }

	</script>
</body>
</html>