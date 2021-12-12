package com.QLBN.Poly.View;

import com.QLBN.Poly.Service.NhanVienServiceImp;
import com.QLBN.Poly.Utils.Auth;
import com.QLBN.Poly.Utils.MsgBox;
import com.QLBN.Poly.Utils.XImages;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class ManHinhChinh extends javax.swing.JFrame {

    JLabel jLabel;
    int Selection = 0;

    public ManHinhChinh() {
        initComponents();
        init();
        logOutAccount();
    }

    void init() {
        this.setIconImage(XImages.getAppIcon());
        this.setTitle("Bệnh Viện Gia Định");
        this.setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        btnTrangChu.setBackground(new Color(64, 148, 94));
        btnTrangChu.setForeground(new Color(255, 255, 255));
        setForm();
        DongHo();
        TrangChu();
    }

    void DongHo() {
        SimpleDateFormat formater = new SimpleDateFormat("hh:mm:ss a");
        new Timer(1000, (ActionEvent e) -> {
            jLabelDongHo.setText(formater.format(new Date()));
        }).start();
    }

    ImageIcon icon() {

        BufferedImage bi = null;
        InputStream input = ManHinhChinh.class.getResourceAsStream("/Images/SliderBenhVien1.jpg");
        try {
            bi = ImageIO.read(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(bi);
        Image image = icon.getImage();
        Image img = image.getScaledInstance(jpnView.getWidth(), jpnView.getHeight(), Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    void TrangChu() {
        Selection = 1;
        jLabel = new JLabel();
        jLabel.setSize((new Dimension(jpnView.getWidth(), jpnView.getHeight())));
        jLabel.setIcon(icon());
        jpnView.removeAll();
        jpnView.add(jLabel);
        jpnView.setPreferredSize(new Dimension(jpnView.getWidth(), jpnView.getHeight()));
        jpnView.validate();
        jpnView.repaint();

        btnDichVu.setBackground(ManHinhChinh.BackgroundUnSelected);
        btnDichVu.setForeground(ManHinhChinh.ForegroundUnSelected);

        btnKhoa.setBackground(ManHinhChinh.BackgroundUnSelected);
        btnKhoa.setForeground(ManHinhChinh.ForegroundUnSelected);

        btnThuoc.setBackground(ManHinhChinh.BackgroundUnSelected);
        btnThuoc.setForeground(ManHinhChinh.ForegroundUnSelected);

        btnTrangChu.setBackground(ManHinhChinh.BackgroundSelection);
        btnTrangChu.setForeground(ManHinhChinh.ForegroundSelection);

        btnNhanVien.setBackground(ManHinhChinh.BackgroundUnSelected);
        btnNhanVien.setForeground(ManHinhChinh.ForegroundUnSelected);
    }
    private static final Color BackgroundSelection = new Color(64, 148, 94);
    private static final Color ForegroundSelection = new Color(255, 255, 255);
    private static final Color BackgroundUnSelected = new Color(255, 204, 255);
    private static final Color ForegroundUnSelected = new Color(51, 51, 51);

    void NhanVien() {
        if (Auth.isLogin()) {
            Selection = 0;
            NhanVienJPanel nhanVienJPanel = new NhanVienJPanel();
            nhanVienJPanel.init();
            jpnView.removeAll();
            jpnView.setLayout(new BorderLayout());
            jpnView.add(nhanVienJPanel);
            jpnView.setPreferredSize(new Dimension(jpnView.getWidth(), jpnView.getHeight()));
            jpnView.validate();
            jpnView.repaint();

            System.out.println(btnNhanVien.getName());
            btnDichVu.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnDichVu.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnKhoa.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnKhoa.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnThuoc.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnThuoc.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnTrangChu.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnTrangChu.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnNhanVien.setBackground(ManHinhChinh.BackgroundSelection);
            btnNhanVien.setForeground(ManHinhChinh.ForegroundSelection);
        } else {
            MsgBox.alert(this, "Vui lòng đăng nhập");
        }
    }

    void BenhNhan() {
        if (Auth.isLogin()) {
            Selection = 0;
            BenhNhanJPanel BenhNhanJPanel = new BenhNhanJPanel();
            BenhNhanJPanel.init();
            jpnView.removeAll();
            jpnView.setLayout(new BorderLayout());
            jpnView.add(BenhNhanJPanel);
            jpnView.setPreferredSize(new Dimension(jpnView.getWidth(), jpnView.getHeight()));
            jpnView.validate();
            jpnView.repaint();

            btnDichVu.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnDichVu.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnKhoa.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnKhoa.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnThuoc.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnThuoc.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnTrangChu.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnTrangChu.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnNhanVien.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnNhanVien.setForeground(ManHinhChinh.ForegroundUnSelected);
        } else {
            MsgBox.alert(this, "Vui lòng đăng nhập");
        }
    }

    void HoSoBanhAn() {
        if (Auth.isLogin()) {
            Selection = 0;
            HoSoBenhAnJPanel HoSoBenhAnJPanel = new HoSoBenhAnJPanel();
            HoSoBenhAnJPanel.init();
            jpnView.removeAll();
            jpnView.setLayout(new BorderLayout());
            jpnView.add(HoSoBenhAnJPanel);
            jpnView.setPreferredSize(new Dimension(jpnView.getWidth(), jpnView.getHeight()));
            jpnView.validate();
            jpnView.repaint();

            btnDichVu.setBackground(new Color(255, 204, 255));
            btnDichVu.setForeground(new Color(51, 51, 51));

            btnDichVu.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnDichVu.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnKhoa.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnKhoa.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnThuoc.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnThuoc.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnTrangChu.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnTrangChu.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnNhanVien.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnNhanVien.setForeground(ManHinhChinh.ForegroundUnSelected);
        } else {
            MsgBox.alert(this, "Vui lòng đăng nhập");
        }
    }

    void DichVu() {
        if (Auth.isLogin()) {
            Selection = 0;
            DichVuJPanel DichVuJPanel = new DichVuJPanel();
            DichVuJPanel.init();
            jpnView.removeAll();
            jpnView.setLayout(new BorderLayout());
            jpnView.add(DichVuJPanel);
            jpnView.setPreferredSize(new Dimension(jpnView.getWidth(), jpnView.getHeight()));
            jpnView.validate();
            jpnView.repaint();

            btnDichVu.setBackground(ManHinhChinh.BackgroundSelection);
            btnDichVu.setForeground(ManHinhChinh.ForegroundSelection);

            btnKhoa.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnKhoa.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnThuoc.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnThuoc.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnTrangChu.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnTrangChu.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnNhanVien.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnNhanVien.setForeground(ManHinhChinh.ForegroundUnSelected);
        } else {
            MsgBox.alert(this, "Vui lòng đăng nhập");
        }
    }

    void Khoa() {
        if (Auth.isLogin()) {
            Selection = 0;
            KhoaJPanel KhoaJPanel = new KhoaJPanel();
            KhoaJPanel.init();
            jpnView.removeAll();
            jpnView.setLayout(new BorderLayout());
            jpnView.add(KhoaJPanel);
            jpnView.setPreferredSize(new Dimension(jpnView.getWidth(), jpnView.getHeight()));
            jpnView.validate();
            jpnView.repaint();

            btnDichVu.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnDichVu.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnKhoa.setBackground(ManHinhChinh.BackgroundSelection);
            btnKhoa.setForeground(ManHinhChinh.ForegroundSelection);

            btnThuoc.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnThuoc.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnTrangChu.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnTrangChu.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnNhanVien.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnNhanVien.setForeground(ManHinhChinh.ForegroundUnSelected);
        } else {
            MsgBox.alert(this, "Vui lòng đăng nhập");
        }
    }

    void BienLaiThuVienPhi() {
        if (Auth.isLogin()) {
            Selection = 0;
            BienLaiJPanel BienLaiThuVienPhiJPanel = new BienLaiJPanel();
            BienLaiThuVienPhiJPanel.init();
            jpnView.removeAll();
            jpnView.setLayout(new BorderLayout());
            jpnView.add(BienLaiThuVienPhiJPanel);
            jpnView.setPreferredSize(new Dimension(jpnView.getWidth(), jpnView.getHeight()));
            jpnView.validate();
            jpnView.repaint();

            btnDichVu.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnDichVu.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnKhoa.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnKhoa.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnThuoc.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnThuoc.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnTrangChu.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnTrangChu.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnNhanVien.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnNhanVien.setForeground(ManHinhChinh.ForegroundUnSelected);
        } else {
            MsgBox.alert(this, "Vui lòng đăng nhập");
        }
    }

    void Thuoc() {
        if (Auth.isLogin()) {
            Selection = 0;
            ThuocJPanel ThuocJPanel = new ThuocJPanel();
            ThuocJPanel.init();
            jpnView.removeAll();
            jpnView.setLayout(new BorderLayout());
            jpnView.add(ThuocJPanel);
            jpnView.setPreferredSize(new Dimension(jpnView.getWidth(), jpnView.getHeight()));
            jpnView.validate();
            jpnView.repaint();

            btnDichVu.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnDichVu.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnKhoa.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnKhoa.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnThuoc.setBackground(ManHinhChinh.BackgroundSelection);
            btnThuoc.setForeground(ManHinhChinh.ForegroundSelection);

            btnTrangChu.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnTrangChu.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnNhanVien.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnNhanVien.setForeground(ManHinhChinh.ForegroundUnSelected);
        } else {
            MsgBox.alert(this, "Vui lòng đăng nhập");
        }
    }

    void PhieuThuoc() {
        if (Auth.isLogin()) {
            Selection = 0;
            PhieuThuocJPanel PhieuThuoc = new PhieuThuocJPanel();
            PhieuThuoc.init();
            jpnView.removeAll();
            jpnView.setLayout(new BorderLayout());
            jpnView.add(PhieuThuoc);
            jpnView.setPreferredSize(new Dimension(jpnView.getWidth(), jpnView.getHeight()));
            jpnView.validate();
            jpnView.repaint();

            btnDichVu.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnDichVu.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnKhoa.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnKhoa.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnThuoc.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnThuoc.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnTrangChu.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnTrangChu.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnNhanVien.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnNhanVien.setForeground(ManHinhChinh.ForegroundUnSelected);
        } else {
            MsgBox.alert(this, "Vui lòng đăng nhập");
        }
    }

    void KhamBenh() {
        if (Auth.isLogin()) {
            Selection = 0;
            KhamBenhJPanel KhamBenhJPanel = new KhamBenhJPanel();
            KhamBenhJPanel.init();
            jpnView.removeAll();
            jpnView.setLayout(new BorderLayout());
            jpnView.add(KhamBenhJPanel);
            jpnView.setPreferredSize(new Dimension(jpnView.getWidth(), jpnView.getHeight()));
            jpnView.validate();
            jpnView.repaint();

            btnDichVu.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnDichVu.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnKhoa.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnKhoa.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnThuoc.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnThuoc.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnTrangChu.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnTrangChu.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnNhanVien.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnNhanVien.setForeground(ManHinhChinh.ForegroundUnSelected);
        } else {
            MsgBox.alert(this, "Vui lòng đăng nhập");
        }
    }

    void XetNghiem() {
        if (Auth.isLogin()) {
            Selection = 0;
            XetNghiemJPanel XetNghiemJPanel = new XetNghiemJPanel();
            XetNghiemJPanel.init();
            jpnView.removeAll();
            jpnView.setLayout(new BorderLayout());
            jpnView.add(XetNghiemJPanel);
            jpnView.setPreferredSize(new Dimension(jpnView.getWidth(), jpnView.getHeight()));
            jpnView.validate();
            jpnView.repaint();

            btnDichVu.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnDichVu.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnKhoa.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnKhoa.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnThuoc.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnThuoc.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnTrangChu.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnTrangChu.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnNhanVien.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnNhanVien.setForeground(ManHinhChinh.ForegroundUnSelected);
        } else {
            MsgBox.alert(this, "Vui lòng đăng nhập");
        }
    }

    void PhieuXetNghiem() {
        if (Auth.isLogin()) {
            Selection = 0;
            PhieuXetNghiemJPanel PhieuXetNghiemJPanel = new PhieuXetNghiemJPanel();
            PhieuXetNghiemJPanel.init();
            jpnView.removeAll();
            jpnView.setLayout(new BorderLayout());
            jpnView.add(PhieuXetNghiemJPanel);
            jpnView.setPreferredSize(new Dimension(jpnView.getWidth(), jpnView.getHeight()));
            jpnView.validate();
            jpnView.repaint();

            btnDichVu.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnDichVu.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnKhoa.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnKhoa.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnThuoc.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnThuoc.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnTrangChu.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnTrangChu.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnNhanVien.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnNhanVien.setForeground(ManHinhChinh.ForegroundUnSelected);
        } else {
            MsgBox.alert(this, "Vui lòng đăng nhập");
        }
    }

    void DV_BenhNhan_SD() {
        if (Auth.isLogin()) {
            Selection = 0;
            DV_BenhNhan_SDJPanel DV_BenhNhan_SDJPanel = new DV_BenhNhan_SDJPanel();
            DV_BenhNhan_SDJPanel.init();
            jpnView.removeAll();
            jpnView.setLayout(new BorderLayout());
            jpnView.add(DV_BenhNhan_SDJPanel);
            jpnView.setPreferredSize(new Dimension(jpnView.getWidth(), jpnView.getHeight()));
            jpnView.validate();
            jpnView.repaint();

            btnDichVu.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnDichVu.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnKhoa.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnKhoa.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnThuoc.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnThuoc.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnTrangChu.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnTrangChu.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnNhanVien.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnNhanVien.setForeground(ManHinhChinh.ForegroundUnSelected);
        } else {
            MsgBox.alert(this, "Vui lòng đăng nhập");
        }
    }

    void Benh() {
        if (Auth.isLogin()) {
            Selection = 0;
            BenhJPanel BenhJPanel = new BenhJPanel();
            BenhJPanel.init();
            jpnView.removeAll();
            jpnView.setLayout(new BorderLayout());
            jpnView.add(BenhJPanel);
            jpnView.setPreferredSize(new Dimension(jpnView.getWidth(), jpnView.getHeight()));
            jpnView.validate();
            jpnView.repaint();

            btnDichVu.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnDichVu.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnKhoa.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnKhoa.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnThuoc.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnThuoc.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnTrangChu.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnTrangChu.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnNhanVien.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnNhanVien.setForeground(ManHinhChinh.ForegroundUnSelected);
        } else {
            MsgBox.alert(this, "Vui lòng đăng nhập");
        }
    }

    void HeThong() {
        if (Auth.isLogin()) {
            Selection = 0;
            HeThongJPanel HeThongJPanel = new HeThongJPanel();
            HeThongJPanel.init();
            jpnView.removeAll();
            jpnView.setLayout(new BorderLayout());
            jpnView.add(HeThongJPanel);
            jpnView.setPreferredSize(new Dimension(jpnView.getWidth(), jpnView.getHeight()));
            jpnView.validate();
            jpnView.repaint();

            btnDichVu.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnDichVu.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnKhoa.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnKhoa.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnThuoc.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnThuoc.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnTrangChu.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnTrangChu.setForeground(ManHinhChinh.ForegroundUnSelected);

            btnNhanVien.setBackground(ManHinhChinh.BackgroundUnSelected);
            btnNhanVien.setForeground(ManHinhChinh.ForegroundUnSelected);
        } else {
            MsgBox.alert(this, "Vui lòng đăng nhập");
        }
    }

    void DoiMatKhau() {
        if (Auth.isLogin()) {
            DoiMatKhauJDialog doiMatKhauJDialog = new DoiMatKhauJDialog(this, rootPaneCheckingEnabled);
            doiMatKhauJDialog.init();
            doiMatKhauJDialog.setTenTaiKhoan(Auth.user.getMaNV());
            doiMatKhauJDialog.setVisible(true);
        } else {
            MsgBox.alert(this, "Vui lòng đăng nhập");
        }
    }

    void DangXuat() {
        this.dispose();
        Auth.clear();
        ManHinhDangNhap manHinhDangNhap = new ManHinhDangNhap();
        manHinhDangNhap.init();
        manHinhDangNhap.setVisible(true);
    }

    void KetThuc() {
        if (MsgBox.confirm(this, "Bạn muốn kết thúc làm việc?")) {
            System.exit(0);
        }
    }

    void setForm() {
        if (Auth.isLogin()) {
            switch (Auth.user.getVaiTro()) {
                case "Bác Sỹ":
                    jMenuItemHoSoBenhAn.setVisible(true);
                    jMenuItemKhamBenh.setVisible(true);
                    jMenuItemPhieuThuoc.setVisible(true);
                    jMenuItemPhieuXetNghiem.setVisible(true);
                    jMenuItemTTBenhNhan.setVisible(true);
                    jMenuItemBenh.setVisible(true);
                    jMenuItemXetNghiem.setVisible(true);
                    btnKhoa.setVisible(true);
                    btnThuoc.setVisible(true);
                    jMenuItemKhoa.setVisible(true);

                    jMenuItemNhanVien.setVisible(false);
                    btnNhanVien.setVisible(false);
                    jMenu4.setVisible(false);
                    btnDichVu.setVisible(false);
                    jMenuItemDichVu.setVisible(false);
                    jMenuItemQLHeThong.setVisible(false);
                    break;
                case "Nhân Viên":
                    jMenuItemHoSoBenhAn.setVisible(false);
                    jMenuItemKhamBenh.setVisible(false);
                    jMenuItemPhieuThuoc.setVisible(false);
                    jMenuItemPhieuXetNghiem.setVisible(false);
                    jMenuItemQLHeThong.setVisible(false);

                    jMenuItemTTBenhNhan.setVisible(true);
                    jMenuItemBenh.setVisible(true);
                    jMenuItemNhanVien.setVisible(true);
                    btnNhanVien.setVisible(true);
                    jMenu4.setVisible(true);
                    btnKhoa.setVisible(true);
                    btnDichVu.setVisible(true);
                    btnThuoc.setVisible(true);
                    jMenuItemDichVu.setVisible(true);
                    jMenuItemKhoa.setVisible(true);
                    jMenuItemXetNghiem.setVisible(true);
                    break;
                case "Quản Lý":
                    jMenuItemHoSoBenhAn.setVisible(true);
                    jMenuItemKhamBenh.setVisible(true);
                    jMenuItemPhieuThuoc.setVisible(true);
                    jMenuItemPhieuXetNghiem.setVisible(true);
                    jMenuItemQLHeThong.setVisible(true);

                    jMenuItemTTBenhNhan.setVisible(true);
                    jMenuItemBenh.setVisible(true);
                    jMenuItemNhanVien.setVisible(true);
                    btnNhanVien.setVisible(true);
                    jMenu4.setVisible(true);
                    btnKhoa.setVisible(true);
                    btnDichVu.setVisible(true);
                    btnThuoc.setVisible(true);
                    jMenuItemDichVu.setVisible(true);
                    jMenuItemKhoa.setVisible(true);
                    jMenuItemXetNghiem.setVisible(true);
                    break;
                default:
                    break;
            }
        } else {
            MsgBox.alert(this, "Vui lòng đăng nhập");
        }
    }

    void logOutAccount() {
        new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        try {
                            if (!new NhanVienServiceImp().isActive(Auth.user.getMaNV())) {
                                DangXuat();
                                MsgBox.alert(ManHinhChinh.this, "Tài Khoản Của Bạn Đã Bị Khóa");
                                Thread.sleep(1000);
                            }
                        } catch (NullPointerException e) {
                        }
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

        }.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabelDongHo = new javax.swing.JLabel();
        jpnView = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        btnTrangChu = new javax.swing.JButton();
        btnDangXuat = new javax.swing.JButton();
        btnKetThuc = new javax.swing.JButton();
        btnNhanVien = new javax.swing.JButton();
        btnThuoc = new javax.swing.JButton();
        btnDichVu = new javax.swing.JButton();
        btnKhoa = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemQLHeThong = new javax.swing.JMenuItem();
        jMenuItemDoiMatKhau = new javax.swing.JMenuItem();
        jMenuItemDangXuat = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemNhanVien = new javax.swing.JMenuItem();
        jMenuItemXetNghiem = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItemKhamBenh = new javax.swing.JMenuItem();
        jMenuItemTTBenhNhan = new javax.swing.JMenuItem();
        jMenuItemHoSoBenhAn = new javax.swing.JMenuItem();
        jMenuItemPhieuThuoc = new javax.swing.JMenuItem();
        jMenuItemPhieuXetNghiem = new javax.swing.JMenuItem();
        jMenuItemKhoa = new javax.swing.JMenuItem();
        jMenuItemDichVu = new javax.swing.JMenuItem();
        jMenuItemThuoc = new javax.swing.JMenuItem();
        jMenuItemBenh = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItemBienLai = new javax.swing.JMenuItem();
        jMenuItemDVBenhNhanSD = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 735));

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 444));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Info.png"))); // NOI18N
        jLabel2.setText("Bệnh viện Gia Định");

        jLabelDongHo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabelDongHo.setForeground(new java.awt.Color(204, 0, 0));
        jLabelDongHo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Clock.png"))); // NOI18N
        jLabelDongHo.setText("jLabel3");

        jpnView.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jpnViewComponentResized(evt);
            }
        });

        javax.swing.GroupLayout jpnViewLayout = new javax.swing.GroupLayout(jpnView);
        jpnView.setLayout(jpnViewLayout);
        jpnViewLayout.setHorizontalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        jpnViewLayout.setVerticalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 484, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 706, Short.MAX_VALUE)
                .addComponent(jLabelDongHo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jpnView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jpnView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelDongHo, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jToolBar1.setBackground(new java.awt.Color(255, 204, 255));
        jToolBar1.setFloatable(false);

        btnTrangChu.setBackground(new java.awt.Color(255, 204, 255));
        btnTrangChu.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnTrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/home.png"))); // NOI18N
        btnTrangChu.setText("Trang Chủ");
        btnTrangChu.setFocusable(false);
        btnTrangChu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTrangChu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTrangChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangChuActionPerformed(evt);
            }
        });
        jToolBar1.add(btnTrangChu);

        btnDangXuat.setBackground(new java.awt.Color(255, 204, 255));
        btnDangXuat.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exit.png"))); // NOI18N
        btnDangXuat.setText("Đăng Xuất");
        btnDangXuat.setFocusable(false);
        btnDangXuat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDangXuat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });
        jToolBar1.add(btnDangXuat);

        btnKetThuc.setBackground(new java.awt.Color(255, 204, 255));
        btnKetThuc.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnKetThuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/stop_sign.png"))); // NOI18N
        btnKetThuc.setText("Kết Thúc");
        btnKetThuc.setFocusable(false);
        btnKetThuc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnKetThuc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKetThucActionPerformed(evt);
            }
        });
        jToolBar1.add(btnKetThuc);

        btnNhanVien.setBackground(new java.awt.Color(255, 204, 255));
        btnNhanVien.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/member.png"))); // NOI18N
        btnNhanVien.setText("Nhân Viên");
        btnNhanVien.setFocusable(false);
        btnNhanVien.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNhanVien.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienActionPerformed(evt);
            }
        });
        jToolBar1.add(btnNhanVien);

        btnThuoc.setBackground(new java.awt.Color(255, 204, 255));
        btnThuoc.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnThuoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/clinic.png"))); // NOI18N
        btnThuoc.setText("Thuốc");
        btnThuoc.setFocusable(false);
        btnThuoc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnThuoc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThuocActionPerformed(evt);
            }
        });
        jToolBar1.add(btnThuoc);

        btnDichVu.setBackground(new java.awt.Color(255, 204, 255));
        btnDichVu.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnDichVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/food_service_16px.png"))); // NOI18N
        btnDichVu.setText("Dịch Vụ");
        btnDichVu.setFocusable(false);
        btnDichVu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDichVu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDichVuActionPerformed(evt);
            }
        });
        jToolBar1.add(btnDichVu);

        btnKhoa.setBackground(new java.awt.Color(255, 204, 255));
        btnKhoa.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnKhoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/department.png"))); // NOI18N
        btnKhoa.setText("Khoa");
        btnKhoa.setFocusable(false);
        btnKhoa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnKhoa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnKhoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoaActionPerformed(evt);
            }
        });
        jToolBar1.add(btnKhoa);

        jMenu1.setText("Hệ Thống");
        jMenu1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jMenuItemQLHeThong.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_TAB, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemQLHeThong.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItemQLHeThong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/system_information.png"))); // NOI18N
        jMenuItemQLHeThong.setText("Quản Lý Hệ Thống");
        jMenuItemQLHeThong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemQLHeThongActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemQLHeThong);

        jMenuItemDoiMatKhau.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItemDoiMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/change_16px.png"))); // NOI18N
        jMenuItemDoiMatKhau.setText("Đổi Mật Khẩu");
        jMenuItemDoiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDoiMatKhauActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemDoiMatKhau);

        jMenuItemDangXuat.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        jMenuItemDangXuat.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItemDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exit.png"))); // NOI18N
        jMenuItemDangXuat.setText("Đăng Xuất");
        jMenuItemDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDangXuatActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemDangXuat);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Quản Lý");
        jMenu2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jMenuItemNhanVien.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemNhanVien.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItemNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/member.png"))); // NOI18N
        jMenuItemNhanVien.setText("Nhân Viên");
        jMenuItemNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNhanVienActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemNhanVien);

        jMenuItemXetNghiem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemXetNghiem.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItemXetNghiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/test_passed.png"))); // NOI18N
        jMenuItemXetNghiem.setText("Xét Nghiệm");
        jMenuItemXetNghiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemXetNghiemActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemXetNghiem);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/hospital_bed.png"))); // NOI18N
        jMenu6.setText("Bệnh Nhân");
        jMenu6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jMenuItemKhamBenh.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemKhamBenh.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItemKhamBenh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/doctors_bag_16px.png"))); // NOI18N
        jMenuItemKhamBenh.setText("Khám Bệnh");
        jMenuItemKhamBenh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemKhamBenhActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItemKhamBenh);

        jMenuItemTTBenhNhan.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemTTBenhNhan.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItemTTBenhNhan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/aed_16px.png"))); // NOI18N
        jMenuItemTTBenhNhan.setText("Thông Tin Bệnh Nhân");
        jMenuItemTTBenhNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTTBenhNhanActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItemTTBenhNhan);

        jMenuItemHoSoBenhAn.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemHoSoBenhAn.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItemHoSoBenhAn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/dialysis_16px.png"))); // NOI18N
        jMenuItemHoSoBenhAn.setText("Hồ Sơ Bệnh Án");
        jMenuItemHoSoBenhAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemHoSoBenhAnActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItemHoSoBenhAn);

        jMenuItemPhieuThuoc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemPhieuThuoc.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItemPhieuThuoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/form_16px.png"))); // NOI18N
        jMenuItemPhieuThuoc.setText("Phiếu Thuốc");
        jMenuItemPhieuThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPhieuThuocActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItemPhieuThuoc);

        jMenuItemPhieuXetNghiem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemPhieuXetNghiem.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItemPhieuXetNghiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/treatment_16px.png"))); // NOI18N
        jMenuItemPhieuXetNghiem.setText("Phiếu Xét Nghiệm");
        jMenuItemPhieuXetNghiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPhieuXetNghiemActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItemPhieuXetNghiem);

        jMenu2.add(jMenu6);

        jMenuItemKhoa.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemKhoa.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItemKhoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/department.png"))); // NOI18N
        jMenuItemKhoa.setText("Khoa");
        jMenuItemKhoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemKhoaActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemKhoa);

        jMenuItemDichVu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemDichVu.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItemDichVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/food_service_16px.png"))); // NOI18N
        jMenuItemDichVu.setText("Dịch Vụ");
        jMenuItemDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDichVuActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemDichVu);

        jMenuItemThuoc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemThuoc.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItemThuoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/clinic.png"))); // NOI18N
        jMenuItemThuoc.setText("Thuốc");
        jMenuItemThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemThuocActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemThuoc);

        jMenuItemBenh.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemBenh.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jMenuItemBenh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/hypertension.png"))); // NOI18N
        jMenuItemBenh.setText("Bệnh");
        jMenuItemBenh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBenhActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemBenh);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Báo Cáo");
        jMenu4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jMenuItemBienLai.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemBienLai.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItemBienLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/receipt_16px.png"))); // NOI18N
        jMenuItemBienLai.setText("Biên Lai");
        jMenuItemBienLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBienLaiActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItemBienLai);

        jMenuItemDVBenhNhanSD.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_6, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemDVBenhNhanSD.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItemDVBenhNhanSD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/order_history_16px.png"))); // NOI18N
        jMenuItemDVBenhNhanSD.setText("DV Bệnh Nhân Sử Dụng");
        jMenuItemDVBenhNhanSD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDVBenhNhanSDActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItemDVBenhNhanSD);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Trợ Giúp");
        jMenu5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jMenuItem2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItem2.setText("Phiên Bản");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem2);

        jMenuItem3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItem3.setText("Hướng Dẫn");
        jMenu5.add(jMenuItem3);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDoiMatKhauActionPerformed
        DoiMatKhau();
    }//GEN-LAST:event_jMenuItemDoiMatKhauActionPerformed

    private void jMenuItemQLHeThongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemQLHeThongActionPerformed
        HeThong();
    }//GEN-LAST:event_jMenuItemQLHeThongActionPerformed

    private void jMenuItemDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDangXuatActionPerformed
        DangXuat();
    }//GEN-LAST:event_jMenuItemDangXuatActionPerformed

    private void jMenuItemTTBenhNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTTBenhNhanActionPerformed
        BenhNhan();
    }//GEN-LAST:event_jMenuItemTTBenhNhanActionPerformed

    private void btnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienActionPerformed
        NhanVien();
    }//GEN-LAST:event_btnNhanVienActionPerformed

    private void btnTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangChuActionPerformed
        TrangChu();
    }//GEN-LAST:event_btnTrangChuActionPerformed

    private void jMenuItemNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNhanVienActionPerformed
        NhanVien();
    }//GEN-LAST:event_jMenuItemNhanVienActionPerformed

    private void jMenuItemHoSoBenhAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemHoSoBenhAnActionPerformed
        HoSoBanhAn();
    }//GEN-LAST:event_jMenuItemHoSoBenhAnActionPerformed

    private void btnDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDichVuActionPerformed
        DichVu();
    }//GEN-LAST:event_btnDichVuActionPerformed

    private void jMenuItemDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDichVuActionPerformed
        DichVu();
    }//GEN-LAST:event_jMenuItemDichVuActionPerformed

    private void btnKhoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoaActionPerformed
        Khoa();
    }//GEN-LAST:event_btnKhoaActionPerformed

    private void jMenuItemBienLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBienLaiActionPerformed
        BienLaiThuVienPhi();
    }//GEN-LAST:event_jMenuItemBienLaiActionPerformed

    private void jMenuItemKhoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemKhoaActionPerformed
        Khoa();
    }//GEN-LAST:event_jMenuItemKhoaActionPerformed

    private void jMenuItemThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemThuocActionPerformed
        Thuoc();
    }//GEN-LAST:event_jMenuItemThuocActionPerformed

    private void btnThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThuocActionPerformed
        Thuoc();
    }//GEN-LAST:event_btnThuocActionPerformed

    private void jMenuItemPhieuThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPhieuThuocActionPerformed
        PhieuThuoc();
    }//GEN-LAST:event_jMenuItemPhieuThuocActionPerformed

    private void jMenuItemDVBenhNhanSDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDVBenhNhanSDActionPerformed
        DV_BenhNhan_SD();
    }//GEN-LAST:event_jMenuItemDVBenhNhanSDActionPerformed

    private void jMenuItemXetNghiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemXetNghiemActionPerformed
        XetNghiem();
    }//GEN-LAST:event_jMenuItemXetNghiemActionPerformed

    private void jMenuItemKhamBenhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemKhamBenhActionPerformed
        KhamBenh();
    }//GEN-LAST:event_jMenuItemKhamBenhActionPerformed

    private void jMenuItemPhieuXetNghiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPhieuXetNghiemActionPerformed
        PhieuXetNghiem();
    }//GEN-LAST:event_jMenuItemPhieuXetNghiemActionPerformed

    private void jMenuItemBenhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBenhActionPerformed
        Benh();
    }//GEN-LAST:event_jMenuItemBenhActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        DangXuat();
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void btnKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKetThucActionPerformed
        KetThuc();
    }//GEN-LAST:event_btnKetThucActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        GioiThieu gt = new GioiThieu(this, rootPaneCheckingEnabled);
        gt.setLocationRelativeTo(this);
        gt.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed
    int minimunsize = 0;
    private void jpnViewComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jpnViewComponentResized
        if (jpnView.getBounds().width != minimunsize) {
            minimunsize = jpnView.getWidth();
            if (Selection == 1) {
                TrangChu();
            }
        }
    }//GEN-LAST:event_jpnViewComponentResized

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManHinhChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManHinhChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManHinhChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManHinhChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ManHinhChinh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnDichVu;
    private javax.swing.JButton btnKetThuc;
    private javax.swing.JButton btnKhoa;
    private javax.swing.JButton btnNhanVien;
    private javax.swing.JButton btnThuoc;
    private javax.swing.JButton btnTrangChu;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelDongHo;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItemBenh;
    private javax.swing.JMenuItem jMenuItemBienLai;
    private javax.swing.JMenuItem jMenuItemDVBenhNhanSD;
    private javax.swing.JMenuItem jMenuItemDangXuat;
    private javax.swing.JMenuItem jMenuItemDichVu;
    private javax.swing.JMenuItem jMenuItemDoiMatKhau;
    private javax.swing.JMenuItem jMenuItemHoSoBenhAn;
    private javax.swing.JMenuItem jMenuItemKhamBenh;
    private javax.swing.JMenuItem jMenuItemKhoa;
    private javax.swing.JMenuItem jMenuItemNhanVien;
    private javax.swing.JMenuItem jMenuItemPhieuThuoc;
    private javax.swing.JMenuItem jMenuItemPhieuXetNghiem;
    private javax.swing.JMenuItem jMenuItemQLHeThong;
    private javax.swing.JMenuItem jMenuItemTTBenhNhan;
    private javax.swing.JMenuItem jMenuItemThuoc;
    private javax.swing.JMenuItem jMenuItemXetNghiem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel jpnView;
    // End of variables declaration//GEN-END:variables
}
