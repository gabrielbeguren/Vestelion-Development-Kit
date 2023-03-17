package VestelionKit;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;

public class VDictionary<K, V> implements Iterable<VPair<K, V>>
{
    private int size;
    private K[] keys;
    private V[] values;

    public VDictionary(int initialCapacity)
    {
        keys = (K[]) new Object[initialCapacity];
        values = (V[]) new Object[initialCapacity];
        size = 0;
    }

    public VDictionary()
    {
        this(1);
    }

    public int Size()
    {
        return size;
    }

    public boolean ContainsKey(K key)
    {
        for (int i = 0; i < size; i++)
        {
            if (keys[i].equals(key))
            {
                return true;
            }
        }

        return false;
    }

    public V Get(K key)
    {
        for (int i = 0; i < size; i++)
        {
            if (keys[i].equals(key))
            {
                return values[i];
            }
        }

        return null;
    }

    public void Add(K key, V value)
    {
        if (ContainsKey(key))
        {
            throw new IllegalArgumentException("The dictionary already contains an entry with the specified key.");
        }

        if (size == keys.length)
        {
            keys = Arrays.copyOf(keys, size * 2);
            values = Arrays.copyOf(values, size * 2);
        }

        keys[size] = key;
        values[size] = value;
        size++;
    }

    public void Clear()
    {
        Arrays.fill(keys, 0, size, null);
        Arrays.fill(values, 0, size, null);
        size = 0;
    }

    public V Remove(K key)
    {
        for (int i = 0; i < size; i++)
        {
            if (keys[i].equals(key))
            {
                V removedValue = values[i];
                System.arraycopy(keys, i + 1, keys, i, size - i - 1);
                System.arraycopy(values, i + 1, values, i, size - i - 1);
                size--;
                keys[size] = null;
                values[size] = null;
                return removedValue;
            }
        }

        return null;
    }

    public boolean Remove(K key, V value)
    {
        for (int i = 0; i < size; i++)
        {
            if (keys[i].equals(key) && values[i].equals(value))
            {
                System.arraycopy(keys, i + 1, keys, i, size - i - 1);
                System.arraycopy(values, i + 1, values, i, size - i - 1);
                size--;
                keys[size] = null;
                values[size] = null;
                return true;
            }
        }

        return false;
    }

    public void UpdateIf(Function<V, Boolean> searchFunction, Function<V, V> updater)
    {
        for (int i = 0; i < size; i++)
        {
            if (searchFunction.apply(values[i]))
            {
                V newValue = updater.apply(values[i]);
                values[i] = newValue;
                break;
            }
        }
    }

    public VList<V> SelectIf(Predicate<V> predicate)
    {
        VList<V> result = new VList<>();

        for (int i = 0; i < size; i++)
        {
            if (predicate.test(values[i]))
            {
                result.Add(values[i]);
            }
        }

        return result;
    }

    public boolean RemoveIf(Predicate<V> predicate)
    {
        boolean removed = false;

        for (int i = 0; i < size; i++)
        {
            if (predicate.test(values[i]))
            {
                Remove(keys[i]);
                removed = true;
                i--;
            }
        }

        return removed;
    }

    public int IndexOf (K key)
    {
        for (int i = 0; i < size; i++)
        {
            if (keys[i].equals(key))
            {
                return i;
            }
        }
        return -1;
    }

    public Iterator<VPair<K, V>> iterator ()
    {
        return new VDictionaryIterator();
    }

    private class VDictionaryIterator implements Iterator<VPair<K, V>>
    {
        private int currentIndex = 0;

        @Override
        public boolean hasNext()
        {
            return currentIndex < size;
        }

        @Override
        public VPair<K, V> next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }

            VPair<K, V> pair = new VPair<>(keys[currentIndex], values[currentIndex]);

            currentIndex++;
            return pair;
        }
    }
}
