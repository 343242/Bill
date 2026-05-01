<template>
  <div class="main-content">
    <!-- 列表页 -->
    <div v-if="!showFlag">
      <el-form :inline="true" :model="searchForm" class="form-content">
        <el-form-item label="练习题库">
          <el-input v-model="searchForm.papername" placeholder="练习题库名称" clearable></el-input>
        </el-form-item>
        <el-form-item label="试题">
          <el-input v-model="searchForm.questionname" placeholder="试题名称" clearable></el-input>
        </el-form-item>
        <el-form-item label="学员">
          <el-input v-model="searchForm.username" placeholder="学员姓名" clearable></el-input>
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
          <el-table-column prop="username" header-align="center" align="center" sortable label="姓名"></el-table-column>
          <el-table-column
            prop="papername"
            header-align="center"
            align="center"
            sortable
            label="练习题库"
          ></el-table-column>
          <el-table-column
            prop="questionname"
            header-align="center"
            align="center"
            sortable
            label="试题名称"
          ></el-table-column>
          <el-table-column prop="score" header-align="center" align="center" sortable label="分值"></el-table-column>
          <el-table-column header-align="center" align="center" label="选项内容" width="220">
            <template slot-scope="scope">
              <div class="options-cell">{{ formatOptions(scope.row.options) }}</div>
            </template>
          </el-table-column>
          <el-table-column prop="answer" header-align="center" align="center" sortable label="正确答案"></el-table-column>
          <el-table-column
            prop="myanswer"
            header-align="center"
            align="center"
            sortable
            label="考生答案"
          ></el-table-column>
          <el-table-column
            prop="analysis"
            header-align="center"
            align="center"
            sortable
            label="试题分析"
          ></el-table-column>
          <el-table-column
            prop="addtime"
            header-align="center"
            align="center"
            sortable
            width="170"
            label="考试时间"
          ></el-table-column>
          <el-table-column fixed="right" header-align="center" align="center" width="100" label="操作">
            <template slot-scope="scope">
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
  </div>
</template>
<script>
export default {
  data() {
    return {
      searchForm: {
        papername: "",
        questionname: "",
        username: ""
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      showFlag: false,
      user: {}
    };
  },
  mounted() {
    this.$http({
      url: `${this.$storage.get("sessionTable")}/session`,
      method: "get"
    }).then(({ data }) => {
      if (data && data.code === 0) {
        this.user = data.data;
      } else {
        this.$message.error(data.msg);
      }
    });
    this.init();
    this.getDataList();
  },
  components: {},
  methods: {
    init() {},
    search() {
      this.pageIndex = 1;
      this.getDataList();
    },
    reset() {
      this.searchForm = {
        papername: "",
        questionname: "",
        username: ""
      };
      this.pageIndex = 1;
      this.getDataList();
    },
    // 获取数据列表
    getDataList() {
      this.dataListLoading = true;
      var params = {
        page: this.pageIndex,
        limit: this.pageSize,
        sort: "id",
        myscore: 0
      };
      if (this.searchForm.papername) {
        params["papername"] = `%${this.searchForm.papername}%`;
      }
      if (this.searchForm.questionname) {
        params["questionname"] = `%${this.searchForm.questionname}%`;
      }
      if (this.searchForm.username) {
        params["username"] = `%${this.searchForm.username}%`;
      }
      this.$http({
        url: "/examrecord/page",
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
    formatOptions(options) {
      if (!options) {
        return "";
      }
      try {
        const optionList = typeof options === "string" ? JSON.parse(options) : options;
        if (!Array.isArray(optionList)) {
          return "";
        }
        return optionList
          .map(item => item && item.text ? item.text : "")
          .filter(item => item)
          .join("\n");
      } catch (e) {
        return options;
      }
    },
    // 多选
    selectionChangeHandler(val) {
      this.dataListSelections = val;
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
          url: "/examrecord/delete",
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
.options-cell {
  white-space: pre-line;
  text-align: left;
  line-height: 1.6;
}
</style>
