<?php
/***************************************************************************
 *                                   en.php
 *                            -------------------
 *   begin                : Tuesday', Aug 15', 2002
 *   copyright            : ('C) 2002 Bugada Andrea
 *   email                : phpATM@free.fr
 *
 *   $Id: cn.php,v 1.1 2010/03/28 18:24:00 jost Exp $
 *
 *
 ***************************************************************************/

/***************************************************************************
 *
 *   This program is free software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation; either version 2 of the License', or
 *   ('at your option) any later version.
 *
 ***************************************************************************/

$headerpage="header.htm";    // The optional header file 
$footerpage="footer.htm";    // The optional footer file 
$infopage="info.htm";        // The optional info file 

$charsetencoding="gb2312";                 // The encoding for national symbols (i.e. for cyryllic  must be "windows-1251")
$uploadcentercaption="PHP Advanced Transfer Manager";
$uploadcentermessage="phpATM";

$mess=array(
"0" => "",
"1" => "一月",
"2" => "二月",
"3" => "三月",
"4" => "四月",
"5" => "五月",
"6" => "六月",
"7" => "七月",
"8" => "八月",
"9" => "九月",
"10" => "十月",
"11" => "十一月",
"12" => "十二月",
"13" => "今天",
"14" => "昨天",
"15" => "文件名称",
"16" => "动作",
"17" => "大小",
"18" => "上传时间",
"19" => "所有者",
"20" => "上传文件",
"21" => "本地文件",
"22" => "文件描述",
"23" => "下载",
"24" => "顺序",
"25" => "首页",
"26" => "文件",
"27" => "打印",
"28" => "关闭",
"29" => "返回",
"30" => "此文件已被移除",
"31" => "不能打开文件",
"32" => "返回",
"33" => "上传文件出错 !",
"34" => "你必须选择一个文件",
"35" => "返回",
"36" => "该文件",
"37" => "已成功上传",
"38" => "这个名称的文件",
"39" => "已经存在",
"40" => "文件已成功上传",
"41" => "选择语言成功",
"42" => "欢迎!",
"43" => "全部空间已使用",
"44" => "显示所有天数的文件",
"45" => "无效ZIP档案!",
"46" => "档案内容:",
"47" => "日期/时间",
"48" => "目录",
"49" => "这个名称的文件被禁止上传",
"50" => "超过允许的最大尺寸",
"51" => "信息",
"52" => "选择皮肤",
"53" => "皮肤",
"54" => "欢迎",
"55" => "现在时间",
"56" => "用户",
"57" => "用户名",
"58" => "注册",
"59" => "注册",
"60" => "星期日",
"61" => "星期一",
"62" => "星期二",
"63" => "星期三",
"64" => "星期四",
"65" => "星期五",
"66" => "星期六",
"67" => "发送",
"68" => "把文件邮给自己",
"69" => "该文件已被邮寄到 %s 地址.",
"70" => "文件由用户: %s 上传",
"71" => "登陆",
"72" => "退出",
"73" => "进入",
"74" => "匿名",
"75" => "普通用户",
"76" => "高级用户",
"77" => "管理员",
"78" => "私人区域",
"79" => "公共区域",
"80" => "你输入了无效帐户名或密码.",
"81" => "我的资料",
"82" => "查看/编辑 我的资料",
"83" => "密码",
"84" => "选择语言",
"85" => "选择时区",
"86" => "你的现在时间",
"88" => "请输入有效e-mail地址.",
"89" => "确定你的e-mail地址是有效的, 因为你的个人激活代码将发送到你的e-mail里.",
"90" => "文件已上传: ",
"91" => "请输入你注册时的e-mail地址.",
"92" => "文件大小: ",
"93" => "请写下你的名字和密码",
"94" => "注册是必要的. 请注册.",
"95" => "没有必要注册. 如果你想把你的名字加入到所有你上传的文件，你可以注册. 其他人将不能用你的名字上传他们的文件.",

"96" => "皮肤已选择.",
"97" => "刷新",
"98" => "请输入你的登陆名和密码",
"99" => "还没有注册吗? - 点这里注册!",
"100" => "忘记密码了?",
"101" => "请 %s 返回 %s 再试.",
"102" => "你已成功退出.",
"103" => "那个用户名无效. 名字不能超过12个符号而且只能包含字母和数字. 用户名可以含有 '-', '_', 以及空格.",
"104" => "该 '%s' 你所选择的已经被注册了.",
"105" => "确认密码",
"106" => "密码不匹配.",
"107" => "你输入的e-mail地址格式无效.",
"108" => "感谢你注册. 请不要忘记密码,因为它已经在我们的数据库里被加密. 我们也不能给你恢复. 然而, 你忘记密码的话, 我们提供一个易用的脚本来生成并邮寄一个新的,随机的密码.",
"109" => "你可以 %s 从这里进入上传中心. %s",
"110" => "你的激活代码已经邮寄给你了. 你必须2天内激活帐户,否则帐户将被自动删除.",
"111" => "你不被允许下载该文件",
"112" => "激活帐户",
"113" => "请激活你的帐户",
"114" => "激活代码",
"115" => "你的帐户现在激活了.",
"116" => "请 %s 在这里输入 %s.",
"117" => "输入的帐户名或激活代码无效.",
"118" => "这个帐户已经激活了.",
"119" => "我希望通过邮件接收上传文件的每日摘要.",
"120" => "修改你的密码.",
"121" => "你的老密码",
"122" => "输入的帐户名不存在.",
"123" => "输入的e-mail地址无效.",
"124" => "你的西欧内密码已被送到你的e-mail中.",
"125" => "不能执行, 目标未找到",
"126" => "自己定义你的帐户设置",
"127" => "应用",
"128" => "你的资料已被保存.",
"129" => "你的密码已被修改.",
"130" => "你输入了无效的老密码.",
"131" => "你必须指定你的新密码.",
"132" => "设置",
"133" => "上传",
"134" => "语言 & 时区",
"135" => "帐户统计",
"136" => "你的帐户已建立:",
"137" => "用户管理",
"138" => "查看 (view only)",
"139" => "上传 (upload only)",
"140" => "帐户 '%s' 成功改变",
"141" => "最近的",
"142" => "所有",
"143" => "在确认后新e-mail生效. 确认代码已经发送到你的新e-mail地址. 请看e-mail里的说明.",
"144" => "",
"145" => "请确认你的新e-mail地址.",
"146" => "确认代码",
"147" => "确认",
"148" => "没有确认.",
"149" => "输入的帐户名或确认代码无效.",
"150" => "你的新e-mail地址 '%s' 被确认.",
"151" => "文件已上传",
"152" => "文件已下载",
"153" => "文件已邮寄",
"154" => "帐户已建立",
"155" => "最后进入时间",
"156" => "状态",
"157" => "激活状态",
"158" => "接收摘要",
"159" => "e-mail",
"160" => "全部:",
"161" => "帐户",
"162" => "删除帐户",
"163" => "显示 %s 帐户 of %s",
"164" => "设置上传中心",
"165" => "编辑多个文件",
"166" => "编辑文件",
"167" => "文件 %s 成功改变",
"168" => "保存",
"169" => "删除",
"170" => "删除文件",
"171" => "镜象",
"172" => "是",
"173" => "否",
"174" => "活跃",
"175" => "不活跃",
"176" => "没有授权",
"177" => "对不起, 服务器不能执行邮件程序.",
"178" => "你的注册失败. 请稍候再试.",
"179" => "请稍候再试.",
"180" => "文件成功删除",
"181" => "你不被允许删除该文件",
"182" => "目录成功删除",
"183" => "你不允许删除该目录",
"184" => "目录成功建立",
"185" => "你不允许建立目录",
"186" => "建立新目录",
"187" => "目录名称",
"188" => "建立目录",
"189" => "目录已存在, 请换个名字",
"190" => "你必须指定一个目录名称",
"191" => "修改",
"192" => "文件名称",
"193" => "修改文件 / 目录详情",
"194" => "目标成功重新命名.",
"195" => "你不允许重新给这个目标命名",
"196" => "根目录路径不正确. 请检查设置",
"197" => "排序",
"198" => "重新命名失败, 文件已经存在",
"199" => "最近上传",
"200" => "下载排名",
"201" => "重新命名失败, 不允许的文件名",

//
// New strings introduced in version 1.02
//
"202" => "你提供的url无效",
"203" => "文件http地址",
"204" => "以http地址上传文件",

//
// New strings introduced in version 1.10
//
"205" => "Always stay logged",
"206" => "不能执行: 名称不允许",
"207" => "IP地址被屏蔽",
"208" => "你的IP地址已经被管理员屏蔽!",
"209" => "要得到更多消息, 请联系管理员",

//
// New strings introduced in version 1.12
//
"210" => "超过每天允许的Mb",
"211" => "超过每月允许的Mb",
"212" => "超过每天允许下载的Mb",
"213" => "超过每月允许下载的Mb",
"214" => "确认文件",
"215" => "文件已确认",
"216" => "确定要删除",
"217" => "你不允许确认那个目标",
"218" => "该文件要管理员确认后才被列出",
"219" => "文件查看器",

//
// New strings for MrScripto mods v1.20
//
"M01" => "移动文件到一个不同的目录",
"M02" => "存在的目录",
"M03" => "新目录",
"M04" => "上传目录 根目录",
"M05" => "选择目录:",
"M06" => "根目录",
"M07" => "移动文件",
"M08" => "移动",
"M09" => "该文件已存在于目标目录里, 不能移动",
"M10" => "该文件没有被确认, 不能移动.",
"M11" => "该文件被成功移动.",
"M12" => "你不允许移动文件.",
"M13" => "Email(s), 用逗号分隔多个邮件地址.",
"M14" => "把文件邮寄给朋友",
"M15" => "邮寄文件",
"M16" => "你的邮局已被发送!",
"M17" => "文件从邮件地址发送",
"M18" => "你不能在公共下载区域上传文件.",
"M19" => "你不能在公共下载区域建立目录.",
"M20" => "给用户发通知邮件?",
"M21" => "建立一个新用户",
"M22" => "新用户已被建立!",
"M23" => "一个新用户一注册了你的上传程序!",
"M24" => "新用户注册",
"M25" => "公共目录",
"M26" => "私人目录",
"M27" => "公共目录内容",
"M28" => "你没有被允许进入那个目录!",
"M29" => "对不起, 上传该文件,",
"M30" => "将超过允许的总量",
"M31" => " , 并删除用户目录 / 文件? "
);

