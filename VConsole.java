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
}
