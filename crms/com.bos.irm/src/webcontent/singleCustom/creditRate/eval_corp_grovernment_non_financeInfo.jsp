<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): miaolf
  - Date: 2014-04-14 09:31:26
  - Description:
-->
<head>
<title>政府融资调整选项 </title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>

<div id="form2" style="width:100%;height:auto;">
	<div id="datagrid2" class="nui-datagrid" showPager="false" 
		onrowdblclick="" sortMode="client"
         allowCellEdit="true"  allowCellWrap="true"   allowCellSelect="true" idField="id" 
         url="com.bos.irm.queryInfo.queryGovernmentFinanceInfo.biz.ext" dataField="outs"  multiSelect="true" >
        <div property="columns" >
        <!--oncellbeginedit="setComboxData"  -->
        	<div type="indexcolumn" headerAlign="center" >序号</div>
        	<div field="propertyTypeCd" name="propertyTypeCd" headerAlign="center" visible='false'  align="center" width="100" dictTypeId="XD_PJCD0019" >指标类别</div>
        	<div field="indexId" headerAlign="center" visible ='false' align="center" width="100">指标id</div>        
        	<div field="indexName" headerAlign="center" align="center" width="100">指标</div>        
            <div field="indexDesc" headerAlign="center" width="150"   >指标说明</div> 
			<div field="indexOption" name="indexOption"  renderer="renderCell" autoEscape="false"  headerAlign="center" >指标选项
				<!-- input property="editor" class="nui-combobox" textField="indexOption" valueField="indexOption" dataField="items" style="width:100%;" /> -->
			</div>            
            <div field="remarks" headerAlign="center">如有特殊事项，请各位填入备注
                <input property="editor" name="remarks" class="nui-textarea" width="150"/>
            </div>
        </div>
    </div>        
</div>
<div id="save" class="nui-toolbar" style="border-bottom:0;text-align:right;margin-top: 20px;">
	<a class="nui-button" iconCls="icon-save" onclick="btnsave">保存</a>
