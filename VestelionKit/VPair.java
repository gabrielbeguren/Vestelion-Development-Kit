package VestelionKit;

public class VPair<T1, T2>
{
    public T1 First;

    public T2 Second;

    public VPair(T1 first, T2 second)
    {
        this.First = first;
        this.Second = second;
    }

    public static <T1, T2> VPair<T1, T2> of(T1 first, T2 second)
    {
        return new VPair<>(first, second);
    }
}
