// 角色菜单配置
var roleMenus = {
    // 游客角色菜单
    'visitor': {
        nav: [
            {name: '首页', url: './pages/home/home.html'},
            {name: '驾校概况', url: './pages/jiaxiaoxinxi/list.html'},
            {name: '教练团队', url: './pages/jiaolian/list.html'},
            {name: '报名须知', url: './pages/news/list.html'}
        ]
    },
    // 游客角色菜单（兼容旧的guest标识）
    'guest': {
        nav: [
            {name: '首页', url: './pages/home/home.html'},
            {name: '驾校概况', url: './pages/jiaxiaoxinxi/list.html'},
            {name: '教练团队', url: './pages/jiaolian/list.html'},
            {name: '报名须知', url: './pages/news/list.html'}
        ]
    },
    // 学员角色菜单
    'xueyuan': {
        nav: [
            {name: '首页', url: './pages/xueyuan/home.html'},
            {name: '个人信息管理', url: './pages/xueyuan/center.html'},
            {
                name: '理论学习考试',
                children: [
                    {name: '理论视频学习', url: './pages/shipin/list.html'},
                    {name: '视频互动管理', url: './pages/storeup/list.html'},
                    {name: '练习题库', url: './pages/exampaper/list.html'},
                    {name: '理论考试', url: './pages/examrecord/list.html'},
                    {name: '错题本', url: './pages/examrecord/wrong.html'}
                ]
            },
            {
                name: '练车预约申请',
                children: [
                    {name: '时段预约申请', url: './pages/yuyuelianche/list.html?view=shenqing'},
                    {name: '预约记录查询', url: './pages/yuyuelianche/list.html?view=jilu'}
                ]
            }
        ],
        sidebar: [
            {name: '首页', url: './pages/xueyuan/home.html', icon: 'layui-icon-home'},
            {
                name: '个人信息管理',
                children: [
                    {name: '个人信息管理', url: './pages/xueyuan/center.html'}
                ]
            },
            {
                name: '理论学习考试',
                children: [
                    {name: '理论视频学习', url: './pages/shipin/list.html'},
                    {name: '视频互动管理', url: './pages/storeup/list.html'},
                    {name: '练习题库', url: './pages/exampaper/list.html'},
                    {name: '理论考试', url: './pages/examrecord/list.html'},
                    {name: '错题本', url: './pages/examrecord/wrong.html'}
                ]
            },
            {
                name: '练车预约申请',
                children: [
                    {name: '时段预约申请', url: './pages/yuyuelianche/list.html?view=shenqing'},
                    {name: '预约记录查询', url: './pages/yuyuelianche/list.html?view=jilu'}
                ]
            }
        ]
    },
    // 教练角色菜单
    'jiaolian': {
        nav: [
            {name: '首页', url: './pages/jiaolian/home.html'},
            {name: '个人信息管理', url: './pages/jiaolian/center.html'},
            {name: '教练排班管理', url: './pages/jiaolian/schedule.html'},
            {name: '学员管理', url: './pages/jiaolian/students.html'},
            {name: '预约审核', url: './pages/jiaolian/approve.html'}
        ],
        sidebar: [
            {name: '首页', url: './pages/jiaolian/home.html', icon: 'layui-icon-home'},
            {
                name: '个人信息管理',
                children: [
                    {name: '个人信息管理', url: './pages/jiaolian/center.html'}
                ]
            },
            {
                name: '教练排班查看管理',
                children: [
                    {name: '个人排班查询', url: './pages/jiaolian/schedule.html'},
                    {name: '排班状态同步', url: './pages/jiaolian/schedule.html'}
                ]
            },
            {
                name: '学员管理',
                children: [
                    {name: '学员管理', url: './pages/jiaolian/students.html'}
                ]
            },
            {
                name: '练车预约申请审核',
                children: [
                    {name: '待审核预约查询', url: './pages/jiaolian/approve.html?view=daishenhe'},
                    {name: '已确认预约列表', url: './pages/jiaolian/approve.html?view=yiqueren'}
                ]
            }
        ]
    },
    // 管理员角色菜单
    'users': {
        nav: [
            {name: '首页', url: './pages/users/home.html'},
            {name: '基础信息管理', url: './pages/jiaolian/list.html'},
            {name: '排班管理', url: './pages/kechenganpai/list.html'},
            {name: '考试管理', url: './pages/exampaper/list.html'},
            {name: '预约管理', url: './pages/yuyuelianche/list.html'},
            {name: '系统管理', url: './pages/config/list.html'}
        ],
        sidebar: [
            {name: '首页', url: './pages/users/home.html', icon: 'layui-icon-home'},
            {
                name: '基础信息管理',
                children: [
                    {name: '教练管理', url: './pages/jiaolian/list.html'},
                    {name: '学员管理', url: './pages/xueyuan/list.html'},
                    {name: '车辆管理', url: './pages/cheliangxinxi/list.html'},
                    {name: '班型管理', url: './pages/banxing/list.html'}
                ]
            },
            {
                name: '教练排班管理',
                children: [
                    {name: '排班与计划管理', url: './pages/kechenganpai/list.html'},
                    {name: '排班信息维护', url: './pages/kechenganpai/list.html'}
                ]
            },
            {
                name: '理论学习考试管理',
                children: [
                    {name: '学习资源管理', url: './pages/shipin/list.html'},
                    {name: '题库与规则管理', url: './pages/exampaper/list.html'},
                    {name: '考试数据管理', url: './pages/examrecord/list.html'},
                    {name: '错题管理', url: './pages/examrecord/wrong.html'}
                ]
            },
            {
                name: '练车预约管理',
                children: [
                    {name: '名额规则配置', url: './pages/yuyuelianche/list.html'},
                    {name: '预约信息查询', url: './pages/yuyuelianche/list.html'},
                    {name: '预约取消管理', url: './pages/yuyuelianche/list.html'}
                ]
            },
            {
                name: '首页信息管理',
                children: [
                    {name: '首页轮播管理', url: './pages/config/list.html'},
                    {name: '公告管理', url: './pages/news/list.html'},
                    {name: '栏目管理', url: './pages/config/list.html'}
                ]
            },
            {
                name: '系统管理',
                children: [
                    {name: '系统参数配置', url: './pages/config/list.html'},
                    {name: '账号管理', url: './pages/users/list.html'},
                    {name: '角色权限管理', url: './pages/role/list.html'},
                    {name: '日志与审计管理', url: './pages/log/list.html'}
                ]
            }
        ]
    }
};

// 获取当前角色菜单
function getRoleMenu() {
    var userTable = localStorage.getItem('userTable');
    if (!userTable) {
        return roleMenus['visitor'];
    }
    return roleMenus[userTable] || roleMenus['visitor'];
}

// 获取导航菜单
function getNavMenu() {
    var userTable = localStorage.getItem('userTable');
    // 未登录或游客身份，返回游客菜单
    if (!userTable || userTable === 'visitor' || userTable === 'guest') {
        return roleMenus['visitor'].nav;
    }
    // 已登录用户，从menu配置中获取frontMenu
    var roleMenu = getRoleMenu();
    if (roleMenu.nav) {
        return roleMenu.nav;
    }
    return roleMenus['visitor'].nav;
}

// 获取侧边栏菜单
function getSidebarMenu() {
    return getRoleMenu().sidebar || [];
}
