<template>
  <div class="main-content">
    <div v-if="showFlag">
      <el-form ref="form" :model="form" label-width="100px">
        <el-form-item label="排班日期">
          <el-date-picker v-model="form.paiBanriqi" type="month" value-format="yyyy-MM" placeholder="请选择排班月份" style="width: 100%" />
        </el-form-item>
        <el-form-item label="时间段">
          <el-select v-model="form.shijianduan" placeholder="请选择时间段" style="width: 100%" @change="onTimeSlotChange">
            <el-option v-for="item in ruleList" :key="item.id" :label="item.guizemingcheng + ' (' + item.kaishishijian + '-' + item.jieshushijian + ')'" :value="item.guizemingcheng + ' (' + item.kaishishijian + '-' + item.jieshushijian + ')'" />
          </el-select>
        </el-form-item>
        <el-form-item label="选择教练">
          <el-select v-model="form.jiaoliangonghao" placeholder="请选择教练" style="width: 100%" @change="onCoachChange">
            <el-option v-for="item in coachList" :key="item.jiaoliangonghao" :label="item.jiaolianxingming + '(' + item.jiaoliangonghao + ')'" :value="item.jiaoliangonghao" />
          </el-select>
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
      coachList: [],
      ruleList: []
    };
  },
  methods: {
    init(id) {
      this.showFlag = true;
      this.form = { shangkezt: '待上课' };
      this.getCoachList();
      this.getRuleList();
      if (id) {
        this.$http({
          url: "kechenganpai/info/" + id,
          method: "get"
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.form = data.data;
            // 排班计划管理只保留到月份精度
            if (this.form.paiBanriqi && this.form.paiBanriqi.length > 7) {
              this.form.paiBanriqi = this.form.paiBanriqi.substring(0, 7);
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
    getRuleList() {
      this.$http({
        url: "paibanguize/page",
        method: "get",
        params: { page: 1, limit: 100 }
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.ruleList = data.data.list.filter(r => r.zhuangtai === '启用');
        }
      });
    },
    onTimeSlotChange(val) {
      this.form.kechengmingcheng = '练车排班-' + val;
    },
    onCoachChange(val) {
      const coach = this.coachList.find(c => c.jiaoliangonghao === val);
      if (coach) {
        this.form.jiaolianxingming = coach.jiaolianxingming;
        this.form.jiaoliangonghao = coach.jiaoliangonghao;
      }
    },
    submitForm() {
      if (!this.form.paiBanriqi) {
        this.$message.warning('请选择排班日期');
        return;
      }
      if (!this.form.shijianduan) {
        this.$message.warning('请选择时间段');
        return;
      }
      if (!this.form.jiaoliangonghao) {
        this.$message.warning('请选择教练');
        return;
      }
      if (!this.form.kechengmingcheng) {
        this.form.kechengmingcheng = '练车排班-' + this.form.shijianduan;
      }
      this.form.kemuleixing = '科目二/三';
      const submitForm = {
        ...this.form,
        paiBanriqi: this.normalizeMonthDate(this.form.paiBanriqi)
      };
      const url = this.form.id ? "kechenganpai/update" : "kechenganpai/save";
      this.$http({
        url: url,
        method: "post",
        data: submitForm
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
    },
    normalizeMonthDate(value) {
      if (!value) {
        return value;
      }
      if (/^\d{4}-\d{2}$/.test(value)) {
        return value + '-01';
      }
      return value;
    }
  }
};
</script>

<style scoped>
</style>
