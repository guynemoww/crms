<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-5-5 13:16:44
  - Description:
-->
<head>
<title>联保小组成员信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	<input name="sqlName" value="com.bos.csm.guar.guarGroup.memberList" class="nui-hidden" />
	<input name="item.groupPartyId" id="item.groupPartyId" class="nui-hidden" />
	
	<div id="crud" class="nui-toolbar" style="border-bottom:0;text-align:left;">
	<a id="add" class="nui-button" iconCls="icon-add" onclick="selectCust()">引入成员</a> 
	<a id="remove" class="nui-button" iconCls="icon-remove" onclick="remove()">删除成员</a>
	</div>
	
</div>

<div id="datagrid1" class="nui-datagrid" style="width:100%;height:auto;"  sortMode="client"
	    url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false" showReloadButton="false"
	    sizeList="[10,20,50,100]"  pageSize="10">
	    <div property="columns">
	        <div type="checkcolumn">选择</div>
	        <div field="partyNum" allowSort="true"  headerAlign="center" >成员编号</div> 
	        <div field="partyName" allowSort="true"  headerAlign="center" >成员名称</div>                
			<div field="unifySocietyCreditNum"name="unifySocietyCreditNum" allowSort="true" headerAlign="center" >统一社会信用代码</div> 
	        <div field="registrCd" name="registrCd" allowSort="true"  headerAlign="center"  >营业执照</div> 
	        <div field="orgRegisterCd" name="orgRegisterCd" allowSort="true"  headerAlign="center"  >组织机构代码</div> 
	        <div field="certType" name="certType" dictTypeId="CDKH0002" allowSort="true"  headerAlign="center"  >证件类型</div> 
	        <div field="certNum" name="certNum" allowSort="true"  headerAlign="center"  >证件号码</div>
	      	<div field="status" dictTypeId="XD_KHCD0231" allowSort="true"  headerAlign="center">认定状态</div>
	     </div>
	</div>

<script type="text/javascript">
	nui.parse();
	git.mask();
	var form = new nui.Form("#form1"); 
	var grid = nui.get("datagrid1");
	var processInstId = "<%=request.getParameter("processInstId") %>" ;
	var partyId = "<%=request.getParameter("partyId") %>";
	var qote = "<%=request.getParameter("qote")%>" ;
	var state = "<%=request.getParameter("state")%>" ;
	var wflow = "<%=request.getParameter("wflow") %>";
	var guarGroup;
	//认定中查看
	var o = new Object();
    o.party = new Object();
    o.party.partyId = partyId;
    o.processInstId=processInstId;
    $.ajax({
         url: "com.bos.csm.guar.guarGroup.getGuarGroupInfo.biz.ext",
         type: 'POST',
         data: nui.encode(o),
         cache: false,
         contentType:'text/json',
         success: function (data) {
         	guarGroup = data.guarGroup;
         	if(guarGroup.jointGuaranteeStatus=='03'||(wflow=='2'&&guarGroup.jointGuaranteeStatus=='02')){
				grid.on("preload", function(e) {
					if (!e.data || e.data.length < 1) {
						return;
					}
					for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
						e.data[i]['partyName']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].relatedCustPartyId+ '\');">'+e.data[i]['partyName']+'</a>';
					}
				});
			}
			if(guarGroup.jointGuaranteeType == '01'){//企业
				grid.hideColumn(grid.getColumn("certType"));
				grid.hideColumn(grid.getColumn("certNum"));
				grid.showColumn(grid.getColumn("registrCd"));
				grid.showColumn(grid.getColumn("orgRegisterCd"));
				grid.showColumn(grid.getColumn("unifySocietyCreditNum"));
			}else{//自然人
				grid.showColumn(grid.getColumn("certType"));
				grid.showColumn(grid.getColumn("certNum"));
				grid.hideColumn(grid.getColumn("registrCd"));
				grid.hideColumn(grid.getColumn("orgRegisterCd"));
				grid.hideColumn(grid.getColumn("unifySocietyCreditNum"));
			}
			//grid.refresh();
			init();
         },
         error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
         }
	});
	if(qote==1){
		nui.get("add").hide();
	   nui.get("remove").hide();
	}	
	function init() {
	  if (partyId) {
			nui.get("item.groupPartyId").setValue(partyId);
		}
		if(qote==2){
		   var json = nui.encode({"page":o.page,"partyId":partyId,"groupType":"512"});
		    $.ajax({
         url: "com.bos.csm.pub.ibatis.getItemEcif.biz.ext",
         type: 'POST',
         data: json,
         cache: false,
         contentType:'text/json',
         success: function (data) {
         if(data.map.msg!="AAAAAAA"){
         nui.alert(data.map.msgg);
         }else{
         		debugger;
         	company = data.items;
    /*      grid.setData(company);// */
    	var data1 = form.getData(); //获取表单多个控件的数据
         grid.load(data1);
         }
         
         },
         error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
         }
	});
		}else{
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
        }
        git.unmask();
         
     }
     
     if(state=="01"){
		alert("联保小组有在途流程，信息无法修改");
		nui.get("crud").hide();
	}
     
    init();
     
    function remove() {
        var row = grid.getSelected();
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	git.mask();
            	var json=nui.encode({"mem":{"id":row.id}});
                $.ajax({
                     url: "com.bos.csm.guar.member.delMember.biz.ext",//delMemberEcif
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	git.unmask();
                    	if (text.msg) {
                    		alert(text.msg);
                    		return;
                    	}else{
                    		if(text.flag){
		            		var node = text.node;
		            		}
		            		
                    	}
                        git.mask();
                        init();
                    },
                    error: function () {
                    	git.unmask();
                    	nui.alert("操作失败！");
                    }
                });
            }); 
        } else {
            nui.alert("请选中一条记录");
        }
    }
    
    //选择人员
	function selectCust(e) {
		var url = '';
		if (guarGroup.jointGuaranteeType == "01") {
			//企业类
			url = nui.context + "/csm/pub/queryCorp.jsp?partyId=" + partyId;
		} else {
			//自然人
			url = nui.context + "/csm/pub/queryNartual.jsp?partyId=" + partyId;
		}
		nui.open({
					url : url,
					showMaxButton : true,
					title : "选择成员客户",
					width : 1000,
					height : 500,
					ondestroy : function(action) {
						if (action == "ok") {
							var iframe = this.getIFrameEl();
							var data = iframe.contentWindow.getData();
							data = nui.clone(data);
							if (data) {
								//保存
								var o = new Object();
								o.mem = new Object();
								o.mem.partyId = partyId;
								o.mem.relatedCustPartyId = data.partyId;
								o.processInstId = processInstId;
								$.ajax({
											url : "com.bos.csm.guar.member.insertMember.biz.ext",//insertMemberEcif
											type : 'POST',
											data : nui.encode(o),
											cache : false,
											contentType : 'text/json',
											success : function(text) {		
							/* 				if(text.map.msg!="AAAAAAA"){
											alert(text.map.msgg);
											}else{	 */		
											if (text.msg) {
													alert(text.msg);
													return;
												} else {
													init();
												}/* } */
							
											git.unmask("form1");
											},
											error : function(jqXHR, textStatus,
													errorThrown) {
												git.unmask("form1");
												nui.alert(jqXHR.responseText);
											}
										});

							}
						}
					}
				});
	}
</script>
	
	

</body>
</html>