package com.zhaohaijie.taskrunner;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.zhaohaijie.logging.AppLogger;

import java.util.List;
import java.util.ArrayList;

public class TaskFactory {
	private static final TaskFactory staticTaskFactory = new TaskFactory();
	
	private static final TaskRunner runner = new TaskRunner();
	
	private static final HashMap<Integer, TaskResultProcessor> taskProcessor = new HashMap<Integer, TaskResultProcessor>();
	private static final HashMap<Integer, TaskGenerator> taskGenerator = new HashMap<Integer, TaskGenerator>();
	
	
	public void addTaskGenerator(TaskGenerator generator){
		taskGenerator.put(generator.getTaskID(), generator);
	}
	
	public void addTaskResultProcessor(TaskResultProcessor processor){
		taskProcessor.put(processor.getTaskID(), processor);
	}
	
	public void removeTaskGenerator(TaskGenerator generator){
		taskGenerator.remove(generator.getTaskID());
	}
	
	public void removeTaskGenerator(Integer typeId){
		taskGenerator.remove(typeId);
	}
	
	public void removeTaskResultProcessor(TaskResultProcessor processor){
		taskProcessor.remove(processor.getTaskID());
	}
	
	public void removeTaskResultProcessor(Integer typeId){
		taskProcessor.remove(typeId);
	}
	
	
	public static TaskFactory getTaskFactoryInstance(){
		return staticTaskFactory;
	}
	
	private TaskFactory(){

	}
	
	private List<Task> BuildTask(){
		AppLogger.getLogger().info("->Build Task");
		
		Iterator iterator = taskGenerator.entrySet().iterator();
		List<Task> tasks = new ArrayList<Task>(); 
		
		while(iterator.hasNext()){
			try{
				@SuppressWarnings("unchecked")
				Map.Entry<Integer, TaskGenerator> entry = (Map.Entry<Integer, TaskGenerator>)iterator.next();
					
				Integer id = entry.getKey();
				TaskGenerator generator = entry.getValue();
				
				AppLogger.getLogger().info("Add: " + id);
				
				if(taskProcessor.containsKey(id)){
					List<Task> tasksFromID = generator.createTasks(taskProcessor.get(id));
					AppLogger.getLogger().info("Total: " + tasksFromID.size());
					tasks.addAll(tasksFromID);
				}
				else{
					AppLogger.getLogger().info("No Result Processor");
					List<Task> tasksFromID = generator.createTasks(null);
					AppLogger.getLogger().info("Total: " + tasksFromID.size());
					tasks.addAll(tasksFromID);
				}
			}catch(Exception ex){
				AppLogger.getLogger().error("Error", ex);	
			}
		}
		
		AppLogger.getLogger().info("<-Build Task");
		
		return tasks;
	}
	
	public void Start(){
		try{
			List<Task> tasks = BuildTask();
			
			for(Task task: tasks){
				runner.execute(task);
			}
		}catch(Exception ex){
			AppLogger.getLogger().error("Error", ex);	
		}
	}
}
