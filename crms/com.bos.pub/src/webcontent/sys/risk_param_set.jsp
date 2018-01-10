<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!-- 
  - Author(s): ganquan
  - Date: 2015-06-25
  - Description:五级分类减值比例设置
-->
<head>
<%@include file="/common/nui/common.jsp" %>
</head>
<body>
 <div id="datagrid1" class="nui-datagrid" style="width:99.5%;height:auto;" allowResize="true" showPager="false" 
        url="com.bos.csm.pub.ibatis.getItem.biz.ext"  idField="id" allowMoveColumn="false" allowAlternating="true" 
        emptyText="数据为空"  showEmptyText="true" dataField="items">
        
        <div property="columns">
            <div name="action" width="20%" headerAlign="center" align="center" renderer="onActionRenderer" cellStyle="padding:0;">#</div>
            <div field="riskLevel" headerAlign="center" align="center" width="40%" dictTypeId="XD_FLCD0001">分类级别</div>
			<div field="valPercent" allowResize="false"  width="40%" align="center" headerAlign="center">减值比例(%)
	            <input property="editor" class="nui-textbox" style="width:100%;" />
	        </div>
        </div>
</div>    
 <script type="text/javascript">
 	nui.parse();
    var grid2 = nui.get("datagrid1");
    var item = new Object();
    item.sqlName = 'com.bos.sys.riskParam.paramList';
    grid2.load(item);
    
    function onActionRenderer(e) {
       var grid2 = e.sender;
       var record = e.record;
       var uid = record._uid;
       var rowIndex = e.rowIndex;

       var s = ' <a class="Edit_Button" href="javascript:editRow(\'' + uid + '\')" >编辑</a>';

       if (grid2.isEditingRow(record)) {
           s = '<a class="Cancel_Button" href="javascript:updateRow(\'' + uid + '\')">更新</a>'
           +' <a class="Cancel_Button" href="javascript:cancelRow(\'' + uid + '\')">取消编辑</a>';
        }
         return s;
      }

   
      function editRow(row_uid) {
         var row = grid2.getRowByUID(row_uid);
         if (row) {
            grid2.cancelEdit();
            grid2.beginEditRow(row);
         }
      }
      function cancelRow(row_uid) {            
         grid2.reload();
      }

	  function updateRow(row_uid) {
            var row = grid2.getRowByUID(row_uid);
            
            grid2.commitEdit();
            var rowData = grid2.getChanges();
            
            grid2.loading("保存中，请稍后......");
            var json = nui.encode({riskParams:rowData});
            
            nui.ajax({
                url: "com.bos.sys.riskParam.saveRiskParamSets.biz.ext",
                data:json,
                type:"POST",
                contentType:'text/json',
                success: function (text) {
                    grid2.reload();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });

        }
	
</script>
</body>
</html>
