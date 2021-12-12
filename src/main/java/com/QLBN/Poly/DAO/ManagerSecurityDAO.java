package com.QLBN.Poly.DAO;

import com.QLBN.Poly.Entity.ManagerSecurity;
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

public class ManagerSecurityDAO {

    public static List<ManagerSecurity> list = new ArrayList<>();

    private static File getFile() {
        File file = new File(new File("File/Restore").getAbsolutePath());
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static int SaveFileRestore(List<ManagerSecurity> list, String destination) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {

            fos = new FileOutputStream(ManagerSecurityDAO.getFile() + destination);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            return 1;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManagerSecurityDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManagerSecurityDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
            }
        }
        return 0;
    }

    public static List<ManagerSecurity> OpenFileRestore(String destination) {
        List<ManagerSecurity> lis = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {

            fis = new FileInputStream(ManagerSecurityDAO.getFile() + destination);
            ois = new ObjectInputStream(fis);
            lis = (List<ManagerSecurity>) ois.readObject();
        } catch (FileNotFoundException ex) {
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ManagerSecurityDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
            }
        }
        return lis;
    }

    private static void XoaFile(String destination) {
        if (OpenFileRestore(destination).size() >= 5000) {
            OpenFileRestore(destination).clear();
            SaveFileRestore(OpenFileRestore(destination), destination);
        }
    }

    public static void CallDeleteFile() {
        XoaFile("\\DangNhap.txt");
        XoaFile("\\DangKy.txt");
        XoaFile("\\ThayDoiMK.txt");
        XoaFile("\\TacVu.txt");
    }
}
