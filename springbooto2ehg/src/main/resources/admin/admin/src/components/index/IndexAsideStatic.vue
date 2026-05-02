<template>
  <el-aside class="index-aside" width="220px">
    <div class="aside-decor"></div>
    <div class="index-aside-inner menulist">
      <div v-for="item in menuList" :key="item.roleName" v-if="role==item.roleName" class="menulist-item">
		<el-menu :mode="'vertical'" :unique-opened="true" class="el-menu-demo" default-active="0">
          <el-menu-item index="0" @click="menuHandler('')">
			<i class="el-icon-s-home menu-ico" />
			<span>首页</span>
		  </el-menu-item>
          <el-submenu :index="1+''">
            <template slot="title">
				<i class="el-icon-user-solid menu-ico" />
				<span>个人信息管理</span>
            </template>
            <el-menu-item :index="1-1" @click="menuHandler('updatePassword')">修改密码</el-menu-item>
            <el-menu-item :index="1-2" @click="menuHandler('center')">个人信息</el-menu-item>
          </el-submenu>
          <el-submenu v-for=" (menu,index) in item.backMenu" :key="menu.menu" :index="index+2+''">
            <template slot="title">
				<i class="el-icon-menu menu-ico" :class="icons[index]" />
				<span>{{ menu.menu }}</span>
            </template>
            <el-menu-item v-for=" (child,sort) in menu.child" v-if="!shouldHideMenuItem(child)" :key="sort" :index="(index+2)+'-'+sort" @click="menuHandler(child.tableName, child.menu)">{{ child.menu }}</el-menu-item>
          </el-submenu>
        </el-menu>
      </div>
    </div>
  </el-aside>
</template>

