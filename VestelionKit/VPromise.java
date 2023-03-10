package VestelionKit;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class VPromise
{
    private Runnable task;
    private Consumer<Throwable> exceptionHandler;
    private Runnable completionTask;

    public VPromise(Runnable task, Consumer<Throwable> exceptionHandler)
    {
        if (task == null || exceptionHandler == null)
        {
            throw new NullPointerException("Argument cannot be null");
        }

        this.task = task;
        this.exceptionHandler = exceptionHandler;
        this.completionTask = null;
    }

    public VPromise(Runnable task, Consumer<Throwable> exceptionHandler, Runnable completionTask)
    {
        if (task == null || exceptionHandler == null || completionTask == null)
        {
            throw new NullPointerException("Argument cannot be null");
        }

        this.task = task;
        this.exceptionHandler = exceptionHandler;
        this.completionTask = completionTask;
    }

    public void RunAsync() throws NullPointerException
    {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() ->
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

        future.whenComplete((result, ex) ->
        {
            if (ex != null)
            {
                exceptionHandler.accept(ex);
            }
            else
            {
                if (completionTask != null)
                {
                    completionTask.run();
                }
            }
        });

        try
        {
            future.join();
        }
        catch (Throwable ex)
        {
            exceptionHandler.accept(ex);
        }
    }

    public static void RunAsync(Runnable task, Consumer<Throwable> exceptionHandler) throws NullPointerException
    {
        if (task == null || exceptionHandler == null)
        {
            throw new NullPointerException("Argument cannot be null");
        }

        CompletableFuture<Void> future = CompletableFuture.runAsync(() ->
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

        try {
            future.join();
        } catch (Throwable ex) {
            exceptionHandler.accept(ex);
        }
    }

    public static void RunAsync(Runnable task, Consumer<Throwable> exceptionHandler, Runnable completionTask) throws NullPointerException
    {
        if (task == null || exceptionHandler == null || completionTask == null)
        {
            throw new NullPointerException("Argument cannot be null");
        }

        CompletableFuture<Void> future = CompletableFuture.runAsync(() ->
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

        future.whenComplete((result, ex) ->
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

        try {
            future.join();
        } catch (Throwable ex) {
            exceptionHandler.accept(ex);
        }
    }
}
