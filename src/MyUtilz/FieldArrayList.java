package MyUtilz;

import java.util.ArrayList;
import java.util.Collection;

public class FieldArrayList<T> extends ArrayList<T> {
    @Override
    public T get(int index) {
        if (index < 0 || index >= size())
            return null;
        return super.get(index);
    }

    public FieldArrayList(int initialCapacity) {
        super(initialCapacity);
    }

    public FieldArrayList() {
        super();
    }

    public FieldArrayList(Collection<? extends T> c) {
        super(c);
    }
}
