package reader;

public class SourceReaderImpl implements SourceReader {

    private int index = 0;
    private int total = 100;

    @Override
    public String next() {
        return "Test string " + (index++);
    }

    @Override
    public boolean hasNext() {
        return index < total;
    }
}
