package VestelionKit;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class VList<T> implements Iterable<T>
{
    private Object[] array;
    private int size;

    public VList()
    {
        array = new Object[16];
        size = 0;
    }

    public int Size()
    {
        return size;
    }

    public void Add(T item)
    {
        if (size == array.length)
        {
            Object[] newArray = new Object[size * 2];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }

        array[size++] = item;
    }

    public void Clear()
    {
        Arrays.fill(array, 0, size, null);
        size = 0;
    }

    public boolean Contains(T item)
    {
        for (int i = 0; i < size; i++)
        {
            if (item == null ? array[i] == null : item.equals(array[i]))
            {
                return true;
            }
        }

        return false;
    }


    public void CopyTo(T[] destination, int index)
    {
        System.arraycopy(array, 0, destination, index, size);
    }

    public int IndexOf(T item)
    {
        for (int i = 0; i < size; i++)
        {
            if (array[i].equals(item))
            {
                return i;
            }
        }

        return -1;
    }

    public void Insert(int index, T item)
    {
        if (index < 0 || index > size)
        {
            throw new IndexOutOfBoundsException();
        }

        if (size == array.length)
        {
            Object[] newArray = new Object[size * 2];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }

        for (int i = size - 1; i >= index; i--)
        {
            array[i + 1] = array[i];
        }

        array[index] = item;
        size++;
    }

    public void Remove(T item)
    {
        int index = IndexOf(item);

        if (index != -1)
        {
            RemoveAt(index);
        }
    }

    public void RemoveAt(int index)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException();
        }

        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
    }

    @SuppressWarnings("unchecked")
    public T Get(int index)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException();
        }

        return (T) array[index];
    }

    public void Set(int index, T value)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException();
        }

        array[index] = value;
    }

    public T Search(Predicate<T> predicate)
    {
        for (int i = 0; i < size; i++)
        {
            T item = Get(i);

            if (predicate.test(item))
            {
                return item;
            }
        }

        return null;
    }

    public boolean RemoveIf(Predicate<T> predicate)
    {
        boolean removed = false;
        int index = 0;

        while (index < size)
        {
            T item = Get(index);

            if (predicate.test(item))
            {
                RemoveAt(index);
                removed = true;
            }
            else
            {
                index++;
            }
        }

        return removed;
    }

    public int IndexOf(Predicate<T> predicate)
    {
        for (int i = 0; i < size; i++)
        {
            T item = Get(i);

            if (predicate.test(item))
            {
                return i;
            }
        }

        return -1;
    }

    public boolean Update(Predicate<T> predicate, T newValue)
    {
        boolean updated = false;
        for (int i = 0; i < size; i++)
        {
            T item = Get(i);
            if (predicate.test(item))
            {
                Set(i, newValue);
                updated = true;
            }
        }

        return updated;
    }



    @Override
    public Iterator<T> iterator()
    {
        return new VListIterator();
    }

    private class VListIterator implements Iterator<T>
    {
        private int currentIndex = 0;

        @Override
        public boolean hasNext()
        {
            return currentIndex < size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }

            return (T) array[currentIndex++];
        }
    }
}
