<template>
  <div class="main-content">
    <div v-if="showFlag">
      <el-form :inline="true" class="form-content">
        <el-alert
          title="名额规则配置：设定每个教练排班时段可接受的最大预约学员数量"
          type="info"
          :closable="false"
          show-icon
          style="margin-bottom: 15px;"
        />

        <el-row class="ad" style="justify-content: flex-start;">
          <el-form-item>
            <el-button v-if="isAuth('xiTongCanShu','新增')" type="success" icon="el-icon-plus" @click="addOrUpdateHandler()">新增参数</el-button>
          </el-form-item>
        </el-row>

        <el-table
          v-loading="dataListLoading"
          :data="dataList"
          border
          style="width: 100%"
        >
          <el-table-column prop="id" label="ID" width="80" />
          <el-table-column prop="canshumingcheng" label="参数名称" width="180" />
          <el-table-column prop="canshujian" label="参数键" width="180" />
          <el-table-column prop="canshuzhi" label="参数值" width="120">
            <template slot-scope="scope">
              <el-tag type="primary" size="medium">{{ scope.row.canshuzhi }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="beizhu" label="说明" />
          <el-table-column prop="zhuangtai" label="状态" width="100">
            <template slot-scope="scope">
              <el-tag :type="scope.row.zhuangtai === '启用' ? 'success' : 'danger'">{{ scope.row.zhuangtai }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="200" align="center">
            <template slot-scope="scope">
              <el-button v-if="isAuth('xiTongCanShu','修改')" type="primary" size="mini" @click="addOrUpdateHandler(scope.row.id)">修改</el-button>
              <el-button v-if="isAuth('xiTongCanShu','删除')" type="danger" size="mini" @click="deleteHandler(scope.row.id)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          @size-change="sizeChangeHandle"
          @current-change="currentChangeHandle"
          :current-page="pageIndex"
          :page-sizes="[10, 20, 50, 100]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="totalPage"
        />
      </el-form>
    </div>

    <!-- 新增/修改 -->
    <add-or-update v-if="addOrUpdateFlag" :parent="this" ref="addOrUpdate"></add-or-update>
  </div>
</template>

<script>
import AddOrUpdate from "./add-or-update";
export default {
  data() {
    return {
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      showFlag: true,
      addOrUpdateFlag: false
    };
  },
  created() {
    this.getDataList();
  },
  components: {
    AddOrUpdate
  },
  methods: {
    getDataList() {
      this.dataListLoading = true;
      this.$http({
        url: "xitongcanshu/page",
        method: "get",
        params: {
          page: this.pageIndex,
          limit: this.pageSize
        }
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
    addOrUpdateHandler(id) {
      this.showFlag = false;
      this.addOrUpdateFlag = true;
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id);
      });
    },
    deleteHandler(id) {
      this.$confirm('确定要删除该配置吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: "xitongcanshu/delete",
          method: "post",
          data: [id]
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.$message.success('删除成功');
            this.getDataList();
          } else {
            this.$message.error(data.msg);
          }
        });
      }).catch(() => {});
    }
  }
};
</script>

<style scoped>
</style>
