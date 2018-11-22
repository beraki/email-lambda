package info.beraki.emaillambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class LambdaRequestHandler
        implements RequestStreamHandler {
    public void handleRequest(InputStream inputStream,
                              OutputStream outputStream, Context context) throws IOException {
        String input =  IOUtils.toString(inputStream, "UTF-8");


        Main.sendMail(input, context);
//        JSONObject headerJson = new JSONObject();
//        headerJson.put("x-custom-header", "my custom header value");
//
//        JSONObject responseJson= new JSONObject();
//        responseJson.put("isBase64Encoded", false);
//        responseJson.put("statusCode", 200);
//        responseJson.put("headers", headerJson);
//        responseJson.put("body", "Thank you for the webhook");
//        outputStream.write(responseJson.toString().getBytes());
//        outputStream.close();
    }
}
