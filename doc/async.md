
## @EnableAsync
```
@SpringBootApplication
@EnableAsync
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

## 不需要返回的patern
### 异步方法
```
class AsyncTask {
    @Async
    public void doAsncTask() throws Exception {
        print("doAsncTask");
        sleep(1000);
    }
}
```

### 调用异步方法
```
@RunWith(SpringRunner.class)
@SpringBootTest
public class AsyncTaskTest {
    @Autowired
    private AsyncTask task;

    @Test
    public void testAsyncTasks() throws Exception {
        task.doAsncTask();
     }
}
```

## 需要返回值的patern
### 异步方法
```
class AsyncTask {
    @Async
    public Future<String> doAsncTask() throws Exception {
        print("doAsncTask");
        sleep(1000);
        return new AsyncResult<>("异步任务完成");
    }
}
```

### 调用异步方法
```
@RunWith(SpringRunner.class)
@SpringBootTest
public class AsyncTaskTest {
    @Autowired
    private AsyncTask task;

    @Test
    public void testAsyncTasks() throws Exception {
        long start = currentTimeMillis();
        Future<String> task1 =task.doAsncTask();
        // 阻塞等待执行结果
	    task1.get();
        // while (!task1.isDone() ) {
        //     sleep(1000);
        // }

        long end = currentTimeMillis();
        out.println("任务完成，总耗时：" + (end - start) + "毫秒");
    }
}
```

## 定义线程池

```
@Configuration
public class TaskConfiguration {
    @Bean("taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(200);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("taskExecutor-");
        executor.setRejectedExecutionHandler(new CallerRunsPolicy());
        return executor;
    }
}
```

## 优雅地关闭线程池
```
@Bean("taskExecutor")
public Executor taskExecutor() {
    ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
    executor.setPoolSize(20);
    executor.setThreadNamePrefix("taskExecutor-");
    executor.setWaitForTasksToCompleteOnShutdown(true);
    executor.setAwaitTerminationSeconds(60);
    return executor;
}
```

