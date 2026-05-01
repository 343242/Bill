<template>
  <div class="main-content">
    <div v-if="showFlag">
      <el-form ref="form" :model="form" label-width="100px">
        <el-form-item label="参数名称">
          <el-select v-model="form.canshumingcheng" placeholder="请选择或输入参数名称" filterable allow-create style="width: 100%" @change="onParamNameChange">
            <el-option label="预约名额配置" value="预约名额配置" />
            <el-option label="取消预约限制" value="取消预约限制" />
            <el-option label="考试时长" value="考试时长" />
            <el-option label="练车预约开放天数" value="练车预约开放天数" />
          </el-select>
        </el-form-item>
        <el-form-item label="参数键">
          <el-input v-model="form.canshujian" placeholder="参数键（英文标识）" />
        </el-form-item>
        <el-form-item label="参数值">
          <el-input-number v-model.number="form.canshuzhi" :min="1" :max="999" placeholder="请输入参数值" style="width: 100%" />
        </el-form-item>
        <el-form-item label="说明">
          <el-input type="textarea" v-model="form.beizhu" placeholder="请输入参数说明" :rows="2" />
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
      paramKeyMap: {
        '预约名额配置': 'yuyue_mingee',
        '取消预约限制': 'quxiao_shixiao',
        '考试时长': 'kaoshi_shichang',
        '练车预约开放天数': 'yuyue_kaifang_tianshu'
      },
      paramDescMap: {
        '预约名额配置': '每个教练排班时段最大可预约学员数',
        '取消预约限制': '提前多少小时可以取消预约(小时)',
        '考试时长': '理论考试时长(分钟)',
        '练车预约开放天数': '提前多少天开放预约(天)'
      }
    };
  },
  methods: {
    init(id) {
      this.showFlag = true;
      this.form = { zhuangtai: '启用' };
      if (id) {
        this.$http({
          url: "xitongcanshu/info/" + id,
          method: "get"
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.form = data.data;
          }
        });
      }
    },
    onParamNameChange(val) {
      if (this.paramKeyMap[val]) {
        this.form.canshujian = this.paramKeyMap[val];
      }
      if (this.paramDescMap[val]) {
        this.form.beizhu = this.paramDescMap[val];
      }
    },
    submitForm() {
      if (!this.form.canshumingcheng) {
        this.$message.warning('请输入参数名称');
        return;
      }
      if (!this.form.canshuzhi) {
        this.$message.warning('请输入参数值');
        return;
      }
      const url = this.form.id ? "xitongcanshu/update" : "xitongcanshu/save";
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
