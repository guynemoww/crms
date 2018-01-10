<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): lizhi
  - Date: 2014-05-07
  - Description:
-->
<head>
<title>基本情况</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
     <!-- <fieldset>
  	         <legend>
    	             <span>财务指标具体分值：</span>
             </legend>--><!-- allowCellEdit="true" allowCellSelect="true"  editNextOnEnterKey="true" 
	         <div id="borrowFanceInfoGrid" class="nui-datagrid" url="com.bos.aft.checkReport.getOppions.biz.ext" dataField="codes" allowResize="false" multiSelect="true"
	              showPager="false" idField="id" style="margin-bottom: 30px;" >
                 <div property="columns">    
       		     --><!--ComboBox：本地数据        
                 <div field="indexName"  headerAlign="center">财务报表分析结果
                     <input property="editor" class="nui-textbox" style="width:100%;" minHeight="50"/>
                 </div>
                 <div field="indexValue"  headerAlign="center">具体分值
                     <input property="editor" class="nui-textbox" style="width:100%;" minHeight="50"/>
                 </div>
                 <div field="indexChoice" type="dictcomboboxcolumn" autoShowPopup="true" name="gender"  dictTypeId="XD_0002" align="center" headerAlign="center">检查结果
	                 <input property="editor" class="nui-dictcombobox" style="width:100%;" dictTypeId="XD_0002" />                
	            </div> 
                </div>
             </div>	
     </fieldset> -->
    <a class="nui-button" style="margin-right:55px;float: left;margin-top: 20px;" iconCls="icon-save" onclick="reLoad()" >获取上期值</a>
	<!-- 显示选项卡 -->
	<div class="mini-dynpanel2Td" id="dynpanelDiv">
	</div>
	<!-- 指标数据ID -->
	<div id="aliDataIdDiv">
	</div>
	<!-- 保存和关闭按钮 -->
    <div class="nui-toolbar" style="text-align:right;border:none">
    <a class="nui-button" style="margin-right:55px;" iconCls="icon-save" onclick="save()" id="btnSave">临时保存</a>
	</div>
	<input name="pageName" id="pageName" class="nui-hidden" value="financeConditionPage"/>
	
