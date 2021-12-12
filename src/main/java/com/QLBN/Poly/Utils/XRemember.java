package com.QLBN.Poly.Utils;

import com.QLBN.Poly.Entity.RememberMe;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XRemember {

    public static List<RememberMe> li = new ArrayList<>();

    private static File getFile() {
        File file = new File(new File("File\\RemamberFile").getAbsolutePath());
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static List<RememberMe> setNhanVienFile() {
        List<RememberMe> list = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(getFile() + "\\RemamberFile.txt");
            ois = new ObjectInputStream(fis);
            list = (List<RememberMe>) ois.readObject();

        } catch (FileNotFoundException ex) {
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(XRemember.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                    ois.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XRemember.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public static void saveNhanVienFile(List<RememberMe> list) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(getFile() + "\\RemamberFile.txt");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XRemember.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XRemember.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
                oos.close();
            } catch (IOException ex) {
                Logger.getLogger(XRemember.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
