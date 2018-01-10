<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> <!-- 
  - Author(s): wangshichun@git.com.cn
  - Date: 2014-03-25
  - Description:TB_ACC_CUSTOMER_FINANCE, accAnalysisProgram
--> <head> <%@include file="/common/nui/common.jsp"%>
</head> <body> <div id="form1"
	style="width:90%;height:auto;overflow:hidden; text-align:left"> <input
	type="hidden" name="accAnalysisProgram._entity"
	value="com.bos.dataset.acc.TbAccAnalysisProgram" class="nui-hidden" />
<input type="hidden" name="accAnalysisProgram.partyId"
	value="<%=request.getParameter("partyId")%>" class="nui-hidden" /> </div> <div
	class="nui-toolbar" style="border-bottom:0;"> <a id="add"
	class="nui-button" iconCls="icon-add" onclick="add()">新增方案</a> <a
	id="submit" class="nui-button" iconCls="icon-edit" onclick="submit()">提交方案</a>
<a id="query" class="nui-button" iconCls="icon-edit" onclick="edit(1)">执行财务分析</a>
<a id="edit" class="nui-button" iconCls="icon-edit" onclick="edit(0)">查看分析结果</a>
<a id="remove" class="nui-button" iconCls="icon-remove"
	onclick="remove()">删除方案</a> </div> <div id="grid1" class="nui-datagrid"
	style="width:100%;height:auto"
	url="com.bos.acc.analy.getAccAnalysisProgramList.biz.ext"
	dataField="accAnalysisPrograms" allowResize="true"
	showReloadButton="false" sizeList="[10,15,20,50,100]"
	multiSelect="false" pageSize="15" sortMode="client"> <div
	property="columns"> <div type="checkcolumn">选择</div> <div
	field="finanysisProgramNum" headerAlign="center" allowSort="true">方案名称</div>
<div field="programState" headerAlign="center" allowSort="true"
	dictTypeId="XD_ACCCD0009">方案状态</div> <!-- <div field="modelCd" headerAlign="center" allowSort="true" dictTypeId="XD_ACCCD0002">财务分析类型</div>-->
<div field="userNum" headerAlign="center" allowSort="true"
	dictTypeId="user">操作人员</div> <div field="userOrg" headerAlign="center"
	allowSort="true" dictTypeId="org">操作机构</div> <div field="createTime"
	headerAlign="center" allowSort="true">生成日期</div> </div> </div> <script
	type="text/javascript">
    nui.parse();
    var  qote="<%=request.getParameter("qote")%>";
    if(qote==1){
		nui.get("add").hide();
	   nui.get("edit").hide();
	   nui.get("remove").hide();
	}
    var form = new nui.Form("#form1"); 
	var grid = nui.get("grid1");
    function search() {
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
    }
    search();
    
    function reset(){
		form.reset();
	}
	
    function add() {
        nui.open({
            url: "acc/analy/program/acc_program_add.jsp?partyId=<%=request.getParameter("partyId")%>",
            showMaxButton: true,
            title: "新增", 
            width: 1024,
	        height: 540,
            ondestroy: function (action) {
               // if(action=="ok"){
                    search();
               // }
            }
        });
    }
    
    //提交财务分析方案
    function submit(){
       var row = grid.getSelected();
        if (row) {
          if(row.programState=='02' || row.programState=='03'){
           if(row.programState=='02'){
              alert('财务分析方案已经提交，不能重复提交');
              return;
           }
           if(row.programState=='03'){
              alert('财务分析方案已经归档，不能再次提交');
              return;
           }
           }
        	nui.confirm("财务分析方案提交后不能删除或者修改，您确定提交该财务分析方案吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"accAnalysisProgram":{"finanysisProgramId":row.finanysisProgramId}});
                $.ajax({
                    url: "com.bos.acc.analy.submitAnalysisProgram.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	if (text.msg) {
                    		nui.alert(text.msg);
                    		return;
                    	}
                        search();
                    },
                    error: function () {
                    	nui.alert("操作失败！");
                    }
                });
            }); 
        } else {
            alert("请选中一条记录");
        }
    }
    
    function edit(v) {
        var row = grid.getSelected();
        if (row) {
          if( v!='1'){
             nui.open({
                url: "acc/analy/analysis/acc_analy_tree.jsp?finanysisProgramId="+row.finanysisProgramId+"&modelCd="+row.modelCd,
                showMaxButton: true,
                title: "财报分析", 
                width: 1024,
	            height: 540,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                    //iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                   if(v=='0'){
                        search();
               	 	}
                }
            });
          }else{
          if(row.programState!='01'){
            alert('只有未提交财务分析方案才能执行分析');
            return;
           }
            nui.open({
                url: "acc/analy/program/acc_program_detail.jsp?finanysisProgramId="+row.finanysisProgramId,
                showMaxButton: true,
                title: "财报选择", 
                width: 1024,
	            height: 540,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                    //iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                   if(v=='0'){
                        search();
               	 	}
                }
            });
            }
        } else {
            alert("请选中一条记录");
        }
        
    }
    
    function remove() {
        var row = grid.getSelected();
        if (row) {
            
          if(row.programState!='01'){
            alert('只能删除未提交的财务分析方案');
            return;
           }
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	var json=nui.encode({"accAnalysisProgram":{"finanysisProgramId":
            		row.finanysisProgramId}});
                $.ajax({
                     url: "com.bos.acc.analy.delaccAnalysisProgram.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	if (text.msg) {
                    		nui.alert(text.msg);
                    		return;
                    	}
                        search();
                    },
                    error: function () {
                    	nui.alert("操作失败！");
                    }
                });
            }); 
        } else {
            alert("请选中一条记录");
        }
    }

	</script> </body> </html>
