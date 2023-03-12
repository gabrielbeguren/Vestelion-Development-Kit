package VestelionKit;

public class VTuple<T1, T2>
{
    public T1 first;

    public T2 second;

    public VTuple(T1 first, T2 second)
    {
        this.first = first;
        this.second = second;
    }

    public static <T1, T2> VTuple<T1, T2> of(T1 first, T2 second)
    {
        return new VTuple<>(first, second);
    }
}
