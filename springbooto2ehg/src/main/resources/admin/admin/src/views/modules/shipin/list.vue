<template>
  <div class="main-content">
    <!-- 列表页 -->
    <div v-if="!showFlag">
      <el-form :inline="true" :model="searchForm" class="form-content">
        <el-form-item label="标题">
          <el-input v-model="searchForm.biaoti" placeholder="视频标题" clearable></el-input>
        </el-form-item>
        <el-form-item label="分类">
          <el-select v-model="searchForm.fenlei" placeholder="请选择分类" clearable>
            <el-option label="科目一" value="科目一" />
            <el-option label="科目四" value="科目四" />
            <el-option label="其他" value="其他" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button round @click="search()">查询</el-button>
          <el-button v-if="isAuth('shipin','新增')" type="primary" @click="addOrUpdateHandle()" round>新增</el-button>
          <el-button
            :disabled="dataListSelections.length <= 0"
            v-if="isAuth('shipin','删除')"
            type="danger"
            @click="deleteHandler()"
            round
          >删除</el-button>
        </el-form-item>
      </el-form>
      <div class="table-content">
        <el-table
          v-loading="listLoading"
          :data="list"
          element-loading-text="正在加载"
          border
          @selection-change="selectionChangeHandler"
          style="width: 100%"
        >
          <el-table-column type="selection" header-align="center" align="center" width="50" />
          <el-table-column prop="id" label="ID" width="80" />
          <el-table-column prop="biaoti" label="标题" />
          <el-table-column prop="fenlei" label="分类" width="120" />
          <el-table-column label="视频" width="120">
            <template slot-scope="scope">
              <div v-if="scope.row.shipinwenjian" class="video-preview-container">
                <video 
                  :src="baseUrl +'/upload/'+ scope.row.shipinwenjian" 
                  width="100" 
                  height="60" 
                  controls 
                  class="video-preview"
                />
                <div v-if="videoLoading[scope.row.id]" class="video-loading-overlay">
                  <div class="loading-spinner"></div>
                </div>
              </div>
              <span v-else>无视频</span>
            </template>
          </el-table-column>
          <el-table-column prop="dianzanshu" label="点赞数" width="100" />
          <el-table-column prop="shoucangshu" label="收藏数" width="100" />
          <el-table-column label="操作" width="300" align="center">
            <template slot-scope="scope">
              <el-button v-if="isAuth('shipin','修改')" type="primary" size="small" @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
              <el-button v-if="isAuth('shipin','删除')" type="danger" size="small" @click="deleteHandler(scope.row.id)">删除</el-button>
              <el-button v-if="isAuth('shipin','查看')" type="info" size="small" @click="viewComments(scope.row.id)">评论</el-button>
              <el-button 
                :type="isLiked[scope.row.id] ? 'success' : 'default'" 
                size="small" 
                @click="toggleLike(scope.row)"
              >
                <i class="el-icon-star-on"></i> {{isLiked[scope.row.id] ? '已点赞' : '点赞'}}
              </el-button>
              <el-button 
                :type="isCollected[scope.row.id] ? 'warning' : 'default'" 
                size="small" 
                @click="toggleCollect(scope.row)"
              >
                <i class="el-icon-star"></i> {{isCollected[scope.row.id] ? '已收藏' : '收藏'}}
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          @size-change="sizeChangeHandle"
          @current-change="currentChangeHandle"
          :current-page="page"
          :page-sizes="[10, 20, 50, 100]"
          :page-size="limit"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
        />
      </div>
    </div>
    <!-- 新增/修改对话框 -->
    <el-dialog :title="title" :visible.sync="dialogVisible" width="800px">
      <el-form ref="form" :model="form" label-width="100px">
        <el-form-item label="标题">
          <el-input v-model="form.biaoti" placeholder="请输入视频标题" />
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
          <div v-if="form.shipinwenjian" style="margin-top:5px;">
            <el-button type="text" size="small" @click="form.shipinwenjian = ''">删除视频</el-button>
          </div>
        </el-form-item>
        <el-form-item label="视频封面">
          <file-upload v-model="form.fengmian" :limit="1" />
        </el-form-item>
        <el-form-item label="描述">
          <el-input type="textarea" v-model="form.miaoshu" placeholder="请输入视频描述" rows="3" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="back()">取消</el-button>
        <el-button type="primary" @click="submitForm">确定</el-button>
      </div>
    </el-dialog>
    <!-- 评论对话框 -->
    <el-dialog :title="'视频评论'" :visible.sync="commentDialogVisible" width="600px">
      <div class="comment-form">
        <div class="comment-input-container">
          <el-input 
            v-model="commentContent" 
            type="textarea" 
            placeholder="请输入评论内容..." 
            rows="3"
            class="comment-textarea"
          ></el-input>
          <el-button 
            type="primary" 
            @click="submitComment" 
            :disabled="!commentContent.trim()"
            class="submit-btn"
          >
            提交评论
          </el-button>
        </div>
      </div>
      <div class="comment-list">
        <div v-for="(item, index) in commentList" :key="index" class="comment-item">
          <div class="comment-avatar">{{item.pinglunren?item.pinglunren.substring(0,1):'管'}}</div>
          <div class="comment-content">
            <div class="comment-header">
              <span class="comment-user">{{item.pinglunren}}</span>
              <span class="comment-time">{{formatDateTime(item.pingluntime)}}</span>
              <el-button type="text" size="small" @click="showReplyForm(item.id)" class="reply-btn">回复</el-button>
            </div>
            <div class="comment-text">{{item.pinglunneirong}}</div>
            <!-- 显示回复 -->
            <div v-if="item.replies && item.replies.length > 0" class="comment-replies">
              <div v-for="(reply, replyIndex) in item.replies" :key="replyIndex" class="reply-item">
                <div class="reply-avatar">{{reply.pinglunren?reply.pinglunren.substring(0,1):'管'}}</div>
                <div class="reply-content">
                  <div class="reply-header">
                    <span class="reply-user">{{reply.pinglunren}}</span>
                    <span class="reply-time">{{formatDateTime(reply.pingluntime)}}</span>
                  </div>
                  <div class="reply-text">{{reply.pinglunneirong}}</div>
                </div>
              </div>
            </div>
            <div v-if="replyToId === item.id" class="reply-form">
              <el-input 
                v-model="replyContent" 
                type="textarea" 
                placeholder="请输入回复内容..." 
                rows="2"
                class="reply-textarea"
              ></el-input>
              <div class="reply-actions">
                <el-button size="small" @click="cancelReply">取消</el-button>
                <el-button type="primary" size="small" @click="submitReply(item.id)" :disabled="!replyContent.trim()">提交回复</el-button>
              </div>
            </div>
          </div>
        </div>
        <div v-if="commentList.length == 0" class="empty-comment">暂无评论</div>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="commentDialogVisible = false">关闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      baseUrl: '/springbooto2ehg',
      list: [],
      total: 0,
      page: 1,
      limit: 10,
      listLoading: false,
      dialogVisible: false,
      title: '',
      form: {},
      uploadHeaders: {
        Token: this.$storage.get('Token')
      },
      searchForm: {
        biaoti: '',
        fenlei: ''
      },
      dataListSelections: [],
      showFlag: false,
      videoLoading: {},
      isLiked: {},
      isCollected: {},
      commentDialogVisible: false,
      currentVideoId: null,
      commentList: [],
      commentContent: '',
      replyContent: '',
      replyToId: null
    }
  },
  mounted() {
    this.search()
  },
  methods: {
    search() {
      this.page = 1
      this.getList()
    },
    selectionChangeHandler(val) {
      this.dataListSelections = val
    },
    getList() {
      this.listLoading = true
      var params = {
        page: this.page,
        limit: this.limit
      }
      if (this.searchForm.biaoti) {
              params['biaoti'] = this.searchForm.biaoti
            }
            if (this.searchForm.fenlei) {
              params['fenlei'] = this.searchForm.fenlei
            }
      this.$http.get('shipin/page', {
        params: params
      }).then(res => {
        if (res.data.code === 0) {
          this.list = res.data.data.list
          this.total = res.data.data.totalCount
          // 检查互动状态
          this.checkInteractionStatus()
        } else {
          this.$message.error(res.data.msg)
        }
        this.listLoading = false
      })
    },
    sizeChangeHandle(val) {
      this.limit = val
      this.page = 1
      this.getList()
    },
    currentChangeHandle(val) {
      this.page = val
      this.getList()
    },
    addOrUpdateHandle(id) {
      this.showFlag = true
      this.$nextTick(() => {
        this.dialogVisible = true
        this.form = {}
        if (id) {
          this.title = '修改视频'
          this.$http.get('shipin/info/' + id).then(res => {
            if (res.data.code === 0) {
              this.form = res.data.data
            }
          })
        } else {
          this.title = '新增视频'
        }
      })
    },
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
      const url = this.form.id ? 'shipin/update' : 'shipin/save'
      this.$http.post(url, this.form).then(res => {
        if (res.data.code === 0) {
          this.$message.success('操作成功')
          this.dialogVisible = false
          this.showFlag = false
          this.getList()
        } else {
          this.$message.error(res.data.msg)
        }
      })
    },
    deleteHandler(id) {
      var ids = id
        ? [Number(id)]
        : this.dataListSelections.map(item => {
            return Number(item.id)
          })
      this.$confirm(`确定进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http.post('shipin/delete', ids).then(res => {
          if (res.data.code === 0) {
            this.$message.success('删除成功')
            this.getList()
          } else {
            this.$message.error(res.data.msg)
          }
        })
      })
    },
    back() {
      this.showFlag = false
      this.dialogVisible = false
    },
    // 点赞功能
    toggleLike(row) {
      // 检查用户角色，管理员不能点赞
      if (this.$storage.get('role') === 'admin') {
        this.$message.error('管理员账户不能点赞')
        return
      }
      const videoId = row.id
      const isCurrentlyLiked = this.isLiked[videoId]
      
      if (isCurrentlyLiked) {
        // 取消点赞
        this.$http.post('storeup/deleteByType', {
          refid: videoId,
          tablename: 'shipin',
          type: 1
        }).then(res => {
          if (res.data.code === 0) {
            this.$set(this.isLiked, videoId, false)
            row.dianzanshu = Math.max(0, (row.dianzanshu || 0) - 1)
            this.$message.success('取消点赞成功')
          } else {
            this.$message.error(res.data.msg || '操作失败')
          }
        })
      } else {
        // 添加点赞
        this.$http.post('storeup/add', {
          refid: videoId,
          tablename: 'shipin',
          type: 1
        }).then(res => {
          if (res.data.code === 0) {
            this.$set(this.isLiked, videoId, true)
            row.dianzanshu = (row.dianzanshu || 0) + 1
            this.$message.success('点赞成功')
          } else {
            this.$message.error(res.data.msg || '操作失败')
          }
        })
      }
    },
    // 收藏功能
    toggleCollect(row) {
      // 检查用户角色，管理员不能收藏
      if (this.$storage.get('role') === 'admin') {
        this.$message.error('管理员账户不能收藏')
        return
      }
      const videoId = row.id
      const isCurrentlyCollected = this.isCollected[videoId]
      
      if (isCurrentlyCollected) {
        // 取消收藏
        this.$http.post('storeup/deleteByType', {
          refid: videoId,
          tablename: 'shipin',
          type: 2
        }).then(res => {
          if (res.data.code === 0) {
            this.$set(this.isCollected, videoId, false)
            row.shoucangshu = Math.max(0, (row.shoucangshu || 0) - 1)
            this.$message.success('取消收藏成功')
          } else {
            this.$message.error(res.data.msg || '操作失败')
          }
        })
      } else {
        // 添加收藏
        this.$http.post('storeup/add', {
          refid: videoId,
          tablename: 'shipin',
          type: 2
        }).then(res => {
          if (res.data.code === 0) {
            this.$set(this.isCollected, videoId, true)
            row.shoucangshu = (row.shoucangshu || 0) + 1
            this.$message.success('收藏成功')
          } else {
            this.$message.error(res.data.msg || '操作失败')
          }
        })
      }
    },
    // 查看评论
    viewComments(videoId) {
      this.currentVideoId = videoId
      this.commentDialogVisible = true
      this.getCommentList(videoId)
    },
    // 获取评论列表
    getCommentList(videoId) {
      this.$http.get('shipinpinglun/list', {
        params: {
          shipinid: videoId,
          sort: 'pingluntime',
          order: 'desc'
        }
      }).then(res => {
        if (res.data.code === 0) {
          const allComments = res.data.data.list
          // 构建评论树
          this.commentList = this.buildCommentTree(allComments)
        } else {
          this.$message.error(res.data.msg)
        }
      })
    },
    // 构建评论树
    buildCommentTree(comments) {
      const commentMap = new Map()
      const rootComments = []
      
      // 首先将所有评论放入map中
      comments.forEach(comment => {
        commentMap.set(comment.id, comment)
        comment.replies = []
      })
      
      // 构建评论树
      comments.forEach(comment => {
        if (comment.parentid) {
          // 是回复
          const parent = commentMap.get(comment.parentid)
          if (parent) {
            parent.replies.push(comment)
          }
        } else {
          // 是根评论
          rootComments.push(comment)
        }
      })
      
      return rootComments
    },
    // 检查点赞和收藏状态
    checkInteractionStatus() {
      this.list.forEach(item => {
        this.checkLikeStatus(item.id)
        this.checkCollectStatus(item.id)
      })
    },
    // 检查点赞状态
    checkLikeStatus(videoId) {
      this.$http.get('storeup/list', {
        params: {
          page: 1,
          limit: 1,
          tablename: 'shipin',
          refid: videoId,
          type: 1
        }
      }).then(res => {
        if (res.data.code === 0 && res.data.data.total > 0) {
          this.$set(this.isLiked, videoId, true)
        }
      })
    },
    // 检查收藏状态
    checkCollectStatus(videoId) {
      this.$http.get('storeup/list', {
        params: {
          page: 1,
          limit: 1,
          tablename: 'shipin',
          refid: videoId,
          type: 2
        }
      }).then(res => {
        if (res.data.code === 0 && res.data.data.total > 0) {
          this.$set(this.isCollected, videoId, true)
        }
      })
    },
    // 提交评论
    submitComment() {
      if (!this.commentContent.trim()) {
        this.$message.error('请输入评论内容')
        return
      }
      // 检查用户角色，管理员不能评论
      if (this.$storage.get('role') === 'admin') {
        this.$message.error('管理员账户不能评论')
        return
      }
      this.$http.post('shipinpinglun/add', {
        shipinid: this.currentVideoId,
        pinglunren: this.$storage.get('username') || this.$storage.get('name') || '用户',
        pinglunneirong: this.commentContent
      }).then(res => {
        if (res.data.code === 0) {
          this.$message.success('评论成功')
          this.commentContent = ''
          this.getCommentList(this.currentVideoId)
        } else {
          this.$message.error(res.data.msg || '评论失败')
        }
      })
    },
    // 格式化时间戳为日期时间
    formatDateTime(timestamp) {
      if (!timestamp) return ''
      const date = new Date(timestamp)
      const year = date.getFullYear()
      const month = String(date.getMonth() + 1).padStart(2, '0')
      const day = String(date.getDate()).padStart(2, '0')
      const hours = String(date.getHours()).padStart(2, '0')
      const minutes = String(date.getMinutes()).padStart(2, '0')
      const seconds = String(date.getSeconds()).padStart(2, '0')
      return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`
    },
    // 显示回复表单
    showReplyForm(commentId) {
      this.replyToId = commentId
      this.replyContent = ''
    },
    // 取消回复
    cancelReply() {
      this.replyToId = null
      this.replyContent = ''
    },
    // 提交回复
    submitReply(commentId) {
      if (!this.replyContent.trim()) {
        this.$message.error('请输入回复内容')
        return
      }
      // 检查用户角色，管理员不能回复
      if (this.$storage.get('role') === 'admin') {
        this.$message.error('管理员账户不能回复')
        return
      }
      this.$http.post('shipinpinglun/reply', {
        shipinid: this.currentVideoId,
        pinglunneirong: this.replyContent,
        pinglunren: this.$storage.get('username') || this.$storage.get('name') || '用户',
        parentid: commentId
      }).then(res => {
        if (res.data.code === 0) {
          this.$message.success('回复成功')
          this.replyToId = null
          this.replyContent = ''
          this.getCommentList(this.currentVideoId)
        } else {
          this.$message.error(res.data.msg || '回复失败')
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.form-content {
  background: transparent;
}
.table-content {
  background: transparent;
}
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

.video-preview-container {
  position: relative;
  display: inline-block;
}

.video-loading-overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.6);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 10;
}

.loading-spinner {
  width: 20px;
  height: 20px;
  border: 2px solid rgba(255, 255, 255, 0.3);
  border-radius: 50%;
  border-top: 2px solid #409EFF;
  animation: spin 1s linear infinite;
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}

.comment-list {
  max-height: 400px;
  overflow-y: auto;
}

.comment-item {
  display: flex;
  padding: 15px 0;
  border-bottom: 1px solid #eee;
}

.comment-avatar {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background: #409EFF;
  color: #fff;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 18px;
  margin-right: 15px;
  flex-shrink: 0;
}

.comment-content {
  flex: 1;
}

.comment-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 8px;
}

.comment-user {
  font-weight: bold;
  color: #333;
}

.comment-time {
  color: #999;
  font-size: 12px;
}

.comment-text {
  color: #666;
  line-height: 1.6;
}

.comment-reply {
  margin-top: 8px;
  padding: 8px;
  background: #f5f5f5;
  border-radius: 5px;
  color: #666;
  font-size: 13px;
}

.empty-comment {
  text-align: center;
  padding: 30px 0;
  color: #999;
}

.comment-form {
  margin-bottom: 20px;
  padding-bottom: 20px;
  border-bottom: 1px solid #eee;
}

.comment-input-container {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.comment-textarea {
  width: 100%;
  resize: none;
}

.submit-btn {
  align-self: flex-end;
}

.reply-btn {
  font-size: 12px;
  padding: 0;
  margin-left: 10px;
}

.reply-form {
  margin-top: 10px;
  padding: 10px;
  background: #f9f9f9;
  border-radius: 5px;
}

.reply-textarea {
  width: 100%;
  resize: none;
  margin-bottom: 10px;
}

.reply-actions {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}

.comment-replies {
  margin-top: 10px;
  margin-left: 40px;
  border-left: 2px solid #eee;
  padding-left: 15px;
}

.reply-item {
  display: flex;
  margin-bottom: 10px;
  padding-bottom: 10px;
  border-bottom: 1px solid #f5f5f5;
}

.reply-avatar {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  background-color: #409EFF;
  color: white;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 14px;
  margin-right: 10px;
  flex-shrink: 0;
}

.reply-content {
  flex: 1;
}

.reply-header {
  display: flex;
  align-items: center;
  margin-bottom: 5px;
}

.reply-user {
  font-weight: 500;
  margin-right: 10px;
  font-size: 14px;
}

.reply-time {
  font-size: 12px;
  color: #999;
}

.reply-text {
  font-size: 14px;
  line-height: 1.4;
}
</style>
