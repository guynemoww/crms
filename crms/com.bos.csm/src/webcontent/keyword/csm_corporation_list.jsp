<%@page import="com.eos.data.datacontext.UserObject"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<!-- 
  - Author(s): chenchuan
  - Date: 2016-5-10
  - Description:TB_CSM_BOND_INFO, com.bos.dataset.csm.TbCsmBondInfo
-->
<head>
<title>公司客户</title>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
	<% 
	UserObject tempUser = (UserObject)session.getAttribute("userObject"); 
	%>
<div id="tabs1" class="nui-tabs" activeIndex="0" style="width:100%;height:auto;">
<div title="公司客户" >
	<div id="form1" class="nui-form" style="width:99.5%;height:auto;overflow:hidden;" >
		<input id="item.userNum" name="item.userNum"  class="nui-hidden" value="<%=tempUser.getUserId() %>"/>
		<div class="nui-dynpanel" columns="6">
		
			<label class="nui-form-label">机构名称：</label>
			<input id="item.orgNum" name="item.orgNum" class="nui-buttonEdit" enabled="false" onbuttonclick="selectOrg"/>
		
			<label>客户性质：</label>
			<input id="item.corpCustomerTypeCd" name="item.corpCustomerTypeCd"
			class="nui-dictcombobox nui-form-input" dictTypeId="XD_KHCD0252" />
			
			<label>客户名称：</label>
			<input name="item.partyName" required="false" class="nui-textbox nui-form-input"  />
			
			<label>统一社会信用代码：</label>
			<input id="item.unifySocietyCreditNum" name="item.unifySocietyCreditNum" class="nui-textbox nui-form-input" required="false"/>
			
			<label>营业执照：</label>
			<input id="item.registerCd" name="item.registerCd" class="nui-textbox nui-form-input" required="false"/>
			
			<label>组织机构代码：</label>
			<input id="item.orgRegisterCd" name="item.orgRegisterCd" required="false" class="nui-textbox nui-form-input"/>
			
			<label>中征码：</label>
			<input id="item.middelCode" name="item.middelCode" required="false" class="nui-textbox nui-form-input" vtype="int;minLength:16;maxLength:16"/>
		
		</div>
		<div class="nui-toolbar" style="text-align:right;border:none" >
		    <a class="nui-button" style="margin-right:5px;height:21px;" iconCls="icon-search" onclick="search()">查询</a>
			<a class="nui-button" style="margin-right:23px;height:21px" iconCls="icon-reset" onclick="reset()">重置</a>
		</div>
	</div>
	<div style="width:99.5%">
		<div class="nui-toolbar" style="border-top:1px solid #8ba0bc;border-bottom:none;text-align:left;margin-top:7px">
			<a id="editCust" class="nui-button" iconCls="icon-edit" onclick="edit()">编辑</a>
		</div>
	</div>

	<div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto;"sortMode="client"
		  	url="com.bos.csm.corporation.corporation.getCorporationListByOrgId.biz.ext" dataField="items"
		    allowAlternating="true" multiSelect="false" showEmptyText="true" showPager="true"
		    emptyText="没有查到数据" showReloadButton="false" showColumnsMenu="true"
		    allowCellEdit="true" allowCellSelect="true"
		    sizeList="[10,20,50,100]" pageSize="10">
		<div property="columns">
			<div type="checkcolumn" >选择</div>
			<div field="orgNum" headerAlign="center" allowSort="true" dictTypeId="org" >机构名称</div>
			<div field="partyName" headerAlign="center" allowSort="true" autoEscape="false" >客户名称</div>
			<div field="corpCustomerTypeCd" headerAlign="center" allowSort="true" dictTypeId="XD_KHCD0252">客户性质</div>
			<div field="unifySocietyCreditNum" headerAlign="center" allowSort="true">统一社会信用代码</div>
			<div field="registerCd" headerAlign="center" allowSort="true" >营业执照</div>
			<div field="orgRegisterCd" headerAlign="center" allowSort="true" >组织机构代码</div>
			<div field="middelCode" headerAlign="center" allowSort="true" >中征码</div>
			<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user">管户客户经理</div>
			<div field="createTime" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd" >创建日期</div>
		</div>
	</div>
