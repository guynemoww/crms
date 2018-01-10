<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-5-10 13:16:44
  - Description:
-->
<head>
<title>集团成员信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>


<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input name="sqlName" value="com.bos.csm.company.company.memberList" class="nui-hidden" />
	<input name="item.groupPartyId" id="item.groupPartyId" class="nui-hidden" />
	<div  id="crud" class="nui-toolbar" style="border-bottom:0;text-align:left;"><a
	id="add" class="nui-button" iconCls="icon-add" onclick="add()">引入成员</a> <a
	id="edit" class="nui-button" iconCls="icon-edit" onclick="edit()">修改成员</a>  <a
	id="remove" class="nui-button" iconCls="icon-remove" onclick="remove()">删除成员</a></div>
</div>

<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto;"  sortMode="client"
	    url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
	    allowAlternating="true" multiSelect="false" showReloadButton="false"
	    sizeList="[10,20,50,100]"  pageSize="10">
	    <div property="columns">
	        <div type="checkcolumn">选择</div>
	        <div field="partyNum" allowSort="true" width="" headerAlign="center" >成员编号</div> 
	        <div field="partyName" allowSort="true"  headerAlign="center" autoEscape="false" >成员名称</div>                
	        <div field="certType" dictTypeId="CDKH0002" allowSort="true" width="" headerAlign="center" >证件类型</div> 
	        <div field="certNum" allowSort="true" width="" headerAlign="center" >证件号码</div> 
	       	<div field="isGroup" allowSort="true" width="" headerAlign="center" dictTypeId="YesOrNo">是否集团客户</div> 
	        <div field="memberTypeCd" allowSort="true" width="" headerAlign="center" dictTypeId="XD_KHCD0142">成员类别</div>  
	        <div field="groupRelTypeCd" allowSort="true" width="" headerAlign="center"  dictTypeId="XD_KHCD0207" >关联类型</div>  
	      	<div field="relationshipState" allowSort="true" width="" headerAlign="center">关联关系说明</div>  
	      	<div field="memberStatusCd" dictTypeId="XD_KHCD0231" allowSort="true" width="" headerAlign="center">认定状态</div>
	      	<div field="operType" allowSort="true" width="" dictTypeId="XD_JTKH6001" headerAlign="center">变更方式</div>    
	      	<!-- 
	      	<div field="memberSrc" allowSort="true" width="" dictTypeId="XD_KHCD1001" headerAlign="center">成员来源</div>
	      	-->
	     </div>
	</div>

<script type="text/javascript">
	nui.parse();
	git.mask();
	var form = new nui.Form("#form1"); 
	var grid = nui.get("datagrid1");
	var partyId = "<%=request.getParameter("partyId") %>";
	var processInstId = "<%=request.getParameter("processInstId") %>";
	var qote = "<%=request.getParameter("qote")%>" ;
	var wflow = "<%=request.getParameter("wflow") %>";
	var company;
	//认定中查看
	var o = new Object();
    o.party = new Object();
    o.party.partyId = partyId;
    o.processInstId=processInstId;
    $.ajax({
         url: "com.bos.csm.company.company.getCompanyInfo.biz.ext",
         type: 'POST',
         data: nui.encode(o),
         cache: false,
         contentType:'text/json',
         success: function (data) {
         	company = data.groupCompany;
			 if(company.status=='02'){
				nui.get("add").hide();
				nui.get("edit").hide();
				nui.get("remove").hide();
			}
			init();
         },
         error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
         }
	});
	
	if(qote==1){
	   nui.get("add").hide();
	   nui.get("edit").hide();
	   nui.get("remove").hide();
	}	
	function init() {
	  if (partyId) {
			nui.get("item.groupPartyId").setValue(partyId);
		}
		if(qote==0){
		   var json = nui.encode({"page":o.page,"partyId":partyId,"groupType":"511"});
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
     	grid.on("preload", function(e) {
     	debugger;
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
				e.data[i]['partyName']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].partyId+ '\');">'+e.data[i]['partyName']+'</a>';
			}
		});
	function add() {
            nui.open({
                url:nui.context + "/csm/company/groupCompany_member_add.jsp?partyId="+partyId+"&processInstId=<%=request.getParameter("processInstId")%>",
                title: "引入成员公司", 
                width: 800, 
            	height: 400,
            	allowResize:true,
            	showMaxButton: true,
                ondestroy: function (action) {
                    if(action=="ok"){
                        git.mask();
                        init();
                    }
                }
            });
        }
	
	function edit() {
        var row = grid.getSelected();
        if (row) {
            nui.open({
                url: nui.context + "/csm/company/groupCompany_member_edit.jsp?itemId="+row.groupMemberId+"&memPartyId="+row.corporationPartyId,
                title: "查看编辑", 
                width: 800,
        		height: 400,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                    //iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                    if(action=="ok"){
                        git.mask();
                        init();
               	 	}
                }
            });
        } else {
            alert("请选中一条记录");
        }
        
    }
        
     
    function remove() {
        var row = grid.getSelected();
        //校验该成员是否参与了业务
        var json = {"partyId":row.partyId};
   	    msg = exeRule("RCSM_0010","1",json);
   	    if(null != msg && '' != msg){
	   	     nui.alert(msg);
	   	     return;
   	     }
   	     
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	git.mask();
            	var json=nui.encode({"mem":row});
                $.ajax({
                     url: "com.bos.csm.company.member.delMember.biz.ext",//delMemberEcif
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
                    		nui.alert("删除成功!");
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
    
    function onSelectionChanged() {
		var row = grid.getSelected();
		if (row) {
			if(row.memberStatusCd == '01') {
				nui.get("remove").show();
			} else {
				nui.get("remove").hide();
			}
		}
	}
	
</script>
	
	

</body>
</html>