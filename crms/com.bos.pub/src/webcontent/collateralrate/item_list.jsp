<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): 卢金彬
  - Date: 2014-02-14
  - Description:TB_SYS_COLLATERAL_RATE, com.bos.dataset.sys.TbSysCollateralRate
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input type="hidden" name="item._entity" value="com.bos.dataset.sys.TbSysCollateralRate" class="nui-hidden" />
	<h3>抵制押比例参数配置</h3>
	<div class="nui-dynpanel" columns="6">
		
		<label>担保类型：</label>
		<input name="tbSysCollateralRate.guaranteeType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_SXCD1185"/>

		<label>担保物属性：</label>
		<input name="tbSysCollateralRate.collateralProperty" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" dictTypeId="XD_DBCD3004"/>

		<label>担保物主类：</label>
		<input name="tbSysCollateralRate.collateralMainType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" />

		<label>担保物子类：</label>
		<input name="tbSysCollateralRate.collateralSubType" required="false" class="nui-dictcombobox nui-form-input" vtype="maxLength:6" />


	</div>
</div>
				
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
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
	url="com.bos.pub.TbSysCollateralRate.getTbSysCollateralRateList.biz.ext" dataField="tbSysCollateralRates"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="guaranteeType" headerAlign="center" allowSort="true" dictTypeId="XD_SXCD1185">担保类型</div>
		<div field="collateralProperty" headerAlign="center" allowSort="true" dictTypeId="XD_DBCD3004">担保物属性</div>
		<div field="collateralMainType" headerAlign="center" allowSort="true" >担保物主类</div>
		<div field="collateralSubType" headerAlign="center" allowSort="true" >担保物子类</div>
		<div field="applicationOrgLevel" headerAlign="center" allowSort="true" dictTypeId="XD_GGCD6004">适用机构级别</div>
		<div field="applicationBusinessType" headerAlign="center" allowSort="true" dictTypeId="product">适用业务种类</div>
		<div field="applicationCustCredit" headerAlign="center" allowSort="true" dictTypeId="XD_KHCD0019">适用客户信用等级</div>
		<div field="collateralRate" headerAlign="center" allowSort="true" >抵质押率（%）</div>
		<div field="rateEffectDate" headerAlign="center" allowSort="true" >生效日期</div>
		<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org" >经办机构名称</div>
		<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">经办用户名称</div>
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
            url: "pub/collateralrate/item_add.jsp",
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
        if (row) {
        		 if(v==0){
                		 var a=new Date();
				         //当前时间
				          var time1=a.getFullYear() + "-" + (a.getMonth() + 1) + "-" + a.getDate();
				          var time2=row.rateEffectDate;
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
							                url: "pub/collateralrate/item_edit.jsp?collateralRateId="+row.collateralRateId+"&view="+v,
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
							                        search();
							               	 	}
							                }
							            });
					
					}
                }else{
                		       nui.open({
					                url: "pub/collateralrate/item_edit.jsp?collateralRateId="+row.collateralRateId+"&view="+v,
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
					                        search();
					               	 	}
					                }
					            });
					
                
                }
            
     
        } else {
            alert("请选中一条记录");
        }
        
    }
    
    function remove() {
        var row = grid.getSelected();
        
        if (row) {
             var a=new Date();
		         //当前时间
		         var time1=a.getFullYear() + "-" + (a.getMonth() + 1) + "-" + a.getDate();
		         var time2=row.rateEffectDate;
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
								            	var json=nui.encode({"tbSysCollateralRate":{"collateralRateId":
								            		row.collateralRateId}});
								                $.ajax({
								                     url: "com.bos.pub.TbSysCollateralRate.delTbSysCollateralRate.biz.ext",
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
