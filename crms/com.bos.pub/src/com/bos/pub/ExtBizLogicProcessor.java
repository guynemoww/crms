package com.bos.pub;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONWriter;

import com.eos.runtime.core.TraceLoggerFactory;
import com.eos.system.exception.EOSException;
import com.eos.system.exception.EOSRuntimeException;
import com.eos.system.logging.Logger;
import com.eos.system.utility.ClassUtil;
import com.primeton.ext.common.muo.MUOCallback;
import com.primeton.ext.common.muo.MUOTemplate;
import com.primeton.ext.data.serialize.ExtendedXMLSerializer;
import com.primeton.ext.data.serialize.SerializeOption;
import com.primeton.ext.data.serialize.marshal.IMarshallingNode;
import com.primeton.ext.engine.core.IBizRuntimeContext;
import com.primeton.ext.engine.core.IParameterSet;
import com.primeton.ext.engine.core.processor.AccessRefusedExceptionHelper;
import com.primeton.ext.engine.core.processor.RichWebL7EHelper;

public class ExtBizLogicProcessor extends
		com.primeton.ext.engine.core.processor.ExtBizProcessor {
	private static final Logger logger = TraceLoggerFactory
			.getLogger(ExtBizLogicProcessor.class);

	private final ExtBizLogicProcessor _this = this;

	private IParameterSet val$parameterSet;

	private String val$componentName;

	private String val$flowName;

	private JSONWriter val$writer;

	private HttpServletResponse response;

	private ByteArrayOutputStream byteArrayOutputStream;

	public void doProcess(HttpServletRequest request,
			HttpServletResponse response, IParameterSet parameterSet)
			throws IOException, ServletException {
		response.setContentType("application/json;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// PrintWriter out = response.getWriter();
		// JSONWriter writer = new JSONWriter(out);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		OutputStreamWriter out2 = new OutputStreamWriter(byteArrayOutputStream);
		JSONWriter writer = new JSONWriter(out2);

		if (!(RichWebL7EHelper.check())) {
			try {
				writer.object();
				writer.key("exception");
				writer.object();
				writer.key("code").value("");
				StringBuffer msg = new StringBuffer();
				writer.key("message").value(msg);
				writer.key("invalid").value(false);
				writer.key("loginPage").value(
						RichWebL7EHelper.getForwardPage(request));

				writer.endObject();
				writer.endObject();

				byteArrayOutputStream.writeTo(response.getOutputStream());
			} catch (JSONException e1) {
				logger.error(e1);
			}
			return;
		}

		String[] uriPaths = request.getRequestURI().split("/");
		String bizAction = uriPaths[(uriPaths.length - 1)];
		bizAction = bizAction.replaceAll(getRequestSuffix(), "");

		String componentName = bizAction.substring(0, bizAction
				.lastIndexOf("."));

		String flowName = bizAction.substring(bizAction.lastIndexOf(".") + 1);
		boolean invalid = false;
		try {
			_this.val$componentName = componentName;
			_this.val$flowName = flowName;
			_this.val$parameterSet = parameterSet;
			_this.val$writer = writer;
			_this.response = response;
			_this.byteArrayOutputStream = byteArrayOutputStream;
			// this, parameterSet, componentName, flowName, writer
			MUOTemplate.execute(request.getSession(), new MUOCallback() {
				public Object run() throws Throwable {
					IBizRuntimeContext context = (IBizRuntimeContext) ClassUtil
							.invokeMethod(
									"com.primeton.ext.engine.component.LogicflowInvokerHelper",
									"invokeLogicflow", new Object[] {
											_this.val$parameterSet,
											_this.val$componentName,
											_this.val$flowName });

					String contentType = _this.response.getContentType();
					if (contentType != null
							&& contentType.startsWith("application/json") == false) {
						// 下载操作已将content type修改
						return null;
					}
					Object data = context.get("data");
					ExtendedXMLSerializer serializer = new ExtendedXMLSerializer();
					SerializeOption option = new SerializeOption();
					serializer.setOption(option);
					option.setDepth(6);
					IMarshallingNode node = serializer.marshallToNode(data,
							"data");

					_this.val$writer.object();
					List children = node.getChildren();
					for (Iterator i$ = children.iterator(); i$.hasNext();) {
						IMarshallingNode child = (IMarshallingNode) i$.next();
						_this.write(child, _this.val$writer);
					}
					_this.val$writer.endObject();

					_this.byteArrayOutputStream.writeTo(_this.response
							.getOutputStream());
					return null;
				}
			});
		} catch (Throwable e) {
			logger.error(e);
			try {
				String code = "";
				if (e instanceof InvocationTargetException)
					e = ((InvocationTargetException) e).getTargetException();

				if (e instanceof EOSException)
					code = ((EOSException) e).getCode();

				if (e instanceof EOSRuntimeException)
					code = ((EOSRuntimeException) e).getCode();

				String loginPage = AccessRefusedExceptionHelper.getLoginPage(
						(e == null) ? e : e.getCause(), request);
				writer.object();
				writer.key("exception");
				writer.object();
				writer.key("code").value(code);
				StringBuffer msg = new StringBuffer();
				getSource(msg, e);
				writer.key("message").value(msg);
				writer.key("invalid").value(invalid);
				writer.key("loginPage").value(loginPage);
				writer.endObject();
				writer.endObject();

				byteArrayOutputStream.writeTo(response.getOutputStream());
			} catch (JSONException e1) {
				logger.error(e1);
			}
		}
	}

	private static void getSource(StringBuffer sb, Throwable t) {
		if (!(t instanceof InvocationTargetException)) {
			sb.append(t + "\n");
		}

		Throwable ourCause = t.getCause();
		if (ourCause != null)
			getSource(sb, ourCause);
	}
}
