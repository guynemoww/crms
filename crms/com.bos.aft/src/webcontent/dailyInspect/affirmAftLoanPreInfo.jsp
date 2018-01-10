<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-05-07
  - Description:TB_CLA_RISK_INDEX_CODE, com.bos.dataset.cla.TbClaRiskIndexCode
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>

	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
		<div class="nui-dynpanel" columns="4">
			<label>客户名称：</label>
		<input name="item.partyName" required="false" class="nui-textbox nui-form-input"  />

		<label>客户编号：</label>
		<input name="item.partyNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

		<label>ECIF客户编号：</label>
		<input name="item.ecifPartyNum" required="false" class="nui-textbox nui-form-input" vtype="maxLength:20" />

		<label>客户类型：</label>
		<input id="item.corpCustomerTypeCd" name="item.corpCustomerTypeCd" required="false" class="nui-buttonEdit nui-form-input" allowInput="false" onbuttonclick="selectCodeList"/>
	
		</div>
		
	</div>

 <div class="nui-toolbar" style="text-align:center;padding-top:5px;padding-bottom:5px;" borderStyle="border:0;">
	    <a class="nui-button"  iconCls="icon-search" onclick="query()">查询</a>
	    <a class="nui-button" onclick="exportCust">导出</a>
	    <a class="nui-button" onclick="reset">重置</a>
	</div>
 
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-add" onclick="addFlow()">增加</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit()">编辑</a>
	
	<a class="nui-button" iconCls="icon-edit" onclick="look()">查看</a>
	<!-- 
	<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
	 -->
