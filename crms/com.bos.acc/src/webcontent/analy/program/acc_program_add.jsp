<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2013-11-22
  - Description:TB_CSM_BOND_INFO, com.bos.dataset.csm.TbCsmBondInfo
-->
<head>
<title>财报选择</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>


	<div id="tabs1" class="nui-tabs" activeIndex="0"
		style="width: 100%; height: 100%;">
		<div title="财报选择">
			<center>
				<div id="form1" class="nui-form"
					style="width: 99.5%; height: auto; overflow: hidden;">
					<input type="hidden" name="accCustomerFinance._entity"
						value="com.bos.dataset.acc.TbAccCustomerFinance"
						class="nui-hidden" /> <input type="hidden"
						name="accCustomerFinance.partyId"
						value="<%=request.getParameter("partyId")%>" class="nui-hidden" />
					<div class="nui-dynpanel" columns="6">
						<label>财报类型：</label> <input id="financeTypeCd"
							name="accCustomerFinance.financeTypeCd" required="true"
							class="nui-dictcombobox nui-form-input" dictTypeId="XD_ACCCD0001"
							allowInput="false" /> <label>财报口径：</label> <input
							id="caliberCd" name="accCustomerFinance.caliberCd"
							required="true" class="nui-dictcombobox nui-form-input"
							dictTypeId="CDKH0071" allowInput="false" /> <label>是否经过审计：</label>
						<input id="auditedInd" name="accCustomerFinance.auditedInd"
							required="false" class="nui-dictcombobox nui-form-input"
							dictTypeId="YesOrNo" allowInput="false" />

					</div>
					<div class="nui-toolbar" style="text-align: right; border: none">
						<a class="nui-button" 
							iconCls="icon-search" onclick="search()">查询</a>
					</div>
				</div>
				<div id="form2" class="nui-form"
					style="width: 99.5%; height: auto; overflow: hidden;">
					<div id="grid1" class="nui-datagrid"
						style="width: 99.5%; height: auto;" sortMode="client"
						url="com.bos.acc.acccustomerfinance.getAccCustomerFinanceList.biz.ext"
						dataField="accCustomerFinances" allowAlternating="true"
						multiSelect="true" showEmptyText="true" showPager="true"
						emptyText="没有查到数据" showReloadButton="false" showColumnsMenu="true"
						onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
						sizeList="[10,20,50,100]" pageSize="10">
						<div property="columns">
							<div type="checkcolumn">选择</div>
							<div field="financeTypeCd" headerAlign="center" allowSort="true"
								dictTypeId="XD_ACCCD0001">财务报表类型</div>
							<div field="financeDeadline" headerAlign="center"
								allowSort="true" dateFormat="yyyy-MM-dd">截止日期</div>
							<div field="caliberCd" headerAlign="center" allowSort="true"
								dictTypeId="CDKH0071">报表口径</div>
							<div field="customerTypeCd" headerAlign="center" allowSort="true"
								dictTypeId="XD_ACCCD0002">财务报表类别</div>
							<div field="auditedInd" headerAlign="center" allowSort="true"
								dictTypeId="YesOrNo">是否经过审计</div>
							<div field="financeStatusCd" headerAlign="center"
								allowSort="true" dictTypeId="XD_ACCCD0004">财务报表状态</div>
							<div field="userNum" headerAlign="center" allowSort="true"
								dictTypeId="user">操作人员</div>
							<div field="orgNum" headerAlign="center" allowSort="true"
								dictTypeId="org">操作机构</div>
							<div field="updateTime" headerAlign="center" allowSort="true">操作日期</div>
						</div>

					</div>
					<div style="width: 99.5%">
						<div class="nui-dynpanel" columns="6">
							<label>方案名称：</label> <input id="finanysisProgramNum"
								name="finanysisProgramNum" required="ture"
								class="nui-textbox nui-form-input" vtype="maxLength:200" />
							<!-- <label>财报分析类型：</label> -->
							<input type="hidden" id="modelCd" name="modelCd" required="true"
								class="nui-hidden" value="1" />
						</div>
						<div class="nui-toolbar"
							style="border-top: 1px solid #8ba0bc; border-bottom: none; text-align: right; margin-top: 7px">
							<a id="addCust" style="margin-left: 5px" class="nui-button"
								iconCls="icon-add" onclick="add()">下一步</a>
						</div>
					</div>
				</div>
			</center>
		</div>
	</div>

	<script type="text/javascript">
    nui.parse();
    var  qote="<%=request.getParameter("qote")%>";
    if(qote==1){
		nui.get("add").hide();
	   nui.get("edit").hide();
	   nui.get("remove").hide();
	}
    var form = new nui.Form("#form1"); 
    var form2 = new nui.Form("#form2");
	var grid = nui.get("grid1");
    function search() {
	var data = form.getData(); //获取表单多个控件的数据
	git.mask("form1");
	var o=form.getData();
	if (form.isValid() == false) {
		alert("请将信息填写完整");
		git.unmask("form1");
		return;
	}
		grid.load(data);
		git.unmask("form1");
    }
    
    function reset(){
    	var form = new nui.Form("#form1"); 
		form.reset();
	}
	
    //添加选中的财报生成财务分析记录
	function add(){
		//获取选中数据
		if (form2.isValid() == false) {
		alert("请将信息填写完整");
		return;
		}
		var rows = grid.getSelecteds();
		if(rows == null ||rows.length==0){
			nui.alert("请至少选中一笔财报记录！");
			return;
		} else if(rows.length < 4){
		    var financeId1='';
		    var financeId2='';
		    var financeId3='';
		    if (rows.length==2){
		        financeId1=rows[0].financeId;
		        financeId2=rows[1].financeId;
		    }
		    if (rows.length==3){
		        financeId1=rows[0].financeId;
		        financeId2=rows[1].financeId;
		        financeId3=rows[2].financeId;
		    }
			var partyId="<%=request.getParameter("partyId")%>";
			var modelCd = nui.get("modelCd").value;
			var finanysisProgramNum=nui.get("finanysisProgramNum").value;
			var sendData = nui.encode({accCustomerFinances:rows,"partyId":partyId,"modelCd":modelCd,
			"finanysisProgramNum":finanysisProgramNum,"financeId1":financeId1,"financeId2":financeId2,"financeId3":financeId3});
			git.mask(); 
			$.ajax({
				url:"com.bos.acc.analy.addAccAnalysisProgram.biz.ext",
				type:'POST',
				data:sendData,
				cache: false,
				contentType:'text/json',
				success:function(text){
					if(text.msg){
            		nui.alert(text.msg);
            		git.unmask(); 
            		return;
            	} else {
            		var url = nui.context +"/acc/analy/program/acc_program_detail.jsp?finanysisProgramId="
            		+text.response.finanysisProgramId;
            		git.go(url);
            	}
				}
			});
	     } else {
		    nui.alert("最多只能选中三笔财报记录！");
			return false;
		} 
	}
    
    function edit(v) {
        var row = grid.getSelected();
        if (row) {
          if(row.financeStatusCd!='01' && v!='1'){
            alert('只能修改未生效的财报');
            return;
          }
            nui.open({
                url: "acc/acccustomerfinance/acccustomerfinance_edit.jsp?financeId="+row.financeId+"&view="+v+"&reportType="+row.customerTypeCd
                +"&financeTypeCd="+row.financeTypeCd,
                showMaxButton: true,
                title: "编辑", 
                width: 1024,
	            height: 540,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                    //iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                   if(v=='0'){
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
            if(row.financeStatusCd!='01'){
            alert('只能删除未生效的财报');
            return;
           }
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"accCustomerFinance":{"financeId":
            		row.financeId,version:row.version}});
                $.ajax({
                     url: "com.bos.acc.acccustomerfinance.delAccCustomerFinance.biz.ext",
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

	</script>
</body>
</html>
