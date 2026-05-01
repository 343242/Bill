<template>
  <div class="app-container">
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
      <el-button @click="$emit('close')">取消</el-button>
      <el-button type="primary" @click="submitForm">确定</el-button>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    form: {
      type: Object,
      default: () => ({})
    }
  },
  methods: {
    submitForm() {
      this.$refs.form.validate(valid => {
        if (valid) {
          this.$emit('submit', this.form)
        }
      })
    }
  }
}
</script>

<style scoped>
</style>