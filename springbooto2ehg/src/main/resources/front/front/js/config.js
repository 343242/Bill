var projectName = '南京市雨花驾校管理信息系统';
/**
 * 轮播图配置
 */
var swiper = {
	// 设定轮播容器宽度，支持像素和百分比
	width: '100%',
	height: '400px',
	// hover（悬停显示）
	// always（始终显示）
	// none（始终不显示）
	arrow: 'none',
	// default（左右切换）
	// updown（上下切换）
	// fade（渐隐渐显切换）
	anim: 'default',
	// 自动切换的时间间隔
	// 默认3000
	interval: 2000,
	// 指示器位置
	// inside（容器内部）
	// outside（容器外部）
	// none（不显示）
	indicator: 'outside'
};

/**
 * 个人中心菜单
 */
var centerMenu = [{
	name: '个人信息管理',
	url: '../' + localStorage.getItem('userTable') + '/center.html'
},
{
	name: '视频收藏',
	url: '../storeup/video-list.html'
},
{
	name: '考试记录',
	url: '../examrecord/list.html'
},
{
	name: '错题本',
	url: '../examrecord/wrong.html'
},
{
	name: '我的收藏',
	url: '../storeup/list.html'
},
{
	name: '预约记录',
	url: '../yuyuelianche/list.html'
}];

var indexNav = [
{
	name: '驾校概况',
	url: './pages/jiaxiaoxinxi/list.html'
},
{
	name: '教练信息',
	url: './pages/jiaolian/list.html'
},
{
	name: '报名须知',
	url: './pages/news/list.html'
}];

var adminurl = "/springbooto2ehg/admin/dist/index.html";

var cartFlag = false;

var chatFlag = false;

var menu = [
    {
        "hasBackLogin": "是",
        "hasBackRegister": "否",
        "hasFrontLogin": "否",
        "hasFrontRegister": "否",
        "roleName": "管理员",
        "tableName": "users"
    },
    {
        "hasBackLogin": "是",
        "hasBackRegister": "否",
        "hasFrontLogin": "是",
        "hasFrontRegister": "是",
        "roleName": "学员",
        "tableName": "xueyuan"
    },
    {
        "hasBackLogin": "是",
        "hasBackRegister": "是",
        "hasFrontLogin": "否",
        "hasFrontRegister": "否",
        "roleName": "教练",
        "tableName": "jiaolian"
    }
];

var isAuth = function (tableName, key) {
    return false;
};

var isFrontAuth = function (tableName, key) {
    return false;
};