package com.zhaohaijie.taskrunner.onfiguration;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.*;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import com.zhaohaijie.logging.AppLogger;
import com.zhaohaijie.taskrunner.TaskFactory;
import com.zhaohaijie.taskrunner.TaskGenerator;
import com.zhaohaijie.taskrunner.TaskResultProcessor;

/**
 * Created by on 03/02/2017.
 */
public class ConfigurationMamanger {
	
	static final String CONFIGURATION_FILE="AppConfig.xml"; 
	
	private static final ConfigurationMamanger staticConfigurationMamanger = new ConfigurationMamanger();
	
	private static final List<Integer> idList = new ArrayList<Integer>();
	
	
	public static ConfigurationMamanger getConfigurationMamangerInstance(){
		return staticConfigurationMamanger;
	}
	
	
	
	public boolean initialize(){
		
		try
		{
			AppLogger.getLogger().info("-> ConfigurationMamanger.initialize()");
			
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        
	        String inputFile = System.getProperty("user.dir") + "\\" + CONFIGURATION_FILE;
	        AppLogger.getLogger().info("Loading: " + inputFile);
	        
	        Document doc = dBuilder.parse(inputFile);

	        XPath xPath = XPathFactory.newInstance().newXPath();
	        NodeList nodes = (NodeList)xPath.evaluate("/config/taskmapping/mapping", 
	        		doc.getDocumentElement(), XPathConstants.NODESET);
	        
	        for (int index = 0; index < nodes.getLength(); index++) {
	            Node node = nodes.item(index);
	            
	            if (node.getNodeType() == Node.ELEMENT_NODE) {
	                Element element = (Element) node;
	                
	                String taskGenerator = element.getAttribute("taskgenerator");
	                String taskProcessor = element.getAttribute("taskprocessor");
	                String taskID = element.getAttribute("taskid");
	                
	                int taskId = Integer.parseInt(taskID);
	                
	                if(idList.contains(taskID)){
	                	throw new Exception("Task Id must be unique, a reduntant id is found");
	                }
	                
	                idList.add(taskId);
	                
	                TaskGenerator generator = getObject(taskGenerator);
	                TaskResultProcessor processor = getObject(taskProcessor);
	                
	                generator.setTaskID(taskId);
	                processor.setTaskID(taskId);
	                
	                if(generator != null && processor != null){
	                	
	                	AppLogger.getLogger().info(String.format("Add: TaskId=%d  TaskGenerator=%s  TaskProcessor%s", taskId,generator, processor));
	                	
	                	TaskFactory.getTaskFactoryInstance().addTaskGenerator(generator);
	                	TaskFactory.getTaskFactoryInstance().addTaskResultProcessor(processor);
	                }
	            }
	        }

	        AppLogger.getLogger().info("<- ConfigurationMamanger.initialize()");
	    	
	        return true;
	        
		}catch(Exception ex){
			AppLogger.getLogger().error("Error at initialize: ", ex);
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	private <T> T getObject(String className){
		try
		{
			AppLogger.getLogger().info("-> ConfigurationMamanger.getObject(): " + className);
			Class<?> clazz = Class.forName(className);
			T object = (T)clazz.newInstance();
			
			AppLogger.getLogger().info("-> ConfigurationMamanger.getObject(): " + (object != null));
			
			return object;
			
		}catch(Exception ex){
			AppLogger.getLogger().error("Error at getObject: ", ex);
			return null;
		}
	}
}
