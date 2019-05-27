package weird;

import java.util.Objects;

public class Arc{
    private final Node start;
    private final Node end;

    public Arc(Node start, Node end) {
        this.start = start;
        this.end = end;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arc arc = (Arc) o;
        return (start.equals(arc.start) && end.equals(arc.end))
                || (start.equals(arc.end) && end.equals(arc.start));
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        return start + " <-> " + end;
    }
}
