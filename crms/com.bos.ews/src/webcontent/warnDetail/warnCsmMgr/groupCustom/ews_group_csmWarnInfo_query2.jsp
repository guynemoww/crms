<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): caozhe@git.com.cn
  - Date: 2014-04-10
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>

<div id="form1" style="width:100%;height:auto;overflow:hidden;">
	
	<div class="nui-dynpanel" columns="4">
		
		<label>机构名称</label>
		<input id="orgid" name="orgid"  allowInput="false" class="nui-buttonEdit" onbuttonclick="selectOrg"/>
		
        <label>集团客户名称</label>
		<input name="partyName" required="false" class="nui-textbox nui-form-input" vtype="maxLength:32" />
		
		<label>预警级别</label>
		<input name="levelCd" required="false" class="nui-dictComboBox nui-form-input" dictTypeId="XD_YJJB0001" vtype="maxLength:32" />
		
	</div>
</div>
				
<div class="nui-toolbar" style="text-align:right;padding-top:5px;padding-bottom:5px;" 
     borderStyle="border:0;">
    <a class="nui-button" iconCls="icon-search" onclick="search(e)">查询</a>
	<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重置</a>
</div>
<div class="nui-toolbar" style="border-bottom:0;">
	<a class="nui-button" iconCls="icon-node" onclick="edit()">查看明细</a>
	<!-- <a class="nui-button" iconCls="icon-node" onclick="query()">查看变更历史</a> -->
</div>

 <fieldset>
  	<legend>
    	<span>预警客户列表</span>
    </legend>

<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" url="com.bos.ews.warnBizInfo.queryWarnBizInfo2.biz.ext"    
	 dataField="warnBizInfos" allowResize="true" showReloadButton="false" showPageSize="true"  pageSize="10" multiSelect="false" sortMode="client">
	<div property="columns">
	<div type="checkcolumn">选择</div>
		<div field="orgNum" headerAlign="center" allowSort="true"  dictTypeId="org" >机构名称</div>
		<div field="partyNum" headerAlign="center" allowSort="true" >集团客户编号</div>
		<div field="partyName" headerAlign="center" allowSort="true" >集团客户名称</div>
		<div field="warningLevelCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJJB0001"  >预警级别</div>
		<div field="CREDIT_AMT" headerAlign="center" allowSort="true" dataType="currency">授信额度</div>
		<div field="BALANCE" headerAlign="center" allowSort="true" dataType="currency">授信余额</div>
		<!-- <div field="guarantyType" headerAlign="center" allowSort="true" dictTypeId="CDZC0005"  >担保方式</div>
		<div field="availableExposure" headerAlign="center" allowSort="true" >授信可用余额</div>
		<div field="occupiedExposure" headerAlign="center" allowSort="true" >敞口余额</div> -->
		<!-- <div field="warningLevelCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0004">预警级别</div>
		<div field="confirmDate" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd" >预警日期</div> -->
		<div field="userNum" headerAlign="center" allowSort="true" dictTypeId="user" >客户经理</div>
	</div>
</div>
 </fieldset>	
    <script type="text/javascript">
 	nui.parse();
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
	var type="<%=request.getParameter("type") %>";
	//alert(type);
	//grid.load({type:type});                                       //加载预警客户信息
    function search(e) {
        git.mask();                                                  //页面遮罩
	    var warnBizInfo = form.getData();                            //获取表单多个控件的数据
		grid.load({warnBizInfo:warnBizInfo,type:type,"yesOrNo":1});
		
		grid.on("preload",function(e){
       		if (!e.data || e.data.length < 1)
       			return;
       		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
       			e.data[i]['partyName']='<a href="#" onclick="clickCust(\''
       				+ e.data[i].partyId+","+e.data[i].partyNum
       				+ '\');return false;" value="'
       				+ e.data[i].partyId
       				+ '">'+e.data[i]['partyName']+'</a>';
       		}
       });
		
		git.unmask(); 
	}
    search();
    
    function clickCust(e){
		var ps = e.split(",");
		partyId = ps[0];
		partyNum = ps[1];
		var infourl = nui.context + "/csm/company/groupCompany_tree.jsp?partyId="+partyId + "&qote=1&partyNum=" + partyNum;
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
	                search();
	            }
      	  });	
	}
    
    function reset(){
        git.mask();                                              //页面遮罩
		form.reset();                                            //表单重置 
		git.unmask();                                            //取消页面遮罩
	}
    
    function edit() {
        git.mask();                                              //页面遮罩
        var row = grid.getSelected();
       // var rule;                                                //判断是否察看权
        
        if (row) {                                                         //if(row.userPlacingCd==3){rule=1; 为管护权时该客户不能作后续的修改 }
           nui.open({
                url: nui.context + "/ews/warnDetail/ews_warn_main.jsp?corpid="+row.partyId+"&rule=3",
                title: "预警客户详情", 
                width: 800,
        		height: 500,
        		state:"max",
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                },
                ondestroy: function (action) {
                }
            });
           
        } else {
            alert("请选中一条记录");
        }
        git.unmask();
    }
    
    function query(){
         git.mask();                                              //页面遮罩
         var row = grid.getSelected();
         // var rule;                                                //判断是否察看权
        
        if (row) {                                                         //if(row.userPlacingCd==3){rule=1; 为管护权时该客户不能作后续的修改 }
           nui.open({
                url: nui.context + "/ews/warnDetail/warnInfoQuery/earlywarn_change_record.jsp?partyId="+row.partyId+"&rule=3",
                title: "预警客户变更记录", 
                width: 1024,
        		height: 768,
        		state:"max",
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                },
                ondestroy: function (action) {
                }
            });
           
        } else {
            alert("请选中一条记录");
        }
        git.unmask();
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
    
   </script>
</body>
</html>
