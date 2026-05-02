<template>
  <div class="main-content">
    <!-- 列表页 -->
    <div v-if="!showFlag">
      <el-form :inline="true" :model="searchForm" class="form-content">
        <el-form-item label="学员账号">
          <el-input v-model="searchForm.zhanghao" placeholder="学员账号" clearable></el-input>
        </el-form-item>
        <el-form-item label="题目内容">
          <el-input v-model="searchForm.timuneirong" placeholder="题目内容" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button round @click="search()">查询</el-button>
          <el-button round @click="reset()">重置</el-button>
        </el-form-item>
      </el-form>
      <div class="table-content">
        <el-table
          :data="dataList"
          border
          v-loading="dataListLoading"
          @selection-change="selectionChangeHandler"
          style="width: 100%;"
        >
          <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
          <el-table-column prop="zhanghao" header-align="center" align="center" sortable label="学员账号"></el-table-column>
          <el-table-column prop="timuneirong" header-align="center" align="center" sortable label="题目内容" show-overflow-tooltip></el-table-column>
          <el-table-column prop="cuowudaan" header-align="center" align="center" sortable label="错误答案"></el-table-column>
          <el-table-column prop="zhengquedaan" header-align="center" align="center" sortable label="正确答案"></el-table-column>
          <el-table-column prop="addtime" header-align="center" align="center" sortable label="添加时间" width="180"></el-table-column>
          <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="removeOrChange(scope.row)">修改</el-button>
              <el-button type="text" size="small" @click="deleteHandle(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          @size-change="sizeChangeHandle"
          @current-change="currentChangeHandle"
          :current-page="pageIndex"
          :page-sizes="[10, 20, 50, 100]"
          :page-size="pageSize"
          :total="totalPage"
          layout="total, sizes, prev, pager, next, jumper"
          class="pagination-content"
        ></el-pagination>
      </div>
    </div>

    <!-- 添加/修改页面 -->
    <div v-else>
      <el-row>
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>{{ !this.dataForm.id ? '添加' : '修改' }}</span>
          </div>
          <el-form :model="dataForm" :rules="rules" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="120px">
            <el-form-item label="学员账号" prop="zhanghao">
              <el-input v-model="dataForm.zhanghao" placeholder="学员账号"></el-input>
            </el-form-item>
            <el-form-item label="题目内容" prop="timuneirong">
              <el-input type="textarea" v-model="dataForm.timuneirong" placeholder="题目内容"></el-input>
            </el-form-item>
            <el-form-item label="错误答案" prop="cuowudaan">
              <el-input v-model="dataForm.cuowudaan" placeholder="错误答案"></el-input>
            </el-form-item>
            <el-form-item label="正确答案" prop="zhengquedaan">
              <el-input v-model="dataForm.zhengquedaan" placeholder="正确答案"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="dataFormSubmit()">提交</el-button>
              <el-button @click="resetForm()">取消</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      searchForm: {
        zhanghao: "",
        timuneirong: ""
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      showFlag: false,
      dataForm: {
        id: 0,
        zhanghao: "",
        timuid: "",
        timuneirong: "",
        cuowudaan: "",
        zhengquedaan: "",
        addtime: ""
      },
      rules: {}
    };
  },
  mounted() {
    if (this.$storage.get("role") === "管理员") {
      this.$message.warning("管理员端已隐藏错题本管理页面");
      this.$router.replace({ path: "/index/" });
      return;
    }
    this.getDataList();
  },
  components: {},
  methods: {
    // 获取数据列表
    getDataList() {
      this.dataListLoading = true;
      var params = {
        page: this.pageIndex,
        limit: this.pageSize,
        sort: "id"
      };
      if (this.searchForm.zhanghao) {
        params["zhanghao"] = `%${this.searchForm.zhanghao}%`;
      }
      if (this.searchForm.timuneirong) {
        params["timuneirong"] = `%${this.searchForm.timuneirong}%`;
      }
      this.$http({
        url: "/cuotiban/list",
        method: "get",
        params: params
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.dataList = data.data.list;
          this.totalPage = data.data.total;
        } else {
          this.dataList = [];
          this.totalPage = 0;
        }
        this.dataListLoading = false;
      });
    },
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getDataList();
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getDataList();
    },
    // 多选
    selectionChangeHandler(val) {
      this.dataListSelections = val;
    },
    // 查询
    search() {
      this.pageIndex = 1;
      this.getDataList();
    },
    // 重置
    reset() {
      this.searchForm = {};
      this.pageIndex = 1;
      this.getDataList();
    },
    // 重置表单
    resetForm() {
      this.showFlag = false;
      this.dataForm = {
        id: 0,
        zhanghao: "",
        timuid: "",
        timuneirong: "",
        cuowudaan: "",
        zhengquedaan: "",
        addtime: ""
      };
    },
    // 添加/修改
    removeOrChange(row) {
      this.showFlag = true;
      this.dataForm = Object.assign({}, row);
    },
    // 删除
    deleteHandle(row) {
      var ids = row.id
        ? [row.id]
        : this.dataListSelections.map(item => {
            return item.id;
          });
      this.$confirm(`确定进行删除操作?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$http({
          url: "/cuotiban/delete",
          method: "post",
          data: ids
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.$message({
              message: "操作成功",
              type: "success",
              duration: 1500,
              onClose: () => {
                this.getDataList();
              }
            });
          } else {
            this.$message.error(data.msg);
          }
        });
      });
    },
    // 提交
    dataFormSubmit() {
      this.$refs["dataForm"].validate(valid => {
        if (valid) {
          this.$http({
            url: "/cuotiban/" + (this.dataForm.id ? "update" : "save"),
            method: "post",
            data: this.dataForm
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: "操作成功",
                type: "success",
                duration: 1500,
                onClose: () => {
                  this.showFlag = false;
                  this.getDataList();
                }
              });
            } else {
              this.$message.error(data.msg);
            }
          });
        }
      });
    }
  }
};
</script>

<style lang="scss" scoped>
.form-content {
  background: transparent;
}
.table-content {
  background: transparent;
}
</style>
