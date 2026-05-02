<template>
  <div class="app-container">
    <div v-if="showFlag">
      <el-row :gutter="20">
        <el-col :span="24">
          <el-card>
            <div slot="header" class="clearfix">
              <span>首页信息管理</span>
              <el-button type="primary" @click="openAddDialog" style="float: right; margin-top: -5px;">新增</el-button>
            </div>
            <el-table
              v-loading="listLoading"
              :data="list"
              element-loading-text="正在加载"
              border
              style="width: 100%"
            >
              <el-table-column prop="id" label="ID" width="100" />
              <el-table-column prop="leixing" label="板块类型" width="120" />
              <el-table-column prop="biaoti" label="标题" min-width="180" />
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
                  <el-button type="primary" size="small" @click="editHandler(scope.row)">编辑</el-button>
                  <el-button type="danger" size="small" @click="deleteHandler(scope.row)">删除</el-button>
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
    </div>

    <el-dialog :title="title" :visible.sync="dialogVisible" width="800px">
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="板块类型" prop="leixing">
          <el-input v-model="form.leixing" readonly />
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

    <el-dialog title="选择新增板块" :visible.sync="sourceDialogVisible" width="420px">
      <el-form label-width="100px">
        <el-form-item label="板块类型">
          <el-select v-model="selectedSourceType" placeholder="请选择板块类型" style="width: 100%;">
            <el-option label="驾校概况" value="shouyexinxi" />
            <el-option label="教练信息" value="jiaolian" />
            <el-option label="报名须知" value="news" />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="sourceDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="confirmAddSource">确定</el-button>
      </div>
    </el-dialog>

    <jiaolian-add-or-update
      v-if="addOrUpdateFlag && jiaolianAddOrUpdateFlag"
      :parent="this"
      ref="jiaolianAddOrUpdate"
    ></jiaolian-add-or-update>

    <news-add-or-update
      v-if="addOrUpdateFlag && newsAddOrUpdateFlag"
      :parent="this"
      ref="newsAddOrUpdate"
    ></news-add-or-update>
  </div>
</template>

<script>
import JiaolianAddOrUpdate from '../jiaolian/add-or-update'
import NewsAddOrUpdate from '../news/add-or-update'

function createDefaultForm() {
  return {
    leixing: '驾校概况',
    biaoti: '',
    neirong: '',
    zhuangtai: '未发布'
  }
}

export default {
  components: {
    JiaolianAddOrUpdate,
    NewsAddOrUpdate
  },
  data() {
    return {
      list: [],
      total: 0,
      page: 1,
      limit: 10,
      listLoading: false,
      showFlag: true,
      dialogVisible: false,
      sourceDialogVisible: false,
      jiaolianAddOrUpdateFlag: false,
      newsAddOrUpdateFlag: false,
      addOrUpdateFlag: false,
      jiaolianCrossAddOrUpdateFlag: false,
      newsCrossAddOrUpdateFlag: false,
      title: '',
      selectedSourceType: 'shouyexinxi',
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
      if (['已发布', '启用', '显示'].includes(status)) {
        return '已发布'
      }
      if (['未发布', '禁用', '隐藏'].includes(status)) {
        return '未发布'
      }
      return status || '未发布'
    },
    statusTagType(status) {
      return this.statusLabel(status) === '已发布' ? 'success' : 'info'
    },
    search() {
      this.getList()
    },
    contentStyleChange() {
    },
    getList() {
      this.listLoading = true
      this.$http.get('shouyexinxi/aggregatePage', {
        params: {
          page: this.page,
          limit: this.limit
        }
      }).then(({ data: res }) => {
        if (res.code === 0) {
          this.list = res.data.list
          this.total = res.data.total
        } else {
          this.list = []
          this.total = 0
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
    openAddDialog() {
      this.selectedSourceType = 'shouyexinxi'
      this.sourceDialogVisible = true
    },
    confirmAddSource() {
      this.sourceDialogVisible = false
      if (this.selectedSourceType === 'shouyexinxi') {
        this.openShouyeDialog()
        return
      }
      if (this.selectedSourceType === 'jiaolian') {
        this.showFlag = false
        this.addOrUpdateFlag = true
        this.newsAddOrUpdateFlag = false
        this.jiaolianAddOrUpdateFlag = true
        this.$nextTick(() => {
          this.$refs.jiaolianAddOrUpdate.init(undefined, 'else')
        })
        return
      }
      this.showFlag = false
      this.addOrUpdateFlag = true
      this.jiaolianAddOrUpdateFlag = false
      this.newsAddOrUpdateFlag = true
      this.$nextTick(() => {
        this.$refs.newsAddOrUpdate.init(undefined, 'else')
      })
    },
    openShouyeDialog(id) {
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
    editHandler(row) {
      if (row.sourceTable === 'shouyexinxi') {
        this.openShouyeDialog(row.sourceId)
        return
      }
      this.showFlag = false
      this.addOrUpdateFlag = true
      if (row.sourceTable === 'jiaolian') {
        this.newsAddOrUpdateFlag = false
        this.jiaolianAddOrUpdateFlag = true
        this.$nextTick(() => {
          this.$refs.jiaolianAddOrUpdate.init(row.sourceId, 'else')
        })
        return
      }
      this.jiaolianAddOrUpdateFlag = false
      this.newsAddOrUpdateFlag = true
      this.$nextTick(() => {
        this.$refs.newsAddOrUpdate.init(row.sourceId, 'else')
      })
    },
    submitForm() {
      this.$refs.form.validate(valid => {
        if (!valid) {
          return
        }
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
      })
    },
    deleteHandler(row) {
      const endpointMap = {
        shouyexinxi: 'shouyexinxi/delete',
        jiaolian: 'jiaolian/delete',
        news: 'news/delete'
      }
      this.$confirm('确定要删除该条记录吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http.post(endpointMap[row.sourceTable], [Number(row.sourceId)]).then(({ data: res }) => {
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
