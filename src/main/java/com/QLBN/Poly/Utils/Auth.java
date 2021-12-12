
package com.QLBN.Poly.Utils;

import com.QLBN.Poly.Entity.NhanVien;

public class Auth {
     public static NhanVien user = null;
    /**
     * Xóa thông tin của người sử dụng khi có yêu cầu đăng xuất
     */
    public static void clear() {
        Auth.user = null;
    }
    /**
     * Kiểm tra xem đăng nhập hay chưa
     * @return 
     */
    public static boolean isLogin() {
        return Auth.user != null;
    }
      /**
     * Kiểm tra xem có phải là trưởng phòng hay không
     * @return 
     */
    public static boolean isManager() {
        return Auth.isLogin() && user.getVaiTro().equals("Quản Lý") && user.isActive();
    }
      /**
     * Kiểm tra xem tài khoản có bị khóa hay không
     * @return 
     */
    public static boolean isActive() {
        return Auth.isLogin() && !user.isActive();
    }
}