</div>
</div>

	<script type="text/javascript">
 	nui.parse();
 	git.mask();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("datagrid1");
	
	initPage();
	search();
	
	function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    	git.unmask();
    }
    
    function initPage(){
    	var orgCode = <%="\""+tempUser.getAttributes().get("orgcode")+"\"" %>;
	    var orgName = <%="\""+tempUser.getUserOrgName() +"\"" %>;
    	nui.get("item.orgNum").setValue(orgCode);
    	nui.get("item.orgNum").setText(orgName);
    }
	
    function reset(){
		form.reset();
	}
	
 	grid.on("preload", function(e) {
			if (!e.data || e.data.length < 1) {
				return;
			}
			for (var i = 0; i < e.data.length; i++) {//nui.alert(nui.encode(e.data[i]));
				e.data[i]['partyName']='<a href="#" onclick="toGoCustDetail(\''+ e.data[i].partyId+ '\');">'+e.data[i]['partyName']+'</a>';
			}
	});  
   	
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
	
    	
	function clickCust(partyId,corpCustomerTypeCd,partyNum){
		var ps = partyId.split(",");
		partyId = ps[0];
		partyNum = ps[1];
		var url = nui.context + "/csm/corporation/csm_corporation_tree.jsp?partyId=" 
			+ partyId+"&qote=1&partyNum="+partyNum;

        //客户性质为企业客户
       	url += "&cusType=" + corpCustomerTypeCd;
	    nui.open({
	            url:url,
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
	
	function add(){
		nui.open({
            url: nui.context + '/csm/corporation/csm_key_messages_add.jsp',
            showMaxButton: true,
            title: "添加公司客户",
            width: 800,
            height: 500,
            onload: function(e){
            	var iframe = this.getIFrameEl();
            	var text = iframe.contentWindow.document.body.innerText;
            },
            ondestroy: function (action) {
              if(action=="ok"){
              	search();
              }
            }
        });
	}
	
	//qote=2 修改权限
    function edit() {
        var row = grid.getSelected();
        if (row) {
	       	var url = "/csm/keyword/csm_corporation_keyword.jsp?partyId="+row.partyId+"&qote=2"+"&partyNum="+row.partyNum;
	        //客户性质为企业客户
	       	var  custType = row.corpCustomerTypeCd;
	       	url += "&cusType=" + custType;
			nui.open({
	            url: nui.context + url,
	            showMaxButton: true,
	            title: "修改客户信息",
	            width: 800,
	            height: 400,
	            onload: function(e){
	            	var iframe = this.getIFrameEl();
	            	var text = iframe.contentWindow.document.body.innerText;
	            	//alert(text);
	            },
	            ondestroy: function (action) {
	                search();
	            }
      	  });	
			
        } else {
            alert("请选中一条记录");
        }
        
    }
    
    //qote=1 查看权限
    function query() {
        var row = grid.getSelected();
        if (row) {
	        var url = "/csm/corporation/csm_corporation_tree.jsp?partyId="+row.partyId+"&qote=1"+"&partyNum="+row.partyNum;
	       	//客户性质为企业客户
	       	var custType = row.corpCustomerTypeCd;
    		url += "&cusType=" + custType;
			//git.go(url);取消页面跳转
			nui.open({
	            url: nui.context + url,
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
    

 
      /* //小企业的认定 
      function smallCorpIdentify(v) {
        var row = grid.getSelected();
         if (row) {
            nui.open({
                url: nui.context + "/csm/custFlow/smallEnterpriseIdentify.jsp?partyId="+row.partyId,
                title: "小企业认定流程", 
                width: 800,
        		height: 500,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                },
                ondestroy: function (text) {
                		if(text.flag){
		            		var node = text.node;
							openSubmitView(node);
		            	}
                }
            });
        } else {
            alert("请选中一条记录");
        }
        
    } */
    
    /* //关键信息维护流程
     function keyCustInfoMaintain(v) {
        var row = grid.getSelected();
         if (row) {
            nui.open({
                url: nui.context + "/csm/custFlow/csm_corporation_keyInfo_maintain.jsp?partyId="+row.partyId,
                title: "关键信息维护流程", 
                width: 800,
        		height: 500,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                },
                ondestroy: function (action) {
                		 search();
                }
            });
        } else {
            alert("请选中一条记录");
        }
        
    }
    
     //客户信息填写说明
     function explain() {
     
            nui.open({
                url: nui.context + "/csm/corporation/csm_fill_explain.jsp",
                title: "客户信息填写说明", 
                width: 800,
        		height: 500,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                },
                ondestroy: function (action) {
                	//search();
                }
            });
    } */
     
     
     /*自定义vtype*/
        nui.VTypes["englishErrorText"] = "请输入英文";
        nui.VTypes["english"] = function (v) {
            var re = new RegExp("^[a-zA-Z\_]+$");
            if (re.test(v)) return true;
            return false;
        }

</script>
</body>
</html>
