<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="100px">
      <el-form-item label="标题">
        <el-input v-model="form.biaoti" placeholder="请输入标题" />
      </el-form-item>
      <el-form-item label="分类">
        <el-select v-model="form.fenlei" placeholder="请选择分类">
          <el-option label="科目一" value="科目一" />
          <el-option label="科目四" value="科目四" />
          <el-option label="其他" value="其他" />
        </el-select>
      </el-form-item>
      <el-form-item label="视频文件">
        <el-upload
          class="video-uploader"
          action="/springbooto2ehg/file/upload"
          :headers="uploadHeaders"
          :show-file-list="false"
          :on-success="handleVideoSuccess"
          :before-upload="beforeVideoUpload"
          accept="video/*"
        >
          <video v-if="form.shipinwenjian" :src="baseUrl +'/upload/'+ form.shipinwenjian" controls class="video-preview" />
          <i v-else class="el-icon-plus video-uploader-icon"></i>
        </el-upload>
      </el-form-item>
      <el-form-item label="视频封面">
        <file-upload v-model="form.fengmian" :limit="1" />
      </el-form-item>
      <el-form-item label="描述">
        <el-input type="textarea" v-model="form.miaoshu" placeholder="请输入描述" />
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
  data() {
    return {
      baseUrl: '/springbooto2ehg',
      uploadHeaders: {
        Token: this.$storage ? this.$storage.get('Token') : ''
      }
    }
  },
  methods: {
    handleVideoSuccess(res, file) {
      if (res.code === 0) {
        this.$set(this.form, 'shipinwenjian', res.file)
        this.$message.success('视频上传成功')
      } else {
        this.$message.error(res.msg || '上传失败')
      }
    },
    beforeVideoUpload(file) {
      const isVideo = file.type.startsWith('video/')
      const isLt100M = file.size / 1024 / 1024 < 100
      if (!isVideo) {
        this.$message.error('只能上传视频文件!')
        return false
      }
      if (!isLt100M) {
        this.$message.error('视频文件大小不能超过100MB!')
        return false
      }
      return true
    },
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
.video-uploader >>> .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  width: 300px;
  height: 170px;
}
.video-uploader >>> .el-upload:hover {
  border-color: #409EFF;
}
.video-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 300px;
  height: 170px;
  line-height: 170px;
  text-align: center;
}
.video-preview {
  width: 300px;
  height: 170px;
  display: block;
}
</style>