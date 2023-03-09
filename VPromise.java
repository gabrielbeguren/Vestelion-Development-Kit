package VestelionKit;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class VPromise
{
    public static <T> void Run(Runnable task, Consumer<Throwable> exceptionHandler)
    {
        CompletableFuture.runAsync(() ->
        {
            try
            {
                task.run();
            }
            catch (Throwable ex)
            {
                exceptionHandler.accept(ex);
            }
        });
    }

    public static <T> void Run(Runnable task, Consumer<Throwable> exceptionHandler, Runnable completionTask)
    {
        CompletableFuture.runAsync(() -> 
        {
            try 
            {
                task.run();
            } 
            catch (Throwable ex) 
            {
                exceptionHandler.accept(ex);
            }
        }).whenComplete((result, ex) -> 
        {
            if (ex != null) 
            {
                exceptionHandler.accept(ex);
            } 
            else 
            {
                completionTask.run();
            }
        });
    }
}
