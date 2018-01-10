<%@page import="com.eos.data.datacontext.UserObject"%>
<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 陈川
  - Date: 2016-6-10 10:58:51
  - Description: 同业客户关键信息维护列表
-->
<head>
<title>同业客户维护</title>
<%@include file="/common/nui/common.jsp" %>
</head> 

<body>
	<% 
	UserObject tempUser = (UserObject)session.getAttribute("userObject"); 
	%>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:115%;">
<div title="同业客户" >
<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;">

	<div class="nui-dynpanel" columns="6">
	
			<label class="nui-form-label">机构名称：</label>
			<input id="item.orgNum" name="item.orgNum" class="nui-buttonEdit" enabled="false" onbuttonclick="selectOrg"/>
		
			<label>金融机构类型：</label>
		 	<input  id="item.financeEnterpriseType" name="item.financeEnterpriseType"  class="nui-dictcombobox nui-form-input" 
		 			allowInput="false"  dictTypeId="ECIF_JRJGLX01" />
			
			<label>客户名称：</label>
			<input id="item.partyName" class="nui-textbox nui-form-input" name="item.partyName"/>
   	    
   	    	<label>统一社会信用代码：</label>
			<input id="item.unifySocietyCreditNum" name="item.unifySocietyCreditNum" class="nui-textbox nui-form-input" required="false"/>
   	    	
   	    	<label>营业执照：</label>
			<input id="item.registerCd" name="item.registerCd" class="nui-textbox nui-form-input" />
			
			<label>组织机构代码：</label>
			<input id="item.orgRegisterCd" name="item.orgRegisterCd" required="false" class="nui-textbox nui-form-input"/>
   	    	
   	    	<label>Swift BIC码：</label>
   	        <input id="item.swiftBicNum" class="nui-textbox" name="item.swiftBicNum">
	</div>
	<div class="nui-toolbar" style="text-align:right;padding-right:20px;border:none;" >
	    <a class="nui-button" iconCls="icon-search" onclick="search()">查询</a>
		<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
	</div>
</div>

<div style="width:99.5%">
<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px;">
		<a id="editCust" class="nui-button" iconCls="icon-edit" onclick="edit()">编辑</a>
</div>
</div>    

<div id="grid1" class="nui-datagrid" style="width:99.5%;height:auto" 
	url="com.bos.csm.financialinstitution.financialinstitutioninfo.getFinancialListByOrgId.biz.ext" dataField="items"
	allowResize="true" showReloadButton="false"allowAlternating="true"
	sizeList="[10,15,20,50,100]" multiSelect="false" pageSize="10" sortMode="client">
	<div property="columns">
		<div type="checkcolumn" >选择</div>
		<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org" >机构名称</div>
		<div field="financeEnterpriseType" headerAlign="center" allowSort="true" dictTypeId="ECIF_JRJGLX01">金融机构类型</div>
		<div field="partyName" headerAlign="center" allowSort="true" >客户名称</div>
		<div field="unifySocietyCreditNum" headerAlign="center" allowSort="true">统一社会信用代码</div>
		<div field="registerCode" headerAlign="center" allowSort="true" >营业执照</div>
		<div field="orgRegisterCd" headerAlign="center" allowSort="true" >组织机构代码</div>
		<div field="swiftBicNum" headerAlign="center" allowSort="true" >Swift BIC码</div>
		<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">管户客户经理</div>
		<div field="createTime" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd"  >创建日期</div>
	</div>
</div>
</div>
</div>

