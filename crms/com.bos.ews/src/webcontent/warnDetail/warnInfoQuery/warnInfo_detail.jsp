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

<fieldset>
  	 <legend>
    	<span>预警事项描述</span>
     </legend>
     <input id="tbRewLevelAdjust.matterState" class="nui-textarea nui-form-input" name="tbRewLevelAdjust.matterState" disabled="true" style="height:150px;width:100%"/>
   </fieldset>
   
   <fieldset>
  	 <legend>
    	<span>拟采取的控制措施及建议</span>
     </legend>
     <input id="tbRewLevelAdjust.suggestState" class="nui-textarea nui-form-input"  name="tbRewLevelAdjust.suggestState" disabled="true" style="height:150px;width:100%" />
   </fieldset>

 <fieldset>
  	<legend>
    	<span>相关预警信号列表</span>
    </legend>

<div id="grid1" class="nui-datagrid" style="width:100%;height:auto" url="com.bos.ews.earlyWarnChangeRecord.getWarnInfoDetail.biz.ext"    
	 dataField="warnInfos" allowResize="true" showReloadButton="false" showPager="false" sortMode="client">
	<div property="columns">
	<div type="indexcolumn">选择</div>
	     <div field="csmSignalId" headerAlign="center" dictTypeId="XD_YJCD0003" allowSort="true"> 预警信号 </div>
         <div field="csmwarningtypeid" headerAlign="center" dictTypeId="XD_YJCD0003" allowSort="true"> 预警信号类别 </div>
         <div field="signalSourceCd" headerAlign="center" allowSort="true"   dictTypeId="XD_YJCD0001"> 预警信号来源 </div>
         <div field="signalStatusCd" headerAlign="center" allowSort="true" dictTypeId="XD_YJCD0002"> 信号状态 </div>
         <div field="holdDate" headerAlign="center"  allowSort="true" dateFormat="yyyy-MM-dd">预警信号认定日期</div>
         <div field="closeDate" headerAlign="center"  allowSort="true" dateFormat="yyyy-MM-dd">预警信号关闭日期</div>
	    
	</div>
</div>
 </fieldset>	
    <script type="text/javascript">
 	nui.parse();
	var grid = nui.get("grid1");
	var bizId="<%=request.getParameter("bizId") %>";                //获取业务ID
	var changeType = "<%=request.getParameter("changeType") %>";    //获取流程类型   
	//grid.load({type:type});                                       //加载预警客户信息
    function search(e) {
    
    	var json=nui.encode({bizId:bizId});
		$.ajax({      
            url: "com.bos.ews.warnInfo.csmWarnInfoQuery.biz.ext",
            type: 'POST',
            data: json,
            cache: false,
            contentType:'text/json',
            success: function (text) {
            		nui.get("tbRewLevelAdjust.matterState").setValue(text.tbRewLevelAdjust.matterState);
            		nui.get("tbRewLevelAdjust.suggestState").setValue(text.tbRewLevelAdjust.suggestState);
            	    var text = nui.decode(text);
            	    form.setData(text);
            	    
            },
            error: function (jqXHR, textStatus, errorThrown) {
                nui.alert(jqXHR.responseText);
                
            }
		});
    	
        git.mask();                                                  //页面遮罩
	    //var warnBizInfo = form.getData();                            //获取表单多个控件的数据                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
		grid.load({bizId:bizId,changeType:changeType});
		git.unmask(); 
	}
    search();
    
    function warnInfoDetail(){
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
   </script>
</body>
</html>
