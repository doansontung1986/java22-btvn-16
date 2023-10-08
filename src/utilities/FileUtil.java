package utilities;

import java.io.*;
import java.util.List;

public class FileUtil<T> implements DataReadable, DataWritable<T> {
    private static FileUtil fileUtil;

    private FileUtil() {

    }

    public static FileUtil getInstance() {
        if (fileUtil == null) {
            fileUtil = new FileUtil();
        }
        return fileUtil;
    }

    @Override
    public Object readDataFromFile(String fileName) {
        String filePath = String.join(File.separator, System.getProperty("user.dir"), "data", fileName);
        if (StringUtil.isNullOrEmpty(filePath)) {
            return null;
        }

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("File dữ liệu không tồn tại hoặc không thể khởi tạo danh sách dữ liệu");
        }

        return null;
    }

    @Override
    public void writeDataToFile(List<T> data, String fileName) {
        String filePath = String.join(File.separator, System.getProperty("user.dir"), "data", fileName);
        if (StringUtil.isNullOrEmpty(filePath)) {
            return;
        }

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            objectOutputStream.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
