<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): miaolf
  - Date: 2014-04-14 09:31:26
  - Description:
-->
<head>
<title>非财务信息</title>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
<div id="form1" style="width:100%;height:auto;"   class="datagridnew">
	<div id="datagrid1" class="nui-datagrid" showPager="false"
         allowCellEdit="true"  allowCellWrap="true"  allowCellSelect="true" idField="id" url="com.bos.irm.queryInfo.queryNonFinanceInfo.biz.ext" dataField="outs" editNextOnEnterKey="true" multiSelect="true" >
        <div property="columns">
        	<div type="indexcolumn" headerAlign="center">序号</div>
        	<div field="propertyTypeCd" name="propertyTypeCd" headerAlign="center" visible ='true'  align="center"  dictTypeId="XD_PJCD0019" width="10%">指标类别</div>
        	<div field="indexId" headerAlign="center" align="center" visible ='false' >指标</div>        
        	<div field="indexName" headerAlign="center" align="center" width="10%" >指标</div>        
            <div field="indexDesc" headerAlign="center"  width="10%"  >指标说明</div> 
			<div field="indexOption" name="indexOption" headerAlign="center"  renderer="renderCell" autoEscape="false" width="40%"  >指标选项
			</div>            
            <div field="remarks" headerAlign="center">如有特殊事项，请各位填入备注
                <input property="editor" name="remarks" class="nui-textarea" width="30%"   />
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
		var form1 = new nui.Form("#form1");
		//var form2 = new nui.Form("#form2");	
		var grid1 = nui.get("datagrid1");
		//var grid2 = nui.get("datagrid2");
		var flag=true;
		var state;
		var allowModifyFlag;//是否允许保存
 	var posicode;//当前用户岗位
     var modelId;//模型id
    var modelTypeCd;//评级模型
     
 		init();	
		if(reAud == "0"){
	    		applyId = "<%=request.getParameter("applyId") %>";
	    	}else{
	    		applyId = "<%=request.getParameter("oldApplyId") %>";
	    }
      //grid1.setData([{},{}]);//这里也可以用load方法
 
	    function init(){//初始化
		    if(reAud == "0"){
	    		applyId = "<%=request.getParameter("applyId") %>";
	    	}else{
	    		applyId = "<%=request.getParameter("oldApplyId") %>";
	    	}
	    //meau();
	    	// 合并
	    	
	    	  var proFlag ="<%=request.getParameter("proFlag") %>";
					if(proFlag!=1){
					nui.get("save").hide();
					flag=false;
					//nui.get("indexOption").setEnabled(false);
					}
	    	if("1"==proFlag){
	    		alert("请谨慎选择非财务信息!");
	    	}
	    	
    		grid1.on("load", function () {
           		 grid1.mergeColumns(["propertyTypeCd"]);
      		  });
    		grid1.load({"item":{"applyId":applyId,"partyId":partyId}});
    	
    		getAllowModifyFlag();
			getRateState();//获取评级状态并控制画面的显示
			getReAud();//通过再审核标识来控制页面编辑		
			indexDesc();	
		}
		
		//指标说明链接
		function indexDesc(){
			grid1.on("preload",function(e){
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
			var indexId = ps[1];
			var json = nui.encode({"indexId":indexId});
			var infourl = nui.context + "/irm/singleCustom/creditRate/irm_index_desc_detail.jsp?applyId="
	            + applyId+"&indexId="+indexId;
	           
	       nui.open({
		            url:infourl,
		            title: "指标说明", width: 600, height: 450,
		            onload: function(e){
		            	//var iframe = this.getIFrameEl();
		            	//var text = iframe.contentWindow.document.body.innerText;
		            	//alert(text);
		            },
		            ondestroy: function (action) {
		               // query();
		            }
	      	  });	
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
				grid1.allowCellEdit=false;
				//grid2.allowCellEdit=false;
				$("#save").hide();
			}
		}
		

		
		
		

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
		var financeId;
		var isImport="0";
		 	 
			 if(allowModifyFlag <-2){
			 	alert("获取评级结果次数过多，不能进行保存！");
			 	return;
			 } 
			var inRows1 =  new Array();
	  		grid1.findRows(function (row){
	  			var obj = new Object();
	  			obj.indexOption = row.indexOption;
	  			obj.indexId = row.indexId;
	  			obj.remarks = row.remarks;
	  			inRows1.push(obj);
	  			//obj=row.inData.indexId;
	  		});
	  		
	  	 	  var json = nui.encode({"item":{"reportId":financeId,"applyId":applyId},"data":data,"isImport":isImport,"modelTypeCd":modelTypeCd});
			nui.ajax({
				url:"com.bos.irm.insertInfomercial.addFinancialInfo.biz.ext",
				type: 'POST',
		  		data: json,
		  		cache: false,
		  	    contentType:'text/json',
		  	    async:false,
		  	    success: function (txt) {
		  	    		//alert(txt.msg);
		  	    },
		        error: function (jqXHR, textStatus, errorThrown) {
		            alert(jqXHR.responseText);
		        }
	    	});
    	
	   	    var json = nui.encode({"inRows":inRows1,"applyId":applyId});
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
		    	grid1.allowCellEdit=false;
		    	//grid2.allowCellEdit=false;
		    }
		    if (state == "2"){//待认定
		    	$("#save").hide();
		    	grid1.allowCellEdit=false;
		    	//grid2.allowCellEdit=false;
		    }		    
		}
		
		
	function renderCell(e) {
	      var id='custom_' + e.rowIndex + '_' + e.columnIndex + '_' + e.field;
	     // if (e.field =='indexOption') {//生成标准html的元素，并加上事件
	     // return '<textarea id="'+id+'" name="'+id+'" onchange="checkName(this)">'+(e.row[e.field] ? e.row[e.field] : '')+'</textarea>';
	     //}
	      if (e.field =='indexOption') {
	       var row=e.row;//这里是当前行的数据
	      //return alert(nui.encode(row));
	      var arr=[];//=[{text:'aaaxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx', value:'1', checked: false},{text:'bbb', value:'2', checked: true},{text:'aaaxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx', value:'3', checked: false}];
	      if(row.indexId =='undefined'){
	      	return ;
	      }
	       //return alert("1:"+row.indexId);
	       
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
	       var str ;
	       if(flag){
	         var str = '<input name="'+id+'" type="radio" '+(arr[i].checked ? 'checked' : '')
		         +' onclick="checkName(this)"  value="'+arr[i].value+'"></input>'+arr[i].indexOption;
		           if (i!=0)
		         str = '<br/>' + str;
		        html += str;
	       }else if(arr[i].checked==true){
	       str ="<label>"+arr[i].indexOption+"</label>";
	         html += str;
	         break;
	       }
		        //var str = '<input name="'+id+'" type="radio" '+(arr[i].checked ? 'checked' : '')
		        // +' onclick="checkName(this)" enabled="'+flag+'" value="'+arr[i].value+'"></input>'+arr[i].indexOption;
		       // if (i!=0)
		         //str = '<br/>' + str;
		     
	       }
	       return html;
	      }
     }
     
     function checkName(e) {
	      var id=e.id||e.name;
	      var ids=id.substr(7).split('_');//共有3个元素，依次：rowIndex columnIndex field
	      var v=e.value;//这里直接取文本框的值，对单选框（组）要特殊处理
	      //可在此进行校验
	      var row=grid1.getRow(parseInt(ids[0]));
	      var col=grid1.getColumn(parseInt(ids[1]));
	      if (ids[2]=='indexOption') {
	   //在此将值更新回去
	   var obj={};
	   obj[ids[2]]=v;
	   grid1.updateRow(row, obj);
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
        grid.setCellIsValid(row,col, false, '请输入正确的值');
        return;
       } else {
        grid.setCellIsValid(row,col, true);
       }
   //在此将值更新回去
   var obj={};
   obj[ids[2]]=v;
   grid1.updateRow(row, obj);
   alert(nui.encode(grid.getRow(parseInt(ids[0]))));
   return;
      }
     }	
</script>
</body>
</html>