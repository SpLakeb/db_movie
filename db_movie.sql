-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: db_movie
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_admin`
--

DROP TABLE IF EXISTS `t_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_admin` (
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_admin`
--

LOCK TABLES `t_admin` WRITE;
/*!40000 ALTER TABLE `t_admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_cinema`
--

DROP TABLE IF EXISTS `t_cinema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_cinema` (
  `cinemaId` int NOT NULL,
  `cinemaName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `brand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `addressX` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `addressY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`cinemaId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_cinema`
--

LOCK TABLES `t_cinema` WRITE;
/*!40000 ALTER TABLE `t_cinema` DISABLE KEYS */;
INSERT INTO `t_cinema` VALUES (707,'太平洋影城（重庆紫荆店）','太平洋电影城\n','渝北区龙山街道旗龙路2号（东和春天耍坝旁原名紫荆电影城）',NULL,NULL),(714,'UME影城（两江西奥）','UME国际影城\n','渝北区奥特莱斯路1号西部奥特莱斯内',NULL,NULL),(719,'横店影城（丰都店）','横店影视\n','丰都县三合街道平都大道东段52号丰都影视中心3层（近公安局）',NULL,NULL),(726,'橙天嘉禾影城（壹街店）','橙天嘉禾影城\n','大渡口区新山村街道文体路88号壹街购物中心A区2楼(区政府、大渡口公园旁)',NULL,NULL),(727,'博纳国际影城（凤天路店）','博纳国际影城\n','沙坪坝区凤天大道37号',NULL,NULL),(732,'横店电影城（煌华店）','横店影视\n','沙坪坝区渝碚路街道小龙坎新街三峡广场步行街6号新纪元购物广场8层',NULL,NULL),(733,'UME影城（重庆北碚店）','UME国际影城\n','北碚区北温泉街道康宁路58号嘉陵风情步行街4-5层(状元府第旁)',NULL,NULL),(734,'UME影城（沙坪坝店）','UME国际影城\n','沙坪坝区天陈路1号附10号炫地购物中心F4、F6',NULL,NULL),(737,'华谊兄弟影院（南坪店）','华谊兄弟\n','南岸区铜元局街道南坪西路38号百联南岸上海城购物中心F5层',NULL,NULL),(739,'万达影城（南坪万达广场店）',' 万达影城\n','南岸区江南大道8号万达广场娱乐楼3F(近珊瑚路)万达影城',NULL,NULL),(744,'大地影院（重庆渝北金港国际店）','大地影院\n','渝北区双龙湖街道金港国际中心二楼(机场对面)',NULL,NULL),(747,'UME影城（九龙坡店）','UME国际影城\n','九龙坡区珠江路48号龙湖西城天街3F—26号',NULL,NULL),(748,'金逸影城（长寿店）','金逸影城\n','长寿区凤城街道向阳路2号协信购物广场7层',NULL,NULL),(749,'中影国际影城（九龙坡印象汇店）','中影国际影城\n','九龙坡区九龙街道西郊三村2号印象汇4楼（动物园大门对面）',NULL,NULL),(760,'UME影城（北城天街CINITY店）','UME国际影城\n','江北区北城天街8号北城天街购物广场B区5F',NULL,NULL),(2025,'华谊兄弟影院（袁家岗店）','华谊兄弟\n','九龙坡区袁家岗奥体路1号中新城上城3楼',NULL,NULL),(2327,'保利万和熙街影城（熙街店）','保利国际影城\n','沙坪坝区大学城景苑路(美丽熙街)8号附1026号',NULL,NULL),(2391,'保利万和彭水影城（彭水店）','保利国际影城\n','彭水苗族土家族自治县汉葭街道下街民族会场(民族会场旁)',NULL,NULL),(2489,'UME影城（渝中）','UME国际影城\n','渝中区龙湖时代天街B馆5F（近石油路地铁站）',NULL,NULL),(2588,'沃美影城协信星光天地店','沃美影城\n','渝北区金开大道70号协信星光天地3楼（近天来大酒店）',NULL,NULL),(5135,'橙天嘉禾影城（日月光店）','橙天嘉禾影城\n','渝中区南纪门街道民权路89号日月光中心广场6层L6001室',NULL,NULL),(5404,'完美世界影城（重庆中冶店）','完美世界影城\n','渝北区金开大道1122号(轻轨鸳鸯站旁)',NULL,NULL),(5513,'UME影城（涪陵店）','UME国际影城\n','涪陵区兴华中路29号高笋塘泽胜商业步行街4楼',NULL,NULL),(5844,'大地影院（重庆巴南龙洲湾店）','大地影院\n','巴南区龙洲湾商街兴业银行楼上（轻轨3号线学堂湾站旁）',NULL,NULL),(6072,'横店电影城（4K120帧CINITY长寿店）','横店影视\n','长寿区桃源大道8号凯谊世纪广场四楼（重百旁）',NULL,NULL),(6096,'横店电影城（梁平店）','横店影视\n','梁平区人民东路一号重百商场六楼',NULL,NULL),(6411,'万达影城（SM广场XLAND店）','万达影城\n','渝北区渝北松石大道148号SM广场L4层（近龙湖紫都城）',NULL,NULL),(6491,'万达影城（万州万达广场CINITY店）','万达影城\n','万州区高笋塘街道北滨大道二段998号5幢附9号万达广场4层',NULL,NULL),(7483,'UME影城（解放碑店）','UME国际影城\n','渝中区较场口86-91号得意世界C座6-7楼',NULL,NULL),(7523,'保利万和国际影城（解放碑店）','保利万和国际影城\n','渝中区民权路59号较场口恒通云鼎6楼（轻轨站9号出口）（轻轨较场口站E出口对面）',NULL,NULL),(9292,'保利万和西彭影城','保利国际影城\n','九龙坡区西彭镇澎湖花园21栋',NULL,NULL),(9425,'横店电影城（外滩店）','横店影视\n','潼南区外滩国际城1号楼3楼（近滨江公园）',NULL,NULL),(9464,'保利万和国际影城（江津御景华庭店）','保利万和国际影城\n','江津区江洲大道600号御景华庭',NULL,NULL),(9835,'ACTO梦空间影城（雅图南坪店）','ACTO梦空间影城\n','南岸区铜元局街道南坪步行街亿象城负一层C区(近万达广场坪百盛旁)',NULL,NULL),(10214,'金逸影城（渝北店）','金逸影城\n','渝北区仙桃街道空港新城华辰财富广场五栋3楼',NULL,NULL),(10397,'保利万和国际影城（合川兆甲店）','保利万和国际影城\n','合川区合阳城街道交通街兆甲·海润天街10号楼3F(大润发对面)',NULL,NULL),(10889,'UME影城（大足）','UME国际影城\n','大足区龙中路89号大足印象购物广场D区4楼',NULL,NULL),(11027,'自由人影城（重庆阳光世纪店）','自由人影城\n','江北区观音桥阳光世纪购物中心L7层',NULL,NULL),(11056,'横店影城（合川店）','横店影视\n','合川区212国道宝龙城市广场内',NULL,NULL),(11655,'百丽宫影城（IMAX万象城店）','百丽宫影城\n','九龙坡区谢家湾正街55号万象城F5层',NULL,NULL),(12912,'保利万和时光影城（永川金科店）','保利国际影城\n','永川区中山路街道兴龙大道777号39幢3层(金科金街)',NULL,NULL),(13394,'太平洋影城（重庆酉阳店）','太平洋电影城\n','酉阳土家族苗族自治县桃花源大道中路81号（碧津广场四楼）',NULL,NULL),(13634,'大地影院（重庆渝北金港国际二店）','大地影院\n','渝北区滨港路金港国际购物中心二楼',NULL,NULL),(13715,'保利万和巫山影城（东转盘店）','保利万和国际影城\n','巫山县广东东路329号电影院4楼（两心四馆）',NULL,NULL),(13726,'嘉裕国际影城（双龙店）','嘉裕国际影城\n','渝北区双湖路1号水木天地1栋4楼',NULL,NULL),(14612,'金逸影城（中交丽景店）','金逸影城\n','大渡口区双龙路1号中交万都汇3楼（巴国城后门永辉超市楼上）',NULL,NULL),(14985,'嘉裕国际影城（沙坪坝店）','嘉裕国际影城\n','沙坪坝区小龙坎新街40号沙龙广场3楼（小龙坎地铁站1号出口前行100米）',NULL,NULL),(15046,'喜满客影城（双城天街店）','喜满客影城\n','沙坪坝区双碑浪升双城天街5楼(双碑轻轨站旁)',NULL,NULL),(15511,'博纳国际影城（大坪协信星光天地店）','博纳国际影城\n','渝中区经纬大道虎头岩总部城协信星光天地4F',NULL,NULL),(15988,'嘉凯影城（重庆金碧天下中国巨幕店）','恒大嘉凯影城\n','江津区双福街道南北大道北段388号恒大金碧天下644幢3-4层',NULL,NULL),(16276,'万达影城（万州高笋塘店）',' 万达影城\n','万州区高笋塘万高国际五层',NULL,NULL),(16301,'UME影城（双桥）','UME国际影城\n','大足区车城大道39号附2号',NULL,NULL),(16351,'万达影城（合川步步高店）',' 万达影城\n','合川区合阳大道将军支路步步高3号门观光电梯上4楼',NULL,NULL),(16418,'万达影城（二郎千叶中央街区巨幕店）',' 万达影城\n','九龙坡区火炬大道99号千叶中央街区2栋3楼',NULL,NULL),(16422,'越界影城（沙坪坝店）','越界国际影城\n','沙坪坝区沙坪坝正街8号附10号欣阳广场4楼（越界影城沙坪坝店）',NULL,NULL),(16457,'红星太平洋影城（重庆渝北LUXE巨幕店）','红星太平洋电影城\n','渝北区金山街道北部新区金开大道1003号爱琴海购物公园5F',NULL,NULL),(16547,'CGV影城（茶园店）','CGV影城\n','南岸区长生桥镇通江大道186号附1号5-1号',NULL,NULL),(16574,'金逸珠江影城（南川店）','金逸影城\n','南川区河滨南路19号电影大厦',NULL,NULL),(16647,'UME影城（南滨）','UME国际影城\n','南岸区南滨路东原1891B馆L3',NULL,NULL),(17054,'保利万和国际影城（奉节海成金街店）','保利万和国际影城\n','奉节县海成金街二区2-08号滨江国际C5座',NULL,NULL),(17090,'横店电影（荣昌店）','横店影视\n','荣昌区昌州街道康宁广场大润发超市3楼',NULL,NULL),(17116,'上影国际影城','上影国际影城\n','铜梁区民谐路与广龙路交叉路口往东南约50米(双远凤凰郡西南侧约50米)',NULL,NULL),(17119,'太平洋影城（重庆石柱店）','太平洋电影城\n','石柱土家族自治县南宾路7号康德中央大街4楼39号',NULL,NULL),(17404,'嘉裕国际影城（华岩店）','嘉裕国际影城\n','九龙坡区华岩镇福园路88号美华星都12幢第四层',NULL,NULL),(23731,'CGV影城（源著IMAX店）','CGV影城\n','江北区福康路25号龙湖源著天街购物广场第3、4、5层',NULL,NULL),(23966,'万达影城（北碚万达广场IMAX店）',' 万达影城\n','北碚区冯时行路300号万达广场4层',NULL,NULL),(24052,'CGV影城（U城IMAX店）','CGV影城\n','沙坪坝区虎溪街道大学城北路97号龙湖U城天街B馆4楼',NULL,NULL),(24193,'万达影城  （綦江万达广场IMAX店）','万达影城\n','綦江区滨河大道27号万达广场(綦江店)F4层',NULL,NULL),(24256,'保利万和国际影城（永川红星店）','保利万和国际影城\n','永川区胜利路街道萱花西路452号商业楼4层',NULL,NULL),(24305,'德信影城（时光城店）','德信影城\n','渝北区龙山街道时光城四楼(sm广场斜对面)',NULL,NULL),(24366,'德纳国际影城（重庆綦江店）','红星电影世界\n','綦江区通惠大道爱琴海购物公园3楼',NULL,NULL),(24601,'德纳国际影城（重庆巴南店）','红星电影世界\n','巴南区鱼洞街道鱼轻路鲁能南渝秀街六楼电影院',NULL,NULL),(24606,'UME影城（南岸店）','UME国际影城\n','南岸区泽科星泽汇4楼',NULL,NULL),(24670,'横店电影城（涪陵宝龙店）','横店影视\n','涪陵区百花路88号涪陵宝龙广场',NULL,NULL),(24912,'上影国际影城（南川南商广场店）','上影国际影城\n','南川区龙井路5号裙楼第四层789号商铺',NULL,NULL),(25015,'奥斯卡国际影城（黔江店）','奥斯卡国际影城\n','黔江区城东街道解放路大十字购物广场2期5楼01号',NULL,NULL),(25378,'完美世界影城（重庆爱融荟城店）','完美世界影城\n','渝北区龙山街道新南路439号爱融荟城B区L4楼',NULL,NULL),(25704,'保利万和国际影城（江豪店）','保利万和国际影城\n','万州区五桥百安坝安宁路69号（原国际大酒店对面）江豪广场',NULL,NULL),(25726,'保利万和国际影城（时代金街店 ）','保利万和国际影城\n','开州区汉丰街道迎宾社区帅乡路192号叁和购物广场负一楼',NULL,NULL),(25986,'华夏星光影城（双碑店）','星光影城\n','沙坪坝区井口街道嘉陵街双碑浪升双城天街5层',NULL,NULL),(26118,'白沙时代影城（巨幕店）','时代影城\n','江津区白沙镇白沙工业园区工商路199号3幢2—1白沙时代影城',NULL,NULL),(26295,'保利万和国际影城（壹街店）','保利万和国际影城\n','大渡口区文体路壹街购物中心A区2层',NULL,NULL),(26577,'金逸影城（开州店）','金逸影城\n','开州区假日国际商业广场3楼金逸影城',NULL,NULL),(26717,'万达影城（华茂国际中心IMAX店）',' 万达影城\n','永川区昌州大道东段789号HMC华茂国际中心C馆第5层',NULL,NULL),(26778,'大地影院（重庆观棠晓月店）','大地影院\n','大足区棠香街道MK购物广场3楼',NULL,NULL),(26929,'中影国际影城（潼南店）','中影国际影城\n','潼南区正兴街御景江山售楼部三楼',NULL,NULL),(27120,'金逸影城（兰亭IMAX店）','金逸影城\n','渝北区兴科大道322号兰亭新都汇4层',NULL,NULL),(27188,'越界影城（石桥铺仁悦天地店）','越界国际影城\n','九龙坡区石桥铺仁悦天地商场5楼',NULL,NULL),(27707,'CGV影城（来福士IMAX店）','CGV影城\n','渝中区接圣街8号重庆来福士广场03、04层03-127、04-120号',NULL,NULL),(27881,'太平洋影城（两江新区店）','太平洋电影城\n','江北区和源路221号附20号2-1',NULL,NULL),(27906,'保利万和国际影城（水土店）','保利万和国际影城\n','北碚区水土街道方正大道98号两江云中街2楼(建设银行水土支行楼上)',NULL,NULL),(28171,'万达影城（南川万达广场PRIME店）',' 万达影城\n','南川区渝南大道16号4楼',NULL,NULL),(28191,'沃美影城鲁能茶园店','沃美影城\n','南岸区长生桥镇鲁能·领秀城5街区南（天文大道西）',NULL,NULL),(28487,'泰禾影城（陈家坪店）','泰禾影城','九龙坡区石杨路43号1栋4-5号水獭云选广场4楼',NULL,NULL),(28504,'上影国际影城五桥店','上影国际影城\n','万州区五桥街道万汇城商业二层',NULL,NULL),(28518,'中视国际影城【巨幕】（潼南店）','中视国际影城\n','潼南区大佛街道子同街商业街3楼',NULL,NULL),(28523,'横店电影城（武隆店）','横店影视\n','武隆区凤山街道南滨路215号渝兆新天地4号楼横店电影城项厅',NULL,NULL),(35832,'重庆苏宁影城（金渝店）','苏宁影城\n','渝北区两江新区汇流路1号苏宁易购美联广场4楼',NULL,NULL),(36288,'苏宁影城（观音桥店）','苏宁影城\n','江北区观音桥街道观音桥步行街9号苏宁易购7楼',NULL,NULL),(36344,'上影国际影城（LUXE礼嘉天街店）','上影国际影城\n','渝北区礼嘉街道礼慈路15号龙湖礼嘉天街B馆-3F-Z02-Z02层',NULL,NULL),(36352,'上影国际影城（云阳店）','上影国际影城\n','云阳县迎宾大道588号云阳天地3楼',NULL,NULL),(36398,'太平洋影城（江津店）','太平洋电影城\n','江津区圣泉街道乾和新天汇6号楼3层',NULL,NULL),(36453,'完美世界影城（永川中央大街店）','完美世界影城\n','永川区渝西大道中段918号11幢附7-1',NULL,NULL),(36458,'银兴国际影城（三峡广场店）','银兴国际影城\n','沙坪坝区三峡广场拉摩国际中心(老新世纪楼上)',NULL,NULL),(36540,'保利万和影城（IMAX石桥广场店）','保利万和国际影城\n','九龙坡区渝州路街道渝州路59号石桥广场F3层',NULL,NULL),(36798,'中视国际影城IMAX（红旗河沟店）','中视国际影城\n','江北区红旗河沟龙湖新壹街C馆UG层',NULL,NULL),(36907,'万象影城（重庆大渡口万象汇杜比巨幕店）','万象影城\n','大渡口区文体路99号万象汇商场L5万象影城',NULL,NULL),(36921,'中影嘉莱国际影城（中渝广场店）','中影嘉莱国际影城\n','渝北区新溉大道恒大中渝广场5楼501',NULL,NULL),(36975,'魔影国际影城（巴南店）','魔影国际影城\n','巴南区商社汇巴南购物中心5楼',NULL,NULL),(37114,'完美世界影城（江北嘴财信广场店）','完美世界影城\n','江北区江北城街道金沙门路56号附18号3-17名义层3层',NULL,NULL),(37444,'星轶STARX影城（吾悦广场旗舰店）','星轶starx影城\n','渝北区仙桃街道同茂大道369号新城吾悦广场B馆6层',NULL,NULL),(37470,'横店电影城（百汇广场店）','横店影视\n','涪陵区聚龙大道69号百汇广场4楼',NULL,NULL),(37472,'金逸影城（重庆大悦城IMAX激光店）','金逸影城\n','渝北区秋成大道99号大悦城购物中心L514号商铺',NULL,NULL),(37479,'沃美影城中央公园龙湖店（WMX）','沃美影城\n','渝北区中央公园龙湖公园天街B馆5层Z02沃美影城',NULL,NULL),(37599,'上影国际影城（南川名润广场店）','上影国际影城\n','南川区南城街道办事处钟楼街18号(名润时代广场)裙楼4-1',NULL,NULL),(37607,'百丽宫影城（Dolby Cinema光环店）','百丽宫影城\n','渝北区湖彩路118号光环购物公园商场-L5-40',NULL,NULL),(37610,'万达影城（云阳店）',' 万达影城\n','云阳县青龙街道滨江大道333号城中城广场三期第F4层',NULL,NULL),(37814,'上影国际影城（万盛店）','上影国际影城\n','綦江区万盛经济技术开发区国能天街D栋3层',NULL,NULL),(37988,'SFC上影国际影城（双福爱琴海购物公园店）','上影国际影城\n','江津区双福街道南北大道北段726号重庆江津爱琴海购物公园F3001',NULL,NULL),(38195,'SFC上影国际影城（港城店）','上影国际影城\n','江北区港城南路悦邻park购物中心3楼',NULL,NULL),(38207,'幸福蓝海国际影城（重庆桃源居LUXE巨幕店）','幸福蓝海国际影城\n','渝北区桃源大道66号桃源天地购物中心5楼',NULL,NULL),(38446,'万达影城（卜蜂莲花店）',' 万达影城\n','江北区洋河北路6号力帆体育城3楼（黄泥磅地铁3A出口下行500米左右）',NULL,NULL),(38455,'博纳国际影城（高新天街店）','博纳国际影城\n','沙坪坝区西双大道32号龙湖高新天街A馆6楼',NULL,NULL),(38673,'横店电影城（重庆渝北店）','横店影视\n','渝北区福畅路10号泽科·空港星泽汇L3层',NULL,NULL),(38766,'SFC上影国际影城（解放碑店）','上影国际影城\n','渝中区五一路99号协信星光广场5楼',NULL,NULL),(38780,'保利万和国际影城（合川财富店）','保利万和国际影城\n','合川区绿港财富广场B馆3楼',NULL,NULL),(39015,'SFC上影国际影城（N37月光之城店）','上影国际影城\n','渝北区鸳鸯街道金开大道218号附68号N37月光之城',NULL,NULL),(39383,'世纪影城（巫溪店）','世纪影城\n','巫溪县柏杨北路92号新世纪百货三楼外租31号商铺',NULL,NULL),(39760,'上影国际影城（万科里店）','上影国际影城\n','九龙坡区彩虹路95号附9号3-12、13、14、15号',NULL,NULL);
/*!40000 ALTER TABLE `t_cinema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_evaluate`
--

DROP TABLE IF EXISTS `t_evaluate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_evaluate` (
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `movie_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `evaluate_time` datetime NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `grade` double DEFAULT NULL,
  PRIMARY KEY (`user_id`,`movie_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_evaluate`
--

LOCK TABLES `t_evaluate` WRITE;
/*!40000 ALTER TABLE `t_evaluate` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_evaluate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_expect`
--

DROP TABLE IF EXISTS `t_expect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_expect` (
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `movie_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `is_expected` int NOT NULL,
  PRIMARY KEY (`user_id`,`movie_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_expect`
--

LOCK TABLES `t_expect` WRITE;
/*!40000 ALTER TABLE `t_expect` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_expect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_hall`
--

DROP TABLE IF EXISTS `t_hall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_hall` (
  `hall_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `hall_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cinema_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `seats` int NOT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`hall_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_hall`
--

LOCK TABLES `t_hall` WRITE;
/*!40000 ALTER TABLE `t_hall` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_hall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_movie`
--

DROP TABLE IF EXISTS `t_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_movie` (
  `movie_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `movie_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `english_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `country` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `describe` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `duration` int DEFAULT NULL,
  `release_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `box_office` bigint DEFAULT NULL,
  `daily_box_office` bigint DEFAULT NULL,
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `expect` bigint DEFAULT NULL,
  `score` double DEFAULT NULL,
  `comments_number` int DEFAULT NULL,
  `preview_img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `preview_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`movie_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_movie`
--

LOCK TABLES `t_movie` WRITE;
/*!40000 ALTER TABLE `t_movie` DISABLE KEYS */;
INSERT INTO `t_movie` VALUES ('1165777','红楼梦之金玉良缘','THE DREAM OF THE RED CHAMBER','中国大陆','影片以贾宝玉大雪天里出家倒叙为始，主要故事情节以林黛玉回府奔丧开篇，揭秘了王熙凤贪没林如海家产盖建大观园，后又出卖了宝、黛两小无猜的爱情，设掉包计，致使宝玉迎娶宝钗，黛玉泪尽而亡，最终情散人悲、归于宿命，完成了红楼梦的悲剧结局',NULL,'2024-03-01 23:12:54','2024-03-31 23:59:59',220826063,0,'https://p0.pipi.cn/mmdb/54ecde9a8077e171f7c69616434226b74f72f.jpg?imageView2/1/w/464/h/644',0,7,NULL,NULL,NULL),('1198578','你想活出怎样的人生','君たちはどう生きるか','日本','电影讲述了少年牧真人的母亲葬身火海后，他随父亲与继母组成新家庭。深陷悲伤的真人阴郁孤僻，难以融入新环境。一次意外，他跟随一只会说话的苍鹭闯入废弃的神秘塔楼，却不料进入了奇幻的“亡灵世界”，开始了一场不可思议的冒险……',124,'2024-03-01 23:12:54','2024-08-31 23:59:59',746190000,0,'https://p0.pipi.cn/mmdb/54ecde0251b3385bf171f7bed23b9c527efea.jpg?imageView2/1/w/464/h/644',0,9.2,8,'https://p0.pipi.cn/friday/197b4b6d0a0d31120afa9d2fdff815c7.jpg?imageView2/1/w/120/h/68','https://vod.pipi.cn/fec9203cvodtransbj1251246104/074d0b851397757887683468519/v.f42905.mp4'),('1220935','一个人的江湖','The Universe Of One’s Own','中国大陆','“拳村”少年张小明，是小拳种金刚拳的唯一传承人，他肩负着将“金刚门”发扬光大的重任，只身来到大城市，希冀用传统的武林方式实现光复门派的愿望，怎奈屡屡碰壁，遭人耻笑。正当他心灰意冷时，他却邂逅了一位叫李昂的神秘蹉跎青年……',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',5491736,0,'https://p0.pipi.cn/mmdb/54ecde8d8d3ddd50c8f0ee963d22eece610b8.jpg?imageView2/1/w/464/h/644',0,8.3,NULL,NULL,NULL),('1221474','西南东北客','CRAZY GAMBLING','中国大陆','胡全胜和胡海行叔侄俩一心造福全村，搞个养猪场确经营不善反欠了钱，听说去瑞丽能一夜暴富还债，叔侄俩就听了老乡梁子的话去闯荡，结果上了骗子的当买了“白面”并装进了赌石中去脱手，从中演绎出各种荒诞的黑色人生。',NULL,'2023-12-01 23:12:54','2024-03-31 23:59:59',404609729,0,'https://p0.pipi.cn/mmdb/54ecdebe02ff2addd25015d97bf25460ec0dc.png?imageView2/1/w/464/h/644',0,3,NULL,NULL,NULL),('1228248','解密',NULL,'中国大陆','影片改编自麦家创作的长篇小说《解密》。上世纪40年代，世界局势暗流涌动，即时准确破译敌人的通讯密码至关重要。容金珍自小就展现出对数字的独有天赋，因意外破解数学系老师设置的难题，让他被更多的人注意到......',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',642191437,0,'https://p0.pipi.cn/mmdb/54ecde9a923c7e3ba38d3354dff87fa4b26eb.jpg?imageView2/1/w/464/h/644',0,2.9,NULL,NULL,NULL),('1250661','消失的木偶','A Vanished Puppet','中国大陆','神秘照片女子离奇失踪，诡异木偶循声杀人, 一对年轻男女深陷走不出的阴霾之中, 逃不开死亡诅咒！阴森月夜，幽闭空间，木偶复活……昏暗走廊尽头似有亡魂归来, 究竟是来自木偶的诅咒，还是亡魂的赎罪？当真相浮出的那一刻，所有的爱恨情仇将穿越阴阳两界, 勾勒出重叠交错的鬼魅倩影，恐怖惊悚直击心脏。',83,'2024-03-01 23:12:54','2024-08-31 23:59:59',1500000,0,'https://p0.pipi.cn/mmdb/54ecde0280792302ff3ba3c5542a0a00970cf.jpg?imageView2/1/w/464/h/644',0,2.3,0,'https://p0.pipi.cn/friday/6c53dac5ca2e5ce056f723c1185e3977.jpg?imageView2/1/w/120/h/68','https://vod.pipi.cn/43903a81vodtransgzp1251246104/d23a66dc1397757886838574232/v.f42905.mp4'),('1251242','功夫乒乓','Kung Fu Ping Pong','中国大陆','这部影片主要讲述在20世纪初期，日本妄图“名正言顺”接手在华权益——通过不平等的比赛夺取领地。其中有一座清城，日本组织了一场“桌网球比赛”妄图接管城市。就这样，一支临时组建的三教九流的球队，一名残疾的酒鬼教练，一场赢则死输则辱的赌局，最终缔造“中国国球”诞生并响彻世界的惊险传奇。',NULL,'2024-03-01 23:12:54','2024-03-31 23:59:59',642191437,0,'https://p0.pipi.cn/mmdb/54ecde02339b128ea302ff0d6d64106eb108b.jpg?imageView2/1/w/464/h/644',0,8.4,NULL,NULL,NULL),('1259816','三叉戟','Three Oldboys','中国大陆','大尺度硬核犯罪大片《三叉戟》，聚焦警界传奇“三叉戟”组合——“大背头”崔铁军（黄志忠 饰）、“大棍子”徐国柱（姜武 饰）、“大喷子”潘江海（郭涛 饰）。三位警界老炮在临近退休之际，意外卷入一场惊心动魄的巨额金融大案！ 幕后黑手企图解冻数十亿资产，不惜雇佣黑道势力动用极端手段进行洗钱。一切的背后隐藏着更为错综复杂的罪恶网络与人脉关系。面对对手的诱惑与重重困难，“三叉戟”能否坚定信念，携手破获此案，将罪犯绳之以法？',NULL,'2024-05-01 23:12:54','2024-08-31 23:59:59',747874009,0,'https://p0.pipi.cn/mmdb/54ecde02c7e51b501539dd989b34b56202ffa.jpg?imageView2/1/w/464/h/644',0,5.6,NULL,NULL,NULL),('1285828','好运来',NULL,'中国大陆','影片讲述了失业中年姚远带领一众“不靠谱”的年轻人，帮助各式奇葩圆梦的搞笑故事：有为好姐妹实现明星梦的少女、想当老板的上班族、想英雄救美的娘炮和想衣锦还乡慰藉父母的打工仔，展现了亲情、友情和爱情的可贵。',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',149774757,0,'https://p0.pipi.cn/mmdb/54ecde9a7a3c7e92358d3393645cdd84124af.jpg?imageView2/1/w/464/h/644',0,6.5,NULL,NULL,NULL),('1290415','课本','Textbook','中国大陆','院线电影《课本》的内容以 20 世纪 90 年代为背景，讲述了一名刚大学毕业的富家女，放弃回家享受安逸的生活，独自一人前往山区当支教老师。教学期间，得到山区小学老校长的循循善诱，在经历了一段曲折的人生历练后，她从一名活泼的小女生，慢慢地在潜移默化中发展成为一名优秀的人民教师，并运用更好的教育方法，帮助那些山区儿童成长的故事。影片以纯朴的乡村气息，让人回味的青春岁月，带观众回归到刚走出校园的时代，用时光倒流的方式进述故事，然后通过故事情节逐步引入更高的思想层次，让观众引起共鸣，思考自己的过去和未来，并为现实生活中的价值观进行反思，从而达到电影的宣传和激励目的。影片的制作以友情、亲情、师生情三条感情线穿插故事主线，并以体现教师的博爱精神、军人的优秀作风、企业家的爱国情怀、大学毕业生迈向社会的激情、山区村民的质朴等元素，潜移默化地增强了影片的社会宣传意义。',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',589767958,0,'https://p0.pipi.cn/mmdb/54ecde8d9237e10faf8d33ffc439f771a0df8.jpg?imageView2/1/w/464/h/644',0,4.7,NULL,NULL,NULL),('1291017','舞动奇迹','DANCEDREAM','中国大陆','佟天麒曾是国内顶尖舞者，因动作失误，导致队友受伤，从此一蹶不振，颓废潦倒的佟天麒，阴错阳差成为了富豪少爷小安的看护。展开了一段鸡飞狗跳的另类生活。小安的坚强与善良打开了佟天麒的心扉，让他重拾对街舞的热爱，为小安等人组建了街舞队，究竟小安这群孩子能否突破重重艰难获得街舞冠军；佟天麒又能否解开心结，重新站上他一生挚爱的舞台……',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',773809032,0,'https://p0.pipi.cn/mmdb/54ecde022ff0fae5bcb53557650c81e5460ad.jpg?imageView2/1/w/464/h/644',0,6.1,NULL,NULL,NULL),('1298240','黄雀在后！','The Victims','中国大陆','10年前的一个夏夜，警官袁文山（冯绍峰 饰）接到一起抢劫杀人案，在逐渐深入的调查过程中，当晚在场的嫌疑人们逐一浮出水面，然而案情的真相远非表面那般简单，三案并行，一场利益和正义的较量愈演愈烈，卷入其中的人们都是所图为何？究竟是情法之争，还是欲望驱使？在案件稍有眉目之时，袁文山却发现了一个被自己忽略的重要线索... ...',99,'2024-03-01 23:12:54','2024-03-31 23:59:59',96810000,0,'https://p0.pipi.cn/mmdb/54ecde0202f06d923571f7618b51bcefbbc69.jpg?imageView2/1/w/464/h/644',0,10,0,'https://p0.pipi.cn/friday/bcb8bdfe337a905649e1e2a2c1ca1a8b.jpg?imageView2/1/w/120/h/68','https://vod.pipi.cn/43903a81vodtransgzp1251246104/7b4ddc1f1397757887997024094/v.f42905.mp4'),('1298495','来自汪星的你','Woof Woof Daddy','中国大陆','糖厂工人江斯旺（郭富城 饰）独自抚养女儿江露（蓝盈莹 饰）成长，不料在阴差阳错中变成一条小狗。狗爸阿旺费尽力气找到女儿，发现此时江露已长大成人，并成为了一位创作歌手，但看似光鲜亮丽的背后却另有隐情。老爸决定以小狗阿旺的身份开启一段“守护之旅”……',105,'2024-05-01 23:12:54','2024-08-31 23:59:59',5960000,0,'https://p0.pipi.cn/mmdb/54ecde022ff0fa06d6be12ab09d2e318bb9b7.jpg?imageView2/1/w/464/h/644',0,7.5,0,'https://p0.pipi.cn/friday/aef5e6e4fc7fbe624c0901e0f812881f.jpg?imageView2/1/w/120/h/68','https://vod.pipi.cn/fec9203cvodtransbj1251246104/6ed9a9df1397757888579028048/v.f42905.mp4'),('1298556','亲爱的她','Daughters','日本','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-03-31 23:59:59',554616266,0,'https://p0.pipi.cn/mmdb/54ecdebe8ea51bc6967e12708539e0a83b097.png?imageView2/1/w/464/h/644',0,2.4,NULL,NULL,NULL),('1299156','水饺皇后','Dumpling Queen','中国大陆','暂无剧情简介',NULL,'2023-12-01 23:12:54','2024-03-31 23:59:59',616245646,0,'https://p0.pipi.cn/mmdb/54ecde02f2a5bf230f67cb1a26259a4a1f9f9.jpg?imageView2/1/w/464/h/644',0,8.5,NULL,NULL,NULL),('1299355','绝密任务','OPERATION BLACK-OPS','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',740616818,0,'https://p0.pipi.cn/mmdb/54ecde02f2a51b02ff7e12da4c0a954d6783b.jpg?imageView2/1/w/464/h/644',0,8.8,NULL,NULL,NULL),('1301249','平原上的火焰','FIRE ON THE PLAIN','中国大陆','暂无剧情简介',NULL,'2024-05-01 23:12:54','2024-08-31 23:59:59',678943012,0,'https://p0.pipi.cn/mmdb/d2dad52ff2a80757e207ac50871b7473dba19.png?imageView2/1/w/464/h/644',0,1.3,NULL,NULL,NULL),('1301552','曾经的我们致70·80·90','DAYS OF THE PAST','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',837604691,0,'https://p0.pipi.cn/mmdb/54ecdebec69c7e925707ac971f3dd018c1eb5.jpg?imageView2/1/w/464/h/644',0,8.6,NULL,NULL,NULL),('1302431','二郎神之深海蛟龙','God with Three Eyes','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',572392897,0,'https://p0.pipi.cn/mmdb/54ecde8d5370fa395b339e965518c3d7eded7.jpg?imageView2/1/w/464/h/644',0,8.1,NULL,NULL,NULL),('1307114','星际变种','VARIANT','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-03-31 23:59:59',594177608,0,'https://p0.pipi.cn/mmdb/54ecdebe5370e1300b07ac3ea5357e8dcc991.jpg?imageView2/1/w/464/h/644',0,6.4,NULL,NULL,NULL),('1309779','猩球崛起：新世界','Kingdom of the Planet of the Apes','美国','暂无剧情简介',NULL,'2024-05-01 23:12:54','2024-08-31 23:59:59',852460460,0,'https://p0.pipi.cn/mmdb/54ecde9a06d5bf2ff7ddd215792b32ae7a7ea.jpg?imageView2/1/w/464/h/644',0,9.6,NULL,NULL,NULL),('1310023','没问题',NULL,'中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',143810375,0,'https://p0.pipi.cn/mmdb/d2dad5927a30e1230f9ab450b22add5b416d9.jpg?imageView2/1/w/464/h/644',0,8.9,NULL,NULL,NULL),('1310520','年少日记','Times Still Turns The Pages','中国香港','一名有轻生念头的十岁学童如何面对家庭和社会带来的绝望感。',95,'2024-03-01 23:12:54','2024-03-31 23:59:59',9090000,0,'https://p0.pipi.cn/mmdb/54ecde025bf51b8ea39ab4ff1eec4d5341980.jpg?imageView2/1/w/464/h/644',0,0.8,0,'https://p1.meituan.net/mmdb/42b53b3bbf3404a634d567705f50215f153814.jpg@120w_68h_1e_1c','https://vod.pipi.cn/fec9203cvodtransbj1251246104/501c05c11397757888952171794/v.f42905.mp4'),('1320548','家庭简史','Brief History Of A Family','中国大陆','暂无剧情简介',NULL,'2024-05-01 23:12:54','2024-08-31 23:59:59',548250483,0,'https://p0.pipi.cn/mmdb/54ecde0251bb120faf7df5c1625cbc4b67f72.png?imageView2/1/w/464/h/644',0,4.4,NULL,NULL,NULL),('1328836','出门在外','GIVE ME A RIDE','中国大陆','待业宅家的余良生（包贝尔 饰）被母亲逼迫去川西寻找孤身一人去支教的妹妹余潇（何蓝逗 饰），却得知妹妹被人贩绑架，救妹心切的他就此踏上了一场险象环生的营救妹妹之旅。途中他意外邂逅同样漂泊在外的顾小曼（李梦 饰），殊不知两人即将被迫卷入更大的阴谋。各怀鬼胎的几方势力，阴差阳错的上演了一幕幕啼笑皆非的闹剧。真真假假的身份、荒诞离谱的误会，…旅途的终点到底还有什么等待着他们？',102,'2024-03-01 23:12:54','2024-08-31 23:59:59',1240000,0,'https://p0.pipi.cn/mmdb/54ecde0251bc7e57e2e7aa92429d4cb407e7a.jpg?imageView2/1/w/464/h/644',0,5.9,0,'https://p0.pipi.cn/friday/a1e4a75cff96288c4dad2c7ac32877e9.jpg?imageView2/1/w/120/h/68','https://vod.pipi.cn/43903a81vodtransgzp1251246104/eecb354e1397757888543813200/v.f42905.mp4'),('1336423','青檀','QINGTAN','中国大陆','暂无剧情简介',NULL,'2024-05-01 23:12:54','2024-08-31 23:59:59',351312280,0,'https://p0.pipi.cn/mmdb/54ecdebe2c9dddc9fdd7c32949103d5b55297.jpg?imageView2/1/w/464/h/644',0,5.8,NULL,NULL,NULL),('1339006','妈妈和七天的时间','Mama','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-03-31 23:59:59',711645862,0,'https://p0.pipi.cn/mmdb/54ecde02e7a923be1239ddcbe3bf229d3b46d.jpg?imageView2/1/w/464/h/644',0,8.9,NULL,NULL,NULL),('1340084','天才游戏','Game of Identity','中国大陆','暂无剧情简介',NULL,'2023-12-01 23:12:54','2024-03-31 23:59:59',835286169,0,'https://p0.pipi.cn/mmdb/fb738633c695bfb53516bd1a801039316b7ed.jpg?imageView2/1/w/464/h/644',0,4.4,NULL,NULL,NULL),('1350281','虎狼之路',NULL,'中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-03-31 23:59:59',510425548,0,'https://p0.pipi.cn/mmdb/54ecde02dddc7e3139e5bc0b9ed85dc41ea3b.png?imageView2/1/w/464/h/644',0,8.2,NULL,NULL,NULL),('1351778','爱在陇南','Land Of Love','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-03-31 23:59:59',629701986,0,'https://p0.pipi.cn/mmdb/54ecde9af2a51b5bf18ea36df2ae876c9b7a8.jpg?imageView2/1/w/464/h/644',0,4.4,NULL,NULL,NULL),('1355287','宝贝回家之玩命追击','Baby Back Home','中国大陆','暂无剧情简介',NULL,'2024-05-01 23:12:54','2024-08-31 23:59:59',648896360,0,'https://p0.pipi.cn/mmdb/54ecde8d7a351be7aa0faf7d65efe2710fe2a.jpg?imageView2/1/w/464/h/644',0,1.7,NULL,NULL,NULL),('1357867','小倩','NIE XIAOQIAN','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',121876008,0,'https://p0.pipi.cn/mmdb/54ecde02c7e923ddd2d236ef4e2bb6661ecbb.jpg?imageView2/1/w/464/h/644',0,4.6,NULL,NULL,NULL),('1362705','爱的重叠','The Overlap of Love','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',317859406,0,'https://p0.pipi.cn/mmdb/54ecde8d9ab7e18d33ddd27008d47eca2686d.jpg?imageView2/1/w/464/h/644',0,7.1,NULL,NULL,NULL),('1370725','草木人间','Dwelling by the West Lake','中国大陆','丈夫何山凭空消失了十年后，苔花（蒋勤勤饰）正要迈进新生，但命运却把她推向了另一重绝境——她前脚被情人（陈建斌饰）母亲从采茶园赶走，后脚就被最亲近的好姐妹金兰骗进了传销组织“蝴蝶国际”。母亲沉迷其中，让一边寻父一边救母的儿子目莲（吴磊饰）身心疲惫，而蝴蝶国际却如藤蔓疯长一般，将恶魔的种子根植在无数百姓心中。苔花也摇身一变，从衣着打扮到言行举止都宛如“新生”，疯癫的举动让目莲感到诧异。母亲如蝴蝶一般翩翩于梦中之际，儿子想尽一切办法奋力解救……这座人间炼狱究竟该怎样逃脱？现代版“目连救母”能否成功？',118,'2024-03-01 23:12:54','2024-08-31 23:59:59',114960000,0,'https://p0.pipi.cn/mmdb/54ecde029abc69338f339e6bf4aa5fda9b4d8.jpg?imageView2/1/w/464/h/644',0,5.2,0,'https://p0.pipi.cn/friday/f50650717d64d70a495a3dd8fb4ebce4.jpg?imageView2/1/w/120/h/68','https://vod.pipi.cn/fec9203cvodtransbj1251246104/7c6319d81397757888141581457/v.f42905.mp4'),('1371004','狗阵',NULL,'中国大陆','暂无剧情简介',NULL,'2024-05-01 23:12:54','2024-08-31 23:59:59',821627802,0,'https://p0.pipi.cn/mmdb/fb7386dd2c933816bd02ffdc6427b8c22aea9.jpg?imageView2/1/w/464/h/644',0,1.8,NULL,NULL,NULL),('1371016','云边有个小卖部',NULL,'中国大陆','暂无剧情简介',NULL,'2023-12-01 23:12:54','2024-08-31 23:59:59',387849514,0,'https://p0.pipi.cn/mmdb/fb7386dd7e151b9ab4338f23def86e6bec962.jpg?imageView2/1/w/464/h/644',0,6.9,NULL,NULL,NULL),('1372970','破冰少年','Skating Boy','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',208298113,0,'https://p0.pipi.cn/mmdb/54ecde8d9ab338cbae31391e8cf1264565b5a.jpg?imageView2/1/w/464/h/644',0,6.5,NULL,NULL,NULL),('1374577','特技狂人','The Fall Guy','美国','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',793454098,0,'https://p0.pipi.cn/mmdb/54ecde02c7e923be12cbaef0e734916216de6.jpg?imageView2/1/w/464/h/644',0,8.7,NULL,NULL,NULL),('1374909','乘船而去','Gone With the Boat','中国大陆','独自生活在运河边农村的老太太周瑾突然被确诊脑瘤，漂在外地的子女不得不回乡照顾。大女儿苏念真在上海经营留学咨询机构，正在经历第二次离婚，她坚持要给母亲最好的治疗；小儿子苏念清是个四处漂泊的导游，他支持母亲放下，接受无常。在死亡面前，周瑾寻找着精神的归宿 ，苏念真和苏念清在不断发现母亲秘密的同时，正与故乡失去最后的连接。',99,'2024-05-01 23:12:54','2024-08-31 23:59:59',1190000,0,'https://p0.pipi.cn/mmdb/54ecde022ff339b860e7aa0ea47c0902f6b7f.jpg?imageView2/1/w/464/h/644',0,7.8,0,'https://p0.pipi.cn/friday/d08a7482ab7f57876d5eed72e1cbd3b7.jpg?imageView2/1/w/120/h/68','https://vod.pipi.cn/fec9203cvodtransbj1251246104/f5422e6e1397757888421028126/v.f42905.mp4'),('1379047','有你真好！','Battle','中国大陆','本片根据CHUC全国街舞联盟青少年街舞国家队队长席嘉琪真实故事改编。 若你决定灿烂，则山无遮，海无拦。天才少女席嘉嘉（席嘉琪 饰）一直以来的梦想就是成为世界街舞冠军，但这在妈妈苗红（郝蕾 饰）眼中却变成了不务正业，恰逢家庭突遭变故，妈妈被迫卖房还债，困顿的家境更无力支撑她实现梦想。面对现实的重压，她不得已靠捡垃圾学习街舞。或许所谓梦想，就是哪怕披荆斩棘，也要坚持前行。只要往前走，就没什么能拦得住你到达彼岸。',97,'2024-03-01 23:12:54','2024-08-31 23:59:59',1690000,0,'https://p0.pipi.cn/mmdb/54ecde02d7c0fa300b71f7b30163479d661a4.jpg?imageView2/1/w/464/h/644',0,5.4,0,'https://p0.pipi.cn/friday/fc16876387723085557c1f1f19c245d2.png?imageView2/1/w/120/h/68','https://vod.pipi.cn/fec9203cvodtransbj1251246104/738025171397757888579221090/v.f42905.mp4'),('1383679','生命如花爱如蜜','TONGHUA GIAL','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',461432889,0,'https://p0.pipi.cn/mmdb/fb7386927a351b87a98ea3f3adf88ecc9c118.jpg?imageView2/1/w/464/h/644',0,7.6,NULL,NULL,NULL),('1385816','八臂哪吒城的传说','The lengends of Eight Armed Nezha City','中国大陆','暂无剧情简介',NULL,'2024-05-01 23:12:54','2024-08-31 23:59:59',601709731,0,'https://p0.pipi.cn/mmdb/54ecdebe06df2a02ffd7c359897f5817a8902.png?imageView2/1/w/464/h/644',0,1.4,NULL,NULL,NULL),('1385822','我才不要和你做朋友呢','Be My Friend','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-03-31 23:59:59',529488246,0,'https://p0.pipi.cn/mmdb/54ecde028d3923ddd2395bd8ea5f8c4aa1256.jpg?imageView2/1/w/464/h/644',0,8.8,NULL,NULL,NULL),('1394072','大场面','KEEP ROLLING','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-03-31 23:59:59',191062855,0,'https://p0.pipi.cn/mmdb/fb73869251b9233ba3e7aa7721da61e9ab2f4.jpg?imageView2/1/w/464/h/644',0,7.7,NULL,NULL,NULL),('1395127','欢迎再次登录',NULL,'中国大陆','暂无剧情简介',NULL,'2024-05-01 23:12:54','2024-03-31 23:59:59',404024249,0,'https://p0.pipi.cn/mmdb/fb7386bec69ddd395b71f746f326e090e6ce2.jpg?imageView2/1/w/464/h/644',0,4.7,NULL,NULL,NULL),('1399450','在那遥远的北方','The Village','中国大陆','转业士兵李战胜为了响应国家的号召，也为了信守对爱人的承诺，和其他三个战友来到环境恶劣的北大荒屯垦戍边。最终李战胜为了保护粮食牺牲在了北大荒...',85,'2023-12-01 23:12:54','2024-03-31 23:59:59',404361867,0,'https://p0.pipi.cn/mmdb/fb73862f7e1f2ae19b06d670062f81d1b4687.png?imageView2/1/w/464/h/644',0,3.9,0,'-1','-1'),('1405602','朝云暮雨','Strangers When We Meet','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-03-31 23:59:59',164911697,0,'https://p0.pipi.cn/mmdb/54ecde0251bddde5bc1789a2b1014b62e05ad.jpg?imageView2/1/w/464/h/644',0,1.5,NULL,NULL,NULL),('1413549','维和防暴队','Formed Police Unit','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-03-31 23:59:59',185637194,0,'https://p0.pipi.cn/mmdb/54ecde027e15bf395b02ff5cce84a702132ab.jpg?imageView2/1/w/464/h/644',0,6.2,NULL,NULL,NULL),('1413550','梦回金鸡岭','Dream Back To JINJILING','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-03-31 23:59:59',378991172,0,'https://p0.pipi.cn/mmdb/54ecde8db1233839ddb12dfdf52713e42a616.jpg?imageView2/1/w/464/h/644',0,4.3,NULL,NULL,NULL),('1418942','走走停停','Gold or Shit','中国大陆','暂无剧情简介',NULL,'2024-05-01 23:12:54','2024-08-31 23:59:59',515211110,0,'https://p0.pipi.cn/mmdb/54ecde023395bf281eaf33e8b07bdb54da09f.jpg?imageView2/1/w/464/h/644',0,5.9,NULL,NULL,NULL),('1419080','踢出个未来','Mountain Soccer Boy','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',543026655,0,'https://p0.pipi.cn/mmdb/fb7386929233387df502ff4ee2c312e51f99a.jpg?imageView2/1/w/464/h/644',0,3.9,NULL,NULL,NULL),('1422798','我的朋友安德烈',NULL,'中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',30575440,0,'https://p0.pipi.cn/mmdb/fb73868db12ddd17891789d2424ce13ebf3e9.jpg?imageView2/1/w/464/h/644',0,7.8,NULL,NULL,NULL),('1423910','盗火者',NULL,'中国大陆','暂无剧情简介',NULL,'2023-12-01 23:12:54','2024-08-31 23:59:59',669078713,0,'https://p0.pipi.cn/mmdb/fb738671dddddd807739dd9f314e7d4c7bbc2.jpg?imageView2/1/w/464/h/644',0,6.1,NULL,NULL,NULL),('1424182','猎杀','Deadly Circles','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',574271185,0,'https://p0.pipi.cn/mmdb/fb73862fb53f2ac9fd21f0570f9f05b9a4f37.jpg?imageView2/1/w/464/h/644',0,9.2,NULL,NULL,NULL),('1426142','我是美术生之星空筑梦','GUARD OF THE STARS','中国大陆','暂无剧情简介',NULL,'2024-05-01 23:12:54','2024-08-31 23:59:59',242461538,0,'https://p0.pipi.cn/mmdb/d2dad5337e10e151ba030cd453702e8ed5d78?imageView2/1/w/464/h/644',0,7.5,NULL,NULL,NULL),('1427828','不能错过的只有你','Nobody But You','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-03-31 23:59:59',52112947,0,'https://p0.pipi.cn/mmdb/54ecde02338c7e230f7df526082366e5c5b5a.jpg?imageView2/1/w/464/h/644',0,9.4,NULL,NULL,NULL),('1427883','因你而动','Butterfly Lovers','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-03-31 23:59:59',120194334,0,'https://p0.pipi.cn/mmdb/fb73862f33971f3139cf3ef1d0695e7f3efc9.jpg?imageView2/1/w/464/h/644',0,3.7,NULL,NULL,NULL),('1427961','寻找消失的恋人','Where  Are  You','中国大陆','暂无剧情简介',NULL,'2023-12-01 23:12:54','2024-03-31 23:59:59',760801132,0,'https://p0.pipi.cn/mmdb/54ecde9a02ff2a50155bf10c8318b72a18991.jpg?imageView2/1/w/464/h/644',0,5.5,NULL,NULL,NULL),('1431842','浙南儿女英雄传之热血长虹','Heroine Zheng Yiying','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-03-31 23:59:59',923181271,0,'https://p0.pipi.cn/mmdb/54ecde8de7a0fa3ba3d236166c71acf63f9ab.jpg?imageView2/1/w/464/h/644',0,4.5,NULL,NULL,NULL),('1432552','追·寻','The pursuit of footprint','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-03-31 23:59:59',357395608,0,'https://p0.pipi.cn/mmdb/54ecde8dbe1ddd7df550c8c78dddaf7d0e263.jpg?imageView2/1/w/464/h/644',0,0.1,NULL,NULL,NULL),('1432921','危机航线','High Forces','中国大陆','暂无剧情简介',NULL,'2024-05-01 23:12:54','2024-08-31 23:59:59',3185789,0,'https://p0.pipi.cn/mmdb/d2dad52f8d3f2a7e1206d63d2289a5aec7759.jpg?imageView2/1/w/464/h/644',0,2.6,NULL,NULL,NULL),('1434029','莫莉的冒险','Molly','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',937040120,0,'https://p0.pipi.cn/mmdb/fb73862fd7cf2ad7c30e13b9db957e50e1dde.png?imageView2/1/w/464/h/644',0,3.9,NULL,NULL,NULL),('1434971','来日皆方长','THE COMING DAYS','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',233270583,0,'https://p0.pipi.cn/mmdb/54ecde02338f2ac9fd87a9095d02d0bb67779.jpg?imageView2/1/w/464/h/644',0,1.5,NULL,NULL,NULL),('1434993','云雾女孩',NULL,'中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',478810706,0,'https://p0.pipi.cn/mmdb/54ecde027e10fa7df5537ce9bd8ba976b37dd.png?imageView2/1/w/464/h/644',0,3.5,NULL,NULL,NULL),('1435067','窗前明月光',NULL,'中国大陆','暂无剧情简介',NULL,'2023-12-01 23:12:54','2024-08-31 23:59:59',74480001,0,'https://p0.pipi.cn/mmdb/54ecde022ff51b0e13537cb940093fb0125a3.jpg?imageView2/1/w/464/h/644',0,5.6,NULL,NULL,NULL),('1437173','绝地重生','Turning Point','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',199692402,0,'https://p0.pipi.cn/mmdb/54ecdebeb533385bf1338f7234c6d57ae7e1f.jpg?imageView2/1/w/464/h/644',0,0.6,NULL,NULL,NULL),('1441786','沙丘2','Dune: Part Two','加拿大','《沙丘2》将会探索保罗·厄崔迪的传奇之旅，他与契妮和弗雷曼人联手，踏上对致其家毁人亡的阴谋者的复仇之路。当面对一生挚爱和已知宇宙命运之间的抉择时，他必须努力阻止只有他能预见的可怕的未来。',166,'2024-05-01 23:12:54','2024-08-31 23:59:59',349570000,0,'https://p0.pipi.cn/mmdb/54ecdebe7a351bd236c69612ca47cec9ad381.jpg?imageView2/1/w/464/h/644',0,2.3,0,'https://p0.pipi.cn/friday/27eff2e53feb2e9c7de0ede8bbf7317e.jpg?imageView2/1/w/120/h/68','https://vod.pipi.cn/43903a81vodtransgzp1251246104/45a71ab11397757886303508651/v.f42905.mp4'),('1446251','午夜怪谈','Midnight Radio','中国大陆','女鬼来到了讲“鬼故事”的电台！当红主播探秘诡异凶宅，引来白衣女鬼暗夜还魂，恐怖浴室血流成河，灵异事件引发阵阵尖叫。夜半无人的十字路口，烧纸钱的老太太猛然抬头，于火花跳跃的映照下瘆人无比；诡异来电歇斯底里，阵阵急促幽深的“还我命来”引发全民恐慌；风声狂作电路冥灭，惊魂诡影频频飘荡于电台、凶宅的暗黑角落，紧贴凡人身后，时刻准备致命一击，夺魂索魄。惊悚电台、恐怖凶宅，以及被怪力操纵而陷入危机的主播，将会开启何种惊吓盛宴？！午夜怪谈，骇人惊吓，即将尖叫引爆！！',88,'2024-03-01 23:12:54','2024-08-31 23:59:59',2920000,0,'https://p0.pipi.cn/mmdb/54ecde02ddd5bf06d6ddd2c73d9d984ef9024.jpg?imageView2/1/w/464/h/644',0,5.2,0,'https://p0.pipi.cn/friday/84fc7858d50b7dca9614d08161bf3ad8.jpg?imageView2/1/w/120/h/68','https://vod.pipi.cn/fec9203cvodtransbj1251246104/a203f0521397757886413349305/v.f42905.mp4'),('1446323','末路狂花钱','THE LAST FRENZY','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-03-31 23:59:59',715602072,0,'https://p0.pipi.cn/mmdb/54ecdebe33951b8d33cbae2ee164f816d021d.jpg?imageView2/1/w/464/h/644',0,8.2,NULL,NULL,NULL),('1448767','灿烂的她','Remember Me','中国大陆','慈祥宽容的奶奶（惠英红 饰）与十二年前意外走失的孙女（刘浩存 饰）重逢，祖孙二人从生疏到彼此依偎。相处期间，她们身上的秘密也逐渐被揭开。最终奶奶拯救了这个本对人生不抱希望的“孙女”。',116,'2024-03-01 23:12:54','2024-03-31 23:59:59',100250000,0,'https://p0.pipi.cn/mmdb/54ecde9a5bf0fa50c806d6be52dbfd4ee06ab.jpg?imageView2/1/w/464/h/644',0,7.7,0,'https://p0.pipi.cn/friday/e0f3ec47a2bee05c8e130c0c14dc8a92.jpg?imageView2/1/w/120/h/68','https://vod.pipi.cn/fec9203cvodtransbj1251246104/43a225cf1397757887113101678/v.f42905.mp4'),('1448919','坠落的审判','Anatomy of A Fall','法国','2023戛纳金棕榈最佳影片！2024奥斯卡最佳原创剧本大奖！狂揽全球280＋项大奖及提名！继是枝裕和的《小偷家族》后，时隔六年，再度登陆内地大银幕的金棕榈最佳影片！国内外各大平台口碑爆棚，影片以女性视角细腻地呈现了夫妻关系中的隐痛，丈夫的死亡真相唤起观众对亲密关系的深入思考和共鸣，没有谁的生活经得起这样的审判！但人性中不肯投降的、微弱的美好和希望，最终将指引着每个人继续前行！3月29日，大银幕探寻家庭与婚姻的真相！',152,'2023-12-01 23:12:54','2024-03-31 23:59:59',27180000,0,'https://p0.pipi.cn/mmdb/54ecde9a338ddde19b9ab45a0811cc2615695.jpg?imageView2/1/w/464/h/644',0,2.1,0,'https://p0.pipi.cn/friday/38f34308ffc80d78307da438a0bfec2d.jpg?imageView2/1/w/120/h/68','https://vod.pipi.cn/fec9203cvodtransbj1251246104/5c5c09361397757887477024326/v.f42905.mp4'),('1451499','被我弄丢的你','I Miss You','中国大陆','2010年夏天，高考考场上，白晓宇第一次遇见王斤斤，就把她给“弄丢”了。 四年后，他们在毕业季的求职人潮中意外重逢，然后相恋。 2018年，相识八年、相爱五年的他们站在婚姻的门前踌躇不前了。 他们彼此相爱却又难逃现实的重重羁绊，白晓宇会再一次弄丢王斤斤吗……',112,'2024-03-01 23:12:54','2024-03-31 23:59:59',211400000,0,'https://p0.pipi.cn/mmdb/54ecde8d338807b86017890eb5ebcca70f917.jpg?imageView2/1/w/464/h/644',0,6.6,0,'https://p0.pipi.cn/friday/f71649e425e017f87027f7d12ef11540.jpg?imageView2/1/w/120/h/68','https://vod.pipi.cn/fec9203cvodtransbj1251246104/915dab2b1397757886311261308/v.f42905.mp4'),('1456267','排球少年!! 垃圾场决战','Haikyu!! The Dumpster Battle','日本','暂无剧情简介',NULL,'2024-05-01 23:12:54','2024-08-31 23:59:59',69174765,0,'https://p0.pipi.cn/mmdb/54ecde022ff339b860d236a3def87fb6ba4db.jpg?imageView2/1/w/464/h/644',0,3.1,NULL,NULL,NULL),('1458719','海关战线','Customs Frontline','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',774788979,0,'https://p0.pipi.cn/mmdb/54ecde02ddd7e12ff7395b08218a84c3b3d60.jpg?imageView2/1/w/464/h/644',0,5,NULL,NULL,NULL),('1459024','老枪','A Long Shot','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',724249546,0,'https://p0.pipi.cn/mmdb/54ecde9a2c9b121789d7c3b0e1b86c6be537e.jpg?imageView2/1/w/464/h/644',0,1.9,NULL,NULL,NULL),('1461152','来福大酒店',NULL,'中国大陆','暂无剧情简介',NULL,'2024-05-01 23:12:54','2024-08-31 23:59:59',666408734,0,'https://p0.pipi.cn/mmdb/fb738651f2af2a1789cf3e717db280e732b2f.jpg?imageView2/1/w/464/h/644',0,8.9,NULL,NULL,NULL),('1461184','老江湖','THE OLD DOG','中国大陆','暂无剧情简介',NULL,'2023-12-01 23:12:54','2024-08-31 23:59:59',229041750,0,'https://p0.pipi.cn/mmdb/fb7386be71fddd51bae19b6d26e845815ba32.jpg?imageView2/1/w/464/h/644',0,4.4,NULL,NULL,NULL),('1461216','红色冲浪板','Labyrinth','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-03-31 23:59:59',679906247,0,'https://p0.pipi.cn/mmdb/54ecde02b1251b923587a9ec80baacf9133e0.jpg?imageView2/1/w/464/h/644',0,9.1,NULL,NULL,NULL),('1461250','六号银像','THE NO.SIX STATUE','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-03-31 23:59:59',620209712,0,'https://p0.pipi.cn/mmdb/fb73862f0fac7e57e2339e97950d29fd7d24a.jpg?imageView2/1/w/464/h/644',0,3.6,NULL,NULL,NULL),('1461260','森巴幸福岛之我要回家','I WANT TO GO HOME','中国大陆','暂无剧情简介',NULL,'2024-05-01 23:12:54','2024-08-31 23:59:59',966523838,0,'https://p0.pipi.cn/mmdb/54ecde8d0fadddbe2a21f077b44df3b770a49.jpg?imageView2/1/w/464/h/644',0,4.8,NULL,NULL,NULL),('1462635','秦淮河边一间房','A Room By The Qinhuai River','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',949768900,0,'https://p0.pipi.cn/mmdb/54ecde8d923b128077c696225aa9b9e66fc57.jpg?imageView2/1/w/464/h/644',0,0.5,NULL,NULL,NULL),('1462637','雪豹','SNOW LEOPARD','中国大陆','冬天，一只雪豹闯进了一家牧民的羊圈，咬死了九只“小牛犊子”一样的羯羊。因此，牧民父子争执不下：大儿子金巴坚持要打死这只雪豹，父亲却要执意放生，小儿子“雪豹喇嘛”得知此事，带领一组电视台记者前往采访报道，并牵引出多方的冲突与争执。从信仰和法律角度出发，众人督促金巴释放雪豹，一场生存与规则、执念与放下的探讨由此展开。在遥远的过去，一只还未成年的雪豹跳进羊圈里，咬死三只羊后被抓获，却被还未出家的“雪豹喇嘛”放走； “雪豹喇嘛”出家后，在山里闭关修行365天，迷失在山林间将要死去时，却遇到了当年被他放走的雪豹……',109,'2023-12-01 23:12:54','2024-03-31 23:59:59',7520000,0,'https://p0.pipi.cn/mmdb/54ecde029ab5bfd23c11e597f1b0621e25d34.jpg?imageView2/1/w/464/h/644',0,3.1,0,'https://p0.pipi.cn/friday/7330b6d5a1813328f24b3d5e585b9fb7.jpg?imageView2/1/w/120/h/68','https://vod.pipi.cn/fec9203cvodtransbj1251246104/aaa578241397757887369939109/v.f42905.mp4'),('1466262','我们永远是我们',NULL,'中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-03-31 23:59:59',63668095,0,'https://p0.pipi.cn/mmdb/54ecde9ad7c51b030c0fafbd73032083c7881.jpg?imageView2/1/w/464/h/644',0,8.2,NULL,NULL,NULL),('1469175','童话·世界','Fantasy ‧ World','中国台湾','影片故事取材自真实事件，揭露真实的世界，并不存在童话的真相。唯有我们开始辨识，才有办法遏制犯罪。 律师张正煦（张孝全 饰）接到一起震惊社会的补习班名师性侵案的委托，发现嫌犯汤师承（李康生饰）竟是自己刚入行执业时的客户，当时受雇于其他事务所的他，接受老板指示替嫌犯辩护，却因此严重伤害到受害者（江宜蓉饰）。17年后，张正煦为了弥补过错，决心不计代价追求属于自己的正义。',105,'2024-03-01 23:12:54','2024-03-31 23:59:59',1430000,0,'https://p0.pipi.cn/mmdb/54ecde028079238ea307acf0eab2fd48aa608.jpg?imageView2/1/w/464/h/644',0,2.9,0,'https://p0.pipi.cn/friday/320e6ebf12465f6fa166ba88c00e81d0.jpg?imageView2/1/w/120/h/68','https://vod.pipi.cn/fec9203cvodtransbj1251246104/9486ee211397757889172689380/v.f42905.mp4'),('1469785','入型入格',NULL,'中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-03-31 23:59:59',156239307,0,'https://p0.pipi.cn/mmdb/fb7386922ff2ff87a9d2360d9e0e9b9695186.jpg?imageView2/1/w/464/h/644',0,5,NULL,NULL,NULL),('1469906','深城树下','Under the City’s Tree','中国大陆','暂无剧情简介',NULL,'2024-05-01 23:12:54','2024-08-31 23:59:59',272778711,0,'https://p0.pipi.cn/mmdb/fb73862f7a3b12c7ed39dd9ac336e75922aba.jpg?imageView2/1/w/464/h/644',0,9.8,NULL,NULL,NULL),('1470629','哥斯拉大战金刚2：帝国崛起','Godzilla × Kong: The New Empire','美国','继上一次的怪兽高燃对战之后，金刚和哥斯拉将再度联手对抗一个潜伏在世界中的巨大威胁，并逐步探索这些巨兽们的起源以及骷髅岛等地的奥秘。同时，上古之战的面纱也将会被揭晓，而正是那场战斗创造出了这些超凡的生物，并深刻影响了人类世界的命运。',115,'2024-03-01 23:12:54','2024-08-31 23:59:59',875640000,0,'https://p0.pipi.cn/mmdb/54ecde02d7c2ff0faf339ed0361f0a9d4919b.jpg?imageView2/1/w/464/h/644',0,7.1,9,'https://p0.meituan.net/mmdb/ce94b381ed34cc38b1879fb8a4acf56c227011.jpg@120w_68h_1e_1c','https://vod.pipi.cn/fec9203cvodtransbj1251246104/994fc55a1397757888091051679/v.f42905.mp4'),('1477714','又是充满希望的一天','Another Day Of Hope','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',869167653,0,'https://p0.pipi.cn/mmdb/54ecde02f2ac7e02ffd23682035e2ca9254b6.jpg?imageView2/1/w/464/h/644',0,8.5,NULL,NULL,NULL),('1478921','出入平安','Give You A Candy','中国大陆','暂无剧情简介',NULL,'2023-12-01 23:12:54','2024-08-31 23:59:59',63572643,0,'https://p0.pipi.cn/mmdb/fb73869271f5bf030ccf3e275fa0ba649e6bb.jpg?imageView2/1/w/464/h/644',0,8.7,NULL,NULL,NULL),('1482170','枯叶','Kuolleet lehdet','芬兰','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',244501428,0,'https://p0.pipi.cn/mmdb/54ecde028d351b339ed7c3765f8d1f2d9eee2.jpg?imageView2/1/w/464/h/644',0,6.7,NULL,NULL,NULL),('1483129','白日之下','In Broad Daylight','中国香港','故事改编自真实事件，揭露隐藏在新闻背后、鲜为人知的残疾院舍真相。新闻社接获一宗有关残疾院舍“彩桥之家”虐待院友的线报，侦查小组为揭露残疾院舍监管问题，记者晓琪（余香凝 饰）潜入院舍，揭发内里的非人道生活，寻找日光之下的残酷真相。',106,'2024-03-01 23:12:54','2024-03-31 23:59:59',11070000,0,'https://p0.pipi.cn/mmdb/54ecde9a2c9dddc9fd51ba1648995790d85b1.jpg?imageView2/1/w/464/h/644',0,6.3,0,'https://p0.pipi.cn/friday/e4238dc84d9f92f193d2b3e95b48b3df.jpg?imageView2/1/w/120/h/68','https://vod.pipi.cn/fec9203cvodtransbj1251246104/51af4eb41397757889280241284/v.f42905.mp4'),('1486817','酱园弄','SHE\'S GOT NO NAME','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-03-31 23:59:59',726338684,0,'https://p0.pipi.cn/mmdb/fb73862f5bf5bf230f3ba374d77d27ca21697.jpg?imageView2/1/w/464/h/644',0,3.2,NULL,NULL,NULL),('1487151','我们一起摇太阳','VIVA LA VIDA','中国大陆','有笑有泪，生命万岁！韩延导演“生命三部曲”终章，当“没头脑”吕途遇上“不高兴”凌敏，两个身患重症却性格迥异的年轻人，因为“生命接力”的约定，阴差阳错地踏上了一段充满爱与力量的治愈之旅。',129,'2024-05-01 23:12:54','2024-08-31 23:59:59',260970000,0,'https://p0.pipi.cn/mmdb/54ecde9a11ef2a57e20e1358a2321f27f7a8e.jpg?imageView2/1/w/464/h/644',0,9.1,0,'https://p0.pipi.cn/friday/d7dd25c243bfa5553c242317a1a42397.jpg?imageView2/1/w/120/h/68','https://vod.pipi.cn/fec9203cvodtransbj1251246104/1ba9b54e1397757887245326050/v.f42905.mp4'),('1487331','金色面具英雄','Heroes of the Golden Mask','加拿大','很久以前，中国有一个神秘的地方叫做三星堆，三星堆城被五个佩戴着面具的英雄守卫着，富饶的国度以及珍贵的宝物玉刀引来了邪恶反派的觊觎。\n       勇士团有五名成员：中国文化的离堆和他的女儿离弦、东亚文化的祝龙、玛雅文化的祖玛、亚特兰蒂斯文化的伊索。在一场战斗中，离堆遭受致命攻击，去世前，他将带领团队的使命和自己的面具都交给了女儿离弦，并告诉她，面具能指引她找到共同守护三星堆的新的战士。通过时空隧道，离弦找到了怯懦的查理。在离弦和团队一次次的感召下，查理在与伙伴们并肩作战的过程中逐渐成长，成为了真正的勇士。\n       最后的决战中，在离弦的带领下，五位勇士团结协作，他们靠着团结、智慧和勇气，奋力战胜了邪恶反派，捍卫了三星堆，也明白了成为英雄的真正意义。',85,'2023-12-01 23:12:54','2024-08-31 23:59:59',5240000,0,'https://p0.pipi.cn/mmdb/fb73862f7e12ff339e5bf1c786bdabbb6511e.jpg?imageView2/1/w/464/h/644',0,2.9,0,'https://p0.pipi.cn/friday/9c9bace09d12ea97d841749589f6ef38.jpg?imageView2/1/w/120/h/68','https://vod.pipi.cn/fec9203cvodtransbj1251246104/dc042b7b3270835013922653092/v.f42905.mp4'),('1487834','熊孩子·探险熊兵','Bear kid：Super Hero','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',371840597,0,'https://p0.pipi.cn/mmdb/54ecdebeddd51bf2aa9ab46daff1c01bed675.png?imageView2/1/w/464/h/644',0,4.5,NULL,NULL,NULL),('1487901','冰雪女王5：融冰之战','The Snow Queen & The Princess','俄罗斯','故事发生在一个安静舒适的小镇上。主人公凯和格尔达过着平凡而幸福的生活，然而，突如其来的事件改变了一切。几个强大的冰怪物突然降临到这个小镇，将所有居民都冻结起来。整个小镇笼罩在冰雪之中，人们美好与和谐的生活被打破。凯和格尔达感到非常绝望，就在这关键时刻，冰雪公主伊拉出现在他们的面前，她带来了希望和力量。在她的帮助下，凯和格尔达终于来到了镜之地，见到了冰雪女王，经过一场激烈的战斗，最终再次封印了冰怪物们，整个小镇的居民们也终于从冰冷的束缚中解放出来。凯和格尔达也学会了珍惜友谊和勇敢面对困难，而伊拉则学会了团结的力量。',82,'2024-03-01 23:12:54','2024-08-31 23:59:59',9140000,0,'https://p0.pipi.cn/mmdb/54ecde0287ac7eecd831399143358c38e492f.jpg?imageView2/1/w/464/h/644',0,2.7,0,'https://p0.pipi.cn/friday/d738c166c99fd3d7f9292fb127bf9518.jpg?imageView2/1/w/120/h/68','https://vod.pipi.cn/fec9203cvodtransbj1251246104/f37531c73270835015121689579/v.f42905.mp4'),('1488646','间谍过家家 代号：白','Spy x Family Code: White','日本','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',969331809,0,'https://p0.pipi.cn/mmdb/54ecde9ab1251bf0ee7a3588708273d90c4f3.jpg?imageView2/1/w/464/h/644',0,1.3,NULL,NULL,NULL),('1488960','周处除三害','The Pig, the Snake and the Pigeon','中国台湾','通缉犯陈桂林生命将尽，却发现自己在通缉榜上只排名第三，他决心查出前两名通缉犯的下落，并将他们一一除掉。陈桂林以为自己已成为当代的周处除三害，却没想到永远参不透的贪嗔痴，才是人生终要面对的罪与罚。 电影引用的“周处除三害”典故，见于《晋书·周处传》和《世说新语》。据记载，少年周处身形魁梧，武力高强，却横行乡里，为邻人所厌。后周处只身斩杀猛虎孽蛟，他自己也浪子回头、改邪归正，至此三害皆除。',134,'2024-03-01 23:12:54','2024-08-31 23:59:59',663880000,0,'https://p0.pipi.cn/mmdb/54ecdebe80751becd8c9fd799d14608745656.jpg?imageView2/1/w/464/h/644',0,6.5,0,'https://p0.pipi.cn/friday/8e9d071a8258070aae3d024ccdc52ae6.jpg?imageView2/1/w/120/h/68','https://vod.pipi.cn/fec9203cvodtransbj1251246104/ef9fd2dc1397757886217492467/v.f42905.mp4'),('1489281','传说','A Legend','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',566718771,0,'https://p0.pipi.cn/mmdb/fb738602c69c6971f7c7ed721e1f7b0646ff3.jpg?imageView2/1/w/464/h/644',0,5.1,NULL,NULL,NULL),('1489349','第二十条','Article 20','中国大陆','这一年的不容易谁能懂？自打挂职到市检察院，韩明（雷佳音 饰）的糟心事就接二连三。儿子韩雨辰（刘耀文 饰）打了校领导（张译 饰）儿子并拒绝道歉，妻子李茂娟（马丽 饰）义愤填膺继而揍了校领导，补刀成功；搭档吕玲玲（高叶 饰）分歧不断，案件久拖不决；又一起案件相关人郝秀萍（赵丽颖 饰）被逼入绝境，万分危急。情与法的较量在展开，事业与家庭的平衡在进行，韩明决定赌上一切，用自己的方式给公平和正义一个说法…',141,'2024-05-01 23:12:54','2024-08-31 23:59:59',2452960000,0,'https://p0.pipi.cn/mmdb/54ecde8d2ff51b8d33300beb713232e1932ab.jpg?imageView2/1/w/464/h/644',0,8.3,0,'https://p0.pipi.cn/friday/dc1dcf2afcf8d9309fe81903f426aeeb.jpg?imageView2/1/w/120/h/68','https://vod.pipi.cn/fec9203cvodtransbj1251246104/fcbce97d388912588920458170/v.f42905.mp4'),('1489410','没有一顿火锅解决不了的事','NOTHING CAN\'T BE UNDONE BY A HOTPOT','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',602199235,0,'https://p0.pipi.cn/mmdb/54ecde022ff5bf3ba3339e40a0fd7fc9a290d.jpg?imageView2/1/w/464/h/644',0,7.6,NULL,NULL,NULL),('1489942','穿过月亮的旅行','I Love You, to the Moon, and Back','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',322439757,0,'https://p0.pipi.cn/mmdb/54ecde02e7ab12f2aaf0ee387df8c86c1e6d7.jpg?imageView2/1/w/464/h/644',0,4.6,NULL,NULL,NULL),('1490902','射雕英雄传：侠之大者',NULL,'中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',554725224,0,'https://p0.pipi.cn/mmdb/fb738687d7c338e7aa8d33b710b6554152c19.jpg?imageView2/1/w/464/h/644',0,6.8,NULL,NULL,NULL),('1490977','时间之子',NULL,'中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',374568258,0,'https://p0.pipi.cn/mmdb/fb7386d75bfddde19b395b57663f4672bd6f6.jpg?imageView2/1/w/464/h/644',0,0.1,NULL,NULL,NULL),('1490986','你就在我身边',NULL,'中国大陆','暂无剧情简介',NULL,'2024-05-01 23:12:54','2024-08-31 23:59:59',875836375,0,'https://p0.pipi.cn/mmdb/54ecde02f2addd92350e1354325972145ad7f.jpg?imageView2/1/w/464/h/644',0,5.8,NULL,NULL,NULL),('1491012','银河写手','Galaxy Writer','中国大陆','乙方的苦乙方知，打工人何时翻身你知不知？ 一个不听任何修改意见的编剧（宋木子饰），一个什么修改意见都听的编剧（合文俊饰），一个自大，一个胆大，组成银河系最强（ruo)乙方，向顶级甲方公司发起才华挑战！金钱VS理想主义，超级反套路喜剧，由宋木子领衔三狗组合吹响打工人战斗号角！ 影片于第17届FIRST青年电影展获评委会大奖、最佳编剧两项大奖。',103,'2024-03-01 23:12:54','2024-08-31 23:59:59',4230000,0,'https://p0.pipi.cn/mmdb/54ecde9a8ea9235bf1c9fd6452e4d7514a068.jpg?imageView2/1/w/464/h/644',0,6.1,0,'https://p0.pipi.cn/friday/7f35ab85d374d4b8ee988ee90ac179f1.jpg?imageView2/1/w/120/h/68','https://vod.pipi.cn/fec9203cvodtransbj1251246104/fded1e7e1397757887825307495/v.f42905.mp4'),('1491083','大“反”派','Super Villain','中国大陆','爽翻又笑翻！一言不合暴打大反派！包贝尔化身倒霉蛋惨遭碾压！\n大家好，我是毕超（包贝尔 饰），一个小演员。有一天我锦鲤附体，拿下了饰演大反派的机会。然而一场意外，我失忆了！我是谁？难道我真是“穷凶极恶”的超级大反派？之前的我竟然在策划一场绑架大富豪的计划！这一次，身为反派的我，一定要过上叱咤风云、泼天富贵的生活，就此走上人生巅峰。奈何我却像“小卡拉米”，不仅手无缚鸡之力，一路挨饿挨揍，还得罪了三位“要钱不要命”的歹徒，他们竟然想绑架我？怎么办，在线求，很急！',98,'2023-12-01 23:12:54','2024-08-31 23:59:59',47960000,0,'https://p0.pipi.cn/mmdb/54ecde02dddf2a21f07df5090816707e7334b.jpg?imageView2/1/w/464/h/644',0,7.9,0,'https://p0.pipi.cn/friday/322a4e5396eb3ae9305415b91ff8c006.jpg?imageView2/1/w/120/h/68','https://vod.pipi.cn/fec9203cvodtransbj1251246104/79431f631397757888389342846/v.f42905.mp4'),('1491086','我会好好的',NULL,'中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',193954572,0,'https://p0.pipi.cn/mmdb/54ecdebe87a7e19ab487a9becff118a4fc27f.jpg?imageView2/1/w/464/h/644',0,9.1,NULL,NULL,NULL),('1491249','错过你的那些年','青春18×2 君へと続く道','中国台湾','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',778641052,0,'https://p0.pipi.cn/mmdb/54ecde02e7ac7e9235d23ce9a4c9c3735e82a.jpg?imageView2/1/w/464/h/644',0,3.1,NULL,NULL,NULL),('1491310','志愿军：雄兵出击2',NULL,'中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',741703743,0,'https://p0.pipi.cn/mmdb/54ecde9a9235bf2ff78d338cb8572c5a1413c.jpg?imageView2/1/w/464/h/644',0,9.8,NULL,NULL,NULL),('1491711','流浪地球3',NULL,'中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',791018502,0,'https://p0.pipi.cn/mmdb/fb7386929ab5bfb12dbe2a0f7cee6d639cde0.jpg?imageView2/1/w/464/h/644',0,4.9,NULL,NULL,NULL),('1491810','发小儿万岁','LONG LIVE FRIENDSHIP','中国大陆','暂无剧情简介',NULL,'2024-05-01 23:12:54','2024-08-31 23:59:59',302976602,0,'https://p0.pipi.cn/mmdb/fb73862f338b12b86092357453533b22df047.jpg?imageView2/1/w/464/h/644',0,7.6,NULL,NULL,NULL),('1491824','沙海之门','The sand murmurs','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',425979048,0,'https://p0.pipi.cn/mmdb/fb7386712ff51b02ff537c8f8ac86212bb15b.jpg?imageView2/1/w/464/h/644',0,7.9,NULL,NULL,NULL),('1495728','飞驰人生2','Pegasus 2','中国大陆','超欢乐！真刺激！好感动！沈腾领衔喜剧大片高口碑热映中！电影工业领先制作水平带来沉浸视听极致燃爽！一句“能扛住的，一起啊”送给五年前的你，这一次，不留一丝遗憾！ 昔日冠军车手张驰（沈腾 饰）沦为落魄驾校教练，过着靠脸吃饭勉强度日的生活。不料天上掉馅饼，濒临停产的老头乐车厂厂长（贾冰 饰）主动提出赞助张驰组建车队再闯最后一届巴音布鲁克拉力赛。面对这泼天的富贵，张驰蠢蠢欲动，而厂长背后真实的目的无人知晓……带资进队的车手厉小海（范丞丞 饰），看似单纯善良却为何有着与之身份毫不相符的超强天赋？ 张驰召集老朋友孙宇强（尹正 饰）和记星（张本煜 饰），与天才车手厉小海和总是考不过科目二的驾校学员刘显德（孙艺洲 饰）组成了一个苦中作乐、鸡飞狗跳的草台班子，克服重重困难，笑料百出地奔赴赛场。这一次他将超越无数次出现在梦中的终点线……',121,'2023-12-01 23:12:54','2024-08-31 23:59:59',3396960000,0,'https://p0.pipi.cn/mmdb/54ecde9a51bc7e02ff16bdc78da10d19f01f5.jpg?imageView2/1/w/464/h/644',0,1.5,0,'https://p0.pipi.cn/friday/03204b21f1067c4177bab3ba7f50cbd5.jpg?imageView2/1/w/120/h/68','https://vod.pipi.cn/fec9203cvodtransbj1251246104/419d4f31388912589079772959/v.f42905.mp4'),('1495758','猪猪侠大电影·星际行动','Interstellar Mission','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',697806736,0,'https://p0.pipi.cn/mmdb/54ecde9a06d923d7c3d23cc5986058faefeba.jpg?imageView2/1/w/464/h/644',0,8,NULL,NULL,NULL),('1498153','野孩子','stand by me','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',929421773,0,'https://p0.pipi.cn/mmdb/fb738692f2a2ffbe127a35a8549bf2fa73b32.jpg?imageView2/1/w/464/h/644',0,2.1,NULL,NULL,NULL),('1499749','高中的我们','Our Youth','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',897726396,0,'https://p0.pipi.cn/mmdb/54ecdebe7e1ddd50c89ab47c2ec5736c140d6.jpg?imageView2/1/w/464/h/644',0,8.2,NULL,NULL,NULL),('1500234','熊出没·逆转时空','Boonie Bears:Time Twist','中国大陆','光头强是一名普通“打工人”，却常梦见陌生的森林和两头狗熊。直到他跟上司出访大客户，终于想起：他原是森林的小导游，偶然得到一次重新选择人生的机会。为了挽救旧时间线里的熊大熊二，光头强开始一场奇妙的时空冒险。',108,'2024-03-01 23:12:54','2024-08-31 23:59:59',1989730000,0,'https://p0.pipi.cn/mmdb/54ecde9a7a39232c950faf5a65e3981459da6.jpg?imageView2/1/w/464/h/644',0,2.3,0,'https://p0.pipi.cn/friday/0250f3c2f6126363e9eb968d09c11d17.jpg?imageView2/1/w/120/h/68','https://vod.pipi.cn/43903a81vodtransgzp1251246104/d8783e66388912588468850563/v.f42905.mp4'),('1500516','恐怖直播',NULL,'中国大陆','暂无剧情简介',NULL,'2024-05-01 23:12:54','2024-08-31 23:59:59',388328588,0,'https://p0.pipi.cn/mmdb/fb7386dd71fddd11e507ace537d8b0dd17f3e.jpg?imageView2/1/w/464/h/644',0,2.4,NULL,NULL,NULL),('1501146','哆啦A梦：大雄的地球交响乐','映画ドラえもん のび太の地球交響楽','日本','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',841337977,0,'https://p0.pipi.cn/mmdb/54ecde022ff51be7aa71f7fa47c3fa14b7adb.jpg?imageView2/1/w/464/h/644',0,6.4,NULL,NULL,NULL),('1501342','那个不为人知的故事',NULL,'中国大陆','暂无剧情简介',NULL,'2023-12-01 23:12:54','2024-08-31 23:59:59',335391447,0,'https://p0.pipi.cn/mmdb/fb7386925bf51b71f78077fbab1a49dd46548.jpg?imageView2/1/w/464/h/644',0,1,NULL,NULL,NULL),('1501854','拼桌',NULL,'中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',711626082,0,'https://p0.pipi.cn/mmdb/fb738692be15bf807787a918dc0ba39fbc227.jpg?imageView2/1/w/464/h/644',0,2,NULL,NULL,NULL),('1502895','我是霸王龙2：无敌霸王龙','Tyrannosaurus Rex 2','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',901196027,0,'https://p0.pipi.cn/mmdb/54ecdebe06dddd16bdaf339840691424fba8a.jpg?imageView2/1/w/464/h/644',0,6.1,NULL,NULL,NULL),('1504105','狂野时代',NULL,'中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',723285806,0,'https://p0.pipi.cn/mmdb/fb7386dd71f7e18d33e5bc2c7267bebbfea66.jpg?imageView2/1/w/464/h/644',0,9.1,NULL,NULL,NULL),('1504991','猎杀',NULL,'中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',665626615,0,'https://p0.pipi.cn/mmdb/fb7386dd06d51b395b339e1b3bab18558e602.jpg?imageView2/1/w/464/h/644',0,3.6,NULL,NULL,NULL),('1505776','即兴谋杀',NULL,'中国大陆','暂无剧情简介',NULL,'2024-05-01 23:12:54','2024-08-31 23:59:59',604740655,0,'https://p0.pipi.cn/mmdb/fb73862f02f51b67cb11e534c4a15ff5ed744.jpg?imageView2/1/w/464/h/644',0,5.4,NULL,NULL,NULL),('1511023','午夜6号房','Memory Reorganization','中国大陆','影片根据“酒店尾房闹鬼事件”改编。宾馆走廊最后房间有脏东西被压着无法升天，开门和开柜子要先敲三下，要留点不见光的地方。 任诚和任静深夜路遇荒野破旧酒店，住进走廊尽头尚好尾房。两人关灯刚要睡下，灯和空调莫名自启，卫生间传来水声和人走动声，但猛推门却一切正常。当两人再次躺到床上，却惊现悬在半空中的身体一点点压下，空洞眼珠死死盯着他们…… 任静极度惊吓，任诚外出寻求帮助回来后，任静已不在房内，互相通话后才发现似在两个平行时空，无法相遇…… 现在，一对采访者正在实地采访这对情侣，采访者诉说了当时另一个更为诡异的真相，噩梦般的事情又重现了，真正恐怖的事情，才刚刚开始……',88,'2024-03-01 23:12:54','2024-08-31 23:59:59',4920000,0,'https://p0.pipi.cn/mmdb/54ecdebe80751bc6968d33bddf80616ed93e3.jpg?imageView2/1/w/464/h/644',0,5.5,0,'https://p0.pipi.cn/friday/a456c46413240e72848bb7978a69b99a.jpg?imageView2/1/w/120/h/68','https://vod.pipi.cn/fec9203cvodtransbj1251246104/334475df1397757885592856290/v.f42905.mp4'),('1511345','伞少女',NULL,'中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-03-31 23:59:59',40532536,0,'https://p0.pipi.cn/mmdb/fb738692c7e51bc696e5bcdff871aadb3e184.jpg?imageView2/1/w/464/h/644',0,4.8,NULL,NULL,NULL),('1515431','寻秘自然：地球往事','SEEK OUT NATURAL MYSTERIES: A LOOK INTO THE PAST OF EARTH','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',433462398,0,'https://p0.pipi.cn/mmdb/54ecde9ac692ff02ff06d627c65759065c7a8.jpg?imageView2/1/w/464/h/644',0,6.6,NULL,NULL,NULL),('1515993','潜艇总动员：寻龙','The Adventure with Dragon','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',102869006,0,'https://p0.pipi.cn/mmdb/54ecde02e7a0fae19b39ddbd0e76efb9b694a.jpg?imageView2/1/w/464/h/644',0,1.8,NULL,NULL,NULL),('1516605','印痕','LOVE BEHIND THE WHEELS','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',638780426,0,'https://p0.pipi.cn/mmdb/54ecde02e7a5bfe7aa51bafab38defa3eb090.jpg?imageView2/1/w/464/h/644',0,2.4,NULL,NULL,NULL),('1517316','遇见美好','Too Simple','中国大陆','暂无剧情简介',NULL,'2024-05-01 23:12:54','2024-08-31 23:59:59',265673215,0,'https://p0.pipi.cn/mmdb/54ecde8df2a338d7c3c7ed0ed49865b2057b7.jpg?imageView2/1/w/464/h/644',0,3.7,NULL,NULL,NULL),('1518388','替身纸人2','Paper Man2','中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',188583496,0,'https://p0.pipi.cn/mmdb/54ecde9a3380fa7a3539ddcce5cc94b86c4a7.png?imageView2/1/w/464/h/644',0,5.3,NULL,NULL,NULL),('1520391','江南：在爱开始的地方等你','JIANG NAN','中国大陆','暂无剧情简介',NULL,'2023-12-01 23:12:54','2024-08-31 23:59:59',83988068,0,'https://p0.pipi.cn/mmdb/54ecde9a02f51b5bf171f790898217cb8ecac.png?imageView2/1/w/464/h/644',0,9,NULL,NULL,NULL),('1523680','脑洞大开',NULL,'中国大陆','暂无剧情简介',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',685314846,0,'https://p0.pipi.cn/mmdb/54ecde9a7a351bcf3eb12dac6613119719edb.jpg?imageView2/1/w/464/h/644',0,4.9,NULL,NULL,NULL),('1525094','长风万里','AMBASSADOR FROM HAN EMPIRE','中国大陆','公元73年，班超“投笔从戎”,率三十六人出使西 域，以“不入虎穴焉得虎子”的英雄气概，收部善、破莎 车、胜龟兹，打败北匈奴，设立西域都护府，打通了尘封 65年之久的丝绸之路，西域再次回到东汉中央王朝的怀抱。 人之初，性本善——班超传播和推广汉文化，并将中原先进农业技术带到了西域，开挖井渠灌溉农田，羊肥马壮，国富民丰，安居乐业，一派祥和的太平盛世。一位英俊的少年骑了一匹骏马飞驰而来，他叫班勇，是班超与长夏之子。',NULL,'2024-05-01 23:12:54','2024-08-31 23:59:59',394060664,0,'https://p0.pipi.cn/mmdb/54ecde02f2a51b39dd230f5e35a08c28c870e.jpg?imageView2/1/w/464/h/644',0,3.4,NULL,NULL,NULL),('225980','哈尔的移动城堡','Howl\'s Moving Castle','日本','宫崎骏口碑佳作，和哈尔来场春日的约会！ 一个拥有一切令女孩心动钟情元素的爱情童话冒险故事，纯真、挚爱、美颜、魔法、冒险、奇观、可爱等一应俱全。在神奇的魔法世界里，哈尔是个传说，是少女倾慕又恐惧的对象，传说他魅力无边又法力无穷，驾驶着骇人的移动城堡神出鬼没，窃食少女心脏。苏菲只是一个平凡无奇的女孩，一次危机之下偶然被哈尔所救，由此人生急转直下陷入魔法的诅咒之中，也牵扯出了她与哈尔的一段跨越时空、历劫生死的冒险成长故事……原来这个世界真有魔法，诞生于勇敢与爱情携手共舞中。',NULL,'2023-12-01 23:12:54','2024-08-31 23:59:59',127828474,0,'https://p0.pipi.cn/mmdb/54ecde0251b923af3339ddbc1bc7a0b59aae9.jpg?imageView2/1/w/464/h/644',0,2.4,NULL,NULL,NULL),('246589','过渡空间',NULL,'中国大陆','此生渡彼岸，往生者来到关又，一个执念者的世界——过渡空间。反劫持谈判专家宋琂在这里遇到她职业生涯中最强大的对手，过渡空间的最高长官孙穆。面对这个从不按常理出牌的女人，孙穆感受到前所未有的威胁。两个内心充满执念的强者，同一时空狭路相逢，一个竭尽全力要留下，一个孤注一掷要离开。一场智慧与勇气的对决拉开大幕，一段段尘封往事浮出水面......没有人知道专职劝服他人放下执念过关的孙穆为何长久于过渡空间流连，没有人理解不顾危急搏命闯关的宋琂如此迫切重返现实世界的执念。是救赎，还是解脱?曾经现世的恩怨情仇，在超现实的过渡空间汇聚，交叠重映，亲情、友情、爱情，一一试炼。',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',70344295,0,'https://p0.pipi.cn/mmdb/54ecde8d71fb120fafd23ce57e80381c5fe66.jpg?imageView2/1/w/464/h/644',0,3.7,NULL,NULL,NULL),('342288','抗战中的文艺（舞台纪录电影·话剧）','RESISTANCE INK: THE LITERARY AND ARTS UPRISING','中国大陆','《抗战中的文艺》以波澜壮阔的笔触，真情再现1931年至1945年，胸怀爱国之志的文艺家们的求索、挣扎、奋斗，寻找中国的文艺出路。抗战结束之际，文艺家们汇聚延安，在党的文艺方针的正确指引下，确立了以人民为中心的创作方向。片中涉及鲁迅、郭沫若、茅盾、巴金、老舍、金山、秦怡、张瑞芳、白杨、舒绣文、夏衍、田汉、聂耳、冼星海、贺敬之、丁玲等文艺名家，左翼文学、戏剧、电影、音乐、美术等艺术样式，以当代舞台语汇集中展现中国文艺的精神和力量。',129,'2024-05-01 23:12:54','2024-08-31 23:59:59',60000,0,'https://p0.pipi.cn/mmdb/54ecde02339923395bc7ed51e432acb3a4573.jpg?imageView2/1/w/464/h/644',0,5.3,0,'https://p0.pipi.cn/friday/3aaa434be1a666f5b8ef022773c9aa20.jpg?imageView2/1/w/120/h/68','https://vod.pipi.cn/fec9203cvodtransbj1251246104/8b07d12d1253642695541349937/v.f42905.mp4'),('342906','非凡任务','Extraordinary Mission','中国大陆','林凯（黄轩饰）是李建国（祖峰饰）安插在贩毒集团的卧底，他逐步渗透到“双鹰”贩毒集团后，发现幕后头目“老鹰”（段奕宏饰）与八年前李建国作为卧底调查的贩毒集团有关，还囚禁了当时李建国的上级，最终林凯与李建国巧妙配合捣毁了整个贩毒集团。',120,'2024-03-01 23:12:54','2024-08-31 23:59:59',156410000,0,'https://p0.pipi.cn/mmdb/d2dad5927e10fab12d030cfe753719762d228.jpg?imageView2/1/w/464/h/644',0,9,0,'https://p1.meituan.net/movie/3084d18d1cfc2f1c8216745dde25b5cf21191.jpg@120w_68h_1e_1c','https://vod.pipi.cn/fe5b84bcvodcq1251246104/7d5ef6d15285890796583934001/f0.mp4'),('343526','我是哪吒','I am NeZha','中国大陆','取材自封神演义的部分故事，主要讲述哪吒从一个顽劣少年蜕变成一个富有正义感且勇于献身的小英雄的成长过程。本片以人龙战争为背景，再现封神演义中哪吒闹海、哪吒重生等经典桥段。殷商末年，东海大将夜叉密谋篡权，企图暗害龙王之女以达成他统治东海的目的。不料其阴谋被陈塘关总兵李靖之子哪吒无意撞到。为了掩人耳目，夜叉将暗害龙女的罪名嫁祸给哪吒，将其变为龙族的公敌。而在另外一方，哪吒受怪仙太乙真人之命前往金光洞修道，途中遇到了遭遇夜叉暗害而失去记忆的龙女，以及精通变化和遁地之术的土行孙，三人都被太乙真人收为门徒。就在哪吒一行人潜习法术之时，夜叉却率龙宫重兵来到了陈塘关，大战一触即发。',90,'2024-03-01 23:12:54','2024-08-31 23:59:59',10170000,0,'https://p0.pipi.cn/mmdb/d2dad5927e10fa8077e5bc35bb2583de7d341.jpg?imageView2/1/w/464/h/644',0,4.9,0,'https://p0.meituan.net/movie/437f368cfd80b273f1f095585fc38d9f29835.jpg@120w_68h_1e_1c','https://vod.pipi.cn/fe5b84bcvodcq1251246104/9c5b99715285890796587044140/f0.mp4'),('475180','功夫熊猫4','Kung Fu Panda 4','美国','爷青回！《功夫熊猫》新作来袭！阿宝“升职”同时，新仇旧敌集结现身，大龙竟起死回生？狐狸小真身份神秘，到底有何心机？ 阿宝被师傅要求选出下一任神龙大侠，正苦恼如何应对时，阿宝昔日的手下败将们却纷纷重出江湖！身世神秘的狐狸小真告诉阿宝，这一切的幕后黑手正是邪恶女巫魅影妖后！阿宝能否打败魅影妖后，昔日敌人又为何再次现身？这次又会发生怎样的搞笑趣事？赶快带上亲朋好友，一起到影院寻找真相吧～',94,'2024-03-01 23:12:54','2024-08-31 23:59:59',342520000,0,'https://p0.pipi.cn/mmdb/54ecde9ab12923537c8ea354a4957f4c59bb7.jpg?imageView2/1/w/464/h/644',0,3.4,0,'https://p0.pipi.cn/friday/9d02dbfaa392b63844cfd93eb6e57a9a.png?imageView2/1/w/120/h/68','https://vod.pipi.cn/fec9203cvodtransbj1251246104/a63d415f1397757886566828037/v.f42905.mp4'),('58747','数码宝贝02：最初的召唤','デジモンアドベンチャー02','日本','2012年2月，东京铁塔上空出现了巨大的数码蛋。就在本宫大辅（20岁）和V仔兽修行拉面技艺的时候，遇到了虽然拿着有裂纹的数码器，但身边没有搭档数码兽的迷之青年——自称“我没有数码兽，我的搭档数码兽被我杀了，我是世界上最初和数码兽建立搭档关系的人类。”',81,'2024-05-01 23:12:54','2024-08-31 23:59:59',11190000,0,'https://p0.pipi.cn/mmdb/54ecde02f2a51b7a35cf3e28aed28bb67cc89.jpg?imageView2/1/w/464/h/644',0,3.7,0,'https://p0.pipi.cn/friday/f04f7c8d44fffd803be15463bf5d207b.jpg?imageView2/1/w/120/h/68','https://vod.pipi.cn/fec9203cvodtransbj1251246104/4564ad841253642695540694260/v.f42905.mp4'),('672148','从21世纪安全撤离','Evacuate from the 21st Century','中国大陆','如果一个喷嚏，就让18岁你的灵魂附着在20年后你的身体里，会是什么情况？如果凑巧拯救了世界呢？三名有着18岁中学生内心的38岁大男孩在20年后的世界里横冲直撞，上演了一出荒诞有趣，热血感人的冒险故事。',NULL,'2024-03-01 23:12:54','2024-08-31 23:59:59',455495315,0,'https://p0.pipi.cn/mmdb/54ecde9a87a923b86011e5296cd9ab912a72d.jpg?imageView2/1/w/464/h/644',0,5.3,NULL,NULL,NULL),('78645','九龙城寨之围城','Twilight of the Warriors: Walled','中国大陆','真人版热血番，五一档动作爽片！上世纪八十年代，恶名昭著的“三不管”地带九龙城寨中黑帮盘踞，危机四伏。一位落难青年因逃难误闯，却意外在此收获兄弟情义。与此同时，觊觎城寨许久的恶人帮掀起了一轮轮夺寨狠斗，保卫家寨的高燃热血之战随即爆发！ 本片根据余儿原著热血格斗小说《九龙城寨》改编。',NULL,'2024-05-01 23:12:54','2024-08-31 23:59:59',631142494,0,'https://p0.pipi.cn/mmdb/54ecde02e7a339b12d338fc612e9508beaacf.jpg?imageView2/1/w/464/h/644',0,9,NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_movietype`
--

DROP TABLE IF EXISTS `t_movietype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_movietype` (
  `movie_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `type_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`movie_id`,`type_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_movietype`
--

LOCK TABLES `t_movietype` WRITE;
/*!40000 ALTER TABLE `t_movietype` DISABLE KEYS */;
INSERT INTO `t_movietype` VALUES ('1165777','4'),('1198578','12'),('1198578','3'),('1198578','4'),('1220935','4'),('1220935','7'),('1221474','2'),('1221474','4'),('1228248','4'),('1250661','5'),('1251242','2'),('1259816','4'),('1285828','2'),('1290415','4'),('1291017','4'),('1298240','9'),('1298495','12'),('1298556','4'),('1299156','4'),('1299355','11'),('1301249','1'),('1301552','9'),('1302431','3'),('1307114','12'),('1309779','7'),('1310023','13'),('1310520','1'),('1320548','17'),('1328836','13'),('1336423','11'),('1339006','24'),('1340084','9'),('1350281','28'),('1351778','9'),('1355287','10'),('1357867','20'),('1362705','12'),('1370725','3'),('1371004','28'),('1371016','9'),('1372970','20'),('1374577','7'),('1374909','20'),('1379047','12'),('1383679','2'),('1385816','7'),('1385822','8'),('1394072','3'),('1395127','24'),('1399450','10'),('1405602','14'),('1413549','11'),('1413550','5'),('1418942','13'),('1419080','24'),('1422798','24'),('1423910','28'),('1424182','23'),('1426142','27'),('1427828','26'),('1427883','21'),('1427961','14'),('1431842','3'),('1432552','16'),('1432921','10'),('1434029','14'),('1434971','17'),('1434993','13'),('1435067','14'),('1437173','1'),('1441786','12'),('1446251','15'),('1446323','8'),('1448767','5'),('1448919','20'),('1451499','2'),('1456267','23'),('1458719','3'),('1459024','26'),('1461152','3'),('1461184','26'),('1461216','1'),('1461250','15'),('1461260','27'),('1462635','9'),('1462637','14'),('1466262','27'),('1469175','27'),('1469785','27'),('1469906','10'),('1470629','21'),('1477714','6'),('1478921','20'),('1482170','2'),('1483129','2'),('1486817','29'),('1487151','17'),('1487331','21'),('1487834','27'),('1487901','24'),('1488646','24'),('1488960','23'),('1489281','28'),('1489349','12'),('1489410','10'),('1489942','6'),('1490902','25'),('1490977','21'),('1490986','20'),('1491012','17'),('1491083','20'),('1491086','22'),('1491249','12'),('1491310','4'),('1491711','9'),('1491810','18'),('1491824','28'),('1495728','9'),('1495758','7'),('1498153','26'),('1499749','10'),('1500234','3'),('1500516','2'),('1501146','21'),('1501342','27'),('1501854','29'),('1502895','16'),('1504105','3'),('1504991','28'),('1505776','25'),('1511023','22'),('1511345','29'),('1515431','20'),('1515993','1'),('1516605','12'),('1517316','15'),('1518388','29'),('1520391','9'),('1523680','1'),('1525094','7'),('225980','14'),('246589','20'),('342288','9'),('342906','14'),('343526','22'),('475180','13'),('58747','25'),('672148','26'),('78645','7');
/*!40000 ALTER TABLE `t_movietype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order`
--

DROP TABLE IF EXISTS `t_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_order` (
  `order_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `order_date` datetime NOT NULL,
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order`
--

LOCK TABLES `t_order` WRITE;
/*!40000 ALTER TABLE `t_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_performer`
--

DROP TABLE IF EXISTS `t_performer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_performer` (
  `performer_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `english_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `birthday` date DEFAULT NULL,
  PRIMARY KEY (`performer_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_performer`
--

LOCK TABLES `t_performer` WRITE;
/*!40000 ALTER TABLE `t_performer` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_performer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_role` (
  `movie_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `performer_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`movie_id`,`performer_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_scenes`
--

DROP TABLE IF EXISTS `t_scenes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_scenes` (
  `scenes_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `hall_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `movie_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `date_start` datetime NOT NULL,
  `date_end` datetime NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`scenes_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_scenes`
--

LOCK TABLES `t_scenes` WRITE;
/*!40000 ALTER TABLE `t_scenes` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_scenes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_ticket`
--

DROP TABLE IF EXISTS `t_ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_ticket` (
  `scenes_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `order_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `seat_no` int NOT NULL,
  PRIMARY KEY (`scenes_id`,`order_id`,`seat_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_ticket`
--

LOCK TABLES `t_ticket` WRITE;
/*!40000 ALTER TABLE `t_ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_type`
--

DROP TABLE IF EXISTS `t_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_type` (
  `type_id` int NOT NULL,
  `type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_type`
--

LOCK TABLES `t_type` WRITE;
/*!40000 ALTER TABLE `t_type` DISABLE KEYS */;
INSERT INTO `t_type` VALUES (1,'爱情'),(2,'喜剧'),(3,'动画'),(4,'剧情'),(5,'恐怖'),(6,'惊悚'),(7,'动作'),(8,'悬疑'),(9,'犯罪'),(10,'冒险'),(11,'战争'),(12,'奇幻'),(13,'运动'),(14,'家庭'),(15,'古装'),(16,'武侠'),(17,'西部'),(18,'历史'),(19,'传记'),(20,'歌舞'),(21,'黑色电影'),(22,'短片'),(23,'纪录片'),(24,'戏曲'),(25,'音乐'),(26,'灾难'),(27,'青春'),(28,'儿童'),(29,'其他');
/*!40000 ALTER TABLE `t_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user` (
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `gender` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '保密',
  `birth` date DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES ('1','2879402384@qq.com','root','root','保密',NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-25  8:13:57
