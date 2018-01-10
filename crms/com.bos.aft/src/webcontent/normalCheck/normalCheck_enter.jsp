<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): xiaoxia
  - Date: 2015-06-04
  - Description:
-->
<head>
<title>日常检查</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<center>
<fieldset>
<legend> <span>日常检查客户列表</span> </legend>
	<div id="form" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px"  class="nui-form">
		<input name="map.partyId" id="map.partyId" required="false" class="nui-hidden nui-form-input"/>
		<div class="nui-dynpanel" columns="6">
			
			<label>客户名称：</label>
			<input name="map.partyName" id="map.partyName" required="false" class="nui-textbox nui-form-input"  />
			
			<!-- <label>营业执照：</label>
			<input name="map.registrCd" id="map.registrCd" required="false" class="nui-textbox nui-form-input"  />
	
			<label>组织机构代码：</label>
			<input name="map.orgRegisterCd" id="map.orgRegisterCd" required="false" class="nui-textbox nui-form-input"  /> -->
			
			<label>证件类型：</label>
			<input id="map.certType" name="map.certType"  class="nui-dictcombobox nui-form-input" dictTypeId="CDKH0002"  allowInput="false" />
			<!-- <input id="map.certType" name="map.certType"  class="nui-dictcombobox nui-form-input"  textField="dictname" valueField="dictid"
				dictTypeId="CDKH0002"  allowInput="false" /> -->
			
			<label>证件号码：</label>
			<input id="map.certNum" name="map.certNum" required="false" class="nui-textbox nui-form-input"  onblur="checkCertCode"/> 
			
			<!-- <label>中征码：</label>
			<input name="map.middelCode" id="map.middelCode" required="false" class="nui-textbox nui-form-input" /> -->
			
			<!-- <a class="nui-button"onclick="query">查询</a> -->
			
		</div>
		
		<div class="nui-toolbar" style="text-align:right;padding-top:15px;padding-right:25px;"borderStyle="border:0;">
			<a class="nui-button" iconCls="icon-search" onclick="query()">查询</a>
			<a class="nui-button" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
	
		<div class="nui-toolbar" style="margin-top:7px;width:99.5%">
			<label class="nui-form-label">日常检查类型：</label>
			<input name="checkType" class="nui-dictcombobox nui-form-input" dictTypeId="XD_RCJC0001" id="checkType" 
					style="margin-right:5px;height:21px;width:13%"/>
			<a class="nui-button" id="btnCreate" onclick="add" style="margin-right:27px;height:21px">创建</a>
		</div>
		
		<div id="grid" class="nui-datagrid" sortMode="client"
		    url="com.bos.aft.normalCheck.findCusList.biz.ext" dataField="items"
		    allowAlternating="true" multiSelect="false" showEmptyText="true" allowResize="true"
		    emptyText="没有查到数据" showReloadButton="false"
		    onrowdblclick="" allowCellEdit="true" allowCellSelect="true"
		    sizeList="[10,20,50,100]" pageSize="10">
		    <div property="columns">
		        <div type="checkcolumn">选择</div>             
		        <div type="indexcolumn" width="50px;">序号</div>
		    <!--   	<div field="PARTY_NUM" headerAlign="center" align="center">客户编号</div> -->
				<div field="PARTY_NAME" headerAlign="center" align="center">客户名称</div>
				<!-- <div field="REGISTR_CD" headerAlign="center" align="center">营业执照</div>
				<div field="ORG_REGISTER_CD" headerAlign="center" align="center">组织机构代码</div> -->
				<div field="CERT_TYPE" headerAlign="center" align="center" dictTypeId="CDKH0002">证件类型</div>
		        <div field="CERT_NUM" align="center" headerAlign="center" >证件号码</div>  
		        <div field="AMT" headerAlign="center" align="right" dataType="currency">贷款金额</div>
		        <div field="BALANCE" headerAlign="center" align="right" dataType="currency">贷款余额</div>
		        <!-- <div field="MIDDEL_CODE" align="center" headerAlign="center" >中征码</div>  -->
		        <div field="CREATE_DATE" headerAlign="center" align="center">上次日常检查日期</div>
		    </div>
		</div>
		
	</div>
</fieldset>
</center>

