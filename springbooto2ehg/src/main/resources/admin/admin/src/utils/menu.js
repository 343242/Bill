import storage from '@/utils/storage'

function stripCoordinatorLabel(label) {
    return typeof label === 'string' ? label.replace(/统筹/g, '') : label;
}

function renameMenuItem(item, menu) {
    return item ? {
        ...item,
        menu
    } : null;
}

function dedupeMenuItems(items) {
    const seen = new Set();
    return items.filter(item => {
        if (!item) {
            return false;
        }
        const key = `${item.tableName || ''}:${item.menuJump || ''}`;
        if (seen.has(key)) {
            return false;
        }
        seen.add(key);
        return true;
    });
}

function restructureAdminBackMenu(backMenu) {
    const studentMenuIndex = backMenu.findIndex(parentMenu => ['基础信息管理', '学员信息管理'].includes(parentMenu.menu));
    const homeMenuIndex = backMenu.findIndex(parentMenu => parentMenu.menu === '首页信息管理');

    if (studentMenuIndex === -1 || homeMenuIndex === -1) {
        return backMenu;
    }

    const studentParentMenu = backMenu[studentMenuIndex];
    const homeParentMenu = backMenu[homeMenuIndex];
    const allChildren = backMenu.flatMap(parentMenu => Array.isArray(parentMenu.child) ? parentMenu.child : []);

    const studentInfoMenu = allChildren.find(child => child.tableName === 'xueyuan');
    const signupInfoMenu = allChildren.find(child => child.tableName === 'baomingxinxi');
    const coachInfoMenu = allChildren.find(child => child.tableName === 'jiaolian');
    const noticeMenu = allChildren.find(child => child.tableName === 'news');
    const schoolOverviewMenu = allChildren.find(child => child.tableName === 'shouYeXinXi');
    return backMenu.map((parentMenu, index) => {
        if (index === studentMenuIndex) {
            return {
                ...studentParentMenu,
                menu: '学员信息管理（原基础信息管理）',
                child: dedupeMenuItems([
                    renameMenuItem(studentInfoMenu, '学员信息管理'),
                    renameMenuItem(signupInfoMenu, '报名信息管理')
                ])
            };
        }
        if (index === homeMenuIndex) {
            return {
                ...homeParentMenu,
                menu: '首页信息管理',
                child: dedupeMenuItems([
                    renameMenuItem(schoolOverviewMenu, '驾校概况'),
                    renameMenuItem(coachInfoMenu, '教练信息展示'),
                    renameMenuItem(noticeMenu, '报名须知')
                ])
            };
        }
        return parentMenu;
    });
}

export function normalizeMenus(menus) {
    if (!Array.isArray(menus)) {
        return menus;
    }
    return menus.map(roleMenu => {
        const isAdminRole = roleMenu.roleName === '管理员';
        const normalizedRoleMenu = {
            ...roleMenu
        };
        if (Array.isArray(roleMenu.backMenu)) {
            const normalizedBackMenu = roleMenu.backMenu.map(parentMenu => {
                const normalizedParentMenu = {
                    ...parentMenu,
                    menu: isAdminRole ? stripCoordinatorLabel(parentMenu.menu) : parentMenu.menu
                };
                if (Array.isArray(parentMenu.child)) {
                    normalizedParentMenu.child = parentMenu.child
                        .filter(child => child.menu !== '视频互动管理')
                        .map(child => {
                            const normalizedChild = {
                                ...child,
                                menu: isAdminRole ? stripCoordinatorLabel(child.menu) : child.menu
                            };
                            if (child.menu === '理论考试') {
                                return {
                                    ...normalizedChild,
                                    menu: '考试记录'
                                };
                            }
                            return normalizedChild;
                        });
                }
                return normalizedParentMenu;
            });
            normalizedRoleMenu.backMenu = isAdminRole ? restructureAdminBackMenu(normalizedBackMenu) : normalizedBackMenu;
        }
        if (Array.isArray(roleMenu.frontMenu)) {
            normalizedRoleMenu.frontMenu = roleMenu.frontMenu.map(parentMenu => {
                const normalizedParentMenu = {
                    ...parentMenu
                };
                if (Array.isArray(parentMenu.child)) {
                    normalizedParentMenu.child = parentMenu.child.map(child => {
                        if (child.menu === '理论考试') {
                            return {
                                ...child,
                                menu: '考试记录'
                            };
                        }
                        return child;
                    });
                }
                return normalizedParentMenu;
            });
        }
        return normalizedRoleMenu;
    });
}
const menu = {
    list() {
        if(storage.get("menus")) {
            return normalizeMenus(eval('(' + storage.get("menus")+ ')'));
        } else {
            return null;
        }
    }
}
export default menu;
