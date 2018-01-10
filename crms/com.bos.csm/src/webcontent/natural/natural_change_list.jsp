<%@page pageEncoding="UTF-8"%>
<html>
<!-- 
  - Author(s): 甘泉
  - Date: 2015-05-21 12:42:24
  - Description:
-->
<head>
<%@ taglib uri="http://eos.primeton.com/tags/bean" prefix="b"%>
<%@include file="/common/nui/common.jsp"%>
</head>
<body>
	<center>
		<div id="form1" style="width: 99.5%; height: auto; overflow: hidden;">
			<input name="sqlName" value="com.bos.csm.natural.natural.changeList"
				class="nui-hidden" /> <input name="item.partyId" id="item.partyId"
				class="nui-hidden nui-form-input"
				value="<%=request.getParameter("bizId")%>" /> <input
				name="item.processInstId" id="item.processInstId"
				class="nui-hidden nui-form-input"
				value="<%=request.getParameter("processInstId")%>" />
		</div>
		<div id="datagrid1" class="nui-datagrid"
			style="width: 99.5%; height: auto;" sortMode="client"
			url="com.bos.csm.pub.ibatis.getItem.biz.ext" dataField="items"
			allowAlternating="true" multiSelect="false" showReloadButton="false"
			sizeList="[10,20,50,100]" pageSize="20">
			<div property="columns">
				<div field="modifyColumn_show" allowSort="true" headerAlign="center">信息项
				</div>
				<div field="originalModifyValue_show" allowSort="true"
					headerAlign="center">原值</div>
				<div field="newModifyValue_show" allowSort="true" headerAlign="center">新值</div>
			</div>
		</div>
	</center>
	<script type="text/javascript">
	//---
	
		var map = {
					partyId:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.partyId"/>',
					partyName:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.partyName"/>',
					englishName:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.englishName"/>',
					chineseShortName:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.chineseShortName"/>',
					englishShortName:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.englishShortName"/>',
					genderCd:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.genderCd"/>',
					marriageCd:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.marriageCd"/>',
					birthday:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.birthday"/>',
					degreeCd:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.degreeCd"/>',
					educationBackgroudCd:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.educationBackgroudCd"/>',
					nation:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.nation"/>',
					remark:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.remark"/>',
					createTime:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.createTime"/>',
					updateTime:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.updateTime"/>',
					updateUserNum:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.updateUserNum"/>',
					updateOrgNum:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.updateOrgNum"/>',
					naturalPersonTypeCd:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.naturalPersonTypeCd"/>',
					workUnit:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.workUnit"/>',
					certType:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.certType"/>',
					certNum:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.certNum"/>',
					hukouProperty:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.hukouProperty"/>',
					isFarmer:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.isFarmer"/>',
					hukouRegisted:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.hukouRegisted"/>',
					streetPoliceStation:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.streetPoliceStation"/>',
					healthState:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.healthState"/>',
					profession:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.profession"/>',
					professionalTitle:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.professionalTitle"/>',
					accountingAssistant:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.accountingAssistant"/>',
					positionProperty:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.positionProperty"/>',
					workYears:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.workYears"/>',
					familyNumber:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.familyNumber"/>',
					provideForNumber:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.provideForNumber"/>',
					familyAddress:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.familyAddress"/>',
					houseProperty:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.houseProperty"/>',
					familyPhone:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.familyPhone"/>',
					phoneNumber:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.phoneNumber"/>',
					unitAdress:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.unitAdress"/>',
					unitPhone:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.unitPhone"/>',
					industry:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.industry"/>',
					industryDesc:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.industryDesc"/>',
					jointGuarantee:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.jointGuarantee"/>',
					stockholderOfBank:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.stockholderOfBank"/>',
					whetherBlackList:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.whetherBlackList"/>',
					blackListReason:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.blackListReason"/>',
					isBankEmployee:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.isBankEmployee"/>',
					employeeGrade:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.employeeGrade"/>',
					isSamllLoandEmp:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.isSamllLoandEmp"/>',
					countrySign:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.countrySign"/>',
					isBasebankRelaCust:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.isBasebankRelaCust"/>',
					thirdCustTypeCd:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.thirdCustTypeCd"/>',
					isThirdCust:'<b:label modelField="com.bos.dataset.csm.TbCsmNaturalPerson.isThirdCust"/>'
			}
			
			var dictTypeIdMap = {
					genderCd:'CDKH0048',
					marriageCd:'CDKH0043',
					degreeCd:'CDZZ0014',
					educationBackgroudCd:'CDKH0045',
					nation:'CDKH0046',
					certType:'CDKH0002',
					hukouProperty:'XD_KHCD2001',
					isFarmer:'YesOrNo',
					healthState:'XD_KHCD2002',
					profession:'XD_KHCD2003',
					professionalTitle:'CDKH0066',
					accountingAssistant:'XD_KHCD2004',
					positionProperty:'XD_KHCD2005',
					houseProperty:'XD_KHCD2006',
					industry:'XD_KHCD2007',
					countrySign:'CD000003',
					thirdCustTypeCd:'XD_KHCD7001',
					isThirdCust:'YesOrNo'
			}

		//---
		nui.parse();
		git.mask();
		var form = new nui.Form("#form1");
		var grid = nui.get("datagrid1");
		var partyId = "<%=request.getParameter("bizId") %>";
		var qote = "<%=request.getParameter("qote")%>" ;
		
		if(qote==1){
		   nui.get("add").hide();
		   nui.get("remove").hide();
		}
		
	function init() {
	  if (partyId) {
			nui.get("item.partyId").setValue(partyId);
		}
		var data = form.getData(); //获取表单多个控件的数据
        grid.load(data);
		git.unmask();
     }
     
     init();
     grid.on("preload", function(e) {
		if (!e.data || e.data.length < 1) {
			return;
		}
		for (var i = 0; i < e.data.length; i++) {
				e.data[i]['modifyColumn_show'] = map[e.data[i].modifyColumn];
				if(e.data[i].modifyColumn in dictTypeIdMap){
					e.data[i]['newModifyValue_show'] = nui.getDictText(dictTypeIdMap[e.data[i].modifyColumn],e.data[i].newModifyValue);
					e.data[i]['originalModifyValue_show'] = nui.getDictText(dictTypeIdMap[e.data[i].modifyColumn],e.data[i].originalModifyValue);
				}else{
					if(e.data[i].modifyColumn=="birthday"){
						e.data[i]['newModifyValue_show'] = nui.formatDate(e.data[i].newModifyValue,"yyyy-MM-dd");
						e.data[i]['originalModifyValue_show'] = nui.formatDate(e.data[i].originalModifyValue,"yyyy-MM-dd");
					}else{
						e.data[i]['newModifyValue_show'] = e.data[i].newModifyValue;
						e.data[i]['originalModifyValue_show'] = e.data[i].originalModifyValue;
					}
					
				}
		}
	});
	
	function add() {
            nui.open({
                url:nui.context + "/csm/natural/natural_relative_add.jsp?partyId="+partyId,
                title: "新增", 
                width: 800, 
            	height: 500,
            	allowResize:true,
            	showMaxButton: true,
                ondestroy: function (action) {
                    if(action=="ok"){
                        git.mask();
                        init();
                    }
                }
            });
        }
	
	function edit(e) {
        var row = grid.getSelected();
        if (row) {
            nui.open({
                url: nui.context + "/csm/natural/natural_info.jsp?partyId="+row.partyId+"&qote=1",
                title: "查看编辑", 
                width: 800,
        		height: 500,
                allowResize:true,
        		showMaxButton: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = row;
                },
                ondestroy: function (action) {
                    if(action=="ok"){
                        git.mask();
                        init();
               	 	}
                }
            });
        } else {
            alert("请选中一条记录");
        }
        
    }
        
     
      function remove() {
        var row = grid.getSelected();
        
        if (row) {
        	nui.confirm("确定删除吗？","确认",function(action){
            	if(action!="ok") return;
            	git.mask();
            	var json=nui.encode({"item":{"naturalRelativeId":
            		row.naturalRelativeId,
					"_entity":"com.bos.dataset.csm.TbCsmNaturalRelative"}});
                $.ajax({
                     url: "com.bos.csm.pub.crudCustInfo.delItem.biz.ext",
	                type: 'POST',
	                data: json,
	                cache: false,
	                contentType:'text/json',
                    success: function (text) {
                    	git.unmask();
                    	if (text.msg) {
                    		nui.alert(text.msg);
                    		return;
                    	}
                        grid.reload();
                    },
                    error: function () {
                    	git.unmask();
                    	nui.alert("操作失败！");
                    }
                });
            }); 
        } else {
            nui.alert("请选中一条记录");
        }
    }
	
</script>
	
	
</body>
</html>