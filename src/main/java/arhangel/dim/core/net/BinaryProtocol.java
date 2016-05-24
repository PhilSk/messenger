package arhangel.dim.core.net;

import arhangel.dim.core.messages.Message;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

/**
 * TODO: реализовать здесь свой протокол
 */
public class BinaryProtocol implements Protocol {

    @Override
    public Message decode(byte[] bytes) throws ProtocolException {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInput in = new ObjectInputStream(bais);

            Message msg = (Message) in.readObject();
            return msg;

        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public byte[] encode(Message msg) throws ProtocolException, IOException {
        ByteArrayOutputStream baos = null;
        ObjectOutput out = null;

        try {
            baos = new ByteArrayOutputStream();
            out = new ObjectOutputStream(baos);

            out.writeObject(msg);
            byte[] objBytes = null;
            objBytes = baos.toByteArray();
            baos.flush();
            return objBytes;

        } catch (IOException e) {
            e.getMessage();
        } finally {
            if (baos != null) {
                baos.close();
            }
            if (out != null) {
                out.flush();
            }
            if (out != null) {
                out.close();
            }
        }

        return null;
    }
}
