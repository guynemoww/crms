<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 
  - Author(s): lizhi
  - Date: 2014-05-13
  - Description:
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
	<div class="nui-toolbar" style="border-bottom:0;display:none">
		<a class="nui-button" iconCls="icon-remove" onclick="deleteSores()">清空打分</a>
	</div>
	<!-- 显示客户综合评分 -->	    
	<div id="tableDiv">
		
	</div>
	<div class="nui-toolbar" style="border-bottom:0;text-align:center;">
		<a class="nui-button" iconCls="icon-save" onclick="save()" id="btnSave">确定</a>
	</div>			

	<script type="text/javascript">
 	nui.parse();
	//设置客户综合评分
	
	//传入贷后检查客户情况ID
	var param=<%=request.getParameter("param") %>;
	var alcInfoId =param.alcInfoId;//"900001"
	//var alcInfoId ="900001";//"900001"
	
	//设置客户类型
	var indexCd = "";//企业：CUCS010000;事业：CUCS020000
	//过滤不需要显示的说明
	var filterIndexCd = "";//企业过滤：CUCS010500;事业过滤：CUCS020400
	
	var corpCustomerTypeCd = param.corpCustomerTypeCd;
	
	if(corpCustomerTypeCd == null || corpCustomerTypeCd == '' || corpCustomerTypeCd == 'undefined') {//默认企业
		indexCd = "CUCS010000";
		filterIndexCd = "CUCS010500";
	} else if(corpCustomerTypeCd == '21101') {//企业
		indexCd = "CUCS010000";
		filterIndexCd = "CUCS010500";
	} else if(corpCustomerTypeCd == '21301') {//事业
		indexCd = "CUCS020000";
		filterIndexCd = "CUCS020400";
	}
	//alert(indexCd+';'+filterIndexCd);
	
	//页面的记录总数
	var length = 0;
	//页面的记录id--indexCdId2
	var recordIds = [];
	//页面初始化记录
	var customerGrades1;
	//页面当前的记录
	var customerGrades2 = [];
	
	var form = new nui.Form("#tableDiv");
	//初始化页面
    function initGrid() {
		$('#tableDiv').html('');
		var json= nui.encode({"indexCd":indexCd,"filterIndexCd":filterIndexCd,"alcInfoId":alcInfoId});
		$.ajax({
		            url: "com.bos.aft.dailyInspect.getComprehensiveGradeList.biz.ext",
		            type: 'POST',
		            data: json,
		            async:false,
		            cache: false,
		            contentType:'text/json',
		            success: function (text) {
		            	var tableString = '<table id="table"  border="2" width="100%" class="nui-form-table">'+
		    								 '<tr>'+
							    			   '<th class="nui-form-label" align="center" colspan="1" width="220">类型</th>'+
							    			   '<th class="nui-form-label" align="center" colspan="1" width="300">指标</th>'+
							    			   '<th class="nui-form-label" align="center" colspan="1" width="500">打分</th>'+
							    			   '<th class="nui-form-label" align="center" colspan="1" width="2000">说明</th>'+
							    		     '</tr>'+
   									       '</table>';
   						var newTable = $(tableString);
   						var customerGrades = text.customerGrades;
   						customerGrades1 = text.customerGrades;
   						length = customerGrades.length;
   						//动态生成表格
   						//生成行 
   						for(var i=1;i<=customerGrades.length;i++) {
   							var type = customerGrades[i-1].type;
   							var indexName = customerGrades[i-1].indexName;
   							var score = customerGrades[i-1].score;
   							var comment = customerGrades[i-1].comment;
   							recordIds[i-1] = customerGrades[i-1].indexCdId2;//indexCdId2
   							//如果没有值，设置为空
   							if((typeof comment)=='undefined' ) {
   								comment = "";
   							}
   							if((typeof score)=='undefined' || score == 0 || score == '0' ) {
   								score = "";
   							}
							var trString = '<tr id="'+i+'">'+
											        '<td class="nui-form-label" id="'+i+'/1" colspan="1" width="100">'+type+
											   '</td><td class="nui-form-label" id="'+i+'/2" colspan="1" width="100">'+indexName+
											   '</td><td class="nui-form-label" id="'+i+'/3" colspan="1" width="50">'+
											   '<input id="input'+i+'" class="nui-textbox nui-form-input" vtype="float" required="true" emptyText="请评分" value="'+score+'"/>'+
											   '</td><td class="nui-form-label" id="'+i+'/4" colspan="1" width="200">'+comment+
											   '</td></tr>';
							var newTr = $(trString);
							newTr.appendTo(newTable);	
							
   						}//生成行结束
   						
   						newTable.appendTo($('#tableDiv'));
   						//合并相同单元格
   						var typeCount = 1;
   						customerGrades = customerGrades.concat('end');//合并最后的单元格
   						for(var i=1;i<=customerGrades.length;i++) {
   							//alert(customerGrades.length);
   							if(customerGrades[i].type == customerGrades[i-1].type) {
   								typeCount++;	
   							} else {
   								//合并
   								merge(i-typeCount+1,i,1,1);
   								typeCount = 1;
   							}
   						}
   						nui.parse(document.getElementById('table'));
		            },
		            error: function () {
	                    nui.alert("保存失败！");
	                }
			});
    }
	//初始化页面
    initGrid();
    
    //保存打分值
    function save() {
    	form.validate();
        if (form.isValid()==false) return;
        git.mask();
    	var j = 0;
    	for(var i=1;i<=length;i++) {
    		//创建customerGrades2
    		var id = 'input'+i;
    		var indexCdId2 = recordIds[i-1];
    		var score = nui.get(id).getValue();//当前分数(非空)
    		//过滤未改变的值
    		if(score != customerGrades1[i-1].score) {
    			customerGrades2[j] = {"indexCdId2":"","score":""};
    			customerGrades2[j].indexCdId2 = indexCdId2;
    			customerGrades2[j].score = score;
    			j++;
    		} 
    	}
    	//alert(customerGrades2.length);//注意：第一次运行程序时，即使只更改了一条数据，但会更该数据库中的所有数据。执行这条语句，可正确运行。
    	//评分有变化
    	if(new Number(customerGrades2.length) != 0) {
			var updateTime = nui.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss");
	    	var data = {"alcInfoId":alcInfoId,"updateTime":updateTime,"customerGrades":customerGrades2};
	        var json = nui.encode(data);
	        nui.ajax({
	                url: "com.bos.aft.dailyInspect.updateCustomerGrades.biz.ext",
	                type: 'POST',
	                data: json,
	                //async:false,
	                cache: false,
	                success: function (text) {
	                	git.unmask();
	                    if(text.msg) {
	                    	alert(text.msg);
	                    } else {
	                    	alert("保存失败");
	                    }
	                },
	                error: function (text) {
	                	git.unmask();
	                    if(text.msg) {
	                    	alert(text.msg);
	                    } else {
	                    	alert("保存失败");
	                    }
	                }
	        }); 
    	} else {
    		alert("保存成功");//评分无变化
    	}
    	git.unmask();
    }
    
    //清空打分
    function deleteSores() {
    	git.mask();
    	var json = nui.encode({"indexCd":indexCd,"alcInfoId":alcInfoId});
        nui.ajax({
                url: "com.bos.aft.dailyInspect.deleteSores.biz.ext",
                type: 'POST',
                data: json,
                success: function (text) {
                	git.unmask();
                    if(text.msg) {
                    	alert(text.msg);
                    } else {
                    	alert("清空失败");
                    }
                },
                error: function (text) {
                	git.unmask();
                    if(text.msg) {
                    	alert(text.msg);
                    } else {
                    	alert("清空失败");
                    }
                }
        }); 
        initGrid();
        //window.location.reload();
    }
    
    //合并相同的单元格
    function merge(beginrow,endrow,begincell,endcell){
		var rowspan=endrow-beginrow+1;
		var cellspan=endcell-begincell+1;
		for (var i=endrow;i>=beginrow;i--)
				for (var j=endcell;j>=begincell;j--) {
					if (i==beginrow&&j==begincell){}else{//指定的起始单元格不能删除
						if(document.getElementById(i+'/'+j)!=null) {
							elem=document.getElementById(i+'/'+j);elem.parentNode.removeChild(elem);	
						}
				}
		}
		var obj=document.getElementById(beginrow+'/'+begincell);
		obj.setAttribute('rowspan',rowspan);
		obj.setAttribute('colspan',cellspan);
	}
	</script>
</body>
</html>
