<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): lujinbin
  - Date: 2013-11-28
  - Description:TB_CSM_ADDRESS, com.bos.dataset.csm.TbCsmAddress
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<h3>基准利率参数</h3>
	<div class="nui-dynpanel" style="text-align:center;padding-top:5px;padding-bottom:5px;" columns="4">
		<label>生效日期：</label>
		<input name="tbSysBenchmarkRate.validDate" required="false" class="nui-DatePicker nui-form-input"  />
	</div>
</div>
<div class="nui-toolbar" style="padding-right:20px;text-align:right;padding-top:5px;padding-bottom:5px;" 
    borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<%--  
	<a class="nui-button" iconCls="icon-add" onclick="add()">增加</a>--%> 
	<a class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
	<a class="nui-button" iconCls="icon-edit" onclick="edit(1)">查看</a>
	<a class="nui-button" iconCls="icon-edit" onclick="run()">执行调整</a>
	<%-- 
	<a class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>--%> 
</div>
	     
<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" 
	url="com.bos.pub.TbSysBenchmarkRate.queryTbSysBenchmarkRateList.biz.ext" dataField="tbSysBenchmarkRates"
	allowResize="true" showReloadButton="false"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<%--<div field="rateType" headerAlign="center" allowSort="true" dictTypeId="RateType">利率类型</div>
		<div field="rateDescription" headerAlign="center" allowSort="true" >利率描述</div>
		<div field="rateNum" headerAlign="center" allowSort="true" >利率数值</div>
		<div field="rateTerm" headerAlign="center" allowSort="true" >利率期限</div>
		<div field="rateTermUnit" headerAlign="center" allowSort="true" dictTypeId="RateTermUnit">利率期限单位</div>
		<div field="rateEffectDate" headerAlign="center" allowSort="true" >生效日期</div>--%>
		<div field="intRateCd" headerAlign="center" allowSort="true" >利率编号</div>
		<div field="intRateName" headerAlign="center" allowSort="true" >利率名称</div>
		<%--<div field="currencyCd" headerAlign="center" allowSort="true" dictTypeId="CD000001">币种</div>--%>
		<div field="intRate" headerAlign="center" allowSort="true" >利率值</div>
		<%--<div field="status" headerAlign="center" allowSort="true" dictTypeId="XD_GGCD2012">利率状态</div>--%>
		<div field="validDate" headerAlign="center" allowSort="true" dictTypeId="RateTermUnit">生效日期</div>
		<%--<div field="invalidDate" headerAlign="center" allowSort="true" >失效日期</div>
		<div field="dataDate" headerAlign="center" allowSort="true" >数据日期</div>--%>
		</div>
</div>
		
<script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");

//for uat3 version
		
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    }
    search();
        
	function reset(){
		form.reset();
		search();
	}
    function add() {
        nui.open({
            url: nui.context + "/pub/benchmarkRate/benchmarkRate_add.jsp",
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
    //比较时间大小
    function toDate(str){
     var sd=str.split("-");
    return new Date(sd[0],sd[1],sd[2]);
      }
		         
    function edit(v) {
        var row = grid.getSelected();
		        
             if (row) {
                if(v==0){
							    nui.open({
				                url: nui.context + "/pub/benchmarkRate/benchmarkRate_edit.jsp?intRateId="+row.intRateId+"&view="+v,
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
					
					
                } else{
                		nui.open({
				                url: nui.context + "/pub/benchmarkRate/benchmarkRate_edit.jsp?intRateId="+row.intRateId+"&view="+v,
				                title: "查看", 
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
					
                
                } 
            
        }else{
            alert("请选中一条记录");
        }
        
    }
    
    function remove() {
        var row = grid.getSelected();
        if (row) {
        alert(nui.encode(row));
         		var a=new Date();
		         //当前时间
		         var time1=a.getFullYear() + "-" + (a.getMonth() + 1) + "-" + a.getDate();
		          var time2=row.validDate;
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
						            	var json=nui.encode({"tbSysBenchmarkRate":{"intRateId":
            										row.intRateId}});
						                $.ajax({
						                     url: "com.bos.pub.TbSysBenchmarkRate.delTbSysBenchmarkRate.biz.ext",
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
					  
					  }
        } else {
            nui.alert("请选中一条记录");
        }
    }
	function run(){
		var o = new Object();
		o.batchId = 'BT0023';
		var json = nui.encode(o);
		$.ajax({
						url: "com.bos.batch.console.runBatchSingle.biz.ext",
						type: 'POST',
						data: json,
						cache: false,
						contentType:'text/json',
						success: function (text) {
						   		
						},
						error: function () {
						    nui.alert("操作失败！");
						}
		 });
		 var url = nui.context+"/batch/monitor/monitor_list.jsp?jobCode=btSynchBaseRate&srcFlag=0";
		 git.go(url);
	}
</script>
</body>
</html>
