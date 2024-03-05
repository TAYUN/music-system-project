/*
 Navicat Premium Data Transfer

 Source Server         : mysql57
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : music

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 31/05/2023 18:52:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '后台管理员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '1');

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '收藏类型：0歌曲，1歌单',
  `song_id` int(11) NULL DEFAULT NULL COMMENT '歌曲id',
  `song_list_id` int(11) NULL DEFAULT NULL COMMENT '歌单id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '收藏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collection
-- ----------------------------
INSERT INTO `collection` VALUES (1, 1, 0, 14, NULL, '2023-03-24 20:45:36');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '收藏类型：0歌曲，1歌单',
  `song_id` int(11) NULL DEFAULT NULL COMMENT '歌曲id',
  `song_list_id` int(11) NULL DEFAULT NULL COMMENT '歌单id',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `up` int(11) NULL DEFAULT NULL COMMENT '点赞数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 13, 1, NULL, 2, '我是wewewe，第一条评论', NULL, NULL);
INSERT INTO `comment` VALUES (2, 13, 1, NULL, 3, 'hello，wewew', NULL, NULL);
INSERT INTO `comment` VALUES (3, 13, 1, NULL, 11, '3435', NULL, NULL);
INSERT INTO `comment` VALUES (4, 13, 1, NULL, 10, '评论测试', NULL, NULL);
INSERT INTO `comment` VALUES (5, 13, 1, NULL, 10, '好好听歌', NULL, NULL);

-- ----------------------------
-- Table structure for consumer
-- ----------------------------
DROP TABLE IF EXISTS `consumer`;
CREATE TABLE `consumer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `sex` tinyint(1) NULL DEFAULT NULL COMMENT '性别：1男，0女',
  `phone_num` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `email` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `birth` datetime(0) NULL DEFAULT NULL COMMENT '生日',
  `introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个签',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地区',
  `avator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '前端用户管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of consumer
-- ----------------------------
INSERT INTO `consumer` VALUES (1, 'libd', '1', 0, '111', '11@11.com', '2023-03-29 08:00:00', '111333', '中国北京', '/img/userPic/1681897223805小梦.jpg', '2023-03-24 19:03:02', '2023-04-19 17:40:24');
INSERT INTO `consumer` VALUES (2, 'admin', '1', 0, '222', '22@qq.com', '2023-03-29 08:00:00', '222333', '222', '/img/userPic/1681897229732鏡音レン.jpg', '2023-03-24 19:03:52', '2023-04-19 17:40:30');
INSERT INTO `consumer` VALUES (3, '1111111', '1', 1, '111111', '111@qq.com', '2023-03-22 08:00:00', '222', '222', '/img/userPic/user.jpeg', '2023-03-24 19:34:01', '2023-03-24 21:46:11');
INSERT INTO `consumer` VALUES (4, '1212', '1', 1, '111', '111@ww.co', '2023-03-30 08:00:00', 'www', 'wwwwwwwwwwww', '/img/userPic/user.jpeg', '2023-03-24 20:06:46', NULL);
INSERT INTO `consumer` VALUES (5, '111122222', '1', 1, '2222', '222@qq.com', '2023-03-28 08:00:00', '0000000000000', '222222222', '/img/userPic/user.jpeg', '2023-03-24 20:13:22', '2023-03-24 21:44:04');
INSERT INTO `consumer` VALUES (6, '111', '111', NULL, NULL, 'werwe@qq.com', NULL, NULL, NULL, '/img/user.jpeg', NULL, NULL);
INSERT INTO `consumer` VALUES (7, '222', '111', NULL, NULL, '11@qq.com', NULL, NULL, NULL, '/img/user.jpeg', NULL, NULL);
INSERT INTO `consumer` VALUES (8, '333', '111', NULL, NULL, 'werwe@11.ocm', NULL, NULL, NULL, '/img/user.jpeg', NULL, NULL);
INSERT INTO `consumer` VALUES (9, '112', '111', NULL, NULL, '111@ww.co', NULL, NULL, NULL, '/img/userPic/1680788779645003.jpg', NULL, '2023-04-06 21:46:20');
INSERT INTO `consumer` VALUES (10, '1112', '111', NULL, NULL, 'werwe!@sas.cv', NULL, NULL, NULL, '/img/user.jpeg', NULL, NULL);
INSERT INTO `consumer` VALUES (11, '113', '111', NULL, NULL, 'werwe@qq.cd', NULL, NULL, NULL, '/img/user.jpeg', NULL, NULL);
INSERT INTO `consumer` VALUES (12, '114', '111', NULL, NULL, 'werwe@121.cd', NULL, NULL, NULL, '/img/user.jpeg', NULL, NULL);
INSERT INTO `consumer` VALUES (13, 'werwe', 'werw', 0, '34234221wwwqweq', 'qweq@wew.cow', '2023-04-01 00:00:00', 'werqwerqdsdasa', '天津', '/img/userPic/1680824137908boy.jpeg', NULL, '2023-05-11 16:50:44');
INSERT INTO `consumer` VALUES (14, 'tomcat', '111', 1, '15696374892', 'erwe@qq.com', '2023-05-10 08:00:00', 'sfdsdf', 'dfsadfasdf', '/img/userPic/user.jpeg', NULL, NULL);

-- ----------------------------
-- Table structure for ranks
-- ----------------------------
DROP TABLE IF EXISTS `ranks`;
CREATE TABLE `ranks`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '\r\n',
  `song_list_id` int(11) NULL DEFAULT NULL COMMENT '歌单id',
  `consumer_id` int(11) NULL DEFAULT NULL COMMENT '用户id\r\n',
  `score` int(11) NULL DEFAULT NULL COMMENT '分数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '评价分' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ranks
-- ----------------------------

-- ----------------------------
-- Table structure for singer
-- ----------------------------
DROP TABLE IF EXISTS `singer`;
CREATE TABLE `singer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '歌手姓名',
  `sex` tinyint(1) NULL DEFAULT NULL COMMENT '性别：1男，0女',
  `pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '歌手图片',
  `birth` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '生日',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '歌手' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of singer
-- ----------------------------
INSERT INTO `singer` VALUES (24, '洛天依', 0, '/img/singerPic/1684565094471user.png', '2023-3-1', '中国网络', '虚拟歌手');
INSERT INTO `singer` VALUES (25, '杨幂', 0, '/img/singerPic/1684565570531003.jpg', '2023-3-14', '中国山东', '代表作  爱的供养');
INSERT INTO `singer` VALUES (26, '张杰', 1, '/img/singerPic/1684564620078张杰.jpg', '2023-3-14', '美国', '歌神');
INSERT INTO `singer` VALUES (29, '陈奕迅', 1, '/img/singerPic/16845655095668fb806ed31d0e506445824856feccead.jpg', '2023-3-1', '中国香港', '十年');
INSERT INTO `singer` VALUES (30, '林俊杰', 1, '/img/singerPic/1684565685557张杰.jpg', '2023-3-4', '新加坡', '囧很丧丁敬槐就');
INSERT INTO `singer` VALUES (31, '李牧', 1, '/img/singerPic/16845655434568fb806ed31d0e506445824856feccead.jpg', '2023-5-10', '广东', '广工');
INSERT INTO `singer` VALUES (32, '白起', 2, '/img/singerPic/1684565536455003.jpg', '2023-5-18', '广西', '南方');
INSERT INTO `singer` VALUES (33, '汪峰', 1, '/img/singerPic/1684565125074user.png', '2023-5-3', '湖北', '歌神');

-- ----------------------------
-- Table structure for song
-- ----------------------------
DROP TABLE IF EXISTS `song`;
CREATE TABLE `song`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gq_singer_id` int(11) NULL DEFAULT NULL COMMENT '歌手id\r\n',
  `gq_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '歌名',
  `gq_introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '歌曲简介',
  `gq_create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gq_update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `gq_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '歌曲图片',
  `gq_lyric` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '歌词',
  `gq_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '歌曲地址',
  `click_count` int(255) NULL DEFAULT NULL COMMENT '歌曲被点击数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '歌曲' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of song
-- ----------------------------
INSERT INTO `song` VALUES (15, 26, '张杰-我们都一样', '年轻', '2023-03-23 16:11:09', '2023-05-31 17:59:38', '/img/songPic/1680528058355003.jpg', '[ti:我们都一样 (Live)]\r\n[ar:吴汶芳/张杰]\r\n[al:最美和声第三季 第2期]\r\n[by:]\r\n[offset:0]\r\n[00:00.39]我们都一样 (Live) - 吴汶芳/张杰 (Jason Zhang)\r\n[00:03.47]词：苟庆,曹轩宾\r\n[00:05.24]曲：曹轩宾\r\n[00:06.77]\r\n[00:18.09]芳：推开窗看见星星\r\n[00:22.68]依然守在夜空中\r\n[00:25.86]\r\n[00:27.48]心中不免多了些暖暖的感动\r\n[00:34.32]\r\n[00:36.14]一闪一闪的光\r\n[00:39.40]\r\n[00:40.64]努力把黑夜点亮\r\n[00:45.71]气氛如此安详\r\n[00:49.72]\r\n[00:53.12]你在我的生命中\r\n[00:56.49]是那最闪亮的星\r\n[01:01.38]一直在无声夜空\r\n[01:04.86]守护着我们的梦\r\n[01:07.96]\r\n[01:10.26]这世界那么大\r\n[01:14.24]\r\n[01:14.78]我的爱只想要你懂\r\n[01:19.97]陪伴我无尽旅程\r\n[01:25.53]合：你知道我的梦\r\n[01:28.61]你知道我的痛\r\n[01:30.61]你知道我们感受都相同\r\n[01:34.82]就算有再大的风\r\n[01:37.22]也挡不住勇敢的冲动\r\n[01:43.21]努力地往前飞 再累也无所谓\r\n[01:47.24]黑夜过后的光芒有多美\r\n[01:51.38]分享你我的力量\r\n[01:53.87]就能把对方的路照亮\r\n[02:03.24]\r\n[02:20.10]杰：我想我们都一样\r\n[02:24.41]渴望梦想的光芒\r\n[02:27.48]\r\n[02:29.46]这一路喜悦彷徨\r\n[02:32.81]不要轻易说失望\r\n[02:35.93]\r\n[02:36.97]合：回到最初时光\r\n[02:42.56]当时的你多么坚强\r\n[02:49.08]\r\n[02:51.21]那鼓励让我难忘\r\n[02:59.58]\r\n[03:01.53]合：你知道我的梦\r\n[03:06.05]你知道我的痛\r\n[03:08.13]你知道我们感受都相同\r\n[03:12.21]就算有再大的风\r\n[03:14.59]也挡不住勇敢的冲动\r\n[03:20.40]努力地往前飞 再累也无所谓\r\n[03:24.44]黑夜过后的光芒有多美\r\n[03:28.51]分享你我的力量\r\n[03:31.06]就能把对方的路照亮\r\n[03:41.81]\r\n[03:50.59]合：我们都一样', '/song/1680528357755我们都一样.mp3', 4);
INSERT INTO `song` VALUES (16, 26, '张杰-三生三世', '一生一世', '2023-03-23 16:12:09', '2023-05-30 22:52:20', '/img/songPic/1680528348731boy.jpeg', '[ar:张杰、杨幂]\r\n[ti:三生三世(Live)]\r\n[00:00.55]张杰、杨幂 - 三生三世(Live)\r\n[00:02.75]作词：代岳东、谭旋\r\n[00:03.54]作曲：谭旋\r\n[00:04.20]编曲：可可\r\n[00:05.54]握不紧那段过往\r\n[00:08.85]冥灭了结魄光芒\r\n[00:12.45]那一滴离别的泪\r\n[00:15.90]灼烧着我的胸膛\r\n[00:19.59]爱在天地中流转\r\n[00:22.99]一颗心为谁奔忙\r\n[00:26.63]四海八荒身在何方\r\n[00:30.09]岁月该如何安放\r\n[00:33.74]听风声在沙沙作响\r\n[00:37.03]敲打着谁的愁肠\r\n[00:40.63]思念在一瞬间生长\r\n[00:44.08]才忘了夜多漫长\r\n[00:47.84]掌心的泪握到滚烫\r\n[00:51.08]只愿为你三生痴狂\r\n[00:54.93]落花满天又闻琴香\r\n[00:58.38]与你天地间徜徉\r\n[01:02.00]如果爱太荒凉\r\n[01:03.57]我陪你梦一场\r\n[01:05.34]赎回你所有泪光\r\n[01:09.05]这一路有多远\r\n[01:10.57]这三世有多长\r\n[01:12.34]执手到地老天荒\r\n[01:15.74]风凄凄雾茫茫\r\n[01:17.56]雨滚滚雪漫漫\r\n[01:19.98]一步步都陪你同往\r\n[01:23.01]牵着手别惊慌\r\n[01:24.78]管明天会怎样\r\n[01:26.35]哪怕注定流浪\r\n[01:30.01]哪怕海角天涯\r\n[02:30.07]听风声在沙沙作响\r\n[02:33.53]敲打着谁的愁肠\r\n[02:37.18]思念在一瞬间生长\r\n[02:40.57]才忘了夜多漫长\r\n[02:44.18]掌心的泪握到滚烫\r\n[02:47.62]只愿为你三生痴狂\r\n[02:51.42]落花满天又闻琴香\r\n[02:54.97]与你天地间徜徉\r\n[02:58.52]如果爱太荒凉\r\n[02:59.93]我陪你梦一场\r\n[03:01.66]赎回你所有泪光\r\n[03:05.41]这一路有多远\r\n[03:07.08]这三世有多长\r\n[03:08.75]执手到地老天荒\r\n[03:12.35]风凄凄雾茫茫\r\n[03:14.13]雨滚滚雪漫漫\r\n[03:15.91]一步步都陪你同往\r\n[03:19.56]牵着手别惊慌\r\n[03:21.23]管明天会怎样\r\n[03:23.05]哪怕注定流浪\r\n[03:26.70]手牢牢不放\r\n[03:28.60]爱念念不忘\r\n[03:30.23]人生何须多辉煌\r\n[03:33.83]浮华的终成空\r\n[03:35.26]执着的都随风\r\n[03:37.13]情路何须多跌宕\r\n[03:40.59]要遇多少风浪\r\n[03:42.60]心不再摇晃\r\n[03:44.43]一起细数这过往\r\n[03:47.16]陪你等风停了雾散了\r\n[03:49.39]雨住了雪化了\r\n[03:51.11]再见绝美月光\r\n[03:54.61]还有我在你身旁', '/song/1680528338875三生三世.flac', 2);
INSERT INTO `song` VALUES (17, 25, '杨幂-爱的供养', '爱情', '2023-03-23 16:12:49', '2023-05-31 18:12:14', '/img/songPic/1680173449872004.jpg', '[ti:爱的供养]\r\n[ar:杨幂]\r\n[al:电视剧《宫》主题曲]\r\n[00:00.77]爱的供养\r\n[00:01.81]作词:于正 作曲:谭璇\r\n[00:03.30]歌手:杨幂\r\n[00:04.69]专辑:宫锁心玉主题曲\r\n[00:14.23]把你捧在手上,虔诚地焚香\r\n[00:21.99]剪下一段烛光,将经纶点亮\r\n[00:30.03]不求荡气回肠,只求爱一场\r\n[00:38.08]爱到最后受了伤,哭得好绝望\r\n[00:45.53]\r\n[00:45.81]我用尽一生一世 来将你供养\r\n[00:53.28]只期盼你停住 流转的目光\r\n[01:01.33]请赐予我无限爱 与被爱的力量\r\n[01:09.07]让我能安心在菩提下 静静的观想\r\n[01:18.80]\r\n[01:31.11]把你放在心上,合起了手掌\r\n[01:39.05]默默乞求上苍,指引我方向\r\n[01:46.98]不求地久天长,只求在身旁\r\n[01:55.03]累了醉倒温柔乡,轻轻地梵唱\r\n[02:02.31]\r\n[02:02.49]我用尽一生一世 来将你供养\r\n[02:10.31]只期盼你停住 流转的目光\r\n[02:18.33]请赐予我无限爱 与被爱的力量\r\n[02:26.09]让我能安心在菩提下 静静的观想\r\n[02:35.39]\r\n[02:36.49]我用尽一生一世 来将你供养\r\n[02:44.32]人世间有太多的 烦恼要忘\r\n[02:52.31]苦海中飘荡着你 那旧时的模样\r\n[03:00.16]一回头发现 早已踏出了红尘万丈', '/song/1679559169315爱的供养.flac', 4);
INSERT INTO `song` VALUES (19, 24, '胡彦冰-月光', '宇宙', '2023-03-23 16:16:42', '2023-05-30 00:16:10', '/img/songPic/1680528108747xg.jpg', '[ti:童话镇]\n[ar:陈一发]\n[al:童话镇]\n[by:]\n[offset:0]\n[00:00.73]童话镇 - 陈一发儿\n[00:01.50]词：竹君\n[00:01.64]曲：暗杠\n[00:01.79]编曲：暗杠\n[00:02.52]\n[00:23.20]听说白雪公主在逃跑\n[00:25.67]\n[00:26.60]小红帽在担心大灰狼\n[00:29.14]\n[00:29.91]听说疯帽喜欢爱丽丝\n[00:32.73]\n[00:33.33]丑小鸭会变成白天鹅\n[00:36.13]\n[00:36.79]听说彼得潘总长不大\n[00:39.46]\n[00:40.20]杰克他有竖琴和魔法\n[00:42.94]\n[00:43.58]听说森林里有糖果屋\n[00:46.32]\n[00:46.83]灰姑娘丢了心爱的玻璃鞋\n[00:50.08]\n[00:50.64]只有睿智的河水知道\n[00:53.16]\n[00:53.80]白雪是因为贪玩跑出了城堡\n[00:57.35]小红帽有件抑制自己\n[00:59.93]\n[01:00.77]变成狼的大红袍\n[01:03.93]总有一条蜿蜒在童话镇里七彩的河\n[01:11.02]沾染魔法的乖张气息\n[01:14.56]却又在爱里曲折\n[01:17.43]\n[01:17.93]川流不息扬起水花\n[01:20.93]又卷入一帘时光入水\n[01:24.17]\n[01:24.72]让所有很久很久以前\n[01:28.24]都走到幸福结局的时刻\n[01:31.74]\n[01:47.48]听说睡美人被埋藏\n[01:49.91]\n[01:50.53]小人鱼在眺望金殿堂\n[01:53.31]\n[01:53.85]听说阿波罗变成金乌\n[01:56.80]\n[01:57.33]草原有奔跑的剑齿虎\n[02:00.17]\n[02:00.77]听说匹诺曹总说着谎\n[02:03.50]\n[02:04.31]侏儒怪拥有宝石满箱\n[02:07.14]\n[02:07.81]听说悬崖有棵长生树\n[02:10.40]\n[02:11.08]红鞋子不知疲倦地在跳舞\n[02:14.64]只有睿智的河水知道\n[02:17.32]\n[02:17.85]睡美人逃避了生活的煎熬\n[02:21.34]小人鱼把阳光抹成眼影\n[02:24.23]\n[02:24.74]投进泡沫的怀抱\n[02:27.81]总有一条蜿蜒在童话镇里七彩的河\n[02:34.75]\n[02:35.27]沾染魔法的乖张气息\n[02:38.65]却又在爱里曲折\n[02:41.50]\n[02:42.01]川流不息扬起水花\n[02:44.98]又卷入一帘时光入水\n[02:48.33]\n[02:48.87]让所有很久很久以前\n[02:52.30]都走到幸福结局的时刻\n[02:55.57]总有一条蜿蜒在童话镇里梦幻的河\n[03:01.94]\n[03:02.61]分隔了理想分隔现实\n[03:05.96]又在前方的山口汇合\n[03:08.86]\n[03:09.36]川流不息扬起水花\n[03:12.44]又卷入一帘时光入水\n[03:15.74]\n[03:16.25]让所有很久很久以前\n[03:19.66]都走到幸福结局的时刻\n[03:22.73]又陌生', '/song/1680543465974月光.flac', 3);
INSERT INTO `song` VALUES (20, 24, '群星-九张机', '宇宙', '2023-03-23 16:16:42', '2023-05-30 00:16:19', '/img/songPic/1680528112419bg2.jpg', '[ti:童话镇]\n[ar:陈一发]\n[al:童话镇]\n[by:]\n[offset:0]\n[00:00.73]童话镇 - 陈一发儿\n[00:01.50]词：竹君\n[00:01.64]曲：暗杠\n[00:01.79]编曲：暗杠\n[00:02.52]\n[00:23.20]听说白雪公主在逃跑\n[00:25.67]\n[00:26.60]小红帽在担心大灰狼\n[00:29.14]\n[00:29.91]听说疯帽喜欢爱丽丝\n[00:32.73]\n[00:33.33]丑小鸭会变成白天鹅\n[00:36.13]\n[00:36.79]听说彼得潘总长不大\n[00:39.46]\n[00:40.20]杰克他有竖琴和魔法\n[00:42.94]\n[00:43.58]听说森林里有糖果屋\n[00:46.32]\n[00:46.83]灰姑娘丢了心爱的玻璃鞋\n[00:50.08]\n[00:50.64]只有睿智的河水知道\n[00:53.16]\n[00:53.80]白雪是因为贪玩跑出了城堡\n[00:57.35]小红帽有件抑制自己\n[00:59.93]\n[01:00.77]变成狼的大红袍\n[01:03.93]总有一条蜿蜒在童话镇里七彩的河\n[01:11.02]沾染魔法的乖张气息\n[01:14.56]却又在爱里曲折\n[01:17.43]\n[01:17.93]川流不息扬起水花\n[01:20.93]又卷入一帘时光入水\n[01:24.17]\n[01:24.72]让所有很久很久以前\n[01:28.24]都走到幸福结局的时刻\n[01:31.74]\n[01:47.48]听说睡美人被埋藏\n[01:49.91]\n[01:50.53]小人鱼在眺望金殿堂\n[01:53.31]\n[01:53.85]听说阿波罗变成金乌\n[01:56.80]\n[01:57.33]草原有奔跑的剑齿虎\n[02:00.17]\n[02:00.77]听说匹诺曹总说着谎\n[02:03.50]\n[02:04.31]侏儒怪拥有宝石满箱\n[02:07.14]\n[02:07.81]听说悬崖有棵长生树\n[02:10.40]\n[02:11.08]红鞋子不知疲倦地在跳舞\n[02:14.64]只有睿智的河水知道\n[02:17.32]\n[02:17.85]睡美人逃避了生活的煎熬\n[02:21.34]小人鱼把阳光抹成眼影\n[02:24.23]\n[02:24.74]投进泡沫的怀抱\n[02:27.81]总有一条蜿蜒在童话镇里七彩的河\n[02:34.75]\n[02:35.27]沾染魔法的乖张气息\n[02:38.65]却又在爱里曲折\n[02:41.50]\n[02:42.01]川流不息扬起水花\n[02:44.98]又卷入一帘时光入水\n[02:48.33]\n[02:48.87]让所有很久很久以前\n[02:52.30]都走到幸福结局的时刻\n[02:55.57]总有一条蜿蜒在童话镇里梦幻的河\n[03:01.94]\n[03:02.61]分隔了理想分隔现实\n[03:05.96]又在前方的山口汇合\n[03:08.86]\n[03:09.36]川流不息扬起水花\n[03:12.44]又卷入一帘时光入水\n[03:15.74]\n[03:16.25]让所有很久很久以前\n[03:19.66]都走到幸福结局的时刻\n[03:22.73]又陌生', '/song/1680543418461九张机.flac', 1);
INSERT INTO `song` VALUES (23, 24, '洛天依-科幻镇c', '宇宙', '2023-03-23 16:16:42', '2023-05-19 23:53:29', '/img/songPic/1680528199411happy_saint_patricks_day_lucky_clover-wallpaper-1920x1200.jpg', '[ar:张杰、杨幂]\r\n[ti:三生三世(Live)]\r\n[00:00.55]张杰、杨幂 - 三生三世(Live)\r\n[00:02.75]作词：代岳东、谭旋\r\n[00:03.54]作曲：谭旋\r\n[00:04.20]编曲：可可\r\n[00:05.54]握不紧那段过往\r\n[00:08.85]冥灭了结魄光芒\r\n[00:12.45]那一滴离别的泪\r\n[00:15.90]灼烧着我的胸膛\r\n[00:19.59]爱在天地中流转\r\n[00:22.99]一颗心为谁奔忙\r\n[00:26.63]四海八荒身在何方\r\n[00:30.09]岁月该如何安放\r\n[00:33.74]听风声在沙沙作响\r\n[00:37.03]敲打着谁的愁肠\r\n[00:40.63]思念在一瞬间生长\r\n[00:44.08]才忘了夜多漫长\r\n[00:47.84]掌心的泪握到滚烫\r\n[00:51.08]只愿为你三生痴狂\r\n[00:54.93]落花满天又闻琴香\r\n[00:58.38]与你天地间徜徉\r\n[01:02.00]如果爱太荒凉\r\n[01:03.57]我陪你梦一场\r\n[01:05.34]赎回你所有泪光\r\n[01:09.05]这一路有多远\r\n[01:10.57]这三世有多长\r\n[01:12.34]执手到地老天荒\r\n[01:15.74]风凄凄雾茫茫\r\n[01:17.56]雨滚滚雪漫漫\r\n[01:19.98]一步步都陪你同往\r\n[01:23.01]牵着手别惊慌\r\n[01:24.78]管明天会怎样\r\n[01:26.35]哪怕注定流浪\r\n[01:30.01]哪怕海角天涯\r\n[02:30.07]听风声在沙沙作响\r\n[02:33.53]敲打着谁的愁肠\r\n[02:37.18]思念在一瞬间生长\r\n[02:40.57]才忘了夜多漫长\r\n[02:44.18]掌心的泪握到滚烫\r\n[02:47.62]只愿为你三生痴狂\r\n[02:51.42]落花满天又闻琴香\r\n[02:54.97]与你天地间徜徉\r\n[02:58.52]如果爱太荒凉\r\n[02:59.93]我陪你梦一场\r\n[03:01.66]赎回你所有泪光\r\n[03:05.41]这一路有多远\r\n[03:07.08]这三世有多长\r\n[03:08.75]执手到地老天荒\r\n[03:12.35]风凄凄雾茫茫\r\n[03:14.13]雨滚滚雪漫漫\r\n[03:15.91]一步步都陪你同往\r\n[03:19.56]牵着手别惊慌\r\n[03:21.23]管明天会怎样\r\n[03:23.05]哪怕注定流浪\r\n[03:26.70]手牢牢不放\r\n[03:28.60]爱念念不忘\r\n[03:30.23]人生何须多辉煌\r\n[03:33.83]浮华的终成空\r\n[03:35.26]执着的都随风\r\n[03:37.13]情路何须多跌宕\r\n[03:40.59]要遇多少风浪\r\n[03:42.60]心不再摇晃\r\n[03:44.43]一起细数这过往\r\n[03:47.16]陪你等风停了雾散了\r\n[03:49.39]雨住了雪化了\r\n[03:51.11]再见绝美月光\r\n[03:54.61]还有我在你身旁', '/song/1680528261920爱的供养.mp3', 0);
INSERT INTO `song` VALUES (24, 24, '洛天依-科幻镇d', '宇宙', '2023-03-23 16:16:42', '2023-05-19 23:53:31', '/img/songPic/1680528220281暮色.jpg', '[ti:我们都一样 (Live)]\r\n[ar:吴汶芳/张杰]\r\n[al:最美和声第三季 第2期]\r\n[by:]\r\n[offset:0]\r\n[00:00.39]我们都一样 (Live) - 吴汶芳/张杰 (Jason Zhang)\r\n[00:03.47]词：苟庆,曹轩宾\r\n[00:05.24]曲：曹轩宾\r\n[00:06.77]\r\n[00:18.09]芳：推开窗看见星星\r\n[00:22.68]依然守在夜空中\r\n[00:25.86]\r\n[00:27.48]心中不免多了些暖暖的感动\r\n[00:34.32]\r\n[00:36.14]一闪一闪的光\r\n[00:39.40]\r\n[00:40.64]努力把黑夜点亮\r\n[00:45.71]气氛如此安详\r\n[00:49.72]\r\n[00:53.12]你在我的生命中\r\n[00:56.49]是那最闪亮的星\r\n[01:01.38]一直在无声夜空\r\n[01:04.86]守护着我们的梦\r\n[01:07.96]\r\n[01:10.26]这世界那么大\r\n[01:14.24]\r\n[01:14.78]我的爱只想要你懂\r\n[01:19.97]陪伴我无尽旅程\r\n[01:25.53]合：你知道我的梦\r\n[01:28.61]你知道我的痛\r\n[01:30.61]你知道我们感受都相同\r\n[01:34.82]就算有再大的风\r\n[01:37.22]也挡不住勇敢的冲动\r\n[01:43.21]努力地往前飞 再累也无所谓\r\n[01:47.24]黑夜过后的光芒有多美\r\n[01:51.38]分享你我的力量\r\n[01:53.87]就能把对方的路照亮\r\n[02:03.24]\r\n[02:20.10]杰：我想我们都一样\r\n[02:24.41]渴望梦想的光芒\r\n[02:27.48]\r\n[02:29.46]这一路喜悦彷徨\r\n[02:32.81]不要轻易说失望\r\n[02:35.93]\r\n[02:36.97]合：回到最初时光\r\n[02:42.56]当时的你多么坚强\r\n[02:49.08]\r\n[02:51.21]那鼓励让我难忘\r\n[02:59.58]\r\n[03:01.53]合：你知道我的梦\r\n[03:06.05]你知道我的痛\r\n[03:08.13]你知道我们感受都相同\r\n[03:12.21]就算有再大的风\r\n[03:14.59]也挡不住勇敢的冲动\r\n[03:20.40]努力地往前飞 再累也无所谓\r\n[03:24.44]黑夜过后的光芒有多美\r\n[03:28.51]分享你我的力量\r\n[03:31.06]就能把对方的路照亮\r\n[03:41.81]\r\n[03:50.59]合：我们都一样', '/song/1680528271702Vk起风了.mp3', 0);
INSERT INTO `song` VALUES (25, 24, '洛天依-科幻镇33', '宇宙', '2023-03-23 16:16:42', '2023-05-20 00:14:59', '/img/songPic/1680528240808music1.png', '[ti:爱的供养]\r\n[ar:杨幂]\r\n[al:电视剧《宫》主题曲]\r\n[00:00.77]爱的供养\r\n[00:01.81]作词:于正 作曲:谭璇\r\n[00:03.30]歌手:杨幂\r\n[00:04.69]专辑:宫锁心玉主题曲\r\n[00:14.23]把你捧在手上,虔诚地焚香\r\n[00:21.99]剪下一段烛光,将经纶点亮\r\n[00:30.03]不求荡气回肠,只求爱一场\r\n[00:38.08]爱到最后受了伤,哭得好绝望\r\n[00:45.53]\r\n[00:45.81]我用尽一生一世 来将你供养\r\n[00:53.28]只期盼你停住 流转的目光\r\n[01:01.33]请赐予我无限爱 与被爱的力量\r\n[01:09.07]让我能安心在菩提下 静静的观想\r\n[01:18.80]\r\n[01:31.11]把你放在心上,合起了手掌\r\n[01:39.05]默默乞求上苍,指引我方向\r\n[01:46.98]不求地久天长,只求在身旁\r\n[01:55.03]累了醉倒温柔乡,轻轻地梵唱\r\n[02:02.31]\r\n[02:02.49]我用尽一生一世 来将你供养\r\n[02:10.31]只期盼你停住 流转的目光\r\n[02:18.33]请赐予我无限爱 与被爱的力量\r\n[02:26.09]让我能安心在菩提下 静静的观想\r\n[02:35.39]\r\n[02:36.49]我用尽一生一世 来将你供养\r\n[02:44.32]人世间有太多的 烦恼要忘\r\n[02:52.31]苦海中飘荡着你 那旧时的模样\r\n[03:00.16]一回头发现 早已踏出了红尘万丈', '/song/1680528296623九张机.flac', 1);
INSERT INTO `song` VALUES (26, 24, '洛天依-科幻镇f', '宇宙', '2023-03-23 16:16:42', '2023-05-19 23:53:34', '/img/songPic/1680528249818delf.png', '[ti:爱的供养]\r\n[ar:杨幂]\r\n[al:电视剧《宫》主题曲]\r\n[00:00.77]爱的供养\r\n[00:01.81]作词:于正 作曲:谭璇\r\n[00:03.30]歌手:杨幂\r\n[00:04.69]专辑:宫锁心玉主题曲\r\n[00:14.23]把你捧在手上,虔诚地焚香\r\n[00:21.99]剪下一段烛光,将经纶点亮\r\n[00:30.03]不求荡气回肠,只求爱一场\r\n[00:38.08]爱到最后受了伤,哭得好绝望\r\n[00:45.53]\r\n[00:45.81]我用尽一生一世 来将你供养\r\n[00:53.28]只期盼你停住 流转的目光\r\n[01:01.33]请赐予我无限爱 与被爱的力量\r\n[01:09.07]让我能安心在菩提下 静静的观想\r\n[01:18.80]\r\n[01:31.11]把你放在心上,合起了手掌\r\n[01:39.05]默默乞求上苍,指引我方向\r\n[01:46.98]不求地久天长,只求在身旁\r\n[01:55.03]累了醉倒温柔乡,轻轻地梵唱\r\n[02:02.31]\r\n[02:02.49]我用尽一生一世 来将你供养\r\n[02:10.31]只期盼你停住 流转的目光\r\n[02:18.33]请赐予我无限爱 与被爱的力量\r\n[02:26.09]让我能安心在菩提下 静静的观想\r\n[02:35.39]\r\n[02:36.49]我用尽一生一世 来将你供养\r\n[02:44.32]人世间有太多的 烦恼要忘\r\n[02:52.31]苦海中飘荡着你 那旧时的模样\r\n[03:00.16]一回头发现 早已踏出了红尘万丈', '/song/1680528307520Lindsey Stirling - Senbonzakura .mp3', 0);
INSERT INTO `song` VALUES (35, 25, '杨幂-爱的供养9', '爱情', '2023-03-23 16:12:49', '2023-05-19 23:53:48', '/img/songPic/default.png', '[ti:爱的供养]\r\n[ar:杨幂]\r\n[al:电视剧《宫》主题曲]\r\n[00:00.77]爱的供养\r\n[00:01.81]作词:于正 作曲:谭璇\r\n[00:03.30]歌手:杨幂\r\n[00:04.69]专辑:宫锁心玉主题曲\r\n[00:14.23]把你捧在手上,虔诚地焚香\r\n[00:21.99]剪下一段烛光,将经纶点亮\r\n[00:30.03]不求荡气回肠,只求爱一场\r\n[00:38.08]爱到最后受了伤,哭得好绝望\r\n[00:45.53]\r\n[00:45.81]我用尽一生一世 来将你供养\r\n[00:53.28]只期盼你停住 流转的目光\r\n[01:01.33]请赐予我无限爱 与被爱的力量\r\n[01:09.07]让我能安心在菩提下 静静的观想\r\n[01:18.80]\r\n[01:31.11]把你放在心上,合起了手掌\r\n[01:39.05]默默乞求上苍,指引我方向\r\n[01:46.98]不求地久天长,只求在身旁\r\n[01:55.03]累了醉倒温柔乡,轻轻地梵唱\r\n[02:02.31]\r\n[02:02.49]我用尽一生一世 来将你供养\r\n[02:10.31]只期盼你停住 流转的目光\r\n[02:18.33]请赐予我无限爱 与被爱的力量\r\n[02:26.09]让我能安心在菩提下 静静的观想\r\n[02:35.39]\r\n[02:36.49]我用尽一生一世 来将你供养\r\n[02:44.32]人世间有太多的 烦恼要忘\r\n[02:52.31]苦海中飘荡着你 那旧时的模样\r\n[03:00.16]一回头发现 早已踏出了红尘万丈', '/song/1679559169315爱的供养.flac', 0);
INSERT INTO `song` VALUES (42, 32, '白起-战神白起', '古风', '2023-05-16 23:41:31', '2023-05-19 23:53:53', '/img/songPic/default.png', '[00:00:00]暂无歌词', '/song/1684251691627九张机.flac', 0);
INSERT INTO `song` VALUES (44, 26, '张杰-天下原唱', '天下', '2023-05-19 23:53:01', '2023-05-30 22:52:25', '/img/songPic/default.png', '[00:00.000] 作词 : 周毅\r\n[00:01.000] 作曲 : 刘迦宁\r\n[00:27.520]烽烟起寻爱似浪淘沙\r\n[00:34.181]遇见她如春水映梨花\r\n[00:40.947]挥剑断天涯相思轻放下\r\n[00:47.686]梦中我痴痴牵挂\r\n[00:54.766]顾不顾将相王侯\r\n[00:56.176]管不管万世千秋\r\n[00:57.953]求只求爱化解\r\n[00:59.546]这万丈红尘纷乱永无休\r\n[01:02.002]爱更爱天长地久\r\n[01:03.778]要更要似水温柔\r\n[01:05.450]谁在乎谁主春秋\r\n[01:07.696]一生有爱何惧风飞沙\r\n[01:10.805]悲白发留不住芳华\r\n[01:14.645]抛去江山如画换她笑面如花\r\n[01:17.832]抵过这一生空牵挂\r\n[01:21.097]心若无怨爱恨也随她\r\n[01:24.101]天地大情路永无涯\r\n[01:27.680]只为她袖手天下\r\n[02:01.038]顾不顾将相王侯\r\n[02:02.997]管不管万世千秋\r\n[02:04.565]求只求爱化解\r\n[02:06.132]这万丈红尘纷乱永无休\r\n[02:08.718]爱更爱天长地久\r\n[02:10.677]要更要似水温柔\r\n[02:12.140]谁在乎谁主春秋\r\n[02:14.387]一生有爱何惧风飞沙\r\n[02:17.443]悲白发留不住芳华\r\n[02:21.100]抛去江山如画换她笑面如花\r\n[02:24.627]抵过这一生空牵挂\r\n[02:27.709]心若无怨爱恨也随她\r\n[02:30.740]天地大情路永无涯\r\n[02:34.371]只为她袖手天下\r\n[02:41.215]一生有爱何惧风飞沙\r\n[02:44.166]悲白发留不住芳华\r\n[02:47.954]抛去江山如画换她笑面如花\r\n[02:51.246]抵过这一生空牵挂\r\n[02:54.328]心若无怨爱恨也随她\r\n[02:57.437]天地大情路永无涯\r\n[03:00.937]只为她袖手天下\r\n[03:07.781]烽烟起寻爱似浪淘沙\r\n[03:14.207]遇见她如春水映梨花\r\n[03:20.921]挥剑断天涯相思轻放下\r\n[03:27.765]梦中我痴痴牵挂', '/song/1684511581696天下 - 张杰.mp3', 2);
INSERT INTO `song` VALUES (45, 26, '张杰-他不懂', '他不懂', '2023-05-20 14:34:24', '2023-05-31 18:11:43', '/img/songPic/1684564604761hybff.png', '[00:00.000] 作词 : 代岳东/周振霆\r\n[00:01.000] 作曲 : 唐湘智\r\n[00:19.410]他留给你是背影\r\n[00:25.060]关于爱情只字不提\r\n[00:29.850]害你哭红了眼睛\r\n[00:34.780]他把谎言说的竟然那么动听\r\n[00:40.750]他不止一次骗了你\r\n[00:44.710]不值得你再为他伤心\r\n[00:48.380]他不懂你的心假装冷静\r\n[00:53.180]他不懂爱情把它当游戏\r\n[00:56.170]他不懂表明相爱这件事\r\n[01:00.060]除了对不起就只剩叹息\r\n[01:04.060]他不懂你的心为何哭泣\r\n[01:08.950]窒息到快要不能呼吸\r\n[01:16.700]喔喔\r\n[01:18.880]他不懂你的心\r\n[01:23.230]\r\n[01:36.550]他把回忆留给你\r\n[01:43.640]连同忧伤强加给你\r\n[01:48.300]对你说来不公平\r\n[01:54.610]他的谎言句句说的那么动听\r\n[01:58.750]他不止一次骗了你\r\n[02:03.640]不值得你再为他伤心\r\n[02:07.980]他不懂你的心假装冷静\r\n[02:11.880]他不懂爱情把它当游戏\r\n[02:15.720]他不懂表明相爱这件事\r\n[02:19.630]除了对不起就只剩叹息\r\n[02:23.960]他不懂你的心为何哭泣\r\n[02:27.970]窒息到快要不能呼吸\r\n[02:36.070]喔喔\r\n[02:38.630]他不懂你的心\r\n[02:42.630]\r\n[02:56.250]他不懂你的心假装冷静\r\n[02:59.990]他不懂爱情把它当游戏\r\n[03:03.860]他不懂表明相爱这件事\r\n[03:07.720]除了对不起就只剩叹息\r\n[03:11.570]他不懂你的心为何哭泣\r\n[03:15.640]窒息到快要不能呼吸\r\n[03:23.800]喔喔\r\n[03:26.320]他不懂你的心', '/song/1684564464027他不懂.mp3', 8);

-- ----------------------------
-- Table structure for song_list
-- ----------------------------
DROP TABLE IF EXISTS `song_list`;
CREATE TABLE `song_list`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片',
  `introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简介',
  `style` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '风格',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `click_count` int(255) NULL DEFAULT NULL COMMENT '歌单被点击数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '歌单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of song_list
-- ----------------------------
INSERT INTO `song_list` VALUES (1, '梦想d宇宙 ', '/img/songListPic/1680503114497music1.png', '宇    宙', '古风', NULL, 2);
INSERT INTO `song_list` VALUES (2, '悠悠古人', '/img/songListPic/1680503121538011.png', '收集古风的歌单  ', '粤语', NULL, 1);
INSERT INTO `song_list` VALUES (3, '虚拟歌手', '/img/songListPic/1680503125286default.png', '虚拟歌手的歌单 ', '粤语', NULL, 0);
INSERT INTO `song_list` VALUES (4, '那些年的青春', '/img/songListPic/1680503131173delf.png', '青春回忆的歌', '日韩', NULL, 1);
INSERT INTO `song_list` VALUES (5, '112233', '/img/songListPic/1680503137036boy.jpeg', 'String filePath = System.getProperty(\"user.dir\") + System.getProperty(\"file.separator\") + \"music-server\"+ System.getProperty(\"file.separator\")+ \"img\"\n                + System.getProperty(\"file.separator\") + \"songListPic\";', '国风', NULL, 1);
INSERT INTO `song_list` VALUES (6, 'werwe的歌单', '/img/songListPic/default.png', '撒法赛发动法警扽红来发；oerweoqiwueioh呼出气ewpieruqwerj 黑十日去ebqwhejkrwjkfjw寒风苏南表', '古风', NULL, 0);
INSERT INTO `song_list` VALUES (10, '权威', '/img/songListPic/1684566107709张杰.jpg', '瓦尔', '沃尔沃', 13, 1);
INSERT INTO `song_list` VALUES (11, '公共歌单', '/img/songListPic/default.png', '远远的过去', '古风', 13, 0);
INSERT INTO `song_list` VALUES (12, 'aref', '/img/songListPic/default.png', 'ef aew', '哇efa', 13, 0);
INSERT INTO `song_list` VALUES (13, 'efae', '/img/songListPic/default.png', 'afaf', ' asfa', 13, 0);
INSERT INTO `song_list` VALUES (14, 'aefae', '/img/songListPic/default.png', ' aefa', ' af', 13, 0);
INSERT INTO `song_list` VALUES (15, 'asfas ', '/img/songListPic/default.png', 'adfa', 'a扽adf', 13, 0);
INSERT INTO `song_list` VALUES (16, '梦想宇宙', '/img/songListPic/default.png', '请问eqw', 'asff', 13, 0);
INSERT INTO `song_list` VALUES (17, '公共歌单11', '/img/songListPic/default.png', '宇宙', '古风', NULL, 0);
INSERT INTO `song_list` VALUES (18, 'x的歌单', '/img/songListPic/default.png', '塞法森', '古风', 13, 0);
INSERT INTO `song_list` VALUES (19, 'xx的歌单', '/img/songListPic/default.png', '东方撒旦', '粤语', 13, 0);
INSERT INTO `song_list` VALUES (20, 'f的歌单', '/img/songListPic/default.png', 's放大塞法', 's扽as', 13, 0);
INSERT INTO `song_list` VALUES (21, 'sdfs的歌单', '/img/songListPic/default.png', 'dfadfasdf', '华语', 13, 0);
INSERT INTO `song_list` VALUES (22, '流行歌单', '/img/songListPic/default.png', 'sdfsd', '粤语', 13, 0);

-- ----------------------------
-- Table structure for song_list_music
-- ----------------------------
DROP TABLE IF EXISTS `song_list_music`;
CREATE TABLE `song_list_music`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `song_id` int(11) NULL DEFAULT NULL COMMENT '歌曲id',
  `song_list_id` int(11) NULL DEFAULT NULL COMMENT '歌单id\r\n',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 114 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '歌单中的歌曲' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of song_list_music
-- ----------------------------
INSERT INTO `song_list_music` VALUES (89, 17, 12);
INSERT INTO `song_list_music` VALUES (90, 16, 1);
INSERT INTO `song_list_music` VALUES (92, 18, 13);
INSERT INTO `song_list_music` VALUES (93, 18, 15);
INSERT INTO `song_list_music` VALUES (94, 20, 11);
INSERT INTO `song_list_music` VALUES (103, 14, 13);
INSERT INTO `song_list_music` VALUES (105, 20, 12);
INSERT INTO `song_list_music` VALUES (106, 20, 15);
INSERT INTO `song_list_music` VALUES (107, 20, 10);
INSERT INTO `song_list_music` VALUES (108, 20, 13);
INSERT INTO `song_list_music` VALUES (109, 17, 11);
INSERT INTO `song_list_music` VALUES (110, 45, 11);
INSERT INTO `song_list_music` VALUES (111, 44, 11);
INSERT INTO `song_list_music` VALUES (112, 15, 11);
INSERT INTO `song_list_music` VALUES (113, 16, 11);

SET FOREIGN_KEY_CHECKS = 1;