<script type="text/javascript">
	nui.parse();
	var param=<%=request.getParameter("param") %>;
	var alcInfoId = 'alcInfoIdIsNull';							//如果alcInfoId为空，则页面无法正常显示选项（页面为空白），故为其设值一个用于正常显示页面但没有实际意义的值
	var lastAlcInfoId;
	var callback='<%=request.getParameter("callback") %>';		//用于判断是否显示保存按钮（客户经理显示，营销团队负责人不显示）
	var goEdit ="<%=request.getParameter("goEdit") %>";
	var k = 0;													//选项卡个数
	var g = 0;            										//该变量用于在页面迭代加载指标选项值时 用于保持控件ID的统一顺序
	var indexResults;											//当前页面选项卡集
	var pageName = nui.get('pageName').value;      				//用于查询相关的选项，本页面查询“经营情况”的选项
	//var borrowFanceInfoGrid = nui.get("borrowFanceInfoGrid");                        //借款人财务信息
	var inputItems = new Array();								//获取填写项目的指标代码
	var v_aliDataId = '';										//指标数据ID，用来插入或更新数据
	var form = new nui.Form("#dynpanelDiv");					//用于校验填写项
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
	   url=nui.context+"/aft/dailyInspect/financeCondition.jsp?param="+nui.encode(param)+"&callback="+callback+"&getFirst=2";
	 }else{
	   url=nui.context+"/aft/dailyInspect/financeCondition.jsp?param="+nui.encode(param)+"&callback="+callback+"&getFirst=1";
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
		 //borrowFanceInfoGrid.load({indexCd:"AFTL090100"});
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
		   
		   /**填写项目资本金等
		    if(temp.id == "PRJV010100") {//-----------------------------构造填写项开始
		    	inputItems = options;
		    	//alert(nui.encode(app));
		    	var currentInputValues;
		    	var beforeInputValues;
		    	var currentInputJsonData=nui.encode({"alcInfoId":alcInfoId,"indexId":temp.id});
				$.ajax({
						url: "com.bos.aft.dailyInspect.getInputValues.biz.ext",
						type: 'POST',
						data: currentInputJsonData,
						async:false,
						cache: false,
						contentType:'text/json',
						success: function (text) {
							currentInputValues = text.currentInputValues;
							beforeInputValues = text.beforeInputValues;
						},
						error: function () {
					        
					    }
				});
		    	
		    	//标题行
		    	var titleText = '<div>'+temp.text+'</div> ';
		    	var tableString = '<table id="table"  border="" width="99.5%" class="nui-form-table">'+
		    								 '<tr>'+
							    			   '<th class="nui-form-label" colspan="1" width="20%"></th>'+
							    			   '<th class="nui-form-label" colspan="1" width="20%">金额</th>'+
							    			   '<th class="nui-form-label" colspan="1" width="20%">比例</th>'+
							    			   '<th class="nui-form-label" colspan="1" width="20%">上期金额</th>'+
							    			   '<th class="nui-form-label" colspan="1" width="20%">上期比例</th>'+
							    		     '</tr>'+
   									       '</table>';
		    	var newTable = $(tableString);
		    	
		    	for(var r=0;r<4;r++) {//数据行
			    	var trString = '<tr id="">'+
									'<th class="nui-form-label" id="" colspan="1" style="text-align:right" width="20%">'+pros[r+1].indexName.replace("金额","：")+'</th>'+
								   '</tr>';
					var newTr = $(trString);
					for(var n=0;n<4;n++) {//数据单元格
						var enable = "false";
						var required = "true";
						var value = 0;
						
						switch(n) {
							case 0:
								enable = "true";
								if(currentInputValues == null || currentInputValues.length == 0) {
									value = "";
								} else {
									value = new Number(currentInputValues[2*r].indexValue);
								}
							break;
							case 1:
								enable = "true";
								if(currentInputValues == null || currentInputValues.length == 0) {
									value = "";
								} else {
									value = new Number(currentInputValues[2*r+1].indexValue);
								}
							break;
							case 2:
								if(beforeInputValues == null || beforeInputValues.length == 0) {
									value = "";
								} else {
									value = new Number(beforeInputValues[2*r].indexValue);
								}
							break;
							case 3:
								if(beforeInputValues == null || beforeInputValues.length == 0) {
									value = "";
								} else {
									value = new Number(beforeInputValues[2*r+1].indexValue);
								}
							break;
							default:
							break;
						}
						var tdString = '<td class="nui-form-label" id="" colspan="1">'+
										'<input id="input'+r+'/'+n+'" class="nui-textbox nui-form-input" '+
										'width="98%" enabled="'+enable+'" value="'+value+'" required="'+required+'"/>'+
									   '</td>';
						var newTd = $(tdString);
						newTd.appendTo(newTr);
					}
					newTr.appendTo(newTable);
		    	}
		    	var titleTextDiv = $(titleText);
		    	titleTextDiv.appendTo($('#dynpanelDiv'));
		    	newTable.appendTo($('#dynpanelDiv'));
		    	$('<br/>').appendTo($('#dynpanelDiv'));	
		    	nui.parse(document.getElementById('table'));
		    	g=1;
				continue;
		    }*///--------------------------------构造填写项结束
		    
		    var h=j-1;
		    
		    //本期选择
		    if(indexResults[h].data1s == null || indexResults[h].data1s[0] == null 
		    	|| indexResults[h].data1s[0].indexChoice == null ) {
		        temp.data1 = "";
		    } else {
		        temp.data1 = indexResults[h].data1s[0].indexChoice;
		    }
		    
		    //上期选择
		    if(indexResults[h].data2s == null || indexResults[h].data2s[0] == null 
		    	|| indexResults[h].data2s[0].indexChoice == null ) {
		        temp.data2 = "";
		    } else {
		        temp.data2 = indexResults[h].data2s[0].indexChoice;
		    }
		    
		    //本期相关描述
		    if(indexResults[h].data1s[0] == null 
		    	|| indexResults[h].data1s[0].comment == null
		    	|| indexResults[h].data1s == null
		    	) {
		    	temp.data3 = '';
		    	
		    } else {
		    	temp.data3 = indexResults[h].data1s[0].comment;
		    }
		    
		    //指标数据ID
		    if(indexResults[h].data1s == null||indexResults[h].data1s[0] == null
		     ||indexResults[h].data1s[0].aliDataId==null||indexResults[h].data1s[0].aliDataId==undefined) {
		    	v_aliDataId = null;
		    }else{
		    	v_aliDataId = indexResults[h].data1s[0].aliDataId
		    }
		    //动态构造选项卡
		    var dynpanelId = 'dynpanel'+h;
		    var aliDataId='aliDataId'+h;
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
	function save(){
	   nui.get("btnSave").setEnabled(false);
		git.mask();
		//校验填写项
	   form.validate();
       if (form.isValid()==false){
          alert("请完善信息的填写！"); 
          git.unmask();
          nui.get("btnSave").setEnabled(true);
          return 
        }
        
				var timestamp = nui.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss");
				var inputValueObjs = [];//TB_AFT_LOAN_CHECK_IDX_DATA实体数组
				var autoIndex = 0;
				var inputValueIndex = 0;
				
				/*for(var row=0;row<4;row++) {
					for(var col=0;col<2;col++) {
						var inputId = 'input'+row+'/'+col;
						inputValue = nui.get(inputId).getValue();//取填写值
						inputValueObjs[inputValueIndex++] = {'alcInfoId':alcInfoId,  //由于贷后指标池维护不用走流程 所以检查ID改用客户参与人ID取代作为标示
												   'indexCd':inputItems[autoIndex++].id,//各填写项指标代码indexCd
												   'indexValue':inputValue,
												   'updateTime':timestamp};
					}
				}
				
				var jsonInput = nui.encode({'inputValueObjs':inputValueObjs});
				$.ajax({
			            url: "com.bos.aft.dailyInspect.updateInputCard.biz.ext",
			            type: 'POST',
			            data: jsonInput,
			            async:true,
			            cache: false,
			            contentType:'text/json',
			            success: function (text) {
			            	
						},
			            error: function () {
		                    nui.alert("保存失败！");
		                }
				});*/
				
		var num=k-1;
		var dataObjects=[];
		for(var i=0;i<=num;i++) {//遍历选项卡
			var aliDataId=nui.get('aliDataId'+i).getValue();
			var commentText = nui.get('dynpanel'+i).getValue().data3;//现在的相关描述
			var timestamp = nui.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss");//时间
			var context = nui.get('dynpanel'+i).getValue();//获取对象值
			var titleIndexCd = context.id;//ACRS020100
			var newData1 = context.data1;//现在的选项
			var indexId = "";
			
			/*校验  选项和描述不能同时为空
			if((newData1 == null || newData1 == '' || newData1 == undefined)
					&&(commentText == null || commentText == '' || commentText == undefined)){
					alert("第"+(i+1)+"个选项，选择和相关描述不能同时为空！");
					git.unmask();
					return;
				}
			*/
			//校验多选，当选择“是”时，至少选择一项
			var result = document.getElementById(titleIndexCd).name;
			var checkList =  document.getElementsByName(result);
			for(var h=0;h<checkList.length;h++){
				if(checkList[h].checked){
					result = checkList[h].value;
					break;
				}
			}
			if(result==1){
			if((newData1 == null || $.trim(newData1) == "" || typeof(newData1) == "undefined")||(commentText == null || $.trim(commentText) == "" || typeof(commentText) == "undefined")){
					alert("第"+(i+1)+"个选项，选择和相关描述不能为空！");
					git.unmask();
					nui.get("btnSave").setEnabled(true);
					return;
				} 
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
	      
	       var json=nui.encode({"dataObjects":dataObjects});
	       $.ajax({
			            url: "com.bos.aft.aft_small_inspect.saveTarge.biz.ext",
			            type: 'POST',
			            data: json,
			            async:false,
			            cache: false,
			            contentType:'text/json',
			            success: function (text) {
			            	//initGrid();
			            	reld="1"
			            	git.unmask();
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