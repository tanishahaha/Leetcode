class TaskManager {
    private static class Task{
        int userId, taskId, priority;
        Task(int userId, int taskId, int priority){
            this.userId=userId;
            this.taskId=taskId;
            this.priority=priority;
        }
    }

    private Map<Integer, Task> taskMap;

    private PriorityQueue<Task> pq;


    public TaskManager(List<List<Integer>> tasks) {
        taskMap=new HashMap<>();
        pq=new PriorityQueue<>((a,b)->{
            if(b.priority!=a.priority) return b.priority-a.priority;
            return b.taskId-a.taskId;
        });

        for(List<Integer> t:tasks){
            add(t.get(0),t.get(1),t.get(2));
        }
        
    }
    
    public void add(int userId, int taskId, int priority) {
        Task task=new Task(userId,taskId,priority);
        taskMap.put(taskId,task);
        pq.offer(task);
        
    }
    
    public void edit(int taskId, int newPriority) {
        Task oldTask =taskMap.get(taskId);
        if(oldTask!=null){
            Task newTask=new Task(oldTask.userId,taskId,newPriority);
            taskMap.put(taskId,newTask);
            pq.offer(newTask);
        }
        
    }

    
    
    public void rmv(int taskId) {
        taskMap.remove(taskId);
        
    }
    
    public int execTop() {
        while(!pq.isEmpty()){
            Task top=pq.peek();
            Task valid=taskMap.get(top.taskId);

            if(valid==null || valid!=top){
                pq.poll();
                continue;
            }

            pq.poll();
            taskMap.remove(top.taskId);
            return top.userId;
        }
        return -1;
        
    }
}

/**
 * Your TaskManager object will be instantiated and called as such:
 * TaskManager obj = new TaskManager(tasks);
 * obj.add(userId,taskId,priority);
 * obj.edit(taskId,newPriority);
 * obj.rmv(taskId);
 * int param_4 = obj.execTop();
 */
