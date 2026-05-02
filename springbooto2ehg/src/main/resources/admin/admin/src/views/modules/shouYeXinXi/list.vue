<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="24">
        <el-card>
          <div slot="header" class="clearfix">
            <span>首页信息管理</span>
            <el-button type="primary" @click="addOrUpdateHandle()" style="float: right; margin-top: -5px;">新增</el-button>
          </div>
          <el-table
            v-loading="listLoading"
            :data="list"
            element-loading-text="正在加载"
            border
            style="width: 100%"
          >
            <el-table-column prop="id" label="ID" width="80" />
            <el-table-column prop="leixing" label="板块类型" width="120" />
            <el-table-column prop="biaoti" label="标题" />
            <el-table-column label="内容预览" show-overflow-tooltip>
              <template slot-scope="scope">
                {{ previewContent(scope.row.neirong) }}
              </template>
            </el-table-column>
            <el-table-column prop="zhuangtai" label="状态" width="100">
              <template slot-scope="scope">
                <el-tag :type="statusTagType(scope.row.zhuangtai)">{{ statusLabel(scope.row.zhuangtai) }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="addtime" label="添加时间" width="180" />
            <el-table-column label="操作" width="200" align="center">
              <template slot-scope="scope">
                <el-button type="primary" size="small" @click="addOrUpdateHandle(scope.row.id)">编辑</el-button>
                <el-button type="danger" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
              </template>
            </el-table-column>
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

    <!-- 新增/修改对话框 -->
    <el-dialog :title="title" :visible.sync="dialogVisible" width="800px">
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="板块类型" prop="leixing">
          <el-select v-model="form.leixing" placeholder="请选择板块类型">
            <el-option label="驾校概况" value="驾校概况" />
            <el-option label="教练信息" value="教练信息" />
            <el-option label="报名须知" value="报名须知" />
          </el-select>
        </el-form-item>
        <el-form-item label="标题" prop="biaoti">
          <el-input v-model="form.biaoti" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="内容" prop="neirong">
          <editor v-model="form.neirong" action="file/upload" />
        </el-form-item>
        <el-form-item label="状态" prop="zhuangtai">
          <el-select v-model="form.zhuangtai" placeholder="请选择状态">
            <el-option label="已发布" value="已发布" />
            <el-option label="未发布" value="未发布" />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="submitForm">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
function createDefaultForm() {
  return {
    leixing: '驾校概况',
    biaoti: '',
    neirong: '',
    zhuangtai: '未发布'
  }
}

export default {
  data() {
    return {
      list: [],
      total: 0,
      page: 1,
      limit: 10,
      listLoading: false,
      dialogVisible: false,
      title: '',
      form: createDefaultForm(),
      rules: {
        leixing: [
          { required: true, message: '请选择板块类型', trigger: 'change' }
        ],
        biaoti: [
          { required: true, message: '请输入标题', trigger: 'blur' }
        ],
        neirong: [
          { required: true, message: '请输入内容', trigger: 'blur' }
        ],
        zhuangtai: [
          { required: true, message: '请选择状态', trigger: 'change' }
        ]
      }
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    getDefaultForm() {
      return createDefaultForm()
    },
    previewContent(content) {
      return (content || '')
        .replace(/<[^>]*>/g, '')
        .replace(/&nbsp;/gi, ' ')
        .replace(/\s+/g, ' ')
        .trim()
    },
    statusLabel(status) {
      return status === '启用' ? '已发布' : status === '禁用' ? '未发布' : status
    },
    statusTagType(status) {
      return ['已发布', '启用'].includes(status) ? 'success' : 'info'
    },
    getList() {
      this.listLoading = true
      this.$http.get('shouyexinxi/page', {
        params: {
          page: this.page,
          limit: this.limit
        }
      }).then(({ data: res }) => {
        if (res.code === 0) {
          this.list = res.data.list
          this.total = res.data.totalCount
        }
        this.listLoading = false
      }).catch(() => {
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
    },
    addOrUpdateHandle(id) {
      this.form = this.getDefaultForm()
      if (this.$refs.form) {
        this.$refs.form.clearValidate()
      }
      if (id) {
        this.title = '编辑首页信息'
        this.$http.get('shouyexinxi/info/' + id).then(({ data: res }) => {
          if (res.code === 0) {
            this.form = Object.assign(this.getDefaultForm(), res.data, {
              zhuangtai: this.statusLabel(res.data.zhuangtai)
            })
          }
        })
      } else {
        this.title = '新增首页信息'
      }
      this.dialogVisible = true
    },
    submitForm() {
      this.$refs.form.validate(valid => {
        if (valid) {
          const url = this.form.id ? 'shouyexinxi/update' : 'shouyexinxi/save'
          this.$http.post(url, this.form).then(({ data: res }) => {
            if (res.code === 0) {
              this.$message.success('操作成功')
              this.dialogVisible = false
              this.getList()
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      })
    },
    deleteHandle(id) {
      this.$confirm('确定要删除该首页信息吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http.post('shouyexinxi/delete', [id]).then(({ data: res }) => {
          if (res.code === 0) {
            this.$message.success('删除成功')
            this.getList()
          } else {
            this.$message.error(res.msg)
          }
        })
      })
    }
  }
}
</script>

<style scoped>
</style>
