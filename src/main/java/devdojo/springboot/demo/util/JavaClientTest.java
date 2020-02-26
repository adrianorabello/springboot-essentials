package devdojo.springboot.demo.util;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.IOUtils;

/**
 * JavaClientTest
 * 
 * classe para montar requisição com java puro passando a authenciação
 */
public class JavaClientTest {

  public static void main(String[] args) {

    HttpURLConnection connection = null;
    BufferedReader reader = null;

    String username = "user";
    String password = "devdojo";

    try {

      // URL url = new URL("http://localhost:8012/courses");
      URL url = new URL("http://localhost:8012/courses");
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.addRequestProperty("Authorization", "Basic " + encodeUserNamePassword(username, password));
      reader = new BufferedReader(new java.io.InputStreamReader(connection.getInputStream()));
      StringBuilder jsonSB = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        jsonSB.append(line);
      }

      System.out.println(jsonSB.toString());
    } catch (Exception e) {

      e.printStackTrace();
    } finally {

      IOUtils.closeQuietly(reader);

      if (connection != null)
        connection.disconnect();
    }

  }

  public static String encodeUserNamePassword(String user, String password) {

    String userPassword = user + ":" + password;
    return new String(Base64.encodeBase64(userPassword.getBytes()));
  }

}