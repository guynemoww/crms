function GetParams(url, c) {
    if (!url) url = location.href;
    if (!c) c = "?";
    url = url.split(c)[1];
    var params = {};
    if (url) {
        var us = url.split("&");
        for (var i = 0, l = us.length; i < l; i++) {
            var ps = us[i].split("=");
            params[ps[0]] = decodeURIComponent(ps[1]);
        }
    }
    return params;
}

function onIFrameLoad() {
    if (!CanSet) return;
    var mainTabs = nui.get("mainTabs");
    if (mainTabs) {
        mainTabs.setActiveIndex(0);
    }
    //url#src=...html
    var iframe = document.getElementById("mainframe");
    var src = "";
    try {
        var url = iframe.contentWindow.location.href;
        if (contextPath && contextPath.length && url.indexOf(contextPath) > 0) {
        	if (url.indexOf(contextPath) + contextPath.length < url.length) {
        		src = url.substr(url.indexOf(contextPath));
        	}
        }
        if (!src) {
	        var ss = url.split("/");
	        var s1 = ss[ss.length - 2];
	        if (s1 != "demo") {
	            src = s1 + "/" + ss[ss.length - 1];
	        } else {
	            src = ss[ss.length - 1];
	        }
        }
    } catch (e) {
    }
    if (src && src != "overview.html" && src.indexOf("/index.jsp") < 0) {
        //window.location.hash = "src=" + src;
    }
}
function onTabsActiveChanged(e) {
    if (this.activeIndex == 1) {
        var url = document.getElementById("mainframe").contentWindow.location.href;
        var codeframe = document.getElementById("codeframe");
        codeframe.src = "runCode/codeview.jsp?url=" + url;
    }
}

function onSkinChange(skin) {
    nui.Cookie.set('miniuiSkin', skin);
    //nui.Cookie.set('miniuiSkin', skin, 100);//100天过期的话，可以保持皮肤切换
    window.location.reload()
}
function AddCSSLink(id, url, doc) {
    doc = doc || document;
    var link = doc.createElement("link");
    link.id = id;
    link.setAttribute("rel", "stylesheet");
    link.setAttribute("type", "text/css");
    link.setAttribute("href", url);

    var heads = doc.getElementsByTagName("head");
    if (heads.length)
        heads[0].appendChild(link);
    else
        doc.documentElement.appendChild(link);
}

var CanSet = false;
window.onload = function () {
    var skin = nui.Cookie.get("miniuiSkin");
    if (skin) {
        var selectSkin = document.getElementById("selectSkin");
        selectSkin.value = skin;
    }

    var frame = document.getElementById("mainframe");
    var demoTree = nui.get("demoTree");

    var url = window.location.href;

    var params = GetParams(location.href, "#");
    if (params.ui) {
        var url = URLS[params.ui];
        if (url) {
            frame.src = url;
        }
    } else if (params.app) {

        var node = demoTree.getNode(params.app);
        if (node) {
            demoTree.expandNode(node);
            demoTree.selectNode(node);

            var url = URLS[params.app];
            if (url) {
                frame.src = url;
            }
        }

    } else if (params.src) {

        frame.src = params.src;
    }
    CanSet = true;
}
var URLS = {
    crud: "datagrid/rowedit.jsp",
    "master-detail": "datagrid/detailform.jsp",
    validator: "form/validation.jsp",
    layout: "layout/sysLayout1.jsp",
    tree: "tree/tree.jsp"
};