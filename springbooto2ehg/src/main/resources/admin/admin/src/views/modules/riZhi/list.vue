<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="24">
        <el-card>
          <div slot="header" class="clearfix">
            <span>系统日志管理</span>
          </div>
          <el-table
            v-loading="listLoading"
            :data="list"
            element-loading-text="正在加载"
            border
            style="width: 100%"
          >
            <el-table-column prop="id" label="ID" width="80" />
            <el-table-column prop="caozuoyonghu" label="操作用户" />
            <el-table-column prop="caozuoleixing" label="操作类型" />
            <el-table-column prop="caozuoneirong" label="操作内容" show-overflow-tooltip />
            <el-table-column prop="caozuoshijian" label="操作时间" width="180" />
          </el-table>
          <el-pagination
            @size-change="sizeChangeHandle"
            @current-change="currentChangeHandle"
            :current-page="page"
            :page-sizes="[10, 20, 50, 100]"
            :page-size="limit"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total"
          />
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      list: [],
      total: 0,
      page: 1,
      limit: 10,
      listLoading: false
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      this.$http.get('rizhi/page', {
        params: {
          page: this.page,
          limit: this.limit
        }
      }).then(res => {
        if (res.code === 0) {
          this.list = res.page.list
          this.total = res.page.totalCount
        }
        this.listLoading = false
      })
    },
    sizeChangeHandle(val) {
      this.limit = val
      this.page = 1
      this.getList()
    },
    currentChangeHandle(val) {
      this.page = val
      this.getList()
    }
  }
}
</script>

<style scoped>
</style>