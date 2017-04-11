package donkey.bora.com.secure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TokenManager {

    private static final String FILE_NAME = "donkey_pref.ini";
    private static final String GUEST_TOKEN = "";
    private static String defaultPath = "";

    public static void init(String path) {
        defaultPath = path;
    }

    public static String load() {
        String dirPath = defaultPath;
        File savedFile = new File(dirPath + FILE_NAME);
        if (!savedFile.exists()) {
            return GUEST_TOKEN;
        }

        BufferedReader br = null;
        String token = null;
        try {
            br = new BufferedReader(new FileReader(savedFile));
            token = br.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return token;
    }

    public static void save(String token, String path) {
        defaultPath = path;
        File filePath = new File(path);
        if (!filePath.exists()) {
            filePath.mkdir();
        }

        BufferedWriter bw = null;
        try {
            File file = new File(filePath + "/" + FILE_NAME);
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(token);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getDefaultPath() {
        return defaultPath;
    }
}
