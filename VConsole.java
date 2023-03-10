package VestelionKit;

import java.util.Formatter;
import java.util.Locale;

public class VConsole
{
    public static void Write(String format, Object... args)
    {
        Formatter formatter = new Formatter(Locale.getDefault());
        System.out.print(formatter.format(format, args));
        formatter.close();
    }

    public static void WriteLine(String format, Object... args)
    {
        Formatter formatter = new Formatter(Locale.getDefault());
        System.out.println(formatter.format(format, args));
        formatter.close();
    }

    public static void WriteLog(String format, Object... args)
    {
        Formatter formatter = new Formatter(Locale.getDefault());
        String message = formatter.format("(?) " + format, args).toString();
        formatter.close();
        System.out.println("\033[32m" + message + "\033[0m");
    }

    public static void WriteWarning(String format, Object... args)
    {
        Formatter formatter = new Formatter(Locale.getDefault());
        String message = formatter.format("(!) " + format, args).toString();
        formatter.close();
        System.out.println("\033[33m" + message + "\033[0m");
    }

}