<script type="text/javascript">
 	nui.parse();
 	git.mask();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	
	grid.on("preload",function(e){
   		if (!e.data || e.data.length < 1)
   			return;
   		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
   		
   		if(null != e.data[i].partyName){
   			e.data[i]['partyName']='<a href="#" onclick="clickCust(\''
   				+ e.data[i].partyId+","+e.data[i].partyNum
   				+ '\');return false;" value="'
   				+ e.data[i].partyId
   				+ '">'+e.data[i]['partyName']+'</a>';
   		}		
   			if(null != e.data[i].englishCustomerName){
   			
	   			e.data[i]['englishCustomerName']='<a href="#" onclick="clickCust(\''
	   				+ e.data[i].partyId+","+e.data[i].partyNum
	   				+ '\');return false;" value="'
	   				+ e.data[i].partyId
	   				+ '">'+e.data[i]['englishCustomerName']+'</a>';	
   			
   			}	
   			
   			
   		}
   });
   
   	initPage();
    function initPage(){
    	var orgCode = <%="\""+tempUser.getAttributes().get("orgcode")+"\"" %>;
	    var orgName = <%="\""+tempUser.getUserOrgName() +"\"" %>;
    	nui.get("item.orgNum").setValue(orgCode);
    	nui.get("item.orgNum").setText(orgName);
    }
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
        git.unmask();
    }
    search();
    
    function reset(){
		form.reset();
	}
	
	//机构选择
	function selectOrg(){
	
		var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/sys/select_org_tree.jsp",
            showMaxButton: true,
            title: "选择机构",
            width: 350,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = nui.clone(data);
                    if (data) {
                    	self.orglevel=data.orglevel;
                        btnEdit.setValue(data.orgid);
                        btnEdit.setText(data.orgname);
                    }
                }
            }
        });      
	}
	
	/**
	*新增同业客户
	*/
	function add(){
		
		nui.open({
	            url: nui.context + "/csm/financialinstitution/csm_financialinstitution_keyinfo.jsp",
	            showMaxButton: true,
	            title: "新增客户信息",
	            width: 800,
	            height: 500,
	            onload: function(e){
	            	var iframe = this.getIFrameEl();
	            	var text = iframe.contentWindow.document.body.innerText;
	            	//alert(text);
	            },
	            ondestroy: function (action) {
	                search();
	            }
      	 	 });
	}
    
    //客户详细信息超链接事件	
	function clickCust(partyId,partyNum){
		var ps = partyId.split(",");
		partyId = ps[0];
		partyNum = ps[1];	
		var url = nui.context + "/csm/financialinstitution/csm_financialinstitution_tree.jsp?partyId="
	        + partyId+"&qote=1&partyNum="+partyNum;
	        //查看
	    nui.open({
	            url: url,
	            showMaxButton: true,
	            title: "查看客户信息",
	            width: 1024,
	            height: 768,
	            state:"max",
	            onload: function(e){
	            	var iframe = this.getIFrameEl();
	            	var text = iframe.contentWindow.document.body.innerText;
	            	//alert(text);
	            },
	            ondestroy: function (action) {
	                //search();
	            }
      	 	 });
	}
	
	
	/**
	*同步客户ECIF号
	*/
	function SynchronizationEcif(){
     		 var row = grid.getSelected();
     		 var json = nui.encode({"partyId":row.partyId,"ecifPartyNum":row.ecifPartyNum});
     		 if(row.areaType.indexOf("2")>=0){//境外客户
     		 	if(row.ecifPartyNum==null||row.ecifPartyNum=="null"){//是否有ECIF编号
     		 		nui.prompt("请输入ECIF编号进行同步","同步ECIF",function(action,text){
     		 			if(action=="ok"){
     		 				if(text!=null||text!="null"){
     		 					if(text.length>10){
     		 						alert("输入错误");
     		 						return;
     		 					}
     		 					json = nui.encode({"partyId":row.partyId,"ecifPartyNum":text});
     		 					
     		 				}
     		 				git.mask();
	     		 			$.ajax({
							            url: "com.bos.csm.inteface.ecif.SynchronizationEcif.biz.ext",
							            type: 'POST',
							            data: json,
							            cache: false,
							            contentType:'text/json',
							            success: function (text) {
							            	git.unmask();
							                if(text.ecifNum){
							                	alert("同步成功！当前客户ECIF编号为："+text.ecifNum);
							                }else{
							                	if(text.errMsg){
								                	alert(text.errMsg);
								                }else{
								                	alert("当前客户暂无ECIF信息");
								                }
							                }
							               
							            },
							            error: function (jqXHR, textStatus, errorThrown) {
							                git.unmask();
							                nui.alert(jqXHR.responseText);
							            }
							});			
	     		 			}
     		 						     		 		
     		 		});
				}else{//境外客户
     		 				git.mask();
     		 				$.ajax({
						            url: "com.bos.csm.inteface.ecif.SynchronizationEcif.biz.ext",
						            type: 'POST',
						            data: json,
						            cache: false,
						            contentType:'text/json',
						            success: function (text) {
						            	git.unmask();
						                 if(text.ecifNum){
							                	alert("同步成功！当前客户ECIF编号为："+text.ecifNum);
							                }else{
							                	if(text.errMsg){
								                	alert(text.errMsg);
								                }else{
								                	alert("当前客户暂无ECIF信息");
								                }
							                }
						               
						            },
						            error: function (jqXHR, textStatus, errorThrown) {
						                git.unmask();
						                nui.alert(jqXHR.responseText);
						            }
							     });
     		 	}
					
     		 }else{//境内客户
     		 				git.mask();
     		 				$.ajax({
						            url: "com.bos.csm.inteface.ecif.SynchronizationEcif.biz.ext",
						            type: 'POST',
						            data: json,
						            cache: false,
						            contentType:'text/json',
						            success: function (text) {
						            	git.unmask();
						                if(text.ecifNum){
							                	alert("同步成功！当前客户ECIF编号为："+text.ecifNum);
							                }else{
							                	if(text.errMsg){
								                	alert(text.errMsg);
								                }else{
								                	alert("当前客户暂无ECIF信息");
								                }
							                }
						               
						            },
						            error: function (jqXHR, textStatus, errorThrown) {
						                git.unmask();
						                nui.alert(jqXHR.responseText);
						            }
							     });
     		 }
	         
     }
		
	//编辑客户信息
    function edit() {
        var row = grid.getSelected();
        if (row) {
	        var url = nui.context+"/csm/keyword/csm_financialinstitution_keyword.jsp?partyId="+row.partyId+"&qote=2&partyNum="+row.partyNum;
			//修改
			nui.open({
	            url: url,
	            showMaxButton: true,
	            title: "编辑客户信息",
	            width: 800,
	            height: 300,
	            onload: function(e){
	            	var iframe = this.getIFrameEl();
	            	var text = iframe.contentWindow.document.body.innerText;
	            },
	            ondestroy: function (action) {
	                search();
	            }
      	 	 });
        } else {
            alert("请选中一条记录");
        }
        
    }
    
    function query() {
        var row = grid.getSelected();
        if (row) {
	        var url = nui.context+"/csm/financialinstitution/csm_financialinstitution_tree.jsp?partyId="+row.partyId+"&qote=1&partyNum="+row.partyNum;
			//查看
			nui.open({
	            url: url,
	            showMaxButton: true,
	            title: "查看客户信息",
	            width: 1024,
	            height: 768,
	            state:"max",
	            onload: function(e){
	            	var iframe = this.getIFrameEl();
	            	var text = iframe.contentWindow.document.body.innerText;
	            	//alert(text);
	            },
	            ondestroy: function (action) {
	                //search();
	            }
      	 	 });
        } else {
            alert("请选中一条记录");
        }
        
    }
    
     //校验组织机构代码是否唯一
   	   function checkOrgNumUnique(e){
   	      git.mask();
   	      if(e.isValid){
   	      	var json = {"dataType":"com.bos.dataset.csm.TbCsmFinancialInstitution","property":"orgnNum",
   	      		"validateValue":e.value};
   	      	$.ajax({
   	      	  	url:"com.bos.utp.tools.CommonUtil.uniqueValidate.biz.ext",
   	      	  	type: 'POST',
   	      	  	data: json,
   	      	  	cache: false,
   	      	  	async: false,
   	         	success: function(text){
   	          	 	git.unmask();
   	          	 	var uCount = text.isUnique;
   	          	 	if(uCount=="0"){
   	          			nui.alert("该客户的组织机构代码已经存在!");
   	          			e.isValid = false;
   	          	 	}else if(uCount=="-1"){
   	          	 		nui.alert("组织机构代码查询异常!");
   	                	e.isValid = false;
   	          	 	}
   	          	},
   	            error:function(jqXHR, textStatus, errorThrown){
   	          		git.unmask();
   	          		nui.alert(jqXHR.responseText);
   	          		e.isValid = false;
   	          }
   	     });
   	    } 
   	  }
   	  
   	  //在选择区域类型为境外时：swift 码和swift行名必须输入
   	  function checkSwift(){
   	     var zoneTypeCd = nui.get("financialInstitution.zoneTypeCd").getValue();
   	  	 var swiftCode = nui.get("financialInstitution.swiftBicNum");
   	  	 var swiftName = nui.get("financialInstitution.swiftName");
   	  	 
   	     if(zoneTypeCd=="02"){//选择区域类型为境外时，swift码和swift行名必须输入
   	     	swiftCode.required=true;
   	     	swiftName.required=true;
   	     }else{
   	     	swiftCode.required=false;
   	     	swiftName.required=false;
   	     }
   	  }
   	  
   	  //必须输入英文加数字
   	  function onEnglishAndNumberValidation(e) {
   	  	if(null != e.value && '' != e.value){
	         if (e.isValid) {
	          if (isEnglishAndNumber(e.value) == false) {
	               e.errorText = "必须输入英文+数字";
	               e.isValid = false;
	            }
	         }
   	  	}
      }
      /* 是否英文+数字 */
      function isEnglishAndNumber(v) {
         var re = new RegExp("^[0-9a-zA-Z\_]+$");
         if (re.test(v)) return true;
         return false;
      }
    
    function onSelectionChanged() {
        	git.mask();
        	var row = grid.getSelected();
        	var json=nui.encode({"partyId": row.partyId});
        	 if (row) 
            {
                nui.ajax(
                {
                    url: "com.bos.csm.pub.getGeneralityInfo.verifyManageRight.biz.ext",//上部分需要的逻辑流
	                type: 'POST',
	                data: json,
	                contentType:'text/json',
                    success: function (text){
                    if(text.flag){
			        	git.unmask();
			        		nui.get("editCust").show();
			        	} else {
			               nui.get("editCust").hide();
			            }
                   },
                   error: function () {
                    	git.unmask();
                    	alert("操作失败！");
                    }
                   
            	
            });
        }
        }
        
       //金融机构类型
      function selectFinancialType(e) {
        var btnEdit = this;
        nui.open({
            url: nui.context + "/pub/dict/code_tree.jsp?dicttypeid=CDKH0023",
            title: "选择字典项",
            width: 800,
            height: 450,
            ondestroy: function (action) {            
                if (action == "ok") {
                    var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.currentNode;
                    data = nui.clone(data);
                    if (data) {
                    	nui.get("item.financeEnterpriseType").setValue(data.dictid);
                        nui.get("item.financeEnterpriseType").setText(data.dictname);
                        
                    }
                }
                if (action == "clear") { //清空选择的内容
                	btnEdit.setValue("");
                	btnEdit.setText("");
            	}
        	}
        });            
	}
</script>
</body>

</html>