package com.QLBN.Poly.Utils;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

public class XImages {

    public static Image getAppIcon() {
        String file = "/Images/logo02.png";
        return new ImageIcon(XImages.class.getResource(file)).getImage();
    }

    /**
     * Sao chép file logo chuyên đề vào thư mục logo
     *
     * @param src là đối tượng file ảnh
     * @return
     */
    public static boolean save(File src) {
        File dst = new File("Image", src.getName());
        if (!dst.getParentFile().exists()) {
            dst.getParentFile().mkdirs();
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    /**
     * Đọc hình ảnh logo chuyên đề
     *
     * @param fileName là tên file logo
     * @param width
     * @param height
     * @return ảnh đọc được
     */
    public static ImageIcon read(String fileName, int width, int height) {
        File path = new File("Image", fileName);
        ImageIcon icon = new ImageIcon(path.getAbsolutePath());
        Image img = icon.getImage();
        ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(width, height, Image.SCALE_SMOOTH));
        return imageIcon;
    }

//    public static FileResolver FileResolver() {
//        //Lấy đường dẫn hình ảnh
//        FileResolver fileResolver = new FileResolver() {
//            @Override
//            public File resolveFile(String fileName) {
//                URI uri;
//                try {
//                    uri = new URI(this.getClass().getResource(fileName).getPath());
//                    return new File(uri.getPath());
//                } catch (URISyntaxException e) {
//                    return null;
//                }
//            }
//        };
//        return fileResolver;
//    }
    public static InputStream getPath(String filename) {
       return XImages.class.getResourceAsStream(filename);
    }
    public static void main(String[] args) {
        System.out.println(XImages.getPath("/Images/logo02_1.png"));
    }
}
