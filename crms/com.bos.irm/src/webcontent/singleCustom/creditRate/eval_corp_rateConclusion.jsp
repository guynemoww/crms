<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): miaolf
  - Date: 2014-04-14 11:19:18
  - Description:
-->
<head>
<title>评级结论</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<fieldset>
	  	<legend>
	    	<span>评级信息</span>
	    </legend>
		<div id="form" style="width:100%;height:auto;overflow:hidden;">
			<input id="item" class="nui-hidden nui-form-input" name="item"/>
			<div class="nui-dynpanel" columns="4" id="table1">
				<label class="nui-form-label">客户编号：</label>
				<input name="party.partyNum" required="false" setEnabled="flase" class="nui-text nui-form-input" vtype="maxLength:100" />

				<label class="nui-form-label">客户名称：</label>
				<input id ='party.partyName' name="party.partyName" required="false" enabled='false' class="nui-text nui-form-input" vtype="maxLength:100" />
								
				<label class="nui-form-label">组织机构代码：</label>
				<input name="corporation.orgRegisterCd" required="false" class="nui-text nui-form-input" vtype="maxLength:100" />
						
				<label class="nui-form-label">客户类型：</label>
				<input name="party.partyTypeCd" required="false" valueField="dictID" dictTypeId="XD_KHCD1001" class="nui-text nui-form-input"  vtype="maxLength:100" />	
						
				<label class="nui-form-label">银行认定企业规模：</label>
				<input name="corporation.bankScaleIdentify" required="false" valueField="dictID" dictTypeId="CDKH0025" class="nui-text nui-form-input" vtype="maxLength:30" />
						
				<label id="grhy"  class="nui-form-label">客户行业：</label>
				<input id="natural.industry" name="natural.industry" class="nui-text nui-form-input" dictTypeId="CDKH0095" required="true"  />
				
				<label id="dghy" class="nui-form-label">客户行业：</label>
 				<input id="corporation.industrialTypeCd" name="corporation.industrialTypeCd" required="false" valueField="dictID" dictTypeId="CDKH0095" class="nui-text nui-form-input" vtype="maxLength:10" />
						
			<!--  	<label class="nui-form-label">中征码：</label>
				<input name="corporation.middelCode" required="false" class="nui-text nui-form-input" vtype="maxLength:30" /> -->
						
				<label class="nui-form-label">评级模型：</label>
				<input name="irmApply.ratingModelCd" required="false" class="nui-text nui-form-input" vtype="maxLength:100" />
				
		   		<label class="nui-form-label">评级发起日期：</label>
				<input id="systime"  name="item.systime"  required="false" class="nui-text nui-form-input" format="YYYY-MM-DD" vtype="maxLength:100" />
		   	
		   		<label class="nui-form-label">认定结果(R2)：</label>
		   		<div>
					<input id="initialRatingCd"  property="editor" class="nui-text" enabled="false" vtype="maxLength:100" />
			   	   <%-- <a class="nui-button" onclick="view()" >查看</a>--%>
				</div>
	
		   		<label id="tt" class="nui-form-label">违约概率（PD1）：</label>
				<input id="avgPd"  property="editor" class="nui-text" vtype="maxLength:100" />
		   	
	   		</div>
	   	</div>
	</fieldset>
   	<div id="form1" style="width:100%;height:auto;overflow:hidden;">
   		<fieldset>
	  	<legend>
	    	<span>推翻历史</span>
	    </legend>
	    <div id="datagrid1" class="nui-datagrid" allowCellEdit="true"  allowCellWrap="true" showPager="false" dataField="items">
	        <div property="columns" >
	        	<div type="indexcolumn" headerAlign="center" >序号</div>
	        	<div field="isOverthrow" headerAlign="center" allowSort="false" width="100" dictTypeId="XD_0002" >是否推翻</div>
	        	<div field="userNum" headerAlign="center" allowSort="false" width="100" dictTypeId="user" >经办人</div>
	        	<div field="orgNum" headerAlign="center" allowSort="false" width="100" dictTypeId="org" >经办机构</div>      
	            <div field="afterGrade" headerAlign="center" allowSort="false" width="120">推翻后评级结果</div>
	            <div field="overthrowDt" headerAlign="center" allowSort="false" width="120">推翻日期</div>
	            <div field="overthrowReason" headerAlign="center" allowSort="false"  width="250">推翻原因</div> 
	            <div field="avagPd" headerAlign="center" allowSort="false" width="100">违约概率（PD2）</div>
	        </div>
	     </div>
	     <div id="datagrid2" class="nui-datagrid" allowCellEdit="true"  allowCellWrap="true" showPager="false" dataField="items">
	        <div property="columns" >
	        	<div type="indexcolumn" headerAlign="center" >序号</div>
	        	<div field="isOverthrow" headerAlign="center" allowSort="false" width="100" dictTypeId="XD_0002" >是否推翻</div>
	        	<div field="userNum" headerAlign="center" allowSort="false" width="100" dictTypeId="user" >经办人</div>
	        	<div field="orgNum" headerAlign="center" allowSort="false" width="100" dictTypeId="org" >经办机构</div>      
	            <div field="afterGrade" headerAlign="center" allowSort="false" width="120">推翻后评级结果</div>
	            <div field="overthrowDt" headerAlign="center" allowSort="false" width="120">推翻日期</div>
	            <div field="overthrowReason" headerAlign="center" allowSort="false" width="250">推翻原因</div> 
	        </div>
	     </div>	
	     </fieldset>   	
   	</div>
   	<div id="tf">
	   	<fieldset>
		  	<legend>
		    	<span>评级推翻 注：若认为前手评级结果尚不能准确体现客户，请进行评级推翻，并录入真实且详细的推翻原因。</span>
		    </legend>
		    <div id="form2_1" style="width:100%;height:auto;overflow:hidden;margin-top:20px;">
			   	<div class="nui-dynpanel" columns="4">
				   	<label class="nui-form-label">前手建议评级：</label>
				   	<input id="overReason" name="overReason" enabled="false" required="false" class="nui-text nui-form-input" width="200" vtype="minLength:10"/>
			   	</div>
		    </div>
		<div id="form2" style="width:100%;height:auto;overflow:hidden;margin-top:20px;">
			<div class="nui-dynpanel" columns="4">
		   		<label class="nui-form-label">是否进行推翻：</label>
	
		   		<div id="YN" name="YN" class="nui-radiobuttonlist"  textField="text" valueField="id"   data="shiti">
		        </div>
		   		<label class="nui-form-label">经办人：</label>
				<input id="userName" name="userName" required="false" class="nui-text nui-form-input" vtype="maxLength:100" />
		   		<label class="nui-form-label">推翻后评级结果：</label>
				<input id="rateResult" name="rateResult" enabled="false" onitemclick="valueChange()"  property="editor" class="nui-dictcombobox nui-form-input" data="ratingCd"  required="true" emptyText="--请选择--"/>
		   		<label class="nui-form-label">经办机构：</label>
				<input id="userOrgName" name="userOrgName" required="false" class="nui-text nui-form-input" vtype="maxLength:100" />	
	       		<label class="nui-form-label">推翻原因：</label>
				<input id="overThrowReason" name="overThrowReason" enabled="false" required="true"  vtype="maxLength:200" class="nui-textarea nui-form-input" width="100%"      />		
			   		<label id="tt1" class="nui-form-label">违约概率（PD2）：</label>
					<input id="avagPd2" name="avagPd2" required="false" class="nui-text nui-form-input" vtype="maxLength:100" />
				<input id="rateResFi" name="rateResFi" style="display: none" class="nui-text nui-form-input" vtype="maxLength:100" />	
				<input id="rateResSe" name="rateResSe" style="display: none;" class="nui-text nui-form-input" vtype="maxLength:100" />	
		   	</div>
		</div>
		</fieldset>
		   	<div id="again" style="overflow:hidden;margin-top:20px;">
			   	<fieldset>
				  	<legend>
				    	<span>评级再审核</span>
				    </legend>
				   		<label>是否进行再审核：</label>
				   		<div id="isReAud" name="isReAud" class="nui-radiobuttonlist"  textField="text" valueField="id"   data="shiti">
		      			  </div>
				<!-- input id="isReAud"  property="editor" class="nui-checkbox"/> -->
			   	</fieldset>	
		   	</div>
		   
		   	<div  class="nui-toolbar"  style="border-bottom:0;text-align:right;margin-top: 20px;">
		   		<a id="save"  class="nui-button" iconCls="icon-save" onclick="save()">保存</a>
		   	</div>
	</div>	
