/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.QLBN.Poly.View;

import com.QLBN.Poly.Entity.Thuoc;
import com.QLBN.Poly.Service.ThuocServiceImp;
import com.QLBN.Poly.Utils.XPrinting;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.util.FileResolver;

/**
 *
 * @author A
 */
public class NewJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        initComponents();
        isert();
    }
//    void in() {
//        List<XetNghiem> lit = new ArrayList<>();
//
//        lit.add(new XetNghiem("Phết tế bào bằng liquid-base cytology (Liqui-prep)", "Sàng lọc ung thư cổ tử cung", 470200, 0));
//        lit.add(new XetNghiem("HPV Real-time PCR", "Sàng lọc ung thư cổ tử cung", 583500, 379000));
//        lit.add(new XetNghiem("Hồng cầu trong phân test nhanh", "Sàng lọc ung thư đại trực tràng", 87300, 65600));
//        lit.add(new XetNghiem("Test thở C13 tìm Helicobacter pylori", "Phát hiện nhiễm H.pylori gây viêm loét dạ dày", 565000, 0));
//        lit.add(new XetNghiem("Tổng phân tích tế bào máu ngoại vi (máy laser)", "Sàng lọc các bệnh lý về máu như thiếu máu, ung thư máu, suy tủy, giảm tiểu cầu,...", 82800, 46200));
//        lit.add(new XetNghiem("Định lượng Creatinin máu", "Kiểm tra chức năng thận, phát hiện bệnh lý thận niệu", 45400, 21500));
//        lit.add(new XetNghiem("Đo hoạt độ ALT (GPT)", "Kiểm tra chức năng gan, phát hiện bệnh lý gan", 45400, 21500));
//        lit.add(new XetNghiem("Đo hoạt độ AST (GOT)", "Kiểm tra chức năng gan, phát hiện bệnh lý gan", 45400, 21500));
//        lit.add(new XetNghiem("Định lượng Glucose (đói)", "Sàng lọc bệnh tiểu đường", 40800, 21500));
//        lit.add(new XetNghiem("Định lượng Cholesterol toàn phần", "Đánh giá rối loạn chuyển hóa mỡ", 53300, 26900));
//        lit.add(new XetNghiem("HBsAg miễn dịch tự động", "Sàng lọc viêm gan siêu vi B", 111100, 74700));
//        lit.add(new XetNghiem("Định lượng HbA1C máu", "Sàng lọc bệnh tiểu đường", 155300, 101000));
//        lit.add(new XetNghiem("Định lượng CA125 máu", "Sàng lọc ung thư buồng trứng", 200600, 0));
//        lit.add(new XetNghiem("Định lượng AFP máu", "Sàng lọc ung thư gan", 154100, 91600));
//        lit.add(new XetNghiem("Tổng phân tích nước tiểu (máy tự động)", "Sàng lọc các bệnh lý của hệ tiết niệu", 62400, 27400));
//        lit.add(new XetNghiem("Chụp cắt lớp vi tính phổi liều thấp tầm soát u (32 dãy)", "Sàng lọc ung thư phổi", 1243000, 0));
//        lit.add(new XetNghiem("Chụp X quang ngực thẳng số hóa (1 phim)", "Đánh giá các bệnh lý của phổi: lao phổi, bệnh phổi tắc nghẽn mãn tính, tràn dịch màng phổi, ung thư phổi", 146200, 65400));
//        lit.add(new XetNghiem("Chụp Xquang tuyến vú số hóa (Chụp nhũ ảnh)", "Sàng lọc ung thư vú", 371800, 188400));
//        lit.add(new XetNghiem("Đo mật độ xương bằng phương pháp DEXA (2 vị trí)", "Sàng lọc loãng xương", 288100, 0));
//        lit.add(new XetNghiem("Siêu âm Doppler các khối u trong ổ bụng", "Đánh giá tổng quát các cơ quan trong ổ bụng: gan, mật, tụy, lách, thận...phát hiện các khối u hoặc sỏi", 145100, 82300));
//        lit.add(new XetNghiem("Siêu âm Doppler tim, van tim", "Đánh giá mức độ ảnh hưởng lên tim của bệnh tăng huyết áp, đái tháo đường và chẩn đoán những bệnh van tim, bệnh cơ tim, suy tim...", 375600, 222000));
//        lit.add(new XetNghiem("Siêu âm ổ bụng (gan mật, tụy, lách, thận, bàng quang)", "Đánh giá tổng quát các cơ quan trong ổ bụng: gan, mật, tụy, lách, thận...phát hiện các khối u hoặc sỏi", 95200, 43900));
//        lit.add(new XetNghiem("Nội soi thực quản - dạ dày  - tá tràng không sinh thiết", "Khảo sát phát hiện những vết loét dạ dày-tá tràng, lấy mẫu mô tìm vi khuẩn HP, nếu cần có thể sinh thiết tìm tế bào ung thư và cắt polyp phòng ngừa ung thư", 484300, 244000));
//        lit.add(new XetNghiem("Nội soi trực tràng ống mềm không sinh thiết", "Khảo sát đoạn trực tràng phát hiện trĩ nội, rò hậu môn, viêm loét trực tràng, nếu cần, cắt polyp trực tràng phòng ngừa ung thư", 370000, 189000));
//        lit.add(new XetNghiem("Siêu âm tuyến vú 2 bên", "Phát hiện các khối u, nang vú", 107700, 43900));
//
//        lit.add(new XetNghiem("Chụp cộng hưởng từ sọ não (MRI 3.0 tesla)", "Khảo sát các khối u trong sọ", 2421300, 0));
//        lit.add(new XetNghiem("Chụp cộng hưởng từ sọ não (MRI 3.0 tesla) có tiêm chất tương phản", "Khảo sát các khối u trong sọ", 2858600, 0));
//        lit.add(new XetNghiem("Chụp CT bụng - tiểu khung thường quy (256 dãy) không có thuốc cản quang", "Khảo sát các khối u vùng bụng", 3318600, 2731000));
//        lit.add(new XetNghiem("Nội soi đại trực tràng toàn bộ ống mềm không sinh thiết", "Khảo sát toàn bộ đại trực tràng phát hiện vết loét, polyp, khối u, trĩ nội, rò hậu môn, nếu cần lấy mẫu mô tìm tế bào ung thư,... và cắt polyp phòng ngừa ung thư đại trực tràng", 670000, 305000));
//        for (XetNghiem x : lit) {
//            xnsi.insert(x);
//        }
//
//    }
    void isert() {
        List<Thuoc> list = new ArrayList<>();

//        list.add(new Thuoc("Glucophage 850mg 850mg", "Metformin hydrochloride", "850mg", "Viên", 3786));
//        list.add(new Thuoc("Glucovance 500/5mg 500mg;5mg", "Metformine; Glibenclamid", "500mg; 5mg", "Viên", 5184));
//        list.add(new Thuoc("Glucophage 500 mg 500mg", "Metformin hydrochloride", "500mg", "Viên", 1757));
//        list.add(new Thuoc("Bông y tế 25g 25g", "Gòn y tế 25g", "25g", "Gói", 4042));
//        list.add(new Thuoc("Hafixim 50 Kids 50mg", "Cefixim", "50mg", "Gói", 3630));
//        list.add(new Thuoc("Halixol 100ml 15mg/5ml 100ml", "Ambroxol HCL", "15mg/5ml 100ml", "Chai", 78110));
//        list.add(new Thuoc("Hapacol 80mg 80mg", "Paracetamol", "80mg", "Gói", 1386));
//        list.add(new Thuoc("Hapacol 150mg 150mg", "Paracetamol", "150mg", "Gói", 1848));
//        list.add(new Thuoc("Hapacol 250mg 250mg", "Paracetamol", "250mg", "Gói", 2483));
//        list.add(new Thuoc("Hasanbose 50 50mg", "Acarbose", "50mg", "Viên", 862));

        for (Thuoc t : list) {
            new ThuocServiceImp().insert(t);
        }
//list.add(new Thuoc("Hemoq Mom 150mg; 25mcg; 1mg	Fe;" ,"Cyanocobalamin; Acid folic	150mg; 25mcg; 1mg	Viên	6527
//list.add(new Thuoc("Hemafort 60ml 300mg;7.98mg;4.2mg"	,"Sắt (dưới dạng Fe (II) gluconat); Mangan (dưới dạng Mangan gluconat dihydrat); Đồng (dưới dạng đồng gluconat)	300mg;7.98mg;4.2mg	Chai	40660
//list.add(new Thuoc("Herbesser 30mg 30mg"	,"Diltiazem HCl	30mg	Viên	1597
//list.add(new Thuoc("Hidrasec 30mg 30mg"	,"Racecadotril	30mg	Gói	5728
//list.add(new Thuoc("Hidrasec 30mg 30mg"	,"Racecadotril	30mg	Gói	6491
//list.add(new Thuoc("Hidrasec 10mg 10mg"	,"Racecadotril	10mg	Gói	5759
//list.add(new Thuoc("Hút mũi	,"Hút mũi"	(blank)	Cái	13482
//list.add(new Thuoc("Imdur 30mg 30mg"	,"Isosorbide 5-mononitrat	30mg	Viên	3569
//list.add(new Thuoc("Imdur 60 60mg"	,"Isosorbid-5- mononitrat	60mg	Viên	6883
//list.add(new Thuoc("Imodium 2mg 2mg"	,"Loperamid	2mg	Viên	2776
//list.add(new Thuoc("Indocollyre 0,1% 5ml 0,1% 5ml"	Indomethacin	0,1% 5ml	Lọ	72758
//list.add(new Thuoc("Insulatard Flexpen 100IU/ml 300IU/3ml"	,"Insulin human tác dụng kéo dài	300IU/3ml	Bút	161698
//list.add(new Thuoc("Insulatard Flexpen 100IU/ml 300IU/3ml"	,"Insulin human tác dụng kéo dài	300IU/3ml	Bút	171499
//list.add(new Thuoc("Isosorbid 10mg 10mg"	,"Isosorbid dinitrat	10mg	Viên	304
//list.add(new Thuoc("Kali Clorid 500mg 500mg"	,"Kaliclorua	500mg	Viên	724
//list.add(new Thuoc("Kalxetin 20mg 20mg"	,"Fluoxetin	20mg	Viên	1697
//list.add(new Thuoc("Kalxetin 20mg 20mg"	,"Fluoxetin	20mg	Viên	1704
//list.add(new Thuoc("Kalxetin 20mg 20mg"	,"Fluoxetin	20mg	Viên	1705
//list.add(new Thuoc("Katrypsin 21 microkatal 21 microkatal"	,"Alpha chymotrypsin	21 microkatal	Viên	140
//list.add(new Thuoc("Ketosteril"	,"Acid amin	(blank)	Viên	14493
//list.add(new Thuoc("Kidviton 60ml 1200mg;520mg;800mg;12mg;14mg;24mg;2400UI;60mg;80mg;40mg"	,"Lysin HCl; Calci; Phospho; Vitamin B1; Vitamin B2; Vitamin B6; Vitamin D3; Vitamin E; Nicotinamid; D-panthenol	1200mg;520mg;800mg;12mg;14mg;24mg;2400UI;60mg;80mg;40mg	Chai	37450
//list.add(new Thuoc("Klamentin 1g 875/125mg"	,"Amoxicillin 875mg; Clavulanic acid 125mg	875/125mg	Viên	10673
//list.add(new Thuoc("KLENZIT-C gel 15g 15g"	,"Adapalene; Clindamycin	15g	Hộp	126000
//list.add(new Thuoc("Klenzit- MS 15g 15g	,"Adapalene"	15g	Hộp	85600
//list.add(new Thuoc("Laevolac 10g/15ml"	,"Lactulose	10g/15ml	Gói	2956
//list.add(new Thuoc("Lamone 100 100mg"	,"Lamivudin	100mg	Viên	5499
//list.add(new Thuoc("Leanmax Adult 400g 400g"	Sữa dinh dưỡng cho người suy nhược	400g	Lon	273000
//list.add(new Thuoc("Lertazin 5mg 5mg"	Levocetirizine	5mg	Viên	6628
//list.add(new Thuoc("Levothyrox 100mcg 100mcg"	Levothyroxin 100mcg	100mcg	Viên	1617
//list.add(new Thuoc("Levothyrox 50mcg 50mcg"	Levothyroxin	50mcg	Viên	1107
//list.add(new Thuoc("LEVODHG 500 500mg"	Levofloxacin	500mg	Viên	2079
//list.add(new Thuoc("Lipistad 10mg 10mg"	Atorvastatin	10mg	Viên	620
//list.add(new Thuoc("Lipitor 10mg 10mg"	Atorvastatin	10mg	Viên	17056
//list.add(new Thuoc("Lisonorm 5/10mg 5mg;10mg"	Amlodipin; Linsinopril	5mg;10mg	Viên	4620
//list.add(new Thuoc("LIVACT Granules 4.15g 952mg;1904mg;1144mg"	L-Isoleucine; L-leucine; L-Valine	952mg;1904mg;1144mg	Gói	41730
//list.add(new Thuoc("Losartan Stada 25mg 25mg"	Losartan kali	25mg	Viên	1650
//list.add(new Thuoc("Lostad T25 25mg"	Losartan	25mg	Viên	1650
//list.add(new Thuoc("Lyrica 75mg 75mg"	Pregabalin	75mg	Viên	18922
//list.add(new Thuoc("Lyrica 150mg 150mg"	Pregabalin	150mg	Viên	28270
//list.add(new Thuoc("Magne B6 Corbière 5mg; 470mg 470mg; 5mg"	Magne; Vit.B6	470mg; 5mg	Viên	1700
//list.add(new Thuoc("Magne B6 Corbière 5mg; 470mg 470mg; 5mg"	Magne; Vit.B6	470mg; 5mg	Viên	1932
//list.add(new Thuoc("Máy đo huyết áp bắp tay Omron HEM-7121"	Máy đo huyết áp bắp tay	(blank)	Cái	1030200
//list.add(new Thuoc("Meconer 500mcg 500mcg	Mecobalamin"	500mcg	Viên	724
//list.add(new Thuoc("Medrol 16mg 16mg	Methylprednisolone"	16mg	Viên	4039
//list.add(new Thuoc("Medoral 250ml 0,2% 250ml	Chlorhexidin"	0,2% 250ml	Chai	96299
//list.add(new Thuoc("Medphadion 5ml 20mg/ml 5ml	Vitamin K1"	20mg/ml 5ml	Lọ	204750
//list.add(new Thuoc("MENISON 4mg 4mg	Methyl prednisolon"	4mg	Viên	1023
//list.add(new Thuoc("Mepoly 10ml 35mg; 10mg; 100.000IU 10ml	Neomycin; Dexamethason; Polymycin B	35mg; 10mg; 100.000IU 10ml	Lọ	39589
//list.add(new Thuoc("MERIKA FORT 200 triệu (CFU) + 1 tỷ (CFU)	Bacillus subtilis + Lactobacillus acidophilus	200 triệu (CFU) + 1 tỷ (CFU)	Gói	3079
//list.add(new Thuoc("Meseca Fort 60 liều 137mcg;50mcg/0,137ml 60 liều	Fluticason propionat + Azelastine Hydrocloride	137mcg;50mcg/0,137ml 60 liều	Lọ	126000
//list.add(new Thuoc("Methycobal 500mcg 500mcg	Mecobalamin	500mcg	Viên	3857
//list.add(new Thuoc("Metformin Denk 850 850mg	Metformin	850mg	Viên	1020
//list.add(new Thuoc("Metpredni 4 A.T 4mg	Methyl prednisolon	4mg	Viên	264
//list.add(new Thuoc("Metrima 100mg 100mg	Clotrimazol	100mg	Viên	748
//list.add(new Thuoc("Miacalcic 50IU/ml 50IU/ml 1ml	Calcitonin cá hồi tổng hợp	50IU/ml 1ml	Ống	94021
//list.add(new Thuoc("Micardis Plus 40/12,5mg 40mg; 12,5mg	Telmisartan; Hydrochlorothiazide	40mg; 12,5mg	Viên	11114
//list.add(new Thuoc("Micardis 80mg 80mg	Telmisartan	80mg	Viên	15887
//list.add(new Thuoc("Micardis 40mg 40mg	Telmisartan	40mg	Viên	10520
//list.add(new Thuoc("Milurit 300mg 300mg	Allopurinol	300mg	Viên	2123
//list.add(new Thuoc("Milurit 300mg 300mg	Allopurinol	300mg	Viên	2750
//list.add(new Thuoc("Mildocap 25 mg 25 mg	Captopril	25 mg	Viên	781
//list.add(new Thuoc("Mimosa 49,5mg; 180mg; 600mg; 600mg;	Cao bình vôi; cao lá sen; cao lạc tiên; cao lá vông nem; cao trinh nữ	49,5mg; 180mg; 600mg; 600mg;	Viên	1386
//list.add(new Thuoc("Minirin 0.1mg 0.1mg	Desmopressin acetate	0.1mg	Viên	23683
//list.add(new Thuoc("Misoprostol Stada 200mcg 200mcg	Misoprostol	200mcg	Viên	4950
//list.add(new Thuoc("Mixtard 30 Flexpen 100IU/ml 3ml 100IU/ml 3ml	Insulin human hỗn hợp 30/70	100IU/ml 3ml	Bút	147000
//list.add(new Thuoc("Mobic 15mg 15mg	Meloxicam	15mg	Viên	17323
//list.add(new Thuoc("Mobic 7,5mg 7,5mg	Meloxicam	7,5mg	Viên	9760
//list.add(new Thuoc("Mobimed 15 15mg	Meloxicam	15mg	Viên	1052
//list.add(new Thuoc("Motilium M 10mg 10mg	Domperidon	10mg	Viên	1994
//list.add(new Thuoc("Motilium M 10mg 10mg	Domperidon	10mg	Viên	2359
//list.add(new Thuoc("Motilium 60ml 60ml	Domperidon	60ml	Chai	44510
//Mydocalm 150mg 150mg	Tolperison	150mg	Viên	2263
//Myderison 50mg 50mg	Tolperison	50mg	Viên	1452
//Myonal 50mg 50mg	Eperison	50mg	Viên	3757
//Nạng gỗ	Nạng gỗ	(blank)	Cặp	73027
//Natri clorid 0,9% 10ml 0,9% 10ml	Natri clorid	0,9% 10ml	Lọ	2956
//Natri clorid 0,9% 500ml rửa 0.9% 500ml	Natri clorid	0.9% 500ml	Chai	7639
//Nebilet 5mg 5mg	Nebivolol	5mg	Viên	8132
//Negacef 500 500 mg	Cefuroxim	500 mg	Viên	8228
//Negacef 250 250mg	Cefuroxim	250mg	Viên	3740
//NeilMed Sinus Rinse Kit 60 sachets 240ml	Dụng cụ rửa mũi	240ml	Hộp	415800
//NeilMed SR Pediatric starter Kit 30 sachets 120ml	Dụng cụ rửa mũi trẻ em	120ml	Hộp	351750
//NeilMed Sinus Rinse 120 premixed sachets	Hỗn hợp rửa mũi (người lớn)	(blank)	Gói	4803
//NeilMed SR Pediatric 120 premixed sachets	Hỗn hợp rửa mũi (trẻ em)	(blank)	Gói	4565
//NeilMed SR Pediatric 120 premixed sachets	Hỗn hợp rửa mũi (trẻ em)	(blank)	Gói	4803
//NeilMed NasaMist Saline spray Isotonic	Dung dịch rửa mũi đẳng trương	(blank)	Chai	104860
//NeilMed NasoGEL for Dry Noses 30ml 30ml	Gel xịt chống khô mũi	30ml	Chai	252000
//Neopeptin Drops 15ml 20mg; 10mg; 2mg; 2mg; 2mg	Alpha-amylase; Papain; Dill oil; Anise oil; Caraway oil	20mg; 10mg; 2mg; 2mg; 2mg	Chai	47614
//Neopeptin Drops 15ml 20mg; 10mg; 2mg; 2mg; 2mg	Alpha-amylase; Papain; Dill oil; Anise oil; Caraway oil	20mg; 10mg; 2mg; 2mg; 2mg	Chai	49151
//Neo-codion 25mg; 100mg; 20mg	Codein camphosulfonat; Sulfoguaiacol; cao mềm Gridelia	25mg; 100mg; 20mg	Viên	3943
//Neo-Tergynan 500mg; 65.000UI; 100.000UI	Metronidazol; Neomycin; Nystatin	500mg; 65.000UI; 100.000UI	Viên đặt	12626
//Nesteloc 20 20mg	Esomeprazol	20mg	Viên	1958
//Neurontin 300mg 300mg	Gabapentin	300mg	Viên	12107
//Neutrivit 5000 50mg; 250mg; 5000mcg	Vitamin B1; B6; B12	50mg; 250mg; 5000mcg	Lọ	11796
//Nexium Mups 20mg 20mg	Esomeprazol	20mg	Viên	24027
//Nexium Mups 40mg 40mg	Esomeprazol	40mg	Viên	24027
//Nexium sac 10mg 10mg	Esomeprazol	10mg	Gói	24027
//NextG Cal 500mg; 120mg; 2mcg; 8mcg	Hydroxyapatite; Calcium; Vit D3; Vit K1	500mg; 120mg; 2mcg; 8mcg	Viên	5617
//Nghiền thuốc	Nghiền thuốc	(blank)	Cái	20223
//Nhiệt kế Thủy ngân	Nhiệt kế	(blank)	Cái	13482
//Nhiệt kế Thủy ngân	Nhiệt kế	(blank)	Cái	16050
//Nifedipin T20 Retard 20mg 20mg	Nifedipin	20mg	Viên	690
//Nivalin 5mg 5mg	Galantamin	5mg	Viên	22470
//Nizoral 2% 5g 2% 5g	Ketoconazole	2% 5g	Tube	20548
//No-Spa forte 80 mg 80mg	Drotaverine	80mg	Viên	1416
//Nootropil 800mg 800mg	Piracetam	800mg	Viên	3869
//Nuu Tragen-10ml 100mg; 200mg; 320IU; 1100IU; 290IU; 210IU; 11IU; 100mg; 200mg; 5mg; 500IU; 1mg; 1mg; 2mg; 100mg; 1mg; 1.5mg - 10ml	Arginine aspartate, Lysine, Protease, Amylase, Lipase, Lactase,Cellulase, Pepsin, Men bia tươi, Kẽm Gluconat, Vitamin A, Vitamin B1, Vitamin B2, Coenzym Vitamin B12, FOS, Vitamin B6, Vitamin PP	100mg; 200mg; 320IU; 1100IU; 290IU; 210IU; 11IU; 100mg; 200mg; 5mg; 500IU; 1mg; 1mg; 2mg; 100mg; 1mg; 1.5mg - 10ml	Ống	9630
//Nystatin 25.000UI 25.000UI	Nystatin	25.000UI	Gói	1093
//Nystatin 25.000UI 25.000UI	Nystatin	25.000UI	Gói	1127
//Obimin 3000UI; 400UI; 100mg; 10mg; 2,5mg; 15mg; 4mcg; 20mg; 7,5mg; 250mg; 1000mcg; 90mg; 100mcg; 100mcg	Vit A; Vit D; Vit C; Vit B1; Vit B2; Vit B6; Vit B12; Vit B3; Ca pantothenat; Ca lactat; Folic; Fe fumarat; Cu; Iod	3000UI; 400UI; 100mg; 10mg; 2,5mg; 15mg; 4mcg; 20mg; 7,5mg; 250mg; 1000mcg; 90mg; 100mcg; 100mcg	Viên	2328
//Ofloxacin Tai 300mg 5ml	Ofloxacin	5ml	Lọ	8067
//Olanstad stada 10mg 10mg	Olanzapin	10mg	Viên	2200
//Omnifix 3ml	Bơm tiêm 3ml không kim	(blank)	Cái	1778
//Omnifix 5ml	Bơm tiêm 5ml không kim	(blank)	Cái	3903
//Omnifix 10ml	Bơm tiêm 10ml không kim	(blank)	Cái	6052
//Orelox 100mg 100mg	Cefpodoxime	100mg	Viên	16264
//Oresol 20g; 3,5g; 1,5g; 2,9g	Glucose; NaCl; KCl; Na Citrate	20g; 3,5g; 1,5g; 2,9g	Gói	2079
//Oresol new 2,7g; 0,52g; 0,58g; 0,3g	Glucose khan; NaCl; Na citrat; KCl	2,7g; 0,52g; 0,58g; 0,3g	Gói	1501
//Orgametril 5mg 5mg	Lynestrenol 5mg	5mg	Viên	2277
//Otifar 8ml 80mg; 4mg 8ml	Chloramphenicol; Dexamethason	80mg; 4mg 8ml	Lọ	4943
//Oxy Già 3% 60ml	Oxy già	3% 60ml	Chai	1732
//Panadol 500mg 500mg	Paracetamol	500mg	Viên	1006
//Panadol Extra 500mg; 65mg	Paracetamol 500mg; Cafein 65mg	500mg; 65mg	Viên	1219
//Panfor SR 500mg 500mg	Metformin	500mg	Viên	1320
//Panalganeffer Codein 500mg+ 30mg	Paracetamol + codein phosphat	500mg+ 30mg	Viên sủi	1130
//Panalganeffer 500 500mg	Paracetamol	500mg	Viên sủi	1085
//Panangin 140mg; 158mg 140mg; 158mg	Magnesi aspartat; Kali aspartat	140mg; 158mg	Viên	1709
//Pantoloc 40mg 40mg	Pantoprazol	40mg	Viên	21180
//Pantostad 40mg 40mg	Pantoprazol	40mg	Viên	2420
//Partamol Stada 325mg 325mg	Paracetamol	325mg	Viên	224
//Para - OPC 150 mg 150 mg	Paracetamol	150 mg	Gói	1444
//PAXIRASOL 8 mg 8 mg	Bromhexin	8 mg	Viên	805
//Pentasa 500mg 500mg	Mesalazin	500mg	Viên	12705
//Pepsane 3g 3g; 4mg	Dimethicon; Guaiazulen	3g; 4mg	Gói	4763
//Perfecta Femina SOS Probiotic	Dung dịch vệ sinh phụ nữ	(blank)	Chai	176400
//Perfecta Femina Balance Probiotic	Dung dịch vệ sinh phụ nữ	(blank)	Chai	176400
//Pharmox 500mg 500mg	Amoxicillin	500mg	Viên	1443
//Phosphalugel 12,38g 12,38g	Aluminium phosphat 12,38g	12,38g	Gói	4416
//Phocodex 5mg 5mg	Enalapril	5mg	Viên	448
//Plavix 75mg 75mg	Clopidogrel 75mg	75mg	Viên	18943
//Plavix 300mg 300mg	Clopidogrel	300mg	Viên	62316
//Plavix 300mg 300mg	Clopidogrel	300mg	Viên	71182
//pms- Rosuvastatin 10mg 10mg	Rosuvastatin (dưới dạng Rosuvastatin calcium )	10mg	Viên	1402
//Polygynax 35.000IU; 35.000IU; 100.000IU	Neomycin 35.000IU; Polymycin B 35.000IU; Nystatin 100.000IU	35.000IU; 35.000IU; 100.000IU	Viên	10165
//Postinor 1 1,5mg 1,5mg	Levonorgestrel	1,5mg	Viên	36380
//Postinor 1 1,5mg 1,5mg	Levonorgestrel	1,5mg	Viên	37400
//Povidin 10% 20ml 10% 20ml	Povidon iodine	10% 20ml	Chai	6403
//Povidin 10% 90ml 10% 90ml	Povidon iode	10% 90ml	Chai	13801
//Pradaxa 110mg 110mg	Dabigatran	110mg	Viên	32515
//Pradaxa 110mg 110mg	Dabigatran	110mg	Viên	36128
//Primperan 10mg 10mg	Metoclopramid	10mg	Viên	2014
//Procoralan 5mg 5mg	Ivabradine	5mg	Viên	10986
//Procoralan 7,5mg 7,5mg	Ivabradine	7,5mg	Viên	11284
//Prospan Cough syrup 70ml 70ml	Cao khô lá thường xuân	70ml	Chai	65163
//Pulmicort respules 500mcg 2ml 500mcg 2ml	Budesonide	500mcg 2ml	Ống	14802
//Que thử thai (Quickstick)	Que thử thai	(blank)	Cái	15729
//Radical Med Anti Dandruff Shampoo	Dầu gội làm sạch và ngăn ngừa gàu	(blank)	Chai	190890
//Radical Med Anti Hair Loss Shampoo	Dầu gội làm sạch và ngăn ngừa rụng tóc	(blank)	Chai	248010
//Ramasav 40mg 40mg	Drotaverin hydroclorid	40mg	Viên nén	897
//Rectiofar 5ml 59,53g/100ml, 5ml	Glycerin	59,53g/100ml, 5ml	Ống	2471
//Rectiofar 3ml 59.53g 3ml	Glycerin, nước tinh khiết	59.53g 3ml	Ống	2050
//Rhynixsol 7.5mg 15ml 7.5mg 15ml	Naphazolin nitrat	7.5mg 15ml	Chai	3580
//Rieserstat 50mg 50mg	Propylthiouracil	50mg	Viên	1320
//Rocimus 0,1% 0,1% 10g	Tacrolimus	0,1% 10g	Tube	367499
//Rocimus 0,03% 0,03% 10g	Tacrolimus	0,03% 10g	Tube	236250
//Rosuvas Hasan 10mg 10mg	Rosuvastatin	10mg	Viên	1062
//Rotundin 30mg 30mg	Rotundin	30mg	Viên	483
//Rutin - Vitamin C (TN) 50mg; 50mg	Vitamin C + rutine	50mg; 50mg	Viên	241
//Salonpas gel 30g 30g	Methyl Salicylate; L-menthol	30g	Tube	30783
//Salonpas 6,5 x 4,2mm	Methyl Salicylate; L-menthol; DL-camphor; Tocopherol Acetate	6,5 x 4,2mm	Miếng	2245
//Sanlein 5ml 1mg/ml, 5ml	Hyaluronat sodium	1mg/ml, 5ml	Lọ	61523
//Sanlein 5ml 1mg/ml, 5ml	Hyaluronat sodium	1mg/ml, 5ml	Lọ	66510
//SaVi Tenofovir 300 300mg	Tenofovir	300mg	Viên	9576
//SaviUrso 300mg 300mg	Ursodeoxycholic acid	300mg	Viên	8934
//SaVi Esomeprazole 40 40mg	Esomeprazol	40mg	Viên	2530
//Savi Eperisone 50 50mg	Eperison	50mg	Viên	989
//Scanax 500mg 500mg	Ciprofloxacin	500mg	Viên	1155
//Serc 8mg 8mg	Betahistine	8mg	Viên	2020
//Silymax 70mg 70mg	Silymarin	70mg	Viên	1265
//Singulair 4mg 4mg	Montelukast	4mg	Viên	14447
//Singulair 4mg (cốm) 4mg	Montelukast	4mg	Gói	14447
//Smecta 3g 3g	Dioctahedral Smectit 3g	3g	Gói	3823
//Spirastad Plus 750.000IU; 125mg	Spiramycin; Metronidazol	750.000IU; 125mg	Viên	1649
//Spiriva Respimat 30 liều 2.5mcg/ 2 nhát xịt/ 1 liều - 30 liều	Tiotropium	2.5mcg/ 2 nhát xịt/ 1 liều - 30 liều	Ống	1036715
//Sporal 100mg 100mg	Itraconazol	100mg	Viên	20303
//Stalevo 100/25/200mg 100/25/200mg	Levodopa, Carbidopa (dưới dạng Carbidopa monohydrate), Entacapon	100/25/200mg	Viên	23629
//Stadnex 20mg 20mg	Esomeprazol	20mg	Viên	2744
//Stadnex 40 40mg	Esomeprazol	40mg	Viên	7276
//Staclazide 60 MR 60mg	Gliclazide	60mg	Viên	2419
//Stacytine 200 200mg	Acetylcystein	200mg	Viên	805
//Stadeltine 5mg 5mg	Levocetirizin	5mg	Viên	1150
//Sterimar baby 50ml 50ml	Nước biển sinh lý	50ml	Chai	83459
//"Sterican G23x1"" G23x1"""	"Kim tiêm G23x1"""	"G23x1"""	Cây	738
//"Sterican G23x1"" G23x1"""	"Kim tiêm G23x1"""	"G23x1"""	Cây	905
//Strepsil Original 1,2mg; 0,6mg	2,4-Dichloro benzyl alcohol; Amylmetacresol	1,2mg; 0,6mg	Viên	1597
//Stromectin 6mg 6mg	Ivermectin	6mg	Viên	72546
//Stugeron 25mg 25mg	Cinnarizin	25mg	Viên	854
//63080 Sữa tắm Eucerin pH5 Body&Face 400ml 400ml	Sữa tắm	400ml	Chai	362670
//Sucrahasan 1g 1g	Sucralfat	1g	Gói	1704
//Symbicort Tur Oth 60 Dose 160/4.5 60 liều 160mcg; 4,5mcg, 60 liều	Budesonide; Formoterol	160mcg; 4,5mcg, 60 liều	Ống	300762
//Systane Ultra Drop 5ml 0,4%; 0,3% 5ml	Polyethylene Glycol; Propylene Glycol	0,4%; 0,3% 5ml	Lọ	64305
//Systane Ultra Drop 5ml 0,4%; 0,3% 5ml	Polyethylene Glycol; Propylene Glycol	0,4%; 0,3% 5ml	Lọ	64306
//Syseye 10ml 10ml	Hydroxypropyl	10ml	Lọ	26750
//Tanakan 40mg 40mg	Ginkgo biloba chuẩn hoá 40mg	40mg	Viên	4656
//Tanatril 10mg 10mg	Imidapril HCL	10mg	Viên	6471
//Tardyferon B9 160,2mg; 350mcg	Fe sulfate; Folic acid	160,2mg; 350mcg	Viên	3133
//Tatanol 500mg 500mg	Paracetamol	500mg	Viên	458
//Tavanic 500mg 500mg	Levofloxacin	500mg	Viên	39108
//Tavanic 500mg 500mg	Levofloxacin	500mg	Viên	49421
//Telfast BD 60mg 60mg	Fexofenadine 60mg	60mg	Viên	3901
//Telfast HD 180mg 180mg	Fexofenadine hydrochloride	180mg	Viên	7302
//Telfast HD 180mg 180mg	Fexofenadine hydrochloride	180mg	Viên	8264
//Telmisartan 40mg 40mg	Telmisartan	40mg	Viên	442
//Tenormin 50mg 50mg	Atenolol	50mg	Viên	3523
//Terbisil 250mg 250mg	Terbinafine	250mg	Viên	14830
//Tetracyclin 1% 5g 1% 5g	Tetracyclin	1% 5g	Tube	4365
//Tetracyclin 500 mg 500mg	Tetracyclin hydrochlorid	500mg	Viên	941
//Theralene 5mg 5mg	Alimemazin	5mg	Viên	445
//Theostat 100mg 100mg	Theophylline	100mg	Viên	1961
//Thiazifar 25mg 25mg	Hydroclorothiazid	25mg	Viên	171
//Thuốc trị sỏi thận Kim Tiền Thảo 120mg 120mg	Cao Kim Tiền Thảo	120mg	Chai	58422
//Thyrozol 5mg 5mg	Thiamazol	5mg	Viên	1412
//Thyrozol 10mg 10mg	Thiamazole	10mg	Viên	2261
//Timmak 3mg 3mg	Dihydro ergotamin mesylat	3mg	Viên	2034
//Timmak 3mg 3mg	Dihydro ergotamin mesylat	3mg	Viên	2035
//Tipharmlor 5mg 5mg	Amlodipin	5mg	Viên	218
//Tobradex 5ml 1mg; 3mg, 5ml	Dexamethason; Tobramycin	1mg; 3mg, 5ml	Lọ	48255
//Tobrex 5ml 5ml	Tobramycin	5ml	Lọ	42799
//Tobradex Oint 0,3%; 0,1% 3,5g 0,3%; 0,1% 3,5g	Tobramycin; Dexamethason	0,3%; 0,1% 3,5g	Tube	55961
//Tormeg 10mg 10mg	Atorvastatin	10mg	Viên	1735
//Tormeg 20mg 20mg	Atorvastatin	20mg	Viên	2629
//Tozinax 70mg 70mg	Kẽm gluconat	70mg	Viên	627
//Trimafort 3030,3mg; 800mg; 266,7mg	Al hydroxyd; Mg hydroxyd; Simethicon	3030,3mg; 800mg; 266,7mg	Gói	4344
//Trivita B fort 250mg; 250mg; 0,25mg	Vitamin B1; B6; B12	250mg; 250mg; 0,25mg	Viên	1110
//Trileptal 300mg 300mg	Oxcarbazepin	300mg	Viên	8628
//Túi treo tay vải	Túi treo tay vải	(blank)	Cái	20223
//Ultracet 325/37,5mg 325mg; 37,5mg	Paracetamol + Tramadol HCl	325mg; 37,5mg	Viên	8559
//Unitrexates Tabs 2.5mg 2.5mg	Methotrexate	2.5mg	Viên	2420
//Urgo Pore 5m x 2,5cm 5m x 2,5cm	Băng keo giấy	5m x 2,5cm	Cuộn	15943
//Urgo Pore 5m x 2,5cm 5m x 2,5cm	Băng keo giấy	5m x 2,5cm	Cuộn	16692
//Urgo Syval 5m x 1,25cm 5m x 1,25cm	Băng keo lụa	5m x 1,25cm	Cuộn	13589
//Urgo Syval 5m x 1,25cm 5m x 1,25cm	Băng keo lụa	5m x 1,25cm	Cuộn	14980
//Urgo Syval 5m x 2,5cm 5m x 2,5cm	Băng keo lụa	5m x 2,5cm	Cuộn	23540
//Urgo Tul 10 x 10cm 10 x 10cm	Băng keo lưới lipido-colloid	10 x 10cm	Miếng	40339
//Urgo Durable 2cm x 6cm Miếng	Băng keo cá nhân vải	Miếng	Miếng	597
//Urgosterile 53mm x 70mm	Băng dán có gạc 53mm x 70mm	(blank)	Miếng	3773
//Urgoband 10cm x 4.5m	Băng thun 3 móc 10cm x 4,5m	(blank)	Cuộn	23005
//Urostad 40 40mg	Furosemid	40mg	Viên	414
//Utrogestan 100mg 100mg	Progesteron	100mg	Viên	6955
//Utrogestan 200mg 200mg	Progesteron	200mg	Viên	13910
//Vagicare 10mg 10mg	Promestrien	10mg	Viên	5778
//Vastarel 20mg 20mg	Trimetazidine	20mg	Viên	2408
//Vastarel MR 35mg 35mg	Trimetazidine	35mg	Viên	2975
//Vaselin pure 10g 10g	Vaseline; Fragrance	10g	Tube	14124
//Ventolin Nebules 2,5mg/2,5ml 2,5mg/2,5ml	Salbutamol	2,5mg/2,5ml	Ống	5032
//Ventolin Nebules 2,5mg/2,5ml 2,5mg/2,5ml	Salbutamol	2,5mg/2,5ml	Ống	5753
//Ventolin Nebules 5mg/2,5ml 5mg/2,5ml	Salbutamol	5mg/2,5ml	Ống	9108
//Ventolin INH 100mcg 200 liều 100mcg 200liều	Salbutamol	100mcg 200liều	Chai	81725
//Ventolin INH 100mcg 200 liều 100mcg 200liều	Salbutamol	100mcg 200liều	Chai	96086
//Verospiron 25mg 25mg	Spironolacton	25mg	Viên	2102
//Vesicare 5mg 5mg	Solifenacin	5mg	Viên	27525
//VG-5 100mg; 130mg; 50mg; 50mg	Cao khô Diệp hạ châu; Nhân trần; Cỏ nhọ nồi; râu bắp	100mg; 130mg; 50mg; 50mg	Viên	1113
//VIAGRA 50mg 50mg	Sildenafil( Citrate)	50mg	Viên	122472
//Vincerol 4mg 4 mg	Acenocoumarol	4 mg	Viên	1848
//Vincerol 4mg 4 mg	Acenocoumarol	4 mg	Viên	2079
//Visanne 2mg 2mg	Dienogest	2mg	Viên	45038
//Vitamin B1 250mg 250mg	Vitamin B1	250mg	Viên	893
//Vitamin PP 500mg 500mg	Vitamine PP	500mg	Viên	627
//Vitamin PP 50mg 50mg	Vitamine PP	50mg	Viên	96
//Vitamin B6 100mg 100mg	Vitamin B6	100mg	Viên	289
//Vitamin PP 500mg (KH) 500mg	Vitamin PP (Khánh Hòa)	500mg	Viên	193
//Vitamin C 250mg 250mg	Vitamin C	250mg	Viên	175
//Vitamin C 250mg 250mg	Vitamin C	250mg	Viên	189
//Vitamin A & D 5000IU; 400IU	Vitamin A + D3	5000IU; 400IU	Viên	204
//Vitamin C Stada 1g 1g	Vitamin C	1g	Viên sủi	2089
//Vitamin E 400IU (HG) 400IU	Vitamin E	400IU	Viên	575
//Voltaren1% 20g 1% 20g	Diclofenac natri	1% 20g	Tube	67622
//Voltaren1% 20g 1% 20g	Diclofenac natri	1% 20g	Tube	67623
//Voltaren 75mg 75mg	Diclofenac natri	75mg	Viên	6617
//Vorifend Forte 500mg 500mg	Glucosamine sulfate	500mg	Viên	1537
//Vớ tĩnh mạch Jobst dài size S	Vớ tĩnh mạch Jobst dài size S	(blank)	Đôi	945000
//Vớ tĩnh mạch Jobst dài size M	Vớ tĩnh mạch Jobst dài size M	(blank)	Đôi	945000
//Vớ tĩnh mạch Jobst ngắn size M	Vớ tĩnh mạch Jobst ngắn size M	(blank)	Đôi	577500
//Xarelto 15mg 15mg	Rivaroxaban	15mg	Viên	62060
//Xatral XL 10mg 10mg	Alfuzosin 10mg	10mg	Viên	16361
//Xypenat 75ml 75ml	Nước biển sâu ưu trương	75ml	Chai	35310
//Zedcal 150mg;25mg;2mg;200IU-100ml	Calcium carbonate; Magnesium hydroxide, Zinc gluconate, Vitamin D3,	150mg;25mg;2mg;200IU-100ml	Chai	51360
//Zentel 200mg 200mg	Albendazole	200mg	Viên	5991
//Zinnat 125mg 125mg	Cefuroxim	125mg	Gói	16073
//Zinnat 500mg 500mg	Cefuroxim	500mg	Viên	23679
//Zinco 15mg/5ml 100ml	Kẽm sulfat heptahydrate	100ml	Chai	115500
//Zycel 200mg 200mg	Celecoxib	200mg	Viên	1124
//Zyrtec 10mg 10mg	Cetirizine	10mg	Viên	8003
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtMatKhau = new javax.swing.JPasswordField();
        jPanel4 = new javax.swing.JPanel();
        lblLevel_2 = new javax.swing.JLabel();
        lblLevel_1 = new javax.swing.JLabel();
        lblLevel_3 = new javax.swing.JLabel();
        txt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đối Tượng", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 12))); // NOI18N

        jRadioButton1.setText("jRadioButton1");

        jRadioButton2.setText("jRadioButton2");

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButton1)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(383, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jLabel1))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));

        txtMatKhau.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txtMatKhau.setBorder(null);
        txtMatKhau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMatKhauKeyReleased(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lblLevel_2.setBackground(new java.awt.Color(255, 255, 255));
        lblLevel_2.setOpaque(true);

        lblLevel_1.setBackground(new java.awt.Color(255, 255, 255));
        lblLevel_1.setOpaque(true);

        lblLevel_3.setBackground(new java.awt.Color(255, 255, 255));
        lblLevel_3.setOpaque(true);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(93, Short.MAX_VALUE)
                .addComponent(lblLevel_1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(lblLevel_2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(lblLevel_3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLevel_1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLevel_2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLevel_3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(txtMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jRadioButton3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jRadioButton3.setText("Đang ĐT");

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));

        jCheckBox1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jCheckBox1.setText("Sáng");

        jCheckBox2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jCheckBox2.setText("Tối");

        jCheckBox3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jCheckBox3.setText("Trưa");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox1)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox3)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox2)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jCheckBox3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));
        jPanel5.setPreferredSize(new java.awt.Dimension(315, 32));

        jTextField1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jTextField1.setText("12345678912345678923");
        jTextField1.setBorder(null);

        jButton3.setBackground(new java.awt.Color(204, 204, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh_barcode_16px.png"))); // NOI18N
        jButton3.setBorder(null);

        jComboBox1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "12345678912345678912", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(298, 298, 298))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120)
                        .addComponent(jRadioButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jButton4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jRadioButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMatKhauKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMatKhauKeyReleased
//        String length = txtMatKhau.getText();
//        if (length.length() != 0) {
//            if ((length.length() > 0 && length.length() <= 3) || checkPassword() == true
//                || !length.matches("[A-Z][a-z]+[0-9]+(\\w)*")) {
//                lblLevel_1.setOpaque(true);
//                lblLevel_2.setOpaque(false);
//                lblLevel_3.setOpaque(false);
//
//                lblLevel_1.setBackground(Color.RED);
//                lblLevel_2.setBackground(new Color(153, 204, 255));
//                lblLevel_3.setBackground(new Color(153, 204, 255));
//                checkLength = 2;
//            } else if (length.length() > 3 && length.length() <= 9) {
//                lblLevel_1.setOpaque(true);
//                lblLevel_2.setOpaque(true);
//                lblLevel_3.setOpaque(false);
//
//                lblLevel_1.setBackground(new Color(255, 255, 0));
//                lblLevel_2.setBackground(new Color(255, 255, 0));
//                lblLevel_3.setBackground(new Color(153, 204, 255));
//                checkLength = 0;
//            } else {
//                lblLevel_1.setOpaque(true);
//                lblLevel_2.setOpaque(true);
//                lblLevel_3.setOpaque(true);
//
//                lblLevel_1.setBackground(new Color(0, 179, 89));
//                lblLevel_2.setBackground(new Color(0, 179, 89));
//                lblLevel_3.setBackground(new Color(0, 179, 89));
//                checkLength = 0;
//            }
//        } else {
//            lblLevel_1.setOpaque(false);
//            lblLevel_2.setOpaque(false);
//            lblLevel_3.setOpaque(false);
//
//            lblLevel_1.setBackground(new Color(153, 204, 255));
//            lblLevel_2.setBackground(new Color(153, 204, 255));
//            lblLevel_3.setBackground(new Color(153, 204, 255));
//        }
    }//GEN-LAST:event_txtMatKhauKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (txt.getText().matches("([0-9]{1,3}/[0-9]{1,3})")) {
            System.out.println("dung");
        } else {
            System.out.println("sai");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//        try {
//            String a = new File("src/com/QBLBN/Poly/View/GiayNhapVien.jrxml").getCanonicalPath();
//            String a1 = new File("src/View/GiayNhapVien.jrxml").getAbsolutePath();
////        String a3 = this.getClass().getResource("src/com/QBLBN/Poly/View/GiayNhapVien.jrxml").toString();
//System.out.println(a);
//        } catch (IOException ex) {
//            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
FileResolver fileResolver = new FileResolver() {

     @Override
     public File resolveFile(String fileName) {
        URI uri;
        try {
          uri = new URI(this.getClass().getResource(fileName).getPath());
          return new File(uri.getPath());
        } catch (URISyntaxException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
          return null;
        }
    }
};System.out.println(fileResolver.resolveFile("/Images/logo02_1.png"));
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
//        String ma = null;
//        String ma1 = null;
//        for (int i = 0; i < 100000; i++) {
//            ma = String.valueOf(1000000000 + Math.round(Math.random() * 899999999));
//            ma1 = String.valueOf(1000000000 + Math.round(Math.random() * 899999999));
//        }
//        System.out.println("ma: "+ma+ma1);

        XPrinting.print("report1.jrxml", "", new HashMap<>());
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblLevel_1;
    private javax.swing.JLabel lblLevel_2;
    private javax.swing.JLabel lblLevel_3;
    private javax.swing.JTextField txt;
    private javax.swing.JPasswordField txtMatKhau;
    // End of variables declaration//GEN-END:variables
}
