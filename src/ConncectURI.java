import javax.sound.midi.InvalidMidiDataException;
import java.io.InputStream;
import java.lang.module.InvalidModuleDescriptorException;
import java.lang.reflect.MalformedParametersException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ConnectURI {
    private final String USER_AGENT = "Mozilla/5.0";

    public static url buildurl(String urlQuerry) {
        URL url = null;
        try {
            url = new URL(urlQuerry.toString());
        } catch (MalformedParametersException e) {
            e.printStackTrace();
        }
        return url
        public static String getResponseFronHttpurl (URL url) throws IDException {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnecti
            try {
                InputStream in = urlConnection.getInputStream();
                Scanner scanner = new Scanner(in);
                Scanner.useDelimiter("\\A");
                boolean hasInput = scanner.hasNext();
                if (hasInput) {
                    return scanner.next();
                            else
                    return null;
                } finally{
                    urlConnection();
                }

                }
            }

        }


    }