USE [master]
GO
/****** Object:  Database [QuanLyBenhNhan]    Script Date: 04/12/2021 07:57:46 AM ******/
CREATE DATABASE [QuanLyBenhNhan]
GO
USE QuanLyBenhNhan
GO
ALTER DATABASE [QuanLyBenhNhan] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QuanLyBenhNhan].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QuanLyBenhNhan] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QuanLyBenhNhan] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QuanLyBenhNhan] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QuanLyBenhNhan] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QuanLyBenhNhan] SET ARITHABORT OFF 
GO
ALTER DATABASE [QuanLyBenhNhan] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [QuanLyBenhNhan] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QuanLyBenhNhan] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QuanLyBenhNhan] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QuanLyBenhNhan] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QuanLyBenhNhan] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QuanLyBenhNhan] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QuanLyBenhNhan] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QuanLyBenhNhan] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QuanLyBenhNhan] SET  ENABLE_BROKER 
GO
ALTER DATABASE [QuanLyBenhNhan] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QuanLyBenhNhan] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QuanLyBenhNhan] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QuanLyBenhNhan] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QuanLyBenhNhan] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QuanLyBenhNhan] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QuanLyBenhNhan] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QuanLyBenhNhan] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [QuanLyBenhNhan] SET  MULTI_USER 
GO
ALTER DATABASE [QuanLyBenhNhan] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QuanLyBenhNhan] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QuanLyBenhNhan] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QuanLyBenhNhan] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [QuanLyBenhNhan] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [QuanLyBenhNhan] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [QuanLyBenhNhan] SET QUERY_STORE = OFF
GO
USE [QuanLyBenhNhan]
GO
/****** Object:  Table [dbo].[Benh]    Script Date: 04/12/2021 07:57:46 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Benh](
	[maBenh] [int] IDENTITY(1841002,1) NOT NULL,
	[tenBenh] [nvarchar](50) NOT NULL,
	[dauHieu] [nvarchar](500) NULL,
 CONSTRAINT [PK_Benh] PRIMARY KEY CLUSTERED 
(
	[maBenh] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BenhNhan]    Script Date: 04/12/2021 07:57:46 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BenhNhan](
	[maBN] [int] IDENTITY(9000000,1) NOT NULL,
	[tenBN] [nvarchar](50) NOT NULL,
	[Ngaysinh] [date] NOT NULL,
	[Diachi] [nvarchar](100) NOT NULL,
	[Gioitinh] [nchar](3) NOT NULL,
	[ngheNghiep] [nvarchar](100) NULL,
	[danToc] [nvarchar](50) NULL,
	[QuocTich] [nvarchar](50) NULL,
	[BHYTHieuLucTu] [date] NULL,
	[BHYTHieuLucDen] [date] NULL,
	[SoBHYT] [varchar](50) NULL,
	[nguoiThan] [nvarchar](50) NULL,
	[sdtNguoiThan] [char](10) NULL,
	[Doituong] [bit] NOT NULL,
 CONSTRAINT [PK_BenhNhan] PRIMARY KEY CLUSTERED 
(
	[maBN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BienLai]    Script Date: 04/12/2021 07:57:46 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BienLai](
	[maBienLai] [int] IDENTITY(7000000,1) NOT NULL,
	[MaNV] [varchar](20) NOT NULL,
	[MaBN] [int] NOT NULL,
	[ngayTT] [date] NOT NULL,
	[TenKhoanChiPhi] [nvarchar](50) NOT NULL,
	[TienChiPhi] [money] NOT NULL,
	[TrangThai] [nvarchar](30) NOT NULL,
 CONSTRAINT [PK_BienLai] PRIMARY KEY CLUSTERED 
(
	[maBienLai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DichVu]    Script Date: 04/12/2021 07:57:46 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DichVu](
	[MaDV] [int] IDENTITY(444444,1) NOT NULL,
	[TenDV] [nvarchar](50) NOT NULL,
	[SoLuong] [int] NOT NULL,
	[Dongia] [money] NOT NULL,
	[Dvtinh] [nvarchar](20) NOT NULL,
 CONSTRAINT [PK_DichVu] PRIMARY KEY CLUSTERED 
(
	[MaDV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DV_BenhNhan_SD]    Script Date: 04/12/2021 07:57:46 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DV_BenhNhan_SD](
	[sphieuSD] [int] IDENTITY(800000,1) NOT NULL,
	[maBN] [int] NOT NULL,
	[maDV] [int] NOT NULL,
	[ngaySD] [date] NOT NULL,
 CONSTRAINT [PK_DVBNSD] PRIMARY KEY CLUSTERED 
(
	[sphieuSD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoSoBenhAn]    Script Date: 04/12/2021 07:57:46 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoSoBenhAn](
	[sPhieuHSBA] [int] IDENTITY(555555,1) NOT NULL,
	[VaoVien] [datetime] NOT NULL,
	[RaVien] [datetime] NOT NULL,
	[huyetAp] [varchar](40) NULL,
	[nhipTim] [int] NULL,
	[Mach] [int] NULL,
	[canNang] [int] NULL,
	[ketQuaDT] [nvarchar](50) NULL,
	[maBenh] [int] NULL,
	[Makhoa] [int] NOT NULL,
	[MaBN] [int] NOT NULL,
	[MaNV] [varchar](20) NOT NULL,
	[Cphidtri] [money] NOT NULL,
	[ThoiGianTV] [datetime] NULL,
	[NguyenNhan] [nvarchar](40) NULL,
 CONSTRAINT [PK_HoSoBenhAn] PRIMARY KEY CLUSTERED 
(
	[sPhieuHSBA] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhamBenh]    Script Date: 04/12/2021 07:57:46 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhamBenh](
	[soPhieuKham] [int] IDENTITY(1841222,1) NOT NULL,
	[ngayKham] [date] NOT NULL,
	[trieuChung] [nvarchar](max) NOT NULL,
	[huyetAp] [varchar](20) NULL,
	[nhipTim] [int] NULL,
	[Mach] [int] NULL,
	[canNang] [int] NULL,
	[chuanDoan] [nvarchar](50) NULL,
	[yeuCau] [bit] NULL,
	[maNV] [varchar](20) NOT NULL,
	[maBenh] [int] NOT NULL,
	[maBenhNhan] [int] NOT NULL,
	[CPKham] [money] NOT NULL,
 CONSTRAINT [PK_KhamBenh] PRIMARY KEY CLUSTERED 
(
	[soPhieuKham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Khoa]    Script Date: 04/12/2021 07:57:46 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Khoa](
	[Makhoa] [int] IDENTITY(121212,1) NOT NULL,
	[Tenkhoa] [nvarchar](50) NOT NULL,
	[KhuVuc] [nvarchar](50) NULL,
 CONSTRAINT [PK_Khoa] PRIMARY KEY CLUSTERED 
(
	[Makhoa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 04/12/2021 07:57:46 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[maNV] [varchar](20) NOT NULL,
	[matKhau] [varchar](50) NOT NULL,
	[tenNV] [nvarchar](50) NOT NULL,
	[ngaySinh] [date] NULL,
	[diaChi] [nvarchar](100) NULL,
	[soDienThoai] [char](10) NULL,
	[email] [varchar](50) NOT NULL,
	[gioiTinh] [nchar](3) NOT NULL,
	[vaiTro] [nvarchar](20) NOT NULL,
	[active] [bit] NULL,
 CONSTRAINT [PK_NhanVien] PRIMARY KEY CLUSTERED 
(
	[maNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhieuThuoc]    Script Date: 04/12/2021 07:57:46 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuThuoc](
	[sphieuthuoc] [int] IDENTITY(5000000,1) NOT NULL,
	[Mathuoc] [int] NOT NULL,
	[Soluong] [nvarchar](50) NOT NULL,
	[MaNV] [varchar](20) NOT NULL,
	[ngayUong] [nvarchar](30) NULL,
	[soLan_Ngay] [nvarchar](30) NULL,
	[BarCode] [varchar](40) NULL,
	[maBN] [int] NOT NULL,
	[NgKeDon] [date] NOT NULL,
	[tongTien] [money] NOT NULL,
 CONSTRAINT [PK_PhieuThuoc] PRIMARY KEY CLUSTERED 
(
	[sphieuthuoc] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhieuXetNghiem]    Script Date: 04/12/2021 07:57:46 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuXetNghiem](
	[sphieuXN] [int] IDENTITY(1,1) NOT NULL,
	[MaXN] [int] NOT NULL,
	[maBN] [int] NOT NULL,
	[ngayXN] [datetime] NOT NULL,
	[kquaXN] [nvarchar](100) NOT NULL,
	[lidoXN] [nvarchar](250) NOT NULL,
	[NguoiYeuCau] [varchar](20) NOT NULL,
 CONSTRAINT [PK_PhieuXetNghiem] PRIMARY KEY CLUSTERED 
(
	[sphieuXN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhongBenh]    Script Date: 04/12/2021 07:57:46 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhongBenh](
	[MaPhong] [int] NOT NULL,
	[soGiuong] [nvarchar](20) NULL,
	[soBuong] [nvarchar](20) NULL,
	[maBN] [int] NOT NULL,
 CONSTRAINT [PK_PhongBenh] PRIMARY KEY CLUSTERED 
(
	[MaPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Thuoc]    Script Date: 04/12/2021 07:57:46 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Thuoc](
	[MaThuoc] [int] IDENTITY(6000000,1) NOT NULL,
	[TenBietDuoc] [nvarchar](100) NOT NULL,
	[tenHoatDuoc] [nvarchar](100) NOT NULL,
	[HamLuong] [varchar](30) NULL,
	[DVT] [nvarchar](20) NULL,
	[donGia] [money] NOT NULL,
 CONSTRAINT [PK_Thuoc] PRIMARY KEY CLUSTERED 
(
	[MaThuoc] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[XetNghiem]    Script Date: 04/12/2021 07:57:46 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[XetNghiem](
	[MaXN] [int] IDENTITY(1,1) NOT NULL,
	[TenXN] [nvarchar](200) NULL,
	[MucDich] [nvarchar](500) NULL,
	[DonGiaBIH] [money] NOT NULL,
	[DonGiaBHYT] [money] NOT NULL,
 CONSTRAINT [PK_XetNghiem] PRIMARY KEY CLUSTERED 
(
	[MaXN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Benh] ON 

INSERT [dbo].[Benh] ([maBenh], [tenBenh], [dauHieu]) VALUES (1841002, N'R???i Lo???n Ti???n ????nh', N'Ch??ng m???t, quay cu???ng, cho??ng v??ng

Kh??ng th??? b?????c ??i, d??? ng?? do m???t c??n b???ng v?? m???t ?????nh h?????ng kh??ng gian

R???i lo???n th??? gi??c nh?? nh??n m???, hoa m???t, nh???y c???m v???i ??nh s??ng...

R???i lo???n th??nh gi??c nh?? ?? tai

Nh???n th???c ho???c t??m l?? thay ?????i nh?? lo l???ng qu?? m???c, kh?? t???p trung, gi???m kh??? n??ng ch?? ??...')
INSERT [dbo].[Benh] ([maBenh], [tenBenh], [dauHieu]) VALUES (1841005, N'Tr??o Ng?????c D??? D??y Th???c Qu???n', N'C???m gi??c n??ng r??t ??? ng???c (??? n??ng), th?????ng l?? sau khi ??n, t??nh tr???ng n??y c?? th??? n???ng h??n v??o ban ????m

??au ng???c

Kh?? nu???t

Th???c ??n trong d??? d??y b??? chua

C???m gi??c c?? kh???i u ch???n ??? trong c??? h???ng')
INSERT [dbo].[Benh] ([maBenh], [tenBenh], [dauHieu]) VALUES (1841015, N'Ung Th?? Ph???i', N'B??? ho k??o d??i kh??ng kh???i.

C?? c???m gi??c kh?? th???, th??? ng???n, c?? ?????m l???n  m??u.

B??? ??au ng???c.

Sau m???t th???i gian ??? b???nh, ng?????i b???nh c?? th??? b??? g???y s??t, m???t m???i, kh??n gi???ng, kh?? nu???t, th??? kh?? kh??, ??au x????ng th???m ch?? b??? tr??n d???ch m??ng ph???i')
INSERT [dbo].[Benh] ([maBenh], [tenBenh], [dauHieu]) VALUES (1841016, N'Ung Th?? D????ng', N'Thay ?????i m??u s???c da ??? d????ng v???t
??au d????ng v???t ??m ??? ho???c ??au nh??i, ?????c bi???t khi c????ng c???ng ho???c va ch???m
R??? m??u, d???ch m??i h??i ??? ?????u d????ng v???t (hay g???p sau giao h???p).
Bao quy ?????u kh?? di ?????ng
Xu???t hi???n c??c n???t, c??c s???n, m???n c??c ho???c lo??t ??? bao quy ?????u.')
INSERT [dbo].[Benh] ([maBenh], [tenBenh], [dauHieu]) VALUES (1841017, N'X?? V???a ?????ng M???ch', N'Suy gi???m ?? th???c nhanh ch??ng

Nh??n m??? ?????t ?????t

N??i kh??

Y???u li???t m???t n???a ng?????i t??y m???c ?????

Kh??m l??m s??ng c?? th??? nghe th???y ti???ng th???i c???a m???ch c???nh')
INSERT [dbo].[Benh] ([maBenh], [tenBenh], [dauHieu]) VALUES (1841018, N'Ung Th?? C??? T??? Cung', N'??au v??ng h??ng, ??au th???t l??ng hay ph?? hai ch??n.

????i m??u n???u x??m l???n b??ng quang

?????i ti???n ra m??u n???u x??m l???n tr???c tr??ng, ????i khi c??n c?? tri???u ch???ng  c???a b???nh c???nh t???c ru???t.

To??n th??n m???t m???i, ch??n ??n, g???y s??t.')
INSERT [dbo].[Benh] ([maBenh], [tenBenh], [dauHieu]) VALUES (1841019, N'U M??ng N??o', N'??au ?????u: ??au ?????u t??ng d???n, th?????ng b???t ?????u ??? 1 v??? tr?? sau ???? lan ra kh???p ?????u. B???nh nh??n sau ???? kh??ng c??n ????p ???ng v???i thu???c gi???m ??au
?????ng kinh: co gi???t c???c b??? 1 b??? ph???n (1 tay, 1 ch??n) hay to??n th???. Tr?????ng h???p n???ng b???nh nh??n c?? th??? r??i v??o tr???ng th??i ?????ng kinh k??o d??i h??n 30 ph??t.
R???i lo???n ?? th???c: l?? l???n, ng??? g??, thay ?????i t??nh c??ch, h??nh vi
N??n, bu???n n??n: th?????ng l?? n??n v???t, kh??ng li??n quan ?????n b???a ??n, sau n??n kh??ng gi???m ??au ?????u
R???i lo???n th??? gi??c: nh??n ????i???
Y???u chi
?? tai, kh??ng nghe ???????c')
INSERT [dbo].[Benh] ([maBenh], [tenBenh], [dauHieu]) VALUES (1841020, N'Thi???u M??u C?? Tim', N'??au ng???c: thi???u m??u c?? tim bi???u hi???n ch??? y???u b???ng c??n ??au th???t ng???c tr??n l??m s??ng. C??n ??au th???t ng???c do m???ch v??nh ???????c m?? t??? g???m 3 ?????c ??i???m:

C???m gi??c ??au nh?? b??p ngh???t, ??au nh?? th???t hay ???? n???ng, sau x????ng ???c, lan l??n c???m, l??n vai tr??i v?? lan xu???ng c??nh tay tr??i

Xu???t hi???n c?? t??nh ch???t quy lu???t, t??ng l??n sau g???ng s???c, x??c c???m m???nh, g???p l???nh,??? k??o d??i 3-15 ph??t

??au ng???c ????? khi ngh??? ng??i ho???c khi d??ng nitroglycerin')
INSERT [dbo].[Benh] ([maBenh], [tenBenh], [dauHieu]) VALUES (1841021, N'S???i M???t', N'S???i t??i m???t: khi vi??n s???i k???t trong c??? t??i m???t, ng?????i b???nh th?????ng ??au b???ng d??? d???i v??ng h??? s?????n ph???i theo t???ng c??n.

S???i trong gan ho???c ???ng m???t ch???: ng?????i b???nh ??au qu???n v??ng h??? s?????n ph???i, lan ra vai ph???i ho???c sau l??ng, v??ng th?????ng v???.')
INSERT [dbo].[Benh] ([maBenh], [tenBenh], [dauHieu]) VALUES (1841022, N'T??ng Huy???t ??p', N'Gi???i nam

N??? ???? m??n kinh

Ti???n s??? gia ????nh c?? ng?????i t??ng huy???t ??p

B??o ph??, th???a c??n

L???i s???ng ??t ho???t ?????ng th??? l???c

H??t thu???c l??

Ch??? ????? ??n nhi???u mu???i, ??n m???n

Stress v?? c??ng th???ng t??m l??

U???ng nhi???u r?????u, bia

B???nh th???n m???n, ????i th??o ???????ng, h???i ch???ng ng???ng th??? khi ng???')
INSERT [dbo].[Benh] ([maBenh], [tenBenh], [dauHieu]) VALUES (1841023, N'Ph??nh ?????ng M???ch N??o', N'??au ?????u

Gi???m th??? l???c

Li???t d??y th???n kinh s??? (?????c bi???t l?? li???t d??y s??? III g??y l??c, nh??n ????i), do kh???i ph??nh ch??n ??p

Tri???u ch???ng c???a ph??nh ?????ng m???ch n??o v???:

??au ?????u r???t d??? d???i ?????t ng???t

N??n, bu???n n??n

G??y c???ng

C?? th??? suy gi???m ?? th???c, h??n m??

M???t s??? c?? th??? g??y c??c tri???u ch???ng th???n kinh khu tr?? t??? nh??? ?????n li???t n???ng

?????ng kinh th?????ng hi???m g???p

?????t t???: 10-15% b???nh nh??n ch???t tr?????c khi ?????n vi???n')
SET IDENTITY_INSERT [dbo].[Benh] OFF
GO
SET IDENTITY_INSERT [dbo].[BenhNhan] ON 

INSERT [dbo].[BenhNhan] ([maBN], [tenBN], [Ngaysinh], [Diachi], [Gioitinh], [ngheNghiep], [danToc], [QuocTich], [BHYTHieuLucTu], [BHYTHieuLucDen], [SoBHYT], [nguoiThan], [sdtNguoiThan], [Doituong]) VALUES (9000001, N'??inh Cao Th???ng', CAST(N'2002-11-16' AS Date), N'???? N???ng', N'Nam', N'Kinh Doanh', N'Kinh', N'Vi???t Nam', NULL, NULL, N'', N'??inh Cao L??m', N'0326817904', 0)
INSERT [dbo].[BenhNhan] ([maBN], [tenBN], [Ngaysinh], [Diachi], [Gioitinh], [ngheNghiep], [danToc], [QuocTich], [BHYTHieuLucTu], [BHYTHieuLucDen], [SoBHYT], [nguoiThan], [sdtNguoiThan], [Doituong]) VALUES (9000003, N'Tr???n Th??? M??? Chi', CAST(N'1997-11-16' AS Date), N'H?? N???i', N'N??? ', N'C??ng Nh??n', N'Kinh', N'Vi???t Nam', CAST(N'2021-11-01' AS Date), CAST(N'2022-11-01' AS Date), N'CN3010003500099', N'Tr???n V??n Ch??u', N'0326817904', 1)
INSERT [dbo].[BenhNhan] ([maBN], [tenBN], [Ngaysinh], [Diachi], [Gioitinh], [ngheNghiep], [danToc], [QuocTich], [BHYTHieuLucTu], [BHYTHieuLucDen], [SoBHYT], [nguoiThan], [sdtNguoiThan], [Doituong]) VALUES (9000004, N'Nguy???n V??n An', CAST(N'1997-11-16' AS Date), N'Qu???ng Nam', N'Nam', N'Qu??n Nh??n', N'Kinh', N'Vi???t Nam', CAST(N'2021-11-16' AS Date), CAST(N'2023-11-16' AS Date), N'QN9256000005183', N'Nguy???n Thi???n Nh??n', N'0326817904', 1)
INSERT [dbo].[BenhNhan] ([maBN], [tenBN], [Ngaysinh], [Diachi], [Gioitinh], [ngheNghiep], [danToc], [QuocTich], [BHYTHieuLucTu], [BHYTHieuLucDen], [SoBHYT], [nguoiThan], [sdtNguoiThan], [Doituong]) VALUES (9000005, N'Mai Qu???c Anh', CAST(N'2010-11-18' AS Date), N'B??nh D????ng', N'Nam', N'Sinh Vi??n', N'Kinh', N'Vi???t Nam', NULL, NULL, NULL, N'Mai Tao Tr???', N'0326859179', 0)
INSERT [dbo].[BenhNhan] ([maBN], [tenBN], [Ngaysinh], [Diachi], [Gioitinh], [ngheNghiep], [danToc], [QuocTich], [BHYTHieuLucTu], [BHYTHieuLucDen], [SoBHYT], [nguoiThan], [sdtNguoiThan], [Doituong]) VALUES (9000006, N'Tr???n Th??? M??? L???', CAST(N'2010-11-18' AS Date), N'?????ng Nai', N'N??? ', N'Sinh Vi??n', N'Kinh', N'Vi???t Nam', NULL, NULL, NULL, N'Tr???n Thi??n Kim', N'0326454198', 0)
INSERT [dbo].[BenhNhan] ([maBN], [tenBN], [Ngaysinh], [Diachi], [Gioitinh], [ngheNghiep], [danToc], [QuocTich], [BHYTHieuLucTu], [BHYTHieuLucDen], [SoBHYT], [nguoiThan], [sdtNguoiThan], [Doituong]) VALUES (9000007, N'Tr???n V??n An', CAST(N'2010-11-20' AS Date), N'Long An', N'Nam', N'Bu??n B??n', N'Kinh', N'Vi???t Nam', CAST(N'2021-11-19' AS Date), CAST(N'2022-11-19' AS Date), N'BN1234567891597', N'Tr???n Nh??n T??m', N'0326554851', 1)
INSERT [dbo].[BenhNhan] ([maBN], [tenBN], [Ngaysinh], [Diachi], [Gioitinh], [ngheNghiep], [danToc], [QuocTich], [BHYTHieuLucTu], [BHYTHieuLucDen], [SoBHYT], [nguoiThan], [sdtNguoiThan], [Doituong]) VALUES (9000008, N'H??? Ng???c H???i', CAST(N'2002-12-01' AS Date), N'Nam ?????nh', N'Nam', N'Kinh Doanh', N'Kinh', N'Vi???t Nam', NULL, NULL, NULL, N'H??? Duy Dung', N'0325617210', 0)
SET IDENTITY_INSERT [dbo].[BenhNhan] OFF
GO
SET IDENTITY_INSERT [dbo].[BienLai] ON 

INSERT [dbo].[BienLai] ([maBienLai], [MaNV], [MaBN], [ngayTT], [TenKhoanChiPhi], [TienChiPhi], [TrangThai]) VALUES (7000000, N'NV104', 9000003, CAST(N'2021-11-21' AS Date), N'X??t Nghi???m', 53130.0000, N'???? Thanh To??n')
INSERT [dbo].[BienLai] ([maBienLai], [MaNV], [MaBN], [ngayTT], [TenKhoanChiPhi], [TienChiPhi], [TrangThai]) VALUES (7000001, N'NV104', 9000003, CAST(N'2021-11-21' AS Date), N'X??t Nghi???m', 3193780.0000, N'???? Thanh To??n')
INSERT [dbo].[BienLai] ([maBienLai], [MaNV], [MaBN], [ngayTT], [TenKhoanChiPhi], [TienChiPhi], [TrangThai]) VALUES (7000002, N'NV101', 9000003, CAST(N'2021-11-21' AS Date), N'D???ch V???', 5750000.0000, N'???? Thanh To??n')
INSERT [dbo].[BienLai] ([maBienLai], [MaNV], [MaBN], [ngayTT], [TenKhoanChiPhi], [TienChiPhi], [TrangThai]) VALUES (7000003, N'NV101', 9000003, CAST(N'2021-11-22' AS Date), N'Vi???n Ph??', 57500000.0000, N'???? Thanh To??n')
INSERT [dbo].[BienLai] ([maBienLai], [MaNV], [MaBN], [ngayTT], [TenKhoanChiPhi], [TienChiPhi], [TrangThai]) VALUES (7000004, N'NV101', 9000001, CAST(N'2021-11-23' AS Date), N'X??t Nghi???m', 3816390.0000, N'???? Thanh To??n')
INSERT [dbo].[BienLai] ([maBienLai], [MaNV], [MaBN], [ngayTT], [TenKhoanChiPhi], [TienChiPhi], [TrangThai]) VALUES (7000005, N'NV101', 9000001, CAST(N'2021-11-25' AS Date), N'Kh??m B???nh', 575000.0000, N'???? Thanh To??n')
SET IDENTITY_INSERT [dbo].[BienLai] OFF
GO
SET IDENTITY_INSERT [dbo].[DichVu] ON 

INSERT [dbo].[DichVu] ([MaDV], [TenDV], [SoLuong], [Dongia], [Dvtinh]) VALUES (444444, N'D???ch V??? V???n Chuy???n', 50, 2000000.0000, N'VND')
INSERT [dbo].[DichVu] ([MaDV], [TenDV], [SoLuong], [Dongia], [Dvtinh]) VALUES (444445, N'??I???U TR??? THEO Y??U C???U', 10, 5000000.0000, N'VND')
SET IDENTITY_INSERT [dbo].[DichVu] OFF
GO
SET IDENTITY_INSERT [dbo].[DV_BenhNhan_SD] ON 

INSERT [dbo].[DV_BenhNhan_SD] ([sphieuSD], [maBN], [maDV], [ngaySD]) VALUES (800002, 9000001, 444444, CAST(N'2021-11-19' AS Date))
INSERT [dbo].[DV_BenhNhan_SD] ([sphieuSD], [maBN], [maDV], [ngaySD]) VALUES (800003, 9000003, 444445, CAST(N'2021-11-21' AS Date))
SET IDENTITY_INSERT [dbo].[DV_BenhNhan_SD] OFF
GO
SET IDENTITY_INSERT [dbo].[HoSoBenhAn] ON 

INSERT [dbo].[HoSoBenhAn] ([sPhieuHSBA], [VaoVien], [RaVien], [huyetAp], [nhipTim], [Mach], [canNang], [ketQuaDT], [maBenh], [Makhoa], [MaBN], [MaNV], [Cphidtri], [ThoiGianTV], [NguyenNhan]) VALUES (555557, CAST(N'2021-11-19T00:00:00.000' AS DateTime), CAST(N'2021-11-19T00:00:00.000' AS DateTime), N'85/160', 100, 80, 65, N'???? Kh???i', 1841002, 121212, 9000001, N'NV104', 80000000.0000, NULL, NULL)
INSERT [dbo].[HoSoBenhAn] ([sPhieuHSBA], [VaoVien], [RaVien], [huyetAp], [nhipTim], [Mach], [canNang], [ketQuaDT], [maBenh], [Makhoa], [MaBN], [MaNV], [Cphidtri], [ThoiGianTV], [NguyenNhan]) VALUES (555561, CAST(N'2021-11-22T07:18:23.000' AS DateTime), CAST(N'2021-11-27T07:18:23.000' AS DateTime), N'75/160', 100, 80, 40, N'???? Kh???i', 1841018, 121212, 9000003, N'NV101', 500000000.0000, NULL, NULL)
SET IDENTITY_INSERT [dbo].[HoSoBenhAn] OFF
GO
SET IDENTITY_INSERT [dbo].[KhamBenh] ON 

INSERT [dbo].[KhamBenh] ([soPhieuKham], [ngayKham], [trieuChung], [huyetAp], [nhipTim], [Mach], [canNang], [chuanDoan], [yeuCau], [maNV], [maBenh], [maBenhNhan], [CPKham]) VALUES (1841229, CAST(N'2021-11-26' AS Date), N'kt', N'120/130', 80, 80, 65, N'Tr??o Ng?????c D??? D??y Th???c Qu???n', 1, N'NV101', 1841005, 9000001, 10000000.0000)
INSERT [dbo].[KhamBenh] ([soPhieuKham], [ngayKham], [trieuChung], [huyetAp], [nhipTim], [Mach], [canNang], [chuanDoan], [yeuCau], [maNV], [maBenh], [maBenhNhan], [CPKham]) VALUES (1841230, CAST(N'2021-12-02' AS Date), N'??au ?????u', N'60/160', 80, 80, 85, N'Ph??nh ?????ng M???ch N??o', 1, N'NV104', 1841023, 9000001, 1000000.0000)
INSERT [dbo].[KhamBenh] ([soPhieuKham], [ngayKham], [trieuChung], [huyetAp], [nhipTim], [Mach], [canNang], [chuanDoan], [yeuCau], [maNV], [maBenh], [maBenhNhan], [CPKham]) VALUES (1841231, CAST(N'2021-12-03' AS Date), N'ddau dau', N'120/160', 80, 80, 65, N'Ung Th?? D????ng', 1, N'NV104', 1841016, 9000005, 10000000.0000)
SET IDENTITY_INSERT [dbo].[KhamBenh] OFF
GO
SET IDENTITY_INSERT [dbo].[Khoa] ON 

INSERT [dbo].[Khoa] ([Makhoa], [Tenkhoa], [KhuVuc]) VALUES (121212, N'Khoa N???i', N'T???ng 3 - D??y C')
INSERT [dbo].[Khoa] ([Makhoa], [Tenkhoa], [KhuVuc]) VALUES (121213, N'Khoa Ngo???i', N'T???ng 2 - D??y C')
INSERT [dbo].[Khoa] ([Makhoa], [Tenkhoa], [KhuVuc]) VALUES (121214, N'Khoa Ph??? S???n', N'T???ng 1 - D??y C')
INSERT [dbo].[Khoa] ([Makhoa], [Tenkhoa], [KhuVuc]) VALUES (121215, N'Khoa Nhi', N'T???ng 1 - D??y B')
INSERT [dbo].[Khoa] ([Makhoa], [Tenkhoa], [KhuVuc]) VALUES (121216, N'Khoa Truy???n Nhi???m', N'T???ng 2 - D??y B')
INSERT [dbo].[Khoa] ([Makhoa], [Tenkhoa], [KhuVuc]) VALUES (121217, N'Khoa C???p C???u', N'T???ng 1 - D??y A')
INSERT [dbo].[Khoa] ([Makhoa], [Tenkhoa], [KhuVuc]) VALUES (121218, N'Khoa H???i S???c T??ch C???c V?? Ch???ng ?????c', N'T???ng 2 - D??y A')
INSERT [dbo].[Khoa] ([Makhoa], [Tenkhoa], [KhuVuc]) VALUES (121219, N'Khoa V???t L?? Tr??? Li???u - Ph???c H???i Ch???c N??ng', N'T???ng 3 - D??y B')
INSERT [dbo].[Khoa] ([Makhoa], [Tenkhoa], [KhuVuc]) VALUES (121220, N'Khoa Gi???i Ph???u B???nh', N'T???ng 3 - D??y A')
SET IDENTITY_INSERT [dbo].[Khoa] OFF
GO
INSERT [dbo].[NhanVien] ([maNV], [matKhau], [tenNV], [ngaySinh], [diaChi], [soDienThoai], [email], [gioiTinh], [vaiTro], [active]) VALUES (N'NV101', N'E64B78FC3BC91BCBC7DC232BA8EC59E0', N'L?? V??n H??ng', CAST(N'1995-10-03' AS Date), N'H?? T??nh', N'0326817904', N'hoanghonvanoinhoem@gmail.com', N'Nam', N'Qu???n L??', 1)
INSERT [dbo].[NhanVien] ([maNV], [matKhau], [tenNV], [ngaySinh], [diaChi], [soDienThoai], [email], [gioiTinh], [vaiTro], [active]) VALUES (N'NV102', N'E64B78FC3BC91BCBC7DC232BA8EC59E0', N'Nguy???n Th??? B??ch Huy???n', CAST(N'2002-11-16' AS Date), N'h?? t??nh', N'0326817904', N'hoanghonvanoinhoem@gmail.com', N'N??? ', N'Nh??n Vi??n', 1)
INSERT [dbo].[NhanVien] ([maNV], [matKhau], [tenNV], [ngaySinh], [diaChi], [soDienThoai], [email], [gioiTinh], [vaiTro], [active]) VALUES (N'NV103', N'E64B78FC3BC91BCBC7DC232BA8EC59E0', N'B??i Xu??n Ho??ng', CAST(N'1995-11-17' AS Date), N'TP h??? ch?? minh', N'0326917908', N'hoanghonvanoinhoem@gmail.com', N'Nam', N'B??c S???', 1)
INSERT [dbo].[NhanVien] ([maNV], [matKhau], [tenNV], [ngaySinh], [diaChi], [soDienThoai], [email], [gioiTinh], [vaiTro], [active]) VALUES (N'NV104', N'E64B78FC3BC91BCBC7DC232BA8EC59E0', N'Th??i B??nh Thi??n Qu???c', CAST(N'2003-11-17' AS Date), N'Th??i B??nh', N'0326817904', N'hoanghonvanoinhoem@gmail.com', N'Nam', N'Qu???n L??', 1)
INSERT [dbo].[NhanVien] ([maNV], [matKhau], [tenNV], [ngaySinh], [diaChi], [soDienThoai], [email], [gioiTinh], [vaiTro], [active]) VALUES (N'NV105', N'E64B78FC3BC91BCBC7DC232BA8EC59E0', N'Nguy???n V??n Anh', CAST(N'1992-11-17' AS Date), N'Cao B???ng', N'0326891982', N'hoanghonvanoinhoem@gmail.com', N'Nam', N'B??c S???', 1)
INSERT [dbo].[NhanVien] ([maNV], [matKhau], [tenNV], [ngaySinh], [diaChi], [soDienThoai], [email], [gioiTinh], [vaiTro], [active]) VALUES (N'NV106', N'E64B78FC3BC91BCBC7DC232BA8EC59E0', N'Tr???n Th??? M??? Chi', CAST(N'2003-11-24' AS Date), N'long an', N'0326817904', N'hoanghonvanoinhoem@gmail.com', N'Nam', N'Qu???n L??', 1)
INSERT [dbo].[NhanVien] ([maNV], [matKhau], [tenNV], [ngaySinh], [diaChi], [soDienThoai], [email], [gioiTinh], [vaiTro], [active]) VALUES (N'NV107', N'E64B78FC3BC91BCBC7DC232BA8EC59E0', N'Nguy???n Xu??n L??m', CAST(N'2003-12-03' AS Date), N'Nam ?????nh', N'0326817904', N'hoanghonvanoinhoem@gmail.com', N'Nam', N'Qu???n L??', 1)
GO
SET IDENTITY_INSERT [dbo].[PhieuThuoc] ON 

INSERT [dbo].[PhieuThuoc] ([sphieuthuoc], [Mathuoc], [Soluong], [MaNV], [ngayUong], [soLan_Ngay], [BarCode], [maBN], [NgKeDon], [tongTien]) VALUES (5000015, 6000000, N'90 Vi??n', N'NV101', N'6 Vi??n', N'S??ng, Tr??a, T???i', N'14062065851697418895', 9000001, CAST(N'2021-11-26' AS Date), 366570.0000)
INSERT [dbo].[PhieuThuoc] ([sphieuthuoc], [Mathuoc], [Soluong], [MaNV], [ngayUong], [soLan_Ngay], [BarCode], [maBN], [NgKeDon], [tongTien]) VALUES (5000016, 6000002, N'90 Vi??n', N'NV101', N'10 Vi??n', N'S??ng, T???i', N'14062065851697418895', 9000001, CAST(N'2021-11-26' AS Date), 466560.0000)
INSERT [dbo].[PhieuThuoc] ([sphieuthuoc], [Mathuoc], [Soluong], [MaNV], [ngayUong], [soLan_Ngay], [BarCode], [maBN], [NgKeDon], [tongTien]) VALUES (5000017, 6000006, N'10 Chai', N'NV101', N'1 Chai', N'S??ng, T???i', N'14062065851697418895', 9000001, CAST(N'2021-11-26' AS Date), 781100.0000)
INSERT [dbo].[PhieuThuoc] ([sphieuthuoc], [Mathuoc], [Soluong], [MaNV], [ngayUong], [soLan_Ngay], [BarCode], [maBN], [NgKeDon], [tongTien]) VALUES (5000018, 6000002, N'80 Vi??n', N'NV104', N'8 Vi??n', N'S??ng', N'18845814651255492906', 9000001, CAST(N'2021-12-02' AS Date), 414720.0000)
INSERT [dbo].[PhieuThuoc] ([sphieuthuoc], [Mathuoc], [Soluong], [MaNV], [ngayUong], [soLan_Ngay], [BarCode], [maBN], [NgKeDon], [tongTien]) VALUES (5000019, 6000007, N'100 G??i', N'NV104', N'10 G??i', N'S??ng, Tr??a, T???i', N'18845814651255492906', 9000001, CAST(N'2021-12-02' AS Date), 138600.0000)
SET IDENTITY_INSERT [dbo].[PhieuThuoc] OFF
GO
SET IDENTITY_INSERT [dbo].[PhieuXetNghiem] ON 

INSERT [dbo].[PhieuXetNghiem] ([sphieuXN], [MaXN], [maBN], [ngayXN], [kquaXN], [lidoXN], [NguoiYeuCau]) VALUES (1, 3167, 9000003, CAST(N'2021-11-18T00:00:00.000' AS DateTime), N'thi???u oxi trong m??u', N'cho??ng khi ng???i d???y', N'NV104')
INSERT [dbo].[PhieuXetNghiem] ([sphieuXN], [MaXN], [maBN], [ngayXN], [kquaXN], [lidoXN], [NguoiYeuCau]) VALUES (2, 3169, 9000001, CAST(N'2021-11-19T00:00:00.000' AS DateTime), N'gan b??nh th?????ng', N'ki???m tra gan', N'NV104')
INSERT [dbo].[PhieuXetNghiem] ([sphieuXN], [MaXN], [maBN], [ngayXN], [kquaXN], [lidoXN], [NguoiYeuCau]) VALUES (3, 3187, 9000003, CAST(N'2021-11-19T00:00:00.000' AS DateTime), N'c?? kh???i u nh??? trong b??n tr??i', N'??au ?????u, hoa m???t, ch??ng m???t', N'NV104')
INSERT [dbo].[PhieuXetNghiem] ([sphieuXN], [MaXN], [maBN], [ngayXN], [kquaXN], [lidoXN], [NguoiYeuCau]) VALUES (4, 3162, 9000003, CAST(N'2021-11-19T10:16:09.000' AS DateTime), N'ki???m tra', N'ki???m tra', N'NV104')
INSERT [dbo].[PhieuXetNghiem] ([sphieuXN], [MaXN], [maBN], [ngayXN], [kquaXN], [lidoXN], [NguoiYeuCau]) VALUES (5, 3167, 9000003, CAST(N'2021-11-20T19:00:25.000' AS DateTime), N'kiem tra', N'kiem tra', N'NV104')
INSERT [dbo].[PhieuXetNghiem] ([sphieuXN], [MaXN], [maBN], [ngayXN], [kquaXN], [lidoXN], [NguoiYeuCau]) VALUES (6, 3166, 9000003, CAST(N'2021-11-21T13:59:45.000' AS DateTime), N'kt', N'kt', N'NV104')
INSERT [dbo].[PhieuXetNghiem] ([sphieuXN], [MaXN], [maBN], [ngayXN], [kquaXN], [lidoXN], [NguoiYeuCau]) VALUES (7, 3165, 9000003, CAST(N'2021-11-21T15:56:52.000' AS DateTime), N'kt', N'kt', N'NV104')
INSERT [dbo].[PhieuXetNghiem] ([sphieuXN], [MaXN], [maBN], [ngayXN], [kquaXN], [lidoXN], [NguoiYeuCau]) VALUES (8, 3189, 9000003, CAST(N'2021-11-21T15:57:05.000' AS DateTime), N'kt', N'kt', N'NV104')
INSERT [dbo].[PhieuXetNghiem] ([sphieuXN], [MaXN], [maBN], [ngayXN], [kquaXN], [lidoXN], [NguoiYeuCau]) VALUES (9, 3189, 9000003, CAST(N'2021-11-22T07:19:02.000' AS DateTime), N'ct', N'ct', N'NV101')
INSERT [dbo].[PhieuXetNghiem] ([sphieuXN], [MaXN], [maBN], [ngayXN], [kquaXN], [lidoXN], [NguoiYeuCau]) VALUES (10, 3162, 9000003, CAST(N'2021-11-22T18:11:25.000' AS DateTime), N'kt', N'kt', N'NV101')
INSERT [dbo].[PhieuXetNghiem] ([sphieuXN], [MaXN], [maBN], [ngayXN], [kquaXN], [lidoXN], [NguoiYeuCau]) VALUES (11, 3165, 9000003, CAST(N'2021-11-23T19:32:47.000' AS DateTime), N'kt', N'kt', N'NV101')
INSERT [dbo].[PhieuXetNghiem] ([sphieuXN], [MaXN], [maBN], [ngayXN], [kquaXN], [lidoXN], [NguoiYeuCau]) VALUES (12, 3189, 9000001, CAST(N'2021-11-23T19:33:37.000' AS DateTime), N'kt', N'kt', N'NV101')
SET IDENTITY_INSERT [dbo].[PhieuXetNghiem] OFF
GO
SET IDENTITY_INSERT [dbo].[Thuoc] ON 

INSERT [dbo].[Thuoc] ([MaThuoc], [TenBietDuoc], [tenHoatDuoc], [HamLuong], [DVT], [donGia]) VALUES (6000000, N'Glucophage 1000mg 1000mg', N'Metformin hydrochloride', N'1000mg', N'Vi??n', 4073.0000)
INSERT [dbo].[Thuoc] ([MaThuoc], [TenBietDuoc], [tenHoatDuoc], [HamLuong], [DVT], [donGia]) VALUES (6000001, N'Glucophage 850mg 850mg', N'850mg', N'850mg', N'Vi??n', 3786.0000)
INSERT [dbo].[Thuoc] ([MaThuoc], [TenBietDuoc], [tenHoatDuoc], [HamLuong], [DVT], [donGia]) VALUES (6000002, N'Glucovance 500/5mg 500mg;5mg', N'500mg; 5mg', N'500mg; 5mg', N'Vi??n', 5184.0000)
INSERT [dbo].[Thuoc] ([MaThuoc], [TenBietDuoc], [tenHoatDuoc], [HamLuong], [DVT], [donGia]) VALUES (6000003, N'Glucophage 500 mg 500mg', N'500mg', N'500mg', N'Vi??n', 1757.0000)
INSERT [dbo].[Thuoc] ([MaThuoc], [TenBietDuoc], [tenHoatDuoc], [HamLuong], [DVT], [donGia]) VALUES (6000004, N'B??ng y t??? 25g 25g', N'25g', N'25g', N'G??i', 4042.0000)
INSERT [dbo].[Thuoc] ([MaThuoc], [TenBietDuoc], [tenHoatDuoc], [HamLuong], [DVT], [donGia]) VALUES (6000005, N'Hafixim 50 Kids 50mg', N'50mg', N'50mg', N'G??i', 3630.0000)
INSERT [dbo].[Thuoc] ([MaThuoc], [TenBietDuoc], [tenHoatDuoc], [HamLuong], [DVT], [donGia]) VALUES (6000006, N'Halixol 100ml 15mg/5ml 100ml', N'15mg/5ml 100ml', N'15mg/5ml 100ml', N'Chai', 78110.0000)
INSERT [dbo].[Thuoc] ([MaThuoc], [TenBietDuoc], [tenHoatDuoc], [HamLuong], [DVT], [donGia]) VALUES (6000007, N'Hapacol 80mg 80mg', N'80mg', N'80mg', N'G??i', 1386.0000)
INSERT [dbo].[Thuoc] ([MaThuoc], [TenBietDuoc], [tenHoatDuoc], [HamLuong], [DVT], [donGia]) VALUES (6000008, N'Hapacol 150mg 150mg', N'150mg', N'150mg', N'G??i', 1848.0000)
INSERT [dbo].[Thuoc] ([MaThuoc], [TenBietDuoc], [tenHoatDuoc], [HamLuong], [DVT], [donGia]) VALUES (6000009, N'Hapacol 250mg 250mg', N'250mg', N'250mg', N'G??i', 2483.0000)
INSERT [dbo].[Thuoc] ([MaThuoc], [TenBietDuoc], [tenHoatDuoc], [HamLuong], [DVT], [donGia]) VALUES (6000010, N'Hasanbose 50 50mg', N'50mg', N'50mg', N'Vi??n', 862.0000)
SET IDENTITY_INSERT [dbo].[Thuoc] OFF
GO
SET IDENTITY_INSERT [dbo].[XetNghiem] ON 

INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3162, N'Ph???t t??? b??o b???ng liquid-base cytology (Liqui-prep)', N'S??ng l???c ung th?? c??? t??? cung', 470200.0000, 0.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3163, N'HPV Real-time PCR', N'S??ng l???c ung th?? c??? t??? cung', 583500.0000, 379000.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3164, N'H???ng c???u trong ph??n test nhanh', N'S??ng l???c ung th?? ?????i tr???c tr??ng', 87300.0000, 65600.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3165, N'Test th??? C13 t??m Helicobacter pylori', N'Ph??t hi???n nhi???m H.pylori g??y vi??m lo??t d??? d??y', 565000.0000, 0.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3166, N'T???ng ph??n t??ch t??? b??o m??u ngo???i vi (m??y laser)', N'S??ng l???c c??c b???nh l?? v??? m??u nh?? thi???u m??u, ung th?? m??u, suy t???y, gi???m ti???u c???u,...', 82800.0000, 46200.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3167, N'?????nh l?????ng Creatinin m??u', N'Ki???m tra ch???c n??ng th???n, ph??t hi???n b???nh l?? th???n ni???u', 45400.0000, 21500.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3168, N'??o ho???t ????? ALT (GPT)', N'Ki???m tra ch???c n??ng gan, ph??t hi???n b???nh l?? gan', 45400.0000, 21500.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3169, N'??o ho???t ????? AST (GOT)', N'Ki???m tra ch???c n??ng gan, ph??t hi???n b???nh l?? gan', 45400.0000, 21500.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3170, N'?????nh l?????ng Glucose (????i)', N'S??ng l???c b???nh ti???u ???????ng', 40800.0000, 21500.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3171, N'?????nh l?????ng Cholesterol to??n ph???n', N'????nh gi?? r???i lo???n chuy???n h??a m???', 53300.0000, 26900.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3172, N'HBsAg mi???n d???ch t??? ?????ng', N'S??ng l???c vi??m gan si??u vi B', 111100.0000, 74700.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3173, N'?????nh l?????ng HbA1C m??u', N'S??ng l???c b???nh ti???u ???????ng', 155300.0000, 101000.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3174, N'?????nh l?????ng CA125 m??u', N'S??ng l???c ung th?? bu???ng tr???ng', 200600.0000, 0.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3175, N'?????nh l?????ng AFP m??u', N'S??ng l???c ung th?? gan', 154100.0000, 91600.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3176, N'T???ng ph??n t??ch n?????c ti???u (m??y t??? ?????ng)', N'S??ng l???c c??c b???nh l?? c???a h??? ti???t ni???u', 62400.0000, 27400.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3177, N'Ch???p c???t l???p vi t??nh ph???i li???u th???p t???m so??t u (32 d??y)', N'S??ng l???c ung th?? ph???i', 1243000.0000, 0.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3178, N'Ch???p X quang ng???c th???ng s??? h??a (1 phim)', N'????nh gi?? c??c b???nh l?? c???a ph???i: lao ph???i, b???nh ph???i t???c ngh???n m??n t??nh, tr??n d???ch m??ng ph???i, ung th?? ph???i', 146200.0000, 65400.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3179, N'Ch???p Xquang tuy???n v?? s??? h??a (Ch???p nh?? ???nh)', N'S??ng l???c ung th?? v??', 371800.0000, 188400.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3180, N'??o m???t ????? x????ng b???ng ph????ng ph??p DEXA (2 v??? tr??)', N'S??ng l???c lo??ng x????ng', 288100.0000, 0.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3181, N'Si??u ??m Doppler c??c kh???i u trong ??? b???ng', N'????nh gi?? t???ng qu??t c??c c?? quan trong ??? b???ng: gan, m???t, t???y, l??ch, th???n...ph??t hi???n c??c kh???i u ho???c s???i', 145100.0000, 82300.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3182, N'Si??u ??m Doppler tim, van tim', N'????nh gi?? m???c ????? ???nh h?????ng l??n tim c???a b???nh t??ng huy???t ??p, ????i th??o ???????ng v?? ch???n ??o??n nh???ng b???nh van tim, b???nh c?? tim, suy tim...', 375600.0000, 222000.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3183, N'Si??u ??m ??? b???ng (gan m???t, t???y, l??ch, th???n, b??ng quang)', N'????nh gi?? t???ng qu??t c??c c?? quan trong ??? b???ng: gan, m???t, t???y, l??ch, th???n...ph??t hi???n c??c kh???i u ho???c s???i', 95200.0000, 43900.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3184, N'N???i soi th???c qu???n - d??? d??y?? - t?? tr??ng kh??ng sinh thi???t', N'Kh???o s??t ph??t hi???n nh???ng v???t lo??t d??? d??y-t?? tr??ng, l???y m???u m?? t??m vi khu???n HP, n???u c???n c?? th??? sinh thi???t t??m t??? b??o ung th?? v?? c???t polyp ph??ng ng???a ung th??', 484400.0000, 244000.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3185, N'N???i soi tr???c tr??ng ???ng m???m kh??ng sinh thi???t', N'Kh???o s??t ??o???n tr???c tr??ng ph??t hi???n tr?? n???i, r?? h???u m??n, vi??m lo??t tr???c tr??ng, n???u c???n, c???t polyp tr???c tr??ng ph??ng ng???a ung th??', 370000.0000, 189000.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3186, N'Si??u ??m tuy???n v?? 2 b??n', N'Ph??t hi???n c??c kh???i u, nang v??', 107700.0000, 43900.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3187, N'Ch???p c???ng h?????ng t??? s??? n??o (MRI 3.0 tesla)', N'Kh???o s??t c??c kh???i u trong s???', 2421300.0000, 0.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3188, N'Ch???p c???ng h?????ng t??? s??? n??o (MRI 3.0 tesla) c?? ti??m ch???t t????ng ph???n', N'Kh???o s??t c??c kh???i u trong s???', 2858600.0000, 0.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3189, N'Ch???p CT b???ng - ti???u khung th?????ng quy (256 d??y) kh??ng c?? thu???c c???n quang', N'Kh???o s??t c??c kh???i u v??ng b???ng', 3318600.0000, 2731000.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3190, N'N???i soi???????i tr???c tr??ng to??n b??? ???ng m???m kh??ng sinh thi???t', N'Kh???o s??t to??n b??? ?????i tr???c tr??ng ph??t hi???n v???t lo??t, polyp, kh???i u, tr?? n???i, r?? h???u m??n, n???u c???n l???y m???u m?? t??m t??? b??o ung th??,... v?? c???t polyp ph??ng ng???a ung th?? ?????i tr???c tr??ng', 670000.0000, 305000.0000)
SET IDENTITY_INSERT [dbo].[XetNghiem] OFF
GO
ALTER TABLE [dbo].[BienLai] ADD  DEFAULT (getdate()) FOR [ngayTT]
GO
ALTER TABLE [dbo].[BienLai] ADD  DEFAULT (N'???? Thanh To??n') FOR [TrangThai]
GO
ALTER TABLE [dbo].[KhamBenh] ADD  DEFAULT (getdate()) FOR [ngayKham]
GO
ALTER TABLE [dbo].[NhanVien] ADD  DEFAULT ((1)) FOR [active]
GO
ALTER TABLE [dbo].[BienLai]  WITH CHECK ADD  CONSTRAINT [FK_BL_BN] FOREIGN KEY([MaBN])
REFERENCES [dbo].[BenhNhan] ([maBN])
GO
ALTER TABLE [dbo].[BienLai] CHECK CONSTRAINT [FK_BL_BN]
GO
ALTER TABLE [dbo].[BienLai]  WITH CHECK ADD  CONSTRAINT [FK_BL_NV] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([maNV])
GO
ALTER TABLE [dbo].[BienLai] CHECK CONSTRAINT [FK_BL_NV]
GO
ALTER TABLE [dbo].[DV_BenhNhan_SD]  WITH CHECK ADD  CONSTRAINT [FK_DVBNSD_BN] FOREIGN KEY([maBN])
REFERENCES [dbo].[BenhNhan] ([maBN])
GO
ALTER TABLE [dbo].[DV_BenhNhan_SD] CHECK CONSTRAINT [FK_DVBNSD_BN]
GO
ALTER TABLE [dbo].[DV_BenhNhan_SD]  WITH CHECK ADD  CONSTRAINT [FK_DVBNSD_DV] FOREIGN KEY([maDV])
REFERENCES [dbo].[DichVu] ([MaDV])
GO
ALTER TABLE [dbo].[DV_BenhNhan_SD] CHECK CONSTRAINT [FK_DVBNSD_DV]
GO
ALTER TABLE [dbo].[HoSoBenhAn]  WITH CHECK ADD  CONSTRAINT [FK_HSBA_Benh] FOREIGN KEY([maBenh])
REFERENCES [dbo].[Benh] ([maBenh])
GO
ALTER TABLE [dbo].[HoSoBenhAn] CHECK CONSTRAINT [FK_HSBA_Benh]
GO
ALTER TABLE [dbo].[HoSoBenhAn]  WITH CHECK ADD  CONSTRAINT [FK_HSBA_BN] FOREIGN KEY([MaBN])
REFERENCES [dbo].[BenhNhan] ([maBN])
GO
ALTER TABLE [dbo].[HoSoBenhAn] CHECK CONSTRAINT [FK_HSBA_BN]
GO
ALTER TABLE [dbo].[HoSoBenhAn]  WITH CHECK ADD  CONSTRAINT [FK_HSBA_Khoa] FOREIGN KEY([Makhoa])
REFERENCES [dbo].[Khoa] ([Makhoa])
GO
ALTER TABLE [dbo].[HoSoBenhAn] CHECK CONSTRAINT [FK_HSBA_Khoa]
GO
ALTER TABLE [dbo].[HoSoBenhAn]  WITH CHECK ADD  CONSTRAINT [FK_HSBA_NV] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([maNV])
GO
ALTER TABLE [dbo].[HoSoBenhAn] CHECK CONSTRAINT [FK_HSBA_NV]
GO
ALTER TABLE [dbo].[KhamBenh]  WITH CHECK ADD  CONSTRAINT [FK_KB_B] FOREIGN KEY([maBenh])
REFERENCES [dbo].[Benh] ([maBenh])
GO
ALTER TABLE [dbo].[KhamBenh] CHECK CONSTRAINT [FK_KB_B]
GO
ALTER TABLE [dbo].[KhamBenh]  WITH CHECK ADD  CONSTRAINT [FK_KB_BN] FOREIGN KEY([maBenhNhan])
REFERENCES [dbo].[BenhNhan] ([maBN])
GO
ALTER TABLE [dbo].[KhamBenh] CHECK CONSTRAINT [FK_KB_BN]
GO
ALTER TABLE [dbo].[KhamBenh]  WITH CHECK ADD  CONSTRAINT [FK_KB_NV] FOREIGN KEY([maNV])
REFERENCES [dbo].[NhanVien] ([maNV])
GO
ALTER TABLE [dbo].[KhamBenh] CHECK CONSTRAINT [FK_KB_NV]
GO
ALTER TABLE [dbo].[PhieuThuoc]  WITH CHECK ADD  CONSTRAINT [FK_PT_BN] FOREIGN KEY([maBN])
REFERENCES [dbo].[BenhNhan] ([maBN])
GO
ALTER TABLE [dbo].[PhieuThuoc] CHECK CONSTRAINT [FK_PT_BN]
GO
ALTER TABLE [dbo].[PhieuThuoc]  WITH CHECK ADD  CONSTRAINT [FK_PT_NV] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([maNV])
GO
ALTER TABLE [dbo].[PhieuThuoc] CHECK CONSTRAINT [FK_PT_NV]
GO
ALTER TABLE [dbo].[PhieuThuoc]  WITH CHECK ADD  CONSTRAINT [FK_PT_Thuoc] FOREIGN KEY([Mathuoc])
REFERENCES [dbo].[Thuoc] ([MaThuoc])
GO
ALTER TABLE [dbo].[PhieuThuoc] CHECK CONSTRAINT [FK_PT_Thuoc]
GO
ALTER TABLE [dbo].[PhieuXetNghiem]  WITH CHECK ADD  CONSTRAINT [FK_PXN_BN] FOREIGN KEY([maBN])
REFERENCES [dbo].[BenhNhan] ([maBN])
GO
ALTER TABLE [dbo].[PhieuXetNghiem] CHECK CONSTRAINT [FK_PXN_BN]
GO
ALTER TABLE [dbo].[PhieuXetNghiem]  WITH CHECK ADD  CONSTRAINT [FK_PXN_NV] FOREIGN KEY([NguoiYeuCau])
REFERENCES [dbo].[NhanVien] ([maNV])
GO
ALTER TABLE [dbo].[PhieuXetNghiem] CHECK CONSTRAINT [FK_PXN_NV]
GO
ALTER TABLE [dbo].[PhieuXetNghiem]  WITH CHECK ADD  CONSTRAINT [FK_PXN_XN] FOREIGN KEY([MaXN])
REFERENCES [dbo].[XetNghiem] ([MaXN])
GO
ALTER TABLE [dbo].[PhieuXetNghiem] CHECK CONSTRAINT [FK_PXN_XN]
GO
ALTER TABLE [dbo].[PhongBenh]  WITH CHECK ADD  CONSTRAINT [FK_PB_BN] FOREIGN KEY([maBN])
REFERENCES [dbo].[BenhNhan] ([maBN])
GO
ALTER TABLE [dbo].[PhongBenh] CHECK CONSTRAINT [FK_PB_BN]
GO
USE [master]
GO
ALTER DATABASE [QuanLyBenhNhan] SET  READ_WRITE 
GO

