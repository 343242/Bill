import storage from '@/utils/storage'

function stripCoordinatorLabel(label) {
    return typeof label === 'string' ? label.replace(/统筹/g, '') : label;
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
            normalizedRoleMenu.backMenu = roleMenu.backMenu.map(parentMenu => {
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
