<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): lizhi
  - Date: 2014-05-07
  - Description:显示客户相关信息
-->
<head>
<title>外部环境与重大事项</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
    <a class="nui-button" style="margin-right:55px;float: left;margin-top: 20px;" iconCls="icon-save" onclick="reLoad()" id="btnSave">获取上期值</a>
	<!-- 显示选项卡 -->
	<div id="dynpanelDiv">   
	</div>
	<!-- 指标数据ID -->
	<div id="aliDataIdDiv">
	</div>   
	<!-- 保存和关闭按钮 -->
    <div class="nui-toolbar" style="text-align:right;border:none">
    <a class="nui-button" style="margin-right:55px;" iconCls="icon-save" onclick="save()" id="btnSave">临时保存</a>
	</div>
	<input name="pageName" id="pageName" class="nui-hidden" value="externalFactorPage"/>
	
<script type="text/javascript">
	nui.parse();
	var param=<%=request.getParameter("param") %>;
	var alcInfoId = 'alcInfoIdIsNull';							//如果alcInfoId为空，则页面无法正常显示选项（页面为空白），故为其设值一个用于正常显示页面但没有实际意义的值
	var lastAlcInfoId;
	var callback='<%=request.getParameter("callback") %>';		//用于判断是否显示保存按钮（客户经理显示，营销团队负责人不显示）
	var goEdit ="<%=request.getParameter("goEdit") %>";
	var k = 0;											   		//选项卡个数
	var indexResults;											//当前页面选项卡集
	var v_aliDataId = '';										//指标数据ID，用来插入或更新数据
	var pageName = nui.get('pageName').value;					//用于查询相关的选项，本页面查询“经营情况”的选项
	var getFirst = "<%=request.getParameter("getFirst") %>";  
	var reld="0";
	if(getFirst=="1"){
	  reld="3";                                                 //指标池维护获取绝对上期ID
	}else if(getFirst=="2"){
	  reld="2";                                                 //从贷后检查报告进入指标池维护时获取上期ID是获取相对的上期ID
	}
	if(callback=="y"){											
		$("#btnSave").hide();//营销团队负责人查看页面时，隐藏保存按钮
	}
	
	function reLoad(){
  	 var url;
	 if(goEdit=="1"){
	   url=nui.context+"/aft/dailyInspect/externalFactor.jsp?param="+nui.encode(param)+"&callback="+callback+"&getFirst=2";
	 }else{
	   url=nui.context+"/aft/dailyInspect/externalFactor.jsp?param="+nui.encode(param)+"&callback="+callback+"&getFirst=1";
	 }
	 git.go(url);
	}
	
	if(param != null && param != '' && param != undefined){
		alcInfoId =param.alcInfoId;								//贷后检查客户情况ID
		lastAlcInfoId = param.lastAlcInfoId;
	}
	
	//显示选项卡
	function initGrid() {
		$('#dynpanelDiv').html('');
		$('#aliDataIdDiv').html('');
		var pageJsonData;
		if(goEdit=="1"){
		  reld="1";
		}
		//alert(reld);
		if(reld =="0"){
		   pageJsonData=nui.encode({"fileName":"targetConfig.xml","pageName":pageName,"alcInfoId":param.corpid,show:"1"});  //由于贷后指标池维护不用走流程 所以检查ID改用客户参与人ID取代作为标示
		}else if(reld=="1"){
		   pageJsonData=nui.encode({"fileName":"targetConfig.xml","pageName":pageName,"alcInfoId":alcInfoId,"lastAlcInfoId":lastAlcInfoId,show:2});  //从贷后检查报告进入的指标维护
		}else if(reld=="2"){
		   pageJsonData=nui.encode({"fileName":"targetConfig.xml","pageName":pageName,"alcInfoId":lastAlcInfoId,"lastAlcInfoId":lastAlcInfoId,show:2});  //从贷后检查报告进入的指标维护获取上期ID（相对上期ID）
		}else{
		   pageJsonData=nui.encode({"fileName":"targetConfig.xml","pageName":pageName,"alcInfoId":param.corpid});  //由于贷后指标池维护不用走流程 所以检查ID改用客户参与人ID取代作为标示
		}
		$.ajax({//获取页面选项卡集indexResults
				url: "com.bos.aft.dailyInspect.getTargetNum.biz.ext",
				type: 'POST',
				data: pageJsonData,
				async:false,
				cache: false,
				contentType:'text/json',
				success: function (text) {
					indexResults=text.indexResults;
					k = indexResults.length;
				},
				error: function () {
			        
			    }
		});
		
		for(var j=1;j<=k;j++) {//构造k个选项卡
			var temp = {};
		    var pros = indexResults[j-1].optionCards;
		    var options = new Array();
		    var multiSelect = false;
		    
		    for(var i=0;i<pros.length;i++) {//构造选项卡
		    	if(i == 0) {//标题
		        	temp.id = pros[i].indexCd;
		            temp.text = (j)+"、"+pros[i].indexName;
		            
		            if(pros[i].standardIndexCd === "2"){//判断单双选
		            	multiSelect = true;
		            }
		        } else {//选项
		        	options[i-1] = {id:pros[i].indexCd,text:pros[i].indexName};
		        }
		    }
		    
		    temp.children = options;
		    
		    //本期选择
		   if(null == indexResults[j-1].data1s[0] || null == indexResults[j-1].data1s[0].indexChoice || 
		     indexResults[j-1].data1s == null) {
		   	    temp.data1 = '';//没有选项
		   } else {
		   	  temp.data1 = indexResults[j-1].data1s[0].indexChoice;//初始化的选项值
		   }
		    
		    //上期选择
		    if(indexResults[j-1].data2s == null || indexResults[j-1].data2s[0] == null 
		    	|| indexResults[j-1].data2s[0].indexChoice == null ) {
		        temp.data2 = "";
		    } else {
		        temp.data2 = indexResults[j-1].data2s[0].indexChoice;
		    }
		    //本期相关描述
		    if(indexResults[j-1].data1s[0] == null || indexResults[j-1].data1s[0].comment == null
		    	|| indexResults[j-1].data1s == null
		    	) {
		    	temp.data3 = '';
		    } else {
		    	temp.data3 = indexResults[j-1].data1s[0].comment;
		    }
		    //指标数据ID
		    if(indexResults[j-1].data1s == null||indexResults[j-1].data1s[0] == null
		     ||indexResults[j-1].data1s[0].aliDataId==null||indexResults[j-1].data1s[0].aliDataId==undefined) {
		    	v_aliDataId = null;
		    }else{
		    	v_aliDataId = indexResults[j-1].data1s[0].aliDataId
		    }
		    //动态构造选项卡
		    var dynpanelId = 'dynpanel'+j;
		    var aliDataId='aliDataId'+j;
		    var html='<div id="'+dynpanelId+'" class="nui-dynpanel2" width="100%" columnValueFields="data1,data2" '
	    			+ 'dataField="children" colAlign="left,left" colWidth="70%,30%" multiSelect="'+multiSelect+'"> </div>';
	    	var dom=$(html);
	    	var html2='<input id="'+aliDataId+'" class="nui-hidden" value="'+v_aliDataId+'" name="'+aliDataId+'" />';
	    	var dom2=$(html2);
	    	dom.appendTo($('#dynpanelDiv'));
	    	dom2.appendTo($('#aliDataIdDiv'));
	    	git._doParse(document.getElementById(dynpanelId));
	    	git._doParse(document.getElementById(aliDataId));
		    nui.get(dynpanelId).setValue(temp);
	    	$('<br/>').appendTo($('#dynpanelDiv'));		
		}
	}
	//显示
	initGrid();
	
	//保存选择
	function save() {
		nui.get("btnSave").setEnabled(false);
		//校验
		for(var i=1;i<=k;i++) {//遍历选项卡
			var context = nui.get('dynpanel'+i).getValue();
			var newData1 = context.data1;//现在的选项
			var commentText = nui.get('dynpanel'+i).getValue().data3;//现在的相关描述
			/*if((newData1 == null || newData1 == '' || newData1 == undefined)
				&&(commentText == null || commentText == '' || commentText == undefined)){
				alert("第"+i+"个选项，选择和相关描述不能同时为空！");
				git.unmask();
				nui.get("btnSave").setEnabled(true);
				return;
			}*/
		}
		git.mask();
		var dataObjects=[];
		for(var i=1;i<=k;i++) {//遍历选项卡
			var aliDataId=nui.get('aliDataId'+i).getValue();
			var commentText = nui.get('dynpanel'+i).getValue().data3;//现在的相关描述
			var timestamp = nui.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss");
			var context = nui.get('dynpanel'+i).getValue();
			var newData1 = context.data1;//现在的选项
			var titleIndexCd = context.id;//ACRS020100
			var indexId = "";
			
			/*校验  选项和描述不能同时为空
			if((newData1 == null || newData1 == '' || newData1 == undefined)
					&&(commentText == null || commentText == '' || commentText == undefined)){
					alert("第"+(i)+"个选项，选择和相关描述不能同时为空！");
					git.unmask();
					nui.get("btnSave").setEnabled(true);
					return;
				} 
			*/
			//校验多选，当选择“是”时，至少选择一项
			var panDuan;
			var result = document.getElementById(titleIndexCd).name;
			var checkList =  document.getElementsByName(result);
			for(var h=0;h<checkList.length;h++){
				if(checkList[h].checked){
					result = checkList[h].value;
					break;
				}
			}
			if(result==1){
			if((newData1 == null || $.trim(newData1) == '' || typeof(newData1) == "undefined")||(commentText == null || $.trim(commentText) == '' || typeof(commentText) == "undefined")){
					alert("第"+(i)+"个选项，选择和相关描述不能为空！");
					nui.get("btnSave").setEnabled(true);
					git.unmask();
					return;
				} 
            
			}
			if(panDuan==1){
			alert("多选为“是”时，至少选择一项！");
			git.unmask();
			nui.get("btnSave").setEnabled(true);
			return;
			}
			
			var temp = new Object();
			temp.comment=commentText;
			temp.updateTime=timestamp;
			temp.indexChoice=newData1;
			temp.indexId=titleIndexCd;
			temp.alcInfoId=alcInfoId;
			temp.aliDataId=aliDataId=='null'?null:aliDataId;
			dataObjects[i]=temp;
	}
	var json=nui.encode({dataObjects:dataObjects});
	       $.ajax({
			            url: "com.bos.aft.aft_small_inspect.saveTarge.biz.ext",
			            type: 'POST',
			            data: json,
			            async:false,
			            cache: false,
			            contentType:'text/json',
			            success: function (text) {
			            	//initGrid();
			            	reld="1";
			            	git.unmask();
			            	nui.get("btnSave").setEnabled(true);
			            	if(text.msg){
			                     alert(text.msg);
			            	}
						},
			            error: function () {
		                    nui.alert("保存失败！");
		                }
				});
				nui.get("btnSave").setEnabled(true);
   }
	
	//过滤字符串的前后空格
	function trim(str){ //删除左右两端的空格     
		return str.replace(/(^\s*)|(\s*$)/g, ""); 
	}
</script>
</body>
</html>