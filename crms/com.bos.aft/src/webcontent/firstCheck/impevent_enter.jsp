<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-06-04
  - Description:
-->
<head>
<title>重大事件</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<fieldset>
<legend> <span>重大事件</span> </legend>
	<div id="form" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px"  class="nui-form">
 	
		<div class="nui-dynpanel" columns="6">
			
			<label>客户名称：</label>
			<input name="map.partyName" id="map.partyName" required="false" class="nui-textbox nui-form-input"  />
			
			<label>证件类型：</label>
			<input id="map.certType" name="map.certType"  class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002"  allowInput="false" />
			<!-- <input id="map.certType" name="map.certType"  class="nui-dictcombobox nui-form-input"  textField="dictname" valueField="dictid"
				dictTypeId="CDKH0002"  allowInput="false" /> -->
			
			<label>证件号码：</label>
			<input id="map.certNum" name="map.certNum" required="false" class="nui-textbox nui-form-input"  onblur="checkCertCode"/> 
			
			
		</div>
		
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
			<a class="nui-button" iconCls="icon-search" onclick="edit()">编辑</a>
			<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
	
		 
		
		<div id="grid" class="nui-datagrid" sortMode="client"
		    url="com.bos.aft.firstCheck.impeventCusList.biz.ext" dataField="items"
		    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
		    emptyText="没有查到数据" showReloadButton="false"
		    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
		    sizeList="[10,20,50,100]" pageSize="10">
		    <div property="columns">
		        <div type="checkcolumn">选择</div>             
		        <div type="indexcolumn" width="50px;">序号</div>
 				<div field="PARTY_NAME" headerAlign="center" align="center">客户名称</div>
				<div field="CERT_TYPE" headerAlign="center" align="center" dictTypeId="CDKH0002">证件类型</div>
		        <div field="CERT_NUM" align="center" headerAlign="center" >证件号码</div> 
		        <div field="AMT" headerAlign="center" align="right" dataType="currency">授信金额</div>
		        <div field="JJYE" headerAlign="center" align="right" dataType="currency">授信余额</div>
				<!--  <div field="xz" headerAlign="center" align="center">操作</div>-->
		    </div>
		</div>
		
	</div>
</fieldset>
</center>

<script type="text/javascript">
	nui.parse();
	var form = new nui.Form("#form");
	var grid = nui.get("grid");  //借据列表
	query();
	function query(){
		var receiptNum=nui.get("map.receiptNum");
		var receiptNum1="<%=request.getParameter("receiptNum")%>";
		if(null!=receiptNum1&&"null"!=receiptNum1&&""!=receiptNum1){
		receiptNum.setValue(receiptNum1);
		}
		var o = form.getData();
		grid.load(o);
		grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){
       			if(null !=e.data[i].PARTY_NAME && ''!=e.data[i].PARTY_NAME){
	       			//客户链接
	       			e.data[i]['PARTY_NAME']='<a href="#" onclick="clickCust(\''
	       				+ e.data[i].PARTY_ID+","+e.data[i].PARTY_NUM+","+e.data[i].PARTY_TYPE_CD
	       				+ '\');return false;" value="'
	       				+ e.data[i].PARTY_ID
	       				+ '">'+e.data[i]['PARTY_NAME']+'</a>';
	       				
	       			e.data[i]['xz']='<a href="#" onclick="clickxz(\''
	       				+ e.data[i].PARTY_ID+","+e.data[i].PARTY_NUM+","+e.data[i].PARTY_TYPE_CD
	       				+ '\');return false;" value="'
	       				+ e.data[i].PARTY_ID
	       				+ '">'+'编辑</a>';
	       				
       			}else{
       			
       				e.data[i]['PARTY_NAME']='<a href="#" onclick="clickCust(\''
	       				+ e.data[i].PARTY_ID+","+e.data[i].PARTY_NUM+","+e.data[i].PARTY_TYPE_CD
	       				+ '\');return false;" value="'
	       				+ e.data[i].PARTY_ID
	       				+ '">'+e.data[i]['ENGLISH_NAME']+'</a>';
       			}
       		}
       });
	}
	
	 
	
 
	
	function clickCust(e){
		var ps = e.split(",");
		var partyId = ps[0];
		var partyNum = ps[1];
 		var partyTypeCd = ps[2];
        if(partyTypeCd=="01") {
	        var infourl = nui.context + "/csm/corporation/csm_corporation_tree.jsp?partyId="
	            + partyId+"&partyNum="+partyNum+"&qote=1";
        }else {
	        var infourl = nui.context + "/csm/natural/natural_tree.jsp?partyId="
	            + partyId+"&partyNum="+partyNum+"&qote=1";
        }
        
      nui.open({
            url:infourl,
            showMaxButton: true,
            title: "",
            width: 1024,
            height: 768,
            state:"max",
            onload: function(e){
            	var iframe = this.getIFrameEl();
            	var text = iframe.contentWindow.document.body.innerText;
            },
            ondestroy: function (action) {
                query();
            }
  	  });	
            
	}
	
	function clickxz(e){
		var ps = e.split(",");
		var partyId = ps[0];
		var partyNum = ps[1];
 		var partyTypeCd = ps[2];
         
	        var infourl = nui.context + "/csm/corporation/csm_impornant_event_list.jsp?partyId="
	            + partyId+"&partyNum="+partyNum;
         
      nui.open({
            url:infourl,
            showMaxButton: true,
            title: "",
            width: 1024,
            height: 768,
             onload: function(e){
            	var iframe = this.getIFrameEl();
            	var text = iframe.contentWindow.document.body.innerText;
            },
            ondestroy: function (action) {
                query();
            }
  	  });	
            
	}
	
	  function edit() {
        var row = grid.getSelected();
        if (row) {
            nui.open({
                url: nui.context + "/csm/corporation/csm_impornant_event_list.jsp?partyId="+row.PARTY_ID+"&partyNum="+row.PARTY_NUM+"&partyTypeCd="+row.PARTY_TYPE_CD,
                title: "编辑", 
                width: 800,
        		height: 400,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                },
                ondestroy: function (action) {
                    if(action=="ok"){
                        git.mask();
                        search();
               	 	}
                }
            });
        } else {
            alert("请选中一条记录");
        }
        
    }
	

    
    //重置
	function reset(){
		form.reset();
	}
</script>
</body>
</html>