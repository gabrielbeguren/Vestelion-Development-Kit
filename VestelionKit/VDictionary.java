package VestelionKit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VDictionary<T1, T2> implements Iterable<VTuple<T1, T2>>
{
    private VList<VTuple<T1, T2>> list;

    public VDictionary()
    {
        list = new VList<>();
    }
    public void Add(T1 key, T2 value)
    {
        for (VTuple<T1, T2> pair : list)
        {
            if (pair.first.equals(key))
            {
                throw new IllegalArgumentException("An item with the same key has already been added.");
            }
        }

        list.Add(new VTuple<>(key, value));
    }

    public boolean ContainsKey(T1 key)
    {
        for (VTuple<T1, T2> pair : list)
        {
            if (pair.first.equals(key))
            {
                return true;
            }
        }

        return false;
    }

    public boolean ContainsValue(T2 value)
    {
        for (VTuple<T1, T2> pair : list)
        {
            if (pair.second.equals(value))
            {
                return true;
            }
        }

        return false;
    }

    public int Size()
    {
        return list.Size();
    }

    public void Clear()
    {
        list.Clear();
    }

    public T2 Get(T1 key)
    {
        for (VTuple<T1, T2> pair : list)
        {
            if (pair.first.equals(key))
            {
                return pair.second;
            }
        }

        throw new IllegalArgumentException("The given key was not present in the dictionary.");
    }

    public void Remove(T1 key)
    {
        for (int i = 0; i < list.Size(); i++)
        {
            if (list.Get(i).first.equals(key))
            {
                list.RemoveAt(i);
                return;
            }
        }

        throw new IllegalArgumentException("The given key was not present in the dictionary.");
    }

    public void Set(T1 key, T2 value)
    {
        for (VTuple<T1, T2> pair : list)
        {
            if (pair.first.equals(key))
            {
                pair.second = value;
                return;
            }
        }

        throw new IllegalArgumentException("The given key was not present in the dictionary.");
    }

    public List<T1> Keys()
    {
        List<T1> keys = new ArrayList<T1>();

        for (VTuple<T1, T2> pair : list)
        {
            keys.add(pair.first);
        }

        return keys;
    }

    public List<T2> Values()
    {
        List<T2> values = new ArrayList<T2>();

        for (VTuple<T1, T2> pair : list)
        {
            values.add(pair.second);
        }

        return values;
    }

    @Override
    public Iterator<VTuple<T1, T2>> iterator()
    {
        return list.iterator();
    }
}
