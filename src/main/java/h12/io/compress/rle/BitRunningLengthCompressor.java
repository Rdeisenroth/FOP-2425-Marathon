package h12.io.compress.rle;

import h12.io.BitInputStream;
import h12.io.BitOutStream;
import h12.io.BufferedBitInputStream;
import h12.io.BufferedBitOutputStream;
import h12.io.compress.Compressor;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * A simple compressor that uses the running length encoding algorithm to compress data.
 *
 * <p>E.g. the input 111111111111111111110000111111 would be compressed to 100010000000010010000110 to represent 20
 * ones, 4 zeros, and 6 ones.
 *
 * @author Per Göttlicher, Nhan Huynh
 */
@DoNotTouch
public class BitRunningLengthCompressor implements Compressor {

    /**
     * The maximum count that can be stored in a byte without the bit at position 7 (X111 1111 = 127).
     */
    public static final int MAX_COUNT = (int) Math.pow(2, 7) - 1;

    /**
     * The input stream to read from.
     */
    @DoNotTouch
    private final BitInputStream in;

    /**
     * The output stream to write to.
     */
    @DoNotTouch
    private final BitOutStream out;

    /**
     * The last read bit.
     */
    private int lastRead = -1;

    /**
     * Creates a new compressor with the given input to compress and output to write to.
     *
     * @param in  the input stream to read from
     * @param out the output stream to write to
     */
    @DoNotTouch
    public BitRunningLengthCompressor(InputStream in, OutputStream out) {
        this.in = in instanceof BitInputStream bitIn ? bitIn : new BufferedBitInputStream(in);
        this.out = out instanceof BitOutStream bitOut ? bitOut : new BufferedBitOutputStream(out);
    }

    /**
     * Returns the number of bits that are the same as the given bit.
     *
     * @param bit the bit to count
     *
     * @return the number of bits that are the same as the given bit
     * @throws IOException if an I/O error occurs
     */
    @StudentImplementationRequired("H12.2.1")
    protected int getBitCount(int bit) throws IOException {
        // TODO H12.2.1
        return org.tudalgo.algoutils.student.Student.crash("H12.2.1 - Remove if implemented");
    }

    @StudentImplementationRequired("H12.2.1")
    @Override
    public void compress() throws IOException {
        // TODO H12.2.1
        org.tudalgo.algoutils.student.Student.crash("H12.2.1 - Remove if implemented");
    }

    @DoNotTouch
    @Override
    public void close() throws Exception {
        in.close();
        out.close();
    }
}