</div>

 <div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.csm.corporation.corporation.getCorpList.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false" showEmptyText="true"
	    emptyText="没有查到数据" showReloadButton="false"
	    onrowdblclick="" allowCellEdit="true" allowCellSelect="true" allowCellWrap="true"
	    sizeList="[10,20,50,100]" pageSize="10">
	    <div property="columns">
	        <div type="checkcolumn">选择</div>
	        <!--<div type="indexcolumn">序号</div>-->                
	        <div field="partyNum" headerAlign="center" allowSort="true" width=""  >客户编号</div>
			<div field="partyName" headerAlign="center" allowSort="true" autoEscape="false" >客户名称</div>
			<div field="ecifPartyNum" headerAlign="center" allowSort="true" >ECIF客户编号</div>
			<div field="corpCustomerTypeCd" headerAlign="center" allowSort="true" dictTypeId="CDKH0001">客户类型</div>
	        <div field="dateOfEstablishment" allowSort="true" width="" headerAlign="center" dateFormat="yyyy-MM-dd">成立日期</div>  
	     </div>
	</div>
 <iframe name="exportFrame" id="exportFrame" src="" style="display:none;"></iframe>
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("datagrid1");
	var partyId="";
	var node;
	var msg;
	var bizId;
	var json;
	
	//对公单一客户查询
	function query(){
       var o = form.getData(false, true);//逻辑流必须返回total
       grid.load(o);
       //alert(nui.encode(o));
       grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
       			partyId = e.data[i].partyId;
       			/*
       			e.data[i]['partyName']='<a href="#" onclick="clickCust(\''
       				+ e.data[i].partyId
       				+ '\');return false;" value="'
       				+ e.data[i].partyId
       				+ '">'+e.data[i]['partyName']+'</a>';
       			*/
       		}
       });
	}
	query();
	
	//增加
    function addFlow() {
    	var row = grid.getSelected();
    	
    	
    	if(row) {
    		//alert(row.partyId);
    		//alert(nui.encode(json));
    		json=nui.encode({"partyId":row.partyId});
    		add();
    	} else {
    		alert("请选中一条记录");
    	}
    	
    }
	//创建一个新的检查
	function add(){
		git.mask();
		nui.ajax({
			url: "com.bos.aft.aft_small_inspect.adjustCorp.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	if(mydata.corpration.fourrEnterpriseSizeCd!=null&&mydata.corpration.fourrEnterpriseSizeCd!=undefined&&mydata.corpration.fourrEnterpriseSizeCd<"3"){
            		norCreateFlow();
            		if(msg==1){
            		    //var param=nui.encode({"bizId":bizId,"node":node});
            			//var url=nui.context+"/aft/daily/daily_tree.jsp?param="+param;
            			//git.go(url);
            			nui.open({
				            url: nui.context+"/aft/daily/daily_tree.jsp?bizId="+bizId,
				            title: "创建贷后检查流程", 
				            width: 1300, 
				        	height: 600,
				        	allowResize:true,
				        	showMaxButton: true,
				            ondestroy: function (action) {
				                if(action=="ok"){
				                    //search();
				                }
				            }
				        });
            		}else if(msg==2){
            			nui.alert("该客户贷后检查流程还未走完!");
            			return;
            		}else if(msg==3){
            			nui.alert("出现异常!");
            			return;
            		}
            		
            	}else{
            		norCreateFlow();
            		if(msg==1){
            		    //var param=nui.encode({"bizId":bizId,"node":node});
            			//var url=nui.context+"/aft/daily/daily_tree.jsp?param="+param;
            			//git.go(url);
            			nui.open({
				            url: nui.context+"/aft/daily/daily_tree.jsp?bizId="+bizId,
				            title: "创建贷后检查流程", 
				            width: 1200, 
				        	height: 600,
				        	allowResize:true,
				        	showMaxButton: true,
				            ondestroy: function (action) {
				                if(action=="ok"){
				                    //search();
				                }
				            }
				        });
            		}else if(msg==2){
            			nui.alert("该客户贷后检查流程还未走完!");
            			return;
            		}else if(msg==3){
            			nui.alert("出现异常!");
            			return;
            		}
            		
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    git.unmask();
            }
		
		});
		
		//var url=nui.context +"/aft/aft_small_inspect/aft_dailyIns_tree.jsp?partyId"+partyId;
	}
	
	function smCreateFlow(){
		nui.ajax({
			url: "com.bos.aft.aft_small_inspect.createSmallBusiFlow.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            async:false,
            contentType:'text/json',
            success: function (mydata) {
            	//alert(nui.encode(mydata));
            	msg=mydata.msg;
            	node=mydata.node;
            	bizId=mydata.bizId;
            	git.unmask();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    git.unmask();
            }
		});
	
	}
	function norCreateFlow(){
		nui.ajax({
			url: "com.bos.aft.dailyInspect.createNormalBusiFlow.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            async:false,
            contentType:'text/json',
            cache: false,
            success: function (mydata) {
            	//alert(nui.encode(mydata));
            	msg=mydata.msg;
            	node=mydata.node;
            	bizId=mydata.bizId;
            	git.unmask();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    git.unmask();
            }
		});
	}
	
	//选择字典项
	function selectCodeList(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=CDKH0001",
            title: "选择字典项",
            width: 800,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.currentNode;
                    data = nui.clone(data);
                    if (data) {
                        btnEdit.setValue(data.dictid);
                        btnEdit.setText(data.dictname);
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	}
        });            
	}

    function reset(){
		form.reset();
	}
	
	
	
    //编辑
    function edit() {
        var row = grid.getSelected();
        var lastAlcInfoId;
        if (row) {
        	lastAlcInfoId = getLastAlcInfoId(row.partyId);
            nui.open({
                url: nui.context+"/aft/daily/daily_tree.jsp?bizId="+lastAlcInfoId,
                title: "编辑当前客户", 
                width: 1200, 
        		height: 600,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                    //iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                    if(action=="ok"){
                        //search();
               	 	}
                }
            });
            
        } else {
            alert("请选中一条记录");
        }
        
    }
    //查看
    function look() {
        var row = grid.getSelected();
        var lastAlcInfoId;
        if (row) {
        	lastAlcInfoId = getLastAlcInfoId(row.partyId);
            nui.open({
                url: nui.context+"/aft/dailyInspect/showAlcDetails.jsp?lastAlcInfoId="+lastAlcInfoId,
                title: "查看当前客户贷后检查信息", 
                width: 1200, 
        		height: 600,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                    //iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                    if(action=="ok"){
                        //search();
               	 	}
                }
            });
        } else {
            alert("请选中一条记录");
        }
        
    }
    //提交
    function remove() {
        var row = grid.getSelected();
        
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"tbClaRiskIndexCode":{"indexCdId":
            		row.indexCdId,version:row.version}});
                $.ajax({
                     url: "com.bos.pub.crud.delTbClaRiskIndexCode.biz.ext",
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
    //根据partyId获取最新的alcInfoId
    function getLastAlcInfoId(partyId) {
    	var partyIdJson = nui.encode({'partyId':partyId});
    	var lastAlcInfoId;
        	$.ajax({
				url: "com.bos.aft.dailyInspect.getLastAlcInfoId.biz.ext",
				type: 'POST',
				data: partyIdJson,
				async:false,
				cache: false,
				contentType:'text/json',
				success: function (text) {
					//alert(nui.encode(text));
					lastAlcInfoId = text.lastAlcInfoId;
				},
				error: function () {
			        
			    }
			});
		return lastAlcInfoId;
    }

	</script>
</body>
</html>
