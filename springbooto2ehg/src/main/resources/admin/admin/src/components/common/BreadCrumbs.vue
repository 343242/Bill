<template>
  <el-breadcrumb class="app-breadcrumb" separator="/">
    <transition-group name="breadcrumb" class="box">
      <el-breadcrumb-item v-for="(item,index) in levelList" :key="item.path">
        <span v-if="item.redirect==='noRedirect'||index==levelList.length-1" class="no-redirect">{{ item.name }}</span>
        <a v-else @click.prevent="handleLink(item)">{{ item.name }}</a>
      </el-breadcrumb-item>
    </transition-group>
  </el-breadcrumb>
</template>

<script>
import pathToRegexp from 'path-to-regexp'
import { generateTitle } from '@/utils/i18n'
export default {
  data() {
    return {
      levelList: null
    }
  },
  watch: {
    $route() {
      this.getBreadcrumb()
    }
  },
  created() {
    this.getBreadcrumb()
  },
  methods: {
    generateTitle,
    getBreadcrumb() {
      let route = this.$route
      let matched = route.matched.filter(item => item.meta)
      const first = matched[0]
      matched = [{ path: '/index' }].concat(matched)

      this.levelList = matched.filter(item => item.meta)
    },
    isDashboard(route) {
      const name = route && route.name
      if (!name) {
        return false
      }
      return name.trim().toLocaleLowerCase() === 'Index'.toLocaleLowerCase()
    },
    pathCompile(path) {
      const { params } = this.$route
      var toPath = pathToRegexp.compile(path)
      return toPath(params)
    },
    handleLink(item) {
      const { redirect, path } = item
      if (redirect) {
        this.$router.push(redirect)
        return
      }
      this.$router.push(path)
    },
  }
}
</script>

<style lang="scss" scoped>
.app-breadcrumb {
  display: flex;
  align-items: center;
  height: 52px;
  line-height: 52px;
  padding: 0 22px;
  margin: 14px 0 8px 0;
  font-size: 14px;
  background: rgba(255, 255, 255, 0.72);
  backdrop-filter: blur(12px);
  -webkit-backdrop-filter: blur(12px);
  border: 1px solid rgba(230, 236, 250, 0.9);
  border-radius: 14px;
  box-shadow: 0 6px 18px rgba(120, 140, 200, 0.06);

  .box {
    display: flex;
    width: 100%;
    height: 100%;
    justify-content: flex-start;
    align-items: center;
  }

  &::v-deep .el-breadcrumb__item {
    .el-breadcrumb__inner {
      color: #7d8aa8;
      font-weight: 500;
      transition: color 0.2s ease;

      a, &.is-link {
        color: #7d8aa8;
        font-weight: 500;

        &:hover { color: #6ea8ff; }
      }
    }

    &:last-child .el-breadcrumb__inner {
      color: #1f2a44 !important;
      font-weight: 600;
    }

    .el-breadcrumb__separator {
      color: #c8d1e8 !important;
      margin: 0 10px;
    }
  }

  .no-redirect {
    color: #1f2a44;
    cursor: text;
    font-weight: 600;
  }
}
</style>