</div>
<script type="text/javascript">
		var partyId="<%=request.getParameter("partyId") %>";//参与人id
		var applyId;
		var reAud="<%=request.getParameter("reAud") %>"	;		
		nui.parse(); 
		var form2 = new nui.Form("#form2");	
		var grid2 = nui.get("datagrid2");
		var flg;
		var state;
		var allowModifyFlag;//是否允许保存
		init();	
		if(reAud == "0"){
	    		applyId = "<%=request.getParameter("applyId") %>";
	    	}else{
	    		applyId = "<%=request.getParameter("oldApplyId") %>";
	    }
 
	    function init(){//初始化
		    if(reAud == "0"){
	    		applyId = "<%=request.getParameter("applyId") %>";
	    	}else{
	    		applyId = "<%=request.getParameter("oldApplyId") %>";
	    	}
    		var json=nui.encode({"partyId":partyId});
    		nui.ajax({
		        url: "com.bos.irm.queryInfo.queryGovernmentFinance.biz.ext",
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
		                flg = o.flg;
		                if(flg != "1"){
		                	form2.setEnabled(false);
		                	$("#save").hide();
		                }	       
		        	}
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            alert(jqXHR.responseText);
		        }
			});
			
    			grid2.on("load", function () {
           			grid2.mergeColumns(["propertyTypeCd"]);
      		 	});
    			grid2.load({"item":{"applyId":applyId,"partyId":partyId}});
    		getAllowModifyFlag();
			getRateState();//获取评级状态并控制画面的显示
			getReAud();//通过再审核标识来控制页面编辑		
			indexDesc();//	指标说明链接
		}
		
		//指标说明链接
		function indexDesc(){
			grid2.on("preload",function(e){
	       		if (!e.data || e.data.length < 1)
	       			return;
	       		for (var i=0; i<e.data.length; i++){//nui.alert(nui.encode(e.data[i]));
	       			e.data[i]['indexDesc']='<a href="#" onclick="clickCust(\''
	       				+","+e.data[i].indexId
	       				+ '\');return false;" value="'
	       				+ '">'+e.data[i]['indexDesc']+'</a>';
	       		}
	       });
		}
		function clickCust(e){
			var ps = e.split(",");
			indexId = ps[1];
			var infourl = nui.context + "/irm/singleCustom/creditRate/irm_index_desc_detail.jsp?applyId="
	            + applyId+"&indexId="+indexId;
	       nui.open({
		            url:infourl,
		            title: "指标说明", width: 600, height: 450,
		            onload: function(e){
		            	var iframe = this.getIFrameEl();
		            	var text = iframe.contentWindow.document.body.innerText;
		            	//alert(text);
		            },
		            ondestroy: function (action) {
		            }
	      	  });	
		}
		
		
		
		function onStatus(e) {
			var statusName = getStatusName(e.value);
			if(e.value=="1") {
				return statusName;
			} else {
				return "<a href='javascript:showErrorDetail(" + e.record.SEQ_WF_OPERATE_LOG + ")'>" + statusName + "</a>";
			}
		}
		
		function showErrorDetail(value) {
	        if (value!="") {
	            nui.open({
	                url: "<%=contextPath %>/managerframe/operateLog/detailError.jsp",
	                title: "错误详情", width: 750, height: 500,
	                onload: function () {
	                    var iframe = this.getIFrameEl();
	                    var data = { sqlLog: value };
	                    iframe.contentWindow.SetData(data);
	                }
	            });
	        } 
		}
				
		
		
		function getAllowModifyFlag(){//是否允许保存
			var json = nui.encode({"applyId":applyId});
			nui.ajax({
			        url: "com.bos.irm.queryInfo.queryAllowModifyFlag.biz.ext",
			        type: 'POST',
			        data: json,
			        cache: false,
				    async:false,        
			        contentType:'text/json',
			        success: function (text) {
			        	if(text.msg){
			        		alert(text.msg)
			        	} else {
			        		var o = nui.decode(text);
			             	allowModifyFlag = o.allowModifyFlag;
			            }
			        }
			});
		}	
		function getReAud(){
			if (reAud == "1"){
				grid2.allowCellEdit=false;
				$("#save").hide();
			}
		}
		//原来给列表set值
		function setComboxData(e){
			if(e.field!="indexOption"){
				return;
			}
			var json=nui.encode({"applyId":applyId,"index":e.row.indexId});
			//alert(e.row.indexId);
			nui.ajax({
		        url: "com.bos.irm.queryInfo.queryIndexOption.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        contentType:'text/json',
		        success: function (text) {
		        	if(text.msg){
		        		alert(text.msg);
		        	} else {	
		        		e.editor.load(text.items);
		        	}
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            alert(jqXHR.responseText);
		        }
			});	
		}
		
		function btnsave(){
			if(allowModifyFlag == 2){
				alert("获取评级结果次数过多，不能进行保存！");
				return;
			}
	  		var inRows2 =  new Array();
	  		grid2.findRows(function (row){
	  			var obj = new Object();
	  			
	  			obj.indexOption = row.indexOption;
	  			obj.indexId = row.indexId;
	  			obj.remarks = row.remarks;
	  			inRows2.push(obj);
	  			//obj=row.inData.indexId;
	  		});
    		//if(flg == "1"){
	    		var json = nui.encode({"inRows":inRows2,"applyId":applyId});
		  		nui.ajax({//插入非财务信息表
	    			url:"com.bos.irm.insertInfomercial.addNonFinancialInfo.biz.ext",
	    			type: 'POST',
	      			data: json,
		      		cache: false,
		      	    contentType:'text/json',
		      	    async:false,
		      	    success: function (txt) {     	
		      	    		alert(txt.msg);
		      	    },
		            error: function (jqXHR, textStatus, errorThrown) {
		                alert(jqXHR.responseText);
		            }
	    		});
	    	//}else{
	    	//}
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
		    if (state == "1"){//待审核
		    	$("#save").hide();
		    	grid2.allowCellEdit=false;
		    }
		    if (state == "2"){//待认定
		    	$("#save").hide();
		    	grid2.allowCellEdit=false;
		    }		    
		}
		
		
	function renderCell(e) {
	      var id='custom_' + e.rowIndex + '_' + e.columnIndex + '_' + e.field;
	      if (e.field =='indexOption') {
	       var row=e.row;//这里是当前行的数据
	      var arr=[];//=[{text:'aaaxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx', value:'1', checked: false},{text:'bbb', value:'2', checked: true},{text:'aaaxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx', value:'3', checked: false}];
	      if(row.indexId =='undefined'){
	      	return ;
	      }
	       var json=nui.encode({"applyId":applyId,"index":row.indexId});
			nui.ajax({
		        url: "com.bos.irm.queryInfo.queryIndexOption.biz.ext",
		        type: 'POST',
		        data: json,
		        cache: false,
		        async:false,    
		        contentType:'text/json',
		        success: function (text) {
		        	if(text.msg){
		        		alert(text.msg);
		        	} else {
		        		arr = nui.decode(text.items);
		        	}
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		            alert(jqXHR.responseText);
		        }
			});	
	       
	       //这里的arr是我用的测试数据，实际执行时应该根据e.row[e.field]获取到的js数组对象进行初始化
	       var html='';
	       for (var i=0; i<arr.length; i++) {//单选框按钮组
		        var str = '<input name="'+id+'" type="radio" '+(arr[i].checked ? 'checked' : '')
		         +' onclick="checkName(this)" value="'+arr[i].value+'"></input>'+arr[i].indexOption;
		        if (i!=0)
		         str = '<br/>' + str;
		        html += str;
	       }
	       return html;
	      }
     }
     function checkName(e) {
	    var id=e.id||e.name;
	      var ids=id.substr(7).split('_');//共有3个元素，依次：rowIndex columnIndex field
	      var v=e.value;//这里直接取文本框的值，对单选框（组）要特殊处理
	      //可在此进行校验
	      var row=grid2.getRow(parseInt(ids[0]));
	      var col=grid2.getColumn(parseInt(ids[1]));
	      if (ids[2]=='indexOption') {
	   //在此将值更新回去
	   var obj={};
	   obj[ids[2]]=v;
	   grid2.updateRow(row, obj);
	   //console.log(nui.encode(grid.getRow(parseInt(ids[0]))));
   
	   var arr=document.getElementsByName(e.name);
	   var idx=0;
	   for (var i=0; i<arr.length; i++) {
	    if (arr[i].value==e.value) {
	     idx=i;
	     continue;
	    }
	    arr[i].checked=false;
	   }
       setTimeout(function(){
        	document.getElementsByName(e.name)[idx].checked=true;
       },100);
  	 	return;
      }
      if (ids[2]=='name') {
       if (e.value=='222') {//如果值错误
        grid2.setCellIsValid(row,col, false, '请输入正确的值');
        return;
       } else {
        grid2.setCellIsValid(row,col, true);
       }
   //在此将值更新回去
   var obj={};
   obj[ids[2]]=v;
   grid2.updateRow(row, obj);
   alert(nui.encode(grid2.getRow(parseInt(ids[0]))));
   return;
      }
     }	
</script>
</body>
</html>