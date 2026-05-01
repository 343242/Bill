<template>
  <div class="main-content">
    <div v-if="!showFlag">
      <el-form :inline="true" :model="searchForm" class="form-content">
        <el-form-item label="视频标题">
          <el-select v-model="searchForm.shipinid" placeholder="请选择视频" clearable>
            <el-option label="全部" value=""></el-option>
            <el-option v-for="item in shipinList" :key="item.id" :label="item.biaoti" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="评论人">
          <el-input v-model="searchForm.pinglunren" placeholder="评论人" clearable></el-input>
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
          <el-table-column prop="shipinid" header-align="center" align="center" label="视频标题">
            <template slot-scope="scope">
              {{ getShipinTitle(scope.row.shipinid) }}
            </template>
          </el-table-column>
          <el-table-column prop="pinglunneirong" header-align="center" align="center" label="评论内容" show-overflow-tooltip></el-table-column>
          <el-table-column prop="pinglunren" header-align="center" align="center" sortable label="评论人"></el-table-column>
          <el-table-column prop="pingluntime" header-align="center" align="center" sortable label="评论时间" width="180"></el-table-column>
          <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="replyHandle(scope.row)">回复</el-button>
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

    <!-- 回复对话框 -->
    <div v-else>
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>回复评论</span>
          <el-button style="float: right;" @click="showFlag = false">返回</el-button>
        </div>
        <el-form :model="replyForm" label-width="100px">
          <el-form-item label="评论内容">
            <div>{{ replyForm.pinglunneirong }}</div>
          </el-form-item>
          <el-form-item label="回复内容">
            <el-input type="textarea" v-model="replyForm.huifuneirong" placeholder="请输入回复内容" :rows="4"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitReply()">提交回复</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      searchForm: {
        shipinid: "",
        pinglunren: ""
      },
      dataList: [],
      shipinList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      showFlag: false,
      replyForm: {
        id: "",
        pinglunneirong: "",
        huifuneirong: ""
      }
    };
  },
  mounted() {
    this.getShipinList();
    this.getDataList();
  },
  methods: {
    getShipinList() {
      this.$http({
        url: "/shipin/list",
        method: "get",
        params: { page: 1, limit: 1000 }
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.shipinList = data.data.list;
        }
      });
    },
    getShipinTitle(id) {
      const item = this.shipinList.find(v => v.id === id);
      return item ? item.biaoti : "";
    },
    getDataList() {
      this.dataListLoading = true;
      var params = {
        page: this.pageIndex,
        limit: this.pageSize,
        sort: "id"
      };
      if (this.searchForm.shipinid) {
        params["shipinid"] = this.searchForm.shipinid;
      }
      if (this.searchForm.pinglunren) {
        params["pinglunren"] = `%${this.searchForm.pinglunren}%`;
      }
      this.$http({
        url: "/shipinpinglun/list",
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
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getDataList();
    },
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getDataList();
    },
    selectionChangeHandler(val) {
      this.dataListSelections = val;
    },
    search() {
      this.pageIndex = 1;
      this.getDataList();
    },
    reset() {
      this.searchForm = {};
      this.pageIndex = 1;
      this.getDataList();
    },
    replyHandle(row) {
      this.showFlag = true;
      this.replyForm = {
        id: row.id,
        pinglunneirong: row.pinglunneirong,
        huifuneirong: row.huifuneirong || ""
      };
    },
    submitReply() {
      this.$http({
        url: "/shipinpinglun/update",
        method: "post",
        data: this.replyForm
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$message.success("回复成功");
          this.showFlag = false;
          this.getDataList();
        } else {
          this.$message.error(data.msg);
        }
      });
    },
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
          url: "/shipinpinglun/delete",
          method: "post",
          data: ids
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.$message({
              message: "删除成功",
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
</style>