<script type="text/javascript">
	nui.parse();
	var checkType = nui.get("checkType");
	var form = new nui.Form("#form");
	var grid = nui.get("grid");  //客户列表
	query();
	function query(){
		var partyId=nui.get("map.partyId");
		var partyId1="<%=request.getParameter("partyId")%>";
		if(null!=partyId1&&"null"!=partyId1&&""!=partyId1){
			partyId.setValue(partyId1);
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
	       				+ e.data[i].PARTY_ID+","+e.data[i].PARTY_NUM+","+e.data[i].CORP_CUSTOMER_TYPE_CD+","+e.data[i].PARTY_TYPE_CD
	       				+ '\');return false;" value="'
	       				+ e.data[i].PARTY_ID
	       				+ '">'+e.data[i]['PARTY_NAME']+'</a>';
	       				
       			}else{
       			
       				e.data[i]['PARTY_NAME']='<a href="#" onclick="clickCust(\''
	       				+ e.data[i].PARTY_ID+","+e.data[i].PARTY_NUM+","+e.data[i].CORP_CUSTOMER_TYPE_CD+","+e.data[i].PARTY_TYPE_CD
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
		var corpCustomerTypeCd = ps[2];
		var partyTypeCd = ps[3];
        if(partyTypeCd=="01") {
	        var infourl = nui.context + "/csm/corporation/csm_corporation_tree.jsp?partyId="
	            + partyId+"&partyNum="+partyNum+"&cusType="+corpCustomerTypeCd+"&qote=1";
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
	
	function add(){
	
		var row = grid.getSelected();
		if (null == row) {
			nui.alert("请选择一个客户");
			return false;
		}
		
		if(checkType.getValue()=="") {
			return nui.alert("请选择日常检查类型");
		}
		
		if(row.BALANCE<5000000) {
			if(checkType.getValue()=="06" || checkType.getValue()=="07" || checkType.getValue()=="08" || checkType.getValue()=="09" || checkType.getValue()=="10") {
				return nui.alert( "贷款余额500万元以下请选择检查表类型！");
			}
		}
		
		if(row.BALANCE>=5000000) {
			if(checkType.getValue()=="01" || checkType.getValue()=="02" || checkType.getValue()=="03" || checkType.getValue()=="04" || checkType.getValue()=="05") {
				return nui.alert("贷款余额500万元（含）以上请选择检查报告类型！");		
				}
		}
		
		if(row.PARTY_TYPE_CD=="02") {
			if(checkType.getValue()!="01" && checkType.getValue()!="10" &&  checkType.getValue()!="11") {
				return nui.alert("个人客户请选择个人检查表或检查报告类型！");
			}
		}
		
	 	 if(row.PARTY_TYPE_CD=="01") {
			if(checkType.getValue()=="01" || checkType.getValue()=="10" || checkType.getValue()=="11") {
				return nui.alert("企业类客户请选择企业类检查表或检查报告类型！");
			}
		}  
		
		nui.get("btnCreate").setEnabled(false);
		
		var reportName = row.PARTY_NAME+" "+row.SYS_DATE+" 日常检查";
		var json=nui.encode({"partyId":row.PARTY_ID,"partyTypeCd":row.PARTY_TYPE_CD,"isSmall":"0","checkType":checkType.getValue(),"reportName":reportName});
		$.ajax({
	        url: "com.bos.aft.normalCheck.createNormalCheck.biz.ext",
	        type: 'POST',
	        data: json,
	        contentType:'text/json',
	        cache: false,
	        success: function (data) {
            	nui.get("btnCreate").setEnabled(true);
            	
            	//if(data.flag=="1"){
                	//return alert(data.msg);
                //}
	        	if(data.msg){
                	if(data.msg != "创建成功！") {//创建失败
	        			alert(data.msg);
	        		}else{//创建成功
	        			//git.go(nui.context+"/crt/con_info/con_tree.jsp?contractId="+data.tbConContractInfo.contractId+"&contractType=02&amountDetailId="+row.AMOUNT_DETAIL_ID,parent);
	           			nui.open({//传值到tree页面
		            		url: nui.context+"/aft/normalCheck/normalCheck_tree.jsp?normalCheckId="+data.tbAftNormalCheck.normalCheckId+"&partyId="+row.PARTY_ID+"&checkType="+data.tbAftNormalCheck.checkType+"&processInstId="+data.processInstId+"&proFlag=1",
		            		showMaxButton: false,
		            		title: "日常检查",
		            		width: 1024,
		            		height: 768,
		            		state:"max",
		            		ondestroy: function (action) {
		            			query();//重新刷新页面
		            		}
	        			});
	        			
	        		}
                }
			},
	        error: function (jqXHR, textStatus, errorThrown) {
	            alert(jqXHR.responseText);
	            nui.get("btnCreate").setEnabled(true);
	            //git.unmask();
	        }
        });	
	}
	
	//初始化自然人证件类型
	/* function init(){
 		git.mask();
	    var json = nui.encode({parentId:"10000"});
	     $.ajax({
	        url: "com.bos.csm.pub.getDict.getCertTypeDict.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
	        contentType:'text/json',
	        success: function (text) {
	            git.unmask();
	            nui.get("map.certType").setData(text.levels);
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            git.unmask();
	            nui.alert(jqXHR.responseText);
	        }
	     });
	}
    init();	 */
    
    //重置
	function reset(){
		form.reset();
	}
</script>
</body>
</html>