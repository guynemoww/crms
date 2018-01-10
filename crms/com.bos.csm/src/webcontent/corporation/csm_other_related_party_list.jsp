<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2015-05-20
  - Description:关联关系维护
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
<div id="form1" style="width:99.5%;height:auto;overflow:hidden;">
	<input type="hidden" name="sqlName" value="com.bos.csm.corporation.tbCsmRelatedInfo.otherRelatedPartyList" class="nui-hidden" />
	<input name="item.partyId" id="item.partyId" class="nui-hidden" />
	<input name="item.custType" id="item.custType" class="nui-hidden" />
	<div id="crud"  class="nui-toolbar" style="border-bottom:0;text-align:left">
		<a id="add" class="nui-button" iconCls="icon-add" onclick="add()">增加</a>
		<a id="edit"  class="nui-button" iconCls="icon-edit" onclick="edit(0)">编辑</a>
		<a id="query" class="nui-button" iconCls="icon-zoomin" onclick="edit(1)">查看</a>
		<a id="remove" class="nui-button" iconCls="icon-remove" onclick="remove()">删除</a>
		<%--<a id="find" class="nui-button" iconCls="icon-node" onclick="CreateView">关系视图</a>--%>
	</div>	
</div>
<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto" 
	url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"allowAlternating="true"
	allowResize="true" showReloadButton="false" 
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="15" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="otherRelatedPartyId" headerAlign="center" allowSort="true" class="nui-hidden" >关联编号</div>
		<div field="partyName" headerAlign="center" allowSort="true" >关联客户名称</div>
		<div field="otherRelatedPartyId" headerAlign="center" allowSort="true" >关联客户号</div>
		<div field="certType" headerAlign="center" allowSort="true" dictTypeId="XD_ZJLX0001" >证件类型</div>
		<div field="certNum" headerAlign="center" allowSort="true" >证件号码</div>
		<div field="relFrom" headerAlign="center" allowSort="true" dictTypeId="XD_KHCD0298">认定类型</div>
		<div field="relatedCd" headerAlign="center" allowSort="true" dictTypeId="XD_GLGX0003">关联关系</div>
		<div field="createTime" headerAlign="center" allowSort="true" dateformat="yyyy-MM-dd">成为关系人日期</div>
	</div>
</div>

<script type="text/javascript">
 	nui.parse();
 	git.mask();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	var partyId = "<%=request.getParameter("partyId") %>";
	var qote = "<%=request.getParameter("qote")%>" ;
    var ecifPartyNum = "<%=request.getParameter("ecifPartyNum")%>" 
		
	if(qote==1){
		nui.get("add").hide();
	    nui.get("edit").hide();
	    nui.get("remove").hide();
	}		
    function search() {
		if (partyId) {
			nui.get("item.partyId").setValue(partyId);
			nui.get("item.custType").setValue("6");
		}
		debugger;
	if(qote!=1){
		   var json = nui.encode({"ecifPartyNum":ecifPartyNum,"partyId":partyId});
		    $.ajax({
         url: "com.bos.csm.pub.ibatis.corpgetItemEcifRelNatural.biz.ext",
         type: 'POST',
         data: json,
         cache: false,
         async:false,
         contentType:'text/json',
         success: function (data) {
         if(data.map.msg!="AAAAAAA"){
    //     nui.alert(data.map.msgg);
         }else{
         
         }
         },
         error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
         }
	});
	}	
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
   		git.unmask();
    }
    search();
    
    function add() {
        nui.open({
            url: nui.context + "/csm/corporation/csm_other_related_party_add.jsp?partyId="+partyId+"&custType=6",
            title: "新增", 
            width: 850, 
        	height: 450,
        	allowResize:true,
        	showMaxButton: true,
            ondestroy: function (action) {
                if(action=="ok"){
                     git.mask();
                     search();
                }
            }
        });
    }
        
    function edit(v) {
    	var url = "";
        var row = grid.getSelected();
        var title ="编辑";
        if('1' == v){
        	title = "查看";
        }
        if (row) {
        	if('1' == v){
   
        	 url = nui.context + "/csm/corporation/csm_other_related_party_edit6.jsp?itemId="+row.otherRelatedPartyId+"&view="+v+"&custType="+row.custType;
        


        	}else{
        	debugger;

        	 url = nui.context + "/csm/corporation/csm_other_related_party_edit6.jsp?itemId="+row.otherRelatedPartyId+"&view="+v+"&custType="+row.custType;
}
        	
            nui.open({
                url: url,
                title: title, 
                width: 850,
        		height: 450,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                    //iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                    if(action=="ok"){
                		git.unmask();
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
            	git.mask();
            	var json=nui.encode({"rela":{"otherRelatedPartyId":
            		row.otherRelatedPartyId}});
                $.ajax({
                     url: "com.bos.csm.corporation.TbCsmRelatedParty.delTbCsmRelatedPartyEcif.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	git.unmask();
                    	       if(text.map.msg!="AAAAAAA"){
                              nui.alert(text.map.msgg);
                              	return;
                                }
                    	if (text.msg) {
                    		nui.alert(text.msg);
                    		return;
                    	}
                        git.unmask();
                         search();
                    },
                    error: function () {
                    	git.unmask();
                    	alert("操作失败！");
                    }
                });
            }); 
        } else {
            nui.alert("请选中一条记录");
        }
    }
    
     function CreateView(){
            nui.open({
                url: nui.context + "/csm/ReTest/line.jsp?partyId="+partyId,
                title: "关联关系视图", 
                width: 800,
        		height: 500,
        		state:"max",
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    //iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                    if(action=="ok"){
						 git.mask();
						 search();
               	 	}
                }
            });
    }
    
/*     function onSelectionChanged() {
	var row = grid.getSelected();
	
		if (row) {
			if (qote != 1) {
				if (row.relFrom == '1') {
					nui.get("edit").show();
					nui.get("remove").show();

				} else {

					nui.get("edit").hide();
					nui.get("remove").hide();
				}
			}
		}
	} */
</script>
</body>
</html>
