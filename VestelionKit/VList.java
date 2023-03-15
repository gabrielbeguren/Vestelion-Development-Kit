package VestelionKit;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class VList<T> implements Iterable<T>
{
    private T[] array;
    private int size;

    public VList()
    {
        array = (T[]) new Object[1];
        size = 0;
    }

    public VList(T[] arr)
    {
        array = arr.clone();
        size = array.length;
    }

    public int Size()
    {
        return size;
    }

    public void Add(T item)
    {
        if (size == array.length)
        {
            T[] newArray = Arrays.copyOf(array, size + 1);
            array = newArray;
        }

        array[size++] = item;
    }

    public void Clear()
    {
        array = (T[]) new Object[1];
        size = 0;
    }

    public boolean Contains(T item)
    {
        for (int i = 0; i < size; i++)
        {
            if (Objects.equals(item, array[i]))
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
            if (Objects.equals(item, array[i]))
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
            T[] newArray = Arrays.copyOf(array, size * 2);
            array = newArray;
        }

        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = item;
        size++;
    }

    public void Sort(Comparator<T> comparator)
    {
        if (size > 1)
        {
            quickSort(comparator, 0, size - 1);
        }
    }

    private void quickSort(Comparator<T> comparator, int left, int right)
    {
        if (left < right)
        {
            int pivotIndex = partition(comparator, left, right);
            quickSort(comparator, left, pivotIndex - 1);
            quickSort(comparator, pivotIndex + 1, right);
        }
    }

    private int partition(Comparator<T> comparator, int left, int right)
    {
        T pivot = array[right];
        int i = left - 1;
        for (int j = left; j < right; j++)
        {
            if (comparator.compare(array[j], pivot) <= 0)
            {
                i++;
                swap(i, j);
            }
        }

        swap(i + 1, right);
        return i + 1;
    }

    private void swap(int i, int j)
    {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public boolean IsEmpty()
    {
        return size == 0;
    }

    public T Get(int index)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException();
        }

        return array[index];
    }

    public T Remove(int index)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException();
        }

        T removedItem = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        array[size] = null;
        return removedItem;
    }

    public T PopLast()
    {
        if (size == 0)
        {
            throw new NoSuchElementException();
        }

        T lastItem = array[size - 1];
        array[size - 1] = null;
        size--;
        return lastItem;
    }

    public T PopFirst()
    {
        if (size == 0)
        {
            throw new NoSuchElementException();
        }

        T firstItem = array[0];
        System.arraycopy(array, 1, array, 0, size - 1);
        array[size - 1] = null;
        size--;
        return firstItem;
    }

    public boolean Remove(T item)
    {
        for (int i = 0; i < size; i++)
        {
            if (Objects.equals(item, array[i]))
            {
                Remove(i);
                return true;
            }
        }
        return false;
    }

    public void RemoveAll(Predicate<T> predicate)
    {
        Objects.requireNonNull(predicate);

        int destIndex = 0;
        for (int srcIndex = 0; srcIndex < size; srcIndex++)
        {
            T item = array[srcIndex];
            if (!predicate.test(item))
            {
                array[destIndex++] = item;
            }
        }

        Arrays.fill(array, destIndex, size, null);
        size = destIndex;
    }

    public void ReplaceAll(UnaryOperator<T> operator)
    {
        Objects.requireNonNull(operator);

        for (int i = 0; i < size; i++)
        {
            array[i] = operator.apply(array[i]);
        }
    }

    public VList<T> SelectIf(Predicate<T> predicate)
    {
        Objects.requireNonNull(predicate);

        VList<T> result = new VList<>();
        for (int i = 0; i < size; i++)
        {
            T item = array[i];
            if (predicate.test(item))
            {
                result.Add(item);
            }
        }

        return result.IsEmpty() ? null : result;
    }

    public void RemoveIf(Predicate<T> predicate)
    {
        Objects.requireNonNull(predicate);

        int destIndex = 0;
        for (int srcIndex = 0; srcIndex < size; srcIndex++)
        {
            T item = array[srcIndex];
            if (!predicate.test(item))
            {
                array[destIndex++] = item;
            }
        }

        Arrays.fill(array, destIndex, size, null);
        size = destIndex;
    }

    public boolean UpdateIf(Predicate<T> predicate, UnaryOperator<T> operator)
    {
        Objects.requireNonNull(predicate);
        Objects.requireNonNull(operator);

        boolean updated = false;
        for (int i = 0; i < size; i++)
        {
            T item = array[i];
            if (predicate.test(item))
            {
                array[i] = operator.apply(item);
                updated = true;
            }
        }

        return updated;
    }

    public T First()
    {
        if (size > 0)
        {
            return array[0];
        }
        else
        {
            return null;
        }
    }

    public T Last()
    {
        if (size > 0)
        {
            return array[size - 1];
        }
        else
        {
            return null;
        }
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

        @Override
        public T next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }

            T item = array[currentIndex++];
            return item;
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }
}