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

INSERT [dbo].[Benh] ([maBenh], [tenBenh], [dauHieu]) VALUES (1841002, N'Rối Loạn Tiền Đình', N'Chóng mặt, quay cuồng, choáng váng

Không thể bước đi, dễ ngã do mất cân bằng và mất định hướng không gian

Rối loạn thị giác như nhìn mờ, hoa mắt, nhạy cảm với ánh sáng...

Rối loạn thính giác như ù tai

Nhận thức hoặc tâm lý thay đổi như lo lắng quá mức, khó tập trung, giảm khả năng chú ý...')
INSERT [dbo].[Benh] ([maBenh], [tenBenh], [dauHieu]) VALUES (1841005, N'Trào Ngược Dạ Dày Thực Quản', N'Cảm giác nóng rát ở ngực (ợ nóng), thường là sau khi ăn, tình trạng này có thể nặng hơn vào ban đêm

Đau ngực

Khó nuốt

Thức ăn trong dạ dày bị chua

Cảm giác có khối u chặn ở trong cổ họng')
INSERT [dbo].[Benh] ([maBenh], [tenBenh], [dauHieu]) VALUES (1841015, N'Ung Thư Phổi', N'Bị ho kéo dài không khỏi.

Có cảm giác khó thở, thở ngắn, có đờm lẫn  máu.

Bị đau ngực.

Sau một thời gian ủ bệnh, người bệnh có thể bị gầy sút, mệt mỏi, khàn giọng, khó nuốt, thở khò khè, đau xương thậm chí bị tràn dịch màng phổi')
INSERT [dbo].[Benh] ([maBenh], [tenBenh], [dauHieu]) VALUES (1841016, N'Ung Thư Dương', N'Thay đổi màu sắc da ở dương vật
Đau dương vật âm ỉ hoặc đau nhói, đặc biệt khi cương cứng hoặc va chạm
Rỉ máu, dịch mùi hôi ở đầu dương vật (hay gặp sau giao hợp).
Bao quy đầu khó di động
Xuất hiện các nốt, các sẩn, mụn cóc hoặc loét ở bao quy đầu.')
INSERT [dbo].[Benh] ([maBenh], [tenBenh], [dauHieu]) VALUES (1841017, N'Xơ Vữa Động Mạch', N'Suy giảm ý thức nhanh chóng

Nhìn mờ đột đột

Nói khó

Yếu liệt một nửa người tùy mức độ

Khám lâm sàng có thể nghe thấy tiếng thổi của mạch cảnh')
INSERT [dbo].[Benh] ([maBenh], [tenBenh], [dauHieu]) VALUES (1841018, N'Ung Thư Cổ Tử Cung', N'Đau vùng hông, đau thắt lưng hay phù hai chân.

Đái máu nếu xâm lấn bàng quang

Đại tiện ra máu nếu xâm lấn trực tràng, đôi khi còn có triệu chứng  của bệnh cảnh tắc ruột.

Toàn thân mệt mỏi, chán ăn, gầy sút.')
INSERT [dbo].[Benh] ([maBenh], [tenBenh], [dauHieu]) VALUES (1841019, N'U Màng Não', N'Đau đầu: đau đầu tăng dần, thường bắt đầu ở 1 vị trí sau đó lan ra khắp đầu. Bệnh nhân sau đó không còn đáp ứng với thuốc giảm đau
Động kinh: co giật cục bộ 1 bộ phận (1 tay, 1 chân) hay toàn thể. Trường hợp nặng bệnh nhân có thể rơi vào trạng thái động kinh kéo dài hơn 30 phút.
Rối loạn ý thức: lú lẫn, ngủ gà, thay đổi tính cách, hành vi
Nôn, buồn nôn: thường là nôn vọt, không liên quan đến bữa ăn, sau nôn không giảm đau đầu
Rối loạn thị giác: nhìn đôi…
Yếu chi
Ù tai, không nghe được')
INSERT [dbo].[Benh] ([maBenh], [tenBenh], [dauHieu]) VALUES (1841020, N'Thiếu Máu Cơ Tim', N'Đau ngực: thiếu máu cơ tim biểu hiện chủ yếu bằng cơn đau thắt ngực trên lâm sàng. Cơn đau thắt ngực do mạch vành được mô tả gồm 3 đặc điểm:

Cảm giác đau như bóp nghẹt, đau như thắt hay đè nặng, sau xương ức, lan lên cằm, lên vai trái và lan xuống cánh tay trái

Xuất hiện có tính chất quy luật, tăng lên sau gắng sức, xúc cảm mạnh, gặp lạnh,… kéo dài 3-15 phút

Đau ngực đỡ khi nghỉ ngơi hoặc khi dùng nitroglycerin')
INSERT [dbo].[Benh] ([maBenh], [tenBenh], [dauHieu]) VALUES (1841021, N'Sỏi Mật', N'Sỏi túi mật: khi viên sỏi kẹt trong cổ túi mật, người bệnh thường đau bụng dữ dội vùng hạ sườn phải theo từng cơn.

Sỏi trong gan hoặc ống mật chủ: người bệnh đau quặn vùng hạ sườn phải, lan ra vai phải hoặc sau lưng, vùng thượng vị.')
INSERT [dbo].[Benh] ([maBenh], [tenBenh], [dauHieu]) VALUES (1841022, N'Tăng Huyết Áp', N'Giới nam

Nữ đã mãn kinh

Tiền sử gia đình có người tăng huyết áp

Béo phì, thừa cân

Lối sống ít hoạt động thể lực

Hút thuốc lá

Chế độ ăn nhiều muối, ăn mặn

Stress và căng thẳng tâm lý

Uống nhiều rượu, bia

Bệnh thận mạn, đái tháo đường, hội chứng ngừng thở khi ngủ')
INSERT [dbo].[Benh] ([maBenh], [tenBenh], [dauHieu]) VALUES (1841023, N'Phình Động Mạch Não', N'Đau đầu

Giảm thị lực

Liệt dây thần kinh sọ (đặc biệt là liệt dây số III gây lác, nhìn đôi), do khối phình chèn ép

Triệu chứng của phình động mạch não vỡ:

Đau đầu rất dữ dội đột ngột

Nôn, buồn nôn

Gáy cứng

Có thể suy giảm ý thức, hôn mê

Một số có thể gây các triệu chứng thần kinh khu trú từ nhẹ đến liệt nặng

Động kinh thường hiếm gặp

Đột tử: 10-15% bệnh nhân chết trước khi đến viện')
SET IDENTITY_INSERT [dbo].[Benh] OFF
GO
SET IDENTITY_INSERT [dbo].[BenhNhan] ON 

INSERT [dbo].[BenhNhan] ([maBN], [tenBN], [Ngaysinh], [Diachi], [Gioitinh], [ngheNghiep], [danToc], [QuocTich], [BHYTHieuLucTu], [BHYTHieuLucDen], [SoBHYT], [nguoiThan], [sdtNguoiThan], [Doituong]) VALUES (9000001, N'Đinh Cao Thắng', CAST(N'2002-11-16' AS Date), N'Đà Nẵng', N'Nam', N'Kinh Doanh', N'Kinh', N'Việt Nam', NULL, NULL, N'', N'Đinh Cao Lâm', N'0326817904', 0)
INSERT [dbo].[BenhNhan] ([maBN], [tenBN], [Ngaysinh], [Diachi], [Gioitinh], [ngheNghiep], [danToc], [QuocTich], [BHYTHieuLucTu], [BHYTHieuLucDen], [SoBHYT], [nguoiThan], [sdtNguoiThan], [Doituong]) VALUES (9000003, N'Trần Thị Mỹ Chi', CAST(N'1997-11-16' AS Date), N'Hà Nội', N'Nữ ', N'Công Nhân', N'Kinh', N'Việt Nam', CAST(N'2021-11-01' AS Date), CAST(N'2022-11-01' AS Date), N'CN3010003500099', N'Trần Văn Châu', N'0326817904', 1)
INSERT [dbo].[BenhNhan] ([maBN], [tenBN], [Ngaysinh], [Diachi], [Gioitinh], [ngheNghiep], [danToc], [QuocTich], [BHYTHieuLucTu], [BHYTHieuLucDen], [SoBHYT], [nguoiThan], [sdtNguoiThan], [Doituong]) VALUES (9000004, N'Nguyễn Văn An', CAST(N'1997-11-16' AS Date), N'Quảng Nam', N'Nam', N'Quân Nhân', N'Kinh', N'Việt Nam', CAST(N'2021-11-16' AS Date), CAST(N'2023-11-16' AS Date), N'QN9256000005183', N'Nguyễn Thiện Nhân', N'0326817904', 1)
INSERT [dbo].[BenhNhan] ([maBN], [tenBN], [Ngaysinh], [Diachi], [Gioitinh], [ngheNghiep], [danToc], [QuocTich], [BHYTHieuLucTu], [BHYTHieuLucDen], [SoBHYT], [nguoiThan], [sdtNguoiThan], [Doituong]) VALUES (9000005, N'Mai Quốc Anh', CAST(N'2010-11-18' AS Date), N'Bình Dương', N'Nam', N'Sinh Viên', N'Kinh', N'Việt Nam', NULL, NULL, NULL, N'Mai Tao Trả', N'0326859179', 0)
INSERT [dbo].[BenhNhan] ([maBN], [tenBN], [Ngaysinh], [Diachi], [Gioitinh], [ngheNghiep], [danToc], [QuocTich], [BHYTHieuLucTu], [BHYTHieuLucDen], [SoBHYT], [nguoiThan], [sdtNguoiThan], [Doituong]) VALUES (9000006, N'Trần Thị Mỹ Lệ', CAST(N'2010-11-18' AS Date), N'Đồng Nai', N'Nữ ', N'Sinh Viên', N'Kinh', N'Việt Nam', NULL, NULL, NULL, N'Trần Thiên Kim', N'0326454198', 0)
INSERT [dbo].[BenhNhan] ([maBN], [tenBN], [Ngaysinh], [Diachi], [Gioitinh], [ngheNghiep], [danToc], [QuocTich], [BHYTHieuLucTu], [BHYTHieuLucDen], [SoBHYT], [nguoiThan], [sdtNguoiThan], [Doituong]) VALUES (9000007, N'Trần Văn An', CAST(N'2010-11-20' AS Date), N'Long An', N'Nam', N'Buôn Bán', N'Kinh', N'Việt Nam', CAST(N'2021-11-19' AS Date), CAST(N'2022-11-19' AS Date), N'BN1234567891597', N'Trần Nhân Tâm', N'0326554851', 1)
INSERT [dbo].[BenhNhan] ([maBN], [tenBN], [Ngaysinh], [Diachi], [Gioitinh], [ngheNghiep], [danToc], [QuocTich], [BHYTHieuLucTu], [BHYTHieuLucDen], [SoBHYT], [nguoiThan], [sdtNguoiThan], [Doituong]) VALUES (9000008, N'Hồ Ngọc Hải', CAST(N'2002-12-01' AS Date), N'Nam Định', N'Nam', N'Kinh Doanh', N'Kinh', N'Việt Nam', NULL, NULL, NULL, N'Hồ Duy Dung', N'0325617210', 0)
SET IDENTITY_INSERT [dbo].[BenhNhan] OFF
GO
SET IDENTITY_INSERT [dbo].[BienLai] ON 

INSERT [dbo].[BienLai] ([maBienLai], [MaNV], [MaBN], [ngayTT], [TenKhoanChiPhi], [TienChiPhi], [TrangThai]) VALUES (7000000, N'NV104', 9000003, CAST(N'2021-11-21' AS Date), N'Xét Nghiệm', 53130.0000, N'Đã Thanh Toán')
INSERT [dbo].[BienLai] ([maBienLai], [MaNV], [MaBN], [ngayTT], [TenKhoanChiPhi], [TienChiPhi], [TrangThai]) VALUES (7000001, N'NV104', 9000003, CAST(N'2021-11-21' AS Date), N'Xét Nghiệm', 3193780.0000, N'Đã Thanh Toán')
INSERT [dbo].[BienLai] ([maBienLai], [MaNV], [MaBN], [ngayTT], [TenKhoanChiPhi], [TienChiPhi], [TrangThai]) VALUES (7000002, N'NV101', 9000003, CAST(N'2021-11-21' AS Date), N'Dịch Vụ', 5750000.0000, N'Đã Thanh Toán')
INSERT [dbo].[BienLai] ([maBienLai], [MaNV], [MaBN], [ngayTT], [TenKhoanChiPhi], [TienChiPhi], [TrangThai]) VALUES (7000003, N'NV101', 9000003, CAST(N'2021-11-22' AS Date), N'Viện Phí', 57500000.0000, N'Đã Thanh Toán')
INSERT [dbo].[BienLai] ([maBienLai], [MaNV], [MaBN], [ngayTT], [TenKhoanChiPhi], [TienChiPhi], [TrangThai]) VALUES (7000004, N'NV101', 9000001, CAST(N'2021-11-23' AS Date), N'Xét Nghiệm', 3816390.0000, N'Đã Thanh Toán')
INSERT [dbo].[BienLai] ([maBienLai], [MaNV], [MaBN], [ngayTT], [TenKhoanChiPhi], [TienChiPhi], [TrangThai]) VALUES (7000005, N'NV101', 9000001, CAST(N'2021-11-25' AS Date), N'Khám Bệnh', 575000.0000, N'Đã Thanh Toán')
SET IDENTITY_INSERT [dbo].[BienLai] OFF
GO
SET IDENTITY_INSERT [dbo].[DichVu] ON 

INSERT [dbo].[DichVu] ([MaDV], [TenDV], [SoLuong], [Dongia], [Dvtinh]) VALUES (444444, N'Dịch Vụ Vận Chuyển', 50, 2000000.0000, N'VND')
INSERT [dbo].[DichVu] ([MaDV], [TenDV], [SoLuong], [Dongia], [Dvtinh]) VALUES (444445, N'ĐIỀU TRỊ THEO YÊU CẦU', 10, 5000000.0000, N'VND')
SET IDENTITY_INSERT [dbo].[DichVu] OFF
GO
SET IDENTITY_INSERT [dbo].[DV_BenhNhan_SD] ON 

INSERT [dbo].[DV_BenhNhan_SD] ([sphieuSD], [maBN], [maDV], [ngaySD]) VALUES (800002, 9000001, 444444, CAST(N'2021-11-19' AS Date))
INSERT [dbo].[DV_BenhNhan_SD] ([sphieuSD], [maBN], [maDV], [ngaySD]) VALUES (800003, 9000003, 444445, CAST(N'2021-11-21' AS Date))
SET IDENTITY_INSERT [dbo].[DV_BenhNhan_SD] OFF
GO
SET IDENTITY_INSERT [dbo].[HoSoBenhAn] ON 

INSERT [dbo].[HoSoBenhAn] ([sPhieuHSBA], [VaoVien], [RaVien], [huyetAp], [nhipTim], [Mach], [canNang], [ketQuaDT], [maBenh], [Makhoa], [MaBN], [MaNV], [Cphidtri], [ThoiGianTV], [NguyenNhan]) VALUES (555557, CAST(N'2021-11-19T00:00:00.000' AS DateTime), CAST(N'2021-11-19T00:00:00.000' AS DateTime), N'85/160', 100, 80, 65, N'Đã Khỏi', 1841002, 121212, 9000001, N'NV104', 80000000.0000, NULL, NULL)
INSERT [dbo].[HoSoBenhAn] ([sPhieuHSBA], [VaoVien], [RaVien], [huyetAp], [nhipTim], [Mach], [canNang], [ketQuaDT], [maBenh], [Makhoa], [MaBN], [MaNV], [Cphidtri], [ThoiGianTV], [NguyenNhan]) VALUES (555561, CAST(N'2021-11-22T07:18:23.000' AS DateTime), CAST(N'2021-11-27T07:18:23.000' AS DateTime), N'75/160', 100, 80, 40, N'Đã Khỏi', 1841018, 121212, 9000003, N'NV101', 500000000.0000, NULL, NULL)
SET IDENTITY_INSERT [dbo].[HoSoBenhAn] OFF
GO
SET IDENTITY_INSERT [dbo].[KhamBenh] ON 

INSERT [dbo].[KhamBenh] ([soPhieuKham], [ngayKham], [trieuChung], [huyetAp], [nhipTim], [Mach], [canNang], [chuanDoan], [yeuCau], [maNV], [maBenh], [maBenhNhan], [CPKham]) VALUES (1841229, CAST(N'2021-11-26' AS Date), N'kt', N'120/130', 80, 80, 65, N'Trào Ngược Dạ Dày Thực Quản', 1, N'NV101', 1841005, 9000001, 10000000.0000)
INSERT [dbo].[KhamBenh] ([soPhieuKham], [ngayKham], [trieuChung], [huyetAp], [nhipTim], [Mach], [canNang], [chuanDoan], [yeuCau], [maNV], [maBenh], [maBenhNhan], [CPKham]) VALUES (1841230, CAST(N'2021-12-02' AS Date), N'đau đầu', N'60/160', 80, 80, 85, N'Phình Động Mạch Não', 1, N'NV104', 1841023, 9000001, 1000000.0000)
INSERT [dbo].[KhamBenh] ([soPhieuKham], [ngayKham], [trieuChung], [huyetAp], [nhipTim], [Mach], [canNang], [chuanDoan], [yeuCau], [maNV], [maBenh], [maBenhNhan], [CPKham]) VALUES (1841231, CAST(N'2021-12-03' AS Date), N'ddau dau', N'120/160', 80, 80, 65, N'Ung Thư Dương', 1, N'NV104', 1841016, 9000005, 10000000.0000)
SET IDENTITY_INSERT [dbo].[KhamBenh] OFF
GO
SET IDENTITY_INSERT [dbo].[Khoa] ON 

INSERT [dbo].[Khoa] ([Makhoa], [Tenkhoa], [KhuVuc]) VALUES (121212, N'Khoa Nội', N'Tầng 3 - Dãy C')
INSERT [dbo].[Khoa] ([Makhoa], [Tenkhoa], [KhuVuc]) VALUES (121213, N'Khoa Ngoại', N'Tầng 2 - Dãy C')
INSERT [dbo].[Khoa] ([Makhoa], [Tenkhoa], [KhuVuc]) VALUES (121214, N'Khoa Phụ Sản', N'Tầng 1 - Dãy C')
INSERT [dbo].[Khoa] ([Makhoa], [Tenkhoa], [KhuVuc]) VALUES (121215, N'Khoa Nhi', N'Tầng 1 - Dãy B')
INSERT [dbo].[Khoa] ([Makhoa], [Tenkhoa], [KhuVuc]) VALUES (121216, N'Khoa Truyền Nhiễm', N'Tầng 2 - Dãy B')
INSERT [dbo].[Khoa] ([Makhoa], [Tenkhoa], [KhuVuc]) VALUES (121217, N'Khoa Cấp Cứu', N'Tầng 1 - Dãy A')
INSERT [dbo].[Khoa] ([Makhoa], [Tenkhoa], [KhuVuc]) VALUES (121218, N'Khoa Hồi Sức Tích Cực Và Chống Độc', N'Tầng 2 - Dãy A')
INSERT [dbo].[Khoa] ([Makhoa], [Tenkhoa], [KhuVuc]) VALUES (121219, N'Khoa Vật Lý Trị Liệu - Phục Hồi Chức Năng', N'Tầng 3 - Dãy B')
INSERT [dbo].[Khoa] ([Makhoa], [Tenkhoa], [KhuVuc]) VALUES (121220, N'Khoa Giải Phẫu Bệnh', N'Tầng 3 - Dãy A')
SET IDENTITY_INSERT [dbo].[Khoa] OFF
GO
INSERT [dbo].[NhanVien] ([maNV], [matKhau], [tenNV], [ngaySinh], [diaChi], [soDienThoai], [email], [gioiTinh], [vaiTro], [active]) VALUES (N'NV101', N'E64B78FC3BC91BCBC7DC232BA8EC59E0', N'Lê Văn Hùng', CAST(N'1995-10-03' AS Date), N'Hà Tĩnh', N'0326817904', N'hoanghonvanoinhoem@gmail.com', N'Nam', N'Quản Lý', 1)
INSERT [dbo].[NhanVien] ([maNV], [matKhau], [tenNV], [ngaySinh], [diaChi], [soDienThoai], [email], [gioiTinh], [vaiTro], [active]) VALUES (N'NV102', N'E64B78FC3BC91BCBC7DC232BA8EC59E0', N'Nguyễn Thị Bích Huyền', CAST(N'2002-11-16' AS Date), N'hà tĩnh', N'0326817904', N'hoanghonvanoinhoem@gmail.com', N'Nữ ', N'Nhân Viên', 1)
INSERT [dbo].[NhanVien] ([maNV], [matKhau], [tenNV], [ngaySinh], [diaChi], [soDienThoai], [email], [gioiTinh], [vaiTro], [active]) VALUES (N'NV103', N'E64B78FC3BC91BCBC7DC232BA8EC59E0', N'Bùi Xuân Hoàng', CAST(N'1995-11-17' AS Date), N'TP hồ chí minh', N'0326917908', N'hoanghonvanoinhoem@gmail.com', N'Nam', N'Bác Sỹ', 1)
INSERT [dbo].[NhanVien] ([maNV], [matKhau], [tenNV], [ngaySinh], [diaChi], [soDienThoai], [email], [gioiTinh], [vaiTro], [active]) VALUES (N'NV104', N'E64B78FC3BC91BCBC7DC232BA8EC59E0', N'Thái Bình Thiên Quốc', CAST(N'2003-11-17' AS Date), N'Thái Bình', N'0326817904', N'hoanghonvanoinhoem@gmail.com', N'Nam', N'Quản Lý', 1)
INSERT [dbo].[NhanVien] ([maNV], [matKhau], [tenNV], [ngaySinh], [diaChi], [soDienThoai], [email], [gioiTinh], [vaiTro], [active]) VALUES (N'NV105', N'E64B78FC3BC91BCBC7DC232BA8EC59E0', N'Nguyễn Văn Anh', CAST(N'1992-11-17' AS Date), N'Cao Bằng', N'0326891982', N'hoanghonvanoinhoem@gmail.com', N'Nam', N'Bác Sỹ', 1)
INSERT [dbo].[NhanVien] ([maNV], [matKhau], [tenNV], [ngaySinh], [diaChi], [soDienThoai], [email], [gioiTinh], [vaiTro], [active]) VALUES (N'NV106', N'E64B78FC3BC91BCBC7DC232BA8EC59E0', N'Trần Thị Mỹ Chi', CAST(N'2003-11-24' AS Date), N'long an', N'0326817904', N'hoanghonvanoinhoem@gmail.com', N'Nam', N'Quản Lý', 1)
INSERT [dbo].[NhanVien] ([maNV], [matKhau], [tenNV], [ngaySinh], [diaChi], [soDienThoai], [email], [gioiTinh], [vaiTro], [active]) VALUES (N'NV107', N'E64B78FC3BC91BCBC7DC232BA8EC59E0', N'Nguyễn Xuân Lâm', CAST(N'2003-12-03' AS Date), N'Nam Định', N'0326817904', N'hoanghonvanoinhoem@gmail.com', N'Nam', N'Quản Lý', 1)
GO
SET IDENTITY_INSERT [dbo].[PhieuThuoc] ON 

INSERT [dbo].[PhieuThuoc] ([sphieuthuoc], [Mathuoc], [Soluong], [MaNV], [ngayUong], [soLan_Ngay], [BarCode], [maBN], [NgKeDon], [tongTien]) VALUES (5000015, 6000000, N'90 Viên', N'NV101', N'6 Viên', N'Sáng, Trưa, Tối', N'14062065851697418895', 9000001, CAST(N'2021-11-26' AS Date), 366570.0000)
INSERT [dbo].[PhieuThuoc] ([sphieuthuoc], [Mathuoc], [Soluong], [MaNV], [ngayUong], [soLan_Ngay], [BarCode], [maBN], [NgKeDon], [tongTien]) VALUES (5000016, 6000002, N'90 Viên', N'NV101', N'10 Viên', N'Sáng, Tối', N'14062065851697418895', 9000001, CAST(N'2021-11-26' AS Date), 466560.0000)
INSERT [dbo].[PhieuThuoc] ([sphieuthuoc], [Mathuoc], [Soluong], [MaNV], [ngayUong], [soLan_Ngay], [BarCode], [maBN], [NgKeDon], [tongTien]) VALUES (5000017, 6000006, N'10 Chai', N'NV101', N'1 Chai', N'Sáng, Tối', N'14062065851697418895', 9000001, CAST(N'2021-11-26' AS Date), 781100.0000)
INSERT [dbo].[PhieuThuoc] ([sphieuthuoc], [Mathuoc], [Soluong], [MaNV], [ngayUong], [soLan_Ngay], [BarCode], [maBN], [NgKeDon], [tongTien]) VALUES (5000018, 6000002, N'80 Viên', N'NV104', N'8 Viên', N'Sáng', N'18845814651255492906', 9000001, CAST(N'2021-12-02' AS Date), 414720.0000)
INSERT [dbo].[PhieuThuoc] ([sphieuthuoc], [Mathuoc], [Soluong], [MaNV], [ngayUong], [soLan_Ngay], [BarCode], [maBN], [NgKeDon], [tongTien]) VALUES (5000019, 6000007, N'100 Gói', N'NV104', N'10 Gói', N'Sáng, Trưa, Tối', N'18845814651255492906', 9000001, CAST(N'2021-12-02' AS Date), 138600.0000)
SET IDENTITY_INSERT [dbo].[PhieuThuoc] OFF
GO
SET IDENTITY_INSERT [dbo].[PhieuXetNghiem] ON 

INSERT [dbo].[PhieuXetNghiem] ([sphieuXN], [MaXN], [maBN], [ngayXN], [kquaXN], [lidoXN], [NguoiYeuCau]) VALUES (1, 3167, 9000003, CAST(N'2021-11-18T00:00:00.000' AS DateTime), N'thiếu oxi trong máu', N'choáng khi ngồi dậy', N'NV104')
INSERT [dbo].[PhieuXetNghiem] ([sphieuXN], [MaXN], [maBN], [ngayXN], [kquaXN], [lidoXN], [NguoiYeuCau]) VALUES (2, 3169, 9000001, CAST(N'2021-11-19T00:00:00.000' AS DateTime), N'gan bình thường', N'kiểm tra gan', N'NV104')
INSERT [dbo].[PhieuXetNghiem] ([sphieuXN], [MaXN], [maBN], [ngayXN], [kquaXN], [lidoXN], [NguoiYeuCau]) VALUES (3, 3187, 9000003, CAST(N'2021-11-19T00:00:00.000' AS DateTime), N'có khối u nhỏ trong bên trái', N'đau đầu, hoa mắt, chóng mặt', N'NV104')
INSERT [dbo].[PhieuXetNghiem] ([sphieuXN], [MaXN], [maBN], [ngayXN], [kquaXN], [lidoXN], [NguoiYeuCau]) VALUES (4, 3162, 9000003, CAST(N'2021-11-19T10:16:09.000' AS DateTime), N'kiểm tra', N'kiểm tra', N'NV104')
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

INSERT [dbo].[Thuoc] ([MaThuoc], [TenBietDuoc], [tenHoatDuoc], [HamLuong], [DVT], [donGia]) VALUES (6000000, N'Glucophage 1000mg 1000mg', N'Metformin hydrochloride', N'1000mg', N'Viên', 4073.0000)
INSERT [dbo].[Thuoc] ([MaThuoc], [TenBietDuoc], [tenHoatDuoc], [HamLuong], [DVT], [donGia]) VALUES (6000001, N'Glucophage 850mg 850mg', N'850mg', N'850mg', N'Viên', 3786.0000)
INSERT [dbo].[Thuoc] ([MaThuoc], [TenBietDuoc], [tenHoatDuoc], [HamLuong], [DVT], [donGia]) VALUES (6000002, N'Glucovance 500/5mg 500mg;5mg', N'500mg; 5mg', N'500mg; 5mg', N'Viên', 5184.0000)
INSERT [dbo].[Thuoc] ([MaThuoc], [TenBietDuoc], [tenHoatDuoc], [HamLuong], [DVT], [donGia]) VALUES (6000003, N'Glucophage 500 mg 500mg', N'500mg', N'500mg', N'Viên', 1757.0000)
INSERT [dbo].[Thuoc] ([MaThuoc], [TenBietDuoc], [tenHoatDuoc], [HamLuong], [DVT], [donGia]) VALUES (6000004, N'Bông y tế 25g 25g', N'25g', N'25g', N'Gói', 4042.0000)
INSERT [dbo].[Thuoc] ([MaThuoc], [TenBietDuoc], [tenHoatDuoc], [HamLuong], [DVT], [donGia]) VALUES (6000005, N'Hafixim 50 Kids 50mg', N'50mg', N'50mg', N'Gói', 3630.0000)
INSERT [dbo].[Thuoc] ([MaThuoc], [TenBietDuoc], [tenHoatDuoc], [HamLuong], [DVT], [donGia]) VALUES (6000006, N'Halixol 100ml 15mg/5ml 100ml', N'15mg/5ml 100ml', N'15mg/5ml 100ml', N'Chai', 78110.0000)
INSERT [dbo].[Thuoc] ([MaThuoc], [TenBietDuoc], [tenHoatDuoc], [HamLuong], [DVT], [donGia]) VALUES (6000007, N'Hapacol 80mg 80mg', N'80mg', N'80mg', N'Gói', 1386.0000)
INSERT [dbo].[Thuoc] ([MaThuoc], [TenBietDuoc], [tenHoatDuoc], [HamLuong], [DVT], [donGia]) VALUES (6000008, N'Hapacol 150mg 150mg', N'150mg', N'150mg', N'Gói', 1848.0000)
INSERT [dbo].[Thuoc] ([MaThuoc], [TenBietDuoc], [tenHoatDuoc], [HamLuong], [DVT], [donGia]) VALUES (6000009, N'Hapacol 250mg 250mg', N'250mg', N'250mg', N'Gói', 2483.0000)
INSERT [dbo].[Thuoc] ([MaThuoc], [TenBietDuoc], [tenHoatDuoc], [HamLuong], [DVT], [donGia]) VALUES (6000010, N'Hasanbose 50 50mg', N'50mg', N'50mg', N'Viên', 862.0000)
SET IDENTITY_INSERT [dbo].[Thuoc] OFF
GO
SET IDENTITY_INSERT [dbo].[XetNghiem] ON 

INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3162, N'Phết tế bào bằng liquid-base cytology (Liqui-prep)', N'Sàng lọc ung thư cổ tử cung', 470200.0000, 0.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3163, N'HPV Real-time PCR', N'Sàng lọc ung thư cổ tử cung', 583500.0000, 379000.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3164, N'Hồng cầu trong phân test nhanh', N'Sàng lọc ung thư đại trực tràng', 87300.0000, 65600.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3165, N'Test thở C13 tìm Helicobacter pylori', N'Phát hiện nhiễm H.pylori gây viêm loét dạ dày', 565000.0000, 0.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3166, N'Tổng phân tích tế bào máu ngoại vi (máy laser)', N'Sàng lọc các bệnh lý về máu như thiếu máu, ung thư máu, suy tủy, giảm tiểu cầu,...', 82800.0000, 46200.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3167, N'Định lượng Creatinin máu', N'Kiểm tra chức năng thận, phát hiện bệnh lý thận niệu', 45400.0000, 21500.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3168, N'Đo hoạt độ ALT (GPT)', N'Kiểm tra chức năng gan, phát hiện bệnh lý gan', 45400.0000, 21500.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3169, N'Đo hoạt độ AST (GOT)', N'Kiểm tra chức năng gan, phát hiện bệnh lý gan', 45400.0000, 21500.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3170, N'Định lượng Glucose (đói)', N'Sàng lọc bệnh tiểu đường', 40800.0000, 21500.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3171, N'Định lượng Cholesterol toàn phần', N'Đánh giá rối loạn chuyển hóa mỡ', 53300.0000, 26900.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3172, N'HBsAg miễn dịch tự động', N'Sàng lọc viêm gan siêu vi B', 111100.0000, 74700.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3173, N'Định lượng HbA1C máu', N'Sàng lọc bệnh tiểu đường', 155300.0000, 101000.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3174, N'Định lượng CA125 máu', N'Sàng lọc ung thư buồng trứng', 200600.0000, 0.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3175, N'Định lượng AFP máu', N'Sàng lọc ung thư gan', 154100.0000, 91600.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3176, N'Tổng phân tích nước tiểu (máy tự động)', N'Sàng lọc các bệnh lý của hệ tiết niệu', 62400.0000, 27400.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3177, N'Chụp cắt lớp vi tính phổi liều thấp tầm soát u (32 dãy)', N'Sàng lọc ung thư phổi', 1243000.0000, 0.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3178, N'Chụp X quang ngực thẳng số hóa (1 phim)', N'Đánh giá các bệnh lý của phổi: lao phổi, bệnh phổi tắc nghẽn mãn tính, tràn dịch màng phổi, ung thư phổi', 146200.0000, 65400.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3179, N'Chụp Xquang tuyến vú số hóa (Chụp nhũ ảnh)', N'Sàng lọc ung thư vú', 371800.0000, 188400.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3180, N'Đo mật độ xương bằng phương pháp DEXA (2 vị trí)', N'Sàng lọc loãng xương', 288100.0000, 0.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3181, N'Siêu âm Doppler các khối u trong ổ bụng', N'Đánh giá tổng quát các cơ quan trong ổ bụng: gan, mật, tụy, lách, thận...phát hiện các khối u hoặc sỏi', 145100.0000, 82300.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3182, N'Siêu âm Doppler tim, van tim', N'Đánh giá mức độ ảnh hưởng lên tim của bệnh tăng huyết áp, đái tháo đường và chẩn đoán những bệnh van tim, bệnh cơ tim, suy tim...', 375600.0000, 222000.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3183, N'Siêu âm ổ bụng (gan mật, tụy, lách, thận, bàng quang)', N'Đánh giá tổng quát các cơ quan trong ổ bụng: gan, mật, tụy, lách, thận...phát hiện các khối u hoặc sỏi', 95200.0000, 43900.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3184, N'Nội soi thực quản - dạ dày  - tá tràng không sinh thiết', N'Khảo sát phát hiện những vết loét dạ dày-tá tràng, lấy mẫu mô tìm vi khuẩn HP, nếu cần có thể sinh thiết tìm tế bào ung thư và cắt polyp phòng ngừa ung thư', 484400.0000, 244000.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3185, N'Nội soi trực tràng ống mềm không sinh thiết', N'Khảo sát đoạn trực tràng phát hiện trĩ nội, rò hậu môn, viêm loét trực tràng, nếu cần, cắt polyp trực tràng phòng ngừa ung thư', 370000.0000, 189000.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3186, N'Siêu âm tuyến vú 2 bên', N'Phát hiện các khối u, nang vú', 107700.0000, 43900.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3187, N'Chụp cộng hưởng từ sọ não (MRI 3.0 tesla)', N'Khảo sát các khối u trong sọ', 2421300.0000, 0.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3188, N'Chụp cộng hưởng từ sọ não (MRI 3.0 tesla) có tiêm chất tương phản', N'Khảo sát các khối u trong sọ', 2858600.0000, 0.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3189, N'Chụp CT bụng - tiểu khung thường quy (256 dãy) không có thuốc cản quang', N'Khảo sát các khối u vùng bụng', 3318600.0000, 2731000.0000)
INSERT [dbo].[XetNghiem] ([MaXN], [TenXN], [MucDich], [DonGiaBIH], [DonGiaBHYT]) VALUES (3190, N'Nội soi đại trực tràng toàn bộ ống mềm không sinh thiết', N'Khảo sát toàn bộ đại trực tràng phát hiện vết loét, polyp, khối u, trĩ nội, rò hậu môn, nếu cần lấy mẫu mô tìm tế bào ung thư,... và cắt polyp phòng ngừa ung thư đại trực tràng', 670000.0000, 305000.0000)
SET IDENTITY_INSERT [dbo].[XetNghiem] OFF
GO
ALTER TABLE [dbo].[BienLai] ADD  DEFAULT (getdate()) FOR [ngayTT]
GO
ALTER TABLE [dbo].[BienLai] ADD  DEFAULT (N'Đã Thanh Toán') FOR [TrangThai]
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

