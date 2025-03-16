package h12.io.compress.huffman;

import h12.io.BitInputStream;
import h12.io.BufferedBitInputStream;
import h12.io.compress.Decompressor;
import h12.io.compress.EncodingTable;
import h12.lang.MyBytes;
import h12.util.TreeNode;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/**
 * A decompressor that uses the Huffman coding algorithm to decompress data.
 *
 * @author Per Göttlicher, Nhan Huynh
 */
@DoNotTouch
public class HuffmanCodingDecompressor implements Decompressor {

    /**
     * The input stream to read the compressed data from.
     */
    @DoNotTouch
    private final BitInputStream in;

    /**
     * The output writer to write the decompressed data to.
     */
    @DoNotTouch
    private final Writer out;

    /**
     * Creates a new decompressor with the given input to decompress and output to write to.
     *
     * @param in  the input stream to read the compressed data from
     * @param out the output stream to write the decompressed data to
     */
    @DoNotTouch
    public HuffmanCodingDecompressor(InputStream in, OutputStream out) {
        this.in = in instanceof BitInputStream bitIn ? bitIn : new BufferedBitInputStream(in);
        this.out = new OutputStreamWriter(out, StandardCharsets.UTF_8);
    }

    /**
     * Skips the filled bits of the first byte.
     *
     * @throws IOException if an I/O error occurs
     */
    @StudentImplementationRequired("H12.4.2")
    protected void skipBits() throws IOException {
        // TODO H12.4.2
        org.tudalgo.algoutils.student.Student.crash("H12.4.2 - Remove if implemented");
    }

    /**
     * Decodes the header of the compressed data to build the encoding table.
     *
     * @return the encoding table built from the header
     * @throws IOException if an I/O error occurs
     */
    @DoNotTouch
    private EncodingTable decodeHeader() throws IOException {
        return new HuffmanEncodingTable(decodeTree());
    }

    /**
     * Decodes the Huffman tree recursively.
     *
     * @return the root of the Huffman tree decoded
     * @throws IOException if an I/O error occurs
     */
    @DoNotTouch
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private TreeNode<Character> decodeTree() throws IOException {
        if (in.readBit() == 1) {
            byte[] bytes = new byte[4];
            in.read(bytes);
            return new TreeNode<>(MyBytes.toChar(bytes));
        }
        TreeNode<Character> left = decodeTree();
        TreeNode<Character> right = decodeTree();
        TreeNode<Character> parent = new TreeNode<>(left, right);
        left.setParent(parent);
        right.setParent(parent);
        return parent;
    }

    /**
     * Decodes a character from the compressed data.
     *
     * @param startBit      the first bit of the character
     * @param encodingTable the encoding table to use
     *
     * @return the decoded character from the compressed data
     * @throws IOException if an I/O error occurs
     */
    @StudentImplementationRequired("H12.4.2")
    char decodeCharacter(int startBit, EncodingTable encodingTable) throws IOException {
        // TODO H12.4.2
        return org.tudalgo.algoutils.student.Student.crash("H12.4.2 - Remove if implemented");
    }

    /**
     * Decodes the content of the compressed data using the given encoding table.
     *
     * @param encodingTable the encoding table to use
     *
     * @throws IOException if an I/O error occurs
     */
    @StudentImplementationRequired("H12.4.2")
    void decodeText(EncodingTable encodingTable) throws IOException {
        // TODO H12.4.2
        org.tudalgo.algoutils.student.Student.crash("H12.4.2 - Remove if implemented");
    }

    /**
     * Decompresses the data using the Huffman coding algorithm.
     *
     * @throws IOException if an I/O error occurs
     */
    @StudentImplementationRequired("H12.4.2")
    @Override
    public void decompress() throws IOException {
        // TODO H12.4.2
        org.tudalgo.algoutils.student.Student.crash("H12.4.2 - Remove if implemented");
    }

    @DoNotTouch
    @Override
    public void close() throws Exception {
        in.close();
        out.close();
    }
}