<script>
import menu, { normalizeMenus } from '@/utils/menu'
export default {
  data() {
    return {
      menuList: [],
      dynamicMenuRoutes: [],
      role: '',
      icons: [
        'el-icon-s-cooperation',
        'el-icon-s-order',
        'el-icon-s-platform',
        'el-icon-s-fold',
        'el-icon-s-unfold',
        'el-icon-s-operation',
        'el-icon-s-promotion',
        'el-icon-s-release',
        'el-icon-s-ticket',
        'el-icon-s-management',
        'el-icon-s-open',
        'el-icon-s-shop',
        'el-icon-s-marketing',
        'el-icon-s-flag',
        'el-icon-s-comment',
        'el-icon-s-finance',
        'el-icon-s-claim',
        'el-icon-s-custom',
        'el-icon-s-opportunity',
        'el-icon-s-data',
        'el-icon-s-check',
        'el-icon-s-grid',
      ],
    }
  },
  mounted() {
    const menus = menu.list()
    if(menus) {
        this.menuList = menus
    } else {
        let params = {
            page: 1,
            limit: 1,
            sort: 'id',
        }
        this.$http({
            url: "menu/list",
            method: "get",
            params: params
        }).then(({
            data
        }) => {
            if (data && data.code === 0) {
                this.menuList = normalizeMenus(JSON.parse(data.data.list[0].menujson));
                this.$storage.set("menus", this.menuList);
            }
        })
    }
    this.role = this.$storage.get('role')
  },
  created(){
    this.icons.sort(()=>{
      return (0.5-Math.random())
    })
  },
  methods: {
    shouldHideMenuItem(child) {
      return this.role === '管理员' && ['examrecord', 'cuotiban', 'examfailrecord'].includes(child.tableName)
    },
    menuHandler(name, menuName) {
      let router = this.$router
      name = '/'+name
      if (menuName) {
        if (this.$route.path !== name || this.$route.query.view !== menuName) {
          router.push({path: name, query: {view: menuName}}).catch(err => {
            if (err.name !== 'NavigationDuplicated') {
              console.error(err)
            }
          })
        }
      } else {
        if (this.$route.path !== name) {
          router.push(name).catch(err => {
            if (err.name !== 'NavigationDuplicated') {
              console.error(err)
            }
          })
        }
      }
    },
  }
}
</script>
<style lang="scss" scoped>
  .el-container { display: block; }

  .index-aside {
    position: relative;
    overflow: hidden;
    background:
	  linear-gradient(180deg, #ffffff 0%, #f7faff 100%);
    border-right: 1px solid rgba(230, 236, 250, 0.9);
    font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", "PingFang SC", "Microsoft YaHei", sans-serif;

    .aside-decor {
      position: absolute;
      inset: 0;
      pointer-events: none;
      background:
        radial-gradient(400px 200px at 50% -60px, rgba(110,168,255,0.15), transparent 60%),
        radial-gradient(400px 300px at 50% 110%, rgba(166,111,255,0.12), transparent 60%);
    }

    .index-aside-inner {
      position: relative;
      height: calc(100vh - 64px);
      padding: 14px 12px;
      overflow-y: auto;
      overflow-x: hidden;
      box-sizing: border-box;
      z-index: 1;

      &::-webkit-scrollbar { width: 6px; }
      &::-webkit-scrollbar-thumb {
        background: #d8e2fa;
        border-radius: 3px;
      }
      &::-webkit-scrollbar-track { background: transparent; }

      &:focus { outline: none; }

      &::v-deep .el-menu {
        border: 0;
        background-color: transparent;
      }
    }

    .menulist-item {
      width: 100%;
      padding: 0;
      background: transparent;
      box-sizing: border-box;
    }

    .el-menu-demo {
      box-sizing: border-box;

      &::v-deep .menu-ico {
        width: 22px;
        text-align: center;
        margin-right: 10px;
        font-size: 16px;
        color: #8a96b5;
        transition: color 0.25s ease;
      }

      &::v-deep .el-menu-item,
      &::v-deep .el-submenu__title {
        height: 44px !important;
        line-height: 44px !important;
        padding: 0 14px !important;
        margin: 4px 0;
        color: #4a5677 !important;
        font-size: 14px;
        border-radius: 10px;
        background: transparent !important;
        display: flex;
        align-items: center;
        transition: all 0.25s ease;
        position: relative;

        &:hover {
          background: linear-gradient(135deg, rgba(110,168,255,0.1) 0%, rgba(166,111,255,0.1) 100%) !important;
          color: #3b4870 !important;
          .menu-ico { color: #6ea8ff; }
        }
      }

      &>.el-menu-item.is-active {
        color: #fff !important;
        background: linear-gradient(135deg, #6ea8ff 0%, #a66fff 100%) !important;
        box-shadow: 0 8px 18px rgba(110, 130, 255, 0.32);

        &::v-deep .menu-ico { color: #fff !important; }
      }

      &::v-deep .el-submenu__icon-arrow {
        color: #a8b2cc !important;
        right: 14px;
        font-size: 12px;
      }

      &::v-deep .el-menu.el-menu--inline {
        padding: 4px 0 4px 10px;
        background: transparent;
        border-left: 2px dashed rgba(110, 168, 255, 0.2);
        margin-left: 18px;

        .el-menu-item {
          height: 38px !important;
          line-height: 38px !important;
          padding: 0 14px !important;
          margin: 2px 0;
          color: #6c779a !important;
          font-size: 13px;
          border-radius: 8px;
          background: transparent !important;
          text-align: left;
          min-width: auto;
          position: relative;

          &::before {
            content: "";
            position: absolute;
            left: 6px;
            top: 50%;
            width: 6px;
            height: 6px;
            border-radius: 50%;
            background: #d8e2fa;
            transform: translateY(-50%);
            transition: all 0.25s ease;
          }

          &.is-active {
            color: #6ea8ff !important;
            background: rgba(110, 168, 255, 0.08) !important;
            font-weight: 600;

            &::before {
              background: #6ea8ff;
              box-shadow: 0 0 0 3px rgba(110, 168, 255, 0.18);
            }
          }

          &:hover {
            color: #3b4870 !important;
            background: rgba(110, 168, 255, 0.06) !important;
            &::before { background: #a66fff; }
          }
        }
      }
    }
  }
</style>
<style>
.el-menu--horizontal .el-menu--popup {
  width: auto;
  min-width: 160px;
  padding: 6px;
  margin: 0;
  background: rgba(255, 255, 255, 0.96);
  backdrop-filter: blur(14px);
  border-radius: 12px;
  border: 1px solid rgba(230, 236, 250, 0.9);
  box-shadow: 0 12px 28px rgba(100, 120, 200, 0.15);
}
.el-menu--horizontal .el-menu--popup .el-menu-item {
  width: 100%;
  height: 38px;
  line-height: 38px;
  padding: 0 14px;
  margin: 2px 0;
  color: #4a5677 !important;
  font-size: 13px;
  border-radius: 8px;
  background: transparent !important;
  text-align: left;
  min-width: auto;
}
.el-menu--horizontal .el-menu--popup .el-menu-item:hover {
  color: #fff !important;
  background: linear-gradient(135deg, #6ea8ff 0%, #a66fff 100%) !important;
}
</style>
