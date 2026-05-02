<template>
  <div class="main-content">
    <div v-if="showFlag">
      <el-form :inline="true" class="form-content">
        <!-- 搜索 -->
        <el-row :gutter="20" class="slt">
          <el-form-item label="排班日期">
            <el-date-picker v-model="searchForm.paiBanriqi" type="month" value-format="yyyy-MM" placeholder="选择月份" clearable />
          </el-form-item>
          <el-form-item label="教练">
            <el-input v-model="searchForm.jiaolianxingming" placeholder="教练姓名" clearable />
          </el-form-item>
          <el-form-item>
            <el-button type="success" icon="el-icon-search" @click="search()">查询</el-button>
            <el-button @click="resetSearch()">重置</el-button>
          </el-form-item>
        </el-row>

        <el-row class="ad" style="justify-content: flex-start;">
          <el-form-item>
            <el-button v-if="isAuth('kechenganpai','新增')" type="success" icon="el-icon-plus" @click="addOrUpdateHandler()">新增排班</el-button>
          </el-form-item>
        </el-row>

        <el-table
          v-loading="dataListLoading"
          :data="dataList"
          border
          style="width: 100%"
        >
          <el-table-column prop="id" label="ID" width="80" />
          <el-table-column prop="paiBanriqi" label="排班日期" width="120">
            <template slot-scope="scope">
              {{ formatMonth(scope.row.paiBanriqi) }}
            </template>
          </el-table-column>
          <el-table-column prop="shijianduan" label="时间段" width="160" />
          <el-table-column prop="jiaoliangonghao" label="教练工号" width="120" />
          <el-table-column prop="jiaolianxingming" label="教练姓名" width="120" />
          <el-table-column prop="shangkezt" label="状态" width="100">
            <template slot-scope="scope">
              <el-tag :type="scope.row.shangkezt === '待上课' ? 'info' : scope.row.shangkezt === '上课中' ? 'success' : 'warning'">{{ scope.row.shangkezt || '待上课' }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="addtime" label="添加时间" width="180" />
          <el-table-column label="操作" width="200" align="center">
            <template slot-scope="scope">
              <el-button v-if="isAuth('kechenganpai','修改')" type="primary" size="mini" @click="addOrUpdateHandler(scope.row.id)">修改</el-button>
              <el-button v-if="isAuth('kechenganpai','删除')" type="danger" size="mini" @click="deleteHandler(scope.row.id)">删除</el-button>
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
      addOrUpdateFlag: false,
      searchForm: {}
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
      let params = {
        page: this.pageIndex,
        limit: this.pageSize,
        sort: 'paiBanriqi'
      };
      if (this.searchForm.paiBanriqi) {
        params['paiBanriqi'] = '%' + this.searchForm.paiBanriqi + '%';
      }
      if (this.searchForm.jiaolianxingming) {
        params['jiaolianxingming'] = '%' + this.searchForm.jiaolianxingming + '%';
      }
      this.$http({
        url: "kechenganpai/page",
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
    search() {
      this.pageIndex = 1;
      this.getDataList();
    },
    resetSearch() {
      this.searchForm = {};
      this.pageIndex = 1;
      this.getDataList();
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
    formatMonth(value) {
      if (!value) {
        return '';
      }
      return String(value).substring(0, 7);
    },
    addOrUpdateHandler(id) {
      this.showFlag = false;
      this.addOrUpdateFlag = true;
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id);
      });
    },
    deleteHandler(id) {
      this.$confirm('确定要删除该排班计划吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: "kechenganpai/delete",
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
