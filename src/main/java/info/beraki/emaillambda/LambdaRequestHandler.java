package info.beraki.emaillambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LambdaRequestHandler
        implements RequestStreamHandler {
    public void handleRequest(InputStream inputStream,
                              OutputStream outputStream, Context context) throws IOException {
        String input = IOUtils.toString(inputStream, "UTF-8");

        Main.sendMail(input, context);
    }
}
