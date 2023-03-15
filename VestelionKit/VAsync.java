package VestelionKit;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class VAsync
{
    private Runnable task;
    private Consumer<Throwable> exceptionHandler;
    private Runnable completionTask;

    public VAsync(Runnable task, Consumer<Throwable> exceptionHandler)
    {
        if (task == null || exceptionHandler == null)
        {
            throw new NullPointerException("Argument cannot be null");
        }

        this.task = task;
        this.exceptionHandler = exceptionHandler;
        this.completionTask = null;
    }

    public VAsync(Runnable task, Consumer<Throwable> exceptionHandler, Runnable completionTask)
    {
        if (task == null || exceptionHandler == null || completionTask == null)
        {
            throw new NullPointerException("Argument cannot be null");
        }

        this.task = task;
        this.exceptionHandler = exceptionHandler;
        this.completionTask = completionTask;
    }

    public void Await() throws NullPointerException
    {
        CompletableFuture.runAsync(() -> task.run())
            .whenComplete((result, ex) -> {
                if (ex != null) {
                    exceptionHandler.accept(ex);
                }
                else if (completionTask != null)
                {
                    completionTask.run();
                }
            });
    }

    public static void Await(Runnable task) throws NullPointerException
    {
        if (task == null)
        {
            throw new NullPointerException("Argument cannot be null");
        }

        CompletableFuture.runAsync(() -> {
            try
            {
                task.run();
            }
            catch (Throwable ex)
            {
                throw new RuntimeException(ex);
            }
        }).join();
    }

    public static void Await(Runnable task, Consumer<Throwable> exceptionHandler) throws NullPointerException
    {
        if (task == null || exceptionHandler == null)
        {
            throw new NullPointerException("Argument cannot be null");
        }

        CompletableFuture.runAsync(() -> task.run())
            .exceptionally(ex -> {
                exceptionHandler.accept(ex);
                return null;
            }).join();
    }

    public static void Await(Runnable task, Consumer<Throwable> exceptionHandler, Runnable completionTask) throws NullPointerException
    {
        if (task == null || exceptionHandler == null || completionTask == null)
        {
            throw new NullPointerException("Argument cannot be null");
        }

        CompletableFuture.runAsync(() -> task.run())
            .whenComplete((result, ex) -> {
                if (ex != null)
                {
                    exceptionHandler.accept(ex);
                }
                else
                {
                    completionTask.run();
                }
            }).join();
    }

    public void Run()
    {
        CompletableFuture.runAsync(() -> task.run())
            .whenComplete((result, ex) -> {
                if (ex != null)
                {
                    exceptionHandler.accept(ex);
                }
                else if (completionTask != null)
                {
                    completionTask.run();
                }
            });
    }

    public static void Run(Runnable task)
    {
        if (task == null)
        {
            throw new NullPointerException("Argument cannot be null");
        }

        CompletableFuture.runAsync(task);
    }

    public static void Run(Runnable task, Consumer<Throwable> exceptionHandler)
    {
        if (task == null || exceptionHandler == null)
        {
            throw new NullPointerException("Argument cannot be null");
        }

        CompletableFuture.runAsync(() -> task.run())
            .exceptionally(ex -> {
                exceptionHandler.accept(ex);
                return null;
            });
    }

    public static void Run(Runnable task, Consumer<Throwable> exceptionHandler, Runnable completionTask)
    {
        if (task == null || exceptionHandler == null || completionTask == null)
        {
            throw new NullPointerException("Argument cannot be null");
        }

        CompletableFuture.runAsync(() -> task.run())
            .whenComplete((result, ex) -> {
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
