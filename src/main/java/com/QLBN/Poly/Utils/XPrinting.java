package com.QLBN.Poly.Utils;

import com.QLBN.Poly.View.KhamBenhJPanel;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class XPrinting {

    public static void print(String addressClass, String Title, HashMap<String, Object> para) {
        try {
            InputStream reportSrcFile = XPrinting.class.getResourceAsStream("/Report/" + addressClass);

            Connection conn = XJDBC.getConnection();

            JasperDesign jd = JRXmlLoader.load(reportSrcFile);
            //Lấy theo kiểu jasper
//            JasperReport jr = (JasperReport) JRLoader.loadObject(reportSrcFile);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            
            JasperPrint jp = JasperFillManager.fillReport(jr, para, conn);
            
//                JasperViewer.viewReport(jp,false);
//                JasperExportManager.exportReportToHtmlFile(jp, "E:\\Java\\QuanLyBenhNhan\\File\\Printing\\GiayVaoVien.pdf");
//                JasperPrintManager.printReport(jp, true);
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setTitle(Title);
            jv.setIconImage(XImages.getAppIcon());
            jv.setZoomRatio(0.85f);
            jv.setExtendedState(JFrame.MAXIMIZED_VERT);
            jv.setVisible(true);
            System.out.print("Done!");
        } catch (JRException | SQLException ex) {
            Logger.getLogger(KhamBenhJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
//        String a = XPrinting.class.getResourceAsStream("/Report/GiayNhapVien.jasper");
//        System.out.println(a);
    }
}
