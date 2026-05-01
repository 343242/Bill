<template>
  <div class="main-content">
    <div v-if="showFlag">
      <el-form ref="form" :model="form" label-width="100px">
        <el-form-item label="规则名称">
          <el-input v-model="form.guizemingcheng" placeholder="请输入规则名称，如：上午班、下午班" />
        </el-form-item>
        <el-form-item label="开始时间">
          <el-time-picker v-model="form.kaishishijian" placeholder="请选择开始时间" format="HH:mm" value-format="HH:mm" />
        </el-form-item>
        <el-form-item label="结束时间">
          <el-time-picker v-model="form.jieshushijian" placeholder="请选择结束时间" format="HH:mm" value-format="HH:mm" />
        </el-form-item>
        <el-form-item label="适用教练">
          <el-select v-model="form.shiyongjiaolian" placeholder="请选择适用教练" style="width: 100%">
            <el-option label="全部教练" value="全部教练" />
            <el-option v-for="item in coachList" :key="item.jiaoliangonghao" :label="item.jiaolianxingming + '(' + item.jiaoliangonghao + ')'" :value="item.jiaoliangonghao" />
          </el-select>
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.zhuangtai">
            <el-radio label="启用" />
            <el-radio label="禁用" />
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div style="text-align: center; margin-top: 20px;">
        <el-button @click="back()">取消</el-button>
        <el-button type="primary" @click="submitForm()">确定</el-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    parent: {
      type: Object,
      default: () => ({})
    }
  },
  data() {
    return {
      showFlag: false,
      form: {},
      coachList: []
    };
  },
  methods: {
    init(id) {
      this.showFlag = true;
      this.form = { zhuangtai: '启用', shiyongjiaolian: '全部教练' };
      this.getCoachList();
      if (id) {
        this.$http({
          url: "paibanguize/info/" + id,
          method: "get"
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.form = data.data;
            // 数据库中时间格式可能是 HH:mm:ss，需要截取为 HH:mm 以适配 el-time-picker
            if (this.form.kaishishijian && this.form.kaishishijian.length > 5) {
              this.form.kaishishijian = this.form.kaishishijian.substring(0, 5);
            }
            if (this.form.jieshushijian && this.form.jieshushijian.length > 5) {
              this.form.jieshushijian = this.form.jieshushijian.substring(0, 5);
            }
          }
        });
      }
    },
    getCoachList() {
      this.$http({
        url: "jiaolian/page",
        method: "get",
        params: { page: 1, limit: 100 }
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.coachList = data.data.list;
        }
      });
    },
    submitForm() {
      if (!this.form.guizemingcheng) {
        this.$message.warning('请输入规则名称');
        return;
      }
      if (!this.form.kaishishijian) {
        this.$message.warning('请选择开始时间');
        return;
      }
      if (!this.form.jieshushijian) {
        this.$message.warning('请选择结束时间');
        return;
      }
      const url = this.form.id ? "paibanguize/update" : "paibanguize/save";
      this.$http({
        url: url,
        method: "post",
        data: this.form
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$message.success('操作成功');
          this.back();
          this.parent.getDataList();
        } else {
          this.$message.error(data.msg);
        }
      });
    },
    back() {
      this.showFlag = false;
      this.parent.showFlag = true;
    }
  }
};
</script>

<style scoped>
</style>
