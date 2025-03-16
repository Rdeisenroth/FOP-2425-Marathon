package h12.io;

import h12.lang.MyBit;
import h12.lang.MyByte;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.io.IOException;
import java.io.OutputStream;

/**
 * An output stream that writes bits to an underlying output stream.
 *
 * @author Per Göttlicher, Nhan Huynh
 */
@DoNotTouch
public class BufferedBitOutputStream extends BitOutStream {

    /**
     * The underlying output stream.
     */
    @DoNotTouch
    private final OutputStream underlying;

    /**
     * The buffer used for writing bits.
     */
    private MyByte buffer = new MyByte();

    /**
     * The position of the next bit to write in the buffer.
     */
    private int position = MyByte.NUMBER_OF_BITS - 1;

    /**
     * Constructs a new bit output stream with the specified underlying output stream.
     *
     * @param underlying the underlying output stream
     */
    @DoNotTouch
    public BufferedBitOutputStream(OutputStream underlying) {
        this.underlying = underlying;
    }

    /**
     * Flushes the buffer if it is not empty to write the remaining bits to the underlying output stream.
     *
     * @throws IOException if an I/O error occurs
     */
    @StudentImplementationRequired("H12.1.2")
    protected void flushBuffer() throws IOException {
        // TODO H12.1.2
        org.tudalgo.algoutils.student.Student.crash("H12.1.2 - Remove if implemented");
    }

    @StudentImplementationRequired("H12.1.2")
    @Override
    public void writeBit(MyBit bit) throws IOException {
        // TODO H12.1.2
        org.tudalgo.algoutils.student.Student.crash("H12.1.2 - Remove if implemented");
    }

    @StudentImplementationRequired("H12.1.2")
    @Override
    public void write(int b) throws IOException {
        // TODO H12.1.2
        org.tudalgo.algoutils.student.Student.crash("H12.1.2 - Remove if implemented");
    }

    @DoNotTouch
    @Override
    public void flush() throws IOException {
        flushBuffer();
        underlying.flush();
    }

    @DoNotTouch
    @Override
    public void close() throws IOException {
        flush();
        underlying.close();
    }
}
