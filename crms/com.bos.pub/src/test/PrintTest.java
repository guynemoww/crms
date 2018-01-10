/**
 * 
 */
package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.bos.utp.tools.SystemInfo;
import com.eos.engine.component.ILogicComponent;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.system.annotation.Bizlet;
import com.primeton.ext.engine.component.LogicComponentFactory;

import commonj.sdo.DataObject;

/**
 * @author Administrator
 * @date 2017-05-18 15:10:44
 * 
 */
@Bizlet("PrintTest")
public class PrintTest {

	@Bizlet("getAllFileName")
	public DataObject[] getAllFileName() {
		List<DataObject> dataList = new ArrayList<DataObject>();
		File file = new File(SystemInfo.APP_WAR_PATH + "document" + SystemInfo.FILE_SEPARATOR + "docx");
		_getAllFileName(file, dataList);
		return dataList.toArray(new DataObject[] {});
	}

	@Bizlet("print")
	public void print(DataObject obj) throws Throwable {
		System.out.println("开始打印：" + obj.getString("path"));
		String componentName = "com.bos.comm.util.print";
		String operationName = "printView";
		String swfPath = "";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("obj", obj);
		param.put("reportName", obj.getString("dir"));
		loadParam(obj);
		ILogicComponent logicComponent = LogicComponentFactory.create(componentName);
		logicComponent.invoke(operationName, new Object[] { param, swfPath });
	}

	private void loadParam(DataObject obj) throws InvalidPropertiesFormatException, FileNotFoundException, IOException {
		String configFile = getConfigFile(obj);
		// Properties pro = new Properties();
		// pro.loadFromXML(new FileInputStream("" + name));
	}

	private String getConfigFile(DataObject obj) {
		String temp = obj.getString("name");
		return "temps/config_" + temp;
	}

	private void _getAllFileName(File file, List<DataObject> dataList) {
		if (!file.exists() || ".svn".equals(file.getName()) || "temp".equals(file.getName())) {
			return;
		}
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File cFile : files) {
				_getAllFileName(cFile, dataList);
			}
		} else {
			DataObject temp = DataObjectUtil.createDataObject("commonj.sdo.DataObject");
			String name = file.getName();
			temp.setString("dir", file.getParentFile().getName() + File.separatorChar + name);
			if (name.contains(".")) {
				String[] temps = name.split("\\.");
				name = temps[temps.length - 2];
			}
			temp.setString("reportName", name);
			temp.setString("path", file.getAbsolutePath());
			dataList.add(temp);
		}
	}
}