<script type="text/javascript">
		//大公国际长期评级
		var ratingCd = [{ id: 'AAA', text: 'AAA'}, { id: 'AA', text: 'AA'}, { id: 'A', text: 'A'},
						{ id: 'BBB', text: 'BBB'}, { id: 'BB', text: 'BB'}, { id: 'B', text: 'B'},
						{ id: 'CCC', text: 'CCC'}, { id: 'CC', text: 'CC'}, { id: 'C', text: 'C'}];
		var shiti = [{ id: '1', text: '是' }, { id: '2', text: '否'}];
	nui.parse();
	var form = new nui.Form("#form");
	var form1 = new nui.Form("#form1");
	var form2 = new nui.Form("#form2");
	var form2_1 = new nui.Form("#form2_1");
	var grid = nui.get("datagrid1");
	var grid1 = nui.get("datagrid2");
	var reAud="<%=request.getParameter("reAud") %>";	
	var partyId="<%=request.getParameter("partyId") %>";//参与人id
	var applyId="<%=request.getParameter("applyId") %>";//评级申请id
	var oldApplyId="<%=request.getParameter("oldApplyId") %>";//评级申请id
	var posicode="<%=request.getParameter("posicode") %>";
	var processInstId="<%=request.getParameter("processInstId") %>";//实例号
	var recId;//计算引擎id
	var overFlg=0;//推翻标记：1表示推翻，0表示不推翻(默认)
	var state;//评级状态
	var flowType="<%=request.getParameter("flowType") %>";
	var flg;
	var isFinance;//是否同业
	var cust_grid = nui.get("cust_grid");
	var isReal = nui.get("YN");
	var isProject;//是否专业贷款（1：是，0：否）
	isReal.on("valuechanged", function (e) {
	    showOn(this.getValue());
	});
	var proFlag ="<%=request.getParameter("proFlag") %>";
	if("1" == proFlag){
		$("#tf").css("display","none");
	}
	
	var json = nui.encode({"iraApplyId":applyId});
	$.ajax({
	    url: "com.bos.irm.irmApply.irmApply.getModeScaleToCombobx.biz.ext",
	    type: 'POST',
	    data: json,
	    async:false,
	    cache: false,
	    contentType:'text/json',
	    cache: false,
	    async: false,
	    success: function (mydata) {
	        debugger;
        	if(mydata && mydata.scales && mydata.scales.length>0){
	          	for(var i = 0;i<mydata.scales.length;i++){
		           	if(!mydata.scales[i].id&& mydata.scales[i].ID){
		           		mydata.scales[i].id = mydata.scales[i].ID;
		           	}
	           		if(!mydata.scales[i].text&& mydata.scales[i].TEXT){
		           		mydata.scales[i].text = mydata.scales[i].TEXT;
		           	}
	           	}
	           	nui.get("rateResult").setData(mydata.scales);
           	}
 		}
	});
	
	function getIsProject(){
		var json = nui.encode({"applyId":applyId});		
		nui.ajax({
	        url: "com.bos.irm.queryInfo.queryIsProject.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
		    async:false,        
	        contentType:'text/json',
	        success: function (text) {
	        	if(text.msg){
	        		alert(text.msg);
	        	} else {
	                var o = nui.decode(text);
	                isProject=o.isProject;
	            }
	        }
	    });
	    if(isProject == "1"){
	    	$("#tt1").hide();
	    	$("#tt").hide();
	    	$("#avagPd2").hide();
	    	$("#avgPd").hide();
	    	$("#datagrid1").hide();
	    }else{
	    	$("#datagrid2").hide();
	    }
	}
	init();//初始化
	function init(){
		if("-1" == proFlag || "1" == proFlag){
			nui.get("save").hide();
			form2.setEnabled(false);
		}else{
			nui.get("save").show();
		}
 
		getIsProject();
		getReplacePos();//获取当前节点岗位
		var json = nui.encode({"partyId":partyId,"applyId":applyId});
	    nui.ajax({
	        url: "com.bos.irm.queryInfo.queryCustInfoJj.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
		    async:false,        
	        contentType:'text/json',
	        success: function (text) {
	        	if(text.msg){
	        		alert(text.msg);
	        	} else {
	                var o = nui.decode(text);
	                form.setData(o);
	                isFinance=o.isFinance;
	                var partytype=o.party.partyTypeCd;
	                if(partytype=="02"){
 	                nui.get("natural.industry").show();
  	                $("#grhy").css("display","block");
 	              	nui.get("corporation.industrialTypeCd").hide();
 	              	 $("#dghy").css("display","none");
	              	nui.get('table1').refreshTable();
	              	
 	              	
	               
	                }else{
	                   nui.get("natural.industry").hide();
	                   $("#grhy").css("display","none");
	              	 $("#dghy").css("display","block");
	              	nui.get("corporation.industrialTypeCd").show();
	              	nui.get('table1').refreshTable();
	                }
	            }
	        }
	    });
 	    			 
	    //查询最近的推翻原因
	    getBeforeRatingResult();
	    
	    getInitialRatingCd();//获取初始评级结果

	    getUserInfo();//获取经办人信息

	    queryRatingScore();//获取评级得分

	    getRateState();//获取评级状态并控制画面的显示
	    
	    getOverRecordInfo();//获取推翻记录
	    //if(flowType == "03"){
	    //	$("#again").hide();
	    //}
	    $("#again").hide();
	    //getReAud();//通过再审核标识来控制页面编辑
	 
		
	}
	function getInitialRatingCd(){
		var json = nui.encode({"partyId":partyId,"applyId":applyId,"oldApplyId":oldApplyId});
		nui.ajax({
	        url: "com.bos.irm.queryInfo.queryInitialRatingCd.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
		    async:false,        
	        contentType:'text/json',
	        success: function (text) {
	        	if(text.msg){
	        		alert(text.msg);
	        		nui.get("save").setEnabled(false);
	        	} else {
	                var o = nui.decode(text);
	                nui.get("initialRatingCd").setValue(o.initialRatingCd);
	                nui.get("systime").setValue(o.time);
	                nui.get("avgPd").setValue(o.avgPd);
	                nui.get("rateResFi").setValue(o.orderNo);
	            }
	        }
	    });
	}
	function getUserInfo(){//获取经办人信息	
		var json = nui.encode({"partyId":partyId,"applyId":applyId});	
	    nui.ajax({
	        url: "com.bos.irm.queryInfo.queryCustomInfo.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
		    async:false,        
	        contentType:'text/json',
	        success: function (text) {
	        	if(text.msg){
	        		alert(text.msg);
	        	} else {
	                var o = nui.decode(text);
	                nui.get("userName").setValue(o.userName);
	                nui.get("userOrgName").setValue(o.userOrgName);
	            }
	        }
	    });
	}
	function getOverRecordInfo(){//获取推翻记录
		var json = nui.encode({"applyId":applyId,"oldApplyId":oldApplyId});
	    nui.ajax({
	        url: "com.bos.irm.queryInfo.queryOverRecordInfo.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
		    async:false,        
	        contentType:'text/json',
	        success: function (text) {
	        	if(text.msg){
	        		alert(text.msg);
	        	} else {
	                var o = nui.decode(text);
	                grid1.setData(o.items);
	                grid.setData(o.items);
	                flg = o.flg;
	            }
	        }
	    });		
	}
	var flgDisplay;//再审核显示标识（1：显示，2：不显示）
	function getReplacePos(){
		var json = nui.encode({"posicode":posicode});
		nui.ajax({
	        url: "com.bos.irm.queryInfo.queryReplacePos.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
		    async:false,        
	        contentType:'text/json',
	        success: function (text) {
	            var o = nui.decode(text);
	            flgDisplay = o.posCd;
	        }
	    });
	} 
	function getRateState(){//获取评级状态并控制画面的显示
		var json = nui.encode({"applyId":applyId});
	    nui.ajax({
	        url: "com.bos.irm.queryInfo.queryRateState.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
		    async:false,        
	        contentType:'text/json',
	        success: function (text) {
	        	if(text.msg){
	        		alert(text.msg);
	        	} else {
	                var o = nui.decode(text);
	                state = o.state;
	            }
	        }
	    });
	    if(flgDisplay=="P1069"){//再审定岗
	    	$("#again").hide();
	    }
	    if(flgDisplay=="P1046"){//发起岗
	    	
	    	$("#again").hide();
	    }
	    if(flgDisplay=="P1047"){//审核岗
	    	$("#again").hide();
	    }
	    if(reAud == "0"){
	   	 	getOverRecordInfo();
		    if (state == "0"){//待发起
		  		if(flg == "0"){
		    		$("#form1").hide();
		    	}
			}
			if (state == "3"){//退回
		  		if(flg == "0"){
		    		$("#form1").hide();
		    	}
			}
		}
	}
	//是否推翻按钮
    function showOn(e){
    	var	isOver = nui.get('YN').getValue();
    	if(isOver<2){
        //if(e.value=="true"){
        	var json = nui.encode({"applyId":applyId});
        	nui.ajax({//获取评级等级
	        url: "com.bos.irm.queryInfo.queryRatingDisplay.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
		    async:false,        
	        contentType:'text/json',
	        success: function (text) {
	        	if(text.msg){
	        		alert(text.msg);
	        	} else {
	                var o = nui.decode(text);
	                //nui.get("rateResult").setData(o.out);
	        	}
	        }
	    });
            nui.get("rateResult").setEnabled(true);
            nui.get("overThrowReason").setEnabled(true);
            overFlg=1;
        }else{
            nui.get("rateResult").setEnabled(false);
            nui.get("overThrowReason").setEnabled(false);
            nui.get("rateResult").setValue(null);
            nui.get("overThrowReason").setValue(null);
            nui.get("avagPd2").setValue(null);
            overFlg=0;
            getBeforeRatingResult();
        }       
    }  
	function  getBeforeRatingResult(){
	    var json = nui.encode({"iarApplyId":applyId,"posicode":posicode,"flowType":flowType});
		    nui.ajax({
		        url: "com.bos.irm.queryInfo.queryOverRecordFirst.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
			    async:false,        
		        contentType:'text/json',
		        success: function (text) {
	                var o = nui.decode(text);
	                form2_1.setData(o);
	                nui.get("rateResSe").setValue(o.orderNo); 	//推翻后评级结果对应值
		        }
		    });
	 }
	//查看按钮
	function view(){
		nui.open({
            url: "irm/financialCustom/financial_view_report.jsp?applyId="+applyId+"&partyId="+partyId+"&recId="+recId+"&processInstId="+processInstId+"&reAud="+reAud+"&oldApplyId="+oldApplyId+"&flowType="+flowType,
            title: "查看评级报告"
        });
	}
	//保存
	function save(){
		var	isReAud;    //是否进行再审核
		var num ;	//相对机评上调级数
		var rateResFi ;	//机评结果
		var rateResSe ;	//推翻后评级结果
		var	isOver = nui.get('YN').getValue();//是否推翻
		rateResSe = nui.get('rateResSe').getValue();	//推翻后评级结果对应排序值
		if(isOver.length >0 ){//表示选择是否推翻
			if(isOver == 1){//1:表示推翻
				var overReason = nui.get('overReason').getValue();//前手评级结果
				var rateResult = nui.get('rateResult').getValue();//推翻结果
				var	overTh = nui.get('overThrowReason').getValue();//是否推翻
				var initialRatingCd = nui.get('initialRatingCd').getValue();
				 
			 
			//	if(rateResult.length > 0){
			//		if(initialRatingCd == rateResult){
			//			return alert('推翻结果不能和前手评级结果相同');
 			//		}
			//	}
			 
			}
		}else{
			return alert('请选择是否推翻并保存');
		}
		if(overFlg == 1){
			rateResFi = nui.get('rateResFi').getValue();	//机评结果对应排序值
			rateResSe = nui.get('rateResSe').getValue();	//推翻后评级结果对应排序值
			num = (rateResFi - rateResSe);
		}
		if(isOver == 2){
			rateResFi = nui.get('rateResFi').getValue();//机评结果对应排序值
		}
		form2.validate();
       if(form2.isValid() == false){
             return alert("请将数据输入完整！");
        }
        if(flowType == "02"){
        }else{    	
			if(flgDisplay == "P1065" || flgDisplay == "P1066" || flgDisplay == "P1067" || flgDisplay == "P1068"){//认定岗
				isReAud = "2";//否
			}
		}
		
		var data = {forms:form2.getData(),"num":num,"processInstId":processInstId,"rateResSe":rateResSe,"applyId":applyId,"partyId":partyId,"state":state,"overFlg":overFlg,"flowType":flowType,"isReAud":isReAud,"posicode":posicode,"flgDisplay":flgDisplay};
		var json = nui.encode(data);
	    nui.ajax({//保存或更新推翻记录表
	        url: "com.bos.irm.insertInfomercial.addOrUpdateTFRecord.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
		    async:false,        
	        contentType:'text/json',
	        success: function (text) {
	        	if(text.msg){
	        		alert(text.msg);
	        	//	init();
	        		getOverRecordInfo();
	        	} 
	        }
	    });
	
        	var ret = checkBeforeSub();

	}
        
			function checkBeforeSub(){
		 
		var json = nui.encode({"iraApplyId":applyId,"processInstId":processInstId});
		     $.ajax({
            url: "com.bos.irm.irmApply.irmApply.setRatingCd.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            cache: false,
            success: function (text) {
            return "1";
            }
        });
		
	
	
	}
 	//下拉框值改变时触发
	function valueChange(){
		var value = nui.get("rateResult").getValue();
		var json = nui.encode({"value":value});
		nui.ajax({//根据推翻后结果获取违约PD值
	        url: "com.bos.irm.queryInfo.queryAvagPD.biz.ext",
	        type: 'POST',
	        data: json,
	        cache: false,
		    async:false,        
	        contentType:'text/json',
	        success: function (text) {
	        	if(text.msg){
	        		alert(text.msg);
	        	}
	        	else{
	        		var o = nui.decode(text);
	        		nui.get("avagPd2").setValue(o.avagPd);
	        		nui.get("rateResSe").setValue(o.orderNo); 	//推翻后评级结果对应值
	        	} 
	        }
	    });
	}
	function queryRatingScore(){
		//cust_grid.load({applyId:applyId});
	}
</script>
</body>
</html>