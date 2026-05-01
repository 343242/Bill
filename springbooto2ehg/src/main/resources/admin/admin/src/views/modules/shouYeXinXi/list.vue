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
            <el-table-column prop="leixing" label="类型" />
            <el-table-column prop="biaoti" label="标题" />
            <el-table-column prop="neirong" label="内容" show-overflow-tooltip />
            <el-table-column prop="zhuangtai" label="状态" width="100">
              <template slot-scope="scope">
                <el-tag :type="scope.row.zhuangtai === '启用' ? 'success' : 'danger'">{{ scope.row.zhuangtai }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="addtime" label="添加时间" width="180" />
            <el-table-column label="操作" width="200" align="center">
              <template slot-scope="scope">
                <el-button type="primary" size="small" @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
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
      <el-form ref="form" :model="form" label-width="100px">
        <el-form-item label="类型">
          <el-select v-model="form.leixing" placeholder="请选择类型">
            <el-option label="驾校概况" value="驾校概况" />
            <el-option label="教练信息" value="教练信息" />
            <el-option label="报名须知" value="报名须知" />
          </el-select>
        </el-form-item>
        <el-form-item label="标题">
          <el-input v-model="form.biaoti" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="内容">
          <el-input type="textarea" v-model="form.neirong" placeholder="请输入内容" rows="5" />
        </el-form-item>
        <el-form-item label="图片">
          <file-upload v-model="form.tupian" :limit="1" />
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.zhuangtai">
            <el-radio label="启用" />
            <el-radio label="禁用" />
          </el-radio-group>
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
      form: {}
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      this.$http.get('shouyexinxi/page', {
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
    },
    addOrUpdateHandle(id) {
      this.form = {}
      if (id) {
        this.title = '修改首页信息'
        this.$http.get('shouyexinxi/info/' + id).then(res => {
          if (res.code === 0) {
            this.form = res.data
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
          this.$http.post(url, this.form).then(res => {
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
        this.$http.post('shouyexinxi/delete', [id]).then(res => {
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