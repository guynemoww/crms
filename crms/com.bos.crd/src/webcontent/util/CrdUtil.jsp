<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script>
	var CrdUtil = {
		limitMode : "warn",

		getLimitCodeText : function(type, code) {
			if (type == "10") {
				return getDictText("org", code);
			} else if (type == "20") {
				return getDictText("product", code);
			} else if (type == "30") {
				return getDictText("CDZC0005", code);
			} else if (type == "90") {
				return getDictText("CDXY0300", code);
			} else {
				return "";
			}
		},

		getErrorMsg : function(msg) {
			if (msg.substr(0, 1) == "{" && msg.substr(msg.length - 1, 1) == "}") {
				var error = nui.decode(msg);
				if (error.limitCode) {
					return CrdUtil.getLimitCodeText(error.limitType,
									error.limitCode) + "已超最高额度";
				} else {
					return error.msg;
				}
			} else {
				return msg;
			}
		},

		checkLimit : function(url, param) {
			var json = nui.encode(param);
			var canAction = false;
			$.ajax({
				url : url,
				type : 'POST',
				data : json,
				contentType : 'text/json',
				cache : false,
				async : false,
				success : function(mydata) {
					debugger;
					if (mydata.msg == null || mydata.msg == '') {
						canAction = true;
					} else {

						var msg = CrdUtil.getErrorMsg(mydata.msg, false);
						if ("warn" == CrdUtil.limitMode) {
							canAction = nui.confirm(msg + "\n是否继续办理业务？", "询问");
						} else if ("coerce" == CrdUtil.limitMode) {
							nui.alert(msg, "错误");
							canAction = false;
						} else {
							canAction = true;
						}
					}
				}
			});
			return canAction;
		},
		setLimitMode : function(v) {
			if (v && v != null && v != "") {
				CrdUtil.limitMode = v;
			}
		}
	};
</script>

