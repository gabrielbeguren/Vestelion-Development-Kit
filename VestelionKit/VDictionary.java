package VestelionKit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

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

    public T2 Search(Predicate<VTuple<T1, T2>> predicate)
    {
        for (VTuple<T1, T2> pair : list)
        {
            if (predicate.test(pair))
            {
                return pair.second;
            }
        }

        return null;
    }

    public int IndexOf(Predicate<VTuple<T1, T2>> predicate)
    {
        for (int i = 0; i < list.Size(); i++)
        {
            VTuple<T1, T2> pair = list.Get(i);

            if (predicate.test(pair))
            {
                return i;
            }
        }

        return -1;
    }

    public boolean RemoveIf(Predicate<VTuple<T1, T2>> predicate)
    {
        boolean removed = false;
        int index = 0;

        while (index < list.Size())
        {
            VTuple<T1, T2> pair = list.Get(index);

            if (predicate.test(pair))
            {
                list.RemoveAt(index);
                removed = true;
            }
            else
            {
                index++;
            }
        }

        return removed;
    }

    public boolean Update(Predicate<VTuple<T1, T2>> predicate, T2 newValue)
    {
        boolean updated = false;
        for (int i = 0; i < list.Size(); i++)
        {
            VTuple<T1, T2> pair = list.Get(i);

            if (predicate.test(pair))
            {
                list.Set(i, new VTuple<>(pair.first, newValue));
                updated = true;
            }
        }

        return updated;
    }

    public HashMap<T1, T2> ToHashMap() 
    {
        HashMap<T1, T2> hashMap = new HashMap<>();

        for (VTuple<T1, T2> pair : list) 
        {
            hashMap.put(pair.first, pair.second);
        }

        return hashMap;
    }


    @Override
    public Iterator<VTuple<T1, T2>> iterator()
    {
        return list.iterator();
    }
}