//
// Send file e-mail configuration
//
$sendfile_email_subject = '需要的文件';
$sendfile_email_body = '
这里有你邮件需求的文件

';
$sendfile_email_end = '敬上,';

//
// Send file to friend e-mail configuration
//
$sendfile_friend_subject = '检查文件!';
$sendfile_friend_body = '
该文件是下列用户发送给你的, 希望你喜欢!

';

//
// Digest e-mail configuration
//
$digest_email_subject = "每日摘要";

//
// Confirm new e-mail configuration
//
$confirm_email_subject = '确认新e-mail';
$confirm_email_body = '亲爱的 %s,

因为你的安全对我们很重要,你的新e-mail需要被确认.

你的个人激活代码是: %s

激活你的邮件地址很简单:
1. 点这里 %s 访问我们, 我们将指导你完成
2. Enter your account name and confirmation code.
3. 点击 \'确认\' 按钮.

';
$confirm_email_end = '敬上,';

//
// Send password e-mail configuration
//
$chpass_email_subject = '新密码';
$chpass_email_body = '亲爱的用户,

你的新密码是 %s

';
$chpass_email_end = '敬上,';

//
// Confirm registration e-mail configuration
//
$register_email_subject = '确认注册';
$register_email_body = '亲爱的 %s,

感谢你注册.

因为你的安全对我们很重要, 你的帐户需要被激活.

你的个人激活代码是: %s
(注意: 这个不是你的密码)

激活你的帐户很简单:
1. 点增长率 %s 访问我们,我们将指导你完成.
2. 输入你的帐户名和激活代码.
3. 点击 \'激活帐户\' 按钮.

';
$register_email_end = '敬上,';
?>
