<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="/common/nui/common.jsp" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>数据字典管理</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <link href="../demo.css" rel="stylesheet" type="text/css" />
    
    <style type="text/css">
        .New_Button, .Edit_Button, .Delete_Button, .Update_Button, .Cancel_Button
        {
            font-size:11px;color:#1B3F91;font-family:Verdana;  
            margin-right:5px;          
        }
                
    </style>        
</head>
<body>
<TABLE height="100%" cellSpacing=0 cellPadding=0 width="98%" border=0>
        <TR>
            <TD width="46%" height="100%" align="left" valign="top">
                <!-- 业务字典类型 -->
                <table align="center" border="0" width="100%" height="100%" cellSpacing=0 cellPadding=0>
                    <tr height="20%">
                        <td style="width:100%;height:20%">
                        	<fieldset style="border:solid 1px #aaa;padding:3px;width:100%">
                            <!--<h1>业务字典管理</h1>-->
							<div class="search-condition">
									<div class="list">
										<form id="form1" name="form1" action="com.bos.utp.dict.DictManager.flow?_eosFlowAction=dictExport" method="post">
											<table class="table">
												<tr>
													<td class="tit">类型代码：</td>
													<td width="25%">
													  	<input name="criteria/_expr[1]/dicttypeid" class="nui-textbox"/>
													  	<input type="hidden" class="nui-hidden" name="criteria/_expr[1]/_op" value="="/>
											        </td>
											        <td class="tit">类型名称：</td>
													<td width="25%">
														<input name="criteria/_expr[2]/dicttypename" class="nui-textbox"/>
														<input type="hidden" class="nui-hidden" name="criteria/_expr[2]/_op" value="like"/>
										              	<input type="hidden" class="nui-hidden" name="criteria/_expr[2]/_likeRule" value="all"/>
										            </td>
											    </tr>
												<tr>
													<td class="btn-wrap" colspan="4">
														<a class="nui-button"  iconCls="icon-reload" onclick="refresh()">刷新缓存</a>&nbsp;&nbsp;
														<a class="nui-button"  iconCls="icon-download" type="submit" onclick="exportDict();" />导 出</a>&nbsp;&nbsp;
														<a class="nui-button"  iconCls="icon-search" onclick="search()">查 询</a>&nbsp;&nbsp;
														<a class="nui-button"  iconCls="icon-reset" onclick="reset()">重 置</a>
													</td>
												</tr>
											</table>
										</form>
									</div>
								</div>
								</fieldset>
                        </td>
                    </tr>
                       
                    <tr height="67%">
                            <td width="100%" align="left" valign="top" colspan="2" height="67%">
                                <div style="width:100%;">
							        <div class="nui-toolbar" style="border-bottom:0;padding:0px;width:100%;">
							            <table style="width:100%;">
							                <tr>
							                    <td style="width:100%;">
							                        <a class="nui-button" iconCls="icon-add" onclick="addRow()">增加</a>
							                        <a class="nui-button" iconCls="icon-remove" onclick="removeRow()">删除</a>
							                        <span class="separator"></span>
							                        <a class="nui-button" iconCls="icon-save" onclick="saveData()">保存</a>            
							                    </td>
							                   <!-- <td style="white-space:nowrap;">
							                        <input id="dicttypeid" class="nui-textbox" emptyText="请输入字典ID" style="width:150px;" onenter="onKeyEnter"/>
							                        <input id="dicttypename" class="nui-textbox" emptyText="请输入名称" style="width:150px;" onenter="onKeyEnter"/> 
							                        <a class="nui-button" onclick="search()">查询</a>
							                    </td>-->
							                </tr>
							            </table>           
							        </div>
							    </div>    
							
							    <div id="dicttype_grid" class="nui-datagrid" style="width:100%;height:100%;" 
							        url="com.bos.utp.dict.DictManager.queryDictType.biz.ext"  idField="dicttypeid"
							        allowResize="true" pageSize="10" allowAlternating="true"
							        allowCellEdit="true" allowCellSelect="true" multiSelect="false" editNextOnEnterKey="true"
							        onselectionchanged="onSelectionChanged">
							        <div property="columns">
							            <div field="dicttypeid" headerAlign="center" >类型代码
							             	<input property="editor" class="nui-textbox" style="width:100%;" />
							            </div>                                        
							            <div field="dicttypename" headerAlign="center" >类型名称
							            	<input property="editor" class="nui-textbox" style="width:100%;" />
							            </div>          
							        </div>
							    </div> 
                            <td>
                    <tr>
                </table>
                </TD>
                <TD id="entryInfo" height="100%" valign="top" width="64%">
                    <TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%" border=0>
                        <TR height="40%">
                            <TD width="100%" align="left" height="40%" valign="top">
                                <div id="layout1" class="nui-layout" width="100%" height="100%">
									<div title="数据字典项" width="100%" class="panel" allowResize="false">
										<ul id="tree1" class="nui-tree" url="com.bos.utp.dict.DictManager.queryDictForTree.biz.ext"
											style="width:100%;height:100%;padding:5px;" 
										    showTreeIcon="true" textField="NAME" dataField="data"
										    idField="ID" parentField="PARENTID" resultAsTree="false"> 
										</ul>
									</div>
								</div>
                                
                            </TD>
                        </TR>
                        <TR height="60%">
                            <TD width="100%" height="60%" align="left" valign="top" >
                                <!-- 业务字典类型项 -->
                                <div style="width:100%;">
							        <div class="nui-toolbar" style="border-bottom:0;padding:0px;">
							            <table style="width:100%;height:15%;">
							                <tr>
							                    <td style="width:100%;">
							                        <a class="nui-button" iconCls="icon-add" onclick="addEntryRow()" >增加</a>
							                        <a class="nui-button" iconCls="icon-remove" onclick="removeEntryRow()" >删除</a>
							                        <span class="separator"></span>
							                        <a class="nui-button" iconCls="icon-save" onclick="saveEntryData()" >保存</a>            
							                    </td>
							                </tr>
							            </table>           
							        </div>
							    </div>
							    <div id="dictentry_grid" class="nui-datagrid" style="width:100%;height:85%;" 
							        url="com.bos.utp.dict.DictManager.getDictTypeEntry.biz.ext" idField="dicttypeid"
							        allowResize="true" showPager="true" onselectionchanged=""  allowAlternating="true"
							        allowCellEdit="true" allowCellSelect="true" multiSelect="true" editNextOnEnterKey="true">
							        <div property="columns">
							        	<div field="dictid" headerAlign="center" allowSort="false">类型代码
							            	<input property="editor" class="nui-textbox" style="width:100%;" />
							            </div>
							        	<div field="parentid" headerAlign="center" allowSort="false">上级类型代码
							            	<input property="editor" class="nui-textbox" style="width:100%;" />
							            </div>
							            <!--<input class="nui-hidden" name="eosDictType.dicttypeid" id="eosDictType.dicttypeid" value="" />-->           
							            <div field="dictname" headerAlign="center" allowSort="false">类型项名称
							            	<input property="editor" class="nui-textbox" style="width:100%;" />
							            </div>
							            <div field="sortno" headerAlign="center" allowSort="false">排序
							            	<input property="editor" class="nui-textbox" style="width:100%;" />
							            </div>
							            <div type="checkboxcolumn" field="status" trueValue="0" falseValue="1" headerAlign="center">是否封存</div>             
							        </div>
							    </div>
                            </TD>
                        </TR>
         			</TABLE>
         		</TD>
         	</TR>
       </TABLE>
    
	
    <script type="text/javascript">
        nui.parse();
		var form = new nui.Form("#form1");
        var dicttype_grid = nui.get("dicttype_grid");
        var dictentry_grid = nui.get("dictentry_grid");
        var tree = nui.get("tree1");

        dicttype_grid.load();
        
        tree.load();
        
        dictentry_grid.load();


        function onSelectionChanged(e) {
            var grid = e.sender;
            var rownum = grid.indexOf(grid.getSelected());
            var co = grid.getCellEditor (0, rownum);
        	$("#" + co.id).attr({"disabled":"disabled"});
        	//$("#" + co.id).attr({"readOnly":true});
            var record = grid.getSelected();
            
            if (record) {
            	if (record.name)
            		$("#" + co.id).attr({"disabled":null});
            	tree.load({ dicttypeid: record.dicttypeid});
                dictentry_grid.load({ dicttypeid: record.dicttypeid });
            }
        }
        
        function search() {       
            //var dicttypeid = nui.get("dicttypeid").getValue();
            //dicttype_grid.load({ dicttypeid: dicttypeid });
            var data = form.getData(false,false);      //获取表单多个控件的数据
            dicttype_grid.load(data);
        }
        
        function onKeyEnter(e) {
            search();
        }

        function addRow() {
            var newRow = { name: "New Row" };
            dicttype_grid.addRow(newRow, 0);
            //var row = dicttype_grid.getRowByUID(newRow._uid)
            //row.allowInput = true;
            //var json = nui.encode(row);
        }
        
        function addEntryRow() {          
            var newRow = { name: "New Row" };
            dictentry_grid.addRow(newRow, 0);
        }
        
        function removeRow() {
            var rows = dicttype_grid.getSelecteds();
            if (rows.length > 0) {
                dicttype_grid.removeRows(rows, true);
            }
        }
        
        function removeEntryRow() {
            var rows = dictentry_grid.getSelecteds();
            if (rows.length > 0) {
                dictentry_grid.removeRows(rows, true);
            }
        }
        
        function saveData() {
            var data = {eosDictTypes:dicttype_grid.getChanges()};
            var json = nui.encode(data);
            dicttype_grid.loading("保存中，请稍后......");
            nui.ajax({
                url: "com.bos.utp.dict.DictManager.saveDictType.biz.ext",
                type: 'POST',
                data: json,
                success: function (text) {
                	dicttype_grid.reload();
                	tree.reload();
                	dictentry_grid.reload();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });
        }
        
        function saveEntryData() {
        	var record = dicttype_grid.getSelected();
        	if (!record) {
        		nui.alert("请在左侧表格中选择一条“业务字典类型”记录");
        		return;
        	}
        	var entryForm = new nui.Form("#dictentry_grid");
			var entryFormData = entryForm.getData(false, true);
			
			
            var data = {eosDictEntries:dictentry_grid.getChanges(),dicttypeid:record.dicttypeid};
            var json = nui.encode(data);
            dictentry_grid.loading("保存中，请稍后......");
            nui.ajax({
                url: "com.bos.utp.dict.DictManager.saveDictEntry.biz.ext",
                type: 'POST',
                data: json,
                success: function (text) {
                    dictentry_grid.reload();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });
        }
        
        function refresh(){
    		var refresh = confirm('您确认需要刷新缓存中的业务字典信息吗');//您确认需要刷新缓存中的业务字典信息吗？
    		var reCode;
			if (refresh){
    			refreshDict();
			}
    	}
    	
    	function reset(){//重置
			form.reset();
		}
        
        function refreshDict() {
            var o = form.getData(true,true);            
			
            form.validate();
            if (form.isValid() == false) return;

            var json = nui.encode(o);
            $.ajax({
                url: "com.bos.utp.dict.DictManager.reloadDictType.biz.ext",
                type: 'POST',
                data: json,
                cache: false,
                contentType:'text/json',
                success: function (text) {
                    var response = text.response || {};
            		if(response){
            			nui.alert(response.message);
            		}
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    //CloseWindow();
                }
            });
    	}
    	
    	/**
    	*导出数据字典
    	*/
    	function exportDict()
	    {
            var frm = document.getElementById("form1");
			frm.submit();
	    }
    	
    	
    	function exportDictbak(){
			var o = form.getData(true,true);            
			
            form.validate();
            if (form.isValid() == false) return;

            var json = nui.encode(o);
            $.ajax({
                url: "com.bos.utp.dict.DictManager.exportDictExcel.biz.ext",
                type: 'POST',
                data: json,
                cache: false,
                contentType:'text/json',
                success: function (text) {
                    nui.open({
                    url: nui.context+"/pub/io/file/download.jsp",
                    title: "编辑菜单",
                    width: 600,
                	height: 167,
                    onload: function () {
                        var iframe = this.getIFrameEl();
                        var data = row;
                        iframe.contentWindow.SetData(data);
                    },
                    ondestroy: function (action) {
                    	if(action=="ok"){
	                    	parent.refresh();
	                        grid.reload();
	                    }
                    }
                });
                    
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    //CloseWindow();
                }
            });
		}
    </script>
</body>
</html>